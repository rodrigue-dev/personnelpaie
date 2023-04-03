package com.example.gpaie.Model;

import com.example.gpaie.Entity.Departement;

public class DepartementModel {
    private Long id;
    private String nomDepartement;

    public DepartementModel() {
    }
    public DepartementModel(Departement departement) {
        this.id=departement.getId();
        this.nomDepartement=departement.getNomDepartement();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomDepartement() {
        return this.nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

}
