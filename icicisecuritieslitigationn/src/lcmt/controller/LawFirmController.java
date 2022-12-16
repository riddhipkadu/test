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
import lcmt.domain.LawFirm;
import lcmt.service.LawFirmService;
import lcmt.service.UtilitiesService;

@Controller
@RequestMapping("/*")
public class LawFirmController {
	@Autowired
	LawFirmService lawFirmService;
	
	@Autowired
    UtilitiesService utilitieservice;
	
	
		//Method Created : Tejashri Zurunge
		//Method Purpose : add Law Firm
		
		@RequestMapping(value="/addLawFirm", method= RequestMethod.GET)
		public ModelAndView addLawFirm(){
			try {
				ModelAndView modelAndView = new ModelAndView("addLawFirm","lawFirm",new LawFirm());
				return modelAndView;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		//Method Created : Tejashri Zurunge
		//Method Purpose : Save Law Firm
		@RequestMapping(value = "/saveLawFirm", method = RequestMethod.POST)
		public String saveLawFirm(LawFirm lawFirm){
			try {
				lawFirmService.persist(lawFirm);
				return "redirect:listLawFirm";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//Method Created : Tejashri Zurunge
		//Method Purpose : list the Law Firm
		@RequestMapping(value = "/listLawFirm", method = RequestMethod.GET)
		public ModelAndView listLawFirm(HttpSession session){
			try {
				
				ModelAndView modelAndView = new ModelAndView("listLawFirm","listLawFirm",lawFirmService.getAll());
				return modelAndView;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//Method Created : Tejashri Zurunge
		//Method Purpose : update the new Law Firm
		@RequestMapping(value="/updateLawFirm",method = RequestMethod.POST)
		public String updateLawFirm(LawFirm lawFirm){
			try {
				lawFirmService.updateLawFirm(lawFirm);
				return "redirect:listLawFirm";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//Method Created : Tejashri Zurunge
		//Method Purpose : edit the Law Firm
		@RequestMapping(value = "/editLawFirm", method = RequestMethod.GET)
		public ModelAndView editLawFirm(int lawf_id){
			try {
				ModelAndView modelAndView = new ModelAndView("editLawFirm","editLawFirm",lawFirmService.getLawFirmById(lawf_id));
				return modelAndView;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//Method Created : Tejashri Zurunge
		//Method Purpose : To check the Law Firm exist or not
				
		@RequestMapping(value="/isLawFirmNameExist" , method = RequestMethod.POST)
		public @ResponseBody String isLawFirmNameExist(@RequestBody String jsonString) throws ParseException {
			try {
				   JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			       int lawf_id = Integer.parseInt(jsonObject.get("lawf_id").toString());
			       String lawf_name = jsonObject.get("lawf_name").toString();
			       int result = lawFirmService.isLawFirmNameExist(lawf_id, lawf_name);
				   return String.valueOf(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		       
			return null;
			}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Law Firm
	@RequestMapping(value = "/deleteLawFirm", method = RequestMethod.POST)
	public @ResponseBody String deleteLawFirm(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int lawf_id = Integer.parseInt(jsonobj.get("lawf_id").toString());
			int deleteCount = lawFirmService.deleteLawFirm(lawf_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Check Whether Litigation is used in other module before
	// delete it
	@RequestMapping(value = "/checkLawFirmDependancy", method = RequestMethod.POST)
	public @ResponseBody String checkLawFirmDependancy(@RequestBody String json) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(json);
			int lawf_id = Integer.parseInt(jsonobj.get("lawf_id").toString());
			String deleteCount = lawFirmService.checkLawFirmDependancy(lawf_id);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created : Tejashri Zurunge
		// Method Purpose : get all law firm as json
		@RequestMapping(value = "/getAllLawFirmJson", method = RequestMethod.POST)
		public @ResponseBody String getAllLawFirmJson(HttpSession session) {
			try {
				return lawFirmService.getAllLawFirmJson();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;

		}

	
}
