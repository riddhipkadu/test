package lcmt.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import lcmt.domain.TechSupport;


public interface UtilitiesService {
	
	public Date getCurrentDate();
	public String getCurrentDateString();
	public int getCurrentYear();
	public int getCurrentSessionUserId(HttpSession session);
	public void addMailToLog(String username, String subject);
	public int checkDependancy(int id,String table_name);
	public Date getCurrentDateWithTime();
	public void addMailToLog(String username, String usernameCC, String subject,String id); 
	public void persistTechSupport(TechSupport techSupport);
	public List<TechSupport> getAllTechSupport();

}
