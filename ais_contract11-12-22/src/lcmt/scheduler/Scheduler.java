package lcmt.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import lcmt.dao.ExecutedContractDao;
import lcmt.dao.LegalNoticeDao;
import lcmt.dao.LitigationDao;
import lcmt.domain.ContractParties;
import lcmt.domain.HearingStageOnHearing;
import lcmt.domain.LitigationEscalationMailId;
import lcmt.domain.User;
import lcmt.service.LitigationService;
import lcmt.service.SchedulerService;
import lcmt.service.SendMailService;
import lcmt.service.UserService;
import lcmt.service.UtilitiesService;

public class Scheduler {

	@Autowired
	SchedulerService schedulerService;
	@Autowired
	SendMailService sendMailService;
	@Autowired
	LitigationDao litigationDao;
	@Autowired
	LegalNoticeDao legalNoticeDao;
	@Autowired
	UtilitiesService utilitiesService;
	@Autowired
	LitigationService litigationService;
	@Autowired
	UserService userService;
	@Autowired
	ExecutedContractDao executedContractDao;

	private @Value("#{config['mail_user_name'] ?: 'null'}") String username;
	private @Value("#{config['mail_password'] ?: 'null'}") String password;
	private @Value("#{config['mail_smtp_host'] ?: 'null'}") String hostName;
	private @Value("#{config['mail_smtp_port'] ?: 'null'}") String portNo;
	private @Value("#{config['mail_from'] ?: 'null'}") String mailFrom;
	private @Value("#{config['project_url'] ?: 'null'}") String project_url;
	private @Value("#{config['project_name'] ?: 'null'}") String project_name;

	SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

	@Scheduled(cron = "00 45 10 * * ?")
	public void demo() {
		// System.out.println(" Date " + new Date());
	}

	// Method created by : Tejashri Zurunge
	// Method purpose : send mail to assign person on reminder date of query
	@Scheduled(cron = "00 45 14 * * ?")
	public void getReminderQueryMailToAssignPerson() {
		try {
			List<Object> assignId = schedulerService.getAssignPersonFromReminderDate();
			if (assignId != null) {

				for (int i = 0; i < assignId.size(); i++) {
					String query_id_send_to_log = "";
					int matter_handled_by_id = Integer.parseInt(assignId.get(i).toString());

					List<Object> assignQuery = schedulerService
							.getReminderQueryMailToAssignPerson(matter_handled_by_id);
					Iterator<Object> iterator2 = assignQuery.iterator();
					// Mail Body Start
					String email_body = "";
					String res_person_mail = "";
					email_body += "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
					email_body += "<h2 style='font-size:18px;'>Dear User,</h2>";
					email_body += "<p style='text-align:justify;width:70%;'>The following query is required to be attended on or before the due date/s. You are requested to follow the link by clicking on the 'Query Id' and reply the query by login to system.</p>"
							+ "<h2 style='font-size:16px;font-weight:bold;'>Query Details :</h2>";
					email_body += "<table style='width:80%;' border='1'>" + "<thead>"
							+ "<tr style='background:#0B6EC3;color:#fff;'>" + "<th>Query ID</th>" + "<th>Entity</th>"
							+ "<th>Unit</th>" + "<th>Function</th>" + "<th>Query From</th>" + "<th>Query</th>"
							+ "<th>Query Date</th>" + "<th>Assigned To</th>" + "<th>Reminder Date</th>"
							+ "<th>Due Date</th>" + "</tr>" + "</thead>" + "<tbody>";
					// Mail body header end
					int SN = 1;
					while (iterator2.hasNext()) {
						Object[] objects = (Object[]) iterator2.next();
						String quer_id = objects[10].toString();
						String entity = objects[0].toString();
						String unit = objects[1].toString();
						String function = objects[2].toString();
						String res_person = objects[3].toString() + ' ' + objects[4].toString();
						res_person_mail = objects[5].toString();
						String query_from = objects[6].toString();
						String query_dated = sdfOut.format(sdfIn.parse(objects[7].toString()));
						String query_reply_date = sdfOut.format(sdfIn.parse(objects[8].toString()));
						String query_reminder_date = sdfOut.format(sdfIn.parse(objects[9].toString()));
						String query_description = objects[11].toString();

						// Append query id
						query_id_send_to_log += quer_id + ",";

						email_body += "<tr>" + "<td><a href='" + project_url + "queryDetails?query_id=" + quer_id + "'>"
								+ SN + "</a></td>" + "<td>" + entity + "</td>" + "<td>" + unit + "</td>" + "<td>"
								+ function + "</td>" + "<td>" + query_from + "</td>" + "<td>" + query_description
								+ "</td>" + "<td>" + query_dated + "</td>" + "<td>" + res_person + "</td>" + "<td>"
								+ query_reminder_date + "</td>" + "<td>" + query_reply_date + "</td>" + "</tr>";
						SN++;
					}

					email_body += "</tbody>" + "</table>";
					email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
							+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
							+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
							+ "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
					// System.out.println(email_body);
					// Email send code start here
					InternetAddress[] address = new InternetAddress[1];
					try {
						address[0] = new InternetAddress(res_person_mail);
					} catch (AddressException e) {
						e.printStackTrace();
					}
					sendMailService.sendMail("Query reminder", email_body, address, address, query_id_send_to_log);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method created by : Tejashri Zurunge
	// Method purpose : send mail to assign person on reminder date of hearing
	// stage(Litigation)
	/*
	 * @Scheduled(cron = "40 52 15 * * ?") public void
	 * getAlertHearingStageMailToAssignPerson() { try { List<Object> assignId =
	 * schedulerService.getAssignPersonFromAlertDate(); if (assignId != null) { for
	 * (int i = 0; i < assignId.size(); i++) { String hearing_id_send_to_log = "";
	 * int matter_handled_by_id = Integer.parseInt(assignId.get(i).toString());
	 * 
	 * List<Object> assignQuery = schedulerService
	 * .getAlertHearingStageMailToAssignPerson(matter_handled_by_id);
	 * Iterator<Object> iterator2 = assignQuery.iterator(); // Mail Body Start
	 * String email_body = ""; String res_person_mail = ""; email_body +=
	 * "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
	 * email_body += "<h2 style='font-size:18px;'>Dear User,</h2>"; email_body +=
	 * "<p style='text-align:justify;width:70%;'>The following Hearing and Stage is required to be attended on the due date/s. You are requested to follow the link by clicking on the 'Hearing & stage Id' and reply it by login to system.</p>"
	 * +
	 * "<h2 style='font-size:16px;font-weight:bold;'>Hearing and Stages Details :</h2>"
	 * ; email_body += "<table style='width:80%;' border='1'>" + "<thead>" +
	 * "<tr style='background:#0B6EC3;color:#fff;'>" + "<th>Hearing ID</th>" //+
	 * "<th>Stages</th>" + "<th>Hearing dates</th>" + "<th>Responsible person</th>"
	 * //+ "<th>Counsel name</th>" + "<th>Alert dates</th>" +
	 * "<th>Stage description</th>" + "</tr>" + "</thead>" + "<tbody>"; // Mail body
	 * header end int SN = 1; while (iterator2.hasNext()) { Object[] objects =
	 * (Object[]) iterator2.next(); String hear_id = objects[5].toString();
	 * 
	 * //String stage = objects[6].toString(); String hearing_date =
	 * sdfOut.format(sdfIn.parse(objects[0].toString())); String res_person =
	 * objects[7].toString() +' '+objects[8].toString(); res_person_mail =
	 * objects[9].toString(); //String counsel_name = objects[10].toString(); String
	 * first_alert = sdfOut.format(sdfIn.parse(objects[1].toString()));
	 * 
	 * String second_alert = ""; if(objects[2] != null){ second_alert =
	 * sdfOut.format(sdfIn.parse(objects[2].toString())); }else{ second_alert =
	 * "NA"; }
	 * 
	 * String third_alert = ""; if(objects[3] != null){ third_alert =
	 * sdfOut.format(sdfIn.parse(objects[3].toString())); }else{ third_alert = "NA";
	 * } String stage_desc = objects[4].toString(); String liti_id =
	 * objects[10].toString(); // Append query id hearing_id_send_to_log += hear_id
	 * + ",";
	 * 
	 * email_body += "<tr>" + "<td><a href='" + project_url +
	 * "litigationDetails?liti_id=" + liti_id+ "'>" + SN + "</a></td>" //+ "<td>" +
	 * stage + "</td>" + "<td>" + hearing_date + "</td>" + "<td>" + res_person +
	 * "</td>" //+ "<td>" + counsel_name + "</td>" + "<td> 1st Alert :"+ first_alert
	 * + "<br/> 2nd Alert :"+ second_alert + "<br/> 3rd Alert :" + third_alert+
	 * "</td>" + "<td>" + stage_desc + "</td>" + "</tr>"; SN++; }
	 * 
	 * email_body += "</tbody>" + "</table>"; email_body +=
	 * "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
	 * +
	 * "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
	 * + "</p>" +
	 * "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>" +
	 * "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
	 * // System.out.println(email_body); // Email send code start here
	 * InternetAddress[] address = new InternetAddress[1]; try { address[0] = new
	 * InternetAddress(res_person_mail); } catch (AddressException e) {
	 * e.printStackTrace(); }
	 * sendMailService.sendMail("Litigation- Hearing & stage reminder  ",
	 * email_body, address, address, hearing_id_send_to_log); } } } catch (Exception
	 * e) { e.printStackTrace(); } }
	 * 
	 * // Method created by : Tejashri Zurunge // Method purpose : send mail to
	 * assign person on 3rd reminder date of hearing // stage(Litigation)
	 * 
	 * @Scheduled(cron = "20 27 18 * * ?") public void
	 * getAlertHearingStageMail3DaysPrior() { try { List<Object> assignId =
	 * schedulerService.getAssignPersonFrom3rdAlertDate(); if (assignId != null) {
	 * for (int i = 0; i < assignId.size(); i++) { String hearing_id_send_to_log =
	 * ""; int matter_handled_by_id = Integer.parseInt(assignId.get(i).toString());
	 * 
	 * List<Object> assignQuery = schedulerService
	 * .get3rdAlertHearingStageMailToAssignPerson(matter_handled_by_id);
	 * Iterator<Object> iterator2 = assignQuery.iterator(); // Mail Body Start
	 * String liti_id = ""; String email_body = ""; String res_person_mail = "";
	 * email_body +=
	 * "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
	 * email_body += "<h2 style='font-size:18px;'>Dear User,</h2>"; email_body +=
	 * "<p style='text-align:justify;width:70%;'>The following Hearing and Stage is required to be attended on the due date/s. You are requested to follow the link by clicking on the 'Hearing & stage Id' and reply it by login to system.</p>"
	 * +
	 * "<h2 style='font-size:16px;font-weight:bold;'>Hearing and Stages Details :</h2>"
	 * ; email_body += "<table style='width:80%;' border='1'>" + "<thead>" +
	 * "<tr style='background:#0B6EC3;color:#fff;'>" + "<th>Hearing ID</th>" +
	 * "<th>Hearing dates</th>" + "<th>Responsible person</th>" +
	 * "<th>Case Title</th>" + "<th>Stage description</th>" + "<th>Court</th>" +
	 * "</tr>" + "</thead>" + "<tbody>"; // Mail body header end int SN = 1; while
	 * (iterator2.hasNext()) { Object[] objects = (Object[]) iterator2.next();
	 * String hear_id = objects[5].toString();
	 * 
	 * //String stage = objects[6].toString(); String hearing_date =
	 * sdfOut.format(sdfIn.parse(objects[0].toString())); String res_person =
	 * objects[7].toString() +' '+objects[8].toString(); res_person_mail =
	 * objects[9].toString(); //String counsel_name = objects[10].toString(); String
	 * case_title = objects[11].toString(); String court = objects[13].toString();
	 * String stage_desc = objects[4].toString(); liti_id = objects[10].toString();
	 * // Append query id hearing_id_send_to_log += hear_id + ",";
	 * 
	 * email_body += "<tr>" + "<td><a href='" + project_url +
	 * "litigationDetails?liti_id=" + liti_id+ "'>" + SN + "</a></td>" + "<td>" +
	 * hearing_date + "</td>" + "<td>" + res_person + "</td>" + "<td> "+ case_title
	 * +"</td>" + "<td>" + stage_desc + "</td>" + "<td> "+ court +"</td>" + "</tr>";
	 * SN++; }
	 * 
	 * email_body += "</tbody>" + "</table>"; email_body +=
	 * "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
	 * +
	 * "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
	 * + "</p>" +
	 * "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>" +
	 * "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
	 * // System.out.println(email_body); // Email send code start here
	 * InternetAddress[] address = new InternetAddress[1]; try { address[0] = new
	 * InternetAddress(res_person_mail); } catch (AddressException e) {
	 * e.printStackTrace(); }
	 * 
	 * //code to get email is in cc(email of appearing counsel, law firm, internal
	 * resources, others) LitigationEscalationMailId escMail =
	 * litigationService.getEscalationMailByLitiId(Integer.parseInt(liti_id));
	 * if(escMail != null){ String allMail = "";
	 * if(!escMail.getEsc_appear_counsel().equals("NA")){ allMail +=
	 * escMail.getEsc_appear_counsel(); }
	 * 
	 * if(!escMail.getEsc_internal_resource().equals("NA")){ allMail +=
	 * ","+escMail.getEsc_internal_resource(); }
	 * 
	 * if(!escMail.getEsc_law_firm().equals("NA")){ allMail +=
	 * ","+escMail.getEsc_law_firm(); }
	 * 
	 * if(!escMail.getEsc_others().equals("NA")){ allMail +=
	 * ","+escMail.getEsc_others(); }
	 * 
	 * String[] mailCC = allMail.split(","); InternetAddress[] addressCC = new
	 * InternetAddress[mailCC.length]; try { if(mailCC.length != 0){ for(int j = 0;
	 * j< mailCC.length ; j++){ addressCC[j] = new InternetAddress(mailCC[j]); } } }
	 * catch (AddressException e) { e.printStackTrace(); }
	 * sendMailService.sendMail("Litigation- Hearing & stage reminder  ",
	 * email_body, address, addressCC, hearing_id_send_to_log); }else
	 * sendMailService.sendMail("Litigation- Hearing & stage reminder  ",
	 * email_body, address, address, hearing_id_send_to_log); } } } catch (Exception
	 * e) { e.printStackTrace(); } }
	 * 
	 * // Method created by : Tejashri Zurunge // Method purpose : send mail to
	 * assign person on reminder date of legal // notice
	 * 
	 * @Scheduled(cron = "00 15 07 * * ?") public void
	 * getReminderLegalNoticeMailToAssignPerson() { try { List<Object> assignId =
	 * schedulerService.getAssignPersonLegalNoticeFromReminderDate(); if (assignId
	 * != null) { for (int i = 0; i < assignId.size(); i++) { String
	 * lega_noti_id_send_to_log = ""; int matter_handled_by_id =
	 * Integer.parseInt(assignId.get(i).toString());
	 * 
	 * List<Object> assignQuery = schedulerService
	 * .getReminderLegalNoticeMailToAssignPerson(matter_handled_by_id);
	 * Iterator<Object> iterator2 = assignQuery.iterator(); // Mail Body Start
	 * String email_body = ""; String res_person_mail = ""; email_body +=
	 * "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
	 * email_body += "<h2 style='font-size:18px;'>Dear User,</h2>"; email_body +=
	 * "<p style='text-align:justify;width:70%;'>The following Legal Notice is required to be attended on or before the due date/s. You are requested to follow the link by clicking on the 'Legal Notice Id' and reply it by login to system.</p>"
	 * + "<h2 style='font-size:16px;font-weight:bold;'>Legal Notice Details :</h2>";
	 * email_body += "<table style='width:80%;' border='1'>" + "<thead>" +
	 * "<tr style='background:#0B6EC3;color:#fff;'>" + "<th>Legal notice ID</th>" +
	 * "<th>Entity</th>" + "<th>Unit</th>" + "<th>Function</th>" +
	 * "<th>Category</th>" + "<th>Notice date</th>" + "<th>Received date</th>" +
	 * "<th>Deadline date</th>" + "<th>Responsible person</th>" +
	 * "<th>Reminder date</th>" + "</tr>" + "</thead>" + "<tbody>"; // Mail body
	 * header end int SN = 1; while (iterator2.hasNext()) { Object[] objects =
	 * (Object[]) iterator2.next(); String legal_noti_id = objects[4].toString();
	 * String entity = objects[0].toString(); String unit = objects[1].toString();
	 * String function = objects[2].toString(); String category =
	 * objects[3].toString(); String notice_date = objects[5].toString(); String
	 * received_date = objects[6].toString(); String deadline_date =
	 * objects[7].toString(); String resp_person = objects[8].toString()+'
	 * '+objects[9].toString(); res_person_mail = objects[10].toString(); String
	 * reminder_date = objects[11].toString();
	 * 
	 * // Append legal id lega_noti_id_send_to_log += legal_noti_id + ",";
	 * 
	 * email_body += "<tr>" + "<td><a href='" + project_url +
	 * "legalNoticeUpdate?lega_noti_id=" + legal_noti_id+ "'>" + SN + "</a></td>" +
	 * "<td>" + entity + "</td>" + "<td>" + unit + "</td>" + "<td>" + function +
	 * "</td>" + "<td>" + category + "</td>" + "<td> "+ notice_date+ "</td>" +
	 * "<td>" + received_date + "</td>" + "<td>" + deadline_date + "</td>" + "<td>"
	 * + resp_person + "</td>" + "<td>" + reminder_date + "</td>"
	 * 
	 * + "</tr>"; SN++; }
	 * 
	 * email_body += "</tbody>" + "</table>"; email_body +=
	 * "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
	 * +
	 * "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
	 * + "</p>" +
	 * "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>" +
	 * "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
	 * // System.out.println(email_body); // Email send code start here
	 * InternetAddress[] address = new InternetAddress[1]; try { address[0] = new
	 * InternetAddress(res_person_mail); } catch (AddressException e) {
	 * e.printStackTrace(); } sendMailService.sendMail("Legal notice reminder  ",
	 * email_body, address,address, lega_noti_id_send_to_log); } } } catch
	 * (Exception e) { e.printStackTrace(); }
	 * 
	 * } // Method created by : Harshad Padole // Method purpose : send mail to
	 * assign person on reminder date of legal notice status
	 * 
	 * @Scheduled(cron = "00 00 07 * * ?") public void
	 * getAssignedPersonLegalnoticeStatus() { try {
	 * 
	 * List<Object> assignId =
	 * schedulerService.getAssignedPersonLegalnoticeStatus(); if (assignId != null)
	 * { for (int i = 0; i < assignId.size(); i++) { String lega_noti_id_send_to_log
	 * = ""; int id = Integer.parseInt(assignId.get(i).toString()); List<Object> res
	 * = schedulerService.getReminderLegalNoticeStatusMailToAssignPerson(id);
	 * Iterator<Object> iterator = res.iterator(); // Mail Body Start String
	 * email_body = ""; String res_person_mail = ""; email_body +=
	 * "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
	 * email_body += "<h2 style='font-size:18px;'>Dear User,</h2>"; email_body +=
	 * "<p style='text-align:justify;width:70%;'>The following Legal Notice Status is required to be attended on or before the due date/s. You are requested to follow the link by clicking on the 'Legal Notice Id' and reply it by login to system.</p>"
	 * +
	 * "<h2 style='font-size:16px;font-weight:bold;'>Legal Notice Status Details :</h2>"
	 * ; email_body += "<table style='width:80%;' border='1'>" + "<thead>" +
	 * "<tr style='background:#0B6EC3;color:#fff;'>" + "<th>Legal notice ID</th>" +
	 * "<th>Entity</th>" + "<th>Unit</th>" + "<th>Function</th>" +
	 * "<th>Deadline date</th>" + "<th>Recived date</th>" + "<th>Action taken</th>"
	 * + "<th>Next action item</th>" + "<th>Next action due date</th>" +
	 * "<th>Responsible person</th>" + "<th>Reminder date</th>" + "</tr>" +
	 * "</thead>" + "<tbody>"; // Mail body header end int SN = 1; while
	 * (iterator.hasNext()) { Object[] objects = (Object[]) iterator.next(); String
	 * legal_noti_id = objects[3].toString(); String entity = objects[0].toString();
	 * String unit = objects[1].toString(); String function = objects[2].toString();
	 * String lega_reply_deadline = "NA"; if(objects[4]!=null) lega_reply_deadline =
	 * sdfOut.format(sdfIn.parse(objects[4].toString()));
	 * 
	 * String resposible_persone = objects[5].toString()+" "+objects[6].toString();
	 * 
	 * res_person_mail = objects[7].toString(); String action_taken =
	 * objects[8].toString(); String next_action_item = objects[9].toString();
	 * String action_item_due_date = "NA"; if(objects[10]!=null)
	 * action_item_due_date = sdfOut.format(sdfIn.parse(objects[10].toString()));
	 * 
	 * String reminder_date = "NA"; if(objects[11]!=null) reminder_date =
	 * sdfOut.format(sdfIn.parse(objects[11].toString()));
	 * 
	 * String recived_date = "NA"; if(objects[12]!=null) recived_date =
	 * sdfOut.format(sdfIn.parse(objects[12].toString()));
	 * 
	 * String status_id = objects[13].toString(); // Append legal id
	 * lega_noti_id_send_to_log += legal_noti_id+"-"+status_id + ",";
	 * 
	 * email_body += "<tr>" + "<td><a href='" + project_url +
	 * "legalNoticeUpdate?lega_noti_id=" + legal_noti_id+ "'>" + SN + "</a></td>" +
	 * "<td>" + entity + "</td>" + "<td>" + unit + "</td>" + "<td>" + function +
	 * "</td>" + "<td>" + lega_reply_deadline + "</td>" + "<td>" + recived_date +
	 * "</td>" + "<td> "+ action_taken+ "</td>" + "<td>" + next_action_item +
	 * "</td>" + "<td>" + action_item_due_date + "</td>" + "<td>" +
	 * resposible_persone + "</td>" + "<td>" + reminder_date + "</td>" + "</tr>";
	 * SN++;
	 * 
	 * }
	 * 
	 * email_body += "</tbody>" + "</table>"; email_body +=
	 * "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
	 * +
	 * "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
	 * + "</p>" +
	 * "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>" +
	 * "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
	 * 
	 * InternetAddress[] address = new InternetAddress[1]; try { address[0] = new
	 * InternetAddress(res_person_mail); } catch (AddressException e) {
	 * e.printStackTrace(); }
	 * sendMailService.sendMail("Legal notice status reminder  ", email_body,
	 * address,address, lega_noti_id_send_to_log); } } } catch (Exception e) {
	 * e.printStackTrace(); } }
	 * 
	 * // Method created by : Harshad Padole // Method purpose : Send previous month
	 * report to admin on every first day of a month
	 * 
	 * @Scheduled(cron = "00 17 16 1 * ?") public void LitigationReport() {
	 * 
	 * ArrayList<String> email = new ArrayList<String>(); String liti_id_log = "";
	 * String email_body = ""; email_body +=
	 * "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
	 * email_body += "<h2 style='font-size:18px;'>Dear Admin,</h2>"; email_body +=
	 * "<p style='text-align:justify;width:70%;'>The following is Litigation report of previous month. </p>"
	 * + "<h2 style='font-size:16px;font-weight:bold;'>Litigation Details :</h2>";
	 * 
	 * email_body += "<table style='width:80%;' border='1'>" + "<thead>" +
	 * "<tr style='background:#0B6EC3;color:#fff;'>" + "<th>Sr.No</th>" +
	 * "<th>Entity</th>" + "<th>Unit</th>" + "<th>Function</th>" +
	 * "<th>By/Against</th>" + "<th>Company acting as</th>" +
	 * "<th>Opposite party</th>" + "<th>Opposite party acting as</th>" +
	 * "<th>Amount Involved</th>" + "<th>Next Hearing Date </th> " +
	 * "<th>Status</th>" //+ "<th>Result</th>" + "</tr>" + "</thead>" + "<tbody>";
	 * 
	 * List<Object> result = schedulerService.getLitigationDetailsForReport(); if
	 * (result != null && result.size() != 0) { // Get User emails List<Object>
	 * admin_list = schedulerService.getAllAdminUser(); Iterator<Object> user_list =
	 * admin_list.iterator(); while (user_list.hasNext()) { Object[] user =
	 * (Object[]) user_list.next(); if (user[1] != null)
	 * email.add(user[1].toString()); } // Get email end } if (result != null &&
	 * result.size() != 0) { Iterator<Object> iterator = result.iterator(); int SN =
	 * 1; while (iterator.hasNext()) { Object[] objects = (Object[])
	 * iterator.next(); String next_hearing_date = "NA"; String liti_id =
	 * objects[0].toString(); String orga_name = objects[1].toString(); String
	 * loca_name = objects[2].toString(); String dept_name = objects[3].toString();
	 * String liti_type = objects[4].toString(); String company_acting_as =
	 * objects[5].toString(); String oppo_party = "NA"; if (objects[6] != null)
	 * oppo_party = objects[6].toString();
	 * 
	 * String oppo_party_acting_as = "NA";
	 * 
	 * if (objects[7] != null) oppo_party_acting_as = objects[7].toString();
	 * 
	 * String amt_involved = "NA"; if (objects[8] != null) amt_involved =
	 * objects[8].toString();
	 * 
	 * String amt_involved_currency = "NA"; if (objects[9] != null)
	 * amt_involved_currency = objects[9].toString();
	 * 
	 * String liti_status = "NA"; if (objects[10] != null) liti_status =
	 * objects[10].toString();
	 * 
	 * String liti_result = "NA"; if (objects[11] != null) liti_result =
	 * objects[11].toString();
	 * 
	 * next_hearing_date =
	 * litigationDao.getLatestNextHearingStageDate(Integer.parseInt(liti_id)); if
	 * (!next_hearing_date.equals("NA")) { try { next_hearing_date =
	 * sdfOut.format(sdfIn.parse(next_hearing_date)); } catch (ParseException e) {
	 * e.printStackTrace(); } }
	 * 
	 * liti_id_log += liti_id+","; email_body += "<tr>" + "<td><a href='" +
	 * project_url + "litigationDetails?liti_id=" + liti_id+ "'>" + SN + "</a></td>"
	 * + "<td>" + orga_name + "</td>" + "<td>" + loca_name + "</td>" + "<td>" +
	 * dept_name + "</td>" + "<td>" + liti_type + "</td>" + "<td>" +
	 * company_acting_as+ "</td>" + "<td>" + oppo_party + "</td>" + "<td>" +
	 * oppo_party_acting_as+ "</td>" + "<td>" + amt_involved
	 * +" "+amt_involved_currency+ "</td>" + "<td>" + next_hearing_date + "</td>" +
	 * "<td>" + liti_status + "</td>" //+ "<td>" + liti_result + "</td>" + "</tr>";
	 * 
	 * SN++; }//while end email_body += "</tbody>" + "</table>"; email_body +=
	 * "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
	 * +
	 * "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
	 * + "</p>" +
	 * "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>" +
	 * "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
	 * 
	 * if (result != null && result.size() != 0) { InternetAddress[] address = new
	 * InternetAddress[email.size()]; for (int i = 0; i < email.size(); i++) { try {
	 * address[i] = new InternetAddress((String) email.get(i)); } catch
	 * (AddressException e) { e.printStackTrace(); } }
	 * sendMailService.sendMail("Litigation Report ", email_body, address,address,
	 * liti_id_log); }
	 * 
	 * }//End if }
	 */
	// Method created by : Tejashri Zurunge
	// Method purpose : Send previous month report to admin on every first day of a
	// month
	@Scheduled(cron = "30 26 16 1 * ?")
	public void QueryReport() {

		ArrayList<String> email = new ArrayList<String>();
		String query_id_log = "";
		String email_body = "";
		email_body += "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
		email_body += "<h2 style='font-size:18px;'>Dear Admin,</h2>";
		email_body += "<p style='text-align:justify;width:70%;'>The following is Query report of previous month. </p>"
				+ "<h2 style='font-size:16px;font-weight:bold;'>Query Details :</h2>";

		email_body += "<table style='width:80%;' border='1'>" + "<thead>"
				+ "<tr style='background:#0B6EC3;color:#fff;'>" + "<th>Sr.No</th>" + "<th>Entity</th>" + "<th>Unit</th>"
				+ "<th>Function</th>" + "<th>From</th>" + "<th>Query</th>" + "<th>Assigned to</th>"
				+ "<th>Query Date </th> " + "<th>Query Status</th>" + "</tr>" + "</thead>" + "<tbody>";

		List<Object> result = schedulerService.getQueryDetailsForReport();
		if (result != null && result.size() != 0) {
			// Get User emails
			List<Object> admin_list = schedulerService.getAllAdminUser();
			Iterator<Object> user_list = admin_list.iterator();
			while (user_list.hasNext()) {
				Object[] user = (Object[]) user_list.next();
				if (user[1] != null)
					email.add(user[1].toString());
			}
			// Get email end
		}
		if (result != null && result.size() != 0) {
			Iterator<Object> iterator = result.iterator();
			int SN = 1;
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				String query_date = "NA";
				String quer_id = objects[9].toString();
				String orga_name = objects[0].toString();
				String loca_name = objects[1].toString();
				String dept_name = objects[2].toString();
				String from_name = objects[5].toString();
				String quer_query = objects[10].toString();
				String assigned_to = "NA";

				if (Integer.parseInt(objects[12].toString()) != 0)
					assigned_to = objects[3].toString() + " " + objects[4].toString();

				String quer_status = "NA";
				if (objects[10] != null)
					quer_status = objects[11].toString();

				if (objects[6] != null)
					try {
						query_date = sdfOut.format(sdfIn.parse(objects[6].toString()));
					} catch (ParseException e) {
						e.printStackTrace();
					}

				query_id_log += quer_id + ",";
				email_body += "<tr>" + "<td><a href='" + project_url + "queryDetails?query_id=" + quer_id + "'>" + SN
						+ "</a></td>" + "<td>" + orga_name + "</td>" + "<td>" + loca_name + "</td>" + "<td>" + dept_name
						+ "</td>" + "<td>" + from_name + "</td>" + "<td>" + quer_query + "</td>" + "<td>" + assigned_to
						+ "</td>" + "<td>" + query_date + "</td>" + "<td>" + quer_status + "</td>" + "</tr>";
				SN++;
			} // while end
			email_body += "</tbody>" + "</table>";
			email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
					+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
					+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
					+ "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";

			if (result != null && result.size() != 0) {
				InternetAddress[] address = new InternetAddress[email.size()];
				for (int i = 0; i < email.size(); i++) {
					try {
						address[i] = new InternetAddress((String) email.get(i));
					} catch (AddressException e) {
						e.printStackTrace();
					}
				}
				sendMailService.sendMail("Query Report ", email_body, address, address, query_id_log);
			}

		} // End if
	}

	// Method created by : Tejashri Zurunge
	// Method purpose : Send previous month report to admin on every first day of a
	// month
	/*
	 * @Scheduled(cron = "00 40 06 1 * ?") public void LegalNoticeReport() {
	 * 
	 * ArrayList<String> email = new ArrayList<String>(); String lega_noti_id_log =
	 * ""; String email_body = ""; email_body +=
	 * "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
	 * email_body += "<h2 style='font-size:18px;'>Dear Admin,</h2>"; email_body +=
	 * "<p style='text-align:justify;width:70%;'>The following is Legal Notice report of previous month. </p>"
	 * + "<h2 style='font-size:16px;font-weight:bold;'>Legal Notice Details :</h2>";
	 * 
	 * email_body += "<table style='width:80%;' border='1'>" + "<thead>" +
	 * "<tr style='background:#0B6EC3;color:#fff;'>" + "<th>Sr.No</th>" +
	 * "<th>Entity</th>" + "<th>Unit</th>" + "<th>Function</th>" +
	 * "<th>Reference No.</th>" + "<th>By/Against</th>" + "<th>Amount Involved</th>"
	 * + "<th>Assigned to</th>" + "<th>Reply Deadline Date </th> " +
	 * "<th>Action Taken</th>" + "</tr>" + "</thead>" + "<tbody>";
	 * 
	 * List<Object> result = schedulerService.getLegalNoticeDetailsForReport(); if
	 * (result != null && result.size() != 0) { // Get User emails List<Object>
	 * admin_list = schedulerService.getAllAdminUser(); Iterator<Object> user_list =
	 * admin_list.iterator(); while (user_list.hasNext()) { Object[] user =
	 * (Object[]) user_list.next(); if (user[1] != null)
	 * email.add(user[1].toString()); } // Get email end } if (result != null &&
	 * result.size() != 0) { Iterator<Object> iterator = result.iterator(); int SN =
	 * 1; while (iterator.hasNext()) { Object[] objects = (Object[])
	 * iterator.next(); String reply_deadline_date = "NA"; String lega_noti_id =
	 * objects[3].toString(); String orga_name = objects[0].toString(); String
	 * loca_name = objects[1].toString(); String dept_name = objects[2].toString();
	 * String ref_no = objects[10].toString(); String company_by_against =
	 * objects[9].toString(); String assign_to = objects[5].toString() + ' ' +
	 * objects[6].toString(); // String action_taken = "NA"; String amt_involved =
	 * "NA"; if (objects[7] != null) amt_involved = objects[7].toString();
	 * 
	 * String amt_involved_currency = "NA"; if (objects[8] != null)
	 * amt_involved_currency = objects[8].toString();
	 * 
	 * // action_taken = "NA"; String action_taken =
	 * legalNoticeDao.getLatestActionTaken(Integer.parseInt(lega_noti_id)); if
	 * (objects[4] != null) { try { reply_deadline_date =
	 * sdfOut.format(sdfIn.parse(objects[4].toString())); } catch (ParseException e)
	 * { e.printStackTrace(); } }
	 * 
	 * lega_noti_id_log += lega_noti_id + ","; email_body += "<tr>" +
	 * "<td><a href='" + project_url + "legalNoticeUpdate?lega_noti_id=" +
	 * lega_noti_id + "'>" + SN + "</a></td>" + "<td>" + orga_name + "</td>" +
	 * "<td>" + loca_name + "</td>" + "<td>" + dept_name + "</td>" + "<td>" + ref_no
	 * + "</td>" + "<td>" + company_by_against + "</td>" + "<td>" + amt_involved +
	 * " " + amt_involved_currency + "</td>" + "<td>" + assign_to + "</td>" + "<td>"
	 * + reply_deadline_date + "</td>" + "<td>" + action_taken + "</td>" + "</tr>";
	 * 
	 * SN++; } // while end email_body += "</tbody>" + "</table>"; email_body +=
	 * "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
	 * +
	 * "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
	 * + "</p>" +
	 * "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>" +
	 * "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
	 * 
	 * if (result != null && result.size() != 0) { InternetAddress[] address = new
	 * InternetAddress[email.size()]; for (int i = 0; i < email.size(); i++) { try {
	 * address[i] = new InternetAddress((String) email.get(i)); } catch
	 * (AddressException e) { e.printStackTrace(); } }
	 * sendMailService.sendMail("Legal Notice Report ", email_body, address,
	 * address, lega_noti_id_log); }
	 * 
	 * } // End if }
	 */
	// Method created by : Tejashri Zurunge
	// Method purpose : Send previous month report to admin on every first day of a month
	@Scheduled(cron = "00 24 16 7 * ?")
	public void ContractReport() {

		ArrayList<String> email = new ArrayList<String>();
		String cont_id_log = "";
		String email_body = "";
		email_body += "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
		email_body += "<h2 style='font-size:18px;'>Dear Admin,</h2>";
		email_body += "<p style='text-align:justify;width:70%;'>The following is Contract report of previous month. </p>"
				+ "<h2 style='font-size:16px;font-weight:bold;'>Pre-Execution Contract Details :</h2>";

		email_body += "<table style='width:80%;' border='1'>" + "<thead>"
				+ "<tr style='background:#0B6EC3;color:#fff;'>" + "<th>Sr.No</th>" + "<th>Entity</th>" + "<th>Unit</th>"
				+ "<th>Function</th>" + "<th>Name of Document</th>" + "<th>Requested On</th>"
				+ "<th>Expected date of closure</th> " + "<th>Assigned to</th>" + "<th>Targeted date </th> "
				+ "<th>Status</th>" + "</tr>" + "</thead>" + "<tbody>";

		List<Object> result = schedulerService.getContractDetailsForReport();
		if (result != null && result.size() != 0) {
			// Get User emails
			List<Object> admin_list = schedulerService.getAllAdminUser();
			Iterator<Object> user_list = admin_list.iterator();
			while (user_list.hasNext()) {
				Object[] user = (Object[]) user_list.next();
				if (user[1] != null)
					email.add(user[1].toString());
			}
			// Get email end
		}
		if (result != null && result.size() != 0) {
			Iterator<Object> iterator = result.iterator();
			int SN = 1;
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				String target_date = "NA";
				String cont_id = objects[9].toString();
				String orga_name = objects[0].toString();
				String loca_name = objects[1].toString();
				String dept_name = objects[2].toString();
				String doc_name = objects[3].toString();
				String req_date = "NA";
				String expe_date = "NA";
				String assigned_to = objects[6].toString() + " " + objects[7].toString();

				String cont_status = "NA";
				if (objects[10] != null)
					cont_status = objects[10].toString();

				if (objects[8] != null)
					try {
						target_date = sdfOut.format(sdfIn.parse(objects[8].toString()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				if (objects[6] != null)
					try {
						expe_date = sdfOut.format(sdfIn.parse(objects[5].toString()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				if (objects[6] != null)
					try {
						req_date = sdfOut.format(sdfIn.parse(objects[4].toString()));
					} catch (ParseException e) {
						e.printStackTrace();
					}

				cont_id_log += cont_id + ",";
				email_body += "<tr>" + "<td><a href='" + project_url + "updateTab?cont_id=" + cont_id + "'>" + SN
						+ "</a></td>" + "<td>" + orga_name + "</td>" + "<td>" + loca_name + "</td>" + "<td>" + dept_name
						+ "</td>" + "<td>" + doc_name + "</td>" + "<td>" + req_date + "</td>" + "<td>" + expe_date
						+ "</td>" + "<td>" + assigned_to + "</td>" + "<td>" + target_date + "</td>" + "<td>"
						+ cont_status + "</td>" + "</tr>";
				SN++;
			} // while end
			email_body += "</tbody>" + "</table>";
			email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
					+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
					+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
					+ "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";

			if (result != null && result.size() != 0) {
				InternetAddress[] address = new InternetAddress[email.size()];
				for (int i = 0; i < email.size(); i++) {
					try {
						address[i] = new InternetAddress((String) email.get(i));
					} catch (AddressException e) {
						e.printStackTrace();
					}
				}
				sendMailService.sendMail("Pre-Execution Report ", email_body, address, address, cont_id_log);
			}

		} // End if
	}

	// Method created by : Tejashri Zurunge
	// Method purpose : Send previous month report to admin on every first day of a
	// month
	@Scheduled(cron = "00 30 09 1 * ?")
	public void ExecutedContractReport() {

		ArrayList<String> email = new ArrayList<String>();
		String exec_id_log = "";
		String email_body = "";
		email_body += "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
		email_body += "<h2 style='font-size:18px;'>Dear Admin,</h2>";
		email_body += "<p style='text-align:justify;width:70%;'>The following is Executed Contract report of previous month. </p>"
				+ "<h2 style='font-size:16px;font-weight:bold;'>Executed Contract Details :</h2>";

		email_body += "<table style='width:80%;' border='1'>" + "<thead>"
				+ "<tr style='background:#0B6EC3;color:#fff;'>" + "<th>Sr.No</th>" + "<th>Entity</th>" + "<th>Unit</th>"
				+ "<th>Function</th>" + "<th>Contract type</th>" + "<th>Description</th>" + "<th>Contract Date </th> "
				+ "</tr>" + "</thead>" + "<tbody>";

		List<Object> result = schedulerService.getExecutedContractDetailsForReport();
		if (result != null && result.size() != 0) {
			// Get User emails
			List<Object> admin_list = schedulerService.getAllAdminUser();
			Iterator<Object> user_list = admin_list.iterator();
			while (user_list.hasNext()) {
				Object[] user = (Object[]) user_list.next();
				if (user[1] != null)
					email.add(user[1].toString());
			}
			// Get email end
		}
		if (result != null && result.size() != 0) {
			Iterator<Object> iterator = result.iterator();
			int SN = 1;
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				String exec_cont_id = objects[3].toString();
				String orga_name = objects[0].toString();
				String loca_name = objects[1].toString();
				String dept_name = objects[2].toString();
				String cont_type_name = objects[4].toString();
				String description = objects[5].toString();
				String cont_date = "NA";

				if (objects[6] != null) {
					try {
						cont_date = sdfOut.format(sdfIn.parse(objects[6].toString()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}

				exec_id_log += exec_cont_id + ",";
				email_body += "<tr>" + "<td><a href='" + project_url + "executedContractUpdates?exec_id=" + exec_cont_id
						+ "'>" + SN + "</a></td>" + "<td>" + orga_name + "</td>" + "<td>" + loca_name + "</td>" + "<td>"
						+ dept_name + "</td>" + "<td>" + cont_type_name + "</td>" + "<td>" + description + "</td>"
						+ "<td>" + cont_date + "</td>" + "</tr>";

				SN++;
			} // while end
			email_body += "</tbody>" + "</table>";
			email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
					+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
					+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
					+ "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";

			if (result != null && result.size() != 0) {
				InternetAddress[] address = new InternetAddress[email.size()];
				for (int i = 0; i < email.size(); i++) {
					try {
						address[i] = new InternetAddress((String) email.get(i));
					} catch (AddressException e) {
						e.printStackTrace();
					}
				}
				System.out.println("mail body "+ email_body);
				sendMailService.sendMail("Executed Contract Report ", email_body, address, address, exec_id_log);
			}

		} // End if
	}

	// Method created by : Tejashri Zurunge
	// Method purpose : send mail to assign person on reminder date of legal
	// notice
	@Scheduled(cron = "00 00 13 * * ?")
	public void getReminderTaskCompletionExeContractMailToAssignPerson() {
		try {
			List<Object> assignId = schedulerService.getAssignPersonTaskCompletionFromReminderDate();
			if (assignId != null) {

				for (int i = 0; i < assignId.size(); i++) {
					String task_id_send_to_log = "";
					int task_assigned_to_id = Integer.parseInt(assignId.get(i).toString());

					List<Object> assignTask = schedulerService
							.getReminderTaskCompletionMailToAssignPerson(task_assigned_to_id);
					Iterator<Object> iterator2 = assignTask.iterator();
					// Mail Body Start
					String email_body = "";
					String res_person_mail = "";
					email_body += "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
					email_body += "<h2 style='font-size:18px;'>Dear User,</h2>";
					email_body += "<p style='text-align:justify;width:70%;'>The following Action Item is required to be attended on or before the due date/s. You are requested to follow the link by clicking on the 'Action item Id' and reply it by login to system.</p>"
							+ "<h2 style='font-size:16px;font-weight:bold;'>Action Item Details :</h2>";
					email_body += "<table style='width:80%;' border='1'>" + "<thead>"
							+ "<tr style='background:#0B6EC3;color:#fff;'>" + "<th>Action Item ID</th>"
							+ "<th>Entity</th>" + "<th>Unit</th>" + "<th>Function</th>" + "<th>Action item</th>"
							+ "<th>Responsible person</th>" + "<th>Action due date</th>" + "<th>Frequency</th>"
							+ "</tr>" + "</thead>" + "<tbody>";
					// Mail body header end
					int SN = 1;
					while (iterator2.hasNext()) {
						Object[] objects = (Object[]) iterator2.next();
						String action_id = objects[7].toString();
						String entity = objects[0].toString();
						String unit = objects[1].toString();
						String function = objects[2].toString();
						String action_item = objects[10].toString();
						String due_date = sdfOut.format(sdfIn.parse(objects[8].toString()));
						String frequency = objects[9].toString();
						String resp_person = objects[3].toString() + ' ' + objects[4].toString();
						res_person_mail = objects[5].toString();

						// Append legal id
						task_id_send_to_log += action_id + ",";

						email_body += "<tr>" + "<td><a href='" + project_url + "actionItemHistory?action_id="
								+ action_id + "'>" + SN + "</a></td>" + "<td>" + entity + "</td>" + "<td>" + unit
								+ "</td>" + "<td>" + function + "</td>" + "<td>" + action_item + "</td>" + "<td> "
								+ resp_person + "</td>" + "<td>" + due_date + "</td>" + "<td>" + frequency + "</td>"

								+ "</tr>";
						SN++;
					}

					email_body += "</tbody>" + "</table>";
					email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
							+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
							+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
							+ "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
					// System.out.println(email_body);
					// Email send code start here
					InternetAddress[] address = new InternetAddress[1];
					try {
						address[0] = new InternetAddress(res_person_mail);
					} catch (AddressException e) {
						e.printStackTrace();
					}
					sendMailService.sendMail("Action item reminder", email_body, address, address, task_id_send_to_log);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method created : Tejashri Zurunge
	// Method purpose : auto activation of task
	@Scheduled(cron = "00 02 08 * * ?")
	public void autoActivateActionItemTask() {
		try {
			schedulerService.autoActivateActionItemTask();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "00 36 15 1 * ?")
	public void sendCurrentMonthActionItemToSPOC() {
		try {
			String task_id_send_to_log = "";

			List<Object> assignTask = schedulerService.getActionItemCurrentMonth();
			Iterator<Object> iterator2 = assignTask.iterator();
			// Mail Body Start
			String email_body = "";
			email_body += "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
			email_body += "<h2 style='font-size:18px;'>Dear User,</h2>";
			email_body += "<p style='text-align:justify;width:70%;'>The following Action Item is required to be attended on or before the due date/s.</p>"
					+ "<h2 style='font-size:16px;font-weight:bold;'>Action Item Details :</h2>";
			email_body += "<table style='width:80%;' border='1'>" + "<thead>"
					+ "<tr style='background:#0B6EC3;color:#fff;'>" + "<th>Action Item ID</th>" + "<th>Entity</th>"
					+ "<th>Unit</th>" + "<th>Function</th>" + "<th>Action item</th>" + "<th>Responsible person</th>"
					+ "<th>Action due date</th>" + "<th>Frequency</th>" + "</tr>" + "</thead>" + "<tbody>";
			int SN = 1;
			while (iterator2.hasNext()) {
				Object[] objects = (Object[]) iterator2.next();
				String action_id = objects[7].toString();
				String entity = objects[0].toString();
				String unit = objects[1].toString();
				String function = objects[2].toString();
				String action_item = objects[10].toString();
				String due_date = sdfOut.format(sdfIn.parse(objects[8].toString()));
				String frequency = objects[9].toString();
				String resp_person = objects[3].toString() + ' ' + objects[4].toString();

				// Append legal id
				task_id_send_to_log += action_id + ",";

				email_body += "<tr>" + "<td><a href='" + project_url + "actionItemHistory?action_id=" + action_id + "'>"
						+ SN + "</a></td>" + "<td>" + entity + "</td>" + "<td>" + unit + "</td>" + "<td>" + function
						+ "</td>" + "<td>" + action_item + "</td>" + "<td> " + resp_person + "</td>" + "<td>" + due_date
						+ "</td>" + "<td>" + frequency + "</td>"

						+ "</tr>";
				SN++;
			}

			email_body += "</tbody>" + "</table>";
			email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
					+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
					+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
					+ "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
			// System.out.println(email_body);
			// Email send code start here
			List<User> userList = schedulerService.getContractSPOCuser();
			Iterator<User> itr = userList.iterator();
			InternetAddress[] address = new InternetAddress[userList.size()];
			int i = 0;
			while (itr.hasNext()) {
				User user = itr.next();
				address[i] = new InternetAddress(user.getUser_email());
				i++;
			}
			sendMailService.sendMail("Current Month Action Items", email_body, address, address, task_id_send_to_log);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * @Scheduled(cron = "00 00 16 * * ?") public void AlertOnHearingDate() { try {
	 * List<Object> result = schedulerService.getHearingStageOnHearing(); if (result
	 * != null) {
	 * 
	 * Iterator<Object> iterator = result.iterator(); while (iterator.hasNext()) {
	 * 
	 * ArrayList<String> email = new ArrayList<String>(); String email_body = "";
	 * String liti_id = ""; String hearing_id = ""; String law_firm_mail_id = "";
	 * 
	 * email_body +=
	 * "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
	 * email_body += "<h2 style='font-size:18px;'>Dear User,</h2>"; email_body +=
	 * "<p style='text-align:justify;width:70%;'>The following hearing stage is required to be attended on or before the due date/s.</p>"
	 * +
	 * "<h2 style='font-size:16px;font-weight:bold;'>Hearing stage details :</h2>";
	 * email_body += "<table style='width:80%;' border='1'>" + "<thead>" +
	 * "<tr style='background:#0B6EC3;color:#fff;'>" + "<th>ID</th>" +
	 * "<th>Next hearing date</th>" + "<th>First alert</th>" +
	 * "<th>Stage Description</th>" + "<th>Responsible person</th>" +
	 * "<th>Link to update status</th>" + "</tr>" + "</thead>" + "<tbody>";
	 * 
	 * Object[] object = (Object[]) iterator.next(); liti_id = object[0].toString();
	 * String res_person =
	 * userService.getUserFullNameById(Integer.parseInt(object[7].toString()));
	 * hearing_id = object[6].toString();
	 * 
	 * email_body += "<tr>" + "<td>1</td>" + "<td>" +
	 * sdfOut.format(sdfIn.parse(object[1].toString())) + "</td>" + "<td>" +
	 * sdfOut.format(sdfIn.parse(object[2].toString())) + "</td>" + "<td>" +
	 * object[5] + "</td>" + "<td> " + res_person + "</td>" + "<td><a href='" +
	 * project_url + "addHearingStageOnHearing?liti_id=" + liti_id + "&hear_id=" +
	 * hearing_id + "'>link</a></td>" + "</tr>";
	 * 
	 * email_body += "</tbody>" + "</table>"; email_body +=
	 * "<p style='text-align:justify;width:70%;'>Kindly follow the above link to update current status of hearing stage. </p>"
	 * ;
	 * 
	 * email_body +=
	 * "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
	 * +
	 * "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
	 * + "</p>" +
	 * "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>" +
	 * "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
	 * 
	 * law_firm_mail_id =
	 * schedulerService.getLawFirmLitigationMailId(Integer.parseInt(liti_id))
	 * .getEsc_law_firm(); if (law_firm_mail_id != null) {
	 * 
	 * String mail[] = law_firm_mail_id.split(",");
	 * 
	 * InternetAddress[] addressTo = new InternetAddress[mail.length]; try { for
	 * (int j = 0; j < mail.length; j++) addressTo[j] = new
	 * InternetAddress(mail[j].toString()); } catch (AddressException e) {
	 * e.printStackTrace(); }
	 * 
	 * // Get Admin emails in cc List<Object> admin_list =
	 * schedulerService.getAllAdminUser(); Iterator<Object> user_list =
	 * admin_list.iterator(); while (user_list.hasNext()) { Object[] user =
	 * (Object[]) user_list.next(); if (user[1] != null)
	 * email.add(user[1].toString()); } // Get email end
	 * 
	 * InternetAddress[] address = new InternetAddress[email.size()]; for (int i =
	 * 0; i < email.size(); i++) { try { address[i] = new
	 * InternetAddress(email.get(i)); } catch (AddressException e) {
	 * e.printStackTrace(); } }
	 * sendMailService.sendMail("Update Status for Hearing Stage ", email_body,
	 * addressTo, address, liti_id); } HearingStageOnHearing hearingStatus = new
	 * HearingStageOnHearing();
	 * hearingStatus.setTrn_hearing_id(Integer.parseInt(hearing_id));
	 * hearingStatus.setTrn_liti_id(Integer.parseInt(liti_id));
	 * hearingStatus.setTrn_hearing_status(0); hearingStatus.setTrn_newHear_id(0);
	 * hearingStatus.setTrn_hearing_created_at(utilitiesService.getCurrentDate());
	 * litigationDao.persist(hearingStatus); // SN++; } } } catch (Exception e) {
	 * e.printStackTrace(); } }
	 * 
	 * // Method created by : Tejashri Zurunge // Method purpose : Send current
	 * month report to admin on every 3rd day of a // month
	 * 
	 * @Scheduled(cron = "30 00 09 3 * ?") public void
	 * LitigationReportForComingMonth() { try { ArrayList<String> email = new
	 * ArrayList<String>(); String liti_id_log = ""; String email_body = "";
	 * email_body +=
	 * "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
	 * email_body += "<h2 style='font-size:18px;'>Dear Admin,</h2>"; email_body +=
	 * "<p style='text-align:justify;width:70%;'>The following is Litigation- Hearing stage report of upcoming month. </p>"
	 * + "<h2 style='font-size:16px;font-weight:bold;'>Litigation Details :</h2>";
	 * 
	 * email_body += "<table style='width:80%;' border='1'>" + "<thead>" +
	 * "<tr style='background:#0B6EC3;color:#fff;'>" + "<th>Sr.No</th>" +
	 * "<th>Entity</th>" + "<th>Unit</th>" + "<th>Function</th>" +
	 * "<th>Litigation type</th>" + "<th>Case Reference number</th>" +
	 * "<th>Case Title</th>" + "<th>Brief Description</th>" +
	 * "<th>Next Hearing Date </th> " + "<th>Litigation Status</th>" // +
	 * "<th>Result</th>" + "</tr>" + "</thead>" + "<tbody>";
	 * 
	 * List<Object> result = schedulerService.getHearingStageDetailsForReport(); if
	 * (result != null && result.size() != 0) { // Get User emails List<Object>
	 * admin_list = schedulerService.getAllAdminUser(); Iterator<Object> user_list =
	 * admin_list.iterator(); while (user_list.hasNext()) { Object[] user =
	 * (Object[]) user_list.next(); if (user[1] != null)
	 * email.add(user[1].toString()); }
	 * 
	 * // Get email end } if (result != null && result.size() != 0) {
	 * Iterator<Object> iterator = result.iterator(); int SN = 1; while
	 * (iterator.hasNext()) { Object[] objects = (Object[]) iterator.next(); String
	 * next_hearing_date = "NA"; String liti_id = objects[0].toString(); String
	 * orga_name = objects[1].toString(); String loca_name = objects[2].toString();
	 * String dept_name = objects[3].toString(); String liti_type =
	 * litigationService.getLiti_typeById(Integer.parseInt(objects[4].toString()))
	 * .getLiti_type_name(); String case_title = objects[6].toString(); String
	 * case_ref_no = "NA"; if (objects[5] != null) case_ref_no =
	 * objects[5].toString();
	 * 
	 * String brief_desc = "NA"; if (objects[7] != null) brief_desc =
	 * objects[7].toString();
	 * 
	 * if (objects[8] != null) next_hearing_date =
	 * sdfOut.format(sdfIn.parse(objects[8].toString()));
	 * 
	 * String liti_status = objects[9].toString();
	 * 
	 * liti_id_log += liti_id + ","; email_body += "<tr>" + "<td><a href='" +
	 * project_url + "litigationDetails?liti_id=" + liti_id + "'>" + SN +
	 * "</a></td>" + "<td>" + orga_name + "</td>" + "<td>" + loca_name + "</td>" +
	 * "<td>" + dept_name + "</td>" + "<td>" + liti_type + "</td>" + "<td>" +
	 * case_ref_no + "</td>" + "<td>" + case_title + "</td>" + "<td>" + brief_desc +
	 * "</td>" + "<td>" + next_hearing_date + "</td>" + "<td>" + liti_status +
	 * "</td>" + "</tr>"; SN++; } // while end email_body += "</tbody>" +
	 * "</table>"; email_body +=
	 * "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
	 * +
	 * "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
	 * + "</p>" +
	 * "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>" +
	 * "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
	 * 
	 * if (result != null && result.size() != 0) { InternetAddress[] address = new
	 * InternetAddress[email.size()]; for (int i = 0; i < email.size(); i++) { try {
	 * address[i] = new InternetAddress((String) email.get(i)); } catch
	 * (AddressException e) { e.printStackTrace(); } }
	 * sendMailService.sendMail("Litigation- Hearing stage Report ", email_body,
	 * address, address, liti_id_log); }
	 * 
	 * } // End if } catch (Exception e) { e.printStackTrace(); } }
	 */

	// Method created by : Akshay Patkar
	// Method purpose : ExecutedContract Expiry Alert
	// @Scheduled(cron = "30 05 14 * * ?")
	@Scheduled(cron = "00 30 15 * * ?")
	public void getExecutedContractExpiryAlert() {
		try {
			// System.out.println("hii");
			ArrayList<String> email = new ArrayList<String>();
			List<Object> assignId = schedulerService.getAssignPersonExecutedContract();
			if (assignId != null) {

				for (int i = 0; i < assignId.size(); i++) {
					String task_id_send_to_log = "";
					int task_assigned_to_id = Integer.parseInt(assignId.get(i).toString());
					System.out.println("task_assigned_to_id" + task_assigned_to_id);
					List<Object> assignTask = schedulerService
							.getReminderContractExpiryAlertToAssignPerson(task_assigned_to_id);
					Iterator<Object> iterator2 = assignTask.iterator();

					int orga_id = 0;
					// Mail Body Start
					String email_body = "";
					String res_person_mail = "";
					email_body += "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
					email_body += "<h2 style='font-size:18px;'>Dear User,</h2>";
					email_body += "<p style='text-align:justify;width:70%;'>The following Contract is expiring on End date. You are requested to follow the link by clicking on the 'Contract Id' and take neccessary step.</p>"
							+ "<h2 style='font-size:16px;font-weight:bold;'>Contract Details :</h2>";
					email_body += "<table style='width:80%;' border='1'>" + "<thead>"
							+ "<tr style='background:#0B6EC3;color:#fff;'>" + "<th>Contract Id</th>" + "<th>Entity</th>"
							+ "<th>Unit</th>" + "<th>Function</th>" + "<th>Contract Type</th>"
							+ "<th>Contract Start Date</th>" + "<th>Contract End Date</th>" + "<th>Parties</th>"
							+ "<th>Responsible person</th>" + "</tr>" + "</thead>" + "<tbody>";

					// System.out.println(task_id_send_to_log);
					// Mail body header end
					int SN = 1;
					while (iterator2.hasNext()) {
						Object[] objects = (Object[]) iterator2.next();
						String contract_id = objects[0].toString();
						String entity = objects[2].toString();
						String unit = objects[3].toString();
						String function = objects[4].toString();
						String cont_type = objects[1].toString();
						String exec_contract_start_date = "";
						//System.out.println("unpersable error "+ sdfIn.parse(objects[6].toString()));
						
				
						
						if(objects[6]!=null)
							exec_contract_start_date = sdfOut.format(sdfIn.parse(objects[6].toString()));
						
						
						/*
						 * if (objects[6].toString() != null) { exec_contract_start_date =
						 * sdfOut.format(sdfIn.parse(objects[6].toString())); }
						 */
						
						System.out.println("start date"+exec_contract_start_date);
						List<ContractParties> parties = executedContractDao
								.getExecutedContractPartiesByContractId(Integer.parseInt(contract_id));
						String exec_contract_end_date = "";
						
						if(objects[10]!=null)
							exec_contract_end_date = sdfOut.format(sdfIn.parse(objects[10].toString()));
						
						
						/*
						 * if (objects[10].toString() != null) { exec_contract_end_date =
						 * sdfOut.format(sdfIn.parse(objects[10].toString())); }
						 */

						orga_id = Integer.parseInt(objects[11].toString());

						String resp_person = objects[7] + " " + objects[8];
						res_person_mail = objects[9].toString();
						Iterator<ContractParties> itr2 = parties.iterator();
						task_id_send_to_log += contract_id;
						int k = 1;
						String party_name = "";
						while (itr2.hasNext()) {
							ContractParties contractParties = (ContractParties) itr2.next();
							party_name += k + ") " + contractParties.getCont_party_name() + "<br/>";
							k++;
						}
						// System.out.println("party"+party_name);
						email_body += "<tr>" + "<td><a href='" + project_url + "executedContractUpdates?exec_id="
								+ contract_id + "'>" + SN + "</a></td>" + "<td>" + entity + "</td>" + "<td>" + unit
								+ "</td>" + "<td>" + function + "</td>" + "<td>" + cont_type + "</td>" + "<td> "
								+ exec_contract_start_date + "</td>" + "<td> " + exec_contract_end_date + "</td>"
								+ "<td>" + party_name + "</td>" + "<td>" + resp_person + "</td>"

								+ "</tr>";
						SN++;
					}

					email_body += "</tbody>" + "</table>";
					email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
							+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
							+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
							+ "<h2 style='font-size:19px;font-weight:bold;'>Team Legal</h2>" + "</div>";
					// System.out.println(email_body);
					// Email send code start here
					InternetAddress[] address = new InternetAddress[1];
					try {
						address[0] = new InternetAddress(res_person_mail);
						// System.out.println("address[0]: "+ address[0]);
					} catch (AddressException e) {
						e.printStackTrace();
					}

					List<Object> admin_list = schedulerService.getAllEntityHeadByOrgaId(orga_id);
					Iterator<Object> user_list = admin_list.iterator();

					while (user_list.hasNext()) {
						Object[] user = (Object[]) user_list.next();
						if (user[1] != null)
							email.add(user[1].toString());
					}

					InternetAddress[] addressCC = new InternetAddress[email.size()];
					for (int j = 0; j < email.size(); j++) {
						try {
							addressCC[j] = new InternetAddress((String) email.get(j));
							// System.out.println("addressCC: "+ addressCC[j]);
						} catch (AddressException e) {
							e.printStackTrace();
						}
					}
					 System.out.println(email_body);
					 System.out.println("address" + address + "addressCC" + addressCC);
					sendMailService.sendMail("Contract Expiry Alert", email_body, address, addressCC,
							task_id_send_to_log);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
