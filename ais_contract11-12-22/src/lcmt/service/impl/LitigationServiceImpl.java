package lcmt.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lcmt.dao.LitigationDao;
import lcmt.dao.LitigationDocumentsDao;
import lcmt.dao.RequestByPOCDao;
import lcmt.dao.SendMailDao;
import lcmt.domain.LitigationAdvocateFees;
import lcmt.domain.ActivityLogs;
import lcmt.domain.AdvocateFeesDetails;
import lcmt.domain.AdvocatePaidFees;
import lcmt.domain.ContractDocuments;
import lcmt.domain.CounselFeesDetails;
import lcmt.domain.CounselPaidFees;
import lcmt.domain.ExternalCounsel;
import lcmt.domain.HearingStage;
import lcmt.domain.HearingStageCounsels;
import lcmt.domain.HearingStageDocuments;
import lcmt.domain.HearingStageOnHearing;
import lcmt.domain.LegalNotice;
import lcmt.domain.LegalNotice_Reference;
import lcmt.domain.Litigation;
import lcmt.domain.LitigationCounselFees;
import lcmt.domain.LitigationDetails;
import lcmt.domain.LitigationDocuments;
import lcmt.domain.LitigationEscalationMailId;
import lcmt.domain.LitigationFeesDocuments;
import lcmt.domain.LitigationReference;
import lcmt.domain.LitigationRequest;
import lcmt.domain.LitigationType;
import lcmt.domain.RequestDocument;
import lcmt.domain.Stages;
import lcmt.domain.User;
import lcmt.service.CourtService;
import lcmt.service.ExternalCounselService;
import lcmt.service.LegalNoticeService;
import lcmt.service.LitigationService;
import lcmt.service.OrganizationService;
import lcmt.service.RequestByPOCService;
import lcmt.service.SchedulerService;
import lcmt.service.SendMailService;
import lcmt.service.StagesService;
import lcmt.service.UserService;
import lcmt.service.UtilitiesService;
import lcmt.domain.Court;
/*
 * Author: Harshad Padole
 * Date: 09/08/2016
 * Updated Date:
 * 
 * */

@Service("litigationService")
public class LitigationServiceImpl implements LitigationService {

	@Autowired
	LitigationDao litigationDao;
	@Autowired
	UtilitiesService utilitieservice;
	@Autowired
	LitigationDocumentsDao litigationDocumentsDao;

	@Autowired
	OrganizationService organizationService;

	@Autowired
	CourtService courtService;

	@Autowired
	HttpSession httpSession;
	@Autowired
	ExternalCounselService externalCounselService;
	@Autowired
	UserService userService;
	@Autowired
	SendMailService sendMailService;
	
	@Autowired
	StagesService stageService;
	
	@Autowired
	SendMailDao sendMailDao;
	
	@Autowired
	SchedulerService schedulerService;
	@Autowired
	RequestByPOCService requestByPOCService;
	@Autowired
	RequestByPOCDao requestByPOCDao;
	@Autowired
	LegalNoticeService legalNoticeService;
	
	private @Value("#{config['project_name'] ?: 'null'}") String project_name;
	private @Value("#{config['project_url'] ?: 'null'}") String project_url;

	@Override
	public void persist(LitigationType litigation) {
		try {
			litigation.setLiti_type_created_at(utilitieservice.getCurrentDate());
			litigation.setLiti_type_id(0);
			litigation.setLiti_type_approval_status(String.valueOf(1));
			litigation.setLiti_type_enable_status(String.valueOf(1));
			litigationDao.persist(litigation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int isLitiNameExist(int liti_id, String liti_name) {
		try {
			return litigationDao.isLitiNameExist(liti_id, liti_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<LitigationType> getAllLitiType() {
		try {
			return litigationDao.getAllLitiType(LitigationType.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LitigationType getLiti_typeById(int liti_id) {
		try {
			return litigationDao.getLiti_typeById(liti_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateLitigation_type(LitigationType litigation) {
		try {

			LitigationType old_data = litigationDao.getLiti_typeById(litigation.getLiti_type_id());
			LitigationType new_data = new LitigationType();
			// Set Old data to new Object
			new_data.setLiti_type_added_by(old_data.getLiti_type_added_by());
			new_data.setLiti_type_approval_status(old_data.getLiti_type_approval_status());
			new_data.setLiti_type_created_at(old_data.getLiti_type_created_at());
			new_data.setLiti_type_enable_status(old_data.getLiti_type_enable_status());
			new_data.setLiti_type_parent_id(old_data.getLiti_type_id());

			// Set form data to new Object
			new_data.setLiti_type_id(litigation.getLiti_type_id());
			new_data.setLiti_type_name(litigation.getLiti_type_name());

			litigationDao.updateLitigation_type(new_data);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public int deleteLitigationType(int liti_id) {
		try {
			int deleteCount = litigationDao.deleteLitigationType(liti_id);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method created :Tejashri Zurunge
	// Method purpose :check dependancy of litigation type
	@Override
	public String checkDependancyLitigationType(int liti_id) {
		try {
			int result = litigationDao.checkDependancyLitigationType(liti_id);
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/******************************************************************************************************************************
	 										* Litigation code starts from here *
	 ******************************************************************************************************************************/

	// Method Created By: Mahesh Kharote
	// Method Purpose: Add New Litigation to Database
	@Override
	public void persistLitigation(Litigation litigation,String status,int id,HttpSession session, String esc_internal_resource,String esc_law_firm,String esc_appear_counsel,String esc_others, String type ) {
		try {
			int liti_last_generated_value = litigationDao.getMaxLastGeneratedValueForClientLitiId();
			String orga_shrt_name = organizationService.getOrganizationById(litigation.getLiti_orga_id()).getOrga_short_name();
			
			/*String comp_name = "";
			if(orga_name.equals("Mankind Pharma Limited"))
				comp_name = "MKD";
			if(orga_name.equals("Shree Jee Laboratories"))
				comp_name = "SJL";
			if(orga_name.equals("Lifestar Pharma Pvt. Ltd."))
				comp_name = "LSP";
			if(orga_name.equals("Magnet Labs Pvt. Ltd."))
				comp_name = "MGL";
			if(orga_name.equals("Lifestar Pharma LLC"))
				comp_name = "LLC";*/
				
			int liti_type = litigation.getLiti_type_id();
			int year = Calendar.getInstance().get(Calendar.YEAR);
			litigation.setLiti_client_liti_id(orga_shrt_name + liti_type + year+ (liti_last_generated_value+1));
			
			litigation.setLiti_last_generated_value_for_client_liti_id(liti_last_generated_value + 1);
			litigation.setLiti_case_title(litigation.getLiti_party_by() + " ...."
							+ litigation.getLiti_company_acting_as() + " v/s " + litigation.getLiti_party_against()
							+ " ...." + litigation.getLiti_opp_party_acting_as());
			litigation.setLiti_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			litigation.setLiti_created_at(utilitieservice.getCurrentDate());
			if(status.equals("Pending")){
			    litigation.setLiti_status(status);
			} else{
				litigation.setLiti_status(status);
				litigation.setLiti_assigned_to(0);
			    litigation.setLiti_internally_handled_by(0);
			}
			
			int liti_id = litigationDao.persistLitigation(litigation);
			if(id > 0 && type.equals("liti")){
				LitigationRequest litiReq = requestByPOCService.getAllListLitigationRequest(id, session);
				litiReq.setReq_liti_approval_status(2);
				litigationDao.update(litiReq);
				//upload litigation document into litigation
				List<RequestDocument> liti_doc = requestByPOCDao.getAllRequestDocument(id, "LitiRequest");
				if(liti_doc !=null){
				Iterator<RequestDocument> itr = liti_doc.iterator();
				//MultipartFile teDoc;
				while (itr.hasNext()) {
					RequestDocument requestDocument = (RequestDocument) itr.next();
					LitigationDocuments documents = new LitigationDocuments();

					documents.setLdoc_liti_id(liti_id);
					documents.setLdoc_original_file_name(requestDocument.getReq_doc_original_file_name());
					documents.setLdoc_generated_file_name(requestDocument.getReq_doc_generated_file_name());
					documents.setLdoc_last_generated_value_for_liti_id(requestDocument.getReq_doc_last_generated_value_for_req_id());
					//documents.set(utilitieservice.getCurrentDateWithTime());
					//documents.setCdoc_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
					litigationDao.persist(documents);
				}
				}
			}
			
			if(type.equals("noti")){
				LegalNotice notice = legalNoticeService.getLegalNoticeBynotiId(id);
				notice.setLega_noti_status("Converted");
				notice.setLega_noti_liti_ref_id(litigation.getLiti_client_liti_id());
				litigationDao.update(notice);
				
			}
			if(!esc_appear_counsel.equals("NA") || !esc_internal_resource.equals("NA") || !esc_law_firm.equals("NA") || !esc_others.equals("NA")){
				
			LitigationEscalationMailId mail = new LitigationEscalationMailId();
			mail.setEsc_appear_counsel(esc_appear_counsel);
			mail.setEsc_internal_resource(esc_internal_resource);
			mail.setEsc_law_firm(esc_law_firm);
			mail.setEsc_others(esc_others);
			mail.setEsc_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			mail.setEsc_created_at(utilitieservice.getCurrentDate());
			mail.setEsc_liti_id(liti_id);
			litigationDao.persist(mail);
			}
			
			if (status.equals("Pending")) {
				sendMailService.sendLitigationHandledMailToInternallyHandledPerson(liti_id, session);

				// persist litigation logs
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(liti_id);
				logs.setLog_assinged_to_id(litigation.getLiti_internally_handled_by());
				logs.setLog_activity("Add");
				logs.setLog_related_to("Litigation");
				logs.setLog_sub_activity("Add Litigation");
				logs.setLog_related_name(litigation.getLiti_case_title());
				logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
				logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
				litigationDao.persist(logs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: List all Litigation
	@Override
	public List<LitigationReference> getAllLitigation(HttpSession session) {
		try {
			String next_hearing_date = "";
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			List<LitigationReference> sendLitst = new ArrayList<>();
			List<Litigation> result = null;
			;
			if (Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 1) {
				result = litigationDao.getAllLitigation(Litigation.class);
			}
			if (Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 2) {
				result = litigationDao.getAllLitigationAccessWise(Litigation.class, session);
			}
			if (result != null) {
				Iterator<Litigation> iterator = result.iterator();
				while (iterator.hasNext()) {
					Litigation litigation = iterator.next();
					LitigationReference litigationReference = new LitigationReference();

					litigationReference.setLiti_id(litigation.getLiti_id());
					litigationReference.setLiti_client_liti_id(litigation.getLiti_client_liti_id());
					litigationReference.setLiti_against_by_id(litigation.getLiti_against_by_id());
					next_hearing_date = litigationDao.getLatestNextHearingStageDate(litigation.getLiti_id());
					if (!next_hearing_date.equals("NA")) {
						litigationReference.setLiti_next_hearing_date(sdfOut.format(sdfIn.parse(next_hearing_date)));
					} else {
						litigationReference.setLiti_next_hearing_date("NA");
					}

					litigationReference.setLiti_case_reference_no(litigation.getLiti_case_reference_no());
					litigationReference.setLiti_case_title(litigation.getLiti_case_title());
					litigationReference.setLiti_status(litigation.getLiti_status());
					sendLitst.add(litigationReference);

				}
			}
			return sendLitst;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Get details for litigation
	@Override
	public LitigationDetails getJoinedLitigationDetailsById(int liti_id) {
		try {
			List<Object> litigationDetailsList = litigationDao.getJoinedLitigationDetailsById(liti_id);
			LitigationDetails litigationDetails = new LitigationDetails();
			Iterator<Object> iterator = litigationDetailsList.iterator();
			while (iterator.hasNext()) {
				Object[] object = (Object[]) iterator.next();
				SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdfOut = new SimpleDateFormat("dd/MM/yyyy");

				litigationDetails.setLiti_id(Integer.parseInt(object[0].toString()));
				litigationDetails.setLiti_against_by_id(object[1].toString());
				if(object[2]!=null)
				  litigationDetails.setLiti_type_name(object[2].toString());
				else
					litigationDetails.setLiti_type_name("NA");
				
				litigationDetails.setLiti_case_reference_no(object[3].toString());
				
				if(object[4]!=null)
				  litigationDetails.setLiti_case_filing_date(sdfOut.format(sdfIn.parse(object[4].toString())));
				else
					litigationDetails.setLiti_case_filing_date("NA");
				
				if(Integer.parseInt(object[5].toString()) != 0 ){
					litigationDetails.setExte_coun_name(externalCounselService.getExte_CounById(Integer.parseInt(object[5].toString())).getExte_coun_name());
				}else{
					litigationDetails.setExte_coun_name("NA");
				}
				//litigationDetails.setExte_coun_name(object[5].toString());
				if(object[6]!=null)
				    litigationDetails.setLiti_internally_handled_name(object[6].toString() +' '+object[7].toString());
				else
					litigationDetails.setLiti_internally_handled_name("NA");
				
				if(object[8] != null)
					litigationDetails.setLiti_party_against(object[8].toString());
				else
					litigationDetails.setLiti_party_against("NA");
				litigationDetails.setLiti_amount_involved(object[10].toString());
				litigationDetails.setLiti_relevant_law(object[11].toString());
				litigationDetails.setLiti_brief_description(object[12].toString());
				litigationDetails.setLiti_status(object[13].toString());
				if (object[14] != null)
					litigationDetails.setLiti_litigation_result(object[14].toString());
				else
					litigationDetails.setLiti_litigation_result("NA");
				if (object[15] != null)
					litigationDetails.setLiti_disposal_date(sdfOut.format(sdfIn.parse(object[15].toString())));
				else
					litigationDetails.setLiti_disposal_date("NA");

				if (object[16] != null)
					litigationDetails.setLiti_synopsis_of_order(object[16].toString());
				else
					litigationDetails.setLiti_synopsis_of_order("NA");
				if (object[17] != null)
					litigationDetails
							.setLiti_last_date_for_filling_appeal(sdfOut.format(sdfIn.parse(object[17].toString())));
				else
					litigationDetails.setLiti_last_date_for_filling_appeal("NA");
				if (object[18] != null)
					litigationDetails.setLiti_comments(object[18].toString());
				else
					litigationDetails.setLiti_comments("");

				litigationDetails.setLiti_documents(
						litigationDocumentsDao.getAllDocumentByLitiId(LitigationDocuments.class, liti_id));
				//litigationDetails.setExte_coun_id(Integer.parseInt(object[18].toString()));
				if(object[19]!=null)
				  litigationDetails.setLiti_advocate_id(Integer.parseInt(object[19].toString()));
				else
					litigationDetails.setLiti_advocate_id(0);
				
				if(object[20]!=null)
				 litigationDetails.setLiti_advocate_name(object[20].toString());
				else
					litigationDetails.setLiti_advocate_name("NA");
				
				if(object[21]!=null)
				   litigationDetails.setLiti_court(object[21].toString());
				else
					litigationDetails.setLiti_court("NA");
				
				litigationDetails.setLiti_client_liti_id(object[23].toString());
				if (object[24] != null) {
					litigationDetails.setLiti_intenal_person(object[24].toString());
				} else {
					litigationDetails.setLiti_intenal_person("NA");
				}
				litigationDetails.setLiti_involved_amt_currency(object[25].toString());
				if (Integer.parseInt(object[26].toString()) != 0) {
				litigationDetails.setLiti_assigned_name(userService.getUserFullNameById(Integer.parseInt(object[26].toString())));
				}else{
				litigationDetails.setLiti_assigned_name("NA");
				}
				if (Integer.parseInt(object[22].toString()) != 0) {
					Court oldCourt = courtService.getCourtById(Integer.parseInt(object[22].toString()));
					litigationDetails.setLiti_completion_court_name(oldCourt.getCourt_name());
				} else
					litigationDetails.setLiti_completion_court_name("NA");
				
				litigationDetails.setLiti_criticality(object[27].toString());
				if(object[28] != null)
				litigationDetails.setLiti_internal_liti_code_name(object[28].toString());
				else
					litigationDetails.setLiti_internal_liti_code_name("NA");
				if(object[29] != null)
					litigationDetails.setLiti_oppo_party_address(object[29].toString());
				else
					litigationDetails.setLiti_oppo_party_address("NA");	
				if(object[30] != null)
					litigationDetails.setLiti_oppo_advo_law_firm(object[30].toString());
				else
					litigationDetails.setLiti_oppo_advo_law_firm("NA");
				if(object[31] != null)
					litigationDetails.setLiti_opposite_party_advocate(object[31].toString());
				else
					litigationDetails.setLiti_opposite_party_advocate("NA");
				if(object[32] != null)
					litigationDetails.setLiti_oppo_advo_contact(object[32].toString());
				else
					litigationDetails.setLiti_oppo_advo_contact("NA");
				if(object[33] != null)
					litigationDetails.setLiti_jurisdiction_name(object[33].toString());
				else
					litigationDetails.setLiti_jurisdiction_name("NA");
				if(object[34] != null || object[34] != "0"){
					litigationDetails.setLiti_previous_liti_ref_no(object[34].toString());
				}else{
					litigationDetails.setLiti_previous_liti_ref_no("NA");
				}
				if(object[35] != null)
					litigationDetails.setLiti_party_by(object[35].toString());
				else
					litigationDetails.setLiti_party_by("NA");
				
				
			}
			// litigationDetails.setLiti_case_filing_date(sdfOut.format(sdfIn.parse(litigationDetails.getLiti_case_filing_date().toString())));
			return litigationDetails;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Save Litigation Document
	@Override
	public void addLitigationDocument(int ldoc_liti_id, String ldoc_document_type, MultipartFile litigationDocument) {
		try {

			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueForLitiDoc = litigationDocumentsDao.getLastGeneratedValueByLitiId(ldoc_liti_id);
			if (lastGeneratedValueForLitiDoc >= 0) {
				if (litigationDocument.getSize() > 0) {
					File dir = new File("D:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "LitigationDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueForLitiDoc++;
					originalFileName = litigationDocument.getOriginalFilename();
					generatedFileName = "uploadedLitiDoc_" + ldoc_liti_id + "_" + lastGeneratedValueForLitiDoc + "."
							+ litigationDocument.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(litigationDocument.getBytes());

					LitigationDocuments litigationDocuments = new LitigationDocuments();

					litigationDocuments.setLdoc_liti_id(ldoc_liti_id);
					litigationDocuments.setLdoc_original_file_name(originalFileName);
					litigationDocuments
							.setLdoc_generated_file_name("D:/"+project_name+"/Documents/LitigationDocuments/" + generatedFileName);
					litigationDocuments.setLdoc_last_generated_value_for_liti_id(lastGeneratedValueForLitiDoc);
					litigationDocuments.setLdoc_document_type(ldoc_document_type);

					litigationDocumentsDao.saveDocuments(litigationDocuments);
				}
			} else {
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Add New Litigation to Database
	@Override
	public List<LitigationDocuments> getAllDocumentByLitiId(int liti_id) {
		try {
			return litigationDocumentsDao.getAllDocumentByLitiId(LitigationDocuments.class, liti_id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Harshad Padole
	// Method Purpose : Save hearing stage
	@Override
	public int saveHearingStage(HearingStage hearingStage, ArrayList<MultipartFile> hearing_doc,ArrayList<String> ttrn_counsel_list,String status) {
		try {
			//SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			String AllEmail = hearingStage.getTtrn_additional_mail_id_1();
			String[] Email = AllEmail.split(",");
			if (Email.length > 0 && !Email[0].equals("")) {
					hearingStage.setTtrn_additional_mail_id_1(Email[0]);

				if (Email.length > 1)
					hearingStage.setTtrn_additional_mail_id_2(Email[1]);

				if (Email.length > 2)
					hearingStage.setTtrn_additional_mail_id_3(Email[2]);
			}
			hearingStage.setTtrn_counsel_law_firm_id(0);
			hearingStage.setTtrn_counsel_id(0);
			hearingStage.setTtrn_created_at(utilitieservice.getCurrentDate());
			hearingStage.setTtrn_updated_at(utilitieservice.getCurrentDate());
			hearingStage.setTtrn_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			hearingStage.setTtrn_stage_status(status);
			int hearing_Stage_Id = litigationDao.saveHearingStage(hearingStage);
			//System.out.println("counsel list: "+ttrn_counsel_list);
			//Save list of counsel
			if(ttrn_counsel_list!=null){
				for(int i=0;i<ttrn_counsel_list.size();i++){
					String[] ids  = ttrn_counsel_list.get(i).toString().split("_");
					HearingStageCounsels counsels = new HearingStageCounsels();
					counsels.setHsco_hearing_stage_id(hearing_Stage_Id);
					counsels.setHsco_counsel_law_firm_id(Integer.parseInt(ids[0]));
					counsels.setHsco_counsel_id(Integer.parseInt(ids[1]));
					counsels.setHsco_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
					counsels.setHsco_created_at(utilitieservice.getCurrentDate());
					litigationDao.persist(counsels);
				}
			}
			if (!status.equals("Draft")) {
				// persist litigation logs
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(hearingStage.getTtrn_litigation_id());
				logs.setLog_sub_activity_id(hearing_Stage_Id);
				logs.setLog_assinged_to_id(hearingStage.getTtrn_responsible_person());
				logs.setLog_activity("Add");
				logs.setLog_related_to("Litigation");
				logs.setLog_sub_activity("Add Hearing Stage");
				//Stages stageName = stageService.getStagesById(hearingStage.getTtrn_stage_id());
				logs.setLog_related_name("hearing stage");
				logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
				logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
				litigationDao.persist(logs);
			}
			
			//get mail ids of all admin
			ArrayList<String> email = new ArrayList<String>(); 
			List<Object> mail_idCC = schedulerService.getAllAdminUser();
			Iterator<Object> iterator = mail_idCC.iterator();
			while (iterator.hasNext()) {
				Object object[] = (Object[]) iterator.next();
				if(object[1] != null)
				email.add(object[1].toString());
			}
			
			String email_subject = "New Hearing stage is created ";
			String email_body= " ";
			email_body += "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
			email_body += "<h2 style='font-size:18px;'>Dear User,</h2>";
			email_body += "<p style='text-align:justify;width:70%;'>The following Hearing stage is been created against litigation. You are requested to follow the link by clicking on the 'LitigationID'.</p>"
					+ "<h2 style='font-size:16px;font-weight:bold;'>Hearing stage details :</h2>";
			email_body += "<table style='width:80%;' border='1'>" 
					+ "<thead>"
					+ "<tr style='background:#0B6EC3;color:#fff;'>"
					+ "<th>Litigation ID</th>"
					+ "<th>Next hearing date</th>" 
					+ "<th>Responsible person</th>"
					+ "<th>Stage description</th>" 
					+ "<th>Action item due date</th>" 
					+ "</tr>" + "</thead>" + "<tbody>";
			String litigation_id = "";
				litigation_id = String.valueOf(hearingStage.getTtrn_litigation_id());
				User user = userService.getUserById(hearingStage.getTtrn_responsible_person());
				String responsible_person = user.getUser_first_name()+" "+user.getUser_last_name();
				//responsible_person_mail = user.get;
				String next_hearing_date = sdfOut.format(hearingStage.getTtrn_next_hearing_date());
				String stage_desc = hearingStage.getTtrn_stage_description();
				String action_item_due_dt = sdfOut.format(hearingStage.getTtrn_stage_due_date());
				
				email_body += "<tr>" 
						+ "<td><a href='" + project_url + "litigationDetails?liti_id=" + litigation_id	+ "'>" + litigation_id + "</a></td>" 
						+ "<td>" + next_hearing_date +" </td>" 
						+ "<td>" + responsible_person + "</td>" 	
						+ "<td>" + stage_desc + "</td>" 
						+ "<td>" + action_item_due_dt + "</td>" 
						+ "</tr>";
			
			email_body += "</tbody>" + "</table>";
			email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
					+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
					+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
					+ "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
			//System.out.println(email_body);
			
			// Email send code start here
				InternetAddress[] address = new InternetAddress[email.size()];
					try {
						for(int i=0; i< email.size(); i++){
						address[i] = new InternetAddress(email.get(i));
						}
					} catch (AddressException e) {
						e.printStackTrace();
					}
			
			Litigation liti = litigationDao.getLitigationByLitiId(hearingStage.getTtrn_litigation_id());
			
			String liti_Allmail_id = liti.getLiti_mail_id_cc();
			if(liti_Allmail_id != null && (!liti_Allmail_id.equals(""))){
			String[] mail_id = liti_Allmail_id.split(",");
			InternetAddress[] addressCC = new InternetAddress[mail_id.length];
			try {
				if(mail_id.length != 0){
				for(int i = 0; i< mail_id.length ; i++){
					addressCC[i] = new InternetAddress(mail_id[i]);
				}
				}
			} catch (AddressException e) {
				e.printStackTrace();
			}
			sendMailService.sendMail(email_subject, email_body, address, addressCC, litigation_id);
			} else
			sendMailService.sendMail(email_subject, email_body, address, address, litigation_id);
			
			// Upload Documents
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueHearId = litigationDocumentsDao
					.getLastGeneratedValueByHearingStageId(hearing_Stage_Id);
			for (int i = 0; i < hearing_doc.size(); i++) {
				MultipartFile file = hearing_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("D:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "HearingStageDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueHearId++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedHearStageDoc_" + hearing_Stage_Id + "_" + lastGeneratedValueHearId
							+ "." + file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					HearingStageDocuments stage_documents = new HearingStageDocuments();

					stage_documents.setHear_stage_id(hearing_Stage_Id);
					stage_documents.setHear_stage_original_file_name(originalFileName);
					stage_documents.setHear_stage_generated_file_name(
							"C:/"+project_name+"/Documents/HearingStageDocuments/" + generatedFileName);
					stage_documents.setHear_stage_last_generated_value_for_hear_stage_id(lastGeneratedValueHearId);
					stage_documents.setHear_stage_created_at(utilitieservice.getCurrentDate());
					stage_documents.setHear_stage_added_by(utilitieservice.getCurrentSessionUserId(httpSession));

					litigationDocumentsDao.saveHearingSatgeDocument(stage_documents);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get Hearing Details By Id
	@SuppressWarnings("unchecked")
	@Override
	public String getHearingDetailsByLitiId(int liti_id,HttpSession session) {
		try {
			JSONArray sendList = new JSONArray();
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

			List<Object> result = litigationDao.getHearingDetailsByLitiId(liti_id);
			//For hearing stage details to all
			Iterator<Object> iterator = result.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				JSONObject data = new JSONObject();
					data.put("liti_id", liti_id);
                
				if(objects[0]!=null)
				    data.put("hearing_date", sdfOut.format(sdfIn.parse(objects[0].toString())));
				else
					data.put("hearing_date", "NA");
				
				if(objects[1]!=null)
					data.put("first_alert", sdfOut.format(sdfIn.parse(objects[1].toString())));
				else
					data.put("first_alert", "NA");
				
				if (objects[2] != null) {
					data.put("second_alert", sdfOut.format(sdfIn.parse(objects[2].toString())));
				} else {
					data.put("second_alert", "NA");
				}
				if (objects[3] != null) {
					data.put("third_alert", sdfOut.format(sdfIn.parse(objects[3].toString())));
				} else {
					data.put("third_alert", "NA");
				}
				if(objects[4]!=null)
					data.put("stage_desc", objects[4].toString());
				else
					data.put("stage_desc", "");
				
					data.put("hear_stage_id", objects[5].toString());
				
				if (!objects[7].equals(0))
					data.put("resp_person", responsiblePersonName(Integer.parseInt(objects[7].toString())));
				else
					data.put("resp_person", "NA");
				if (objects[8].equals("0"))
					data.put("counsel_name", "NA");
				
				JSONArray sendCoun = new JSONArray();			
				List<Object> res_coun = litigationDao.getCounselByHearingStageId(Integer.parseInt(objects[5].toString()));
				if(res_coun!=null){
					Iterator<Object> coun = res_coun.iterator();
					while(coun.hasNext()){
						Object[] obj = (Object[]) coun.next();
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("hsco_id", obj[0]);
						jsonObject.put("hsco_law_firm_id",obj[1]);
						jsonObject.put("hsco_law_firm_name",obj[2]);
						jsonObject.put("hsco_counsel_id",obj[3]);
						jsonObject.put("hsco_counsel_name",obj[4]);
						sendCoun.add(jsonObject);
					}
				}
				
				data.put("counsel_list",sendCoun);
				
				data.put("added_by", objects[9].toString());
				data.put("hearing_status", objects[10].toString());
				data.put("user_id", session.getAttribute("sess_user_id"));
				data.put("user_role", session.getAttribute("sess_user_role"));
				
				JSONArray DocList = new JSONArray();
				List<HearingStageDocuments> res = litigationDocumentsDao.getHearingDocumentByHearStageId(
						HearingStageDocuments.class, Integer.parseInt(objects[5].toString()));
				Iterator<HearingStageDocuments> doc = res.iterator();
				while (doc.hasNext()) {
					HearingStageDocuments documents = doc.next();
					JSONObject jsonDoc = new JSONObject();
					jsonDoc.put("doc_id", documents.getHear_doc_id());
					jsonDoc.put("doc_orig_name", documents.getHear_stage_original_file_name());
					jsonDoc.put("doc_gene_name", documents.getHear_stage_generated_file_name());
					DocList.add(jsonDoc);
				}
				data.put("docList", DocList);
				sendList.add(data);

			}
			if(Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 1 || Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 5){
			List<Object> result1 = litigationDao.getHearingDetailsOnHearingDay(liti_id);
			Iterator<Object> iterator1 = result1.iterator();
			while (iterator1.hasNext()) {
				Object[] object = (Object[]) iterator1.next();
				JSONObject data = new JSONObject();
					data.put("liti_id", liti_id);
                
				if(object[0]!=null)
				    data.put("hearing_date", sdfOut.format(sdfIn.parse(object[0].toString())));
				else
					data.put("hearing_date", "NA");
				
				if(object[1]!=null)
					data.put("first_alert", sdfOut.format(sdfIn.parse(object[1].toString())));
				else
					data.put("first_alert", "NA");
				
				if (object[2] != null) {
					data.put("second_alert", sdfOut.format(sdfIn.parse(object[2].toString())));
				} else {
					data.put("second_alert", "NA");
				}
				if (object[3] != null) {
					data.put("third_alert", sdfOut.format(sdfIn.parse(object[3].toString())));
				} else {
					data.put("third_alert", "NA");
				}
				if(object[4]!=null)
					data.put("stage_desc", object[4].toString());
				else
					data.put("stage_desc", "");
				
					data.put("hear_stage_id", object[5].toString());
				
				if (!object[7].equals(0))
					data.put("resp_person", responsiblePersonName(Integer.parseInt(object[7].toString())));
				else
					data.put("resp_person", "NA");
				if (object[8].equals("0"))
					data.put("counsel_name", "NA");
				
				JSONArray sendCoun = new JSONArray();			
				List<Object> res_coun = litigationDao.getCounselByHearingStageId(Integer.parseInt(object[5].toString()));
				if(res_coun!=null){
					Iterator<Object> coun = res_coun.iterator();
					while(coun.hasNext()){
						Object[] obj = (Object[]) coun.next();
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("hsco_id", obj[0]);
						jsonObject.put("hsco_law_firm_id",obj[1]);
						jsonObject.put("hsco_law_firm_name",obj[2]);
						jsonObject.put("hsco_counsel_id",obj[3]);
						jsonObject.put("hsco_counsel_name",obj[4]);
						sendCoun.add(jsonObject);
					}
				}
				
				data.put("counsel_list",sendCoun);
				
				data.put("added_by", object[9].toString());
				data.put("hearing_status", object[10].toString());
				data.put("user_id", session.getAttribute("sess_user_id"));
				data.put("user_role", session.getAttribute("sess_user_role"));
				
				JSONArray DocList = new JSONArray();
				List<HearingStageDocuments> res = litigationDocumentsDao.getHearingDocumentByHearStageId(
						HearingStageDocuments.class, Integer.parseInt(object[5].toString()));
				Iterator<HearingStageDocuments> doc = res.iterator();
				while (doc.hasNext()) {
					HearingStageDocuments documents = doc.next();
					JSONObject jsonDoc = new JSONObject();
					jsonDoc.put("doc_id", documents.getHear_doc_id());
					jsonDoc.put("doc_orig_name", documents.getHear_stage_original_file_name());
					jsonDoc.put("doc_gene_name", documents.getHear_stage_generated_file_name());
					DocList.add(jsonDoc);
				}
				data.put("docList", DocList);
				sendList.add(data);
			}
		}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Delete Litigation Document from database
	@Override
	public int deleteLitigationDocumentById(int ldoc_id) {
		try {
			LitigationDocuments litigationDocuments = litigationDocumentsDao.getLitigationDocumentById(ldoc_id);
			File file = new File(litigationDocuments.getLdoc_generated_file_name());

			if (file.delete()) {
				//System.out.println(file.getName() + " is deleted!");
			} else {
				//System.out.println("Delete operation is failed.");
			}

			return litigationDocumentsDao.deleteLitigationDocument(litigationDocuments);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Download document to local pc
	@Override
	public void downloadLitigationDocument(int ldoc_id, HttpServletResponse response) {
		try {
			if (litigationDocumentsDao.getLitigationDocumentFilePath(ldoc_id) != null) {
				File file = new File(litigationDocumentsDao.getLitigationDocumentFilePath(ldoc_id));
				InputStream is = new FileInputStream(file);

				response.setContentType("application/octet-stream");

				response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

				OutputStream os = response.getOutputStream();
				byte[] buffer = new byte[1024];
				int len;
				while ((len = is.read(buffer)) != -1) {
					os.write(buffer, 0, len);
				}
				os.flush();
				os.close();
				is.close();
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created By: Harshad Padole
	// Method Purpose: Update hearing Stage
	@Override
	public int updateHearingStage(HearingStage hearingStage, ArrayList<MultipartFile> hearing_doc,ArrayList<String> ttrn_counsel_list,String status) {
		try {
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			HearingStage oldData = litigationDao.getHearingDetailsByStageId(hearingStage.getTtrn_hearing_stage());

			String AllEmail = hearingStage.getTtrn_additional_mail_id_1();
			String[] Email = AllEmail.split(",");
			if (Email.length > 0 && !Email[0].equals("")) {
				hearingStage.setTtrn_additional_mail_id_1(Email[0]);

				if (Email.length > 1)
					hearingStage.setTtrn_additional_mail_id_2(Email[1]);

				if (Email.length > 2)
					hearingStage.setTtrn_additional_mail_id_3(Email[2]);
			}
			hearingStage.setTtrn_counsel_id(0);
			hearingStage.setTtrn_counsel_law_firm_id(0);
			
			if(!oldData.getTtrn_stage_status().equals("Waiting for Approval"))
			hearingStage.setTtrn_created_at(oldData.getTtrn_created_at());
			else
			hearingStage.setTtrn_created_at(utilitieservice.getCurrentDateWithTime());
			
			hearingStage.setTtrn_updated_at(utilitieservice.getCurrentDate());
			if(!oldData.getTtrn_stage_status().equals("Waiting for Approval"))
			hearingStage.setTtrn_added_by(oldData.getTtrn_added_by());
			else
				hearingStage.setTtrn_added_by(utilitieservice.getCurrentSessionUserId(httpSession));	
			
			if ((oldData.getTtrn_stage_status().equals("Draft") || oldData.getTtrn_stage_status().equals("Waiting for Approval")) && status.equals("Pending"))
				hearingStage.setTtrn_stage_status("Pending");
			else
				hearingStage.setTtrn_stage_status(oldData.getTtrn_stage_status());
			
			litigationDao.updateHearingStage(hearingStage);
			
			if ((oldData.getTtrn_stage_status().equals("Draft") || oldData.getTtrn_stage_status().equals("Waiting for Approval")) && !status.equals("Draft") ) {
				// persist litigation logs
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(hearingStage.getTtrn_litigation_id());
				logs.setLog_sub_activity_id(oldData.getTtrn_hearing_stage());
				logs.setLog_assinged_to_id(hearingStage.getTtrn_responsible_person());
				logs.setLog_activity("Add");
				logs.setLog_related_to("Litigation");
				logs.setLog_sub_activity("Add Hearing Stage");
				//Stages stageName = stageService.getStagesById(hearingStage.getTtrn_stage_id());
				//logs.setLog_related_name();
				logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
				logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
				litigationDao.persist(logs);
			}
			
			//Save new list of counsel
			if(ttrn_counsel_list!=null){
				for(int i=0;i<ttrn_counsel_list.size();i++){
					String[] ids  = ttrn_counsel_list.get(i).toString().split("_");
					
					HearingStageCounsels counsels = new HearingStageCounsels();
					counsels.setHsco_hearing_stage_id(hearingStage.getTtrn_hearing_stage());
					counsels.setHsco_counsel_law_firm_id(Integer.parseInt(ids[0]));
					counsels.setHsco_counsel_id(Integer.parseInt(ids[1]));
					counsels.setHsco_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
					counsels.setHsco_created_at(utilitieservice.getCurrentDate());
					litigationDao.persist(counsels);
				}
			}
			if (status.equals("Pending") && (!oldData.getTtrn_stage_status().equals("Draft") || !oldData.getTtrn_stage_status().equals("Waiting for Approval"))) {
				// persist litigation logs
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(hearingStage.getTtrn_litigation_id());
				logs.setLog_sub_activity_id(hearingStage.getTtrn_hearing_stage());
				logs.setLog_assinged_to_id(hearingStage.getTtrn_responsible_person());
				logs.setLog_activity("Update");
				logs.setLog_related_to("Litigation");
				logs.setLog_sub_activity("Update Hearing Stage");
				logs.setLog_related_name("");
				logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
				logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
				litigationDao.persist(logs);
			}
			//send mail to admin and number of mail ids which enter in litigation are marked in cc
			
			//get mail ids of all admin
			ArrayList<String> email = new ArrayList<String>(); 
			List<Object> mail_idCC = schedulerService.getAllAdminUser();
			Iterator<Object> iterator = mail_idCC.iterator();
			while (iterator.hasNext()) {
				Object object[] = (Object[]) iterator.next();
				if(object[1] != null)
				email.add(object[1].toString());
			}
			
			String email_subject = "Hearing stage is updated ";
			String email_body= " ";
			email_body += "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
			email_body += "<h2 style='font-size:18px;'>Dear User,</h2>";
			email_body += "<p style='text-align:justify;width:70%;'>The following Hearing stage has been updated against litigation. You are requested to follow the link by clicking on the 'LitigationID'.</p>"
					+ "<h2 style='font-size:16px;font-weight:bold;'>Hearing stage details :</h2>";
			email_body += "<table style='width:80%;' border='1'>" 
					+ "<thead>"
					+ "<tr style='background:#0B6EC3;color:#fff;'>"
					+ "<th>Litigation ID</th>"
					+ "<th>Next hearing date</th>" 
					+ "<th>Responsible person</th>"
					+ "<th>Stage description</th>" 
					+ "<th>Action item due date</th>" 
					+ "</tr>" + "</thead>" + "<tbody>";
			
			String litigation_id = "";
			
				litigation_id = String.valueOf(hearingStage.getTtrn_litigation_id());
				User user = userService.getUserById(hearingStage.getTtrn_responsible_person());
				String responsible_person = user.getUser_first_name()+" "+user.getUser_last_name();
				//responsible_person_mail = user.get;
				String next_hearing_date = sdfOut.format(hearingStage.getTtrn_next_hearing_date());
				String stage_desc = hearingStage.getTtrn_stage_description();
				String action_item_due_dt = sdfOut.format(hearingStage.getTtrn_stage_due_date());
				
				email_body += "<tr>" 
						+ "<td><a href='" + project_url + "litigationDetails?liti_id=" + litigation_id	+ "'>" + litigation_id + "</a></td>" 
						+ "<td>" + next_hearing_date +" </td>" 
						+ "<td>" + responsible_person + "</td>" 	
						+ "<td>" + stage_desc + "</td>" 
						+ "<td>" + action_item_due_dt + "</td>" 
						+ "</tr>";
			
			email_body += "</tbody>" + "</table>";
			email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
					+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
					+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
					+ "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
			//System.out.println(email_body);
			// Email send code start here
						InternetAddress[] address = new InternetAddress[email.size()];
						try {
							for (int j = 0; j < email.size(); j++) {
								address[j] = new InternetAddress(email.get(j));
							}
						} catch (AddressException e) {
							e.printStackTrace();
						}
						
			Litigation liti = litigationDao.getLitigationByLitiId(hearingStage.getTtrn_litigation_id());
			
			String liti_Allmail_id = liti.getLiti_mail_id_cc();
			if(liti_Allmail_id != null && (!liti_Allmail_id.equals(""))){
			String[] mail_id = liti_Allmail_id.split(",");
			InternetAddress[] addressCC = new InternetAddress[mail_id.length];
			try {
				for (int i = 0; i < mail_id.length; i++) {
					addressCC[i] = new InternetAddress(mail_id[i]);
				}
			} catch (AddressException e) {
				e.printStackTrace();
			}
			sendMailService.sendMail(email_subject, email_body, address, addressCC, litigation_id);
			}
			else
			sendMailService.sendMail(email_subject, email_body, address, address, litigation_id);
			
			// Upload Documents
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueHearId = litigationDocumentsDao
					.getLastGeneratedValueByHearingStageId(hearingStage.getTtrn_hearing_stage());
			for (int i = 0; i < hearing_doc.size(); i++) {
				MultipartFile file = hearing_doc.get(i);
				//System.out.println("File Name " + file.getOriginalFilename());
				if (file.getSize() > 0) {
					File dir = new File("D:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "HearingStageDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueHearId++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedHearStageDoc_" + hearingStage.getTtrn_hearing_stage() + "_"
							+ lastGeneratedValueHearId + "." + file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					HearingStageDocuments stage_documents = new HearingStageDocuments();

					stage_documents.setHear_stage_id(hearingStage.getTtrn_hearing_stage());
					stage_documents.setHear_stage_original_file_name(originalFileName);
					stage_documents.setHear_stage_generated_file_name(
							"D:/"+project_name+"/Documents/HearingStageDocuments/" + generatedFileName);
					stage_documents.setHear_stage_last_generated_value_for_hear_stage_id(lastGeneratedValueHearId);
					stage_documents.setHear_stage_created_at(utilitieservice.getCurrentDate());
					stage_documents.setHear_stage_added_by(utilitieservice.getCurrentSessionUserId(httpSession));

					litigationDocumentsDao.saveHearingSatgeDocument(stage_documents);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created By: Harshad Padole
	// Method Purpose: Get Hearing details By hearing stage id
	@Override
	public HearingStage getHearingDetailsByStageId(int hear_stage_id) {
		try {
			HearingStage hearingStage = litigationDao.getHearingDetailsByStageId(hear_stage_id);
			String email = null;
			if (hearingStage.getTtrn_additional_mail_id_1() != null)
				email = hearingStage.getTtrn_additional_mail_id_1();

			if (hearingStage.getTtrn_additional_mail_id_2() != null)
				email += "," + hearingStage.getTtrn_additional_mail_id_2();

			if (hearingStage.getTtrn_additional_mail_id_3() != null)
				email += "," + hearingStage.getTtrn_additional_mail_id_3();

			// String email =
			// hearingStage.getTtrn_additional_mail_id_1()+","+hearingStage.getTtrn_additional_mail_id_2()+","+hearingStage.getTtrn_additional_mail_id_3();
			hearingStage.setTtrn_additional_mail_id_1(email);
			return hearingStage;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Harshad Padole
	// Method Purpose: Update litigation
	@Override
	public void updateLitigation(Litigation litigation, String status, HttpSession session, String esc_internal_resource, String esc_law_firm, String esc_appear_counsel, String esc_others) {
		try {
			Litigation OldLitigation = litigationDao.getLitigationByLitiId(litigation.getLiti_id());

			litigation.setLiti_litigation_result(OldLitigation.getLiti_litigation_result());
			litigation.setLiti_disposal_date(OldLitigation.getLiti_disposal_date());
			litigation.setLiti_synopsis_of_order(OldLitigation.getLiti_synopsis_of_order());
			litigation.setLiti_last_date_for_filling_appeal(OldLitigation.getLiti_last_date_for_filling_appeal());
			litigation.setLiti_comments(OldLitigation.getLiti_comments());
			if(status.equals("Pending")){
			    litigation.setLiti_status(status);
			}
			else{
				litigation.setLiti_status(OldLitigation.getLiti_status());
				litigation.setLiti_assigned_to(0);
				litigation.setLiti_internally_handled_by(0);
			}
			litigation.setLiti_case_title(litigation.getLiti_party_by() + " ...."+ litigation.getLiti_company_acting_as() 
			+ " V/s " + litigation.getLiti_party_against()
							+ " ...." + litigation.getLiti_opp_party_acting_as());
			litigation.setLiti_client_liti_id(OldLitigation.getLiti_client_liti_id());
			//litigation.setLiti_client_liti_id("L" + (String.format("%02d", litigation.getLiti_dept_id())
				//	+ String.format("%07d", (OldLitigation.getLiti_last_generated_value_for_client_liti_id()))));
			litigation.setLiti_created_at(OldLitigation.getLiti_created_at());
			litigation.setLiti_last_generated_value_for_client_liti_id(
					OldLitigation.getLiti_last_generated_value_for_client_liti_id());
			litigation.setLiti_advocate_law_firm(litigation.getLiti_advocate_law_firm());
			litigation.setLiti_oppo_advo_law_firm(litigation.getLiti_oppo_advo_law_firm());
			litigation.setLiti_opposite_party_advocate(litigation.getLiti_opposite_party_advocate());
			litigation.setLiti_oppo_advo_contact(litigation.getLiti_oppo_advo_contact());
			litigation.setLiti_oppo_party_address(litigation.getLiti_oppo_party_address());
			litigation.setLiti_jurisdiction_name(litigation.getLiti_jurisdiction_name());
			litigation.setLiti_previous_liti_ref_no(litigation.getLiti_previous_liti_ref_no());
			litigation.setLiti_added_by(OldLitigation.getLiti_added_by());
			litigationDao.updateLitigation(litigation);
			
			if(litigation.getLiti_criticality().equals("High")) {
				sendMailService.sendLitigationHandledMailToInternallyHandledPerson(OldLitigation.getLiti_id(), session);
			}
			
			LitigationEscalationMailId litiMail = litigationDao.getEscalationMailByLitiId(litigation.getLiti_id());
				
			if(litiMail != null){
				
				litiMail.setEsc_appear_counsel(esc_appear_counsel);
				litiMail.setEsc_internal_resource(esc_internal_resource);
				litiMail.setEsc_law_firm(esc_law_firm);
				litiMail.setEsc_others(esc_others);
				litiMail.setEsc_added_by(litiMail.getEsc_added_by());
				litiMail.setEsc_created_at(litiMail.getEsc_created_at());
				litiMail.setEsc_liti_id(litigation.getLiti_id());
				litiMail.setEsc_id(litiMail.getEsc_id());
				litigationDao.update(litiMail);
					
			} else {
				
				LitigationEscalationMailId mail = new LitigationEscalationMailId();
				mail.setEsc_appear_counsel(esc_appear_counsel);
				mail.setEsc_internal_resource(esc_internal_resource);
				mail.setEsc_law_firm(esc_law_firm);
				mail.setEsc_others(esc_others);
				mail.setEsc_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
				mail.setEsc_created_at(utilitieservice.getCurrentDate());
				mail.setEsc_liti_id(litigation.getLiti_id());
				litigationDao.persist(mail);
			}
			if (!OldLitigation.getLiti_status().toString().equals("Draft")) {
				// persist litigation logs
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(litigation.getLiti_id());
				logs.setLog_assinged_to_id(litigation.getLiti_internally_handled_by());
				logs.setLog_activity("Update");
				logs.setLog_related_to("Litigation");
				logs.setLog_sub_activity("Update Litigation");
				logs.setLog_related_name(litigation.getLiti_case_title());
				logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
				logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
				litigationDao.persist(logs);
			}
					
			if (status.equals("Pending") && OldLitigation.getLiti_status().toString().equals("Draft")) {
				sendMailService.sendLitigationHandledMailToInternallyHandledPerson(OldLitigation.getLiti_id(), session);

				// persist litigation logs
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(OldLitigation.getLiti_id());
				logs.setLog_assinged_to_id(litigation.getLiti_internally_handled_by());
				logs.setLog_activity("Add");
				logs.setLog_related_to("Litigation");
				logs.setLog_sub_activity("Add Litigation");
				logs.setLog_related_name(litigation.getLiti_case_title());
				logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
				logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
				litigationDao.persist(logs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created By: Harshad Padole
	// Method Purpose: Get litigation by id
	@Override
	public Litigation getLitigationByLitiId(int liti_id) {
		try {
			return litigationDao.getLitigationByLitiId(liti_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Harshad Padole
	// Method Purpose: Search Litigation
	@SuppressWarnings("unchecked")
	@Override
	public String searchLitigation(String json, HttpSession session) {
		try {
			String next_hearing_date = "";
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			JSONArray sendList = new JSONArray();
			List<Litigation> result = litigationDao.searchLitigation(json, session);
			Iterator<Litigation> iterator = result.iterator();
			while (iterator.hasNext()) {
				Litigation litigation = iterator.next();
				JSONObject jsonObject = new JSONObject();

				jsonObject.put("liti_id", litigation.getLiti_id());
				jsonObject.put("liti_client_id", litigation.getLiti_client_liti_id());
				jsonObject.put("liti_by_againts", litigation.getLiti_against_by_id());
				next_hearing_date = litigationDao.getLatestNextHearingStageDate(litigation.getLiti_id());
				
				if (!next_hearing_date.equals("NA")) {
					jsonObject.put("liti_next_hearing_date", sdfOut.format(sdfIn.parse(next_hearing_date)));
				} else {
					jsonObject.put("liti_next_hearing_date", "NA");
				}

				jsonObject.put("liti_case_ref_no", litigation.getLiti_case_reference_no());
				jsonObject.put("liti_case_title", litigation.getLiti_case_title());
				jsonObject.put("liti_status", litigation.getLiti_status());
				jsonObject.put("liti_added_by", litigation.getLiti_added_by());
				jsonObject.put("liti_user_id", session.getAttribute("sess_user_id"));
				jsonObject.put("liti_user_role", session.getAttribute("sess_user_role"));
				sendList.add(jsonObject);
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Download hearing document
	@Override
	public void downloadHearingDocuments(int hearing_doc_id, HttpServletResponse response) {
		try {
			String path = litigationDocumentsDao.getHearingDocumentFilePath(hearing_doc_id);
			if (path != null) {
				File file = new File(path);
				InputStream is = new FileInputStream(file);

				response.setContentType("application/octet-stream");

				response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

				OutputStream os = response.getOutputStream();
				byte[] buffer = new byte[1024];
				int len;
				while ((len = is.read(buffer)) != -1) {
					os.write(buffer, 0, len);
				}
				os.flush();
				os.close();
				is.close();
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Harshad Padole
	// Method Purpose : save litigation completion
	@Override
	public void saveLitigationCompletion(Litigation litigation, ArrayList<MultipartFile> liti_completion_doc) {
		try {
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			Litigation OldLitigation = litigationDao.getLitigationByLitiId(litigation.getLiti_id());
			//System.out.println(litigation.getLiti_litigation_result()+" dis:"+litigation.getLiti_disposal_date()+" synop:"+litigation.getLiti_synopsis_of_order()+" cou:"+litigation.getLiti_completion_court_id()+" filing:"+litigation.getLiti_last_date_for_filling_appeal()+" comm:"+litigation.getLiti_comments());
			// add new data to old Litigation data
			OldLitigation.setLiti_litigation_result(litigation.getLiti_litigation_result());
			OldLitigation.setLiti_disposal_date(litigation.getLiti_disposal_date());
			if(!litigation.getLiti_synopsis_of_order().equals(""))
			OldLitigation.setLiti_synopsis_of_order(litigation.getLiti_synopsis_of_order());
			if(litigation.getLiti_completion_court_id() != 0)
			OldLitigation.setLiti_completion_court_id(litigation.getLiti_completion_court_id());
			if(!litigation.getLiti_last_date_for_filling_appeal().equals("") || litigation.getLiti_last_date_for_filling_appeal() != null)
			OldLitigation.setLiti_last_date_for_filling_appeal(litigation.getLiti_last_date_for_filling_appeal());
			OldLitigation.setLiti_comments(litigation.getLiti_comments());
			OldLitigation.setLiti_status("Completed");
			// Pass old data and new data to update
			litigationDao.updateLitigation(OldLitigation);
			
			//persist litigation logs 
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(litigation.getLiti_id());
			logs.setLog_assinged_to_id(OldLitigation.getLiti_internally_handled_by());
			logs.setLog_activity("Completed");
			logs.setLog_related_to("Litigation");
			logs.setLog_sub_activity("Completion");
			logs.setLog_related_name(litigation.getLiti_case_title());
			logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
			litigationDao.persist(logs);

			//send mail to admin and number of mail ids which enter in litigation are marked in cc
			
			//get mail ids of all admin
			ArrayList<String> email = new ArrayList<String>(); 
			List<Object> mail_idCC = schedulerService.getAllAdminUser();
			Iterator<Object> iterator = mail_idCC.iterator();
			while (iterator.hasNext()) {
				Object object[] = (Object[]) iterator.next();
				if(object[1] != null)
				email.add(object[1].toString());
			}
			
			String email_subject = "Litigation is Completed ";
			String email_body= " ";
			email_body += "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
			email_body += "<h2 style='font-size:18px;'>Dear User,</h2>";
			email_body += "<p style='text-align:justify;width:70%;'>The following litigation has been completed. You are requested to follow the link by clicking on the 'LitigationID'.</p>"
					+ "<h2 style='font-size:16px;font-weight:bold;'>Litigation details :</h2>";
			email_body += "<table style='width:80%;' border='1'>" 
					+ "<thead>"
					+ "<tr style='background:#0B6EC3;color:#fff;'>"
					+ "<th>Litigation ID</th>"
					+ "<th>Result</th>" 
					+ "<th>Synopsis of order</th>"
					//+ "<th>Court</th>" 
					+ "<th>Filing appeal last date</th>" 
					+ "<th>Comments</th>" 
					+ "</tr>" + "</thead>" + "<tbody>";
			
				String litigation_id = "";
			
				litigation_id = String.valueOf(OldLitigation.getLiti_id());
				String case_filing_date = sdfOut.format(litigation.getLiti_last_date_for_filling_appeal());
				String result = litigation.getLiti_litigation_result();
				String synopsis_order = litigation.getLiti_synopsis_of_order();
				String comments = litigation.getLiti_comments();
				
				email_body += "<tr>" 
						+ "<td><a href='" + project_url + "litigationDetails?liti_id=" + litigation_id	+ "'>" + litigation_id + "</a></td>" 
						+ "<td>" + result +" </td>" 
						+ "<td>" + synopsis_order + "</td>" 	
						+ "<td>" + case_filing_date + "</td>" 
						+ "<td>" + comments + "</td>" 
						+ "</tr>";
			
			email_body += "</tbody>" + "</table>";
			email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
					+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
					+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
					+ "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
			//System.out.println(email_body);
			
			InternetAddress[] address = new InternetAddress[email.size()];
			try {
				for (int i = 0; i < email.size(); i++) {
					address[i] = new InternetAddress(email.get(i));
				}
			} catch (AddressException e) {
				e.printStackTrace();
			}
			
			String liti_Allmail_id = "";
			Litigation liti = litigationDao.getLitigationByLitiId(OldLitigation.getLiti_id());
			
			if(!liti.getLiti_mail_id_cc().equals(null)){
			liti_Allmail_id = liti.getLiti_mail_id_cc();
			String[] mail_id = liti_Allmail_id.split(",");
			
			InternetAddress[] addressCC = new InternetAddress[mail_id.length];
			try {
				for (int i = 0; i < mail_id.length; i++) {
					addressCC[i] = new InternetAddress(mail_id[i]);
				}
			} catch (AddressException e) {
				e.printStackTrace();
			}
			sendMailService.sendMail(email_subject, email_body, address, addressCC, litigation_id);
			} else{
			sendMailService.sendMail(email_subject, email_body, address, address, litigation_id);	
			}
			
			String originalFileName = null;
			String generatedFileName = null;
			for (int i = 0; i < liti_completion_doc.size(); i++) {
				MultipartFile file = liti_completion_doc.get(i);
				int lastGeneratedValueForLitiDoc = litigationDocumentsDao
						.getLastGeneratedValueByLitiId(litigation.getLiti_id());
				if (lastGeneratedValueForLitiDoc >= 0) {
					if (file.getSize() > 0) {
						File dir = new File("D:" + File.separator + project_name + File.separator + "Documents"
								+ File.separator + "LitigationDocuments");
						if (!dir.exists())
							dir.mkdirs();

						lastGeneratedValueForLitiDoc++;
						originalFileName = file.getOriginalFilename();
						generatedFileName = "uploadedLitiDoc_" + litigation.getLiti_id() + "_"
								+ lastGeneratedValueForLitiDoc + "." + file.getOriginalFilename().split("\\.")[1];
						File newFile = new File(dir.getPath() + File.separator + generatedFileName);
						if (!newFile.exists()) {
							newFile.createNewFile();
						}

						@SuppressWarnings("resource")
						OutputStream outputStream = new FileOutputStream(newFile);

						outputStream.write(file.getBytes());

						LitigationDocuments litigationDocuments = new LitigationDocuments();

						litigationDocuments.setLdoc_liti_id(litigation.getLiti_id());
						litigationDocuments.setLdoc_original_file_name(originalFileName);
						litigationDocuments.setLdoc_generated_file_name(
								"D:/"+project_name+"/Documents/LitigationDocuments/" + generatedFileName);
						litigationDocuments.setLdoc_last_generated_value_for_liti_id(lastGeneratedValueForLitiDoc);
						litigationDocuments.setLdoc_document_type("LitigationCompletion");

						litigationDocumentsDao.saveDocuments(litigationDocuments);
					}
				} else {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created : Harshad Padole
	// Method Purpose : save counsel fees
	@Override
	public int saveCounselFees(LitigationCounselFees counselFees, ArrayList<MultipartFile> counsel_agreed_doc) {
		try {
			counselFees.setLcou_created_at(utilitieservice.getCurrentDate());
			counselFees.setLcou_counsel_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
		    litigationDao.saveCounselFees(counselFees);
		    
		    //persist litigation logs 
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(counselFees.getLcou_liti_id());
			logs.setLog_sub_activity_id(counselFees.getLcou_id());
			logs.setLog_activity("Add");
			logs.setLog_related_to("Litigation");
			logs.setLog_sub_activity("Litigation Counsel Fees");
			logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
			litigationDao.persist(logs);
		    
		    //upload documents
			String originalFileName = null;
			String generatedFileName = null;
			
			for (int i = 0; i < counsel_agreed_doc.size(); i++) {
				MultipartFile file = counsel_agreed_doc.get(i);
				int lastGeneratedValueForFeesDoc = litigationDocumentsDao
						.getLastGeneratedValueByFeesId(counselFees.getLcou_id(), "AAC");
				if (lastGeneratedValueForFeesDoc >= 0) {
					if (file.getSize() > 0) {
						File dir = new File("D:" + File.separator + project_name + File.separator + "Documents"
								+ File.separator + "LitigationFeesDocuments"+ File.separator +"CounselFeesDocuments"+File.separator +"CounselFeesAgreedDocuments");
						if (!dir.exists())
							dir.mkdirs();

						lastGeneratedValueForFeesDoc++;
						originalFileName = file.getOriginalFilename();
						generatedFileName = "uploadedLitiFeesDoc_" + counselFees.getLcou_id() + "_"
								+ lastGeneratedValueForFeesDoc + "." + file.getOriginalFilename().split("\\.")[1];
						File newFile = new File(dir.getPath() + File.separator + generatedFileName);
						if (!newFile.exists()) {
							newFile.createNewFile();
						}

						@SuppressWarnings("resource")
						OutputStream outputStream = new FileOutputStream(newFile);

						outputStream.write(file.getBytes());

						LitigationFeesDocuments litigationDocuments = new LitigationFeesDocuments();

						litigationDocuments.setFdoc_fees_id(counselFees.getLcou_id());
						litigationDocuments.setFdoc_original_file_name(originalFileName);
						litigationDocuments.setFdoc_generated_file_name(
								"D:/"+project_name+"/Documents/LitigationFeesDocuments/CounselFeesDocuments/CounselFeesAgreedDocuments/" + generatedFileName);
						litigationDocuments.setFdoc_last_generated_value_for_fees_id(lastGeneratedValueForFeesDoc);
						litigationDocuments.setFdoc_document_type("AAC");

						litigationDocumentsDao.saveDocuments(litigationDocuments);
					}
				} else {
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method Purpose : get fees details by litigation id
	@Override
	public List<CounselFeesDetails> getCounselFeesByLitiId(int liti_id) {
		try {
			List<CounselFeesDetails> sendList = new ArrayList<>();
			List<Object> result = litigationDao.getCounselFeesDeatilsByLitiId(liti_id);
			Iterator<Object> iterator = result.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				CounselFeesDetails counselFeesDetails = new CounselFeesDetails();
				counselFeesDetails.setLcou_id(Integer.parseInt(objects[0].toString()));
				counselFeesDetails.setLcou_counsel_name(objects[1].toString());
				counselFeesDetails.setLcou_type_of_fees(objects[2].toString());
				counselFeesDetails.setLcou_effective_non_effective(objects[3].toString());
				counselFeesDetails.setLcou_counsel_fees_amount(objects[4].toString() + " " + objects[6].toString());
				counselFeesDetails.setLcou_comments(objects[5].toString());
				counselFeesDetails.setFees_doc(litigationDocumentsDao.getAllDocumentByFeesId(LitigationFeesDocuments.class, Integer.parseInt(objects[0].toString()) ,"AAC"));//AAC - Agreed Amount Counsel 
				counselFeesDetails.setLcoun_fees_added_by(Integer.parseInt(objects[7].toString()));
				sendList.add(counselFeesDetails);
			}
			return sendList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : get only list of user who handle the litigation matters
	@Override
	public List<Object> getMatterHandleByUsers() {
		try {
			return litigationDao.getMatterHandleByUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Purpose : Get external Counsel name because in hearing stage External
	// counsel not mandatory
	@Override
	public String externalCounselName(int exte_coun_id) {
		try {
			ExternalCounsel counsel = externalCounselService.getExte_CounById(exte_coun_id);
			return counsel.getExte_coun_name();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Purpose : Get Responsible person name because in hearing stage
	// responsible person not mandatory
	@Override
	public String responsiblePersonName(int user_id) {
		try {
			return userService.getUserFullNameById(user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Purpose : Get counsel as per law firm
	@SuppressWarnings("unchecked")
	@Override
	public String getCounselByLawFirmId(int law_firm_id) {
		try {
			JSONArray sendList = new JSONArray();
			List<ExternalCounsel> res = litigationDao.getConselAsPerLawFirm(law_firm_id);
			Iterator<ExternalCounsel> iterator = res.iterator();
			while (iterator.hasNext()) {
				ExternalCounsel counsel = iterator.next();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("counsel_id", counsel.getExte_coun_id());
				jsonObject.put("counsel_name", counsel.getExte_coun_name());

				sendList.add(jsonObject);

			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getLawFirmExternalCounsel(int liti_id) {
		try {
			return litigationDao.getLawFirmExternalCounsel(liti_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Purpose : Save advocate fees
	@Override
	public int saveAdvocateFees(LitigationAdvocateFees advocateFeesDetails, ArrayList<MultipartFile> advocate_agreed_doc) {
		try {
			advocateFeesDetails.setLadv_created_at(utilitieservice.getCurrentDate());
			advocateFeesDetails.setLadv_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			litigationDao.persist(advocateFeesDetails);
			
			//persist litigation logs 
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(advocateFeesDetails.getLadv_litigation_id());
			logs.setLog_sub_activity_id(advocateFeesDetails.getLadv_id());
			logs.setLog_activity("Add");
			logs.setLog_related_to("Litigation");
			logs.setLog_sub_activity("Litigation Advocate Fees");
			logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
			litigationDao.persist(logs);
			
			//upload documents
			String originalFileName = null;
			String generatedFileName = null;
			
			for (int i = 0; i < advocate_agreed_doc.size(); i++) {
				MultipartFile file = advocate_agreed_doc.get(i);
				int lastGeneratedValueForFeesDoc = litigationDocumentsDao
						.getLastGeneratedValueByFeesId(advocateFeesDetails.getLadv_id(), "AAA");
				if (lastGeneratedValueForFeesDoc >= 0) {
					if (file.getSize() > 0) {
						File dir = new File("D:" + File.separator + project_name + File.separator + "Documents"
								+ File.separator + "LitigationFeesDocuments"+ File.separator +"AdvocateFeesDocuments"+ File.separator +"AdvocateFeesAgreedDocuments");
						if (!dir.exists())
							dir.mkdirs();

						lastGeneratedValueForFeesDoc++;
						originalFileName = file.getOriginalFilename();
						generatedFileName = "uploadedLitiFeesDoc_" + advocateFeesDetails.getLadv_id() + "_"
								+ lastGeneratedValueForFeesDoc + "." + file.getOriginalFilename().split("\\.")[1];
						File newFile = new File(dir.getPath() + File.separator + generatedFileName);
						if (!newFile.exists()) {
							newFile.createNewFile();
						}

						@SuppressWarnings("resource")
						OutputStream outputStream = new FileOutputStream(newFile);

						outputStream.write(file.getBytes());

						LitigationFeesDocuments litigationDocuments = new LitigationFeesDocuments();

						litigationDocuments.setFdoc_fees_id(advocateFeesDetails.getLadv_id());
						litigationDocuments.setFdoc_original_file_name(originalFileName);
						litigationDocuments.setFdoc_generated_file_name(
								"D:/"+project_name+"/Documents/LitigationFeesDocuments/AdvocateFeesDocuments/AdvocateFeesAgreedDocuments/" + generatedFileName);
						litigationDocuments.setFdoc_last_generated_value_for_fees_id(lastGeneratedValueForFeesDoc);
						litigationDocuments.setFdoc_document_type("AAA");

						litigationDocumentsDao.saveDocuments(litigationDocuments);
					}
				} else {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Purpose : Get Advocate fees details by litigation id for listing
	@Override
	public List<AdvocateFeesDetails> getAdvocateFeesDetailsByLitiId(int liti_id) {
		try {
			List<AdvocateFeesDetails> sendList = new ArrayList<>();
			List<Object> result = litigationDao.getAdvocateFeesDeatilsByLitiId(liti_id);
			Iterator<Object> iterator = result.iterator();
			while (iterator.hasNext()) {
				AdvocateFeesDetails advocateFeesDetails = new AdvocateFeesDetails();
				Object[] objects = (Object[]) iterator.next();

				advocateFeesDetails.setLadv_advocate_id(Integer.parseInt(objects[0].toString()));
				advocateFeesDetails.setLadv_advocate_name(objects[1].toString());
				advocateFeesDetails.setLadv_type_of_fees(objects[2].toString());
				advocateFeesDetails.setLadv_effective_non_effective(objects[3].toString());
				advocateFeesDetails.setLadv_advocate_fees_amount(objects[4].toString() + " " + objects[8].toString());
				advocateFeesDetails.setLadv_comments(objects[5].toString());
				advocateFeesDetails.setLadv_id(Integer.parseInt(objects[7].toString()));
				advocateFeesDetails.setLadv_added_by(Integer.parseInt(objects[9].toString()));
				advocateFeesDetails.setFees_doc(litigationDocumentsDao.getAllDocumentByFeesId(LitigationFeesDocuments.class, Integer.parseInt(objects[7].toString()), "AAA"));
				sendList.add(advocateFeesDetails);
			}
			return sendList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Purpose : Get counsel fees details to edit
	@Override
	public LitigationCounselFees getCounselFeesDetailsById(int lcou_id) {
		try {
			return litigationDao.getCounselFeesDetailsById(lcou_id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Purpose : Get Advocate fees details to edit
	@Override
	public LitigationAdvocateFees getAdvocateFeesDetailsById(int ladv_id) {
		try {
			return litigationDao.getAdvocateFeesDetailsById(ladv_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Update Counsel fees details
	@Override
	public void updateCounselFees(LitigationCounselFees counselFees, ArrayList<MultipartFile> counsel_agreed_doc) {
		try {
			LitigationCounselFees OldcounselFees = litigationDao.getCounselFeesDetailsById(counselFees.getLcou_id());
			if (!counselFees.getLcou_type_of_fees().equals("appearance"))
				counselFees.setLcou_effective_non_effective("NA");

			counselFees.setLcou_created_at(OldcounselFees.getLcou_created_at());
			counselFees.setLcou_counsel_added_by(OldcounselFees.getLcou_counsel_added_by());
			litigationDao.merge(counselFees);
			
			//persist litigation logs 
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(counselFees.getLcou_liti_id());
			logs.setLog_sub_activity_id(counselFees.getLcou_id());
			logs.setLog_activity("Update");
			logs.setLog_related_to("Litigation");
			logs.setLog_sub_activity("Litigation Counsel Fees");
			logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
			litigationDao.persist(logs);
			
			//upload documents
			String originalFileName = null;
			String generatedFileName = null;
			
			for (int i = 0; i < counsel_agreed_doc.size(); i++) {
				MultipartFile file = counsel_agreed_doc.get(i);
				int lastGeneratedValueForFeesDoc = litigationDocumentsDao
						.getLastGeneratedValueByFeesId(counselFees.getLcou_id(), "AAC");
				if (lastGeneratedValueForFeesDoc >= 0) {
					if (file.getSize() > 0) {
						File dir = new File("D:" + File.separator + project_name + File.separator + "Documents"
								+ File.separator + "LitigationFeesDocuments"+ File.separator +"CounselFeesDocuments"+File.separator +"CounselFeesAgreedDocuments");
						if (!dir.exists())
							dir.mkdirs();

						lastGeneratedValueForFeesDoc++;
						originalFileName = file.getOriginalFilename();
						generatedFileName = "uploadedLitiFeesDoc_" + counselFees.getLcou_id() + "_"
								+ lastGeneratedValueForFeesDoc + "." + file.getOriginalFilename().split("\\.")[1];
						File newFile = new File(dir.getPath() + File.separator + generatedFileName);
						if (!newFile.exists()) {
							newFile.createNewFile();
						}

						@SuppressWarnings("resource")
						OutputStream outputStream = new FileOutputStream(newFile);

						outputStream.write(file.getBytes());

						LitigationFeesDocuments litigationDocuments = new LitigationFeesDocuments();

						litigationDocuments.setFdoc_fees_id(counselFees.getLcou_id());
						litigationDocuments.setFdoc_original_file_name(originalFileName);
						litigationDocuments.setFdoc_generated_file_name(
								"D:/"+project_name+"/Documents/LitigationFeesDocuments/CounselFeesDocuments/CounselFeesAgreedDocuments" + generatedFileName);
						litigationDocuments.setFdoc_last_generated_value_for_fees_id(lastGeneratedValueForFeesDoc);
						litigationDocuments.setFdoc_document_type("AAC");

						litigationDocumentsDao.saveDocuments(litigationDocuments);
					}
				} else {
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Harshad Padole
	// Method Purpose : Update Advocate fees details
	@Override
	public void updateAdvocateFees(LitigationAdvocateFees advocateFees, ArrayList<MultipartFile> advocate_agreed_doc) {
		try {
			LitigationAdvocateFees OldadvocateFees = litigationDao.getAdvocateFeesDetailsById(advocateFees.getLadv_id());
			if (!advocateFees.getLadv_type_of_fees().equals("appearance"))
				advocateFees.setLadv_effective_non_effective("NA");

			advocateFees.setLadv_added_by(OldadvocateFees.getLadv_added_by());
			advocateFees.setLadv_created_at(OldadvocateFees.getLadv_created_at());
			litigationDao.merge(advocateFees);
			
			//persist litigation logs 
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(advocateFees.getLadv_litigation_id());
			logs.setLog_sub_activity_id(advocateFees.getLadv_id());
			logs.setLog_activity("Update");
			logs.setLog_related_to("Litigation");
			logs.setLog_sub_activity("Litigation Advocate Fees");
			logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
			litigationDao.persist(logs);
			
			//upload documents
			String originalFileName = null;
			String generatedFileName = null;
			
			for (int i = 0; i < advocate_agreed_doc.size(); i++) {
				MultipartFile file = advocate_agreed_doc.get(i);
				int lastGeneratedValueForFeesDoc = litigationDocumentsDao
						.getLastGeneratedValueByFeesId(advocateFees.getLadv_id(), "AAA");
				if (lastGeneratedValueForFeesDoc >= 0) {
					if (file.getSize() > 0) {
						File dir = new File("D:" + File.separator + project_name + File.separator + "Documents"
								+ File.separator + "LitigationFeesDocuments"+ File.separator +"AdvocateFeesDocuments"+ File.separator +"AdvocateFeesAgreedDocuments");
						if (!dir.exists())
							dir.mkdirs();

						lastGeneratedValueForFeesDoc++;
						originalFileName = file.getOriginalFilename();
						generatedFileName = "uploadedLitiFeesDoc_" + advocateFees.getLadv_id() + "_"
								+ lastGeneratedValueForFeesDoc + "." + file.getOriginalFilename().split("\\.")[1];
						File newFile = new File(dir.getPath() + File.separator + generatedFileName);
						if (!newFile.exists()) {
							newFile.createNewFile();
						}

						@SuppressWarnings("resource")
						OutputStream outputStream = new FileOutputStream(newFile);

						outputStream.write(file.getBytes());

						LitigationFeesDocuments litigationDocuments = new LitigationFeesDocuments();

						litigationDocuments.setFdoc_fees_id(advocateFees.getLadv_id());
						litigationDocuments.setFdoc_original_file_name(originalFileName);
						litigationDocuments.setFdoc_generated_file_name(
								"D:/"+project_name+"/Documents/LitigationFeesDocuments/AdvocateFeesDocuments/AdvocateFeesAgreedDocuments/" + generatedFileName);
						litigationDocuments.setFdoc_last_generated_value_for_fees_id(lastGeneratedValueForFeesDoc);
						litigationDocuments.setFdoc_document_type("AAA");

						litigationDocumentsDao.saveDocuments(litigationDocuments);
					
				} else {
				}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<LitigationReference> getAllListLitigation(HttpSession session) {
		try {
			String next_hearing_date = "";
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			List<LitigationReference> sendLitst = new ArrayList<>();
			
			List<Object> result = litigationDao.getAllListLitigationAccessWise(session);

			if (result != null) {
				Iterator<Object> iterator = result.iterator();
				while (iterator.hasNext()) {
					Object[] object = (Object[]) iterator.next();
					LitigationReference litigationReference = new LitigationReference();

					litigationReference.setLiti_id(Integer.parseInt(object[0].toString()));

					litigationReference.setLiti_against_by_id(object[1].toString());
					next_hearing_date = litigationDao
							.getLatestNextHearingStageDate(Integer.parseInt(object[0].toString()));
					if (!next_hearing_date.equals("NA")) {
						litigationReference.setLiti_next_hearing_date(sdfOut.format(sdfIn.parse(next_hearing_date)));
					} else {
						litigationReference.setLiti_next_hearing_date("NA");
					}
					//System.out.println("hearing date :"+sdfIn.parse(next_hearing_date)+" todays date :"+new Date());
					litigationReference.setLiti_case_reference_no(object[3].toString());
					if(object[2]!=null)
					litigationReference.setLiti_type_name(object[2].toString());
					else
					litigationReference.setLiti_type_name("NA");
					
					if(object[4]!=null)
					litigationReference.setLiti_case_filing_date(sdfOut.format(sdfIn.parse(object[4].toString())));
					litigationReference.setLiti_case_filing_date("NA");
					if(Integer.parseInt(object[5].toString()) != 0 ){
						litigationReference.setLiti_external_counsel(externalCounselService.getExte_CounById(Integer.parseInt(object[5].toString())).getExte_coun_name());
					}else{
					litigationReference.setLiti_external_counsel("NA");
					}
					if(object[6]!=null)
					litigationReference.setLiti_internally_handled_name(object[6].toString() +' '+object[7].toString());
					else
						litigationReference.setLiti_internally_handled_name("NA");
					if(object[8] != null)
					litigationReference.setLiti_party_by(object[8].toString());
					else
						litigationReference.setLiti_party_by("NA");
					litigationReference.setLiti_amount_involved(object[10].toString());
					litigationReference.setLiti_relevant_law(object[11].toString());
					litigationReference.setLiti_brief_description(object[12].toString());
					litigationReference.setLiti_status(object[13].toString());
					if(object[21]!=null)
					litigationReference.setLiti_court(object[21].toString());
					else
					litigationReference.setLiti_court("NA");
					
					litigationReference.setLiti_client_liti_id(object[23].toString());
					litigationReference.setLiti_case_title(object[24].toString());
					if (object[25] != null) {
						litigationReference.setLiti_intenal_person(object[25].toString());
					} else {
						litigationReference.setLiti_intenal_person("NA");
					}
						litigationReference.setLiti_added_by(Integer.parseInt(object[26].toString()));
					
					if(object[27] != null)
						litigationReference.setLiti_oppo_party_address(object[27].toString());
					else
						litigationReference.setLiti_oppo_party_address("NA");	
					if(object[28] != null)
						litigationReference.setLiti_oppo_advo_law_firm(object[28].toString());
					else
						litigationReference.setLiti_oppo_advo_law_firm("NA");
					if(object[29] != null)
						litigationReference.setLiti_opposite_party_advocate(object[29].toString());
					else
						litigationReference.setLiti_opposite_party_advocate("NA");
					if(object[30] != null)
						litigationReference.setLiti_oppo_advo_contact(object[30].toString());
					else
						litigationReference.setLiti_oppo_advo_contact("NA");
					if(object[31] != null)
						litigationReference.setLiti_jurisdiction_name(object[31].toString());
					else
						litigationReference.setLiti_jurisdiction_name("NA");
					if(object[32] != null)
						litigationReference.setLiti_party_against(object[32].toString());
					else
						litigationReference.setLiti_party_against("NA");
					
					
					sendLitst.add(litigationReference);

				}
			}
			return sendLitst;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// Method Created : Harshad Padole
	// Method Purpose : Save paid counsel fees
	@Override
	public int savePaidCounselFees(CounselPaidFees fees, ArrayList<MultipartFile> counsel_paid_doc) {
		try {
			LitigationCounselFees counselFees = litigationDao.getCounselFeesDetailsById(fees.getCpaid_counsel_fees_id());
			fees.setCpaid_counsel_id(counselFees.getLcou_counsel_id());
			fees.setCpaid_liti_id(counselFees.getLcou_liti_id());
			fees.setCpaid_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			fees.setCpaid_created_at(utilitieservice.getCurrentDate());
			litigationDao.persist(fees);
			
			// persist Logs of Litigation
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(fees.getCpaid_liti_id());
			logs.setLog_sub_activity_id(fees.getCpaid_id());
			logs.setLog_activity("Add");
			logs.setLog_related_to("Litigation");
			logs.setLog_sub_activity("Paid Counsel Fees");
			logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
			litigationDao.persist(logs);
			
			//upload documents
			String originalFileName = null;
			String generatedFileName = null;
			
			for (int i = 0; i < counsel_paid_doc.size(); i++) {
				MultipartFile file = counsel_paid_doc.get(i);
				int lastGeneratedValueForFeesDoc = litigationDocumentsDao
						.getLastGeneratedValueByFeesId(fees.getCpaid_id(), "PC");
				if (lastGeneratedValueForFeesDoc >= 0) {
					if (file.getSize() > 0) {
						File dir = new File("D:" + File.separator + project_name + File.separator + "Documents"
								+ File.separator + "LitigationFeesDocuments"+ File.separator +"CounselFeesDocuments"+File.separator +"CounselFeesPaidDocuments");
						if (!dir.exists())
							dir.mkdirs();

						lastGeneratedValueForFeesDoc++;
						originalFileName = file.getOriginalFilename();
						generatedFileName = "uploadedLitiFeesDoc_" + fees.getCpaid_id() + "_"
								+ lastGeneratedValueForFeesDoc + "." + file.getOriginalFilename().split("\\.")[1];
						File newFile = new File(dir.getPath() + File.separator + generatedFileName);
						if (!newFile.exists()) {
							newFile.createNewFile();
						}

						@SuppressWarnings("resource")
						OutputStream outputStream = new FileOutputStream(newFile);

						outputStream.write(file.getBytes());

						LitigationFeesDocuments litigationDocuments = new LitigationFeesDocuments();

						litigationDocuments.setFdoc_fees_id(fees.getCpaid_id());
						litigationDocuments.setFdoc_original_file_name(originalFileName);
						litigationDocuments.setFdoc_generated_file_name(
								"D:/"+project_name+"/Documents/LitigationFeesDocuments/CounselFeesDocuments/CounselFeesPaidDocuments/" + generatedFileName);
						litigationDocuments.setFdoc_last_generated_value_for_fees_id(lastGeneratedValueForFeesDoc);
						litigationDocuments.setFdoc_document_type("PC");

						litigationDocumentsDao.saveDocuments(litigationDocuments);
					}
				} else {
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get paid fees details by counsel fees id(primary key)
	@Override
	public List<CounselFeesDetails> getPaidFeesDetailsByConFeesId(int counsel_fees_id) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			List<CounselFeesDetails> sendList = new ArrayList<>();
			List<CounselPaidFees> res = litigationDao.getPaidFeesDetailsByConFeesId(counsel_fees_id);
			for (CounselPaidFees counselPaidFees : res) {
				CounselFeesDetails details = new CounselFeesDetails();
				details.setCpaid_id(counselPaidFees.getCpaid_id());
				details.setCpaid_invoice_no(counselPaidFees.getCpaid_invoice_no());
				details.setCpaid_invoice_amt(counselPaidFees.getCpaid_invoice_amt());
				details.setCpaid_invoice_date(
						sdfOut.format(sdfIn.parse(counselPaidFees.getCpaid_invoice_date().toString())));
				details.setLcou_comments(counselPaidFees.getCpaid_comments());
				details.setCpaid_fees_amount(counselPaidFees.getCpaid_fees_amount());
				details.setCpaid_currency_code(counselPaidFees.getCpaid_currency_code());
				details.setFees_doc(litigationDocumentsDao.getAllDocumentByFeesId(LitigationFeesDocuments.class, counselPaidFees.getCpaid_id(), "PC"));
				details.setLcoun_fees_added_by(counselPaidFees.getCpaid_added_by());
				sendList.add(details);
			}
			return sendList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int savePaidAdvocateFees(AdvocatePaidFees fees, ArrayList<MultipartFile> advocate_paid_doc) {
		try {
			LitigationAdvocateFees advocateFees = litigationDao.getAdvocateFeesDetailsById(fees.getApaid_advocate_fees_id());
			fees.setApaid_advocate_id(advocateFees.getLadv_advocate_id());
			fees.setApaid_liti_id(advocateFees.getLadv_litigation_id());
			fees.setApaid_created_at(utilitieservice.getCurrentDate());
			fees.setApaid_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			litigationDao.persist(fees);
			
			// persist Logs of Litigation
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(fees.getApaid_liti_id());
			logs.setLog_sub_activity_id(fees.getApaid_id());
			logs.setLog_activity("Add");
			logs.setLog_related_to("Litigation");
			logs.setLog_sub_activity("Paid Advocate Fees");
			logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
			litigationDao.persist(logs);
			
			//upload documents
			String originalFileName = null;
			String generatedFileName = null;
			
			for (int i = 0; i < advocate_paid_doc.size(); i++) {
				MultipartFile file = advocate_paid_doc.get(i);
				int lastGeneratedValueForFeesDoc = litigationDocumentsDao
						.getLastGeneratedValueByFeesId(fees.getApaid_id(), "PA");
				if (lastGeneratedValueForFeesDoc >= 0) {
					if (file.getSize() > 0) {
						File dir = new File("D:" + File.separator + project_name + File.separator + "Documents"
								+ File.separator + "LitigationFeesDocuments"+ File.separator +"AdvocateFeesDocuments"+ File.separator +"AdvocateFeesPaidDocuments");
						if (!dir.exists())
							dir.mkdirs();

						lastGeneratedValueForFeesDoc++;
						originalFileName = file.getOriginalFilename();
						generatedFileName = "uploadedLitiFeesDoc_" + fees.getApaid_id() + "_"
								+ lastGeneratedValueForFeesDoc + "." + file.getOriginalFilename().split("\\.")[1];
						File newFile = new File(dir.getPath() + File.separator + generatedFileName);
						if (!newFile.exists()) {
							newFile.createNewFile();
						}

						@SuppressWarnings("resource")
						OutputStream outputStream = new FileOutputStream(newFile);

						outputStream.write(file.getBytes());

						LitigationFeesDocuments litigationDocuments = new LitigationFeesDocuments();

						litigationDocuments.setFdoc_fees_id(fees.getApaid_id());
						litigationDocuments.setFdoc_original_file_name(originalFileName);
						litigationDocuments.setFdoc_generated_file_name(
								"D:/"+project_name+"/Documents/LitigationFeesDocuments/AdvocateFeesDocuments/AdvocateFeesPaidDocuments/" + generatedFileName);
						litigationDocuments.setFdoc_last_generated_value_for_fees_id(lastGeneratedValueForFeesDoc);
						litigationDocuments.setFdoc_document_type("PA");

						litigationDocumentsDao.saveDocuments(litigationDocuments);
					}
				} else {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<AdvocateFeesDetails> getPaidFeesDetailsByAdvocateFeesId(int advocate_fees_id) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			List<AdvocateFeesDetails> sendList = new ArrayList<>();
			List<AdvocatePaidFees> res = litigationDao.getPaidFeesDetailsByAdvocateFeesId(advocate_fees_id);
			for (AdvocatePaidFees AdvocatePaidFees : res) {
				AdvocateFeesDetails details = new AdvocateFeesDetails();
				details.setApaid_id(AdvocatePaidFees.getApaid_id());
				details.setApaid_invoice_no(AdvocatePaidFees.getApaid_invoice_no());
				details.setApaid_invoice_amt(AdvocatePaidFees.getApaid_invoice_amt());
				details.setApaid_invoice_amt_currency(AdvocatePaidFees.getApaid_invoice_amt_currency());
				details.setApaid_invoice_date(
						sdfOut.format(sdfIn.parse(AdvocatePaidFees.getApaid_invoice_date().toString())));
				details.setLadv_comments(AdvocatePaidFees.getApaid_comments());
				details.setApaid_fees_amount(AdvocatePaidFees.getApaid_fees_amount());
				details.setApaid_currency_code(AdvocatePaidFees.getApaid_currency_code());
				details.setFees_doc(litigationDocumentsDao.getAllDocumentByFeesId(LitigationFeesDocuments.class, AdvocatePaidFees.getApaid_id(), "PA"));
				sendList.add(details);
			}
			return sendList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get Advocate paid fees by id(primary key)
	@Override
	public AdvocatePaidFees getAdvocatePaidFeesById(int apaid_id) {
		try {
			return litigationDao.getAdvocatePaidFeesById(apaid_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Update Paid Advocate fees
	@Override
	public void updatePaidAdvocateFees(AdvocatePaidFees advocatePaidFees, ArrayList<MultipartFile> advocate_paid_doc) {
		try {
			AdvocatePaidFees oldAdvocatePaidFees = litigationDao
					.getAdvocatePaidFeesById(advocatePaidFees.getApaid_id());
			advocatePaidFees.setApaid_advocate_fees_id(oldAdvocatePaidFees.getApaid_advocate_fees_id());
			advocatePaidFees.setApaid_liti_id(oldAdvocatePaidFees.getApaid_liti_id());
			advocatePaidFees.setApaid_advocate_id(oldAdvocatePaidFees.getApaid_advocate_id());
			advocatePaidFees.setApaid_added_by(oldAdvocatePaidFees.getApaid_added_by());
			advocatePaidFees.setApaid_created_at(oldAdvocatePaidFees.getApaid_created_at());

			litigationDao.merge(advocatePaidFees);
			
			// persist Logs of Litigation
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(advocatePaidFees.getApaid_liti_id());
			logs.setLog_sub_activity_id(advocatePaidFees.getApaid_id());
			logs.setLog_activity("Update");
			logs.setLog_related_to("Litigation");
			logs.setLog_sub_activity("Paid Advocate Fees");
			logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
			litigationDao.persist(logs);
			
			//upload documents
			String originalFileName = null;
			String generatedFileName = null;
			
			for (int i = 0; i < advocate_paid_doc.size(); i++) {
				MultipartFile file = advocate_paid_doc.get(i);
				int lastGeneratedValueForFeesDoc = litigationDocumentsDao
						.getLastGeneratedValueByFeesId(advocatePaidFees.getApaid_id(), "PA");
				if (lastGeneratedValueForFeesDoc >= 0) {
					if (file.getSize() > 0) {
						File dir = new File("D:" + File.separator + project_name + File.separator + "Documents"
								+ File.separator + "LitigationFeesDocuments"+ File.separator +"AdvocateFeesDocuments"+ File.separator +"AdvocateFeesPaidDocuments");
						if (!dir.exists())
							dir.mkdirs();

						lastGeneratedValueForFeesDoc++;
						originalFileName = file.getOriginalFilename();
						generatedFileName = "uploadedLitiFeesDoc_" + advocatePaidFees.getApaid_id() + "_"
								+ lastGeneratedValueForFeesDoc + "." + file.getOriginalFilename().split("\\.")[1];
						File newFile = new File(dir.getPath() + File.separator + generatedFileName);
						if (!newFile.exists()) {
							newFile.createNewFile();
						}

						@SuppressWarnings("resource")
						OutputStream outputStream = new FileOutputStream(newFile);

						outputStream.write(file.getBytes());

						LitigationFeesDocuments litigationDocuments = new LitigationFeesDocuments();

						litigationDocuments.setFdoc_fees_id(advocatePaidFees.getApaid_id());
						litigationDocuments.setFdoc_original_file_name(originalFileName);
						litigationDocuments.setFdoc_generated_file_name(
								"D:/"+project_name+"/Documents/LitigationFeesDocuments/AdvocateFeesDocuments/AdvocateFeesPaidDocuments/" + generatedFileName);
						litigationDocuments.setFdoc_last_generated_value_for_fees_id(lastGeneratedValueForFeesDoc);
						litigationDocuments.setFdoc_document_type("PA");

						litigationDocumentsDao.saveDocuments(litigationDocuments);
					}
				} else {
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get counsel paid fees by id(primary key)
	@Override
	public CounselPaidFees getCounselPaidFeesById(int cpaid_id) {
		try {
			return litigationDao.getCounselPaidFeesById(cpaid_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Update counsel paid fees
	@Override
	public void updatePaidCounselFees(CounselPaidFees paidFees, ArrayList<MultipartFile> counsel_paid_doc) {
		try {
			CounselPaidFees oldCounselPaidFees = litigationDao.getCounselPaidFeesById(paidFees.getCpaid_id());
			paidFees.setCpaid_counsel_id(oldCounselPaidFees.getCpaid_counsel_id());
			paidFees.setCpaid_liti_id(oldCounselPaidFees.getCpaid_liti_id());
			paidFees.setCpaid_counsel_fees_id(oldCounselPaidFees.getCpaid_counsel_fees_id());
			paidFees.setCpaid_added_by(oldCounselPaidFees.getCpaid_added_by());
			paidFees.setCpaid_created_at(oldCounselPaidFees.getCpaid_created_at());
			litigationDao.merge(paidFees);
			
			// persist Logs of Litigation
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(paidFees.getCpaid_liti_id());
			logs.setLog_sub_activity_id(paidFees.getCpaid_id());
			logs.setLog_activity("Update");
			logs.setLog_related_to("Litigation");
			logs.setLog_sub_activity("Paid Counsel Fees");
			logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
			litigationDao.persist(logs);
						
			//upload documents
			String originalFileName = null;
			String generatedFileName = null;
			
			for (int i = 0; i < counsel_paid_doc.size(); i++) {
				MultipartFile file = counsel_paid_doc.get(i);
				int lastGeneratedValueForFeesDoc = litigationDocumentsDao
						.getLastGeneratedValueByFeesId(paidFees.getCpaid_id(), "PC");
				if (lastGeneratedValueForFeesDoc >= 0) {
					if (file.getSize() > 0) {
						File dir = new File("D:" + File.separator + project_name + File.separator + "Documents"
								+ File.separator + "LitigationFeesDocuments"+ File.separator +"CounselFeesDocuments"+File.separator +"CounselFeesPaidDocuments");
						if (!dir.exists())
							dir.mkdirs();

						lastGeneratedValueForFeesDoc++;
						originalFileName = file.getOriginalFilename();
						generatedFileName = "uploadedLitiFeesDoc_" + paidFees.getCpaid_id() + "_"
								+ lastGeneratedValueForFeesDoc + "." + file.getOriginalFilename().split("\\.")[1];
						File newFile = new File(dir.getPath() + File.separator + generatedFileName);
						if (!newFile.exists()) {
							newFile.createNewFile();
						}

						@SuppressWarnings("resource")
						OutputStream outputStream = new FileOutputStream(newFile);

						outputStream.write(file.getBytes());

						LitigationFeesDocuments litigationDocuments = new LitigationFeesDocuments();

						litigationDocuments.setFdoc_fees_id(paidFees.getCpaid_id());
						litigationDocuments.setFdoc_original_file_name(originalFileName);
						litigationDocuments.setFdoc_generated_file_name(
								"D:/"+project_name+"/Documents/LitigationFeesDocuments/CounselFeesDocuments/CounselFeesPaidDocuments" + generatedFileName);
						litigationDocuments.setFdoc_last_generated_value_for_fees_id(lastGeneratedValueForFeesDoc);
						litigationDocuments.setFdoc_document_type("PC");

						litigationDocumentsDao.saveDocuments(litigationDocuments);
					}
				} else {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//Method created : Tejashri Zurunge
	//Method purpose : download litigation fees documents
	@Override
	public void downloadLitigationFeesDocument(int fdoc_id, HttpServletResponse response) {
		try {
			if (litigationDocumentsDao.getLitigationFeesDocumentFilePath(fdoc_id) != null) {
				File file = new File(litigationDocumentsDao.getLitigationFeesDocumentFilePath(fdoc_id));
				InputStream is = new FileInputStream(file);

				response.setContentType("application/octet-stream");

				response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

				OutputStream os = response.getOutputStream();
				byte[] buffer = new byte[1024];
				int len;
				while ((len = is.read(buffer)) != -1) {
					os.write(buffer, 0, len);
				}
				os.flush();
				os.close();
				is.close();
			} else {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get hearing stage logs (primary key)
	@SuppressWarnings("unchecked")
	@Override
	public String getHearingStageLogs(String json, HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			JSONArray sendList = new JSONArray();
			List<Object> logs = litigationDao.getHearingStageLogs(json);
			Iterator<Object> iterator = logs.iterator();
			while(iterator.hasNext()){
				Object objects[] = (Object[]) iterator.next();
				JSONObject data = new JSONObject();

				data.put("stages", objects[6].toString());
				data.put("hearing_date", sdfOut.format(sdfIn.parse(objects[0].toString())));

				data.put("first_alert", sdfOut.format(sdfIn.parse(objects[1].toString())));
				if (objects[2] != null) {
					data.put("second_alert", sdfOut.format(sdfIn.parse(objects[2].toString())));
				} else {
					data.put("second_alert", "NA");
				}
				if (objects[3] != null) {
					data.put("third_alert", sdfOut.format(sdfIn.parse(objects[3].toString())));
				} else {
					data.put("third_alert", "NA");
				}
				data.put("stage_desc", objects[4].toString());
				data.put("hear_stage_id", objects[5].toString());
				if (!objects[7].equals("0"))
					data.put("resp_person", responsiblePersonName(Integer.parseInt(objects[7].toString())));
				else
					data.put("resp_person", "NA");
				if (!objects[8].equals("0"))
					data.put("counsel_name", objects[9].toString());
				else
					data.put("counsel_name", "NA");

				
				data.put("added_by", objects[10].toString());
				data.put("stage_log_status", objects[11].toString());
				data.put("user_id", session.getAttribute("sess_user_id"));
				data.put("user_role", session.getAttribute("sess_user_role"));
				sendList.add(data);
				
			}
			return sendList.toJSONString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : delete litigation
	@Override
	public int deleteLitigation(int liti_id) {
		try {
			Litigation litigation = litigationDao.getLitigationByLitiId(liti_id);
			int delete_id = litigationDao.deleteLitigation(liti_id);
			
			// persist Logs of Litigation
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(liti_id);
			logs.setLog_sub_activity_id(delete_id);
			logs.setLog_activity("Delete");
			logs.setLog_related_to("Litigation");
			logs.setLog_sub_activity("Delete Litigation");
			logs.setLog_related_name(litigation.getLiti_case_title());
			logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
			litigationDao.persist(logs);
			
			return delete_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : check whether paid amount greater than agreed amount or not
	@Override
	public int isCounselPaidFeesGreaterThanAgreedFees(int cpaid_fees_amount, int cpaid_counsel_fees_id) {
		try {
			int result = litigationDao.isCounselPaidFeesGreaterThanAgreedFees(cpaid_fees_amount, cpaid_counsel_fees_id);
			if(result == 0){
			LitigationCounselFees counselFees = getCounselFeesDetailsById(cpaid_counsel_fees_id);
			Double agreed_amt = counselFees.getLcou_counsel_fees_amount(); 
				if(cpaid_fees_amount <= agreed_amt ){
					return 0;
				}else{
					return 1;
				}
			
			}else{
			return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
		// Method Purpose : check whether paid amount greater than agreed amount or not
	@Override
	public int isAdvocatePaidFeesGreaterThanAgreedFees(int apaid_fees_amount, int apaid_advocate_fees_id) {
		try {
			int result = litigationDao.isAdvocatePaidFeesGreaterThanAgreedFees(apaid_fees_amount, apaid_advocate_fees_id);
			if(result == 0){
			LitigationAdvocateFees advocateFees = getAdvocateFeesDetailsById(apaid_advocate_fees_id);
			Double agreed_amt = advocateFees.getLadv_advocate_fees_amount(); 
				if(apaid_fees_amount <= agreed_amt ){
					return 0;
				}else{
					return 1;
				}
			
			}else{
			return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : delete Hearing stage
	@Override
	public int deleteHearingStage(int hear_stage_id) {
		try {
			HearingStage stage = litigationDao.getHearingDetailsByStageId(hear_stage_id);
			int delete_id = litigationDao.deleteHearingStage(hear_stage_id);

			// persist Logs of Litigation
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(stage.getTtrn_litigation_id());
			logs.setLog_sub_activity_id(hear_stage_id);
			logs.setLog_activity("Delete");
			logs.setLog_related_to("Litigation");
			logs.setLog_related_name(stageService.getStagesById(stage.getTtrn_stage_id()).getStage_name());
			logs.setLog_sub_activity("Delete Hearing Stage");
			logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
			litigationDao.persist(logs);

			return delete_id;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<HearingStageDocuments> getHearingStageDocById(int hear_stage_id) {
		try {
			return litigationDocumentsDao.getHearingDocumentByHearStageId(HearingStageDocuments.class, hear_stage_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteHearingStageDoc(int doc_id) {
		try {
			//String generated_file_name = litigationDocumentsDao.getHearingDocumentFilePath(doc_id);
			//System.out.println("generatedFile :"+generated_file_name);
			File file = new File(litigationDocumentsDao.getHearingDocumentFilePath(doc_id));

			if (file.delete()) {
				//System.out.println(file.getName() + " is deleted!");
			} else {
				//System.out.println("Delete operation is failed.");
			}
			return litigationDocumentsDao.deleteHearingStageDoc(doc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get Counsel fees doc
	@Override
	public List<LitigationFeesDocuments> getLitiFeesDocById(int lcou_id) {
		try {
			return litigationDocumentsDao.getAllDocumentByFeesId(LitigationFeesDocuments.class, lcou_id, "AAC");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get Counsel fees doc
	@Override
	public int deleteLitiFeesDoc(int fdoc_id) {
		try {
			LitigationFeesDocuments litigationFeesDocuments = litigationDocumentsDao.getFeesDocumentById(fdoc_id);
			File file = new File(litigationFeesDocuments.getFdoc_generated_file_name());

			if (file.delete()) {
			//	System.out.println(file.getName() + " is deleted!");
			} else {
				//System.out.println("Delete operation is failed.");
			}
			return litigationDocumentsDao.deleteLitiFeesDocument(fdoc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get Counsel fees doc
	@Override
	public List<LitigationFeesDocuments> getAdvocateFeesDocById(int ladv_id) {
		try {
			return litigationDocumentsDao.getAllDocumentByFeesId(LitigationFeesDocuments.class, ladv_id, "AAA");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<LitigationFeesDocuments> getCounselPaidDocById(int cpaid_id) {
		try {
			return litigationDocumentsDao.getAllDocumentByFeesId(LitigationFeesDocuments.class, cpaid_id, "PC");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<LitigationFeesDocuments> getAdvocatePaidDocById(int apaid_id) {
		try {
			return litigationDocumentsDao.getAllDocumentByFeesId(LitigationFeesDocuments.class, apaid_id, "PA");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String getHearingStageCounselList(int hearing_stage_id) {
		try {
            JSONArray sendList = new JSONArray();			
			List<Object> res = litigationDao.getCounselByHearingStageId(hearing_stage_id);
			if(res!=null){
				Iterator<Object> iterator = res.iterator();
				while(iterator.hasNext()){
					Object[] objects = (Object[]) iterator.next();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("hsco_id", objects[0]);
					jsonObject.put("hsco_law_firm_id",objects[1]);
					jsonObject.put("hsco_law_firm_name",objects[2]);
					jsonObject.put("hsco_counsel_id",objects[3]);
					jsonObject.put("hsco_counsel_name",objects[4]);
					sendList.add(jsonObject);
				}
			}
			
			return sendList.toJSONString();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public int deleteHearingCounselById(String json) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
			int hsco_id = Integer.parseInt(jsonObject.get("hsco_id").toString());
			int res = litigationDao.deleteHearingCounselById(hsco_id);
			/*int res = litigationDao.deleteHearingCounselById(hsco_id);
			JSONObject send = new JSONObject();
			send.put("status",res);
			if(res==1)
			{
			  send.put("msg", "Counsel deleted succesfully.");	
			}else{
			  send.put("msg", "Please try again.");
			}
			return send.toJSONString();*/
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String AllLitiTypeJson() {
		try {
			 JSONArray sendList = new JSONArray();
			 List<LitigationType> res = litigationDao.getAllLitiType(LitigationType.class);
			 //System.out.println("Count "+res.size());
			 Iterator<LitigationType> iterator = res.iterator();
			 while(iterator.hasNext()){
				 LitigationType litigationType = iterator.next();
				 
				 JSONObject jsonObject = new JSONObject();
				 jsonObject.put("label",litigationType.getLiti_type_name());
				 jsonObject.put("value",litigationType.getLiti_type_name());
				 //System.out.println("value "+litigationType.getLiti_type_name());
				 sendList.add(jsonObject);
			 }
			 return sendList.toJSONString();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAllLitigationClientId() {
		try {
			List<Litigation> liti_list = litigationDao.getAllPendingLitigationClientId(Litigation.class);
			 JSONArray liti_ref_no = new JSONArray();
			 Iterator<Litigation> itr = liti_list.iterator();
			 while (itr.hasNext()) {
				Litigation liti = itr.next();
				JSONObject jsonObj = new JSONObject();
				
				jsonObj.put("client_id", liti.getLiti_client_liti_id());
			
				liti_ref_no.add(jsonObj);
			}
			 return liti_ref_no.toJSONString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getpreviouslitiId(String liti_id) {
		try {
			return litigationDao.getpreviouslitiId(liti_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LitigationEscalationMailId getEscalationMailByLitiId(int liti_id) {
		try {
			return litigationDao.getEscalationMailByLitiId(liti_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int saveHearingStageOnHearing(HearingStage hearingStage, ArrayList<MultipartFile> hearing_doc,
			 String status, String hearing_id) {
		try {
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			hearingStage.setTtrn_created_at(utilitieservice.getCurrentDate());
			hearingStage.setTtrn_updated_at(utilitieservice.getCurrentDate());
			//hearingStage.setTtrn_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			hearingStage.setTtrn_stage_status(status);
			int hearing_Stage_Id = litigationDao.persistHearingStageOnHearing(hearingStage);
			
			//Persist status of hearing stage
			HearingStageOnHearing hearingStatus = litigationDao.getHearingOnHearingDetailsById(Integer.parseInt(hearing_id));
			hearingStatus.setTrn_hearing_id(hearingStatus.getTrn_hearing_id());
			hearingStatus.setTrn_newHear_id(hearing_Stage_Id);
			hearingStatus.setTrn_liti_id(hearingStage.getTtrn_litigation_id());
			hearingStatus.setTrn_hearing_status(1);
			
			litigationDao.update(hearingStatus);
			
			//get mail ids of all admin
			ArrayList<String> email = new ArrayList<String>(); 
			List<Object> mail_idCC = schedulerService.getAllAdminUser();
			Iterator<Object> iterator = mail_idCC.iterator();
			while (iterator.hasNext()) {
				Object object[] = (Object[]) iterator.next();
				if(object[1] != null)
				email.add(object[1].toString());
			}
			
			String email_subject = "New Hearing stage is updated on Date of Hearing";
			String email_body= " ";
			email_body += "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
			email_body += "<h2 style='font-size:18px;'>Dear User,</h2>";
			email_body += "<p style='text-align:justify;width:70%;'>The following Hearing stage is been created against litigation and waiting for your approval. You are requested to follow the link by clicking on the 'ID'.</p>"
					+ "<h2 style='font-size:16px;font-weight:bold;'>Hearing stage details :</h2>";
			email_body += "<table style='width:80%;' border='1'>" 
					+ "<thead>"
					+ "<tr style='background:#0B6EC3;color:#fff;'>"
					+ "<th> ID</th>"
					+ "<th>Next hearing date</th>" 
					//+ "<th>Responsible person</th>"
					+ "<th>Stage description</th>" 
					+ "<th>Action item due date</th>" 
					+ "<th>Action item</th>"
					+ "</tr>" + "</thead>" + "<tbody>";
			String litigation_id = "";
			String action_item_due_dt = "";
			String stage_desc =""; String action_item = "";
				litigation_id = String.valueOf(hearingStage.getTtrn_litigation_id());
				String next_hearing_date = sdfOut.format(hearingStage.getTtrn_next_hearing_date());
				if(!hearingStage.getTtrn_stage_description().equals(""))
				stage_desc = hearingStage.getTtrn_stage_description();
				if(!hearingStage.getTtrn_action_item().equals(""))
				action_item = hearingStage.getTtrn_action_item();
				if(!hearingStage.getTtrn_stage_due_date().equals(""))
				action_item_due_dt = sdfOut.format(hearingStage.getTtrn_stage_due_date());
				
				email_body += "<tr>" 
						+ "<td><a href='" + project_url + "litigationDetails?liti_id=" + litigation_id	+ "'>" + litigation_id + "</a></td>" 
						+ "<td>" + next_hearing_date +" </td>" 
						+ "<td>" + stage_desc + "</td>" 
						+ "<td>" + action_item_due_dt + "</td>" 
						+ "<td>" + action_item + "</td>" 
						+ "</tr>";
			
			email_body += "</tbody>" + "</table>";
			email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
					+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
					+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
					+ "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
			//System.out.println(email_body);
			
			// Email send code start here
				InternetAddress[] address = new InternetAddress[email.size()];
					try {
						for(int i=0; i< email.size(); i++){
						address[i] = new InternetAddress(email.get(i));
						}
					} catch (AddressException e) {
						e.printStackTrace();
					}
			
			/*Litigation liti = litigationDao.getLitigationByLitiId(hearingStage.getTtrn_litigation_id());
			
			String liti_Allmail_id = liti.getLiti_mail_id_cc();
			if(liti_Allmail_id != null && (!liti_Allmail_id.equals(""))){
			String[] mail_id = liti_Allmail_id.split(",");
			InternetAddress[] addressCC = new InternetAddress[mail_id.length];
			try {
				if(mail_id.length != 0){
				for(int i = 0; i< mail_id.length ; i++){
					addressCC[i] = new InternetAddress(mail_id[i]);
				}
				}
			} catch (AddressException e) {
				e.printStackTrace();
			}
			sendMailService.sendMail(email_subject, email_body, address, addressCC, litigation_id);
			} */
			sendMailService.sendMail(email_subject, email_body, address, address, litigation_id);
			
			// Upload Documents
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueHearId = litigationDocumentsDao
					.getLastGeneratedValueByHearingStageId(hearing_Stage_Id);
			for (int i = 0; i < hearing_doc.size(); i++) {
				MultipartFile file = hearing_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("D:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "HearingStageDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueHearId++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedHearStageDoc_" + hearing_Stage_Id + "_" + lastGeneratedValueHearId
							+ "." + file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					HearingStageDocuments stage_documents = new HearingStageDocuments();

					stage_documents.setHear_stage_id(hearing_Stage_Id);
					stage_documents.setHear_stage_original_file_name(originalFileName);
					stage_documents.setHear_stage_generated_file_name(
							"D:/"+project_name+"/Documents/HearingStageDocuments/" + generatedFileName);
					stage_documents.setHear_stage_last_generated_value_for_hear_stage_id(lastGeneratedValueHearId);
					stage_documents.setHear_stage_created_at(utilitieservice.getCurrentDate());
					//stage_documents.setHear_stage_added_by(utilitieservice.getCurrentSessionUserId(httpSession));

					litigationDocumentsDao.saveHearingSatgeDocument(stage_documents);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public HearingStageOnHearing getHearingOnHearingDetailsById(int hear_id) {
		try {
			return litigationDao.getHearingOnHearingDetailsById(hear_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
