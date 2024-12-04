package com.example.demo.service;

import com.example.demo.entity.BidHistory;
import com.example.demo.entity.Item;
import com.example.demo.repository.BidHistoryRepository;
import com.example.demo.service.ItemService;
import com.example.demo.entity.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;



@Service
public class UserService {

    private final UserRepository userRepository;
    private final ItemService itemService;

    private Logger logger = LoggerFactory.getLogger(ItemService.class);



    public UserService(UserRepository userRepository, ItemService itemService) {
        this.userRepository = userRepository;
        this.itemService = itemService;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();

    }
    public User getUserById(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    public boolean updateUser(Long id, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            // Update fields
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setName(updatedUser.getName());
            existingUser.setPhone_number(updatedUser.getPhone_number());
            existingUser.setStatus(updatedUser.getStatus());
            existingUser.setSuspend_number(updatedUser.getSuspend_number());
            existingUser.setRating(updatedUser.getRating());

            userRepository.save(existingUser);
            return true;
        }
        return false;
    }

    public boolean addNewFavorite(Long id, String newFavorite) {

        Optional<User> existingUserOpt = userRepository.findById(id);

        if (existingUserOpt.isEmpty()) {
            return false;
        }

        User existingUser = existingUserOpt.get();

        // Initialize favorite array if null
        if (existingUser.getFavorite() == null) {
            existingUser.setFavorite(new String[]{newFavorite}); // Initialize with the new value
        } else {
            // Create a new array with extra slot
            String[] existingFavorite = existingUser.getFavorite();
            String[] updateFavorite = new String[existingFavorite.length + 1];

            // Copy existing favorite to the new array
            System.arraycopy(existingFavorite, 0, updateFavorite, 0, existingFavorite.length);

            // Add the nre favorite
            updateFavorite[existingFavorite.length] = newFavorite;

            // Update the user's favorite
            existingUser.setFavorite(updateFavorite);

        }
        //Save updated favorite back to the database
        userRepository.save(existingUser);

        return true;
    }


    public boolean addRecentView(Long id, Long recentViews) {

        Optional<User> existingUserOpt = userRepository.findById(id);

        if (existingUserOpt.isEmpty()) {
            return false;
        }

        User existingUser = existingUserOpt.get();

        // Initialize recentViews array if null
        if (existingUser.getRecent_view() == null) {
            existingUser.setRecent_view(new Long[]{recentViews}); // Initialize with the new value
        } else {
            // Create a new array with extra slot
            Long[] existingRecentViews = existingUser.getRecent_view();
            Long[] updateRecentViews = new Long[existingRecentViews.length + 1];

            // Copy existing recentViews to the new array
            System.arraycopy(existingRecentViews, 0, updateRecentViews, 0, existingRecentViews.length);

            // Add the nre recentViews
            updateRecentViews[existingRecentViews.length] = recentViews;

            // Update the user's recentViews
            existingUser.setRecent_view(updateRecentViews);

        }
        //Save updated recentViews back to the database
        userRepository.save(existingUser);

        return true;
    }


    public boolean addPurchase(Long id, Long newPurchase) {

        // Fetch the user by ID
        Optional<User> existingUserOpt = userRepository.findById(id);

        // Check if the user exists
        if (existingUserOpt.isEmpty()) {
            return false;
        }

        // Get the existing user object
        User existingUser = existingUserOpt.get();

        // Handle the Purchase array
        Long[] existingPurchases = existingUser.getPurchase();
        if (existingPurchases == null) {
            // If null, initialize with a single-element array containing the new purchase
            existingUser.setPurchase(new Long[]{newPurchase});
        } else {
            // Resize and update the array with the new purchase
            Long[] updatedPurchases = new Long[existingPurchases.length + 1];
            System.arraycopy(existingPurchases, 0, updatedPurchases, 0, existingPurchases.length);
            updatedPurchases[existingPurchases.length] = newPurchase;

            // Update the user's Purchase array
            existingUser.setPurchase(updatedPurchases);
        }

        // Save the updated user back to the repository
        userRepository.save(existingUser);

        return true;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean addItemToBlogList(Long userId, String itemName) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return false; // User not found
        }

        Item item = itemService.getItemByName(itemName);


        User user = optionalUser.get();
        List<BidHistory> historyList = new ArrayList<>();

        // Add entries to the list
        BidHistory bidHistory = new BidHistory();
        bidHistory.setUserId(user.getId());
        bidHistory.setItemName(item.getName());
        bidHistory.setPrice(item.getPrice());
        historyList.add(bidHistory);


        user.setBlogList(historyList);


        userRepository.save(user);

        return true;
    }

    public User findUsername(String username){
        List<User> users = getAllUsers();

        for (User user : users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }

        return null;
    }



}
