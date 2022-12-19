package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.LawFirmDao;
import lcmt.domain.LawFirm;

/*
 * Author: Harshad Padole
 * Date: 24/08/2016
 * Updated By: 
 * Updated Date: 
 * 
 * */

@Repository(value = "LawFirmDao")
@Transactional
public class LawFirmDaoImpl implements LawFirmDao {

	@PersistenceContext
	private EntityManager em;

	// Method Created : Tejashri Zurunge
	// Method Purpose : Add new law firm
	@Override
	public void persist(Object obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get all law firm
	@Override
	public <T> List<T> getAllLawFirm(Class<T> clazz) {
		try {
			TypedQuery<T> query = em.createQuery("from " + clazz.getName(), clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: get the all Law Firm values by id

	public LawFirm getLawFirmById(int lawf_id) {
		try {
			String sql = "SELECT * FROM mst_law_firm where lawf_id = " + lawf_id;
			Query query = em.createNativeQuery(sql, LawFirm.class);
			if (!query.getResultList().isEmpty()) {
				return (LawFirm) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created by : Tejashri Zurunge
	// Method Purpose : Update Law Firm

	public void updateLawFirm(LawFirm lawFirm) {
		try {
			em.merge(lawFirm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method created by : Tejashri Zurunge
	// Method Purpose : To check whether Law Firm Name exist or not

	@Override
	public int isLawFirmNameExist(int lawf_id, String lawf_name) {
		try {
			String sql = "select count(*) as lawfcount from mst_law_firm where lawf_name='" + lawf_name + "' " + " ";
			if (lawf_id != 0) {
				sql += " AND lawf_id !=" + lawf_id;
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
	public int deleteLawFirm(int lawf_id) {
		try {
			String sql = "DELETE FROM mst_law_firm WHERE lawf_id =" + lawf_id;
			Query query = em.createNativeQuery(sql);
			int deleteCount = query.executeUpdate();
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> checkLawFirmDependancy(int lawf_id) {
		try {
			String sql = " Select  case when liti.liti_advocate_law_firm > 0 then 1 else 0 END as liti_law_firm, "
					+ " case when hear.hsco_counsel_law_firm_id > 0 then 1 else 0 END as hear_law_firm,"
					+ " case when exte.exte_coun_law_firm > 0 then 1 else 0 END as exte_law_firm, "
					+ " case when fees.lcou_law_firm_id > 0 then 1 else 0 end as coun_law_firm, "
					+ " case when advo.ladv_advocate_law_firm_id > 0 then 1 else 0 end as advo_law_firm"
					+ " from mst_law_firm lawf LEFT JOIN mst_litigation liti "
					+ " ON lawf.lawf_id = liti.liti_advocate_law_firm " + " LEFT join trn_hearing_stage_counsel hear "
					+ " ON lawf.lawf_id = hear.hsco_counsel_law_firm_id " + " LEFT join mst_external_counsel exte "
					+ " ON lawf.lawf_id = exte.exte_coun_law_firm " + " LEFT join trn_litigation_counsel_fees fees "
					+ " ON lawf.lawf_id = fees.lcou_law_firm_id " + " LEFT join trn_litigation_advocate_fees advo "
					+ " ON lawf.lawf_id = advo.ladv_advocate_law_firm_id" + " where lawf.lawf_id = " +lawf_id +" LIMIT 1";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
