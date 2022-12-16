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

import lcmt.domain.ContractType;
import lcmt.service.ContractTypeService;

@Controller
@RequestMapping("/*")
public class ContractTypeController {
	@Autowired
	ContractTypeService contractTypeService;

	// Method Created : Tejashri Zurunge
	// Method purpose: add new Contract Type
	@RequestMapping(value = "/addContractType", method = RequestMethod.GET)
	public ModelAndView addContractType() {
		try {
			ModelAndView addContractType = new ModelAndView("addContractType", "addContractType", new ContractType());
			return addContractType;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: Save Contract Type
	@RequestMapping(value = "/saveContractType", method = RequestMethod.POST)
	public String saveContractType(ContractType contractType, HttpSession session) {
		try {
			contractTypeService.persist(contractType);
			return "redirect:listContractType";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: listing and view Contract Type
	@RequestMapping(value = "/listContractType", method = RequestMethod.GET)
	public ModelAndView listContractType(HttpSession session) {

		try {
			ModelAndView modelAndView = new ModelAndView("listContractType", "listContractType",contractTypeService.getAll());
			return modelAndView;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :Edit Contract Type
	@RequestMapping(value = "/editContractType", method = RequestMethod.GET)
	public ModelAndView editContractType(int cont_type_id) {
		try {
			ModelAndView modelAndView = new ModelAndView("editContractType", "editContractType",
					contractTypeService.getContractTypeById(cont_type_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Update Contract Type
	@RequestMapping(value = "/updateContractType", method = RequestMethod.POST)
	public String updateContractType(ContractType contractType) {
		try {
			contractTypeService.updateContractType(contractType);
			return "redirect:listContractType";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Check whether Contract Type Name Exist or not
	@RequestMapping(value = "/isContractTypeNameExist", method = RequestMethod.POST)
	public @ResponseBody String isContractTypeNameExist(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int cont_type_id = Integer.parseInt(jsonObject.get("cont_type_id").toString());
			String cont_type_name = jsonObject.get("cont_type_name").toString();
			int result = contractTypeService.isContractTypeNameExist(cont_type_id, cont_type_name);
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Contract Type
	@RequestMapping(value = "/deleteContractType", method = RequestMethod.POST)
	public @ResponseBody String deleteContractType(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int cont_type_id = Integer.parseInt(jsonObject.get("cont_type_id").toString());
			int deleteCount = contractTypeService.deleteContractType(cont_type_id);
			return String.valueOf(deleteCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Contract Type
	@RequestMapping(value = "/checkDependancyContractType", method = RequestMethod.POST)
	public @ResponseBody String checkDependancyContractType(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int cont_type_id = Integer.parseInt(jsonObject.get("cont_type_id").toString());
			String deleteCount = contractTypeService.checkDependancyContractType(cont_type_id);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get all law firm as json
	@RequestMapping(value = "/getAllContractTypeJson", method = RequestMethod.POST)
	public @ResponseBody String getAllContractTypeJson(HttpSession session) {
		try {
			return contractTypeService.getAllContractTypeJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
