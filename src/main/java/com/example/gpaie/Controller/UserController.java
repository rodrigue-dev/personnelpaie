package com.example.gpaie.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.gpaie.Model.UserModel;
import com.example.gpaie.Service.UserServiceInterface;

@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    private UserServiceInterface userServiceInterface;

    @GetMapping({ "/users" })
	public List<UserModel> findAll() {
		return userServiceInterface.findAll();
	}
    @GetMapping("/users/{id}")
    public ResponseEntity<UserModel> getById(@PathVariable("id") Long id) {
        Optional<UserModel> existingItemOptional = userServiceInterface.findOne(id);
        return existingItemOptional.map(sms -> new ResponseEntity<>(sms, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user){
    
         try {
            UserModel newUser = userServiceInterface.save(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //log.debug("Created Information for User: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        } 
    }

    @PutMapping("/users")
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel user){
      
       try {
            UserModel newUser = userServiceInterface.save(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        } 
    }
}
