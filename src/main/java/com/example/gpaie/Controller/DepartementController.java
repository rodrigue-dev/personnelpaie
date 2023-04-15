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

import com.example.gpaie.Model.DepartementModel;
import com.example.gpaie.Service.DepartementService;

@RestController
@RequestMapping("/v1")
public class DepartementController {
    @Autowired
    private DepartementService departementService;

    @GetMapping({ "/departements" })
	public List<DepartementModel> findAll() {
		return departementService.findAll();
	}
    @GetMapping("/departements/{id}")
    public ResponseEntity<DepartementModel> getById(@PathVariable("id") Long id) {
        Optional<DepartementModel> existingItemOptional = departementService.findOne(id);
        return existingItemOptional.map(sms -> new ResponseEntity<>(sms, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/departements/fonction/{id}")
    public List<DepartementModel> getDepartementByFonction(@PathVariable("id") Long id) {
        return departementService.findAllByFonction(id);
    }

    @PostMapping("/departements")
    public ResponseEntity<DepartementModel> createDepartement(@RequestBody DepartementModel departementModel){

         try {
            DepartementModel newdepartementModel = departementService.save(departementModel);
            return new ResponseEntity<>(newdepartementModel, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        } 
    }
}
