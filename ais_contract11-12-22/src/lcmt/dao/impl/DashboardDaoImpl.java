package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.DashboardDao;

@Repository(value = "dashboardDao")
@Transactional
public class DashboardDaoImpl implements DashboardDao {

	@PersistenceContext
	private EntityManager em;

	//Method created :
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getOverallPreContractStatusGraph(HttpSession session) {
		try {
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			int user_role = Integer.parseInt(session.getAttribute("sess_user_role").toString());
			String sql = "SELECT SUM(cont_status ='Sent for Review') as SentforReview, SUM(cont_status ='Finalized') as Finalized, SUM(cont_status ='Closed/Executed') as ClosedExecuted, SUM(cont_status ='SentToPOCforNegotiation') as SentToPOCforNegotiation, SUM(cont_status ='Others') as Others, SUM(cont_status ='Pending') as Pending FROM mst_contracts ";
			if(user_role != 1 && user_role == 2){
				sql += " where cont_added_by ="+user_id+ " OR cont_responsible_user_id ="+user_id;
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
	public List<Object> getAllPreContractDataByStatusPieChart(String status, HttpSession session) {
		try {
			//System.out.println( Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			
			int user_role = Integer.parseInt(session.getAttribute("sess_user_role").toString());
			
			String sql = "select cont_id, orga_name, loca_name, dept_name, cont_start_date, cont_end_date, u.user_first_name, u.user_last_name as resposibleperson from mst_contracts LEFT join mst_organization on orga_id = cont_orga_id LEFT join mst_location on loca_id = cont_loca_id LEFT join mst_department on dept_id = cont_dept_id LEFT join mst_user u on u.user_id = cont_responsible_user_id where cont_status = '"+status+"'";
			if(user_role != 1 && user_role == 2){
				sql += " AND (cont_added_by ="+user_id+ " OR cont_responsible_user_id ="+user_id+")";
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
			String sql = " SELECT cont_type_id, cont_type_name from mst_contract_type  ";
			//System.out.println(sql);
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllContractForCategory(int cont_id, HttpSession session) {
		try {
			
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			//System.out.println("user_id"+ user_id);
			
			int user_role = Integer.parseInt(session.getAttribute("sess_user_role").toString());
			String sql = " SELECT mst_contracts.cont_status,mst_contracts.cont_id from mst_contracts  join trn_contract_contract_type on  mst_contracts.cont_id =trn_contract_contract_type.cont_contract_id where cont_contract_type_id ="+cont_id;
			//System.out.println(sql);
			
			if(user_role != 1 && user_role == 2){
				sql += " AND (mst_contracts.cont_added_by ="+ user_id+ " OR mst_contracts.cont_responsible_user_id ="+ user_id+")";
			}
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int getContractIdByContractType(String cont_type_name) {
		try {
			//System.out.println(" name  "+liti_type_name);
			String sql = "SELECT cont_type_id FROM mst_contract_type where cont_type_name ='"+cont_type_name+"'";
			//System.out.println(" sql "+sql);
			Query query = em.createNativeQuery(sql);
			return Integer.parseInt(query.getResultList().get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllContractByCaseCategory(int caseCategory, String PreContractStatus,
			HttpSession session) {
		try {
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			int user_role = Integer.parseInt(session.getAttribute("sess_user_role").toString());
			
			String sql = "SELECT cont.cont_id, orga_name, loca_name, dept_name, cont.cont_start_date, cont.cont_end_date, u.user_first_name, u.user_last_name as resposibleperson, cont.cont_status from mst_contracts cont LEFT join mst_organization on orga_id = cont_orga_id LEFT join mst_location on loca_id = cont_loca_id LEFT join mst_department on dept_id = cont_dept_id LEFT join mst_user u on u.user_id = cont.cont_responsible_user_id left join trn_contract_contract_type cont_type on  cont.cont_id = cont_type.cont_contract_id where cont.cont_status ='"+PreContractStatus+"'";
			if(caseCategory != 0){
				sql += " AND cont_type.cont_contract_type_id ="+caseCategory+"";
			}
			if(user_role != 1 && user_role == 2){
				sql += " AND (cont.cont_added_by ="+user_id+ " OR cont.cont_responsible_user_id ="+user_id+")";
			}
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



//Method created : Akash Jadhav 
	
		@SuppressWarnings("unchecked")
		@Override
		public List<Object> getOverallEntityGraph(HttpSession session) {
			try {
				int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
				int user_role = Integer.parseInt(session.getAttribute("sess_user_role").toString());
				String sql = "SELECT SUM(exec_contract_entity_id = 2) as 'Mahyco Grow', SUM(exec_contract_entity_id =3) as 'Mahyco Private Limited', SUM(exec_contract_entity_id = 4) as 'Maharashtra Hybrid Seeds Company Private Limited', SUM(exec_contract_entity_id =5) as 'Grow Indigo Private Limited', SUM(exec_contract_entity_id =6) as 'Seven Star Fruits Private Limited', SUM(exec_contract_entity_id =7) as 'Leadbeter Seeds Private Limited', SUM(exec_contract_entity_id =8) as 'Mahyco International Pte Limited', SUM(exec_contract_entity_id =9) as 'Mahyco Bangladesh Private Limited', SUM(exec_contract_entity_id = 10) as MahycoKenyaPrivateLimited, SUM(exec_contract_entity_id =11) as MahycoGrowSAPtyLTD FROM mst_executed_contacts";
				if(user_role != 1 && user_role == 2){
					sql += " where cont_added_by ="+user_id+ " OR cont_responsible_user_id ="+user_id;
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
public List<Object> getAllEntityDataByPieChart(String status, HttpSession session) {
	try {
		//System.out.println( Integer.parseInt(session.getAttribute("sess_user_id").toString()));
		int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
		
		int user_role = Integer.parseInt(session.getAttribute("sess_user_role").toString());
		
		String sql = "select exec_contract_id, orga_name, loca_name, dept_name, ifnull(DATE_FORMAT(exec_contract_date, '%d-%m-%Y'),'NA'),ifnull(DATE_FORMAT(exec_contract_end_date, '%d-%m-%Y'),'NA') , ifnull(u.user_first_name,'NA'), ifnull(u.user_last_name,'NA')  from mst_executed_contacts LEFT join mst_organization on orga_id = exec_contract_entity_id LEFT join mst_location on loca_id = exec_contract_unit_id LEFT join mst_department on dept_id = exec_contract_function_id LEFT join mst_user u on u.user_id = exec_contract_resposible_person where orga_name = '"+status+"'";
		if(user_role != 1 && user_role == 2){
			sql += " AND (exec_contract_added_by ="+user_id+ " OR exec_contract_resposible_person ="+user_id+")";
		}
		
		System.out.println("getAllEntityDataByPieChart:- "+sql);
		Query query = em.createNativeQuery(sql);
		return query.getResultList();
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
}

