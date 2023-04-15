package com.example.gpaie.Service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gpaie.Entity.Avantage;
import com.example.gpaie.Entity.Fonction;
import com.example.gpaie.Model.AvantageModel;
import com.example.gpaie.Repository.AvantageRepository;
import com.example.gpaie.Repository.FonctionRepository;
import com.example.gpaie.Service.AvantageService;
@Service
public class AvantageServiceImpl implements AvantageService{

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private AvantageRepository avantageRepository;
    @Autowired
    private FonctionRepository fonctionRepository;
    @Override
    public AvantageModel save(AvantageModel avantageModel) {
        Avantage avantage;
        if(avantageModel.getId()== null){
            avantage=new Avantage();  
        }else{
             avantage =avantageRepository.findById(avantageModel.getId()).get();
        }
        avantage.setTypeAvantage(avantageModel.getTypeAvantage());
        avantageRepository.saveAndFlush(avantage);
        return avantageModel;
    }

    @Override
    public Optional<AvantageModel> partialUpdate(AvantageModel zoneDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'partialUpdate'");
    }

    @Override
    public List<AvantageModel> findAll() {
      return avantageRepository.findAll().stream().filter(Objects::nonNull).map(this::avantageToAvantageModel).collect(Collectors.toList());
    }

    @Override
    public Optional<AvantageModel> findOne(Long id) {
       return avantageRepository.findById(id).map(e->avantageToAvantageModel(e));
    }

    @Override
    public void delete(Long id) {
        avantageRepository.deleteById(id);
    }
    public AvantageModel avantageToAvantageModel(Avantage avantage) {
        return new AvantageModel(avantage);
    }

    @Override
    public List<AvantageModel> findAllByFonction(Long id) {
        Fonction fonction=fonctionRepository.findById(id).get();
      return  fonction.getAvantages().stream().map(this::avantageToAvantageModel).collect(Collectors.toList());

    }
    
}
