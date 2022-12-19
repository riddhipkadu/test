package lcmt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
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

import lcmt.domain.LitigationAdvocateFees;
import lcmt.dao.UserDao;
import lcmt.domain.AdvocatePaidFees;
import lcmt.domain.CounselPaidFees;
import lcmt.domain.HearingStage;
import lcmt.domain.HearingStageOnHearing;
import lcmt.domain.Litigation;
import lcmt.domain.LitigationCounselFees;
import lcmt.domain.LitigationDetails;
import lcmt.domain.LitigationDocuments;
import lcmt.domain.LitigationEscalationMailId;
import lcmt.domain.LitigationReference;
import lcmt.domain.LitigationType;
import lcmt.service.AdvocateService;
import lcmt.service.CourtService;
import lcmt.service.CurrencyService;
import lcmt.service.EntityMappingService;
import lcmt.service.ExternalCounselService;
import lcmt.service.InternalLitigationService;
import lcmt.service.LawFirmService;
import lcmt.service.LitigationService;
import lcmt.service.StagesService;
import lcmt.service.UserService;
import lcmt.service.UtilitiesService;

/*
 * Author: Harshad Padole
 * Date: 09/08/2016
 * Updated By:
 * Updated Date:
 * 
 * */

@Controller
@RequestMapping("/*")
public class LitigationController {

	@Autowired
	UtilitiesService utilitieservice;

	@Autowired
	EntityMappingService entityMappingService;

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
	UserDao userDao;

	// Method Created : Harshad Padole
	// Method Purpose : Add litigation type
	@RequestMapping(value = "/addLitigationType", method = RequestMethod.GET)
	public ModelAndView addLitigationType() {
		try {
			ModelAndView addLitigation = new ModelAndView("addLitigationType", "add_litigation", new LitigationType());
			// addLitigation.addObject("allLitiType",litigationService.AllLitiTypeJson());
			return addLitigation;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Save litigation type
	@RequestMapping(value = "/saveLitigationType", method = RequestMethod.POST)
	public String saveLitigationType(LitigationType litigation, HttpSession session) {
		try {
			litigation.setLiti_type_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			litigationService.persist(litigation);
			return "redirect:listLitigationType";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Check Litigation type exist or not
	@RequestMapping(value = "/isLitiNameExist", method = RequestMethod.POST)
	public @ResponseBody String isLitiNameExist(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int liti_id = Integer.parseInt(jsonObject.get("liti_id").toString());
			String liti_name = jsonObject.get("liti_name").toString();
			int result = litigationService.isLitiNameExist(liti_id, liti_name);
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : List of litigation type
	@RequestMapping(value = "/listLitigationType", method = RequestMethod.GET)
	public ModelAndView listLitigationType() {
		try {
			ModelAndView listLitigation = new ModelAndView("listLitigation_type", "list_litigation",
					litigationService.getAllLitiType());
			return listLitigation;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Edit litigation type
	@RequestMapping(value = "/editLitiType", method = RequestMethod.GET)
	public ModelAndView editLitigationType(int liti_id) {
		ModelAndView editLitiEdit = new ModelAndView("editLitigationtype", "edit_liti_type",
				litigationService.getLiti_typeById(liti_id));
		return editLitiEdit;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Update litigation type
	@RequestMapping(value = "/updateLitigation_type", method = RequestMethod.POST)
	public String updateLitigation_type(LitigationType litigation) {
		try {
			litigationService.updateLitigation_type(litigation);
			return "redirect:listLitigationType";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Delete litigation type
	@RequestMapping(value = "/deleteLitigationType", method = RequestMethod.POST)
	public @ResponseBody String deleteLitigationType(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int liti_id = Integer.parseInt(jsonobj.get("liti_id").toString());
			// System.out.println("inner Liti_id:" + liti_id);
			int deleteCount = litigationService.deleteLitigationType(liti_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Delete litigation type
	@RequestMapping(value = "/checkDependancyLitigationType", method = RequestMethod.POST)
	public @ResponseBody String checkDependancyLitigationType(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int liti_id = Integer.parseInt(jsonobj.get("liti_id").toString());
			String deleteCount = litigationService.checkDependancyLitigationType(liti_id);
			return deleteCount;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*---------------------------------------Code For Litigation-------------------------------------------------------------*/

	// Method Created : Mahesh Kharote
	// Method Purpose : Show add litigation page
	@RequestMapping(value = "/addLitigation", method = RequestMethod.GET)
	public ModelAndView addLitigation(HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("addLitigation", "litigation", new Litigation());
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("litigation_type_list", litigationService.getAllLitiType());
			modelAndView.addObject("external_counsel_list", externalCounselService.getAll());
			modelAndView.addObject("liti_code", internalLitigationService.getAll());
			modelAndView.addObject("user_legal_department", userService.getAll());
			// modelAndView.addObject("user_socndary_department",userService.getAll());
			modelAndView.addObject("listAdvocate", advocateService.getAll());
			modelAndView.addObject("allLawFirm", lawFirmService.getAllLawFirm());
			modelAndView.addObject("allCurrency", currencyService.getAll());
			modelAndView.addObject("AllCourt", courtService.getAll());
			modelAndView.addObject("allUsers", userService.getAllUser());
			// modelAndView.addObject("liti_refe_no",
			// litigationService.getAllLitigationClientId());

			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Mahesh Kharote
	// Method Purpose : Save new litigation
	@RequestMapping(value = "/listLitigation", method = RequestMethod.GET)
	public ModelAndView listLitigation(HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("listLitigation", "list_litigation",
					litigationService.getAllListLitigation(session));
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("litigation_type_list", litigationService.getAllLitiType());
			modelAndView.addObject("liti_code", internalLitigationService.getAll());
			modelAndView.addObject("allUser", userService.getAllUser());
			// modelAndView.addObject("UserList",
			// litigationService.getMatterHandleByUsers());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Mahesh Kharote
	// Method Purpose : Save new litigation
	@RequestMapping(value = "/saveLititgation", method = RequestMethod.POST)
	public String saveLititgation(Litigation litigation,
			@RequestParam(value = "Save", required = false, defaultValue = "d_save") String save,
			@RequestParam(value = "Draft", required = false, defaultValue = "d_draft") String draft,
			@RequestParam(name = "liti_req_id", required = false, defaultValue = "0") int id, HttpSession session,
			@RequestParam(name = "esc_internal_resource", required = false, defaultValue = "") String esc_internal_resource,
			@RequestParam(name = "esc_law_firm", required = false, defaultValue = "") String esc_law_firm,
			@RequestParam(name = "esc_appear_counsel", required = false, defaultValue = "") String esc_appear_counsel,
			@RequestParam(name = "esc_others", required = false, defaultValue = "") String esc_others,
			@RequestParam(name = "liti_req_type", required = false, defaultValue = "NA") String type,
			@RequestParam("litigation_doc") ArrayList<MultipartFile> litigation_doc
	/* @RequestParam("ldoc_document_type") String ldoc_document_type, */
	) {
		try {
			String status = "";
			if (save.equals("Save")) {
				status = "Pending";
			}
			if (draft.equals("Draft")) {
				status = "Draft";
			}
			litigationService.persistLitigation(litigation, status, id, session, esc_internal_resource, esc_law_firm,
					esc_appear_counsel, esc_others, type, litigation_doc);
			return "redirect:listLitigation";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// Method Created : Mahesh Kharote
	// Method Purpose : Show Litigation for Update
	// Method updated : Harshad Padole
	// Reason : Update Litigation details
	@RequestMapping(value = "/editLitigation", method = RequestMethod.GET)
	public ModelAndView editLitigation(int liti_id, HttpSession session) {
		try {
			Litigation litigation = litigationService.getLitigationByLitiId(liti_id);

			ModelAndView modelAndView = new ModelAndView("editLitigation", "editLitigation", litigation);
			// modelAndView.addObject("allOrganizations",
			// entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("litigation_type_list", litigationService.getAllLitiType());
			modelAndView.addObject("external_counsel_list", externalCounselService.getAll());
			int orga = litigation.getLiti_orga_id();
			int loca = litigation.getLiti_loca_id();
			int dept = litigation.getLiti_dept_id();
			/*
			 * modelAndView.addObject("user_legal_department",
			 * userService.getUsersByOrganizationLocationDepartment(orga, loca, dept));
			 * modelAndView.addObject("user_secondary_department",
			 * userService.getUsersByOrganizationLocationDepartment(orga, loca, dept));
			 * modelAndView.addObject("user_third_department",userService.
			 * getUsersByOrganizationLocationDepartment(orga, loca, dept));
			 * modelAndView.addObject("user_fourth_department",userService.
			 * getUsersByOrganizationLocationDepartment(orga, loca, dept));
			 */
			modelAndView.addObject("allLocations", entityMappingService.getLocationByUserId(session, orga));
			modelAndView.addObject("allDepartments", entityMappingService.getDeptByUserId(session, loca, orga));
			modelAndView.addObject("listAdvocate", advocateService.getAll());
			modelAndView.addObject("liti_code", internalLitigationService.getAll());
			modelAndView.addObject("allLawFirm", lawFirmService.getAllLawFirm());
			modelAndView.addObject("allCurrency", currencyService.getAll());
			modelAndView.addObject("AllCourt", courtService.getAll());
			modelAndView.addObject("previous_liti_no", litigation.getLiti_previous_liti_ref_no());
			modelAndView.addObject("criticality", litigation.getLiti_criticality());
			modelAndView.addObject("allUsers", userService.getAllUser());
			LitigationEscalationMailId litiMail = litigationService.getEscalationMailByLitiId(liti_id);

			if (litiMail != null) {
				if (!litiMail.getEsc_internal_resource().equals(""))
					modelAndView.addObject("internal_resource", litiMail.getEsc_internal_resource());
				else {
					modelAndView.addObject("internal_resource", "");
				}

				if (!litiMail.getEsc_law_firm().equals("")) {
					modelAndView.addObject("law_firm", litiMail.getEsc_law_firm());
				} else {
					modelAndView.addObject("law_firm", "");
				}
				if (!litiMail.getEsc_appear_counsel().equals("")) {
					modelAndView.addObject("counsel_appear", litiMail.getEsc_appear_counsel());
				} else {
					modelAndView.addObject("counsel_appear", "");
				}
				if (!litiMail.getEsc_others().equals("")) {
					modelAndView.addObject("others", litiMail.getEsc_others());
				} else {
					modelAndView.addObject("others", "");
				}
			}

			Map<Integer, String> acting_as = new HashMap<>();

			if (litigation.getLiti_against_by_id().equals("By")) {
				acting_as.put(0, "--Select--");
				acting_as.put(1, "Plaintiff");
				acting_as.put(2, "Applicant");
				acting_as.put(3, "Complainant");
				acting_as.put(4, "Appellant");
				acting_as.put(5, "Petitioner");

			} else {
				acting_as.put(0, "--Select--");
				acting_as.put(1, "Defendant");
				acting_as.put(2, "Opponent");
				acting_as.put(3, "Opposite_Party");
				acting_as.put(4, "Repondent");
			}
			modelAndView.addObject("acting_as", acting_as);
			modelAndView.addObject("status", litigation.getLiti_status());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Mahesh Kharote
	// Method Purpose : Get Details of Litigation
	@RequestMapping(value = "/litigationDetails", method = RequestMethod.GET)
	public ModelAndView litigationDetails(int liti_id) {
		try {
			LitigationDetails details = litigationService.getJoinedLitigationDetailsById(liti_id);

			ModelAndView modelAndView = new ModelAndView("litigationDetails", "litigation_details_by_id", details);
			// modelAndView.addObject("litigation_documents",
			// litigationService.getAllDocumentByLitiId(liti_id));
			modelAndView.addObject("litigation_completion", new Litigation());
			modelAndView.addObject("allCourt", courtService.getAll());
			modelAndView.addObject("CounselFeesDetails", litigationService.getCounselFeesByLitiId(liti_id));
			modelAndView.addObject("AdvocateFeesDetails", litigationService.getAdvocateFeesDetailsByLitiId(liti_id));
			if (!details.getLiti_previous_liti_ref_no().equals("NA"))
				modelAndView.addObject("previous_liti_client_id",
						litigationService.getpreviouslitiId(details.getLiti_previous_liti_ref_no()));
			modelAndView.addObject("allDocuments", litigationService.getAllDocumentByLitiId(liti_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Mahesh Kharote
	// Method Purpose : Save Document to drive and add entry to database
	@RequestMapping(value = "/addLitigationDocument", method = RequestMethod.POST)
	public @ResponseBody int addLitigationDocument(@RequestParam("litigationDocument") MultipartFile litigationDocument,
			@RequestParam("ldoc_document_type") String ldoc_document_type,
			@RequestParam("ldoc_liti_id") int ldoc_liti_id) {
		try {
			litigationService.addLitigationDocument(ldoc_liti_id, ldoc_document_type, litigationDocument);
			return ldoc_liti_id;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// Method Created : Mahesh Kharote
	// Method Purpose : Get all documents for Ajax Call
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getAllDocumentForAjax", method = RequestMethod.POST)
	public @ResponseBody String getAllDocumentForAjax(@RequestBody String jsonString) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			List<LitigationDocuments> litigationDocuments = litigationService
					.getAllDocumentByLitiId(Integer.parseInt(jsonObject.get("ldoc_liti_id").toString()));
			if (!litigationDocuments.isEmpty()) {
				JSONArray documentsDataToSend = new JSONArray();
				Iterator<LitigationDocuments> itr = litigationDocuments.iterator();
				while (itr.hasNext()) {
					LitigationDocuments litigationDocumentss = (LitigationDocuments) itr.next();
					JSONObject objToAddToArray = new JSONObject();
					objToAddToArray.put("ldoc_id", litigationDocumentss.getLdoc_id());
					objToAddToArray.put("ldoc_original_file_name", litigationDocumentss.getLdoc_original_file_name());
					objToAddToArray.put("ldoc_generated_file_name", litigationDocumentss.getLdoc_generated_file_name());
					objToAddToArray.put("ldoc_document_type", litigationDocumentss.getLdoc_document_type());

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

	// Method Created : Mahesh Kharote
	// Method Purpose : Delete document by ldoc_id
	@RequestMapping(value = "/deleteLitigationDocument", method = RequestMethod.POST)
	public @ResponseBody String deleteLitigationDocument(@RequestBody String jsonString) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			litigationService.deleteLitigationDocumentById(Integer.parseInt(jsonObject.get("ldoc_id").toString()));
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed";
		}
	}

	// Method Created : Mahesh Kharote
	// Method Purpose : Delete document by ldoc_id
	@RequestMapping(value = "/downloadLitigationDocument", method = RequestMethod.GET)
	public void downloadLitigationDocument(int ldoc_id, HttpServletResponse response) {
		try {
			litigationService.downloadLitigationDocument(ldoc_id, response);
		} catch (Exception e) {
			e.printStackTrace();
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
	// Method Purpose : show add hearing stage page
	@RequestMapping(value = "/addHearingStage", method = RequestMethod.GET)
	public ModelAndView addHearingStage(int liti_id) {
		try {
			ModelAndView modelAndView = new ModelAndView("addHearingStage", "addHearingStage", new HearingStage());
			Litigation litigation = litigationService.getLitigationByLitiId(liti_id);
			int orga = litigation.getLiti_orga_id();
			int loca = litigation.getLiti_loca_id();
			int dept = litigation.getLiti_dept_id();
			modelAndView.addObject("user_list", userService.getUsersByOrganizationLocationDepartment(orga, loca, dept));
			
			/*
			 * modelAndView.addObject("user_lists",
			 * userService.getUsersByOrganizationLocationDepartment(orga, loca, dept));
			 */
			 
			modelAndView.addObject("listStages", stagesService.getAll());
			modelAndView.addObject("allLawFirm", lawFirmService.getAllLawFirm());
			/// modelAndView.addObject("external_counsel_list",
			/// externalCounselService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Save hearing stage
	@RequestMapping(value = "/saveHearingStage", method = RequestMethod.POST)
	public String saveHearingStage(HearingStage hearingStage,
			@RequestParam("stage_document") ArrayList<MultipartFile> hearing_doc,
			@RequestParam(name = "counsel_list", required = false) ArrayList<String> ttrn_counsel_list,
			@RequestParam(value = "Save", required = false, defaultValue = "d_save") String save,
			@RequestParam(value = "Draft", required = false, defaultValue = "d_draft") String draft) {
		try {
			String status = "";
			if (save.equals("Save")) {
				status = "Pending";
			}
			if (draft.equals("Draft")) {
				status = "Draft";
			}
			litigationService.saveHearingStage(hearingStage, hearing_doc, ttrn_counsel_list, status);
			return "redirect:litigationDetails?liti_id=" + hearingStage.getTtrn_litigation_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get Hearing Details By Id
	@RequestMapping(value = "/getHearingStagesById", method = RequestMethod.POST)
	public @ResponseBody String getHearingStagesById(@RequestParam("liti_id") Integer liti_id, HttpSession session) {
		try {
			String search = litigationService.getHearingDetailsByLitiId(liti_id, session);
			return search;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Created : Harshad Padole
	// Method Purpose : show add hearing stage page
	@RequestMapping(value = "/editHearingStage", method = RequestMethod.GET)
	public ModelAndView editHearingStage(int hear_stage_id) {
		
		try {
			HearingStage hearingStage = litigationService.getHearingDetailsByStageId(hear_stage_id);
			ModelAndView modelAndView = new ModelAndView("editHearingStage", "editHearingStage", hearingStage);
			Litigation litigation = litigationService.getLitigationByLitiId(hearingStage.getTtrn_litigation_id());
			int orga = litigation.getLiti_orga_id();
			int loca = litigation.getLiti_loca_id();
			int dept = litigation.getLiti_dept_id();
			modelAndView.addObject("user_list", userService.getUsersByOrganizationLocationDepartment(orga, loca, dept));
			//modelAndView.addObject("user_lists",userService.getUsersByOrganizationLocationDepartment(orga, loca, dept));
			modelAndView.addObject("hearing_stage_doc", litigationService.getHearingStageDocById(hear_stage_id));
			modelAndView.addObject("listStages", stagesService.getAll());
			modelAndView.addObject("allLawFirm", lawFirmService.getAllLawFirm());
			modelAndView.addObject("external_counsel_list", externalCounselService.getAll());
			modelAndView.addObject("counsel_list", litigationService.getHearingStageCounselList(hear_stage_id));
			modelAndView.addObject("status", hearingStage.getTtrn_stage_status());
			modelAndView.addObject("liti_id", hearingStage.getTtrn_litigation_id());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Save hearing stage
	@RequestMapping(value = "/updateHearingStage", method = RequestMethod.POST)
	public String updateHearingStage(HearingStage hearingStage,
			@RequestParam("stage_document") ArrayList<MultipartFile> hearing_doc,
			@RequestParam(name = "counsel_list", required = false) ArrayList<String> ttrn_counsel_list,
			@RequestParam(value = "Update", required = false, defaultValue = "d_save") String update,
			@RequestParam(value = "Draft", required = false, defaultValue = "d_draft") String draft) {
		try {
			String status = "";
			if (update.equals("Update")) {
				status = "Pending";
			}
			if (draft.equals("Draft")) {
				status = "Draft";
			}
			litigationService.updateHearingStage(hearingStage, hearing_doc, ttrn_counsel_list, status);
			return "redirect:litigationDetails?liti_id=" + hearingStage.getTtrn_litigation_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Update litigation
	@RequestMapping(value = "/updateLitigation", method = RequestMethod.POST)
	public String updateLitigation(Litigation litigation,
			@RequestParam(value = "Update", required = false, defaultValue = "d_save") String save,
			@RequestParam(value = "Draft", required = false, defaultValue = "d_draft") String draft,
			HttpSession session,
			@RequestParam(name = "esc_internal_resource", required = false, defaultValue = "") String esc_internal_resource,
			@RequestParam(name = "esc_law_firm", required = false, defaultValue = "") String esc_law_firm,
			@RequestParam(name = "esc_appear_counsel", required = false, defaultValue = "") String esc_appear_counsel,
			@RequestParam(name = "esc_others", required = false, defaultValue = "") String esc_others) {
		try {
			String status = "";
			if (save.equals("Update")) {
				status = "Pending";
			}
			if (draft.equals("Draft")) {
				status = "Draft";
			}
			litigationService.updateLitigation(litigation, status, session, esc_internal_resource, esc_law_firm,
					esc_appear_counsel, esc_others);
			return "redirect:listLitigation";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Harshad Padole
	// Method Purpose : Search litigation
	@RequestMapping(value = "/searchLitigation", method = RequestMethod.POST)
	public @ResponseBody String searchLitigation(@RequestBody String json, HttpSession session) {
		try {
			return litigationService.searchLitigation(json, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;

	}
	
	
	

	// Method Created : Harshad Padole
	// Method Purpose : Download hearing document
	@RequestMapping(value = "/downloadHearingDocument", method = RequestMethod.GET)
	public void downloadHearingDocument(int hearing_doc_id, HttpServletResponse response) {
		try {
			litigationService.downloadHearingDocuments(hearing_doc_id, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Harshad Padole
	// Method Purpose : Save litigation completion details
	@RequestMapping(value = "/saveLitiCompletion", method = RequestMethod.POST)
	public String saveLitiCompletion(Litigation litigation,
			@RequestParam("liti_completion_doc") ArrayList<MultipartFile> liti_completion_doc) {
		try {
			// System.out.println(":hii");
			litigationService.saveLitigationCompletion(litigation, liti_completion_doc);
			return "redirect:litigationDetails?liti_id=" + litigation.getLiti_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Save litigation completion details
	@RequestMapping(value = "/saveCounselFees", method = RequestMethod.POST)
	public String saveCounselFees(LitigationCounselFees fees,
			@RequestParam("counsel_agreed_doc") ArrayList<MultipartFile> counsel_agreed_doc) {
		try {
			litigationService.saveCounselFees(fees, counsel_agreed_doc);
			return "redirect:litigationDetails?liti_id=" + fees.getLcou_liti_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : show add counsel page
	@RequestMapping(value = "/addCounselFees", method = RequestMethod.GET)
	public ModelAndView addCounselFees(int liti_id) {
		try {
			ModelAndView modelAndView = new ModelAndView("addCounselFees", "addCounselFees",
					new LitigationCounselFees());
			modelAndView.addObject("external_counsel_list", externalCounselService.getAll());
			modelAndView.addObject("allLawFirm", lawFirmService.getAllLawFirm());
			modelAndView.addObject("litigationDetails", litigationService.getJoinedLitigationDetailsById(liti_id));
			modelAndView.addObject("allCurrency", currencyService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : show add counsel page
	@RequestMapping(value = "/addAdvocateFees", method = RequestMethod.GET)
	public ModelAndView addAdvocateFees(int liti_id) {
		try {
			ModelAndView modelAndView = new ModelAndView("addAdvocateFees", "addAdvocateFees",
					new LitigationAdvocateFees());
			// modelAndView.addObject("external_counsel_list",
			// externalCounselService.getAll());
			modelAndView.addObject("allLawFirm", lawFirmService.getAllLawFirm());
			modelAndView.addObject("litigationDetails", litigationService.getJoinedLitigationDetailsById(liti_id));
			modelAndView.addObject("allCurrency", currencyService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Save litigation completion details
	@RequestMapping(value = "/saveAdvocateFees", method = RequestMethod.POST)
	public String saveAdvocateFees(LitigationAdvocateFees fees,
			@RequestParam("advocate_agreed_doc") ArrayList<MultipartFile> advocate_agreed_doc) {
		try {
			litigationService.saveAdvocateFees(fees, advocate_agreed_doc);
			return "redirect:litigationDetails?liti_id=" + fees.getLadv_litigation_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : edit counsel fees details
	@RequestMapping(value = "/editCounselFees", method = RequestMethod.GET)
	public ModelAndView editCounselFees(int lcou_id) {
		try {
			LitigationCounselFees counselFees = litigationService.getCounselFeesDetailsById(lcou_id);
			ModelAndView modelAndView = new ModelAndView("editCounselFees", "editCounselFees", counselFees);
			modelAndView.addObject("litigationDetails",
					litigationService.getJoinedLitigationDetailsById(counselFees.getLcou_liti_id()));
			modelAndView.addObject("CounselFeesDoc", litigationService.getLitiFeesDocById(lcou_id));
			modelAndView.addObject("allCurrency", currencyService.getAll());
			modelAndView.addObject("allLawFirm", lawFirmService.getAllLawFirm());
			modelAndView.addObject("external_counsel_list", externalCounselService.getAll());
			modelAndView.addObject("liti_id", counselFees.getLcou_liti_id());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Update counsel fees details
	@RequestMapping(value = "/updateCounselFees", method = RequestMethod.POST)
	public String updateCounselFees(LitigationCounselFees counselFees,
			@RequestParam("counsel_agreed_doc") ArrayList<MultipartFile> counsel_agreed_doc) {
		try {
			litigationService.updateCounselFees(counselFees, counsel_agreed_doc);
			return "redirect:litigationDetails?liti_id=" + counselFees.getLcou_liti_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : edit advocate fees details
	@RequestMapping(value = "/editAdvocateFees", method = RequestMethod.GET)
	public ModelAndView editAdvocateFees(int ladv_id) {
		try {
			LitigationAdvocateFees advocateFees = litigationService.getAdvocateFeesDetailsById(ladv_id);
			ModelAndView modelAndView = new ModelAndView("editAdvocateFees", "editAdvocateFees", advocateFees);
			modelAndView.addObject("litigationDetails",
					litigationService.getJoinedLitigationDetailsById(advocateFees.getLadv_litigation_id()));
			modelAndView.addObject("allCurrency", currencyService.getAll());
			modelAndView.addObject("AdvocateFeesDoc", litigationService.getAdvocateFeesDocById(ladv_id));
			modelAndView.addObject("allLawFirm", lawFirmService.getAllLawFirm());
			modelAndView.addObject("allAdvocate", advocateService.getAll());
			modelAndView.addObject("liti_id", advocateFees.getLadv_litigation_id());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Update advocate fees details
	@RequestMapping(value = "/updateAdvocateFees", method = RequestMethod.POST)
	public String updateAdvocateFees(LitigationAdvocateFees advocateFees,
			@RequestParam("advocate_agreed_doc") ArrayList<MultipartFile> advocate_agreed_doc) {
		try {
			litigationService.updateAdvocateFees(advocateFees, advocate_agreed_doc);
			return "redirect:litigationDetails?liti_id=" + advocateFees.getLadv_litigation_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : list paid counsel fees
	@RequestMapping(value = "/listPaidCounselFees", method = RequestMethod.GET)
	public ModelAndView listPaidCounselFees(int counsel_fees_id) {
		try {
			litigationService.getPaidFeesDetailsByConFeesId(counsel_fees_id);
			ModelAndView modelAndView = new ModelAndView("listCounselPaidFees", "counsel_fees_id", counsel_fees_id);
			modelAndView.addObject("listPaidFees", litigationService.getPaidFeesDetailsByConFeesId(counsel_fees_id));
			// modelAndView.addObject("liti_id", );
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : pay counsel fees
	@RequestMapping(value = "/payCounselFees", method = RequestMethod.GET)
	public ModelAndView payCounselFees(int counsel_fees_id) {
		try {
			ModelAndView modelAndView = new ModelAndView("payCounselFees", "pay_counsel", new CounselPaidFees());
			modelAndView.addObject("counsel_fees_id", counsel_fees_id);
			modelAndView.addObject("allCurrency", currencyService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Save paid counsel fees
	@RequestMapping(value = "/savePaidCounselFees", method = RequestMethod.POST)
	public String savePaidCounselFees(CounselPaidFees fees,
			@RequestParam("counsel_paid_doc") ArrayList<MultipartFile> counsel_paid_doc) {
		try {

			litigationService.savePaidCounselFees(fees, counsel_paid_doc);
			return "redirect:listPaidCounselFees?counsel_fees_id=" + fees.getCpaid_counsel_fees_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : list paid advocate fees
	@RequestMapping(value = "/listPaidAdvocateFees", method = RequestMethod.GET)
	public ModelAndView listPaidAdvocateFees(int advocate_fees_id) {
		try {
			ModelAndView modelAndView = new ModelAndView("listAdvocatePaidFees", "advocate_fees_id", advocate_fees_id);
			modelAndView.addObject("listPaidFees",
					litigationService.getPaidFeesDetailsByAdvocateFeesId(advocate_fees_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : pay advocate fees
	@RequestMapping(value = "/payAdvocateFees", method = RequestMethod.GET)
	public ModelAndView payAdvocateFees(int advocate_fees_id) {
		try {
			ModelAndView modelAndView = new ModelAndView("payAdvocateFees", "pay_advocate", new AdvocatePaidFees());
			modelAndView.addObject("advocate_fees_id", advocate_fees_id);
			modelAndView.addObject("allCurrency", currencyService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Save paid advocate fees
	@RequestMapping(value = "/savePaidAdvocateFees", method = RequestMethod.POST)
	public String savePaidAdvocateFees(AdvocatePaidFees fees,
			@RequestParam("advocate_paid_doc") ArrayList<MultipartFile> advocate_paid_doc) {
		try {
			litigationService.savePaidAdvocateFees(fees, advocate_paid_doc);
			return "redirect:listPaidAdvocateFees?advocate_fees_id=" + fees.getApaid_advocate_fees_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get Advocate paid fees by id(primary key)
	@RequestMapping(value = "/editAdvocatePaidFees", method = RequestMethod.GET)
	public ModelAndView editAdvocatePaidFees(int apaid_id) {
		try {
			ModelAndView modelAndView = new ModelAndView("editAdvocatePaidFees", "editPay_advocate",
					litigationService.getAdvocatePaidFeesById(apaid_id));
			modelAndView.addObject("allCurrency", currencyService.getAll());
			modelAndView.addObject("AdvocatePaidFees", litigationService.getAdvocatePaidDocById(apaid_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Update Paid Advocate fees
	@RequestMapping(value = "/updatePaidAdvocateFees", method = RequestMethod.POST)
	public String updatePaidAdvocateFees(AdvocatePaidFees advocatePaidFees,
			@RequestParam("advocate_paid_doc") ArrayList<MultipartFile> advocate_paid_doc) {
		try {
			litigationService.updatePaidAdvocateFees(advocatePaidFees, advocate_paid_doc);
			return "redirect:listPaidAdvocateFees?advocate_fees_id=" + advocatePaidFees.getApaid_advocate_fees_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get counsel paid fees by id(primary key)
	@RequestMapping(value = "/editCounselPaidFees", method = RequestMethod.GET)
	public ModelAndView editCounselPaidFees(int cpaid_id) {
		try {
			ModelAndView modelAndView = new ModelAndView("editCounselPaidFees", "editPay_counsel",
					litigationService.getCounselPaidFeesById(cpaid_id));
			modelAndView.addObject("allCurrency", currencyService.getAll());
			modelAndView.addObject("CounselPaidFees", litigationService.getCounselPaidDocById(cpaid_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Update Paid Advocate fees
	@RequestMapping(value = "/updatePaidCounselFees", method = RequestMethod.POST)
	public String updatePaidCounselFees(CounselPaidFees paidFees,
			@RequestParam("counsel_paid_doc") ArrayList<MultipartFile> counsel_paid_doc) {
		try {
			litigationService.updatePaidCounselFees(paidFees, counsel_paid_doc);
			return "redirect:listPaidCounselFees?counsel_fees_id=" + paidFees.getCpaid_counsel_fees_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Download document by fdoc_id
	@RequestMapping(value = "/downloadLitigationFeesDocument", method = RequestMethod.GET)
	public void downloadLitigationFeesDocument(int fdoc_id, HttpServletResponse response) {
		try {
			litigationService.downloadLitigationFeesDocument(fdoc_id, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get hearing stage logs (primary key)
	@RequestMapping(value = "/getHearingStageLogs", method = RequestMethod.POST)
	public @ResponseBody String getHearingStageLogs(@RequestBody String json, HttpSession session)
			throws ParseException {
		try {
			return litigationService.getHearingStageLogs(json, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : delete litigation
	@RequestMapping(value = "/deleteLitigation", method = RequestMethod.POST)
	public @ResponseBody String deleteLitigation(@RequestBody String json) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
			int liti_id = Integer.parseInt(jsonObject.get("liti_id").toString());
			int deleteCount = litigationService.deleteLitigation(liti_id);
			return String.valueOf(deleteCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : check whether paid amount greater than agreed amount or
	// not
	@RequestMapping(value = "/isAmountGreaterThanAgreedFees", method = RequestMethod.POST)
	public @ResponseBody String isAmountGreaterThanAgreedFees(@RequestBody String json) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
			String amount = jsonObject.get("cpaid_fees_amount").toString();
			float amt = Float.valueOf(amount);
			int cpaid_fees_amount = Math.round(amt);
			int cpaid_counsel_fees_id = Integer.parseInt(jsonObject.get("cpaid_counsel_fees_id").toString());
			int count = litigationService.isCounselPaidFeesGreaterThanAgreedFees(cpaid_fees_amount,
					cpaid_counsel_fees_id);
			return String.valueOf(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : check whether paid amount greater than agreed amount or
	// not
	@RequestMapping(value = "/isAdvocateAmountGreaterThanAgreedFees", method = RequestMethod.POST)
	public @ResponseBody String isAdvocateAmountGreaterThanAgreedFees(@RequestBody String json) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
			String amount = jsonObject.get("apaid_fees_amount").toString();
			float amt = Float.valueOf(amount);
			int apaid_fees_amount = Math.round(amt);
			// = Integer.parseInt();
			int apaid_advocate_fees_id = Integer.parseInt(jsonObject.get("apaid_advocate_fees_id").toString());
			int count = litigationService.isAdvocatePaidFeesGreaterThanAgreedFees(apaid_fees_amount,
					apaid_advocate_fees_id);
			return String.valueOf(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : delete Hearing Stage
	@RequestMapping(value = "/deleteHearingStage", method = RequestMethod.POST)
	public @ResponseBody String deleteHearingStage(@RequestBody String json) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
			int hear_stage_id = Integer.parseInt(jsonObject.get("hear_stage_id").toString());
			int deleteCount = litigationService.deleteHearingStage(hear_stage_id);
			return String.valueOf(deleteCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : delete Hearing Stage
	@RequestMapping(value = "/deleteHearingStageDoc", method = RequestMethod.POST)
	public @ResponseBody String deleteHearingStageDoc(@RequestBody String json) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
			int doc_id = Integer.parseInt(jsonObject.get("doc_id").toString());
			int deleteCount = litigationService.deleteHearingStageDoc(doc_id);
			return String.valueOf(deleteCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : delete Hearing Stage
	@RequestMapping(value = "/deleteLitiFeesDoc", method = RequestMethod.POST)
	public @ResponseBody String deleteLitiFeesDoc(@RequestBody String json) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
			int fdoc_id = Integer.parseInt(jsonObject.get("fdoc_id").toString());
			int deleteCount = litigationService.deleteLitiFeesDoc(fdoc_id);
			return String.valueOf(deleteCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : delete counsel from transactional table
	@RequestMapping(value = "/deleteHearingCounselById", method = RequestMethod.POST)
	public @ResponseBody String deleteHearingCounselById(@RequestBody String json, HttpSession session) {
		try {
			int res = litigationService.deleteHearingCounselById(json);
			return String.valueOf(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created : Harshad Padole
	// Method Purpose : get all litigation type as json
	@RequestMapping(value = "/getAllLitiJson", method = RequestMethod.POST)
	public @ResponseBody String getAllLitiJson(HttpSession session) {
		try {
			return litigationService.AllLitiTypeJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/getAllClientId", method = RequestMethod.GET)
	public @ResponseBody String getAllClientId(HttpSession session) {
		try {
			return litigationService.getAllLitigationClientId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/addHearingStageOnHearing", method = RequestMethod.GET)
	public ModelAndView addHearingStageOnHearing(int liti_id, int hear_id) {
		try {
			ModelAndView modelAndView = new ModelAndView("addHearingStageOnHearing", "addHearingStageOnHearing",
					new HearingStage());
			HearingStageOnHearing hearingStatus = litigationService.getHearingOnHearingDetailsById(hear_id);
			// System.out.println("hearing id in controller:"+hear_id+"status
			// is"+hearingStatus.getTrn_hearing_status());
			modelAndView.addObject("hearing_status", hearingStatus.getTrn_hearing_status());
			// modelAndView.addObject("listStages", stagesService.getAll());
			// modelAndView.addObject("allLawFirm", lawFirmService.getAllLawFirm());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/saveHearingStageOnHearing", method = RequestMethod.POST)
	public String saveHearingStageOnHearing(HearingStage hearingStage,
			@RequestParam("stage_document") ArrayList<MultipartFile> hearing_doc,
			@RequestParam(value = "Save", required = false, defaultValue = "d_save") String save,
			@RequestParam(name = "hearing_id", required = false, defaultValue = "0") String hearing_id) {
		try {
			String status = "";
			if (save.equals("Save")) {
				status = "Waiting for Approval";
			}
			litigationService.saveHearingStageOnHearing(hearingStage, hearing_doc, status, hearing_id);
			return "redirect:addHearingStageOnHearing?liti_id=" + hearingStage.getTtrn_litigation_id() + "&hear_id="
					+ hearing_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/addHearingByApprove", method = RequestMethod.GET)
	public ModelAndView addHearingByApprove(int id) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

			HearingStage hearingStage = litigationService.getHearingDetailsByStageId(id);
			ModelAndView modelAndView = new ModelAndView("addHearingByApprove", "addHearingByApprove", hearingStage);
			Litigation litigation = litigationService.getLitigationByLitiId(hearingStage.getTtrn_litigation_id());
			int orga = litigation.getLiti_orga_id();
			int loca = litigation.getLiti_loca_id();
			int dept = litigation.getLiti_dept_id();
			modelAndView.addObject("user_list", userService.getUsersByOrganizationLocationDepartment(orga, loca, dept));
			modelAndView.addObject("user_lists",
					userService.getUsersByOrganizationLocationDepartment(orga, loca, dept));
			modelAndView.addObject("hearing_stage_doc", litigationService.getHearingStageDocById(id));
			modelAndView.addObject("listStages", stagesService.getAll());
			modelAndView.addObject("allLawFirm", lawFirmService.getAllLawFirm());
			modelAndView.addObject("external_counsel_list", externalCounselService.getAll());
			modelAndView.addObject("counsel_list", litigationService.getHearingStageCounselList(id));
			modelAndView.addObject("status", hearingStage.getTtrn_stage_status());
			modelAndView.addObject("hearing_date", hearingStage.getTtrn_next_hearing_date());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(hearingStage.getTtrn_next_hearing_date());
			calendar.add(Calendar.DATE, -2);
			Date yesterday = calendar.getTime();
			modelAndView.addObject("threeDayBefore", sdfOut.format(yesterday));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/*---------------------------------------Code For Litigation Ends Here---------------------------------------------------*/
	/*-------------------------------------------Start ACM Report-----------------------------------------------------------------------------*/

	  
// Method Created : Akshay Patkar
// Method Purpose : Export ACM Report
		@RequestMapping(value = "/ExportList", method = RequestMethod.POST)
		public @ResponseBody String ExportList(@RequestBody String json,HttpSession session, HttpServletRequest request)
		{
			try {
			
				String result =litigationService.acmLitigation(json,session);
			return result;
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		
		@RequestMapping(value = "/downloadACMReport" , method = RequestMethod.GET)
		public @ResponseBody void ExportList1(HttpServletResponse response){
			
			try {
				
			 litigationService.downloadACMReport(response);
		
		
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		/*------------------------------------------------------------End ACM------------------------------------------------------*/

	}
