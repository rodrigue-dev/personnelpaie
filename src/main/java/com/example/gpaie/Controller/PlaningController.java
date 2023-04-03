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

import com.example.gpaie.Model.PlaningModel;
import com.example.gpaie.Model.TypePlaningModel;
import com.example.gpaie.Service.PlaningService;
import com.example.gpaie.Service.TypeplaningService;
@RestController
@RequestMapping("/v1")
public class PlaningController {
    @Autowired
    private PlaningService planingService;
    @Autowired
    private TypeplaningService typeplaningService;
    @GetMapping({ "/planings" })
	public List<PlaningModel> findAll() {
		return planingService.findAll();
	}
    @GetMapping("/planings/{id}")
    public ResponseEntity<PlaningModel> getById(@PathVariable("id") Long id) {
        Optional<PlaningModel> existingItemOptional = planingService.findOne(id);
        return existingItemOptional.map(sms -> new ResponseEntity<>(sms, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/planings")
    public ResponseEntity<PlaningModel> createUser(@RequestBody PlaningModel planingModel){
    
         try {
            PlaningModel newplaningModel = planingService.save(planingModel);
            return new ResponseEntity<>(newplaningModel, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //log.debug("Created Information for User: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        } 
    }
    @GetMapping({ "/typeplanings" })
	public List<TypePlaningModel> findAllType() {
		return typeplaningService.findAll();
	}
    @GetMapping("/typeplanings/{id}")
    public ResponseEntity<TypePlaningModel> getTypeById(@PathVariable("id") Long id) {
        Optional<TypePlaningModel> existingItemOptional = typeplaningService.findOne(id);
        return existingItemOptional.map(sms -> new ResponseEntity<>(sms, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/typeplanings")
    public ResponseEntity<TypePlaningModel> createUser(@RequestBody TypePlaningModel typePlaningModel){
    
         try {
            TypePlaningModel newtypePlaningModel = typeplaningService.save(typePlaningModel);
            return new ResponseEntity<>(newtypePlaningModel, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        } 
    }
}
