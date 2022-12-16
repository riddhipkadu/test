package lcmt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.DashboardDao;
import lcmt.domain.Litigation;
import lcmt.service.DashboardService;
import lcmt.service.LitigationService;

@Service(value="dashboardService")
public class DashboardServiceImpl implements DashboardService {
	
	@Autowired
	DashboardDao dashboardDao;
	
	@Autowired
	LitigationService litigationService;

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getOverallLitigationStatusGraph(HttpSession session) {
		try {
			Map<String,Object> totalPieConut = new HashMap<String,Object>();
			ArrayList<Integer> orgData = new ArrayList<>();
			JSONObject json = new JSONObject();
			Map data = new HashMap<>();
			
			List<Object> allStatus = dashboardDao.getOverallLitigationStatusGraph(session);
			
			int Pending = 0;
			int Against = 0;
			int InFavour = 0;
			int Settled = 0;
			int Withdrawn = 0;
			
			Iterator<Object> itr = allStatus.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
				Pending = Integer.parseInt(object[0].toString());
				Against = Integer.parseInt(object[1].toString());
				InFavour = Integer.parseInt(object[2].toString());
				Settled = Integer.parseInt(object[3].toString());
				Withdrawn = Integer.parseInt(object[4].toString());
			}
			orgData.add(Pending);
			orgData.add(Against);
			orgData.add(InFavour);
			orgData.add(Settled);
			orgData.add(Withdrawn);
		//Assign count to JSON				
		totalPieConut.put("pieData", orgData);

		data.put("OrgData", totalPieConut);
		json.putAll(data);
		return json;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAllLitigationDataByStatusPieChart(String status, HttpSession session) {
		try {
			JSONArray sendData = new JSONArray();
			
			List<Object> alldata = dashboardDao.getAllLitigationDataByStatusPieChart(status, session);
			Iterator<Object> itr = alldata.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("liti_client_id", object[0].toString());
				jsonObj.put("by_against", object[1].toString());
				jsonObj.put("liti_type_id", litigationService.getLiti_typeById(Integer.parseInt(object[2].toString())).getLiti_type_name());
				jsonObj.put("case_title", object[3].toString());
				jsonObj.put("case_ref_no", object[4].toString());
				jsonObj.put("liti_id", object[5].toString());
				sendData.add(jsonObj);
			}
			return sendData.toJSONString();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getCaseCategoryGraph(HttpSession session) {
		try {System.out.println("in serv");
			SimpleDateFormat sdfOut = new SimpleDateFormat("yyyy-MM-dd");

			JSONObject CategoryJson = new JSONObject();

			
			List<Object> AllCateoryNos = null;
			
	
			AllCateoryNos = dashboardDao.getAllCateoryNos(session);
			//else
			
			
			Iterator<Object> ProductItr = AllCateoryNos.iterator();
			while (ProductItr.hasNext()) {
				Object[] productProtocol = (Object[]) ProductItr.next();
				int Product = Integer.parseInt(productProtocol[0].toString());
				String category_name = productProtocol[1].toString();
				
				JSONObject innerJson = new JSONObject();
				
				int Pending = 0;
				int In_favour = 0;
				int Settled = 0;
				int Against = 0;
			    int WithDrawn = 0;
				int totalCount = 0;
			
				List<Object> allTask = dashboardDao.getAllLitigationForCategory(Product, session);
				
				Iterator<Object> itr = allTask.iterator();

				//Get Total Count
				totalCount = allTask.size();
			
				
				while (itr.hasNext()) {				
					Object object[] = (Object[]) itr.next();
							//Pending 
				
							if((object[0].toString()).equals("Pending")){
								Pending++;
							}
							//In favour 
							if(object[0].equals("In Favour")){
								In_favour++;
							}
							//Settled 
							if(object[0].equals("Settled")){
								Settled++;
							}
							//Against 
							if(object[0].equals("Against")){
								Against++;
							}
							if(object[0].equals("WithDrawn")){
								WithDrawn++;
							}
							
				}
				
				innerJson.put("Pending",Pending);
				innerJson.put("In favour",In_favour);
				innerJson.put("Settled",Settled);
				innerJson.put("Against",Against);
				innerJson.put("WithDrawn",WithDrawn);
				
				innerJson.put("TotalCount",totalCount);

				CategoryJson.put(category_name, innerJson);
			}			
			return CategoryJson;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAllLitigationByCaseCategory(String caseCategory, String LitigationStatus, HttpSession session) {
		try {
			JSONArray sendData = new JSONArray();
			int liti_type = dashboardDao.getLitigationIdByLitigationtype(caseCategory);
			List<Object> alldata = dashboardDao.getAllLitigationByCaseCategory(liti_type, LitigationStatus , session);
			Iterator<Object> itr = alldata.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("liti_client_id", object[0].toString());
				jsonObj.put("by_against", object[1].toString());
				jsonObj.put("liti_type_id", litigationService.getLiti_typeById(Integer.parseInt(object[2].toString())).getLiti_type_name());
				jsonObj.put("case_title", object[3].toString());
				jsonObj.put("case_ref_no", object[4].toString());
				jsonObj.put("liti_id", object[5].toString());
				sendData.add(jsonObj);
			}
			return sendData.toJSONString();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
