package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.CourtDao;
import lcmt.domain.Court;

@Repository(value = "courtDao")
@Transactional
public class CourtDaoImpl implements CourtDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void persist(Court court) {
		try {
			em.persist(court);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> List<T> getAllCourt(Class<T> clazz) {
		try {
			TypedQuery<T> query = em.createQuery("from " + clazz.getName(), clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Court getCourtById(int court_id) {
		try {
			String sql = "Select * from mst_court where court_id = "+court_id;
			Query query = em.createNativeQuery(sql, Court.class);
			if(!query.getResultList().isEmpty()){
				return (Court) query.getResultList().get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateCourt(Court court) {
		try {
			em.merge(court);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int isCourtExistOrNot(int court_id, String court_name) {
		try {
			String sql = "Select count(*) as courtCount from mst_court where court_name = '"+court_name
					+ "'"; 
			if(court_id != 0){
				sql += " AND court_id != "+court_id;
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
	public int deleteCourt(int court_id) {
		try {
			String sql = "Delete from mst_court where court_id = "+court_id;
			Query query = em.createNativeQuery(sql);
			int deletecount = query.executeUpdate();
			return deletecount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> checkDependancyCourt(int court_id) {
		try {
			String sql = " Select TOP 1 case when liti.liti_court > 0 then 1 else 0 END as litiCourt, "
						+ " case when liti.liti_completion_court_id > 0 then 1 else 0 END as completionCourt from mst_court court " 
						+ " full join mst_litigation liti on (court.court_id = liti.liti_court " 
						+ " OR court.court_id = liti.liti_completion_court_id) "
						+ " where court.court_id =  "+court_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
