package com.example.gpaie.Entity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Entity
public class Planinig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    private LocalDate datePlaning;
    @ManyToOne
    private TypePlaining typePlaining;
    @ManyToOne
    private Fonction fonction;
    @ManyToOne
    private User user;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Boolean isPointe;
    private Integer type_planing;

    public Long getId() {
        return this.id;
    }

    public Integer getType_planing() {
        return this.type_planing;
    }

    public void setType_planing(Integer type_planing) {
        this.type_planing = type_planing;
    }

    public LocalDate getDatePlaning() {
        return this.datePlaning;
    }

    public void setDatePlaning(LocalDate datePlaning) {
        this.datePlaning = datePlaning;
    }

    public LocalTime getHeureDebut() {
        return this.heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return this.heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }


    public Boolean isIsPointe() {
        return this.isPointe;
    }

    public Boolean getIsPointe() {
        return this.isPointe;
    }

    public void setIsPointe(Boolean isPointe) {
        this.isPointe = isPointe;
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

    public TypePlaining getTypePlaining() {
        return this.typePlaining;
    }

    public void setTypePlaining(TypePlaining typePlaining) {
        this.typePlaining = typePlaining;
    }

    public Fonction getFonction() {
        return this.fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateDebut() {
        return this.dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

}
