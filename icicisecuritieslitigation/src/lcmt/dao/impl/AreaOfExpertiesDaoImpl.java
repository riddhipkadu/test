package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lcmt.dao.AreaOfExpertiseDao;
import lcmt.domain.AreaOfExpertise;

/*
 * Author: Harshad Padole
 * Date: 23/08/2016
 * Updated By: 
 * Updated Date: 
 * 
 * */

@Repository(value ="AreaOfExpertise")
@Transactional
public class AreaOfExpertiesDaoImpl implements AreaOfExpertiseDao {

	@PersistenceContext
	private EntityManager em;
	
	//Method Created By: Tejashri Zurunge
	//Method Purpose: Add New Area of Expertise	
	@Override
	public void persist(Object obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}

	
	//Method Created By: Tejashri Zurunge
	//Method Purpose: Get All Area of Expertise	
	@Override
	public <T> List<T> getAllAreaOfExpertise(Class<T> clazz) {
		try {
			TypedQuery<T> query = em.createQuery(" from "+clazz.getName(), clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
		//Method Created By: Tejashri Zurunge
		//Method Purpose: get the all values by id
			
	public AreaOfExpertise getAreaOfExpertiseById(int area_expe_id) {
		try {
			String sql = "SELECT * FROM mst_area_of_expertise where area_expe_id = "+area_expe_id;
			Query query = em.createNativeQuery(sql,AreaOfExpertise.class);
			if(!query.getResultList().isEmpty()){
				return (AreaOfExpertise) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
		//Method created by : Tejashri Zurunge
		//Method Purpose : Update Area Of Expertise 
		
		public void updateAreaOfExpertise(AreaOfExpertise areaOfExpertise) {
			try {
				em.merge(areaOfExpertise);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		//Method Created : Tejashri Zurunge
		//Method purpose : Enable or disable Area Of Expertise
				@Override
		public int enableDisableAreaOfExpertise(int area_expe_id, int area_expe_status) {
					try {
						String sql = "Update mst_area_of_expertise SET area_expe_enable_status='"+area_expe_status+"' where area_expe_id="+area_expe_id;
						Query query = em.createNativeQuery(sql);
						int res = query.executeUpdate();
						return res;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					return 0;
				}
	
	//Method Created By: Tejashri Zurunge
	//Method Purpose: To Verify Whether Area Of Expertise is exist or not
		
	public int isAreaExpeNameExist(int area_expe_id, String area_expe_name) {
		try {
			String sql = "select count(*) as aoecount from mst_area_of_expertise where area_expe_name='"+area_expe_name+"' "+" ";
			if(area_expe_id !=0){
				sql +=" AND area_expe_id !="+area_expe_id;
			}
			Query query = em.createNativeQuery(sql);
			String count = query.getResultList().get(0).toString();
			return Integer.parseInt(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	//Method Created : Tejashri Zurunge
	//Method purpose : Delete Area Of Expertise
	
	@Override
	public int deleteAreaOfExpertise(int area_expe_id) {
		try {
			String sql = "DELETE FROM mst_area_of_expertise WHERE area_expe_id = "+ area_expe_id;
			Query query = em.createNativeQuery(sql);
            int deleteCount = query.executeUpdate();
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
