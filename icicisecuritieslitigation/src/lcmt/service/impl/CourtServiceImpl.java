package lcmt.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.CourtDao;
import lcmt.domain.Court;
import lcmt.service.CourtService;
import lcmt.service.UtilitiesService;

@Service("courtService")
public class CourtServiceImpl implements CourtService {

@Autowired
CourtDao courtDao;
@Autowired
UtilitiesService utilitiesService;
@Autowired
HttpSession session;
	
	@Override
	public void persist(Court court) {
		try {
			court.setCourt_added_by(utilitiesService.getCurrentSessionUserId(session));
			court.setCourt_created_at(utilitiesService.getCurrentDate());
			court.setCourt_updated_at(utilitiesService.getCurrentDate());
			courtDao.persist(court);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Court> getAll() {
		try {
			return courtDao.getAllCourt(Court.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Court getCourtById(int court_id) {
		try {
			return courtDao.getCourtById(court_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateCourt(Court court) {
		try {
			Court oldCourt = getCourtById(court.getCourt_id());
			Court newCourt = new Court();
			
			newCourt.setCourt_id(oldCourt.getCourt_id());
			newCourt.setCourt_created_at(oldCourt.getCourt_created_at());
			newCourt.setCourt_added_by(oldCourt.getCourt_added_by());
			newCourt.setCourt_updated_at(utilitiesService.getCurrentDate());
			//set new data
			newCourt.setCourt_name(court.getCourt_name());
			
			courtDao.updateCourt(newCourt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int isCourtExistOrNot(int court_id, String court_name) {
		try {
		return courtDao.isCourtExistOrNot(court_id, court_name);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteCourt(int court_id) {
		try {
		int deleteCount = courtDao.deleteCourt(court_id);	
		return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int checkDependancyCourt(int court_id) {
		try {
			int result = 0;
			List<Object> courtObj = courtDao.checkDependancyCourt(court_id);
			Iterator<Object> iterator = courtObj.iterator(); 
			while(iterator.hasNext()){
				Object object[] = (Object[]) iterator.next();
				result = Integer.parseInt(object[0].toString());
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAllCourtJson() {
		try {
			 JSONArray jsonArray = new JSONArray();
			 List<Court> courts = courtDao.getAllCourt(Court.class);
			 Iterator<Court> iterator = courts.iterator();
			 while(iterator.hasNext()){
				 Court court = iterator.next();
				 JSONObject jsonObject = new JSONObject();
				 jsonObject.put("label", court.getCourt_name());
				 jsonObject.put("value", court.getCourt_name());
				 jsonArray.add(jsonObject);
			 }
			 return jsonArray.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
