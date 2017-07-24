package com.example.item.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ItemReviewServiceImpl implements ItemReviewService {
	private static Logger logger = LoggerFactory.getLogger(ItemReviewServiceImpl.class);

	@Override
	public ItemReview getItemReview(int id) {
		logger.debug("Mock Item Review Object");
		ItemReview itemReview = new ItemReview();
		itemReview.setId(id);
		itemReview.setRating(3.5);
		String reviews = "{ title:Excellenet Book, text:reading it was fun!, reviewer:John }";
		itemReview.setReviews(reviews);
		return itemReview;
	}

}
