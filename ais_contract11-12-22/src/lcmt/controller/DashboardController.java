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

@Controller
public class DashboardController {
	
	
	@Autowired
	DashboardService dashboardService;
	@Autowired
	LitigationService litigationService;

	
	@RequestMapping(value = "/dashboard" , method = RequestMethod.GET)
	public ModelAndView getDashboard(HttpSession session){
		try {
			ModelAndView modelAndView = new ModelAndView("dashboard");
			//modelAndView.addObject("allLitiType", litigationService.getAllLitiType());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getOverallPreContractStatusGraph", method = RequestMethod.POST)
	public @ResponseBody JSONObject getOverallPreContractStatusGraph(HttpSession session) {
		try {
			return dashboardService.getOverallPreContractStatusGraph(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	
	
	@RequestMapping(value = "/getAllPreContractDataByStatusPieChart", method = RequestMethod.POST)
	public @ResponseBody String getAllPreContractDataByStatusPieChart(@RequestBody String jsonString, HttpSession session) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			return dashboardService.getAllPreContractDataByStatusPieChart(jsonObject.get("cont_status").toString(), session);
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
	@RequestMapping(value = "/getAllPreContractByCaseCategory", method = RequestMethod.POST)
	public @ResponseBody String getAllPreContractByCaseCategory(@RequestBody String jsonString, HttpSession session) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			String caseCategory = jsonObject.get("caseCategory").toString();
			String PreContractStatus = jsonObject.get("PreContractStatus").toString();
			
			return dashboardService.getAllPreContractByCaseCategory(caseCategory,PreContractStatus, session);
			//return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed";
		}
	}


//method for Overall entity graph - Akash jadhav

	@RequestMapping(value = "/getOverallEntityGraph", method = RequestMethod.POST)
	public @ResponseBody JSONObject getOverallEntityGraph(HttpSession session) {
		try {
			return dashboardService.getOverallEntityGraph(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	@RequestMapping(value = "/getAllEntityDataByPieChart", method = RequestMethod.POST)
	public @ResponseBody String getAllEntityDataByPieChart(@RequestBody String jsonString, HttpSession session) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			return dashboardService.getAllEntityDataByPieChart(jsonObject.get("cont_status").toString(), session);
			//return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed";
		}
	}
	}