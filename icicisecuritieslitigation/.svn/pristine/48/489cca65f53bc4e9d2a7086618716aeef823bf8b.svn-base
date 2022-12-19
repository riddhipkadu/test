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

import lcmt.domain.AreaOfExpertise;
import lcmt.service.AreaOfExpertiseService;
import lcmt.service.UtilitiesService;

@Controller
@RequestMapping("/*")
public class AreaOfExpertiseController {

	@Autowired
	AreaOfExpertiseService areaOfExpertiseService;

	@Autowired
	UtilitiesService utilitieservice;

	// Method Created : Harshad Padole
	// Method purpose: add new external area of expertise
	@RequestMapping(value = "/addAreaOfExpertise", method = RequestMethod.GET)
	public ModelAndView addAreaOfExpertise() {
		try {
			ModelAndView addAreaOfExpertise = new ModelAndView("addAreaOfExpertise", "addAreaOfExpertise",
					new AreaOfExpertise());
			return addAreaOfExpertise;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose: Save Area of Expertise
	@RequestMapping(value = "/saveAreaOfExpertise", method = RequestMethod.POST)
	public String saveAreaOfExpertise(AreaOfExpertise areaOfExpertise) {
		try {
			areaOfExpertiseService.persist(areaOfExpertise);
			return "redirect:listAreaOfExpertise";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: listing and view area of expertise
	@RequestMapping(value = "/listAreaOfExpertise", method = RequestMethod.GET)
	public ModelAndView listAreaOfExpertise(HttpSession session) {

		try {
			ModelAndView modelAndView = new ModelAndView("listAreaOfExpertise", "listAreaOfExpertise",
					areaOfExpertiseService.getAll());
			return modelAndView;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :Edit Area of Expertise view
	@RequestMapping(value = "/editAreaOfExpertise", method = RequestMethod.GET)
	public ModelAndView editAreaOfExpertise(int area_expe_id) {
		try {
			AreaOfExpertise areaOfExpertise = areaOfExpertiseService.getAreaOfExpertiseById(area_expe_id);
			ModelAndView modelAndView = new ModelAndView("editAreaOfExpertise", "editAreaOfExpertise", areaOfExpertise);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Update Area Of Expertise
	@RequestMapping(value = "/updateAreaOfExpertise", method = RequestMethod.POST)
	public String updateAreaOfExpertise(AreaOfExpertise areaOfExpertise) {
		try {
			areaOfExpertiseService.updateAreaOfExpertise(areaOfExpertise);
			return "redirect:listAreaOfExpertise";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: Verify whether Area Expertise Name is exist or not

	@RequestMapping(value = "/isAreaExpeNameExist", method = RequestMethod.POST)
	public @ResponseBody String isAreaExpeNameExist(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int area_expe_id = Integer.parseInt(jsonObject.get("area_expe_id").toString());
			String area_expe_name = jsonObject.get("area_expe_name").toString();
			int result = areaOfExpertiseService.isAreaExpeNameExist(area_expe_id, area_expe_name);
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Area Of Expertise
	@RequestMapping(value = "/deleteAreaOfExpertise", method = RequestMethod.POST)
	public @ResponseBody String deleteAreaOfExpertise(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int area_expe_id = Integer.parseInt(jsonobj.get("area_expe_id").toString());
			int deleteCount = areaOfExpertiseService.deleteAreaOfExpertise(area_expe_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Check Whether Area Of Expertise is used in other module
	// before delete it
	@RequestMapping(value = "/checkAreaOfExpertiseDependancy", method = RequestMethod.POST)
	public @ResponseBody String checkAreaOfExpertiseDependancy(@RequestBody String json) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(json);
			int area_expe_id = Integer.parseInt(jsonobj.get("area_expe_id").toString());
			int deleteCount = utilitieservice.checkDependancy(area_expe_id, "mst_area_of_expertise");
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Delete Area Of Expertise
	@RequestMapping(value = "/getAllAreaOfExpertiseJson", method = RequestMethod.POST)
	public @ResponseBody String getAllAreaOfExpertiseJson(HttpSession session) {
		try {
			return areaOfExpertiseService.getAllAreaOfExpertiseJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
