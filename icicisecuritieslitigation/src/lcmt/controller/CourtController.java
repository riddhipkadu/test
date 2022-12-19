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

import lcmt.domain.Court;
import lcmt.service.CourtService;

@Controller
@RequestMapping(value="/*")
public class CourtController {
	@Autowired
	CourtService courtService;
	
	@Autowired
	HttpSession httpSession;

	@RequestMapping(value="/addCourt", method=RequestMethod.GET)
	public ModelAndView addCourt(){
		try {
			ModelAndView modelAndView = new ModelAndView("addCourt","addCourt", new Court());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/saveCourt", method = RequestMethod.POST)
	public String saveCourt(Court court, HttpSession httpSession){
		try {
			courtService.persist(court);
			return "redirect:listCourt";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="listCourt" , method = RequestMethod.GET)
	public  ModelAndView listCourt(HttpSession httpSession){
		try {
			ModelAndView modelAndView = new ModelAndView("listCourt", "listCourt", courtService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="editCourt", method = RequestMethod.GET)
	public ModelAndView editCourt(int court_id){
		try {
			ModelAndView modelAndView = new ModelAndView("editCourt", "editCourt", courtService.getCourtById(court_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="updateCourt", method = RequestMethod.POST)
	public String updateCourt(Court court){
		try {
			courtService.updateCourt(court);
			return "redirect:listCourt";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="isCourtExistOrNot", method = RequestMethod.POST)
	public @ResponseBody String isCourtExistOrNot(@RequestBody String jsonString){
		try {
			JSONObject json = (JSONObject) new JSONParser().parse(jsonString);
			int court_id = Integer.parseInt(json.get("court_id").toString());
			String court_name = json.get("court_name").toString();
			int result = courtService.isCourtExistOrNot(court_id, court_name);
			return String.valueOf(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;	
	}
	
	@RequestMapping(value= "deleteCourt", method = RequestMethod.POST)
	public @ResponseBody String deleteCourt(@RequestBody String jsonString){
		try {
			JSONObject json = (JSONObject) new JSONParser().parse(jsonString);
			int court_id = Integer.parseInt(json.get("court_id").toString());
			int result = courtService.deleteCourt(court_id);
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value ="checkDependancyCourt", method = RequestMethod.POST)
	public @ResponseBody String checkDependancyCourt(@RequestBody String jsonString){
		try {
			JSONObject json = (JSONObject) new JSONParser().parse(jsonString);
			int court_id = Integer.parseInt(json.get("court_id").toString());
			int result = courtService.checkDependancyCourt(court_id);
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Method Created : Harshad Padole
	//Method purpose : Get all court type in json format
	@RequestMapping(value ="/getAllCourtJson", method = RequestMethod.POST)
	public @ResponseBody String getAllCourtJson(){
		try {
			 return courtService.getAllCourtJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
