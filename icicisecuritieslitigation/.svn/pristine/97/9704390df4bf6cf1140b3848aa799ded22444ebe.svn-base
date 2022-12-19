package lcmt.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import lcmt.domain.Litigation;

public interface DashboardDao {
	
	public List<Object> getOverallLitigationStatusGraph(HttpSession session);
	public List<Object> getAllLitigationDataByStatusPieChart(String status, HttpSession session);
	public List<Object> getAllCateoryNos(HttpSession session); 
	public List<Object> getAllLitigationForCategory(int liti_type_id, HttpSession session);
	public List<Object> getAllLitigationByCaseCategory(int caseCategory, String LitigationStatus, HttpSession session);
	public int getLitigationIdByLitigationtype(String liti_type_name);
}
