package com.example.gpaie.Service.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gpaie.Entity.Absence;
import com.example.gpaie.Entity.Planinig;
import com.example.gpaie.Entity.User;
import com.example.gpaie.Model.Makeplaning;
import com.example.gpaie.Model.PlaningModel;
import com.example.gpaie.Model.PlaningUserModel;
import com.example.gpaie.Repository.AbsenceRepository;
import com.example.gpaie.Repository.FonctionRepository;
import com.example.gpaie.Repository.PlaningRepository;
import com.example.gpaie.Repository.TypePlaningRepository;
import com.example.gpaie.Repository.UserRepository;
import com.example.gpaie.Service.PlaningService;
import com.example.gpaie.Utils.DateUtil;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

@Service
public class PlaningServiceImpl implements PlaningService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private PlaningRepository planingRepository;
    @Autowired
    private AbsenceRepository congeRepository;
    @Autowired
    private FonctionRepository fonctionRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public PlaningModel save(PlaningModel planingModel) {
        // System.out.println(planingModel);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Planinig planinig;
        if (planingModel.getId() != null) {
            planinig = planingRepository.findById(planingModel.getId()).get();
        } else {
            planinig = new Planinig();
            planinig.setDatePlaning(LocalDate.parse(planingModel.getDate_debut(), dateTimeFormatter));
        }
        planinig.setFonction(fonctionRepository.findById(planingModel.getFonction_id()).get());
        planinig.setUser(userRepository.findById(planingModel.getUser_id()).get());
        planinig.setFonction(fonctionRepository.findById(planingModel.getFonction_id()).get());
        planinig.setUser(userRepository.findById(planingModel.getUser_id()).get());
        planinig.setType_planing(planingModel.getType_planing());
        planinig.setDateDebut(LocalDate.parse(planingModel.getDate_debut().toString(), dateTimeFormatter));
        planinig.setDateFin(LocalDate.parse(planingModel.getDate_fin().toString(), dateTimeFormatter));
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
        return planingRepository.findById(id).map(e -> planingToPlaningModel(e));
    }

    @Override
    public void delete(Long id) {
        var planing=planingRepository.findById(id).get();
        var conge=new Absence();
        conge.setDateAbsence(planing.getDatePlaning());
        conge.setUser(planing.getUser());
        congeRepository.save(conge);
        planingRepository.deleteById(id);
       
    }

    public PlaningModel planingToPlaningModel(Planinig planinig) {
        return new PlaningModel(planinig);
    }

    @Override
    public List<PlaningUserModel> getPlaningWeek(String localdate) {
        System.out.println(localdate);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<LocalDate> days = DateUtil.getWeekFromDate(LocalDate.parse(localdate, dateTimeFormatter));
        List<User> users = userRepository.findAll();
        List<PlaningUserModel> planingUserModels = new ArrayList<>();
        for (User user : users) {
            List<Makeplaning> makeplanings = new ArrayList<>();
            var nb_user=0;
            double total_heure = 0.0;
            for (LocalDate localDate2 : days) {
                if (user.getTypeplaning() == 2) { //Etudiant
                    var makeP = new Makeplaning();
                    Planinig planinig = planingRepository.findOneByDatePlaningAndUser(localDate2, user);
                    List<Planinig>planinigs=planingRepository.findAllByUserAndDatePlaningBetween(user, days.stream().findFirst().get(), days.stream().sorted(Comparator.reverseOrder()).findFirst().get());
                    Absence absence=congeRepository.findOneByDateAbsenceAndUser(localDate2, user);
                    if(planinig == null && absence==null && planinigs.size()<3){
                        planinig=new Planinig();
                        planinig.setFonction(user.getFonction());
                        planinig.setHeureDebut(LocalTime.of(8, 0, 0));
                        planinig.setHeureFin(planinig.getHeureDebut().plusHours(8));
                        planinig.setDatePlaning(localDate2);
                        planinig.setUser(user);
                        planinig.setType_planing(user.getTypeplaning());
                        planingRepository.save(planinig);
                        
                    }
                    if (planinig != null) {
                        makeP.setFonction(planinig.getFonction().getTypeFonction());
                        makeP.setHeure_debut(planinig.getHeureDebut().toString());
                        makeP.setHeure_fin(planinig.getHeureFin().toString());
                        makeP.setPlaning_id(planinig.getId());
                        var minFin = planinig.getHeureFin().getHour() * 60 + planinig.getHeureFin().getMinute();
                        var minDebut = planinig.getHeureDebut().getHour() * 60 + planinig.getHeureDebut().getMinute();
                        nb_user+=1;
                        total_heure += (minFin - minDebut);
                    }
                    makeP.setDate_planing(localDate2.format(dateTimeFormatter));
                    if(nb_user<=3){

                      makeplanings.add(makeP);
                    }
                } else if (user.getTypeplaning() == 1) { //Mitemps
                    var makeP = new Makeplaning();
                    Planinig planinig = planingRepository.findOneByDatePlaningAndUser(localDate2, user);
                    Absence absence=congeRepository.findOneByDateAbsenceAndUser(localDate2, user);
                    if(planinig == null && absence==null){
                        planinig=new Planinig();
                        planinig.setFonction(user.getFonction());
                        planinig.setHeureDebut(LocalTime.of(8, 0, 0));
                        planinig.setHeureFin(planinig.getHeureDebut().plusHours(4));
                        planinig.setDatePlaning(localDate2);
                        planinig.setUser(user);
                        planinig.setType_planing(user.getTypeplaning());
                        planingRepository.save(planinig);
                        
                    }
                    if (planinig != null) {
                        makeP.setFonction(planinig.getFonction().getTypeFonction());
                        makeP.setHeure_debut(planinig.getHeureDebut().toString());
                        makeP.setHeure_fin(planinig.getHeureFin().toString());
                        makeP.setPlaning_id(planinig.getId());
                        var minFin = planinig.getHeureFin().getHour() * 60 + planinig.getHeureFin().getMinute();
                        var minDebut = planinig.getHeureDebut().getHour() * 60 + planinig.getHeureDebut().getMinute();

                        total_heure += (minFin - minDebut);
                    }
                    makeP.setDate_planing(localDate2.format(dateTimeFormatter));
                    makeplanings.add(makeP);
                } else { //temps plein
                    var makeP = new Makeplaning();
                    Planinig planinig = planingRepository.findOneByDatePlaningAndUser(localDate2, user);
                    Absence absence=congeRepository.findOneByDateAbsenceAndUser(localDate2, user);
                    if(planinig == null && absence==null){
                        planinig=new Planinig();
                        planinig.setFonction(user.getFonction());
                        planinig.setHeureDebut(LocalTime.of(8, 0, 0));
                        planinig.setHeureFin(planinig.getHeureDebut().plusHours(8));
                        planinig.setDatePlaning(localDate2);
                        planinig.setUser(user);
                        planinig.setType_planing(user.getTypeplaning());
                        planingRepository.save(planinig);
                        
                    }
                    if (planinig != null) {
                        makeP.setFonction(planinig.getFonction().getTypeFonction());
                        makeP.setHeure_debut(planinig.getHeureDebut().toString());
                        makeP.setHeure_fin(planinig.getHeureFin().toString());
                        makeP.setPlaning_id(planinig.getId());
                        var minFin = planinig.getHeureFin().getHour() * 60 + planinig.getHeureFin().getMinute();
                        var minDebut = planinig.getHeureDebut().getHour() * 60 + planinig.getHeureDebut().getMinute();

                        total_heure += (minFin - minDebut);
                    }
                    makeP.setDate_planing(localDate2.format(dateTimeFormatter));
                    makeplanings.add(makeP);
                }

            }
            PlaningUserModel planingUserModel = new PlaningUserModel();
            planingUserModel.setUser(user.getNom() + ' ' + user.getPrenom());
            planingUserModel.setUser_id(user.getId());
            planingUserModel.setDepartement_id(user.getDepartement().getId());
            planingUserModel.setMakeplanings(makeplanings);
            planingUserModel.setTotal_heure(total_heure / 60);
            planingUserModels.add(planingUserModel);
        }
        return planingUserModels;

    }

    @Override
    public List<LocalDate> getPlaningHeaderWeek(String localdate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<LocalDate> days = DateUtil.getWeekFromDate(LocalDate.parse(localdate, dateTimeFormatter));
        return days;
    }

    @Override
    public List<PlaningModel> getPlaningByUserBetwennDate(Long user_id, String datedebut, String datefin) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var user = userRepository.findById(user_id).get();
        return planingRepository
                .findAllByUserAndDatePlaningBetween(user, LocalDate.parse(datedebut, dateTimeFormatter),
                        LocalDate.parse(datefin, dateTimeFormatter))
                .stream()
                .map(this::planingToPlaningModel).collect(Collectors.toList());
    }

    @Override
    public List<Makeplaning> getPlaningByUser(Long user_id, String localdate) {
        var user = userRepository.findById(user_id).get();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<LocalDate> days = DateUtil.getWeekFromDate(LocalDate.parse(localdate, dateTimeFormatter));
        List<Makeplaning> makeplanings = new ArrayList<>();
        double total_heure = 0.0;
        for (LocalDate localDate2 : days) {
            var makeP = new Makeplaning();
            Planinig planinig = planingRepository.findOneByDatePlaningAndUser(localDate2, user);
            if(planinig == null){
                planinig=new Planinig();
                planinig.setFonction(user.getFonction());
                planinig.setHeureDebut(LocalTime.of(8, 0, 0));
                planinig.setHeureFin(planinig.getHeureDebut().plusHours(4));
                planinig.setDatePlaning(localDate2);
                planinig.setUser(user);
                planinig.setType_planing(user.getTypeplaning());
                planingRepository.save(planinig);
                
            }
            if (planinig != null) {
                makeP.setFonction(planinig.getFonction().getTypeFonction());
                makeP.setHeure_debut(planinig.getHeureDebut().toString());
                makeP.setHeure_fin(planinig.getHeureFin().toString());
                makeP.setPlaning_id(planinig.getId());
                makeP.setDate_planing(localdate);
                var minFin = planinig.getHeureFin().getHour() * 60 + planinig.getHeureFin().getMinute();
                var minDebut = planinig.getHeureDebut().getHour() * 60 + planinig.getHeureDebut().getMinute();

                total_heure += (minFin - minDebut);
            }
            makeP.setDate_planing(localDate2.format(dateTimeFormatter));
            makeplanings.add(makeP);
        }
        return makeplanings;
    }
}
