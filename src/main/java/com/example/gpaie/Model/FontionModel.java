package com.example.gpaie.Model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.gpaie.Entity.Fonction;

public class FontionModel {
    private Long id;
    private String typeFonction;
    private float salaireSuppl;
    private int heureTravaille;
    private Set<Long>avantages=new HashSet<>();
    private Set<Long>departements=new HashSet<>();

    public FontionModel() {
    }

    public FontionModel(Fonction item) {
        this.id=item.getId();
        this.typeFonction=item.getTypeFonction();
        this.salaireSuppl=item.getSalaireSuppl();
        this.heureTravaille=item.getHeureTravaille();
        this.avantages=item.getAvantages().stream().map(e->e.getId()).collect(Collectors.toSet());
        this.departements=item.getDepartements().stream().map(e->e.getId()).collect(Collectors.toSet());
    }

    public Set<Long> getAvantages() {
        return this.avantages;
    }

    public void setAvantages(Set<Long> avantages) {
        this.avantages = avantages;
    }

    public Set<Long> getDepartements() {
        return this.departements;
    }

    public void setDepartements(Set<Long> departements) {
        this.departements = departements;
    }
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeFonction() {
        return this.typeFonction;
    }

    public void setTypeFonction(String typeFonction) {
        this.typeFonction = typeFonction;
    }

    public float getSalaireSuppl() {
        return this.salaireSuppl;
    }

    public void setSalaireSuppl(float salaireSuppl) {
        this.salaireSuppl = salaireSuppl;
    }

    public int getHeureTravaille() {
        return this.heureTravaille;
    }

    public void setHeureTravaille(int heureTravaille) {
        this.heureTravaille = heureTravaille;
    }

}
