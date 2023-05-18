package com.example.gpaie.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gpaie.Entity.Avantage;
import com.example.gpaie.Entity.Departement;
import com.example.gpaie.Entity.Fonction;
import com.example.gpaie.Model.FontionModel;
import com.example.gpaie.Model.fonctionItemRequest;
import com.example.gpaie.Repository.AvantageRepository;
import com.example.gpaie.Repository.DepartementRepository;
import com.example.gpaie.Repository.FonctionRepository;
import com.example.gpaie.Service.FonctionService;
@Service
public class FonctionServiceImpl implements FonctionService{
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private FonctionRepository fonctionRepository;
    @Autowired
    private AvantageRepository avantageRepository;
    @Autowired
    private DepartementRepository departementRepository;
    @Override
    public FontionModel save(FontionModel fontionModel) {
        Fonction fonction;
        if(fontionModel.getId()== null){
            fonction=new Fonction();  
        }else{
            fonction =fonctionRepository.findById(fontionModel.getId()).get();
        }
        fonction.setHeureTravaille(fontionModel.getHeureTravaille());
        fonction.setSalaireSuppl(fontionModel.getSalaireSuppl());
        fonction.setTypeFonction(fontionModel.getTypeFonction());
        fonctionRepository.saveAndFlush(fonction);
        return fontionModel;
    }
    @Override
    public Optional<FontionModel> partialUpdate(FontionModel zoneDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'partialUpdate'");
    }
    @Override
    public List<FontionModel> findAll() {
     return fonctionRepository.findAll().stream().map(e->fonctionToFonctionModel(e)).collect(Collectors.toList());
    }
    @Override
    public Optional<FontionModel> findOne(Long id) {
        return fonctionRepository.findById(id).map(this::fonctionToFonctionModel);
    }
    @Override
    public void delete(Long id) {
        fonctionRepository.deleteById(id);
    }
    public FontionModel fonctionToFonctionModel(Fonction fonction) {
        return new FontionModel(fonction);
    }
    @Override
    public FontionModel addAvantage(fonctionItemRequest aRequest) {
     var fonctionOption=fonctionRepository.findById(aRequest.getFonction_id());
     if(fonctionOption.isPresent()){
       var fonction=fonctionOption.get();
           Set<Avantage>avantages=aRequest.getItems().stream().map(e->avantageRepository.findById(e).get()).collect(Collectors.toSet());
    fonction.getAvantages().addAll(avantages);
    fonctionRepository.saveAndFlush(fonction);
     return new FontionModel(fonction);
     }else{
        return new FontionModel();
     }
  
    }
    @Override
    public FontionModel addDepartement(fonctionItemRequest aRequest) {
     Fonction fonction=fonctionRepository.findById(aRequest.getFonction_id()).get();
     Set<Departement>departements=aRequest.getItems().stream().map(e->departementRepository.findById(e).get()).collect(Collectors.toSet());
    fonction.getDepartements().addAll(departements);
    fonctionRepository.saveAndFlush(fonction);
     return new FontionModel(fonction);
    }
    @Override
    public FontionModel removeAvantage(fonctionItemRequest aRequest) {
        Fonction fonction=fonctionRepository.findById(aRequest.getFonction_id()).get();
        Set<Avantage>avantages=aRequest.getItems().stream().map(e->avantageRepository.findById(e).get()).collect(Collectors.toSet());
       fonction.getAvantages().removeAll(avantages);
       fonctionRepository.saveAndFlush(fonction);
        return new FontionModel(fonction);
    }
    @Override
    public FontionModel removeDepartement(fonctionItemRequest aRequest) {
        Fonction fonction=fonctionRepository.findById(aRequest.getFonction_id()).get();
        Set<Departement>departements=aRequest.getItems().stream().map(e->departementRepository.findById(e).get()).collect(Collectors.toSet());
       fonction.getDepartements().removeAll(departements);
       fonctionRepository.saveAndFlush(fonction);
        return new FontionModel(fonction);
    }
    @Override
    public List<FontionModel> findAllByDepartement(long departement_id) {
        Departement departement=departementRepository.findById(departement_id).get();
        return fonctionRepository.findAll().stream().filter(e->e.getDepartements().contains(departement)).map(e->fonctionToFonctionModel(e)).collect(Collectors.toList());
    }
}
