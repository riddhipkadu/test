package lcmt.controller;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lcmt.domain.Clause;
import lcmt.service.ClauseService;

@Controller
@RequestMapping("/*")

public class ClauseController {
@Autowired
ClauseService clauseService;

//Method Created : Akash jadhav
	// Method purpose: add new Clause
	@RequestMapping(value = "/addClause", method = RequestMethod.GET)
	public ModelAndView addClause() {
		try {
			ModelAndView addClause = new ModelAndView("addClause", "addClause", new Clause());
			return addClause;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: Save Contract Type
	@RequestMapping(value = "/saveClause", method = RequestMethod.POST)
	public String saveClause(Clause clause, HttpSession session) {
		try {
			clauseService.persist(clause);
			return "redirect:listClause";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
//Method Created: Akash Jadhav
// Method Purpose: listing and view of clause
	@RequestMapping(value = "/listClause", method = RequestMethod.GET)
	public ModelAndView listClause(HttpSession session) {

		try {
			ModelAndView modelAndView = new ModelAndView("listClause", "listClause",clauseService.getAll());
			return modelAndView;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created :Akash jadhav
		// Method purpose :Edit clause
		@RequestMapping(value = "/editClause", method = RequestMethod.GET)
		public ModelAndView editClause(int cls_id) {
			try {
				ModelAndView modelAndView = new ModelAndView("editClause", "editClause",
						clauseService.getClauseById(cls_id));
				return modelAndView;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;

		}

		// Method Created : Akash jadhav
		// Method purpose : Update Clause
		@RequestMapping(value = "/updateClause", method = RequestMethod.POST)
		public String updateClause(Clause clause) {
			try {
				clauseService.updateClause(clause);
				return "redirect:listClause";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}	
		
		
		// Method Created : Akash jadhav
		// Method Purpose : Delete Clause
		@RequestMapping(value = "/deleteClause", method = RequestMethod.POST)
		public @ResponseBody String deleteClause(@RequestBody String jsonString) throws ParseException {
			try {
				JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
				int cls_id = Integer.parseInt(jsonObject.get("cls_id").toString());
				int deleteCount = clauseService.deleteClause(cls_id);
				return String.valueOf(deleteCount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		/*
		 * // Method Created : Akash Jadhav // Method Purpose : Delete Clause
		 * 
		 * @RequestMapping(value = "/checkDependancyClause", method =
		 * RequestMethod.POST) public @ResponseBody String
		 * checkDependancyClause(@RequestBody String jsonString) throws ParseException {
		 * try { JSONObject jsonObject = (JSONObject) new
		 * JSONParser().parse(jsonString); int cls_id =
		 * Integer.parseInt(jsonObject.get("cls_id").toString()); String deleteCount =
		 * clauseService.checkDependancyClause(cls_id); return deleteCount; } catch
		 * (Exception e) { e.printStackTrace(); } return null; }
		 */
}
