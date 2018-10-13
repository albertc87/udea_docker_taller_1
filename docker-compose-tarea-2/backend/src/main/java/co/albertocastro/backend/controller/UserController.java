package co.albertocastro.backend.controller;

import co.albertocastro.backend.exception.ResourceNotFoundException;
import co.albertocastro.backend.model.User;
import co.albertocastro.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository repository) {
        this.userRepository = repository;
    }

    /**
     * GET all users
     * @return users list
     */
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * Insert a new user into the DB
     * @param user
     * @return
     */
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * Get a single user by id
     * @param userId
     * @return User
     */
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    /**
     * Update user data
     * @param userId
     * @param userDetails
     * @return
     */
    @PutMapping("/users/{id}")
    public User updateNote(@PathVariable(value = "id") Long userId,
                           @Valid @RequestBody User userDetails) {

        User user = getUserById(userId);

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());

        return userRepository.save(user);
    }

    /**
     * Delete an user
     * @param userId
     * @return
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long userId) {
        userRepository.delete(getUserById(userId));
        return ResponseEntity.ok().build();
    }

}

