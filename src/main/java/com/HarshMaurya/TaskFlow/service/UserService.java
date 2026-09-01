package com.HarshMaurya.TaskFlow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.HarshMaurya.TaskFlow.entity.User;
import com.HarshMaurya.TaskFlow.exception.ResourceNotFoundException;
import com.HarshMaurya.TaskFlow.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor injection — Spring automatically supplies UserRepository here
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id); // reuse the method above
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRole(updatedUser.getRole());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}