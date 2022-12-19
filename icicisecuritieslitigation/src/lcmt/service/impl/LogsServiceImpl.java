package lcmt.service.impl;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.LogsDao;
import lcmt.service.LogsService;
import lcmt.service.UserService;

@Service("logsService")
public class LogsServiceImpl implements LogsService {

	@Autowired
	LogsDao logsDao;

	@Autowired
	UserService userService;

	// Method Created : Tejashri Zurunge
	// Method purpose : get list of All Logs
	@SuppressWarnings("unchecked")
	@Override
	public String searchLogs(String json) {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(json);
			String related_to = jsonobj.get("log_related_to").toString();
			String from_date = jsonobj.get("from_date").toString();
			String to_date = jsonobj.get("to_date").toString();

			SimpleDateFormat In = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat out = new SimpleDateFormat("dd-MM-yyyy");

			String from_date_logs = "";
			String to_date_logs = "";

			// set date into out format
			if (!from_date.equals("") && !to_date.equals("")) {

				from_date_logs = In.format(out.parse(from_date));
				to_date_logs = In.format(out.parse(to_date));
			}

			JSONArray sendList = new JSONArray();

			List<Object> logs = logsDao.getAllPersistLogs(related_to, from_date_logs, to_date_logs);
			Iterator<Object> iterator = logs.iterator();
			while (iterator.hasNext()) {
				JSONObject jsonObj = new JSONObject();
				Object object[] = (Object[]) iterator.next();

				jsonObj.put("log_id", Integer.parseInt(object[0].toString()));
				jsonObj.put("log_activity", object[1].toString());
				jsonObj.put("log_activity_id", Integer.parseInt(object[2].toString()));
				if (object[3] != null) {
					jsonObj.put("log_assigned_to",
							userService.getUserFullNameById(Integer.parseInt(object[3].toString())));
				} else {
					jsonObj.put("log_assigned_to", "NA");
				}
				jsonObj.put("log_created_at", out.format(In.parse(object[4].toString())));
				jsonObj.put("log_related_to", object[5].toString());
				jsonObj.put("log_subactivity", object[6].toString());
				jsonObj.put("log_subactivity_id", Integer.parseInt(object[7].toString()));
				
				if (object[8] != null) {
				jsonObj.put("log_related_name", object[8].toString());
				}else{
					jsonObj.put("log_related_name", "NA");	
				}
				sendList.add(jsonObj);
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : get list of All Logs
	@SuppressWarnings("unchecked")
	@Override
	public String getActivityLogs(String json) {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(json);
			String related_to = jsonobj.get("log_related_to").toString();
			int log_activity_id = Integer.parseInt(jsonobj.get("log_activity_id").toString());

			SimpleDateFormat In = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat out = new SimpleDateFormat("dd-MM-yyyy");

			JSONArray sendList = new JSONArray();

			List<Object> logs = logsDao.getActivityLogs(related_to, log_activity_id);
			Iterator<Object> iterator = logs.iterator();
			while (iterator.hasNext()) {
				JSONObject jsonObj = new JSONObject();
				Object object[] = (Object[]) iterator.next();

				jsonObj.put("log_id", Integer.parseInt(object[0].toString()));
				jsonObj.put("log_activity", object[1].toString());
				jsonObj.put("log_activity_id", Integer.parseInt(object[2].toString()));
				if (object[3] != null) {
					jsonObj.put("log_assigned_to",
							userService.getUserFullNameById(Integer.parseInt(object[3].toString())));
				} else {
					jsonObj.put("log_assigned_to", "NA");
				}
				jsonObj.put("log_created_at", out.format(In.parse(object[4].toString())));
				jsonObj.put("log_related_to", object[5].toString());
				jsonObj.put("log_subactivity", object[6].toString());
				jsonObj.put("log_subactivity_id", Integer.parseInt(object[7].toString()));
				if (object[8] != null) {
					jsonObj.put("log_related_name", object[8].toString());
				}else{
					jsonObj.put("log_related_name", "NA");	
				}
				sendList.add(jsonObj);
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
