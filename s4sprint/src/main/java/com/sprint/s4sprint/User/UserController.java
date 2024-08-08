package com.sprint.s4sprint.User;

import com.sprint.s4sprint.Forms.LoginForm;
import com.sprint.s4sprint.Forms.RegisterForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("search_user")
    public List<User> searchUser(@RequestParam(value = "userName", required = false) String userName) {
        return userService.findByUserName(userName);
    }

    @GetMapping("users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("users/login")
    public ResponseEntity loginUser(@RequestBody @Valid LoginForm formData, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.internalServerError().body("Input was invalid.");

        return userService.validateLogin(formData);
    }

    @GetMapping("user/{index}")
    public User getUser(@PathVariable Integer index) {
        return userService.getUser(index);
    }

    @PostMapping("users/signUp")
    public ResponseEntity registerUser(@RequestBody @Valid RegisterForm formData, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.internalServerError().body("Input was invalid.");

        return userService.registerUser(formData);
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