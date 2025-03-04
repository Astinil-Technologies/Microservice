package com.example.hotelService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotelService.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,String >{

}
