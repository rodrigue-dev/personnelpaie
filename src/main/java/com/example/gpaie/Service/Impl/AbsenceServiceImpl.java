package com.example.gpaie.Service.Impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gpaie.Entity.Absence;
import com.example.gpaie.Entity.Planinig;
import com.example.gpaie.Entity.User;
import com.example.gpaie.Model.AbsenceModel;
import com.example.gpaie.Repository.AbsenceRepository;
import com.example.gpaie.Repository.PlaningRepository;
import com.example.gpaie.Repository.UserRepository;
import com.example.gpaie.Service.AbsenceService;
import com.example.gpaie.Service.PlaningService;
@Service
public class AbsenceServiceImpl implements AbsenceService{

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private AbsenceRepository absenceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlaningRepository planingRepository;

    @Override
    public AbsenceModel save(AbsenceModel absenceModel) {
        Absence absence;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(absenceModel.getId()== null){
            absence=new Absence();  
            absence.setUser(userRepository.findById(absenceModel.getUser_id()).get());
        }else{
            absence =absenceRepository.findById(absenceModel.getId()).get();
        }
        absence.setDateDebut(LocalDate.parse(absenceModel.getDateDebut(), dateTimeFormatter));
        absence.setDateFin(LocalDate.parse(absenceModel.getDateFin(), dateTimeFormatter));
        absence.setMotif(absenceModel.getMotif());
       var ab= absenceRepository.saveAndFlush(absence);
       var planings= planingRepository.finByUserAndDatePlaningBetween(ab.getDateDebut(), ab.getDateFin(),ab.getUser());
       for (Planinig planinig : planings) {
        System.out.println(planinig.toString());
        planingRepository.deleteById(planinig.getId());
       }
        return absenceModel;
    }

    @Override
    public Optional<AbsenceModel> partialUpdate(AbsenceModel zoneDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'partialUpdate'");
    }

    @Override
    public List<AbsenceModel> findAll() {
      return absenceRepository.findAll().stream().filter(Objects::nonNull).map(this::absenceToAbsenceModel).sorted((x,y)-> y.getId().compareTo(x.getId())).collect(Collectors.toList());
    }
    @Override
    public List<AbsenceModel> findAllOwn(Long id) {
        User user=userRepository.findById(id).get();
      return absenceRepository.findAll().stream().filter(e->e.getUser()==user).filter(Objects::nonNull).map(this::absenceToAbsenceModel).collect(Collectors.toList());
    }
    @Override
    public Optional<AbsenceModel> findOne(Long id) {
       return absenceRepository.findById(id).map(e->absenceToAbsenceModel(e));
    }

    @Override
    public void delete(Long id) {
        absenceRepository.deleteById(id);
    }
    public AbsenceModel absenceToAbsenceModel(Absence absence) {
        return new AbsenceModel(absence);
    }
    
}
