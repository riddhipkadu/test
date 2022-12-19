package lcmt.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.LocationDao;
import lcmt.domain.Location;
import lcmt.service.LocationService;
import lcmt.service.UtilitiesService;

/*
 * Author: Mugdha Chandratre
 * Date: 19/02/2016
 * Updated By: Mugdha Chandratre
 * Updated Date: 19/02/2016
 * 
 * */

@Service("locationService")
public class LocationServiceImpl implements LocationService {

	@Autowired 
	LocationDao locationDao;
	
	@Autowired
	UtilitiesService utilitiesService;
	
	@Autowired
	HttpSession httpSession;

	//Method created : Mugdha Chandratre
	//Method Purpose : Add Location
	@Override
	public void persist(Location location) {
		try {
			
			location.setLoca_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
			location.setLoca_created_at(utilitiesService.getCurrentDate());
			location.setLoca_approval_status("1");
			location.setLoca_enable_status("1");
			
			locationDao.persist(location);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//Method created : Mugdha Chandratre
	//Method Purpose : Update location
	@Override
	public void updateLocation(Location location) {
		try {
			
			Location oldLocation = getLocationById(location.getLoca_id());
			
			Location newLocation = new Location();
			
			//Set old location data to new location
			newLocation.setLoca_id(oldLocation.getLoca_id());
			newLocation.setLoca_added_by(oldLocation.getLoca_added_by());
			newLocation.setLoca_created_at(oldLocation.getLoca_created_at());
			newLocation.setLoca_enable_status(oldLocation.getLoca_enable_status());
			newLocation.setLoca_approval_status(oldLocation.getLoca_approval_status());
			
			//Set new data to newLocation object
			newLocation.setLoca_name(location.getLoca_name());
			newLocation.setLoca_parent_id(location.getLoca_parent_id());
			newLocation.setLoca_short_name(location.getLoca_short_name());
			
			locationDao.updateLocation(newLocation);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	//Method created : Mugdha Chandratre
	//Method Purpose : get all Location
	@Override
	public List<Location> getAll() {
		try {
			return locationDao.getAll(Location.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method created : Mugdha Chandratre
	//Method Purpose : get Location by location id
	@Override
	public Location getLocationById(int loca_id) {
		try {
			return locationDao.getLocationById(loca_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	//Method created : Mugdha Chandratre
	//Method Purpose : get all location for organization 
	@Override
	public Map<Integer, String> getAllLocationsForOrganization(int orga_id) {
		try {
			List<Object> localistfororga = locationDao.getAllLocationsForOrganization(orga_id);
			Map<Integer, String> localist_for_orga = new HashMap<Integer, String>();

			Iterator<Object> itr = localistfororga.iterator();
			while(itr.hasNext()){
				Object[] obj = (Object[]) itr.next();

				localist_for_orga.put(Integer.parseInt(obj[0].toString()) , obj[1].toString());
			}

			return localist_for_orga;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method created : Mugdha Chandratre
	//Method Purpose : get all location name by id
	@Override
	public String getLocationNameById(int loca_id) {
		try {
			Location loca = locationDao.getLocationById(loca_id);
			return loca.getLoca_name();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//Method Created : Harshad Padole
	//Method purpose : Approve or disapprove location
	@Override
	public int approveDisapprove(int loc_id , int loca_status) {
		try {
			int res = locationDao.approveDisapproveLoc(loc_id, loca_status);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	//Method Created : Harshad Padole
	//Method purpose : Enable or Disable location
	@Override
	public int enableDisable(int loc_id , int loca_status) {
		try {
			int res = locationDao.enableDisableLoc(loc_id, loca_status);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}


	@Override
	public int isLocaNameExist(int loca_id, String loca_name) {
		try {
			return locationDao.isLocaNameExist(loca_id, loca_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int islocaShortNameExist(int loca_id, String loca_short_name) {
		try {
			return locationDao.islocaShortNameExist(loca_id, loca_short_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
