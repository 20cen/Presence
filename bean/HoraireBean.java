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
public class HoraireBean {
    private int idhoraire;
    private String horaire;
    private int personnelid;

    public HoraireBean() {
    }

    public HoraireBean(String horaire, int personnelid) {
        this.horaire = horaire;
        this.personnelid = personnelid;
    }

    public HoraireBean(int idhoraire, String horaire, int personnelid) {
        this.idhoraire = idhoraire;
        this.horaire = horaire;
        this.personnelid = personnelid;
    }

    public int getIdhoraire() {
        return idhoraire;
    }

    public void setIdhoraire(int idhoraire) {
        this.idhoraire = idhoraire;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public int getPersonnelid() {
        return personnelid;
    }

    public void setPersonnelid(int personnelid) {
        this.personnelid = personnelid;
    }
    
    
}
