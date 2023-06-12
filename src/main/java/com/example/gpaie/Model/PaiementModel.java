package com.example.gpaie.Model;

import com.example.gpaie.Entity.Paiement;

public class PaiementModel {
    private Long id;
    private String datePaie;
    private String dateDebut;
    private String dateFin;
    private String dateCreation;
    private double total_jour;
    private int month;
    private int year;
    private String code_paiement;
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
    private long user_id;
    private String user_name;
    private String user_matricule;
    private double totalHeureSuppl;
    private long createby;
    public PaiementModel() {
    }

    public PaiementModel(Paiement item) {
        this.id=item.getId();
        this.totalHeureSuppl=item.getTotalHeureSuppl();
        this.total_net=item.getTotal_net();
        this.total_impossable=item.getTotal_impossable();
        this.total_prime=item.getTotal_prime();
        this.total_brut=item.getTotal_brut();
        this.prime_equipe=item.getPrime_equipe();
        this.precomptePro=item.getPrecomptePro();
        this.prime_prestation=item.getPrime_prestation();
        this.prime_HS=item.getPrime_HS();
        this.suppl_transport=item.getSuppl_transport();
        this.cnss_retenue=item.getCnss_retenue();
        this.retenu_total=item.getRetenu_total();
        this.retenu_chomage=item.getRetenu_chomage();
        this.retenu_retraite=item.getRetenu_retraite();
        this.cotisationCnss=item.getCotisationCnss();
        this.code_paiement=item.getCode_paiement();
        this.month=item.getMonth();
        this.year=item.getYear();
        this.total_jour=item.getTotal_heure();
        this.dateCreation=item.getCreatedAt()==null?null:item.getCreatedAt().toString();
        this.datePaie=item.getDatePaie()==null?null:item.getDatePaie().toString();
        this.user_name=item.getUser().getNom()+' '+item.getUser().getPrenom();
        this.user_matricule=item.getUser().getMatricule();
    }

    public String getDateCreation() {
        return this.dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getTotalHeureSuppl() {
        return this.totalHeureSuppl;
    }

    public void setTotalHeureSuppl(double totalHeureSuppl) {
        this.totalHeureSuppl = totalHeureSuppl;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_matricule() {
        return this.user_matricule;
    }

    public void setUser_matricule(String user_matricule) {
        this.user_matricule = user_matricule;
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

    public String getDatePaie() {
        return this.datePaie;
    }

    public void setDatePaie(String datePaie) {
        this.datePaie = datePaie;
    }

    public double getTotal_jour() {
        return this.total_jour;
    }

    public void setTotal_jour(double total_jour) {
        this.total_jour = total_jour;
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

    public long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getCreateby() {
        return this.createby;
    }

    public void setCreateby(long createby) {
        this.createby = createby;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
