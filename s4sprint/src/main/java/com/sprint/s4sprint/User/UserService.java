package com.sprint.s4sprint.User;

import com.sprint.s4sprint.Forms.LoginForm;
import com.sprint.s4sprint.Forms.RegisterForm;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
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

    private final PasswordEncoder bcrypt = new BCryptPasswordEncoder(10);

    public User getUser(long index) {
        Optional<User> result = userRepository.findById(index);

        return result.orElse(null);
    }

    public ResponseEntity registerUser(RegisterForm formData) {
        if (!formData.getPassword().equals(formData.getPasswordVerify()))
            return ResponseEntity.internalServerError()
                    .body("Passwords do not match.");

        Optional<User> existing;

        // Determine if username is already in use
        existing = userRepository.findUserByUserNameOrEmail(formData.getUsername(), formData.getEmail());
        if (existing.isPresent()) {
            if (formData.getUsername().equals(existing.get().getUserName()))
                return ResponseEntity.internalServerError()
                        .body("The username " + formData.getUsername() + " is already in use.");
            else
                return ResponseEntity.internalServerError()
                        .body("The email address " + formData.getEmail() + " is already in use.");
        }

        String hashedPW = bcrypt.encode(formData.getPassword());

        User newUser = new User();
        newUser.setUserName(formData.getUsername());
        newUser.setEmail(formData.getEmail());
        newUser.setLocation(formData.getLocation());
        newUser.setPhone(formData.getPhoneNum());
        newUser.setPosition(formData.getPosition());
        newUser.setPassword(hashedPW);

        userRepository.save(newUser);

        return ResponseEntity.ok(newUser);
    }

    public ResponseEntity<String> validateLogin(LoginForm formData) {
        Optional<User> user = userRepository.findUserByUserName(formData.getUsername());
        if (user.isEmpty())
            return ResponseEntity.internalServerError().body("User " + formData.getUsername() + " does not exist.");

        String hashedPW = user.get().getPassword();
        if (bcrypt.matches(formData.getPassword(), hashedPW)) {
            // Incomplete implementation of a JWT - expiry and revocation cannot be performed
            // Furthermore, the token uses a fixed signature and is not used to restrict access to API endpoints
            String jwt = Jwts.builder()
                    .issuer("PrecisionHire")
                    .subject(user.get().getUserName())
                    .claim("username", user.get().getUserName())
                    .claim("email", user.get().getEmail())
                    .issuedAt(new Date())
                    .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(
                            "MIICXAIBAAKBgQCJfDzujxBJp0nAgatTtCrx1uWYnT+p8j8xCHF0oe1AK80XvcHM" +
                                    "l1SxqCw7I+ckiDQ0xMtFuktLLPcP9QcGQ4PU4+I1G0i1JoVjiNnCi4WGyRbGT0S3" +
                                    "9U1rfY9Nls/BeUJsRjcQOzqQ8i6nUSMZRr7jyvqUfWXJYNcBcoGs9GkrbwIDAQAB" +
                                    "AoGAIYzw7aJ57g9l3xWFsrp/1F6FzvVoyNc19ohB751oYUWPGiETfxLa9zO7/36m" +
                                    "dHLMkqgHlu98Wk8Rx5Ia8bteGyIAB6aFaUlRuGJlZvCu2Ec9J6biM2ZHVw0J3i9I" +
                                    "1VDEe30hsj/ySRAfNw5SJr3vV7e/t+AIkJ/r5NnJH71WrXECQQDzoMGyzBobDYGR" +
                                    "MZV8lVFmk2ACwRzmCOr6hzKnW4eYfoM+XW6fu+8GAd1A3GerGsQyy4S0mf6WuRHb" +
                                    "kc6KY4AXAkEAkHeXkz2OoK960QDhpeNGhugBX8Xaqe5xbVwGv4NDWDJe2S/Oj4+V" +
                                    "clNAVKu2hU9bG2b66kqrS8PuomHk8AauaQJBAJRjTLdHY3sovcsepUstc1gD2Y2z" +
                                    "65McyWpwYGTY9fzmya95nwCBqjNBfW6dEDOCaQ/7L8gEp7FL5dmTtHZL5l8CQFiX" +
                                    "3m4/K75CnrCZN8fBTrjggic3cFcRkKGd881yM9RcX2oVNTk4tk872pGSFAZAmgB9" +
                                    "8JXJYK6/4xHVss23azECQCoXHIJMd4Oj5itIAQ/Sc4RKZZj1h+gPdAbQV+bYI2W8" +
                                    "oiA7BZi4oi68PGljJdEIekXA6PxJg38qJD6MXaZWdyQ=")))
                    .compact();

            return ResponseEntity.ok(jwt);
        } else return ResponseEntity.internalServerError().body("Incorrect password.");
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
