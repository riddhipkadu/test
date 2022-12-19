package lcmt.controller;

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

import lcmt.domain.Stages;
import lcmt.service.StagesService;

/*
 * Author: Tejashri Zurunge
 * Date: 12/09/2016
 * Updated By:
 * Updated Date:
 * 
 * */
@Controller
@RequestMapping("/*")
public class StagesController {
	@Autowired
	StagesService stagesService;

	// Method Created: Tejashri Zurunge
	// Method Purpose: Add new Stage
	@RequestMapping(value = "/addStages", method = RequestMethod.GET)
	public ModelAndView addStages() {
		try {
			ModelAndView addStages = new ModelAndView("addStages", "add_stages", new Stages());
			return addStages;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: Save Stage
	@RequestMapping(value = "/saveStages", method = RequestMethod.POST)
	public String saveStages(Stages stages, HttpSession session) {
		try {
			stagesService.persist(stages);
			return "redirect:listStages";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: listing and view Stage
	@RequestMapping(value = "/listStages", method = RequestMethod.GET)
	public ModelAndView listStages(HttpSession session) {

		try {
			ModelAndView modelAndView = new ModelAndView("listStages", "listStages", stagesService.getAll());
			return modelAndView;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :Edit Stage view
	@RequestMapping(value = "/editStages", method = RequestMethod.GET)
	public ModelAndView editStages(int stage_id) {
		try {
			ModelAndView modelAndView = new ModelAndView("editStages", "editStages",
					stagesService.getStagesById(stage_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Update Stages
	@RequestMapping(value = "/updateStages", method = RequestMethod.POST)
	public String updateStages(Stages stages) {
		try {
			stagesService.updateStages(stages);
			return "redirect:listStages";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Check whether Stage Name Exist or not
	@RequestMapping(value = "/isStagesNameExist", method = RequestMethod.POST)
	public @ResponseBody String isStagesNameExist(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int stage_id = Integer.parseInt(jsonObject.get("stage_id").toString());
			String stage_name = jsonObject.get("stage_name").toString();
			int result = stagesService.isStagesNameExist(stage_id, stage_name);
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Stages
	@RequestMapping(value = "/deleteStages", method = RequestMethod.POST)
	public @ResponseBody String deleteStages(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int stage_id = Integer.parseInt(jsonObject.get("stage_id").toString());
			int deleteCount = stagesService.deleteStages(stage_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/checkDependancyStages", method = RequestMethod.POST)
	public @ResponseBody String checkDependancyStages(@RequestBody String json)throws ParseException{
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
			int stage_id = Integer.parseInt(jsonObject.get("stage_id").toString());
			int deleteCount = stagesService.checkDependancyStages(stage_id);
			return String.valueOf(deleteCount);
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	// Method Created : Tejashri Zurunge
	// Method Purpose : get all law firm as json
	@RequestMapping(value = "/getAllStagesJson", method = RequestMethod.POST)
	public @ResponseBody String getAllStagesJson(HttpSession session) {
		try {
			return stagesService.getAllStagesJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
}
