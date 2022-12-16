package lcmt.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lcmt.domain.StandardFormContractType;
import lcmt.domain.StandardFormContracts;
import lcmt.service.EntityMappingService;
import lcmt.service.StandardFormContractService;

@Controller
@RequestMapping("/*")
public class StandardFormContractsController {

	@Autowired
	StandardFormContractService standardFormContractService;
	
	@Autowired
	EntityMappingService entityMappingService;

	// Method Created : Harshad Padole
	// Method Purpose : Listing standard form contracts
	@RequestMapping(value = "/listStandardFormContracts", method = RequestMethod.GET)
	public ModelAndView listStandardContracts(HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("listStandardFormContracts");
			modelAndView.addObject("listStandardFormContracts", standardFormContractService.getAllStdFormContracts(session));
			modelAndView.addObject("allSfcType", standardFormContractService.getAll());
			//modelAndView.addObject("sfcoDocuments", standardFormContractService.getDocumentBySfcId(sfco_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Add standard form contracts page
	@RequestMapping(value = "/addStandardFormContracts", method = RequestMethod.GET)
	public ModelAndView addStandardContracts(HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("addStandardFormContracts", "addStandardFormContracts",
					new StandardFormContracts());
			modelAndView.addObject("allSfcType", standardFormContractService.getAll());
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose: Save Standard Form Contract
	@RequestMapping(value = "/saveStandardFormContracts", method = RequestMethod.POST)
	public String saveStandardFormContracts(StandardFormContracts standardFormContracts,
			@RequestParam("sfco_doc") ArrayList<MultipartFile> sfco_doc) {

		try {
			standardFormContractService.persist(standardFormContracts, sfco_doc);
			return "redirect:listStandardFormContracts";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :Edit Standard Form Contract view
	@RequestMapping(value = "/editStandardFormContracts", method = RequestMethod.GET)
	public ModelAndView editStandardFormContracts(int sfco_id, HttpSession session) {
		try {
			StandardFormContracts contracts = standardFormContractService.getStandardFormContractsById(sfco_id);
			ModelAndView modelAndView = new ModelAndView("editStandardFormContracts", "editStandardFormContracts",contracts);
			modelAndView.addObject("allSfcType", standardFormContractService.getAll());
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			int orga_id = contracts.getSfco_entity_id();
			int loca_id = contracts.getSfco_unit_id();
			modelAndView.addObject("location",entityMappingService.getLocationByUserId(session,orga_id));
			modelAndView.addObject("department",entityMappingService.getDeptByUserId(session, loca_id, orga_id));
			modelAndView.addObject("cont_type", contracts.getSfco_contract_type());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :Update Standard Form Contract view
	@RequestMapping(value = "/updateStandardFormContracts", method = RequestMethod.POST)
	public String updateStandardFormContracts(StandardFormContracts standardFormContracts,
			@RequestParam("sfco_doc") ArrayList<MultipartFile> sfco_doc) {
		try {
			standardFormContractService.updateStandardFormContracts(standardFormContracts, sfco_doc);
			return "redirect:listStandardFormContracts";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Standard Form Contract
	@RequestMapping(value = "/deleteStandardFormContracts", method = RequestMethod.POST)
	public @ResponseBody String deleteStandardFormContracts(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int sfco_id = Integer.parseInt(jsonobj.get("sfco_id").toString());
			int deleteCount = standardFormContractService.deleteStandardFormContracts(sfco_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete document by id
	@RequestMapping(value = "/deleteSfcoDocument", method = RequestMethod.POST)
	public @ResponseBody String deleteSfcoDocument(@RequestBody String jsonString) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			standardFormContractService
					.deleteSfcoDocumentById(Integer.parseInt(jsonObject.get("sfco_doc_id").toString()));
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed";
		}
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : Download document by id
	@RequestMapping(value= "/downloadSfcoDocument" , method = RequestMethod.GET)
	public void downloadSfcoDocument(int sfco_doc_id, HttpServletResponse response){
		try {
			standardFormContractService.downloadSfcoDocument(sfco_doc_id, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// <--------------------------------- code for Standard form contract type starts here ------------------------------------------->
	// Method Created : Tejashri Zurunge
	// Method purpose: add new Standard Form Contract Type
	@RequestMapping(value = "/addStandardFormContractType", method = RequestMethod.GET)
	public ModelAndView addStandardFormContractType() {
		try {
			ModelAndView addSfcoType = new ModelAndView("addStandardFormContractType", "addStandardFormContractType",
					new StandardFormContractType());

			return addSfcoType;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose: Save Standard Form Contract Type
	@RequestMapping(value = "/saveStandardFormContractType", method = RequestMethod.POST)
	public String saveStandardFormContractType(StandardFormContractType standardFormContractType) {

		try {
			standardFormContractService.saveSfcoType(standardFormContractType);
			return "redirect:listStandardFormContractType";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: listing and view Standard Form Contract Type
	@RequestMapping(value = "/listStandardFormContractType", method = RequestMethod.GET)
	public ModelAndView listStandardFormContractType(HttpSession session) {

		try {
			ModelAndView modelAndView = new ModelAndView("listStandardFormContractType", "listStandardFormContractType",
					standardFormContractService.getAll());
			return modelAndView;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :Edit Standard Form Contract Type view
	@RequestMapping(value = "/editStandardFormContractType", method = RequestMethod.GET)
	public ModelAndView editStandardFormContractType(int sfco_type_id) {
		try {
			StandardFormContractType standardFormContractType = standardFormContractService
					.getStandardFormContractTypeById(sfco_type_id);
			ModelAndView modelAndView = new ModelAndView("editStandardFormContractType", "editStandardFormContractType",
					standardFormContractType);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Update Standard Form Contract Type
	@RequestMapping(value = "/updateStandardFormContractType", method = RequestMethod.POST)
	public String updateStandardFormContractType(StandardFormContractType standardFormContractType) {

		try {
			standardFormContractService.updateStandardFormContractType(standardFormContractType);
			return "redirect:listStandardFormContractType";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: Verify whether Standard Form Contract Type is exist or
	// not

	@RequestMapping(value = "/isStandardFormContractTypeExist", method = RequestMethod.POST)
	public @ResponseBody String isStandardFormContractTypeExist(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int sfco_type_id = Integer.parseInt(jsonObject.get("sfco_type_id").toString());
			String sfco_type_name = jsonObject.get("sfco_type_name").toString();
			int result = standardFormContractService.isStandardFormContractTypeExist(sfco_type_id, sfco_type_name);
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Standard Form Contract Type
	@RequestMapping(value = "/deleteStandardFormContractType", method = RequestMethod.POST)
	public @ResponseBody String deleteStandardFormContractType(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int sfco_type_id = Integer.parseInt(jsonobj.get("sfco_type_id").toString());
			int deleteCount = standardFormContractService.deleteStandardFormContractType(sfco_type_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Standard Form Contract Type
	@RequestMapping(value = "/checkDependancySFCType", method = RequestMethod.POST)
	public @ResponseBody String checkDependancySFCType(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int sfco_type_id = Integer.parseInt(jsonobj.get("sfco_type_id").toString());
			int deleteCount = standardFormContractService.checkDependancySFCType(sfco_type_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Harshad Padole
	// Method Purpose : get all SFC Type in json format
	@RequestMapping(value = "/getAllSFCType", method = RequestMethod.POST)
	public @ResponseBody String getAllSFCType() {
		try {
			return standardFormContractService.getAllSFCTypejson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
