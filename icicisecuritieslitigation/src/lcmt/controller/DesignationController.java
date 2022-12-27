package lcmt.controller;

import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import lcmt.domain.Designation;
import lcmt.service.DesignationService;

/*
 * Author: Mahesh Kharote
 * Date: 19/02/2016
 * Updated By: Harshad Padole
 * Updated Date: 22/02/2016
 * 
 * */

@Controller
@RequestMapping("/*")
public class DesignationController {
	
	@Autowired
	DesignationService designationService;
    
	//Method Created : Mahesh Kharote
	//Method purpose : Get all designation and load listing view
	@RequestMapping(value = "/listDesignations", method = RequestMethod.GET)
	public ModelAndView listDesignations(){
		try {
			ModelAndView modelAndView = new ModelAndView("listDesignations","allDesignations",designationService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Mahesh Kharote
	//Method purpose : load add designation page and show designation dropdown listing
	@RequestMapping(value = "/addDesignation", method = RequestMethod.GET)
	public ModelAndView addDesignation(){
		try {
			Map<Integer, String> designation_list = designationService.getAllDesignationForDropDown();
			designation_list.put(0, "--Select--");
			ModelAndView modelAndView = new ModelAndView("addDesignation","designation",new Designation());
			
			modelAndView.addObject("designation_list", designation_list);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
	//Method Created : Mahesh Kharote
	//Method purpose : add designation 
	@RequestMapping(value = "/saveDesignation",method = RequestMethod.POST)
	public String saveDesignation(Designation designation){
		try {
			designationService.persist(designation);
			return "redirect:listDesignations";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
	//Method Created : Mahesh Kharote
	//Method purpose : Edit designation page load 
	@RequestMapping(value = "/editDesignation", method = RequestMethod.GET)
	public ModelAndView editDesignation(int desi_id){
		try {
			Designation designation = designationService.getDesignationById(desi_id);
			Map<Integer, String> designation_list = designationService.getAllDesignationForDropDown();
			ModelAndView modelAndView = new ModelAndView("editDesignation","designation",designation);
			modelAndView.addObject("designation_list", designation_list);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Mahesh Kharote
	//Method purpose : Update designation 
	@RequestMapping(value = "/updateDesignation" , method = RequestMethod.POST)
	public String updateDesignation(Designation designation){
		try {
			designationService.updateDesignation(designation);
			return "redirect:listDesignations";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Method Created : Harshad Padole
			//Method Purpose : Approve or disapprove department
			@RequestMapping(value="/approveDisapproveDesi",method = RequestMethod.POST)
			public @ResponseBody String approveDisapproveDesi(@RequestBody String jsonString) throws ParseException{
				try {
					JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
					int desi_id = Integer.parseInt(jsonObject.get("desi_id").toString());
					int desi_status = Integer.parseInt(jsonObject.get("desi_status").toString());
					int res = designationService.approveDisapproveDesi(desi_id, desi_status);
					return String.valueOf(res);
				} catch (org.json.simple.parser.ParseException e) {
					e.printStackTrace();
				}
				
				return String.valueOf(0);
				
			}
			//Method Created : Harshad Padole
			//Method Purpose : Enable or disable department
			@RequestMapping(value="/enableDisableDesi",method = RequestMethod.POST)
			public @ResponseBody String enableDisableDesi(@RequestBody String jsonString) throws ParseException{
				try {
					JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
					int desi_id = Integer.parseInt(jsonObject.get("desi_id").toString());
					int desi_status = Integer.parseInt(jsonObject.get("desi_status").toString());
					int res = designationService.enableDisableDesi(desi_id, desi_status);
					return String.valueOf(res);
				} catch (org.json.simple.parser.ParseException e) {
					e.printStackTrace();
				}
				
				return String.valueOf(0);
			}
	
			//Method Created : Mugdha Chandratre
			//Method Purpose : Verify if email id is exist or not
			@RequestMapping(value="/isDesiNameExist" , method = RequestMethod.POST)
			public @ResponseBody String isDesiNameExist(@RequestBody String jsonString) throws ParseException {
				
				try {
					   JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
				       int desi_id = Integer.parseInt(jsonObject.get("desi_id").toString());
				       String desi_name = jsonObject.get("desi_name").toString();
				       int result = designationService.isDesiNameExist(desi_id, desi_name);
					   return String.valueOf(result);
				} catch (Exception e) {
					e.printStackTrace();
				}
			       
				return null;
			}

}
