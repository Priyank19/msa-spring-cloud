package com.example.product;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.product.client.ItemInfoClient;
import com.example.product.client.RecommendationClient;
import com.example.product.model.ItemInfo;
import com.example.product.model.ItemReview;
import com.example.product.model.Product;
import com.example.product.model.Recommendation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ProductServiceImpl implements ProductService {
	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	private final String reviewUri = "http://localhost:9002/itemreview/{id}";

	@Autowired
	ProductService selfRef;

	@Autowired
	ItemInfoClient itemInfoClient;

	@Autowired
	RecommendationClient recommendationClient;

	private boolean enableCollapser = true;

	@Override
	public Product getProduct(int id) {
		logger.debug("Aggregating core services");
		// Lets use Feign
		String itemInfoJson = itemInfoClient.getItemInfo(id);
		// Lets use Hystrix
		String itemReviewJson = selfRef.getItemReview(id);
		Future<String> itemRecommendationJsonFuture = null;
		if (enableCollapser) {
			// Lets use Hystrix Collapser
			itemRecommendationJsonFuture = recommendationClient.getItemRecommendation(id);
		}
		// Anti-corruption layer
		ObjectMapper mapper = new ObjectMapper();
		ItemInfo itemInfo = null;
		ItemReview itemReview = null;
		Recommendation itemRecommendation = null;
		try {
			itemInfo = mapper.readValue(itemInfoJson, ItemInfo.class);
			itemReview = mapper.readValue(itemReviewJson, ItemReview.class);
			if (enableCollapser) {
				itemRecommendation = mapper.readValue(itemRecommendationJsonFuture.get(), Recommendation.class);
			}
		} catch (IOException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		Product product = new Product(id, itemInfo, itemReview);
		if (enableCollapser) {
			product.setRecommendation(itemRecommendation);
		}
		return product;
	}

	@Override
	@HystrixCommand(fallbackMethod = "defaultReview")
	public String getItemReview(int id) {
		String itemReviewJson = new RestTemplate().getForObject(reviewUri, String.class, id);
		return itemReviewJson;
	}

	private String defaultReview(int id) {
		return "{\"id\":1,\"rating\":\"un-rated\",\"reviews\":\"un-reviewed\"}";
	}
}
