package lcmt.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lcmt.dao.ContractDao;
import lcmt.dao.ExecutedContractDao;
import lcmt.dao.RequestByPOCDao;
import lcmt.dao.SendMailDao;
import lcmt.domain.Contract;
import lcmt.domain.ContractParties;
import lcmt.domain.ContractRequest;
import lcmt.domain.ContractRequestReference;
import lcmt.domain.LegalNoticeRequest;
import lcmt.domain.LitigationRequest;
import lcmt.domain.PocNegotiationDocument;
import lcmt.domain.PocStatusForNegotiation;
import lcmt.domain.PocStatusNegotiationReference;
import lcmt.domain.QueryRaised;
import lcmt.domain.QueryRequest;
import lcmt.domain.RequestByPOCReference;
import lcmt.domain.RequestDocument;
import lcmt.domain.RequestRejectStatus;
import lcmt.domain.RequestRejectStatusReference;
import lcmt.domain.User;
import lcmt.service.DepartmentService;
import lcmt.service.LocationService;
import lcmt.service.OrganizationService;
import lcmt.service.RequestByPOCService;
import lcmt.service.SchedulerService;
import lcmt.service.SendMailService;
import lcmt.service.UserService;
import lcmt.service.UtilitiesService;

@Service("requestByPOCService")
public class RequestByPOCServiceImpl implements RequestByPOCService {
	
	@Autowired
	UtilitiesService utilitiesService;
	@Autowired
	RequestByPOCDao requestByPOCDao;
	@Autowired
	UserService userService;
	@Autowired
	SendMailService sendMailService;
	@Autowired
	OrganizationService organizationService;
	@Autowired
	LocationService locationService;
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	SendMailDao sendMailDao;
	
	@Autowired
	SchedulerService schedulerService;
	@Autowired
	ExecutedContractDao executedContractDao;
	@Autowired
	ContractDao contractDao;
	
	
	private @Value("#{config['project_name'] ?: 'null'}") String project_name;
	private @Value("#{config['project_url'] ?: 'null'}") String project_url;
	private @Value("#{config['mail_user_name'] ?: 'null'}") String username;
	private @Value("#{config['mail_password'] ?: 'null'}") String password;
	private @Value("#{config['mail_smtp_host'] ?: 'null'}") String hostName;
	private @Value("#{config['mail_smtp_port'] ?: 'null'}") String portNo;
	private @Value("#{config['mail_from'] ?: 'null'}") String mailFrom;

	@Override
	public List<RequestByPOCReference> getAllListRequestByPOC(HttpSession session) {
		try {
			List<RequestByPOCReference> sendList = new ArrayList<>();
			List<Object> request = requestByPOCDao.getAllListRequestByPOC(session);
			Iterator<Object> itr = request.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
				
				RequestByPOCReference ref = new RequestByPOCReference();
				ref.setReq_entity_name(object[0].toString());
				ref.setReq_unit_name(object[1].toString());
				ref.setReq_function_name(object[2].toString());
				ref.setReq_related_to(object[3].toString());
				ref.setReq_id(Integer.parseInt(object[4].toString()));
				sendList.add(ref);
			}
			return sendList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveContractRequest(ContractRequest req, ArrayList<String> additional_parties, ArrayList<MultipartFile> contract_doc, 
			 ArrayList<MultipartFile> term_sheet_doc, HttpSession session, String status) {
		try {
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			req.setReq_contract_added_by(utilitiesService.getCurrentSessionUserId(session));
			req.setReq_contract_created_at(utilitiesService.getCurrentDate());
			if(!req.getReq_contract_major_clause().equals(""))
				req.setReq_contract_major_clause(req.getReq_contract_major_clause());
			else
				req.setReq_contract_major_clause("No");	
			
			if(!req.getReq_contract_surviving_clause().equals(""))
				req.setReq_contract_surviving_clause(req.getReq_contract_surviving_clause());
			else
				req.setReq_contract_surviving_clause("No");	
			
			if(!req.getReq_contract_perform_rel_payment().equals(""))
				req.setReq_contract_perform_rel_payment(req.getReq_contract_perform_rel_payment());
			else
				req.setReq_contract_perform_rel_payment("No");	
			
			if(!req.getReq_contract_damage().equals(""))
				req.setReq_contract_damage(req.getReq_contract_damage());
			else
				req.setReq_contract_damage("No");
			
			if(!req.getReq_contract_insurance().equals(""))
				req.setReq_contract_insurance(req.getReq_contract_insurance());
			else
				req.setReq_contract_insurance("No");	
			
			if(!req.getReq_contract_notice_period().equals(""))
				req.setReq_contract_notice_period(req.getReq_contract_notice_period());
			else
				req.setReq_contract_notice_period("No");
			
			if(status.equals("Pending"))
				req.setReq_contract_approval_status(0);
				else
				req.setReq_contract_approval_status(4);
			
			int req_id = requestByPOCDao.saveContractRequest(req);
			for (int i = 0; i < additional_parties.size(); i++) {
				ContractParties contractParties = new ContractParties();
				contractParties.setCont_party_contract_id(req_id);
				contractParties.setCont_party_name(additional_parties.get(i));
				contractParties.setCont_party_type(3);
				contractParties.setCont_party_created_at(utilitiesService.getCurrentDateWithTime());
				contractParties.setCont_party_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
				requestByPOCDao.save(contractParties);
				// System.out.println("Party "+additional_parties.get(i));
			}
			
		if(req.getReq_contract_approval_status() != 4){
				
			String email_subject = "Contract Request Generated ";
			
			//get mail ids of all admin
			ArrayList<String> email = new ArrayList<String>(); 
			List<Object> mail_idCC = schedulerService.getAllAdminUser();
			Iterator<Object> iterator = mail_idCC.iterator();
			while (iterator.hasNext()) {
				Object object[] = (Object[]) iterator.next();
				if(object[1] != null)
				email.add(object[1].toString());
			}
			//get mail ids of all SPOCs
			ArrayList<String> spoc = new ArrayList<String>(); 
			
			List<User> spoc_mail = schedulerService.getContractSPOCuser();
			
			Iterator<User> iterator1 = spoc_mail.iterator();
			while (iterator1.hasNext()) {
				User user = iterator1.next();
				if(!user.getUser_email().equals(""))
					spoc.add(user.getUser_email());
			}
			
		    String email_body  = " <h2 style='font-size:18px;'>Dear SPOC,</h2> ";
			   email_body += " <p style='text-align:justify;width:70%;'>"+userService.getUserFullNameById(Integer.parseInt(session.getAttribute("sess_user_id").toString()))+" generated contract request. "
			   			+ "Kindly go through link by clicking on 'Id' and share your feedback on this. </p> "
		
						+ "<h2 style='font-size:16px;font-weight:bold;'>Contract Request Details :</h2>";
				email_body += "<table style='width:80%;' border='1'>" 
						+ "<thead>"
						+ "<tr style='background:#0B6EC3;color:#fff;'>"
						+ "<th>Id</th>"
						+ "<th>Entity</th>" 
						+ "<th>Unit</th>" 
						+ "<th>Function</th>"
						+ "<th>Contract Date</th>"
						//+ "<th>Party</th>"
						+ "<th>Description</th>"
						+ "<th>Major Clauses</th>"
						+ "</tr>" + "</thead>" + "<tbody>";
				String cont_date= "";
				if(req.getReq_contract_date() != null){
				 cont_date = sdfOut.format(req.getReq_contract_date());
				}else{
				 cont_date = "NA";	
				}
				email_body += "<tr>" 
						+ "<td> <a href='"+project_url+"listContractRequest'>1</a></td>" 
						+ "<td>" + organizationService.getOrganizationById(req.getReq_contract_entity_id()).getOrga_name() + "</td>" 
						+ "<td>" + locationService.getLocationNameById(req.getReq_contract_unit_id()) + "</td>" 	
						+ "<td>" + departmentService.getDepartmentNameById(req.getReq_contract_function_id()) + "</td>" 
						+ "<td>" + cont_date + "</td>" 
						//+ "<td>" + requestByPOCDao.getAllPartyForRequest(Integer.parseInt(object[4].toString())) +"</td>"
						+ "<td>" + req.getReq_contract_desc() + "</td>" 
						+ "<td>" + req.getReq_contract_major_clause() + "</td>" 
						+ "</tr>";
			
				email_body += "</tbody>" + "</table>";
				
				email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
						+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
						+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
						+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
		
			   InternetAddress[] address = new InternetAddress[spoc.size()];
				try {
					for(int j=0; j < spoc.size(); j++){
					address[j] = new InternetAddress((String)spoc.get(j));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}	
	
				 InternetAddress[] addressCC = new InternetAddress[email.size()];
				try {
					for(int i = 0; i<email.size(); i++){
					addressCC[i] = new InternetAddress((String)email.get(i));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}	
			sendMailService.sendMail(email_subject, email_body, address, addressCC, String.valueOf(req_id));
			
			}
			// Upload Documents Contract
			String originalFileName = null;
			String generatedFileName = null;
						int lastGeneratedValueDoc = requestByPOCDao.getLastGenerateValueForRequest(req_id,"ContractRequest");
						for (int i = 0; i < contract_doc.size(); i++) {
							MultipartFile file = contract_doc.get(i);
							if (file.getSize() > 0) {
								File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
										+ "RequestDocuments");
								if (!dir.exists())
									dir.mkdirs();

								lastGeneratedValueDoc++;

								originalFileName = file.getOriginalFilename();
								generatedFileName = "uploadedContractRequestDoc_" + req_id + "_" + lastGeneratedValueDoc + "."
										+ file.getOriginalFilename().split("\\.")[1];
								File newFile = new File(dir.getPath() + File.separator + generatedFileName);
								if (!newFile.exists()) {
									newFile.createNewFile();
								}

								@SuppressWarnings("resource")
								OutputStream outputStream = new FileOutputStream(newFile);

								outputStream.write(file.getBytes());

								RequestDocument documents = new RequestDocument();

								documents.setReq_id(req_id);
								documents.setReq_doc_original_file_name(originalFileName);
								documents.setReq_doc_generated_file_name(newFile.getAbsolutePath());
								documents.setReq_doc_last_generated_value_for_req_id(lastGeneratedValueDoc);
								documents.setReq_doc_created_at(utilitiesService.getCurrentDateWithTime());
								documents.setReq_doc_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
								documents.setReq_doc_document_type("ContractRequest");
								
								requestByPOCDao.save(documents);
							}
						}
						
						//upload term sheet document
						int lastGeneratedValueTermDoc = requestByPOCDao.getLastGenerateValueForRequest(req_id,"TermSheetContRequest");
						for (int i = 0; i < term_sheet_doc.size(); i++) {
							MultipartFile file = term_sheet_doc.get(i);
							if (file.getSize() > 0) {
								File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
										+ "RequestDocuments");
								if (!dir.exists())
									dir.mkdirs();

								lastGeneratedValueTermDoc++;

								originalFileName = file.getOriginalFilename();
								generatedFileName = "uploadedTermSheetRequestDoc_" + req_id + "_" + lastGeneratedValueTermDoc + "."
										+ file.getOriginalFilename().split("\\.")[1];
								File newFile = new File(dir.getPath() + File.separator + generatedFileName);
								if (!newFile.exists()) {
									newFile.createNewFile();
								}

								@SuppressWarnings("resource")
								OutputStream outputStream = new FileOutputStream(newFile);

								outputStream.write(file.getBytes());

								RequestDocument documents = new RequestDocument();

								documents.setReq_id(req_id);
								documents.setReq_doc_original_file_name(originalFileName);
								documents.setReq_doc_generated_file_name(newFile.getAbsolutePath());
								documents.setReq_doc_last_generated_value_for_req_id(lastGeneratedValueTermDoc);
								documents.setReq_doc_created_at(utilitiesService.getCurrentDateWithTime());
								documents.setReq_doc_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
								documents.setReq_doc_document_type("TermSheetContRequest");
								
								requestByPOCDao.save(documents);
							}
						}				
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<RequestByPOCReference> getAllListContractRequest(HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			List<RequestByPOCReference> ref = new ArrayList<>();
			List<Object> contList = requestByPOCDao.getAllListContractRequest(session); 
			
			Iterator<Object> itr = contList.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
				RequestByPOCReference newRef = new RequestByPOCReference();
				if(object[0] != null)
				newRef.setReq_contract_type_name(object[0].toString());
				else
					newRef.setReq_contract_type_name("NA");	
				if(object[1] != null)
					newRef.setReq_contract_date(sdfOut.format(sdfIn.parse(object[1].toString())));
				else
					newRef.setReq_contract_date("NA");	
				if(object[2] != null)
				newRef.setReq_contract_desc(object[2].toString());
				else
					newRef.setReq_contract_desc("NA");	
				newRef.setReq_contract_id(Integer.parseInt(object[4].toString()));
				newRef.setReq_cont_parties(requestByPOCDao.getAllPartyForRequest(Integer.parseInt(object[4].toString())));
				newRef.setReq_doc_list(requestByPOCDao.getAllRequestDocument(Integer.parseInt(object[4].toString()), "ContractRequest"));
				newRef.setReq_term_doc_list(requestByPOCDao.getAllRequestDocument(Integer.parseInt(object[4].toString()), "TermSheetContRequest"));
				newRef.setReq_contract_approval_status(Integer.parseInt(object[5].toString()));
				newRef.setReq_entity_name(object[6].toString());
				newRef.setReq_unit_name(object[7].toString());
				newRef.setReq_function_name(object[8].toString());
				newRef.setReq_added_by_name(userService.getUserFullNameById(Integer.parseInt(object[9].toString())));
				newRef.setReq_added_by(Integer.parseInt(object[9].toString()));
				newRef.setCreated_at(sdfOut.format(sdfIn.parse(object[10].toString())));
				ref.add(newRef);
			}
			return ref;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveQueryRequest(QueryRequest req, ArrayList<MultipartFile> query_doc, String status, HttpSession session) {
		try {
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			req.setReq_query_added_by(utilitiesService.getCurrentSessionUserId(session));
			req.setReq_query_created_at(utilitiesService.getCurrentDate());
			
			if(status.equals("Draft")){
				req.setReq_query_approval_status(4);	
			}else{
				req.setReq_query_approval_status(0);
			}
			int quer_req_id = requestByPOCDao.saveQueryRequest(req);
			
			if(!status.equals("Draft")){
				
			String email_subject = "Query Request Generated ";
			
		    String email_body  = " <h2 style='font-size:18px;'>Dear SPOC,</h2> ";
			   email_body += " <p style='text-align:justify;width:70%;'>"+userService.getUserFullNameById(Integer.parseInt(session.getAttribute("sess_user_id").toString()))+" generated query request. "
			   			+ "Kindly go through link by clicking on 'Id' and share your feedback on this. </p> "
		
						+ "<h2 style='font-size:16px;font-weight:bold;'>Contract Request Details :</h2>";
				email_body += "<table style='width:80%;' border='1'>" 
						+ "<thead>"
						+ "<tr style='background:#0B6EC3;color:#fff;'>"
						+ "<th>Id</th>"
						+ "<th>Entity</th>" 
						+ "<th>Unit</th>" 
						+ "<th>Function</th>"
						+ "<th>Query</th>"
						+ "<th>Query Date</th>" 
						+ "</tr>" + "</thead>" + "<tbody>";
				
				email_body += "<tr>" 
						+ "<td> <a href='"+project_url+"listQueryRequest'>1</a></td>" 
						+ "<td>" + organizationService.getOrganizationById(req.getReq_query_entity_id()).getOrga_name() + "</td>" 
						+ "<td>" + locationService.getLocationNameById(req.getReq_query_unit_id()) + "</td>" 	
						+ "<td>" + departmentService.getDepartmentNameById(req.getReq_query_function_id()) + "</td>" 
						+ "<td>" + req.getReq_query() + "</td>" 
						+ "<td>" + sdfOut.format(req.getReq_query_date()) + "</td>" 
						+ "</tr>";
			
				email_body += "</tbody>" + "</table>";
				
				email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
						+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
						+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
						+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
		
				//get mail ids of all SPOCs
				ArrayList<String> spoc = new ArrayList<String>(); 
				
				List<User> spoc_mail = schedulerService.getAllSPOCuser();
				
				Iterator<User> iterator1 = spoc_mail.iterator();
				while (iterator1.hasNext()) {
					User user = iterator1.next();
					if(!user.getUser_email().equals(""))
						spoc.add(user.getUser_email());
					//System.out.println("mail to:"+user.getUser_email());
				}
				
				 InternetAddress[] addresses = new InternetAddress[spoc.size()];
					try {
						for(int j=0; j < spoc.size(); j++){
						addresses[j] = new InternetAddress((String)spoc.get(j));
						}
					} catch (AddressException e) {
						e.printStackTrace();
					}	
		
					//get mail ids of all admin
					ArrayList<String> email = new ArrayList<String>(); 
					List<Object> mail_idCC = schedulerService.getAllAdminUser();
					Iterator<Object> iterator = mail_idCC.iterator();
					while (iterator.hasNext()) {
						Object object[] = (Object[]) iterator.next();
						if(object[1] != null)
						email.add(object[1].toString());
						//System.out.println("mail cc:"+object[1].toString());
						
					}
					
				InternetAddress[] addressCC = new InternetAddress[email.size()];
					try {
						for(int i = 0; i<email.size(); i++){
						addressCC[i] = new InternetAddress((String)email.get(i));
						}
					} catch (AddressException e) {
						e.printStackTrace();
					}	
	
			sendMailService.sendMail(email_subject, email_body, addresses, addressCC, String.valueOf(quer_req_id));
		}
			// Upload Documents Contract
			String originalFileName = null;
			String generatedFileName = null;
						int lastGeneratedValueDoc = requestByPOCDao.getLastGenerateValueForRequest(quer_req_id,"QueryRequest");
						for (int i = 0; i < query_doc.size(); i++) {
							MultipartFile file = query_doc.get(i);
							if (file.getSize() > 0) {
								File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
										+ "RequestDocuments");
								if (!dir.exists())
									dir.mkdirs();

								lastGeneratedValueDoc++;

								originalFileName = file.getOriginalFilename();
								generatedFileName = "uploadedQueryRequestDoc_" + quer_req_id + "_" + lastGeneratedValueDoc + "."
										+ file.getOriginalFilename().split("\\.")[1];
								File newFile = new File(dir.getPath() + File.separator + generatedFileName);
								if (!newFile.exists()) {
									newFile.createNewFile();
								}

								@SuppressWarnings("resource")
								OutputStream outputStream = new FileOutputStream(newFile);

								outputStream.write(file.getBytes());

								RequestDocument documents = new RequestDocument();

								documents.setReq_id(quer_req_id);
								documents.setReq_doc_original_file_name(originalFileName);
								documents.setReq_doc_generated_file_name(newFile.getAbsolutePath());
								documents.setReq_doc_last_generated_value_for_req_id(lastGeneratedValueDoc);
								documents.setReq_doc_created_at(utilitiesService.getCurrentDateWithTime());
								documents.setReq_doc_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
								documents.setReq_doc_document_type("QueryRequest");
								
								requestByPOCDao.save(documents);
							}
						}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<RequestByPOCReference> getAllListQueryRequest(HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			List<RequestByPOCReference> sendList = new ArrayList<>();
			
			List<Object> query = requestByPOCDao.getAllListQueryRequest(session);
			Iterator<Object> itr = query.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
 				
				RequestByPOCReference ref = new RequestByPOCReference();
				ref.setReq_query(object[0].toString());
				if(object[1] != null)
				ref.setReq_query_date_name(sdfOut.format(sdfIn.parse(object[1].toString())));
				else
					ref.setReq_query_date_name("NA");
				//ref.setReq_query_from(object[2].toString());
				ref.setReq_doc_list(requestByPOCDao.getAllRequestDocument(Integer.parseInt(object[3].toString()), "QueryRequest"));
				ref.setReq_query_approval_status(Integer.parseInt(object[4].toString()));
				ref.setReq_query_id(Integer.parseInt(object[3].toString()));
				ref.setReq_entity_name(object[5].toString());
				ref.setReq_unit_name(object[6].toString());
				ref.setReq_function_name(object[7].toString());
				ref.setReq_added_by_name(userService.getUserFullNameById(Integer.parseInt(object[8].toString())));
				ref.setReq_added_by(Integer.parseInt(object[8].toString()));
				if(object[9] != null)
				ref.setReq_query_criticality(object[9].toString());
				else
				ref.setReq_query_criticality("NA");	
				if(object[10] != null)
				ref.setReq_query_turnaround_time_name(sdfOut.format(sdfIn.parse(object[10].toString())));
				else
				ref.setReq_query_turnaround_time_name("NA");	
				sendList.add(ref);
			}
			return sendList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveLitigationRequest(LitigationRequest req, ArrayList<MultipartFile> liti_doc, String status,HttpSession session) {
		try {
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			req.setReq_liti_added_by(utilitiesService.getCurrentSessionUserId(session));
			req.setReq_liti_created_at(utilitiesService.getCurrentDate());
			if(!status.equals("Draft"))
				req.setReq_liti_approval_status(0);
			else
				req.setReq_liti_approval_status(4);
			int req_liti_id = requestByPOCDao.saveLitiRequest(req);
			
			if(!status.equals("Draft")){
			
				String email_subject = "Litigation Request Generated ";
			//get mail ids of all admin
			ArrayList<String> email = new ArrayList<String>(); 
			List<Object> mail_idCC = schedulerService.getAllAdminUser();
			Iterator<Object> iterator = mail_idCC.iterator();
			while (iterator.hasNext()) {
				Object object[] = (Object[]) iterator.next();
				if(object[1] != null)
				email.add(object[1].toString());
			}
			//get mail ids of all SPOCs
			ArrayList<String> spoc = new ArrayList<String>(); 
			
			List<User> spoc_mail = schedulerService.getLitigationSPOCuser();
			
			Iterator<User> iterator1 = spoc_mail.iterator();
			while (iterator1.hasNext()) {
				User user = iterator1.next();
				if(!user.getUser_email().equals(""))
					spoc.add(user.getUser_email());
			}
			
		    String email_body  = " <h2 style='font-size:18px;'>Dear SPOC,</h2> ";
			   email_body += " <p style='text-align:justify;width:70%;'>"+userService.getUserFullNameById(Integer.parseInt(session.getAttribute("sess_user_id").toString()))+" generated litigation request. "
			   			+ "Kindly go through link by clicking on 'Id' and share your feedback on this. </p> "
						+ "<h2 style='font-size:16px;font-weight:bold;'>Litigation Request Details :</h2>";
				email_body += "<table style='width:80%;' border='1'>" 
						+ "<thead>"
						+ "<tr style='background:#0B6EC3;color:#fff;'>"
						+ "<th>Id</th>"
						+ "<th>Entity</th>" 
						+ "<th>Unit</th>" 
						+ "<th>Function</th>"
						+ "<th>(Notice/Court order) Received Date</th>" 
						+ "<th>By Party</th>"
						+ "<th>Against Party</th>"
						+ "<th>Brief Description</th>" 
						+ "</tr>" + "</thead>" + "<tbody>";
				
				email_body += "<tr>" 
						+ "<td><a href='"+project_url+"listLitigationRequest'>1</a></td>" 
						+ "<td>" + organizationService.getOrganizationById(req.getReq_liti_entity_id()).getOrga_name() + "</td>" 
						+ "<td>" + locationService.getLocationNameById(req.getReq_liti_unit_id()) + "</td>" 	
						+ "<td>" + departmentService.getDepartmentNameById(req.getReq_liti_function_id()) + "</td>" 
						+ "<td>" + sdfOut.format(req.getReq_liti_received_date()) + "</td>" 
						+ "<td>" + req.getReq_liti_party_by() + "</td>" 
						+ "<td>" + req.getReq_liti_party_against() + "</td>" 
						+ "<td>" + req.getReq_liti_des() + "</td>" 
						+ "</tr>";
			
				email_body += "</tbody>" + "</table>";
				
				email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
						+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
						+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
						+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
		
				InternetAddress[] address = new InternetAddress[spoc.size()];
				try {
					for(int j=0; j < spoc.size(); j++){
					address[j] = new InternetAddress((String)spoc.get(j));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}	
	
				InternetAddress[] addressCC = new InternetAddress[email.size()];
				try {
					for(int i = 0; i<email.size(); i++){
					addressCC[i] = new InternetAddress((String)email.get(i));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}	
	
			sendMailService.sendMail(email_subject, email_body, address, addressCC, String.valueOf(req_liti_id));
			}
			// Upload Documents Contract
			String originalFileName = null;
			String generatedFileName = null;
						int lastGeneratedValueDoc = requestByPOCDao.getLastGenerateValueForRequest(req_liti_id,"LitiRequest");
						for (int i = 0; i < liti_doc.size(); i++) {
							MultipartFile file = liti_doc.get(i);
							if (file.getSize() > 0) {
								File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
										+ "RequestDocuments");
								if (!dir.exists())
									dir.mkdirs();

								lastGeneratedValueDoc++;

								originalFileName = file.getOriginalFilename();
								generatedFileName = "uploadedLitigationRequestDoc_" + req_liti_id + "_" + lastGeneratedValueDoc + "."
										+ file.getOriginalFilename().split("\\.")[1];
								File newFile = new File(dir.getPath() + File.separator + generatedFileName);
								if (!newFile.exists()) {
									newFile.createNewFile();
								}

								@SuppressWarnings("resource")
								OutputStream outputStream = new FileOutputStream(newFile);

								outputStream.write(file.getBytes());

								RequestDocument documents = new RequestDocument();

								documents.setReq_id(req_liti_id);
								documents.setReq_doc_original_file_name(originalFileName);
								documents.setReq_doc_generated_file_name(newFile.getAbsolutePath());
								documents.setReq_doc_last_generated_value_for_req_id(lastGeneratedValueDoc);
								documents.setReq_doc_created_at(utilitiesService.getCurrentDateWithTime());
								documents.setReq_doc_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
								documents.setReq_doc_document_type("LitiRequest");
								
								requestByPOCDao.save(documents);
							}	
						}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		
	}

	@Override
	public List<RequestByPOCReference> getAllListLitigationRequest(HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			List<RequestByPOCReference> sendList = new ArrayList<>();
			
			List<Object> liti = requestByPOCDao.getAllListLitigtionRequest(session);
			Iterator<Object> itr = liti.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
 				
				RequestByPOCReference ref = new RequestByPOCReference();
				if(object[0] != null)
					ref.setReq_liti_received_date_name(sdfOut.format(sdfIn.parse(object[0].toString())));
				else
					ref.setReq_liti_received_date_name("NA");
				if(object[1] != null)
				ref.setReq_liti_party_by(object[1].toString());
				else
					ref.setReq_liti_party_by("NA");
				if(object[2] != null)
					ref.setReq_liti_des(object[2].toString());
				else
					ref.setReq_liti_des("NA");
				ref.setReq_liti_approval_status(Integer.parseInt(object[4].toString()));
				ref.setReq_liti_id(Integer.parseInt(object[3].toString()));
				ref.setReq_entity_name(object[5].toString());
				ref.setReq_unit_name(object[6].toString());
				ref.setReq_function_name(object[7].toString());
				ref.setReq_added_by_name(userService.getUserFullNameById(Integer.parseInt(object[8].toString())));
				ref.setReq_doc_list(requestByPOCDao.getAllRequestDocument(Integer.parseInt(object[3].toString()), "LitiRequest"));
				ref.setReq_added_by(Integer.parseInt(object[8].toString()));
				if(object[9] != null)
					ref.setReq_liti_party_against(object[9].toString());
				else
					ref.setReq_liti_party_against("NA");	
				sendList.add(ref);
			}
			return sendList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int approveDisapproveRequest(int req_id, int req_status, String req_type, String reason, HttpSession session) {
		try {
			int status = requestByPOCDao.approveDisapproveRequest(req_id, req_status, req_type);
			
			if(req_status == 1){
				
				RequestRejectStatus req = new RequestRejectStatus();
				
				req.setReq_spoc_id(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
				req.setReq_spoc_comments(reason);
				req.setReq_created_at(utilitiesService.getCurrentDate());
				req.setReq_related_to(req_type);
				req.setReq_related_id(req_id);
				requestByPOCDao.save(req);
				
				String email_body = "";
				String subject ="";
				
					   subject += ""+req_type+" is disapproved";
					  // QueryRequest query_req = requestByPOCDao.getQueryRequestById(req_id);
				
				       email_body  += " <h2 style='font-size:18px;'>Dear User,</h2> ";
					   email_body += " <p style='text-align:justify;width:70%;'> "+req_type+/*" with "+ query_req.getReq_query() +*/" has been rejected by "+userService.getUserFullNameById(Integer.parseInt(session.getAttribute("sess_user_id").toString()))+" , reason being : "+reason+". "
					   		+ " Kindly go through link and share your feedback on this. </p> ";
				
					   email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
								+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
								+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
								+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
				
				List<User> admin_mail_id = userService.getAllBoth();
				Iterator<User> iterator = admin_mail_id.iterator();
				
				 ArrayList<String> email= new ArrayList<String>();
			      while(iterator.hasNext()) {
			    	  User user = iterator.next();
			                email.add(user.getUser_email());
			      }
				
			      InternetAddress[] address = new InternetAddress[email.size()];
			      for (int i = 0; i < email.size(); i++) {
			          address[i] = new InternetAddress(email.get(i).toString());
			      }
			
				sendMailService.sendMail(subject, email_body, address, address, String.valueOf(req_id));
				}
			
			if(req_status == 3){
				
				RequestRejectStatus req = requestByPOCDao.getAllRequestStatus(req_id, req_type, session);
				if(req != null){
				
				req.setReq_admin_id(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
				req.setReq_admin_comments(reason);
				req.setReq_admin_request_status(3);
				req.setReq_related_to(req.getReq_related_to());
				req.setReq_related_id(req.getReq_related_id());
				requestByPOCDao.update(req);
				}else{
					RequestRejectStatus request = new RequestRejectStatus();
					
					request.setReq_admin_id(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
					request.setReq_admin_comments(reason);
					request.setReq_admin_request_status(3);
					request.setReq_created_at(utilitiesService.getCurrentDate());
					request.setReq_related_to(req_type);
					request.setReq_related_id(req_id);
					requestByPOCDao.save(request);
				}
			int added_by_id = requestByPOCDao.getRequestedUserIdByReqId(req_id, req_type);
			
			String email_id = userService.getUserById(added_by_id).getUser_email();
			String email_body = "";
			String subject ="";
			
				   subject += ""+req_type+" is disapproved generated by you";
			       email_body  += " <h2 style='font-size:18px;'>Dear User,</h2> ";
				   email_body += " <p style='text-align:justify;width:70%;'> "+req_type+/*" with "+ query_req.getReq_query() +*/" has been rejected by "+userService.getUserFullNameById(Integer.parseInt(session.getAttribute("sess_user_id").toString()))+" , reason being : "+reason+" </p> ";
			
				   email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
							+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
							+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
							+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
			
				   InternetAddress[] address = new InternetAddress[1];
					try {
						address[0] = new InternetAddress(email_id);
					} catch (AddressException e) {
						e.printStackTrace();
					}		
			sendMailService.sendMail(subject, email_body, address, address, String.valueOf(req_id));
			}
			
			if(Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 1 && req_status == 1){
				RequestRejectStatus req = requestByPOCDao.getAllRequestStatus(req_id, req_type, session);
				
				req.setReq_admin_id(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
				req.setReq_admin_request_status(2);
				req.setReq_related_to(req.getReq_related_to());
				req.setReq_related_id(req.getReq_related_id());
				requestByPOCDao.update(req);
				
			}
			
			return status;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void downloadRequestDocument(int doc_id, HttpServletResponse response) {
		try {
			if (requestByPOCDao.getRequestDocumentFilePath(doc_id) != null) {
				File file = new File(requestByPOCDao.getRequestDocumentFilePath(doc_id));
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

	@SuppressWarnings("unchecked")
	@Override
	public String searchRequestQuery(int entity_id, int unit_id, int function_id, String from_date, String to_date,
			HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			String f_date = "";
			String t_date = "";
			
			if(!from_date.equals("") && !to_date.equals("")){
			
			 f_date = sdfIn.format(sdfOut.parse(from_date));
			 t_date = sdfIn.format(sdfOut.parse(to_date));
			}
			
			JSONArray json = new JSONArray();
			List<Object> query = requestByPOCDao.searchRequestQuery(entity_id, unit_id, function_id, f_date, t_date, session);
			Iterator<Object> iterator = query.iterator();
			while(iterator.hasNext()){
				
				Object[] obj = (Object[]) iterator.next();
				JSONObject jsonObj = new JSONObject();
				
				jsonObj.put("req_query", obj[0].toString());
				if(obj[1] != null)
				jsonObj.put("req_query_date", sdfOut.format(sdfIn.parse(obj[1].toString())));
				else
					jsonObj.put("req_query_date", "NA");	
				jsonObj.put("req_added_byName", userService.getUserFullNameById(Integer.parseInt(obj[2].toString())));
				jsonObj.put("added_by", obj[2].toString());
				jsonObj.put("orga_name", obj[3].toString());
				jsonObj.put("loca_name", obj[4].toString());
				//System.out.println("loca name is :"+obj[4].toString());
				jsonObj.put("dept_name", obj[5].toString());
				jsonObj.put("req_query_id", obj[6].toString());
				JSONArray doc_list = new JSONArray();
				List<RequestDocument> docs =  requestByPOCDao.getAllRequestDocument(Integer.parseInt(obj[6].toString()), "QueryRequest");
				if(docs != null){
				for (RequestDocument requestDocument : docs) {
					
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("doc_name", requestDocument.getReq_doc_original_file_name());
					jsonObject.put("doc_id", requestDocument.getReq_doc_id());
					doc_list.add(jsonObject);
				}
				}
				jsonObj.put("doc_list", doc_list);
				jsonObj.put("approval_status", obj[7].toString());
				jsonObj.put("role_id", session.getAttribute("sess_user_role").toString());
				jsonObj.put("sess_user_id", session.getAttribute("sess_user_id").toString());
				if(obj[8] != null)
				jsonObj.put("req_query_criticality", obj[8].toString());
				else
				jsonObj.put("req_query_criticality", "NA");
				if(obj[9] != null)
				jsonObj.put("req_query_turnaround_time_name", sdfOut.format(sdfIn.parse(obj[9].toString())));
				else
				jsonObj.put("req_query_turnaround_time_name", "NA");
				json.add(jsonObj);
			}
			return json.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String searchRequestLitigation(int entity_id, int unit_id, int function_id, String from_date, String to_date,
			HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			String f_date = "";
			String t_date = "";
			
			if(!from_date.equals("") && !to_date.equals("") ){
				
			f_date = sdfIn.format(sdfOut.parse(from_date));
			t_date = sdfIn.format(sdfOut.parse(to_date));
			
			}
			
			JSONArray json = new JSONArray();
			List<Object> query = requestByPOCDao.searchRequestLitigation(entity_id, unit_id, function_id, f_date, t_date, session);
			Iterator<Object> iterator = query.iterator();
			while(iterator.hasNext()){
				
				Object[] obj = (Object[]) iterator.next();
				JSONObject jsonObj = new JSONObject();
				if(obj[0] != null){
					jsonObj.put("req_liti_received_date", sdfOut.format(sdfIn.parse(obj[0].toString())));
				}else{
					jsonObj.put("req_liti_received_date", "NA");
				}
				if(obj[1] != null)
					jsonObj.put("party_by", obj[1].toString());
				else
					jsonObj.put("party_by", "NA");
				if(obj[2] != null)
					jsonObj.put("req_liti_des", obj[2].toString());
				else
					jsonObj.put("req_liti_des", "NA");	
				jsonObj.put("req_liti_id", obj[3].toString());
				jsonObj.put("req_added_byName", userService.getUserFullNameById(Integer.parseInt(obj[4].toString())));
				jsonObj.put("orga_name", obj[5].toString());
				jsonObj.put("loca_name", obj[6].toString());
				jsonObj.put("dept_name", obj[7].toString());
				JSONArray doc_list = new JSONArray();
				List<RequestDocument> allDocuments = requestByPOCDao.getAllRequestDocument(Integer.parseInt(obj[3].toString()), "LitiRequest");
				if(allDocuments != null){
				for (RequestDocument requestDocument : allDocuments) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("doc_name", requestDocument.getReq_doc_original_file_name());
					jsonObject.put("doc_id", requestDocument.getReq_doc_id());
					doc_list.add(jsonObject);
				}
				}
				jsonObj.put("doc_list", doc_list );				
				jsonObj.put("approval_status", obj[8].toString());
				jsonObj.put("role_id", session.getAttribute("sess_user_role").toString());
				jsonObj.put("added_by", Integer.parseInt(obj[4].toString()));
				jsonObj.put("sess_user_id", session.getAttribute("sess_user_id").toString());
				if(obj[9] != null)
					jsonObj.put("party_against", obj[9].toString());
				else
					jsonObj.put("party_against", "NA");	
				json.add(jsonObj);
			}
			return json.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String searchRequestContract(int entity_id, int unit_id, int function_id, String from_date, String to_date,
			HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			String f_date = "";
			String t_date = "";
			
			if(!from_date.equals("") && !to_date.equals("") ){
				
			f_date = sdfIn.format(sdfOut.parse(from_date));
			t_date = sdfIn.format(sdfOut.parse(to_date));
			
			}
			
			JSONArray json = new JSONArray();
			List<Object> query = requestByPOCDao.searchRequestContract(entity_id, unit_id, function_id, f_date, t_date, session);
			Iterator<Object> iterator = query.iterator();
			while(iterator.hasNext()){
				
				Object[] obj = (Object[]) iterator.next();
				
				JSONObject jsonObj = new JSONObject();
				if(obj[0] != null)
				jsonObj.put("contract_type_name", obj[0].toString());
				else
					jsonObj.put("contract_type_name", "NA");	
				if(obj[1] != null){
					jsonObj.put("req_contract_date", sdfOut.format(sdfIn.parse(obj[1].toString())));
				}else{
					jsonObj.put("req_contract_date", "NA");
				}
				//jsonObj.put("oppo_party", obj[1].toString());
				if(obj[2] != null){
				jsonObj.put("contract_desc", obj[2].toString());
				}else
					jsonObj.put("contract_desc", "NA");
				jsonObj.put("contract_id", obj[4].toString());
				jsonObj.put("req_added_byName", userService.getUserFullNameById(Integer.parseInt(obj[9].toString())));
				//jsonObj.put("cont_parties", requestByPOCDao.getAllPartyForRequest(Integer.parseInt(obj[4].toString())));
				jsonObj.put("orga_name", obj[6].toString());
				jsonObj.put("loca_name", obj[7].toString());
				jsonObj.put("dept_name", obj[8].toString());
				JSONArray doc_list = new JSONArray();
				List<RequestDocument> allDocuments = requestByPOCDao.getAllRequestDocument(Integer.parseInt(obj[4].toString()), "ContractRequest");
				if(allDocuments != null){
				for (RequestDocument requestDocument : allDocuments) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("doc_name", requestDocument.getReq_doc_original_file_name());
					jsonObject.put("doc_id", requestDocument.getReq_doc_id());
					doc_list.add(jsonObject);
				}
				}
				jsonObj.put("doc_list", doc_list );	
				
				JSONArray party_list = new JSONArray();
				List<ContractParties> allParties = requestByPOCDao.getAllPartyForRequest(Integer.parseInt(obj[4].toString()));
				if(allParties != null){
				for (ContractParties party : allParties) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("party_name", party.getCont_party_name());
					jsonObject.put("party_id", party.getCont_party_id());
					party_list.add(jsonObject);
				}
				}
				jsonObj.put("cont_parties", party_list);
				
				jsonObj.put("approval_status", obj[5].toString());
				jsonObj.put("role_id", session.getAttribute("sess_user_role").toString());
				jsonObj.put("added_by", Integer.parseInt(obj[9].toString()));
				jsonObj.put("sess_user_id", session.getAttribute("sess_user_id").toString());
				jsonObj.put("created_at", sdfOut.format(sdfIn.parse(obj[10].toString())));
				json.add(jsonObj);
			}
			return json.toJSONString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getQueryRaisedList(String related_to, int related_id) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			//List<Object> res = requestByPOCDao.listRaisedQueries(related_to, related_id);
			 List<Object> res = requestByPOCDao.listRaisedQueries(related_to, related_id);
			 for (Iterator<Object> iterator = res.iterator(); iterator.hasNext();) {
				Object[] object = (Object[]) iterator.next();
				if(object[8] != null){
				String raised_at = sdfOut.format(sdfIn.parse(object[8].toString()));
				object[8] = raised_at;
				}
				if(object[9] != null){
				String replied_at = sdfOut.format(sdfIn.parse(object[9].toString()));
				object[9] = replied_at;
				}
			}
			 return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String raiseQuery(String json,HttpSession session) {
		JSONObject res = new JSONObject();
		try {
			 JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
			 String query = jsonObject.get("query").toString();
			 int related_to_id = Integer.parseInt(jsonObject.get("query_related_id").toString());
			 String related_to = jsonObject.get("query_related_to").toString();
			 String admin_cc_status = jsonObject.get("admin_cc").toString();
			 
			 QueryRaised queryRaised = new QueryRaised();
			 queryRaised.setTrn_created_at(utilitiesService.getCurrentDateWithTime());
			 queryRaised.setTrn_query(query);
			 //queryRaised.setTrn_query_answer_at(utilitiesService.getCurrentDateWithTime());
			 queryRaised.setTrn_query_related_id(related_to_id);
			 queryRaised.setTrn_query_raised_by_id(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			 queryRaised.setTrn_query_related_to(related_to);
			 requestByPOCDao.save(queryRaised);
			 
			 String subject = "Query Raised against "+ related_to;
			 String email_body = "Dear User,";
			  email_body += " <p style='text-align:justify;width:70%;'>"+userService.getUserFullNameById(Integer.parseInt(session.getAttribute("sess_user_id").toString()))+" has generated query, '"+query+"' against "+related_to+" request. <br/>"
			   			+ "Kindly go through link and share your feedback on this. </p>"
			   			+ " <a href='"+ project_url +"listRaisedQuery?relatedTo="+related_to+"&id="+related_to_id+"'>link for raised query</a><br/><br/>";
			  email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
						+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
						+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
						+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
			 
			 	//get mail ids of all SPOCs
				ArrayList<String> spoc = new ArrayList<String>(); 
				if(Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 3){
					
					if(related_to.equals("litigation")){
					
					List<User> spoc_mail = schedulerService.getLitigationSPOCuser();
					Iterator<User> iterator1 = spoc_mail.iterator();
					while (iterator1.hasNext()) {
						User user = iterator1.next();
						if(!user.getUser_email().equals(""))
							spoc.add(user.getUser_email());
					}
					
					}else if(related_to.equals("contract")){
						List<User> spoc_mail = schedulerService.getContractSPOCuser();
						Iterator<User> iterator1 = spoc_mail.iterator();
						while (iterator1.hasNext()) {
							User user = iterator1.next();
							if(!user.getUser_email().equals(""))
								spoc.add(user.getUser_email());
						}
					}else{
						List<User> spoc_mail = schedulerService.getAllSPOCuser();
						Iterator<User> iterator1 = spoc_mail.iterator();
						while (iterator1.hasNext()) {
							User user = iterator1.next();
							if(!user.getUser_email().equals(""))
								spoc.add(user.getUser_email());
						}
					}
				 
					InternetAddress[] address = new InternetAddress[spoc.size()];
				      for (int i = 0; i < spoc.size(); i++) {
				          address[i] = new InternetAddress(spoc.get(i).toString());
				      }
				      
				      if(admin_cc_status.equals("Yes")){
				    	  
				    	//get mail ids of all admin
							ArrayList<String> email = new ArrayList<String>(); 
							List<Object> mail_idCC = schedulerService.getAllAdminUser();
							Iterator<Object> iterator = mail_idCC.iterator();
							while (iterator.hasNext()) {
								Object object[] = (Object[]) iterator.next();
								if(object[1] != null)
								email.add(object[1].toString());
							}
				    	  
							InternetAddress[] addressCC = new InternetAddress[email.size()];
						      for (int i = 0; i < email.size(); i++) {
						    	  addressCC[i] = new InternetAddress(email.get(i).toString());
						      }
						      sendMailService.sendMail(subject, email_body, address, addressCC, String.valueOf(1));
				      } else{
				    	  sendMailService.sendMail(subject, email_body, address, address, String.valueOf(1));
				      }
				      
				} else{
					
					if(related_to.equals("litigation")){
					
					LitigationRequest litiReq = getAllListLitigationRequest(related_to_id, session);
					String email = userService.getUserById(litiReq.getReq_liti_added_by()).getUser_email();
					InternetAddress[] address = new InternetAddress[1];
					try{
						if(!email.equals(""))
				        address[0] = new InternetAddress(email);
				      }catch(Exception e ){
				    	e.printStackTrace();  
				      }
					
					if(admin_cc_status.equals("Yes")){
				    	  
				    	//get mail ids of all admin
							ArrayList<String> emailcc = new ArrayList<String>(); 
							List<Object> mail_idCC = schedulerService.getAllAdminUser();
							Iterator<Object> iterator = mail_idCC.iterator();
							while (iterator.hasNext()) {
								Object object[] = (Object[]) iterator.next();
								if(object[1] != null)
								emailcc.add(object[1].toString());
							}
				    	  
							InternetAddress[] addressCC = new InternetAddress[emailcc.size()];
						      for (int i = 0; i < emailcc.size(); i++) {
						    	  addressCC[i] = new InternetAddress(emailcc.get(i).toString());
						      }
						      sendMailService.sendMail(subject, email_body, address, addressCC, String.valueOf(1));
				      } else{
				    	  sendMailService.sendMail(subject, email_body, address, address, String.valueOf(1));
				      }
					}
					
					if(related_to.equals("contract")){
						
						ContractRequest ContReq = getAllContractrequest(related_to_id, session);
						String email = userService.getUserById(ContReq.getReq_contract_added_by()).getUser_email();
						InternetAddress[] address = new InternetAddress[1];
						try{
							if(!email.equals(""))
					        address[0] = new InternetAddress(email);
					      }catch(Exception e ){
					    	e.printStackTrace();  
					      }
						
						if(admin_cc_status.equals("Yes")){
					    	  
					    	//get mail ids of all admin
								ArrayList<String> emailcc = new ArrayList<String>(); 
								List<Object> mail_idCC = schedulerService.getAllAdminUser();
								Iterator<Object> iterator = mail_idCC.iterator();
								while (iterator.hasNext()) {
									Object object[] = (Object[]) iterator.next();
									if(object[1] != null)
									emailcc.add(object[1].toString());
								}
					    	  
								InternetAddress[] addressCC = new InternetAddress[emailcc.size()];
							      for (int i = 0; i < emailcc.size(); i++) {
							    	  addressCC[i] = new InternetAddress(emailcc.get(i).toString());
							      }
							      sendMailService.sendMail(subject, email_body, address, addressCC, String.valueOf(related_to_id));
					      } else{
					    	  sendMailService.sendMail(subject, email_body, address, address, String.valueOf(related_to_id));
					      }
					}
					if(related_to.equals("notice")){
						
						LegalNoticeRequest notiReq = getAllNoticerequest(related_to_id, session);
						String email = userService.getUserById(notiReq.getReq_noti_added_by()).getUser_email();
						InternetAddress[] address = new InternetAddress[1];
						try{
							if(!email.equals(""))
					        address[0] = new InternetAddress(email);
					      }catch(Exception e ){
					    	e.printStackTrace();  
					      }
						
						if(admin_cc_status.equals("Yes")){
					    	  
					    	//get mail ids of all admin
								ArrayList<String> emailcc = new ArrayList<String>(); 
								List<Object> mail_idCC = schedulerService.getAllAdminUser();
								Iterator<Object> iterator = mail_idCC.iterator();
								while (iterator.hasNext()) {
									Object object[] = (Object[]) iterator.next();
									if(object[1] != null)
									emailcc.add(object[1].toString());
								}
					    	  
								InternetAddress[] addressCC = new InternetAddress[emailcc.size()];
							      for (int i = 0; i < emailcc.size(); i++) {
							    	  addressCC[i] = new InternetAddress(emailcc.get(i).toString());
							      }
							      sendMailService.sendMail(subject, email_body, address, addressCC, String.valueOf(related_to_id));
					      } else{
					    	  sendMailService.sendMail(subject, email_body, address, address, String.valueOf(related_to_id));
					      }
					}
				}
			 res.put("responseMessage", "success");
			 return res.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			 res.put("responseMessage", "fail");
			 return res.toJSONString();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String raiseQueryReply(String json, HttpSession session) {
		JSONObject res = new JSONObject();
		try {
			 JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
			 String queryreply = jsonObject.get("queryreply").toString();
			 int related_to_id = Integer.parseInt(jsonObject.get("query_id").toString());
             
			 QueryRaised queryRaised = requestByPOCDao.getRasiedQuery(related_to_id);
			 
			 queryRaised.setTrn_query_answer(queryreply);
			 queryRaised.setTrn_query_answer_by_id(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			 queryRaised.setTrn_query_answer_at(utilitiesService.getCurrentDateWithTime());
			 
			 requestByPOCDao.updateObject(queryRaised);
			 
			 String subject = "Query Replied against "+ queryRaised.getTrn_query_related_to();
			 String email_body = "Dear User,";
			  email_body += " <p style='text-align:justify;width:70%;'>"+userService.getUserFullNameById(Integer.parseInt(session.getAttribute("sess_user_id").toString()))+" has replied, '"+queryreply+"' to the query '"+queryRaised.getTrn_query()+"' against "+queryRaised.getTrn_query_related_to()+" request. <br/>"
			   			+ "Kindly go through link and share your feedback on this. </p>"
			   			+ " <a href='"+ project_url +"listRaisedQuery?relatedTo="+queryRaised.getTrn_query_related_to()+"&id="+queryRaised.getTrn_query_related_id()+"'>link for reply query</a><br/><br/>";
			  email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
						+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
						+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
						+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
			 
			  String query_raise_mail = userService.getUserById(queryRaised.getTrn_query_raised_by_id()).getUser_email();
			  
				InternetAddress[] address = new InternetAddress[1];
				try{
			    	  address[0] = new InternetAddress(query_raise_mail);
			      }catch(Exception e ){
				    	e.printStackTrace();  
			      }
				
				//get mail ids of all admin
				ArrayList<String> emailcc = new ArrayList<String>(); 
				List<Object> mail_idCC = schedulerService.getAllAdminUser();
				Iterator<Object> iterator = mail_idCC.iterator();
				while (iterator.hasNext()) {
					Object object[] = (Object[]) iterator.next();
					if(object[1] != null)
					emailcc.add(object[1].toString());
				}
	    	  
				InternetAddress[] addressCC = new InternetAddress[emailcc.size()];
			      for (int i = 0; i < emailcc.size(); i++) {
			    	  addressCC[i] = new InternetAddress(emailcc.get(i).toString());
			      }
				
			      sendMailService.sendMail(subject, email_body, address, addressCC, String.valueOf(related_to_id));
			 
			 res.put("responseMessage", "success");
			 return res.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			 res.put("responseMessage", "fail");
			 return res.toJSONString();
		}
	}
	
	@Override
	public LitigationRequest getAllListLitigationRequest(int liti_id, HttpSession session) {
		try {
			return requestByPOCDao.getAllListLitigationRequest(liti_id, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public QueryRequest getAllQueryRequest(int quer_id, HttpSession session) {
		try {
			return requestByPOCDao.getQueryRequestById(quer_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ContractRequest getAllContractrequest(int cont_id, HttpSession session) {
		try {
			return requestByPOCDao.getAllContractrequest(cont_id, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateQueryRequest(QueryRequest req, ArrayList<MultipartFile> query_doc, String status,
			HttpSession session) {
		try {
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			req.setReq_query_added_by(utilitiesService.getCurrentSessionUserId(session));
			req.setReq_query_created_at(utilitiesService.getCurrentDate());
			
			if(status.equals("Draft")){
				req.setReq_query_approval_status(4);	
			}else{
				req.setReq_query_approval_status(0);
			}
			int quer_req_id = requestByPOCDao.updateQueryRequest(req);
			
			if(!status.equals("Draft")){
				
			String email_subject = "Query Request Generated ";
			//get mail ids of all admin
			ArrayList<String> email = new ArrayList<String>(); 
			List<Object> mail_idCC = schedulerService.getAllAdminUser();
			Iterator<Object> iterator = mail_idCC.iterator();
			while (iterator.hasNext()) {
				Object object[] = (Object[]) iterator.next();
				if(object[1] != null)
				email.add(object[1].toString());
			}
			//get mail ids of all SPOCs
			ArrayList<String> spoc = new ArrayList<String>(); 
			
			List<User> spoc_mail = schedulerService.getAllSPOCuser();
			
			Iterator<User> iterator1 = spoc_mail.iterator();
			while (iterator1.hasNext()) {
				User user = iterator1.next();
				if(!user.getUser_email().equals(""))
					spoc.add(user.getUser_email());
			}
			
		    String email_body  = " <h2 style='font-size:18px;'>Dear SPOC,</h2> ";
			   email_body += " <p style='text-align:justify;width:70%;'>"+userService.getUserFullNameById(Integer.parseInt(session.getAttribute("sess_user_id").toString()))+" generated query request. "
			   			+ "Kindly go through link by clicking on 'Id' and share your feedback on this. </p> "
		
						+ "<h2 style='font-size:16px;font-weight:bold;'>Contract Request Details :</h2>";
				email_body += "<table style='width:80%;' border='1'>" 
						+ "<thead>"
						+ "<tr style='background:#0B6EC3;color:#fff;'>"
						+ "<th>Id</th>"
						+ "<th>Entity</th>" 
						+ "<th>Unit</th>" 
						+ "<th>Function</th>"
						+ "<th>Query</th>"
						+ "<th>Query Date</th>" 
						+ "</tr>" + "</thead>" + "<tbody>";
				
				email_body += "<tr>" 
						+ "<td> <a href='"+project_url+"listQueryRequest'>1</a></td>" 
						+ "<td>" + organizationService.getOrganizationById(req.getReq_query_entity_id()).getOrga_name() + "</td>" 
						+ "<td>" + locationService.getLocationNameById(req.getReq_query_unit_id()) + "</td>" 	
						+ "<td>" + departmentService.getDepartmentNameById(req.getReq_query_function_id()) + "</td>" 
						+ "<td>" + req.getReq_query() + "</td>" 
						+ "<td>" + sdfOut.format(req.getReq_query_date()) + "</td>" 
						+ "</tr>";
			
				email_body += "</tbody>" + "</table>";
				
				email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
						+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
						+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
						+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
		
				 InternetAddress[] address = new InternetAddress[spoc.size()];
					try {
						for(int j=0; j < spoc.size(); j++){
						address[j] = new InternetAddress((String)spoc.get(j));
						}
					} catch (AddressException e) {
						e.printStackTrace();
					}	
		
				InternetAddress[] addressCC = new InternetAddress[email.size()];
					try {
						for(int i = 0; i<email.size(); i++){
						addressCC[i] = new InternetAddress((String)email.get(i));
						}
					} catch (AddressException e) {
						e.printStackTrace();
					}	
	
			sendMailService.sendMail(email_subject, email_body, address, addressCC, String.valueOf(quer_req_id));
		}
			// Upload Documents Contract
			String originalFileName = null;
			String generatedFileName = null;
						int lastGeneratedValueDoc = requestByPOCDao.getLastGenerateValueForRequest(quer_req_id,"QueryRequest");
						for (int i = 0; i < query_doc.size(); i++) {
							MultipartFile file = query_doc.get(i);
							if (file.getSize() > 0) {
								File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
										+ "RequestDocuments");
								if (!dir.exists())
									dir.mkdirs();

								lastGeneratedValueDoc++;

								originalFileName = file.getOriginalFilename();
								generatedFileName = "uploadedQueryRequestDoc_" + quer_req_id + "_" + lastGeneratedValueDoc + "."
										+ file.getOriginalFilename().split("\\.")[1];
								File newFile = new File(dir.getPath() + File.separator + generatedFileName);
								if (!newFile.exists()) {
									newFile.createNewFile();
								}

								@SuppressWarnings("resource")
								OutputStream outputStream = new FileOutputStream(newFile);

								outputStream.write(file.getBytes());

								RequestDocument documents = new RequestDocument();

								documents.setReq_id(quer_req_id);
								documents.setReq_doc_original_file_name(originalFileName);
								documents.setReq_doc_generated_file_name(newFile.getAbsolutePath());
								documents.setReq_doc_last_generated_value_for_req_id(lastGeneratedValueDoc);
								documents.setReq_doc_created_at(utilitiesService.getCurrentDateWithTime());
								documents.setReq_doc_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
								documents.setReq_doc_document_type("QueryRequest");
								
								requestByPOCDao.save(documents);
							}
						}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateLitiRequest(LitigationRequest req, ArrayList<MultipartFile> liti_doc, String status,
			HttpSession session) {
		try {
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			req.setReq_liti_added_by(utilitiesService.getCurrentSessionUserId(session));
			req.setReq_liti_created_at(utilitiesService.getCurrentDate());
			if(!status.equals("Draft"))
				req.setReq_liti_approval_status(0);
			else
				req.setReq_liti_approval_status(4);
			
			int req_liti_id = req.getReq_liti_id();
			requestByPOCDao.update(req);
			
			if(!status.equals("Draft")){
			
				String email_subject = "Litigation Request Generated ";
			//get mail ids of all admin
			ArrayList<String> email = new ArrayList<String>(); 
			List<Object> mail_idCC = schedulerService.getAllAdminUser();
			Iterator<Object> iterator = mail_idCC.iterator();
			while (iterator.hasNext()) {
				Object object[] = (Object[]) iterator.next();
				if(object[1] != null)
				email.add(object[1].toString());
			}
			//get mail ids of all SPOCs
			ArrayList<String> spoc = new ArrayList<String>(); 
			
			List<User> spoc_mail = schedulerService.getLitigationSPOCuser();
			
			Iterator<User> iterator1 = spoc_mail.iterator();
			while (iterator1.hasNext()) {
				User user = iterator1.next();
				if(!user.getUser_email().equals(""))
					spoc.add(user.getUser_email());
			}
			
		    String email_body  = " <h2 style='font-size:18px;'>Dear SPOC,</h2> ";
			   email_body += " <p style='text-align:justify;width:70%;'>"+userService.getUserFullNameById(Integer.parseInt(session.getAttribute("sess_user_id").toString()))+" generated litigation request. "
			   			+ "Kindly go through link by clicking on 'Id' and share your feedback on this. </p> "
						+ "<h2 style='font-size:16px;font-weight:bold;'>Litigation Request Details :</h2>";
				email_body += "<table style='width:80%;' border='1'>" 
						+ "<thead>"
						+ "<tr style='background:#0B6EC3;color:#fff;'>"
						+ "<th>Id</th>"
						+ "<th>Entity</th>" 
						+ "<th>Unit</th>" 
						+ "<th>Function</th>"
						+ "<th>(Notice/Court order) Received Date</th>" 
						+ "<th>By Party</th>"
						+ "<th>Against Party</th>"
						+ "<th>Brief Description</th>" 
						+ "</tr>" + "</thead>" + "<tbody>";
				
				email_body += "<tr>" 
						+ "<td> <a href='"+project_url+"listLitigationRequest'>1</a></td>" 
						+ "<td>" + organizationService.getOrganizationById(req.getReq_liti_entity_id()).getOrga_name() + "</td>" 
						+ "<td>" + locationService.getLocationNameById(req.getReq_liti_unit_id()) + "</td>" 	
						+ "<td>" + departmentService.getDepartmentNameById(req.getReq_liti_function_id()) + "</td>" 
						+ "<td>" + sdfOut.format(req.getReq_liti_received_date()) + "</td>" 
						+ "<td>" + req.getReq_liti_party_by() + "</td>" 
						+ "<td>" + req.getReq_liti_party_against() + "</td>" 
						+ "<td>" + req.getReq_liti_des() + "</td>" 
						+ "</tr>";
			
				email_body += "</tbody>" + "</table>";
				
				email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
						+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
						+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
						+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
		
				InternetAddress[] address = new InternetAddress[spoc.size()];
				try {
					for(int j=0; j < spoc.size(); j++){
					address[j] = new InternetAddress((String)spoc.get(j));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}	
	
				InternetAddress[] addressCC = new InternetAddress[email.size()];
				try {
					for(int i = 0; i<email.size(); i++){
					addressCC[i] = new InternetAddress((String)email.get(i));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}	
				//System.out.println("cc is: "+addressCC+" to is: "+address);
			sendMailService.sendMail(email_subject, email_body, address, addressCC, String.valueOf(req_liti_id));
			}
			// Upload Documents Contract
			String originalFileName = null;
			String generatedFileName = null;
						int lastGeneratedValueDoc = requestByPOCDao.getLastGenerateValueForRequest(req_liti_id,"LitiRequest");
						for (int i = 0; i < liti_doc.size(); i++) {
							MultipartFile file = liti_doc.get(i);
							if (file.getSize() > 0) {
								File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
										+ "RequestDocuments");
								if (!dir.exists())
									dir.mkdirs();

								lastGeneratedValueDoc++;

								originalFileName = file.getOriginalFilename();
								generatedFileName = "uploadedLitigationRequestDoc_" + req_liti_id + "_" + lastGeneratedValueDoc + "."
										+ file.getOriginalFilename().split("\\.")[1];
								File newFile = new File(dir.getPath() + File.separator + generatedFileName);
								if (!newFile.exists()) {
									newFile.createNewFile();
								}

								@SuppressWarnings("resource")
								OutputStream outputStream = new FileOutputStream(newFile);

								outputStream.write(file.getBytes());

								RequestDocument documents = new RequestDocument();

								documents.setReq_id(req_liti_id);
								documents.setReq_doc_original_file_name(originalFileName);
								documents.setReq_doc_generated_file_name(newFile.getAbsolutePath());
								documents.setReq_doc_last_generated_value_for_req_id(lastGeneratedValueDoc);
								documents.setReq_doc_created_at(utilitiesService.getCurrentDateWithTime());
								documents.setReq_doc_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
								documents.setReq_doc_document_type("LitiRequest");
								
								requestByPOCDao.save(documents);
							}	
						}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ContractRequestReference getContractrequestById(int cont_id, HttpSession session) {
		try {
			ContractRequest req = requestByPOCDao.getAllContractrequest(cont_id, session);
			
			ContractRequestReference ref = new ContractRequestReference();
			ref.setReq_contract_damage(req.getReq_contract_damage());
			ref.setReq_contract_date(req.getReq_contract_date());
			ref.setReq_contract_desc(req.getReq_contract_desc());
			ref.setReq_contract_end_date(req.getReq_contract_end_date());
			ref.setReq_contract_entity_id(req.getReq_contract_entity_id());
			ref.setReq_contract_function_id(req.getReq_contract_function_id());
			ref.setReq_contract_id(req.getReq_contract_id());
			ref.setReq_contract_insurance(req.getReq_contract_insurance());
			ref.setReq_contract_jurisdiction(req.getReq_contract_jurisdiction());
			ref.setReq_contract_major_clause(req.getReq_contract_major_clause());
			ref.setReq_contract_name(req.getReq_contract_name());
			ref.setReq_contract_notice_period(req.getReq_contract_notice_period());
			ref.setReq_contract_perform_rel_payment(req.getReq_contract_perform_rel_payment());
			ref.setReq_contract_start_date(req.getReq_contract_start_date());
			ref.setReq_contract_surviving_clause(req.getReq_contract_surviving_clause());
			ref.setReq_contract_total_value(req.getReq_contract_total_value());
			ref.setReq_contract_type(req.getReq_contract_type());
			ref.setReq_contract_unit_id(req.getReq_contract_unit_id());
			ref.setReq_party_no(req.getReq_party_no());
			ref.setCont_parties(requestByPOCDao.getAllPartyForRequest(req.getReq_contract_id()));
			ref.setReq_contract_email_id(req.getReq_contract_email_id());
			ref.setReq_contract_criticality(req.getReq_contract_criticality());
			ref.setReq_contract_expected_date(req.getReq_contract_expected_date());
			
			return ref;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateContractRequest(ContractRequestReference req, ArrayList<String> additional_parties,
			ArrayList<MultipartFile> contract_doc, ArrayList<MultipartFile> term_sheet_doc, HttpSession session,
			String status) {
		try {
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			ContractRequest contreq = requestByPOCDao.getAllContractrequest(req.getReq_contract_id(), session);
			
			if(!req.getReq_contract_major_clause().equals(""))
				contreq.setReq_contract_major_clause(req.getReq_contract_major_clause());
			else
				contreq.setReq_contract_major_clause("No");	
			
			if(!req.getReq_contract_surviving_clause().equals(""))
				contreq.setReq_contract_surviving_clause(req.getReq_contract_surviving_clause());
			else
				contreq.setReq_contract_surviving_clause("No");	
			
			if(!req.getReq_contract_perform_rel_payment().equals(""))
				contreq.setReq_contract_perform_rel_payment(req.getReq_contract_perform_rel_payment());
			else
				contreq.setReq_contract_perform_rel_payment("No");	
			
			if(!req.getReq_contract_damage().equals(""))
				contreq.setReq_contract_damage(req.getReq_contract_damage());
			else
				contreq.setReq_contract_damage("No");
			
			if(!req.getReq_contract_insurance().equals(""))
				contreq.setReq_contract_insurance(req.getReq_contract_insurance());
			else
				contreq.setReq_contract_insurance("No");	
			
			if(!req.getReq_contract_notice_period().equals(""))
				contreq.setReq_contract_notice_period(req.getReq_contract_notice_period());
			else
				contreq.setReq_contract_notice_period("No");
			
			if(status.equals("Pending"))
				contreq.setReq_contract_approval_status(0);
				else
				contreq.setReq_contract_approval_status(4);
			
			//contreq.setReq_contract_damage(req.getReq_contract_damage());
			contreq.setReq_contract_date(req.getReq_contract_date());
			contreq.setReq_contract_desc(req.getReq_contract_desc());
			contreq.setReq_contract_end_date(req.getReq_contract_end_date());
			contreq.setReq_contract_entity_id(req.getReq_contract_entity_id());
			contreq.setReq_contract_function_id(req.getReq_contract_function_id());
			contreq.setReq_contract_id(req.getReq_contract_id());
			//contreq.setReq_contract_insurance(req.getReq_contract_insurance());
			contreq.setReq_contract_jurisdiction(req.getReq_contract_jurisdiction());
			//contreq.setReq_contract_major_clause(req.getReq_contract_major_clause());
			contreq.setReq_contract_name(req.getReq_contract_name());
			//contreq.setReq_contract_notice_period(req.getReq_contract_notice_period());
			//ontreq.setReq_contract_perform_rel_payment(req.getReq_contract_perform_rel_payment());
			contreq.setReq_contract_start_date(req.getReq_contract_start_date());
			//contreq.setReq_contract_surviving_clause(req.getReq_contract_surviving_clause());
			contreq.setReq_contract_total_value(req.getReq_contract_total_value());
			contreq.setReq_contract_type(req.getReq_contract_type());
			contreq.setReq_contract_unit_id(req.getReq_contract_unit_id());
			contreq.setReq_party_no(req.getReq_party_no());
			contreq.setReq_contract_email_id(req.getReq_contract_email_id());
			contreq.setReq_contract_criticality(req.getReq_contract_criticality());
			contreq.setReq_contract_expected_date(req.getReq_contract_expected_date());
			requestByPOCDao.update(contreq);
			int req_id = req.getReq_contract_id();
			
			List<ContractParties> parties = req.getCont_parties();
			if (parties != null && parties.size() > 0) {
				for (ContractParties contractParties : parties) {
					ContractParties oldDataParty = executedContractDao.getPartyById(contractParties.getCont_party_id());
					// Assign old data to new party data
					contractParties.setCont_party_type(oldDataParty.getCont_party_type());
					contractParties.setCont_party_contract_id(oldDataParty.getCont_party_contract_id());
					contractParties.setCont_party_series(oldDataParty.getCont_party_series());
					contractParties.setCont_party_added_by(oldDataParty.getCont_party_added_by());
					contractParties.setCont_party_created_at(oldDataParty.getCont_party_created_at());
					requestByPOCDao.update(contractParties);
				}
			}
			if(additional_parties != null){
			for (int i = 0; i < additional_parties.size(); i++) {
				ContractParties contractParties = new ContractParties();
				contractParties.setCont_party_contract_id(req_id);
				contractParties.setCont_party_name(additional_parties.get(i));
				contractParties.setCont_party_type(3);
				contractParties.setCont_party_created_at(utilitiesService.getCurrentDateWithTime());
				contractParties.setCont_party_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
				requestByPOCDao.save(contractParties);
				// System.out.println("Party "+additional_parties.get(i));
			}
			}
		if(req.getReq_contract_approval_status() != 4){
				
			String email_subject = "Contract Request Generated ";
			
			//get mail ids of all admin
			ArrayList<String> email = new ArrayList<String>(); 
			List<Object> mail_idCC = schedulerService.getAllAdminUser();
			Iterator<Object> iterator = mail_idCC.iterator();
			while (iterator.hasNext()) {
				Object object[] = (Object[]) iterator.next();
				if(object[1] != null)
				email.add(object[1].toString());
			}
			//get mail ids of all SPOCs
			ArrayList<String> spoc = new ArrayList<String>(); 
			
			List<User> spoc_mail = schedulerService.getContractSPOCuser();
			
			Iterator<User> iterator1 = spoc_mail.iterator();
			while (iterator1.hasNext()) {
				User user = iterator1.next();
				if(!user.getUser_email().equals(""))
					spoc.add(user.getUser_email());
			}
			
		    String email_body  = " <h2 style='font-size:18px;'>Dear SPOC,</h2> ";
			   email_body += " <p style='text-align:justify;width:70%;'>"+userService.getUserFullNameById(Integer.parseInt(session.getAttribute("sess_user_id").toString()))+" generated contract request. "
			   			+ "Kindly go through link by clicking on 'Id' and share your feedback on this. </p> "
		
						+ "<h2 style='font-size:16px;font-weight:bold;'>Contract Request Details :</h2>";
				email_body += "<table style='width:80%;' border='1'>" 
						+ "<thead>"
						+ "<tr style='background:#0B6EC3;color:#fff;'>"
						+ "<th>Id</th>"
						+ "<th>Entity</th>" 
						+ "<th>Unit</th>" 
						+ "<th>Function</th>"
						+ "<th>Contract Date</th>"
						//+ "<th>Party</th>"
						+ "<th>Description</th>"
						+ "<th>Major Clauses</th>"
						+ "</tr>" + "</thead>" + "<tbody>";
				String cont_date= "";
				if(req.getReq_contract_date() != null){
				 cont_date = sdfOut.format(req.getReq_contract_date());
				}else{
				 cont_date = "NA";	
				}
				email_body += "<tr>" 
						+ "<td> <a href='"+project_url+"listContractRequest'>1</a></td>" 
						+ "<td>" + organizationService.getOrganizationById(req.getReq_contract_entity_id()).getOrga_name() + "</td>" 
						+ "<td>" + locationService.getLocationNameById(req.getReq_contract_unit_id()) + "</td>" 	
						+ "<td>" + departmentService.getDepartmentNameById(req.getReq_contract_function_id()) + "</td>" 
						+ "<td>" + cont_date + "</td>" 
						//+ "<td>" + requestByPOCDao.getAllPartyForRequest(Integer.parseInt(object[4].toString())) +"</td>"
						+ "<td>" + req.getReq_contract_desc() + "</td>" 
						+ "<td>" + req.getReq_contract_major_clause() + "</td>" 
						+ "</tr>";
			
				email_body += "</tbody>" + "</table>";
				
				email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
						+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
						+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
						+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
		
			   InternetAddress[] address = new InternetAddress[spoc.size()];
				try {
					for(int j=0; j < spoc.size(); j++){
					address[j] = new InternetAddress((String)spoc.get(j));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}	
	
				 InternetAddress[] addressCC = new InternetAddress[email.size()];
				try {
					for(int i = 0; i<email.size(); i++){
					addressCC[i] = new InternetAddress((String)email.get(i));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}	
			sendMailService.sendMail(email_subject, email_body, address, addressCC, String.valueOf(req_id));
			
			}
			// Upload Documents Contract
			String originalFileName = null;
			String generatedFileName = null;
						int lastGeneratedValueDoc = requestByPOCDao.getLastGenerateValueForRequest(req_id,"ContractRequest");
						for (int i = 0; i < contract_doc.size(); i++) {
							MultipartFile file = contract_doc.get(i);
							if (file.getSize() > 0) {
								File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
										+ "RequestDocuments");
								if (!dir.exists())
									dir.mkdirs();

								lastGeneratedValueDoc++;

								originalFileName = file.getOriginalFilename();
								generatedFileName = "uploadedContractRequestDoc_" + req_id + "_" + lastGeneratedValueDoc + "."
										+ file.getOriginalFilename().split("\\.")[1];
								File newFile = new File(dir.getPath() + File.separator + generatedFileName);
								if (!newFile.exists()) {
									newFile.createNewFile();
								}

								@SuppressWarnings("resource")
								OutputStream outputStream = new FileOutputStream(newFile);

								outputStream.write(file.getBytes());

								RequestDocument documents = new RequestDocument();

								documents.setReq_id(req_id);
								documents.setReq_doc_original_file_name(originalFileName);
								documents.setReq_doc_generated_file_name(newFile.getAbsolutePath());
								documents.setReq_doc_last_generated_value_for_req_id(lastGeneratedValueDoc);
								documents.setReq_doc_created_at(utilitiesService.getCurrentDateWithTime());
								documents.setReq_doc_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
								documents.setReq_doc_document_type("ContractRequest");
								
								requestByPOCDao.save(documents);
							}
						}
						
						//upload term sheet document
						int lastGeneratedValueTermDoc = requestByPOCDao.getLastGenerateValueForRequest(req_id,"TermSheetContRequest");
						for (int i = 0; i < term_sheet_doc.size(); i++) {
							MultipartFile file = term_sheet_doc.get(i);
							if (file.getSize() > 0) {
								File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
										+ "RequestDocuments");
								if (!dir.exists())
									dir.mkdirs();

								lastGeneratedValueTermDoc++;

								originalFileName = file.getOriginalFilename();
								generatedFileName = "uploadedTermSheetRequestDoc_" + req_id + "_" + lastGeneratedValueTermDoc + "."
										+ file.getOriginalFilename().split("\\.")[1];
								File newFile = new File(dir.getPath() + File.separator + generatedFileName);
								if (!newFile.exists()) {
									newFile.createNewFile();
								}

								@SuppressWarnings("resource")
								OutputStream outputStream = new FileOutputStream(newFile);

								outputStream.write(file.getBytes());

								RequestDocument documents = new RequestDocument();

								documents.setReq_id(req_id);
								documents.setReq_doc_original_file_name(originalFileName);
								documents.setReq_doc_generated_file_name(newFile.getAbsolutePath());
								documents.setReq_doc_last_generated_value_for_req_id(lastGeneratedValueTermDoc);
								documents.setReq_doc_created_at(utilitiesService.getCurrentDateWithTime());
								documents.setReq_doc_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
								documents.setReq_doc_document_type("TermSheetContRequest");
								
								requestByPOCDao.save(documents);
							}
						}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveContractStatusForNegotiation(PocStatusForNegotiation poc, ArrayList<MultipartFile> contract_doc,
			HttpSession session) {
		try {
			poc.setPoc_status_created_at(utilitiesService.getCurrentDate());
			poc.setPoc_status_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			poc.setPoc_contract_req_id(poc.getPoc_contract_req_id());
			//set contract id 
			Contract cont = contractDao.getContractByRequestId(poc.getPoc_contract_req_id());
			poc.setPoc_contract_id(cont.getCont_id());
			int status_id = requestByPOCDao.savePocStatus(poc);
			
			String originalFileName = null;
			String generatedFileName = null;
			//upload term sheet document
			int lastGeneratedValueTermDoc = requestByPOCDao.getLastGenerateValueForPocDoc(status_id);
			for (int i = 0; i < contract_doc.size(); i++) {
				MultipartFile file = contract_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "POCDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueTermDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedPocDoc_" + status_id + "_" + lastGeneratedValueTermDoc + "."
							+ file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());
					
					PocNegotiationDocument pocDoc = new PocNegotiationDocument();
					pocDoc.setPoc_doc_contract_id(status_id);
					pocDoc.setPoc_doc_generated_file_name(newFile.getAbsolutePath());
					pocDoc.setPoc_doc_last_generated_value_for_contract_id(lastGeneratedValueTermDoc);
					pocDoc.setPoc_doc_original_file_name(originalFileName);
					pocDoc.setPoc_document_type("SentByPOC");
					pocDoc.setPoc_doc_created_at(utilitiesService.getCurrentDateWithTime());
					pocDoc.setPoc_doc_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
					
					requestByPOCDao.save(pocDoc);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<PocStatusNegotiationReference> getAllContractStatusNegotiation(int id) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			List<PocStatusNegotiationReference> newPoc =  new ArrayList<>();
			
			List<Object> allStatus = requestByPOCDao.getAllPocStatusForNegotiation(id);
			Iterator<Object> iterator = allStatus.iterator();
			while (iterator.hasNext()) {
				Object[] object = (Object[]) iterator.next();
				PocStatusNegotiationReference poc = new PocStatusNegotiationReference();
				poc.setPoc_status_id(Integer.parseInt(object[0].toString()));
				poc.setPoc_action_item(object[1].toString());
				poc.setPoc_status(object[2].toString());
				poc.setPoc_status_date_name(sdfOut.format(sdfIn.parse(object[3].toString())));
				poc.setPoc_contract_req_id(Integer.parseInt(object[6].toString()));
				poc.setPoc_contract_id(Integer.parseInt(object[7].toString()));
				poc.setPoc_status_added_by_name(userService.getUserFullNameById(Integer.parseInt(object[5].toString())));
				poc.setPocDoc(requestByPOCDao.getAllPocStatusDocument(Integer.parseInt(object[0].toString())));
				newPoc.add(poc);
			}
			return newPoc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void downloadPOCDocument(int doc_id, HttpServletResponse response) {
		try {
			if (requestByPOCDao.getPocDocumentFilePath(doc_id) != null) {
				File file = new File(requestByPOCDao.getPocDocumentFilePath(doc_id));
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

	@Override
	public String approveDisapproveStatusSentNegotiation(int req_id, int req_status, String reason,
			HttpSession session) {
		try {
			//SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			PocStatusForNegotiation poc = requestByPOCDao.getPocStatusByStatusId(req_id);	
			String name = userService.getUserFullNameById(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			if(req_status == 1){
			
			/***************** Update status of accepting request **********************/
			
			poc.setPoc_status("Accepted");	
			requestByPOCDao.update(poc);
			
			/****** Send mail to POC (SPOC in cc) that req has been accepted by admin ********/
			String subject = "Contract document sent for Negotiation is Accepted";
			
			ContractRequest req = requestByPOCDao.getContractRequestById(poc.getPoc_contract_req_id());
			String poc_email = userService.getUserById(req.getReq_contract_added_by()).getUser_email();
			
			String email_body = "<h2 style='font-size:18px;'>Dear "+userService.getUserFullNameById(req.getReq_contract_added_by())+",</h2><br/>";
				   email_body += "<p style='text-align:justify;width:70%;'>"+name+" has accepted the document which is sent for negotiation."
			   			//+ "Kindly go through link by clicking on 'Id' and share your feedback on this. </p>"
					+ "<h2 style='font-size:16px;font-weight:bold;'>Contract Request Details :</h2>";
				   email_body += "<table style='width:80%;' border='1'>" 
							+ "<thead>"
							+ "<tr style='background:#0B6EC3;color:#fff;'>"
							+ "<th>Id</th>"
							+ "<th>Action item</th>" 
							+ "<th>Status</th>" 
							+ "<th>Status date</th>"
							+ "<th>Added by</th>"
							+ "</tr>" + "</thead>" + "<tbody>";
					
					email_body += "<tr>" 
							+ "<td>1</td>" 
							+ "<td>" + poc.getPoc_action_item() + "</td>" 
							+ "<td>" + poc.getPoc_status() + "</td>" 	
							+ "<td>" + sdfOut.format(poc.getPoc_status_date()) + "</td>" 
							+ "<td>" + userService.getUserFullNameById(poc.getPoc_status_added_by()) + "</td>" 
							+ "</tr>";
				
					email_body += "</tbody>" + "</table>";
					
					email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
							+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
							+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
							+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
				   
			//get mail ids of all SPOCs
			ArrayList<String> spoc = new ArrayList<String>(); 
			List<User> spoc_mail = schedulerService.getContractSPOCuser();
			Iterator<User> iterator1 = spoc_mail.iterator();
			while (iterator1.hasNext()) {
				User user = iterator1.next();
				if(!user.getUser_email().equals(""))
					spoc.add(user.getUser_email());
				}
			 	InternetAddress[] addressCC = new InternetAddress[spoc.size()];
				try {
					for(int i = 0; i<spoc.size(); i++){
					addressCC[i] = new InternetAddress((String)spoc.get(i));
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
			
			PocNegotiationDocument pocDoc = requestByPOCDao.getPocDocumentById(poc.getPoc_status_id());	
			
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
					message.setRecipients(Message.RecipientType.TO, addressTo);
					message.setRecipients(Message.RecipientType.CC, addressCC);
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
					if(pocDoc.getPoc_doc_generated_file_name() != null){
					messageBodyPart = new MimeBodyPart();
					
					DataSource source = new FileDataSource(pocDoc.getPoc_doc_generated_file_name());
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(pocDoc.getPoc_doc_original_file_name());
					multipart.addBodyPart(messageBodyPart);
					}
					// Send the complete message parts
					message.setContent(multipart);
					Transport.send(message);

					for (int i = 0; i < addressCC.length; i++) {
						utilitiesService.addMailToLog(String.valueOf(addressTo), String.valueOf(addressCC[i]), subject, String.valueOf(poc.getPoc_status_id()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				//PocStatusForNegotiation newPoc = new PocStatusForNegotiation();	
				
				poc.setPoc_status("Rejected");
				requestByPOCDao.update(poc);
				
				/*newPoc.setPoc_action_item(reason);
				newPoc.setPoc_contract_id(poc.getPoc_contract_id());
				newPoc.setPoc_contract_req_id(poc.getPoc_contract_req_id());
				newPoc.setPoc_status_added_by(utilitiesService.getCurrentSessionUserId(session));
				newPoc.setPoc_status_created_at(utilitiesService.getCurrentDateWithTime());
				newPoc.setPoc_status_date(utilitiesService.getCurrentDateWithTime());
				int status_id = requestByPOCDao.savePocStatus(newPoc);*/
			
				/****** Send mail to POC (SPOC in cc) that req has been accepted by admin ********/
				String subject = "Contract document sent for Negotiation has been rejected";
				
				String email_body = "<h2 style='font-size:18px;'>Dear SPOC user,</h2><br/>";
					   email_body += "<p style='text-align:justify;width:70%;'>"+name+" has rejected the document which is sent for negotiation for reason being,<b> "+reason+".</b>"
				   			+ "Kindly go through link by clicking on 'Id' and share your feedback on this. </p>"
						+ "<h2 style='font-size:16px;font-weight:bold;'>Contract Request Details :</h2>";
					   email_body += "<table style='width:80%;' border='1'>" 
								+ "<thead>"
								+ "<tr style='background:#0B6EC3;color:#fff;'>"
								+ "<th>Id</th>"
								+ "<th>Action item</th>" 
								+ "<th>Status</th>" 
								+ "<th>Status date</th>"
								+ "<th>Added by</th>"
								+ "</tr>" + "</thead>" + "<tbody>";
						
						email_body += "<tr>" 
								+ "<td> <a href='"+project_url+"updateTab?cont_id="+poc.getPoc_contract_id()+"'>1</a></td>" 
								+ "<td>" + poc.getPoc_action_item() + "</td>" 
								+ "<td>" + poc.getPoc_status() + "</td>" 	
								+ "<td>" + sdfOut.format(poc.getPoc_status_date()) + "</td>" 
								+ "<td>" + userService.getUserFullNameById(poc.getPoc_status_added_by()) + "</td>" 
								+ "</tr>";
					
						email_body += "</tbody>" + "</table>";
						
						email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
								+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
								+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
								+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
					   
				//get mail ids of all SPOCs
				ArrayList<String> spoc = new ArrayList<String>(); 
				List<User> spoc_mail = schedulerService.getContractSPOCuser();
				Iterator<User> iterator1 = spoc_mail.iterator();
				while (iterator1.hasNext()) {
					User user = iterator1.next();
					if(!user.getUser_email().equals(""))
						spoc.add(user.getUser_email());
					}
				 	InternetAddress[] addressCC = new InternetAddress[spoc.size()];
					try {
						for(int i = 0; i<spoc.size(); i++){
						addressCC[i] = new InternetAddress((String)spoc.get(i));
						}
					} catch (AddressException e) {
						e.printStackTrace();
					}
					
					/*InternetAddress[] addressTo = new InternetAddress[1];
					try {
						addressTo[0] = new InternetAddress(poc_email);
					} catch (AddressException e) {
						e.printStackTrace();
					}
				*/
					PocNegotiationDocument pocDoc = requestByPOCDao.getPocDocumentById(poc.getPoc_status_id());	
				
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
						message.setRecipients(Message.RecipientType.TO, addressCC);
						message.setRecipients(Message.RecipientType.CC, addressCC);
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
						if(pocDoc.getPoc_doc_generated_file_name() != null){
						messageBodyPart = new MimeBodyPart();
						
						DataSource source = new FileDataSource(pocDoc.getPoc_doc_generated_file_name());
						messageBodyPart.setDataHandler(new DataHandler(source));
						messageBodyPart.setFileName(pocDoc.getPoc_doc_original_file_name());
						multipart.addBodyPart(messageBodyPart);
						}
						// Send the complete message parts
						message.setContent(multipart);
						Transport.send(message);

						for (int i = 0; i < addressCC.length; i++) {
							utilitiesService.addMailToLog(String.valueOf(addressCC[i]), String.valueOf(addressCC[i]), subject, String.valueOf(poc.getPoc_status_id()));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
			return String.valueOf(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PocStatusNegotiationReference> getAllContractStatusNegotiationByContractId(int cont_id) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			List<PocStatusNegotiationReference> newPoc =  new ArrayList<>();
			
			List<Object> allStatus = requestByPOCDao.getAllPocStatusForNegotiationByContractId(cont_id);
			Iterator<Object> iterator = allStatus.iterator();
			while (iterator.hasNext()) {
				Object[] object = (Object[]) iterator.next();
				PocStatusNegotiationReference poc = new PocStatusNegotiationReference();
				poc.setPoc_status_id(Integer.parseInt(object[0].toString()));
				poc.setPoc_action_item(object[1].toString());
				poc.setPoc_status(object[2].toString());
				poc.setPoc_status_date_name(sdfOut.format(sdfIn.parse(object[3].toString())));
				poc.setPoc_contract_req_id(Integer.parseInt(object[6].toString()));
				poc.setPoc_contract_id(Integer.parseInt(object[7].toString()));
				poc.setPoc_status_added_by_name(userService.getUserFullNameById(Integer.parseInt(object[5].toString())));
				poc.setPocDoc(requestByPOCDao.getAllPocStatusDocument(Integer.parseInt(object[0].toString())));
				newPoc.add(poc);
			}
			return newPoc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RequestRejectStatusReference getAllRejectStatus(int related_id, String related_to, HttpSession session) {
		try {
			String req_related_to = "";
			if(related_to.equals("contract")){
				req_related_to += "Contract Request";
			}
			if(related_to.equals("litigation")){
				req_related_to += "Litigation Request";
			}
			if(related_to.equals("query")){
				req_related_to += "Query Request";
			}
			if(related_to.equals("notice")){
				req_related_to += "Notice Request";
			}
			RequestRejectStatus rejectStatus = requestByPOCDao.getAllRequestStatus(related_id, req_related_to, session);
			RequestRejectStatusReference ref = new RequestRejectStatusReference();
			ref.setReq_reject_related_to(req_related_to);
			ref.setReq_reject_related_id(related_id);
			ref.setReq_reject_spoc_name(userService.getUserFullNameById(rejectStatus.getReq_spoc_id()));
			ref.setReq_reject_spoc_comments(rejectStatus.getReq_spoc_comments());
			ref.setReq_reject_admin_name(userService.getUserFullNameById(rejectStatus.getReq_admin_id()));
			ref.setReq_reject_admin_comments(rejectStatus.getReq_admin_comments());
			return ref;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveNoticeRequest(LegalNoticeRequest req, ArrayList<MultipartFile> noti_doc, String status,
			HttpSession session) {
		try {
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			req.setReq_noti_added_by(utilitiesService.getCurrentSessionUserId(session));
			req.setReq_noti_created_at(utilitiesService.getCurrentDate());
			if(!status.equals("Draft"))
				req.setReq_noti_approval_status(0);
			else
				req.setReq_noti_approval_status(4);
			int req_noti_id = requestByPOCDao.saveNoticeRequest(req);
			
			if(!status.equals("Draft")){
			
				String email_subject = "Legal Notice Request Generated ";
			//get mail ids of all admin
			ArrayList<String> email = new ArrayList<String>(); 
			List<Object> mail_idCC = schedulerService.getAllAdminUser();
			Iterator<Object> iterator = mail_idCC.iterator();
			while (iterator.hasNext()) {
				Object object[] = (Object[]) iterator.next();
				if(object[1] != null)
				email.add(object[1].toString());
			}
			//get mail ids of all SPOCs
			ArrayList<String> spoc = new ArrayList<String>(); 
			
			List<User> spoc_mail = schedulerService.getAllSPOCuser();
			
			Iterator<User> iterator1 = spoc_mail.iterator();
			while (iterator1.hasNext()) {
				User user = iterator1.next();
				if(!user.getUser_email().equals(""))
					spoc.add(user.getUser_email());
			}
			
		    String email_body  = " <h2 style='font-size:18px;'>Dear SPOC,</h2> ";
			   email_body += " <p style='text-align:justify;width:70%;'>"+userService.getUserFullNameById(Integer.parseInt(session.getAttribute("sess_user_id").toString()))+" generated Legal Notice request. "
			   			+ "Kindly go through link by clicking on 'Id' and share your feedback on this. </p> "
						+ "<h2 style='font-size:16px;font-weight:bold;'>Litigation Request Details :</h2>";
				email_body += "<table style='width:80%;' border='1'>" 
						+ "<thead>"
						+ "<tr style='background:#0B6EC3;color:#fff;'>"
						+ "<th>Id</th>"
						+ "<th>Entity</th>" 
						+ "<th>Unit</th>" 
						+ "<th>Function</th>"
						+ "<th>Notice Type</th>"
						+ "<th>(Notice/Court order) Date</th>" 
						+ "<th>Opposite Party</th>"
						+ "<th>Brief Description</th>" 
						+ "</tr>" + "</thead>" + "<tbody>";
				
				email_body += "<tr>" 
						+ "<td><a href='"+project_url+"listNoticeRequest'>1</a></td>" 
						+ "<td>" + organizationService.getOrganizationById(req.getReq_noti_entity_id()).getOrga_name() + "</td>" 
						+ "<td>" + locationService.getLocationNameById(req.getReq_noti_unit_id()) + "</td>" 	
						+ "<td>" + departmentService.getDepartmentNameById(req.getReq_noti_function_id()) + "</td>" 
						+ "<td>" + req.getReq_noti_type_by_against() + "</td>" 
						+ "<td>" + sdfOut.format(req.getReq_noti_date()) + "</td>" 
						+ "<td>" + req.getReq_noti_oppo_party() + "</td>" 
						+ "<td>" + req.getReq_noti_des() + "</td>" 
						+ "</tr>";
			
				email_body += "</tbody>" + "</table>";
				
				email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
						+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
						+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
						+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
		
				InternetAddress[] address = new InternetAddress[spoc.size()];
				try {
					for(int j=0; j < spoc.size(); j++){
					address[j] = new InternetAddress((String)spoc.get(j));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}	
	
				InternetAddress[] addressCC = new InternetAddress[email.size()];
				try {
					for(int i = 0; i<email.size(); i++){
					addressCC[i] = new InternetAddress((String)email.get(i));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}	
	
			sendMailService.sendMail(email_subject, email_body, address, addressCC, String.valueOf(req_noti_id));
			}
			// Upload Documents Contract
			String originalFileName = null;
			String generatedFileName = null;
						int lastGeneratedValueDoc = requestByPOCDao.getLastGenerateValueForRequest(req_noti_id,"NotiRequest");
						for (int i = 0; i < noti_doc.size(); i++) {
							MultipartFile file = noti_doc.get(i);
							if (file.getSize() > 0) {
								File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
										+ "RequestDocuments");
								if (!dir.exists())
									dir.mkdirs();

								lastGeneratedValueDoc++;

								originalFileName = file.getOriginalFilename();
								generatedFileName = "uploadedNoticeRequestDoc_" + req_noti_id + "_" + lastGeneratedValueDoc + "."
										+ file.getOriginalFilename().split("\\.")[1];
								File newFile = new File(dir.getPath() + File.separator + generatedFileName);
								if (!newFile.exists()) {
									newFile.createNewFile();
								}

								@SuppressWarnings("resource")
								OutputStream outputStream = new FileOutputStream(newFile);

								outputStream.write(file.getBytes());

								RequestDocument documents = new RequestDocument();

								documents.setReq_id(req_noti_id);
								documents.setReq_doc_original_file_name(originalFileName);
								documents.setReq_doc_generated_file_name(newFile.getAbsolutePath());
								documents.setReq_doc_last_generated_value_for_req_id(lastGeneratedValueDoc);
								documents.setReq_doc_created_at(utilitiesService.getCurrentDateWithTime());
								documents.setReq_doc_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
								documents.setReq_doc_document_type("NotiRequest");
								
								requestByPOCDao.save(documents);
							}	
						}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<RequestByPOCReference> getAllListNoticeRequest(HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			List<RequestByPOCReference> sendList = new ArrayList<>();
			
			List<Object> noti = requestByPOCDao.getAllListNoticeRequest(session);
			if(noti != null){
			Iterator<Object> itr = noti.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
 				
				RequestByPOCReference ref = new RequestByPOCReference();
				if(object[0] != null)
					ref.setReq_noti_date_name(sdfOut.format(sdfIn.parse(object[0].toString())));
				else
					ref.setReq_noti_date_name("NA");
				if(object[1] != null)
				ref.setReq_noti_oppo_party(object[1].toString());
				else
					ref.setReq_noti_oppo_party("NA");
				if(object[2] != null)
					ref.setReq_noti_des(object[2].toString());
				else
					ref.setReq_noti_des("NA");
				ref.setReq_noti_approval_status(Integer.parseInt(object[4].toString()));
				ref.setReq_noti_id(Integer.parseInt(object[3].toString()));
				ref.setReq_entity_name(object[5].toString());
				ref.setReq_unit_name(object[6].toString());
				ref.setReq_function_name(object[7].toString());
				ref.setReq_added_by_name(userService.getUserFullNameById(Integer.parseInt(object[8].toString())));
				ref.setReq_doc_list(requestByPOCDao.getAllRequestDocument(Integer.parseInt(object[3].toString()), "NotiRequest"));
				ref.setReq_added_by(Integer.parseInt(object[8].toString()));
				if(object[9] != null)
					ref.setReq_noti_type_by_against(object[9].toString());
				else
					ref.setReq_noti_type_by_against("NA");	
				sendList.add(ref);
			}
			return sendList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LegalNoticeRequest getAllNoticerequest(int noti_id, HttpSession session) {
		try {
			return requestByPOCDao.getAllNoticerequest(noti_id, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateNoticeRequest(LegalNoticeRequest req, ArrayList<MultipartFile> noti_doc, String status,
			HttpSession session) {
		try {
			LegalNoticeRequest notiReq = requestByPOCDao.getAllNoticerequest(req.getReq_noti_id(), session);
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			req.setReq_noti_added_by(utilitiesService.getCurrentSessionUserId(session));
			req.setReq_noti_created_at(utilitiesService.getCurrentDate());
			if(!status.equals("Draft"))
				req.setReq_noti_approval_status(0);
			else
				req.setReq_noti_approval_status(4);
			int req_noti_id = req.getReq_noti_id();
			 requestByPOCDao.update(req);
			
			if(!status.equals("Draft")){
			
			String email_subject = "Legal Notice Request Generated ";
			//get mail ids of all admin
			ArrayList<String> email = new ArrayList<String>(); 
			List<Object> mail_idCC = schedulerService.getAllAdminUser();
			Iterator<Object> iterator = mail_idCC.iterator();
			while (iterator.hasNext()) {
				Object object[] = (Object[]) iterator.next();
				if(object[1] != null)
				email.add(object[1].toString());
			}
			//get mail ids of all SPOCs
			ArrayList<String> spoc = new ArrayList<String>(); 
			
			List<User> spoc_mail = schedulerService.getAllSPOCuser();
			
			Iterator<User> iterator1 = spoc_mail.iterator();
			while (iterator1.hasNext()) {
				User user = iterator1.next();
				if(!user.getUser_email().equals(""))
					spoc.add(user.getUser_email());
			}
			
		    String email_body  = " <h2 style='font-size:18px;'>Dear SPOC,</h2> ";
			   email_body += " <p style='text-align:justify;width:70%;'>"+userService.getUserFullNameById(Integer.parseInt(session.getAttribute("sess_user_id").toString()))+" generated Legal Notice request. "
			   			+ "Kindly go through link by clicking on 'Id' and share your feedback on this. </p> "
						+ "<h2 style='font-size:16px;font-weight:bold;'>Litigation Request Details :</h2>";
				email_body += "<table style='width:80%;' border='1'>" 
						+ "<thead>"
						+ "<tr style='background:#0B6EC3;color:#fff;'>"
						+ "<th>Id</th>"
						+ "<th>Entity</th>" 
						+ "<th>Unit</th>" 
						+ "<th>Function</th>"
						+ "<th>Notice Type</th>"
						+ "<th>(Notice/Court order) Date</th>" 
						+ "<th>Opposite Party</th>"
						+ "<th>Brief Description</th>" 
						+ "</tr>" + "</thead>" + "<tbody>";
				
				email_body += "<tr>" 
						+ "<td><a href='"+project_url+"listNoticeRequest'>1</a></td>" 
						+ "<td>" + organizationService.getOrganizationById(req.getReq_noti_entity_id()).getOrga_name() + "</td>" 
						+ "<td>" + locationService.getLocationNameById(req.getReq_noti_unit_id()) + "</td>" 	
						+ "<td>" + departmentService.getDepartmentNameById(req.getReq_noti_function_id()) + "</td>" 
						+ "<td>" + req.getReq_noti_type_by_against() + "</td>" 
						+ "<td>" + sdfOut.format(req.getReq_noti_date()) + "</td>" 
						+ "<td>" + req.getReq_noti_oppo_party() + "</td>" 
						+ "<td>" + req.getReq_noti_des() + "</td>" 
						+ "</tr>";
			
				email_body += "</tbody>" + "</table>";
				
				email_body += "<p>This is a system generated mail. Please do not reply to this mail.<br/>"
						+ "In case of any doubt or difficulty, please call Helpdesk as per details given on the support page."
						+ "</p>" + "<h2 style='font-size:18px;font-weight:bold;'>Yours Sincerely</h2>"
						+ "<h2 style='font-size:19px;font-weight:bold;'>Legal Team</h2>" + "</div>";
		
				InternetAddress[] address = new InternetAddress[spoc.size()];
				try {
					for(int j=0; j < spoc.size(); j++){
					address[j] = new InternetAddress((String)spoc.get(j));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}	
	
				InternetAddress[] addressCC = new InternetAddress[email.size()];
				try {
					for(int i = 0; i<email.size(); i++){
					addressCC[i] = new InternetAddress((String)email.get(i));
					}
				} catch (AddressException e) {
					e.printStackTrace();
				}	
	
			sendMailService.sendMail(email_subject, email_body, address, addressCC, String.valueOf(req_noti_id));
			}
			// Upload Documents Contract
			String originalFileName = null;
			String generatedFileName = null;
						int lastGeneratedValueDoc = requestByPOCDao.getLastGenerateValueForRequest(req_noti_id,"NotiRequest");
						for (int i = 0; i < noti_doc.size(); i++) {
							MultipartFile file = noti_doc.get(i);
							if (file.getSize() > 0) {
								File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
										+ "RequestDocuments");
								if (!dir.exists())
									dir.mkdirs();

								lastGeneratedValueDoc++;

								originalFileName = file.getOriginalFilename();
								generatedFileName = "uploadedNoticeRequestDoc_" + req_noti_id + "_" + lastGeneratedValueDoc + "."
										+ file.getOriginalFilename().split("\\.")[1];
								File newFile = new File(dir.getPath() + File.separator + generatedFileName);
								if (!newFile.exists()) {
									newFile.createNewFile();
								}

								@SuppressWarnings("resource")
								OutputStream outputStream = new FileOutputStream(newFile);

								outputStream.write(file.getBytes());

								RequestDocument documents = new RequestDocument();

								documents.setReq_id(req_noti_id);
								documents.setReq_doc_original_file_name(originalFileName);
								documents.setReq_doc_generated_file_name(newFile.getAbsolutePath());
								documents.setReq_doc_last_generated_value_for_req_id(lastGeneratedValueDoc);
								documents.setReq_doc_created_at(utilitiesService.getCurrentDateWithTime());
								documents.setReq_doc_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
								documents.setReq_doc_document_type("NotiRequest");
								
								requestByPOCDao.save(documents);
							}	
						}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public String searchRequestNotice(int entity_id, int unit_id, int function_id, String from_date, String to_date,
			HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			String f_date = "";
			String t_date = "";
			
			if(!from_date.equals("") && !to_date.equals("")){
			
			 f_date = sdfIn.format(sdfOut.parse(from_date));
			 t_date = sdfIn.format(sdfOut.parse(to_date));
			}
			
			JSONArray json = new JSONArray();
			List<Object> query = requestByPOCDao.searchRequestNotice(entity_id, unit_id, function_id, f_date, t_date, session);
			Iterator<Object> iterator = query.iterator();
			while(iterator.hasNext()){
				
				Object[] obj = (Object[]) iterator.next();
				JSONObject jsonObj = new JSONObject();
				
				if(obj[0] != null)
				jsonObj.put("req_noti_date", sdfOut.format(sdfIn.parse(obj[0].toString())));
				else
					jsonObj.put("req_noti_date", "NA");	
				jsonObj.put("req_oppo_party",obj[1].toString());
				jsonObj.put("req_desc",obj[2].toString());
				jsonObj.put("req_noti_id", obj[3].toString());
				jsonObj.put("approval_status", obj[4].toString());
				jsonObj.put("orga_name", obj[5].toString());
				jsonObj.put("loca_name", obj[6].toString());
				//System.out.println("loca name is :"+obj[4].toString());
				jsonObj.put("dept_name", obj[7].toString());
				
				jsonObj.put("req_added_byName", userService.getUserFullNameById(Integer.parseInt(obj[8].toString())));
				jsonObj.put("added_by", obj[8].toString());
				jsonObj.put("noti_by_against", obj[9].toString());
				
				JSONArray doc_list = new JSONArray();
				List<RequestDocument> docs =  requestByPOCDao.getAllRequestDocument(Integer.parseInt(obj[3].toString()), "NotiRequest");
				if(docs != null){
				for (RequestDocument requestDocument : docs) {
					
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("doc_name", requestDocument.getReq_doc_original_file_name());
					jsonObject.put("doc_id", requestDocument.getReq_doc_id());
					doc_list.add(jsonObject);
				}
				}
				jsonObj.put("doc_list", doc_list);
				
				jsonObj.put("role_id", session.getAttribute("sess_user_role").toString());
				jsonObj.put("sess_user_id", session.getAttribute("sess_user_id").toString());
				json.add(jsonObj);
			}
			return json.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
