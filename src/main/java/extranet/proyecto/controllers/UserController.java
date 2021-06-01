package extranet.proyecto.controllers;


import extranet.proyecto.models.User;
import extranet.proyecto.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<User> getUsers() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") final Long id){
        Optional<User> user = repository.findById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping
    public User postUser(@RequestBody User newUser){
        return repository.save(newUser);
    }

    @PutMapping
    public User updateUser(@PathVariable(value = "id") Long id,
                           @RequestBody User userDetails) {
        Optional<User> user = repository.findById(id);

        user.get().setFirstname(userDetails.getFirstname());
        user.get().setLastname(userDetails.getLastname());
        user.get().setEmail(userDetails.getEmail());
        user.get().setPhone(userDetails.getPhone());
        user.get().setActive(userDetails.getActive());
        final User updatedUser = repository.save(user.get());
        return updatedUser;

    }

    @DeleteMapping("users/{id}")
    public Optional<User> deleteUser(@PathVariable(value = "id") Long userId){
        Optional<User> user = repository.findById(userId);
        repository.deleteById(userId);
        return user;
    }
}
