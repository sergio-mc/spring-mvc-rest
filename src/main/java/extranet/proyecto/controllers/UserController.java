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

        if(user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else if(!user.isEmpty()){
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User newUser){
        if(newUser.getFirstname() != null && newUser.getLastname() != null && newUser.getEmail() != null && newUser.getPhone() != null && newUser.getActive() != null){
            repository.save(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,
                           @RequestBody User userDetails) {
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(userDetails.getFirstname() != null){
            user.get().setFirstname(userDetails.getFirstname());
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(userDetails.getLastname() != null){
            user.get().setLastname(userDetails.getLastname());
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(userDetails.getEmail() != null){
            user.get().setEmail(userDetails.getEmail());
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(userDetails.getPhone() != null){
            user.get().setPhone(userDetails.getPhone());
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(userDetails.getActive() != null){
            user.get().setActive(userDetails.getActive());
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // El motivo por el que no uno todos los campos en un if con "&& and" es debido a que creo que se pueden crear HttpStatus Custom
        }

        final User updatedUser = repository.save(user.get());
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable(value = "id") Long id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            try {
                repository.deleteById(id);
                return new ResponseEntity(user.get(),HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
