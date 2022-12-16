package lcmt.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lcmt.domain.Location;
import lcmt.service.LocationService;

/*
 * Author: Mahesh Kharote
 * Date: 19/02/2016
 * Updated By: Harshad Padole
 * Updated Date: 22/02/2016
 * 
 * */

@Controller
@RequestMapping("/*")
public class LocationController {
	@Autowired
	LocationService locationService;

	//Method Created : Mahesh Kharote
	//Method purpose : Get all Location and load listing view
	@RequestMapping(value = "/listLocations" , method = RequestMethod.GET)
	public ModelAndView listLocations(HttpSession session){

		try {
			if(session.getAttribute("username") != null){
				ModelAndView modelAndView = new ModelAndView("listLocations","allLocations",locationService.getAll());
				return modelAndView;
			}
			else{

				ModelAndView modelAndView = new ModelAndView("userLogin");
				return modelAndView;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Mahesh Kharote
	//Method purpose : load add Location view
	@RequestMapping(value = "/addLocation" , method = RequestMethod.GET)
	public ModelAndView addLocation(){
		try {
			ModelAndView modelAndView = new ModelAndView("addLocation","location",new Location());

			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Mahesh Kharote
	//Method purpose : Save Location 
	@RequestMapping(value = "/saveLocation" , method = RequestMethod.POST)
	public String saveLocation(Location location){
		try {
			@SuppressWarnings("deprecation")
			Date dateobj = new Date(2016,01,05);

			location.setLoca_added_by(1);
			location.setLoca_created_at(dateobj);

			locationService.persist(location);
			return "redirect:listLocations";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Mahesh Kharote
	//Method purpose :Edit Location view
	@RequestMapping(value = "/editLocation" , method = RequestMethod.GET)
	public ModelAndView editLocation(int loca_id){
		try {
			Location location = locationService.getLocationById(loca_id);
			ModelAndView modelAndView = new ModelAndView("editLocation","location",location);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Mahesh Kharote
	//Method purpose : Update Location 
	@RequestMapping(value = "/updateLocation" , method = RequestMethod.POST)
	public String updateLocation(Location location){

		try {
			locationService.updateLocation(location);
			return "redirect:listLocations";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//Method Created : Harshad Padole
	//Method purpose : Approve or disapprove location
	@RequestMapping(value = "/approveDisapproveLoc" , method = RequestMethod.POST)
	public @ResponseBody String approveDisapprove(@RequestBody String jsonString) throws ParseException{
		JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString);
		int loc_id      = Integer.parseInt(jsonObj.get("loca_id").toString());
		int loca_status = Integer.parseInt(jsonObj.get("loca_status").toString());
		try {
			int res = locationService.approveDisapprove(loc_id,loca_status);
			return String.valueOf(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(0);
	}

	//Method Created : Harshad Padole
	//Method purpose : Enable or Disable location
	@RequestMapping(value = "/enableDisableLoc" , method = RequestMethod.POST)
	public @ResponseBody String enableDisable(@RequestBody String jsonString) throws ParseException{
		JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString);
		int loc_id      = Integer.parseInt(jsonObj.get("loca_id").toString());
		int loca_status = Integer.parseInt(jsonObj.get("loca_status").toString());
		try {
			int res = locationService.enableDisable(loc_id, loca_status);
			return String.valueOf(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(0);
	}
	
	//Method Created : Mugdha Chandratre
	//Method Purpose : Verify if location name is exist or not
	@RequestMapping(value="/isLocaNameExist" , method = RequestMethod.POST)
	public @ResponseBody String isLocaNameExist(@RequestBody String jsonString) throws ParseException {
		try {
			   JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
		       int loca_id = Integer.parseInt(jsonObject.get("loca_id").toString());
		       String loca_name = jsonObject.get("loca_name").toString();
		       int result = locationService.isLocaNameExist(loca_id, loca_name);
			   return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	       
		return null;
	}
	//Method created : Tejahri Zurunge
	//Method purpose : to check whether location short name is existed or not
	@RequestMapping(value="/islocaShortNameExist" , method = RequestMethod.POST)
	public @ResponseBody String islocaShortNameExist(@RequestBody String jsonString) throws ParseException {
		try {
			   JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
		       int loca_id = Integer.parseInt(jsonObject.get("loca_id").toString());
		       String loca_short_name = jsonObject.get("loca_short_name").toString();
		       int result = locationService.islocaShortNameExist(loca_id, loca_short_name);
			   return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	       
		return null;
	}
	
}
