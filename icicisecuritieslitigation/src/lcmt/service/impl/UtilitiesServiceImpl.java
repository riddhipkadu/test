package lcmt.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lcmt.dao.UtilitiesDao;
import lcmt.domain.TechSupport;
//import lcmt.dao.SchedulerDao;
//import lcmt.dao.TasksDao;
//import lcmt.dao.TasksDetailsDao;
//import lcmt.domain.TaskTransactional;
import lcmt.service.UserService;
import lcmt.service.UtilitiesService;

/*
 * Author: Mahesh Kharote
 * Date: 19/02/2016
 * Updated By: Mahesh Kharote
 * Updated Date: 19/02/2016
 * 
 * */

@Service("utilitiesService")
public class UtilitiesServiceImpl implements UtilitiesService {

	@Autowired
	UserService userService;

	@Autowired
	UtilitiesDao utilitiesDao;
	
	@Autowired
	HttpSession session;

	SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

	private @Value("#{config['project_name'] ?: 'null'}") String project_name;
	// Method Created By: Mahesh Kharote
	// Method Purpose: Get Current date
	@Override
	public Date getCurrentDate() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Date date1 = null;

		try {
			date1 = dateFormat.parse(dateFormat.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date1;
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Get Current date
	@Override
	public String getCurrentDateString() {

		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			return dateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Get User Id set in session
	@Override
	public int getCurrentSessionUserId(HttpSession session) {

		try {
			return Integer.parseInt(session.getAttribute("sess_user_id").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Get User Id set in session
	@Override
	public int getCurrentYear() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy");
			Date date = new Date();
			return Integer.parseInt(dateFormat.format(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Get Current date
	@Override
	public void addMailToLog(String username, String subject) {

		File dir = new File("C:" + File.separator + project_name + File.separator + "EmailLogs");
		if (!dir.exists())
			dir.mkdirs();

		File newFile = new File(dir.getPath() + File.separator + "logs.txt");
		if (!newFile.exists()) {
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try (FileWriter fw = new FileWriter(
				"C:" + File.separator + project_name + File.separator + "EmailLogs" + File.separator + "logs.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println("mail sent to: " + username);
			// more code
			java.util.Date date = new java.util.Date();
			out.println("Date and Time: " + new Timestamp(date.getTime()));
			// more code
			out.println("Subject: " + subject);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: Check the Dependancy
	@Override
	public int checkDependancy(int id, String table_name) {
		try {
			int deleteCount = utilitiesDao.checkDependancy(id, table_name);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	// Method Created: Harshad Padole
	// Method Purpose: DATE with Time
	@Override
	public Date getCurrentDateWithTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Date date1 = null;

		try {
			date1 = dateFormat.parse(dateFormat.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date1;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: Adding mail to the log
	@Override
	public void addMailToLog(String username, String usernameCC, String subject, String id) {
		File dir = new File("C:"+ File.separator + project_name + File.separator +"EmailLogs");
		if (!dir.exists())
			dir.mkdirs();
		
		
		File newFile = new File(dir.getPath()
                + File.separator + "logs.txt");
		if (!newFile.exists()) {  
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}  
		
		try(FileWriter fw = new FileWriter("C:"+ File.separator +project_name+ File.separator +"EmailLogs" + File.separator + "logs.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("mail sent to: "+ username);
			    out.println("in mail cc: "+ usernameCC);
			    //more code
			    java.util.Date date = new java.util.Date();
			    out.println("Date and Time: "+ new Timestamp(date.getTime()));
			    //more code
			    out.println("Subject: "+ subject);
			    
			    out.println("Task Id : "+ id);
			} catch (IOException e) {
			    e.printStackTrace();
			}
				
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: save mail details in db
	@Override
	public void persistTechSupport(TechSupport techSupport) {
		try {
			techSupport.setSupp_created_at(getCurrentDateWithTime());
			techSupport.setSupp_added_by(getCurrentSessionUserId(session));
			utilitiesDao.persistTechSupport(techSupport);
		} catch (Exception e) {

		}
		
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: list mail details
	@Override
	public List<TechSupport> getAllTechSupport() {
		try {
			return utilitiesDao.getAllTechSupport();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
