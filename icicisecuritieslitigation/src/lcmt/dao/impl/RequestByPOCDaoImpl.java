package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;


import lcmt.dao.RequestByPOCDao;
import lcmt.domain.ContractHistoryDocuments;
import lcmt.domain.ContractParties;
import lcmt.domain.ContractRequest;
import lcmt.domain.LegalNoticeRequest;
import lcmt.domain.LitigationRequest;
import lcmt.domain.PocNegotiationDocument;
import lcmt.domain.PocStatusForNegotiation;
import lcmt.domain.QueryRaised;
import lcmt.domain.QueryRequest;
import lcmt.domain.RequestDocument;
import lcmt.domain.RequestRejectStatus;

@Repository(value="requestByPOCDao")
@Transactional
public class RequestByPOCDaoImpl implements RequestByPOCDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Object obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllListRequestByPOC(HttpSession session) {
		try {
			String sql = " Select orga.orga_name, loca.loca_name, dept.dept_name, req_related_to, req_id from trn_request_by_poc "
					+ " LEFT JOIN mst_organization orga ON orga.orga_id = req_entity_id "
					+ " LEFT JOIN mst_location loca ON loca.loca_id = req_unit_id "
					+ " LEFT JOIN mst_department  dept ON dept.dept_id = req_function_id "
					+ " where req_added_by = "+session.getAttribute("sess_user_id").toString();
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int saveContractRequest(ContractRequest req) {
		try {
			em.persist(req);
			return req.getReq_contract_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getLastGenerateValueForRequest(int req_id , String request_type) {
		try {
			String sql = "SELECT MAX(req_doc_last_generated_value_for_req_id) FROM trn_request_document where req_id= "+req_id+" AND req_doc_document_type= '"+request_type+"'";
			Query query = em.createNativeQuery(sql);
			if(query.getResultList().get(0) != null)
			return Integer.parseInt(query.getResultList().get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllListContractRequest(HttpSession session) {
		try {
			int role_id = Integer.parseInt(session.getAttribute("sess_user_role").toString());
			String sql = " SELECT type.cont_type_name, req_contract_date, req_contract_desc, req_party_no, req_contract_id, req_contract_approval_status, orga.orga_name, loca.loca_name, dept.dept_name, req_contract_added_by, req_contract_created_at FROM `trn_contract_request` LEFT join mst_contract_type type on req_contract_type = type.cont_type_id "
						+ " LEFT JOIN mst_organization orga ON orga.orga_id = req_contract_entity_id "
						+ " LEFT JOIN mst_location loca ON loca.loca_id = req_contract_unit_id "
						+ " LEFT JOIN mst_department  dept ON dept.dept_id = req_contract_function_id ";
			if(role_id == 3){
			sql += " where req_contract_added_by = "+Integer.parseInt(session.getAttribute("sess_user_id").toString());	
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
	public List<ContractParties> getAllPartyForRequest(int cont_id) {
		try {
			String sql = " select * from trn_contract_parties where cont_party_contract_id =?  AND cont_party_type = 3";
			Query query = em.createNativeQuery(sql, ContractParties.class);
			query.setParameter(1, cont_id);
			return query.getResultList();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RequestDocument> getAllRequestDocument(int req_id, String req_type) {
		try {
			String sql = "SELECT * FROM `trn_request_document` where req_id = "+req_id+" AND req_doc_document_type = '"+req_type+"'";
			Query query = em.createNativeQuery(sql, RequestDocument.class);
			if (!query.getResultList().isEmpty()) {
				return query.getResultList();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int saveQueryRequest(QueryRequest req) {
		try {
			em.persist(req);
			return req.getReq_query_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllListQueryRequest(HttpSession session) {
		try {
			int role_id = Integer.parseInt(session.getAttribute("sess_user_role").toString());
			String sql = " SELECT req_query, req_query_date, req_query_from, req_query_id, req_query_approval_status, orga.orga_name, loca.loca_name, dept.dept_name,req_query_added_by, req_query_criticality, req_query_turnaround_time FROM `trn_query_request` join mst_organization orga on req_query_entity_id = orga.orga_id join mst_location loca on req_query_unit_id= loca.loca_id join mst_department dept on req_query_function_id= dept.dept_id ";
			if(role_id == 3){
				sql += " where req_query_added_by = "+Integer.parseInt(session.getAttribute("sess_user_id").toString());	
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
	public List<Object> getAllListLitigtionRequest(HttpSession session) {
		try {
			int role_id = Integer.parseInt(session.getAttribute("sess_user_role").toString());
			String sql = "SELECT `req_liti_received_date`, `req_liti_party_by`, `req_liti_des`, `req_liti_id`, req_liti_approval_status, orga.orga_name, loca.loca_name, dept.dept_name, req_liti_added_by, req_liti_party_against FROM `trn_litigation_request` "
						+ " LEFT JOIN mst_organization orga ON orga.orga_id = req_liti_entity_id "
						+ " LEFT JOIN mst_location loca ON loca.loca_id = req_liti_unit_id "
						+ " LEFT JOIN mst_department  dept ON dept.dept_id = req_liti_function_id ";
			if(role_id == 3){
				sql += " where req_liti_added_by = "+Integer.parseInt(session.getAttribute("sess_user_id").toString());	
			}
				Query query = em.createNativeQuery(sql);
				return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int approveDisapproveRequest(int req_id, int req_status, String req_type) {
		try {
			if(req_type.equals("Contract Request")){
				
				String sql = "UPDATE `trn_contract_request` SET `req_contract_approval_status` = "+req_status+" WHERE `req_contract_id` = "+req_id; 
				Query query = em.createNativeQuery(sql);
				return query.executeUpdate();
			
			}else if(req_type.equals("Litigation Request")){
				
				String sql = "UPDATE `trn_litigation_request` SET `req_liti_approval_status` = "+req_status+"  WHERE `req_liti_id` ="+req_id; 
				Query query = em.createNativeQuery(sql);
				return query.executeUpdate();
				
			}else if(req_type.equals("Query Request")) {
				
				String sql = "UPDATE `trn_query_request` SET `req_query_approval_status` = "+req_status+" WHERE `req_query_id` = "+req_id; 
				Query query = em.createNativeQuery(sql);
				return query.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getRequestedUserIdByReqId(int req_id, String req_type) {
		try {
			if(req_type.equals("Query Request")) {
				
				String sql = " SELECT req_query_added_by FROM `trn_query_request` where req_query_id = "+req_id;
				Query query = em.createNativeQuery(sql);
				return Integer.parseInt(query.getResultList().get(0).toString());
			
			}else if(req_type.equals("Litigation Request")) {
				
				String sql = " SELECT req_liti_added_by FROM `trn_litigation_request` where req_liti_id = "+req_id;
				Query query = em.createNativeQuery(sql);
				return Integer.parseInt(query.getResultList().get(0).toString());
			
			}else if(req_type.equals("Contract Request")) {
				
				String sql = " SELECT req_contract_added_by FROM `trn_contract_request` where req_contract_id = "+req_id;
				Query query = em.createNativeQuery(sql);
				return Integer.parseInt(query.getResultList().get(0).toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String getRequestDocumentFilePath(int doc_id) {
		try {
			String sql = "select req_doc_generated_file_name from trn_request_document where req_doc_id = "
					+ doc_id;
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

	@Override
	public QueryRequest getQueryRequestById(int req_id) {
		try {
			String sql = " Select * FROM `trn_query_request` where req_query_id = "+req_id;
			Query query = em.createNativeQuery(sql, QueryRequest.class);
			return (QueryRequest) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ContractRequest getContractRequestById(int req_id) {
		try {
			String sql = " Select * FROM `trn_contract_request` where req_contract_id = "+req_id;
			Query query = em.createNativeQuery(sql, ContractRequest.class);
			if(query.getResultList() != null)
			return (ContractRequest) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LitigationRequest getLitiRequestById(int req_id) {
		try {
			String sql = " Select * FROM `trn_litigation_request` where req_liti_id = "+req_id;
			Query query = em.createNativeQuery(sql, LitigationRequest.class);
			return (LitigationRequest) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> searchRequestQuery(int entity_id, int unit_id, int function_id, String from_date,
			String to_date, HttpSession session) {
			try {
				String sql = "Select req_query, req_query_date, req_query_added_by, orga.orga_name, loca.loca_name, dept.dept_name, req_query_id, req_query_approval_status, req_query_criticality, req_query_turnaround_time from trn_query_request join mst_organization orga on req_query_entity_id = orga_id join mst_location loca on req_query_unit_id = loca.loca_id join mst_department dept on req_query_function_id = dept.dept_id ";
						sql += " where req_query_entity_id = "+entity_id;
					if(unit_id > 0){
						sql += " AND req_query_unit_id = "+unit_id;	
						}
					if(function_id > 0){
						sql += " AND req_query_function_id = "+function_id;
						}
					if(!from_date.equals("") || !to_date.equals("")){
						sql += " AND req_query_date BETWEEN '" + from_date + "' AND '" + to_date + "'";
					}
					if(Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 3){
					   sql	+= " AND req_query_added_by = "+Integer.parseInt(session.getAttribute("sess_user_id").toString());
					}
				Query query = em.createNativeQuery(sql);
				return query.getResultList();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public int saveLitiRequest(LitigationRequest req) {
		try {
			em.persist(req);
			return req.getReq_liti_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> searchRequestLitigation(int entity_id, int unit_id, int function_id, String from_date,
			String to_date, HttpSession session) {
		try {
			String sql =" SELECT req_liti_received_date, req_liti_party_by, req_liti_des, req_liti_id, req_liti_added_by, orga.orga_name, loca.loca_name, dept.dept_name, req_liti_approval_status, req_liti_party_against FROM `trn_litigation_request` join mst_organization orga on req_liti_entity_id = orga_id join mst_location loca on req_liti_unit_id = loca.loca_id join mst_department dept on req_liti_function_id = dept.dept_id ";
				sql += " where req_liti_entity_id = "+entity_id;
			if(unit_id > 0){
				sql += " AND req_liti_unit_id = "+unit_id;	
				}
			if(function_id > 0){
				sql += " AND req_liti_function_id = "+function_id;
				}
			if(!from_date.equals("") || !to_date.equals("")){
				sql += " AND req_liti_received_date BETWEEN '" + from_date + "' AND '" + to_date + "'";
			}
			if(Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 3){
			   sql	+= " AND req_liti_added_by = "+Integer.parseInt(session.getAttribute("sess_user_id").toString());
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
	public List<Object> searchRequestContract(int entity_id, int unit_id, int function_id, String from_date,
			String to_date, HttpSession session) {
		try {
			String sql = " SELECT type.cont_type_name, req_contract_date, req_contract_desc, req_party_no, req_contract_id, req_contract_approval_status, orga.orga_name, loca.loca_name, dept.dept_name, req_contract_added_by, req_contract_created_at FROM `trn_contract_request` left join mst_contract_type type on req_contract_type = type.cont_type_id "
					+ "left JOIN mst_organization orga ON orga.orga_id = req_contract_entity_id "
					+ "left JOIN mst_location loca ON loca.loca_id = req_contract_unit_id "
					+ "left JOIN mst_department dept ON dept.dept_id = req_contract_function_id where req_contract_entity_id ="+entity_id;
			if(unit_id > 0){
				sql += " AND req_contract_unit_id = "+unit_id;	
				}
			if(function_id > 0){
				sql += " AND req_contract_function_id = "+function_id;
				}
			if(!from_date.equals("") || !to_date.equals("")){
				sql += " AND req_contract_date BETWEEN '" + from_date + "' AND '" + to_date + "'";
			}
			if(Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 3){
			   sql	+= " AND req_contract_added_by = "+Integer.parseInt(session.getAttribute("sess_user_id").toString());
			}
		Query query = em.createNativeQuery(sql);
		return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public RequestRejectStatus getAllRequestStatus(int req_id, String req_type, HttpSession session) {
		try {
			String sql = " Select * FROM `trn_req_reject_status` where req_related_id = "+req_id+" AND req_related_to ='"+req_type+"'";
			Query query = em.createNativeQuery(sql, RequestRejectStatus.class);
			if(query.getResultList() != null)
			return (RequestRejectStatus) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Object obj) {
		try {
			em.merge(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @SuppressWarnings("unchecked")
	@Override
	public <T> List<T> listRaisedQueries(String related_to, int related_id) {
		try {
			String sql = "SELECT tqr.trn_query_id,tqr.trn_query, qu.user_first_name as raised_fname,qu.user_last_name as raised_lname, tqr.trn_query_answer, qau.user_first_name as ans_fname,qau.user_last_name as ans_lname,tqr.trn_query_related_to, trn_created_at, trn_query_answer_at FROM trn_query_raised tqr "
					+ "JOIN mst_user qu ON qu.user_id = tqr.trn_query_raised_by_id "
					+ "LEFT JOIN mst_user qau ON qau.user_id = tqr.trn_query_answer_by_id where tqr.trn_query_related_id =:related_id AND tqr.trn_query_related_to=:related_to";
			Query query = em.createNativeQuery(sql);
			query.setParameter("related_to", related_to);
			query.setParameter("related_id", related_id);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public QueryRaised getRasiedQuery(int id) {
		try {
			 @SuppressWarnings("rawtypes")
			TypedQuery query = em.createQuery(" FROM "+QueryRaised.class.getName()+" WHERE trn_query_id =:id",QueryRaised.class);
			 query.setParameter("id", id);
			 return (QueryRaised) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateObject(Object object) {
		try {
			 em.merge(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public LitigationRequest getAllListLitigationRequest(int liti_id, HttpSession session) {
		try {
			String sql = " Select * FROM `trn_litigation_request` where req_liti_id = "+liti_id;
			Query query = em.createNativeQuery(sql, LitigationRequest.class);
			if(query.getResultList() != null)
			return (LitigationRequest) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public QueryRequest getAllQueryRequest(int quer_id, HttpSession session) {
		try {
			String sql = " Select * FROM `trn_query_request` where req_query_id = "+quer_id;
			Query query = em.createNativeQuery(sql, QueryRequest.class);
			if(query.getResultList() != null)
			return (QueryRequest) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ContractRequest getAllContractrequest(int cont_id, HttpSession session) {
		try {
			String sql = " Select * FROM `trn_contract_request` where req_contract_id = "+cont_id;
			Query query = em.createNativeQuery(sql, ContractRequest.class);
			if(query.getResultList() != null)
			return (ContractRequest) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateQueryRequest(QueryRequest req) {
		try {
			em.merge(req);
			return req.getReq_query_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllPocStatusForNegotiation(int id) {
		try {
			String sql = "SELECT poc_status_id,poc_action_item, poc_status, poc_status_date, poc_status_created_at, poc_status_added_by, poc_contract_req_id, poc_contract_id FROM trn_poc_status_negotiation "
					+ " where poc_contract_req_id = "+id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int savePocStatus(PocStatusForNegotiation poc) {
		try {
			em.persist(poc);
			return poc.getPoc_status_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getLastGenerateValueForPocDoc(int req_id) {
		try {
			String sql ="SELECT MAX(poc_doc_last_generated_value_for_contract_id) FROM trn_poc_document where poc_doc_contract_id="+req_id;
			Query query = em.createNativeQuery(sql);
			if (query.getResultList().get(0) != null) {
				return Integer.parseInt(query.getResultList().get(0).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PocNegotiationDocument> getAllPocStatusDocument(int cont_id) {
		try {
			String sql = "SELECT * FROM trn_poc_document where poc_doc_contract_id=" + cont_id;
			Query query = em.createNativeQuery(sql, PocNegotiationDocument.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getPocDocumentFilePath(int doc_id) {
		try {
			String sql = "SELECT poc_doc_generated_file_name FROM trn_poc_document where poc_doc_id = " + doc_id;
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

	@Override
	public PocStatusForNegotiation getPocStatusByStatusId(int status_id) {
		try {
			String sql = " Select * FROM `trn_poc_status_negotiation` where poc_status_id = "+status_id;
			Query query = em.createNativeQuery(sql, PocStatusForNegotiation.class);
			return (PocStatusForNegotiation) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PocNegotiationDocument getPocDocumentById(int status_id) {
		try {
			String sql = "SELECT * FROM trn_poc_document where poc_doc_contract_id=" + status_id;
			Query query = em.createNativeQuery(sql, PocNegotiationDocument.class);
			if(query.getResultList() != null)
			return (PocNegotiationDocument) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllPocStatusForNegotiationByContractId(int cont_id) {
		try {
			String sql = "SELECT poc_status_id,poc_action_item, poc_status, poc_status_date, poc_status_created_at, poc_status_added_by, poc_contract_req_id, poc_contract_id FROM trn_poc_status_negotiation "
					+ " where poc_contract_id = "+cont_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int saveNoticeRequest(LegalNoticeRequest req) {
		try {
			em.persist(req);
			return req.getReq_noti_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllListNoticeRequest(HttpSession session) {
		try {
			String sql = "SELECT `req_noti_date`, `req_noti_oppo_party`, `req_noti_des`, `req_noti_id`, req_noti_approval_status, orga.orga_name, loca.loca_name, dept.dept_name, req_noti_added_by, req_noti_type_by_against, req_noti_created_at FROM `trn_notice_request` LEFT JOIN mst_organization orga ON orga.orga_id = req_noti_entity_id  LEFT JOIN mst_location loca ON loca.loca_id = req_noti_unit_id LEFT JOIN mst_department dept ON dept.dept_id = req_noti_function_id ";
					if(Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 3){
						sql	+= " AND req_noti_added_by ="+Integer.parseInt(session.getAttribute("sess_user_id").toString());
					}
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LegalNoticeRequest getAllNoticerequest(int noti_id, HttpSession session) {
		try {
			String sql = " Select * FROM `trn_notice_request` where req_noti_id = "+noti_id;
			Query query = em.createNativeQuery(sql, LegalNoticeRequest.class);
			if(query.getResultList() != null)
			return (LegalNoticeRequest) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> searchRequestNotice(int entity_id, int unit_id, int function_id, String from_date,
			String to_date, HttpSession session) {
		try {
			String sql = " SELECT `req_noti_date`, `req_noti_oppo_party`, `req_noti_des`, `req_noti_id`, req_noti_approval_status, orga.orga_name, loca.loca_name, dept.dept_name, req_noti_added_by, req_noti_type_by_against, req_noti_created_at FROM `trn_notice_request` LEFT JOIN mst_organization orga ON orga.orga_id = req_noti_entity_id  LEFT JOIN mst_location loca ON loca.loca_id = req_noti_unit_id LEFT JOIN mst_department dept ON dept.dept_id = req_noti_function_id "
					+ " where req_noti_entity_id ="+entity_id;
			if(unit_id > 0){
				sql += " AND req_noti_unit_id = "+unit_id;	
				}
			if(function_id > 0){
				sql += " AND req_noti_function_id = "+function_id;
				}
			if(!from_date.equals("") || !to_date.equals("")){
				sql += " AND req_noti_date BETWEEN '" + from_date + "' AND '" + to_date + "'";
			}
			if(Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 3){
			   sql	+= " AND req_noti_added_by = "+Integer.parseInt(session.getAttribute("sess_user_id").toString());
			}
		Query query = em.createNativeQuery(sql);
		return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}

