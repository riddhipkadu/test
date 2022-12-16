package lcmt.service;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

public interface DashboardService {
	
	public JSONObject getOverallPreContractStatusGraph(HttpSession session);
	
	public JSONObject getOverallEntityGraph(HttpSession session);
	
	public String getAllEntityDataByPieChart(String status, HttpSession session);

	public String getAllPreContractDataByStatusPieChart(String status, HttpSession session);

	public JSONObject getCaseCategoryGraph(HttpSession session);

	public String getAllPreContractByCaseCategory(String caseCategory, String PreContractStatus, HttpSession session);

}
