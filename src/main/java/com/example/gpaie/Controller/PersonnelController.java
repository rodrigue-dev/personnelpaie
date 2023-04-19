package com.example.gpaie.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gpaie.Model.PersonnelModel;
import com.example.gpaie.Service.PersonnelService;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class PersonnelController {
    @Autowired
    private PersonnelService personnelService;
    @GetMapping({ "/personnels" })
	public List<PersonnelModel> findAll() {
    
		return personnelService.findAll();
	}
    @GetMapping("/personnels/{id}")
    public ResponseEntity<PersonnelModel> getById(@PathVariable("id") Long id) {
        Optional<PersonnelModel> existingItemOptional = personnelService.findOne(id);
        return existingItemOptional.map(sms -> new ResponseEntity<>(sms, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    } 
    @PostMapping("/personnels")
    public ResponseEntity<PersonnelModel> createUser(@RequestBody PersonnelModel user){
    
         try {
            PersonnelModel newUser = personnelService.save(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        } 
    }
    @DeleteMapping("/personnels/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        personnelService.delete(id);
    return   new ResponseEntity<>(null, HttpStatus.OK);
       
    }
}
