package lcmt.service.impl;

import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lcmt.dao.ContractDao;
import lcmt.dao.ExecutedContractDao;
import lcmt.domain.ExecutedContracts;
import lcmt.domain.LitigationType;
import lcmt.domain.TermRenwContract;
import lcmt.domain.TermRenwContractRef;
import lcmt.domain.TransactionalActionItem;
import lcmt.domain.ActivityLogs;
import lcmt.domain.AmendmentContract;
import lcmt.domain.AmendmentContractRef;
import lcmt.domain.Contract;
import lcmt.domain.ContractDocuments;
import lcmt.domain.ContractParties;
import lcmt.domain.ContractType;
import lcmt.domain.ExecuteActionItem;
import lcmt.domain.ExecuteActionItemReferenece;
import lcmt.domain.ExecutedContractDocuments;
import lcmt.domain.ExecutedContractReference;
import lcmt.service.ContractService;
import lcmt.service.ExecutedContractService;
import lcmt.service.SendMailService;
import lcmt.service.UserService;
import lcmt.service.UtilitiesService;

/*
 * Author: Harshad Padole
 * Date: 13/09/2016
 * Updated By: 
 * Updated Date: 
 * 
 * */
@Service("executedContractService")
public class ExecutedContractServiceImpl implements ExecutedContractService {
	
	@Autowired
	UtilitiesService utilitieservice;
	@Autowired
	ExecutedContractDao executedContractDao;
	@Autowired
	UtilitiesService utilitiesService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	ContractDao contractDao;
	@Autowired
	UserService userService;
	@Autowired
	ContractService contractService;
	@Autowired
	SendMailService sendMailService;
	
	private @Value("#{config['project_name'] ?: 'null'}") String project_name;

	// Method Created : Harshad Padole
	// Method purpose : Save Executed contracts
	@Override
	public int saveExecutedContract(ExecutedContracts contracts, ArrayList<MultipartFile> executed_contract_documents,ArrayList<String> additional_parties,String status) {
		try {
			
			int user_id = utilitiesService.getCurrentSessionUserId(httpSession);
			Date curr_date = utilitiesService.getCurrentDate();
			contracts.setExec_contract_created_at(curr_date);
			contracts.setExec_contract_updated_at(curr_date);
			contracts.setExec_contract_added_by(user_id);
			if(status.equals("Pending"))
			{ 
				if(contracts.getExec_contract_agree_status().equals("Closed"))
				{
					contracts.setExec_contract_agree_status("Closed");
					}
				else{
					contracts.setExec_contract_agree_status(status);}
			}
				
			
			/*
			 * contracts.setExec_contract_agree_status("Pending");} else{
			 * contracts.setExec_contract_agree_status(status); }
			 */
			
			else	if(status.equals("Draft"))
			{ /*System.out.println("in draft method");*/
				contracts.setExec_contract_agree_status("Draft");
				}
		
			int exec_contra_id = executedContractDao.saveExecutedContract(contracts);
			
			//Save Parties Details
			for(int i=0; i<additional_parties.size();i++){
				ContractParties contractParties = new ContractParties();
				contractParties.setCont_party_contract_id(exec_contra_id);
				contractParties.setCont_party_name(additional_parties.get(i));
				contractParties.setCont_party_type(2);
				contractParties.setCont_party_created_at(curr_date);
				contractParties.setCont_party_added_by(user_id);
				//executedContractLogs.setExec_contract_party_name(contractParties.getCont_party_name()+", ");
				executedContractDao.savePartyDetails(contractParties);
				//System.out.println("Party "+additional_parties.get(i));
			}
			
			if(status.equals("Pending")){
				sendMailService.sendExecutionAssignMailToResponsiblePerson(exec_contra_id, httpSession);
				
			
			// persist history log
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(exec_contra_id);
			logs.setLog_activity("Add");
			logs.setLog_related_to("Executed contract");
			logs.setLog_sub_activity("Add Executed contract");
			//logs.setLog_related_name(contracts.get);
			logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
			executedContractDao.saveLogs(logs);
			}
			// Upload Documents
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueContactDoc = executedContractDao.getLastGeneratedValueByContactId(exec_contra_id,1);

			for (int i = 0; i < executed_contract_documents.size(); i++) {
				MultipartFile file = executed_contract_documents.get(i);
				if (file.getSize() > 0) {
					File dir = new File("D:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "ContractDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueContactDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedContraDoc_" + exec_contra_id + "_" + lastGeneratedValueContactDoc + "."
							+ file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					ExecutedContractDocuments newDoc = new ExecutedContractDocuments();
					newDoc.setExec_contract_id(exec_contra_id);
					newDoc.setExec_original_file_name(originalFileName);
					newDoc.setExec_generated_file_name("D:/"+project_name+"/Documents/ContractDocuments/" + generatedFileName);
					newDoc.setExec_last_generated_value_for_contract_id(lastGeneratedValueContactDoc);
					newDoc.setExec_doc_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
					newDoc.setExec_contract_created_date(utilitiesService.getCurrentDate());
					newDoc.setExec_doc_type(1);
					executedContractDao.saveContractDocuments(newDoc);
				}
			}
			return exec_contra_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method purpose : Update Executed contracts
	@Override
	public int updateExecutedContract(ExecutedContractReference contracts,
			ArrayList<MultipartFile> executed_contract_documents,ArrayList<String> additional_parties,HttpSession session,String status) {
		try {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			if(status.equals("Draft"))
			{ /*System.out.println("in draft method");*/
				contracts.setExec_contract_agree_status("Draft");
				}
			ExecutedContracts newData = new ExecutedContracts();
			if(contracts.getExec_contract_id() > 0){
				ExecutedContracts oldData = executedContractDao.getExecutedContactsById(contracts.getExec_contract_id());
				newData.setExec_contract_added_by(oldData.getExec_contract_added_by());
				newData.setExec_contract_created_at(oldData.getExec_contract_created_at());
				newData.setExec_contract_id(oldData.getExec_contract_id());	
			}
			
			newData.setExec_contract_entity_id(contracts.getExec_contract_entity_id());
			newData.setExec_contract_unit_id(contracts.getExec_contract_unit_id());
			newData.setExec_contract_function_id(contracts.getExec_contract_function_id());
			newData.setExec_contract_type_id(contracts.getExec_contract_type_id());
			//newData.setExec_contract_executed_with(contracts.getExec_contract_executed_with());
			newData.setExec_contract_description(contracts.getExec_contract_description());
			//newData.setExec_contract_internal_contact_name(contracts.getExec_contract_internal_contact_person());
			newData.setExec_contract_executed_contact_name(contracts.getExec_contract_executed_contact_name());
			newData.setExec_contract_date(contracts.getExec_contract_date_date());
			newData.setExec_contract_updated_at(utilitiesService.getCurrentDate());
			newData.setExec_contract_resposible_person(contracts.getExec_contract_resposible_person());
			if(contracts.getExec_contract_start_date() != null){
			newData.setExec_contract_start_date(contracts.getExec_contract_start_date());}
			if(contracts.getExec_contract_end_date() != null){
				newData.setExec_contract_end_date(contracts.getExec_contract_end_date());}
			newData.setExec_contract_surviving_obl(contracts.getExec_contract_surviving_obl());
			newData.setExec_contract_insurance(contracts.getExec_contract_insurance());
			newData.setExec_contract_payment(contracts.getExec_contract_payment());
			newData.setExec_contract_notice_period(contracts.getExec_contract_notice_period());
			newData.setExec_contract_damages(contracts.getExec_contract_damages());
			newData.setExec_contract_jurisdiction(contracts.getExec_contract_jurisdiction());
			newData.setExec_contract_arbitration(contracts.getExec_contract_arbitration());
			newData.setExec_contract_criticality(contracts.getExec_contract_criticality());
			newData.setExec_contract_cont_person_email(contracts.getExec_contract_cont_person_email());
			newData.setExec_contract_cont_person_number(contracts.getExec_contract_cont_person_number());
			
			newData.setExec_contract_lockin_period(contracts.getExec_contract_lockin_period());
			newData.setExec_contract_termination_clause(contracts.getExec_contract_termination_clause());
			newData.setExec_contract_consider_involve(contracts.getExec_contract_consider_involve());
			newData.setExec_contract_signatories_assign(contracts.getExec_contract_signatories_assign());
			newData.setExec_contract_execution_date(dateFormat.parse(contracts.getExec_contract_execution_date()));
			//newData.setExec_contract_renewal_date(dateFormat.parse(contracts.getExec_contract_renewal_date()));
			newData.setExec_contract_ammendment(contracts.getExec_contract_ammendment());
			newData.setExec_contract_agree_status(contracts.getExec_contract_agree_status());
			newData.setExec_contract_renewal_status(contracts.getExec_contract_renewal_status());
			newData.setExec_remind_date(dateFormat.parse(contracts.getExec_remind_date()));
			newData.setExec_remind_date_second(dateFormat.parse(contracts.getExec_remind_date_second()));
			newData.setExec_remind_date_third(dateFormat.parse(contracts.getExec_remind_date_third()));
			newData.setExec_contract_division(contracts.getExec_contract_division());
			newData.setExec_contract_jurisdiction(contracts.getExec_contract_jurisdiction());
			newData.setExec_force_majeure(contracts.getExec_force_majeure());
			int exec_id = 0;
			if(contracts.getExec_contract_id() > 0){
				 exec_id = executedContractDao.updateExecutedContract(newData);
				// persist history log
					ActivityLogs logs = new ActivityLogs();
					logs.setLog_activity_id(exec_id);
					logs.setLog_activity("Update");
					logs.setLog_related_to("Executed contract");
					logs.setLog_sub_activity("Update Executed contract");
					logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
					logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
					executedContractDao.saveLogs(logs);	
					
					//Update Party details
					List<ContractParties> parties = contracts.getExec_parties();
					if(parties!=null && parties.size()>0){
						for (ContractParties contractParties : parties) {
						      	ContractParties oldDataParty = executedContractDao.getPartyById(contractParties.getCont_party_id());
						      	//Assign old data to new party data
						      	contractParties.setCont_party_type(oldDataParty.getCont_party_type());
						      	contractParties.setCont_party_contract_id(oldDataParty.getCont_party_contract_id());
						      	contractParties.setCont_party_series(oldDataParty.getCont_party_series());
						      	contractParties.setCont_party_added_by(oldDataParty.getCont_party_added_by());
						      	contractParties.setCont_party_created_at(oldDataParty.getCont_party_created_at());
						      	executedContractDao.meageParties(contractParties);
						}
					}
			}else{
			newData.setExec_contract_pre_cont_id(contracts.getExec_contract_pre_cont_id());
			newData.setExec_contract_added_by(utilitiesService.getCurrentSessionUserId(session));
			newData.setExec_contract_created_at(utilitiesService.getCurrentDate());
			exec_id = executedContractDao.saveExecutedContract(newData);
			
			if(contracts.getExec_contract_pre_cont_id() > 0){
				Contract cont = contractDao.getContractById(contracts.getExec_contract_pre_cont_id());
				cont.setCont_status("Converted into Executed Contracts");
				executedContractDao.meageParties(cont);
			}
			
			// persist history log
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_activity_id(exec_id);
				logs.setLog_activity("Save");
				logs.setLog_related_to("Executed contract");
				logs.setLog_sub_activity("Save Executed contract");
				logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
				logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
				executedContractDao.saveLogs(logs);	
			
				List<ContractParties> parties = contracts.getExec_parties();
				if(parties!=null && parties.size()>0){
					for (ContractParties contractParties : parties) {
						ContractParties contParties = new ContractParties();
						contParties.setCont_party_contract_id(exec_id);
						contParties.setCont_party_name(contractParties.getCont_party_name());
						contParties.setCont_party_type(2);
						contParties.setCont_party_created_at(utilitiesService.getCurrentDate());
						contParties.setCont_party_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
						executedContractDao.savePartyDetails(contParties);
					}
				}
				
				List<ContractDocuments> contDoc = contractService.getAllContractDocumentsByContractId(contracts.getExec_contract_pre_cont_id());
				Iterator<ContractDocuments> itr = contDoc.iterator();
				while (itr.hasNext()) {
					ContractDocuments contractDocuments = (ContractDocuments) itr.next();
					ExecutedContractDocuments doc = new ExecutedContractDocuments();
					
					doc.setExec_contract_created_date(utilitiesService.getCurrentDate());
					doc.setExec_doc_added_by(utilitiesService.getCurrentSessionUserId(session));
					doc.setExec_contract_id(exec_id);
					doc.setExec_generated_file_name(contractDocuments.getCdoc_generated_file_name());
					doc.setExec_original_file_name(contractDocuments.getCdoc_original_file_name());
					int lastGenratedValue = executedContractDao.getLastGeneratedValueByContactId(exec_id,1);
					lastGenratedValue++;
					doc.setExec_last_generated_value_for_contract_id(lastGenratedValue);
					executedContractDao.save(doc);
				}
				
			}
			//Save Parties Details
			if(additional_parties!=null){
				for(int i=0; i<additional_parties.size();i++){
					ContractParties contractParties = new ContractParties();
					contractParties.setCont_party_contract_id(exec_id);
					contractParties.setCont_party_name(additional_parties.get(i));
					contractParties.setCont_party_type(2);
					contractParties.setCont_party_created_at(utilitiesService.getCurrentDate());
					contractParties.setCont_party_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
					executedContractDao.savePartyDetails(contractParties);
					//System.out.println("Party "+additional_parties.get(i));
				}
			}	
			
			// Upload Documents
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueContactDoc = executedContractDao.getLastGeneratedValueByContactId(exec_id,1);

			for (int i = 0; i < executed_contract_documents.size(); i++) {
				MultipartFile file = executed_contract_documents.get(i);
				if (file.getSize() > 0) {
					File dir = new File("D:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "ContractDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueContactDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedContraDoc_" + exec_id + "_"
							+ lastGeneratedValueContactDoc + "." + file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					ExecutedContractDocuments newDoc = new ExecutedContractDocuments();
					newDoc.setExec_contract_id(exec_id);
					newDoc.setExec_original_file_name(originalFileName);
					newDoc.setExec_generated_file_name("D:/"+project_name+"/Documents/ContractDocuments/" + generatedFileName);
					newDoc.setExec_last_generated_value_for_contract_id(lastGeneratedValueContactDoc);
					newDoc.setExec_doc_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
					newDoc.setExec_contract_created_date(utilitiesService.getCurrentDate());
					newDoc.setExec_doc_type(1);
					executedContractDao.saveContractDocuments(newDoc);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// Method Created : Harshad Padole
	// Method purpose : Get Executed contracts by id
	@Override
	public ExecutedContracts getExecutedContractById(int exec_id) {
		try {
			ExecutedContracts executedContracts = executedContractDao.getExecutedContactsById(exec_id);
			return executedContracts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created by : Harshad Padole
	// Method purpose of : Get Executed contracts list
	// Method updated by: Tejashri Zurunge
	// Updated at : 16/09/2016
	@Override
	public List<ExecutedContractReference> getListAllContract(HttpSession session) {
		try {
			
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			//final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
			List<Object> result = executedContractDao.getAllExecutedContractsDetails(session);
			List<ExecutedContractReference> sendList = new ArrayList<>();
			Iterator<Object> iterator = result.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				ExecutedContractReference contractReference = new ExecutedContractReference();
				contractReference.setExec_contract_executed_type_status(1);
				contractReference.setExec_contract_id(Integer.parseInt(objects[0].toString()));
				//contractReference.setExec_contract_executed_with(objects[2].toString());
				contractReference.setExec_contract_type_name(objects[1].toString());
			
				contractReference.setExec_contract_entity_name(objects[3].toString());
				
				contractReference.setExec_contract_unit_name(objects[4].toString());
				contractReference.setExec_contract_function_name(objects[5].toString());
				contractReference.setExec_parties(executedContractDao.getExecutedContractPartiesByContractId(Integer.parseInt(objects[0].toString())));
				contractReference.setExec_contract_added_by(Integer.parseInt(objects[6].toString()));
				ExecutedContractDocuments doc = executedContractDao.getDocumetById(Integer.parseInt(objects[0].toString()));
				if (doc != null) {
					/*
					 * File file = new File(doc.getExec_generated_file_name());
					 * // Get length of file in bytes long fileSizeInBytes =
					 * file.length(); int digitGroups = (int)
					 * (Math.log10(fileSizeInBytes) / Math.log10(1024)); String
					 * fileSize = new
					 * DecimalFormat("#,##0.#").format(fileSizeInBytes /
					 * Math.pow(1024, digitGroups))+ " " + units[digitGroups];
					 */
					contractReference.setExec_contract_document_name(doc.getExec_original_file_name());
					// contractReference.setExec_contract_file_size(fileSize);
					contractReference.setExec_contract_file_path(doc.getExec_generated_file_name());
					contractReference.setExec_contract_doc_id(doc.getExec_doc_id());
				} else {
					contractReference.setExec_contract_document_name("NA");
					contractReference.setExec_contract_file_size("NA");

				}
				contractReference.setExec_contract_entity_id(Integer.parseInt(objects[7].toString()));
				contractReference.setExec_contract_unit_id(Integer.parseInt(objects[8].toString()));
				contractReference.setExec_contract_function_id(Integer.parseInt(objects[9].toString()));
				

				contractReference.setExec_contract_execution_date(objects[10].toString());
				/*contractReference.setExec_contract_execution_date(sdfOut.format(sdfIn.parse(objects[10].toString())));*/
				/*contractReference.setExec_contract_start_date(sdfOut.format(sdfIn.parse(objects[11].toString())));*/
			
				/*contractReference.setExec_contract_renewal_date(sdfOut.format(sdfIn.parse(objects[13].toString())));*/
				contractReference.setExec_contract_renewal_date(objects[13].toString());
     
				contractReference.setExec_contract_execution_date(objects[10].toString());
			
				contractReference.setExec_contract_renewal_date(objects[13].toString());

				if(objects[10] != null)
				contractReference.setExec_contract_execution_date(objects[10].toString());
				else
					contractReference.setExec_contract_execution_date("NA");
				if(objects[11] != null)
				contractReference.setExec_contract_start_date_name(objects[11].toString());
				else
					contractReference.setExec_contract_start_date_name("NA");	
				if(objects[12] != null)
				contractReference.setExec_contract_end_date_name(objects[12].toString());
				else
					contractReference.setExec_contract_end_date_name("NA");
				if(objects[13] != null)
				contractReference.setExec_contract_renewal_date(objects[13].toString());

				
				else
					contractReference.setExec_contract_renewal_date("NA");
					
				contractReference.setExec_contract_notice_period(objects[14].toString());
				
				if(objects[15] != null)
				contractReference.setExec_contract_lockin_period(objects[15].toString());
				
				if(objects[16] != null)
				contractReference.setExec_contract_description(objects[16].toString());
				if(objects[17] != null)
				contractReference.setExec_contract_payment(objects[17].toString());
				
				if(objects[18] != null)
				contractReference.setExec_contract_termination_clause(objects[18].toString());
				if(objects[19] != null)
				contractReference.setExec_contract_damages(objects[19].toString());
				if(objects[20] != null)
				contractReference.setExec_contract_consider_involve(objects[20].toString());
				if(objects[21] != null)
				contractReference.setExec_contract_signatories_assign(objects[21].toString());
				if(objects[22] != null)
				contractReference.setExec_contract_agree_status(objects[22].toString());
				if(objects[23] != null)
				contractReference.setExec_contract_renewal_status(objects[23].toString());
				else
					contractReference.setExec_contract_renewal_status("NA");
				if(objects[24] != null)
				contractReference.setExec_contract_ammendment(objects[24].toString());
				else
					contractReference.setExec_contract_ammendment("NA");
				//contractReference.setExec_contract_resposible_person_name(objects[25].toString());
				contractReference.setExec_contract_arbitration(objects[25].toString());
				contractReference.setExec_contract_insurance(objects[26].toString());
				
				if(objects[27] != null)
					contractReference.setExec_contract_division(objects[27].toString());
				else
					contractReference.setExec_contract_division("NA");
				
				
				sendList.add(contractReference);
			}
			
			/*List<Object> completeContract = executedContractDao.getAllCompletedContract(session);
			if(completeContract != null){
			Iterator<Object> iterator2 = completeContract.iterator();
			while(iterator2.hasNext()){
				Object[] objects = (Object[]) iterator2.next();
			
				ExecutedContractReference contractReference1 = new ExecutedContractReference();	
				
				contractReference1.setExec_contract_executed_type_status(2);	
				contractReference1.setExec_contract_entity_name(objects[0].toString());
				contractReference1.setExec_contract_unit_name(objects[1].toString());
				contractReference1.setExec_contract_function_name(objects[2].toString());
				contractReference1.setExec_parties(contractDao.getContractPartiesByContractId(Integer.parseInt(objects[10].toString())));
				contractReference1.setExec_cont_type_list_name(contractDao.getContractTypeById(Integer.parseInt(objects[10].toString())));
				contractReference1.setExec_contract_added_by(Integer.parseInt(objects[12].toString()));
				contractReference1.setExec_contract_id(Integer.parseInt(objects[10].toString()));

				contractReference1.setExec_contract_entity_id(Integer.parseInt(objects[13].toString()));
				contractReference1.setExec_contract_unit_id(Integer.parseInt(objects[14].toString()));
				contractReference1.setExec_contract_function_id(Integer.parseInt(objects[15].toString()));
				sendList.add(contractReference1);
			}
			}*/
			return sendList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose: search Executed Contract

	@SuppressWarnings("unchecked")
	@Override
	public String searchExecutedContract(int exec_contract_type_id, int exec_contract_entity_id,int	exec_contract_unit_id,int exec_contract_function_id,String exec_contract_division, HttpSession session) {
		try {
			//System.out.println("Search Service");
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			JSONArray sendList = new JSONArray();
			//final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };

			List<Object> result = executedContractDao.searchExecutedContract(exec_contract_type_id,
					exec_contract_entity_id,exec_contract_unit_id,exec_contract_function_id, exec_contract_division, session);

			Iterator<Object> iterator = result.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				JSONObject search = new JSONObject();

				ExecutedContractDocuments doc = executedContractDao.getDocumetById(Integer.parseInt(objects[3].toString()));
				if (doc != null) {
					/*File file = new File(doc.getExec_generated_file_name());
					// Get length of file in bytes
					long fileSizeInBytes = file.length();
					int digitGroups = (int) (Math.log10(fileSizeInBytes) / Math.log10(1024));
					String fileSize = new DecimalFormat("#,##0.#").format(fileSizeInBytes / Math.pow(1024, digitGroups))
							+ " " + units[digitGroups];*/

					search.put("exec_contract_document_name", doc.getExec_original_file_name());
					//search.put("exec_contract_file_size", fileSize);
					search.put("exec_contract_generated_file_name", doc.getExec_generated_file_name());
				} else {
					search.put("exec_contract_document_name", "NA");
					search.put("exec_contract_file_size", "NA");

				}
				search.put("exec_contract_type_status", 1);
				search.put("exec_entity_id", objects[0]);
				search.put("exec_unit_id", objects[1]);
				search.put("exec_function_id", objects[2]);
				search.put("exec_contract_id", objects[3]);
				search.put("exec_contract_type_name", objects[4]);
				search.put("exec_contract_added_by", objects[6]);
				
				
				search.put("user_id", Integer.parseInt(session.getAttribute("sess_user_id").toString()));
				search.put("user_role", Integer.parseInt(session.getAttribute("sess_user_role").toString()));
				
				search.put("exec_contract_execution_date", (sdfOut.format(sdfIn.parse(objects[7].toString()))));
				if(objects[8] != null)
				search.put("exec_contract_start_date", (sdfOut.format(sdfIn.parse(objects[8].toString()))));
				if(objects[9] != null)
				search.put("exec_contract_end_date",  (sdfOut.format(sdfIn.parse(objects[9].toString()))));
				if(objects[10] != null)
				search.put("exec_contract_renewal_date",  (sdfOut.format(sdfIn.parse(objects[10].toString()))));
				search.put("exec_contract_notice_period", objects[11]);
				search.put("exec_contract_lockin_period", objects[12]);
				
				search.put("exec_contract_description", objects[13]);
				search.put("exec_contract_payment", objects[14]);
				search.put("exec_contract_termination_clause", objects[15]);
				search.put("exec_contract_damages", objects[16]);
				search.put("exec_contract_consider_involve", objects[17]);
				search.put("exec_contract_signatories_assign", objects[18]);
				search.put("exec_contract_agree_status", objects[19]);
				search.put("exec_contract_renewal_status", objects[20]);
				search.put("exec_contract_ammendment", objects[21]);
				search.put("exec_contract_arbitration", objects[22]);
				search.put("exec_contract_insurance", objects[23]);
				if(objects[24] != null)
				search.put("exec_contract_division", objects[24]);
				else
					search.put("exec_contract_division", "NA");
					
				List<ContractParties> partyJson = executedContractDao.getExecutedContractPartiesByContractId(Integer.parseInt(objects[3].toString()));
				Iterator<ContractParties> iterator2 = partyJson.iterator();
				JSONArray multiParty = new JSONArray();
				while(iterator2.hasNext()){
				ContractParties contractParties = iterator2.next();
				JSONObject party = new JSONObject();
				party.put("party_id", contractParties.getCont_party_id());
				party.put("party_name", contractParties.getCont_party_name());
				multiParty.add(party);
				}
				search.put("parties",multiParty);
				// search.put("exec_contract_document_name",objects[2]);
				// search.put("exec_contract_file_size",objects[2]);
				//search.put("exec_contract_executed_with", objects[2]);
				sendList.add(search);
			}
			
			//** listing of completed/Executed contract ** 
			List<Object> completedContract = executedContractDao.searchCompletedContract(exec_contract_type_id,exec_contract_entity_id, exec_contract_unit_id, exec_contract_function_id, session);
			
			Iterator<Object> iterator3 = completedContract.iterator();
			while(iterator3.hasNext()){
				Object[] objects = (Object[]) iterator3.next();
				
				JSONObject search = new JSONObject();
				search.put("exec_contract_type_status", 2);
				search.put("exec_entity_id", objects[0]);
				search.put("exec_unit_id", objects[1]);
				search.put("exec_function_id", objects[2]);
				search.put("exec_contract_id", objects[3]);

				//get contract type of pre-execution contract
				List<ContractType> contraType = contractDao.getContractTypeById(Integer.parseInt(objects[3].toString()));
				Iterator<ContractType> iterator4 = contraType.iterator();
				JSONArray contractName = new JSONArray();
				while(iterator4.hasNext()){
				ContractType contractType = iterator4.next();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("contract_type_id", contractType.getCont_type_id());
				jsonObj.put("contract_type_name", contractType.getCont_type_name());
				contractName.add(jsonObj);
				}
				search.put("contract_type", contractName);
				
				//get parties of pre-Execution contract
				List<ContractParties> partyJson = contractDao.getContractPartiesByContractId(Integer.parseInt(objects[3].toString()));
				Iterator<ContractParties> iterator5 = partyJson.iterator();
				JSONArray multiParty = new JSONArray();
				while(iterator5.hasNext()){
				ContractParties contractParties = iterator5.next();
				JSONObject party = new JSONObject();
				party.put("party_id", contractParties.getCont_party_id());
				party.put("party_name", contractParties.getCont_party_name());
				multiParty.add(party);
				}
				search.put("parties",multiParty);
				
				search.put("exec_contract_added_by", objects[5]);
				search.put("user_id", Integer.parseInt(session.getAttribute("sess_user_id").toString()));
				search.put("user_role", Integer.parseInt(session.getAttribute("sess_user_role").toString()));
				sendList.add(search);
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Get Executed Contract Executed with

	@Override
	public Map<Integer, String> getAllExecutedWith() {
		try {
			List<ExecutedContracts> contractList = executedContractDao.getAllExecutedWith(ExecutedContracts.class);
			Map<Integer, String> contract_list = new HashMap<Integer, String>();
			for (ExecutedContracts temp : contractList) {
				// contract_list.put(0, "--Select--");
				contract_list.put(temp.getExec_contract_id(), temp.getExec_contract_executed_with());
			}

			return contract_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : download Executed Contract documents
	@Override
	public void downloadExecutedContractDoc(int exec_doc_id, HttpServletResponse response) {
		try {
			if (executedContractDao.getExecutedContractDocumentFilePath(exec_doc_id) != null) {
				File file = new File(executedContractDao.getExecutedContractDocumentFilePath(exec_doc_id));
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
	// Method purpose : get Executed Contract details by id

	@Override
	public ExecutedContractReference getJoinedExecutedContractDetailsById(int exec_id) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			Object object[] = executedContractDao.getJoinedExecutedContractDetailsById(exec_id);

			ExecutedContractReference executedContractReference = new ExecutedContractReference();
			executedContractReference.setExec_contract_entity_name(object[0].toString());
			executedContractReference.setExec_contract_unit_name(object[1].toString());
			executedContractReference.setExec_contract_function_name(object[2].toString());
			executedContractReference.setExec_contract_type_name(object[3].toString());
			if(object[4] != null)
			executedContractReference.setExec_contract_description(object[4].toString());
			//executedContractReference.setExec_contract_internal_contact_person(object[5].toString());
			//executedContractReference.setExec_contract_executed_with(object[6].toString());
			if(object[7] != null)
			executedContractReference.setExec_contract_executed_contact_name(object[7].toString());
			if(object[8] != null)
			executedContractReference.setExec_contract_date(sdfOut.format(sdfIn.parse(object[8].toString())));
			executedContractReference.setExec_parties(executedContractDao.getExecutedContractPartiesByContractId(Integer.parseInt(object[9].toString())));
			executedContractReference.setExec_contract_entity_id(Integer.parseInt(object[10].toString()));
			executedContractReference.setExec_contract_unit_id(Integer.parseInt(object[11].toString()));
			executedContractReference.setExec_contract_function_id(Integer.parseInt(object[12].toString()));
			executedContractReference.setExec_contract_type_id(Integer.parseInt(object[13].toString()));
			executedContractReference.setExec_contract_id(Integer.parseInt(object[9].toString()));
			if(object[8] != null)
			executedContractReference.setExec_contract_date_date(sdfIn.parse(object[8].toString()));
			if(object[14] != null)
			executedContractReference.setExec_contract_resposible_person(Integer.parseInt(object[14].toString()));
			executedContractReference.setExec_contract_resposible_person_name(userService.getUserFullNameById(Integer.parseInt(object[14].toString())));
			
			if(object[15] != null)
			executedContractReference.setExec_contract_start_date(sdfIn.parse(object[15].toString()));
			if(object[16] != null)
			executedContractReference.setExec_contract_end_date(sdfIn.parse(object[16].toString()));
			if(object[17] != null)
			executedContractReference.setExec_contract_surviving_obl(object[17].toString());
			if(object[18] != null)
			executedContractReference.setExec_contract_payment(object[18].toString());
			if(object[19] != null)
			executedContractReference.setExec_contract_notice_period(object[19].toString());
			if(object[20] != null)
			executedContractReference.setExec_contract_insurance(object[20].toString());
			if(object[21] != null)
			executedContractReference.setExec_contract_damages(object[21].toString());
			if(object[22] != null)
			executedContractReference.setExec_contract_jurisdiction(object[22].toString());
			if(object[23] != null)
			executedContractReference.setExec_contract_arbitration(object[23].toString());
			if(object[24] != null)
			executedContractReference.setExec_contract_criticality(object[24].toString());
			if(object[25] != null)
			executedContractReference.setExec_contract_cont_person_email(object[25].toString());
			if(object[26] != null)
			executedContractReference.setExec_contract_cont_person_number(object[26].toString());
			if(object[27] != null)
				executedContractReference.setExec_contract_execution_date(sdfOut.format(sdfIn.parse(object[27].toString())));
			if(object[28] != null)
				executedContractReference.setExec_contract_renewal_date(sdfOut.format(sdfIn.parse(object[28].toString())));
			if(object[29] != null)
				executedContractReference.setExec_contract_lockin_period(object[29].toString());
			if(object[30] != null)
				executedContractReference.setExec_contract_termination_clause(object[30].toString());
			if(object[31] != null)
				executedContractReference.setExec_contract_consider_involve(object[31].toString());
			if(object[32] != null)
				executedContractReference.setExec_contract_signatories_assign(object[32].toString());
			if(object[33] != null)
				executedContractReference.setExec_contract_ammendment(object[33].toString());
			if(object[34] != null)
				executedContractReference.setExec_contract_agree_status(object[34].toString());
			if(object[35] != null)
				executedContractReference.setExec_contract_renewal_status(object[35].toString());
			if(object[36] != null)
				executedContractReference.setExec_remind_date(sdfOut.format(sdfIn.parse(object[36].toString())));
			if(object[37] != null)
				executedContractReference.setExec_remind_date_second(sdfOut.format(sdfIn.parse(object[37].toString())));
			if(object[38] != null)
				executedContractReference.setExec_remind_date_third(sdfOut.format(sdfIn.parse(object[38].toString())));
			if(object[39] != null)
				executedContractReference.setExec_contract_division(object[39].toString());
			if(object[40] != null)
				executedContractReference.setExec_force_majeure(object[40].toString());
			
			if(object[41] != null)
			executedContractReference.setExec_royalty_number(object[41].toString());
			if(object[42] != null)
			executedContractReference.setExec_option_number(object[42].toString());
			if(object[43] != null)
			executedContractReference.setExec_territory_number(object[43].toString());
			if(object[44] != null)
			executedContractReference.setExec_crop_number(object[44].toString());
			if(object[45] != null)
			executedContractReference.setExec_technology_number(object[45].toString());
			if(object[46] != null)
			executedContractReference.setExec_report_number(object[46].toString());
			if(object[47] != null)
			executedContractReference.setExec_milestone_number(object[47].toString());
			if(object[48] != null)
				executedContractReference.setExec__assignability(object[48].toString());
			
			return executedContractReference;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

	// Method Created : Harshad Padole
	// Method purpose : get list of parties by contract id
	@Override
	public List<ContractParties> getPartiesByContractId(int exec_id) {
		try {
			return executedContractDao.getExecutedContractPartiesByContractId(exec_id);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Executed Contract
	@Override
	public int deleteExecutedContract(int exec_contract_id) {
		try {
			int delete_id = executedContractDao.deleteExecutedContract(exec_contract_id);
			// persist history log
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(exec_contract_id);
			logs.setLog_sub_activity_id(delete_id);
			logs.setLog_activity("Delete");
			logs.setLog_related_to("Executed contract");
			logs.setLog_sub_activity("Delete Executed contract");
			logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
			executedContractDao.saveLogs(logs);

			return delete_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteExecutedContractDocument(int exec_doc_id) {
		try {
			String docs = executedContractDao.getExecutedContractDocumentFilePath(exec_doc_id);
			File file = new File(docs);
			if(file.delete()){
				//System.out.println("File "+ file.getName() +" deleted successfully..");
			}else{
			//System.out.println("file deletion is failed..!");	
			}
			return executedContractDao.deleteExecutedContractDocument(exec_doc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int saveActionItem(ExecuteActionItem actionItem,HttpSession session) {
		try {
			actionItem.setExec_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			actionItem.setExec_created_at(utilitiesService.getCurrentDate());

			int last_gen_value = executedContractDao.saveActionItem(actionItem);

			TransactionalActionItem actionItem2 = new TransactionalActionItem();
			
			actionItem2.setAtrn_action_id(last_gen_value);
			actionItem2.setAtrn_resposible_user(actionItem.getExec_responsible_user_id());
			actionItem2.setAtrn_contract_id(actionItem.getExec_contract_id());
			actionItem2.setAtrn_frequency(actionItem.getExec_frequency());
			actionItem2.setAtrn_first_alert_prior_days(actionItem.getExec_first_alert_prior_days());
			actionItem2.setAtrn_second_alert_prior_days(actionItem.getExec_second_alert_prior_days());
			actionItem2.setAtrn_action_due_date(actionItem.getExec_due_date());
			actionItem2.setAtrn_status("Pending");
			actionItem2.setAtrn_created_date(utilitiesService.getCurrentDate());
			executedContractDao.save(actionItem2);
			return last_gen_value;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public ExecuteActionItem getActionItem(int exec_id) {
		try {
			 return executedContractDao.getExecutedActionItemDetails(exec_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateExecuteActionItem(ExecuteActionItem actionItem) {
		try {
			ExecuteActionItem OldData = executedContractDao.getExecutedActionItemDetails(actionItem.getExec_action_id());
			
			actionItem.setExec_contract_id(OldData.getExec_contract_id());
			actionItem.setExec_added_by(OldData.getExec_added_by());
			actionItem.setExec_created_at(OldData.getExec_created_at());
			
			executedContractDao.updateExecuteActionItem(actionItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<ExecuteActionItemReferenece> getAllExecutedActionItem(int exec_id) {
		try {
			
			SimpleDateFormat sdfIn1 = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdfOut1 = new SimpleDateFormat("yyyy-MM-dd");
			List<Object> actionItem = executedContractDao.getAllExecutedActionItem(exec_id);
			Iterator<Object> iterator = actionItem.iterator();
			List<ExecuteActionItemReferenece> sendList = new ArrayList<>();
			while (iterator.hasNext()) {
				Object[] object = (Object[]) iterator.next();
				ExecuteActionItemReferenece ref = new ExecuteActionItemReferenece();
				ref.setExec_action_item(object[0].toString());
				ref.setExec_frequency(object[1].toString());
				ref.setExec_due_date(sdfIn1.format(sdfOut1.parse(object[2].toString())));
				ref.setExec_responsible_user_name(userService.getUserFullNameById(Integer.parseInt(object[3].toString())));
				ref.setExec_first_alert_prior_days(Integer.parseInt(object[4].toString()));
				if(!object[5].equals("") || !object[5].equals("0")){
				ref.setExec_second_alert_prior_days(Integer.parseInt(object[5].toString()));
				}else{
				ref.setExec_second_alert_prior_days(0);
				}
				ref.setExec_action_id(Integer.parseInt(object[6].toString()));
				ref.setExec_added_by(Integer.parseInt(object[7].toString()));
				sendList.add(ref);
			}
			return sendList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ExecuteActionItemReferenece> getActionItemHistoryById(int action_id) {
	try {
		SimpleDateFormat sdfIn1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdfOut1 = new SimpleDateFormat("yyyy-MM-dd");
		List<Object> item = executedContractDao.getActionItemHistoryById(action_id);
		Iterator<Object> iterator = item.iterator();
		List<ExecuteActionItemReferenece> sendlist = new ArrayList<>();
		while (iterator.hasNext()) {
			Object[] object =  (Object[])iterator.next();
			ExecuteActionItemReferenece actionItem = new ExecuteActionItemReferenece();
			
			actionItem.setAtrn_id(Integer.parseInt(object[0].toString()));
			actionItem.setExec_due_date(sdfIn1.format(sdfOut1.parse(object[1].toString())));
			actionItem.setExec_action_id(Integer.parseInt(object[2].toString()));
			
			if(Integer.parseInt(object[3].toString()) != 0){
			actionItem.setAtrn_completed_by(userService.getUserFullNameById(Integer.parseInt(object[3].toString())));
			}else{
			actionItem.setAtrn_completed_by("NA");
			}
			//System.out.println("object 4 is :"+object[4]);
			if(object[4] != null){
				actionItem.setAtrn_completed_date(sdfIn1.format(sdfOut1.parse(object[4].toString())));
			}else{
				actionItem.setAtrn_completed_date("NA");
			}
			//actionItem.setAtrn_completed_date(object[4].toString());
			actionItem.setExec_contract_id(Integer.parseInt(object[5].toString()));
			actionItem.setExec_first_alert_prior_days(Integer.parseInt(object[6].toString()));
			actionItem.setExec_frequency(object[7].toString());
			actionItem.setExec_responsible_user_name(userService.getUserFullNameById(Integer.parseInt(object[8].toString())));
			actionItem.setExec_second_alert_prior_days(Integer.parseInt(object[9].toString()));
			actionItem.setAtrn_status(object[10].toString());
			//actionItem.setExec_action_item(executedContractDao.getExecutedActionItemDetails(Integer.parseInt(object[2].toString())).getExec_action_item());
	
			sendlist.add(actionItem);
		}
		return sendlist;
	} catch(Exception e) {
		e.printStackTrace();
	}
	return null;
}

	@Override
	public TransactionalActionItem getCompletionTaskById(int atrn_id) {
		try {
			return executedContractDao.getCompletionTaskById(atrn_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateTaskCompletionAction(TransactionalActionItem item, HttpSession session) {
		try {
			TransactionalActionItem oldItem = executedContractDao.getCompletionTaskById(item.getAtrn_id());
			item.setAtrn_action_id(oldItem.getAtrn_action_id());
			item.setAtrn_contract_id(oldItem.getAtrn_contract_id());
			item.setAtrn_created_date(oldItem.getAtrn_created_date());
			item.setAtrn_action_due_date(oldItem.getAtrn_action_due_date());
			item.setAtrn_first_alert_prior_days(oldItem.getAtrn_first_alert_prior_days());
			item.setAtrn_second_alert_prior_days(oldItem.getAtrn_second_alert_prior_days());
			item.setAtrn_frequency(oldItem.getAtrn_frequency());
			item.setAtrn_resposible_user(oldItem.getAtrn_resposible_user());
			item.setAtrn_status("Completed");
			item.setAtrn_completed_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			executedContractDao.updateCompletionTask(item);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String searchActionItem(int exec_id, String exec_frequency, String exec_from_due_date, String exec_to_due_date,
			HttpSession session) {
		try {
			JSONArray sendList = new JSONArray();
			
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			String from_date = "";
			String to_date = "";
			if (!exec_from_due_date.equals("") && !exec_to_due_date.equals("")) {
			
				 from_date = sdfIn.format(sdfOut.parse(exec_from_due_date));
			
				 to_date = sdfIn.format(sdfOut.parse(exec_to_due_date));
			}
			List<Object> listAction = executedContractDao.searchActionItem(exec_id, exec_frequency, from_date, to_date, session);
			
			Iterator<Object> iterator = listAction.iterator();
			while (iterator.hasNext()) {
				Object[] object = (Object[]) iterator.next();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("exec_action_item", object[0]);
				jsonObj.put("exec_frequency", object[1]);
				jsonObj.put("exec_due_date", sdfOut.format(sdfIn.parse(object[2].toString())));
				jsonObj.put("exec_responsible_user", userService.getUserFullNameById(Integer.parseInt(object[3].toString())));
				jsonObj.put("exec_first_alert_prior_days", Integer.parseInt(object[4].toString()));
				jsonObj.put("exec_second_alert_prior_days", Integer.parseInt(object[5].toString()));
				jsonObj.put("exec_action_id", Integer.parseInt(object[6].toString()));
				jsonObj.put("added_by", object[7].toString());
				jsonObj.put("user_role", Integer.parseInt(session.getAttribute("sess_user_role").toString()));
				jsonObj.put("user_id", Integer.parseInt(session.getAttribute("sess_user_id").toString()));
				sendList.add(jsonObj);
			}
			return sendList.toJSONString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getExportedXLS(ArrayList<MultipartFile> executed_contract_documents) {
		try {
			FileInputStream file = new FileInputStream(new File("D://abc.xlsx"));
			 
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) 
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                 
                while (cellIterator.hasNext()) 
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) 
                    {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + "t");
                            break;
                    }
                }
               // System.out.println("");
            }
            file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// Method Created : Tejashri Zurunge
		// Method purpose : get Executed Contract documents
		/*@Override
		public List<ExecutedContractDocuments> getAllExecutedContractDocumentsById(int exec_id) {
			try {
				return executedContractDao.getAllExecutedContractDocumentsById(exec_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}*/
	
	
	@Override
	public List<ExecutedContractDocuments> getAllExecutedContractDocumentsById(int exec_id, int type) {
		try {
			return executedContractDao.getAllExecutedContractDocumentsById(exec_id, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addExecContractDocument(int exec_contra_id, MultipartFile executed_contract_documents) {
		try {
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueDoc = executedContractDao.getLastGeneratedValueByContactId(exec_contra_id,1);
				if (executed_contract_documents.getSize() > 0) {
					File dir = new File("D:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "ContractDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = executed_contract_documents.getOriginalFilename();
					generatedFileName = "uploadedContractDoc_" + exec_contra_id + "_" + lastGeneratedValueDoc + "."
							+ executed_contract_documents.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(executed_contract_documents.getBytes());

					ExecutedContractDocuments contracts = new ExecutedContractDocuments();

					contracts.setExec_contract_id(exec_contra_id);
					contracts.setExec_original_file_name(originalFileName);
					contracts.setExec_generated_file_name(
							"C:/"+project_name+"/Documents/ContractDocuments/" + generatedFileName);
					contracts.setExec_last_generated_value_for_contract_id(lastGeneratedValueDoc);
					contracts.setExec_contract_created_date(utilitieservice.getCurrentDateWithTime());
					contracts.setExec_doc_added_by(utilitieservice.getCurrentSessionUserId(httpSession));
					contracts.setExec_doc_type(1);
					executedContractDao.save(contracts);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			}
	@Override
	public int termrenwpersist(TermRenwContract termrenwcontract, HttpSession session, String status, ArrayList<MultipartFile> termrenw_documents) {
		try {
			

			Date curr_date = utilitieservice.getCurrentDate();
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			termrenwcontract.setTermren_contract_addedby(user_id);
			termrenwcontract.setTermren_contract_created_at(curr_date);
				
				
			int termrenwcontract_id = executedContractDao.savetermrenwContract(termrenwcontract);
			
			String originalFileName = null;
			String generatedFileName = null;
			
			int lastGeneratedValueContactDoc = executedContractDao.getLastGeneratedValueByContactId(termrenwcontract_id, 3);

			for (int i = 0; i < termrenw_documents.size(); i++) {
				MultipartFile file = termrenw_documents.get(i);
				if (file.getSize() > 0) {
					File dir = new File("D:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "ContractDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueContactDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedContraDoc_" + termrenwcontract_id + "_" + lastGeneratedValueContactDoc + "."
							+ file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					ExecutedContractDocuments newDoc = new ExecutedContractDocuments();
					newDoc.setExec_contract_id(termrenwcontract_id);
					newDoc.setExec_original_file_name(originalFileName);
					newDoc.setExec_generated_file_name("D:/"+project_name+"/Documents/ContractDocuments/" + generatedFileName);
					newDoc.setExec_last_generated_value_for_contract_id(lastGeneratedValueContactDoc);
					newDoc.setExec_doc_added_by(utilitieservice.getCurrentSessionUserId(session));
					newDoc.setExec_contract_created_date(utilitieservice.getCurrentDate());
					newDoc.setExec_doc_type(3);
					executedContractDao.saveContractDocuments(newDoc);
				}
			}
			
			return termrenwcontract_id;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
   return 0;
}
	@Override
	public List<TermRenwContractRef> getJoinedTermRenContractDetails(int exec_id, HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

			List<TermRenwContractRef> allTermRenwContractRefDetailsSend = new ArrayList<>();

			List<Object> allTermRenwContractRef = executedContractDao.getJoinedTermRenwContractRefDetails(exec_id, session);
			Iterator<Object> itr = allTermRenwContractRef.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
				TermRenwContractRef termRenwContractRef = new TermRenwContractRef();
				
				
				if(object[0]!=null)
					termRenwContractRef.setReftermren_contract_start_date_name(sdfOut.format(sdfIn.parse(object[0].toString())));
					else
						termRenwContractRef.setReftermren_contract_start_date_name("NA");
				if(object[1]!=null)
					termRenwContractRef.setReftermren_contract_end_date_name(sdfOut.format(sdfIn.parse(object[1].toString())));
					else
						termRenwContractRef.setReftermren_contract_end_date_name("NA"); 
				if(object[2]!=null)
					termRenwContractRef.setReftermren_responsible_person_name(object[2].toString() +' '+ object[3].toString());
				    else
				    	termRenwContractRef.setReftermren_responsible_person_name("NA");
		
		
				
				
				
				termRenwContractRef.setReftermren_contract_status(object[4].toString());
				
				termRenwContractRef.setReftermren_contract_doc(executedContractDao.getAllExecutedContractDocumentsById(Integer.parseInt(object[5].toString()), 3));
					
					
				termRenwContractRef.setReftermren_contract_id(Integer.parseInt(object[5].toString()));
				allTermRenwContractRefDetailsSend.add(termRenwContractRef);
				
				
			}
			return allTermRenwContractRefDetailsSend;

		
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public TermRenwContract getTermRenContractById(int termren_contract_id) {
		try {
			return executedContractDao.getTermRenContractById(termren_contract_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	}
