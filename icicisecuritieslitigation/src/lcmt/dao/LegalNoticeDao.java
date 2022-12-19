package lcmt.dao;

import java.util.List;

import javax.servlet.http.HttpSession;
import lcmt.domain.LegalNotice;
import lcmt.domain.LegalNoticeDocuments;
import lcmt.domain.LegalNoticeStatus;

public interface LegalNoticeDao {
     public int persist(LegalNotice legalNotice);
     public List<Object> getAllLegalNotice(HttpSession session);
     public LegalNotice getLegalNoticeById(int lega_noti_id);
     public void updateLegalNotice(LegalNotice legalNotice);

	public List<Object> searchLegalNotice(int lega_noti_entity_id,
			/* int lega_noti_unit_id, int lega_noti_function_id, */ String lega_noti_by_against, int lega_noti_category_id, int lega_noti_assigned_to_id,int lega_noti_secondary_responsible_person, int lega_noti_third_responsible_person, String scau_notice_from_date, String scau_notice_to_date, HttpSession session);
     public int getLastGenerateValueForLegalNotice(int lega_noti_id,int related_to);
     public void saveLegalDocuments(LegalNoticeDocuments documents);
     public Object[] getJoinedDetailsLegalNotice(int lega_noti_id);
     public List<LegalNoticeDocuments> getAllLegalNoticeDocById(int lega_noti_id,int related_to);
     public String getLegalNoticeDocFilePath(int lega_doc_id); 
     public int saveLegalNoticeStatus(LegalNoticeStatus legalNoticeStatus);
     public <T>List<T> getAllLegalStatus(int lega_noti_id);
     public LegalNoticeStatus getLegalStatusById(int lega_noti_id);
     public void merge(Object object);
     public int saveLogs(Object obj);
     public <T> List<T> getLegalNoticeStatusLogs(String json);
     public int deleteLegalNotice(int lega_noti_id); 
     public int deleteLegalNoticeStatus(int lega_status_id);
     public int deleteLegaNotiStatusDoc(int doc_id);
     public String getLatestActionTaken(int noti_id);
     public <T> List<T> getUserByLegalNoticeId(int orga_id,int loca_id,int dept_id);
     public void update(Object obj); 
     public LegalNotice getAllListlegalNotice(int lega_noti_id, HttpSession session);
	/*
	 * public List<Object> searchLegalNotice(int lega_noti_entity_id, int
	 * lega_noti_unit_id, int lega_noti_function_id, String lega_noti_by_against,
	 * int lega_noti_category_id, int lega_noti_assigned_to_id, String
	 * notice_from_date, String notice_to_date, HttpSession session, int
	 * lega_noti_secondary_responsible_person);
	 */
	
	
}
