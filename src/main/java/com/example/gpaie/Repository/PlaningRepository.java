package com.example.gpaie.Repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gpaie.Entity.Planinig;

public interface PlaningRepository  extends JpaRepository<Planinig, Long>{

    Planinig findByDatePlaning(LocalDate localDate2);
    
}
