package lcmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import lcmt.domain.SarfaesiAct;
import lcmt.domain.SarfaesiActDocument;
import lcmt.domain.SarfaesiActRef;
import lcmt.domain.SarfaesiAction;
import lcmt.domain.SarfaesiActionRef;

public interface SarfaesiActService {
	
	public int persist(SarfaesiAct sarfaesiAct, ArrayList<MultipartFile> SarfDocument,HttpSession session);
	public List<SarfaesiActRef> getAll(HttpSession session);
	public Set<String> getLoanNumber();
	public Set<String> getBorrower();
	public String searchSarfaesiAct(String json, HttpSession session);
	public SarfaesiAct getSarfaesiActById(int sarf_act_id);
	public void updateSarfaesiAct(SarfaesiAct sarfaesiAct,ArrayList<MultipartFile> sarf_act_documents,  HttpSession session);
	public int deleteSarfaesiAct(int sarf_act_id);
	public SarfaesiActRef getJoinedSarfaesiActDetail(int sarf_act_id);
	public List<SarfaesiActDocument> getALLSarfaesiActDocByid(int sarfact_id);
	public void downloadSarfaesiActDoc(int sarf_doc_id, HttpServletResponse response);
	public int deleteSarfaesiActDocumentById(int sarf_doc_id);
	int saveSarfaesiAction(SarfaesiAction sarfaesiAction, HttpSession session);
	public List<SarfaesiActionRef> getAllSarfaesiActActionItem(int sarfact_id);
	public int deleteSarfaesiActAction(int sarfaction_id); 
	public SarfaesiAction getSarfaesiActActionById(int sarf_action_id);
	public void updateSarfaesiActAction(SarfaesiAction sarfaesiAction,HttpSession session);
	public void addSarfActDocuments( int doc_SarfAct_id,String sarf_document_Type, MultipartFile SarfDocument);
	public int isLoanNumberExistOrNot(String sarf_loan_no);
	public String  getArcManagerByARCnameId(int arc_name_id);
	public String getSarfaesiNoticedateByid(int sarfact_id); 
	
}
