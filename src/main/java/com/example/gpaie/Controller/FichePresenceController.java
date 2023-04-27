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

import com.example.gpaie.Model.FichePresenceModel;
import com.example.gpaie.Service.FichePresenceService;

@RestController
@RequestMapping("/v1")
public class FichePresenceController {
    @Autowired
    private FichePresenceService fichePresenceService;

    @GetMapping({ "/fichepresences" })
	public List<FichePresenceModel> findAll() {
		return fichePresenceService.findAll();
	}
    @GetMapping({ "/fichepresences/user/{id}" })
	public List<FichePresenceModel> findAllByUser(@PathVariable("id") Long id) {
		return fichePresenceService.findByEmploye(id);
	}
    @GetMapping({ "/fichepresences/date/{date}" })
	public List<FichePresenceModel> findAllByDate(@PathVariable("date") String date) {
		return fichePresenceService.findAllByDate(date);
	}
    @GetMapping("/fichepresences/{id}")
    public ResponseEntity<FichePresenceModel> getById(@PathVariable("id") Long id) {
        Optional<FichePresenceModel> existingItemOptional = fichePresenceService.findOne(id);
        return existingItemOptional.map(sms -> new ResponseEntity<>(sms, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
   

    @PostMapping("/fichepresences")
    public ResponseEntity<FichePresenceModel> createFichePresence(@RequestBody FichePresenceModel FichePresenceModel){

         try {
            FichePresenceModel newFichePresenceModel = fichePresenceService.save(FichePresenceModel);
            return new ResponseEntity<>(newFichePresenceModel, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        } 
    }
    @DeleteMapping("/fichepresences/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        fichePresenceService.delete(id);
    return   new ResponseEntity<>(null, HttpStatus.OK);
       
    }
}
