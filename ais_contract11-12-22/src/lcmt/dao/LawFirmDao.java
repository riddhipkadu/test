package lcmt.dao;

import java.util.List;


import lcmt.domain.LawFirm;

public interface LawFirmDao {
	
	 public void persist(Object obj);
	 public  <T> List <T> getAllLawFirm(Class<T> clazz);
	 public LawFirm getLawFirmById(int lawf_id);
     public void updateLawFirm(LawFirm lawFirm);
     public int isLawFirmNameExist(int lawf_id, String lawf_name);
     public int deleteLawFirm(int lawf_id);
     public List<Object> checkLawFirmDependancy(int lawf_id);
     
}
