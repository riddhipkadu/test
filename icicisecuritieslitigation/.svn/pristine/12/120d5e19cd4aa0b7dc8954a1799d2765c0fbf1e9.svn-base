package lcmt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.ExternalCounselDao;
import lcmt.domain.Ext_Coun_Reference;
import lcmt.domain.ExternalCounsel;
import lcmt.domain.LawFirm;
import lcmt.service.ExternalCounselService;
import lcmt.service.UtilitiesService;

/*
 * Author: Harshad Padole
 * Date: 23/08/2016
 * Updated By: 
 * Updated Date: 
 * 
 * */
@Service("externalCounselService")
public class ExternalCounselServiceImpl implements ExternalCounselService {

	@Autowired
	UtilitiesService utilitiService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	ExternalCounselDao externalCounselDao;

	// Method Created By: Harshad Padole
	// Method Purpose: Add New External Counsel
	@Override
	public void persist(ExternalCounsel counsel) {
		try {
			counsel.setExte_coun_added_by(utilitiService.getCurrentSessionUserId(httpSession));
			counsel.setExte_coun_created_at(utilitiService.getCurrentDate());
			counsel.setExte_coun_updated_at(utilitiService.getCurrentDate());
			counsel.setExte_coun_approval_status("1");
			counsel.setExte_coun_enable_status("1");

			externalCounselDao.persist(counsel);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created By: Harshad Padole
	// Method Purpose: Add New External Counsel
	@Override
	public List<Ext_Coun_Reference> getAll() {
		try {
			List<Object> counsels = externalCounselDao.getAll();

			List<Ext_Coun_Reference> SendList = new ArrayList<>();

			Iterator<Object> iterator = counsels.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				Ext_Coun_Reference coun_Reference = new Ext_Coun_Reference();
				coun_Reference.setExte_coun_id(Integer.parseInt(objects[0].toString()));
				coun_Reference.setExte_coun_name(objects[1].toString());
				coun_Reference.setExte_coun_law_firm_id(Integer.parseInt(objects[2].toString()));
				coun_Reference.setExte_coun_law_firm(objects[3].toString());
				if(objects[4] != null){
				coun_Reference.setExte_coun_mobile_no(objects[4].toString());
				}else{
				coun_Reference.setExte_coun_mobile_no("NA");
				}
				
				if(objects[5] != null){
					coun_Reference.setExte_coun_email_id(objects[5].toString());
				}else{
				coun_Reference.setExte_coun_email_id("NA");
				}
				if(objects[6] != null){
				coun_Reference.setExte_coun_address(objects[6].toString());
				}else{
				coun_Reference.setExte_coun_address("NA");	
				}
				coun_Reference.setExte_coun_area_of_expertise_id(Integer.parseInt(objects[7].toString()));
				coun_Reference.setExte_coun_area_of_expertise(objects[8].toString());

				SendList.add(coun_Reference);

			}

			return SendList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Harshad Padole
	// Method Purpose: Get External Counsel By Id
	@Override
	public ExternalCounsel getExte_CounById(int exte_coun_id) {
		try {
			return externalCounselDao.getExte_CounById(exte_coun_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Harshad Padole
	// Method Purpose : Update External Counsel
	@Override
	public void updateExternalCounsel(ExternalCounsel counsel) {
		try {
			ExternalCounsel oldData = externalCounselDao.getExte_CounById(counsel.getExte_coun_id());

			ExternalCounsel newData = new ExternalCounsel();

			// Set Old Data
			newData.setExte_coun_added_by(oldData.getExte_coun_added_by());
			newData.setExte_coun_approval_status(oldData.getExte_coun_approval_status());
			newData.setExte_coun_enable_status(oldData.getExte_coun_enable_status());
			newData.setExte_coun_created_at(oldData.getExte_coun_created_at());
			newData.setExte_coun_updated_at(utilitiService.getCurrentDate());

			// Set New Updated data
			newData.setExte_coun_id(counsel.getExte_coun_id());
			newData.setExte_coun_city(counsel.getExte_coun_city());
			newData.setExte_coun_name(counsel.getExte_coun_name());
			newData.setExte_coun_law_firm(counsel.getExte_coun_law_firm());
			newData.setExte_coun_mobile_no(counsel.getExte_coun_mobile_no());
			newData.setExte_coun_email_id(counsel.getExte_coun_email_id());
			newData.setExte_coun_address(counsel.getExte_coun_address());
			newData.setExte_coun_area_of_expertise(counsel.getExte_coun_area_of_expertise());
			newData.setExte_coun_country_id(counsel.getExte_coun_country_id());
			newData.setExte_coun_state_id(counsel.getExte_coun_state_id());

			externalCounselDao.updateExternalCounsel(newData);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose : To check the Expternal Counsel exist or not

	@Override
	public int isExternalCounselNameExist(int exte_coun_id, String exte_coun_name) {
		try {
			return externalCounselDao.isExternalCounselNameExist(exte_coun_id, exte_coun_name);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: Delete External Counsel

	@Override
	public int deleteExternalCounsel(int exte_coun_id) {
		try {
			int deleteCount = externalCounselDao.deleteExternalCounsel(exte_coun_id);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: Search all External Counsel
	@SuppressWarnings("unchecked")
	@Override
	public String searchExternalCounsel(int exte_coun_country_id, int exte_coun_state_id, int exte_coun_city,
			int exte_coun_law_firm, int exte_coun_name, int exte_coun_area_of_expertise) {
		try {
			JSONArray sendList = new JSONArray();

			List<Object> result = externalCounselDao.searchExternalCounsel(exte_coun_country_id, exte_coun_state_id,
					exte_coun_city, exte_coun_law_firm, exte_coun_name, exte_coun_area_of_expertise);
			Iterator<Object> iterator = result.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				// System.out.println("ID "+objects[0]);
				JSONObject search = new JSONObject();
				search.put("exte_coun_id", objects[0].toString());
				// System.out.println("object at 0 :"+objects[0]);
				search.put("exte_coun_name", objects[1]);
				search.put("lawf_name", objects[3]);
				if(objects[4] != null){
				search.put("exte_coun_mobile_no", objects[4]);
				}else{
				search.put("exte_coun_mobile_no", "NA");
				}
				
				if(objects[5] != null){
				search.put("exte_coun_email_id", objects[5]);
				}else{
				search.put("exte_coun_email_id", "NA");	
				}
				search.put("area_expe_name", objects[8]);
				sendList.add(search);
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get counsel name by law firm id
	@SuppressWarnings("unchecked")
	@Override
	public String getCounselByLawFirmId(int law_firm_id) {
		try {
			JSONArray sendList = new JSONArray();
			List<ExternalCounsel> res = externalCounselDao.getConselAsPerLawFirm(law_firm_id);
			Iterator<ExternalCounsel> iterator = res.iterator();
			while (iterator.hasNext()) {
				ExternalCounsel counsel = iterator.next();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("counsel_id", counsel.getExte_coun_id());
				jsonObject.put("counsel_name", counsel.getExte_coun_name());

				sendList.add(jsonObject);

			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get country name
	@Override
	public Map<Integer, String> getCountryByCounselId() {
		try {
			List<Object> countryList = externalCounselDao.getCountryByCounselId();
			Map<Integer, String> country_list = new HashMap<Integer, String>();

			Iterator<Object> iterator = countryList.iterator();
			country_list.put(0, "--Select--");
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				country_list.put(Integer.parseInt(objects[0].toString()), objects[1].toString());
			}
			return country_list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get state by country id
	@SuppressWarnings("unchecked")
	@Override
	public String getJoinedStateByCountryId(int exte_coun_country_id) {
		try {
			JSONArray sendList = new JSONArray();
			List<Object> res = externalCounselDao.getJoinedStateByCountryId(exte_coun_country_id);
			if (res != null) {
				Iterator<Object> iterator = res.iterator();
				while (iterator.hasNext()) {
					Object[] objects = (Object[]) iterator.next();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("state_id", objects[0].toString());
					jsonObject.put("state_name", objects[1].toString());
					sendList.add(jsonObject);
				}
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get city by state
	@SuppressWarnings("unchecked")
	@Override
	public String getJoinedCityByStateId(int exte_coun_state_id) {
		try {
			JSONArray sendList = new JSONArray();
			List<Object> res = externalCounselDao.getJoinedCityByStateId(exte_coun_state_id);
			if (res != null) {
				Iterator<Object> iterator = res.iterator();
				while (iterator.hasNext()) {
					Object[] objects = (Object[]) iterator.next();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("city_id", objects[0].toString());
					jsonObject.put("city_name", objects[1].toString());
					sendList.add(jsonObject);
				}
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String checkDependencyExternalCounsel(int exte_coun_id) {
		try {
			 JSONArray jsonArray = new JSONArray();
			 List<Object> result = externalCounselDao.checkDependencyExternalCounsel(exte_coun_id);
			 
			 Iterator<Object> iterator = result.iterator();
			 while(iterator.hasNext()){
				 Object object[] = (Object[]) iterator.next();
				 JSONObject jsonObj = new JSONObject();
				 jsonObj.put("liti_counsel", Integer.parseInt(object[0].toString()));
				 jsonObj.put("hear_counsel", Integer.parseInt(object[1].toString()));
				 jsonObj.put("lega_counsel", Integer.parseInt(object[2].toString()));
				 jsonObj.put("fees_counsel", Integer.parseInt(object[3].toString()));
				 jsonArray.add(jsonObj);
			 }
			 return jsonArray.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get all external counsel as json
	@SuppressWarnings("unchecked")
	@Override
	public String getAllExternalCounselJson() {
		try {
			JSONArray sendList = new JSONArray();
			 List<Object> exte = externalCounselDao.getAll();
			 //System.out.println("Count "+res.size());
			 Iterator<Object> iterator = exte.iterator();
			 while(iterator.hasNext()){
				 Object object[] = (Object[]) iterator.next();
				 
				 JSONObject jsonObject = new JSONObject();
				 jsonObject.put("label",object[1].toString());
				 jsonObject.put("value",object[1].toString());
				 sendList.add(jsonObject);
			 }
			 return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
