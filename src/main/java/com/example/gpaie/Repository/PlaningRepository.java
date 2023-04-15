package com.example.gpaie.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gpaie.Entity.Planinig;
import com.example.gpaie.Entity.User;

public interface PlaningRepository  extends JpaRepository<Planinig, Long>{

    Planinig findOneByDatePlaningAndUser(LocalDate localDate2,User user);
    List<Planinig> findAllByUserAndDatePlaningBetween(User user, LocalDate datePresence,LocalDate datePresence2);
    
}
