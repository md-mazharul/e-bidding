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
    @GetMapping("/items/{itemName}")
    public ResponseEntity<?> getItemByName(@PathVariable String itemName) {
        // Call the service to get the item
        Item item = itemService.getItemByName(itemName);

        // Check if the item is found
        if (item != null) {
            return ResponseEntity.ok(item); // HTTP 200 with the item
        } else {
            return ResponseEntity.status(404)
                    .body("Item not found with name: " + itemName);
        }
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
        boolean isUpdated = itemService.updateItem(id, updatedItem);
        if (isUpdated) {
            return new ResponseEntity<>("Item updated successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Item not found or could not be updated!", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/rating")
    public ResponseEntity<String> addRating(@PathVariable Long id, @RequestBody Double newrating) {
        boolean addrating = itemService.addnewrating(id, newrating);
        if(addrating){
            return new ResponseEntity<>("Rating add successfully!", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Rating is not add", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}/bidding")
    public ResponseEntity<String> addBidding(@PathVariable Long id, @RequestBody Double newbidding) {
        boolean addrating = itemService.addnewbidding(id, newbidding);
        if(addrating){
            return new ResponseEntity<>("Bidding add successfully!", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Bidding is not add", HttpStatus.NOT_FOUND);
        }
    }




    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> delteItem(@PathVariable Long id){
        boolean isDeleted = itemService.deleteItemById(id);
        if(isDeleted){
            return new ResponseEntity<>("Item deleted successfully!", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Item not found!", HttpStatus.NOT_FOUND);
        }
    }



}
