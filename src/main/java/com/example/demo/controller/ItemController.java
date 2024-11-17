package com.example.demo.controller;

import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
