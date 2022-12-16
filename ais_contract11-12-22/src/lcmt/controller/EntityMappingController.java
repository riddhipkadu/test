package lcmt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lcmt.domain.EntityMapping;
import lcmt.domain.EntityMappingReference;
import lcmt.service.DepartmentService;
import lcmt.service.EntityMappingService;
import lcmt.service.LocationService;

@Controller
@RequestMapping("/*")
public class EntityMappingController {

	@Autowired
	EntityMappingService entityMappingService;
	@Autowired
	DepartmentService departmentService;

	@Autowired
	LocationService locationService;
	
	//Method Created : Mugdha Chandratre
	//Method purpose : Get all entity mapping and load listing view
	@RequestMapping(value = "/listEntitiesMapping", method = RequestMethod.GET)
	public ModelAndView listEntitiesMapping(HttpSession session){
		try {
			if(session.getAttribute("username") != null){
				ModelAndView modelAndView = new ModelAndView("listEntitiesMapping","entitiesMapping",entityMappingService.getJoinedAll());
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

	//Method Created : Mugdha Chandratre
	//Method purpose : load add entity mapping view
	@RequestMapping(value = "/addEntitiesMapping" , method = RequestMethod.GET)
	public ModelAndView addEntitiesMapping(){
		try {
			ModelAndView modelAndView = new ModelAndView("addEntitiesMapping","entitiesMapping",new EntityMappingReference());
			modelAndView.addObject("allOrganizations", entityMappingService.getAllOrganizations());
			modelAndView.addObject("allLocations", entityMappingService.getAllLocations());
			modelAndView.addObject("allDepartments", entityMappingService.getAllDepartments());
			modelAndView.addObject("allDepartmentsJSON", new JSONObject( entityMappingService.getAllDepartments()));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Mugdha Chandratre
	//Method purpose : Save entity mapping
	@RequestMapping(value = "/saveEntitiesMapping" , method = RequestMethod.POST)
	public String saveEntitiesMapping(EntityMappingReference entityMappingReference){
		try {
			entityMappingService.persist(entityMappingReference);
			return "redirect:listEntitiesMapping";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	//Method Created : Mugdha Chandratre
	//Method purpose : Edit entity mapping
	@RequestMapping(value = "/editEntitiesMapping", method = RequestMethod.GET)
	public ModelAndView editEntitiesMapping(int enti_id){
		try {
			List<Integer> selectedDept = new ArrayList<>();
			selectedDept.add(2);
			selectedDept.add(4);
			ModelAndView modelAndView = new ModelAndView("editEntitiesMapping","entitiesMapping",entityMappingService.getEntitiesMappingById(enti_id));
			modelAndView.addObject("allOrganizations", entityMappingService.getAllOrganizations());
			modelAndView.addObject("allLocations", entityMappingService.getAllLocations());
			modelAndView.addObject("allDepartments", entityMappingService.getAllDepartments());
			modelAndView.addObject("selectedDepts", selectedDept);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Method Created : Mugdha Chandratre
	//Method purpose : Update Entity Mapping
	@RequestMapping(value = "/updateEntitiesMapping" , method = RequestMethod.POST)
	public String updateEntitiesMapping(EntityMapping entityMapping ){
		try {
			entityMappingService.updateEntitiesMapping(entityMapping);
			//ModelAndView modelAndView = new ModelAndView("listEntitiesMapping","entitiesMapping",entityMappingService.getJoinedAll());
			return "redirect:listEntitiesMapping";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	//Method Created : Mugdha Chandratre
	//Method purpose : Fetch list of departments which are mapped with organization and location 
	@RequestMapping(value = "/getMappedDepartments" , method = RequestMethod.POST)
	public @ResponseBody String getAllDepartments(@RequestBody String jsonString) throws ParseException{
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			return departmentService.getMappedDepartments(Integer.parseInt(jsonobj.get("enti_orga_id").toString()) , Integer.parseInt(jsonobj.get("enti_loca_id").toString())).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	//Method created : Harshad Padole
	//Method purpose : Approve or disapprove entity
	@RequestMapping(value = "/approveDisapproveEntity" , method = RequestMethod.POST)
	public @ResponseBody String approveDisapproveEntity(@RequestBody String jsonString) throws ParseException{
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int enti_id = Integer.parseInt(jsonobj.get("enti_id").toString());
			int enti_status = Integer.parseInt(jsonobj.get("enti_status").toString());
			int res = entityMappingService.approveDisapproveEntity(enti_id, enti_status);
			return String.valueOf(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(0);

	}

	//Method created : Harshad Padole
	//Method purpose : Enable or disable entity
	@RequestMapping(value = "/enableDisableEntity" , method = RequestMethod.POST)
	public @ResponseBody String enableDisableEntity(@RequestBody String jsonString) throws ParseException{
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int enti_id = Integer.parseInt(jsonobj.get("enti_id").toString());
			int enti_status = Integer.parseInt(jsonobj.get("enti_status").toString());
			int res = entityMappingService.enableDisableEntity(enti_id, enti_status);
			return String.valueOf(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(0);

	}

	//Method created : Harshad Padole
	//Method purpose : Check mapped entity
	@RequestMapping(value = "/isEntityExist" , method = RequestMethod.POST)
	public @ResponseBody String isEntityExist(@RequestBody String jsonString) throws ParseException{
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int enti_id = Integer.parseInt(jsonobj.get("enti_id").toString());
			int orga_id = Integer.parseInt(jsonobj.get("orga_id").toString());
			int loca_id = Integer.parseInt(jsonobj.get("loca_id").toString());
			int dept_id = Integer.parseInt(jsonobj.get("dept_id").toString());
			int res = entityMappingService.isExistEntityMapping(enti_id, orga_id, loca_id, dept_id);
			return String.valueOf(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(0);

	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getAllOrganizationForDropDown" , method = RequestMethod.POST)
	public @ResponseBody String getAllOrganizationForDropDown(){
		try {
			
			Map<Integer, String> organization_list = entityMappingService.getAllOrganizations();
			JSONObject jsonobject = new JSONObject();

			jsonobject.putAll(organization_list);
			return jsonobject.toJSONString();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getAllLocationForDropDown" , method = RequestMethod.POST)
	public @ResponseBody String getAllLocationForDropDown(){
		try {
			
			Map<Integer, String> location_list = entityMappingService.getAllLocations();
			JSONObject jsonobject = new JSONObject();

			jsonobject.putAll(location_list);
			return jsonobject.toJSONString();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	
	//Method Created : Mugdha Chandratre (15-07-2016)
	//Method purpose : Get all mapping user as per entity mapping
	@RequestMapping(value = "/listAllMapping" , method = RequestMethod.GET)
	public ModelAndView listAllMapping(){

		try {
				ModelAndView modelAndView = new ModelAndView("organogramSummary","mappingList",entityMappingService.getUniqueOrganogramMapping());
				return modelAndView;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getMappedDeptList" , method = RequestMethod.POST)
	public @ResponseBody String getMappedDeptList(@RequestBody String jsonString) throws ParseException{
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int orga_id = Integer.parseInt(jsonobj.get("enti_orga_id").toString());
			int loca_id = Integer.parseInt(jsonobj.get("enti_loca_id").toString());
			List<String> result = entityMappingService.getMappedDeptList(orga_id, loca_id);
			//System.out.println("controller list: " + result.size());
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("result",result);
			
			return jsonobject.toJSONString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(0);

	}
	
	
	@RequestMapping(value = "/deleteEntityMapping" , method = RequestMethod.POST)
	public @ResponseBody String deleteEntityMapping(@RequestBody String jsonString) throws ParseException{
		try {
			
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int enti_id = Integer.parseInt(jsonobj.get("enti_id").toString());
			//System.out.println(" Orga id in controller: " + entityMappingService.getEntitiesMappingById(enti_id).getEnti_orga_id());
			int orga_id = entityMappingService.getEntitiesMappingById(enti_id).getEnti_orga_id();
			int loca_id = entityMappingService.getEntitiesMappingById(enti_id).getEnti_loca_id();
			int dept_id = entityMappingService.getEntitiesMappingById(enti_id).getEnti_dept_id();
			//System.out.println("orga : " +orga_id +"loca : " + loca_id + " and dept : " + dept_id);
			int executorCount = entityMappingService.getExecutorMappingCount(orga_id, loca_id, dept_id);
			int evaluatorCount = entityMappingService.getEvaluatorMappingCount(orga_id, loca_id, dept_id);
			int functionHeadCount = entityMappingService.getFunctionHeadMappingCount(orga_id, loca_id, dept_id);
			
			//System.out.println("counts in controller: " + executorCount + ", " + evaluatorCount + ", " + functionHeadCount);
			
			List<Integer> allCounts = new ArrayList<>();
			allCounts.add(executorCount);
			allCounts.add(evaluatorCount);
			allCounts.add(functionHeadCount);
			
			return String.valueOf(allCounts);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	
	@RequestMapping(value = "/deleteActualEntityMapping" , method = RequestMethod.POST)
	public @ResponseBody String deleteActualEntityMapping(@RequestBody String jsonString) throws ParseException{
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int enti_id = Integer.parseInt(jsonobj.get("enti_id").toString());
			//System.out.println("inner enti_id:" + enti_id);
			int deleteCount = entityMappingService.deleteActualEntityMapping(enti_id);
			return String.valueOf(deleteCount);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	    //Method Created By: Mahesh Kharote(23/02/2016)
		//Method Purpose: Get all locations for ajaxCall
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/getAllLocationsForOrganization" , method = RequestMethod.POST)
		public @ResponseBody String getAllLocationsForOrganization(@RequestBody String jsonString) throws ParseException{
			try {
				JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
				Map<Integer, String> listallloca = locationService.getAllLocationsForOrganization(Integer.parseInt(jsonobj.get("tmap_orga_id").toString()));
				JSONObject jsonobject = new JSONObject();

				jsonobject.putAll(listallloca);
				return jsonobject.toJSONString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";

		}
		//Method Created By: Mahesh Kharote(23/02/2016)
		//Method Purpose: Get all departments for organization and location for ajaxCall
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/getAllDepartmentsForOrgAndLoc" , method = RequestMethod.POST)
		public @ResponseBody String getAllDepartmentsForOrgAndLoc(@RequestBody String jsonString) throws ParseException{
			try {
				JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString);
				Map <Integer , String> listAllDeptsForUserMapping = departmentService.getAllDepartmentsForUserMapping(Integer.parseInt(jsonObj.get("tmap_orga_id").toString()), Integer.parseInt(jsonObj.get("tmap_loca_id").toString()));
				JSONObject jsonobject = new JSONObject();
				jsonobject.putAll(listAllDeptsForUserMapping);
				return jsonobject.toJSONString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";

		}
		
		@RequestMapping(value = "/getLocationByOrgaUserId" , method = RequestMethod.POST)
		public @ResponseBody String getLocationByOrgaUserId(@RequestParam("orga_id") Integer orga_id, HttpSession session){
		try {
		    return entityMappingService.getLocationByOrgaUserId(session, orga_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
			
		}
	
		@RequestMapping(value = "/getDepartmentsByLocaUserId" , method = RequestMethod.POST)
		public @ResponseBody String getDepartmentsByLocaUserId(@RequestParam("loca_id") Integer loca_id, @RequestParam("orga_id") Integer orga_id, HttpSession session){
		try {
		    return entityMappingService.getDepartmentsByLocaUserId(session, loca_id, orga_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
			
		}
		
		//Method created : Harshad Padole
		//Method purpose : get state by country id
		@RequestMapping(value = "/getStateByCountryId" , method = RequestMethod.POST)
		public @ResponseBody String getStateByCountryId(@RequestParam("country_id") Integer country_id ) throws ParseException{
			try {
				return entityMappingService.getAllStateByCounId(country_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return String.valueOf(0);

		}

	// Method created : Harshad Padole
	// Method purpose : get city by state id
	@RequestMapping(value = "/getCityByStateId", method = RequestMethod.POST)
	public @ResponseBody String getCityByStateId(@RequestParam("state_id") Integer state_id)throws ParseException {
		try {
			return entityMappingService.getAllCitiesByStateId(state_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(0);

	}
		
	
	
}
