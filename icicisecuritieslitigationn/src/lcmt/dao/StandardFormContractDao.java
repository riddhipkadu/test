package lcmt.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import lcmt.domain.Sfco_documents;
import lcmt.domain.StandardFormContractType;
import lcmt.domain.StandardFormContracts;

public interface StandardFormContractDao {

public List<Object> getListOfSfc(HttpSession session);
public StandardFormContracts getStandardFormContractsById(int sfco_id);
public void updateStandardFormContracts(StandardFormContracts standardFormContracts);
public int deleteStandardFormContracts(int sfco_id);
public int getLastGeneratedValueForSfcDoc(int sfco_id);
public void saveSfcDocuments(Sfco_documents documents);
public List<Sfco_documents> getDocumentBySfcId(int sfco_id);
public int deleteSfcoDocument(Sfco_documents sfco_documents);
public Sfco_documents getSfcoDocumentById(int sfco_doc_id);
public String getSfcoDocumentFilePath(int sfco_doc_id);
public int saveLogs(Object obj);

	public int persist(StandardFormContracts contracts);
	public void saveSfcoType(StandardFormContractType standardFormContractType);
	public <T> List<T> getAllStandardFormContractType(Class<T> clazz);
	public void updateStandardFormContractType(StandardFormContractType standardFormContractType);
	public StandardFormContractType getStandardFormContractTypeById(int sfco_type_id);
	public int isStandardFormContractTypeExist(int sfco_type_id, String sfco_type_name);
	public int deleteStandardFormContractType(int sfco_type_id);
	public int checkDependancySFCType(int sfco_type_id); 
	


}
