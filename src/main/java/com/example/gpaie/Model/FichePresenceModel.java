package com.example.gpaie.Model;

import java.time.LocalTime;

import com.example.gpaie.Entity.FichePresence;

public class FichePresenceModel {
    private Long id;
    private LocalTime heureDebut;
    private LocalTime heureFin;

    public FichePresenceModel() {
    }

    public FichePresenceModel(FichePresence item) {
        this.id=item.getId();
        this.heureDebut=item.getHeureDebut();
        this.heureFin=item.getHeureFin();
    }

    public LocalTime getHeureFin() {
        return this.heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getHeureDebut() {
        return this.heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }


}
