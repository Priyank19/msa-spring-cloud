package com.niit.product;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductServiceImpl implements ProductService {
	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	private final String infoUri;
	private final String reviewUri;

	public ProductServiceImpl() {
		this.infoUri = "localhost:9090";
		this.reviewUri = "localhost:9191";
	}

	@Override
	public Product getProduct(int id) {
		logger.debug("Aggregating core services");
		String itemInfoJson = new RestTemplate().getForObject("http://" + infoUri + "/iteminfo/{id}", String.class, id);
		String itemReviewJson = new RestTemplate().getForObject("http://" + reviewUri + "/itemreview/{id}",
				String.class, id);
		// Anti-corruption layer
		ObjectMapper mapper = new ObjectMapper();
		ItemInfo itemInfo = null;
		ItemReview itemReview = null;
		try {
			itemInfo = mapper.readValue(itemInfoJson, ItemInfo.class);
			itemReview = mapper.readValue(itemReviewJson, ItemReview.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Product product = new Product(id, itemInfo, itemReview);
		return product;
	}

}
