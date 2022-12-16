package lcmt.service;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

public interface DashboardService {
	
	public JSONObject getOverallLitigationStatusGraph(HttpSession session);
	public String getAllLitigationDataByStatusPieChart(String status, HttpSession session);
	public JSONObject getCaseCategoryGraph(HttpSession session);
	public String getAllLitigationByCaseCategory(String caseCategory, String LitigationStatus, HttpSession session);

}
