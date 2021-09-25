/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author EXACTITUMNG
 */
public class PersonnelBean {
    private int idpersonnel;
    private String nom;
    private String prenom;
    private String sexe;
    private String tel;
    private String droits;
    private int serviceid;

    public PersonnelBean() {
    }

    public PersonnelBean(String nom, String prenom, String sexe, String tel, String droits, int serviceid) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.tel = tel;
        this.droits = droits;
        this.serviceid = serviceid;
    }

    public PersonnelBean(int idpersonnel, String nom, String prenom, String sexe, String tel, String droits, int serviceid) {
        this.idpersonnel = idpersonnel;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.tel = tel;
        this.droits = droits;
        this.serviceid = serviceid;
    }

    public int getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(int idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDroits() {
        return droits;
    }

    public void setDroits(String droits) {
        this.droits = droits;
    }

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }
    
    
}
