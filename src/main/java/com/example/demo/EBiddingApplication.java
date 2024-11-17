package com.example.demo;

import com.example.demo.entity.Item;
import com.example.demo.entity.User;
import com.example.demo.service.ItemService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EBiddingApplication {

	@Autowired
	ItemService service;
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(EBiddingApplication.class, args);
	}
}
