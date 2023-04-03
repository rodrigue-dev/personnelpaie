package com.example.gpaie.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gpaie.Entity.FichePresence;
import com.example.gpaie.Model.FichePresenceModel;
import com.example.gpaie.Repository.FichePresenceRepository;
import com.example.gpaie.Service.FichePresenceService;
@Service
public class FichePresenceServiceImpl implements FichePresenceService{
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private FichePresenceRepository fichePresenceRepository;
    @Override
    public FichePresenceModel save(FichePresenceModel fichePresenceModel) {
        FichePresence fichePresence;
        if(fichePresenceModel.getId()== null){
            fichePresence=new FichePresence();  
        }else{
            fichePresence =fichePresenceRepository.findById(fichePresenceModel.getId()).get();
        }
        fichePresence.setHeureDebut(fichePresenceModel.getHeureDebut());
        fichePresence.setHeureFin(fichePresenceModel.getHeureFin());
        fichePresenceRepository.saveAndFlush(fichePresence);
        return fichePresenceModel;
    }
    @Override
    public Optional<FichePresenceModel> partialUpdate(FichePresenceModel zoneDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'partialUpdate'");
    }
    @Override
    public List<FichePresenceModel> findAll() {
        return fichePresenceRepository.findAll().stream().map(this::fichePresenceToFichePresenceModel).collect(Collectors.toList());
    }
    @Override
    public Optional<FichePresenceModel> findOne(Long id) {
       return fichePresenceRepository.findById(id).map(this::fichePresenceToFichePresenceModel);
    }
    @Override
    public void delete(Long id) {
        fichePresenceRepository.deleteById(id);
    }
    public FichePresenceModel fichePresenceToFichePresenceModel(FichePresence fichePresence) {
        return new FichePresenceModel(fichePresence);
    }
}
