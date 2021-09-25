/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import bean.SerBean;
import dao.ConnexionMysql;
import interfaces.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author EXACTITUMNG
 */
public class ServiceEntity extends ConnexionMysql implements IService{
    PreparedStatement pstm = null;
    Statement stm = null;
    ResultSet rs = null;
    Connection conn = null;
    
    @Override
    public int addService(SerBean sb) {
        if (isExist(sb.getLabelservice())) {
            JOptionPane.showMessageDialog(null, "Ce service existe déjà", "Alert doublon", 2);
        } else {
            try {
            conn = getConn();
            String sql = "insert into en_service set labelservice = ?, codeservice = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, sb.getLabelservice());
            pstm.setString(2, sb.getCodeservice());
            return pstm.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Message SQL", 0);
        }
        }
        return 0;
    }

    @Override
    public int editService(SerBean sb) {
        try {
            conn = getConn();
            String sql = "update en_service set labelservice = ?, codeservice = ? where idservice = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, sb.getLabelservice());
            pstm.setString(2, sb.getCodeservice());
            pstm.setInt(3, sb.getIdservice());
            return pstm.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Message SQL", 0);
        }
        return 0;
    }

    @Override
    public boolean isExist(String labelservice) {
        try {
            conn = getConn();
            String sql = "select * from en_service where labelservice = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, labelservice);
            rs = pstm.executeQuery();
            rs.last();
            if (rs.getRow()>0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Message SQL", 0);
        }
        return false;
    }

    @Override
    public List<SerBean> listeservice() {
        List<SerBean> liste = new ArrayList<>();
        try {
            conn = getConn();
            stm = conn.createStatement();
            String sql = "select * from en_service";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                liste.add(new SerBean(rs.getInt("idservice"), rs.getString("labelservice"), rs.getString("codeservice")));                
            }
            return liste;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Message SQL", 0);
        }
        return liste;
    }

    @Override
    public SerBean getServiceById(int id) {
        try {
            conn = getConn();
            String sql = "select * from en_service where idservice = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.last();
            if (rs.getRow()>0) {
                return new SerBean(rs.getInt("idservice"), rs.getString("labelservice"), rs.getString("codeservice"));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SerBean getServiceByLabel(String label) {
        try {
            conn = getConn();
            String sql = "select * from en_service where labelservice = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, label);
            rs = pstm.executeQuery();
            rs.last();
            if (rs.getRow()>0) {
                return new SerBean(rs.getInt("idservice"), rs.getString("labelservice"), rs.getString("codeservice"));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SerBean getServiceByCode(String code) {
        try {
            conn = getConn();
            String sql = "select * from en_service where codeservice = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            rs.last();
            if (rs.getRow()>0) {
                return new SerBean(rs.getInt("idservice"), rs.getString("labelservice"), rs.getString("codeservice"));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
