package lcmt.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import lcmt.domain.Contract;
import lcmt.domain.ContractDocuments;
import lcmt.domain.ContractHistory;
import lcmt.domain.ContractHistoryDocuments;
import lcmt.domain.ContractHistoryReference;
import lcmt.domain.ContractReference;

public interface ContractService {
	public int persist(Contract contract,ArrayList<MultipartFile> term_doc,ArrayList<MultipartFile> contract_doc, HttpSession session,ArrayList<String> additional_parties,ArrayList<String> list_type_of_contract,String status);
	public List<ContractReference> getJoinedContractDetails(HttpSession session);
	public ContractReference getJoinedContractDetailsById(int cont_id);
	public void persistContractHistory(ContractHistory contractHistory,ArrayList<MultipartFile> contract_hst_doc,ArrayList<String> chst_contract_type,HttpSession session, String adminStatus);
	public List<ContractHistoryReference> getContractHistoryById(int cont_id);
	public List<Object> getContractTypesForPreExeContracts(HttpSession session);
	public String searchContract(String json,HttpSession session);
	public Contract getContractByContractId(int cont_id);
	public void updateContract(ContractReference contract,ArrayList<MultipartFile> term_doc,ArrayList<MultipartFile> contract_doc,ArrayList<String> additional_parties,ArrayList<String> list_type_of_contract,String status,HttpSession session);
	public List<ContractDocuments> getAllContractDocumentsByContractId(int contract_id);
	public void downloadPreExecutedDocument(int cdoc_id, HttpServletResponse response);
	public void downloadPreExecutedHistoryDocument(int chst_doc_id, HttpServletResponse response);
	public ContractHistory getContractHistoryByHistoryId(int chst_id);
	//public int persistLogContractHistory(ContractHistoryLog contractHistoryLog);
	public void updateContractHistory(ContractHistoryReference contractHistory ,ArrayList<MultipartFile> hst_doc,ArrayList<String> chst_contract_type);
	public int deletePreExecutedParty(int party_id);
	public int deletePreExecutedContract(int cont_id);
	public ContractHistoryReference getContractHistory(int chst_history_id);
	public int deletePreExecutedContractHistory(int chst_id); 
	public int deletePreExecutedContractDocument(int cdoc_id);
	public List<ContractHistoryDocuments> getContractHistoryDocById(int chst_id);
	public int deleteContractHistoryDoc(int doc_id);
	public void saveContractByAccept(ContractReference contractRef,ArrayList<MultipartFile> term_doc,ArrayList<MultipartFile> contract_doc,ArrayList<String> additional_parties,ArrayList<String> list_type_of_contract,String status,int id,HttpSession session);
}
