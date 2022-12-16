package lcmt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.DesignationDao;
import lcmt.domain.Designation;
import lcmt.service.DesignationService;
import lcmt.service.UtilitiesService;

/*
 * Author: Mahesh Kharote
 * Date: 19/02/2016
 * Updated By: Harshad Padole
 * Updated Date: 19/02/2016
 * 
 * */


@Service("designationService")
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	DesignationDao designationDao;
	
	@Autowired
	UtilitiesService utilitiesService;
	
	@Autowired
	HttpSession httpSession;
	
	//Method Created By: Harshad Padole
	//Method Purpose: Add New Designation to Database
	@Override
	public void persist(Designation designation) {
		try {
			
			designation.setDesi_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
			designation.setDesi_created_at(utilitiesService.getCurrentDate());
			designation.setDesi_approval_status("1");
			designation.setDesi_enable_status("1");
			
			designationDao.persist(designation);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//Method Created By: Harshad Padole
	//Method Purpose: Update Designation
	@Override
	public void updateDesignation(Designation designation) {
		try {

			Designation oldDesignation = getDesignationById(designation.getDesi_id());
			
			Designation newDesignation = new Designation();
			
			//Set old data to newDesignation object
			newDesignation.setDesi_id(oldDesignation.getDesi_id());
			newDesignation.setDesi_added_by(oldDesignation.getDesi_added_by());
			newDesignation.setDesi_created_at(oldDesignation.getDesi_created_at());
			newDesignation.setDesi_approval_status(oldDesignation.getDesi_approval_status());
			newDesignation.setDesi_enable_status(oldDesignation.getDesi_enable_status());
			
			//Set new data to newDesigation object
			newDesignation.setDesi_name(designation.getDesi_name());
			newDesignation.setDesi_parent_id(designation.getDesi_parent_id());
						
			designationDao.updateDesignation(newDesignation);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	//Method Created By: Harshad Padole
	//Method Purpose: Fetch List of all departments from database
	@Override
	public List<Designation> getAll() {
		try {
			return designationDao.getAll(Designation.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Harshad Padole
	//Method Purpose: Fetch Designation by desi Id
	@Override
	public Designation getDesignationById(int desi_id) {
		try {
			return designationDao.getDesignationById(desi_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	//Method Created By: Harshad Padole
	//Method Purpose: Fetch all designation list for dropdown menu
	@Override
	public Map<Integer, String> getAllDesignationForDropDown() {
		try {
			List<Designation> desiglist = designationDao.getAll(Designation.class);

			Map<Integer, String> designation_list = new HashMap<Integer , String>();

			for(Designation temp:desiglist){
				designation_list.put(0, "--Select--");
				designation_list.put(temp.getDesi_id(), temp.getDesi_name());
			}
			return designation_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
   
	//Method Created : Harshad Padole
		//Method Purpose : Approve or disapprove designation
		@Override
		public int approveDisapproveDesi(int desi_id, int desi_status) {
			try {
				return designationDao.approveDisapproveDesi(desi_id, desi_status);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
	    //Method Created : Harshad Padole
		//Method Purpose : Enable or disable designation
		@Override
		public int enableDisableDesi(int desi_id, int desi_status) {
			try {
				return designationDao.enableDisableDesi(desi_id, desi_status);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}


		@Override
		public int isDesiNameExist(int desi_id, String desi_name) {
			try {
				return designationDao.isDesiNameExist(desi_id, desi_name);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
}
