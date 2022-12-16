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
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lcmt.dao.ContractDao;
import lcmt.dao.ExecutedContractDao;
import lcmt.dao.RequestByPOCDao;
import lcmt.dao.SendMailDao;
import lcmt.domain.ActivityLogs;
import lcmt.domain.Contract;
import lcmt.domain.ContractContractType;
import lcmt.domain.ContractDocuments;
import lcmt.domain.ContractHistory;
import lcmt.domain.ContractHistoryDocuments;
import lcmt.domain.ContractHistoryReference;
import lcmt.domain.ContractParties;
import lcmt.domain.ContractReference;
import lcmt.domain.ContractRequest;
import lcmt.domain.PocNegotiationDocument;
import lcmt.domain.PocStatusForNegotiation;
import lcmt.domain.RequestDocument;
import lcmt.service.ContractService;
import lcmt.service.ContractTypeService;
import lcmt.service.RequestByPOCService;
import lcmt.service.SchedulerService;
import lcmt.service.SendMailService;
import lcmt.service.UserService;
import lcmt.service.UtilitiesService;

/*
 * Author: Mahesh Kharote
 * Date: 27/09/2016
 * Updated By:
 * Updated Date:
 * 
 * */

@Service(value = "contractForExecutionService")
public class ContractServiceImpl implements ContractService {

	@Autowired
	ContractDao contractDao;
	@Autowired
	UtilitiesService utilitieservice;
	@Autowired
	HttpSession httpSession;
	@Autowired
	SendMailService sendMailService;
	@Autowired
	ExecutedContractDao executedContractDao;
	@Autowired
	UserService userService;
	@Autowired
	ContractTypeService contractService;
	@Autowired
	SendMailDao sendMailDao;
	@Autowired
	SchedulerService schedulerService;
	@Autowired
	RequestByPOCService requestByPOCService;
	@Autowired
	RequestByPOCDao requestByPOCDao;
	private @Value("#{config['project_name'] ?: 'null'}") String project_name;
	private @Value("#{config['project_url'] ?: 'null'}") String project_url;
	private @Value("#{config['mail_user_name'] ?: 'null'}") String username;
	private @Value("#{config['mail_password'] ?: 'null'}") String password;
	private @Value("#{config['mail_smtp_host'] ?: 'null'}") String hostName;
	private @Value("#{config['mail_smtp_port'] ?: 'null'}") String portNo;
	private @Value("#{config['mail_from'] ?: 'null'}") String mailFrom;

	// Method Created : Mahesh Kharote
	// Method Purpose : Save Contract For Execution page
	@Override
	public int persist(Contract contract, ArrayList<MultipartFile> term_doc, ArrayList<MultipartFile> contract_doc,
			HttpSession session,ArrayList<String> additional_parties, ArrayList<String> list_type_of_contracts,String status) {
		try {
			Date curr_date = utilitieservice.getCurrentDate();
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			contract.setCont_added_by(user_id);
			contract.setCont_created_at(curr_date);
			contract.setCont_req_id(0);
			if(status.equals("Pending"))
			   contract.setCont_status("Pending");
			else
				contract.setCont_status(status);
			
			int contract_id = contractDao.saveContract(contract);
			
			// Save Parties Details
			for (int i = 0; i < additional_parties.size(); i++) {
				ContractParties contractParties = new ContractParties();
				contractParties.setCont_party_contract_id(contract_id);
				contractParties.setCont_party_name(additional_parties.get(i));
				contractParties.setCont_party_type(1);
				contractParties.setCont_party_created_at(curr_date);
				contractParties.setCont_party_added_by(user_id);
				executedContractDao.savePartyDetails(contractParties);
				// System.out.println("Party "+additional_parties.get(i));
			}
			if(list_type_of_contracts!=null){
			// Save Multiple contract type Details
			for (int i = 0; i < list_type_of_contracts.size(); i++) {
				ContractContractType ContractType = new ContractContractType();
				ContractType.setCont_contract_id(contract_id);
				ContractType.setCont_contract_type_id(list_type_of_contracts.get(i));
				ContractType.setCont_contract_type(1);
				ContractType.setCont_contract_type_created_at(curr_date);
				ContractType.setCont_contract_type_added_by(user_id);
				contractDao.saveContractTypeList(ContractType);
			}
		   }
			if(status.equals("Pending")){
			sendMailService.sendPreExecutionAssignMailToResponsiblePerson(contract_id, session);
			// persist contract log
						ActivityLogs logs = new ActivityLogs();
						logs.setLog_activity_id(contract_id);
						logs.setLog_assinged_to_id(Integer.parseInt(contract.getCont_responsible_user_id()));
						logs.setLog_activity("Add");
						logs.setLog_related_to("Pre Execution contract");
						logs.setLog_sub_activity("Add Pre Execution contract");
						logs.setLog_related_name(contract.getCont_agreement_name());
						logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
						logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
						contractDao.persist(logs);
			}
			// Upload Documents terms
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueDoc = contractDao.getLastGenerateValueForContract(contract_id);
			for (int i = 0; i < term_doc.size(); i++) {
				MultipartFile file = term_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "PreExecutionContractDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedContractDoc_" + contract_id + "_" + lastGeneratedValueDoc + "."
							+ file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					ContractDocuments documents = new ContractDocuments();

					documents.setCdoc_contract_id(contract_id);
					documents.setCdoc_original_file_name(originalFileName);
					documents.setCdoc_generated_file_name(
							"C:/"+project_name+"/Documents/PreExecutionContractDocuments/" + generatedFileName);
					documents.setCdoc_last_generated_value_for_contract_id(lastGeneratedValueDoc);
					documents.setCdoc_created_at(utilitieservice.getCurrentDateWithTime());
					documents.setCdoc_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
					documents.setCdoc_document_type("Term");
					contractDao.persist(documents);
				}
			}

			// Upload Documents Contract
			lastGeneratedValueDoc = contractDao.getLastGenerateValueForContract(contract_id);
			for (int i = 0; i < contract_doc.size(); i++) {
				MultipartFile file = contract_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "PreExecutionContractDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedContractDoc_" + contract_id + "_" + lastGeneratedValueDoc + "."
							+ file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					ContractDocuments documents = new ContractDocuments();

					documents.setCdoc_contract_id(contract_id);
					documents.setCdoc_original_file_name(originalFileName);
					documents.setCdoc_generated_file_name(
							"C:/"+project_name+"/Documents/PreExecutionContractDocuments/" + generatedFileName);
					documents.setCdoc_last_generated_value_for_contract_id(lastGeneratedValueDoc);
					documents.setCdoc_created_at(utilitieservice.getCurrentDateWithTime());
					documents.setCdoc_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
					documents.setCdoc_document_type("Contract");
					contractDao.persist(documents);
				}
			}
			return contract_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Mahesh Kharote
	// Method Purpose : Get Contract details For Execution page list
	@Override
	public List<ContractReference> getJoinedContractDetails(HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

			List<ContractReference> allContractsDetailsSend = new ArrayList<>();

			List<Object> allContractsDetails = contractDao.getJoinedContractDetails(session);
			Iterator<Object> itr = allContractsDetails.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
				ContractReference contractReference = new ContractReference();

				contractReference.setOrga_name(object[0].toString());
				contractReference.setLoca_name(object[1].toString());
				contractReference.setDept_name(object[2].toString());
				contractReference.setCont_agreement_name(object[3].toString());
				if(object[4]!=null)
				    contractReference.setUser_requested_by_fullname(userService.getUserFullNameById(Integer.parseInt(object[4].toString())));
				else
					contractReference.setUser_requested_by_fullname("NA");
				
				if(object[5]!=null)
				   contractReference.setCont_requested_date(sdfOut.format(sdfIn.parse(object[5].toString())));
				else
					contractReference.setCont_requested_date("NA");
				
				if(object[6]!=null)
				   contractReference.setCont_expected_date(sdfOut.format(sdfIn.parse(object[6].toString())));
				else
					contractReference.setCont_expected_date("NA");
				
				contractReference.setCont_parties(contractDao.getContractPartiesByContractId(Integer.parseInt(object[10].toString())));
				if(object[7]!=null)
				     contractReference.setUser_responsible_fullname(object[7].toString() +' '+ object[8].toString());
				else
					contractReference.setUser_responsible_fullname("NA");
				
				if(object[9]!=null)
				    contractReference.setCont_targetted_date(sdfOut.format(sdfIn.parse(object[9].toString())));
				else
					contractReference.setCont_targetted_date("NA");
				
				contractReference.setCont_id(Integer.parseInt(object[10].toString()));
				
				if (object[11].toString().equals("Sent_For_Review"))
					contractReference.setCont_status("Sent For Review");
				else
					contractReference.setCont_status(object[11].toString());

				contractReference.setCont_added_by(Integer.parseInt(object[12].toString()));

				allContractsDetailsSend.add(contractReference);
			}
			return allContractsDetailsSend;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Mahesh Kharote
	// Method Purpose : Get Contract details For Execution page list
	@Override
	public ContractReference getJoinedContractDetailsById(int cont_id) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			Object object[] = contractDao.getJoinedContractDetailsById(cont_id);

			ContractReference contractReference = new ContractReference();

			contractReference.setOrga_name(object[0].toString());
			contractReference.setLoca_name(object[1].toString());
			contractReference.setDept_name(object[2].toString());
			contractReference.setCont_agreement_name(object[3].toString());
			if(object[4]!=null)
			    contractReference.setCont_major_clause(object[4].toString());
			else
				contractReference.setCont_major_clause("NA");
			
			if(object[5]!=null)
			    contractReference.setCont_requested_date(sdfOut.format(sdfIn.parse(object[5].toString())));
			else
				contractReference.setCont_requested_date("NA");
			
			if(object[6]!=null)
			contractReference.setCont_expected_date(sdfOut.format(sdfIn.parse(object[6].toString())));
			else
			contractReference.setCont_expected_date("NA");
			
			contractReference.setCont_type_list(contractDao.getContractTypeByContractId(Integer.parseInt(object[10].toString())));
			
			contractReference.setCont_parties(contractDao.getContractPartiesByContractId(Integer.parseInt(object[10].toString())));
			
			if(object[7]!=null)
			contractReference.setUser_responsible_fullname(object[7].toString()+' '+object[8].toString());
			else
			contractReference.setUser_responsible_fullname("NA");
			
			if(object[9]!=null)
			contractReference.setCont_targetted_date(sdfOut.format(sdfIn.parse(object[9].toString())));
			else
			contractReference.setCont_targetted_date("NA");
			
			contractReference.setCont_id(Integer.parseInt(object[10].toString()));
			contractReference.setCont_status(object[11].toString());
			contractReference.setCont_orga_id(Integer.parseInt(object[12].toString()));
			contractReference.setCont_loca_id(Integer.parseInt(object[13].toString()));
			contractReference.setCont_dept_id(Integer.parseInt(object[14].toString()));
			if(object[15]!=null)
			   contractReference.setCont_responsible_user_id(object[15].toString());
			else
				contractReference.setCont_responsible_user_id("NA");
			
			if(object[5]!=null)
			   contractReference.setCont_requested_date_date(sdfIn.parse(object[5].toString()));
			else
				contractReference.setCont_requested_date_date(null);
			
			if(object[6]!=null)
			   contractReference.setCont_expected_date_date(sdfIn.parse(object[6].toString()));
			else
				contractReference.setCont_expected_date_date(null);
			
			if(object[9]!=null)
			   contractReference.setCont_targetted_date_date(sdfIn.parse(object[9].toString()));
			else
				contractReference.setCont_targetted_date_date(null);
			
			if(object[16]!=null)
			   contractReference.setCont_purpose(object[16].toString());
			else
				contractReference.setCont_purpose("NA");
			if(object[17] != null)
			contractReference.setCont_criticality(object[17].toString());
			else
			contractReference.setCont_criticality("");
			
			contractReference
					.setCont_type_list_name(contractDao.getContractTypeById(Integer.parseInt(object[10].toString())));
			if(object[18] != null)
			contractReference.setCont_nature(object[18].toString());
			else
				contractReference.setCont_nature("");
			
			if(object[19] != null)
			contractReference.setCont_term(object[19].toString());
			else
				contractReference.setCont_term("");
			
			if(object[20] != null)
			contractReference.setCont_payment(object[20].toString());
			else
				contractReference.setCont_payment("");
			
			if(object[21] != null)
			contractReference.setCont_surviving_clause(object[21].toString());
			else
				contractReference.setCont_surviving_clause("");
			
			if(object[22] != null)
			contractReference.setUser_requested_by_fullname(object[22].toString()+" "+object[23].toString());
			else
				contractReference.setCont_term("NA");
			
			contractReference.setCont_requested_by_user_id(Integer.parseInt(object[24].toString()));
			if(object[25] != null){
			contractReference.setCont_start_date(sdfIn.parse(object[25].toString()));
			contractReference.setCont_start_date_name(sdfOut.format(sdfIn.parse(object[25].toString())));
			}
			if(object[26] != null){
			contractReference.setCont_end_date(sdfIn.parse(object[26].toString()));
			contractReference.setCont_end_date_name(sdfOut.format(sdfIn.parse(object[26].toString())));
			}
			if(object[27] != null)
			contractReference.setCont_damages(object[27].toString());
			
			if(object[28] != null)
			contractReference.setCont_instructions(object[28].toString());
			if(object[29] != null){
			contractReference.setCont_reminder_date(sdfIn.parse(object[29].toString()));
			contractReference.setCont_reminder_date_name(sdfOut.format(sdfIn.parse(object[29].toString())));
			contractReference.setCont_req_id(Integer.parseInt(object[30].toString()));
			}
			return contractReference;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Mahesh Kharote
	// Method Purpose : Get Contract details For Execution page list
	@Override
	public void persistContractHistory(ContractHistory contractHistory, ArrayList<MultipartFile> contract_doc,ArrayList<String> chst_contract_type,
			HttpSession session, String adminStatus) {
		try {
			contractHistory.setChst_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			contractHistory.setChst_created_at(utilitieservice.getCurrentDateWithTime());
			int last_hst_id = contractDao.persistContractHistory(contractHistory);
			
			//if status is Draft sent for negotiation and Closed/Executed
			if(contractHistory.getChst_status().equals("Draft sent for negotiation") || contractHistory.getChst_status().equals("Closed/Executed")){
				String sub = "";
				String email_body ="";
		
				//get mail ids of all admin
				ArrayList<String> email = new ArrayList<String>(); 
				List<Object> mail_idCC = schedulerService.getAllAdminUser();
				Iterator<Object> iterator = mail_idCC.iterator();
				while (iterator.hasNext()) {
					Object object[] = (Object[]) iterator.next();
					if(object[1] != null)
					email.add(object[1].toString());
				}
				
				if(contractHistory.getChst_status().equals("Draft sent for negotiation")){
				sub += "Contract draft sent for negotiation";	
				
				email_body += "<h2 style='font-size:18px;'>Dear User,</h2>";
				email_body += "<p style='text-align:justify;width:70%;'>The contract draft is sent for negotiation. <a href='" + project_url + "updateTab?cont_id=" + contractHistory.getChst_contract_id() + "'> click here </a> to know more </p>";
				
				email_body += "<br/><br/><p>This is a system generated mail. Please do not reply to this mail.<br/>"
						+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
						+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
						+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
				// Email send code start here
				InternetAddress[] address = new InternetAddress[email.size()];
				try {
					for (int i = 0; i < email.size(); i++) {
						address[i] = new InternetAddress(email.get(i));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}
				
				sendMailService.sendMail(sub, email_body, address, address, String.valueOf(last_hst_id));
				}
				
				if(contractHistory.getChst_status().equals("Closed/Executed")){
					sub += "Contract has closed/Executed";	
					
					email_body += "<h2 style='font-size:18px;'>Dear User,</h2>";
					email_body += "<p style='text-align:justify;width:70%;'>This contract is closed/executed. Please <a href='" + project_url + "updateTab?cont_id=" + contractHistory.getChst_contract_id() + "'> click here </a> to know details of this contract. </p>";
					
					email_body += "<br/><br/><p>This is a system generated mail. Please do not reply to this mail.<br/>"
							+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
							+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
							+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
					// Email send code start here
					InternetAddress[] address = new InternetAddress[email.size()];
					try {
						for (int i = 0; i < email.size(); i++) {
							address[i] = new InternetAddress(email.get(i));
						}
					} catch (AddressException e) {
						e.printStackTrace();
					}
					sendMailService.sendMail(sub, email_body, address, address, String.valueOf(last_hst_id));
				}
				}
			            // Save Multiple contract type Details
                        if(chst_contract_type != null){			
							for (int i = 0; i < chst_contract_type.size(); i++) {
								ContractContractType ContractType = new ContractContractType();
								ContractType.setCont_contract_id(last_hst_id); // history id save in contract id column  
								ContractType.setCont_contract_type_id(chst_contract_type.get(i));
								ContractType.setCont_contract_type(2);//2 for contract history contract type/document type
								ContractType.setCont_contract_type_created_at(utilitieservice.getCurrentDateWithTime());
								ContractType.setCont_contract_type_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
								//contract_type += list_type_of_contracts.get(i) + " , "; //For logs only
								contractDao.saveContractTypeList(ContractType);
							}
                        }
			if (!contractHistory.getChst_status().toString().equals("Save As Draft")) {
				// Update Contract Status in master table
				Contract contract = contractDao.getContractById(contractHistory.getChst_contract_id());
				contract.setCont_status(contractHistory.getChst_status());

				contractDao.updateRecord(contract);
				// persist history log
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(contractHistory.getChst_contract_id());
				logs.setLog_assinged_to_id(contractHistory.getChst_assigned_to());
				logs.setLog_activity("Add");
				logs.setLog_sub_activity_id(contractHistory.getChst_id());
				logs.setLog_related_to("Pre Execution contract");
				logs.setLog_sub_activity("Contract History");
				
				if(!contractHistory.getChst_status().equals("Others")){
					logs.setLog_related_name(contractHistory.getChst_status());
					
					}else{
					logs.setLog_related_name(contractHistory.getChst_status_others());	
					}
					
					logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
					logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
					contractDao.persist(logs);
			}
			
			// Upload Documents terms
			String originalFileName = null;
			String generatedFileName = null;
			String generated_file_name = "";
			int lastGeneratedValueDoc = contractDao.getLastGenerateValueForContractHistory(last_hst_id);
			for (int i = 0; i < contract_doc.size(); i++) {
				MultipartFile file = contract_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "PreExecutionContractDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedContractHSTDoc_" + last_hst_id + "_" + lastGeneratedValueDoc + "."
							+ file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());
					generated_file_name = "C:/"+project_name+"/Documents/PreExecutionContractDocuments/" + generatedFileName;
					ContractHistoryDocuments contractHistoryDocuments = new ContractHistoryDocuments();
					contractHistoryDocuments.setChst_doc_contract_history_id(last_hst_id);
					contractHistoryDocuments.setChst_doc_contract_id(contractHistory.getChst_contract_id());
					contractHistoryDocuments.setChst_doc_original_file_name(originalFileName);
					contractHistoryDocuments.setChst_doc_generated_file_name(generated_file_name);
					contractHistoryDocuments
							.setChst_doc_last_generated_value_for_contract_history_id(lastGeneratedValueDoc);
					contractHistoryDocuments.setChst_document_type("History");
					contractHistoryDocuments.setChst_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
					contractHistoryDocuments.setChst_created_at(utilitieservice.getCurrentDateWithTime());

					contractDao.persist(contractHistoryDocuments);

				}
			}
			
			//if status is SentToPOCforNegotiation
			if(contractHistory.getChst_status().equals("SentToPOCforNegotiation")){
			
			//***************** save this status in POC status table *************************
				
				Contract cont = contractDao.getContractById(contractHistory.getChst_contract_id());
				PocStatusForNegotiation poc = new PocStatusForNegotiation();	
				
				poc.setPoc_action_item(contractHistory.getChst_comments());
				poc.setPoc_status(contractHistory.getChst_status());
				poc.setPoc_contract_id(contractHistory.getChst_contract_id());
				poc.setPoc_contract_req_id(cont.getCont_req_id());
				poc.setPoc_status_added_by(utilitieservice.getCurrentSessionUserId(session));
				poc.setPoc_status_created_at(utilitieservice.getCurrentDateWithTime());
				poc.setPoc_status_date(utilitieservice.getCurrentDateWithTime());
				int status_id = requestByPOCDao.savePocStatus(poc);
				// Upload Documents terms
				
				String originalPOCFileName = null;
				String generatedPOCFileName = null;
				String generated_poc_file_name = "";
				int lastGeneratedValuePOCDoc = requestByPOCDao.getLastGenerateValueForPocDoc(status_id);
				for (int i = 0; i < contract_doc.size(); i++) {
					MultipartFile file = contract_doc.get(i);
					if (file.getSize() > 0) {
						File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
								+ "POCDocuments");
						if (!dir.exists())
							dir.mkdirs();

						lastGeneratedValuePOCDoc++;

						originalPOCFileName = file.getOriginalFilename();
						generatedPOCFileName = "uploadedPocDoc_" + status_id + "_" + lastGeneratedValuePOCDoc + "."
								+ file.getOriginalFilename().split("\\.")[1];
						File newFile = new File(dir.getPath() + File.separator + generatedPOCFileName);
						if (!newFile.exists()) {
							newFile.createNewFile();
						}

						@SuppressWarnings("resource")
						OutputStream outputStream = new FileOutputStream(newFile);

						outputStream.write(file.getBytes());
						generated_poc_file_name = "C:/"+project_name+"/Documents/POCDocuments/" + generatedPOCFileName;
						PocNegotiationDocument pocDoc = new PocNegotiationDocument();
						
						pocDoc.setPoc_doc_contract_id(status_id);
						pocDoc.setPoc_doc_original_file_name(originalPOCFileName);
						pocDoc.setPoc_doc_generated_file_name(generated_poc_file_name);
						pocDoc.setPoc_doc_last_generated_value_for_contract_id(lastGeneratedValuePOCDoc);
						pocDoc.setPoc_doc_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
						pocDoc.setPoc_doc_created_at(utilitieservice.getCurrentDateWithTime());
						pocDoc.setPoc_document_type("");
						contractDao.persist(pocDoc);

					}
				}
			if(cont.getCont_req_id() != 0){
				//******************** send mail to Admin with POC user in cc *********************
				String subject = "";
				String email_body = "";
				String poc_email = userService.getUserById(contractHistory.getChst_poc_user_id()).getUser_email();
				subject += "Contract document sent for negotiation";	
				
				email_body += "<h2 style='font-size:18px;'>Dear User,</h2>";
				email_body += "<p style='text-align:justify;width:70%;'>The following contract document is sent for negotiation. <a href='" + project_url + "updateTab?cont_id=" + contractHistory.getChst_contract_id() + "'> click here </a> to know more </p>";
				email_body += "<h2 style='font-size:18px;'>Contract Details :</h2>";
				email_body += "<table style='width:80%;' border='1'>" 
						+ "<thead>"
						+ "<tr style='background:#0B6EC3;color:#fff;'>" 
						+ "<th>ID</th>" 
						+ "<th>Action to be performed</th>"
						+ "<th>Action performed by</th>" 
						+ "<th>Comments</th>" 
						+ "<th>Action assigned to</th>" 
						+ "</tr>" + "</thead>" + "<tbody>";
				
				email_body += "<tr>" 
						+ "<td><a href='" + project_url + "updateTab?cont_id=" + contractHistory.getChst_contract_id() + "'> "+ contractHistory.getChst_contract_id() + "</a></td>"; 
						//+ "<td>" + stage + "</td>" 
						if(!contractHistory.getChst_action_performed().equals("Others")){
							email_body += "<td>" + contractHistory.getChst_action_performed() + "</td>";
						}else{
							email_body += "<td>" + contractHistory.getChst_action_performed_others() + "</td>";	
						}
						
						if(!contractHistory.getChst_performed_by().equals("Others")){
							email_body += "<td>" + contractHistory.getChst_performed_by() + "</td>";
						}else{
							email_body += "<td>" + contractHistory.getChst_performed_by_others() + "</td>";	
						}
				email_body += "<td>" + contractHistory.getChst_comments() + "</td>"; 
						if(contractHistory.getChst_assigned_to() != -1){
				email_body += "<td> "+ userService.getUserFullNameById(contractHistory.getChst_assigned_to()) + "</td>";
						}else{
				email_body += "<td> "+ contractHistory.getChst_assign_others() + "</td>";			
						}
				email_body += "</tr>";
				
				email_body += "<br/><br/><p>This is a system generated mail. Please do not reply to this mail.<br/>"
						+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
						+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
						+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
				
				ArrayList<String> email = new ArrayList<String>(); 
				List<Object> mail_idCC = schedulerService.getAllAdminUser();
				Iterator<Object> iterator = mail_idCC.iterator();
				while (iterator.hasNext()) {
					Object object[] = (Object[]) iterator.next();
					if(object[1] != null)
					email.add(object[1].toString());
				}
				
				// Email send code start here
				InternetAddress[] address = new InternetAddress[email.size()];
				try {
					for (int i = 0; i < email.size(); i++) {
						address[i] = new InternetAddress(email.get(i));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}
				
				InternetAddress[] addressTo = new InternetAddress[1];
				try {
					addressTo[0] = new InternetAddress(poc_email);
				} catch (AddressException e) {
					e.printStackTrace();
				}
				
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", hostName);
				props.put("mail.smtp.port", portNo);

				Session session1 = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
				try {
					Message message = new MimeMessage(session1);
					message.setFrom(new InternetAddress(mailFrom));
					if(!adminStatus.equals("Yes"))
					message.setRecipients(Message.RecipientType.TO, addressTo);
					else
					message.setRecipients(Message.RecipientType.TO, address);
					message.setRecipients(Message.RecipientType.CC, address);
					message.setSubject(subject);
					
					// Create the message part
					BodyPart messageBodyPart = new MimeBodyPart();

					// Now set the actual message
					messageBodyPart.setText(email_body);
					messageBodyPart.setContent(email_body, "text/html; charset=utf-8");
					
					// Create a multipart message
					Multipart multipart = new MimeMultipart();
					// Set text message part
					multipart.addBodyPart(messageBodyPart);
					// Part two is attachment
					if(generated_file_name != null){
					messageBodyPart = new MimeBodyPart();
					
					DataSource source = new FileDataSource(generated_file_name);
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(originalFileName);
					multipart.addBodyPart(messageBodyPart);
					}
					// Send the complete message parts
					message.setContent(multipart);
					Transport.send(message);

					for (int i = 0; i < address.length; i++) {
						utilitieservice.addMailToLog(String.valueOf(addressTo), String.valueOf(address[i]), subject, String.valueOf(last_hst_id));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get Contract history and documents details For Execution
	// page list
	@Override
	public List<ContractHistoryReference> getContractHistoryById(int cont_id) {
		try {
			SimpleDateFormat dateIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dateOut = new SimpleDateFormat("dd-MM-yyyy");
			
			ArrayList<ContractHistoryReference> sendList = new ArrayList<>();
			List<ContractHistory> contract = contractDao.getContractHistoryById(cont_id);
			Iterator<ContractHistory> iterator = contract.iterator();
			while (iterator.hasNext()) {
				ContractHistory contractHistory = iterator.next();
				ContractHistoryReference contractHistoryReference = new ContractHistoryReference();
				contractHistoryReference.setChst_id(contractHistory.getChst_id());
				contractHistoryReference.setChst_contract_id(contractHistory.getChst_contract_id());
				contractHistoryReference.setChst_comments(contractHistory.getChst_comments());
				contractHistoryReference.setChst_performed_by(contractHistory.getChst_performed_by());
				contractHistoryReference.setChst_performed_by_others(contractHistory.getChst_performed_by_others());
				contractHistoryReference.setChst_action_performed(contractHistory.getChst_action_performed());
				contractHistoryReference
						.setChst_action_performed_others(contractHistory.getChst_action_performed_others());
				if(contractHistory.getChst_assigned_to() != -1){
					contractHistoryReference
					.setChst_assigned_to(userService.getUserFullNameById(contractHistory.getChst_assigned_to()));
				}else{
					contractHistoryReference.setChst_assigned_to("Others");
				}
				contractHistoryReference.setChst_status(contractHistory.getChst_status());
				contractHistoryReference.setChst_status_others(contractHistory.getChst_status_others());

				// contractHistoryReference.setChst_status(contractHistory.getChst_status());
				contractHistoryReference
						.setHst_doc(contractDao.getAllHistoryDocumentsByHstId(contractHistory.getChst_id()));
				contractHistoryReference.setChst_created_at(
						dateOut.format(dateIn.parse(contractHistory.getChst_created_at().toString())));
				
				contractHistoryReference.setChst_assign_others(contractHistory.getChst_assign_others());
				
				if(contractHistory.getChst_received_date() != null){
				contractHistoryReference.setChst_received_date(dateOut.format(dateIn.parse(contractHistory.getChst_received_date().toString())));
				}else{
				contractHistoryReference.setChst_received_date("NA");
				}
				contractHistoryReference.setChst_added_by(contractHistory.getChst_added_by());
				sendList.add(contractHistoryReference);
			}
			return sendList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Object> getContractTypesForPreExeContracts(HttpSession session) {
		try {
			return contractDao.getContractTypesForPreExeContracts(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : search contract
	@SuppressWarnings("unchecked")
	@Override
	public String searchContract(String json, HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

			JSONArray senList = new JSONArray();
			List<Object> searchResult = contractDao.searchContract(json, session);
			Iterator<Object> iterator = searchResult.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("orga_name", objects[0].toString());
				jsonObject.put("loca_name", objects[1].toString());
				jsonObject.put("dept_name", objects[2].toString());
				jsonObject.put("contract_name", objects[3].toString());
				jsonObject.put("requested_by", objects[4].toString());
				jsonObject.put("requested_name", userService.getUserFullNameById(Integer.parseInt(objects[4].toString())));
				if(objects[5]!=null)
				 jsonObject.put("requested_date", sdfOut.format(sdfIn.parse(objects[5].toString())));
				else
					jsonObject.put("requested_date", "NA");
				
				if(objects[6]!=null)
				   jsonObject.put("expected_date", sdfOut.format(sdfIn.parse(objects[6].toString())));
				else
					jsonObject.put("expected_date", "NA");
				
				if(objects[7]!=null)
				   jsonObject.put("responsible_person", objects[7].toString()+' '+objects[8].toString());
				else
					jsonObject.put("responsible_person", "NA");
				if(objects[9]!=null)
				jsonObject.put("targeted_date", sdfOut.format(sdfIn.parse(objects[9].toString())));
				else
					jsonObject.put("targeted_date", "NA");
				jsonObject.put("contract_id", objects[10].toString());

				List<ContractParties> res = contractDao
						.getContractPartiesByContractId(Integer.parseInt(objects[10].toString()));
				Iterator<ContractParties> iterator2 = res.iterator();
				JSONArray multiParty = new JSONArray();
				while (iterator2.hasNext()) {
					ContractParties contractParties = iterator2.next();
					JSONObject party = new JSONObject();
					party.put("party_id", contractParties.getCont_party_id());
					party.put("party_name", contractParties.getCont_party_name());
					multiParty.add(party);
				}
				jsonObject.put("parties", multiParty);

				if (objects[11].toString().equals("Sent_For_Review"))
					jsonObject.put("contract_status", "Sent For Review");
				else
					jsonObject.put("contract_status", objects[11].toString());

				jsonObject.put("contract_added_by", objects[12].toString());
				jsonObject.put("user_role", session.getAttribute("sess_user_role"));
				jsonObject.put("user_id", session.getAttribute("sess_user_id"));

				senList.add(jsonObject);
			}
			return senList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : get Contract details by id
	@Override
	public Contract getContractByContractId(int cont_id) {
		try {
			return contractDao.getContractById(cont_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : update contract details
	@Override
	public void updateContract(ContractReference contractRef, ArrayList<MultipartFile> term_doc,
			ArrayList<MultipartFile> contract_doc, ArrayList<String> additional_parties,
			ArrayList<String> list_type_of_contract,String status,HttpSession session) {
		try {
			Contract Oldcontract = contractDao.getContractById(contractRef.getCont_id());
			Contract newContract = new Contract();
			newContract.setCont_id(contractRef.getCont_id());
			newContract.setCont_orga_id(contractRef.getCont_orga_id());
			newContract.setCont_loca_id(contractRef.getCont_loca_id());
			newContract.setCont_dept_id(contractRef.getCont_dept_id());
			newContract.setCont_agreement_name(contractRef.getCont_agreement_name());
			newContract.setCont_requested_date(contractRef.getCont_requested_date_date());
			newContract.setCont_requested_by_user_id(contractRef.getCont_requested_by_user_id());
			newContract.setCont_purpose(contractRef.getCont_purpose());
			newContract.setCont_expected_date(contractRef.getCont_expected_date_date());
			if(status.equals("Draft"))
			  newContract.setCont_responsible_user_id("0");
			else
				newContract.setCont_responsible_user_id(contractRef.getCont_responsible_user_id());
			newContract.setCont_criticality(contractRef.getCont_criticality());
			newContract.setCont_targetted_date(contractRef.getCont_targetted_date_date());
			newContract.setCont_added_by(Oldcontract.getCont_added_by());
			newContract.setCont_created_at(Oldcontract.getCont_created_at());
			newContract.setCont_nature(contractRef.getCont_nature());
			newContract.setCont_major_clause(contractRef.getCont_major_clause());
			newContract.setCont_surviving_clause(contractRef.getCont_surviving_clause());
			newContract.setCont_payment(contractRef.getCont_payment());
			newContract.setCont_requested_by_user_id(contractRef.getCont_requested_by_user_id());
			newContract.setCont_term(contractRef.getCont_term());
			newContract.setCont_start_date(contractRef.getCont_start_date());
			newContract.setCont_end_date(contractRef.getCont_end_date());
			newContract.setCont_damages(contractRef.getCont_damages());
			newContract.setCont_instructions(contractRef.getCont_instructions());
			newContract.setCont_reminder_date(contractRef.getCont_reminder_date());
			newContract.setCont_req_id(Oldcontract.getCont_req_id());
			
			if(!Oldcontract.getCont_status().toString().equals("Draft"))
				newContract.setCont_status(Oldcontract.getCont_status());
			else
				newContract.setCont_status(status);
			
			contractDao.updateRecord(newContract);
			int contract_id = contractRef.getCont_id();

			
			if (!Oldcontract.getCont_status().toString().equals("Draft")) {
				// persist history log
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(contractRef.getCont_id());
				logs.setLog_assinged_to_id(Integer.parseInt(newContract.getCont_responsible_user_id()));
				logs.setLog_activity("Update");
				logs.setLog_related_to("Pre Execution contract");
				logs.setLog_sub_activity("Update Pre Execution contract");
				logs.setLog_related_name(newContract.getCont_agreement_name());
				logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
				logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
				contractDao.persist(logs);
			}
			
			if(status.equals("Pending") && Oldcontract.getCont_status().toString().equals("Draft")){
				sendMailService.sendPreExecutionAssignMailToResponsiblePerson(contract_id, session);
				// persist contract log
							ActivityLogs logs = new ActivityLogs();
							logs.setLog_activity_id(contract_id);
							logs.setLog_assinged_to_id(Integer.parseInt(Oldcontract.getCont_responsible_user_id()));
							logs.setLog_activity("Add");
							logs.setLog_related_to("Pre Execution contract");
							logs.setLog_sub_activity("Add Pre Execution contract");
							logs.setLog_related_name(Oldcontract.getCont_agreement_name());
							logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
							logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
							contractDao.persist(logs);
				}
			// Update Party details
			List<ContractParties> parties = contractRef.getCont_parties();
			if (parties != null && parties.size() > 0) {
				for (ContractParties contractParties : parties) {
					ContractParties oldDataParty = executedContractDao.getPartyById(contractParties.getCont_party_id());
					// Assign old data to new party data
					contractParties.setCont_party_type(oldDataParty.getCont_party_type());
					contractParties.setCont_party_contract_id(oldDataParty.getCont_party_contract_id());
					contractParties.setCont_party_series(oldDataParty.getCont_party_series());
					contractParties.setCont_party_added_by(oldDataParty.getCont_party_added_by());
					contractParties.setCont_party_created_at(oldDataParty.getCont_party_created_at());
					executedContractDao.meageParties(contractParties);
				}
			}

			// Save Parties Details
			if(additional_parties !=null){
				for (int i = 0; i < additional_parties.size(); i++) {
					ContractParties contractParties = new ContractParties();
					contractParties.setCont_party_contract_id(contract_id);
					contractParties.setCont_party_name(additional_parties.get(i));
					contractParties.setCont_party_type(1);
					contractParties.setCont_party_created_at(utilitieservice.getCurrentDate());
					contractParties.setCont_party_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
					executedContractDao.savePartyDetails(contractParties);
					// System.out.println("Party "+additional_parties.get(i));
				}
			}

			// Delete all previous contract type from transactional table
			contractDao.deleteContractType(contract_id,1);
			if(list_type_of_contract!=null){
			// Save Multiple contract type Details
			for (int i = 0; i < list_type_of_contract.size(); i++) {
				ContractContractType ContractType = new ContractContractType();
				ContractType.setCont_contract_id(contract_id);
				ContractType.setCont_contract_type_id(list_type_of_contract.get(i));
				ContractType.setCont_contract_type(1);
				ContractType.setCont_contract_type_created_at(utilitieservice.getCurrentDate());
				ContractType.setCont_contract_type_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
				contractDao.saveContractTypeList(ContractType);
			}
			}
			// Upload Documents terms
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueDoc = contractDao.getLastGenerateValueForContract(contract_id);
			for (int i = 0; i < term_doc.size(); i++) {
				MultipartFile file = term_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "PreExecutionContractDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedContractDoc_" + contract_id + "_" + lastGeneratedValueDoc + "."
							+ file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					ContractDocuments documents = new ContractDocuments();

					documents.setCdoc_contract_id(contract_id);
					documents.setCdoc_original_file_name(originalFileName);
					documents.setCdoc_generated_file_name(
							"C:/"+project_name+"/Documents/PreExecutionContractDocuments/" + generatedFileName);
					documents.setCdoc_last_generated_value_for_contract_id(lastGeneratedValueDoc);
					documents.setCdoc_created_at(utilitieservice.getCurrentDateWithTime());
					documents.setCdoc_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
					documents.setCdoc_document_type("Term");
					contractDao.persist(documents);

				}
			}

			// Upload Documents Contract
			lastGeneratedValueDoc = contractDao.getLastGenerateValueForContract(contract_id);
			for (int i = 0; i < contract_doc.size(); i++) {
				MultipartFile file = contract_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "PreExecutionContractDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedContractDoc_" + contract_id + "_" + lastGeneratedValueDoc + "."
							+ file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					ContractDocuments documents = new ContractDocuments();

					documents.setCdoc_contract_id(contract_id);
					documents.setCdoc_original_file_name(originalFileName);
					documents.setCdoc_generated_file_name(
							"C:/"+project_name+"/Documents/PreExecutionContractDocuments/" + generatedFileName);
					documents.setCdoc_last_generated_value_for_contract_id(lastGeneratedValueDoc);
					documents.setCdoc_created_at(utilitieservice.getCurrentDateWithTime());
					documents.setCdoc_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
					documents.setCdoc_document_type("Contract");
					contractDao.persist(documents);

				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created : Harshad Padole
	// Method Purpose : Get all documents by contract id
	@Override
	public List<ContractDocuments> getAllContractDocumentsByContractId(int contract_id) {
		try {
			return contractDao.getAllContractDocumentsByContractId(contract_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: Download document
	@Override
	public void downloadPreExecutedDocument(int cdoc_id, HttpServletResponse response) {
		try {
			if (contractDao.getPreExecutionDocumentFilePath(cdoc_id) != null) {
				File file = new File(contractDao.getPreExecutionDocumentFilePath(cdoc_id));
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

	// Method Created By: Tejashri Zurunge
	// Method Purpose: Download document
	@Override
	public void downloadPreExecutedHistoryDocument(int chst_doc_id, HttpServletResponse response) {
		try {
			if (contractDao.getPreExecutionHistoryDocumentFilePath(chst_doc_id) != null) {
				File file = new File(contractDao.getPreExecutionHistoryDocumentFilePath(chst_doc_id));
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

	// Method Created By: Tejashri Zurunge
	// Method Purpose: get contract history by history id
	@Override
	public ContractHistory getContractHistoryByHistoryId(int chst_id) {
		try {
			return contractDao.getContractHistoryByHistoryId(chst_id);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: update contract history
	@Override
	public void updateContractHistory(ContractHistoryReference contractHistory, ArrayList<MultipartFile> hst_doc,ArrayList<String> chst_contract_type) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			ContractHistory oldContract = contractDao.getContractHistoryByHistoryId(contractHistory.getChst_id());
			ContractHistory newContract = new ContractHistory();

			newContract.setChst_id(contractHistory.getChst_id());
			newContract.setChst_status(oldContract.getChst_status());
			newContract.setChst_status_others(oldContract.getChst_status_others());
			newContract.setChst_action_performed(contractHistory.getChst_action_performed());
			newContract.setChst_action_performed_others(contractHistory.getChst_action_performed_others());
			newContract.setChst_assigned_to(Integer.parseInt(contractHistory.getChst_assigned_to()));
			newContract.setChst_performed_by(contractHistory.getChst_performed_by());
			newContract.setChst_performed_by_others(contractHistory.getChst_performed_by_others());
			newContract.setChst_comments(contractHistory.getChst_comments());
			// set old records
			newContract.setChst_contract_id(oldContract.getChst_contract_id());
			newContract.setChst_created_at(oldContract.getChst_created_at());
			newContract.setChst_added_by(oldContract.getChst_added_by());
			newContract.setChst_assign_others(contractHistory.getChst_assign_others());
			newContract.setChst_poc_user_id(contractHistory.getChst_poc_user_id());
			if(!contractHistory.getChst_received_date().equals("")){
				newContract.setChst_received_date(sdfIn.parse(sdfIn.format(sdfOut.parse(contractHistory.getChst_received_date()))));	
			}
			contractDao.updateRecord(newContract);
			// Delete all previous contract type from transactional table
			contractDao.deleteContractType(contractHistory.getChst_id(), 2);

			// Save Multiple contract type Details
			if(chst_contract_type!=null){
				for (int i = 0; i < chst_contract_type.size(); i++) {
					ContractContractType ContractType = new ContractContractType();
					ContractType.setCont_contract_id(contractHistory.getChst_id());
					ContractType.setCont_contract_type_id(chst_contract_type.get(i));
					ContractType.setCont_contract_type(2);
					ContractType.setCont_contract_type_created_at(utilitieservice.getCurrentDate());
					ContractType.setCont_contract_type_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
					contractDao.saveContractTypeList(ContractType);
				}
			}
			/*Contract contract = contractDao.getContractById(contractHistory.getChst_contract_id());
			contract.setCont_status(contractHistory.getChst_status());
			contractDao.updateRecord(contract);*/
			/*if (!contractHistory.getChst_status().toString().equals("Save As Draft") && oldContract.getChst_status().equals("Save As Draft")) {
				// Update Contract Status in master table
				Contract contract = contractDao.getContractById(contractHistory.getChst_contract_id());
				contract.setCont_status(contractHistory.getChst_status());
				contractDao.updateRecord(contract);
				
				// persist history log
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(contractHistory.getChst_contract_id());
				logs.setLog_assinged_to_id(Integer.parseInt(contractHistory.getChst_assigned_to()));
				logs.setLog_activity("Add");
				logs.setLog_sub_activity_id(contractHistory.getChst_id());
				logs.setLog_related_to("Pre Execution contract");
				logs.setLog_sub_activity("Contract History");
				
				if(!contractHistory.getChst_status().equals("Others")){
					logs.setLog_related_name(contractHistory.getChst_status());
					
					}else{
					logs.setLog_related_name(contractHistory.getChst_status_others());	
					}
					
					logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
					logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
					contractDao.persist(logs);
			}*/
			
			if (!oldContract.getChst_status().equals("Save As Draft")) {
				/*Contract contract = contractDao.getContractById(contractHistory.getChst_contract_id());
				contract.setCont_status(contractHistory.getChst_status());
				contractDao.updateRecord(contract);*/
				// persist history log
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(newContract.getChst_contract_id());
				logs.setLog_sub_activity_id(contractHistory.getChst_id());
				logs.setLog_assinged_to_id(newContract.getChst_assigned_to());
				logs.setLog_activity("Update");
				logs.setLog_related_to("Pre Execution contract");
				logs.setLog_sub_activity("Contract History");

				if (!oldContract.getChst_status().equals("Others")) {
					logs.setLog_related_name(oldContract.getChst_status());
				} else {
					logs.setLog_related_name(oldContract.getChst_status_others());
				}
				logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
				logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
				contractDao.persist(logs);
			}
			// Upload Documents terms
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueDoc = contractDao
					.getLastGenerateValueForContractHistory(contractHistory.getChst_id());
			for (int i = 0; i < hst_doc.size(); i++) {
				MultipartFile file = hst_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "PreExecutionContractDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedContractHSTDoc_" + contractHistory.getChst_id() + "_"
							+ lastGeneratedValueDoc + "." + file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					ContractHistoryDocuments contractHistoryDocuments = new ContractHistoryDocuments();
					contractHistoryDocuments.setChst_doc_contract_history_id(contractHistory.getChst_id());
					contractHistoryDocuments.setChst_doc_contract_id(contractHistory.getChst_contract_id());
					contractHistoryDocuments.setChst_doc_original_file_name(originalFileName);
					contractHistoryDocuments.setChst_doc_generated_file_name(
							"C:/"+project_name+"/Documents/PreExecutionContractDocuments/" + generatedFileName);
					contractHistoryDocuments
							.setChst_doc_last_generated_value_for_contract_history_id(lastGeneratedValueDoc);
					contractHistoryDocuments.setChst_document_type("History");
					contractHistoryDocuments.setChst_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
					contractHistoryDocuments.setChst_created_at(utilitieservice.getCurrentDateWithTime());

					contractDao.persist(contractHistoryDocuments);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// Method Created By: Tejashri Zurunge
	// Method Purpose: delete contract party
	@Override
	public int deletePreExecutedParty(int party_id) {
		try {
			int deleteCount = contractDao.deletePreExecutedParty(party_id);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: delete contract
	@Override
	public int deletePreExecutedContract(int cont_id) {
		try {
			Contract contract = contractDao.getContractById(cont_id);
			int delete_id = contractDao.deletePreExecutedContract(cont_id);
			
			// persist history log
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(cont_id);
			logs.setLog_sub_activity_id(delete_id);
			logs.setLog_activity("Delete");
			logs.setLog_related_to("Pre Execution contract");
			logs.setLog_sub_activity("Delete Pre Execution contract");
			logs.setLog_related_name(contract.getCont_agreement_name());
			logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
			
			contractDao.persist(logs);
			return delete_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	@Override
	public ContractHistoryReference getContractHistory(int chst_history_id) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			ContractHistory res = contractDao.getContractHistoryByHistoryId(chst_history_id);
			
			ContractHistoryReference sendDetails = new ContractHistoryReference();
			
			sendDetails.setChst_id(res.getChst_id());
			sendDetails.setChst_contract_id(res.getChst_contract_id());
			sendDetails.setChst_status(res.getChst_status());
			sendDetails.setChst_status_others(res.getChst_status_others());
			sendDetails.setChst_action_performed(res.getChst_action_performed());
			sendDetails.setChst_action_performed_others(res.getChst_action_performed_others());
			sendDetails.setChst_performed_by(res.getChst_performed_by());
			sendDetails.setChst_performed_by_others(res.getChst_performed_by_others());
			sendDetails.setChst_assigned_to(String.valueOf(res.getChst_assigned_to()));
			
			sendDetails.setChst_comments(res.getChst_comments());
			sendDetails.setChst_contract_type(contractDao.getContractType(chst_history_id, 2));//2 for history type document type/contract type
			sendDetails.setChst_assign_others(res.getChst_assign_others());
			if(res.getChst_received_date() != null){
			sendDetails.setChst_received_date(sdfOut.format(sdfIn.parse(res.getChst_received_date().toString())));	
			}else{
			sendDetails.setChst_received_date("");
			}
			sendDetails.setChst_status(res.getChst_status());
			sendDetails.setChst_poc_user_id(res.getChst_poc_user_id());
			return sendDetails;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: delete contract
	@Override
	public int deletePreExecutedContractHistory(int chst_id) {
		try {
			ContractHistory res = contractDao.getContractHistoryByHistoryId(chst_id);
			int delete_id = contractDao.deletePreExecutedContractHistory(chst_id);
			// persist history log
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(res.getChst_contract_id());
			logs.setLog_sub_activity_id(chst_id);
			logs.setLog_activity("Delete");
			logs.setLog_related_to("Pre Execution contract");
			logs.setLog_sub_activity("Delete contract History");
			
			if(!res.getChst_status().equals("Others")){
				logs.setLog_related_name(res.getChst_status());
			}else{
				logs.setLog_related_name(res.getChst_status_others());	
			}
			logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());

			contractDao.persist(logs);
			return delete_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public int deletePreExecutedContractDocument(int cdoc_id) {
		try {
			File file = new File(contractDao.getPreExecutionDocumentFilePath(cdoc_id));
			if(file.delete()){
				System.out.println("The "+file.getName() +" deleted successfully");
			}else{
				System.out.println("Delete operation failed");
			}
			return contractDao.deletePreExecutedContractDocument(cdoc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<ContractHistoryDocuments> getContractHistoryDocById(int chst_id) {
		try {
			return contractDao.getAllHistoryDocumentsByHstId(chst_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteContractHistoryDoc(int doc_id) {
		try {
			File file = new File(contractDao.getPreExecutionHistoryDocumentFilePath(doc_id));
			if(file.delete()){
				System.out.println("The "+file.getName() +" deleted successfully");
			}else{
				System.out.println("Delete operation failed");
			}
			return contractDao.deleteContractHistoryDoc(doc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void saveContractByAccept(ContractReference contractRef, ArrayList<MultipartFile> term_doc,
			ArrayList<MultipartFile> contract_doc, ArrayList<String> additional_parties,
			ArrayList<String> list_type_of_contract, String status, int id, HttpSession session) {
		try {
			//Contract Oldcontract = contractDao.getContractById(contractRef.getCont_id());
			Contract newContract = new Contract();
			//newContract.setCont_id(contractRef.getCont_id());
			newContract.setCont_orga_id(contractRef.getCont_orga_id());
			newContract.setCont_loca_id(contractRef.getCont_loca_id());
			newContract.setCont_dept_id(contractRef.getCont_dept_id());
			newContract.setCont_agreement_name(contractRef.getCont_agreement_name());
			newContract.setCont_requested_date(contractRef.getCont_requested_date_date());
			newContract.setCont_requested_by_user_id(contractRef.getCont_requested_by_user_id());
			newContract.setCont_purpose(contractRef.getCont_purpose());
			newContract.setCont_expected_date(contractRef.getCont_expected_date_date());
			if(status.equals("Draft"))
			newContract.setCont_responsible_user_id("0");
			else
			newContract.setCont_responsible_user_id(contractRef.getCont_responsible_user_id());
			newContract.setCont_criticality(contractRef.getCont_criticality());
			newContract.setCont_targetted_date(contractRef.getCont_targetted_date_date());
			newContract.setCont_added_by(utilitieservice.getCurrentSessionUserId(session));
			newContract.setCont_created_at(utilitieservice.getCurrentDateWithTime());
			newContract.setCont_nature(contractRef.getCont_nature());
			newContract.setCont_major_clause(contractRef.getCont_major_clause());
			newContract.setCont_surviving_clause(contractRef.getCont_surviving_clause());
			newContract.setCont_payment(contractRef.getCont_payment());
			newContract.setCont_requested_by_user_id(contractRef.getCont_requested_by_user_id());
			newContract.setCont_term(contractRef.getCont_term());
			newContract.setCont_start_date(contractRef.getCont_start_date());
			newContract.setCont_end_date(contractRef.getCont_end_date());
			newContract.setCont_damages(contractRef.getCont_damages());
			newContract.setCont_instructions(contractRef.getCont_instructions());
			newContract.setCont_reminder_date(contractRef.getCont_reminder_date());
			newContract.setCont_req_id(id);
			/*if(!Oldcontract.getCont_status().toString().equals("Draft"))
				newContract.setCont_status(Oldcontract.getCont_status());
			else*/
			newContract.setCont_status(status);
			
			int contract_id = contractDao.saveContract(newContract);
			
			sendMailService.sendPreExecutionAssignMailToResponsiblePerson(contract_id, session);
			
			if(status.equals("Pending")){
				// persist contract log
							ActivityLogs logs = new ActivityLogs();
							logs.setLog_activity_id(contract_id);
							logs.setLog_assinged_to_id(Integer.parseInt(newContract.getCont_responsible_user_id()));
							logs.setLog_activity("Add");
							logs.setLog_related_to("Pre Execution contract");
							logs.setLog_sub_activity("Add Pre Execution contract");
							logs.setLog_related_name(newContract.getCont_agreement_name());
							logs.setLog_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
							logs.setLog_created_at(utilitieservice.getCurrentDateWithTime());
							contractDao.persist(logs);
				} 
			// Update Party details
			List<ContractParties> parties = contractRef.getCont_parties();
			if (parties != null && parties.size() > 0) {
				for (ContractParties contractParty : parties) {
					//ContractParties oldDataParty = executedContractDao.getPartyById(contractParties.getCont_party_id());
					// Assign old data to new party data
					ContractParties newDataParty = new ContractParties();
					newDataParty.setCont_party_type(1);
					newDataParty.setCont_party_contract_id(contract_id);
					newDataParty.setCont_party_name(contractParty.getCont_party_name());
					//contractParties.setCont_party_series(oldDataParty.getCont_party_series());
					newDataParty.setCont_party_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
					newDataParty.setCont_party_created_at(utilitieservice.getCurrentDate());
					executedContractDao.savePartyDetails(newDataParty);
				}
			}
			
			// Save Parties Details
			if(additional_parties !=null){
				for (int i = 0; i < additional_parties.size(); i++) {
					ContractParties contractParties = new ContractParties();
					contractParties.setCont_party_contract_id(contract_id);
					contractParties.setCont_party_name(additional_parties.get(i));
					contractParties.setCont_party_type(1);
					contractParties.setCont_party_created_at(utilitieservice.getCurrentDate());
					contractParties.setCont_party_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
					executedContractDao.savePartyDetails(contractParties);
					// System.out.println("Party "+additional_parties.get(i));
				}
			}
			
			// Delete all previous contract type from transactional table
			//contractDao.deleteContractType(contract_id,1);
			if(list_type_of_contract!=null){
			// Save Multiple contract type Details
			for (int i = 0; i < list_type_of_contract.size(); i++) {
				ContractContractType ContractType = new ContractContractType();
				ContractType.setCont_contract_id(contract_id);
				ContractType.setCont_contract_type_id(list_type_of_contract.get(i));
				ContractType.setCont_contract_type(1);
				ContractType.setCont_contract_type_created_at(utilitieservice.getCurrentDate());
				ContractType.setCont_contract_type_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
				contractDao.saveContractTypeList(ContractType);
			}
			}
			
			if(id > 0){
				ContractRequest contReq = requestByPOCService.getAllContractrequest(id, session);
				contReq.setReq_contract_approval_status(2);
				contractDao.updateRecord(contReq);
			}
			
			List<RequestDocument> termDoc = requestByPOCDao.getAllRequestDocument(id, "TermSheetContRequest");
			if(termDoc !=null){
			Iterator<RequestDocument> itr = termDoc.iterator();
			//MultipartFile teDoc;
			while (itr.hasNext()) {
				RequestDocument requestDocument = (RequestDocument) itr.next();
				ContractDocuments documents = new ContractDocuments();

				documents.setCdoc_contract_id(contract_id);
				documents.setCdoc_original_file_name(requestDocument.getReq_doc_original_file_name());
				documents.setCdoc_generated_file_name(requestDocument.getReq_doc_generated_file_name());
				documents.setCdoc_last_generated_value_for_contract_id(requestDocument.getReq_doc_last_generated_value_for_req_id());
				documents.setCdoc_created_at(utilitieservice.getCurrentDateWithTime());
				documents.setCdoc_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
				documents.setCdoc_document_type("Term");
				contractDao.persist(documents);
			}
			}
			
			List<RequestDocument> contDoc = requestByPOCDao.getAllRequestDocument(id,"ContractRequest");
			if(contDoc !=null){
			Iterator<RequestDocument> contItr = contDoc.iterator();
			//MultipartFile teDoc;
			while (contItr.hasNext()) {
				RequestDocument requestDocument = (RequestDocument) contItr.next();
				ContractDocuments documents = new ContractDocuments();

				documents.setCdoc_contract_id(contract_id);
				documents.setCdoc_original_file_name(requestDocument.getReq_doc_original_file_name());
				documents.setCdoc_generated_file_name(requestDocument.getReq_doc_generated_file_name());
				documents.setCdoc_last_generated_value_for_contract_id(requestDocument.getReq_doc_last_generated_value_for_req_id());
				documents.setCdoc_created_at(utilitieservice.getCurrentDateWithTime());
				documents.setCdoc_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
				documents.setCdoc_document_type("Contract");
				contractDao.persist(documents);
			}
			}
			
			// Upload Documents terms
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueDoc = contractDao.getLastGenerateValueForContract(contract_id);
			for (int i = 0; i < term_doc.size(); i++) {
				MultipartFile file = term_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "PreExecutionContractDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedContractDoc_" + contract_id + "_" + lastGeneratedValueDoc + "."
							+ file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					ContractDocuments documents = new ContractDocuments();

					documents.setCdoc_contract_id(contract_id);
					documents.setCdoc_original_file_name(originalFileName);
					documents.setCdoc_generated_file_name(
							"C:/"+project_name+"/Documents/PreExecutionContractDocuments/" + generatedFileName);
					documents.setCdoc_last_generated_value_for_contract_id(lastGeneratedValueDoc);
					documents.setCdoc_created_at(utilitieservice.getCurrentDateWithTime());
					documents.setCdoc_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
					documents.setCdoc_document_type("Term");
					contractDao.persist(documents);
				}
			}

			// Upload Documents Contract
			lastGeneratedValueDoc = contractDao.getLastGenerateValueForContract(contract_id);
			for (int i = 0; i < contract_doc.size(); i++) {
				MultipartFile file = contract_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "PreExecutionContractDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedContractDoc_" + contract_id + "_" + lastGeneratedValueDoc + "."
							+ file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					ContractDocuments documents = new ContractDocuments();

					documents.setCdoc_contract_id(contract_id);
					documents.setCdoc_original_file_name(originalFileName);
					documents.setCdoc_generated_file_name(
							"C:/"+project_name+"/Documents/PreExecutionContractDocuments/" + generatedFileName);
					documents.setCdoc_last_generated_value_for_contract_id(lastGeneratedValueDoc);
					documents.setCdoc_created_at(utilitieservice.getCurrentDateWithTime());
					documents.setCdoc_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
					documents.setCdoc_document_type("Contract");
					contractDao.persist(documents);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}



