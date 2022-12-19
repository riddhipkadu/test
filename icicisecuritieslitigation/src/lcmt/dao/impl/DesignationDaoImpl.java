package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.DesignationDao;
import lcmt.domain.Designation;

/*
 * Author: Mahesh Kharote
 * Date: 19/02/2016
 * Updated By: Harshad Padole
 * Updated Date: 19/02/2016
 * 
 * */

@Repository(value = "designationDao")
@Transactional
public class DesignationDaoImpl implements DesignationDao {

	@PersistenceContext
	private EntityManager em;


	//Method Created By: Harshad Padole
	//Method Purpose: Add New Designation to Database
	@Override
	public void persist(Object obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//Method Created By: Harshad Padole
	//Method Purpose: Fetch List of all departments from database
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

	//Method Created By: Harshad Padole
	//Method Purpose: Fetch List of all departments with its parent from database
	@Override
	public <T> List<T> getJoinedAll() {
		return null;
	}

	//Method Created By: Harshad Padole
	//Method Purpose: Fetch Designation by desi Id
	@Override
	public Designation getDesignationById(int desi_id) {

		try {
			String sql = "SELECT * FROM mst_designation where desi_id = "+desi_id;
			Query query = em.createNativeQuery(sql,Designation.class);
			if(!query.getResultList().isEmpty()){
				return (Designation) query.getResultList().get(0);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	//Method Created By: Harshad Padole
	//Method Purpose: Update Designation
	@Override
	public void updateDesignation(Designation designation) {
		try {
			em.merge(designation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Method Created : Harsahad Padole
	//Method Purpose : Approve or disapprove Designation
	@Override
	public int approveDisapproveDesi(int desi_id, int desi_status) {
		try {
			String sql = "Update mst_designation SET desi_approval_status='"+desi_status+"' where desi_id="+desi_id;
			Query query = em.createNativeQuery(sql);
			return query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	//Method Created : Harsahad Padole
	//Method Purpose : Enable or disable Designation
	@Override
	public int enableDisableDesi(int desi_id, int desi_status) {
		try {
			String sql = "Update mst_designation SET desi_enable_status='"+desi_status+"' where desi_id="+desi_id;
			Query query = em.createNativeQuery(sql);
			return query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int isDesiNameExist(int desi_id, String desi_name) {
		try {
			String sql = "select count(*) as desicount from mst_designation where desi_name='"+desi_name+"' "+" ";
			if(desi_id !=0){
				sql +=" AND desi_id !="+desi_id;
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
