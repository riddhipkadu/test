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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lcmt.domain.ExternalCounsel;
import lcmt.service.AreaOfExpertiseService;
import lcmt.service.EntityMappingService;
import lcmt.service.ExternalCounselService;
import lcmt.service.LawFirmService;

@Controller
@RequestMapping("/*")
public class ExternalCounselController {

	@Autowired
	ExternalCounselService externalCounselService;
	@Autowired
	EntityMappingService entityMappingService;
	@Autowired
	AreaOfExpertiseService areaOfExpertiseService;
	@Autowired
	LawFirmService LawFirmService;

	// Method Created : Harshad Padole
	// Method Purpose : Load view add External Counsel
	@RequestMapping(value = "/addExternalCounsel", method = RequestMethod.GET)
	public ModelAndView addExternalCounsel() {
		try {
			ModelAndView modelAndView = new ModelAndView("addExternalCounsel", "externalcounsel",
					new ExternalCounsel());
			modelAndView.addObject("allAreaOfExpertise", areaOfExpertiseService.getAllAreaOfExpertise());
			modelAndView.addObject("allLawFirm", LawFirmService.getAllLawFirm());
			modelAndView.addObject("allCoutries", entityMappingService.getAllCountries());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Save External Counsel
	@RequestMapping(value = "/saveExternalCounsel", method = RequestMethod.POST)
	public String saveExternal(ExternalCounsel counsel) {
		try {
			externalCounselService.persist(counsel);
			return "redirect:listExternalCounsel";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : list External Counsel
	@RequestMapping(value = "/listExternalCounsel", method = RequestMethod.GET)
	public ModelAndView listExternalCounsel() {
		try {

			ModelAndView modelAndView = new ModelAndView("listExternalCounsel", "listExternalCounsel",
					externalCounselService.getAll());
			modelAndView.addObject("allAreaOfExpertise", areaOfExpertiseService.getAllAreaOfExpertise());
			modelAndView.addObject("allLawFirm", LawFirmService.getAllLawFirm());
			modelAndView.addObject("allCoutries", externalCounselService.getCountryByCounselId());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : edit External Counsel
	@RequestMapping(value = "/editExternalCounsel", method = RequestMethod.GET)
	public ModelAndView editExternalCounsel(int exte_coun_id) {
		try {
			ExternalCounsel counsel = externalCounselService.getExte_CounById(exte_coun_id);
			ModelAndView modelAndView = new ModelAndView("editExternalCounsel", "editExternalCounsel", counsel);
			int coun_id = counsel.getExte_coun_country_id();
			int state_id = counsel.getExte_coun_state_id();
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

	// Method Created : Harshad Padole
	// Method Purpose : Update External Counsel
	@RequestMapping(value = "/updateExternalCounsel", method = RequestMethod.POST)
	public String updateExternalCounsel(ExternalCounsel counsel) {
		try {
			externalCounselService.updateExternalCounsel(counsel);
			return "redirect:listExternalCounsel";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : To check the External Counsel exist or not

	@RequestMapping(value = "/isExternalCounselNameExist", method = RequestMethod.POST)
	public @ResponseBody String isExternalCounselNameExist(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int exte_coun_id = Integer.parseInt(jsonObject.get("exte_coun_id").toString());
			String exte_coun_name = jsonObject.get("exte_coun_name").toString();
			int result = externalCounselService.isExternalCounselNameExist(exte_coun_id, exte_coun_name);
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete External Counsel
	@RequestMapping(value = "/deleteExternalCounsel", method = RequestMethod.POST)
	public @ResponseBody String deleteExternalCounsel(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int exte_coun_id = Integer.parseInt(jsonobj.get("exte_coun_id").toString());
			int deleteCount = externalCounselService.deleteExternalCounsel(exte_coun_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : search External Counsel
	@RequestMapping(value = "/searchExternalCounsel", method = RequestMethod.POST)
	public @ResponseBody String searchExternalCounsel(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int exte_coun_country_id = Integer.parseInt(jsonobj.get("exte_coun_country_id").toString());
			int exte_coun_state_id = Integer.parseInt(jsonobj.get("exte_coun_state_id").toString());
			int exte_coun_city = Integer.parseInt(jsonobj.get("exte_coun_city").toString());
			int exte_coun_law_firm = Integer.parseInt(jsonobj.get("exte_coun_law_firm").toString());
			int exte_coun_name = Integer.parseInt(jsonobj.get("exte_coun_name").toString());
			int exte_coun_area_of_expertise = Integer.parseInt(jsonobj.get("exte_coun_area_of_expertise").toString());
			String result = externalCounselService.searchExternalCounsel(exte_coun_country_id, exte_coun_state_id,
					exte_coun_city, exte_coun_law_firm, exte_coun_name, exte_coun_area_of_expertise);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get counsel as per law firm
	@RequestMapping(value = "/getCounselByLawFirmId", method = RequestMethod.POST)
	public @ResponseBody String getCounselByLawFirmId(@RequestParam("law_firm_id") Integer law_firm_id) {
		try {
			return externalCounselService.getCounselByLawFirmId(law_firm_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get state as per country
	@RequestMapping(value = "/getJoinedCounselStateByCountryId", method = RequestMethod.POST)
	public @ResponseBody String getJoinedCounselStateByCountryId(
			@RequestParam("exte_coun_country_id") Integer exte_coun_country_id) {
		try {
			return externalCounselService.getJoinedStateByCountryId(exte_coun_country_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get city as per state
	@RequestMapping(value = "/getJoinedCounselCityByStateId", method = RequestMethod.POST)
	public @ResponseBody String getJoinedCounselCityByStateId(
			@RequestParam("exte_coun_state_id") Integer exte_coun_state_id) {
		try {
			return externalCounselService.getJoinedCityByStateId(exte_coun_state_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete External Counsel
	@RequestMapping(value = "/checkDependencyExternalCounsel", method = RequestMethod.POST)
	public @ResponseBody String checkDependencyExternalCounsel(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int exte_coun_id = Integer.parseInt(jsonobj.get("exte_coun_id").toString());
			//System.out.println("exte_coun_id:" + exte_coun_id);
			String deleteCount = externalCounselService.checkDependencyExternalCounsel(exte_coun_id);
			return deleteCount;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get all law firm as json
	@RequestMapping(value = "/getAllExternalCounselJson", method = RequestMethod.POST)
	public @ResponseBody String getAllExternalCounselJson(HttpSession session) {
		try {
			return externalCounselService.getAllExternalCounselJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
}
