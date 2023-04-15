package com.example.gpaie.Service.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import com.example.gpaie.Entity.Planinig;
import com.example.gpaie.Entity.User;
import com.example.gpaie.Model.Makeplaning;
import com.example.gpaie.Model.PlaningModel;
import com.example.gpaie.Model.PlaningUserModel;
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
    private TypePlaningRepository typePlaningRepository;
    @Autowired
    private FonctionRepository fonctionRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public PlaningModel save(PlaningModel planingModel) {
        // System.out.println(planingModel);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        Planinig planinig;
        LocalDate in_date=LocalDate.parse(planingModel.getDate_debut(), dateTimeFormatter);
        if (planingModel.getRepeat().equals("none")) {
           
            if (planingModel.getId() != null) {
                planinig = planingRepository.findById(planingModel.getId()).get();
            } else {
                planinig = new Planinig();
                planinig.setDatePlaning(LocalDate.parse(planingModel.getDate_debut(), dateTimeFormatter));
            }

            planinig.setTypePlaining(typePlaningRepository.findById(planingModel.getType_planing_id()).get());
            planinig.setFonction(fonctionRepository.findById(planingModel.getFonction_id()).get());
            planinig.setUser(userRepository.findById(planingModel.getUser_id()).get());
            planinig.setTypePlaining(typePlaningRepository.findById(planingModel.getType_planing_id()).get());
            planinig.setFonction(fonctionRepository.findById(planingModel.getFonction_id()).get());
            planinig.setUser(userRepository.findById(planingModel.getUser_id()).get());

            planinig.setHeureDebut(LocalTime.parse(planingModel.getHeure_debut().toString(), timeFormatter));
            planinig.setHeureFin(LocalTime.parse(planingModel.getHeure_fin().toString(), timeFormatter));
            planingRepository.saveAndFlush(planinig);
        } else if (planingModel.getRepeat().equals("semaine")) {

            List<LocalDate> days = DateUtil
                    .getWeekFromDate(LocalDate.parse(planingModel.getDate_debut(), dateTimeFormatter));
            for (LocalDate localDate : days) {
                if(localDate.isAfter(in_date)|| localDate.isEqual(in_date)){
                planinig = new Planinig();
                planinig.setDatePlaning(localDate);
                planinig.setTypePlaining(typePlaningRepository.findById(planingModel.getType_planing_id()).get());
                planinig.setFonction(fonctionRepository.findById(planingModel.getFonction_id()).get());
                planinig.setUser(userRepository.findById(planingModel.getUser_id()).get());
                planinig.setTypePlaining(typePlaningRepository.findById(planingModel.getType_planing_id()).get());
                planinig.setFonction(fonctionRepository.findById(planingModel.getFonction_id()).get());
                planinig.setUser(userRepository.findById(planingModel.getUser_id()).get());
                planinig.setHeureDebut(LocalTime.parse(planingModel.getHeure_debut().toString(), timeFormatter));
                planinig.setHeureFin(LocalTime.parse(planingModel.getHeure_fin().toString(), timeFormatter));
                planingRepository.saveAndFlush(planinig);
                }
            }

        } else {
            List<LocalDate> days = DateUtil
            .getMonthFromDate(LocalDate.parse(planingModel.getDate_debut(), dateTimeFormatter));
    for (LocalDate localDate : days) {
        System.out.println(localDate);
        if(localDate.isAfter(in_date)|| localDate.isEqual(in_date)){
        planinig = new Planinig();
        planinig.setDatePlaning(localDate);
        planinig.setTypePlaining(typePlaningRepository.findById(planingModel.getType_planing_id()).get());
        planinig.setFonction(fonctionRepository.findById(planingModel.getFonction_id()).get());
        planinig.setUser(userRepository.findById(planingModel.getUser_id()).get());
        planinig.setTypePlaining(typePlaningRepository.findById(planingModel.getType_planing_id()).get());
        planinig.setFonction(fonctionRepository.findById(planingModel.getFonction_id()).get());
        planinig.setUser(userRepository.findById(planingModel.getUser_id()).get());
        planinig.setHeureDebut(LocalTime.parse(planingModel.getHeure_debut().toString(), timeFormatter));
        planinig.setHeureFin(LocalTime.parse(planingModel.getHeure_fin().toString(), timeFormatter));
        planingRepository.saveAndFlush(planinig);
        }
        
    }

        }

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
        planingRepository.deleteById(id);
        ;
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
            double total_heure = 0.0;
            for (LocalDate localDate2 : days) {
                var makeP = new Makeplaning();
                Planinig planinig = planingRepository.findOneByDatePlaningAndUser(localDate2, user);
                if (planinig != null) {
                    makeP.setFonction(planinig.getFonction().getTypeFonction());
                    makeP.setHeure_debut(planinig.getHeureDebut().toString());
                    makeP.setHeure_fin(planinig.getHeureFin().toString());
                    makeP.setPlaning_id(planinig.getId());
                    var minFin = planinig.getHeureFin().getHour() * 60 + planinig.getHeureFin().getMinute();
                    var minDebut = planinig.getHeureDebut().getHour() * 60 + planinig.getHeureDebut().getMinute();
                    System.out.println(minFin - minDebut);

                    total_heure += (minFin - minDebut);
                }
                makeP.setDate_planing(localDate2.format(dateTimeFormatter));
                makeplanings.add(makeP);
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
