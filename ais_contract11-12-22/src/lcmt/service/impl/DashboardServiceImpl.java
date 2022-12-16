package lcmt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.util.SystemOutLogger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.ContractDao;
import lcmt.dao.DashboardDao;
import lcmt.dao.ExecutedContractDao;
import lcmt.domain.ContractParties;
import lcmt.service.DashboardService;



 @Service(value="dashboardService")
 public class DashboardServiceImpl implements DashboardService {
 
	 @Autowired 
	 DashboardDao dashboardDao;
	 @Autowired 
	 ContractDao contractDao;
	 @Autowired 
	 ExecutedContractDao executedContractDao;
	 
	 
    @SuppressWarnings("unchecked") 
	@Override
	public JSONObject getOverallPreContractStatusGraph(HttpSession session) {
		
		try {
			Map<String,Object> totalPieConut = new HashMap<String,Object>();
			ArrayList<Integer> orgData = new ArrayList<>();
			JSONObject json = new JSONObject();
			Map data = new HashMap<>();
			
			List<Object> allStatus = dashboardDao.getOverallPreContractStatusGraph(session);
			
			int SentforReview = 0;
			int Finalized = 0;
			int ClosedExecuted = 0;
			int SentToPOCforNegotiation = 0;
			int Others = 0;
			int Pending = 0;
			
			Iterator<Object> itr = allStatus.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
				SentforReview = Integer.parseInt(object[0].toString());
				Finalized = Integer.parseInt(object[1].toString());
				ClosedExecuted = Integer.parseInt(object[2].toString());
				SentToPOCforNegotiation = Integer.parseInt(object[3].toString());
				Others = Integer.parseInt(object[4].toString());
				Pending = Integer.parseInt(object[5].toString());
			}
			orgData.add(SentforReview);
			orgData.add(Finalized);
			orgData.add(ClosedExecuted);
			orgData.add(SentToPOCforNegotiation);
			orgData.add(Others);
			orgData.add(Pending);
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
	public String getAllPreContractDataByStatusPieChart(String status, HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			JSONArray sendData = new JSONArray();
			
			List<Object> alldata = dashboardDao.getAllPreContractDataByStatusPieChart(status, session);
			//System.out.println(alldata);
			Iterator<Object> itr = alldata.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("cont_id", object[0].toString());
				jsonObj.put("orga_name", object[1].toString());
				jsonObj.put("loca_name", object[2].toString());
				jsonObj.put("dept_name", object[3].toString());
				jsonObj.put("cont_start_date", (sdfOut.format(sdfIn.parse(object[4].toString()))));
				jsonObj.put("cont_end_date", (sdfOut.format(sdfIn.parse(object[5].toString()))));
				jsonObj.put("cont_responsible_person", (object[6].toString()+" "+object[7].toString()));
				
				List<ContractParties> res = contractDao
						.getContractPartiesByContractId(Integer.parseInt(object[0].toString()));
				Iterator<ContractParties> iterator2 = res.iterator();
				JSONArray multiParty = new JSONArray();
				while (iterator2.hasNext()) {
					ContractParties contractParties = iterator2.next();
					JSONObject party = new JSONObject();
					party.put("party_id", contractParties.getCont_party_id());
					party.put("party_name", contractParties.getCont_party_name());
					//System.out.println(contractParties.getCont_party_name());
					multiParty.add(party);
				}
				jsonObj.put("parties", multiParty);
				
				
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
		try {
			SimpleDateFormat sdfOut = new SimpleDateFormat("yyyy-MM-dd");

			JSONObject CategoryJson = new JSONObject();

			/*String CurrentDateFormat = sdfOut.format(utilitiesService.getCurrentDate());
			Date CurrentDate = sdfOut.parse(CurrentDateFormat.toString());*/ 
			List<Object> AllCateoryNos = null;
			
			//if(ProductProtocolNo.equals("0"))
			AllCateoryNos = dashboardDao.getAllCateoryNos(session);
			//else
			//	AllProtocolNos = dashboardDao.getAllProtocolNosBYID(ProductProtocolNo,session);
			
			Iterator<Object> ProductItr = AllCateoryNos.iterator();
			while (ProductItr.hasNext()) {
				Object[] productProtocol = (Object[]) ProductItr.next();
				int Product = Integer.parseInt(productProtocol[0].toString());
				String category_name = productProtocol[1].toString();
				//System.out.println("type :"+category_name+" id"+Product);
				JSONObject innerJson = new JSONObject();
				
				int Sent_for_Review = 0;
				int Finalized = 0;
				int ClosedExecuted = 0;
				int SentToPOCforNegotiation = 0;
				int Others = 0;
				int Pending = 0;
				int totalCount = 0;
				/*int reOpened = 0;
				
				int Upcoming = 0;
				int EventNotOccurred = 0;*/
				List<Object> allTask = dashboardDao.getAllContractForCategory(Product, session);
				Iterator<Object> itr = allTask.iterator();

				//Get Total Count
				totalCount = allTask.size();
				
				while (itr.hasNext()) {				
					Object object[] = (Object[]) itr.next();
					//if(liti != null){

							
							if(object[0].equals("Sent for Review")){
								Sent_for_Review++;
							}
					
							if(object[0].equals("Finalized")){
								Finalized++;
							}
							
							if(object[0].equals("Closed/Executed")){
								ClosedExecuted++;
							}
							
							if(object[0].equals("SentToPOCforNegotiation")){
								SentToPOCforNegotiation++;
							}
							
							if(object[0].equals("Others")){
								Others++;
							}
							if(object[0].equals("Pending")){
								Pending++;
							}
				}
				
				innerJson.put("Sent for Review",Sent_for_Review);
				//System.out.println(Sent_for_Review);
				innerJson.put("Finalized",Finalized);
				innerJson.put("Closed/Executed",ClosedExecuted);
				innerJson.put("SentToPOCforNegotiation",SentToPOCforNegotiation);
				innerJson.put("Others",Others);
				innerJson.put("Pending",Pending);
				//innerJson.put("ReOpened",reOpened);
				innerJson.put("TotalCount",totalCount);
				/*innerJson.put("UpComming",Upcoming);
				innerJson.put("EventNotOccurred",EventNotOccurred);*/
				CategoryJson.put(category_name, innerJson);
				//System.out.println(" status :"+CategoryJson);
			}			
			return CategoryJson;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

    @SuppressWarnings("unchecked")
	@Override
	public String getAllPreContractByCaseCategory(String caseCategory, String PreContractStatus, HttpSession session) {
		try {
			
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			JSONArray sendData = new JSONArray();
			int Cont_type = dashboardDao.getContractIdByContractType(caseCategory);
			List<Object> alldata = dashboardDao.getAllContractByCaseCategory(Cont_type, PreContractStatus , session);
			Iterator<Object> itr = alldata.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("cont_id", object[0].toString());
				jsonObj.put("orga_name", object[1].toString());
				jsonObj.put("loca_name", object[2].toString());
				jsonObj.put("dept_name", object[3].toString());
				jsonObj.put("cont_start_date", (sdfOut.format(sdfIn.parse(object[4].toString()))));
				jsonObj.put("cont_end_date", (sdfOut.format(sdfIn.parse(object[5].toString()))));
				jsonObj.put("cont_responsible_person", (object[6].toString()+" "+object[7].toString()));
				jsonObj.put("cont_status", object[8].toString());
				
				String other_status =contractDao.getLatestContractOtherStatusByContId(Integer.parseInt(object[0].toString()));
				//System.out.println(other_status);
				
				if(other_status != null)
				jsonObj.put("other_status", other_status);
				
				else
					jsonObj.put("other_status", "NA");
			
				
				List<ContractParties> res = contractDao
						.getContractPartiesByContractId(Integer.parseInt(object[0].toString()));
				Iterator<ContractParties> iterator2 = res.iterator();
				JSONArray multiParty = new JSONArray();
				while (iterator2.hasNext()) {
					ContractParties contractParties = iterator2.next();
					JSONObject party = new JSONObject();
					party.put("party_id", contractParties.getCont_party_id());
					party.put("party_name", contractParties.getCont_party_name());
					//System.out.println(contractParties.getCont_party_name());
					multiParty.add(party);
				}
				jsonObj.put("parties", multiParty);

				sendData.add(jsonObj);
			}
			return sendData.toJSONString();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
    // method for entity wise graph - Akash Jadhav

    @SuppressWarnings("unchecked") 
   	@Override
   	public JSONObject getOverallEntityGraph(HttpSession session) {
   		
   		try {
   			Map<String,Object> totalPieConut = new HashMap<String,Object>();
   			ArrayList<Integer> orgData = new ArrayList<>();
   			JSONObject json = new JSONObject();
   			Map data = new HashMap<>();
   			
   			List<Object> allStatus = dashboardDao.getOverallEntityGraph(session);
   			
   			int MahycoGrow = 0;
   			int MahycoPrivateLimited = 0;
   			int MaharashtraHybridSeedsCompanyPrivateLimited = 0;
   			int GrowIndigoPrivateLimited = 0;
   			int SevenStarFruitsPrivateLimited = 0;
   			int LeadbeterSeedsPrivateLimited = 0;
   			int MahycoInternationalPteLimited = 0;
   			int MahycoBangladeshPrivateLimited = 0;
   			int MahycoKenyaPrivateLimited = 0;
   			int MahycoGrowSAPtyLTD = 0;
   			
   			
   			Iterator<Object> itr = allStatus.iterator();
   			while (itr.hasNext()) {
   				Object[] object = (Object[]) itr.next();
   				MahycoGrow = Integer.parseInt(object[0].toString());
   				MahycoPrivateLimited = Integer.parseInt(object[1].toString());
   				MaharashtraHybridSeedsCompanyPrivateLimited = Integer.parseInt(object[2].toString());
   				GrowIndigoPrivateLimited = Integer.parseInt(object[3].toString());
   				SevenStarFruitsPrivateLimited = Integer.parseInt(object[4].toString());
   				LeadbeterSeedsPrivateLimited = Integer.parseInt(object[5].toString());
   				MahycoInternationalPteLimited = Integer.parseInt(object[6].toString());
   				MahycoBangladeshPrivateLimited = Integer.parseInt(object[7].toString());
   				MahycoKenyaPrivateLimited = Integer.parseInt(object[8].toString());
   				MahycoGrowSAPtyLTD = Integer.parseInt(object[9].toString());
   				
   			}
   			orgData.add(MahycoGrow);
   			orgData.add(MahycoPrivateLimited);
   			orgData.add(MaharashtraHybridSeedsCompanyPrivateLimited);
   			orgData.add(GrowIndigoPrivateLimited);
   			orgData.add(SevenStarFruitsPrivateLimited);
   			orgData.add(LeadbeterSeedsPrivateLimited);
   			orgData.add(MahycoInternationalPteLimited);
   			orgData.add(MahycoBangladeshPrivateLimited);
   			orgData.add(MahycoKenyaPrivateLimited );
   			orgData.add(MahycoGrowSAPtyLTD);
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
	public String getAllEntityDataByPieChart(String status, HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			JSONArray sendData = new JSONArray();
			
			List<Object> alldata = dashboardDao.getAllEntityDataByPieChart(status, session);
			//System.out.println(alldata);
			Iterator<Object> itr = alldata.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("cont_id", object[0].toString());
				jsonObj.put("orga_name", object[1].toString());
				jsonObj.put("loca_name", object[2].toString());
				jsonObj.put("dept_name", object[3].toString());
				jsonObj.put("cont_start_date", object[4].toString());
				jsonObj.put("cont_end_date", object[5].toString());
				jsonObj.put("cont_responsible_person", (object[6].toString()+" "+object[7].toString()));
				
				
				List<ContractParties> partyJson = executedContractDao.getExecutedContractPartiesByContractId(Integer.parseInt(object[0].toString()));
				Iterator<ContractParties> iterator2 = partyJson.iterator();
				JSONArray multiParty = new JSONArray();
				while(iterator2.hasNext()){
				ContractParties contractParties = iterator2.next();
				JSONObject party = new JSONObject();
				party.put("party_id", contractParties.getCont_party_id());
				party.put("party_name", contractParties.getCont_party_name());
				multiParty.add(party);
				}
				
				/*
				 * List<ContractParties> res = contractDao
				 * .getContractPartiesByContractId(Integer.parseInt(object[0].toString()));
				 * Iterator<ContractParties> iterator2 = res.iterator(); JSONArray multiParty =
				 * new JSONArray(); while (iterator2.hasNext()) { ContractParties
				 * contractParties = iterator2.next(); JSONObject party = new JSONObject();
				 * party.put("party_id", contractParties.getCont_party_id());
				 * party.put("party_name", contractParties.getCont_party_name());
				 * //System.out.println(contractParties.getCont_party_name());
				 * multiParty.add(party); }
				 */
				jsonObj.put("parties", multiParty);
				
				
				sendData.add(jsonObj);
			}
			return sendData.toJSONString();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}