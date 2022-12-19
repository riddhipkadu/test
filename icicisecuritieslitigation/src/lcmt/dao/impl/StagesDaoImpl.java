package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.domain.Stages;
import lcmt.dao.StagesDao;

@Repository(value = "stagesDao")
@Transactional
public class StagesDaoImpl implements StagesDao {

	@PersistenceContext
	private EntityManager em;

	// Method Created By: Tejashri Zurunge
	// Method Purpose: Add New Stage
	@Override
	public void persist(Object obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: listing and view Stage
	@Override
	public <T> List<T> getAllStages(Class<T> clazz) {
		try {
			TypedQuery<T> query = em.createQuery(" from " + clazz.getName(), clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: listing Stage by Id
	@Override
	public Stages getStagesById(int stage_id) {
		try {
			String sql = "SELECT * FROM mst_stages where stage_id = " + stage_id;
			Query query = em.createNativeQuery(sql, Stages.class);
			if (!query.getResultList().isEmpty()) {
				return (Stages) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Update Stages
	@Override
	public void updateStages(Stages stages) {
		try {
			em.merge(stages);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Check whether Stages Exist or not
	@Override
	public int isStagesNameExist(int stage_id, String stage_name) {
		try {
			String sql = "select count(*) as scount from mst_stages where stage_name='" + stage_name + "' " + " ";
			if (stage_id != 0) {
				sql += " AND stage_id !=" + stage_id;
			}
			Query query = em.createNativeQuery(sql);
			String count = query.getResultList().get(0).toString();
			return Integer.parseInt(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Delete Stages
	@Override
	public int deleteStages(int stage_id) {
		try {
			String sql = "DELETE FROM mst_stages WHERE stage_id = " + stage_id;
			Query query = em.createNativeQuery(sql);
			int deleteCount = query.executeUpdate();
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : check Stages dependancy	
	@Override
	public int checkDependancyStages(int stage_id) {
		try {
			String sql ="Select TOP 1 case when hear.ttrn_stage_id > 0 then 1 else 0 END as hear_stage "
					+ " from mst_stages stage full join trn_hearing_stages hear "
					+ " on stage.stage_id = hear.ttrn_stage_id where stage.stage_id = "+stage_id;
			Query query = em.createNativeQuery(sql);
			return Integer.parseInt(query.getResultList().get(0).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
