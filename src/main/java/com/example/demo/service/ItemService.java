package com.example.demo.service;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private Logger logger = LoggerFactory.getLogger(ItemService.class);

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item getItemByName(String itemName) {
        List<Item> items = getAllItem();

        for(Item item : items){
            if(item.getName().equals(itemName)){
                return item;
            }
        }
        return null;
    }

    public boolean updateItem(Long id, Item updatedItem) {
        Optional<Item> existingItemOpt = itemRepository.findById(id);
        if (existingItemOpt.isPresent()) {
            Item existingItem = existingItemOpt.get();
            existingItem.setOwner(updatedItem.getOwner());
            existingItem.setName(updatedItem.getName());
            existingItem.setStatus(updatedItem.getStatus());
            existingItem.setDescription(updatedItem.getDescription());
            existingItem.setPrice(updatedItem.getPrice());
            existingItem.setRating(updatedItem.getRating());
            existingItem.setBidding(updatedItem.getBidding());

            itemRepository.save(existingItem);
            return true;
        }
        return false;
    }


    public boolean addnewrating(Long id, Double newRating) {
        Optional<Item> existingItemOpt = itemRepository.findById(id);

        if (existingItemOpt.isEmpty()) {
            return false;
        }
        Item existingItem = existingItemOpt.get();

        // Initialize ratings array if null
        if (existingItem.getRating() == null) {
            existingItem.setRating(new Double[]{newRating}); // Initialize with the new value
        } else {
            // Create a new array with an extra slot
            Double[] existingRatings = existingItem.getRating();
            Double[] updatedRatings = new Double[existingRatings.length + 1];

            // Copy existing ratings to the new array
            System.arraycopy(existingRatings, 0, updatedRatings, 0, existingRatings.length);

            // Add the new rating
            updatedRatings[existingRatings.length] = newRating;

            // Update the item's ratings
            existingItem.setRating(updatedRatings);
        }

        // Save updated item back to the database
        itemRepository.save(existingItem);

        return true;
    }

    public boolean addnewbidding(Long id, Double newbidding) {
        Optional<Item> existingUserOpt = itemRepository.findById(id);


        if (existingUserOpt.isEmpty()) {
            return false;
        }
        Item existingItem = existingUserOpt.get();

        // Initialize biddng array if null
        if (existingItem.getBidding() == null) {
            existingItem.setBidding(new Double[]{newbidding}); // Initialize with the new value
        } else {
            // Create a new array with an extra slot
            Double[] existingBidding = existingItem.getBidding();
            Double[] updatedBidding = new Double[existingBidding.length + 1];

            // Copy existing bidding to the new array
            System.arraycopy(existingBidding, 0, updatedBidding, 0, existingBidding.length);

            // Add the new bidding
            updatedBidding[existingBidding.length] = newbidding;

            // Update the item's bidding
            existingItem.setBidding(updatedBidding);
        }
        // Save updated item back to the database
        itemRepository.save(existingItem);
        return true;
    }

    public boolean deleteItemById(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;

    }


}
