package com.example.UserService.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.UserService.entities.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

	//get
	
	//post
	@PostMapping("/ratings")
	public Rating createRating(Rating values);
	
	//PUT
	@PutMapping("/ratings/{ratingId}")
	public Rating updateRating(@PathVariable("ratingId") String ratingId , Rating rating);
	
	//Delete
	@DeleteMapping("/ratings/{ratingId}")
	public void deleteRating(@PathVariable("ratingId") String ratingId);
}
