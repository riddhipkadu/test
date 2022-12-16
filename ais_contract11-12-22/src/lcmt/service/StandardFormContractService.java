package lcmt.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import lcmt.domain.Sfco_documents;
import lcmt.domain.Sfco_reference;
import lcmt.domain.StandardFormContractType;
import lcmt.domain.StandardFormContracts;

public interface StandardFormContractService {

	public int persist(StandardFormContracts standardFormContracts, ArrayList<MultipartFile> sfco_doc);
	public List<Sfco_reference> getAllStdFormContracts(HttpSession session);
	public StandardFormContracts getStandardFormContractsById(int sfco_id);
	public void updateStandardFormContracts(StandardFormContracts standardFormContracts, ArrayList<MultipartFile> sfco_doc);
	public int deleteStandardFormContracts(int sfco_id);
	public List<Sfco_documents> getDocumentBySfcId(int sfco_id);
	public int deleteSfcoDocumentById(int sfco_doc_id);
	public void downloadSfcoDocument(int sfco_doc_id, HttpServletResponse response);
	

	//-------Standard Form Contract Type Methods---------
	public void saveSfcoType(StandardFormContractType standardFormContractType);
	public List<StandardFormContractType> getAll();
	public StandardFormContractType getStandardFormContractTypeById(int sfco_type_id);
	public void updateStandardFormContractType(StandardFormContractType standardFormContractType);
	public int isStandardFormContractTypeExist(int sfco_type_id, String sfco_type_name);
	public int deleteStandardFormContractType(int sfco_type_id);
	public int checkDependancySFCType(int sfco_type_id);
	public String getAllSFCTypejson();

}
