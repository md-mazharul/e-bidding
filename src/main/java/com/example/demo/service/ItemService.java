package com.example.demo.service;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

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


    public boolean updateUser(Long id, Double newRating){
        Optional<Item> existingUserOpt = itemRepository.findById(id);

        if (existingUserOpt.isEmpty()) {
            return false;
        }
        Item existingUser = existingUserOpt.get();

        // Initialize ratings array if null
        if (existingUser.getRating() == null) {
            existingUser.setRating(new Double[]{newRating}); // Initialize with the new value
        } else {
            // Create a new array with an extra slot
            Double[] existingRatings = existingUser.getRating();
            Double[] updatedRatings = new Double[existingRatings.length + 1];

            // Copy existing ratings to the new array
            System.arraycopy(existingRatings, 0, updatedRatings, 0, existingRatings.length);

            // Add the new rating
            updatedRatings[existingRatings.length] = newRating;

            // Update the user's ratings
            existingUser.setRating(updatedRatings);
        }

        // Save updated user back to the database
        itemRepository.save(existingUser);

        return true;
    }

    public boolean deleteItemById(Long id){
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;

    }




}
