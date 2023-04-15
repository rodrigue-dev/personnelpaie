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
    private double total_heure;
    private int totalJours;
    private int month;
    private int year;
    private String code_paiement;
    private double totalHeureSuppl;
    private double precomptePro;
    private double cotisationCnss;
    private double retenu_retraite;
    private double retenu_chomage;
    private double retenu_total;
    private double cnss_retenue;
    private double suppl_transport;
    private double prime_HS;
    private double prime_prestation;
    private double prime_equipe;
    private double total_brut;
    private double total_prime;
    private double total_impossable;
    private double total_net;
    @ManyToOne
    private User user;
    @ManyToOne
    private User createby;

    public Long getId() {
        return this.id;
    }

    public int getTotalJours() {
        return this.totalJours;
    }

    public void setTotalJours(int totalJours) {
        this.totalJours = totalJours;
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

    public double getRetenu_total() {
        return this.retenu_total;
    }

    public void setRetenu_total(double retenu_total) {
        this.retenu_total = retenu_total;
    }

    public double getCnss_retenue() {
        return this.cnss_retenue;
    }

    public void setCnss_retenue(double cnss_retenue) {
        this.cnss_retenue = cnss_retenue;
    }

    public double getSuppl_transport() {
        return this.suppl_transport;
    }

    public void setSuppl_transport(double suppl_transport) {
        this.suppl_transport = suppl_transport;
    }

    public double getPrime_HS() {
        return this.prime_HS;
    }

    public void setPrime_HS(double prime_HS) {
        this.prime_HS = prime_HS;
    }

    public double getPrime_prestation() {
        return this.prime_prestation;
    }

    public void setPrime_prestation(double prime_prestation) {
        this.prime_prestation = prime_prestation;
    }

    public double getPrime_equipe() {
        return this.prime_equipe;
    }

    public void setPrime_equipe(double prime_equipe) {
        this.prime_equipe = prime_equipe;
    }

    public double getTotal_brut() {
        return this.total_brut;
    }

    public void setTotal_brut(double total_brut) {
        this.total_brut = total_brut;
    }

    public double getTotal_prime() {
        return this.total_prime;
    }

    public void setTotal_prime(double total_prime) {
        this.total_prime = total_prime;
    }

    public double getTotal_impossable() {
        return this.total_impossable;
    }

    public void setTotal_impossable(double total_impossable) {
        this.total_impossable = total_impossable;
    }

    public double getTotal_net() {
        return this.total_net;
    }

    public void setTotal_net(double total_net) {
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


    public double getTotal_heure() {
        return this.total_heure;
    }

    public void setTotal_heure(double total_heure) {
        this.total_heure = total_heure;
    }

    public double getTotalHeureSuppl() {
        return this.totalHeureSuppl;
    }

    public void setTotalHeureSuppl(double totalHeureSuppl) {
        this.totalHeureSuppl = totalHeureSuppl;
    }

    public double getPrecomptePro() {
        return this.precomptePro;
    }

    public void setPrecomptePro(double precomptePro) {
        this.precomptePro = precomptePro;
    }

    public double getCotisationCnss() {
        return this.cotisationCnss;
    }

    public void setCotisationCnss(double cotisationCnss) {
        this.cotisationCnss = cotisationCnss;
    }

    public double getRetenu_retraite() {
        return this.retenu_retraite;
    }

    public void setRetenu_retraite(double retenu_retraite) {
        this.retenu_retraite = retenu_retraite;
    }

    public double getRetenu_chomage() {
        return this.retenu_chomage;
    }

    public void setRetenu_chomage(double retenu_chomage) {
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
