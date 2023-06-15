package com.example.gpaie.Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    private String imageFile;
    private String email;
    private String role;
    private String dateDebut;
    private String dateFin;
    private long departement_id;
    private long fonction_id;
    private String departement;
    private String fonction;
    private byte[] image;
    private Integer typeplaning;
    private Set<Integer> dayworks= new HashSet<>();
    public UserModel() {
    }

    public UserModel(User user) {
        this.id = user.getId();
        this.firstname=user.getNom();
        this.lastname=user.getPrenom();
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
        this.fonction_id=user.getFonction().getId();
        this.fonction=user.getFonction().getTypeFonction();
        this.typeplaning=user.getTypeplaning();
        this.dayworks=user.getDayworks();
        this.dateDebut=user.getCreatedAt()==null?null:user.getCreatedAt().toString();
        this.dateFin=user.getModifiedAt()==null?null:user.getModifiedAt().toString();
        String fileDownloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/v1/files/")
          .path(user.getId().toString())
          .toUriString();
          this.imageFile=fileDownloadUri;
        this.image=user.getImage();
    }


    public String getDateDebut() {
        return this.dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }



    public Set<Integer> getDayworks() {
        return this.dayworks;
    }

    public void setDayworks(Set<Integer> dayworks) {
        this.dayworks = dayworks;
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


    public Integer getTypeplaning() {
        return this.typeplaning;
    }

    public void setTypeplaning(Integer typeplaning) {
        this.typeplaning = typeplaning;
    }

    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageFile() {
        return this.imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
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
