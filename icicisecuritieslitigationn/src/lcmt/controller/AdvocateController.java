package lcmt.controller;

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

import lcmt.domain.Advocate;
import lcmt.service.AdvocateService;
import lcmt.service.AreaOfExpertiseService;
import lcmt.service.EntityMappingService;
import lcmt.service.LawFirmService;

@Controller
@RequestMapping(value = "/*")

public class AdvocateController {
	@Autowired
	AreaOfExpertiseService areaOfExpertiseService;

	@Autowired
	LawFirmService LawFirmService;

	@Autowired
	AdvocateService advocateService;
	@Autowired
	EntityMappingService entityMappingService;

	// Method Created :Pranit
	// Method Purpose : Add Advocate
	@RequestMapping(value = "/addAdvocate", method = RequestMethod.GET)
	public ModelAndView addAdvocate() {
		try {
			ModelAndView modelAndView = new ModelAndView("addAdvocate", "addAdvocate", new Advocate());
			modelAndView.addObject("allAreaOfExpertise", areaOfExpertiseService.getAllAreaOfExpertise());
			modelAndView.addObject("allLawFirm", LawFirmService.getAllLawFirm());
			modelAndView.addObject("allCoutries", entityMappingService.getAllCountries());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Save Advocate
	@RequestMapping(value = "/saveAdvocate", method = RequestMethod.POST)
	public String saveAdvocate(Advocate advocate) {
		try {
			advocateService.persist(advocate);
			return "redirect:listAdvocate";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : List Advocate
	@RequestMapping(value = "/listAdvocate", method = RequestMethod.GET)
	public ModelAndView listAdvocate() {
		try {
			ModelAndView modelAndView = new ModelAndView("listAdvocate", "listAdvocate", advocateService.getAll());
			modelAndView.addObject("allAreaOfExpertise", areaOfExpertiseService.getAllAreaOfExpertise());
			modelAndView.addObject("allLawFirm", LawFirmService.getAllLawFirm());
			modelAndView.addObject("allJoinCountries", advocateService.getJoinedCountries());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Edit Advocate
	@RequestMapping(value = "/editAdvocate", method = RequestMethod.GET)
	public ModelAndView editAdvocate(int advo_id) {
		try {
			Advocate advocate = advocateService.getAdvocateById(advo_id);
			int coun_id = advocate.getAdvo_country_id();
			int state_id = advocate.getAdvo_state_id();
			ModelAndView modelAndView = new ModelAndView("editAdvocate", "editAdvocate", advocate);

			modelAndView.addObject("allAreaOfExpertise", areaOfExpertiseService.getAllAreaOfExpertise());
			modelAndView.addObject("allLawFirm", LawFirmService.getAllLawFirm());
			modelAndView.addObject("allCoutries", entityMappingService.getAllCountries());
			modelAndView.addObject("allState", entityMappingService.getStatesByCountryId(coun_id));
			modelAndView.addObject("allCities", entityMappingService.getCityByStateId(state_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : update Advocate
	@RequestMapping(value = "/updateAdvocate", method = RequestMethod.POST)
	public String updateAdvocate(Advocate advocate) {
		try {
			advocateService.updateAdvocate(advocate);
			return "redirect:listAdvocate";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : delete Advocate
	@RequestMapping(value = "/deleteAdvocate", method = RequestMethod.POST)
	public @ResponseBody String deleteAdvocate(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int advo_id = Integer.parseInt(jsonobj.get("advo_id").toString());
			int deleteCount = advocateService.deleteAdvocate(advo_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : search Advocate
	@RequestMapping(value = "/searchAdvocate", method = RequestMethod.POST)
	public @ResponseBody String searchAdvocate(@RequestBody String jsonString) {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int advo_law_firm = Integer.parseInt(jsonobj.get("advo_law_firm").toString());
			int advo_city = Integer.parseInt(jsonobj.get("advo_city").toString());
			int advo_area_of_expertise = Integer.parseInt(jsonobj.get("advo_area_of_expertise").toString());
			int advo_country = Integer.parseInt(jsonobj.get("advo_country").toString());
			int advo_state = Integer.parseInt(jsonobj.get("advo_state").toString());
			int advo_id = Integer.parseInt(jsonobj.get("advo_id").toString());
			String result = advocateService.searchAdvocate(advo_law_firm, advo_city, advo_area_of_expertise,
					advo_country, advo_state, advo_id);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get advocate as per law firm
	@RequestMapping(value = "/getAdvocateByLawFirmId", method = RequestMethod.POST)
	public @ResponseBody String getAdvocateByLawFirmId(@RequestParam("law_firm_id") Integer law_firm_id) {
		try {
			return advocateService.getAdvocateByLawFirmId(law_firm_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created : Harshad Padole
	// Method Purpose : Get state by country id
	@RequestMapping(value = "/getJoinedStateByCountryId", method = RequestMethod.POST)
	public @ResponseBody String getJoinedStateByCountryId(@RequestParam("country_id") Integer country_id) {
		try {
			return advocateService.getJoinedStateByCountryId(country_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created : Harshad Padole
	// Method Purpose : Get city by state id
	@RequestMapping(value = "/getJoinedCityByStateId", method = RequestMethod.POST)
	public @ResponseBody String getJoinedStateByStateId(@RequestParam("state_id") Integer state_id) {
		try {
			return advocateService.getJoinedCityByStateId(state_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get advocate name
	@RequestMapping(value = "/getAllAdvocateNameJson", method = RequestMethod.POST)
	public @ResponseBody String getAllAdvocateNameJson() {
		try {
			return advocateService.getAllAdvocateJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : To check the Advocate Name exist or not
	@RequestMapping(value = "/isAdvocateNameExist", method = RequestMethod.POST)
	public @ResponseBody String isAdvocateNameExist(@RequestBody String jsonString) throws ParseException {
		try {
			int result = advocateService.isAdvocateNameExist(jsonString);
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : check advocate dependancy
	@RequestMapping(value = "/checkDependancyAdvocate", method = RequestMethod.POST)
	public @ResponseBody String checkDependancyAdvocate(@RequestBody String json) throws ParseException {
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
			int advo_id = Integer.parseInt(jsonObj.get("advo_id").toString());
			String deleteCount = advocateService.checkDependancyAdvocate(advo_id);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
