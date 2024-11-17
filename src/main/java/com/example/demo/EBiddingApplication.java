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
public class EBiddingApplication implements CommandLineRunner {

	@Autowired
	ItemService service;
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(EBiddingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Item item = new Item(
				1L,
				"Rahim",
				"Notebook",
				"Selling",
				"It is a white notebook. It has 120 sheets. Dimension is 10.5 X 8 in",
				2L,
				new Double[]{4.1, 3.5, 3.0, 4.0},
				new Double[]{2.10,2.50}
		);
		service.createItem(item);
		User user = new User(
				3L,
				"abcd1020@gmail.com",
				"Karim",
				123456L,
				"Not_Suspend",
				0L,
				new Double[]{4.1, 3.5, 3.0, 4.0}


		);
		userService.createUser(user);

	}

}
