package com.example.gpaie.Model;

import com.example.gpaie.Entity.TypePlaining;

public class TypePlaningModel {
    private Long id;
    private String nomType;

    public TypePlaningModel() {
    }

    public TypePlaningModel(TypePlaining item) {
        this.id=item.getId();
        this.nomType=item.getNomType();
    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomType() {
        return this.nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

}
