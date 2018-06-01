package io.daniellavoie.scdf.trading.pricer.producer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import io.daniellavoie.scdf.trading.pricer.model.PortfolioPricing;

@Service
@EnableBinding(PortfolioPricingSource.class)
public class PortfolioPricingProducerImpl implements PortfolioPricingProducer {
	private PortfolioPricingSource portfolioPricingSource;

	public PortfolioPricingProducerImpl(PortfolioPricingSource portfolioPricingSource) {
		this.portfolioPricingSource = portfolioPricingSource;
	}

	@Override
	public void notifyPortfolio(PortfolioPricing portfolioPricing) {
		portfolioPricingSource.output().send(MessageBuilder.withPayload(portfolioPricing).build());
	}
}
