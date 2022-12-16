package lcmt.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

public interface DashboardDao {
	
	public List<Object> getOverallPreContractStatusGraph(HttpSession session);
	
	public List<Object> getOverallEntityGraph(HttpSession session);
	
	public List<Object> getAllEntityDataByPieChart(String status, HttpSession session);

	public List<Object> getAllPreContractDataByStatusPieChart(String status, HttpSession session);

	public List<Object> getAllCateoryNos(HttpSession session);

	
	public int getContractIdByContractType(String cont_type_name);

	public List<Object> getAllContractByCaseCategory(int caseCategory, String PreContractStatus, HttpSession session);

	public List<Object> getAllContractForCategory(int cont_id, HttpSession session);

}
