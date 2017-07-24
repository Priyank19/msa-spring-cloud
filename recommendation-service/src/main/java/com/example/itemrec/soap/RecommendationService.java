package com.example.itemrec.soap;

import java.util.List;

public interface RecommendationService {
	public Recommendation getRecommendation(int itemId);

	public List<Recommendation> getRecommendations(List<Integer> itemIds);
}
