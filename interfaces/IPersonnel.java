/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import bean.PersonnelBean;
import java.util.List;

/**
 *
 * @author EXACTITUMNG
 */
public interface IPersonnel {
   public int addPersonnel(PersonnelBean pb);
   public int editPersonnel(PersonnelBean pb);
   public boolean isExist(String tel);
   public List<PersonnelBean> listepersonnel();
   public PersonnelBean getPersonnelById(int id);
   public PersonnelBean getPersonnelByTel(String tel);
   public PersonnelBean getPersonnelByNomOrPre(PersonnelBean pb);
   public PersonnelBean getPersonnelByService(String service);
   public PersonnelBean getPersonnelByNomAndPre(PersonnelBean pb);
   public PersonnelBean login(PersonnelBean pb);
   public PersonnelBean changePass(PersonnelBean pb);
   public PersonnelBean changeTel(PersonnelBean pb);
}
