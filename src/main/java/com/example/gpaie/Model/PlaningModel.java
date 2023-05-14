package com.example.gpaie.Model;


import com.example.gpaie.Entity.Planinig;

public class PlaningModel {
    private Long id;
    private String date_debut;
    private String date_fin;
    private String heure_debut;
    private String heure_fin;
    private long user_id;
    private long departement_id;
    private long fonction_id;
    private long type_planing_id;
    private int type_planing;
    private String type_planing_string;
    private String typeFonction;
    private String user_name;
    private String repeat;

    public PlaningModel() {
        
    }

    public PlaningModel(Planinig item) {
        this.id=item.getId();
        this.date_debut=item.getDateDebut().toString();
        this.date_fin=item.getDateFin().toString();
        if(item.getHeureDebut()!=null){
        this.heure_debut=item.getHeureDebut().toString();
        this.heure_fin=item.getHeureFin().toString();
        }
        
        this.user_id=item.getUser().getId();
        this.departement_id=item.getUser().getDepartement().getId();
        this.fonction_id=item.getFonction().getId();
        this.typeFonction=item.getFonction().getTypeFonction();
        this.user_name=item.getUser().getNom() +" "+ item.getUser().getPrenom();
        this.type_planing_string=makeType(item.getType_planing());
        this.type_planing=item.getType_planing();
        
    }

    public int getType_planing() {
        return this.type_planing;
    }

    public void setType_planing(int type_planing) {
        this.type_planing = type_planing;
    }

    public String getType_planing_string() {
        return this.type_planing_string;
    }

    public void setType_planing_string(String type_planing_string) {
        this.type_planing_string = type_planing_string;
    }

    public long getDepartement_id() {
        return this.departement_id;
    }

    public void setDepartement_id(long departement_id) {
        this.departement_id = departement_id;
    }

    public String getRepeat() {
        return this.repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getType_planing_id() {
        return this.type_planing_id;
    }

    public void setType_planing_id(long type_planing_id) {
        this.type_planing_id = type_planing_id;
    }

    
    public long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getFonction_id() {
        return this.fonction_id;
    }

    public void setFonction_id(long fonction_id) {
        this.fonction_id = fonction_id;
    }

    public String getTypeFonction() {
        return this.typeFonction;
    }

    public void setTypeFonction(String typeFonction) {
        this.typeFonction = typeFonction;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDate_debut() {
        return this.date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return this.date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getHeure_debut() {
        return this.heure_debut;
    }

    public void setHeure_debut(String heure_debut) {
        this.heure_debut = heure_debut;
    }

    public String getHeure_fin() {
        return this.heure_fin;
    }

    public void setHeure_fin(String heure_fin) {
        this.heure_fin = heure_fin;
    }
    private String makeType(int typeplaning){
        switch(typeplaning){
            case 0:
            return "Temps plein";
          
            case 1:
            return "Mi-temps";
          
            case 2:
            return "Etudiant";
        
            default:
            return "";
        }
         
    }
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", date_debut='" + getDate_debut() + "'" +
            ", date_fin='" + getDate_fin() + "'" +
            ", heure_debut='" + getHeure_debut() + "'" +
            ", heure_fin='" + getHeure_fin() + "'" +
            ", user_id='" + getUser_id() + "'" +
            ", fonction_id='" + getFonction_id() + "'" +
            ", type_planing_id='" + getType_planing_id() + "'" +
            ", typeFonction='" + getTypeFonction() + "'" +
            ", user_name='" + getUser_name() + "'" +
            ", repeat='" + getRepeat() + "'" +
            "}";
    }


}
