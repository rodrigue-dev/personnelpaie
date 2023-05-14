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

import com.example.gpaie.Model.AbsenceModel;
import com.example.gpaie.Service.AbsenceService;

@RestController
@RequestMapping("/v1")
public class AbsenceController {
    @Autowired
    private AbsenceService absenceservice;

    @GetMapping({ "/absences" })
	public List<AbsenceModel> findAll() {
		return absenceservice.findAll();
	}
    @GetMapping({ "/absences/my/{id}" })
	public List<AbsenceModel> findAllOwn(@PathVariable("id") Long id) {
		return absenceservice.findAllOwn(id);
	}
    @GetMapping("/absences/{id}")
    public ResponseEntity<AbsenceModel> getById(@PathVariable("id") Long id) {
        Optional<AbsenceModel> existingItemOptional = absenceservice.findOne(id);
        return existingItemOptional.map(sms -> new ResponseEntity<>(sms, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/absences")
    public ResponseEntity<AbsenceModel> createDepartement(@RequestBody AbsenceModel AbsenceModel){

         try {
            AbsenceModel newdepartementModel = absenceservice.save(AbsenceModel);
            return new ResponseEntity<>(newdepartementModel, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        } 
    } 
    @DeleteMapping("/absences/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        absenceservice.delete(id);
    return   new ResponseEntity<>(null, HttpStatus.OK);
       
    }
}
