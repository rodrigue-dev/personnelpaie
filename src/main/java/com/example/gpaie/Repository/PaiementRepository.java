package com.example.gpaie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gpaie.Entity.Paiement;

public interface PaiementRepository  extends JpaRepository<Paiement, Long>{
    
}
