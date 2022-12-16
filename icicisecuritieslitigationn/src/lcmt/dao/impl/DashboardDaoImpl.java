package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;

//import org.eclipse.jdt.internal.compiler.lookup.IQualifiedTypeResolutionListener;
import org.springframework.stereotype.Repository;

import lcmt.dao.DashboardDao;
import lcmt.domain.Litigation;
import lcmt.domain.LitigationType;

@Repository(value = "dashboardDao")
@Transactional
public class DashboardDaoImpl implements DashboardDao {

	@PersistenceContext
	private EntityManager em;

	//Method created :
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getOverallLitigationStatusGraph(HttpSession session) {
		try {
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			int user_role = Integer.parseInt(session.getAttribute("sess_user_role").toString());
			String sql = "SELECT SUM(liti_litigation_result ='Pending') as Pending, SUM(liti_litigation_result ='Against') as Against, SUM(liti_litigation_result ='In Favour') as InFavour, SUM(liti_litigation_result ='Settled') as Settled, SUM(liti_litigation_result ='Withdrawn') as Withdrawn FROM mst_litigation ";
			if(user_role != 1){
				sql += " where liti_added_by ="+user_id+ " OR liti_assigned_to ="+user_id;
			}
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllLitigationDataByStatusPieChart(String status, HttpSession session) {
		try {
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			int user_role = Integer.parseInt(session.getAttribute("sess_user_role").toString());
			
			String sql = "SELECT liti_client_liti_id, liti_against_by_id,liti_type_id, liti_case_title,liti_case_reference_no, liti_id FROM mst_litigation where liti_litigation_result ='"+status+"'";
			if(user_role != 1){
				sql += " AND (liti_added_by ="+user_id+ " OR liti_assigned_to ="+user_id+")";
			}
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllCateoryNos(HttpSession session) {
		try {
			String sql = " SELECT liti_type_id, liti_type_name from mst_litigation_type ";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllLitigationForCategory(int liti_type_id, HttpSession session) {
		try {
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			int user_role = Integer.parseInt(session.getAttribute("sess_user_role").toString());
			
			String sql = " SELECT liti_litigation_result, liti_id from mst_litigation where liti_type_id= " + liti_type_id;
			if(user_role != 1){
				sql += " AND (liti_added_by ="+user_id+ " OR liti_assigned_to ="+user_id+")";
			}
			
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllLitigationByCaseCategory(int caseCategory, String LitigationStatus,
			HttpSession session) {
		try {
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			int user_role = Integer.parseInt(session.getAttribute("sess_user_role").toString());
			
			String sql = "SELECT liti_client_liti_id, liti_against_by_id,liti_type_id, liti_case_title,liti_case_reference_no, liti_id FROM mst_litigation where liti_litigation_result ='"+LitigationStatus+"'";
			if(caseCategory != 0){
				sql += " AND liti_type_id="+caseCategory+"";
			}
			if(user_role != 1){
				sql += " AND (liti_added_by ="+user_id+ " OR liti_assigned_to ="+user_id+")";
			}
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getLitigationIdByLitigationtype(String liti_type_name) {
		try {
			//System.out.println(" name  "+liti_type_name);
			String sql = "SELECT liti_type_id FROM mst_litigation_type where liti_type_name='"+liti_type_name+"'";
			//System.out.println(" sql "+sql);
			Query query = em.createNativeQuery(sql);
			return Integer.parseInt(query.getResultList().get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	
}
