package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import lcmt.dao.InternalLitigationDao;
import lcmt.domain.InternalLitigation;

@Repository(value = "internalLitigationDao")
@Transactional
public class InternalLitigationDaoImpl implements InternalLitigationDao {
	
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

	@Override
	public <T> List<T> getAll(Class<T> clazz) {
		try {
			try {
				TypedQuery<T> query = em.createQuery(" from "+clazz.getName(), clazz);
				return query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public InternalLitigation getInternalLitiById(int inter_id) {
		try {
			String sql = "SELECT * FROM mst_internal_litigation_code where internal_liti_id = "+inter_id;
			Query query = em.createNativeQuery(sql,InternalLitigation.class);
			if(!query.getResultList().isEmpty()){
				return (InternalLitigation) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateInternalLitigation(InternalLitigation newLiti) {
		try {
			em.merge(newLiti);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int deleteInternalLitigation(int internal_liti_id) {
		try {
			String sql = "delete from mst_internal_litigation_code where internal_liti_id = "+internal_liti_id;
			Query query = em.createNativeQuery(sql);
            int deleteCount = query.executeUpdate();
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int isLitigationCodeExist(String jsonString) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			String internal_liti_code = jsonObject.get("internal_liti_code").toString();
			String sql = "select count(*) from mst_internal_litigation_code where internal_liti_code = ?";
			Query query = em.createNativeQuery(sql);
			query.setParameter(1, internal_liti_code);
			String count = query.getResultList().get(0).toString();
			return Integer.parseInt(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
