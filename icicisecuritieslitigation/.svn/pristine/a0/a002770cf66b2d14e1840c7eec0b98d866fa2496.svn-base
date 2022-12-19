package lcmt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

import lcmt.domain.QueryHistory;
import lcmt.domain.Query_Reference;
import lcmt.service.DepartmentService;
import lcmt.service.EntityMappingService;
import lcmt.service.LocationService;
import lcmt.service.OrganizationService;
import lcmt.service.QueryService;
import lcmt.service.UserService;

@Controller
@RequestMapping("/*")
public class QueryController {

	@Autowired
	QueryService queryService;
	@Autowired
	OrganizationService organizationService;
	@Autowired
	UserService userService;
	@Autowired
	LocationService locationService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	EntityMappingService entityMappingService;

	// Method Created : Harshad Padole
	// Method Purpose : Load List view
	@RequestMapping(value = "/listQuery", method = RequestMethod.GET)
	public ModelAndView listQuery(HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("listQuery");
			modelAndView.addObject("listQuery", queryService.getAllQueryList(session));
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("allUser", userService.getAll());

			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Load Add query view
	@RequestMapping(value = "/addQuery", method = RequestMethod.GET)
	public ModelAndView addQuery(HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("addQuery", "addQuery", new Query_Reference());
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("allUser", userService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Save Query to DB
	@RequestMapping(value = "/saveQuery", method = RequestMethod.POST)
	public String saveQuery(Query_Reference query_reference,
			@RequestParam("query_doc") ArrayList<MultipartFile> query_doc,
			@RequestParam(value="Save",required=false,defaultValue="d_save") String save,
			@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft,@RequestParam(value="quer_req_id",required=false,defaultValue="0") int id, HttpSession session) { 
		try {
			String status = "";
			if(save.equals("Save")){
				status = "Query Raised";	
			}
			if(draft.equals("Draft")){
				status = "Draft";	
			}
			queryService.persist(query_reference, query_doc,id, session, status);
			return "redirect:listQuery";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Load Edit query view
	@RequestMapping(value = "/editQuery", method = RequestMethod.GET)
	public ModelAndView editQuery(int query_id, HttpSession session) {
		try { 
			Query_Reference quer = queryService.getQueryById(query_id);
			ModelAndView modelAndView = new ModelAndView("editQuery", "editQuery", quer);
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			int orga = quer.getQuer_entity_id();
			int loca = quer.getQuer_unit_id();
			int dept = quer.getQuer_function_id();
			modelAndView.addObject("user_legal_department", userService.getUsersByOrganizationLocationDepartment(orga, loca, dept));
			modelAndView.addObject("queryDocuments", queryService.getDocumentByQueryId(query_id));
			modelAndView.addObject("allquery_doc", queryService.getDocumentByQueryId(query_id));
			modelAndView.addObject("query_status", quer.getQuer_status());
			modelAndView.addObject("allLocations", entityMappingService.getLocationByUserId(session, orga));
			modelAndView.addObject("allDepartments", entityMappingService.getDeptByUserId(session, loca, orga));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Update Query
	@RequestMapping(value = "/updateQuery", method = RequestMethod.POST)
	public String updateQuery(Query_Reference query_Reference,
			@RequestParam("query_doc") ArrayList<MultipartFile> query_doc,
			@RequestParam(value="Update",required=false,defaultValue="d_update") String save,
			@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft, HttpSession session) {
		try {
			String status = "";
			if(save.equals("Update")){
				status = "Query Raised";	
			}
			if(draft.equals("Draft")){
				status = "Draft";	
			}
			queryService.updateQuery(query_Reference, query_doc, status, session);
			return "redirect:listQuery";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : search Query
	@RequestMapping(value = "/searchQuery", method = RequestMethod.POST)
	public @ResponseBody String searchQuery(@RequestBody String jsonString,HttpSession session) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int quer_entity_id = Integer.parseInt(jsonobj.get("quer_entity_id").toString());
			int quer_unit_id = Integer.parseInt(jsonobj.get("quer_unit_id").toString());
			int quer_function_id = Integer.parseInt(jsonobj.get("quer_function_id").toString());
			int quer_assigned_to = Integer.parseInt(jsonobj.get("quer_assigned_to").toString());
			String from_date = jsonobj.get("from_date").toString();
			String to_date = jsonobj.get("to_date").toString();

			String result = queryService.searchQuery(quer_entity_id, quer_unit_id, quer_function_id, quer_assigned_to,
					from_date, to_date,session);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete document by id
	@RequestMapping(value = "/deleteQueryDocument", method = RequestMethod.POST)
	public @ResponseBody int deleteQueryDocument(@RequestBody String jsonString) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int deleteCount = queryService.deleteQueryDocumentById(Integer.parseInt(jsonObject.get("quer_doc_id").toString()));
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	// Method Created : Tejashri Zurunge
	// Method Purpose : create sending of query reply

	@RequestMapping(value = "/replyToQuery", method = RequestMethod.GET)
	public ModelAndView replyToQuery(int query_id) {
		try {
			QueryHistory queryHistory = new QueryHistory();
			queryHistory.setQuery_quer_id(query_id);
			//System.out.println("Query ID "+query_id);
			ModelAndView modelAndView = new ModelAndView("replyToQuery", "replyToQuery", queryHistory);
			Query_Reference quer = queryService.getQueryById(query_id);
			int orga = quer.getQuer_entity_id();
			int loca = quer.getQuer_unit_id();
			int dept = quer.getQuer_function_id();
			modelAndView.addObject("allUser", userService.getUsersByOrganizationLocationDepartment(orga, loca, dept));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : save sending of query reply
	@RequestMapping(value = "/saveReplyQuery", method = RequestMethod.POST)
	public String saveReplyQuery(QueryHistory queryHistory,@RequestParam("query_doc") ArrayList<MultipartFile> hst_doc, HttpSession session){
	try {
			queryService.persistReplyQuery(queryHistory, hst_doc, session);
			//System.out.println("in Reply save query");
			return "redirect:queryDetails?query_id="+ queryHistory.getQuery_quer_id() ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get history of query by Id

	/*@RequestMapping(value = "/getReplyQueryById", method = RequestMethod.POST)
	public @ResponseBody String getReplyQueryById(@RequestParam("quer_id") Integer query_id) {
		try {
			String search = queryService.getReplyQueryById(query_id);
			return search;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get details of query
	@RequestMapping(value = "/queryDetails", method = RequestMethod.GET)
	public ModelAndView queryDetails(int query_id,HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("queryDetails");
			Query_Reference res = queryService.getJoinedQueryDetailsById(query_id,session);
			modelAndView.addObject( "query_details_by_id",res);
			modelAndView.addObject("queryHistory", queryService.getReplyQueryById(query_id));
			modelAndView.addObject("quer_id", query_id);
			if(res !=null){
			modelAndView.addObject("allDocuments", queryService.getAllQueryListWithDocuments(query_id));
			}
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : Get assigned to user of query by entity, unit and function id 
	@RequestMapping(value="/getAllUserByFunction", method = RequestMethod.POST)
	public @ResponseBody String getAllUserByFunction(@RequestParam("orga_id") Integer orga_id, @RequestParam("loca_id") Integer loca_id, @RequestParam("dept_id") Integer dept_id){
		try {
			return queryService.getAllUserByFunction(orga_id, loca_id, dept_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : Download query documents
	@RequestMapping(value="/downloadQueryDocument" , method = RequestMethod.GET)
	public void downloadQueryDocument(int quer_doc_id, HttpServletResponse response){
		try {
			queryService.downloadQueryDocument(quer_doc_id, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		//Method Created By: Mahesh Kharote
		//Method Purpose: get InitBinder
		@InitBinder
		public void initBinder(WebDataBinder binder) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			sdf.setLenient(true);
			binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		}
	
		// Method Created : Tejashri Zurunge
		// Method Purpose : get query history logs
		@RequestMapping(value = "/getQueryHistoryLogs", method = RequestMethod.POST)
		public @ResponseBody String getQueryHistoryLogs( @RequestBody String json)throws ParseException {
			try {
				return queryService.getQueryHistoryLogs(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		// Method Created : Tejashri Zurunge
		// Method Purpose : Delete query
		@RequestMapping(value = "/deleteQuery", method = RequestMethod.POST)
		public @ResponseBody String deleteQuery(@RequestBody String jsonString) {
			try {
				JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
				int quer_id = Integer.parseInt(jsonObject.get("quer_id").toString());
				int deleteCount = queryService.deleteQuery(quer_id);
				return String.valueOf(deleteCount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		// Method Created : Tejashri Zurunge
		// Method Purpose : Load Edit query history 
		@RequestMapping(value = "/editQueryHistory", method = RequestMethod.GET)
		public ModelAndView editQueryHistory(int query_hst_id, HttpSession session) {
			try { 
				QueryHistory hst = queryService.getQueryHistoryByHstId(query_hst_id);
				ModelAndView modelAndView = new ModelAndView("editQueryHistory", "editQueryHistory", hst);
				//modelAndView.addObject("queryDocuments", queryService.getDocumentByQueryId(query_hst_id));
				int query_id = hst.getQuery_quer_id();
				Query_Reference quer = queryService.getQueryById(query_id);
				int orga = quer.getQuer_entity_id();
				int loca = quer.getQuer_unit_id();
				int dept = quer.getQuer_function_id();
				modelAndView.addObject("allUser", userService.getUsersByOrganizationLocationDepartment(orga, loca, dept));
				modelAndView.addObject("query_history_doc", queryService.getHistoryDocumentByQueryId(query_hst_id));
				modelAndView.addObject("query_status", hst.getQuery_hst_status());
				return modelAndView;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		// Method Created : Tejashri Zurunge
		// Method Purpose : Update Query
		@RequestMapping(value = "/updateQueryHistory", method = RequestMethod.POST)
		public String updateQueryHistory(QueryHistory query_hst,
				@RequestParam("query_doc") ArrayList<MultipartFile> query_doc) {
			try {
				queryService.updateQueryHistory(query_hst, query_doc);
				return "redirect:queryDetails?query_id="+ query_hst.getQuery_quer_id();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@RequestMapping(value = "/deleteQueryHistory", method = RequestMethod.POST)
		public @ResponseBody String deleteQueryHistory(@RequestBody String json) throws ParseException{
			try {
				JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
				int query_hst_id = Integer.parseInt(jsonObj.get("query_hst_id").toString());
				int deleteCount = queryService.deleteQueryHistory(query_hst_id);
				return String.valueOf(deleteCount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
			
		}
		
}
