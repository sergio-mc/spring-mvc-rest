package extranet.proyecto.controllers;


import extranet.proyecto.models.User;
import extranet.proyecto.repositories.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
//        new Integer('a');
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") final Long id){
        User user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new ResponseEntity(user,HttpStatus.OK);
    }

//    @GetMapping("{id}") PRUEBAS
//        public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") final Long id){
//        Optional<User> user = repository.findById(id);
//
//        if(user.isEmpty()){
//            return ResponseEntity.badRequest().body(user);
//        }else if(!user.isEmpty()){
//            return new ResponseEntity(user, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    @GetMapping("{id}") CORRECTA
//    public ResponseEntity<User> getUserById(@PathVariable("id") final Long id){
//        Optional<User> user = repository.findById(id);
//
//        if(user.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }else if(!user.isEmpty()){
//            return new ResponseEntity(user, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    @GetMapping("{id}")
//    public ResponseEntity<User> getUserById(@PathVariable("id") final Long id){
//        Optional<User> user = repository.findById(id);
//
//        if(user.isEmpty()){
//            return ResponseEntity.notFound()
//        }else if(!user.isEmpty()){
//            return new ResponseEntity(user, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }


    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User user){
        User newUser = null;
        try {
            newUser = repository.save(user);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(newUser,HttpStatus.OK);

    }
//    @PostMapping ESTE POST SERÍA EN CASO DE QUE EN EL FRONT NO SE VALIDASE EL OBJETO
//    public ResponseEntity<User> postUser(@RequestBody User newUser){
//        if(newUser.getFirstname() != null && newUser.getLastname() != null && newUser.getEmail() != null && newUser.getPhone() != null && newUser.getActive() != null){
//            repository.save(newUser);
//            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,
                           @RequestBody User userDetails) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(userDetails.getFirstname() != null){
            user.setFirstname(userDetails.getFirstname());
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(userDetails.getLastname() != null){
            user.setLastname(userDetails.getLastname());
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(userDetails.getEmail() != null){
            user.setEmail(userDetails.getEmail());
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(userDetails.getPhone() != null){
            user.setPhone(userDetails.getPhone());
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(userDetails.getActive() != null){
            user.setActive(userDetails.getActive());
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // El motivo por el que no uno todos los campos en un if con "&& and" es debido a que creo que se pueden crear HttpStatus Custom
        }

        User updatedUser = repository.save(user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable(value = "id") Long id){

        User user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        try {
            repository.deleteById(id);
            return new ResponseEntity(user,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Codigo, mensaje, data
    }
}
