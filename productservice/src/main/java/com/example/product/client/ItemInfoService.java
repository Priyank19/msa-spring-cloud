package com.example.product.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// Benefits to keep the signature of the name and fallback same.
public interface ItemInfoService {

	@RequestMapping("/iteminfo/{id}")
	public String getItemInfo(@PathVariable("id") int id);

}
