package lcmt.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lcmt.dao.LegalNoticeDao;
import lcmt.dao.RequestByPOCDao;
import lcmt.domain.ActivityLogs;
import lcmt.domain.ExternalCounsel;
import lcmt.domain.LegalNotice;
import lcmt.domain.LegalNoticeDocuments;
import lcmt.domain.LegalNoticeRequest;
import lcmt.domain.LegalNoticeStatus;
import lcmt.domain.LegalNoticeStatusReference;
import lcmt.domain.LegalNotice_Reference;
import lcmt.domain.LitigationDocuments;
import lcmt.domain.LitigationRequest;
import lcmt.domain.RequestDocument;
import lcmt.service.ExternalCounselService;
import lcmt.service.LegalNoticeService;
import lcmt.service.RequestByPOCService;
import lcmt.service.SendMailService;
import lcmt.service.UserService;
import lcmt.service.UtilitiesService;

/*
 * Author: Harshad Padole
 * Date: 3/09/2016
 * Updated By: 
 * Updated Date: 
 * 
 * */
@Service("legalNoticeService")
public class LegalNoticeServiceImpl implements LegalNoticeService {

	@Autowired
	UtilitiesService utilitiesService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	LegalNoticeDao legalNoticeDao;
	@Autowired
	SendMailService sendMailService;
	@Autowired
	ExternalCounselService externalCounselService;
	@Autowired
	UserService userService;
	@Autowired
	RequestByPOCService requestByPOCService;
	@Autowired
	RequestByPOCDao requestByPOCDao;

	private @Value("#{config['project_name'] ?: 'null'}") String project_name;

	// Method Created : Harshad Padole
	// Method Purpose : Save LegalNotice
	@Override
	public int saveLegalNotice(LegalNotice_Reference legalNotice_Reference, int id, ArrayList<MultipartFile> legal_doc,
			HttpSession session, String status) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			LegalNotice legalNotice = new LegalNotice();
			legalNotice.setLega_noti_entity_id(legalNotice_Reference.getLega_noti_entity_id());
			legalNotice.setLega_noti_unit_id(legalNotice_Reference.getLega_noti_unit_id());
			legalNotice.setLega_noti_function_id(legalNotice_Reference.getLega_noti_function_id());
			legalNotice.setLega_noti_by_against(legalNotice_Reference.getLega_noti_by_against());
			legalNotice.setLega_noti_opposite_party(legalNotice_Reference.getLega_noti_opposite_party());
			legalNotice.setLega_noti_category_id(legalNotice_Reference.getLega_noti_category_id());
			legalNotice.setLega_noti_reference_no(legalNotice_Reference.getLega_noti_reference_no());
			legalNotice.setLega_noti_addressed_to(legalNotice_Reference.getLega_noti_addressed_to());

			if (!legalNotice_Reference.getLega_noti_dated().toString().equals("")) {
				legalNotice.setLega_noti_dated(dateFormat.parse(legalNotice_Reference.getLega_noti_dated()));
			}
			if (!legalNotice_Reference.getLega_noti_recived_on().toString().equals("")) {
				legalNotice.setLega_noti_recived_on(dateFormat.parse(legalNotice_Reference.getLega_noti_recived_on()));
			}
			if (!legalNotice_Reference.getLega_noti_reply_deadline().toString().equals("")) {
				legalNotice.setLega_noti_reply_deadline(
						dateFormat.parse(legalNotice_Reference.getLega_noti_reply_deadline()));
			}
			if (!legalNotice_Reference.getLega_noti_reminder_date().toString().equals("")) {
				legalNotice.setLega_noti_reminder_date(
						dateFormat.parse(legalNotice_Reference.getLega_noti_reminder_date()));
			}

			if (status.equals("Pending"))
				legalNotice.setLega_noti_assigned_to_id(legalNotice_Reference.getLega_noti_assigned_to_id());
			else
				legalNotice.setLega_noti_assigned_to_id(0);

			if (status.equals("Pending"))
				legalNotice.setLega_noti_secondary_responsible_person(
						legalNotice_Reference.getLega_noti_secondary_responsible_person());
			else
				legalNotice.setLega_noti_secondary_responsible_person(0);

			if (status.equals("Pending"))
				legalNotice.setLega_noti_third_responsible_person(
						legalNotice_Reference.getLega_noti_third_responsible_person());
			else
				legalNotice.setLega_noti_third_responsible_person(0);
			
			if (status.equals("Pending"))
				legalNotice.setLega_noti_other_responsible_person(
						legalNotice_Reference.getLega_noti_other_responsible_person());
			else
				legalNotice.setLega_noti_other_responsible_person(0);

			legalNotice.setLega_noti_intern_cont_person(legalNotice_Reference.getLega_noti_intern_cont_person());
			legalNotice.setLega_noti_external_counsel_id(legalNotice_Reference.getLega_noti_external_counsel_id());
			legalNotice
					.setLega_noti_opposite_party_advocate(legalNotice_Reference.getLega_noti_opposite_party_advocate());
			legalNotice.setLega_noti_relevant_law(legalNotice_Reference.getLega_noti_relevant_law());
			legalNotice.setLega_noti_comments(legalNotice_Reference.getLega_noti_comments());
			legalNotice.setLega_noti_prayer_details(legalNotice_Reference.getLega_noti_prayer_details());
	        legalNotice.setLega_noti_amount_involved(legalNotice_Reference.getLega_noti_amount_involved());
	        legalNotice.setLega_noti_interest(legalNotice_Reference.getLega_noti_interest());
	        legalNotice.setLega_noti_total_amount(legalNotice_Reference.getLega_noti_total_amount());
			legalNotice.setLega_noti_involved_amt_currency(legalNotice_Reference.getLega_noti_involved_amt_currency());
			legalNotice.setLega_noti_conversion_rate(legalNotice_Reference.getLega_noti_conversion_rate());
			legalNotice.setLega_noti_converted_amt(legalNotice_Reference.getLega_noti_converted_amt());
			legalNotice
					.setLega_noti_converted_amt_currency(legalNotice_Reference.getLega_noti_converted_amt_currency());
			legalNotice.setLega_noti_created_at(utilitiesService.getCurrentDate());
			legalNotice.setLega_noti_updated_at(utilitiesService.getCurrentDate());
			legalNotice.setLega_noti_status(status);
			legalNotice.setLega_noti_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
			
			// legalNotice.setLega_noti_secondary_responsible_person(legalNotice_Reference.getLega_noti_secondary_responsible_person());
			int lega_notice_id = legalNoticeDao.persist(legalNotice);

			if (id > 0) {
				LegalNoticeRequest notiReq = requestByPOCService.getAllNoticerequest(id, session);
				notiReq.setReq_noti_approval_status(2);
				legalNoticeDao.update(notiReq);
				// upload litigation document into litigation
				List<RequestDocument> noti_doc = requestByPOCDao.getAllRequestDocument(id, "NotiRequest");
				if (noti_doc != null) {
					Iterator<RequestDocument> itr = noti_doc.iterator();
					// MultipartFile teDoc;
					while (itr.hasNext()) {
						RequestDocument requestDocument = (RequestDocument) itr.next();
						LegalNoticeDocuments documents = new LegalNoticeDocuments();

						documents.setLega_notice_id(lega_notice_id);
						documents.setLega_doc_original_file_name(requestDocument.getReq_doc_original_file_name());
						documents.setLega_doc_generated_file_name(requestDocument.getReq_doc_generated_file_name());
						documents.setLega_doc_last_generated_value_for_notice_id(
								requestDocument.getReq_doc_last_generated_value_for_req_id());
						// documents.set(utilitieservice.getCurrentDateWithTime());
						// documents.setCdoc_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
						legalNoticeDao.saveLegalDocuments(documents);
					}
				}
			}

			if (status.equals("Pending")) {
				sendMailService.sendLegalNoticeMailToAssignPerson(lega_notice_id, session);

				// persist legal notice logs
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(lega_notice_id);
				logs.setLog_activity("Add");
				logs.setLog_assinged_to_id(legalNotice_Reference.getLega_noti_assigned_to_id());
				logs.setLog_secondary_responsible_person(
						legalNotice_Reference.getLega_noti_secondary_responsible_person());
				logs.setLog_third_responsible_person(legalNotice_Reference.getLega_noti_third_responsible_person());
				logs.setLog_other_responsible_person(legalNotice_Reference.getLega_noti_other_responsible_person());
				logs.setLog_related_to("Legal Notice");
				logs.setLog_sub_activity("Add Legal Notice");
				logs.setLog_related_name(legalNotice_Reference.getLega_noti_reference_no());
				logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
				logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
				legalNoticeDao.saveLogs(logs);
			}

			/*
			 * if(status.equals("Draft")){ // persist legal notice logs ActivityLogs logs =
			 * new ActivityLogs(); logs.setLog_activity_id(lega_notice_id);
			 * logs.setLog_activity("Add");
			 * logs.setLog_assinged_to_id(legalNotice_Reference.getLega_noti_assigned_to_id(
			 * )); logs.setLog_related_to("Legal Notice");
			 * logs.setLog_sub_activity("Add Draft Legal Notice");
			 * logs.setLog_related_name(legalNotice_Reference.getLega_noti_reference_no());
			 * logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
			 * logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
			 * legalNoticeDao.saveLogs(logs); }
			 */
			// Upload Documents
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueDoc = legalNoticeDao.getLastGenerateValueForLegalNotice(lega_notice_id, 1);
			for (int i = 0; i < legal_doc.size(); i++) {
				MultipartFile file = legal_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents"
							+ File.separator + "LegalNoticeDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedLegaNotiDoc_" + lega_notice_id + "_" + lastGeneratedValueDoc + "."
							+ file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					LegalNoticeDocuments lega_noti_documents = new LegalNoticeDocuments();
					lega_noti_documents.setLega_notice_id(lega_notice_id);
					lega_noti_documents.setLega_doc_original_file_name(originalFileName);
					lega_noti_documents.setLega_doc_generated_file_name(
							"C:/" + project_name + "/Documents/LegalNoticeDocuments/" + generatedFileName);
					lega_noti_documents.setLega_doc_last_generated_value_for_notice_id(lastGeneratedValueDoc);
					lega_noti_documents.setLega_doc_created_date(utilitiesService.getCurrentDate());
					lega_noti_documents.setLega_doc_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
					lega_noti_documents.setLega_doc_related_to(1);

					legalNoticeDao.saveLegalDocuments(lega_noti_documents);
				}
			}
			return lega_notice_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method Purpose : get List of LegalNotice
	@Override
	public List<LegalNotice_Reference> getAllLegalNotice(HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			List<LegalNotice_Reference> sendList = new ArrayList<>();

			List<Object> list = legalNoticeDao.getAllLegalNotice(session);
			Iterator<Object> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				LegalNotice_Reference legal_notice = new LegalNotice_Reference();

				legal_notice.setLega_noti_entity_name(objects[0].toString());
				legal_notice.setLega_noti_unit_name(objects[1].toString());
				legal_notice.setLega_noti_function_name(objects[2].toString());
				legal_notice.setLega_noti_category_name(objects[3].toString());
				legal_notice.setLega_noti_id(Integer.parseInt(objects[4].toString()));
				if (objects[5] != null) {
					legal_notice.setLega_noti_dated(sdfOut.format(sdfIn.parse(objects[5].toString())));
				} else {
					legal_notice.setLega_noti_dated("NA");
				}
				if (objects[6] != null) {
					legal_notice.setLega_noti_recived_on(sdfOut.format(sdfIn.parse(objects[6].toString())));
				} else {
					legal_notice.setLega_noti_recived_on("NA");
				}

				if (objects[7] != null) {
					legal_notice.setLega_noti_reply_deadline(sdfOut.format(sdfIn.parse(objects[7].toString())));
				} else {
					legal_notice.setLega_noti_reply_deadline("NA");
				}
				if (objects[8] != null) {
					legal_notice.setLega_noti_assigned_to_name(objects[8].toString() + " " + objects[9].toString());
				} else {
					legal_notice.setLega_noti_assigned_to_name("NA");
				}

				if (objects[10] != null) {
					legal_notice.setLega_noti_reminder_date(sdfOut.format(sdfIn.parse(objects[10].toString())));
				} else {
					legal_notice.setLega_noti_reminder_date("NA");
				}
				legal_notice.setLega_noti_added_by(Integer.parseInt(objects[11].toString()));
				legal_notice.setLega_noti_status(objects[12].toString());
				if (objects[13] != null) {
					legal_notice.setLega_noti_secondary_responsible_person_name(objects[13].toString() + " " + objects[9].toString());
				} else {
					legal_notice.setLega_noti_secondary_responsible_person_name("NA");
				}
				
				sendList.add(legal_notice);
			}
			return sendList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get Legal notice by id
	@Override
	public LegalNotice_Reference getLegalNoticeById(int lega_noti_id) {
		try {

			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

			LegalNotice result = legalNoticeDao.getLegalNoticeById(lega_noti_id);

			LegalNotice_Reference sendDate = new LegalNotice_Reference();

			sendDate.setLega_noti_id(lega_noti_id);
			sendDate.setLega_noti_entity_id(result.getLega_noti_entity_id());
			sendDate.setLega_noti_unit_id(result.getLega_noti_unit_id());
			sendDate.setLega_noti_function_id(result.getLega_noti_function_id());
			sendDate.setLega_noti_by_against(result.getLega_noti_by_against());
			sendDate.setLega_noti_opposite_party(result.getLega_noti_opposite_party());
			sendDate.setLega_noti_category_id(result.getLega_noti_category_id());
			sendDate.setLega_noti_amount_involved(result.getLega_noti_amount_involved());
			sendDate.setLega_noti_interest(result.getLega_noti_interest());
			sendDate.setLega_noti_total_amount(result.getLega_noti_total_amount());
			sendDate.setLega_noti_reference_no(result.getLega_noti_reference_no());
			sendDate.setLega_noti_addressed_to(result.getLega_noti_addressed_to());
			sendDate.setLega_noti_assigned_to_id(result.getLega_noti_assigned_to_id());
			sendDate.setLega_noti_secondary_responsible_person(result.getLega_noti_secondary_responsible_person());
			sendDate.setLega_noti_third_responsible_person(result.getLega_noti_third_responsible_person());
			sendDate.setLega_noti_other_responsible_person(result.getLega_noti_other_responsible_person());
			sendDate.setLega_noti_intern_cont_person(result.getLega_noti_intern_cont_person());
			if (result.getLega_noti_dated() != null) {
				Date lega_noti_dated = sdfIn.parse(result.getLega_noti_dated().toString());
				sendDate.setLega_noti_dated(sdfOut.format(lega_noti_dated));
			} else {
				// sendDate.setLega_noti_dated("NA");
			}
			if (result.getLega_noti_recived_on() != null) {
				Date lega_noti_recived_on = sdfIn.parse(result.getLega_noti_recived_on().toString());
				sendDate.setLega_noti_recived_on(sdfOut.format(lega_noti_recived_on));
			} else {
				// sendDate.setLega_noti_recived_on("NA");
			}

			if (result.getLega_noti_reply_deadline() != null) {
				Date lega_noti_reply_deadline = sdfIn.parse(result.getLega_noti_reply_deadline().toString());
				sendDate.setLega_noti_reply_deadline(sdfOut.format(lega_noti_reply_deadline));
			} else {
				// sendDate.setLega_noti_reply_deadline("NA");
			}

			if (result.getLega_noti_reminder_date() != null) {
				Date lega_noti_reminder_date = sdfIn.parse(result.getLega_noti_reminder_date().toString());
				sendDate.setLega_noti_reminder_date(sdfOut.format(lega_noti_reminder_date));
			} else {
				// sendDate.setLega_noti_reminder_date("NA");
			}
			sendDate.setLega_noti_external_counsel_id(result.getLega_noti_external_counsel_id());
			sendDate.setLega_noti_opposite_party_advocate(result.getLega_noti_opposite_party_advocate());
			sendDate.setLega_noti_relevant_law(result.getLega_noti_relevant_law());
			sendDate.setLega_noti_comments(result.getLega_noti_comments());
			sendDate.setLega_noti_prayer_details(result.getLega_noti_prayer_details());
			sendDate.setLega_noti_involved_amt_currency(result.getLega_noti_involved_amt_currency());
			sendDate.setLega_noti_conversion_rate(result.getLega_noti_conversion_rate());
			sendDate.setLega_noti_converted_amt(result.getLega_noti_converted_amt());
			sendDate.setLega_noti_converted_amt_currency(result.getLega_noti_converted_amt_currency());
			sendDate.setLega_noti_added_by(result.getLega_noti_added_by());
			sendDate.setLega_noti_created_at(sdfOut.format(result.getLega_noti_created_at()));
			sendDate.setLega_noti_updated_at(sdfOut.format(result.getLega_noti_updated_at()));
			sendDate.setLega_noti_status(result.getLega_noti_status());

			return sendDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Update legal notice
	@Override
	public void updateLegalNotice(LegalNotice_Reference legalNotice_Reference, ArrayList<MultipartFile> legal_doc,
			String status, HttpSession session) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			LegalNotice oldData = legalNoticeDao.getLegalNoticeById(legalNotice_Reference.getLega_noti_id());

			LegalNotice legalNotice = new LegalNotice();
			legalNotice.setLega_noti_id(legalNotice_Reference.getLega_noti_id());
			legalNotice.setLega_noti_entity_id(legalNotice_Reference.getLega_noti_entity_id());
			legalNotice.setLega_noti_unit_id(legalNotice_Reference.getLega_noti_unit_id());
			legalNotice.setLega_noti_function_id(legalNotice_Reference.getLega_noti_function_id());
			legalNotice.setLega_noti_by_against(legalNotice_Reference.getLega_noti_by_against());
			legalNotice.setLega_noti_opposite_party(legalNotice_Reference.getLega_noti_opposite_party());
			legalNotice.setLega_noti_category_id(legalNotice_Reference.getLega_noti_category_id());
			legalNotice.setLega_noti_reference_no(legalNotice_Reference.getLega_noti_reference_no());
			legalNotice.setLega_noti_addressed_to(legalNotice_Reference.getLega_noti_addressed_to());
			if (!legalNotice_Reference.getLega_noti_dated().toString().equals("")) {
				legalNotice.setLega_noti_dated(dateFormat.parse(legalNotice_Reference.getLega_noti_dated()));
			}
			if (!legalNotice_Reference.getLega_noti_recived_on().toString().equals("")) {
				legalNotice.setLega_noti_recived_on(dateFormat.parse(legalNotice_Reference.getLega_noti_recived_on()));
			}
			if (!legalNotice_Reference.getLega_noti_reply_deadline().toString().equals("")) {
				legalNotice.setLega_noti_reply_deadline(
						dateFormat.parse(legalNotice_Reference.getLega_noti_reply_deadline()));
			}
			if (!legalNotice_Reference.getLega_noti_reminder_date().toString().equals("")) {
				legalNotice.setLega_noti_reminder_date(
						dateFormat.parse(legalNotice_Reference.getLega_noti_reminder_date()));
			}

			if (status.equals("Pending"))
				legalNotice.setLega_noti_assigned_to_id(legalNotice_Reference.getLega_noti_assigned_to_id());
			else
				legalNotice.setLega_noti_assigned_to_id(0);

			if (status.equals("Pending"))
				legalNotice.setLega_noti_secondary_responsible_person(
						legalNotice_Reference.getLega_noti_secondary_responsible_person());
			else
				legalNotice.setLega_noti_secondary_responsible_person(0);

			if (status.equals("Pending"))
				legalNotice.setLega_noti_third_responsible_person(
						legalNotice_Reference.getLega_noti_third_responsible_person());
			else
				legalNotice.setLega_noti_third_responsible_person(0);
			
			if (status.equals("Pending"))
				legalNotice.setLega_noti_other_responsible_person(
						legalNotice_Reference.getLega_noti_other_responsible_person());
			else
				legalNotice.setLega_noti_other_responsible_person(0);
			
			

			// legalNotice.setLega_noti_assigned_to_id(legalNotice_Reference.getLega_noti_assigned_to_id());
			legalNotice.setLega_noti_intern_cont_person(legalNotice_Reference.getLega_noti_intern_cont_person());
			legalNotice.setLega_noti_external_counsel_id(legalNotice_Reference.getLega_noti_external_counsel_id());
			legalNotice
					.setLega_noti_opposite_party_advocate(legalNotice_Reference.getLega_noti_opposite_party_advocate());
			legalNotice.setLega_noti_relevant_law(legalNotice_Reference.getLega_noti_relevant_law());
			legalNotice.setLega_noti_comments(legalNotice_Reference.getLega_noti_comments());
			legalNotice.setLega_noti_prayer_details(legalNotice_Reference.getLega_noti_prayer_details());

			legalNotice.setLega_noti_amount_involved(legalNotice_Reference.getLega_noti_amount_involved());
			legalNotice.setLega_noti_interest(legalNotice_Reference.getLega_noti_interest());
			legalNotice.setLega_noti_total_amount(legalNotice_Reference.getLega_noti_total_amount());
			legalNotice.setLega_noti_involved_amt_currency(legalNotice_Reference.getLega_noti_involved_amt_currency());
			legalNotice.setLega_noti_conversion_rate(legalNotice_Reference.getLega_noti_conversion_rate());
			legalNotice.setLega_noti_converted_amt(legalNotice_Reference.getLega_noti_converted_amt());
			legalNotice
					.setLega_noti_converted_amt_currency(legalNotice_Reference.getLega_noti_converted_amt_currency());

			legalNotice.setLega_noti_created_at(oldData.getLega_noti_created_at());
			legalNotice.setLega_noti_updated_at(utilitiesService.getCurrentDate());
			if (status.equals("Pending"))
				legalNotice.setLega_noti_status("Pending");
			else
				legalNotice.setLega_noti_status(oldData.getLega_noti_status());
			legalNotice.setLega_noti_added_by(oldData.getLega_noti_added_by());
			legalNoticeDao.updateLegalNotice(legalNotice);
			if (status.equals("Pending") && oldData.getLega_noti_status().toString().equals("Draft")) {
				sendMailService.sendLegalNoticeMailToAssignPerson(oldData.getLega_noti_id(), session);
				// persist legal notice logs
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(oldData.getLega_noti_id());
				logs.setLog_activity("Add");
				logs.setLog_assinged_to_id(legalNotice_Reference.getLega_noti_assigned_to_id());
				logs.setLog_secondary_responsible_person(
						legalNotice_Reference.getLega_noti_secondary_responsible_person());
				logs.setLog_third_responsible_person(legalNotice_Reference.getLega_noti_third_responsible_person());
				logs.setLog_other_responsible_person(legalNotice_Reference.getLega_noti_other_responsible_person());
				logs.setLog_related_to("Legal Notice");
				logs.setLog_sub_activity("Add Legal Notice");
				logs.setLog_related_name(legalNotice_Reference.getLega_noti_reference_no());
				logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
				logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
				legalNoticeDao.saveLogs(logs);
			}
			
			/*
			 * if(status.equals("Draft")){ // persist legal notice logs ActivityLogs logs =
			 * new ActivityLogs(); logs.setLog_activity_id(oldData.getLega_noti_id());
			 * logs.setLog_activity("Update");
			 * logs.setLog_assinged_to_id(legalNotice_Reference.getLega_noti_assigned_to_id(
			 * )); logs.setLog_related_to("Legal Notice");
			 * logs.setLog_sub_activity("Update Draft Legal Notice");
			 * logs.setLog_related_name(legalNotice_Reference.getLega_noti_reference_no());
			 * logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
			 * logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
			 * legalNoticeDao.saveLogs(logs); }
			 */

			if (oldData.getLega_noti_status().toString().equals("Pending")) {
				// persist legal notice logs
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(legalNotice_Reference.getLega_noti_id());
				logs.setLog_activity("Update");
				logs.setLog_assinged_to_id(legalNotice_Reference.getLega_noti_assigned_to_id());
				logs.setLog_secondary_responsible_person(
						legalNotice_Reference.getLega_noti_secondary_responsible_person());
				logs.setLog_third_responsible_person(legalNotice_Reference.getLega_noti_third_responsible_person());
				logs.setLog_other_responsible_person(legalNotice_Reference.getLega_noti_other_responsible_person());
				logs.setLog_related_to("Legal Notice");
				logs.setLog_sub_activity("Update Legal Notice");
				logs.setLog_related_name(legalNotice_Reference.getLega_noti_reference_no());
				logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
				logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
				legalNoticeDao.saveLogs(logs);
			}
			// Upload Documents
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueDoc = legalNoticeDao
					.getLastGenerateValueForLegalNotice(legalNotice_Reference.getLega_noti_id(), 1);

			for (int i = 0; i < legal_doc.size(); i++) {
				MultipartFile file = legal_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents"
							+ File.separator + "LegalNoticeDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedLegaNotiDoc_" + legalNotice_Reference.getLega_noti_id() + "_"
							+ lastGeneratedValueDoc + "." + file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					LegalNoticeDocuments lega_noti_documents = new LegalNoticeDocuments();
					lega_noti_documents.setLega_notice_id(legalNotice_Reference.getLega_noti_id());
					lega_noti_documents.setLega_doc_original_file_name(originalFileName);
					lega_noti_documents.setLega_doc_generated_file_name(
							"C:/" + project_name + "/Documents/LegalNoticeDocuments/" + generatedFileName);
					lega_noti_documents.setLega_doc_last_generated_value_for_notice_id(lastGeneratedValueDoc);
					lega_noti_documents.setLega_doc_created_date(utilitiesService.getCurrentDate());
					lega_noti_documents.setLega_doc_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
					lega_noti_documents.setLega_doc_related_to(1);
					legalNoticeDao.saveLegalDocuments(lega_noti_documents);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Search legal notice
	// Method Updated : Harshad Padole
	@SuppressWarnings("unchecked")
	@Override
	public String searchLegalNotice(String jsonString, HttpSession session) {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);

			int lega_noti_entity_id = Integer.parseInt(jsonobj.get("lega_noti_entity_id").toString());
			int lega_noti_unit_id = Integer.parseInt(jsonobj.get("lega_noti_unit_id").toString());
			int lega_noti_function_id = Integer.parseInt(jsonobj.get("lega_noti_function_id").toString());
			String lega_noti_by_against = jsonobj.get("lega_noti_by_against").toString();
			int lega_noti_category_id = Integer.parseInt(jsonobj.get("lega_noti_category_id").toString());
			int lega_noti_assigned_to_id = Integer.parseInt(jsonobj.get("lega_noti_assigned_id").toString());

			String lega_noti_from_date = jsonobj.get("lega_noti_from_date").toString();
			String lega_noti_to_date = jsonobj.get("lega_noti_to_date").toString();

			JSONArray sendList = new JSONArray();
			SimpleDateFormat sdfIn1 = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdfOut1 = new SimpleDateFormat("yyyy-MM-dd");
			String notice_from_date = "";
			String notice_to_date = "";
			if (!lega_noti_from_date.equals("") && !lega_noti_to_date.equals("")) {

				notice_from_date = sdfOut1.format(sdfIn1.parse(lega_noti_from_date));
				notice_to_date = sdfOut1.format(sdfIn1.parse(lega_noti_to_date));
			}

			List<Object> result = legalNoticeDao.searchLegalNotice(lega_noti_entity_id, lega_noti_by_against,
					lega_noti_function_id, lega_noti_unit_id, lega_noti_category_id, lega_noti_assigned_to_id,
					notice_from_date, notice_to_date, session);

			Iterator<Object> iterator = result.iterator();

			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				JSONObject search = new JSONObject();

				search.put("lega_noti_id", objects[4].toString());
				search.put("lega_noti_entity_name", objects[0]);
				search.put("lega_noti_unit_name", objects[1]);
				search.put("lega_noti_function_name", objects[2]);
				search.put("lega_noti_category_name", objects[3]);

				if (objects[5] != null)
					search.put("lega_noti_dated", sdfIn1.format(sdfOut1.parse(objects[5].toString())));
				else
					search.put("lega_noti_dated", "NA");

				if (objects[6] != null)
					search.put("lega_noti_recived_on", sdfIn1.format(sdfOut1.parse(objects[6].toString())));
				else
					search.put("lega_noti_recived_on", "NA");

				if (objects[7] != null)
					search.put("lega_noti_reply_deadline", sdfIn1.format(sdfOut1.parse(objects[7].toString())));
				else
					search.put("lega_noti_reply_deadline", "NA");

				if (objects[8] != null)
					search.put("lega_noti_assigned_to_name", objects[8].toString() + " " + objects[9].toString());
				else
					search.put("lega_noti_assigned_to_name", "NA");

				if (objects[10] != null)
					search.put("lega_noti_reminder_date", sdfIn1.format(sdfOut1.parse(objects[10].toString())));
				else
					search.put("lega_noti_reminder_date", "NA");
				
			
				

				search.put("lega_added_by", objects[11]);
				search.put("lega_noti_status", objects[12]);
				search.put("lega_user_id", session.getAttribute("sess_user_id"));
				search.put("lega_user_role", session.getAttribute("sess_user_role"));
				sendList.add(search);
			}
			return sendList.toJSONString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get details of legal notice
	@Override
	public LegalNotice_Reference getJoinedDetailsLegalNotice(int lega_noti_id) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			Object[] object = legalNoticeDao.getJoinedDetailsLegalNotice(lega_noti_id);
			LegalNotice_Reference legalNotice_Reference = new LegalNotice_Reference();

			legalNotice_Reference.setLega_noti_id(lega_noti_id);
			legalNotice_Reference.setLega_noti_entity_name(object[0].toString());
			legalNotice_Reference.setLega_noti_unit_name(object[1].toString());
			legalNotice_Reference.setLega_noti_function_name(object[2].toString());
			legalNotice_Reference.setLega_noti_category_name(object[3].toString());
			if (object[5] != null)
				legalNotice_Reference.setLega_noti_dated(sdfOut.format(sdfIn.parse(object[5].toString())));
			else
				legalNotice_Reference.setLega_noti_dated("NA");

			if (object[6] != null)
				legalNotice_Reference.setLega_noti_recived_on(sdfOut.format(sdfIn.parse(object[6].toString())));
			else
				legalNotice_Reference.setLega_noti_recived_on("NA");

			if (object[7] != null)
				legalNotice_Reference.setLega_noti_reply_deadline(sdfOut.format(sdfIn.parse(object[7].toString())));
			else
				legalNotice_Reference.setLega_noti_reply_deadline("NA");
			if (object[8] != null)
				legalNotice_Reference.setLega_noti_assigned_to_name(object[8].toString() + " " + object[9].toString());
			else
				legalNotice_Reference.setLega_noti_assigned_to_name("NA");

			if (object[10] != null)
				legalNotice_Reference.setLega_noti_reminder_date(sdfOut.format(sdfIn.parse(object[10].toString())));
			else
				legalNotice_Reference.setLega_noti_reminder_date("NA");


			legalNotice_Reference.setLega_noti_by_against(object[11].toString());
			legalNotice_Reference.setLega_noti_opposite_party(object[12].toString());
			legalNotice_Reference.setLega_noti_amount_involved(Double.parseDouble(object[13].toString()));
			legalNotice_Reference.setLega_noti_reference_no(object[14].toString());
			
			legalNotice_Reference.setLega_noti_addressed_to(object[15].toString());
			if (Integer.parseInt(object[16].toString()) != 0) {
				ExternalCounsel exe_name = externalCounselService
						.getExte_CounById(Integer.parseInt(object[16].toString()));
				legalNotice_Reference.setLega_noti_external_counsel_name(exe_name.getExte_coun_name());
			} else {
				legalNotice_Reference.setLega_noti_external_counsel_name("NA");
			}
			legalNotice_Reference.setLega_noti_opposite_party_advocate(object[17].toString());
			legalNotice_Reference.setLega_noti_relevant_law(object[18].toString());
			legalNotice_Reference.setLega_noti_comments(object[19].toString());
			
			
			if (object[24] != null)
			legalNotice_Reference.setLega_noti_comments(object[24].toString());
			else
				legalNotice_Reference.setLega_noti_comments("NA");
				
			legalNotice_Reference.setLega_noti_intern_cont_person(object[20].toString());
			// legalNotice_Reference.setLega_noti_involved_amt_currency(object[21].toString());
			legalNotice_Reference.setLega_noti_status(object[22].toString());
			if (object[23] != null)
				legalNotice_Reference.setLega_noti_liti_ref_id(object[23].toString());
			else
				legalNotice_Reference.setLega_noti_liti_ref_id("NA");
			
			if (object[25] != null)
				legalNotice_Reference.setLega_noti_secondary_responsible_person_name(userService.getUserFullNameById(Integer.parseInt(object[25].toString())));
			else
				legalNotice_Reference.setLega_noti_secondary_responsible_person_name("NA");
			
			if (object[26] != null)
				legalNotice_Reference.setLega_noti_third_responsible_person_name(userService.getUserFullNameById(Integer.parseInt(object[26].toString())));
			else
				legalNotice_Reference.setLega_noti_third_responsible_person_name("NA");
			
			if (object[27] != null)
				legalNotice_Reference.setLega_noti_other_responsible_person_name(userService.getUserFullNameById(Integer.parseInt(object[27].toString())));
			else
				legalNotice_Reference.setLega_noti_other_responsible_person_name("NA");
			
			legalNotice_Reference.setLega_noti_interest(Double.parseDouble(object[28].toString()));
			legalNotice_Reference.setLega_noti_total_amount(Double.parseDouble(object[29].toString()));
			
			return legalNotice_Reference;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : list all documents of legal notice
	@Override
	public List<LegalNoticeDocuments> getAllLegalNoticeDocById(int lega_noti_id, int related_to) {
		try {
			return legalNoticeDao.getAllLegalNoticeDocById(lega_noti_id, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : download legal notice doc
	@Override
	public void downloadLegalNoticeDoc(int lega_doc_id, HttpServletResponse response) {
		try {
			if (legalNoticeDao.getLegalNoticeDocFilePath(lega_doc_id) != null) {
				File file = new File(legalNoticeDao.getLegalNoticeDocFilePath(lega_doc_id));
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
	// Method Purpose : Save status details
	@Override
	public int savelegalNoticeStatus(LegalNoticeStatus legalNoticeStatus, ArrayList<MultipartFile> lega_status_doc,
			HttpSession session, String status) {
		try {
			legalNoticeStatus.setLega_created_at(utilitiesService.getCurrentDate());
			legalNoticeStatus
					.setLega_status_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			legalNoticeStatus.setLega_notice_status(status);
			if (status.equals("Draft")) {
				legalNoticeStatus.setLega_person_responsible(0);
			}
			// legalNoticeDao.saveLegalNoticeStatus(legalNoticeStatus);
			int lega_status_id = legalNoticeDao.saveLegalNoticeStatus(legalNoticeStatus);

			if (!status.equals("Draft")) {
				// persist legal notice logs
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(legalNoticeStatus.getLega_notice_id());
				logs.setLog_sub_activity_id(legalNoticeStatus.getLega_status_id());
				logs.setLog_activity("Add");
				logs.setLog_assinged_to_id(legalNoticeStatus.getLega_person_responsible());
				logs.setLog_related_to("Legal Notice");
				logs.setLog_sub_activity("Legal Notice Status");
				logs.setLog_related_name(legalNoticeStatus.getLega_action_taken());
				logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
				logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
				legalNoticeDao.saveLogs(logs);
			}
			// Upload Documents
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueDoc = legalNoticeDao.getLastGenerateValueForLegalNotice(lega_status_id, 2);
			for (int i = 0; i < lega_status_doc.size(); i++) {
				MultipartFile file = lega_status_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents"
							+ File.separator + "LegalNoticeStatusDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedLegaNotiStatusDoc_" + lega_status_id + "_" + lastGeneratedValueDoc
							+ "." + file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					LegalNoticeDocuments lega_noti_documents = new LegalNoticeDocuments();
					lega_noti_documents.setLega_notice_id(lega_status_id);
					lega_noti_documents.setLega_doc_original_file_name(originalFileName);
					lega_noti_documents.setLega_doc_generated_file_name(
							"C:/" + project_name + "/Documents/LegalNoticeStatusDocuments/" + generatedFileName);
					lega_noti_documents.setLega_doc_last_generated_value_for_notice_id(lastGeneratedValueDoc);
					lega_noti_documents.setLega_doc_created_date(utilitiesService.getCurrentDate());
					lega_noti_documents.setLega_doc_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
					lega_noti_documents.setLega_doc_related_to(2);

					legalNoticeDao.saveLegalDocuments(lega_noti_documents);
				}
			}
			return lega_status_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method Purpose : get status details by legal notice id
	@Override
	public List<LegalNoticeStatusReference> getAllLegalNoticeStatus(int lega_noti_id) {
		try {
			SimpleDateFormat In = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat out = new SimpleDateFormat("dd-MM-yyyy");
			List<LegalNoticeStatusReference> sendList = new ArrayList<>();
			List<Object> res = legalNoticeDao.getAllLegalStatus(lega_noti_id);
			Iterator<Object> iterator = res.iterator();
			while (iterator.hasNext()) {
				Object[] object = (Object[]) iterator.next();
				LegalNoticeStatusReference legalNoticeStatusReference = new LegalNoticeStatusReference();
				legalNoticeStatusReference.setLega_status_id(Integer.parseInt(object[0].toString()));
				legalNoticeStatusReference.setLega_notice_id(Integer.parseInt(object[1].toString()));
				legalNoticeStatusReference.setLega_action_taken(object[2].toString());
				if (object[3] != null || object[3] != "") {
					legalNoticeStatusReference.setLega_next_action_item(object[3].toString());
				} else {
					legalNoticeStatusReference.setLega_next_action_item("NA");
				}
				if (object[4] != null) {
					legalNoticeStatusReference.setLega_action_item_due_date(out.format(In.parse(object[4].toString())));
				} else {
					legalNoticeStatusReference.setLega_action_item_due_date("NA");
				}
				if (object[5] != null) {
					legalNoticeStatusReference.setLega_reminder_date(out.format(In.parse(object[5].toString())));
				} else {
					legalNoticeStatusReference.setLega_reminder_date("NA");
				}
				legalNoticeStatusReference.setLega_status_added_by(Integer.parseInt(object[7].toString()));
				if (Integer.parseInt(object[6].toString()) != -1 && Integer.parseInt(object[6].toString()) != 0) {
					legalNoticeStatusReference.setLega_person_responsible(
							userService.getUserFullNameById(Integer.parseInt(object[6].toString())));
				} else {
					legalNoticeStatusReference.setLega_person_responsible(object[9].toString());
				}
				// legalNoticeStatusReference.setLega_person_responsible_others(object[9].toString());
				if (object[10] != null) {
					legalNoticeStatusReference.setLega_received_date(out.format(In.parse(object[10].toString())));
				} else {
					legalNoticeStatusReference.setLega_received_date("NA");
				}
				legalNoticeStatusReference.setLega_notice_status(object[8].toString());
				legalNoticeStatusReference.setLega_documents(
						legalNoticeDao.getAllLegalNoticeDocById(Integer.parseInt(object[0].toString()), 2));
				sendList.add(legalNoticeStatusReference);
			}
			return sendList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LegalNoticeStatus getLegalStatusById(int lega_noti_status_id) {
		try {
			return legalNoticeDao.getLegalStatusById(lega_noti_status_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updatelegalNoticeStatus(LegalNoticeStatus legalNoticeStatus, HttpSession session,
			ArrayList<MultipartFile> lega_status_doc, String status) {
		try {
			LegalNoticeStatus OldData = legalNoticeDao.getLegalStatusById(legalNoticeStatus.getLega_status_id());

			legalNoticeStatus.setLega_created_at(OldData.getLega_created_at());
			legalNoticeStatus.setLega_status_added_by(OldData.getLega_status_added_by());
			legalNoticeStatus.setLega_notice_status(status);

			legalNoticeDao.merge(legalNoticeStatus);

			if (!status.equals("Draft") && OldData.getLega_notice_status().toString().equals("Draft")) {
				// persist legal notice logs
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(legalNoticeStatus.getLega_notice_id());
				logs.setLog_sub_activity_id(legalNoticeStatus.getLega_status_id());
				logs.setLog_activity("Add");
				logs.setLog_assinged_to_id(legalNoticeStatus.getLega_person_responsible());
				logs.setLog_related_to("Legal Notice");
				logs.setLog_sub_activity("Legal Notice Status");
				logs.setLog_related_name(legalNoticeStatus.getLega_action_taken());
				logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
				logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
				legalNoticeDao.saveLogs(logs);
			}

			if (!OldData.getLega_notice_status().toString().equals("Draft")) {
				// persist legal notice logs
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(legalNoticeStatus.getLega_notice_id());
				logs.setLog_sub_activity_id(legalNoticeStatus.getLega_status_id());
				logs.setLog_activity("Update");
				logs.setLog_assinged_to_id(legalNoticeStatus.getLega_person_responsible());
				logs.setLog_related_to("Legal Notice");
				logs.setLog_sub_activity("Legal Notice Status");
				logs.setLog_related_name(legalNoticeStatus.getLega_action_taken());
				logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
				logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
				legalNoticeDao.saveLogs(logs);
			}

			// Upload Documents
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueDoc = legalNoticeDao
					.getLastGenerateValueForLegalNotice(legalNoticeStatus.getLega_status_id(), 2);
			for (int i = 0; i < lega_status_doc.size(); i++) {
				MultipartFile file = lega_status_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents"
							+ File.separator + "LegalNoticeStatusDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedLegaNotiStatusDoc_" + legalNoticeStatus.getLega_status_id() + "_"
							+ lastGeneratedValueDoc + "." + file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					LegalNoticeDocuments lega_noti_documents = new LegalNoticeDocuments();
					lega_noti_documents.setLega_notice_id(legalNoticeStatus.getLega_status_id());
					lega_noti_documents.setLega_doc_original_file_name(originalFileName);
					lega_noti_documents.setLega_doc_generated_file_name(
							"C:/" + project_name + "/Documents/LegalNoticeStatusDocuments/" + generatedFileName);
					lega_noti_documents.setLega_doc_last_generated_value_for_notice_id(lastGeneratedValueDoc);
					lega_noti_documents.setLega_doc_created_date(utilitiesService.getCurrentDate());
					lega_noti_documents.setLega_doc_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
					lega_noti_documents.setLega_doc_related_to(2);

					legalNoticeDao.saveLegalDocuments(lega_noti_documents);
				}
			}
			return legalNoticeStatus.getLega_notice_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get Legal Notice Status logs
	@SuppressWarnings("unchecked")
	@Override
	public String getLegalNoticeStatusLogs(String json, HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

			JSONArray sendList = new JSONArray();
			List<Object> logs = legalNoticeDao.getLegalNoticeStatusLogs(json);

			Iterator<Object> iterator = logs.iterator();
			while (iterator.hasNext()) {
				Object objects[] = (Object[]) iterator.next();
				JSONObject data = new JSONObject();
				data.put("lega_status_id", objects[0].toString());
				data.put("lega_action_taken", objects[2].toString());
				data.put("lega_next_action_item", objects[3].toString());
				data.put("lega_action_item_due_date", sdfOut.format(sdfIn.parse(objects[4].toString())));
				data.put("lega_reminder_date", sdfOut.format(sdfIn.parse(objects[5].toString())));
				data.put("lega_person_responsible", objects[8].toString() + " " + objects[9].toString());
				data.put("lega_status_added_by", objects[7].toString());
				data.put("log_status", objects[10].toString());
				sendList.add(data);
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created : Tejashri Zurunge
	// Method purpose : Delete legal notice
	@Override
	public int deleteLegalNotice(int lega_noti_id) {
		try {
			LegalNotice legalNotice = legalNoticeDao.getLegalNoticeById(lega_noti_id);

			List<Object> legal_status = legalNoticeDao.getAllLegalStatus(lega_noti_id);
			Iterator<Object> iterator2 = legal_status.iterator();
			while (iterator2.hasNext()) {
				Object status[] = (Object[]) iterator2.next();

				// delete status file physically
				List<LegalNoticeDocuments> status_doc = legalNoticeDao
						.getAllLegalNoticeDocById(Integer.parseInt(status[0].toString()), 2);
				if (status_doc != null) {
					Iterator<LegalNoticeDocuments> iterator1 = status_doc.iterator();
					while (iterator1.hasNext()) {
						LegalNoticeDocuments documents1 = iterator1.next();
						File file = new File(documents1.getLega_doc_generated_file_name());
						if (file.delete()) {
						} else {
						}
					}
				}
			}

			// delete notice file physically
			List<LegalNoticeDocuments> doc = legalNoticeDao.getAllLegalNoticeDocById(lega_noti_id, 1);
			if (doc != null) {
				Iterator<LegalNoticeDocuments> iterator = doc.iterator();
				while (iterator.hasNext()) {
					LegalNoticeDocuments documents = iterator.next();
					File file = new File(documents.getLega_doc_generated_file_name());
					if (file.delete()) {
					} else {
					}
				}
			}
			int delete_id = legalNoticeDao.deleteLegalNotice(lega_noti_id);

			// persist legal notice logs
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(lega_noti_id);
			logs.setLog_sub_activity_id(delete_id);
			logs.setLog_activity("Delete");
			logs.setLog_related_to("Legal Notice");
			logs.setLog_sub_activity("Delete Legal Notice");
			logs.setLog_related_name(legalNotice.getLega_noti_reference_no());
			logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
			legalNoticeDao.saveLogs(logs);
			return delete_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method created : Tejashri Zurunge
	// Method purpose : Delete legal notice status
	@Override
	public int deleteLegalNoticeStatus(int lega_status_id) {
		try {
			LegalNoticeStatus legalStatus = getLegalStatusById(lega_status_id);

			List<LegalNoticeDocuments> status_doc = legalNoticeDao.getAllLegalNoticeDocById(lega_status_id, 2);
			Iterator<LegalNoticeDocuments> iterator1 = status_doc.iterator();
			while (iterator1.hasNext()) {
				LegalNoticeDocuments documents1 = iterator1.next();
				File file = new File(documents1.getLega_doc_generated_file_name());
				if (file.delete()) {

				} else {

				}
			}

			int delete_id = legalNoticeDao.deleteLegalNoticeStatus(lega_status_id);
			// persist legal notice logs
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(legalStatus.getLega_notice_id());
			logs.setLog_sub_activity_id(lega_status_id);
			logs.setLog_activity("Delete");
			logs.setLog_related_to("Legal Notice");
			logs.setLog_sub_activity("Delete Legal Notice Status");
			logs.setLog_related_name(legalStatus.getLega_action_taken());
			logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
			legalNoticeDao.saveLogs(logs);
			return delete_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method created : Tejashri Zurunge
	// Method purpose : Delete legal notice status
	@Override
	public List<LegalNoticeDocuments> getLegalNoticeStatusDocById(int lega_noti_status_id) {
		try {
			return legalNoticeDao.getAllLegalNoticeDocById(lega_noti_status_id, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created : Tejashri Zurunge
	// Method purpose : Delete legal notice status doc
	@Override
	public int deleteLegaNotiStatusDoc(int doc_id) {
		try {
			File file = new File(legalNoticeDao.getLegalNoticeDocFilePath(doc_id));

			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}
			return legalNoticeDao.deleteLegaNotiStatusDoc(doc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Object> getUserByLegalNoticeId(int orga_id, int loca_id, int dept_id) {
		try {
			// List<Object> userList = legalNoticeDao.getUserByLegalNoticeId(orga_id,
			// loca_id, dept_id);
			/*
			 * Iterator<Object> iterator =userList.iterator(); while (iterator.hasNext()) {
			 * Object[] object = (Object[]) iterator.next();
			 * System.out.println("This is obj:"+object[0]); }
			 */
			if (legalNoticeDao.getUserByLegalNoticeId(orga_id, loca_id, dept_id) != null) {
				return legalNoticeDao.getUserByLegalNoticeId(orga_id, loca_id, dept_id);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LegalNotice getLegalNoticeBynotiId(int noti_id) {
		try {
			return legalNoticeDao.getLegalNoticeById(noti_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LegalNotice getAllListlegalNotice(int lega_noti_id, HttpSession session) {
		// TODO Auto-generated method stub
		try {
			return legalNoticeDao.getAllListlegalNotice(lega_noti_id, session);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
