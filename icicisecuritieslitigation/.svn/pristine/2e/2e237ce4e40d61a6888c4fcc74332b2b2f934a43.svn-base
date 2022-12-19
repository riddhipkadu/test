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

import lcmt.domain.InternalLitigation;
import lcmt.service.InternalLitigationService;

@Controller
@RequestMapping(value = "/*")
public class InternalLitigationController {
	
	@Autowired
	InternalLitigationService internalLitigationService;
	
	@RequestMapping(value="/addInternalLitigation", method = RequestMethod.GET)
	public ModelAndView addIntenalLitigation(){
		try {
			ModelAndView modelAndView = new ModelAndView("addInternalLitigation", "addInternalLitigation", new InternalLitigation());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	// Method Created : Tejashri Zurunge
	// Method Purpose : Save Advocate
	@RequestMapping(value = "/saveInternalLitigation", method = RequestMethod.POST)
	public String saveInternalLitigation(InternalLitigation internal, HttpSession session) {
		try {
			internalLitigationService.persist(internal, session);
			return "redirect:listInternalLitigation";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : List Advocate
	@RequestMapping(value = "/listInternalLitigation", method = RequestMethod.GET)
	public ModelAndView listInternalLitigation() {
		try {
			ModelAndView modelAndView = new ModelAndView("listInternalLitigation", "listInternalLitigation", internalLitigationService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created :Tejashri Zurunge
	// Method purpose :Edit internal litigation
	@RequestMapping(value = "/editInternalLitigation", method = RequestMethod.GET)
	public ModelAndView editInternalLitigation(int inter_id) {
		try {
			InternalLitigation inter_liti = internalLitigationService.getInternalLitiById(inter_id);
			ModelAndView modelAndView = new ModelAndView("editInternalLitigation", "editInternalLitigation", inter_liti);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Update internal litigation
	@RequestMapping(value = "/updateInternalLitigation", method = RequestMethod.POST)
	public String updateInternalLitigation(InternalLitigation intern) {
		try {
			internalLitigationService.updateInternalLitigation(intern);
			return "redirect:listInternalLitigation";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Area Of Expertise
	@RequestMapping(value = "/deleteInternalLitigation", method = RequestMethod.POST)
	public @ResponseBody String deleteInternalLitigation(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int internal_liti_id = Integer.parseInt(jsonobj.get("internal_liti_id").toString());
			int deleteCount = internalLitigationService.deleteInternalLitigation(internal_liti_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
	// Method Purpose : To check the internal litigation Name exist or not
	@RequestMapping(value = "/isLitigationCodeExist", method = RequestMethod.POST)
	public @ResponseBody String isLitigationCodeExist(@RequestBody String jsonString) throws ParseException {
		try {
			int result = internalLitigationService.isLitigationCodeExist(jsonString);
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
