package com.example.gpaie.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import com.example.gpaie.Model.PaiementModel;
import com.example.gpaie.Service.FileService;
import com.example.gpaie.Service.PaiementService;

@RestController
@RequestMapping("/v1")
public class PaiementController {
    @Autowired
    private PaiementService paiementService;
    @Autowired
    private FileService fileService;
    @GetMapping({ "/paiements/month/{month}" })
	public List<PaiementModel> findAll(@PathVariable("month") int month ) {
		return paiementService.findAll();
	}
    @GetMapping({ "/paiements" })
	public List<PaiementModel> findAll() {
		return paiementService.findAll();
	}
    @GetMapping({ "/paiements/user/{id}" })
	public List<PaiementModel> findAllByUser(@PathVariable("id") Long id) {
		return paiementService.paiementByUser(id);
	}
    @GetMapping("/paiements/{id}")
    public ResponseEntity<PaiementModel> getById(@PathVariable("id") Long id) {
        Optional<PaiementModel> existingItemOptional = paiementService.findOne(id);
        return existingItemOptional.map(sms -> new ResponseEntity<>(sms, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/paiements/calcul/{month}/{year}/{type}/{user}")
    public List<PaiementModel>  calculVariableSalaire(@PathVariable("month") Integer month,
    @PathVariable("year") Integer year,@PathVariable("type") Integer type,@PathVariable("user")Long id_user) {
        List<PaiementModel> existingItemOptional=new ArrayList<>();
        switch(type){
            case 1:
            existingItemOptional=paiementService.calculSalaire(month, year,id_user);
            break;
            case 2:
            existingItemOptional=paiementService.calculHeureSupp(month, year,id_user);
            break;
            case 11:
            existingItemOptional=paiementService.sendMail(month, year,id_user);
            break;
            case 10:
            existingItemOptional=paiementService.generatePaie(month, year,id_user);
            break;
        }
        return existingItemOptional;
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
    @GetMapping("{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
        Resource file = fileService.download(filename);
        Path path = file.getFile()
                        .toPath();

        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                             .body(file);
    }
}
