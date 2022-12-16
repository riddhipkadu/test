package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.UtilitiesDao;
import lcmt.domain.TechSupport;


@Transactional
@Repository(value="utilitiesDao")
public class UtilitiesDaoImpl implements UtilitiesDao {
	

	@PersistenceContext
	private EntityManager em;
	

	@Override
	public int checkDependancy(int id, String table_name) {
		try {
			if(table_name=="mst_law_firm"){
				String sql_per = "SELECT count(*) FROM mst_external_counsel where exte_coun_law_firm= "+id;
				Query query = em.createNativeQuery(sql_per);
				int count=  Integer.parseInt(query.getResultList().get(0).toString());
				return count;	
			}
			
			 if(table_name=="mst_area_of_expertise"){
				String sql_per = "SELECT count(*) FROM mst_external_counsel where exte_coun_area_of_expertise= "+id;
				Query query = em.createNativeQuery(sql_per);
				int count=  Integer.parseInt(query.getResultList().get(0).toString());
				return count;	
			}
			 
			 
		} catch (Exception e) {
		e.printStackTrace();
		}
		return 0;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: save mail details in db
	@Override
	public void persistTechSupport(TechSupport techSupport) {
		try {
			em.persist(techSupport);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: list mail details
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TechSupport> getAllTechSupport() {
		try {
			String sql = "select * from mst_tech_support";
			Query query = em.createNativeQuery(sql, TechSupport.class);
				return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
