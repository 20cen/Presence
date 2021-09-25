/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presence;

import bean.SerBean;
import dao.ConnexionMysql;
import entity.ServiceEntity;

/**
 *
 * @author EXACTITUMNG
 */
public class Presence {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServiceEntity se = new ServiceEntity();
        
//        Ajouter
//        SerBean sb = new SerBean("FINANCE", "F01");
//        System.out.println(se.addService(sb));
        
//        Afficher
//        SerBean sb = new SerBean();
//        System.out.println(se.listeservice().get(0).getLabelservice());
        
        //Modifier
//        SerBean sb = new SerBean(3, "Entretient", "E03");
//        System.out.println(se.editService(sb));
    }
    
}
