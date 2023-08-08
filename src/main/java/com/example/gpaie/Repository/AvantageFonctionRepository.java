package com.example.gpaie.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gpaie.Entity.Avantage_fontion;
import com.example.gpaie.Entity.Fonction;
import com.example.gpaie.Entity.TypePlaining;

public interface AvantageFonctionRepository  extends JpaRepository<Avantage_fontion, Long>{
    List<Avantage_fontion> findAllByFonction(Fonction fonction);
}
