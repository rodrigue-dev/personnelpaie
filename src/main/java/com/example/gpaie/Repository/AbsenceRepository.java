package com.example.gpaie.Repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gpaie.Entity.Absence;
import com.example.gpaie.Entity.User;

public interface AbsenceRepository  extends JpaRepository<Absence, Long>{
    Absence findOneByDateAbsenceAndUser(LocalDate localDate2,User user);
}
