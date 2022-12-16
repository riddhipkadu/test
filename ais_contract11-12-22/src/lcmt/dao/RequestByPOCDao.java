package lcmt.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

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

public interface RequestByPOCDao {
	
	public void save(Object obj);
	public List<Object> getAllListRequestByPOC(HttpSession session);
	public int saveContractRequest(ContractRequest req);
	public int getLastGenerateValueForRequest(int req_id , String request_type);
	public List<Object> getAllListContractRequest(HttpSession session);
	public List<ContractParties> getAllPartyForRequest(int cont_id);
	public List<RequestDocument> getAllRequestDocument(int req_id, String req_type);
	public int saveQueryRequest(QueryRequest req);
	public List<Object> getAllListQueryRequest(HttpSession session);
	public List<Object> getAllListLitigtionRequest(HttpSession session);
	public int approveDisapproveRequest(int req_id,int req_status,String req_type);
	public int getRequestedUserIdByReqId(int req_id,String req_type);
	public String getRequestDocumentFilePath(int doc_id);
	public QueryRequest getQueryRequestById(int req_id);
	public ContractRequest getContractRequestById(int req_id);
	public LitigationRequest getLitiRequestById(int req_id);
	public List<Object> searchRequestQuery(int entity_id, int unit_id, int function_id, String from_date, String to_date, HttpSession session);
	public int saveLitiRequest(LitigationRequest req);
	public List<Object> searchRequestLitigation(int entity_id, int unit_id, int function_id, String from_date, String to_date, HttpSession session);
	public List<Object> searchRequestContract(int entity_id, int unit_id, int function_id, String from_date, String to_date, HttpSession session);
	public RequestRejectStatus getAllRequestStatus(int req_id, String req_type,HttpSession session);
	public void update(Object obj);
	public LitigationRequest getAllListLitigationRequest(int liti_id, HttpSession session);
	public <T>List<T> listRaisedQueries(String related_to,int related_id);
	public QueryRaised getRasiedQuery(int id);
	public void updateObject(Object object);
	public QueryRequest getAllQueryRequest(int quer_id, HttpSession session);
	public ContractRequest getAllContractrequest(int cont_id, HttpSession session);
	public int updateQueryRequest(QueryRequest req);
	public List<Object> getAllPocStatusForNegotiation(int id);
	public int savePocStatus(PocStatusForNegotiation poc);
	public int getLastGenerateValueForPocDoc(int req_id);
	public List<PocNegotiationDocument> getAllPocStatusDocument(int cont_id);
	public String getPocDocumentFilePath(int doc_id);
	public PocStatusForNegotiation getPocStatusByStatusId(int status_id);
	public PocNegotiationDocument getPocDocumentById(int status_id);
	public List<Object> getAllPocStatusForNegotiationByContractId(int cont_id);
	public int saveNoticeRequest(LegalNoticeRequest req);
	public List<Object> getAllListNoticeRequest(HttpSession session);
	public LegalNoticeRequest getAllNoticerequest(int noti_id, HttpSession session);
	public List<Object> searchRequestNotice(int entity_id, int unit_id, int function_id, String from_date, String to_date, HttpSession session);
}
