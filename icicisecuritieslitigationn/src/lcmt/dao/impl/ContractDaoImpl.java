package lcmt.dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import lcmt.dao.ContractDao;
import lcmt.domain.Contract;
import lcmt.domain.ContractContractType;
import lcmt.domain.ContractDocuments;
import lcmt.domain.ContractHistory;
import lcmt.domain.ContractHistoryDocuments;
import lcmt.domain.ContractParties;
import lcmt.domain.ContractType;

/*
 * Author: Mahesh Kharote
 * Date: 27/09/2016
 * Updated By:
 * Updated Date:
 * 
 * */

@Repository(value = "contractDao")
@Transactional
public class ContractDaoImpl implements ContractDao {

	@PersistenceContext
	private EntityManager em;

	// Method Created : Mahesh Kharote
	// Method Purpose : Save Contract For Execution page
	@Override
	public void persist(Object obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get Contract details by id
	@Override
	public Contract getContractById(int cont_id) {
		try {
			String sql = "select * from mst_contracts where cont_id =" + cont_id;
			Query query = em.createNativeQuery(sql, Contract.class);
			return (Contract) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Mahesh Kharote
	// Method Purpose : Get Contract details For Execution page list
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getJoinedContractDetails(HttpSession session) {
		try {
			String sql = null;
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			// For admin
			if (Integer.parseInt(session.getAttribute("sess_user_role").toString()) != 2 ) {
				sql = "SELECT orga_name, loca_name, dept_name, cont_agreement_name, cont_requested_by_user_id , cont_requested_date,cont_expected_date,u.user_first_name, u.user_last_name as resposibleperson,cont_targetted_date,cont_id,cont_status,cont_added_by FROM mst_contracts LEFT join mst_organization on orga_id = cont_orga_id LEFT join mst_location on loca_id = cont_loca_id LEFT join mst_department on dept_id = cont_dept_id LEFT join mst_user u on u.user_id = cont_responsible_user_id where cont_status != 'Closed/Executed'";
			}
			// Normal User
			else {
				sql = "SELECT orga_name, loca_name, dept_name, cont_agreement_name, cont_requested_by_user_id , cont_requested_date,cont_expected_date,u.user_first_name, u.user_last_name as resposibleperson,cont_targetted_date,cont_id,cont_status,cont_added_by FROM mst_contracts LEFT join mst_organization on orga_id = cont_orga_id LEFT join mst_location on loca_id = cont_loca_id LEFT join mst_department on dept_id = cont_dept_id LEFT join mst_user u on u.user_id = cont_responsible_user_id where cont_status != 'Closed/Executed' AND (cont_responsible_user_id="
						+ user_id + " OR cont_added_by=" + user_id
						+ " OR cont_responsible_user_id IN(select user_id from mst_user where user_report_to=" + user_id
						+ ") OR cont_added_by IN(select user_id from mst_user where user_report_to=" + user_id + "))";
			}
			//System.out.println("QUERY "+sql);
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Mahesh Kharote
	// Method Purpose : Get Contract details For Execution page list
	@Override
	public Object[] getJoinedContractDetailsById(int cont_id) {
		try {
			String sql = "SELECT orga_name, loca_name, dept_name, cont_agreement_name, cont_major_clause , cont_requested_date,cont_expected_date,u.user_first_name, u.user_last_name as resposibleperson,cont_targetted_date,cont_id,cont_status,cont_orga_id,cont_loca_id,cont_dept_id,cont_responsible_user_id,cont_purpose, cont_criticality, cont_nature, cont_term,cont_payment,cont_surviving_clause, user.user_first_name as fname, user.user_last_name as requestedperson, cont_requested_by_user_id, cont_start_date, cont_end_date, cont_damages, cont_instructions, cont_reminder_date, cont_req_id FROM mst_contracts "
					+ " join mst_organization on orga_id = cont_orga_id "
					+ " join mst_location on loca_id = cont_loca_id "
					+ " join mst_department on dept_id = cont_dept_id "
					+ " LEFT join mst_user u on u.user_id = cont_responsible_user_id"
					+ " LEFT join mst_user user on user.user_id = cont_requested_by_user_id where cont_id ="+ cont_id;
			Query query = em.createNativeQuery(sql);
			return (Object[]) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Mahesh Kharote
	// Method Purpose : Save Contract history
	@Override
	public int persistContractHistory(ContractHistory obj) {
		try {
			em.persist(obj);
			em.flush();
			return obj.getChst_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Mahesh Kharote
	// Method Purpose : Get Contract details For Execution page list
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractHistory> getContractHistoryById(int cont_id) {
		try {
			String sql = "SELECT * FROM trn_contract_history where chst_contract_id = " + cont_id
					+ " order by chst_id desc";
			Query query = em.createNativeQuery(sql, ContractHistory.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Harshad Padole
	// Method Purpose : get distinct contract type which exist in contract table
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getContractTypesForPreExeContracts(HttpSession session) {
		try {
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			String sql = "SELECT distinct cont_type_id,cont_type_name FROM mst_contracts con JOIN trn_contract_contract_type con_type ON con_type.cont_contract_id = con.cont_id JOIN mst_contract_type ON cont_type_id = con_type.cont_contract_type_id where cont_responsible_user_id ="
					+ user_id + " OR cont_added_by=" + user_id
					+ " OR cont_responsible_user_id IN(select user_id from mst_user where user_report_to=" + user_id
					+ ") OR cont_added_by IN(select user_id from mst_user where user_report_to=" + user_id + ")";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Search pre execution Contract
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> searchContract(String json, HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdfOut = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
			int orga_id = Integer.parseInt(jsonObject.get("pre_orga_id").toString());
			int loca_id = Integer.parseInt(jsonObject.get("pre_loca_id").toString());
			int dept_id = Integer.parseInt(jsonObject.get("pre_dept_id").toString());
			String status = jsonObject.get("pre_status").toString();
			String cont_criticality = jsonObject.get("cont_criticality").toString();
			int pre_contract_type = Integer.parseInt(jsonObject.get("pre_contract_type").toString());
			
			String targeted_date_from = jsonObject.get("pre_tergeted_date_from").toString();
			String targeted_date_to = jsonObject.get("pre_tergeted_date_to").toString();
			
			int pre_responsible_user_id = Integer.parseInt(jsonObject.get("pre_responsible_user_id").toString());
			
			int user_role = Integer.parseInt(session.getAttribute("sess_user_role").toString());
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			@SuppressWarnings("unused")
			int user_report_to = Integer.parseInt(session.getAttribute("sess_user_report_to").toString());
			String sql = "SELECT orga_name, loca_name, dept_name, cont_agreement_name, cont_requested_by_user_id ,cont_requested_date,cont_expected_date,u.user_first_name , u.user_last_name as resposibleperson,cont_targetted_date,cont_id,cont_status,cont_added_by FROM mst_contracts "
					+ " join mst_organization on orga_id = cont_orga_id "
					+ " join mst_location on loca_id = cont_loca_id "
					+ " join mst_department on dept_id = cont_dept_id "
					+ " LEFT join mst_user u on u.user_id = cont_responsible_user_id " + " where cont_orga_id = " + orga_id;

			if (loca_id > 0)
				sql += " AND cont_loca_id= " + loca_id;

			if (dept_id > 0)
				sql += " AND cont_dept_id= " + dept_id;

			if (!status.equals("NA")) {
				sql += " AND cont_status = '" + status + "'";
			} else {
				sql += " AND cont_status != 'Closed/Executed'";
			}
			
			if(pre_responsible_user_id > 0)
				sql += " AND cont_responsible_user_id ="+pre_responsible_user_id;
			
			if(!cont_criticality.equals("NA"))
				sql += " AND cont_criticality = '"+cont_criticality+"'";
			
			
			
			if (!targeted_date_from.equals("") && !targeted_date_to.equals("")) {
				targeted_date_from = sdfOut.format(sdfIn.parse(targeted_date_from));
				targeted_date_to = sdfOut.format(sdfIn.parse(targeted_date_to));
				sql += " AND cont_targetted_date  Between '" + targeted_date_from + "' AND '" + targeted_date_to + "'";
			}

			if (user_role == 2) {
				sql += " AND (cont_added_by= " + user_id + " OR cont_responsible_user_id= " + user_id
						+ " OR cont_responsible_user_id IN(select user_id from mst_user where user_report_to=" + user_id
						+ ") OR cont_added_by IN(select user_id from mst_user where user_report_to=" + user_id + "))";
			}
			if (pre_contract_type > 0)
				sql += " AND cont_id IN (select DISTINCT cont_contract_id from trn_contract_contract_type where cont_contract_type = 1 AND cont_contract_type_id ="
						+ pre_contract_type + ")";
			
				
			// System.out.println("search "+sql);
			Query query = em.createNativeQuery(sql);
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get latest Contract
	@Override
	public String getLatestContractStatusByContraId(int contra_id) {
		try {
			String sql = "SELECT chst_status FROM trn_contract_history where chst_contract_id = " + contra_id
					+ " ORDER BY chst_id DESC limit 1";
			Query query = em.createNativeQuery(sql);
			if (!query.getResultList().isEmpty()) {
				return query.getResultList().get(0).toString();
			} else {
				return "Pending";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Common method for update record
	@Override
	public void updateRecord(Object object) {
		try {
			em.merge(object);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created : Harshad Padole
	// Method Purpose : Get last generated id for contract
	@Override
	public int getLastGenerateValueForContract(int contra_id) {
		try {
			String sql = " SELECT MAX(cdoc_last_generated_value_for_contract_id) FROM trn_contract_documents where cdoc_contract_id= "
					+ contra_id;
			Query query = em.createNativeQuery(sql);

			if (query.getResultList().get(0) != null) {
				return Integer.parseInt(query.getResultList().get(0).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method Purpose : SAVE CONTRACT DETAILS
	@Override
	public int saveContract(Contract contract) {
		try {
			em.persist(contract);
			em.flush();
			return contract.getCont_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get all documents by contract id
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractDocuments> getAllContractDocumentsByContractId(int contract_id) {
		try {
			String sql = "select * from trn_contract_documents where cdoc_contract_id=" + contract_id;
			Query query = em.createNativeQuery(sql, ContractDocuments.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get last generated id for contract history
	@Override
	public int getLastGenerateValueForContractHistory(int contra_history_id) {
		try {
			String sql = "SELECT MAX(chst_doc_last_generated_value_for_contract_history_id) FROM trn_contract_history_documents where chst_doc_contract_history_id="
					+ contra_history_id;
			Query query = em.createNativeQuery(sql);

			if (query.getResultList().get(0) != null) {
				return Integer.parseInt(query.getResultList().get(0).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get all history documents by contract history id
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractHistoryDocuments> getAllHistoryDocumentsByHstId(int hst_id) {
		try {
			String sql = "select * from trn_contract_history_documents where chst_doc_contract_history_id =" + hst_id;
			// System.out.println("History doc query "+sql);
			Query query = em.createNativeQuery(sql, ContractHistoryDocuments.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created : Tejashri Zurunge
	// Method Purpose : download documents by contract id
	@Override
	public String getPreExecutionDocumentFilePath(int cdoc_id) {
		try {
			String sql = "SELECT cdoc_generated_file_name FROM trn_contract_documents where cdoc_id = " + cdoc_id;
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

	// Method created : Tejashri Zurunge
	// Method Purpose : download history documents by contract history id
	@Override
	public String getPreExecutionHistoryDocumentFilePath(int chst_doc_id) {
		try {
			String sql = "select chst_doc_generated_file_name from trn_contract_history_documents where chst_doc_id = "
					+ chst_doc_id;
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

	// Method created : Harshad Padole
	// Method Purpose : Save Contract type list as per contract id
	@Override
	public void saveContractTypeList(Object object) {
		try {
			em.persist(object);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method created : Harshad Padole
	// Method Purpose : get Contract type list as per contract id
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractContractType> getContractTypeByContractId(int contract_id) {
		try {
			String sql = "select * from trn_contract_contract_type JOIN mst_contract_type on cont_type_id = cont_contract_type_id where cont_contract_id = ? AND cont_contract_type = 1";
			Query query = em.createNativeQuery(sql, ContractContractType.class);
			query.setParameter(1, contract_id);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created : Harshad Padole
	// Method Purpose : get Contract party list as per contract id
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractParties> getContractPartiesByContractId(int contract_id) {
		try {
			String sql = " select * from trn_contract_parties where cont_party_contract_id =?  AND cont_party_type = 1";
			Query query = em.createNativeQuery(sql, ContractParties.class);
			query.setParameter(1, contract_id);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: get contract history by history id
	@Override
	public ContractHistory getContractHistoryByHistoryId(int chst_id) {
		try {
			String sql = "SELECT * FROM trn_contract_history where chst_id =  " + chst_id;
			Query query = em.createNativeQuery(sql, ContractHistory.class);
			return (ContractHistory) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method created : Harshad Padole
	// Method Purpose : delete contract type as per contract id
	@Override
	public void deleteContractType(int id, int type) {
		try {
			String sql = " DELETE FROM trn_contract_contract_type where cont_contract_id = ? AND cont_contract_type = ?";
			Query query = em.createNativeQuery(sql);
			query.setParameter(1, id);
			query.setParameter(2, type);
			query.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: delete contract party
	@Override
	public int deletePreExecutedParty(int party_id) {
		try {
			String sql = "delete from trn_contract_parties where cont_party_id= " + party_id;
			Query query = em.createNativeQuery(sql);
			int deleteCount = query.executeUpdate();
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: delete contract party
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractType> getContractTypeById(int cont_id) {
		try {
			String sql = "Select * from mst_contract_type join trn_contract_contract_type cont on cont_type_id = cont.cont_contract_type_id where cont.cont_contract_id = "
					+ cont_id + " AND cont.cont_contract_type = 1";
			Query query = em.createNativeQuery(sql, ContractType.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: delete contract party
	@Override
	public int deletePreExecutedContract(int cont_id) {
		try {
			String sql_one = "Delete from mst_contracts where cont_id =" + cont_id;
			String sql_two = "Delete from trn_contract_history where chst_contract_id =" + cont_id;
			String sql_three = "Delete from trn_contract_history_documents where chst_doc_contract_id = " + cont_id;
			String sql_four = "DELETE FROM trn_contract_contract_type where cont_contract_id = " + cont_id
					+ " AND cont_contract_type = 1";
			String sql_five = "Delete from trn_contract_documents where cdoc_contract_id = " + cont_id;
			String sql_six = "Delete from trn_contract_parties where cont_party_contract_id= " + cont_id
					+ " AND cont_party_type = 1";
			Query query1 = em.createNativeQuery(sql_one);
			Query query2 = em.createNativeQuery(sql_two);
			Query query3 = em.createNativeQuery(sql_three);
			Query query4 = em.createNativeQuery(sql_four);
			Query query5 = em.createNativeQuery(sql_five);
			Query query6 = em.createNativeQuery(sql_six);
			query1.executeUpdate();
			query2.executeUpdate();
			query3.executeUpdate();
			query4.executeUpdate();
			query5.executeUpdate();
			query6.executeUpdate();
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContractContractType> getContractType(int id, int type_id) {
		try {
			String sql = "select * from trn_contract_contract_type JOIN mst_contract_type on cont_type_id = cont_contract_type_id where cont_contract_id = ? AND cont_contract_type =? ";
			Query query = em.createNativeQuery(sql, ContractContractType.class);
			query.setParameter(1, id);
			query.setParameter(2, type_id);
			// System.out.println(" res "+"select * from
			// trn_contract_contract_type JOIN mst_contract_type on cont_type_id
			// = cont_contract_type_id where cont_contract_id = "+id+" AND
			// cont_contract_type ="+type_id+"");
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: delete contract party
	@Override
	public int deletePreExecutedContractHistory(int chst_id) {
		try {
			String sql = "Delete from trn_contract_history where chst_id =" + chst_id;
			Query query = em.createNativeQuery(sql);
			int deleteCount = query.executeUpdate();
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deletePreExecutedContractDocument(int cdoc_id) {
		try {
			String sql = "Delete from trn_contract_documents where cdoc_id = " + cdoc_id;
			Query query = em.createNativeQuery(sql);
			query.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteContractHistoryDoc(int doc_id) {
		try {
			String sql = "Delete from trn_contract_history_documents where chst_doc_id = " + doc_id;
			Query query = em.createNativeQuery(sql);
			query.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Contract getContractByRequestId(int req_id) {
		try {
			String sql = "select * from mst_contracts where cont_req_id =" + req_id;
			Query query = em.createNativeQuery(sql, Contract.class);
			if (query.getResultList().get(0) != null) {
			return (Contract) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
}
