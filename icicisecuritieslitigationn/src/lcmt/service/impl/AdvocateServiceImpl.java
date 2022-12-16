package lcmt.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.AdvocateDao;
import lcmt.domain.Advocate;
import lcmt.domain.Advocate_reference;
import lcmt.service.AdvocateService;
import lcmt.service.UtilitiesService;

@Service(value = "advocateService")
public class AdvocateServiceImpl implements AdvocateService {

	@Autowired
	UtilitiesService utilitiesService;
	@Autowired
	HttpSession session;

	@Autowired
	AdvocateDao advocateDao;

	// Method Created : Tejashri Zurunge
	// Method Purpose : Save Advocate
	@Override
	public void persist(Advocate advocate) {
		try {
			advocate.setAdvo_added_by(utilitiesService.getCurrentSessionUserId(session));
			advocate.setAdvo_created_at(utilitiesService.getCurrentDate());
			advocate.setAdvo_updated_at(utilitiesService.getCurrentDate());
			advocateDao.persist(advocate);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : List Advocate
	@Override
	public List<Advocate_reference> getAll() {
		try {
			List<Object> advocate = advocateDao.getAll();

			List<Advocate_reference> SendList = new ArrayList<>();

			Iterator<Object> iterator = advocate.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				Advocate_reference advo = new Advocate_reference();
				advo.setAdvo_id(Integer.parseInt(objects[0].toString()));
				advo.setAdvo_name(objects[1].toString());
				advo.setAdvo_law_firm_name(objects[3].toString());
				advo.setAdvo_mobile_no(objects[4].toString());
				advo.setAdvo_email_id(objects[5].toString());
				advo.setAdvo_area_of_expertise_name(objects[8].toString());

				SendList.add(advo);
			}
			return SendList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get Advocate by Id
	@Override
	public Advocate getAdvocateById(int advo_id) {
		try {
			return advocateDao.getAdvocateById(advo_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Update Advocate
	@Override
	public void updateAdvocate(Advocate advocate) {
		try {
			Advocate advocateOld = advocateDao.getAdvocateById(advocate.getAdvo_id());

			Advocate advocateNew = new Advocate();
			// set new data
			advocateNew.setAdvo_id(advocate.getAdvo_id());
			advocateNew.setAdvo_name(advocate.getAdvo_name());
			advocateNew.setAdvo_law_firm(advocate.getAdvo_law_firm());
			advocateNew.setAdvo_mobile_no(advocate.getAdvo_mobile_no());
			advocateNew.setAdvo_email_id(advocate.getAdvo_email_id());
			advocateNew.setAdvo_address(advocate.getAdvo_address());
			advocateNew.setAdvo_area_of_expertise(advocate.getAdvo_area_of_expertise());
			advocateNew.setAdvo_country_id(advocate.getAdvo_country_id());
			advocateNew.setAdvo_state_id(advocate.getAdvo_state_id());
			advocateNew.setAdvo_city_id(advocate.getAdvo_city_id());
			// set old data
			advocateNew.setAdvo_added_by(advocateOld.getAdvo_added_by());
			advocateNew.setAdvo_created_at(advocateOld.getAdvo_created_at());
			advocateNew.setAdvo_updated_at(utilitiesService.getCurrentDate());
			advocateDao.updateAdvocate(advocateNew);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Advocate
	@Override
	public int deleteAdvocate(int advo_id) {
		try {
			int deleteCount = advocateDao.deleteAdvocate(advo_id);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Search Advocate
	@SuppressWarnings("unchecked")
	@Override
	public String searchAdvocate(int advo_law_firm, int advo_city, int advo_area_of_expertise,int advo_country_id,int advo_state_id,int advo_advocate_id) {
		try {
			JSONArray sendList = new JSONArray();
			List<Object> result = advocateDao.searchAdvocate(advo_law_firm, advo_city, advo_area_of_expertise,advo_country_id,advo_state_id,advo_advocate_id);
			Iterator<Object> iterator = result.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				JSONObject search = new JSONObject();
				search.put("advo_id", objects[0].toString());
				search.put("advo_name", objects[1]);
				search.put("advo_law_firm", objects[3]);
				search.put("advo_mob_no", objects[4]);
				search.put("advo_email_id", objects[5]);
				search.put("advo_area_expe_name", objects[7]);
				sendList.add(search);
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get Advocate by Id
	@SuppressWarnings("unchecked")
	@Override
	public String getAdvocateByLawFirmId(int law_firm_id) {
		try {
			JSONArray sendList = new JSONArray();
			List<Advocate> res = advocateDao.getAdvocateByLawFirmId(law_firm_id);
			Iterator<Advocate> iterator = res.iterator();
			while (iterator.hasNext()) {
				Advocate advocate = iterator.next();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("advocate_id", advocate.getAdvo_id());
				jsonObject.put("advocate_name", advocate.getAdvo_name());

				sendList.add(jsonObject);

			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get only Country which exist in mst advocate table 
	@Override
	public List<Object> getJoinedCountries() {
		try {
			List<Object> res = advocateDao.getJoinedCountries();
			if(res !=null)
				return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get only State which exist in mst advocate table by country id
	@SuppressWarnings("unchecked")
	@Override
	public String getJoinedStateByCountryId(int country_id) {
		try {
			JSONArray sendList = new JSONArray();
			List<Object> res = advocateDao.getJoinedStateByCountryId(country_id);
			if(res !=null){
			    Iterator<Object> iterator = res.iterator();
				while (iterator.hasNext()) {
					Object[] objects = (Object[]) iterator.next();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("state_id",objects[0].toString());
					jsonObject.put("state_name",objects[1].toString());
					sendList.add(jsonObject);
				}
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get only City which exist in mst advocate table by state id
	@SuppressWarnings("unchecked")
	@Override
	public String getJoinedCityByStateId(int state_id) {
		try {
			JSONArray sendList = new JSONArray();
			List<Object> res = advocateDao.getJoinedCityByStateId(state_id);
			if(res !=null){
			    Iterator<Object> iterator = res.iterator();
				while (iterator.hasNext()) {
					Object[] objects = (Object[]) iterator.next();
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("city_id",objects[0].toString());
					jsonObject.put("city_name",objects[1].toString());
					sendList.add(jsonObject);
				}
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//Method Created : Harshad Padole
	//method Purpose : Get all advocate in json format
	@SuppressWarnings("unchecked")
	@Override
	public String getAllAdvocateJson() {
		try {
			JSONArray sendList = new JSONArray();
			List<Object> list = advocateDao.getAll();
			Iterator<Object> iterator = list.iterator();
			while(iterator.hasNext()){
				Object[] objects = (Object[]) iterator.next();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("label",objects[1].toString());
				jsonObject.put("value", objects[1].toString());
				sendList.add(jsonObject);
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int isAdvocateNameExist(String json) {
		try {
			return advocateDao.isAdvocateNameExist(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: check dependancy 	
	@SuppressWarnings("unchecked")
	@Override
	public String checkDependancyAdvocate(int advo_id) {
		try {
			JSONArray sendList = new JSONArray();
			List<Object> advocate =  advocateDao.checkDependancyAdvocate(advo_id);
			Iterator<Object> iterator = advocate.iterator();
			while(iterator.hasNext()){
			JSONObject JSONObj = new JSONObject();	
			Object objects[] = (Object[]) iterator.next();
			JSONObj.put("liti_advo", objects[0]);
			JSONObj.put("fees_advo", objects[1]);
			sendList.add(JSONObj);
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}