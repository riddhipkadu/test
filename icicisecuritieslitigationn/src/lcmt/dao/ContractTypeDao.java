package lcmt.dao;

import java.util.List;

import lcmt.domain.ContractType;

public interface ContractTypeDao {
	
	public void persist(Object obj);
	public <T> List<T> getAllContractType(Class<T> clazz);
	public void updateContractType(ContractType contractType);
	public ContractType getContractTypeById(int cont_type_id);
	public int isContractTypeNameExist(int cont_type_id, String cont_type_name);
	public int deleteContractType(int cont_type_id);
	public List<Object> checkDependancyContractType(int cont_type_id);
	public List<Object> getContractTypeByContractID(int cont_id);
}
