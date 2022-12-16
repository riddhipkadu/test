package lcmt.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import lcmt.domain.AmendmentContract;
import lcmt.domain.AmendmentContractRef;



public interface AmendmentContractService {
	


	public List<AmendmentContractRef> getJoinedAmendmentContractDetails(int exec_id, HttpSession session);

	public int persist(AmendmentContract amendmentcontract, ArrayList<String> additional_parties, HttpSession session,
			String status, ArrayList<MultipartFile> amend_documents);

	public AmendmentContractRef getJoinedAmendmentContractDetailsById(int amend_contract_id);

	public void updateAmendmentContract(AmendmentContractRef amendmentContractRef, ArrayList<String> additional_parties,
			String save, HttpSession session,ArrayList<MultipartFile> amend_doc);

	public int deleteAmendmentContract(int amend_contract_id);

}
