package lcmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import lcmt.domain.ContractParties;
import lcmt.domain.ExecuteActionItem;
import lcmt.domain.ExecuteActionItemReferenece;
import lcmt.domain.ExecutedContractDocuments;
import lcmt.domain.ExecutedContractReference;
import lcmt.domain.ExecutedContracts;
import lcmt.domain.TransactionalActionItem;

public interface ExecutedContractService {

	public int saveExecutedContract(ExecutedContracts contracts,ArrayList<MultipartFile> executed_contract_documents,ArrayList<String> additional_parties);
	public ExecutedContracts getExecutedContractById(int exec_id);
	public int updateExecutedContract(ExecutedContractReference contracts,ArrayList<MultipartFile> executed_contract_documents,ArrayList<String> additional_parties);
	public List<ExecutedContractReference> getListAllContract(HttpSession session);
	public String searchExecutedContract(int exec_contract_type_id, int exec_contract_entity_id,int	exec_contract_unit_id,int exec_contract_function_id, HttpSession session);
	public Map<Integer, String> getAllExecutedWith();
	public void downloadExecutedContractDoc(int exec_doc_id, HttpServletResponse response);
	public ExecutedContractReference getJoinedExecutedContractDetailsById(int exec_id);
	public List<ExecutedContractDocuments> getAllExecutedContractDocumentsById(int exec_id);
	public List<ContractParties> getPartiesByContractId(int exec_id);
	public int deleteExecutedContract(int exec_contract_id); 
	public int deleteExecutedContractDocument(int exec_doc_id);
	public int saveActionItem(ExecuteActionItem actionItem,HttpSession session);
	public ExecuteActionItem getActionItem(int exec_id);
	public void updateExecuteActionItem(ExecuteActionItem actionItem);
    public List<ExecuteActionItemReferenece> getAllExecutedActionItem(int exec_id);
    public List<ExecuteActionItemReferenece> getActionItemHistoryById(int action_id);
    public TransactionalActionItem getCompletionTaskById(int atrn_id);
    public int updateTaskCompletionAction(TransactionalActionItem item, HttpSession session);
    public String searchActionItem(int exec_id, String exec_frequency,String exec_from_due_date, String exec_to_due_date, HttpSession session);
    public String getExportedXLS(ArrayList<MultipartFile> executed_contract_documents);

}
