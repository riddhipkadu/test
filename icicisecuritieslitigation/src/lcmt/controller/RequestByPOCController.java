package lcmt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import lcmt.dao.ContractDao;
import lcmt.dao.RequestByPOCDao;
import lcmt.domain.ContractReference;
import lcmt.domain.ContractRequest;
import lcmt.domain.ContractRequestReference;
import lcmt.domain.Ext_Coun_Reference;
import lcmt.domain.LegalNotice;
import lcmt.domain.LegalNoticeRequest;
import lcmt.domain.Litigation;
import lcmt.domain.LitigationRequest;
import lcmt.domain.PocStatusForNegotiation;
import lcmt.domain.QueryMaster;
import lcmt.domain.QueryRequest;
import lcmt.service.AdvocateService;
import lcmt.service.ContractTypeService;
import lcmt.service.CourtService;
import lcmt.service.CurrencyService;
import lcmt.service.EntityMappingService;
import lcmt.service.ExternalCounselService;
import lcmt.service.InternalLitigationService;
import lcmt.service.LawFirmService;
import lcmt.service.LegalCategoryService;
import lcmt.service.LegalNoticeService;
import lcmt.service.LitigationService;
import lcmt.service.OrganizationService;
import lcmt.service.RequestByPOCService;
import lcmt.service.StagesService;
import lcmt.service.UserService;

@Controller
@RequestMapping(value = "/*")
public class RequestByPOCController {

	@Autowired
	EntityMappingService entityMappingService;

	@Autowired
	RequestByPOCService requestByPOCService;
     
	@Autowired
	LegalNoticeService legalNoticeService;
	
	@Autowired
	ContractTypeService contractTypeService;

	@Autowired
	LitigationService litigationService;

	@Autowired
	UserService userService;

	@Autowired
	ExternalCounselService externalCounselService;

	@Autowired
	StagesService stagesService;
	@Autowired
	LawFirmService lawFirmService;
	@Autowired
	CurrencyService currencyService;
	@Autowired
	AdvocateService advocateService;
	@Autowired
	CourtService courtService;
	@Autowired
	InternalLitigationService internalLitigationService;
	@Autowired
	OrganizationService organizationService;
	@Autowired
	RequestByPOCDao requestByPOCDao;
	
	@Autowired
	ContractDao contractDao;
	@Autowired
	LegalCategoryService legalCategoryService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Add Litigation request
	@RequestMapping(value="/listRequestByPOC" , method = RequestMethod.GET)
	public ModelAndView listRequestByPOC(HttpSession session){
		try {
			ModelAndView modelAndView = new ModelAndView("listRequestByPOC", "listRequestByPOC", requestByPOCService.getAllListRequestByPOC(session));
			//modelAndView.addObject(attributeValue)
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	// Method Created : Tejashri Zurunge
	// Method Purpose : Add Advocate
	@RequestMapping(value = "/addQueryRequest", method = RequestMethod.GET)
	public ModelAndView addQueryRequest(HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("addQueryRequest", "queryRequest", new QueryRequest());
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Add Litigation request
	@RequestMapping(value = "/addContractRequest", method = RequestMethod.GET)
	public ModelAndView addContractRequest(HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("addContractRequest", "addContractRequest", new ContractRequest());
			modelAndView.addObject("allContractType", contractTypeService.getAllContractType());
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Add Litigation request
	@RequestMapping(value = "/saveContractRequest" , method = RequestMethod.POST)
	public String saveContractRequest(ContractRequest req,@RequestParam("additional_parties") ArrayList<String> additional_parties,
			@RequestParam("contract_document") ArrayList<MultipartFile> contract_doc,@RequestParam("term_sheet_document") ArrayList<MultipartFile> term_sheet_doc,
			@RequestParam(value="Save",required=false,defaultValue="d_save") String save,
			@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft, HttpSession session){
		try {
			String status = "";
			if(save.equals("Save")){
				status = "Pending";	
			}
			if(draft.equals("Draft")){
				status = "Draft";	
			}
			requestByPOCService.saveContractRequest(req, additional_parties, contract_doc, term_sheet_doc,session, status);
			
			return "redirect:listContractRequest";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Add Litigation request
	@RequestMapping(value="/listContractRequest" , method = RequestMethod.GET)
	public ModelAndView listContractRequest(HttpSession session){
		try {
			ModelAndView modelAndView = new ModelAndView("listContractRequest", "listContractRequest", requestByPOCService.getAllListContractRequest(session));
			modelAndView.addObject("user_role_id", Integer.parseInt(session.getAttribute("sess_user_role").toString()));
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Add Query request
	@RequestMapping(value = "/saveQueryRequest" , method = RequestMethod.POST)
	public String saveQueryRequest(QueryRequest req,
			@RequestParam("query_doc") ArrayList<MultipartFile> query_doc, 
			@RequestParam(value="Save",required=false,defaultValue="d_save") String save,
			@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft, HttpSession session){
		try {
			String status = "";
			if(save.equals("Save")){
				status = "Query Raised";	
			}
			if(draft.equals("Draft")){
				status = "Draft";	
			}
			requestByPOCService.saveQueryRequest(req, query_doc,status, session);
			return "redirect:listQueryRequest";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Add Litigation request
	@RequestMapping(value="/listQueryRequest" , method = RequestMethod.GET)
	public ModelAndView listQueryRequest(HttpSession session){
		try {
			ModelAndView modelAndView = new ModelAndView("listQueryRequest", "listQueryRequest", requestByPOCService.getAllListQueryRequest(session));
			modelAndView.addObject("user_role_id", Integer.parseInt(session.getAttribute("sess_user_role").toString()));
			//modelAndView.addObject("requestDocument", requestByPOCService.getAllListContractRequestDocuments(session));
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Add Litigation request
	@RequestMapping(value = "/addLitigationRequest", method = RequestMethod.GET)
	public ModelAndView addLitigationRequest(HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("addLitigationRequest", "addLitigationRequest", new LitigationRequest());
			//modelAndView.addObject("litigation_type_list", litigationService.getAllLitiType());
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Add Litigation request
	@RequestMapping(value = "/saveLitigationRequest" , method = RequestMethod.POST)
	public String saveLitigationRequest(LitigationRequest req, @RequestParam("liti_document") ArrayList<MultipartFile> liti_doc,
			@RequestParam(value="Save",required=false,defaultValue="d_save") String save,
			@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft,
			HttpSession session){
		try {
			String status = "";
			if(save.equals("Save")){
				status = "Pending";	
			}
			if(draft.equals("Draft")){
				status = "Draft";	
			}
			requestByPOCService.saveLitigationRequest(req , liti_doc, status, session);
			return "redirect:listLitigationRequest";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Add Litigation request
	@RequestMapping(value="/listLitigationRequest" , method = RequestMethod.GET)
	public ModelAndView listLitigationRequest(HttpSession session){
		try {
			ModelAndView modelAndView = new ModelAndView("listLitigationRequest", "listLitigationRequest", requestByPOCService.getAllListLitigationRequest(session));
			modelAndView.addObject("user_role_id", Integer.parseInt(session.getAttribute("sess_user_role").toString()));
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			//modelAndView.addObject(attributeValue)
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method created : Tejashri Zurunge
	//Method purpose : Approve or disapprove Request by SPOC
	@RequestMapping(value = "/approveDisapproveRequest" , method = RequestMethod.POST)
	public @ResponseBody String approveDisapproveRequest(@RequestBody String jsonString, HttpSession session) throws ParseException{
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int req_id = Integer.parseInt(jsonobj.get("req_id").toString());
			int req_status = Integer.parseInt(jsonobj.get("req_status").toString());
			String req_type = jsonobj.get("req_type").toString();
			String reason = jsonobj.get("reason").toString();
			
			//System.out.println("req_id"+req_id+" "+ req_status +" "+ req_type);
			int res = requestByPOCService.approveDisapproveRequest(req_id, req_status, req_type, reason, session);
			return String.valueOf(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(0);
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : Download documents
	@RequestMapping(value="/downloadRequestDocument" , method = RequestMethod.GET)
	public void downloadRequestDocument(int doc_id, HttpServletResponse response){
		try {
			requestByPOCService.downloadRequestDocument(doc_id, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/searchRequestQuery" , method = RequestMethod.POST)
	public @ResponseBody String searchRequestQuery(@RequestBody String jsonString, HttpSession session) throws ParseException{
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString); 
			int entity_id 	= Integer.parseInt(jsonObj.get("quer_entity_id").toString());
			int unit_id 	= Integer.parseInt(jsonObj.get("quer_unit_id").toString());
			int function_id	= Integer.parseInt(jsonObj.get("quer_function_id").toString());
			String from_date= jsonObj.get("from_date").toString();
			String to_date	= jsonObj.get("to_date").toString();
			
			String status = requestByPOCService.searchRequestQuery(entity_id, unit_id, function_id, from_date, to_date, session);
			return status;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/searchRequestLitigation" , method = RequestMethod.POST)
	public @ResponseBody String searchRequestLitigation(@RequestBody String jsonString, HttpSession session) throws ParseException{
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString); 
			int entity_id 	= Integer.parseInt(jsonObj.get("liti_entity_id").toString());
			int unit_id 	= Integer.parseInt(jsonObj.get("liti_unit_id").toString());
			int function_id	= Integer.parseInt(jsonObj.get("liti_function_id").toString());
			String from_date= jsonObj.get("from_date").toString();
			String to_date	= jsonObj.get("to_date").toString();
			
			String status = requestByPOCService.searchRequestLitigation(entity_id, unit_id, function_id, from_date, to_date, session);
			return status;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/searchRequestContract" , method = RequestMethod.POST)
	public @ResponseBody String searchRequestContract(@RequestBody String jsonString, HttpSession session) throws ParseException{
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString); 
			int entity_id 	= Integer.parseInt(jsonObj.get("req_contract_entity_id").toString());
			int unit_id 	= Integer.parseInt(jsonObj.get("req_contract_unit_id").toString());
			int function_id	= Integer.parseInt(jsonObj.get("req_contract_function_id").toString());
			String from_date= jsonObj.get("from_date").toString();
			String to_date	= jsonObj.get("to_date").toString();
			
			String status = requestByPOCService.searchRequestContract(entity_id, unit_id, function_id, from_date, to_date, session);
			return status;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Harshad Padole
	// Method Purpose : view raised query
	@RequestMapping(value="/listRaisedQuery" , method = RequestMethod.GET)
	public ModelAndView listRaisedQuery(HttpSession session,@RequestParam("relatedTo") String related_to,@RequestParam("id") int related_id){
		try {
			ModelAndView modelAndView = new ModelAndView("listRaisedQuery");
			modelAndView.addObject("user_role_id", Integer.parseInt(session.getAttribute("sess_user_role").toString()));
			modelAndView.addObject("queryList", requestByPOCService.getQueryRaisedList(related_to, related_id));
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Harshad Padole
	// Method Purpose : raised query
	@RequestMapping(value = "/raiseQuery" , method = RequestMethod.POST)
	public @ResponseBody String raiseQuery(@RequestBody String jsonString, HttpSession session) throws ParseException{
		try {
			return requestByPOCService.raiseQuery(jsonString,session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Harshad Padole
	// Method Purpose : Raised query reply
	@RequestMapping(value = "/raiseQueryReply" , method = RequestMethod.POST)
	public @ResponseBody String raiseQueryReply(@RequestBody String jsonString, HttpSession session) throws ParseException{
		try {
			return requestByPOCService.raiseQueryReply(jsonString, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : Add Litigation By accepting the request
	@RequestMapping(value = "/addLitigationByAccept", method = RequestMethod.GET)
	public ModelAndView addLitigationByAccept(int id, String type, HttpSession session) {
		try {
			System.out.println("AcceptByID:"+id);
			/************** after accepting litigation request ***************/
			if(type.equals("liti")){
			//SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			Litigation litigation = new Litigation();
			LitigationRequest litiReq = requestByPOCService.getAllListLitigationRequest(id, session);
			System.out.println(litiReq);
			int orga = litiReq.getReq_liti_entity_id();
			int loca = litiReq.getReq_liti_unit_id();
			int dept = litiReq.getReq_liti_function_id();
			litigation.setLiti_orga_id(orga);
			litigation.setLiti_loca_id(loca);
			litigation.setLiti_dept_id(dept);
			
			ModelAndView modelAndView = new ModelAndView("addLitigationByAccept", "litiRequest",litigation);
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			
			modelAndView.addObject("allLocations", entityMappingService.getAllLocations());
			modelAndView.addObject("allDepartments", entityMappingService.getAllDepartments());
			
			modelAndView.addObject("litigation_type_list", litigationService.getAllLitiType());
			modelAndView.addObject("external_counsel_list", externalCounselService.getAll());
			modelAndView.addObject("liti_code", internalLitigationService.getAll());
			modelAndView.addObject("user_legal_department",
					userService.getUsersByOrganizationLocationDepartment(orga, loca, dept));
			modelAndView.addObject("listAdvocate", advocateService.getAll());
			modelAndView.addObject("allLawFirm", lawFirmService.getAllLawFirm());
			modelAndView.addObject("allCurrency", currencyService.getAll());
			modelAndView.addObject("AllCourt", courtService.getAll());
			
			/*modelAndView.addObject("orgaName", organizationService.getOrganizationById(litiReq.getReq_liti_entity_id()).getOrga_name());
			modelAndView.addObject("locaName", litiReq.getReq_liti_unit_id());
			modelAndView.addObject("deptName", litiReq.getReq_liti_function_id());*/
			modelAndView.addObject("notice_date", sdfOut.format(litiReq.getReq_liti_received_date()));
			modelAndView.addObject("party_by", litiReq.getReq_liti_party_by());
			modelAndView.addObject("party_against", litiReq.getReq_liti_party_against());
			modelAndView.addObject("description", litiReq.getReq_liti_des());
			modelAndView.addObject("id", id);
			
			return modelAndView;
			}
			
			/************** legal notice to litigation conversion ***************/
			if(type.equals("noti")){
				System.out.println("noti");
				Litigation litigation = new Litigation();
				LegalNotice legalNotice =legalNoticeService.getAllListlegalNotice(id, session);
				
				ModelAndView modelAndView = new ModelAndView("addLitigationByAccept", "litiRequest",litigation);
				modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
				int orga = legalNotice.getLega_noti_entity_id();
				int loca = legalNotice.getLega_noti_unit_id();
				int dept = legalNotice.getLega_noti_function_id();
				litigation.setLiti_orga_id(orga);
				litigation.setLiti_loca_id(loca);
				litigation.setLiti_dept_id(dept);

				modelAndView.addObject("allLocations", entityMappingService.getLocationByUserId(session, orga));
				modelAndView.addObject("allFunctions", entityMappingService.getDeptByUserId(session, loca, orga));
				modelAndView.addObject("user_legal_department",userService.getUsersByOrganizationLocationDepartment(orga, loca, dept));
				
				modelAndView.addObject("litigation_type_list", litigationService.getAllLitiType());
			    modelAndView.addObject("external_counsel_list", externalCounselService.getAll());
				modelAndView.addObject("liti_code", internalLitigationService.getAll());
				modelAndView.addObject("user_legal_department", userService.getAll());
				modelAndView.addObject("listAdvocate", advocateService.getAll());
				modelAndView.addObject("allLawFirm", lawFirmService.getAllLawFirm());
				modelAndView.addObject("allCurrency", currencyService.getAll());
				modelAndView.addObject("AllCourt", courtService.getAll());
				modelAndView.addObject("type", type);
				modelAndView.addObject("id", id);
			//modelAndView.addObject("liti_refe_no", litigationService.getAllLitigationClientId());

				return modelAndView;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/addQueryByAccept", method = RequestMethod.GET)
	public ModelAndView addQueryByAccept(int id, HttpSession session) {
		try {
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			QueryMaster query = new QueryMaster();
			QueryRequest queryReq = requestByPOCService.getAllQueryRequest(id, session);
			int orga = queryReq.getReq_query_entity_id();
			int loca = queryReq.getReq_query_unit_id();
			int dept = queryReq.getReq_query_function_id();
			query.setQuer_entity_id(orga);
			query.setQuer_unit_id(loca);
			query.setQuer_function_id(dept);
			
			ModelAndView modelAndView = new ModelAndView("addQueryRequestByAccept", "addQueryByAccept", query);
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			
			modelAndView.addObject("allLocations", entityMappingService.getLocationByUserId(session, orga));
			modelAndView.addObject("allDepartments", entityMappingService.getDeptByUserId(session, loca, orga));
			modelAndView.addObject("query", queryReq.getReq_query());
			modelAndView.addObject("quer_date", sdfOut.format(queryReq.getReq_query_date()));
			modelAndView.addObject("user_legal_department", userService.getUsersByOrganizationLocationDepartment(orga, loca, dept));
			modelAndView.addObject("allUser", userService.getAll());
			modelAndView.addObject("allquery_doc", requestByPOCDao.getAllRequestDocument(id, "QueryRequest"));
			modelAndView.addObject("id", id);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/addContractByAccept", method = RequestMethod.GET)
	public ModelAndView addContractByAccept(int id, HttpSession session) {
		try {
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			ContractReference ref = new ContractReference();
			//Contract cont = new Contract();
			ContractRequest contReq = requestByPOCService.getAllContractrequest(id, session);
			int orga = contReq.getReq_contract_entity_id();
			int loca = contReq.getReq_contract_unit_id();
			int dept = contReq.getReq_contract_function_id();
			ref.setCont_orga_id(orga);
			ref.setCont_loca_id(loca);
			ref.setCont_dept_id(dept);
			ref.setCont_requested_by_user_id(contReq.getReq_contract_added_by());
			ref.setCont_parties(requestByPOCDao.getAllPartyForRequest(contReq.getReq_contract_id()));
			
			//ref.setCont_type_list();
			ModelAndView modelAndView = new ModelAndView("addContractRequestByAccept", "addContractByAccept", ref);
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("allLocations", entityMappingService.getLocationByUserId(session, orga));
			modelAndView.addObject("allDepartments", entityMappingService.getDeptByUserId(session, loca, orga));
			modelAndView.addObject("cont_type", contReq.getReq_contract_type());
			if(contReq.getReq_contract_date() != null){
				modelAndView.addObject("cont_date", sdfOut.format(contReq.getReq_contract_date()));
			}else {
				modelAndView.addObject("cont_date", "");
			}
			if(contReq.getReq_contract_start_date() != null){
			modelAndView.addObject("start_date", sdfOut.format(contReq.getReq_contract_start_date()));
			}else {
				modelAndView.addObject("start_date", "");
			}
			if(contReq.getReq_contract_end_date() != null){
			modelAndView.addObject("end_date", sdfOut.format(contReq.getReq_contract_end_date()));
			}else {
			modelAndView.addObject("end_date", "");
			}
			modelAndView.addObject("major_clause", contReq.getReq_contract_major_clause());
			modelAndView.addObject("surviving_clause", contReq.getReq_contract_surviving_clause());
			modelAndView.addObject("payment", contReq.getReq_contract_perform_rel_payment());
			modelAndView.addObject("damages", contReq.getReq_contract_damage());
			modelAndView.addObject("nature", contReq.getReq_contract_desc());
			modelAndView.addObject("allContractType", contractTypeService.getContractTypeByContractID(contReq.getReq_contract_type()));
			modelAndView.addObject("user_legal_department", userService.getUsersByOrganizationLocationDepartment(orga, loca, dept));
			modelAndView.addObject("allUser", userService.getAll());
			modelAndView.addObject("req_doc_list", requestByPOCDao.getAllRequestDocument(id, "ContractRequest"));
			modelAndView.addObject("req_term_doc_list", requestByPOCDao.getAllRequestDocument(id, "TermSheetContRequest"));
			
			modelAndView.addObject("id",id);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/editQueryRequest", method = RequestMethod.GET)
	public ModelAndView editQueryRequest(int id, HttpSession session) {
		try {
			//SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			QueryRequest queryReq = requestByPOCService.getAllQueryRequest(id, session);
			int orga = queryReq.getReq_query_entity_id();
			int loca = queryReq.getReq_query_unit_id();
			//int dept = queryReq.getReq_query_function_id();
			
			ModelAndView modelAndView = new ModelAndView("editQueryRequest", "editQueryRequest", queryReq);
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			
			modelAndView.addObject("allLocations", entityMappingService.getLocationByUserId(session, orga));
			modelAndView.addObject("allDepartments", entityMappingService.getDeptByUserId(session, loca, orga));
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : Update Query request
	@RequestMapping(value = "/updateQueryRequest", method = RequestMethod.POST)
	public String updateQueryRequest(QueryRequest query_Reference,
			@RequestParam("query_doc") ArrayList<MultipartFile> query_doc,
			@RequestParam(value="Save",required=false,defaultValue="d_save") String save,
			@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft, HttpSession session) {
		try {
			String status = "";
			if(save.equals("Save")){
				status = "Query Raised";	
			}
			if(draft.equals("Draft")){
				status = "Draft";	
			}
			requestByPOCService.updateQueryRequest(query_Reference, query_doc, status, session);
			return "redirect:listQueryRequest";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/editLitiRequest", method = RequestMethod.GET)
	public ModelAndView editLitiRequest(int id, HttpSession session) {
		try {
			//SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			LitigationRequest litiReq = requestByPOCService.getAllListLitigationRequest(id, session);
			int orga = litiReq.getReq_liti_entity_id();
			int loca = litiReq.getReq_liti_unit_id();
			//int dept = queryReq.getReq_query_function_id();
			
			ModelAndView modelAndView = new ModelAndView("editLitiRequest", "editLitiRequest", litiReq);
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("allLocations", entityMappingService.getLocationByUserId(session, orga));
			modelAndView.addObject("allDepartments", entityMappingService.getDeptByUserId(session, loca, orga));
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : Update Query request
	@RequestMapping(value = "/updateLitiRequest", method = RequestMethod.POST)
	public String updateLitiRequest(LitigationRequest litiReq,
			@RequestParam("liti_document") ArrayList<MultipartFile> liti_doc,
			@RequestParam(value="Save",required=false,defaultValue="d_save") String save,
			@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft, HttpSession session) {
		try {
			String status = "";
			if(save.equals("Save")){
				status = "Pending";	
			}
			if(draft.equals("Draft")){
				status = "Draft";	
			}
			requestByPOCService.updateLitiRequest(litiReq, liti_doc, status, session);
			return "redirect:listLitigationRequest";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : Add Litigation request
	@RequestMapping(value = "/editContractRequest", method = RequestMethod.GET)
	public ModelAndView editContractRequest(int id, HttpSession session) {
		try {
			ContractRequestReference ref = requestByPOCService.getContractrequestById(id, session);
			//ContractReference ref = new ContractReference();
			int orga = ref.getReq_contract_entity_id();
			int loca = ref.getReq_contract_unit_id();
			//int dept = ref.getReq_contract_function_id();
			//List<> parties=ref.setCont_parties(requestByPOCDao.getAllPartyForRequest(contReq.getReq_contract_id()));
			//ref.setCont_type_list();
			ModelAndView modelAndView = new ModelAndView("editContractRequest", "editContractRequest", ref);
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("allLocations", entityMappingService.getLocationByUserId(session, orga));
			modelAndView.addObject("allDepartments", entityMappingService.getDeptByUserId(session, loca, orga));
			modelAndView.addObject("major_claus", ref.getReq_contract_major_clause());
			modelAndView.addObject("surviving_claus", ref.getReq_contract_surviving_clause());
			modelAndView.addObject("payment", ref.getReq_contract_perform_rel_payment());
			modelAndView.addObject("damages", ref.getReq_contract_damage());
			modelAndView.addObject("insurance", ref.getReq_contract_insurance());
			modelAndView.addObject("notice_period", ref.getReq_contract_notice_period());
			//ModelAndView modelAndView = new ModelAndView("editContractRequest", "editContractRequest", ref);
			modelAndView.addObject("allContractType", contractTypeService.getAllContractType());
			modelAndView.addObject("party_no", ref.getReq_party_no());
			modelAndView.addObject("party", requestByPOCDao.getAllPartyForRequest(ref.getReq_contract_id()));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Add Litigation request
	@RequestMapping(value = "/updateContractRequest" , method = RequestMethod.POST)
	public String updateContractRequest(ContractRequestReference req,
			@RequestParam(name="additional_parties", required=false) ArrayList<String> additional_parties,
			@RequestParam("contract_document") ArrayList<MultipartFile> contract_doc,
			@RequestParam("term_sheet_document") ArrayList<MultipartFile> term_sheet_doc,
			@RequestParam(value="Save",required=false,defaultValue="d_save") String save,
			@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft, HttpSession session){
		try {
			String status = "";
			if(save.equals("Save")){
				status = "Pending";	
			}
			if(draft.equals("Draft")){
				status = "Draft";	
			}
			requestByPOCService.updateContractRequest(req, additional_parties, contract_doc, term_sheet_doc,session, status);
			
			return "redirect:listContractRequest";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
// Method Created : Tejashri Zurunge
	// Method Purpose : Add Litigation request
	@RequestMapping(value = "/addContractStatusForNegotiation", method = RequestMethod.GET)
	public ModelAndView addContractStatusForNegotiation(int id, HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("addPOCstatusNegotiation", "addContractStatusForNegotiation", new PocStatusForNegotiation());
			modelAndView.addObject("id", id);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : Add Litigation request
	@RequestMapping(value = "/saveContractStatusForNegotiation" , method = RequestMethod.POST)
	public String saveContractStatusForNegotiation(PocStatusForNegotiation poc,
			@RequestParam("contract_doc") ArrayList<MultipartFile> contract_doc,
			 HttpSession session){
		try {
			requestByPOCService.saveContractStatusForNegotiation(poc, contract_doc, session);
			
			return "redirect:listContractStatusForNegotiation?id="+poc.getPoc_contract_req_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : Show List Contract status sent doc for negotiation page
	@RequestMapping(value = "/listContractStatusForNegotiation", method = RequestMethod.GET)
	public ModelAndView listContractStatusForNegotiation(int id, HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("listContractStatusForNegotiation", "allStatus",
					requestByPOCService.getAllContractStatusNegotiation(id));
			modelAndView.addObject("role_id", Integer.parseInt(session.getAttribute("sess_user_role").toString()));
			modelAndView.addObject("id", id);
			//modelAndView.addObject("isexist", attributeValue);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Method Created By: Tejashri Zurunge
	// Method Purpose: Download document
	@RequestMapping(value = "/downloadPOCDocument", method = RequestMethod.GET)
	public void downloadPOCDocument(int doc_id, HttpServletResponse response) {
		try {
			requestByPOCService.downloadPOCDocument(doc_id, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Method created : Tejashri Zurunge
	//Method purpose : Approve or disapprove Request by SPOC
	@RequestMapping(value = "/approveDisapproveStatusSentNegotiation" , method = RequestMethod.POST)
	public @ResponseBody String approveDisapproveStatusSentNegotiation(@RequestBody String jsonString, HttpSession session) throws ParseException{
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int req_id = Integer.parseInt(jsonobj.get("req_id").toString());
			int req_status = Integer.parseInt(jsonobj.get("req_status").toString());
			String reason = jsonobj.get("reason").toString();
			//System.out.println("req_id"+req_id+" "+ req_status +" "+ req_type);
			String res = requestByPOCService.approveDisapproveStatusSentNegotiation(req_id, req_status, reason, session);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(0);
	}
	
		// Method Created : Tejashri Zurunge
		// Method Purpose : view rejection reason
		@RequestMapping(value="/listRejectionReason" , method = RequestMethod.GET)
		public ModelAndView listRejectionReason(HttpSession session,@RequestParam("relatedTo") String related_to,@RequestParam("id") int related_id){
			try {
				ModelAndView modelAndView = new ModelAndView("listRejectionReason");
				modelAndView.addObject("user_role_id", Integer.parseInt(session.getAttribute("sess_user_role").toString()));
				modelAndView.addObject("reasonList", requestByPOCService.getAllRejectStatus(related_id, related_to, session));
				return modelAndView;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	
		// Method Created : Tejashri Zurunge
		// Method Purpose : List Legal notice request
		@RequestMapping(value = "/listNoticeRequest", method = RequestMethod.GET)
		public ModelAndView listNoticeRequest(HttpSession session) {
			try {
				ModelAndView modelAndView = new ModelAndView("listNoticeRequest", "listNoticeRequest", requestByPOCService.getAllListNoticeRequest(session));
				modelAndView.addObject("user_role_id", Integer.parseInt(session.getAttribute("sess_user_role").toString()));
				modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
				//modelAndView.addObject(attributeValue)
				return modelAndView;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		// Method Created : Tejashri Zurunge
		// Method Purpose : Add Legal notice request
		@RequestMapping(value = "/addNoticeRequest", method = RequestMethod.GET)
		public ModelAndView addNoticeRequest(HttpSession session) {
			try {
				ModelAndView modelAndView = new ModelAndView("addNoticeRequest", "addNoticeRequest", new LegalNoticeRequest());
				modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
				return modelAndView;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		// Method Created : Tejashri Zurunge
		// Method Purpose : Save notice request
		@RequestMapping(value = "/saveNoticeRequest" , method = RequestMethod.POST)
		public String saveNoticeRequest(LegalNoticeRequest req, @RequestParam("noti_document") ArrayList<MultipartFile> noti_doc,
				@RequestParam(value="Save",required=false,defaultValue="d_save") String save,
				@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft,
				HttpSession session){
			try {
				String status = "";
				if(save.equals("Save")){
					status = "Pending";	
				}
				if(draft.equals("Draft")){
					status = "Draft";	
				}
				requestByPOCService.saveNoticeRequest(req , noti_doc, status, session);
				return "redirect:listNoticeRequest";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		// Method Created : Tejashri Zurunge
		// Method Purpose : Add Litigation By accepting the request
		@RequestMapping(value = "/addNoticeByAccept", method = RequestMethod.GET)
		public ModelAndView addNoticeByAccept(int id, HttpSession session) {
			try {
				//SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
				
				LegalNotice noti = new LegalNotice();
				LegalNoticeRequest notiReq = requestByPOCService.getAllNoticerequest(id, session);
				int orga = notiReq.getReq_noti_entity_id();
				int loca = notiReq.getReq_noti_unit_id();
				int dept = notiReq.getReq_noti_function_id();
				noti.setLega_noti_entity_id(orga);
				noti.setLega_noti_unit_id(loca);
				noti.setLega_noti_function_id(dept);
				
				ModelAndView modelAndView = new ModelAndView("addNoticeByAccept", "addNoticeByAccept",noti);
				modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
				
				modelAndView.addObject("allLocations", entityMappingService.getAllLocations());
				modelAndView.addObject("allDepartments", entityMappingService.getAllDepartments());
				
				modelAndView.addObject("notice_type", notiReq.getReq_noti_type_by_against());
				if(notiReq.getReq_noti_date() != null){
					modelAndView.addObject("noti_date", sdfOut.format(notiReq.getReq_noti_date()));
				}else {
					modelAndView.addObject("noti_date", "");
				}
				modelAndView.addObject("oppo_party", notiReq.getReq_noti_oppo_party());
				modelAndView.addObject("description", notiReq.getReq_noti_des());
				modelAndView.addObject("user_legal_department",
						userService.getUsersByOrganizationLocationDepartment(orga, loca, dept));
				modelAndView.addObject("allLegalCategory", legalCategoryService.getAll());
				modelAndView.addObject("allCurrency", currencyService.getAll());
				List<Ext_Coun_Reference> counsels = externalCounselService.getAll();
				Map<Integer, String> sendList = new HashMap<Integer, String>();
				for (Ext_Coun_Reference temp : counsels) {
					sendList.put(0, "--Select--");
					sendList.put(temp.getExte_coun_id(), temp.getExte_coun_name());
				}
				modelAndView.addObject("allExternalCounsel", sendList);
				
				modelAndView.addObject("id", id);
				
				return modelAndView;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@RequestMapping(value = "/editNoticeRequest", method = RequestMethod.GET)
		public ModelAndView editNoticeRequest(int id, HttpSession session) {
			try {
				//SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
				
				LegalNoticeRequest notiReq = requestByPOCService.getAllNoticerequest(id, session);
				int orga = notiReq.getReq_noti_entity_id();
				int loca = notiReq.getReq_noti_unit_id();
				//int dept = queryReq.getReq_query_function_id();
				
				ModelAndView modelAndView = new ModelAndView("editNoticeRequest", "editNoticeRequest", notiReq);
				modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
				modelAndView.addObject("allLocations", entityMappingService.getLocationByUserId(session, orga));
				modelAndView.addObject("allDepartments", entityMappingService.getDeptByUserId(session, loca, orga));
				return modelAndView;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		// Method Created : Tejashri Zurunge
		// Method Purpose : update notice request
		@RequestMapping(value = "/updateNoticeRequest" , method = RequestMethod.POST)
		public String upateNoticeRequest(LegalNoticeRequest req, @RequestParam("noti_document") ArrayList<MultipartFile> noti_doc,
				@RequestParam(value="Save",required=false,defaultValue="d_save") String save,
				@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft,
				HttpSession session){
			try {
				String status = "";
				if(save.equals("Save")){
					status = "Pending";	
				}
				if(draft.equals("Draft")){
					status = "Draft";	
				}
				requestByPOCService.updateNoticeRequest(req , noti_doc, status, session);
				return "redirect:listNoticeRequest";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@RequestMapping(value = "/searchRequestNotice" , method = RequestMethod.POST)
		public @ResponseBody String searchRequestNotice(@RequestBody String jsonString, HttpSession session) throws ParseException{
			try {
				JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString); 
				int entity_id 	= Integer.parseInt(jsonObj.get("noti_entity_id").toString());
				int unit_id 	= Integer.parseInt(jsonObj.get("noti_unit_id").toString());
				int function_id	= Integer.parseInt(jsonObj.get("noti_function_id").toString());
				String from_date= jsonObj.get("from_date").toString();
				String to_date	= jsonObj.get("to_date").toString();
				
				String status = requestByPOCService.searchRequestNotice(entity_id, unit_id, function_id, from_date, to_date, session);
				return status;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} 
		
}
