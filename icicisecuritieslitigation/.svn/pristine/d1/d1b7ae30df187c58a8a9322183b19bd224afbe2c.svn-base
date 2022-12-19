package lcmt.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import lcmt.domain.LegalNotice;
import lcmt.domain.LegalNoticeDocuments;
import lcmt.domain.LegalNoticeStatus;
import lcmt.domain.LegalNoticeStatusReference;
import lcmt.domain.LegalNotice_Reference;
import lcmt.domain.User;

public interface LegalNoticeService {

	public int saveLegalNotice(LegalNotice_Reference legalNotice_Reference,int id,ArrayList<MultipartFile> legal_doc, HttpSession session,String status);
	public List<LegalNotice_Reference> getAllLegalNotice(HttpSession session);
	public LegalNotice_Reference getLegalNoticeById(int lega_noti_id);
	public void updateLegalNotice(LegalNotice_Reference legalNotice_Reference, ArrayList<MultipartFile> legal_doc,String update,HttpSession session);
	public String searchLegalNotice(String jsonString,HttpSession session);
	public LegalNotice_Reference getJoinedDetailsLegalNotice(int lega_noti_id);
	public List<LegalNoticeDocuments> getAllLegalNoticeDocById(int lega_noti_id,int related_to);
	public void downloadLegalNoticeDoc(int lega_doc_id, HttpServletResponse response);
	public int savelegalNoticeStatus(LegalNoticeStatus legalNoticeStatus, ArrayList<MultipartFile> lega_status_doc ,HttpSession session, String status);
	public List<LegalNoticeStatusReference> getAllLegalNoticeStatus(int lega_noti_id);
	public LegalNoticeStatus getLegalStatusById(int lega_noti_status_id);
	public int updatelegalNoticeStatus(LegalNoticeStatus legalNoticeStatus,HttpSession session, ArrayList<MultipartFile> lega_status_doc, String status);
	public String getLegalNoticeStatusLogs(String json, HttpSession session); 
	public int deleteLegalNotice(int lega_noti_id);
	public int deleteLegalNoticeStatus(int lega_status_id);
	public List<LegalNoticeDocuments> getLegalNoticeStatusDocById(int lega_noti_status_id);
	public int deleteLegaNotiStatusDoc(int doc_id);
	public List<Object> getUserByLegalNoticeId(int orga_id, int loca_id, int dept_id);
	public LegalNotice getLegalNoticeBynotiId(int noti_id);
	
	public LegalNotice getAllListlegalNotice(int lega_noti_id, HttpSession session);
}

