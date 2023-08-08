package com.example.gpaie.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Avantage_fontion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Fonction fonction;
    @ManyToOne
    private Avantage avantage;
    private float montant;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fonction getFonction() {
        return this.fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public Avantage getAvantage() {
        return this.avantage;
    }

    public void setAvantage(Avantage avantage) {
        this.avantage = avantage;
    }

    public float getMontant() {
        return this.montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

}
