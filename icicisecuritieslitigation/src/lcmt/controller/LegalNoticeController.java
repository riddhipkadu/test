package lcmt.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
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

import lcmt.domain.Ext_Coun_Reference;
import lcmt.domain.LegalNotice;
import lcmt.domain.LegalNoticeStatus;
import lcmt.domain.LegalNotice_Reference;
import lcmt.service.CurrencyService;
import lcmt.service.EntityMappingService;
import lcmt.service.ExternalCounselService;
import lcmt.service.LegalCategoryService;
import lcmt.service.LegalNoticeService;
import lcmt.service.LitigationService;
import lcmt.service.OrganizationService;
import lcmt.service.UserService;

@Controller
@RequestMapping("/*")
public class LegalNoticeController {

	@Autowired
	EntityMappingService entityMappingService;
	@Autowired
	LegalNoticeService legalNoticeService;
	@Autowired
	UserService userService;
	@Autowired
	ExternalCounselService externalCounselService;
	@Autowired
	LegalCategoryService legalCategoryService;
	@Autowired
	OrganizationService organizationService;
	@Autowired
	CurrencyService currencyService;
	@Autowired
	LitigationService litigationService;
	// Method Created : Harshad Padole
	// Method Purpose : Show List of LegalNotice
	@RequestMapping(value = "/listLegalNotice", method = RequestMethod.GET)
	public ModelAndView listLegalNotice(HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("listLegalNotice", "listLegalNotice",
					legalNoticeService.getAllLegalNotice(session));
			// modelAndView.addObject("allOrganizations",
			// organizationService.getAll());
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("allUser", userService.getAllUser());
			modelAndView.addObject("allLegalCategory", legalCategoryService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Show add legal Notice Page
	@RequestMapping(value = "/addLegalNotice", method = RequestMethod.GET)
	public ModelAndView addLegalNotice(HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("addLegalNotice", "addLegalNotice",
					new LegalNotice_Reference());
			modelAndView.addObject("allOrganization", entityMappingService.getOrganizationByUserId(session));
			//modelAndView.addObject("allUser", userService.getAllUser());
			modelAndView.addObject("allLegalCategory", legalCategoryService.getAll());
			modelAndView.addObject("allCurrency", currencyService.getAll());
			modelAndView.addObject("allUsers", userService.getAllUser());
			List<Ext_Coun_Reference> counsels = externalCounselService.getAll();
			Map<Integer, String> sendList = new HashMap<Integer, String>();
			for (Ext_Coun_Reference temp : counsels) {
				sendList.put(0, "--Select--");
				sendList.put(temp.getExte_coun_id(), temp.getExte_coun_name());
			}
			modelAndView.addObject("allExternalCounsel", sendList);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Save Legal Notice
	@RequestMapping(value = "/saveLegalNotice", method = RequestMethod.POST)
	public String saveLegalNotice(LegalNotice_Reference legalNotice_Reference,@RequestParam(name="lega_noti_req_id",required=false,defaultValue="0") int id,
			@RequestParam("legal_doc") ArrayList<MultipartFile> legal_doc,@RequestParam(value="Save",required=false,defaultValue="d_save") String save,@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft, HttpSession session) { 
		try {
			String status = "";
			if(save.equals("Save")){
				status = "Pending";	
			}
			if(draft.equals("Draft")){
				status = "Draft";	
			}
			legalNoticeService.saveLegalNotice(legalNotice_Reference,id, legal_doc, session,status);
			return "redirect:listLegalNotice";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Edit legal notice
	@RequestMapping(value = "/editLegalNotice", method = RequestMethod.GET)
	public ModelAndView editLegalNotice(int lega_noti_id, HttpSession session) {
		try {
			LegalNotice_Reference legalNotice = legalNoticeService.getLegalNoticeById(lega_noti_id);
			ModelAndView modelAndView = new ModelAndView("editLegalNotice", "editLegalNotice",legalNotice);
			modelAndView.addObject("allOrganization", entityMappingService.getOrganizationByUserId(session));
			
			int orga = legalNotice.getLega_noti_entity_id();
			int loca = legalNotice.getLega_noti_unit_id();
			int dept = legalNotice.getLega_noti_function_id();
			
			/*
			 * modelAndView.addObject("user_legal_department",
			 * userService.getUsersByOrganizationLocationDepartment (orga,loca ,dept));
			 * 
			 * modelAndView.addObject("users_legals_departments",
			 * userService.getUsersByOrganizationLocationDepartment (orga,loca ,dept));
			 * 
			 * modelAndView.addObject("user_third_legal_department",
			 * userService.getUsersByOrganizationLocationDepartment (orga, loca ,dept));
			 * 
			 * modelAndView.addObject("user_other_legal_department",
			 * userService.getUsersByOrganizationLocationDepartment (orga, loca ,dept));
			 */
			
			modelAndView.addObject("allLocations", entityMappingService.getLocationByUserId(session, orga));
			modelAndView.addObject("allDepartments", entityMappingService.getDeptByUserId(session, loca, orga));
			modelAndView.addObject("allLegalCategory", legalCategoryService.getAll());
			modelAndView.addObject("allCurrency", currencyService.getAll());
			modelAndView.addObject("allUsers", userService.getAllUser());
			List<Ext_Coun_Reference> counsels = externalCounselService.getAll();
			Map<Integer, String> sendList = new HashMap<Integer, String>();
			for (Ext_Coun_Reference temp : counsels) {
				sendList.put(0, "--Select--");
				sendList.put(temp.getExte_coun_id(), temp.getExte_coun_name());
			}
			modelAndView.addObject("allExternalCounsel", sendList);
			modelAndView.addObject("NoticeStatus",legalNotice.getLega_noti_status());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Update legal notice
	@RequestMapping(value = "/updateLegalNotice", method = RequestMethod.POST)
	public String updateLegalNotice(LegalNotice_Reference legalNotice_Reference,
			@RequestParam("legal_doc") ArrayList<MultipartFile> legal_doc,@RequestParam(value="Update",required=false,defaultValue="d_update") String save,@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft,HttpSession session) {
		try {
			String status = "";
			if(save.equals("Update")){
				status = "Pending";	
			}
			if(draft.equals("Draft")){
				status = "Draft";	
			}
			legalNoticeService.updateLegalNotice(legalNotice_Reference, legal_doc,status,session);
			return "redirect:listLegalNotice";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Search legal notice
	@RequestMapping(value = "/searchLegalNotice", method = RequestMethod.POST)
	public @ResponseBody String searchLegalNotice(@RequestBody String jsonString, HttpSession session)
			throws ParseException {
		try {
			String json = legalNoticeService.searchLegalNotice(jsonString, session);
			return json;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : show updates of legal notice
	@RequestMapping(value = "/legalNoticeUpdate", method = RequestMethod.GET)
	public ModelAndView legalNoticeUpdate(int lega_noti_id) {
		try {
			LegalNotice_Reference ref = legalNoticeService.getJoinedDetailsLegalNotice(lega_noti_id);
			ModelAndView modelAndView = new ModelAndView("legalNoticeUpdate", "legalNoticeDetails",
					ref);
			modelAndView.addObject("legalNoticeDocuments", legalNoticeService.getAllLegalNoticeDocById(lega_noti_id,1));
			modelAndView.addObject("legalNoticeStatus",legalNoticeService.getAllLegalNoticeStatus(lega_noti_id));
			LegalNotice noti = legalNoticeService.getLegalNoticeBynotiId(lega_noti_id);
			if(!ref.getLega_noti_liti_ref_id().equals("")){
			modelAndView.addObject("liti_id", litigationService.getpreviouslitiId(noti.getLega_noti_liti_ref_id()));
			}
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : download Legal documents
	@RequestMapping(value = "/downloadLegalNoticeDoc", method = RequestMethod.GET)
	public void downloadLegalNoticeDoc(int lega_doc_id, HttpServletResponse response) {
		try {
			legalNoticeService.downloadLegalNoticeDoc(lega_doc_id, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Method Created : Harshad Padole
	// Method Purpose : Update legal notice status
	@RequestMapping(value = "/legalNoticeStatus", method = RequestMethod.GET)
	public ModelAndView legalNoticeStatus(int lega_noti_id) {
		try {
			LegalNoticeStatus legalNoticeStatus = new LegalNoticeStatus();
			legalNoticeStatus.setLega_notice_id(lega_noti_id);
			ModelAndView modelAndView = new ModelAndView("addLegalNoticeStatus","addLegalStatus",legalNoticeStatus);
			LegalNotice_Reference reference = legalNoticeService.getLegalNoticeById(lega_noti_id);
			int orga_id = reference.getLega_noti_entity_id();
			int loca_id = reference.getLega_noti_unit_id();
			int dept_id = reference.getLega_noti_function_id();
			modelAndView.addObject("allUser", userService.getUsersByOrganizationLocationDepartment(orga_id, loca_id, dept_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	

	@RequestMapping(value="/saveLegalNoticeStatus", method=RequestMethod.POST)
	public String saveLegalNoticeStatus(LegalNoticeStatus legalNoticeStatus,@RequestParam("lega_status_doc") ArrayList<MultipartFile> lega_status_doc ,HttpSession session,
			@RequestParam(value="Save",required=false,defaultValue="d_save") String save,
			@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft){
		try {
			String status = "";
			if(save.equals("Save")){
				status = "Active";	
			}
			if(draft.equals("Draft")){
				status = "Draft";	
			}
			legalNoticeService.savelegalNoticeStatus(legalNoticeStatus,lega_status_doc, session, status);
			return "redirect:legalNoticeUpdate?lega_noti_id="+legalNoticeStatus.getLega_notice_id();
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
	// Method Purpose : Edit status
	@RequestMapping(value = "/editLegalNoticeStatus", method = RequestMethod.GET)
	public ModelAndView editLegalNoticeStatus(int lega_noti_status_id) {
		try {
			LegalNoticeStatus lega_status = legalNoticeService.getLegalStatusById(lega_noti_status_id);
			ModelAndView modelAndView = new ModelAndView("editLegalNoticeStatus", "editLegalNoticeStatus", lega_status);
			modelAndView.addObject("statusDoc", legalNoticeService.getLegalNoticeStatusDocById(lega_noti_status_id));
			LegalNotice_Reference reference = legalNoticeService.getLegalNoticeById(lega_status.getLega_notice_id());
			int orga_id = reference.getLega_noti_entity_id();
			int loca_id = reference.getLega_noti_unit_id();
			int dept_id = reference.getLega_noti_function_id();
			modelAndView.addObject("allUser", userService.getUsersByOrganizationLocationDepartment(orga_id, loca_id, dept_id));
			modelAndView.addObject("legal_status", lega_status.getLega_notice_status());
			modelAndView.addObject("lega_noti_id", lega_status.getLega_notice_id());
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Harshad Padole
	// Method Purpose : Update status
	@RequestMapping(value="/updateLegalNoticeStatus", method=RequestMethod.POST)
	public String updateLegalNoticeStatus(LegalNoticeStatus legalNoticeStatus,HttpSession session, @RequestParam("lega_status_doc") ArrayList<MultipartFile> lega_status_doc,
		@RequestParam(value="Update",required=false,defaultValue="d_save") String update,
		@RequestParam(value="Draft",required=false,defaultValue="d_draft") String draft){
	try {
		String status = "";
		if(update.equals("Update")){
			status = "Active";	
		}
		if(draft.equals("Draft")){
			status = "Draft";	
		}
			legalNoticeService.updatelegalNoticeStatus(legalNoticeStatus, session, lega_status_doc, status);
			return "redirect:legalNoticeUpdate?lega_noti_id="+legalNoticeStatus.getLega_notice_id();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
	// Method purpose : Delete Legal notice 	
	@RequestMapping(value = "/deleteLegalNotice", method = RequestMethod.POST)
	public @ResponseBody String deleteLegalNotice(@RequestBody String json) throws ParseException{
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
			int lega_noti_id = Integer.parseInt(jsonObj.get("lega_noti_id").toString());
			
			int deleteCount = legalNoticeService.deleteLegalNotice(lega_noti_id);
			return String.valueOf(deleteCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Delete Legal notice status
	@RequestMapping(value = "/deleteLegalNoticeStatus", method = RequestMethod.POST)
	public @ResponseBody String deleteLegalNoticeStatus(@RequestBody String json) throws ParseException {
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
			int lega_status_id = Integer.parseInt(jsonObj.get("lega_status_id").toString());
			int deleteCount = legalNoticeService.deleteLegalNoticeStatus(lega_status_id);
			return String.valueOf(deleteCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Delete Legal notice status
	@RequestMapping(value = "/deleteLegaNotiStatusDoc", method = RequestMethod.POST)
	public @ResponseBody String deleteLegaNotiStatusDoc(@RequestBody String json) throws ParseException {
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
			int doc_id = Integer.parseInt(jsonObj.get("doc_id").toString());
			int deleteCount = legalNoticeService.deleteLegaNotiStatusDoc(doc_id);
			return String.valueOf(deleteCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
	// Method purpose : get conversion rate
	/*
	 * @RequestMapping(value = "/getConversionRate", method = RequestMethod.POST)
	 * public @ResponseBody String getConversionRate(@RequestBody String json)
	 * throws ParseException { try { SimpleDateFormat sdfIn = new
	 * SimpleDateFormat("yyyy-MM-dd"); SimpleDateFormat sdfOut = new
	 * SimpleDateFormat("dd-MM-yyyy");
	 * 
	 * JSONObject jsonObj = (JSONObject) new JSONParser().parse(json); // String
	 * converted_amt_currency = jsonObj.get("converted_amt_currency").toString();
	 * String involved_amt_currency =
	 * jsonObj.get("involved_amt_currency").toString(); String date = ""; String
	 * amtDate = ""; if (!jsonObj.get("amount_date").equals("")) { date =
	 * jsonObj.get("amount_date").toString(); amtDate =
	 * sdfIn.format(sdfOut.parse(date)); } else { amtDate = "latest"; } String Rate
	 * = ""; if (!(involved_amt_currency).equals(converted_amt_currency)) { URL url
	 * = new URL("http://api.fixer.io/" + amtDate + "?from=" + involved_amt_currency
	 * + "&to=" + converted_amt_currency);
	 * 
	 * URLConnection connection = url.openConnection();
	 * 
	 * connection.connect(); // Cast to a HttpURLConnection if (connection
	 * instanceof HttpURLConnection) { HttpURLConnection httpConnection =
	 * (HttpURLConnection) connection; int code = httpConnection.getResponseCode();
	 * //System.out.println("COde " + code); if (code == 200) { String data =
	 * IOUtils.toString(url);
	 * 
	 * JSONObject json1 = (JSONObject) new JSONParser().parse(data); JSONObject
	 * convertedcurrency = (JSONObject) json1.get("rates"); //
	 * System.out.println("currency is // :"+convertedcurrency); if
	 * (convertedcurrency.size() != 0) { Rate =
	 * convertedcurrency.get(converted_amt_currency).toString(); } else { Rate =
	 * "0.0"; } } else { Rate = "0.0"; } } } else { Rate = "1.0"; } return Rate;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); return String.valueOf(0); }
	 * 
	 * }
	 */
	
	// Method Created : Tejashri Zurunge
	// Method purpose : get user by legal notice entity, unit and dept id
	//@SuppressWarnings("unchecked")
	/*@RequestMapping(value = "/getUserByLegalNoticeId", method = RequestMethod.POST)
	public @ResponseBody String getUserByLegalNoticeId(@RequestBody String json) throws ParseException{
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
			int orga_id = Integer.parseInt(jsonObj.get("orga_id").toString());
			int loca_id = Integer.parseInt(jsonObj.get("loca_id").toString());
			int dept_id = Integer.parseInt(jsonObj.get("dept_id").toString());
			JSONArray sendList = new JSONArray();
			List<Object> userList = legalNoticeService.getUserByLegalNoticeId(orga_id, loca_id, dept_id);
			Iterator<Object> iterator = userList.iterator();
			while (iterator.hasNext()) {
				Object[] object = (Object[]) iterator.next();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("user_id", Integer.parseInt(object[0].toString()));
				jsonObject.put("user_name", object[1].toString()+" "+object[2].toString());
				sendList.add(jsonObject);
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}*/

}
