package com.niit.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	private int id;
	private ItemInfo itemInfo;
	private ItemReview itemReview;

	public Product() {
	}

	public Product(int id, ItemInfo itemInfo, ItemReview itemReview) {
		super();
		this.id = id;
		this.itemInfo = itemInfo;
		this.itemReview = itemReview;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ItemInfo getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(ItemInfo itemInfo) {
		this.itemInfo = itemInfo;
	}

	public ItemReview getItemReview() {
		return itemReview;
	}

	public void setItemReview(ItemReview itemReview) {
		this.itemReview = itemReview;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", itemInfo=" + itemInfo + ", itemReview=" + itemReview + "]";
	}

}
