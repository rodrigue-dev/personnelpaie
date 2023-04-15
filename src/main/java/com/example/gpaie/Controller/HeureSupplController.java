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

import com.example.gpaie.Model.HeureSupplModel;
import com.example.gpaie.Service.heureSupplService;

@RestController
@RequestMapping("/v1")
public class HeureSupplController {
    @Autowired
    private heureSupplService heureSupplService;

    @GetMapping({ "/heuresupplementaires" })
	public List<HeureSupplModel> findAll() {
		return heureSupplService.findAll();
	}
    @GetMapping("/heuresupplementaires/user/{id}")
    public List<HeureSupplModel> getByFonction(@PathVariable("id") Long id) {
        return heureSupplService.findAll();
    }
    @GetMapping("/heuresupplementaires/{id}")
    public ResponseEntity<HeureSupplModel> getById(@PathVariable("id") Long id) {
        Optional<HeureSupplModel> existingItemOptional = heureSupplService.findOne(id);
        return existingItemOptional.map(sms -> new ResponseEntity<>(sms, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/heuresupplementaires")
    public ResponseEntity<HeureSupplModel> createDepartement(@RequestBody HeureSupplModel avantageModel){

         try {
            HeureSupplModel newdepartementModel = heureSupplService.save(avantageModel);
            return new ResponseEntity<>(newdepartementModel, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        } 
    } 
}
