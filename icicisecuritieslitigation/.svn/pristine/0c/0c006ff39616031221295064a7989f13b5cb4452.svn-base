package lcmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import lcmt.domain.AccessLevels;
import lcmt.domain.User;


public interface UserService {
	public String authenticateUser(String username, String password, HttpSession session);
	public List<User> getAll();
	public void persist(User user);
	public void updateUser(User user);
	public User getUserById(int user_id);
	public List<User> getAllBoth();
	public List<User> getAllPerformer();
	public List<User> getAllReviewer();
	public String getUserFullNameById(int user_id);
	public String checkAccessLevelForUser(int user_id);
	
	public int approveDisapproveUser(int user_id,int user_status);
	public int enableDisableUser(int user_id,int user_status);
	public List<User> getAllUserNotSetAccess(); 
	public List<AccessLevels> getUserAccess(int org_id,int loc_id,int dept_id);
	public String serAccessToUser(String jsonStringStoreData);
	public List<AccessLevels> getUserAccessByUserId(int user_id);
	public ArrayList<AccessLevels> getOrgaForUserAccess(int user_id);
	public ArrayList<AccessLevels> getLocaForUserAccess(int user_id,int orga_id);
	public ArrayList<AccessLevels> getDeptForUserAccess(int user_id,int orga_id,int loca_id);
	public int enableDisableUmap(int umap_id, int umap_status);
	public List<AccessLevels> getRemainUserAccess(int user_id, int orga_id, int loca_id,int dept_id);
	public int CheckEmployeeExist(int user_id,String emp_id );
	public int isEmailIdExist(int user_id,String email_id);
	public int isUserNameExist(int user_id, String user_name);
	
	public List<User> getUsersByOrganization(int orga_id);
	public List<User> getUsersByOrganizationLocation(int orga_id, int loca_id);
	public List<User> getUsersByOrganizationLocationDepartment(int orga_id, int loca_id, int dept_id);
	public List<User> getAllReportingUsersByOrganizationLocationDepartment(int orga_id, int loca_id, int dept_id);
	
	public <T>List <T>  getPerformerReviewerByAccess(int user_id,String json);
	public List<User> getUserProfile(int userId);
	public void updateUserPic(String pic,int userId);
	public String getPicName(int parseInt);
	public String getUserPassword(int UserId);
	public int changeNewPassword(String newPassword, int userId);
	public String CheckTaskExistForUser(String json,HttpSession session);
	public String resetPassword(String user_username, String user_mailId);
	public Map<Integer, String> getAllUser();
	
	public String authenticateUserPeopleSoft(String empno, HttpSession session);
	public List<User> getAdminMailId();
	public List<User> getAllPOCUsers();
}


/*public String addUser(String jsonStringStoreData) throws ParseException;*/