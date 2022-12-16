package lcmt.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import lcmt.domain.User;

public interface UserDao {

	public String authenticateUser(String username, String password, HttpSession session);
	public void persist(Object obj);
	public void updateUser(User user); 
	public User getUserById(int user_id);
	public <T> List<T> getAll(Class<T> clazz);
	public <T> List<T> getAllBoth(Class<T> clazz);
	public <T> List<T> getAllPerformer(Class<T> clazz);
	public <T> List<T> getAllReviewer(Class<T> clazz);
	public <T> List<T> getUserFullNameById(int user_id);
	public <T> List<T> getAllDepartmentAccessible(int user_id);
	
	public int approveDisapproveUser(int user_id,int user_status);
	public int enableDisableUser(int user_id,int user_status);
	public List<Object> getUserAccess(int org_id,int loc_id,int dept_id);
	public <T> List<T> getAllUserNotSetAccess(Class<T> clazz);
	public List<Object> getUserAccessByUserId(int user_id);
	public List<Object> getOrgaForUserAccess(int user_id);
	public List<Object> getLocaForUserAccess(int user_id,int orga_id);
	public List<Object> getDeptForUserAccess(int user_id,int orga_id,int loca_id);
	public int enableDisableUmap(int umap_id, int umap_staus);
	public List<Object> getRemainUserAccess(int user_id, int orga_id, int loca_id,int dept_id);
	public int CheckEmployeeExist(int user_id,String emp_id);
	public int isEmailIdExist(int user_id,String email_id);
	public int isUserNameExist(int user_id, String user_name);
	
	public <T> List<T> getUsersByOrganization(int orga_id);
	public <T> List<T> getUsersByOrganizationLocation(int orga_id, int loca_id);
	public <T> List<T> getUsersByOrganizationLocationDepartment(int orga_id, int loca_id, int dept_id);
	public <T> List<T> getAllReportingUsersByOrganizationLocationDepartment(int orga_id, int loca_id, int dept_id);
	
	public <T> List<T> getPerformerReviewerByAccess(int user_id , String json);
	public List<User> getUserProfile(int userId);
	public void updateUserPic(String pic,int userId);
	public String getProfilePic(int userId);
	public String getOriginalPassword(int userId);
	public int changeNewPassword(String newPassword, int userId);
	public String CheckTaskExistForUser(String json,HttpSession session);
	public String resetPassword(String user_username, String user_mailId); 
	public String authenticateUserPeopleSoft(String empno, HttpSession session);
	public List<User> getAdminMailId();
	public <T> List<T> getAllPOCUser();
}


/*public int persist(Object obj);*/
