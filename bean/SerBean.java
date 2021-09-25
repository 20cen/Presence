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
public class SerBean {
    private int idservice;
    private String labelservice;
    private String codeservice;

    public SerBean() {
    }

    public SerBean(String labelservice, String codeservice) {
        this.labelservice = labelservice;
        this.codeservice = codeservice;
    }

    public SerBean(int idservice, String labelservice, String codeservice) {
        this.idservice = idservice;
        this.labelservice = labelservice;
        this.codeservice = codeservice;
    }

    public int getIdservice() {
        return idservice;
    }

    public void setIdservice(int idservice) {
        this.idservice = idservice;
    }

    public String getLabelservice() {
        return labelservice;
    }

    public void setLabelservice(String labelservice) {
        this.labelservice = labelservice;
    }

    public String getCodeservice() {
        return codeservice;
    }

    public void setCodeservice(String codeservice) {
        this.codeservice = codeservice;
    }
    
    
}
