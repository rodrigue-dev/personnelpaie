package com.example.gpaie.Model;

import java.time.format.DateTimeFormatter;

import com.example.gpaie.Entity.HeureSupplementaire;

public class HeureSupplModel {
    private String dateHeureSuppl;
    private String heureDebut;
    private String heureFin;
    private Long id;
    private long user_id;
    private String user_name;


    public HeureSupplModel() {
    }

    public HeureSupplModel(HeureSupplementaire hSupplementaire) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm");
        this.dateHeureSuppl=hSupplementaire.getDateHeureSuppl().format(dateTimeFormatter);
        this.heureDebut=hSupplementaire.getHeureDebut().format(timeFormatter);
        this.heureFin=hSupplementaire.getHeureFin().format(timeFormatter);
        this.user_id=hSupplementaire.getUser().getId();
        this.user_name=hSupplementaire.getUser().getNom()+' '+hSupplementaire.getUser().getPrenom();
    }

    public String getDateHeureSuppl() {
        return this.dateHeureSuppl;
    }

    public void setDateHeureSuppl(String dateHeureSuppl) {
        this.dateHeureSuppl = dateHeureSuppl;
    }

    public String getHeureDebut() {
        return this.heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return this.heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

}
