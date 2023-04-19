package com.example.gpaie.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gpaie.Model.AvantageModel;
import com.example.gpaie.Service.AvantageService;

@RestController
@RequestMapping("/v1")
public class AvantageController {
    @Autowired
    private AvantageService avantageService;

    @GetMapping({ "/avantages" })
	public List<AvantageModel> findAll() {
		return avantageService.findAll();
	}
    @GetMapping("/avantages/fonction/{id}")
    public List<AvantageModel> getByFonction(@PathVariable("id") Long id) {
        return avantageService.findAllByFonction(id);
    }
    @GetMapping("/avantages/{id}")
    public ResponseEntity<AvantageModel> getById(@PathVariable("id") Long id) {
        Optional<AvantageModel> existingItemOptional = avantageService.findOne(id);
        return existingItemOptional.map(sms -> new ResponseEntity<>(sms, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/avantages")
    public ResponseEntity<AvantageModel> createDepartement(@RequestBody AvantageModel avantageModel){

         try {
            AvantageModel newdepartementModel = avantageService.save(avantageModel);
            return new ResponseEntity<>(newdepartementModel, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        } 
    } 
    @DeleteMapping("/avantages/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        avantageService.delete(id);
    return   new ResponseEntity<>(null, HttpStatus.OK);
       
    }
}
