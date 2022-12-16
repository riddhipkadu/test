package lcmt.dao;

import lcmt.domain.ExecutedContracts;
import lcmt.domain.TransactionalActionItem;

import java.util.List;

import javax.servlet.http.HttpSession;

import lcmt.domain.ContractParties;
import lcmt.domain.ExecuteActionItem;
import lcmt.domain.ExecutedContractDocuments;

public interface ExecutedContractDao {

	public int saveExecutedContract(ExecutedContracts contracts);
	public ExecutedContracts getExecutedContactsById(int exec_id);
	public int updateExecutedContract(ExecutedContracts contracts);
	public int getLastGeneratedValueByContactId(int contract_id);
	public void saveContractDocuments(ExecutedContractDocuments contractDocuments);
	public ExecutedContractDocuments getDocumetById(int contract_id);
	//public <T> List<T> getAllExecutedContracts(Class<T> clazz);
	public <T> List<T> getAllExecutedContractsDetails(HttpSession session);
	public List<Object> searchExecutedContract(int exec_contract_type_id, int exec_contract_entity_id,int exec_contract_unit_id,int exec_contract_function_id, HttpSession session);
	public <T> List<T> getAllExecutedWith(Class<T> clazz);
	public String getExecutedContractDocumentFilePath(int exec_doc_id);
	public List<ExecutedContractDocuments> getAllExecutedContractDocumentsById(int exec_id);
	public Object[] getJoinedExecutedContractDetailsById(int exec_id);
	public void savePartyDetails(Object object);
	public List<ContractParties> getExecutedContractPartiesByContractId(int contract_id);
	public ContractParties getPartyById(int party_id);
	public void meageParties(Object object);
	public <T> List<T> getAllCompletedContract(HttpSession session); 
	public List<Object> searchCompletedContract(int exec_contract_type_id,int exec_contract_entity_id,int exec_contract_unit_id,int exec_contract_function_id, HttpSession session);
	public int deleteExecutedContract(int exec_contract_id);
	public int saveLogs(Object obj);
	public int deleteExecutedContractDocument(int exec_doc_id);
	public int saveActionItem(ExecuteActionItem actionItem);
	public void save(Object object);
	public ExecuteActionItem getExecutedActionItemDetails(int exec_id);
	public void updateExecuteActionItem(ExecuteActionItem actionItem);
	public List<Object> getAllExecutedActionItem(int exec_id);
	public List<Object> getActionItemHistoryById(int action_id);
	public TransactionalActionItem getCompletionTaskById(int atrn_id);
	public int updateCompletionTask(TransactionalActionItem item);
	public List<Object> searchActionItem(int exec_id, String exec_frequency, String exec_from_due_date, String exec_to_due_date,	HttpSession session);
}
