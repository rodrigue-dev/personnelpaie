package com.example.gpaie.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gpaie.Model.FontionModel;
import com.example.gpaie.Model.fonctionItemRequest;
import com.example.gpaie.Service.FonctionService;

@RestController
@RequestMapping("/v1")
public class FonctionController {
    @Autowired
    private FonctionService fonctionService;
    @GetMapping({ "/fonctions" })
	public List<FontionModel> findAll() {
		return fonctionService.findAll();
	}
    @GetMapping({ "/fonctions/departement/{id}" })
	public List<FontionModel> findAllByDepartement(@PathVariable("id") Long id) {
		return fonctionService.findAllByDepartement(id);
	}
    @GetMapping("/fonctions/{id}")
    public ResponseEntity<FontionModel> getById(@PathVariable("id") Long id) {
        Optional<FontionModel> existingItemOptional = fonctionService.findOne(id);
        return existingItemOptional.map(sms -> new ResponseEntity<>(sms, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/fonctions")
    public ResponseEntity<FontionModel> create(@RequestBody FontionModel fontionModel){
        
         try {
            FontionModel newfontionModel= fonctionService.save(fontionModel);
            return new ResponseEntity<>(newfontionModel, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        } 
    }
    @PostMapping("/fonctions/addavantage")
    public ResponseEntity<FontionModel> addAvantage(@RequestBody fonctionItemRequest fontionModel){
        
         try {
            FontionModel newfontionModel= fonctionService.addAvantage(fontionModel);
            return new ResponseEntity<>(newfontionModel, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        } 
    }
    @PostMapping("/fonctions/adddepartement")
    public ResponseEntity<FontionModel> addDepartement(@RequestBody fonctionItemRequest fontionModel){
        
         try {
            FontionModel newfontionModel= fonctionService.addDepartement(fontionModel);
            return new ResponseEntity<>(newfontionModel, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        } 
    }
    @PostMapping("/fonctions/removedepartement")
    public ResponseEntity<FontionModel> removeDepartement(@RequestBody fonctionItemRequest fontionModel){
        
         try {
            FontionModel newfontionModel= fonctionService.removeDepartement(fontionModel);
            return new ResponseEntity<>(newfontionModel, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        } 
    }
    @PostMapping("/fonctions/removeavantage")
    public ResponseEntity<FontionModel> removeAvantage(@RequestBody fonctionItemRequest fontionModel){
        
         try {
            FontionModel newfontionModel= fonctionService.removeAvantage(fontionModel);
            return new ResponseEntity<>(newfontionModel, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        } 
    }
}
