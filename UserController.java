package LostFoundBackend.controller;

import java.util.Map;

import LostFoundBackend.model.User;
import LostFoundBackend.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.getAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    @PostMapping("/login")
public ResponseEntity<String> login(@RequestBody Map<String, String> loginData) {

    String email = loginData.get("email");
    String password = loginData.get("password");

    boolean valid = userRepository.validateLogin(email, password);

    if (valid) {
        return ResponseEntity.ok("Login successful");
    } else {
        return ResponseEntity.status(401).body("Invalid email or password");
    }
 }
}