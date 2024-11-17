package com.example.demo.service;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /*
    @Override
    public String setItem(Item item) {
        Item savedItem = itemRepository.save(item);
        return "Item saved with ID: " + savedItem.getId();
    }
    */
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }


}
