package lcmt.dao.impl;

import javax.transaction.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import lcmt.dao.ClauseLibDao;
import lcmt.dao.LitigationDao;
import lcmt.domain.Clause;
import lcmt.domain.ClauseLib;
import lcmt.domain.QueryMaster;

import org.springframework.stereotype.Repository;

@Repository(value = "clauseLibDao")
@Transactional

public class ClauseLibDaoImpl implements ClauseLibDao{
	
	@PersistenceContext
	private EntityManager em;

	

	@Override
	public void persist(ClauseLib clauseLib) {
		try {
			em.persist(clauseLib);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}



	@Override
	public ClauseLib getClauseLibById(int clause_Lib_Id) {
		try {
			String sql = "SELECT * FROM mst_clauselibrary where clause_Lib_Id = " + clause_Lib_Id;
			Query query = em.createNativeQuery(sql, ClauseLib.class);
			if (!query.getResultList().isEmpty()) {
				return (ClauseLib) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public <T> List<T> getAllClauseLib(Class<T> clazz) {
		try {
			TypedQuery<T> query = em.createQuery(" from " + clazz.getName(), clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}




	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getJoinedAll() {

		try {

			String sql = "SELECT lib.clause_Lib_Id, lib.lib_paragraph, DATE_FORMAT(lib.lib_review_date, '%d-%m-%Y') , cls.cls_name, concat(usr.user_first_name, ' ' , usr.user_last_name) FROM mst_clauselibrary lib left join mst_clause_name cls on cls.cls_id = clause_Id Left JOIN mst_user usr ON usr.user_id = lib_owner_Id ";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}



	@Override
	public int deleteClauseLib(int clause_Lib_Id) {
		try {
			String sql = "DELETE FROM mst_clauselibrary WHERE clause_Lib_Id = " + clause_Lib_Id;
			Query query = em.createNativeQuery(sql);
			int deleteCount = query.executeUpdate();
			// System.out.println("This is no of rows deleted:"+deleteCount);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}



	@Override
	public void updateClauseLib(ClauseLib clauseLib) {
		try {
			em.merge(clauseLib);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



		
		
	
	@Override
	public ClauseLib getClauseLibById_ref(int clause_Lib_Id) {
		try {
			String sql = "select * from mst_clauselibrary where clause_Lib_Id = " + clause_Lib_Id;
			Query query = em.createNativeQuery(sql, ClauseLib.class);
			if (!query.getResultList().isEmpty()) {
				return (ClauseLib) query.getResultList().get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
