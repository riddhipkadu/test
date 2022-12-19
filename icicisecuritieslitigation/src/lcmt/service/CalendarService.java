package lcmt.service;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface CalendarService {

	public String getLitigationForCalendar(HttpSession session);
	public String searchLitigationForCalendar(String json,HttpSession session);
	List<Object> getAllUsers();
}
