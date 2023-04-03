package com.example.gpaie.Entity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Fonction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    private String typeFonction;
    private float salaireSuppl;
    private int heureTravaille;
    @JsonIgnore
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Avantage> avantages = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Departement> departements = new HashSet<>();


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return this.modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getTypeFonction() {
        return this.typeFonction;
    }

    public void setTypeFonction(String typeFonction) {
        this.typeFonction = typeFonction;
    }

    public float getSalaireSuppl() {
        return this.salaireSuppl;
    }

    public void setSalaireSuppl(float salaireSuppl) {
        this.salaireSuppl = salaireSuppl;
    }

    public int getHeureTravaille() {
        return this.heureTravaille;
    }

    public void setHeureTravaille(int heureTravaille) {
        this.heureTravaille = heureTravaille;
    }

    public Set<Avantage> getAvantages() {
        return this.avantages;
    }

    public void setAvantages(Set<Avantage> avantages) {
        this.avantages = avantages;
    }

    public Set<Departement> getDepartements() {
        return this.departements;
    }

    public void setDepartements(Set<Departement> departements) {
        this.departements = departements;
    }

}
