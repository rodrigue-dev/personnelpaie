package com.example.gpaie.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gpaie.Entity.Paiement;
import com.example.gpaie.Entity.User;
import com.example.gpaie.Model.PaiementModel;
import com.example.gpaie.Repository.PaiementRepository;
import com.example.gpaie.Repository.UserRepository;
import com.example.gpaie.Service.PaiementService;

@Service
public class PaiementServiceImpl implements PaiementService{
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private PaiementRepository paiementRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public PaiementModel save(PaiementModel paiementModel) {
        Paiement paiement;
        if(paiementModel.getId()== null){
            paiement=new Paiement();  
        }else{
            paiement =paiementRepository.findById(paiementModel.getId()).get();
        }
        var base=17.5;
        var nombreHeure_travaille=paiementModel.getTotal_jour()*8;
        var salaire_base=base*nombreHeure_travaille;
        paiementRepository.saveAndFlush(paiement);
        return paiementModel;
    }
    @Override
    public Optional<PaiementModel> partialUpdate(PaiementModel paiementModel) {
        return Optional.of(paiementModel);
    }
    @Override
    public List<PaiementModel> findAll() {
     return paiementRepository.findAll().stream().map(this::paiementToPaiementModel).collect(Collectors.toList());
    }
    @Override
    public Optional<PaiementModel> findOne(Long id) {
      return paiementRepository.findById(id).map(this::paiementToPaiementModel);
    }
    @Override
    public void delete(Long id) {
       paiementRepository.deleteById(id);;
    }
    public PaiementModel paiementToPaiementModel(Paiement paiement) {
        return new PaiementModel(paiement);
    }
    public void saveInfoPaiement(long user_id,int month,int year){
         User user=userRepository.findById(user_id).get();
    }
}
