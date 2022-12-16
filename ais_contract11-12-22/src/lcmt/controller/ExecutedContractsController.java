package lcmt.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.SystemOutLogger;
import org.json.simple.JSONArray;
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

import lcmt.dao.ContractDao;
import lcmt.dao.impl.ExecutedContractDaoImpl;
import lcmt.domain.AmendmentContract;
import lcmt.domain.Contract;
import lcmt.domain.ContractContractType;
import lcmt.domain.ExecuteActionItem;
import lcmt.domain.ExecutedContractDocuments;
import lcmt.domain.ExecutedContractReference;
import lcmt.domain.ExecutedContracts;
import lcmt.domain.TermRenwContract;
import lcmt.domain.TermRenwContractRef;
import lcmt.domain.TransactionalActionItem;
import lcmt.service.ContractService;
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
	@Autowired 
	ContractService contractService;
	@Autowired 
	ContractDao contractDao;
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
			@RequestParam("executed_documents") ArrayList<MultipartFile> executed_contract_documents,
			@RequestParam("additional_parties") ArrayList<String> additional_parties,
			@RequestParam(value = "Save", required = false, defaultValue = "Save") String save,
			@RequestParam(value = "Draft", required = false, defaultValue = "Draft") String draft,
			@RequestParam(value="actionType",required = false,defaultValue="Pending") String actionType)
			{
		try {
			
			String status = actionType;
			if(status.equals("Save")){
				status = "Pending";	
			}
			if(status.equals("Draft")){
				
				status = "Draft";	
			}
			
			
			/*System.out.println("Check:"+save);*/
			executedContractService.saveExecutedContract(contracts, executed_contract_documents,additional_parties,status);
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
			int user_id = exec.getExec_contract_added_by();
			System.out.println("id:"+user_id);
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("allLocations", entityMappingService.getLocationByUserId(session, orga_id));
			modelAndView.addObject("allDepartments", entityMappingService.getDeptByUserId(session, loca_id, orga_id));
			modelAndView.addObject("allContractType", contractTypeService.getAllContractType());
			modelAndView.addObject("allParties", executedContractService.getPartiesByContractId(exec_id));
			modelAndView.addObject("user_list", userService.getUsersByOrganizationLocationDepartment(orga_id,loca_id ,dept_id));
			modelAndView.addObject("User",userService.getUserById(user_id));
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
			@RequestParam("executed_documents") ArrayList<MultipartFile> executed_contract_documents,
			@RequestParam(name="additional_parties",required=false) ArrayList<String> additional_parties, HttpSession session,
			@RequestParam(value = "Draft", required = false, defaultValue = "Draft") String draft ) {
		try {
			String status = "";
			if(draft.equals("draft")){
				status = "Draft";
			}
			executedContractService.updateExecutedContract(contracts, executed_contract_documents,additional_parties, session,status);
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
			String exec_contract_division =	jsonobj.get("exec_contract_division").toString();
			String result = executedContractService.searchExecutedContract(exec_contract_type_id,exec_contract_entity_id,
					exec_contract_unit_id,exec_contract_function_id,exec_contract_division, session);
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
			modelAndView.addObject("executedContractDocuments", executedContractService.getAllExecutedContractDocumentsById(exec_id,1));
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

		
		// Method Created : Tejashri Zurunge
		@SuppressWarnings("deprecation")
		// Method purpose : Load add Executed contracts action item history
		@RequestMapping(value = "/addExecutedByAccept", method = RequestMethod.GET)
		public ModelAndView addExecutedByAccept(int cont_id, HttpSession session,
				@RequestParam(value="Draft",required=false) String draft,
				@RequestParam(value="actionType",required = false,defaultValue="Pending") String actionType) {
			try {new SimpleDateFormat("dd-MM-yyyy");
				new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
				
				ExecutedContractReference ref = new ExecutedContractReference(); 
				
				String status = actionType;
				if(status.equals("Save")){
					status = "Pending";	
				}
				if(status.equals("Draft")){
					status = "Draft";	
				}
				Contract cont = contractService.getContractByContractId(cont_id);
				
				int orga_id = cont.getCont_orga_id();
				int loca_id = cont.getCont_loca_id();
				int dept_id = cont.getCont_dept_id();
				ref.setExec_contract_entity_id(orga_id);
				ref.setExec_contract_unit_id(loca_id);
				ref.setExec_contract_function_id(dept_id);
				if(cont.getCont_start_date()!= null){
				ref.setExec_contract_start_date_name(sdfOut.format(cont.getCont_start_date()));}
				
				if(cont.getCont_end_date()!=null){
				ref.setExec_contract_end_date_name(sdfOut.format(cont.getCont_end_date()));}
				
				
			/*	ref.setExec_contract_end_date(sdfOut.format(cont.getCont_end_date()));*/
		       Date end_date = cont.getCont_end_date();
		     //  Date date = null;
				/*if(end_date == null)
					return null;*/
				if(end_date != null){
		       Date exec_remind_date = new Date(end_date.getTime() - 15 * 24 * 3600 * 1000  );
		    
		       ref.setExec_remind_date(sdfOut.format(exec_remind_date)); 
		       
		       Date exec_remind_date_second = new Date(end_date.getTime() - 7 * 24 * 3600 * 1000  );
		    
		       ref.setExec_remind_date_second(sdfOut.format(exec_remind_date_second));
		       
		       Date Third_Reminder_Alert = new Date(end_date.getTime() - 3 * 24 * 3600 * 1000  );
		   
		       ref.setExec_remind_date_third(sdfOut.format(Third_Reminder_Alert));
				}
				
				ref.setExec_contract_surviving_obl(cont.getCont_surviving_clause());
				ref.setExec_contract_payment(cont.getCont_payment());
				ref.setExec_contract_damages(cont.getCont_damages());
				ref.setExec_contract_resposible_person(Integer.parseInt(cont.getCont_responsible_user_id()));
				ref.setExec_parties(contractDao.getContractPartiesByContractId(cont_id));
				ref.setExec_cont_type_list(contractDao.getContractTypeByContractId(cont_id));
				ref.setExec_contract_pre_cont_id(cont_id);
				ref.setExec_contract_notice_period(cont.getExec_contract_notice_period());
				ref.setExec_contract_division(cont.getCont_division());
				ref.setExec_force_majeure(cont.getCont_force_majeure());
				ModelAndView modelAndView = new ModelAndView("addExecutedByAccept", "addExecutedByAccept",
						ref);
				modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
				modelAndView.addObject("allLocations", entityMappingService.getLocationByUserId(session, orga_id));
				modelAndView.addObject("allDepartments", entityMappingService.getDeptByUserId(session, loca_id, orga_id));
				modelAndView.addObject("allContractType", contractTypeService.getAllContractType());
				//modelAndView.addObject("start_date", attributeValue);
				//modelAndView.addObject("end_date", attributeValue);
				modelAndView.addObject("allParties", contractDao.getContractPartiesByContractId(cont_id));
				modelAndView.addObject("user_list", userService.getUsersByOrganizationLocationDepartment(orga_id,loca_id ,dept_id));
				modelAndView.addObject("contractDocuments",contractService.getAllContractDocumentsByContractId(cont_id));
				return modelAndView;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		
		
		
		@RequestMapping(value = "/addExecContractDocument", method = RequestMethod.POST)
		public @ResponseBody int addExecContractDocument(@RequestParam("executed_contract_documents") MultipartFile executed_contract_documents,
				//@RequestParam("doc_comments") String doc_comments,
				@RequestParam("exec_contract_id") int exec_id) {
			try {
				executedContractService.addExecContractDocument(exec_id , executed_contract_documents);
				return exec_id;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
		
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/getAllExecutedContractDocumentForAjax", method = RequestMethod.POST)
		public @ResponseBody String getAllExecutedContractDocumentForAjax(@RequestBody String jsonString) {
			try {
				JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
				int exec_contract_id = Integer.parseInt(jsonObject.get("exec_contract_id").toString());
				List<ExecutedContractDocuments> contracts = executedContractService.getAllExecutedContractDocumentsById(exec_contract_id, 1);
				if (!contracts.isEmpty()) {
					JSONArray documentsDataToSend = new JSONArray();
					Iterator<ExecutedContractDocuments> itr = contracts.iterator();
					while (itr.hasNext()) {
						ExecutedContractDocuments execontractDoc = (ExecutedContractDocuments) itr.next();
						JSONObject objToAddToArray = new JSONObject();
						objToAddToArray.put("eldoc_id", execontractDoc.getExec_doc_id());
						objToAddToArray.put("eldoc_original_file_name", execontractDoc.getExec_original_file_name());
						objToAddToArray.put("eldoc_generated_file_name", execontractDoc.getExec_generated_file_name());
						objToAddToArray.put("eldoc_document_type", execontractDoc.getExec_doc_type());

						documentsDataToSend.add(objToAddToArray);

					}
					return documentsDataToSend.toJSONString();
				} else {
					return "";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}	
		@RequestMapping(value = "/listTermRenContract", method = RequestMethod.GET)
		public ModelAndView listTermRenContract(int exec_id, HttpSession session) {
			try {
				ModelAndView modelAndView = new ModelAndView("listTermRenContract", "alllistTermRenContract",executedContractService.getJoinedTermRenContractDetails(exec_id, session));
				/*ModelAndView modelAndView = new ModelAndView("listTermRenContract");
				modelAndView.addObject("user_legal_department", userService.getAll());*/
				return modelAndView;
				
			/*	 List<TermRenwContract> list=executedContractService.getTermRenwContract();    
			        m.addAttribute("list",list);  
			        return "viewemp"; */ 
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		// Method Created : Pranit Hanamghar
		// Method purpose : Add term/renw Contract
		@RequestMapping(value = "/addTermRenContract", method = RequestMethod.GET)
		public ModelAndView addTermRenContract(int exec_id, HttpSession session) {
			try {

				/*ModelAndView modelAndView = new ModelAndView("addTermRenContract", "addTermRen",
						new AmendmentContract());*/
				ModelAndView modelAndView = new ModelAndView("addTermRenContract","addTermRen",
						new TermRenwContract());
				modelAndView.addObject("user_legal_department", userService.getAll());

				return modelAndView;
			} catch (Exception e) {
				e.printStackTrace(); 
			}
			return null;
		}
		
		// Method Created : Pranit Hanamghar
		// Method purpose : Save Term Ren Contract
		@RequestMapping(value = "/saveTermRenContract", method = RequestMethod.POST)
		public String saveTermRenContract(TermRenwContract termrenwcontract,
			
				HttpSession session, 
				@RequestParam(value = "Save", required = false,defaultValue="d_save") String save,
				
				@RequestParam("termrenw_documents") ArrayList<MultipartFile> termrenw_documents) {
			try {
				String status = "";
				
				if (save.equals("Save")) {
					status = "Pending";
				}
				executedContractService.termrenwpersist(termrenwcontract,  session, status, termrenw_documents);
				return "redirect:listTermRenContract?exec_id=" + termrenwcontract.getTermren_exec_contract_id();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		// Method Created :Pranit Hanamghar
				// Method purpose :Edit Amendment Contract
				@RequestMapping(value = "/editTermRenContract", method = RequestMethod.GET)
				public ModelAndView editTermRenContract(int termren_contract_id, HttpSession session) {
					try {
						TermRenwContract ref = executedContractService.getTermRenContractById(termren_contract_id);
								

						ModelAndView modelAndView = new ModelAndView("editTermRenContract", "editTermRenContract", ref);
						modelAndView.addObject("user_legal_department", userService.getAll());
						modelAndView.addObject("exec_id", ref.getTermren_exec_contract_id());
						modelAndView.addObject("amend_doc",
								executedContractService.getAllExecutedContractDocumentsById(termren_contract_id, 3));
						return modelAndView;
					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;

				}
	
}
