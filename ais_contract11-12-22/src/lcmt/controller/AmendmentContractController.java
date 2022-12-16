package lcmt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lcmt.dao.AmendmentContractDao;
import lcmt.dao.ExecutedContractDao;
import lcmt.domain.AmendmentContract;
import lcmt.domain.AmendmentContractRef;
import lcmt.service.AmendmentContractService;
import lcmt.service.UserService;

/*
 * Author: PRANIT HANAMGHAR
 * Date: 21/3/2018
 * Updated By: 
 * Updated Date: 
 * 
 * */

@Controller
@RequestMapping("/*")

public class AmendmentContractController {

	@Autowired
	UserService userService;
	@Autowired
	AmendmentContractDao amendmentcontractDao;
	@Autowired
	AmendmentContractService amendmentcontractService;
	@Autowired
	ExecutedContractDao executedContractDao;

	// Method Created : Pranit Hanamghar
	// Method purpose : Add Amendment Contract
	@RequestMapping(value = "/addAmendmentContract", method = RequestMethod.GET)
	public ModelAndView addAmendmentContract(int exec_id, HttpSession session) {
		try {

			ModelAndView modelAndView = new ModelAndView("addAmendmentContract", "addamendment",
					new AmendmentContract());

			modelAndView.addObject("user_legal_department", userService.getAll());

			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	// Method Created : Pranit Hanamghar
	// Method purpose : Save Amendment Contract
	@RequestMapping(value = "/saveAmendmentContract", method = RequestMethod.POST)
	public String saveAmendmentContract(AmendmentContract amendmentcontract,
			@RequestParam(name = "additional_parties", required = false) ArrayList<String> additional_parties,
			HttpSession session, 
			@RequestParam(value = "Save", required = false,defaultValue="d_save") String save,
			//@RequestParam(value="actionType",required = false,defaultValue="Pending") String actionType,
			@RequestParam("amend_documents") ArrayList<MultipartFile> amend_documents) {
		try {
			String status = "";
			//String status = actionType;
			if (save.equals("Save")) {
				status = "Pending";
			}
			amendmentcontractService.persist(amendmentcontract, additional_parties, session, status, amend_documents);
			return "redirect:listAmendmentContract?exec_id=" + amendmentcontract.getAmend_exec_contract_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@RequestMapping(value = "/listAmendmentContract", method = RequestMethod.GET)
	public ModelAndView listAmendmentContract(int exec_id, HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("listAmendmentContract", "allAmendmentContract",amendmentcontractService.getJoinedAmendmentContractDetails(exec_id, session));
			modelAndView.addObject("user_legal_department", userService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created :Pranit Hanamghar
	// Method purpose :Edit Amendment Contract
	@RequestMapping(value = "/editAmendmentContract", method = RequestMethod.GET)
	public ModelAndView editAmendmentContract(int amend_contract_id, HttpSession session) {
		try {
			AmendmentContractRef ref = amendmentcontractService
					.getJoinedAmendmentContractDetailsById(amend_contract_id);

			ModelAndView modelAndView = new ModelAndView("editAmendmentContract", "editAmendmentContract", ref);
			modelAndView.addObject("user_legal_department", userService.getAll());
			modelAndView.addObject("exec_id", ref.getAmend_exec_contract_id());
			modelAndView.addObject("amend_doc",
					executedContractDao.getAllExecutedContractDocumentsById(amend_contract_id, 2));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/updateAmendmentContract", method = RequestMethod.POST)
	public String updateContract(AmendmentContractRef amendmentContractRef,
			@RequestParam(name = "additional_parties", required = false) ArrayList<String> additional_parties,
			@RequestParam(value = "Update", required = false, defaultValue = "d_save") String save, HttpSession session,
			@RequestParam("amend_documents") ArrayList<MultipartFile> amend_documents) {

		try {
			String status = "";
			if (save.equals("Update")) {
				status = "Pending";
			}
			amendmentcontractService.updateAmendmentContract(amendmentContractRef, additional_parties, save, session,
					amend_documents);

			return "redirect:listAmendmentContract?exec_id=" + amendmentContractRef.getAmend_exec_contract_id();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// Method Created By: Pranit Hanamghar
	// Method Purpose: delete contract
	@RequestMapping(value = "/deleteAmendmentContract", method = RequestMethod.POST)
	public @ResponseBody String deleteAmendmentContract(@RequestBody String json) {
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
			int amend_contract_id = Integer.parseInt(jsonObj.get("amend_contract_id").toString());
			int deleteCount = amendmentcontractService.deleteAmendmentContract(amend_contract_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
