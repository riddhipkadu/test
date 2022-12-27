package lcmt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lcmt.dao.SendMailDao;
import lcmt.service.ExternalCounselService;
import lcmt.service.SchedulerService;
import lcmt.service.SendMailService;
import lcmt.service.UserService;
import lcmt.service.UtilitiesService;

@Service("sendMailService")
public class SendMailServiceImpl implements SendMailService {

	private @Value("#{config['mail_user_name'] ?: 'null'}") String username;
	private @Value("#{config['mail_password'] ?: 'null'}") String password;
	private @Value("#{config['mail_smtp_host'] ?: 'null'}") String hostName;
	private @Value("#{config['mail_smtp_port'] ?: 'null'}") String portNo;
	private @Value("#{config['mail_from'] ?: 'null'}") String mailFrom;
	private @Value("#{config['project_url'] ?: 'null'}") String project_url;

	@Autowired
	SendMailDao sendMailDao;
	@Autowired
	UtilitiesService utilitiesService;
	@Autowired
	ExternalCounselService externalCounselService;
	@Autowired
	SchedulerService schedulerService;
	@Autowired
	UserService userService;

	SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

	// Method created by : Tejashri Zurunge
	// Method created by : Tejashri Zurunge
	// Method purpose : send mail to assign person while creating new query
	@Override
	public void sendQueryAssignMailToResponsiblePerson(int query_id, HttpSession session) {
		try {
			List<Object> res = sendMailDao.getQueryDetailsByQueryId(query_id);
			if (res != null) {
				String email_body = "";
				String res_person_mail = "";
				email_body += "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
				email_body += "<h2 style='font-size:18px;'>Dear User,</h2>";
				email_body += "<p style='text-align:justify;width:70%;'>The following query is required to be attended on or before the due date/s. You are requested to follow the link by clicking on the 'Query Id' and reply the query by login to system.</p>"
						+ "<h2 style='font-size:16px;font-weight:bold;'>Query Details :</h2>";
				email_body += "<table style='width:80%;' border='1'>" 
						+ "<thead>"
						+ "<tr style='background:#0B6EC3;color:#fff;'>" 
						+ "<th>Query Id</th>" 
						+ "<th>Entity</th>"
						+ "<th>Unit</th>"
						+ "<th>Function</th>" 
						+ "<th>Query From</th>" 
						+ "<th>Query</th>"
						+ "<th>Query Date</th>" 
						+ "<th>Assigned By</th>"
						+ "<th>Assigned To</th>" 
						+ "<th>Reminder Date</th>"
						+ "<th>Due Date</th>" 
						+ "</tr>" 
						+ "</thead>" 
						+ "<tbody>";
				String quer_id = "";
				Iterator<Object> iterator = res.iterator();
				while (iterator.hasNext()) {
					Object[] objects = (Object[]) iterator.next();
					quer_id = objects[10].toString();
					String entity = objects[0].toString();
					String unit = objects[1].toString();
					String function = objects[2].toString();
					String res_person = objects[3].toString()+' '+objects[4].toString();
					res_person_mail = objects[5].toString();
					String query_from = objects[6].toString();
					String query_dated = sdfOut.format(sdfIn.parse(objects[7].toString()));
					String query_reply_date = sdfOut.format(sdfIn.parse(objects[8].toString()));
					String query_reminder_date = sdfOut.format(sdfIn.parse(objects[9].toString()));
					String query_description = objects[11].toString();
					String assigned_by = session.getAttribute("fullName").toString(); 
					email_body += "<tr>" 
							+ "<td><a href='" + project_url + "queryDetails?query_id=" + quer_id + "'>"	+ quer_id + "</a></td>" 
							+ "<td>" + entity + "</td>" 
							+ "<td>" + unit + "</td>" 
							+ "<td>" + function + "</td>" 
							+ "<td>" + query_from + "</td>" 
							+ "<td>" + query_description + "</td>"
							+ "<td>" + query_dated + "</td>"
							+ "<td>" + assigned_by + "</td>" 
							+ "<td>" + res_person + "</td>" 
							+ "<td>" + query_reminder_date + "</td>" 
							+ "<td>" + query_reply_date + "</td>" 
							+ "</tr>";

				}
				email_body += "</tbody>" + "</table>";
				email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
						+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
						+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
						+ "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";

				// Email send code start here
				InternetAddress[] address = new InternetAddress[1];
				try {
					address[0] = new InternetAddress(res_person_mail);
				} catch (AddressException e) {
					e.printStackTrace();
				}
				
				//get mail ids of all admin
				ArrayList<String> email = new ArrayList<String>(); 
				List<Object> mail_idCC = schedulerService.getAllAdminUser();
				Iterator<Object> iterator1 = mail_idCC.iterator();
				while (iterator1.hasNext()) {
					Object object[] = (Object[]) iterator1.next();
					if(object[1] != null)
					email.add(object[1].toString());
				}
				
				// Email send code start here
				InternetAddress[] addressCC = new InternetAddress[email.size()];
				try {
					for (int i = 0; i < email.size(); i++) {
						addressCC[i] = new InternetAddress(email.get(i));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}
				
				sendMail("Query Assigned", email_body, address, addressCC, quer_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method created by : Tejashri Zurunge
	// Method purpose : send mail to assign person while creating new litigation
	@Override
	public void sendLitigationHandledMailToInternallyHandledPerson(int liti_id, HttpSession session) {
		try {
			
			
			
			List<Object> res = sendMailDao.getLitigationDetailsByLitigationId(liti_id);
			if (res != null) {
				String email_body = "";
				//String intern_handle_person_mail = "";
				email_body += "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
				email_body += "<h2 style='font-size:18px;'>Dear User,</h2>";
				email_body += "<p style='text-align:justify;width:70%;'>The following litigation is assigned. You are requested to follow the link by clicking on the 'LitigationID'.</p>"
						+ "<h2 style='font-size:16px;font-weight:bold;'>Litigation Details :</h2>";
				email_body += "<table style='width:80%;' border='1'>" 
						+ "<thead>"
						+ "<tr style='background:#0B6EC3;color:#fff;'>"
						+ "<th>Litigation ID</th>"
						+ "<th>By/Against Company</th>" 
						+ "<th>Category</th>" 
						+ "<th>Case No.</th>"
						+ "<th>Case Filling Date</th>" 
						+ "<th>Company External Counsel</th>"
						+ "<th>Assigned By</th>"
						//+ "<th>Matter Handled By</th>" 
						+ "<th>Opposite Party</th>" 
						+ "<th>Court/Tribunal</th>"
						+ "<th>Amount Involved</th>" 
						+ "<th>Brief Description</th>" 
						+ "</tr>" + "</thead>" + "<tbody>";
				String litigation_id = "";
				Iterator<Object> iterator = res.iterator();
				while (iterator.hasNext()) {
					Object[] objects = (Object[]) iterator.next();
					litigation_id = objects[0].toString();
					String liti_client_liti_id = objects[12].toString();
					String liti_against_by_id = objects[1].toString();
					String liti_type_name = objects[2].toString();
					String liti_case_reference_no = objects[3].toString();
					String liti_case_filing_date = sdfOut.format(sdfIn.parse(objects[4].toString()));
					String exte_coun_name ="NA";
					if(Integer.parseInt(objects[5].toString()) != 0){
						exte_coun_name = externalCounselService.getExte_CounById(Integer.parseInt(objects[5].toString())).getExte_coun_name();
					}
					
					//String liti_internally_handled_name = objects[6].toString() +' '+objects[7].toString();
					//intern_handle_person_mail = objects[8].toString();
				    String liti_opposite_party = objects[6].toString();
					String liti_court = objects[8].toString();
					String liti_amount_involved = objects[9].toString();
					String liti_brief_description = objects[10].toString();
					String assigned_by = session.getAttribute("fullName").toString();
					email_body += "<tr>" 
							+ "<td><a href='" + project_url + "litigationDetails?liti_id=" + litigation_id	+ "'>" + liti_client_liti_id + "</a></td>" 
							+ "<td>" + liti_against_by_id +" Company </td>" 
							+ "<td>" + liti_type_name + "</td>" 	
							+ "<td>" + liti_case_reference_no + "</td>" 
							+ "<td>" + liti_case_filing_date + "</td>" 
							+ "<td>" + exte_coun_name + "</td>" 
							+ "<td>" + assigned_by + "</td>" 
							//+ "<td>" + liti_internally_handled_name + "</td>" 
							+ "<td>" + liti_opposite_party + "</td>" 
							+ "<td>" + liti_court + "</td>" 
							+ "<td>" + liti_amount_involved + "</td>" 
							+ "<td>" + liti_brief_description + "</td>" 
							+ "</tr>";
				}
				email_body += "</tbody>" + "</table>";
				email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
						+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
						+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
						+ "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
				//System.out.println(email_body);
				// Email send code start here
				/*InternetAddress[] address = new InternetAddress[1];
				try {
					address[0] = new InternetAddress(intern_handle_person_mail);
				} catch (AddressException e) {
					e.printStackTrace();
				}*/
				
				//get mail ids of all admin
				ArrayList<String> email = new ArrayList<String>(); 
				List<Object> mail_idCC = schedulerService.getAllAdminUser();
				Iterator<Object> iterator1 = mail_idCC.iterator();
				while (iterator1.hasNext()) {
					Object object[] = (Object[]) iterator1.next();
					if(object[1] != null)
					email.add(object[1].toString());
				}
				
				// Email send code start here
				InternetAddress[] addressCC = new InternetAddress[email.size()];
				try {
					for (int i = 0; i < email.size(); i++) {
						addressCC[i] = new InternetAddress(email.get(i));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}
				
				System.out.println("mail  " +email_body);
				sendMail("Litigation Assigned", email_body, addressCC, addressCC, litigation_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method created by : Tejashri Zurunge
	// Method purpose : sending mail
	@Override
	public void sendMail(String email_subject, String email_body, InternetAddress[] addresses, InternetAddress[] addressCC ,String id) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", hostName);
		props.put("mail.smtp.port", portNo);

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailFrom));
			message.setRecipients(Message.RecipientType.TO, addresses);
			message.setRecipients(Message.RecipientType.CC, addressCC);
			message.setSubject(email_subject);
			message.setContent(email_body, "text/html; charset=utf-8");
			Transport.send(message);
			String mailCC = null;
			for (int j = 0; j < addressCC.length; j++) {
			mailCC += String.valueOf(addressCC[j]);
			}
			for (int i = 0; i < addresses.length; i++) {
				utilitiesService.addMailToLog(String.valueOf(addresses[i]),mailCC, email_subject, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method created by : Tejashri Zurunge
	// Method purpose : send mail to assign person while creating new
	// pre-execution contract
	@Override
	public void sendPreExecutionAssignMailToResponsiblePerson(int cont_id, HttpSession session) {
		try {
			List<Object> res = sendMailDao.getPreExecutionDetailsByContractId(cont_id);
			if (res != null) {
				String email_body = "";
				String assign_person_mail = "";
				email_body += "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
				email_body += "<h2 style='font-size:18px;'>Dear User,</h2>";
				email_body += "<p style='text-align:justify;width:70%;'>The following Pre-execution contract is assigned to you. You are requested to follow the link by clicking on the 'Contract ID'.</p>"
						+ "<h2 style='font-size:16px;font-weight:bold;'>Pre Execution Details :</h2>";
				email_body += "<table style='width:80%;' border='1'>" + "<thead>"
						+ "<tr style='background:#0B6EC3;color:#fff;'>"
						+ "<th>Contract ID</th>"
						+ "<th>Entity</th>"
						+ "<th>Unit</th>" 
						+ "<th>Function</th>" 
						+ "<th>Name of agreement</th>" 
						+ "<th>Requested on</th>"
						+ "<th>Expected date of closure</th>" 
						+ "<th>Requested by</th>"
						//+ "<th>Assigned by</th>"
						+ "<th>Assigned to</th>" 
						+ "<th>Targeted date</th>" 
						+ "</tr>"
						+ "</thead>" + "<tbody>";
				String contract_id = "";
				Iterator<Object> iterator = res.iterator();
				while (iterator.hasNext()) {
					Object[] objects = (Object[]) iterator.next();
					contract_id = objects[11].toString();
					String orga_name = objects[0].toString();
					String loca_name = objects[1].toString();
					String dept_name = objects[2].toString();
					String cont_agreement_name = objects[3].toString();
					String cont_requested_date = "";
					String cont_expected_date = "";
					
					if(objects[5]!=null)
					 cont_requested_date = sdfOut.format(sdfIn.parse(objects[5].toString()));
					if(objects[6]!=null)
					  cont_expected_date = sdfOut.format(sdfIn.parse(objects[6].toString()));
					
					String user_requested_by_fullname = userService.getUserFullNameById(Integer.parseInt(objects[4].toString()));
					String user_responsible_fullname = objects[7].toString()+" "+objects[8].toString();
					assign_person_mail = objects[9].toString();

					String cont_targetted_date = sdfOut.format(sdfIn.parse(objects[10].toString()));
					//String assigned_by = session.getAttribute("fullName").toString();
					email_body += "<tr>" + "<td><a href='" + project_url + "updateTab?cont_id=" + contract_id + "'>"+ contract_id + "</a></td>" 
							+ "<td>" + orga_name + "</td>" 
							+ "<td>" + loca_name + "</td>"
							+ "<td>" + dept_name + "</td>" 
							+ "<td>" + cont_agreement_name + "</td>" 
							+ "<td>" + cont_requested_date + "</td>" 
							+ "<td>" + cont_expected_date + "</td>" 
							+ "<td>" + user_requested_by_fullname + "</td>" 
							//+ "<td>" + assigned_by + "</td>" 
							+ "<td>" + user_responsible_fullname + "</td>"
							+ "<td>" + cont_targetted_date + "</td>" 
							+ "</tr>";
				}
				email_body += "</tbody>" + "</table>";
				email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
						+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
						+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
						+ "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
				//System.out.println(email_body);
				// Email send code start here
				InternetAddress[] address = new InternetAddress[1];
				try {
					address[0] = new InternetAddress(assign_person_mail);
				} catch (AddressException e) {
					e.printStackTrace();
				}
				
				//get mail ids of all admin
				ArrayList<String> email = new ArrayList<String>(); 
				List<Object> mail_idCC = schedulerService.getAllAdminUser();
				Iterator<Object> iterator1 = mail_idCC.iterator();
				while (iterator1.hasNext()) {
					Object object[] = (Object[]) iterator1.next();
					if(object[1] != null)
					email.add(object[1].toString());
				}
				
				// Email send code start here
				InternetAddress[] addressCC = new InternetAddress[email.size()];
				try {
					for (int i = 0; i < email.size(); i++) {
						addressCC[i] = new InternetAddress(email.get(i));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}
				
				sendMail("Pre-execution Assigned", email_body, address,addressCC, contract_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method created by : Tejashri Zurunge
	// Method purpose : send mail to assign person while creating new legal notice
	@Override
	public void sendLegalNoticeMailToAssignPerson(int lega_noti_id, HttpSession session) {
		try {
			List<Object> res = sendMailDao.getLegalNoticeDetailsByLegalNoticeId(lega_noti_id);
			if (res != null) {
				String email_body = "";
				String resp_person_mail = "";
				email_body += "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
				email_body += "<h2 style='font-size:18px;'>Dear User,</h2>";
				email_body += "<p style='text-align:justify;width:70%;'>The following Legal Notice is assigned to you. You are requested to follow the link by clicking on the 'Legal Notice Id'.</p>"
						+ "<h2 style='font-size:16px;font-weight:bold;'>Legal notice details :</h2>";
				email_body += "<table style='width:80%;' border='1'>" + "<thead>"
						+ "<tr style='background:#0B6EC3;color:#fff;'>" 
						+ "<th>Legal notice ID</th>" 
						+ "<th>Entity</th>"
						+ "<th>Unit</th>" 
						+ "<th>Function</th>" 
						+ "<th>Category</th>"
						+ "<th>Notice date</th>" 
						+ "<th>Received date</th>" 
						+ "<th>Deadline date</th>"
						+ "<th>Assigned by</th>" 
						+ "<th>Responsible person</th>" 
						+ "<th>Reminder date</th>"
						+ "</tr>"
						+ "</thead>" + "<tbody>";
				String legal_notice_id = "";
				Iterator<Object> iterator = res.iterator();
				while (iterator.hasNext()) {
					Object[] objects = (Object[]) iterator.next();
					legal_notice_id = objects[4].toString();
					String entity = objects[0].toString();
					String unit = objects[1].toString();
					String function = objects[2].toString();
					String category = objects[3].toString();
					String notice_date = "NA";
					String received_date = "NA";
					String deadline_date = "NA";
					String reminder_date = "NA";
					if(objects[5]!=null)
					 notice_date = sdfOut.format(sdfIn.parse(objects[5].toString()));
					
					if(objects[6]!=null)
					 received_date = sdfOut.format(sdfIn.parse(objects[6].toString()));
					
					if(objects[7]!=null)
					deadline_date = sdfOut.format(sdfIn.parse(objects[7].toString()));
					
					String resp_person = objects[8].toString()+" "+objects[9].toString();
					resp_person_mail = objects[10].toString();
					if(objects[11] != null)
					reminder_date = sdfOut.format(sdfIn.parse(objects[11].toString()));
					
					String assigned_by = session.getAttribute("fullName").toString(); 

					email_body += "<tr>" 
							+ "<td><a href='" + project_url + "legalNoticeUpdate?lega_noti_id=" + legal_notice_id+ "'>" + legal_notice_id + "</a></td>" 
							+ "<td>" + entity + "</td>" 	
							+ "<td>" + unit + "</td>"
							+ "<td>" + function + "</td>" 
							+ "<td>" + category + "</td>" 
							+ "<td> "+ notice_date+ "</td>" 
							+ "<td>" + received_date + "</td>" 
							+ "<td>" + deadline_date + "</td>"
							+ "<td>" + assigned_by + "</td>"
							+ "<td>" + resp_person + "</td>"
							+ "<td>" + reminder_date + "</td>"
							+ "</tr>";
				}
				email_body += "</tbody>" + "</table>";
				email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
						+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
						+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
						+ "<h2 style='font-size:19px;font-weight:bold;'>Team LexCare</h2>" + "</div>";
				//System.out.println(email_body);
				// Email send code start here
				InternetAddress[] address = new InternetAddress[1];
				try {
					address[0] = new InternetAddress(resp_person_mail);
				} catch (AddressException e) {
					e.printStackTrace();
				}
				
				//get mail ids of all admin
				ArrayList<String> email = new ArrayList<String>(); 
				List<Object> mail_idCC = schedulerService.getAllAdminUser();
				Iterator<Object> iterator1 = mail_idCC.iterator();
				while (iterator1.hasNext()) {
					Object object[] = (Object[]) iterator1.next();
					if(object[1] != null)
					email.add(object[1].toString());
				}
				
				// Email send code start here
				InternetAddress[] addressCC = new InternetAddress[email.size()];
				try {
					for (int i = 0; i < email.size(); i++) {
						addressCC[i] = new InternetAddress(email.get(i));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}
				sendMail("Legal notice Assigned", email_body, address , addressCC, legal_notice_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Method created by : Pranjali Kawale
		// Method purpose : send mail to assign person while creating new Sarfaesi Act
		@Override
		public void sendSarfaesiActHandledMailToInternallyHandledPerson(int sarfact_id, HttpSession session) {
			// TODO Auto-generated method stub
			try {
				//System.out.println("######");
	          List<Object> res = sendMailDao.getSarfaesiActDetailsBySarfActId(sarfact_id);
				//System.out.println("sarfid" +sarfact_id);
				if (res != null) {
					String email_body = "";
					String sarf_Executor="";
					//String intern_handle_person_mail = "";
					email_body += "<div style='margin:0 auto;width:100%;height:auto;padding:20px;'>";
					email_body += "<h2 style='font-size:18px;'>Dear User,</h2>";
					email_body += "<p style='text-align:justify;width:70%;'>The following Sarfaesi Act is added. You are requested to follow the link by clicking on the 'Sarfaesi Act Id'.</p>"
							+ "<h2 style='font-size:16px;font-weight:bold;'>Sarfaesi Act Details :</h2>";	
					email_body += "<table style='width:100%;' border='1'>" 
							+ "<display:column property='class' title='class' style='width : 2cm;' /> "
							+ "<thead>"
							+ "<tr style='background:#0B6EC3;color:#fff;'>"
							+ "<th>Sarfaesi Act ID</th>"
							+ "<th>Loan Number</th>" 
							+ "<th>Borrower Name</th>"
							+ "<th>Security Type</th>" 
							+ "<th>Type of Security Interest</th>"
							+ "<th>Executor</th>" 
							/*+ "<th>ARC Manager </th>"*/
							+ "<th>NPA Date</th>"
							+ "<th>Case Filling Date</th>"
							+ "<th>Notice Issue Date</th>"
							+ "</tr>" + "</thead>" + "<tbody>";
					String sarfAct_id = "";
					String sarf_npa_date = "";
					String sarf_filling_date = "";
					String sarf_NotiIssue_date = "";
					
					Iterator<Object> iterator = res.iterator();
					while (iterator.hasNext()) {
						Object[] objects = (Object[]) iterator.next();
						sarfAct_id = objects[0].toString();
						String sarf_loan_no = objects[1].toString();
						String sarf_borrower = objects[2].toString();
						String sarf_security_type = objects[3].toString();
						String	sarf_security_Interest = objects[4].toString();
						
					if(objects[5] == null)
						sarf_npa_date = "NA";	
					else
						sarf_npa_date = sdfOut.format(sdfIn.parse(objects[5].toString()));
						
					if(objects[6] == null)
						sarf_filling_date = "NA";	
					else
						sarf_filling_date = sdfOut.format(sdfIn.parse(objects[6].toString()));
						
					if(objects[7] == null)
						sarf_NotiIssue_date = "NA";	
					else
						sarf_NotiIssue_date = sdfOut.format(sdfIn.parse(objects[7].toString()));
					
					String	sarf_Executor_id = userService.getUserFullNameById(Integer.parseInt(objects[8].toString()));
					//System.out.println(sarf_Executor_id);
					//String	sarf_manager_id = userService.getUserFullNameById(Integer.parseInt(objects[9].toString()));
					//System.out.println(sarf_manager_id);
					 sarf_Executor = objects[10].toString();
						
						email_body += "<tr>" 
								+ "<td><a href='" + project_url + "listSarfaesiAct?sarf_act_id=" + sarfAct_id	+ "'>" + sarfAct_id + "</a></td>" 
								+ "<td>" + sarf_loan_no + "</td>" 	
								+ "<td>" + sarf_borrower + "</td>" 
								+ "<td>" + sarf_security_type + "</td>" 
								+ "<td>" + sarf_security_Interest + "</td>" 
								+ "<td>" + sarf_Executor_id + "</td>" 
								//+ "<td>" + sarf_manager_id + "</td>" 
								+ "<td>" + sarf_npa_date + "</td>" 
								+ "<td>" + sarf_filling_date + "</td>" 
								+ "<td>" + sarf_NotiIssue_date + "</td>" ;
								 email_body	 += "</tr>";
								 
				email_body += "</tbody>" + "</table><br/>";
					}
					email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
							+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
							+ "<h2 style='font-size:19px;font-weight:bold;'>Team Legal</h2>" + "</div>";
					//System.out.println(email_body);
					// Email send code start here
					InternetAddress[] address = new InternetAddress[1];
					try {
						address[0] = new InternetAddress(sarf_Executor);
					} catch (AddressException e) {
						e.printStackTrace();
					}
					
					//get mail ids of all admin
					ArrayList<String> email = new ArrayList<String>(); 
					List<Object> mail_idCC = schedulerService.getAllAdminUser();
					Iterator<Object> iterator1 = mail_idCC.iterator();
					while (iterator1.hasNext()) {
						Object object[] = (Object[]) iterator1.next();
						if(object[1] != null)
						email.add(object[1].toString());
					}
					
					// Email send code start here
					InternetAddress[] addressCC = new InternetAddress[email.size()];
					try {
						for (int i = 0; i < email.size(); i++) {
							addressCC[i] = new InternetAddress(email.get(i));
						}
					} catch (AddressException e) {
						e.printStackTrace();
					}
					sendMail("Sarfaesi Act Added: " ,email_body,address,addressCC,sarfAct_id);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}