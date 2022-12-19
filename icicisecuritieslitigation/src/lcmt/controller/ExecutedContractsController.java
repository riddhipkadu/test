package lcmt.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

import lcmt.domain.ExecuteActionItem;
import lcmt.domain.ExecutedContractReference;
import lcmt.domain.ExecutedContracts;
import lcmt.domain.TransactionalActionItem;
import lcmt.service.ContractTypeService;
import lcmt.service.EntityMappingService;
import lcmt.service.ExecutedContractService;
import lcmt.service.UserService;

/*
 * Author: Harshad Padole
 * Date: 13/09/2016
 * Updated By: 
 * Updated Date: 
 * 
 * */

@Controller
@RequestMapping("/*")
public class ExecutedContractsController {

	@Autowired
	EntityMappingService entityMappingService;
	@Autowired
	ExecutedContractService executedContractService;
	@Autowired
	ContractTypeService contractTypeService;
	@Autowired
	UserService userService;
	// Method Created : Harshad Padole
	// Method purpose : Get all Executed contracts
	@RequestMapping(value = "/listExecutedContract", method = RequestMethod.GET)
	public ModelAndView listExecutedContract(HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("listExecutedContracts", "ContractList",
					executedContractService.getListAllContract(session));
			modelAndView.addObject("allContractType", contractTypeService.getAllContractType());
			modelAndView.addObject("allExecutedWith", executedContractService.getAllExecutedWith());
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method purpose : Load add Executed contracts
	@RequestMapping(value = "/addExecutedContract", method = RequestMethod.GET)
	public ModelAndView addExecutedContract(HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("addExecutedContract", "addExecutedContract",
					new ExecutedContracts());
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("allContractType", contractTypeService.getAllContractType());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method purpose : add Executed contracts
	@RequestMapping(value = "/saveExecutedContract", method = RequestMethod.POST)
	public String saveExecutedContract(ExecutedContracts contracts,
			@RequestParam("executed_documents") ArrayList<MultipartFile> executed_contract_documents,@RequestParam("additional_parties") ArrayList<String> additional_parties) {
		try {
			executedContractService.saveExecutedContract(contracts, executed_contract_documents,additional_parties);
			return "redirect:listExecutedContract";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
	// Method purpose : Load add Executed contracts
	@RequestMapping(value = "/editExecutedContract", method = RequestMethod.GET)
	public ModelAndView editExecutedContract(int exec_id, HttpSession session) {
		try {
			ExecutedContractReference exec = executedContractService.getJoinedExecutedContractDetailsById(exec_id);
			ModelAndView modelAndView = new ModelAndView("editExecutedContracts", "editExecutedContract",exec);
			int orga_id = exec.getExec_contract_entity_id();
			int loca_id = exec.getExec_contract_unit_id();
			int dept_id = exec.getExec_contract_function_id();
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("allLocations", entityMappingService.getLocationByUserId(session, orga_id));
			modelAndView.addObject("allDepartments", entityMappingService.getDeptByUserId(session, loca_id, orga_id));
			modelAndView.addObject("allContractType", contractTypeService.getAllContractType());
			modelAndView.addObject("allParties", executedContractService.getPartiesByContractId(exec_id));
			modelAndView.addObject("user_list", userService.getUsersByOrganizationLocationDepartment(orga_id,loca_id ,dept_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method purpose : add Executed contracts
	@RequestMapping(value = "/updateExecutedContract", method = RequestMethod.POST)
	public String updateExecutedContract(ExecutedContractReference contracts,
			@RequestParam("executed_documents") ArrayList<MultipartFile> executed_contract_documents,@RequestParam(name="additional_parties",required=false) ArrayList<String> additional_parties) {
		try {
			executedContractService.updateExecutedContract(contracts, executed_contract_documents,additional_parties);
			return "redirect:listExecutedContract";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : search Executed Contract
	@RequestMapping(value = "/searchExecutedContract", method = RequestMethod.POST)
	public @ResponseBody String searchExecutedContract(@RequestBody String jsonString, HttpSession session) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int exec_contract_type_id = Integer.parseInt(jsonobj.get("exec_contract_type_id").toString());
			int exec_contract_entity_id = Integer.parseInt(jsonobj.get("exec_contract_entity_id").toString());
			int exec_contract_unit_id = Integer.parseInt(jsonobj.get("exec_contract_unit_id").toString());
			int exec_contract_function_id = Integer.parseInt(jsonobj.get("exec_contract_function_id").toString());

			String result = executedContractService.searchExecutedContract(exec_contract_type_id,exec_contract_entity_id,
					exec_contract_unit_id,exec_contract_function_id, session);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : download Executed Contract documents
	@RequestMapping(value = "/downloadExecutedContractDoc", method = RequestMethod.GET)
	public void downloadExecutedContractDoc(int exec_doc_id, HttpServletResponse response) {
		try {
			executedContractService.downloadExecutedContractDoc(exec_doc_id, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Method Created By:	Tejashri Zurunge
	//Method Purpose: 		executed contract details tab
	@RequestMapping(value = "/executedContractUpdates" , method = RequestMethod.GET)
	public ModelAndView executedContractUpdates(int exec_id, HttpSession session){
		try {
			ModelAndView modelAndView = new ModelAndView("executedContractUpdates", "executedContractDetails", executedContractService.getJoinedExecutedContractDetailsById(exec_id));
			modelAndView.addObject("executedContractDocuments", executedContractService.getAllExecutedContractDocumentsById(exec_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Executed Contract
	@RequestMapping(value = "/deleteExecutedContract", method = RequestMethod.POST)
	public @ResponseBody String deleteExecutedContract(@RequestBody String jsonString) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int exec_contract_id = Integer.parseInt(jsonObject.get("exec_contract_id").toString());
			int deleteCount = executedContractService.deleteExecutedContract(exec_contract_id);
			return String.valueOf(deleteCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Executed Contract
	@RequestMapping(value = "/deleteExecutedContractDocument", method = RequestMethod.POST)
	public @ResponseBody String deleteExecutedContractDocument(@RequestBody String jsonString) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int exec_doc_id = Integer.parseInt(jsonObject.get("exec_doc_id").toString());
			int deleteCount = executedContractService.deleteExecutedContractDocument(exec_doc_id);
			return String.valueOf(deleteCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Load add Executed contracts action item page
	@RequestMapping(value = "/addActionItem", method = RequestMethod.GET)
	public ModelAndView addActionItem(int exec_id, HttpSession session) {
		try {
			//String[] ids = exec_id.split("-");
			ExecutedContractReference exec = executedContractService.getJoinedExecutedContractDetailsById(exec_id);
			int orga_id = exec.getExec_contract_entity_id();
			int loca_id = exec.getExec_contract_unit_id();
			int dept_id = exec.getExec_contract_function_id();
			
			ExecuteActionItem item = new ExecuteActionItem();
			item.setExec_contract_id(exec_id);
			
			ModelAndView modelAndView = new ModelAndView("addActionItem","addActionItem",item); 
			modelAndView.addObject("user_list", userService.getUsersByOrganizationLocationDepartment(orga_id,loca_id ,dept_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method purpose : add Executed contracts action item
	@RequestMapping(value = "/saveActionItem", method = RequestMethod.POST)
	public String saveActionItem(ExecuteActionItem actionItem,HttpSession session) {
		try {
			 executedContractService.saveActionItem(actionItem,session);
			return "redirect:listActionItem?exec_id="+actionItem.getExec_contract_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
	// Method purpose : Load edit Executed contracts action item
	@RequestMapping(value = "/editActionItem", method = RequestMethod.GET)
	public ModelAndView editActionItem(int exec_id,HttpSession session) {
		try {
			ExecuteActionItem item = executedContractService.getActionItem(exec_id);
			
			ExecutedContractReference exec = executedContractService.getJoinedExecutedContractDetailsById(item.getExec_contract_id());
			int orga_id = exec.getExec_contract_entity_id();
			int loca_id = exec.getExec_contract_unit_id();
			int dept_id = exec.getExec_contract_function_id();
			
			ModelAndView modelAndView = new ModelAndView("editActionItem", "editActionItem",item);
			modelAndView.addObject("user_list", userService.getUsersByOrganizationLocationDepartment(orga_id,loca_id ,dept_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Method created : Tejashri Zurunge
	//Method purpose : list Action item
	@RequestMapping(value = "/listActionItem", method = RequestMethod.GET)
	public ModelAndView listActionItem(HttpSession session, int exec_id,HttpServletRequest request) {
		try {
			ModelAndView modelAndView = new ModelAndView("listActionItem", "ActionItemsList", executedContractService.getAllExecutedActionItem(exec_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method purpose : update Executed contracts action item
	@RequestMapping(value = "/updateActionItem", method = RequestMethod.POST)
	public String updateActionItem(ExecuteActionItem actionItem, HttpSession session) {
		try {
			executedContractService.updateExecuteActionItem(actionItem);
			return "redirect:listActionItem?exec_id="+actionItem.getExec_contract_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By:	Tejashri Zurunge
	//Method Purpose: 		executed contract details tab
	@RequestMapping(value = "/actionItemHistory" , method = RequestMethod.GET)
	public ModelAndView actionItemHistory(int action_id, HttpSession session){
		try {
			ExecuteActionItem item = executedContractService.getActionItem(action_id);
			int exec_id = item.getExec_contract_id();
			ModelAndView modelAndView = new ModelAndView("actionItemHistory", "taskHistory",
					executedContractService.getActionItemHistoryById(action_id));
			modelAndView.addObject("exec_id", exec_id);
			//modelAndView.addObject("action_id", item.getExec_action_id());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Method Created : Tejashri Zurunge
	// Method purpose : Load edit Executed contracts action item
	@RequestMapping(value = "/addTaskCompletion", method = RequestMethod.GET)
	public ModelAndView addTaskCompletion(int atrn_id, HttpSession session) {
		try {
			TransactionalActionItem actionItem = executedContractService.getCompletionTaskById(atrn_id);
			ModelAndView modelAndView = new ModelAndView("addActionTaskCompletion", "addTaskCompletion", actionItem);
			modelAndView.addObject("action_id", actionItem.getAtrn_action_id());
			return modelAndView;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value ="/updateTaskCompletionAction", method=RequestMethod.POST)
	public String updateTaskCompletionAction(TransactionalActionItem item, HttpSession session){
		try {
			executedContractService.updateTaskCompletionAction(item, session);
			return "redirect:actionItemHistory?action_id= "+item.getAtrn_action_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : search Executed Contract
	@RequestMapping(value = "/searchActionItem", method = RequestMethod.POST)
	public @ResponseBody String searchActionItem(@RequestBody String jsonString, HttpSession session) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int exec_id = Integer.parseInt(jsonobj.get("exec_id").toString());
			String exec_frequency = jsonobj.get("exec_frequency").toString();
			String exec_from_due_date = jsonobj.get("exec_from_due_date").toString();
			String exec_to_due_date = jsonobj.get("exec_to_due_date").toString();

			String result = executedContractService.searchActionItem(exec_id,exec_frequency,exec_from_due_date,	exec_to_due_date, session);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
		// Method Created : Tejashri Zurunge
		// Method purpose : Load add Executed contracts action item history
		@RequestMapping(value = "/addActionItemHistory", method = RequestMethod.GET)
		public ModelAndView addActionItemHistory(HttpSession session) {
			try {
				//TransactionalActionItem actionItem = executedContractService.getCompletionTaskById(atrn_id);
				ModelAndView modelAndView = new ModelAndView("addActionItemHistory");
				return modelAndView;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@RequestMapping(value = "/exportXLSData", method = RequestMethod.POST)
		public String exportXLSData(@RequestParam("executed_documents") ArrayList<MultipartFile> executed_contract_documents){
			try {
				return executedContractService.getExportedXLS(executed_contract_documents);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	
}
