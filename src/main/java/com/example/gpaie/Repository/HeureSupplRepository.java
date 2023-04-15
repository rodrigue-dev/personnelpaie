package com.example.gpaie.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gpaie.Entity.HeureSupplementaire;
import com.example.gpaie.Entity.User;

public interface HeureSupplRepository  extends JpaRepository<HeureSupplementaire, Long>{
    List<HeureSupplementaire> findAllByUserAndDateHeureSupplBetween(User user,LocalDate datedebut , LocalDate datefin);
}
