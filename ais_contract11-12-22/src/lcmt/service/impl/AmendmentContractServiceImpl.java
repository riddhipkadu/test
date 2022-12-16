package lcmt.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import lcmt.dao.AmendmentContractDao;
import lcmt.dao.ExecutedContractDao;
import lcmt.domain.AmendmentContract;
import lcmt.domain.AmendmentContractRef;
import lcmt.domain.ContractParties;
import lcmt.domain.ExecutedContractDocuments;
import lcmt.service.AmendmentContractService;
import lcmt.service.UtilitiesService;



@Repository(value = "amendmentcontractService")
@Transactional

public class AmendmentContractServiceImpl implements AmendmentContractService{
	
	@Autowired
	UtilitiesService utilitieservice;
	@Autowired 
	AmendmentContractDao amendmentcontractDao;
	@Autowired
	ExecutedContractDao executedContractDao;
	
	private @Value("#{config['project_name'] ?: 'null'}") String project_name;
	
	@Override
	public int persist(AmendmentContract amendmentcontract, ArrayList<String> additional_parties,HttpSession session, String status, ArrayList<MultipartFile> amend_doc) {
		try {
			//System.out.println("AmendmentContract_id" );
			Date curr_date = utilitieservice.getCurrentDate();
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			amendmentcontract.setAmend_contract_addedby(user_id);
			amendmentcontract.setAmend_contract_created_at(curr_date);
			if(status.equals("Pending"))
				amendmentcontract.setAmend_contract_status("Pending");
			int AmendmentContract_id = amendmentcontractDao.saveAmendmentContract(amendmentcontract);
			//System.out.println(AmendmentContract_id);
			// Save Parties Details
			for (int i = 0; i < additional_parties.size(); i++) {
				ContractParties contractParties = new ContractParties();
				contractParties.setCont_party_contract_id(AmendmentContract_id);
				contractParties.setCont_party_name(additional_parties.get(i));
				contractParties.setCont_party_type(4);
				contractParties.setCont_party_created_at(curr_date);
				contractParties.setCont_party_added_by(user_id);
				amendmentcontractDao.savePartyDetails(contractParties);
				// System.out.println("Party "+additional_parties.get(i));
			}
			String originalFileName = null;
			String generatedFileName = null;
			
			int lastGeneratedValueContactDoc = executedContractDao.getLastGeneratedValueByContactId(AmendmentContract_id, 2);

			for (int i = 0; i < amend_doc.size(); i++) {
				MultipartFile file = amend_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("D:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "ContractDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueContactDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedContraDoc_" + AmendmentContract_id + "_" + lastGeneratedValueContactDoc + "."
							+ file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					ExecutedContractDocuments newDoc = new ExecutedContractDocuments();
					newDoc.setExec_contract_id(AmendmentContract_id);
					newDoc.setExec_original_file_name(originalFileName);
					newDoc.setExec_generated_file_name("D:/"+project_name+"/Documents/ContractDocuments/" + generatedFileName);
					newDoc.setExec_last_generated_value_for_contract_id(lastGeneratedValueContactDoc);
					newDoc.setExec_doc_added_by(utilitieservice.getCurrentSessionUserId(session));
					newDoc.setExec_contract_created_date(utilitieservice.getCurrentDate());
					newDoc.setExec_doc_type(2);
					executedContractDao.saveContractDocuments(newDoc);
				}
			}
			
			return AmendmentContract_id;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
   return 0;
}
	
	
	
	@Override
	public List<AmendmentContractRef> getJoinedAmendmentContractDetails(int exec_id, HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

			List<AmendmentContractRef> allAmendmentContractsDetailsSend = new ArrayList<>();

			List<Object> allAmendmentContract = amendmentcontractDao.getJoinedAmendmentContractDetails(exec_id, session);
			Iterator<Object> itr = allAmendmentContract.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
				AmendmentContractRef amendmentcontractref = new AmendmentContractRef();
				
				
				if(object[0]!=null)
					amendmentcontractref.setRef_amend_contract_start_date(sdfOut.format(sdfIn.parse(object[0].toString())));
					else
				    amendmentcontractref.setRef_amend_contract_start_date("NA");
				if(object[1]!=null)
					amendmentcontractref.setRef_amend_contract_end_date(sdfOut.format(sdfIn.parse(object[1].toString())));
					else
				    amendmentcontractref.setRef_amend_contract_end_date("NA"); 
				if(object[2]!=null)
					amendmentcontractref.setRef_amend_Fresponsible_person(object[2].toString() +' '+ object[3].toString());
				    else
					amendmentcontractref.setRef_amend_Fresponsible_person("NA");
		
				amendmentcontractref.setRef_cont_parties(amendmentcontractDao.getContractPartiesByContractId(Integer.parseInt(object[7].toString())));
				
				if(object[4]!=null)
				amendmentcontractref.setAmend_contract_term(object[4].toString());
				else
				amendmentcontractref.setAmend_contract_term("NA");	
				
				if(object[5]!=null)
				amendmentcontractref.setAmend_contract_clauses(object[5].toString());
					else
				amendmentcontractref.setAmend_contract_clauses("NA");	
				
					amendmentcontractref.setAmend_contract_status(object[6].toString());
					amendmentcontractref.setAmend_doc(executedContractDao.getAllExecutedContractDocumentsById(Integer.parseInt(object[7].toString()), 2));
					
					
					amendmentcontractref.setAmend_contract_id(Integer.parseInt(object[7].toString()));
				allAmendmentContractsDetailsSend.add(amendmentcontractref);
				
				
			}
			return allAmendmentContractsDetailsSend;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*@Override
	public AmendmentContract getAmendmentContractById(int amend_contract_id) {

		try {
			return amendmentcontractDao.getAmendmentContractById(amend_contract_id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
	
	public AmendmentContractRef getJoinedAmendmentContractDetailsById(int amend_contract_id){
		try {
	    SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
		
		Object object[] = amendmentcontractDao.getAmendmentContractById(amend_contract_id);
	    AmendmentContractRef amendmentcontractref = new AmendmentContractRef();
	    
	    if(object[0]!=null)
			amendmentcontractref.setRef_amend_contract_start_date(sdfOut.format(sdfIn.parse(object[0].toString())));
			else
		    amendmentcontractref.setRef_amend_contract_start_date("NA");
		if(object[1]!=null)
			amendmentcontractref.setRef_amend_contract_end_date(sdfOut.format(sdfIn.parse(object[1].toString())));
			else
		    amendmentcontractref.setRef_amend_contract_end_date("NA"); 
		if(object[2]!=null)
			amendmentcontractref.setRef_amend_responsible_person(object[2].toString() +' '+ object[3].toString());
		    else
			amendmentcontractref.setRef_amend_responsible_person("NA");
		
		
		amendmentcontractref.setRef_cont_parties(amendmentcontractDao.getContractPartiesByContractId(Integer.parseInt(object[7].toString())));
		
		if(object[4]!=null)
		amendmentcontractref.setAmend_contract_term(object[4].toString());
		else
			amendmentcontractref.setAmend_contract_term("NA");	
		if(object[5]!=null)
			amendmentcontractref.setAmend_contract_clauses(object[5].toString());
			else
				amendmentcontractref.setAmend_contract_clauses("NA");	
		
			amendmentcontractref.setAmend_contract_status(object[6].toString());
			
			amendmentcontractref.setAmend_contract_id(Integer.parseInt(object[7].toString()));
			amendmentcontractref.setAmend_responsible_person(Integer.parseInt(object[8].toString()));
			if(object[0]!=null)
				amendmentcontractref.setAmend_contract_start_date(sdfIn.parse(object[0].toString()));
				else
					amendmentcontractref.setAmend_contract_start_date(null);
			if(object[1]!=null)
				amendmentcontractref.setAmend_contract_end_date(sdfIn.parse(object[1].toString()));
				else
					amendmentcontractref.setAmend_contract_end_date(null);
			amendmentcontractref.setAmend_exec_contract_id(Integer.parseInt(object[9].toString()));
			return amendmentcontractref;
			
      } catch (Exception e) {

        	e.printStackTrace();
			return null;
  }
		
	
	}


	
	
	@Override
	public void updateAmendmentContract(AmendmentContractRef amendmentContractRef, ArrayList<String> additional_parties,
			String status, HttpSession session,ArrayList<MultipartFile> amend_doc){
		 try{
		AmendmentContract Old = amendmentcontractDao.getAmendmentContById(amendmentContractRef.getAmend_contract_id());
		AmendmentContract newAmendment = new AmendmentContract();
		newAmendment.setAmend_contract_id(amendmentContractRef.getAmend_contract_id());
		newAmendment.setAmend_contract_start_date(amendmentContractRef.getAmend_contract_start_date());
		newAmendment.setAmend_contract_end_date(amendmentContractRef.getAmend_contract_end_date());
		newAmendment.setAmend_contract_term(amendmentContractRef.getAmend_contract_term());
		newAmendment.setAmend_contract_clauses(amendmentContractRef.getAmend_contract_clauses());
		//newAmendment.setAmend_responsible_person(amendmentContractRef.getAmend_responsible_person());
		newAmendment.setAmend_responsible_person(amendmentContractRef.getAmend_responsible_person());
		newAmendment.setAmend_contract_addedby(Old.getAmend_contract_addedby());
		newAmendment.setAmend_contract_created_at(Old.getAmend_contract_created_at());
		newAmendment.setAmend_contract_status(Old.getAmend_contract_status());
		newAmendment.setAmend_exec_contract_id(Old.getAmend_exec_contract_id());
		amendmentcontractDao.updateRecord(newAmendment);
		
		int AmendmentContract_id = amendmentContractRef.getAmend_contract_id();
		// Update Party details
					List<ContractParties> parties = amendmentContractRef.getRef_cont_parties();
					if (parties != null && parties.size() > 0) {
						for (ContractParties contractParties : parties) {
							ContractParties oldDataParty = amendmentcontractDao.getPartyById(contractParties.getCont_party_id());
							// Assign old data to new party data
							contractParties.setCont_party_type(oldDataParty.getCont_party_type());
							contractParties.setCont_party_contract_id(oldDataParty.getCont_party_contract_id());
							contractParties.setCont_party_series(oldDataParty.getCont_party_series());
							contractParties.setCont_party_added_by(oldDataParty.getCont_party_added_by());
							contractParties.setCont_party_created_at(oldDataParty.getCont_party_created_at());
							amendmentcontractDao.mergeParties(contractParties);
						}
					}

					// Save Parties Details
					if(additional_parties !=null){
						for (int i = 0; i < additional_parties.size(); i++) {
							ContractParties contractParties = new ContractParties();
							contractParties.setCont_party_contract_id(AmendmentContract_id);
							contractParties.setCont_party_name(additional_parties.get(i));
							contractParties.setCont_party_type(4);
							contractParties.setCont_party_created_at(utilitieservice.getCurrentDate());
							contractParties.setCont_party_added_by(utilitieservice.getCurrentSessionUserId(session));
							amendmentcontractDao.savePartyDetails(contractParties);
							// System.out.println("Party "+additional_parties.get(i));
						}
					}
					String originalFileName = null;
					String generatedFileName = null;
					int lastGeneratedValueContactDoc = executedContractDao.getLastGeneratedValueByContactId(AmendmentContract_id, 2);

					for (int i = 0; i < amend_doc.size(); i++) {
						MultipartFile file = amend_doc.get(i);
						if (file.getSize() > 0) {
							File dir = new File("D:" + File.separator + project_name + File.separator + "Documents" + File.separator
									+ "ContractDocuments");
							if (!dir.exists())
								dir.mkdirs();

							lastGeneratedValueContactDoc++;

							originalFileName = file.getOriginalFilename();
							generatedFileName = "uploadedContraDoc_" + AmendmentContract_id + "_" + lastGeneratedValueContactDoc + "."
									+ file.getOriginalFilename().split("\\.")[1];
							File newFile = new File(dir.getPath() + File.separator + generatedFileName);
							if (!newFile.exists()) {
								newFile.createNewFile();
							}

							@SuppressWarnings("resource")
							OutputStream outputStream = new FileOutputStream(newFile);

							outputStream.write(file.getBytes());

							ExecutedContractDocuments newDoc = new ExecutedContractDocuments();
							newDoc.setExec_contract_id(AmendmentContract_id);
							newDoc.setExec_original_file_name(originalFileName);
							newDoc.setExec_generated_file_name("D:/"+project_name+"/Documents/ContractDocuments/" + generatedFileName);
							newDoc.setExec_last_generated_value_for_contract_id(lastGeneratedValueContactDoc);
							newDoc.setExec_doc_added_by(utilitieservice.getCurrentSessionUserId(session));
							newDoc.setExec_contract_created_date(utilitieservice.getCurrentDate());
							newDoc.setExec_doc_type(2);
							executedContractDao.saveContractDocuments(newDoc);
		
						} 
					}
	} catch (Exception e) {
		e.printStackTrace();
	}
		 
	}
	
	@Override
	public int deleteAmendmentContract(int amend_contract_id) {
		try {
			//AmendmentContract amendmentcontract = amendmentcontractDao.getAmendmentContById(amend_contract_id);
			int delete_id = amendmentcontractDao.deleteAmendmentContract(amend_contract_id);
			return delete_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	

}

