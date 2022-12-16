package lcmt.service;

import java.util.List;
import java.util.Map;

import lcmt.domain.ContractType;

public interface ContractTypeService {

	public void persist(ContractType contractType);
	public List<ContractType> getAll();
	public void updateContractType(ContractType contractType);
	public ContractType getContractTypeById(int cont_type_id);
	public int isContractTypeNameExist(int cont_type_id, String cont_type_name);
	public int deleteContractType(int cont_type_id);
	Map<Integer, String> getAllContractType();
	public String checkDependancyContractType(int cont_type_id);
	public String getAllContractTypeJson();
	public Map<Integer, String> getContractTypeByContractID(int cont_id);
	
}
