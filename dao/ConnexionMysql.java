/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author EXACTITUMNG
 */

public class ConnexionMysql {
    private static String username = "root";
    private static String password = "";
    private static String db_host = "127.0.0.1";
    private static String db_name = "presence";
    private static String server_port = "3306";
    private static String conn_string = "jdbc:mysql://"+db_host+":"+server_port+"/"+db_name;;
    private static Connection conn = null;
    
    static{
        try {
            conn = DriverManager.getConnection(conn_string, username, password);
            if(conn!=null){
//                JOptionPane.showMessageDialog(null, "Connexion réussie","Connexion réussie",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Connexion échouée",JOptionPane.ERROR_MESSAGE);
        }
    };

    public static Connection getConn() {
        return conn;
    }
}
