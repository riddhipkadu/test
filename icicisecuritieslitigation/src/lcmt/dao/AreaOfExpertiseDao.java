package lcmt.dao;

import java.util.List;

import lcmt.domain.AreaOfExpertise;

public interface AreaOfExpertiseDao {
      public void persist(Object obj);
      public  <T> List <T> getAllAreaOfExpertise(Class<T> clazz);
      public AreaOfExpertise getAreaOfExpertiseById(int area_expe_id);
      public void updateAreaOfExpertise(AreaOfExpertise areaOfExpertise);
      public int enableDisableAreaOfExpertise(int area_expe_id , int area_expe_status);
      public int isAreaExpeNameExist(int area_expe_id, String area_expe_name);
      public int deleteAreaOfExpertise(int area_expe_id);
}
