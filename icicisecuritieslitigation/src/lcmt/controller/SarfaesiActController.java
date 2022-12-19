package lcmt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import lcmt.domain.ExecuteActionItem;
import lcmt.domain.ExecutedContractReference;
import lcmt.domain.LitigationDocuments;
import lcmt.domain.SarfaesiAct;
import lcmt.domain.SarfaesiActDocument;
import lcmt.domain.SarfaesiActRef;
import lcmt.domain.SarfaesiAction;
import lcmt.service.ARCnameService;
import lcmt.service.ArcManagerService;
import lcmt.service.EntityMappingService;
import lcmt.service.SarfaesiActService;
import lcmt.service.UserService;


@Controller
@RequestMapping(value="/*")
public class SarfaesiActController {
	@Autowired
	EntityMappingService entityMappingService;
	@Autowired
	UserService userService;
	@Autowired
	SarfaesiActService sarfaesiActService;
	@Autowired
	ArcManagerService arcManagerService;
	@Autowired
	ARCnameService arcNameService;

	//Method Created: Pranjali Kawale
	// Method Purpose: list Sarfaesi Act
	@RequestMapping(value = "/listSarfaesiAct", method = RequestMethod.GET)
	public ModelAndView sarfaesiAct(HttpSession session) {
		try {

			ModelAndView modelAndView = new ModelAndView("listSarfaesiAct", "listSarfaesiAct",
					sarfaesiActService.getAll(session));
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("sarf_loan_no", sarfaesiActService.getLoanNumber());
			modelAndView.addObject("sarf_borrower", sarfaesiActService.getBorrower());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Pranjali Kawale
	// Method Purpose: Add Sarfaesi Act
	@RequestMapping(value = "/addSarfaesiAct", method = RequestMethod.GET)
	public ModelAndView addSarfaesiAct(HttpSession session) {

		try {
			// System.out.println("!!!!!!!");
			ModelAndView modelAndView = new ModelAndView("addSarfaesiAct", "addSarfaesiAct", new SarfaesiAct());
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("userSarf", userService.getAll());
			modelAndView.addObject("allARCname", arcNameService.getAllArcName());
			modelAndView.addObject("allArcManager", arcManagerService.getAllArcManager());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	// Method Created: Pranjali Kawale
	// Method Purpose: Save Sarfaesi Act
	@RequestMapping(value = "/saveSarfaesiAct", method = RequestMethod.POST)
	public String saveSarfaesiAct(SarfaesiAct sarfaesiAct,
			@RequestParam("SarfDocument") ArrayList<MultipartFile> SarfDocument, HttpSession session) {
		try {
			sarfaesiActService.persist(sarfaesiAct, SarfDocument, session);
			return "redirect:listSarfaesiAct";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Pranjali Kawale
	// Method Purpose : Search Sarfaesi Act
	@RequestMapping(value = "/searchSarfaesiAct", method = RequestMethod.POST)
	public @ResponseBody String searchTaskAssignment(@RequestBody String jsonString, HttpSession session)
			throws ParseException {
		try {
			return sarfaesiActService.searchSarfaesiAct(jsonString, session);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString;
	}

	// Method Created : Pranjali Kawale
	// Method Purpose : Edit Sarfaesi Act
	@RequestMapping(value = "/editSarfaesiAct", method = RequestMethod.GET)
	public ModelAndView editSarfaesiAct(int sarf_act_id, HttpSession session) {
		try {
			SarfaesiAct sarfaesiAct = sarfaesiActService.getSarfaesiActById(sarf_act_id);
			ModelAndView modelAndView = new ModelAndView("editSarfaesiAct", "editSarfaesiAct", sarfaesiAct);
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			int orga = sarfaesiAct.getSarf_orga_id();
			int loca = sarfaesiAct.getSarf_loca_id();

			modelAndView.addObject("allLocations", entityMappingService.getLocationByUserId(session, orga));
			modelAndView.addObject("allFunctions", entityMappingService.getDeptByUserId(session, loca, orga));
			modelAndView.addObject("userSarf", userService.getAll());
			modelAndView.addObject("allARCname", arcNameService.getAllArcName());
			modelAndView.addObject("allmanager", arcManagerService.getAllArcManager());
			// modelAndView.addObject("allmanager",
			// sarfaesiActService.getArcManagerByARCnameId(sarf_act_id));
			return modelAndView;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	// Method Created : Pranjali Kawale
	// Method Purpose : Update Sarfaesi Act
	@RequestMapping(value = "/updateSarfaesiAct", method = RequestMethod.POST)
	public String updateSarfaesiAct(SarfaesiAct sarfaesiAct,
			@RequestParam("sarfaesiAct_documents") ArrayList<MultipartFile> sarf_act_documents, HttpSession session) {
		try {
			// System.out.println(sarf_act_documents.size());
			sarfaesiActService.updateSarfaesiAct(sarfaesiAct, sarf_act_documents, session);
			return "redirect:listSarfaesiAct";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Pranjali Kawale
	// Method purpose : Sarfaesi Act Details
	@RequestMapping(value = "/sarfaesiActDetails", method = RequestMethod.GET)
	public ModelAndView sarfaesiActDetails(int sarf_act_id, HttpSession session) {
		try {
			SarfaesiActRef ref = sarfaesiActService.getJoinedSarfaesiActDetail(sarf_act_id);

			ModelAndView modelAndView = new ModelAndView("sarfaesiActDetails", "sarfaesiActDetails", ref);
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			modelAndView.addObject("SarfDocument", sarfaesiActService.getALLSarfaesiActDocByid(sarf_act_id));
			modelAndView.addObject("sarfaesiActActionItem",
					sarfaesiActService.getAllSarfaesiActActionItem(sarf_act_id));
			// modelAndView.addObject("allmanager",
			// sarfaesiActService.getArcManagerByARCnameId(sarf_act_id));
			// modelAndView.addObject("allmanager",
			// arcManagerService.getAllArcManager());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// Method Created : Pranjali Kawale
	// Method Purpose : Delete Sarfaesi Act
	@RequestMapping(value = "/deleteSarfaesiAct", method = RequestMethod.POST)
	public @ResponseBody String deleteSarfaesiAct(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int sarf_act_id = Integer.parseInt(jsonobj.get("sarf_act_id").toString());
			int deleteCount = sarfaesiActService.deleteSarfaesiAct(sarf_act_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Pranjali Kawale
	// Method purpose : For binding date
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	// Method Created : Pranjali Kawale
	// Method purpose : Load add Sarfaesi Act action item page
	@RequestMapping(value = "/addSarfaesiAction", method = RequestMethod.GET)
	public ModelAndView addActionItem(int sarf_act_id, HttpSession session) {
		try {

			ModelAndView modelAndView = new ModelAndView("addSarfaesiAction", "addSarfaesiAction",
					new SarfaesiAction());
			/*modelAndView.addObject("sarf_action_NotiIssue_date", sarfaesiActService.getSarfaesiNoticedateByid(sarf_act_id));
			System.out.println(sarfaesiActService.getSarfaesiNoticedateByid(sarf_act_id));*/
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Pranjali Kawale
	// Method Purpose : Save Sarfaesi Act Action

	@RequestMapping(value = "/saveSarfaesiAction", method = RequestMethod.POST)
	public String saveSarfaesiAction(SarfaesiAction sarfaesiAction, HttpSession session) {
		try {
			// System.out.println("!!!!!!");
			sarfaesiActService.saveSarfaesiAction(sarfaesiAction, session);
			return "redirect:sarfaesiActDetails?sarf_act_id=" + sarfaesiAction.getAction_sarf_act_id();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Pranjali Kawale
	// Method Purpose : Delete Sarfaesi Act Action
	@RequestMapping(value = "/deleteSarfaesiActAction", method = RequestMethod.POST)
	public @ResponseBody String deleteSarfaesiActAction(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int sarf_action_id = Integer.parseInt(jsonobj.get("sarf_action_id").toString());
			int deleteCount = sarfaesiActService.deleteSarfaesiActAction(sarf_action_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Pranjali Kawale
	// Method Purpose : Edit Sarfaesi Act
	@RequestMapping(value = "/editSarfaesiActAction", method = RequestMethod.GET)
	public ModelAndView editSarfaesiActAction(int sarf_action_id, HttpSession session) {
		try {
			SarfaesiAction sarfaesiAction = sarfaesiActService.getSarfaesiActActionById(sarf_action_id);
			ModelAndView modelAndView = new ModelAndView("editSarfaesiActAction", "editSarfaesiActAction",
					sarfaesiAction);
			modelAndView.addObject("sarf_act_id", sarfaesiAction.getAction_sarf_act_id());
			return modelAndView;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	// Method Created : Pranjali Kawale
	// Method Purpose : Update Sarfaesi Act Action
	@RequestMapping(value = "/updateSarfaesiActAction", method = RequestMethod.POST)
	public String updateActionItem(SarfaesiAction sarfaesiAction, HttpSession session) {
		try {
			// System.out.println(sarfaesiAction.getAction_sarf_act_id());
			sarfaesiActService.updateSarfaesiActAction(sarfaesiAction, session);
			return "redirect:sarfaesiActDetails?sarf_act_id=" + sarfaesiAction.getAction_sarf_act_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Pranjali Kawale
	// Method Purpose : Save Document to drive and add entry to database
	@RequestMapping(value = "/addSarfActDocuments", method = RequestMethod.POST)
	public @ResponseBody int addSarfActDocuments(@RequestParam("doc_SarfAct_id") int doc_SarfAct_id,
			@RequestParam("sarf_document_Type") String sarf_document_Type,
			@RequestParam("SarfDocument") MultipartFile SarfDocument) {
		try {
			// System.out.println("!!!!!!");
			sarfaesiActService.addSarfActDocuments(doc_SarfAct_id, sarf_document_Type, SarfDocument);
			return doc_SarfAct_id;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// Method Created : Pranjali Kawale
	// Method Purpose : Get all documents for Ajax Call
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getAllSarfDocumentForAjax", method = RequestMethod.POST)
	public @ResponseBody String getAllSarfDocumentForAjax(@RequestBody String jsonString) {
		try {
			// System.out.println("AjaxCall");
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			List<SarfaesiActDocument> sarfaesiActDocument = sarfaesiActService
					.getALLSarfaesiActDocByid(Integer.parseInt(jsonObject.get("doc_SarfAct_id").toString()));
			if (!sarfaesiActDocument.isEmpty()) {
				JSONArray documentsDataToSend = new JSONArray();
				Iterator<SarfaesiActDocument> itr = sarfaesiActDocument.iterator();
				while (itr.hasNext()) {
					SarfaesiActDocument sarfaesiactDocument = (SarfaesiActDocument) itr.next();
					JSONObject objToAddToArray = new JSONObject();

					objToAddToArray.put("sarf_doc_id", sarfaesiactDocument.getSarf_doc_id());
					objToAddToArray.put("sarf_original_file_name", sarfaesiactDocument.getSarf_original_file_name());
					objToAddToArray.put("sarf_generated_file_name", sarfaesiactDocument.getSarf_generated_file_name());
					objToAddToArray.put("sarf_document_Type", sarfaesiactDocument.getSarf_document_Type());
					documentsDataToSend.add(objToAddToArray);

				}
				return documentsDataToSend.toJSONString();
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	// Method Created : Pranjali Kawale
	// Method purpose : download Sarfaesi Act documents
	@RequestMapping(value = "/downloadSarfActDoc", method = RequestMethod.GET)
	public void downloadTaskAssignDoc(int sarf_doc_id, HttpServletResponse response) {
		try {
			sarfaesiActService.downloadSarfaesiActDoc(sarf_doc_id, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Pranjali Kawale
	// Method Purpose : Delete Sarfaesi Act Document
	@RequestMapping(value = "/deleteSarfaesiActDocument", method = RequestMethod.POST)
	public @ResponseBody String deleteSarfaesiActDocument(@RequestBody String jsonString) {
		try {
			// System.out.println("Delete");
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			sarfaesiActService.deleteSarfaesiActDocumentById(Integer.parseInt(jsonObject.get("sarf_doc_id").toString()));
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed";
		}
	}

	// Method Created : Pranjali Kawale
	// Method Purpose : check Sarfaesi Act Exit or Not
	@RequestMapping(value = "isLoanNumberExistOrNot", method = RequestMethod.POST)
	public @ResponseBody String isLoanNumberExistOrNot(@RequestBody String jsonString) {
		try {
			// System.out.println("LoanExit");
			JSONObject json = (JSONObject) new JSONParser().parse(jsonString);
			String sarf_loan_no = json.get("sarf_loan_no").toString();
			// System.out.println(sarf_loan_no);
			int result = sarfaesiActService.isLoanNumberExistOrNot(sarf_loan_no);
			return String.valueOf(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Pranjali Kawale
	// Method Purpose : Get ARC Manager as per ARC Name
	@RequestMapping(value = "/getArcManagerByARCnameId", method = RequestMethod.POST)
	public @ResponseBody String getArcManagerByARCnameId(@RequestParam("arc_name_id") Integer arc_name_id) {
		try {
			// System.out.println(arc_name_id);
			return sarfaesiActService.getArcManagerByARCnameId(arc_name_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
