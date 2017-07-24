package com.example.product;

import com.example.product.model.Product;

public interface ProductService {
	public Product getProduct(int id);
	
	public String getItemReview(int id);
}
