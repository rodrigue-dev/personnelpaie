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

import com.example.gpaie.Model.PaiementModel;
import com.example.gpaie.Service.PaiementService;

@RestController
@RequestMapping("/v1")
public class PaiementController {
    @Autowired
    private PaiementService paiementService;
    @GetMapping({ "/paiements" })
	public List<PaiementModel> findAll() {
		return paiementService.findAll();
	}
    @GetMapping("/paiements/{id}")
    public ResponseEntity<PaiementModel> getById(@PathVariable("id") Long id) {
        Optional<PaiementModel> existingItemOptional = paiementService.findOne(id);
        return existingItemOptional.map(sms -> new ResponseEntity<>(sms, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/paiements")
    public ResponseEntity<PaiementModel> createPaiement(@RequestBody PaiementModel paiementModel){
    
         try {
            PaiementModel newpaiementModel = paiementService.save(paiementModel);
            return new ResponseEntity<>(newpaiementModel, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        } 
    }
}
