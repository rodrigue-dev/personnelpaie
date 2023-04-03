package com.example.gpaie.Entity;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    private LocalDate datePaie;
    private int total_jour;
    private int month;
    private int year;
    private String code_paiement;
    private float precomptePro;
    private float cotisationCnss;
    private float retenu_retraite;
    private float retenu_chomage;
    private float retenu_total;
    private float cnss_retenue;
    private float suppl_transport;
    private float prime_HS;
    private float prime_prestation;
    private float prime_equipe;
    private float total_brut;
    private float total_prime;
    private float total_impossable;
    private float total_net;
    @ManyToOne
    private User user;
    @ManyToOne
    private User createby;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCode_paiement() {
        return this.code_paiement;
    }

    public void setCode_paiement(String code_paiement) {
        this.code_paiement = code_paiement;
    }

    public float getRetenu_total() {
        return this.retenu_total;
    }

    public void setRetenu_total(float retenu_total) {
        this.retenu_total = retenu_total;
    }

    public float getCnss_retenue() {
        return this.cnss_retenue;
    }

    public void setCnss_retenue(float cnss_retenue) {
        this.cnss_retenue = cnss_retenue;
    }

    public float getSuppl_transport() {
        return this.suppl_transport;
    }

    public void setSuppl_transport(float suppl_transport) {
        this.suppl_transport = suppl_transport;
    }

    public float getPrime_HS() {
        return this.prime_HS;
    }

    public void setPrime_HS(float prime_HS) {
        this.prime_HS = prime_HS;
    }

    public float getPrime_prestation() {
        return this.prime_prestation;
    }

    public void setPrime_prestation(float prime_prestation) {
        this.prime_prestation = prime_prestation;
    }

    public float getPrime_equipe() {
        return this.prime_equipe;
    }

    public void setPrime_equipe(float prime_equipe) {
        this.prime_equipe = prime_equipe;
    }

    public float getTotal_brut() {
        return this.total_brut;
    }

    public void setTotal_brut(float total_brut) {
        this.total_brut = total_brut;
    }

    public float getTotal_prime() {
        return this.total_prime;
    }

    public void setTotal_prime(float total_prime) {
        this.total_prime = total_prime;
    }

    public float getTotal_impossable() {
        return this.total_impossable;
    }

    public void setTotal_impossable(float total_impossable) {
        this.total_impossable = total_impossable;
    }

    public float getTotal_net() {
        return this.total_net;
    }

    public void setTotal_net(float total_net) {
        this.total_net = total_net;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return this.modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public LocalDate getDatePaie() {
        return this.datePaie;
    }

    public void setDatePaie(LocalDate datePaie) {
        this.datePaie = datePaie;
    }

    public int getTotal_jour() {
        return this.total_jour;
    }

    public void setTotal_jour(int total_jour) {
        this.total_jour = total_jour;
    }

    public float getPrecomptePro() {
        return this.precomptePro;
    }

    public void setPrecomptePro(float precomptePro) {
        this.precomptePro = precomptePro;
    }

    public float getCotisationCnss() {
        return this.cotisationCnss;
    }

    public void setCotisationCnss(float cotisationCnss) {
        this.cotisationCnss = cotisationCnss;
    }

    public float getRetenu_retraite() {
        return this.retenu_retraite;
    }

    public void setRetenu_retraite(float retenu_retraite) {
        this.retenu_retraite = retenu_retraite;
    }

    public float getRetenu_chomage() {
        return this.retenu_chomage;
    }

    public void setRetenu_chomage(float retenu_chomage) {
        this.retenu_chomage = retenu_chomage;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getCreateby() {
        return this.createby;
    }

    public void setCreateby(User createby) {
        this.createby = createby;
    }

}
