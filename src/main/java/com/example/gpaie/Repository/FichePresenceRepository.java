package com.example.gpaie.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gpaie.Entity.FichePresence;
import com.example.gpaie.Entity.User;

public interface FichePresenceRepository  extends JpaRepository<FichePresence, Long>{

    List<FichePresence> findAllByUserAndDatePresence(User user, LocalDate datePresence);
    List<FichePresence> findAllByUserAndDatePresenceBetween(User user, LocalDate datePresence,LocalDate datePresence2);
    FichePresence findByUserAndDatePresence(User user, LocalDate datePresence);
    
}
