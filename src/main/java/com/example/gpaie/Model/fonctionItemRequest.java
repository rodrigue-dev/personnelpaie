package com.example.gpaie.Model;

import java.util.HashSet;
import java.util.Set;

public class fonctionItemRequest {
    private Long fonction_id;
    private Set<Long>items=new HashSet<>();

    public Long getFonction_id() {
        return this.fonction_id;
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
