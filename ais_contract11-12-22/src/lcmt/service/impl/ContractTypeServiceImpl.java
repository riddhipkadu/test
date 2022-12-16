package lcmt.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.ContractTypeDao;
import lcmt.domain.ContractType;
import lcmt.service.ContractTypeService;
import lcmt.service.UtilitiesService;

@Service("contractService")
public class ContractTypeServiceImpl implements ContractTypeService {

	@Autowired
	UtilitiesService utilitiService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	ContractTypeDao contractTypeDao;

	// Method Created By: Tejashri Zurunge
	// Method Purpose: Add New Stage
	@Override
	public void persist(ContractType contractType) {
		try {

			contractType.setCont_type_added_by(utilitiService.getCurrentSessionUserId(httpSession));
			contractType.setCont_type_created_at(utilitiService.getCurrentDate());
			contractType.setCont_type_updated_at(utilitiService.getCurrentDate());
			contractTypeDao.persist(contractType);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: listing and view Contract Type
	@Override
	public List<ContractType> getAll() {
		try {
			return contractTypeDao.getAllContractType(ContractType.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	//Method Created By: Tejashri Zurunge
		//Method Purpose: Fetch all list of Contract Type
		@Override
		public Map<Integer, String> getAllContractType() {
			try {
				List<ContractType> contractList = contractTypeDao.getAllContractType(ContractType.class);
				Map<Integer , String> contract_list = new HashMap<Integer, String>();
				//contract_list.put(0, "--Select--");
				for(ContractType temp:contractList){
					
					contract_list.put(temp.getCont_type_id(), temp.getCont_type_name());
				}

				return contract_list;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}


	// Method Created : Tejashri Zurunge
	// Method purpose : Update Contract Type
	@Override
	public void updateContractType(ContractType contractType) {
		try {

			ContractType oldContractType = getContractTypeById(contractType.getCont_type_id());

			ContractType newContractType = new ContractType();

			// Set old AreaOfExpertise data to new AreaOfExpertise
			newContractType.setCont_type_id(oldContractType.getCont_type_id());
			newContractType.setCont_type_added_by(oldContractType.getCont_type_added_by());
			newContractType.setCont_type_created_at(oldContractType.getCont_type_created_at());
			newContractType.setCont_type_updated_at(utilitiService.getCurrentDate());

			// Set new data to newAreaOfExpertise object
			newContractType.setCont_type_name(contractType.getCont_type_name());

			contractTypeDao.updateContractType(newContractType);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: Updating Contract Type By Id
	@Override
	public ContractType getContractTypeById(int cont_type_id) {

		try {
			return contractTypeDao.getContractTypeById(cont_type_id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Check whether Contract Type Name Exist or not
	@Override
	public int isContractTypeNameExist(int cont_type_id, String cont_type_name) {
		try {
			return contractTypeDao.isContractTypeNameExist(cont_type_id, cont_type_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Contract Type
	@Override
	public int deleteContractType(int cont_type_id) {
		try {
			int deleteCount = contractTypeDao.deleteContractType(cont_type_id);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Contract Type	
	@SuppressWarnings("unchecked")
	@Override
	public String checkDependancyContractType(int cont_type_id) {
		try {
			JSONArray sendList = new JSONArray();
			List<Object> contractType =  contractTypeDao.checkDependancyContractType(cont_type_id);
			Iterator<Object> iterator = contractType.iterator();
			while(iterator.hasNext()){
			JSONObject JSONObj = new JSONObject();	
			Object objects[] = (Object[]) iterator.next();
			JSONObj.put("cont_type", objects[0]);
			JSONObj.put("exec_type", objects[1]);
			sendList.add(JSONObj);
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAllContractTypeJson() {
		try {
			JSONArray sendList = new JSONArray();
			 List<ContractType> cont = contractTypeDao.getAllContractType(ContractType.class);
			 Iterator<ContractType> iterator = cont.iterator();
			 while(iterator.hasNext()){
				 ContractType contract =  iterator.next();
				 
				 JSONObject jsonObject = new JSONObject();
				 jsonObject.put("label",contract.getCont_type_name());
				 jsonObject.put("value",contract.getCont_type_name());
				 sendList.add(jsonObject);
			 }
			 return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<Integer, String> getContractTypeByContractID(int cont_id) {
		try {
			List<Object> contList = contractTypeDao.getContractTypeByContractID(cont_id);
			Map<Integer, String> cont_list = new HashMap<Integer, String>();

			Iterator<Object> iterator = contList.iterator();
			cont_list.put(0, "--Select--");
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				cont_list.put(Integer.parseInt(objects[0].toString()), objects[1].toString());
			}
			return cont_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
