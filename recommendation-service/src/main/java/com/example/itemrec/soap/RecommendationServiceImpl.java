package com.example.itemrec.soap;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemrec.soap.client.CountryClient;
import com.example.itemrec.soap.client.QuoteClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import country.wsdl.GetCountryResponse;
import hello.wsdl.GetQuoteResponse;

@Service
public class RecommendationServiceImpl implements RecommendationService {
	private static Logger logger = LoggerFactory.getLogger(RecommendationServiceImpl.class);

	@Autowired
	CountryClient countryClient;
	@Autowired
	QuoteClient quoteClient;

	@Override
	@HystrixCommand(fallbackMethod = "localRecommendation")
	public Recommendation getRecommendation(int itemId) {
		GetQuoteResponse response = quoteClient.getQuote("MSFT");
		String quote = response.getGetQuoteResult();
		System.err.println(quote);
		Recommendation recommendation = new Recommendation();
		logger.debug("Recommendation Object");
		recommendation.setId(itemId * 2);
		recommendation.setItemId(itemId);
		recommendation.setRecommendation(3);
		return recommendation;
	}

	@HystrixCommand(fallbackMethod = "defaultRecommendation")
	public Recommendation localRecommendation(int itemId) {
		GetCountryResponse response = countryClient.getCountry("Spain");
		String capital = response.getCountry().getCapital();
		System.err.println(capital);
		Recommendation recommendation = new Recommendation();
		logger.debug("Mock Recommendation Object");
		recommendation.setId(itemId * 999);
		recommendation.setItemId(itemId * 999);
		recommendation.setRecommendation(999);
		return recommendation;
	}

	public Recommendation defaultRecommendation(int itemId) {
		Recommendation recommendation = new Recommendation();
		logger.debug("Default Recommendation Object");
		recommendation.setId(itemId * -1);
		recommendation.setItemId(itemId * -1);
		recommendation.setRecommendation(-1);
		return recommendation;
	}

	@Override
	public List<Recommendation> getRecommendations(List<Integer> itemIds) {
		List<Recommendation> recommendations = new ArrayList<>();
		for (int itemId : itemIds) {
			Recommendation recommendation = new Recommendation();
			recommendation.setId(itemId);
			recommendation.setItemId(itemId * itemId * 2);
			recommendation.setRecommendation(itemId * 2);
			recommendations.add(recommendation);
		}
		return recommendations;
	}
}
