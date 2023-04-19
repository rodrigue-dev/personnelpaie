package com.example.gpaie.Model;

import com.example.gpaie.Entity.Personnel;

public class PersonnelModel {
    private Long id;
    private double salaireFixe;
    private String password;
    private String username;
    private String firstname;
    private String lastname;
    private String phone;
    private String matricule;
    private String genre;
    private String etatCivil;
    private String compteIban;
    private String email;
    private String role;
    private long departement_id;
    private long user_id;
    private long fonction_id;
    private String fonction;
    private String departement;

    public PersonnelModel() {
    }

    public PersonnelModel(Personnel personnel) {
        this.id = personnel.getId();
        this.firstname=personnel.getUser().getNom();
        this.lastname=personnel.getUser().getPrenom();
        this.phone=personnel.getUser().getTelephone();
        this.email = personnel.getUser().getEmail();
        this.matricule = personnel.getUser().getMatricule();
        this.genre = personnel.getUser().getGenre();
        this.etatCivil = personnel.getUser().getEtatCivil();
        this.compteIban = personnel.getUser().getCompteIban();
        this.role = personnel.getUser().getAuthority().getAuthority();
        this.username = personnel.getUser().getUsername();
        this.departement=personnel.getUser().getDepartement().getNomDepartement();
        this.departement_id=personnel.getUser().getDepartement().getId();
        this.user_id=personnel.getUser().getId();
        this.fonction_id=personnel.getFonction().getId();
        this.fonction=personnel.getFonction().getTypeFonction();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSalaireFixe() {
        return this.salaireFixe;
    }

    public void setSalaireFixe(double salaireFixe) {
        this.salaireFixe = salaireFixe;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMatricule() {
        return this.matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEtatCivil() {
        return this.etatCivil;
    }

    public void setEtatCivil(String etatCivil) {
        this.etatCivil = etatCivil;
    }

    public String getCompteIban() {
        return this.compteIban;
    }

    public void setCompteIban(String compteIban) {
        this.compteIban = compteIban;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public long getFonction_id() {
        return this.fonction_id;
    }

    public void setFonction_id(long fonction_id) {
        this.fonction_id = fonction_id;
    }

    public String getFonction() {
        return this.fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getDepartement() {
        return this.departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

}
