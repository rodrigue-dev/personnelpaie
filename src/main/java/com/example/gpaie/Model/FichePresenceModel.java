package com.example.gpaie.Model;

import java.time.LocalTime;

import com.example.gpaie.Entity.FichePresence;

public class FichePresenceModel {
    private Long id;
    private String heureDebut;
    private String heureFin;
    private String user_name;
    private String user_matricule;
    private Long user_id;
    private String date_presence;

    public FichePresenceModel() {
    }

    public FichePresenceModel(FichePresence item) {
        this.id=item.getId();
        this.heureDebut=item.getHeureDebut()==null?"":item.getHeureDebut().toString();
        this.heureFin=item.getHeureFin()==null?"":item.getHeureFin().toString();
        this.date_presence=item.getDatePresence().toString();
        this.user_id=item.getUser().getId();
        this.user_name=item.getUser().getNom()+' '+item.getUser().getPrenom();
        this.user_matricule=item.getUser().getMatricule();
    }

    public String getUser_matricule() {
        return this.user_matricule;
    }

    public void setUser_matricule(String user_matricule) {
        this.user_matricule = user_matricule;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getDate_presence() {
        return this.date_presence;
    }

    public void setDate_presence(String date_presence) {
        this.date_presence = date_presence;
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

    public String getHeureDebut() {
        return this.heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }


}
