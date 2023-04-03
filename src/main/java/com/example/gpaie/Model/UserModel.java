package com.example.gpaie.Model;

import java.io.Serializable;

import com.example.gpaie.Entity.User;

public class UserModel implements Serializable{
    private Long id;
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
    private String departement;

    public UserModel(User user) {
        this.id = user.getId();
        this.firstname=user.getPrenom();
        this.phone=user.getTelephone();
        this.email = user.getEmail();
        this.matricule = user.getMatricule();
        this.genre = user.getGenre();
        this.etatCivil = user.getEtatCivil();
        this.compteIban = user.getCompteIban();
        this.role = user.getAuthority().getAuthority();
        this.username = user.getUsername();
        this.departement=user.getDepartement().getNomDepartement();
        this.departement_id=user.getDepartement().getId();
    }


    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public long getDepartement_id() {
        return this.departement_id;
    }

    public void setDepartement_id(long departement_id) {
        this.departement_id = departement_id;
    }

    public String getDepartement() {
        return this.departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
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

}
