package lcmt.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import lcmt.domain.ContractRequest;
import lcmt.domain.ContractRequestReference;
import lcmt.domain.LegalNoticeRequest;
import lcmt.domain.LitigationRequest;
import lcmt.domain.PocStatusForNegotiation;
import lcmt.domain.PocStatusNegotiationReference;
import lcmt.domain.QueryRequest;
import lcmt.domain.RequestByPOCReference;
import lcmt.domain.RequestRejectStatusReference;

public interface RequestByPOCService {

	//public void save(RequestByPOC poc, HttpSession session);
	public List<RequestByPOCReference> getAllListRequestByPOC(HttpSession session);
	public void saveContractRequest(ContractRequest req, ArrayList<String> additional_parties, ArrayList<MultipartFile> contract_doc, 
			 ArrayList<MultipartFile> term_sheet_doc, HttpSession session, String status);
	public List<RequestByPOCReference> getAllListContractRequest(HttpSession session);
	public void saveQueryRequest(QueryRequest req, ArrayList<MultipartFile> query_doc,String status, HttpSession session);
	public List<RequestByPOCReference> getAllListQueryRequest(HttpSession session);
	public void saveLitigationRequest(LitigationRequest req, ArrayList<MultipartFile> liti_doc,String status, HttpSession session);
	public List<RequestByPOCReference> getAllListLitigationRequest(HttpSession session);
	public int approveDisapproveRequest(int req_id,int req_status,String req_type, String reason, HttpSession session);
	public void downloadRequestDocument(int doc_id, HttpServletResponse response);
	public String searchRequestQuery(int entity_id, int unit_id, int function_id, String from_date, String to_date, HttpSession session);
	public String searchRequestLitigation(int entity_id, int unit_id, int function_id, String from_date, String to_date, HttpSession session);
	public String searchRequestContract(int entity_id, int unit_id, int function_id, String from_date, String to_date, HttpSession session);
	public List<Object> getQueryRaisedList(String related_to,int related_id);
	public String raiseQuery(String json,HttpSession session);
	public String raiseQueryReply(String json,HttpSession session);
	public LitigationRequest getAllListLitigationRequest(int liti_id, HttpSession session);
	public QueryRequest getAllQueryRequest(int quer_id, HttpSession session);
	public ContractRequest getAllContractrequest(int cont_id, HttpSession session);
	public void updateQueryRequest(QueryRequest req, ArrayList<MultipartFile> query_doc,String status, HttpSession session);
	public void updateLitiRequest(LitigationRequest req, ArrayList<MultipartFile> liti_doc,String status, HttpSession session);
	public ContractRequestReference getContractrequestById(int cont_id, HttpSession session);
	public void updateContractRequest(ContractRequestReference req, ArrayList<String> additional_parties, ArrayList<MultipartFile> contract_doc, 
			 ArrayList<MultipartFile> term_sheet_doc, HttpSession session, String status);
	public void saveContractStatusForNegotiation(PocStatusForNegotiation poc, ArrayList<MultipartFile> contract_doc, HttpSession session);
	public List<PocStatusNegotiationReference> getAllContractStatusNegotiation(int id);	
	public void downloadPOCDocument(int doc_id, HttpServletResponse response);
	public String approveDisapproveStatusSentNegotiation(int req_id,int req_status,String reason,HttpSession session);
	public List<PocStatusNegotiationReference> getAllContractStatusNegotiationByContractId(int cont_id);
	public RequestRejectStatusReference getAllRejectStatus(int related_id,String related_to,HttpSession session);
	public void saveNoticeRequest(LegalNoticeRequest req, ArrayList<MultipartFile> noti_doc,String status, HttpSession session);
	public List<RequestByPOCReference> getAllListNoticeRequest(HttpSession session);
	public LegalNoticeRequest getAllNoticerequest(int noti_id, HttpSession session);
	public void updateNoticeRequest(LegalNoticeRequest req,ArrayList<MultipartFile> noti_doc,String status,HttpSession session);
	public String searchRequestNotice(int entity_id, int unit_id, int function_id, String from_date, String to_date, HttpSession session);
}
