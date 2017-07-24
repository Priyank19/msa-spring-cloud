package com.example.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.model.Product;

@RestController
public class ProductController {

	private static Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;
	@Autowired
	private Environment environment;

	@RequestMapping(value = "/")
	String home() {
		String value = environment.getProperty("item.provider");
		logger.info("Access / " + value);
		return "Hi! from Product!";
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = "application/json")
	Product getProduct(@PathVariable("id") int id) {
		logger.info("Using pure spring rest.");
		return this.productService.getProduct(id);
	}

}
