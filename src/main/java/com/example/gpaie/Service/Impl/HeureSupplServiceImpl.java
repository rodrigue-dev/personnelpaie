package com.example.gpaie.Service.Impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gpaie.Entity.HeureSupplementaire;
import com.example.gpaie.Model.HeureSupplModel;
import com.example.gpaie.Repository.HeureSupplRepository;
import com.example.gpaie.Repository.UserRepository;
import com.example.gpaie.Service.HeureSupplService;
@Service
public class HeureSupplServiceImpl implements HeureSupplService{
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private HeureSupplRepository heureSupplRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public HeureSupplModel save(HeureSupplModel heureSupplModel) {
        HeureSupplementaire hSupplementaire;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        if(heureSupplModel.getId()== null){
            var user=userRepository.findById(heureSupplModel.getUser_id()).get();
            var hs=heureSupplRepository.findOneByDateHeureSupplAndUser(LocalDate.parse(heureSupplModel.getDateHeureSuppl().toString(), dateTimeFormatter), user);
            if(hs==null){
                hSupplementaire=new HeureSupplementaire();
                hSupplementaire.setUser(user); 
            }else{
                hSupplementaire=hs;
                
            }
            
        }else{
            hSupplementaire =heureSupplRepository.findById(heureSupplModel.getId()).get();
        }

        hSupplementaire.setHeureDebut(LocalTime.parse(heureSupplModel.getHeureDebut().toString(), timeFormatter));
        hSupplementaire.setHeureFin(LocalTime.parse(heureSupplModel.getHeureFin().toString(), timeFormatter));
        hSupplementaire.setDateHeureSuppl(LocalDate.parse(heureSupplModel.getDateHeureSuppl().toString(), dateTimeFormatter));
        heureSupplRepository.saveAndFlush(hSupplementaire);
        return heureSupplModel;
    }
    @Override
    public Optional<HeureSupplModel> partialUpdate(HeureSupplModel heureSupplModel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'partialUpdate'");
    }
    @Override
    public List<HeureSupplModel> findAll() {
        return heureSupplRepository.findAll().stream().filter(Objects::nonNull).sorted((x,y)-> y.getId().compareTo(x.getId())).map(this::heureSupplToHeureSupplModel).collect(Collectors.toList());
    }
    @Override
    public Optional<HeureSupplModel> findOne(Long id) {
        return heureSupplRepository.findById(id).map(e->heureSupplToHeureSupplModel(e));
    }
    public HeureSupplModel heureSupplToHeureSupplModel(HeureSupplementaire hSupplementaire) {
        return new HeureSupplModel(hSupplementaire);
    }
    @Override
    public void delete(Long id) {
        heureSupplRepository.deleteById(id);
    }
    @Override
    public List<HeureSupplModel> heureSuppByUser(Long userid) {
        var user =userRepository.findById(userid).get();
        return heureSupplRepository.findAll().stream().filter(e->e.getUser()==user).map(this::heureSupplToHeureSupplModel).collect(Collectors.toList());
    }

}
