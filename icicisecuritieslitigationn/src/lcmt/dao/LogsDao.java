package lcmt.dao;

import java.util.List;


public interface LogsDao {

	public List<Object> getAllPersistLogs(String related_to, String from_date, String to_date);
	public List<Object> getActivityLogs(String related_to, int log_activity_id);
}
