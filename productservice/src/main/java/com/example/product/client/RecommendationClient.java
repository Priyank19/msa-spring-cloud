package com.example.product.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.product.model.Recommendation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.HystrixCollapser.Scope;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Component
public class RecommendationClient {

	private static Logger logger = LoggerFactory.getLogger(RecommendationClient.class);
	private final static String recommendtionsUri = "http://localhost:9008/recommendations?ids={ids}";
	private boolean DIY = true;

	@HystrixCollapser(scope = Scope.GLOBAL, batchMethod = "getItemRecommendations", collapserProperties = {
			@HystrixProperty(name = "timerDelayInMilliseconds", value = "5000") })
	public Future<String> getItemRecommendation(Integer id) {
		throw new RuntimeException("This method body should not be executed");
	}

	@HystrixCommand
	public List<String> getItemRecommendations(List<Integer> ids) {
		List<String> values = new ArrayList<>();
		if (DIY ) {
			// Removing duplicate
			List<Integer> uniqueIds = ids.stream().distinct().collect(Collectors.toList());
			String idsCSV = String.join(",", uniqueIds.toString()).replace("[", "").replace("]", "");
			logger.debug("Collapsing core services for " + idsCSV);
			Recommendation[] uniqueRes = new RestTemplate().getForObject(recommendtionsUri, Recommendation[].class,
					idsCSV);
			for (Recommendation r : uniqueRes) {
				logger.debug("Response " + r);
			}
			// Substituting removed duplicates
			List<Recommendation> response = ids.stream()
					.map(it -> Arrays.stream(uniqueRes).filter(u -> (u.getId() == it.intValue())).findFirst().get())
					.collect(Collectors.toList());
			// Convert them to JSON Strings.
			ObjectMapper mapper = new ObjectMapper();
			try {
				for (Recommendation rec : response) {
					values.add(mapper.writeValueAsString(rec));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return values;
	}
}
