package com.sprint.s4sprint.User;

import com.sprint.s4sprint.Forms.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder bcrypt = new BCryptPasswordEncoder(10);

    public User getUser(long index) {
        Optional<User> result = userRepository.findById(index);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    /*public User createUser(User newUser) {
        return userRepository.save(newUser);
    }*/

    public ResponseEntity registerUser(RegisterForm formData) {
        List<User> existing;

        // Determine if username is already in use
        existing = userRepository.findUsersByUserName(formData.getUsername());
        if (!existing.isEmpty())
            return ResponseEntity.internalServerError()
                    .body("The username " + formData.getUsername() + " is already in use.");

        // Determine if email is already in use
        existing = userRepository.findUsersByEmail(formData.getEmail());
        if (!existing.isEmpty())
            return ResponseEntity.internalServerError()
                    .body("The email address " + formData.getEmail() + " is already in use.");

        String hashedPW = bcrypt.encode(formData.getPassword());

        User newUser = new User();
        newUser.setUserName(formData.getUsername());
        newUser.setEmail(formData.getEmail());
        newUser.setLocation(formData.getLocation());
        newUser.setPhone(formData.getPhoneNum());
        newUser.setPosition(formData.getPosition());
        newUser.setPassword(hashedPW);

        userRepository.save(newUser);

        // TODO: implement JWT
        return ResponseEntity.ok(newUser);

        // TODO: implement exception handling
        // return ResponseEntity.internalServerError().body("An unknown error occurred.");
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

    public List<User> findUsersByUserNameAndEmail(String userName, String email) {
        return userRepository.findUsersByUserNameAndEmail(userName, email);
    }
}
