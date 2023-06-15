package com.example.gpaie.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import com.example.gpaie.Model.EmailModel;
import com.example.gpaie.Model.UserModel;
import com.example.gpaie.Service.UserServiceInterface;
import com.example.gpaie.Utils.HeaderUtil;

@RestController
@RequestMapping("/v1")
public class UserController {
    private static final String ENTITY_NAME = "USERS";
    @Autowired
    private UserServiceInterface userServiceInterface;
    @Autowired
	private AuthenticationManager authenticationManager;
    @GetMapping({ "/users" })
	public List<UserModel> findAll() {
        /* return userServiceInterface.findAll().stream().map(e->{

        }); */
		return userServiceInterface.findAll();
	}
    @GetMapping("/users/{id}")
    public ResponseEntity<UserModel> getById(@PathVariable("id") Long id) {
        Optional<UserModel> existingItemOptional = userServiceInterface.findOne(id);
        return existingItemOptional.map(sms -> new ResponseEntity<>(sms, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/users/byemail/{email}")
    public ResponseEntity<UserModel> getByEmail(@PathVariable("email") String email) {
        Optional<UserModel> existingItemOptional = userServiceInterface.findByEmail(email);
        return existingItemOptional.map(sms -> new ResponseEntity<>(sms, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/users/changepassword/{id}/{oldpass}/{newpass}")
    public ResponseEntity<?> changePassword(@PathVariable("id") Long id,@PathVariable("oldpass") String oldpass,@PathVariable("newpass") String newpass) {
        userServiceInterface.changePassword(oldpass,newpass,id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    @PostMapping("/users")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user){
    
        // try {
            UserModel newUser = userServiceInterface.save(user);
            System.out.println("impossible");
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
      //  } catch (Exception e) {
          //  System.out.println("impossible------");
           // System.out.println(e.getCause());
            //log.debug("Created Information for User: {}", e.getMessage());
            //return new ResponseEntity<>(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, e.toString()), HttpStatus.BAD_REQUEST);
       // } 
    }
    @PostMapping("/users/sendmail")
    public ResponseEntity<?> sendMail(@RequestBody EmailModel user){
    
         try {
            userServiceInterface.sendMail(user);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userServiceInterface.delete(id);
    return   new ResponseEntity<>(null, HttpStatus.OK);
       
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        UserModel fileDB = userServiceInterface.findOne(id).get();

        System.out.println(fileDB.getImageFile());
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFirstname() + "\"")
          .body(fileDB.getImage());
    }
}
