package com.example.item.review;

public class ItemReview {

	private int id;
	private double rating;
	private String reviews;

	public ItemReview() {
		super();
	}

	public ItemReview(int id, double rating, String reviews) {
		super();
		this.id = id;
		this.rating = rating;
		this.reviews = reviews;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "ItemReview [id=" + id + ", rating=" + rating + ", reviews=" + reviews + "]";
	}

}
