package com.example.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserService.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
