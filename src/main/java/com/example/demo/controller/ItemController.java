package com.example.demo.controller;

import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items")
    public ResponseEntity<?> createItem(@RequestBody Item item) {
        Item createdItem  = itemService.createItem(item);
        System.out.println(createdItem.toString());
        if (!ObjectUtils.isEmpty(createdItem)) {
            return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("item NOT created !!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /*
    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 No Content if the list is empty
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

     */

    @GetMapping("/items")
    public @ResponseBody List<Item> getAllItems() {
        return itemService.getAllItem();


    }
}
