package com.example.gpaie.Model;

import java.util.HashSet;
import java.util.Set;

public class fonctionItemRequest {
    private Long fonction_id;
    private Set<Long>items=new HashSet<>();
    private Long avantage_id;
    private float montant;
    public Long getFonction_id() {
        return this.fonction_id;
    }

    public Long getAvantage_id() {
        return this.avantage_id;
    }

    public void setAvantage_id(Long avantage_id) {
        this.avantage_id = avantage_id;
    }

    public float getMontant() {
        return this.montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public void setFonction_id(Long fonction_id) {
        this.fonction_id = fonction_id;
    }

    public Set<Long> getItems() {
        return this.items;
    }

    public void setItems(Set<Long> items) {
        this.items = items;
    }

    

    @Override
    public String toString() {
        return "{" +
            " fonction_id='" + getFonction_id() + "'" +
            ", avantages='" + getItems() + "'" +
            "}";
    }

}
