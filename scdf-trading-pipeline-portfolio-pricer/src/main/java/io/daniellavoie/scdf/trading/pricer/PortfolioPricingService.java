package io.daniellavoie.scdf.trading.pricer;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import io.daniellavoie.scdf.trading.model.gdax.GdaxMessage;
import io.daniellavoie.scdf.trading.pricer.model.PortfolioPricing;
import io.daniellavoie.scdf.trading.pricer.model.ProductPricing;
import io.daniellavoie.scdf.trading.pricer.portfolio.PortfolioService;
import io.daniellavoie.scdf.trading.pricer.portfolio.model.Portfolio;
import io.daniellavoie.scdf.trading.pricer.portfolio.model.PortfolioProduct;
import io.daniellavoie.scdf.trading.pricer.tick.TickConsumer;
import reactor.core.Disposable;

@Service
public class PortfolioPricingService implements ApplicationContextAware {
	private final Logger LOGGER = LoggerFactory.getLogger(PortfolioPricingService.class);

	private PortfolioNotificationService portfolioNotificationService;
	private PortfolioService portfolioService;
	private TickConsumer tickConsumer;

	private ApplicationContext context;

	private List<Portfolio> portfolios;
	private Map<String, Double> pricesByProductId = new ConcurrentHashMap<>();
	private Disposable subscription;

	public PortfolioPricingService(PortfolioNotificationService portfolioNotificationService,
			PortfolioService portfolioService, TickConsumer tickConsumer) {
		this.portfolioNotificationService = portfolioNotificationService;
		this.portfolioService = portfolioService;
		this.tickConsumer = tickConsumer;
	}

	@EventListener
	public void initialize(ContextRefreshedEvent contextRefreshedEvent) {
		if (subscription == null || subscription.isDisposed()) {
			subscription = portfolioService.findAll().collectList().doOnNext(portfolios -> this.portfolios = portfolios)

					.doOnNext(portfolios -> LOGGER.info("Loaded " + portfolios.size() + " portfolios."))

					.flatMapMany(voidValue -> tickConsumer.getTicks())

					.filter(message -> message.getProductId() != null && message.getBestAsk() != null)

					.doOnNext(message -> LOGGER.info("Received tick for product " + message.getProductId() + "."))

					.doOnNext(this::updateProductPrices)

					.map(GdaxMessage::getProductId)

					.flatMapIterable(this::computeImpactedPortfolio)

					.doOnNext(portfolio -> LOGGER.info("Portfolio " + portfolio.getId() + " impacted."))

					.map(this::computePortfolioPricing)

					.filter(Optional::isPresent)

					.map(Optional::get)

					.doOnNext(portfolioNotificationService::notifyPortfolio)

					.doOnError(this::shutdown)

					.subscribe();
		}
	}

	private List<Portfolio> computeImpactedPortfolio(String productId) {
		LOGGER.info("Computing impacted portfolios for " + productId + ".");

		List<Portfolio> portfolios = this.portfolios.stream()

				.filter(portfolio -> portfolioContainsProduct(portfolio, productId))

				.collect(Collectors.toList());

		LOGGER.info(portfolios.size() + " portfolios impacted by " + productId + ".");

		return portfolios;
	}

	private boolean portfolioContainsProduct(Portfolio portfolio, String productId) {
		boolean result = portfolio.getPortfolioProducts().stream()

				.filter(portfolioProduct -> productId.equals(portfolioProduct.getProductId()))

				.findAny().isPresent();

		return result;
	}

	private Optional<PortfolioPricing> computePortfolioPricing(Portfolio portfolio) {
		List<String> productsWithMissingPrice = portfolio.getPortfolioProducts().stream()

				.filter(portfolioProduct -> !Optional.ofNullable(pricesByProductId.get(portfolioProduct.getProductId()))
						.isPresent())

				.map(PortfolioProduct::getProductId)

				.collect(Collectors.toList());

		if (!productsWithMissingPrice.isEmpty()) {
			LOGGER.warn("Could not compute pricing for portfolio " + portfolio.getId()
					+ " due to missing prices for products " + productsWithMissingPrice);

			return Optional.empty();
		}

		return Optional.of(new PortfolioPricing(portfolio,
				portfolio.getPortfolioProducts().stream()
						.map(portfolioProduct -> new ProductPricing(portfolioProduct.getProductId(),
								pricesByProductId.get(portfolioProduct.getProductId()) + portfolioProduct.getAmount()))
						.collect(Collectors.toList())));
	}

	private void updateProductPrices(GdaxMessage gdaxMessage) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Updating prices cache.");
		}

		pricesByProductId.put(gdaxMessage.getProductId(), gdaxMessage.getBestAsk());
	}

	private void shutdown(Throwable ex) {
		LOGGER.error("Error while processing portfoling prices. Shuting down application.", ex);

		((ConfigurableApplicationContext) context).close();
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

}
