package lcmt.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import lcmt.domain.AmendmentContract;
import lcmt.domain.ContractParties;

public interface AmendmentContractDao {
	
	public int saveAmendmentContract(AmendmentContract amendmentcontract);

	public void savePartyDetails(Object object);
	public <T> List<T> getJoinedAmendmentContractDetails(int exec_id, HttpSession session);

	List<ContractParties> getContractPartiesByContractId(int contract_id);

	Object[] getAmendmentContractById(int amend_contract_id);

	AmendmentContract getAmendmentContById(int amend_contract_id);
	public void updateRecord(Object object);

	ContractParties getPartyById(int party_id);

	void mergeParties(Object object);

	int deleteAmendmentContract(int amend_contract_id);
}
