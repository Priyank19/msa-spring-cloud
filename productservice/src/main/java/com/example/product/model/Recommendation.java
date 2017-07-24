package com.example.product.model;

public class Recommendation {

	private int id;
	private int itemId;
	private double recommendation;

	public Recommendation() {
		super();
	}

	public Recommendation(int id, int itemId, double recommendation) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.recommendation = recommendation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public double getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(double recommendation) {
		this.recommendation = recommendation;
	}

	@Override
	public String toString() {
		return "Recommendation [id=" + id + ", itemId=" + itemId + ", recommendation=" + recommendation + "]";
	}
}
