package com.niit.item.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.item.review.ItemReview;

@RestController
public class ItemReviewController {

	private static Logger logger = LoggerFactory.getLogger(ItemReviewController.class);
	@Autowired
	private ItemReviewService itemReviewService;

	@RequestMapping(value = "/")
	String home() {
		logger.info("Access /");
		return "Hi! from Item Review!";
	}

	@RequestMapping(value = "/itemreview/{id}", method = RequestMethod.GET, produces = "application/json")
	ItemReview getItemReview(@PathVariable("id") int id) {
		logger.info("Using pure spring rest.");
		return this.itemReviewService.getItemReview(id);
	}

}
