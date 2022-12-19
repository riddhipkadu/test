package lcmt.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface CalendarDao {

	public <T>List<T> getLitigationForCalendar(Class<T> clazz,HttpSession session);
	public <T> List<T> searchLitigationForCalendar(String json,HttpSession session);
	public List<Object> getAllUsers();
}
