package com.sprint.s4sprint.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("search_user")
    public List<User> searchUser(@RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "email", required = false) String email) {
        return userService.findUsersByUserNameAndEmail(userName, email);
    }

    @GetMapping("users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("user/{index}")
    public User getUser(@PathVariable Integer index) {
        return userService.getUser(index);
    }

    @PostMapping("user")
    public User createUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @PutMapping("user/{index}")
    public User updateUser(@PathVariable Integer index, @RequestBody User updatedUser) {
        return userService.updateUser(index, updatedUser);
    }

    @DeleteMapping("user/{index}")
    public void deleteUser(@PathVariable Integer index) {
        userService.deleteUser(index);
    }
}