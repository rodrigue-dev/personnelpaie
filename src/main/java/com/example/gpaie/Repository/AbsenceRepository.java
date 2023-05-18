package com.example.gpaie.Repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gpaie.Entity.Absence;
import com.example.gpaie.Entity.User;

public interface AbsenceRepository  extends JpaRepository<Absence, Long>{
    Absence findOneByDateDebutAndUser(LocalDate localDate2,User user);
    @Query("select p from Absence p  where p.dateDebut >=:x and p.dateFin <=:x and p.user =:y")
    Absence findbetwenDate(@Param("x") LocalDate date,@Param("y") User user);
}
