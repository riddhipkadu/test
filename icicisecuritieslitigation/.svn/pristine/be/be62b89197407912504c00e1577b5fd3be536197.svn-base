package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.ExternalCounselDao;
import lcmt.domain.ExternalCounsel;

/*
 * Author: Harshad Padole
 * Date: 23/08/2016
 * Updated By: 
 * Updated Date: 
 * 
 * */

@Repository(value = "externalCounselDao")
@Transactional
public class ExternalCounselDaoImpl implements ExternalCounselDao {

	@PersistenceContext
	private EntityManager em;

	// Method Created By: Harshad Padole
	// Method Purpose: Add New External Counsel
	@Override
	public void persist(ExternalCounsel counsel) {
		try {
			em.persist(counsel);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created By: Harshad Padole
	// Method Purpose: Get all External Counsel
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAll() {
		try {
			String sql = "SELECT exte_coun_id,exte_coun_name,lawf_id,lawf_name,exte_coun_mobile_no,exte_coun_email_id,exte_coun_address,area_expe_id,area_expe_name, exte_coun_city, exte_coun_country_id, exte_coun_state_id, exte_coun_enable_status,exte_coun_approval_status,exte_coun_created_at,exte_coun_updated_at,exte_coun_added_by FROM mst_external_counsel JOIN mst_area_of_expertise ON area_expe_id = exte_coun_area_of_expertise "
					+ "JOIN mst_law_firm ON mst_law_firm.lawf_id = exte_coun_law_firm ";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Harshad Padole
	// Method Purpose: Get External Counsel By Id
	@Override
	public ExternalCounsel getExte_CounById(int exte_coun_id) {
		try {
			String sql = "select * from mst_external_counsel where exte_coun_id =" + exte_coun_id;
			Query query = em.createNativeQuery(sql, ExternalCounsel.class);
			if (!query.getResultList().isEmpty()) {
				return (ExternalCounsel) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Harshad Padole
	// Method Purpose: Update ExternalCounsel
	@Override
	public void updateExternalCounsel(ExternalCounsel counsel) {
		try {
			em.merge(counsel);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// Method Created By: Tejashri Zurunge
	// Method Purpose: Check whether External Counsel is exist or not

	@Override
	public int isExternalCounselNameExist(int exte_coun_id, String exte_coun_name) {
		try {
			String sql = "select count(*) as extCounselcount from mst_external_counsel where exte_coun_name='"
					+ exte_coun_name + "' " + " ";
			if (exte_coun_id != 0) {
				sql += " AND exte_coun_id !=" + exte_coun_id;
			}
			Query query = em.createNativeQuery(sql);
			String count = query.getResultList().get(0).toString();
			return Integer.parseInt(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: Delete External Counsel

	@Override
	public int deleteExternalCounsel(int exte_coun_id) {
		try {
			String sql = "DELETE FROM mst_external_counsel WHERE exte_coun_id = " + exte_coun_id;
			Query query = em.createNativeQuery(sql);
			int deleteCount = query.executeUpdate();
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: Search all External Counsel
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> searchExternalCounsel(int exte_coun_country_id, int exte_coun_state_id, int exte_coun_city,
			int exte_coun_law_firm, int exte_coun_name, int exte_coun_area_of_expertise) {
		try {
			String sql = "SELECT exte_coun_id,exte_coun_name,lawf_id,lawf_name,exte_coun_mobile_no,exte_coun_email_id,exte_coun_address,area_expe_id,area_expe_name,exte_coun_enable_status,exte_coun_approval_status,exte_coun_created_at,exte_coun_updated_at,exte_coun_added_by FROM mst_external_counsel JOIN mst_area_of_expertise ON area_expe_id = exte_coun_area_of_expertise "
					+ "JOIN mst_law_firm ON mst_law_firm.lawf_id = exte_coun_law_firm where exte_coun_country_id = "
					+ exte_coun_country_id;
			if (exte_coun_state_id > 0) {
				sql += " AND exte_coun_state_id = " + exte_coun_state_id;
			}
			if (exte_coun_city > 0) {
				sql += " AND exte_coun_city = " + exte_coun_city;
			}
			if (exte_coun_law_firm > 0) {
				sql += "  AND exte_coun_law_firm = " + exte_coun_law_firm;
			}
			if (exte_coun_name > 0) {
				sql += " AND exte_coun_name = " + exte_coun_name;
			}
			if (exte_coun_area_of_expertise > 0) {
				sql += " AND exte_coun_area_of_expertise = " + exte_coun_area_of_expertise;
			}
			// System.out.println("Seach exte "+sql);
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get Counsel By law firm id
	@SuppressWarnings("unchecked")
	@Override
	public List<ExternalCounsel> getConselAsPerLawFirm(int law_firm_id) {
		try {
			String sql = "select * from mst_external_counsel where exte_coun_law_firm =" + law_firm_id;
			Query query = em.createNativeQuery(sql, ExternalCounsel.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get country name
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getCountryByCounselId() {
		try {
			String sql = "select distinct coun_id , coun_name from mst_countries join mst_external_counsel on coun_id = exte_coun_country_id ";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get state name by country
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getJoinedStateByCountryId(int exte_coun_country_id) {
		try {
			String sql = "select distinct stat_id, stat_name from mst_states join mst_external_counsel on exte_coun_state_id = stat_id where exte_coun_country_id = "
					+ exte_coun_country_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get city name by state
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getJoinedCityByStateId(int exte_coun_state_id) {
		try {
			String sql = "select distinct city_id, city_name from mst_cities join mst_external_counsel on exte_coun_city = city_id where exte_coun_state_id = "
					+ exte_coun_state_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> checkDependencyExternalCounsel(int exte_coun_id) {
		try {
			String sql = " Select  case when liti.liti_external_counsel_id > 0 then 1 else 0 END as liti_counsel, "
					+ " case when hear.hsco_counsel_id > 0 then 1 else 0 END as hear_counsel,"
					+ " case when lega.lega_noti_external_counsel_id > 0 then 1 else 0 END as lega_counsel, "
					+ " case when fees.lcou_counsel_id > 0 then 1 else 0 END as fees_counsel "
					+ " from mst_external_counsel exte LEFT JOIN mst_litigation liti "
					+ " ON exte.exte_coun_id = liti.liti_external_counsel_id  LEFT join trn_hearing_stage_counsel hear "
					+ " ON exte.exte_coun_id = hear.hsco_counsel_id LEFT join mst_legal_notice lega "
					+ " ON exte.exte_coun_id = lega.lega_noti_external_counsel_id  LEFT JOIN trn_litigation_counsel_fees fees "
					+ " ON exte.exte_coun_id = fees.lcou_counsel_id "
					+ " where exte.exte_coun_id = "+exte_coun_id+" LIMIT 1" ;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
