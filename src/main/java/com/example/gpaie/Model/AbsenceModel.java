package com.example.gpaie.Model;

import com.example.gpaie.Entity.Absence;
import com.example.gpaie.Entity.Avantage;

public class AbsenceModel {
    private Long id;
    private Long user_id;
    private String motif;
    private String dateAbsence;
    private String user_name;

    public AbsenceModel() {
    }

    public AbsenceModel(Absence absence) {
        this.id=absence.getId();
        this.motif=absence.getMotif();
        this.user_name=absence.getUser().getNom()+ " "+absence.getUser().getPrenom();
        this.user_id=absence.getUser().getId();
        this.dateAbsence=absence.getDateAbsence().toString();
    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMotif() {
        return this.motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getDateAbsence() {
        return this.dateAbsence;
    }

    public void setDateAbsence(String dateAbsence) {
        this.dateAbsence = dateAbsence;
    }


}
