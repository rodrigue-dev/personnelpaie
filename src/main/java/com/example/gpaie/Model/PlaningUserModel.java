package com.example.gpaie.Model;

import java.util.List;

public class PlaningUserModel {
    private long user_id;
    private long departement_id;
    private String user;
    private List<Makeplaning> makeplanings;
    private double total_heure;
    private double total_heure_suppl;

    public double getTotal_heure_suppl() {
        return this.total_heure_suppl;
    }

    public void setTotal_heure_suppl(double total_heure_suppl) {
        this.total_heure_suppl = total_heure_suppl;
    }

    public double getTotal_heure() {
        return this.total_heure;
    }

    public void setTotal_heure(double total_heure) {
        this.total_heure = total_heure;
    }

    public long getDepartement_id() {
        return this.departement_id;
    }

    public void setDepartement_id(long departement_id) {
        this.departement_id = departement_id;
    }

    public long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Makeplaning> getMakeplanings() {
        return this.makeplanings;
    }

    public void setMakeplanings(List<Makeplaning> makeplanings) {
        this.makeplanings = makeplanings;
    }

}
