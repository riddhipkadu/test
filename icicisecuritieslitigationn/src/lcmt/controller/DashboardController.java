package lcmt.controller;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lcmt.service.DashboardService;
import lcmt.service.LitigationService;

/*
 * Created By : Tejashri Zurunge
 * Created at : 20-4-2018
 * Purpose    : Dashboard of litigation
 * 
 */


@Controller
public class DashboardController {
	
	@Autowired
	DashboardService dashboardService;
	@Autowired
	LitigationService litigationService;

	@RequestMapping(value="/contract_dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(){
		try {
			ModelAndView modelAndView = new ModelAndView("contract_dashboard");
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	@RequestMapping(value = "/dashboard" , method = RequestMethod.GET)
	public ModelAndView getDashboard(HttpSession session){
		try {
			ModelAndView modelAndView = new ModelAndView("dashboard");
			modelAndView.addObject("allLitiType", litigationService.getAllLitiType());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/getOverallLitigationStatusGraph", method = RequestMethod.POST)
	public @ResponseBody JSONObject getOverallLitigationStatusGraph(HttpSession session) {
		try {
			return dashboardService.getOverallLitigationStatusGraph(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
		// Method Created : Tejashri Zurunge
		// Method Purpose : get all litigation data status wise
		@RequestMapping(value = "/getAllLitigationDataByStatusPieChart", method = RequestMethod.POST)
		public @ResponseBody String getAllLitigationDataByStatusPieChart(@RequestBody String jsonString, HttpSession session) {
			try {
				JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
				
				return dashboardService.getAllLitigationDataByStatusPieChart(jsonObject.get("liti_litigation_result").toString(), session);
				
				//return "Success";
			} catch (Exception e) {
				e.printStackTrace();
				
				return "Failed";
			}
		}
	
		@RequestMapping(value = "/getCaseCategoryGraph", method = RequestMethod.POST)
		public @ResponseBody JSONObject getCaseCategoryGraph(HttpSession session) {
			try {
				/*JSONObject json = (JSONObject) new JSONParser().parse(jsonString);
				String impact = json.get("taskImpact").toString();
				String FromDate = json.get("fromDate").toString();
				String ToDate = json.get("toDate").toString();		
				String ProductProtocolNo = json.get("productProtocolNo").toString();*/
				return dashboardService.getCaseCategoryGraph(session);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		// Method Created : Tejashri Zurunge
		// Method Purpose : get all litigation data status wise
		@RequestMapping(value = "/getAllLitigationByCaseCategory", method = RequestMethod.POST)
		public @ResponseBody String getAllLitigationByCaseCategory(@RequestBody String jsonString, HttpSession session) {
			try {
				JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
				String caseCategory = jsonObject.get("caseCategory").toString();
				String LitigationStatus = jsonObject.get("LitigationStatus").toString();
				
				return dashboardService.getAllLitigationByCaseCategory(caseCategory,LitigationStatus, session);
				//return "Success";
			} catch (Exception e) {
				e.printStackTrace();
				return "Failed";
			}
		}
	
	
	
}
