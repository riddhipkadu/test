package lcmt.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import lcmt.domain.Contract;
import lcmt.domain.ContractContractType;
import lcmt.domain.ContractDocuments;
import lcmt.domain.ContractHistory;
import lcmt.domain.ContractHistoryDocuments;
import lcmt.domain.ContractParties;
import lcmt.domain.ContractType;

public interface ContractDao {

	public void persist(Object obj);
	//public List<Contract> getAllContracts();
	public Contract getContractById(int cont_id);
	public <T> List<T> getJoinedContractDetails(HttpSession session);
	public Object[] getJoinedContractDetailsById(int cont_id);
	public int persistContractHistory(ContractHistory contractHistory);
	public List<ContractHistory> getContractHistoryById(int cont_id);
	public List<Object> getContractTypesForPreExeContracts(HttpSession session);
	public <T> List <T> searchContract(String json,HttpSession session);
	public String getLatestContractStatusByContraId(int contra_id);
	public void updateRecord(Object object);
	public int getLastGenerateValueForContract(int contra_id);
	public int saveContract(Contract contract);
	public List<ContractDocuments> getAllContractDocumentsByContractId(int contract_id);
	public int getLastGenerateValueForContractHistory(int contra_history_id);
	public List<ContractHistoryDocuments> getAllHistoryDocumentsByHstId(int hst_id);
	public String getPreExecutionDocumentFilePath(int cdoc_id);
	public String getPreExecutionHistoryDocumentFilePath(int chst_doc_id);
	public void saveContractTypeList(Object object);
	public List<ContractContractType> getContractTypeByContractId(int contract_id);
	public List<ContractParties> getContractPartiesByContractId(int contract_id);
	public ContractHistory getContractHistoryByHistoryId(int chst_id);
	public void deleteContractType(int id,int type);
	public int deletePreExecutedParty(int party_id);
	public List<ContractType> getContractTypeById(int cont_id);
	public int deletePreExecutedContract(int cont_id);
	public List<ContractContractType> getContractType(int id,int type_id);
	public int deletePreExecutedContractHistory(int chst_id);
	public int deletePreExecutedContractDocument(int cdoc_id);
	public int deleteContractHistoryDoc(int doc_id);
	public Contract getContractByRequestId(int req_id);
}

