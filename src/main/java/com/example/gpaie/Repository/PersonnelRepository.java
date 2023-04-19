package com.example.gpaie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gpaie.Entity.Personnel;

public interface PersonnelRepository   extends JpaRepository<Personnel, Long>{
    
}
