package lcmt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
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

import lcmt.domain.Contract;
import lcmt.domain.ContractHistory;
import lcmt.domain.ContractHistoryReference;
import lcmt.domain.ContractReference;
import lcmt.domain.ContractRequest;
import lcmt.service.ContractService;
import lcmt.service.ContractTypeService;
import lcmt.service.EntityMappingService;
import lcmt.service.RequestByPOCService;
import lcmt.service.UserService;

/*
 * Author: Mahesh Kharote
 * Date: 27/09/2016
 * Updated By:
 * Updated Date:
 * 
 * */

@Controller
@RequestMapping("/*")
public class PreExecutionSummaryController {

	@Autowired
	UserService userService;

	@Autowired
	EntityMappingService entityMappingService;

	@Autowired
	ContractTypeService contractTypeService;

	@Autowired
	ContractService contractForExecutionService;

	@Autowired
	RequestByPOCService requestByPOCService;
	
	// Method Created : Mahesh Kharote
	// Method Purpose : Show List Contract page
	@RequestMapping(value = "/listPreExecutionSummary", method = RequestMethod.GET)
	public ModelAndView listPreExecutionSummary(HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("listPreExecutionSummary", "allContractDetails",
					contractForExecutionService.getJoinedContractDetails(session));
			
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("contract_types",contractForExecutionService.getContractTypesForPreExeContracts(session));
			//modelAndView.addObject("user_legal_department",
					//userService.getUsersByOrganizationLocationDepartment(orga_id, loca_id, dept_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Mahesh Kharote
	// Method Purpose : Show Add Contract For Execution page
	@RequestMapping(value = "/addContractForExecution", method = RequestMethod.GET)
	public ModelAndView addContractForExecution(HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("addContractForExecution", "newContract", new Contract());
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("allContractType", contractTypeService.getAllContractType());
			modelAndView.addObject("user_legal_department", userService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Mahesh Kharote
	// Method Purpose : Show Add Contract For Execution page
	// Method Updated : Harshad Padole
	@RequestMapping(value = "/saveContractForExecution", method = RequestMethod.POST)
	public String saveContractForExecution(Contract contract,
			@RequestParam("term_sheet_document") ArrayList<MultipartFile> term_doc,
			@RequestParam("contract_document") ArrayList<MultipartFile> contract_doc, HttpSession session,
			@RequestParam("additional_parties") ArrayList<String> additional_parties,
			@RequestParam(value="Save",required=false,defaultValue="d_save") String save,
			@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft,
			@RequestParam(value="cont_type_of_contract",required = false,defaultValue="0") ArrayList<String> list_type_of_contract) {
		try {
			String status = "";
			if(save.equals("Save")){
				status = "Pending";	
			}
			if(draft.equals("Draft")){
				status = "Draft";	
			}
			//System.out.println("id: "+id);
			contractForExecutionService.persist(contract, term_doc, contract_doc, session, additional_parties,list_type_of_contract,status);
			return "redirect:listPreExecutionSummary";

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: get InitBinder
	@RequestMapping(value = "/updateTab", method = RequestMethod.GET)
	public ModelAndView updateTab(int cont_id, HttpSession session) {
		try {
			ContractReference ref = contractForExecutionService.getJoinedContractDetailsById(cont_id);
			ModelAndView modelAndView = new ModelAndView("updateTab", "contractDetails",ref);
			modelAndView.addObject("contractHistory", contractForExecutionService.getContractHistoryById(cont_id));
			modelAndView.addObject("contractDocuments",
					contractForExecutionService.getAllContractDocumentsByContractId(cont_id));
			modelAndView.addObject("allContractType", contractTypeService.getAllContractType());
			modelAndView.addObject("allStatus",requestByPOCService.getAllContractStatusNegotiationByContractId(cont_id));
			modelAndView.addObject("role_id", Integer.parseInt(session.getAttribute("sess_user_role").toString()));
			modelAndView.addObject("id", cont_id);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Add contract history
	@RequestMapping(value = "/addContractHistory", method = RequestMethod.GET)
	public ModelAndView addContractHistory(int cont_id, HttpSession session) {
		try {
			ContractHistory contractHistory = new ContractHistory();
			contractHistory.setChst_contract_id(cont_id);
			
			ContractReference cont = contractForExecutionService.getJoinedContractDetailsById(cont_id);
			int orga_id = cont.getCont_orga_id();
			int loca_id = cont.getCont_loca_id();
			int dept_id = cont.getCont_dept_id();
			contractHistory.setChst_assigned_to(Integer.parseInt(cont.getCont_responsible_user_id()));
			if(cont.getCont_req_id() != 0){
			ContractRequest req = requestByPOCService.getAllContractrequest(cont.getCont_req_id(), session);
			contractHistory.setChst_poc_user_id(req.getReq_contract_added_by());
			}
			ModelAndView modelAndView = new ModelAndView("addContractHistory", "contractHistory", contractHistory);
			
			modelAndView.addObject("allPOCUser",userService.getAllPOCUsers());	
			modelAndView.addObject("allUser",userService.getUsersByOrganizationLocationDepartment(orga_id, loca_id, dept_id));
			modelAndView.addObject("allContractType", contractTypeService.getAllContractType());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose:  save contract history
	@RequestMapping(value = "/saveContractHistory", method = RequestMethod.POST)
	public String saveContractHistory(ContractHistory contractHistory,@RequestParam("contract_history_document") ArrayList<MultipartFile> hst_doc,
			@RequestParam(name="chst_contract_type",required=false) ArrayList<String> chst_contract_type,HttpSession session, 
			@RequestParam(name="Yes",required=false,defaultValue="No") String adminStatus) {
		try {
			//System.out.println("adminStatus :"+adminStatus);
			contractForExecutionService.persistContractHistory(contractHistory, hst_doc, chst_contract_type,session, adminStatus);
			return "redirect:updateTab?cont_id=" + contractHistory.getChst_contract_id();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: get InitBinder
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	// Method Created : Harshad Padole
	// Method Purpose : Search Pre execution contracts
	@RequestMapping(value = "/searchContractForExecution", method = RequestMethod.POST)
	public @ResponseBody String searchContractForExecution(@RequestBody String json, HttpSession session) {
		try {
			return contractForExecutionService.searchContract(json, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created By: Harshad Padole
	// Method Purpose: get contract details to edit
	@RequestMapping(value = "/editContract", method = RequestMethod.GET)
	public ModelAndView editContract(int cont_id, HttpSession session) {
		try {
			ContractReference cont = contractForExecutionService.getJoinedContractDetailsById(cont_id);
			ModelAndView modelAndView = new ModelAndView("editContractForExecution", "editContract", cont);

			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("allContractType", contractTypeService.getAllContractType());
			int orga_id = cont.getCont_orga_id();
			int loca_id = cont.getCont_loca_id();
			int dept_id = cont.getCont_dept_id();
			modelAndView.addObject("user_legal_department",
					userService.getUsersByOrganizationLocationDepartment(orga_id, loca_id, dept_id));
			modelAndView.addObject("allUser", userService.getAll());
			modelAndView.addObject("allLocations", entityMappingService.getLocationByUserId(session, orga_id));
			modelAndView.addObject("allDepartments", entityMappingService.getDeptByUserId(session, loca_id, orga_id));
			modelAndView.addObject("status",cont.getCont_status());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created By: Harshad Padole
	// Method Purpose: Update Contract details
	@RequestMapping(value = "/updateContract", method = RequestMethod.POST)
	public String updateContract(ContractReference contract,
			@RequestParam("term_sheet_document") ArrayList<MultipartFile> term_doc,
			@RequestParam("contract_document") ArrayList<MultipartFile> contract_doc,
			@RequestParam(name="additional_parties",required=false) ArrayList<String> additional_parties,
			@RequestParam(value="Update",required=false,defaultValue="d_save") String save,@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft,
			@RequestParam(value="cont_type_of_contract",required=false) ArrayList<String> list_type_of_contract, HttpSession session) {
		try {
			String status = "";
			if(save.equals("Update")){
				status = "Pending";	
			}
			if(draft.equals("Draft")){
				status = "Draft";	
			}
			contractForExecutionService.updateContract(contract, term_doc, contract_doc, additional_parties,
					list_type_of_contract,status,session);
			return "redirect:listPreExecutionSummary";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: Download document
	@RequestMapping(value = "/downloadPreExecutedDocument", method = RequestMethod.GET)
	public void downloadPreExecutedDocument(int cdoc_id, HttpServletResponse response) {
		try {
			contractForExecutionService.downloadPreExecutedDocument(cdoc_id, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: Download document
	@RequestMapping(value = "/downloadPreExecutedHistoryDocument", method = RequestMethod.GET)
	public void downloadPreExecutedHistoryDocument(int chst_doc_id, HttpServletResponse response) {
		try {
			contractForExecutionService.downloadPreExecutedHistoryDocument(chst_doc_id, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: edit contract history
	@RequestMapping(value = "/editContractHistory", method = RequestMethod.GET)
	public ModelAndView editContractHistory(int chst_id) {
		try {
			//ContractHistory cont_hst = contractForExecutionService.getContractHistoryByHistoryId(chst_id);
			ContractHistoryReference cont_hst = contractForExecutionService.getContractHistory(chst_id);
			ModelAndView modelAndView = new ModelAndView("editContractHistory", "editContractHistory",cont_hst);
			ContractReference cont = contractForExecutionService.getJoinedContractDetailsById(cont_hst.getChst_contract_id());
			int orga_id = cont.getCont_orga_id();
			int loca_id = cont.getCont_loca_id();
			int dept_id = cont.getCont_dept_id();
			modelAndView.addObject("allUser",userService.getUsersByOrganizationLocationDepartment(orga_id, loca_id, dept_id));
			modelAndView.addObject("contract_history_doc", contractForExecutionService.getContractHistoryDocById(chst_id));
			modelAndView.addObject("allContractType", contractTypeService.getAllContractType());
			modelAndView.addObject("allPOCUser",userService.getAllPOCUsers());
			modelAndView.addObject("status",cont_hst.getChst_status());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: update contract history
	@RequestMapping(value = "/updateContractHistory", method = RequestMethod.POST)
	public String updateContractHistory(ContractHistoryReference contractHistory,@RequestParam( name="chst_contract_type_id",required=false) ArrayList<String> chst_contract_type,@RequestParam("contract_history_document") ArrayList<MultipartFile> hst_doc) {
		try {
			contractForExecutionService.updateContractHistory(contractHistory,hst_doc,chst_contract_type);
			return "redirect:updateTab?cont_id=" + contractHistory.getChst_contract_id();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: delete contract party
	@RequestMapping(value = "/deletePreExecutedParty", method = RequestMethod.POST)
	public @ResponseBody String deletePreExecutedParty(@RequestBody String jsonString) {
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString);
			int party_id = Integer.parseInt(jsonObj.get("party_id").toString());
			int deleteCount = contractForExecutionService.deletePreExecutedParty(party_id);
			return String.valueOf(deleteCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: delete contract
	@RequestMapping(value = "/deletePreExecutedContract", method = RequestMethod.POST)
	public @ResponseBody String deletePreExecutedContract(@RequestBody String json) {
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
			int cont_id = Integer.parseInt(jsonObj.get("cont_id").toString());
			int deleteCount = contractForExecutionService.deletePreExecutedContract(cont_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: delete contract
	@RequestMapping(value = "/deletePreExecutedContractHistory", method = RequestMethod.POST)
	public @ResponseBody String deletePreExecutedContractHistory(@RequestBody String json) {
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
			int chst_id = Integer.parseInt(jsonObj.get("chst_id").toString());
			int deleteCount = contractForExecutionService.deletePreExecutedContractHistory(chst_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: delete contract document
	@RequestMapping(value = "/deletePreExecutedContractDocument", method = RequestMethod.POST)
	public @ResponseBody String deletePreExecutedContractDocument(@RequestBody String json) {
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
			int cdoc_id = Integer.parseInt(jsonObj.get("cdoc_id").toString());
			int deleteCount = contractForExecutionService.deletePreExecutedContractDocument(cdoc_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created By: Tejashri Zurunge
	// Method Purpose: delete contract document
	@RequestMapping(value = "/deleteContractHistoryDoc", method = RequestMethod.POST)
	public @ResponseBody String deleteContractHistoryDoc(@RequestBody String json) {
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
			int doc_id = Integer.parseInt(jsonObj.get("doc_id").toString());
			int deleteCount = contractForExecutionService.deleteContractHistoryDoc(doc_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created By: 	Tejashri Zurunge
	// Method Purpose: 		Save Contract details after accept
	@RequestMapping(value = "/saveContractByAccept", method = RequestMethod.POST)
	public String saveContractByAccept(ContractReference contractRef,
			@RequestParam("term_sheet_document") ArrayList<MultipartFile> term_doc,
			@RequestParam("contract_document") ArrayList<MultipartFile> contract_doc,
			@RequestParam(name="additional_parties",required=false) ArrayList<String> additional_parties,
			@RequestParam(value="Save",required=false,defaultValue="d_save") String save,@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft,
			@RequestParam(value="cont_type_of_contract",required=false) ArrayList<String> list_type_of_contract, 
			@RequestParam(name="cont_req_id",required=false,defaultValue="0") int id, HttpSession session) {
		try {
			String status = "";
			if(save.equals("Save")){
				status = "Pending";	
			}
			if(draft.equals("Draft")){
				status = "Draft";	
			}
			contractForExecutionService.saveContractByAccept(contractRef, term_doc, contract_doc, additional_parties,
					list_type_of_contract,status,id,session);
			return "redirect:listPreExecutionSummary";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
