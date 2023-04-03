package com.example.gpaie.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.gpaie.Entity.Planinig;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class PlaningModel {
    private Long id;
    private String dateDebut;
    private String dateFin;
    private long user_id;
    private long fonction_id;
    private long type_planing_id;
    private String typeFonction;
    private String user_name;

    public PlaningModel() {
        
    }

    public PlaningModel(Planinig item) {
        this.id=item.getId();
        this.dateDebut=item.getDateDebut().toString();
        this.dateFin=item.getDateFin().toString();
        this.user_id=item.getUser().getId();
        this.fonction_id=item.getFonction().getId();
        this.type_planing_id=item.getFonction().getId();
        this.typeFonction=item.getFonction().getTypeFonction();
        this.user_name=item.getUser().getNom();
    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getType_planing_id() {
        return this.type_planing_id;
    }

    public void setType_planing_id(long type_planing_id) {
        this.type_planing_id = type_planing_id;
    }

    public String getDateDebut() {
        return this.dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getFonction_id() {
        return this.fonction_id;
    }

    public void setFonction_id(long fonction_id) {
        this.fonction_id = fonction_id;
    }

    public String getTypeFonction() {
        return this.typeFonction;
    }

    public void setTypeFonction(String typeFonction) {
        this.typeFonction = typeFonction;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", user_id='" + getUser_id() + "'" +
            ", fonction_id='" + getFonction_id() + "'" +
            ", type_planing_id='" + getType_planing_id() + "'" +
            ", typeFonction='" + getTypeFonction() + "'" +
            ", user_name='" + getUser_name() + "'" +
            "}";
    }


}
