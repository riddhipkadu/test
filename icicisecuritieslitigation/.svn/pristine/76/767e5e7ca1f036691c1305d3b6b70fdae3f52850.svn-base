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

import lcmt.dao.AreaOfExpertiseDao;
import lcmt.domain.AreaOfExpertise;
import lcmt.service.AreaOfExpertiseService;
import lcmt.service.UtilitiesService;

/*
 * Author: Harshad Padole
 * Date: 23/08/2016
 * Updated By: 
 * Updated Date: 
 * 
 * */
@Service("AreaOfExpertiseService")
public class AreaOfExpertiseServiceImpl implements AreaOfExpertiseService {
	@Autowired
	UtilitiesService utilitiService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	AreaOfExpertiseDao areaOfExpertiseDao;
	
	    //Method Created By: Harshad Padole
		//Method Purpose   : Add New External Counsel		
		@Override
		public void persist(AreaOfExpertise areaOfExpertise) {
			try {
				 areaOfExpertise.setArea_expe_added_by(utilitiService.getCurrentSessionUserId(httpSession));
				 areaOfExpertise.setArea_expe_approval_status("1");
				 areaOfExpertise.setArea_expe_created_at(utilitiService.getCurrentDate());
				 areaOfExpertise.setArea_expe_enable_status("1");
				 areaOfExpertiseDao.persist(areaOfExpertise);
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		//Method Created By: Tejashri Zurunge
		//Method purpose: Get All Area of Expertise 
		
		@Override
		public List<AreaOfExpertise> getAll() {
			try {
				return areaOfExpertiseDao.getAllAreaOfExpertise(AreaOfExpertise.class);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
				//Method Created By: Tejashri Zurunge
				//Method purpose: Get Area of Expertise by id 
				
		public AreaOfExpertise getAreaOfExpertiseById(int area_expe_id) {
			try {
				return areaOfExpertiseDao.getAreaOfExpertiseById(area_expe_id);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}


		//Method Created By: Tejashri Zurunge
		//Method purpose: To Verify Whether Area Of Expertise is exist or not
		
		public int isAreaExpeNameExist(int area_expe_id, String area_expe_name) {
			try {
				return areaOfExpertiseDao.isAreaExpeNameExist(area_expe_id, area_expe_name);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}


		//Method Created By: Tejashri Zurunge
		//Method purpose: To Update Area Of Expertise
		
		@Override
		public void updateAreaOfExpertise(AreaOfExpertise areaOfExpertise) {
			try {
				
				AreaOfExpertise oldAreaOfExpertise = getAreaOfExpertiseById(areaOfExpertise.getArea_expe_id());
				
				AreaOfExpertise newAreaOfExpertise = new AreaOfExpertise();
				
				//Set old AreaOfExpertise data to new AreaOfExpertise
				newAreaOfExpertise.setArea_expe_id(oldAreaOfExpertise.getArea_expe_id());
				newAreaOfExpertise.setArea_expe_added_by(oldAreaOfExpertise.getArea_expe_added_by());
				newAreaOfExpertise.setArea_expe_created_at(oldAreaOfExpertise.getArea_expe_created_at());
				newAreaOfExpertise.setArea_expe_enable_status(oldAreaOfExpertise.getArea_expe_enable_status());
				newAreaOfExpertise.setArea_expe_approval_status(oldAreaOfExpertise.getArea_expe_approval_status());
				
				//Set new data to newAreaOfExpertise object
				newAreaOfExpertise.setArea_expe_name(areaOfExpertise.getArea_expe_name());
				
				areaOfExpertiseDao.updateAreaOfExpertise(newAreaOfExpertise);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		//Method Created : Harshad Padole
		//Method purpose : Get all area of Expertise Id and Name
		@Override
		public Map<Integer, String> getAllAreaOfExpertise() {
			try {
				List<AreaOfExpertise> result = areaOfExpertiseDao.getAllAreaOfExpertise(AreaOfExpertise.class);
				Map<Integer,String> sendList = new HashMap<Integer,String>();
				for(AreaOfExpertise list:result){
					sendList.put(0, "--Select--");
					sendList.put(list.getArea_expe_id(), list.getArea_expe_name());
				}
				return sendList;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		//Method Created : Tejashri Zurunge
		//Method purpose : Delete Area Of Expertise 
		@Override
		public int deleteAreaOfExpertise(int area_expe_id) {
			try {
				int deleteCount = areaOfExpertiseDao.deleteAreaOfExpertise(area_expe_id);
				return deleteCount;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}

		//Method Created : Tejashri Zurunge
		//Method purpose : Delete Area Of Expertise		
		@SuppressWarnings("unchecked")
		@Override
		public String getAllAreaOfExpertiseJson() {
			try {
				JSONArray sendlist = new JSONArray();
				List<AreaOfExpertise> aoe = areaOfExpertiseDao.getAllAreaOfExpertise(AreaOfExpertise.class);
				Iterator<AreaOfExpertise> iterator = aoe.iterator();
				while(iterator.hasNext()){
					AreaOfExpertise areaOfExpertise = iterator.next();
					
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("label", areaOfExpertise.getArea_expe_name());
					jsonObj.put("value", areaOfExpertise.getArea_expe_name());
					sendlist.add(jsonObj);
				}
				return sendlist.toJSONString();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}		
		
}


