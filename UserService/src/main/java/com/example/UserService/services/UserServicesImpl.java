package com.example.UserService.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.UserService.entities.Hotel;
import com.example.UserService.entities.Rating;
import com.example.UserService.entities.User;
import com.example.UserService.exceptions.ResourceNotFoundException;
import com.example.UserService.external.services.HotelService;
import com.example.UserService.repositories.UserRepository;

@Service
public class UserServicesImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	//get Single User
	@Override
	public User getUser(String userId) {
		//get user from database with the help of user repository
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server " +userId));
		
		//fetch rating of the above user from rating service
		//http://localhost:8083/ratings/users/+user.getUserId()
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		
		//Converting Array into List of Ratings
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		                                                             
		//api call to hotel service to get the hotel inside rating
		List<Rating> ratingList = ratings.stream().map(rating -> {
		//http://localhost:8082/hotels/+rating.getHotelId
		//ResponseEntity<Hotel> forEntity =restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
		//Hotel hotel = forEntity.getBody();
		
		//Feign Client
		Hotel hotel = hotelService.getHotel(rating.getHotelId());
		rating.setHotel(hotel);
		return rating;
			
		//set the hotel to rating
		//rating.setHotel(hotel);
		
		//return the rating
		//return rating;
		}).collect(Collectors.toList());
		
		
		user.setRatings(ratingList);
		
		return user;
				
	}

}
