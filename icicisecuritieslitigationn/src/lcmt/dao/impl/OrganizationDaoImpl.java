package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.OrganizationDao;
import lcmt.domain.Organization;


/*
 * Author: Mugdha Chandratre
 * Date: 19/02/2016
 * Updated By: Mahesh Kharote 
 * Updated Date: 30/03/2016
 * 
 * */

@Repository (value = "organizationDao")
@Transactional
public class OrganizationDaoImpl implements OrganizationDao {

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

	//Method Created By: Mugdha Chandratre
	//Method Purpose: Fetch List of all Organization from database
	// Update by mahesh kharote
	// Reason: Hide NA from organization list list
	@Override
	public <T> List<T> getAll(Class<T> clazz) {

		
		
		try {
			TypedQuery<T> query = em.createQuery(" from "+clazz.getName()+" where orga_id > 1 ", clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Mahesh Kharte
	//Method Purpose: Adding entity requires NA
	@Override
	public <T> List<T> getAllForAddingEntity(Class<T> clazz) {
		try {
			TypedQuery<T> query = em.createQuery(" from "+clazz.getName(), clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Method Created By: Mugdha Chandratre
	//Method Purpose: Fetch List of all Organization parent and Children from database
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getJoinedAll() {

		try {

			String sql = "SELECT org.orga_id AS 'child_orga_id', org.orga_name AS 'child_orga_name' , org1.orga_name AS 'parent_orga_name',org.orga_approval_status,org.orga_enable_status, org.orga_short_name FROM mst_organization org, mst_organization org1 WHERE org.orga_parent_id = org1.orga_id";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	//Method Created By: Mugdha Chandratre
	//Method Purpose: Fetch Organization by org Id
	@Override
	public Organization getOrganizationById(int orga_id) {

		try {
			String sql = "Select * from mst_organization where orga_id = "+orga_id;
			Query query = em.createNativeQuery(sql, Organization.class);

			if(!query.getResultList().isEmpty()){
				return (Organization) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	//Method Created By: Mugdha Chandratre
	//Method Purpose: Update Organization
	@Override
	public void updateOrganization(Organization organization) {
		try {
			em.merge(organization);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Method Created : Harshad Padole
	//Method purpose : Approve or disapprove Organization
	@Override
	public int approveDisapproveOrg(int org_id, int orga_status) {
		try {
			String sql = "Update mst_organization SET orga_approval_status='"+orga_status+"' where orga_id="+org_id;
			Query query = em.createNativeQuery(sql);
			int res = query.executeUpdate();
			return res;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	//Method Created : Harshad Padole
	//Method purpose :Enable or Disable Organization
	@Override
	public int enableDisableOrg(int org_id, int orga_status) {
		try {
			String sql = "Update mst_organization SET orga_enable_status='"+orga_status+"' where orga_id="+org_id;
			Query query = em.createNativeQuery(sql);
			int res = query.executeUpdate();
			return res;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int isOrgaNameExist(int orga_id, String orga_name) {
		try {
			
			String sql = "select count(*) as orgacount from mst_organization where orga_name='"+orga_name+"' "+" ";
			if(orga_id !=0){
				sql +=" AND orga_id !="+orga_id;
			}
			Query query = em.createNativeQuery(sql);
			String count = query.getResultList().get(0).toString();

			return Integer.parseInt(count);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int isOrgaShortNameExist(int orga_id, String orga_short_name) {
		try {
			String sql = "select count(*) as orgacount from mst_organization where orga_short_name='"+orga_short_name+"' "+" ";
			if(orga_id !=0){
				sql +=" AND orga_id !="+orga_id;
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
