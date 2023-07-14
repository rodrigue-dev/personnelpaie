package com.example.gpaie.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gpaie.Entity.Planinig;
import com.example.gpaie.Entity.User;

public interface PlaningRepository  extends JpaRepository<Planinig, Long>{

    Planinig findFirstByDatePlaningAndUser(LocalDate localDate2,User user);
    @Query("select p from Planinig p  where p.datePlaning >=:x and p.datePlaning <:z and p.user =:y")
    List<Planinig> finByUserAndDatePlaningBetween(@Param("x") LocalDate dateDebut,@Param("z") LocalDate dateFin,@Param("y") User user);
    List<Planinig> findAllByUserAndDatePlaningBetween(User user, LocalDate datePresence,LocalDate datePresence2);
    
}
