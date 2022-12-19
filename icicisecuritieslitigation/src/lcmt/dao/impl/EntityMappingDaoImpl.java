package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.EntityMappingDao;
import lcmt.domain.Cities;
import lcmt.domain.Countries;
import lcmt.domain.EntityMapping;
import lcmt.domain.States;

/*
 * Author: Mahesh Kharote
 * Date: 19/02/2016
 * Updated By: Mugdha Chandratre
 * Updated Date: 23/02/2016
 * 
 * */

@Repository( value = "entityMappingDao")
@Transactional
public class EntityMappingDaoImpl implements EntityMappingDao {

	@PersistenceContext
	private EntityManager em;

	//Method Created By: Mugdha Chandratre
	//Method Purpose: Add New Entity Mapping to Database
	@Override
	public void persist(Object obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	//Method Created By: Mugdha Chandratre
	//Method Purpose: Get list of all Entity Mapping
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
	
	
	//Method Created By: Mugdha Chandratre
	//Method Purpose: Fetch List of all joined Entity Mapping from database
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getJoinedAll() {
		try {
			String sql = "SELECT ent.enti_id, org.orga_name, loc.loca_name, dep.dept_name,ent.enti_approval_status,ent.enti_enable_status FROM cfg_entity_mapping ent JOIN mst_organization org on ent.enti_orga_id = org.orga_id JOIN mst_location loc on ent.enti_loca_id = loc.loca_id JOIN mst_department dep on ent.enti_dept_id = dep.dept_id";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Mugdha Chandratre
	//Method Purpose: Fetch Entity Mapping by id from database
	@Override
	public EntityMapping getEntitiesMappingById(int enti_id) {
		try {
			String sql = "SELECT * FROM cfg_entity_mapping where enti_id = "+enti_id;
			Query query = em.createNativeQuery(sql,EntityMapping.class);
			if(!query.getResultList().isEmpty()){
				return  (EntityMapping) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Mugdha Chandratre
	//Method Purpose: Update Entity Mapping
	@Override
	public void updateEntitiesMapping(EntityMapping entityMapping) {
		try {
			em.merge(entityMapping);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/*//Method Created By: Mugdha Chandratre
	//Method Purpose: Delete Entity Mapping
	@Override
	public void deleteEntitiesMapping(int enti_orga_id, int enti_loca_id) {
		try {
			String sql = "delete from cfg_entity_mapping where enti_orga_id = "+enti_orga_id+" and enti_loca_id = "+enti_loca_id +" and enti_dept_id > 0 ";
			Query query = em.createNativeQuery(sql);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	
	//Method Created : Harshad Padole
	//Method Purpose : Approve or disapprove entity
	@Override
	public int approveDisapproveEntity(int enti_id, int enti_status) {
		try {
			String sql = "Update cfg_entity_mapping SET enti_approval_status='"+enti_status+"' where enti_id="+enti_id;
			Query query = em.createNativeQuery(sql);
			return query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	//Method Created : Harshad Padole
	//Method Purpose : enable or disable entity
	@Override
	public int enableDisableEntity(int enti_id, int enti_status) {
		try {
			String sql = "Update cfg_entity_mapping SET enti_enable_status='"+enti_status+"' where enti_id="+enti_id;
			Query query = em.createNativeQuery(sql);
			return query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	//Method Created : Harshad Padole
	//Method Purpose : Check mapped entity
	@Override
	public int isExistEntityMapping(int enti_id, int orga_id, int loca_id, int dept_id) {
		try {
			String sql = "SELECT count(*) as `enti_id` FROM `cfg_entity_mapping` WHERE `enti_id` !="+enti_id+" AND `enti_orga_id` = "+orga_id+" AND `enti_loca_id` = "+loca_id+" AND `enti_dept_id` = "+dept_id+" ";
			Query query = em.createNativeQuery(sql);
            String count = query.getResultList().get(0).toString();
			return Integer.parseInt(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	//Method Created : Mugdha Chandratre
	//Method purpose : Get unique entity mapping
	@SuppressWarnings("unchecked")
	@Override
	public <t> List<t> getUniqueOrganogramMapping() {
		try {
			String sql = "SELECT enti_orga_id, mst_organization.orga_name, enti_loca_id, mst_location.loca_name, enti_dept_id, mst_department.dept_name FROM cfg_entity_mapping, mst_organization, mst_location, mst_department WHERE mst_organization.orga_id = enti_orga_id AND mst_location.loca_id = enti_loca_id AND mst_department.dept_id = enti_dept_id";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	//Method Created : Mugdha Chandratre
	//Method purpose : Get Executor list as per unique entity mapping
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<Object> getExecutorListMappingWise(int orgaid, int locaid, int deptid) {
		try {
			String sql = "SELECT DISTINCT mst_user.user_id, mst_user.user_first_name, mst_user.user_last_name, mst_user.user_role_id FROM cfg_user_entity_mapping, mst_user WHERE umap_orga_id = " + orgaid + " AND umap_loca_id = " + locaid + " AND umap_dept_id = " + deptid + " AND umap_user_id = mst_user.user_id AND mst_user.user_role_id = 1";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	//Method Created : Mugdha Chandratre
	//Method purpose : Get Evaluator list as per unique entity mapping
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<Object> getEvaluatorListMappingWise(int orgaid, int locaid, int deptid) {
		try {
			String sql = "SELECT DISTINCT mst_user.user_id, mst_user.user_first_name, mst_user.user_last_name, mst_user.user_role_id FROM cfg_user_entity_mapping, mst_user WHERE umap_orga_id = " + orgaid + " AND umap_loca_id = " + locaid + " AND umap_dept_id = " + deptid + " AND umap_user_id = mst_user.user_id AND mst_user.user_role_id = 2";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	//Method Created : Mugdha Chandratre
	//Method purpose : Get FunctionHead list as per unique entity mapping
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<Object> getFunctionHeadListMappingWise(int orgaid, int locaid, int deptid) {
		try {
			String sql = "SELECT DISTINCT mst_user.user_id, mst_user.user_first_name, mst_user.user_last_name, mst_user.user_role_id FROM cfg_user_entity_mapping, mst_user WHERE umap_orga_id = " + orgaid + " AND umap_loca_id = " + locaid + " AND umap_dept_id = " + deptid + " AND umap_user_id = mst_user.user_id AND mst_user.user_role_id = 3";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	//Method Created : Mugdha Chandratre
		//Method purpose : Get FunctionHead list as per unique entity mapping
		@SuppressWarnings("unchecked")
		@Override
		public List<String> getMappedDeptList(int orgaid, int locaid) {
			try {
				String sql = "SELECT DISTINCT mst_department.dept_name FROM cfg_entity_mapping, mst_department WHERE enti_orga_id = " + orgaid + " AND enti_loca_id = " + locaid + " AND enti_dept_id = mst_department.dept_id";
				Query query = em.createNativeQuery(sql);
				return query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}


		@Override
		public int getExecutorMappingCount(int orga_id, int loca_id, int dept_id) {
			try {
				String sql = "SELECT COUNT(*) as executorcount FROM cfg_user_entity_mapping, mst_user WHERE umap_orga_id = " + orga_id + " AND umap_loca_id = " + loca_id + " AND umap_dept_id = " + dept_id + " AND umap_user_id = mst_user.user_id AND mst_user.user_role_id = 1";
				Query query = em.createNativeQuery(sql);
	            String count = query.getResultList().get(0).toString();
				return Integer.parseInt(count);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}


		@Override
		public int getEvaluatorMappingCount(int orga_id, int loca_id, int dept_id) {
			try {
				String sql = "SELECT COUNT(*) as evaluatorcount FROM cfg_user_entity_mapping, mst_user WHERE umap_orga_id = " + orga_id + " AND umap_loca_id = " + loca_id + " AND umap_dept_id = " + dept_id + " AND umap_user_id = mst_user.user_id AND mst_user.user_role_id = 2";
				Query query = em.createNativeQuery(sql);
	            String count = query.getResultList().get(0).toString();
				return Integer.parseInt(count);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}


		@Override
		public int getFunctionHeadMappingCount(int orga_id, int loca_id, int dept_id) {
			try {
				String sql = "SELECT COUNT(*) as functionheadcount FROM cfg_user_entity_mapping, mst_user WHERE umap_orga_id = " + orga_id + " AND umap_loca_id = " + loca_id + " AND umap_dept_id = " + dept_id + " AND umap_user_id = mst_user.user_id AND mst_user.user_role_id = 3";
				Query query = em.createNativeQuery(sql);
	            String count = query.getResultList().get(0).toString();
				return Integer.parseInt(count);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}


		@Override
		public int deleteActualEntityMapping(int enti_id) {
			try {
				String sql = "DELETE FROM `cfg_entity_mapping` WHERE enti_id = " + enti_id + " ";
				Query query = em.createNativeQuery(sql);
				
	            int deleteCount = query.executeUpdate();
	            System.out.println("This is no of rows deleted:"+deleteCount);
				return deleteCount;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Object> getOrganizationByUserId(HttpSession session) {
			try {
				int sess_user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
				String sql= "SELECT DISTINCT orga_id,orga_name "
						+ "FROM mst_organization "
						+ "JOIN cfg_user_entity_mapping ON umap_orga_id = orga_id "
						+ "WHERE umap_user_id = "+ sess_user_id;
				Query query = em.createNativeQuery(sql);
				return query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}


		@SuppressWarnings("unchecked")
		@Override
		public List<Object> getLocationByOrgaUserId(HttpSession session, int orga_id) {
			try {
				int sess_user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
				String sql= "SELECT DISTINCT loca_id,loca_name FROM mst_location JOIN cfg_user_entity_mapping ON umap_loca_id = loca_id WHERE umap_user_id = "+sess_user_id+" and umap_orga_id = "+orga_id;
				Query query = em.createNativeQuery(sql);
				return query.getResultList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
		}


		@SuppressWarnings("unchecked")
		@Override
		public List<Object> getDepartmentsByLocaUserId(HttpSession session, int loca_id, int orga_id) {
			try {
				int sess_user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
				String sql = "SELECT DISTINCT dept_id,dept_name FROM mst_department JOIN cfg_user_entity_mapping ON umap_dept_id = dept_id WHERE umap_user_id = "+sess_user_id+ " and umap_orga_id = "+orga_id+" and umap_loca_id = "+loca_id;
				Query query = em.createNativeQuery(sql);
				return query.getResultList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
		}

		// Method created : Harshad Padole
		// Method purpose : get all countries
	@SuppressWarnings("unchecked")
	@Override
	public List<Countries> getAllCountries() {
		try {
			String sql = "SELECT * FROM mst_countries";
			Query query = em.createNativeQuery(sql,Countries.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// Method created : Harshad Padole
	// Method purpose : get State by Country id
	@SuppressWarnings("unchecked")
	@Override
	public List<States> getAllStateByCounId(int coun_id) {
		try {
			String sql = "select * from mst_states where stat_country_id=" + coun_id;
			Query query = em.createNativeQuery(sql,States.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// Method created : Harshad Padole
	// Method purpose : get city by state id
	@SuppressWarnings("unchecked")
	@Override
	public List<Cities> getAllCityByStateId(int state_id) {
		try {
			String sql = "select * from mst_cities where city_state_id =" + state_id;
			Query query = em.createNativeQuery(sql,Cities.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created : Harshad Padole
	// Method purpose : get all State
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllState() {
		try {
			String sql = "SELECT * FROM mst_states";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created : Harshad Padole
	// Method purpose : get all Cities
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllCity() {
		try {
			String sql = "SELECT * FROM mst_cities";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
