package com.example.gpaie.Service.Impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gpaie.Entity.Planinig;
import com.example.gpaie.Model.PlaningModel;
import com.example.gpaie.Repository.FonctionRepository;
import com.example.gpaie.Repository.PlaningRepository;
import com.example.gpaie.Repository.TypePlaningRepository;
import com.example.gpaie.Repository.UserRepository;
import com.example.gpaie.Service.PlaningService;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
@Service
public class PlaningServiceImpl implements PlaningService{
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private PlaningRepository planingRepository;
    @Autowired
    private TypePlaningRepository typePlaningRepository;
    @Autowired
    private FonctionRepository fonctionRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public PlaningModel save(PlaningModel planingModel) {
        System.out.println(planingModel);
        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Planinig planinig;
        if(planingModel.getId()== null){
            planinig=new Planinig();  
        }else{
            planinig =planingRepository.findById(planingModel.getId()).get();
        }
        planinig.setTypePlaining(typePlaningRepository.findById(planingModel.getType_planing_id()).get());
        planinig.setFonction(fonctionRepository.findById(planingModel.getFonction_id()).get());
        planinig.setUser(userRepository.findById(planingModel.getUser_id()).get());
        planinig.setDateDebut(LocalDateTime.parse(planingModel.getDateDebut(),dateTimeFormatter));
        planinig.setDateFin(LocalDateTime.parse(planingModel.getDateFin(),dateTimeFormatter));
        planingRepository.saveAndFlush(planinig);
        return planingModel;
    }
    @Override
    public Optional<PlaningModel> partialUpdate(PlaningModel zoneDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'partialUpdate'");
    }
    @Override
    public List<PlaningModel> findAll() {
       return planingRepository.findAll().stream().map(this::planingToPlaningModel).collect(Collectors.toList());
    }
    @Override
    public Optional<PlaningModel> findOne(Long id) {
        return planingRepository.findById(id).map(e->planingToPlaningModel(e));
    }
    @Override
    public void delete(Long id) {
       planingRepository.deleteById(id);;
    }
    public PlaningModel planingToPlaningModel(Planinig planinig) {
        return new PlaningModel(planinig);
    }
}
