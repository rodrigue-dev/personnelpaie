package com.example.gpaie.Service.Impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gpaie.Entity.FichePresence;
import com.example.gpaie.Entity.User;
import com.example.gpaie.Model.FichePresenceModel;
import com.example.gpaie.Repository.FichePresenceRepository;
import com.example.gpaie.Repository.UserRepository;
import com.example.gpaie.Service.FichePresenceService;
@Service
public class FichePresenceServiceImpl implements FichePresenceService{
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private FichePresenceRepository fichePresenceRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public FichePresenceModel save(FichePresenceModel fichePresenceModel) {
        FichePresence fichePresence;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        if(fichePresenceModel.getId()== null){
            fichePresence=new FichePresence();
            fichePresence.setUser(userRepository.findById(fichePresenceModel.getUser_id()).get());
            fichePresence.setDatePresence(LocalDate.parse(fichePresenceModel.getDate_presence(), dateTimeFormatter));
        }else{
            fichePresence =fichePresenceRepository.findById(fichePresenceModel.getId()).get();
            
        }
        if(fichePresenceModel.getHeureDebut() !=null){
        fichePresence.setHeureDebut(LocalTime.parse(fichePresenceModel.getHeureDebut().toString(), timeFormatter));
        }
        if(fichePresenceModel.getHeureFin() !=null){
             fichePresence.setHeureFin(LocalTime.parse(fichePresenceModel.getHeureFin().toString(), timeFormatter));
        }
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
    public List<FichePresenceModel> findAllByDate(String date) {
        var users=userRepository.findAll();
        List<FichePresence>fichePresences=new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (User user : users) {
            FichePresence fichePresence;
            var fichePresenceO=fichePresenceRepository.findByUserAndDatePresence(user, LocalDate.parse(date, dateTimeFormatter));
            if(!fichePresenceO.isPresent()){
                fichePresence=new FichePresence();
                fichePresence.setUser(user);
                fichePresence.setDatePresence(LocalDate.parse(date, dateTimeFormatter));
            }else{
                fichePresence=fichePresenceO.get();
            }
            fichePresences.add(fichePresence);
        }
        fichePresenceRepository.saveAllAndFlush(fichePresences);

        return fichePresences.stream().map(this::fichePresenceToFichePresenceModel).collect(Collectors.toList());
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
    @Override
    public List<FichePresenceModel> findByEmployeBetwennDate(Long user_id,String dateDebut, String datefin) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var user=userRepository.findById(user_id).get();
        return fichePresenceRepository.findAllByUserAndDatePresenceBetween(user, LocalDate.parse(dateDebut, dateTimeFormatter), LocalDate.parse(datefin, dateTimeFormatter)).stream()
        .map(this::fichePresenceToFichePresenceModel).collect(Collectors.toList());
    }
    @Override
    public List<FichePresenceModel> findByEmploye(Long user_id) {
        var user=userRepository.findById(user_id).get();
        return fichePresenceRepository.findAll().stream().filter(e->e.getHeureDebut()!=null).filter(e->e.getUser()==user).map(this::fichePresenceToFichePresenceModel).collect(Collectors.toList());
    }
    @Override
    public FichePresenceModel findOneByEmployeBetwennDate(Long user_id, String dateDebut) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var user=userRepository.findById(user_id).get();
        return fichePresenceRepository.findByUserAndDatePresence(user,LocalDate.parse(dateDebut, dateTimeFormatter)).map(this::fichePresenceToFichePresenceModel).orElseThrow();
    }
}
