package com.example.gpaie.Model;

import com.example.gpaie.Entity.Avantage;

public class AvantageModel {
    private Long id;
    private String typeAvantage;

    public AvantageModel() {
    }

    public AvantageModel(Avantage avantage) {
        this.id=avantage.getId();
        this.typeAvantage=avantage.getTypeAvantage();
    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeAvantage() {
        return this.typeAvantage;
    }

    public void setTypeAvantage(String typeAvantage) {
        this.typeAvantage = typeAvantage;
    }

}
