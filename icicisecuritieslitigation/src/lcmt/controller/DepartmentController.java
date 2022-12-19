package lcmt.controller;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import lcmt.domain.Department;
import lcmt.service.DepartmentService;

/*
 * Author: Mahesh Kharote
 * Date: 19/02/2016
 * Updated By: Harshad Padole
 * Updated Date: 22/02/2016
 * 
 * */

@Controller
@RequestMapping("/*")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	//Method Created : Mahesh Kharote
	//Method purpose : Get all department and load listing view
	@RequestMapping(value = "/listDepartments" , method = RequestMethod.GET)
	public ModelAndView listDepartments(HttpSession session){
		try {
			if(session.getAttribute("username") != null){
				ModelAndView modelAndView = new ModelAndView("listDepartments","allDepartments",departmentService.getAll());
				
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
	//Method purpose : load add designation view
	@RequestMapping(value = "/addDepartment" , method = RequestMethod.GET)
	public ModelAndView addDepartment(){
		try {
			ModelAndView modelAndView = new ModelAndView("addDepartment","department",new Department());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Method Created : Mahesh Kharote
	//Method purpose : Save Department
	@RequestMapping(value = "/saveDepartment" , method = RequestMethod.POST)
	public String saveDepartment(Department department){
		try {
			departmentService.persist(department);
			return "redirect:listDepartments";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	//Method Created : Harshad Padole
	//Method purpose : Edit Department
	@RequestMapping(value = "/editDepartment" , method = RequestMethod.GET)
	public ModelAndView editDepartment(int dept_id){
		try {
			Department department = departmentService.getDepartmentById(dept_id);
			ModelAndView modelAndView = new ModelAndView("editDepartment","department",department);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	//Method Created : Harshad Padole
	//Method purpose : Update Department
	@RequestMapping(value = "/updateDepartment" , method = RequestMethod.POST)
	public String updateDepartment(Department department){
		try {
			departmentService.updateDepartment(department);
			return "redirect:listDepartments";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	//Method Created : Harshad Padole
		//Method Purpose : Approve or disapprove department
		@RequestMapping(value="/approveDisapproveDept",method = RequestMethod.POST)
		public @ResponseBody String approveDisapproveDept(@RequestBody String jsonString) throws ParseException{
			try {
				JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
				int dept_id = Integer.parseInt(jsonObject.get("dept_id").toString());
				int dept_status = Integer.parseInt(jsonObject.get("dept_status").toString());
				int res = departmentService.approveDisapproveDept(dept_id, dept_status);
				return String.valueOf(res);
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
			
			return String.valueOf(0);
			
		}
		//Method Created : Harshad Padole
		//Method Purpose : Enable or disable department
		@RequestMapping(value="/enableDisableDept",method = RequestMethod.POST)
		public @ResponseBody String enableDisableDept(@RequestBody String jsonString) throws ParseException{
			try {
				JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
				int dept_id = Integer.parseInt(jsonObject.get("dept_id").toString());
				int dept_status = Integer.parseInt(jsonObject.get("dept_status").toString());
				int res = departmentService.enableDisableDept(dept_id, dept_status);
				return String.valueOf(res);
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
			
			return String.valueOf(0);
		}
	

		//Method Created : Mugdha Chandratre
		//Method Purpose : Verify if email id is exist or not
		@RequestMapping(value="/isDeptNameExist" , method = RequestMethod.POST)
		public @ResponseBody String isDeptNameExist(@RequestBody String jsonString) throws ParseException {
			
			try {
				   JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			       int dept_id = Integer.parseInt(jsonObject.get("dept_id").toString());
			       String dept_name = jsonObject.get("dept_name").toString();
			       int result = departmentService.isDeptNameExist(dept_id, dept_name);
				   return String.valueOf(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		       
			return null;
		}
		
		@RequestMapping(value="/isDeptShortNameExist" , method = RequestMethod.POST)
		public @ResponseBody String isDeptShortNameExist(@RequestBody String jsonString) throws ParseException {
			try {
				   JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			       int dept_id = Integer.parseInt(jsonObject.get("dept_id").toString());
			       String dept_short_name = jsonObject.get("dept_short_name").toString();
			       int result = departmentService.isDeptShortNameExist(dept_id, dept_short_name);
				   return String.valueOf(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
}
