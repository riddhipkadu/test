package lcmt.dao;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

import lcmt.domain.ArcManager;
import lcmt.domain.LitigationDocuments;
import lcmt.domain.SarfaesiAct;
import lcmt.domain.SarfaesiActDocument;
import lcmt.domain.SarfaesiAction;

public interface SarfaesiActDao {
	public int persist(SarfaesiAct obj);
	public int getLastGeneratedValueBySarfActId(int sarf_id);
	public void saveSarfaesiActDocument(SarfaesiActDocument sarfaesiActDocument);
	public List<Object> getAllSarfaesiAct(HttpSession session);
	public List<Object> getLoanNumber();
	public List<Object> getBorrower();
	public List<Object> searchSarfaesiAct(int sarf_orga_id,int sarf_loca_id,String sarf_security_type,
            String sarf_loan_no,String sarf_borrower, 
            String sarf_security_Interest,String SarfAct_from_date,String SarfAct_to_date);
	public SarfaesiAct getSarfaesiActById(int sarf_act_id);
	public void updateSarfaesiAct(SarfaesiAct sarfaesiAct);
	public int deleteSarfaesiAct(int sarf_act_id);
	public Object[] getJoinedSarfaesiAct(int sarf_act_id);
	public <T> List<T> getALLSarfaesiActDocByid(Class<T> clazz, int sarfact_id);
	public SarfaesiActDocument  getSarfaesiActDocByid(int sarfact_id); 
	public String getSarfaesiActDocumentFilePath(int sarf_doc_id);
	public int deleteSarfaesiActDocument(SarfaesiActDocument sarfaesiActDocument); 
	public int deleteSarfaesiActDocument(int sarf_doc_id);
	public int saveSarfaesiAction(SarfaesiAction sarfaesiAction);
	public List<Object> getAllSarfaesiActActionItem(int sarfact_id);
	public SarfaesiAction getSarfaesiActActionById(int sarf_action_id); 
	public int deleteSarfaesiActAction(int sarfaction_id);
	public void updateSarfaesiActAction(SarfaesiAction sarfaesiAction);
	public int isLoanNumberExistOrNot(String sarf_loan_no);
	public List<ArcManager> getArcManagerByARCnameId(int arc_name_id);
	public String getSarfaesiNoticedateByid(int sarfact_id); 
	
}
