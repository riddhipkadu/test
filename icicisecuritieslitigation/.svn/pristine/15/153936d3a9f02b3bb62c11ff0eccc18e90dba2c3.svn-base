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

import lcmt.dao.LawFirmDao;
import lcmt.domain.LawFirm;
import lcmt.domain.LitigationType;
import lcmt.service.LawFirmService;
import lcmt.service.UtilitiesService;
/*
 * Author: Harshad Padole
 * Date: 24/08/2016
 * Updated By: 
 * Updated Date: 
 * 
 * */
@Service("lawFirmService")
public class LawFirmServiceImpl implements LawFirmService {

	@Autowired
	LawFirmDao lawFirmDao;
	
	@Autowired
	UtilitiesService utilitiService;
	
	@Autowired
	HttpSession httpSession;
	

	//Method created:	Tejashri Zurunge
	//Method Purpose:	Add new Law Firm
	@Override
	public void persist(LawFirm lawFirm) {
		try {
			
			 lawFirm.setLawf_added_by(utilitiService.getCurrentSessionUserId(httpSession));
			 lawFirm.setLawf_created_at(utilitiService.getCurrentDate());
			 lawFirm.setLawf_updated_at(utilitiService.getCurrentDate());
			 
			 lawFirmDao.persist(lawFirm);
			 
			} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
		//Method created:	Tejashri Zurunge
		//Method Purpose:	Add new Law Firm
	@Override
	public Map<Integer, String> getAllLawFirm() {
		try {
			List<LawFirm> result = lawFirmDao.getAllLawFirm(LawFirm.class);
			Map<Integer,String> sendList = new HashMap<Integer,String>();
			sendList.put(0, "--Select--");
			for(LawFirm list:result){
				sendList.put(list.getLawf_id(), list.getLawf_name());
			}
			return sendList;
			
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		return null;
	}
		
			//Method created:	Tejashri Zurunge
			//Method Purpose:	list the all Law Firm
		@Override
		public List<LawFirm> getAll() {
			try {
				return lawFirmDao.getAllLawFirm(LawFirm.class);
			} catch (Exception e) {
				e.printStackTrace();				
			}
			return null;
		}


		//Method created:	Tejashri Zurunge
		//Method Purpose:	Update the all Law Firm
	@Override
		public void updateLawFirm(LawFirm lawFirm) {
			try {
				
				LawFirm oldLawFirm = getLawFirmById(lawFirm.getLawf_id());
				
				LawFirm newLawFirm = new LawFirm();
				
				//Set old AreaOfExpertise data to new AreaOfExpertise
				newLawFirm.setLawf_id(oldLawFirm.getLawf_id());
				newLawFirm.setLawf_added_by(oldLawFirm.getLawf_added_by());
				newLawFirm.setLawf_created_at(oldLawFirm.getLawf_created_at());
				
				//Set new data to newAreaOfExpertise object
				newLawFirm.setLawf_name(lawFirm.getLawf_name());
				newLawFirm.setLawf_contact_person(lawFirm.getLawf_contact_person());
				newLawFirm.setLawf_mobile_no(lawFirm.getLawf_mobile_no());
				newLawFirm.setLawf_email_id(lawFirm.getLawf_email_id());
				newLawFirm.setLawf_address(lawFirm.getLawf_address());
				
				lawFirmDao.updateLawFirm(newLawFirm);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//Method created:	Tejashri Zurunge
		//Method Purpose:	Get the all Law Firm by Id
		@Override
		public LawFirm getLawFirmById(int lawf_id) {
			try {
				return lawFirmDao.getLawFirmById(lawf_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
		}

		//Method created:	Tejashri Zurunge
		//Method Purpose:	To check whether Law Firm is exist or not
				
		@Override
		public int isLawFirmNameExist(int lawf_id, String lawf_name) {
			try {
				return lawFirmDao.isLawFirmNameExist(lawf_id, lawf_name);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}

		@Override
		public int deleteLawFirm(int lawf_id) {
			try {
				int deleteCount = lawFirmDao.deleteLawFirm(lawf_id);
				return deleteCount;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}

		@SuppressWarnings("unchecked")
		@Override
		public String checkLawFirmDependancy(int lawf_id) {
			try {
				 JSONArray jsonArray = new JSONArray();
				 List<Object> result = lawFirmDao.checkLawFirmDependancy(lawf_id);
				 
				 Iterator<Object> iterator = result.iterator();
				 while(iterator.hasNext()){
					 Object object[] = (Object[]) iterator.next();
					 JSONObject jsonObj = new JSONObject();
					 jsonObj.put("liti_law_firm", Integer.parseInt(object[0].toString()));
					 jsonObj.put("hear_law_firm", Integer.parseInt(object[1].toString()));
					 jsonObj.put("exte_coun_law_firm", Integer.parseInt(object[2].toString()));
					 jsonObj.put("liti_counsel_fee_lawFirm", Integer.parseInt(object[3].toString()));
					 jsonObj.put("liti_advocate_fee_lawFirm", Integer.parseInt(object[4].toString()));
					 jsonArray.add(jsonObj);
				 }
				 return jsonArray.toJSONString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public String getAllLawFirmJson() {
			try {
				 JSONArray sendList = new JSONArray();
				 List<LawFirm> lawf = lawFirmDao.getAllLawFirm(LawFirm.class);
				 //System.out.println("Count "+res.size());
				 Iterator<LawFirm> iterator = lawf.iterator();
				 while(iterator.hasNext()){
					 LawFirm lawfirm = iterator.next();
					 
					 JSONObject jsonObject = new JSONObject();
					 jsonObject.put("label",lawfirm.getLawf_name());
					 jsonObject.put("value",lawfirm.getLawf_name());
					 //System.out.println("value "+litigationType.getLiti_type_name());
					 sendList.add(jsonObject);
				 }
				 return sendList.toJSONString();
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
			
}


	
