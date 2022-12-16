package lcmt.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lcmt.dao.ExecutedContractDao;
import lcmt.domain.ExecutedContracts;
import lcmt.domain.LegalNoticeDocuments;
import lcmt.domain.LitigationType;
import lcmt.domain.TermRenwContract;
import lcmt.domain.TransactionalActionItem;

import lcmt.domain.AmendmentContract;
import lcmt.domain.ContractParties;
import lcmt.domain.ExecuteActionItem;
import lcmt.domain.ExecutedContractDocuments;

/*
 * Author: Harshad Padole
 * Date: 13/09/2016
 * Updated By: 
 * Updated Date: 
 * 
 * */

@Repository(value = "executedContractDao")
@Transactional
public class ExecutedContractDaoImpl implements ExecutedContractDao {

	@PersistenceContext
	private EntityManager em;
	JdbcTemplate template;    
    
	public void setTemplate(JdbcTemplate template) {    
	    this.template = template;    
	}  
	// Method Created : Harshad Padole
	// Method purpose : add Executed contracts
	@Override
	public int saveExecutedContract(ExecutedContracts contracts) {
		try {
			em.persist(contracts);
			em.flush();
			return contracts.getExec_contract_id();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method purpose : update Executed contracts
	@Override
	public int updateExecutedContract(ExecutedContracts contracts) {
		try {
			em.merge(contracts);
			return contracts.getExec_contract_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method purpose : Get Executed contracts by id
	@Override
	public ExecutedContracts getExecutedContactsById(int exec_id) {
		try {
			String sql = "select * from mst_executed_contacts where exec_contract_id = " + exec_id;
			Query query = em.createNativeQuery(sql, ExecutedContracts.class);
			if (!query.getResultList().isEmpty()) {
				return (ExecutedContracts) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method purpose : Get last generated id for contract document
	@Override
	public int getLastGeneratedValueByContactId(int contract_id, int type) {
		try {
			String sql = "SELECT MAX(exec_last_generated_value_for_contract_id) FROM trn_executed_contract_documents where exec_contract_id= ? AND exec_doc_type = ? ";
					
			Query query = em.createNativeQuery(sql);
			query.setParameter(1, contract_id);
			query.setParameter(2, type);
			if (query.getResultList().get(0) != null) {
				return Integer.parseInt(query.getResultList().get(0).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method purpose : Save contract document
	@Override
	public void saveContractDocuments(ExecutedContractDocuments contractDocuments) {
		try {
			em.persist(contractDocuments);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created : Harshad Padole
	// Method purpose : Get Contract Documents By Id
	@Override
	public ExecutedContractDocuments getDocumetById(int contract_id) {
		try {
			String sql = "SELECT * FROM trn_executed_contract_documents where exec_contract_id=" + contract_id;
			Query query = em.createNativeQuery(sql, ExecutedContractDocuments.class);

			if (!query.getResultList().isEmpty()) {
				return (ExecutedContractDocuments) query.getResultList().get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Get all Executed Contract Details
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getAllExecutedContractsDetails(HttpSession session) {
		try {
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			String sql = "SELECT exe.exec_contract_id,ifnull(cont.cont_type_name,'NA'), exe.exec_contract_executed_with,ifnull(orga_name,'NA'),loca_name,dept_name, exe.exec_contract_added_by,exe.exec_contract_entity_id,exe.exec_contract_unit_id,exe.exec_contract_function_id,ifnull(date_format(exec_contract_execution_date,'%d-%m-%Y'),'NA'),ifnull(date_format(exe.exec_contract_start_date,'%d-%m-%Y'),'NA'),ifnull(date_format(exe.exec_contract_end_date,'%d-%m-%Y'),'NA'),ifnull(date_format(exe.exec_contract_renewal_date,'%d-%m-%Y'),'NA'),exe.exec_contract_notice_period,exe.exec_contract_lockin_period,exe.exec_contract_description,exec_contract_payment,exe.exec_contract_termination_clause,exe.exec_contract_damages,exe.exec_contract_consider_involve,exe.exec_contract_signatories_assign,exe.exec_contract_agree_status,exe.exec_contract_renewal_status,exe.exec_contract_ammendment,exe.exec_contract_arbitration,ifnull(exe.exec_contract_insurance,'NA'),exe.exec_contract_division "
					+ " FROM mst_executed_contacts exe "
					+ " LEFT JOIN mst_contract_type cont ON cont.cont_type_id = exe.exec_contract_type_id "
					+ " LEFT JOIN mst_organization ON orga_id = exe.exec_contract_entity_id "
					+ " LEFT JOIN mst_location ON loca_id = exe.exec_contract_unit_id "
					+ " LEFT JOIN mst_department ON dept_id = exe.exec_contract_function_id ";
					if(Integer.parseInt(session.getAttribute("sess_user_role").toString())==2){
					sql += " WHERE (exe.exec_contract_added_by = "+user_id+" OR exe.exec_contract_added_by IN(select DISTINCT user_id from mst_user where user_report_to="+user_id+"))";
					}
					
					
					if(Integer.parseInt(session.getAttribute("sess_user_role").toString())==7){
					sql = "SELECT distinct exe.exec_contract_id, cont.cont_type_name, exe.exec_contract_executed_with,orga_name,loca_name,dept_name, exe.exec_contract_added_by,exe.exec_contract_entity_id,exe.exec_contract_unit_id,exe.exec_contract_function_id,ifnull(date_format(exec_contract_execution_date,'%d-%m-%Y'),'NA'),ifnull(date_format(exe.exec_contract_start_date,'%d-%m-%Y'),'NA'),ifnull(date_format(exe.exec_contract_end_date,'%d-%m-%Y'),'NA'),ifnull(date_format(exe.exec_contract_renewal_date,'%d-%m-%Y'),'NA'),exe.exec_contract_notice_period,exe.exec_contract_lockin_period,exe.exec_contract_description,exec_contract_payment,exe.exec_contract_termination_clause,exe.exec_contract_damages,exe.exec_contract_consider_involve,exe.exec_contract_signatories_assign,exe.exec_contract_agree_status,exe.exec_contract_renewal_status,exe.exec_contract_ammendment,exe.exec_contract_arbitration,exe.exec_contract_insurance,exe.exec_contract_division "
									+ "FROM mst_executed_contacts exe "
									+ " LEFT JOIN mst_contract_type cont ON cont.cont_type_id = exe.exec_contract_type_id "
									+ " LEFT JOIN mst_organization ON orga_id = exe.exec_contract_entity_id "
									+ " LEFT JOIN mst_location ON loca_id = exe.exec_contract_unit_id "
									+ " LEFT JOIN mst_department ON dept_id = exe.exec_contract_function_id "
				+ " LEFT join cfg_user_entity_mapping ON cfg_user_entity_mapping.umap_orga_id = exec_contract_entity_id "
		        + "  where cfg_user_entity_mapping.umap_user_id =" + user_id ;
					}
					sql += " order by exec_contract_id desc";
			
					Query query = em.createNativeQuery(sql);
			
			if (query.getResultList().size() > 0) {
				return query.getResultList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : search Executed Contract Details
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> searchExecutedContract(int exec_contract_type_id, int exec_contract_entity_id,int exec_contract_unit_id,int exec_contract_function_id,String exec_contract_division, HttpSession session) {
		try {
			//System.out.println("Search Dao");
			
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			String sql = "SELECT orga_name, loca_name, dept_name, exe.exec_contract_id, cont.cont_type_name, exe.exec_contract_executed_with, exe.exec_contract_added_by,exe.exec_contract_execution_date,exe.exec_contract_start_date,exe.exec_contract_end_date,exe.exec_contract_renewal_date,exe.exec_contract_notice_period,exe.exec_contract_lockin_period,exe.exec_contract_description,exe.exec_contract_payment,exe.exec_contract_termination_clause,exe.exec_contract_damages,exe.exec_contract_consider_involve,exe.exec_contract_signatories_assign,exe.exec_contract_agree_status,exe.exec_contract_renewal_status,exe.exec_contract_ammendment,exe.exec_contract_arbitration,exe.exec_contract_insurance,exe.exec_contract_division FROM mst_executed_contacts exe"
						+ " JOIN mst_contract_type cont ON exe.exec_contract_type_id = cont.cont_type_id "
						+ " JOIN mst_organization ON orga_id = exe.exec_contract_entity_id "
						+ " JOIN mst_location ON loca_id = exe.exec_contract_unit_id "
						+ " JOIN mst_department ON dept_id = exe.exec_contract_function_id "
						+ " where exe.exec_contract_entity_id = "+ exec_contract_entity_id;
			if (exec_contract_unit_id > 0) {
				sql += " AND exe.exec_contract_unit_id = " + exec_contract_unit_id;
				//System.out.println(sql);
			}
			if (exec_contract_function_id > 0) {
				sql += " AND exe.exec_contract_function_id = " + exec_contract_function_id;
			}
			if (exec_contract_type_id > 0) {
				sql += " AND exe.exec_contract_type_id = " + exec_contract_type_id;
			}
			if(!exec_contract_division.equals("NA"))
				sql += " AND exec_contract_division = '"+exec_contract_division+"'";
			
			if(Integer.parseInt(session.getAttribute("sess_user_role").toString())==2){
				sql += " AND (exe.exec_contract_added_by = "+user_id+" OR exe.exec_contract_added_by IN(select DISTINCT user_id from mst_user where user_report_to="+user_id+"))";
			}
			//System.out.println("search : "+sql);
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Get all Executed Contract executed with
	@Override
	public <T> List<T> getAllExecutedWith(Class<T> clazz) {
		try {
			TypedQuery<T> query = em.createQuery(" from " + clazz.getName(), clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : download Executed Contract documents
	@Override
	public String getExecutedContractDocumentFilePath(int exec_doc_id) {
		try {
			String sql = "select exec_generated_file_name from trn_executed_contract_documents where exec_doc_id = "
					+ exec_doc_id;
			Query query = em.createNativeQuery(sql);
			if (!query.getResultList().isEmpty()) {
				if (query.getResultList().get(0) != null) {
					return query.getResultList().get(0).toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExecutedContractDocuments> getAllExecutedContractDocumentsById(int exec_id, int type) {
		try {System.out.println(exec_id +" " + type);
			String sql = "SELECT * FROM trn_executed_contract_documents where exec_contract_id = ? AND exec_doc_type= ?";
			
			Query query = em.createNativeQuery(sql, ExecutedContractDocuments.class);
			query.setParameter(1, exec_id);
			query.setParameter(2, type);
			if (!query.getResultList().isEmpty()) {
				return query.getResultList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object[] getJoinedExecutedContractDetailsById(int exec_id) {
		try {
			String sql = "select orga_name, loca_name, dept_name, cont_type_name, exec_contract_description, exec_contract_internal_contact_name,"
					+ " exec_contract_executed_with, exec_contract_executed_contact_name, exec_contract_date,exec_contract_id,orga_id,loca_id,"
					+ " dept_id,exec_contract_type_id,exec_contract_resposible_person,exec_contract_start_date, exec_contract_end_date,"
					+ " exec_contract_surviving_obl, exec_contract_payment, exec_contract_notice_period, exec_contract_insurance,"
					+ " exec_contract_damages, exec_contract_jurisdiction, exec_contract_arbitration, exec_contract_criticality,"
					+ " exec_contract_cont_person_email, exec_contract_cont_person_number,exec_contract_execution_date,exec_contract_renewal_date,"
					+ " exec_contract_lockin_period,exec_contract_termination_clause,exec_contract_consider_involve,"
					+ " exec_contract_signatories_assign,exec_contract_ammendment,exec_contract_agree_status,exec_contract_renewal_status,"
					+ " exec_remind_date,exec_remind_date_second,exec_remind_date_third,exec_contract_division,exec_force_majeure,exec_royalty_number,exec_option_number,exec_territory_number,exec_crop_number,exec_technology_number,exec_report_number,exec_milestone_number,exec__assignability "
					+ " from mst_executed_contacts "
					+ " join mst_organization on orga_id = exec_contract_entity_id "
					+ " join mst_location on loca_id = exec_contract_unit_id "
					+ " join mst_department on dept_id = exec_contract_function_id "
					+ " join mst_contract_type on cont_type_id = exec_contract_type_id "
					+ " where exec_contract_id = "
						+exec_id;
			
			System.out.println("details: "+sql);
			Query query = em.createNativeQuery(sql);
			return (Object[]) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Harshad Padole
	//Method Purpose : Save party details 
	@Override
	public void savePartyDetails(Object object) {
		try {
			em.persist(object);
		} catch (Exception e) {
		 e.printStackTrace();
		}
		
	}

	//Method Created : Harshad Padole
	//Method Purpose : get party by contract id
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractParties> getExecutedContractPartiesByContractId(int contract_id) {
		try {
			String sql = " select * from trn_contract_parties where cont_party_contract_id =?  AND cont_party_type = 2";
			Query query = em.createNativeQuery(sql, ContractParties.class);
			query.setParameter(1,contract_id);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Harshad Padole
	//Method Purpose : get party by party id
	@Override
	public ContractParties getPartyById(int party_id) {
		try {
			String sql = " select * from trn_contract_parties where cont_party_id =?";
			Query query = em.createNativeQuery(sql, ContractParties.class);
			query.setParameter(1,party_id);
			return (ContractParties) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Harshad Padole
	//Method Purpose : Update party details 
	@Override
	public void meageParties(Object object) {
		try {
			em.merge(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : download Executed Contract documents
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getAllCompletedContract(HttpSession session) {
		try {
			String sql = null;
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			//For admin 
			if(Integer.parseInt(session.getAttribute("sess_user_role").toString()) != 2){
			 sql = "SELECT orga_name, loca_name, dept_name, cont_agreement_name, cont_requested_by_user_id , cont_requested_date,cont_expected_date,u.user_first_name, u.user_last_name as resposibleperson,cont_targetted_date,cont_id,cont_status,cont_added_by,cont_orga_id,cont_loca_id,cont_dept_id FROM mst_contracts join mst_organization on orga_id = cont_orga_id join mst_location on loca_id = cont_loca_id join mst_department on dept_id = cont_dept_id join mst_user u on u.user_id = cont_responsible_user_id where cont_status = 'Closed/Executed'";
			}
			//Normal User
			if(Integer.parseInt(session.getAttribute("sess_user_role").toString())==2){
				sql = "SELECT orga_name, loca_name, dept_name, cont_agreement_name, cont_requested_by_user_id , cont_requested_date,cont_expected_date,u.user_first_name, u.user_last_name as resposibleperson,cont_targetted_date,cont_id,cont_status,cont_added_by,cont_orga_id,cont_loca_id,cont_dept_id FROM mst_contracts join mst_organization on orga_id = cont_orga_id join mst_location on loca_id = cont_loca_id join mst_department on dept_id = cont_dept_id join mst_user u on u.user_id = cont_responsible_user_id where cont_status = 'Closed/Executed' AND (cont_responsible_user_id="+user_id+" OR cont_added_by="+user_id+" OR cont_responsible_user_id IN(select user_id from mst_user where user_report_to="+user_id+") OR cont_added_by IN(select user_id from mst_user where user_report_to="+user_id+"))";
			}
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> searchCompletedContract(int exec_contract_type_id, int exec_contract_entity_id,
			int exec_contract_unit_id, int exec_contract_function_id, HttpSession session) {
		try {
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			String sql = "SELECT orga_name, loca_name, dept_name, cont_id,cont_status,cont_added_by, cont_responsible_user_id FROM mst_contracts "
					+ " join mst_organization on orga_id = cont_orga_id "
					+ " join mst_location on loca_id = cont_loca_id "
					+ " join mst_department on dept_id = cont_dept_id "
					+ " where cont_status = 'Closed/Executed' ";
			if (exec_contract_entity_id > 0)	
				sql	+= " AND cont_orga_id = "+exec_contract_entity_id;
			
			if (exec_contract_unit_id > 0)
				sql += " AND cont_loca_id= " + exec_contract_unit_id;

			if (exec_contract_function_id > 0)
				sql += " AND cont_dept_id= " + exec_contract_function_id;
			
			if(exec_contract_type_id >0)
				sql+= " AND cont_id IN (select DISTINCT cont_contract_id from trn_contract_contract_type where cont_contract_type = 1 AND cont_contract_type_id ="+exec_contract_type_id+")";
			
			//Normal User
			if(Integer.parseInt(session.getAttribute("sess_user_role").toString())==2){
				
				sql+= " AND (cont_responsible_user_id="+user_id+" OR cont_added_by="+user_id+" OR cont_responsible_user_id IN(select user_id from mst_user where user_report_to="+user_id+") OR cont_added_by IN(select user_id from mst_user where user_report_to="+user_id+"))";
			}
			
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Executed Contract
	@Override
	public int deleteExecutedContract(int exec_contract_id) {
		try {
			String sql_one = "Delete from mst_executed_contacts where exec_contract_id= "+exec_contract_id;
			String sql_two = "Delete from trn_executed_contract_documents where exec_contract_id= "+exec_contract_id;
			String sql_three = "Delete from trn_contract_parties where cont_party_contract_id = "+exec_contract_id+" AND cont_party_type= 2 ";
			Query query = em.createNativeQuery(sql_one);
			Query query2 = em.createNativeQuery(sql_two);
			Query query3 = em.createNativeQuery(sql_three);
			query.executeUpdate();
			query2.executeUpdate();
			query3.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : persist Executed Contract	
	@Override
	public int saveLogs(Object obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : delete Executed Contract doc	
	@Override
	public int deleteExecutedContractDocument(int exec_doc_id) {
		try {
			String sql = "Delete from trn_executed_contract_documents where exec_doc_id= "+exec_doc_id;
			Query query = em.createNativeQuery(sql);
			query.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int saveActionItem(ExecuteActionItem actionItem) {
		try {
			 em.persist(actionItem);
			 em.flush();
			 return actionItem.getExec_action_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void save(Object object) {
		try {
			em.persist(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ExecuteActionItem getExecutedActionItemDetails(int exec_id) {
		try {
			
			String sql = "select * from ttrn_execute_action_item where exec_action_id =?";
			Query query = em.createNativeQuery(sql, ExecuteActionItem.class);
			query.setParameter(1, exec_id);
			return (ExecuteActionItem) query.getResultList().get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateExecuteActionItem(ExecuteActionItem actionItem) {
		try {
			  em.merge(actionItem);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllExecutedActionItem(int exec_id) {
		try {
			String sql = "Select exec_action_item, exec_frequency, exec_due_date, exec_responsible_user_id, exec_first_alert_prior_days, exec_second_alert_prior_days, exec_action_id, exec_added_by from ttrn_execute_action_item where exec_contract_id = "+exec_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getActionItemHistoryById(int action_id) {
		try {
			String sql = "SELECT atrn_id,atrn_action_due_date,atrn_action_id,atrn_completed_by, atrn_completed_date, atrn_contract_id, atrn_first_alert_prior_days, atrn_frequency, atrn_resposible_user, atrn_second_alert_prior_days, atrn_status "
					+ " FROM ttrn_action_item_transactional WHERE atrn_action_id =  "+action_id;
			Query query = em.createNativeQuery(sql);
			//System.out.println("sql :"+sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public TransactionalActionItem getCompletionTaskById(int atrn_id) {
		try {
			String sql ="Select * from ttrn_action_item_transactional where atrn_id="+atrn_id;
			Query query = em.createNativeQuery(sql,TransactionalActionItem.class);
			return (TransactionalActionItem) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateCompletionTask(TransactionalActionItem item) {
		try {
			em.merge(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> searchActionItem(int exec_id ,String exec_frequency, String exec_from_due_date, String exec_to_due_date,
			HttpSession session) {
		try {
			String sql = " Select exec_action_item, exec_frequency, exec_due_date, exec_responsible_user_id, exec_first_alert_prior_days, exec_second_alert_prior_days, exec_action_id, exec_added_by from ttrn_execute_action_item "
					+ " where exec_contract_id = "+exec_id+" AND exec_frequency = '"+exec_frequency+"'";
					if(!exec_from_due_date.equals("") && !exec_to_due_date.equals("")){
					sql	+= " AND exec_due_date BETWEEN '"+exec_from_due_date+"' AND '"+exec_to_due_date+"'";
					}
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public int savetermrenwContract(TermRenwContract termrenwcontract) {
		try {
			em.persist(termrenwcontract);
			em.flush();
			return termrenwcontract.getTermren_contract_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public List<TermRenwContract> getTermRenwContract(){    
	    return template.query("select * from trn_termrenw_contract where termren_exec_contract_id = 580 ",new RowMapper<TermRenwContract>(){    
	        public TermRenwContract mapRow(ResultSet rs, int row) throws SQLException {    
	        	TermRenwContract tern=new TermRenwContract(); 
	        	tern.setTermren_contract_id(rs.getInt(1));
	        	tern.setTermren_contract_start_date(rs.getDate(2));
	          tern.setTermren_contract_status(rs.getString(3)); 
	          System.out.println(tern);
	            return tern;    
	        }    
	    });    
	}    
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getJoinedTermRenwContractRefDetails(int exec_id, HttpSession session) {
		try {
		
			//int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			String sql = "select termren_contract_start_date,termren_contract_end_date,u.user_first_name, u.user_last_name ,termren_contract_status,termren_contract_id from trn_termrenw_contract left join mst_user u on u.user_id =termren_responsible_person where termren_exec_contract_id = " + exec_id + " order by termren_contract_id DESC ";
			System.out.println(sql);
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public TermRenwContract getTermRenContractById(int termren_contract_id) {
		try {
			String sql = "SELECT * from trn_termrenw_contract where termren_contract_id=" + termren_contract_id;
			Query query = em.createNativeQuery(sql, TermRenwContract.class);
			if (!query.getResultList().isEmpty()) {
				return (TermRenwContract) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
