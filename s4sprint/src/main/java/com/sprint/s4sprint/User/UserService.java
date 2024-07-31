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
        userToUpdate.setEmail(updatedUser.getEmail());

        return userRepository.save(userToUpdate);
    }

    public void deleteUser(long index) {
        userRepository.delete(getUser(index));
    }

    public List<User> findUsersByUserNameAndEmail(String userName, String email) {
        return userRepository.findUsersByUserNameAndEmail(userName, email);
    }
}
