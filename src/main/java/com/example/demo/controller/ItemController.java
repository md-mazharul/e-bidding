package com.example.demo.controller;

import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/item")
    public ResponseEntity<String> setItem(@RequestBody Item item) {
        if (!ObjectUtils.isEmpty(itemService.createItem(item))) {
            return new ResponseEntity<>("item created !!", HttpStatus.OK);
        }
        return new ResponseEntity<>("item NOT created !!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 No Content if the list is empty
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


}
