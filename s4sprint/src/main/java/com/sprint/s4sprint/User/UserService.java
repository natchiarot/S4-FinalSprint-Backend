package com.sprint.s4sprint.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUser(long index) {
        Optional<User> result = userRepository.findById(index);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User updateUser(Integer index, User updatedUser) {
        User userToUpdate = getUser(index);

        userToUpdate.setUserName(updatedUser.getUserName());
        userToUpdate.setPosition(updatedUser.getPosition());
        userToUpdate.setEmail(updatedUser.getEmail());
        userToUpdate.setPhone(updatedUser.getPhone());
        userToUpdate.setLocation(updatedUser.getLocation());
        userToUpdate.setLastLogin(updatedUser.getLastLogin());
        userToUpdate.setPassword(updatedUser.getPassword());

        return userRepository.save(userToUpdate);
    }

    public void deleteUser(long index) {
        userRepository.delete(getUser(index));
    }

    public List<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
