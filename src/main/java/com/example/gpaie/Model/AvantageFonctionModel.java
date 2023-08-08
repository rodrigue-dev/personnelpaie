package com.example.gpaie.Model;

import com.example.gpaie.Entity.Avantage_fontion;

public class AvantageFonctionModel {
    private Long id;
    private Long avantage_id;
    private Long fonction_id;
    private String avantage_libelle;
    private String fonction_libelle;
    private float montant;

    public AvantageFonctionModel() {
    }

    public AvantageFonctionModel(Avantage_fontion avantage_fontion) {
        this.id=avantage_fontion.getId();
        this.avantage_id=avantage_fontion.getAvantage().getId();
        this.avantage_libelle=avantage_fontion.getAvantage().getTypeAvantage();
        this.fonction_id=avantage_fontion.getFonction().getId();
        this.fonction_libelle=avantage_fontion.getFonction().getTypeFonction();
        this.montant=avantage_fontion.getMontant();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAvantage_id() {
        return this.avantage_id;
    }

    public void setAvantage_id(Long avantage_id) {
        this.avantage_id = avantage_id;
    }

    public Long getFonction_id() {
        return this.fonction_id;
    }

    public void setFonction_id(Long fonction_id) {
        this.fonction_id = fonction_id;
    }

    public String getAvantage_libelle() {
        return this.avantage_libelle;
    }

    public void setAvantage_libelle(String avantage_libelle) {
        this.avantage_libelle = avantage_libelle;
    }

    public String getFonction_libelle() {
        return this.fonction_libelle;
    }

    public void setFonction_libelle(String fonction_libelle) {
        this.fonction_libelle = fonction_libelle;
    }

    public float getMontant() {
        return this.montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

}
