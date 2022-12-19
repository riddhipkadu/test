package lcmt.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.CalendarDao;
import lcmt.dao.LitigationDao;
import lcmt.domain.Litigation;
import lcmt.service.CalendarService;

@Service("calendarService")
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	LitigationDao litigationDao;

	@Autowired
	CalendarDao calendarDao;
	//Method Created : Harshad Padole
	//Method Purpose : Get litigation for calendar	
	@SuppressWarnings("unchecked")
	@Override
	public String getLitigationForCalendar(HttpSession session) {
		try {
			List<Litigation> result = calendarDao.getLitigationForCalendar(Litigation.class,session); //litigationDao.getAllLitigation(Litigation.class);
			JSONArray jsonArray = new JSONArray();
			Iterator<Litigation> iterator = result.iterator();
			while(iterator.hasNext()){
				Litigation litigation = iterator.next();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("liti_id", litigation.getLiti_id());
				jsonObject.put("liti_client_id", litigation.getLiti_client_liti_id());
				jsonObject.put("liti_next_hearing_date", litigationDao.getLatestNextHearingStageDate(litigation.getLiti_id()));
				
				jsonArray.add(jsonObject);
			}
			return jsonArray.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//Method Created : Harshad Padole
	//Method Purpose : Search litigation for calendar
	@SuppressWarnings("unchecked")
	@Override
	public String searchLitigationForCalendar(String json,HttpSession session) {
		try {
			//SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			JSONArray sendList = new JSONArray();
			List<Litigation> result = calendarDao.searchLitigationForCalendar(json,session);
			Iterator<Litigation> iterator = result.iterator();
			while (iterator.hasNext()) {
				Litigation litigation = iterator.next();
				JSONObject jsonObject = new JSONObject();

				jsonObject.put("liti_id", litigation.getLiti_id());
				jsonObject.put("liti_client_id", litigation.getLiti_client_liti_id());
				jsonObject.put("liti_next_hearing_date", litigationDao.getLatestNextHearingStageDate(litigation.getLiti_id()));
				/*jsonObject.put("liti_assigned_to", value)*/
				sendList.add(jsonObject);
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Object> getAllUsers() {
		try {
			return calendarDao.getAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
