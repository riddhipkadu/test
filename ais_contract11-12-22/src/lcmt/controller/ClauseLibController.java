package lcmt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

import lcmt.domain.Clause;
import lcmt.domain.ClauseLib;
import lcmt.domain.ClauseLib_Reference;
import lcmt.domain.Query_Reference;
import lcmt.service.ClauseLibService;
import lcmt.service.ClauseService;
import lcmt.service.UserService;
import lcmt.service.UtilitiesService;

@Controller
@RequestMapping(value = "/*")

public class ClauseLibController {
	
	@Autowired
	UtilitiesService utilitiService;
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	UserService userService;

	@Autowired
	ClauseLibService clauseLibService;

	@Autowired
	ClauseService clauseService;

	@RequestMapping(value = "/addClauseLib", method = RequestMethod.GET)
	public ModelAndView addClauseLib() {
		try {
			ModelAndView modelAndView = new ModelAndView("addClauseLib", "addClauseLib", new ClauseLib());
			modelAndView.addObject("allClause", clauseService.getAllClause());
			modelAndView.addObject("allUser", userService.getAllUser());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	@RequestMapping(value = "/saveClauseLib", method = RequestMethod.POST)
	public String saveClauseLib(ClauseLib clauseLib, HttpSession session) {
		try {

			clauseLib.setLib_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			clauseLibService.persist(clauseLib);
			return "redirect:listClauseLib";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	

	@RequestMapping(value = "/listClauseLib", method = RequestMethod.GET)
	public ModelAndView listClauseLib(HttpSession session) {
		try {
			
			  ModelAndView modelAndView = new ModelAndView("listClauseLib", "listClauseLib", clauseLibService.getJoinedAll());
			  modelAndView.addObject("allClause", clauseService.getAllClause());
			  modelAndView.addObject("allUser", userService.getAll());
			  
			  return modelAndView;
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	@RequestMapping(value = "/editClauseLib", method = RequestMethod.GET)
	public ModelAndView editClauseLib(int clause_Lib_Id, HttpSession session) {
		try {
			ModelAndView modelAndView = new ModelAndView("editClauseLib", "editClauseLib",
					clauseLibService.getClauseLibById(clause_Lib_Id));
			modelAndView.addObject("allClause", clauseService.getAllClause());
			modelAndView.addObject("allUser", userService.getAllUser());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	 
	 
	
	
	
	/*
	 * @RequestMapping(value = "/editClauseLib", method = RequestMethod.GET) public
	 * ModelAndView editClauseLib(int clause_Lib_Id, HttpSession session) { try {
	 * ClauseLib_Reference clslib_ref =
	 * clauseLibService.getClauseLibById_ref(clause_Lib_Id); ModelAndView
	 * modelAndView = new ModelAndView("editClauseLib", "editClauseLib",
	 * clslib_ref);
	 * 
	 * clslib_ref.getLib_owner_Id();
	 * 
	 * modelAndView.addObject("allClause", clauseService.getAllClause());
	 * modelAndView.addObject("allUser", userService.getAllUser());
	 * 
	 * return modelAndView; } catch (Exception e) { e.printStackTrace(); } return
	 * null; }
	 */
	 
	 
	
	
	@RequestMapping(value = "/updateClauseLib", method = RequestMethod.POST)
	public String updateClauseLib(ClauseLib_Reference clauseLib_Reference,  HttpSession session) {
		try {
			clauseLibService.updateClauseLib(clauseLib_Reference, session);
			return "redirect:listClauseLib";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	
	
	
	
	
	@RequestMapping(value = "/deleteClauseLib", method = RequestMethod.POST)
	public @ResponseBody String deleteClauseLib(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int clause_Lib_Id = Integer.parseInt(jsonObject.get("clause_Lib_Id").toString());
			int deleteCount = clauseLibService.deleteClauseLib(clause_Lib_Id);
			return String.valueOf(deleteCount);
	
			
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




}
