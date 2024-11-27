package com.example.demo.controller;

import com.example.demo.entity.Item;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.User;

import java.util.List;

@RestController
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<String> setUser(@RequestBody User user) {
        if (!ObjectUtils.isEmpty(userService.createUser(user))) {
            return new ResponseEntity<>("user created !!", HttpStatus.OK);
        }
        return new ResponseEntity<>("user NOT created !!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 No Content if the list is empty
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/favorite")
    public ResponseEntity<String> addFavorite(@PathVariable Long id, @RequestBody String favorite) {
        boolean addNewFavorite = userService.addNewFavorite(id, favorite);
        if(addNewFavorite){
            return new ResponseEntity<>("Favorite add successfully!", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Favorite is not add", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        boolean isUpdated = userService.updateUser(id, updatedUser);
        if (isUpdated) {
            return new ResponseEntity<>("User updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found or could not be updated!", HttpStatus.NOT_FOUND);
        }
    }


}



