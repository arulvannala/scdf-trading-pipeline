package io.daniellavoie.scdf.trading.pricer.portfolio;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.daniellavoie.scdf.trading.pricer.PortfolioPricerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortfolioPricerApplication.class)
public class PortfolioServiceImplIT {
	@Autowired
	private PortfolioService portfolioService;

	@Test
	public void test() {
		Assert.assertNotNull(portfolioService.findAll().blockFirst());
	}
}
