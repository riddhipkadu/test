package lcmt.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import lcmt.domain.AdvocateFeesDetails;
import lcmt.domain.AdvocatePaidFees;
import lcmt.domain.CounselFeesDetails;
import lcmt.domain.CounselPaidFees;
import lcmt.domain.HearingStage;
import lcmt.domain.HearingStageDocuments;
import lcmt.domain.HearingStageOnHearing;
import lcmt.domain.Litigation;
import lcmt.domain.LitigationAdvocateFees;
import lcmt.domain.LitigationCounselFees;
import lcmt.domain.LitigationDetails;
import lcmt.domain.LitigationDocuments;
import lcmt.domain.LitigationEscalationMailId;
import lcmt.domain.LitigationFeesDocuments;
import lcmt.domain.LitigationReference;
import lcmt.domain.LitigationType;

public interface LitigationService {

	public void persist(LitigationType litigation);

	public int isLitiNameExist(int liti_id, String liti_name);

	public List<LitigationType> getAllLitiType();

	public LitigationType getLiti_typeById(int liti_id);

	public void updateLitigation_type(LitigationType litigation);

	public int deleteLitigationType(int liti_id);

	public String checkDependancyLitigationType(int liti_id);

	public int persistLitigation(Litigation litigation, String status, int id, HttpSession session,
			String esc_internal_resource, String esc_law_firm, String esc_appear_counsel, String esc_others,
			String type,  ArrayList<MultipartFile> litigation_doc/*, String ldoc_document_type,*/);

	public List<LitigationReference> getAllLitigation(HttpSession session);

	public List<LitigationReference> getAllListLitigation(HttpSession session);

	public LitigationDetails getJoinedLitigationDetailsById(int liti_id);

	public void addLitigationDocument(int ldoc_liti_id, String ldoc_document_type, MultipartFile litigationDocument);

	public List<LitigationDocuments> getAllDocumentByLitiId(int liti_id);

	public int deleteLitigationDocumentById(int ldoc_id);

	public void downloadLitigationDocument(int ldoc_id, HttpServletResponse response);

	public void downloadLitigationFeesDocument(int fdoc_id, HttpServletResponse response);

	public int saveHearingStage(HearingStage hearingStage, ArrayList<MultipartFile> hearing_doc,
			ArrayList<String> ttrn_counsel_list, String status);

	public String getHearingDetailsByLitiId(int liti_id, HttpSession session);

	public HearingStage getHearingDetailsByStageId(int hear_stage_id);

	public int updateHearingStage(HearingStage hearingStage, ArrayList<MultipartFile> hearing_doc,
			ArrayList<String> ttrn_counsel_list, String status);

	public Litigation getLitigationByLitiId(int liti_id);

	public void updateLitigation(Litigation litigation, String status, HttpSession session,
			String esc_internal_resource, String esc_law_firm, String esc_appear_counsel, String esc_others);

	public String searchLitigation(String json, HttpSession session);

	public void downloadHearingDocuments(int hearing_doc_id, HttpServletResponse response);

	public void saveLitigationCompletion(Litigation litigation, ArrayList<MultipartFile> liti_completion_doc);

	public int saveCounselFees(LitigationCounselFees counselFees, ArrayList<MultipartFile> counsel_agreed_doc);

	public List<CounselFeesDetails> getCounselFeesByLitiId(int liti_id);

	public List<Object> getMatterHandleByUsers();

	public String externalCounselName(int exte_coun_id);

	public String responsiblePersonName(int user_id);

	public String getCounselByLawFirmId(int law_firm_id);

	public List<Object> getLawFirmExternalCounsel(int liti_id);

	public int saveAdvocateFees(LitigationAdvocateFees advocateFeesDetails,
			ArrayList<MultipartFile> advocate_agreed_doc);

	public List<AdvocateFeesDetails> getAdvocateFeesDetailsByLitiId(int liti_id);

	public LitigationCounselFees getCounselFeesDetailsById(int lcou_id);

	public LitigationAdvocateFees getAdvocateFeesDetailsById(int ladv_id);

	public void updateCounselFees(LitigationCounselFees counselFees, ArrayList<MultipartFile> counsel_agreed_doc);

	public void updateAdvocateFees(LitigationAdvocateFees advocateFees, ArrayList<MultipartFile> advocate_agreed_doc);

	public int savePaidCounselFees(CounselPaidFees fees, ArrayList<MultipartFile> counsel_paid_doc);

	public List<CounselFeesDetails> getPaidFeesDetailsByConFeesId(int counsel_fees_id);

	public int savePaidAdvocateFees(AdvocatePaidFees fees, ArrayList<MultipartFile> advocate_paid_doc);

	public List<AdvocateFeesDetails> getPaidFeesDetailsByAdvocateFeesId(int advocate_fees_id);

	public AdvocatePaidFees getAdvocatePaidFeesById(int apaid_id);

	public void updatePaidAdvocateFees(AdvocatePaidFees advocatePaidFees, ArrayList<MultipartFile> advocate_paid_doc);

	public CounselPaidFees getCounselPaidFeesById(int cpaid_id);

	public void updatePaidCounselFees(CounselPaidFees paidFees, ArrayList<MultipartFile> counsel_paid_doc);

	public String getHearingStageLogs(String json, HttpSession session);

	public int deleteLitigation(int liti_id);

	public int isCounselPaidFeesGreaterThanAgreedFees(int cpaid_fees_amount, int cpaid_counsel_fees_id);

	public int isAdvocatePaidFeesGreaterThanAgreedFees(int apaid_fees_amount, int apaid_advocate_fees_id);

	public int deleteHearingStage(int hear_stage_id);

	public List<HearingStageDocuments> getHearingStageDocById(int hear_stage_id);

	public int deleteHearingStageDoc(int doc_id);

	public List<LitigationFeesDocuments> getLitiFeesDocById(int lcou_id);

	public int deleteLitiFeesDoc(int fdoc_id);

	public List<LitigationFeesDocuments> getAdvocateFeesDocById(int ladv_id);

	public List<LitigationFeesDocuments> getCounselPaidDocById(int cpaid_id);

	public List<LitigationFeesDocuments> getAdvocatePaidDocById(int apaid_id);

	public String getHearingStageCounselList(int hearing_stage_id);

	public int deleteHearingCounselById(String json);

	public String AllLitiTypeJson();

	public String getAllLitigationClientId();

	public String getpreviouslitiId(String liti_id);

	public LitigationEscalationMailId getEscalationMailByLitiId(int liti_id);

	public int saveHearingStageOnHearing(HearingStage hearingStage, ArrayList<MultipartFile> hearing_doc, String status,
			String hearing_id);

	public HearingStageOnHearing getHearingOnHearingDetailsById(int hear_id);



	//public String getLitiRefExcelExportUrl(List<LitigationReference> list_liti_ref, HttpServletRequest request);
	//public List<LitigationReference> searchLitigationExport(String json, HttpSession session);

	public String acmLitigation(String json, HttpSession session);


	public void downloadACMReport(HttpServletResponse response);
}
