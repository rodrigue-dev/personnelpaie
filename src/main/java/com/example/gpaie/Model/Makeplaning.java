package com.example.gpaie.Model;

public class Makeplaning {
    private long planing_id;
    private String fonction;
    private int typeplaning;
    private boolean ispointe;
    private String heure_debut;
    private String heure_fin;
    private String date_planing;
    private Long heuresuppl;

    public Long getHeuresuppl() {
        return this.heuresuppl;
    }

    public void setHeuresuppl(Long heuresuppl) {
        this.heuresuppl = heuresuppl;
    }

    public boolean isIspointe() {
        return this.ispointe;
    }

    public boolean getIspointe() {
        return this.ispointe;
    }

    public void setIspointe(boolean ispointe) {
        this.ispointe = ispointe;
    }

    public int getTypeplaning() {
        return this.typeplaning;
    }

    public void setTypeplaning(int typeplaning) {
        this.typeplaning = typeplaning;
    }

    public long getPlaning_id() {
        return this.planing_id;
    }

    public void setPlaning_id(long planing_id) {
        this.planing_id = planing_id;
    }

    public String getHeure_debut() {
        return this.heure_debut;
    }

    public void setHeure_debut(String heure_debut) {
        this.heure_debut = heure_debut;
    }

    public String getHeure_fin() {
        return this.heure_fin;
    }

    public void setHeure_fin(String heure_fin) {
        this.heure_fin = heure_fin;
    }

    public String getDate_planing() {
        return this.date_planing;
    }

    public void setDate_planing(String date_planing) {
        this.date_planing = date_planing;
    }

    public String getFonction() {
        return this.fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

}
