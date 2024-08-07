package com.sprint.s4sprint.User;

import com.sprint.s4sprint.Forms.LoginForm;
import com.sprint.s4sprint.Forms.RegisterForm;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @RequestMapping(path = "/users/login", method = RequestMethod.GET)
    public ResponseEntity loginUser(@ModelAttribute @Valid LoginForm formData, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.internalServerError().body("Input was invalid.");

        return userService.validateLogin(formData);
    }

    @GetMapping("user/{index}")
    public User getUser(@PathVariable Integer index) {
        return userService.getUser(index);
    }

    /*@PostMapping("user")
    public User createUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }*/

    /*@PostMapping(path = "/users/signUp",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser() {
        return ResponseEntity.ok(HttpStatus.OK);
    }*/

    @RequestMapping(path = "/users/signUp", method = RequestMethod.POST)
    public ResponseEntity registerUser(@ModelAttribute @Valid RegisterForm formData, BindingResult bindingResult) {
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