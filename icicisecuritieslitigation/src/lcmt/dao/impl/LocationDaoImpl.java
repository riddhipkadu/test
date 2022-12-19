package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.LocationDao;
import lcmt.domain.Location;

/*
 * Author: Mugdha Chandratre 
 * Date: 19/02/2016
 * Updated By: Mugdha Chandratre
 * Updated Date: 19/02/2016
 * 
 * */
@Repository(value = "locationDao")
@Transactional
public class LocationDaoImpl implements LocationDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void persist(Object obj) {
		
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//Method created by : Mugdha Chadratre
	//Method Purpose : Get list all Location 
	@Override
	public <T> List<T> getAll(Class<T> clazz) {
		try {
			TypedQuery<T> query = em.createQuery(" from "+clazz.getName(), clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> List<T> getJoinedAll() {
		// TODO Auto-generated method stub
		return null;
	}
    
	//Method created by : Mugdha Chadratre
	//Method Purpose : Get Location by Id 
	@Override
	public Location getLocationById(int loca_id) {
		try {
			String sql = "SELECT * FROM mst_location where loca_id = "+loca_id;
			Query query = em.createNativeQuery(sql,Location.class);
			if(!query.getResultList().isEmpty()){
				return (Location) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method created by : Mugdha Chadratre
	//Method Purpose : Update Location 
	@Override
	public void updateLocation(Location location) {
		try {
			em.merge(location);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
    
	//Method created by : Mugdha Chadratre
	//Method Purpose : Get all location for organization by org_id
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getAllLocationsForOrganization(int orga_id) {
		try {
			String sql = "SELECT DISTINCT loc.loca_id,loc.loca_name FROM mst_location loc JOIN cfg_entity_mapping ent on ent.enti_loca_id = loc.loca_id WHERE ent.enti_orga_id = "+orga_id;
			Query query = em.createNativeQuery(sql);
			
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Method Created : Harshad Padole
		//Method purpose : Approve or disapprove location
		@Override
		public int approveDisapproveLoc(int loc_id, int loca_status) {
			try {
				String sql = "UPDATE mst_location SET loca_approval_status='"+loca_status+"' where loca_id="+loc_id;
				Query query = em.createNativeQuery(sql);
				int res = query.executeUpdate();
				return res;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}

		//Method Created : Harshad Padole
		//Method purpose : Enable or disable location
		@Override
		public int enableDisableLoc(int loc_id, int loca_status) {
			try {
				String sql = "Update mst_location SET loca_enable_status='"+loca_status+"' where loca_id="+loc_id;
				Query query = em.createNativeQuery(sql);
				int res = query.executeUpdate();
				return res;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}

		@Override
		public int isLocaNameExist(int loca_id, String loca_name) {
			try {
				String sql = "select count(*) as locacount from mst_location where loca_name='"+loca_name+"' "+" ";
				if(loca_id !=0){
					sql +=" AND loca_id !="+loca_id;
				}
				Query query = em.createNativeQuery(sql);
				String count = query.getResultList().get(0).toString();
				return Integer.parseInt(count);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}

		@Override
		public int islocaShortNameExist(int loca_id, String loca_short_name) {
			try {
				String sql = "select count(*) as locacount from mst_location where loca_short_name='"+loca_short_name+"' "+" ";
				if(loca_id !=0){
					sql +=" AND loca_id !="+loca_id;
				}
				Query query = em.createNativeQuery(sql);
				String count = query.getResultList().get(0).toString();
				return Integer.parseInt(count);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
   
	
}
