package co.albertocastro.backend.controller;

import co.albertocastro.backend.exception.ResourceNotFoundException;
import co.albertocastro.backend.model.User;
import co.albertocastro.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger logger =  LoggerFactory.getLogger(UserController.class);
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
        logger.info("Getting ALL users");
        return userRepository.findAll();
    }

    /**
     * Insert a new user into the DB
     * @param user
     * @return
     */
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {

        logger.info("Creating a new user: %s",user);
        return userRepository.save(user);
    }

    /**
     * Get a single user by id
     * @param userId
     * @return User
     */
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        logger.info("Getting user by id: %s",userId);
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
        logger.info("Updating user -> old user: %s new data:%  ",user, userDetails);
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
        logger.info("Deleting user by id: %s", userId);
        userRepository.delete(getUserById(userId));
        return ResponseEntity.ok().build();
    }

}

