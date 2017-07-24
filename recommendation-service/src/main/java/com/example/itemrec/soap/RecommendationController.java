package com.example.itemrec.soap;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendationController {

	private static Logger logger = LoggerFactory.getLogger(RecommendationController.class);

	@Autowired
	private Environment environment;

	@Autowired
	private RecommendationService recommendationService;

	@RequestMapping(value = "/")
	String home() {
		logger.info("Access value ");
		String propertyValue = environment.getProperty("item.provider");
		return "Hi! from Recommendation! " + propertyValue;
	}

	@RequestMapping(value = "/recommendation/{id}", method = RequestMethod.GET, produces = "application/json")
	Recommendation getRecommendation(@PathVariable("id") int itemId) {
		logger.info("Using pure spring rest.");
		return recommendationService.getRecommendation(itemId);
	}

	@RequestMapping(value = "/recommendations", params = "ids", method = RequestMethod.GET, produces = "application/json")
	List<Recommendation> getRecommendations(@RequestParam("ids") List<Integer> itemIds) {
		logger.info("Using pure spring rest for a list -> " + itemIds);
		return recommendationService.getRecommendations(itemIds);
	}
}
