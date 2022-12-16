package lcmt.dao.impl;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.DepartmentDao;
import lcmt.domain.Department;

/*
 * Author: Mahesh Kharote
 * Date: 19/02/2016
 * Updated By: Mahesh Kharote
 * Updated Date: 19/02/2016
 * 
 * */


@Repository(value = "departmentDao")
@Transactional
public class DepartmentDaoImpl implements DepartmentDao {

	@PersistenceContext
	private EntityManager em;

	//Method Created By: Mahesh Kharote
	//Method Purpose: Add New Department to Database
	@Override
	public void persist(Object obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//Method Created By: Mahesh Kharote
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

	//Method Created By: Mahesh Kharote
	//Method Purpose: Get Department by id for edit from Database
	@Override
	public Department getDepartmentById(int dept_id) {
		try {
			String sql = "SELECT * FROM mst_department where dept_id = "+dept_id;
			Query query = em.createNativeQuery(sql,Department.class);
			if(!query.getResultList().isEmpty()){
				return (Department) query.getResultList().get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Mahesh Kharote
	//Method Purpose: Update particular department in Database
	@Override
	public void updateDepartment(Department department) {
		try {
			em.merge(department);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
 
	//Method Created : Harshad Padole
	//Method Purpose : get mapped Department 
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getMappedDepartments(int enti_orga_id, int enti_loca_id) {
		String sql = "SELECT enti_dept_id FROM cfg_entity_mapping WHERE enti_orga_id = " + enti_orga_id +" and enti_loca_id = "+enti_loca_id;
		Query query = em.createNativeQuery(sql);
		return query.getResultList();
	}
    
	
	//Method Created : Harshad Padole
		//Method Purpose : Department approve or disapprove
		@Override
		public int approveDisapproveDept(int dept_id, int dept_status) {
			// TODO Auto-generated method stub
			try {
				String sql = "Update mst_department SET dept_approval_status='"+dept_status+"' where dept_id="+dept_id;
				Query query = em.createNativeQuery(sql);
				return query.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}


		//Method Created : Harshad Padole
		//Method Purpose : Department enable or disable
		@Override
		public int enableDisableDept(int dept_id, int dept_status) {
			// TODO Auto-generated method stub
			try {
				String sql = "Update mst_department SET dept_enable_status='"+dept_status+"' where dept_id="+dept_id;
				Query query = em.createNativeQuery(sql);
				return query.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		
		//Method Created By: Mahesh Kharote(23/02/2016)
		//Method Purpose: Get mapped dept to the organization and location
		@SuppressWarnings("unchecked")
		@Override
		public <T> List<T> getAllDepartmentsForUserMapping(int user_orga_id, int user_loca_id) {
			try {
				String sql = "SELECT dep.dept_id,dep.dept_name FROM mst_department dep JOIN cfg_entity_mapping ent on ent.enti_dept_id = dep.dept_id WHERE ent.enti_orga_id = "+ user_orga_id +" and ent.enti_loca_id = "+user_loca_id;
				Query query = em.createNativeQuery(sql);
				return query.getResultList();
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}

		@Override
		public int isDeptNameExist(int dept_id, String dept_name) {
			try {
				String sql = "select count(*) as deptcount from mst_department where dept_name='"+dept_name+"' "+" ";
				if(dept_id !=0){
					sql +=" AND dept_id !="+dept_id;
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
		public int isDeptShortNameExist(int dept_id, String dept_short_name) {
			try {
				String sql = "select count(*) as deptcount from mst_department where dept_short_name='"+dept_short_name+"' "+" ";
				if(dept_id !=0){
					sql +=" AND dept_id !="+dept_id;
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

