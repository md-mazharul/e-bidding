package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public boolean addNewFavorite(Long id, String newFavorite){

        Optional<User>existingUserOpt = userRepository.findById(id);

        if (existingUserOpt.isEmpty()) {
            return false;
        }

        User existingUser = existingUserOpt.get();

        // Initialize favorite array if null
        if (existingUser.getFavorite() == null) {
            existingUser.setFavorite(new String[]{newFavorite}); // Initialize with the new value
        }
        else {
            // Create a new array with extra slot
            String[] existingFavorite = existingUser.getFavorite();
            String[] updateFavorite = new String[existingFavorite.length+1];

            // Copy existing favorite to the new array
            System.arraycopy(existingFavorite,0,updateFavorite,0,existingFavorite.length);

            // Add the nre favorite
            updateFavorite[existingFavorite.length] = newFavorite;

            // Update the user's favorite
            existingUser.setFavorite(updateFavorite);

        }
        //Save updated favorite back to the database
        userRepository.save(existingUser);

        return true;
    }


    public boolean addRecentView(Long id, Long recentViews){

        Optional<User>existingUserOpt = userRepository.findById(id);

        if (existingUserOpt.isEmpty()) {
            return false;
        }

        User existingUser = existingUserOpt.get();

        // Initialize recentViews array if null
        if (existingUser.getRecent_view() == null) {
            existingUser.setRecent_view(new Long[]{recentViews}); // Initialize with the new value
        }
        else {
            // Create a new array with extra slot
            Long[] existingRecentViews = existingUser.getRecent_view();
            Long[] updateRecentViews = new Long[existingRecentViews.length+1];

            // Copy existing recentViews to the new array
            System.arraycopy(existingRecentViews,0,updateRecentViews,0,existingRecentViews.length);

            // Add the nre recentViews
            updateRecentViews[existingRecentViews.length] = recentViews;

            // Update the user's recentViews
            existingUser.setRecent_view(updateRecentViews);

        }
        //Save updated recentViews back to the database
        userRepository.save(existingUser);

        return true;
    }
}
