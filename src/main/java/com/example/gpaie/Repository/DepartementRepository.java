package com.example.gpaie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gpaie.Entity.Departement;

public interface DepartementRepository  extends JpaRepository<Departement, Long>{
    
}
