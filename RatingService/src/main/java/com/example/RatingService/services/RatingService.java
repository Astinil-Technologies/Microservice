package com.example.RatingService.services;

import java.util.List;

import com.example.RatingService.Entity.Rating;

public interface RatingService {

	//create
	Rating create(Rating rating);
	
	//get all ratings
	List<Rating> getRatings();
	
	//get all by userId
	List<Rating> getRatingsByUserId(String userId);
	
	//get all by hotel
	List<Rating> getRatingsByHotelId(String userId);
}
