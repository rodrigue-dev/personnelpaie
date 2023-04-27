package com.example.gpaie.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gpaie.Entity.Paiement;
import com.example.gpaie.Entity.User;

public interface PaiementRepository  extends JpaRepository<Paiement, Long>{

    List<Paiement> findAllByMonthAndYear(int month, int year);
    List<Paiement> findAllByUser(User user);
    Paiement findOneByMonthAndYearAndUser(int date_debut, int date_fin,User user);
    
}
