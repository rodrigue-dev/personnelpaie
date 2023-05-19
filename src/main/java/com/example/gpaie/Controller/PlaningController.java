package com.example.gpaie.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gpaie.Model.Makeplaning;
import com.example.gpaie.Model.PlaningModel;
import com.example.gpaie.Model.PlaningUserModel;
import com.example.gpaie.Model.TypePlaningModel;
import com.example.gpaie.Service.PlaningService;
import com.example.gpaie.Service.TypeplaningService;
@RestController
@RequestMapping("/v1")
public class PlaningController {
    private static final String ENTITY_NAME = "planing";
    @Autowired
    private PlaningService planingService;
    @Autowired
    private TypeplaningService typeplaningService;

    @GetMapping({ "/planings" })
	public List<PlaningModel> findAll() {
		return planingService.findAll();
	}
    @GetMapping("/planings/byuser/{user_id}/{date_jour}")
	public List<Makeplaning> getModelByUser(@PathVariable("user_id") Long user_id,@PathVariable("date_jour") String dateString) {
        
		return planingService.getPlaningByUser(user_id,dateString);
	}
    @GetMapping("/planings/models/{date_jour}")
	public List<PlaningUserModel> getModels(@PathVariable("date_jour") String dateString) {
        
		return planingService.getPlaningWeek(dateString);
	}
    @GetMapping("/planings/headers/{date_jour}")
	public List<LocalDate> getPlaningHeaderWeek(@PathVariable("date_jour") String dateString) {
    
		return planingService.getPlaningHeaderWeek(dateString);
	}
    @GetMapping("/planings/headers/month/{date_jour}")
	public List<LocalDate> getPlaningHeaderMonth(@PathVariable("date_jour") String dateString) {
    
		return planingService.getPlaningHeaderMonth(dateString);
	}
    @GetMapping("/planings/models/month/{date_jour}")
	public List<PlaningUserModel> getModelMonths(@PathVariable("date_jour") String dateString) {
        
		return planingService.getPlaningMonth(dateString);
	}
    @GetMapping("/planings/{id}")
    public ResponseEntity<PlaningModel> getById(@PathVariable("id") Long id) {
        Optional<PlaningModel> existingItemOptional = planingService.findOne(id);
        return existingItemOptional.map(sms -> new ResponseEntity<>(sms, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/planings/{id}")
    public ResponseEntity<?> deletePlaning(@PathVariable("id") Long id) {
        planingService.delete(id);
    return   new ResponseEntity<>(null, HttpStatus.OK);
       
    }

    @PostMapping("/planings")
    public ResponseEntity<?> createPlaning(@RequestBody PlaningModel planingModel){
    
        try {
            PlaningModel newplaningModel = planingService.save(planingModel);
            return new ResponseEntity<>(newplaningModel, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //log.debug("Created Information for User: {}", e.getMessage());
            HttpHeaders responseHeaders = new HttpHeaders();
            //responseHeaders.setLocation(location);
            responseHeaders.set("MyResponseHeader", "MyValue");
            return new ResponseEntity<>(e,responseHeaders,HttpStatus.INTERNAL_SERVER_ERROR);
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
    @DeleteMapping("/typeplanings/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        typeplaningService.delete(id);
    return   new ResponseEntity<>(null, HttpStatus.OK);
       
    }
}
