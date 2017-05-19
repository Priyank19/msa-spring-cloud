package com.niit.product;

public class ItemReview {

	private int id;
	private String rating;
	private String reviews;

	public ItemReview() {
		super();
	}

	public ItemReview(int id, String rating, String reviews) {
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

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
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
