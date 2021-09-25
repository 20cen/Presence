/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import bean.SerBean;
import java.util.List;

/**
 *
 * @author EXACTITUMNG
 */
public interface IService {
   public int addService(SerBean sb);
   public int editService(SerBean sb);
   public boolean isExist(String labelservice);
   public List<SerBean> listeservice();
   public SerBean getServiceById(int id);
   public SerBean getServiceByLabel(String label);
   public SerBean getServiceByCode(String code);
}
