package io.daniellavoie.scdf.trading.pricer;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.daniellavoie.scdf.trading.pricer.model.PortfolioPricing;
import io.daniellavoie.scdf.trading.pricer.producer.PortfolioPricingProducer;

@Service
public class PortfolioNotificationServiceImpl implements PortfolioNotificationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PortfolioNotificationService.class);

	private final PortfolioPricingProducer portfolioPricingProducer;

	private final Map<Long, PortfolioPricing> notifiedPortfolioById = new ConcurrentHashMap<>();

	public PortfolioNotificationServiceImpl(final PortfolioPricingProducer portfolioPricingProducer) {
		this.portfolioPricingProducer = portfolioPricingProducer;
	}

	@Override
	public void notifyPortfolio(PortfolioPricing portfolioPricing) {
		if (hasNeverBeenNotified(portfolioPricing) || productAdded(portfolioPricing) || productDeleted(portfolioPricing)
				|| productPriceChanged(portfolioPricing)) {
			notifiedPortfolioById.put(portfolioPricing.getPortfolio().getId(), portfolioPricing);

			LOGGER.info("Notifying pricing for portfolio " + portfolioPricing.getPortfolio().getId());

			portfolioPricingProducer.notifyPortfolio(portfolioPricing);
		}
	}

	private boolean hasNeverBeenNotified(PortfolioPricing portfolioPricing) {
		return !Optional.ofNullable(notifiedPortfolioById.get(portfolioPricing.getPortfolio().getId())).isPresent();
	}

	private boolean productAdded(PortfolioPricing portfolioPricing) {
		return portfolioPricing.getProductPricings().stream()

				.filter(existingProductPricing -> !notifiedPortfolioById.get(portfolioPricing.getPortfolio().getId())
						.getProductPricings().stream()
						.filter(productPricing -> productPricing.getProductId()
								.equals(existingProductPricing.getProductId()))
						.findAny().isPresent())

				.findAny().isPresent();
	}

	private boolean productDeleted(PortfolioPricing portfolioPricing) {
		return notifiedPortfolioById.get(portfolioPricing.getPortfolio().getId()).getProductPricings().stream()

				.filter(existingProductPricing -> !portfolioPricing.getProductPricings().stream()
						.filter(productPricing -> productPricing.getProductId()
								.equals(existingProductPricing.getProductId()))
						.findAny().isPresent())

				.findAny().isPresent();

	}

	private boolean productPriceChanged(PortfolioPricing portfolioPricing) {
		return portfolioPricing.getProductPricings().stream()

				.filter(productPricing -> notifiedPortfolioById.get(portfolioPricing.getPortfolio().getId())
						.getProductPricings().stream()

						.filter(existingProductPricing -> existingProductPricing.getProductId()
								.equals(productPricing.getProductId()))

						.filter(existingProductPricing -> Math.abs(
								existingProductPricing.getProductValue() - productPricing.getProductValue()) >= 0.001)

						.findAny().isPresent())

				.findAny().isPresent();
	}

}
