package lcmt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.UserDao;
import lcmt.dao.UserEntityMappingDao;
import lcmt.domain.AccessLevels;
import lcmt.domain.User;
import lcmt.domain.UserEntityMapping;
import lcmt.service.UserService;
import lcmt.service.UtilitiesService;

/*
 * Author: Mahesh Kharote
 * Date: 23/02/2016
 * Updated By: Mahesh Kharote
 * Updated Date: 23/02/2016
 * 
 * */


@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	UtilitiesService utilitiesService;

	@Autowired
	HttpSession httpSession;

	@Autowired
	UserEntityMappingDao userEntityMappingDao; 



	//Method Created By: Mahesh Kharote
	//Method Purpose: Authenticate user
	@Override
	public String authenticateUser(String username, String password, HttpSession session) {
		try {
			return userDao.authenticateUser(username, password,session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Mugdha Chandratre 01/03/2016
	//Method Purpose: Save the user in the database

	//Method Updated By: Rahul Shinde 26/07/2016
	//Method Purpose: Add Value For Password reset field
	@Override
	public void persist(User user) {
		try {
			user.setUser_approval_status("1");
			user.setUser_enable_status("1");
			user.setUser_default_password_changed("0");
			//if(user)
			user.setUser_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
			user.setUser_created_at(utilitiesService.getCurrentDate());
			userDao.persist(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//Method Created By: Mugdha Chandratre 01/03/2016
	//Method Purpose: Update the particular user in the database
	@Override
	public void updateUser(User user) {
		try {
			User oldUser = getUserById(user.getUser_id());
			//oldUser = getUserById(user.getUser_id());

			User newUser = new User();
			//Old User Data
			newUser.setUser_id(user.getUser_id());
			newUser.setUser_added_by(oldUser.getUser_added_by());
			newUser.setUser_enable_status(oldUser.getUser_enable_status());
			newUser.setUser_approval_status(oldUser.getUser_approval_status());
			newUser.setUser_userpassword(oldUser.getUser_userpassword());
			newUser.setUser_created_at(oldUser.getUser_created_at());
			//Updated User data
			newUser.setUser_first_name(user.getUser_first_name());
			newUser.setUser_last_name(user.getUser_last_name());
			newUser.setUser_mobile(user.getUser_mobile());
			newUser.setUser_email(user.getUser_email());
			newUser.setUser_username(user.getUser_username());
			newUser.setUser_address(user.getUser_address());
			newUser.setUser_organization_id(user.getUser_organization_id());
			newUser.setUser_location_id(user.getUser_location_id());
			newUser.setUser_department_id(user.getUser_department_id());
			newUser.setUser_report_to(user.getUser_report_to());
			newUser.setUser_employee_id(user.getUser_employee_id());
			newUser.setUser_designation_id(user.getUser_designation_id());
			newUser.setUser_role_id(user.getUser_role_id());

			newUser.setUser_default_password_changed(oldUser.getUser_default_password_changed());
			userDao.updateUser(newUser);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//Method Created By: Mugdha Chandratre 01/03/2016
	//Method Purpose: Fetch the particular user by id from database
	@Override
	public User getUserById(int user_id) {
		try {
			return userDao.getUserById(user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Mugdha Chandratre
	//Method Purpose: Fetch list of all users from database
	@Override
	public List<User> getAll() {
		try {
			return userDao.getAll(User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Mahesh Kharote
	//Method Purpose: Get all performer as well as reviewer
	@Override
	public List<User> getAllBoth() {
		try {
			return userDao.getAllBoth(User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Mahesh Kharote
	//Method Purpose: Get all performer
	@Override
	public List<User> getAllPerformer() {
		try {
			return userDao.getAllPerformer(User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	//Method Created By: Mahesh Kharote
	//Method Purpose: Get all reviewer

	//Method Update By : Mu
	@Override
	public List<User> getAllReviewer() {
		try {
			return	userDao.getAllReviewer(User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}







	//	@Override
	//	public String getUserFullNameById(int user_id) {
	//		try {
	//
	//		} catch (Exception e) {
	//			// TODO: handle exception
	//		}
	//		return null;
	//	}
	//


	//Method Created By: Rahul Shinde (24/02/2016)
	//Method Purpose: Get Full Name Of User
	@Override
	public String getUserFullNameById(int user_id) {
		try {
			// TODO Auto-generated  method stub
			List<User> list = userDao.getUserFullNameById(user_id);
			String fullname ="";
			Iterator<User> itr = list.iterator();
			while (itr.hasNext()) {
				User users = (User) itr.next();
				fullname = users.getUser_first_name()+" "+users.getUser_last_name();
			}
			return fullname;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}



	@Override
	public String checkAccessLevelForUser(int user_id) {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	//Method Created By: Harshad Padole on 25/02/2016
	//Method Purpose: Approve or disapprove user
	@Override
	public int approveDisapproveUser(int user_id, int user_status) {
		try {
			return userDao.approveDisapproveUser(user_id, user_status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	//Method Created By: Harshad Padole on 25/02/2016
	//Method Purpose: enable or disable user
	@Override
	public int enableDisableUser(int user_id, int user_status) {
		try {
			return userDao.enableDisableUser(user_id, user_status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	//Method Created : Harshad Padole
	//Method Purpose : Get mapped access using ajax call
	@Override
	public List<AccessLevels> getUserAccess(int org_id, int loc_id,int dept_id) {
		try {

			List<Object> getAccess = userDao.getUserAccess(org_id, loc_id,dept_id);
			List<AccessLevels> sendAccessList = new ArrayList<>();
			Iterator<Object> itr = getAccess.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
				AccessLevels user = new AccessLevels();

				user.setOrga_id((int) object[0]);
				user.setLoca_id((int) object[1]);
				user.setDept_id((int) object[2]);
				user.setOrga_name(object[3].toString());
				user.setLoca_name(object[4].toString());
				user.setDept_name(object[5].toString());
				sendAccessList.add(user);

			}

			return sendAccessList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//Method Created : Harshad Padole
	//Method Purpose : Save user access
	@Override
	public String serAccessToUser(String jsonStringStoreData) {
		try {
			JSONArray jsonarr = (JSONArray) new JSONParser().parse(jsonStringStoreData);
			JSONObject UserInfo = (JSONObject) new JSONParser().parse(jsonarr.get(0).toString());
			int user_id = Integer.parseInt(UserInfo.get("user_user_id").toString());
			JSONObject UserAccess = (JSONObject) new JSONParser().parse(jsonarr.get(1).toString());
			JSONArray AccessUser = (JSONArray) new JSONParser().parse(UserAccess.get("user_access_level_data").toString());
			for(int k =0; k<AccessUser.size();k++){
				JSONObject jsonobjuseraccessinner = (JSONObject) new JSONParser().parse(AccessUser.get(k).toString());
				UserEntityMapping userEntityMapping = new UserEntityMapping();
				userEntityMapping.setUmap_user_id(user_id);
				userEntityMapping.setUmap_orga_id(Integer.parseInt(jsonobjuseraccessinner.get("user_orga_id").toString()));
				userEntityMapping.setUmap_loca_id(Integer.parseInt(jsonobjuseraccessinner.get("user_loca_id").toString()));
				userEntityMapping.setUmap_dept_id(Integer.parseInt(jsonobjuseraccessinner.get("user_dept_id").toString()));
				userEntityMapping.setUmap_added_by(1);
				userEntityMapping.setUmap_approval_status("1");
				userEntityMapping.setUmap_enable_status("1");
				userEntityMapping.setUmap_created_at(utilitiesService.getCurrentDate());
				userEntityMappingDao.persist(userEntityMapping);

			}	
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
	//Method Created : Harshad Padole
	//Method Purpose : get all user where access not set to user
	@Override
	public List<User> getAllUserNotSetAccess() {
		try {
			return userDao.getAllUserNotSetAccess(User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Harshad Padole
	//Method Purpose : get all access by user id
	@Override
	public List<AccessLevels> getUserAccessByUserId(int user_id) {
		try {
			List<Object> accessList =  userDao.getUserAccessByUserId(user_id);
			ArrayList<AccessLevels> accessLevels =  new ArrayList<>();
			Iterator<Object> iterator = accessList.iterator();
			while(iterator.hasNext()){
				Object[] objects = (Object[]) iterator.next();
				AccessLevels access = new AccessLevels();

				access.setUmap_id((int) objects[0]);
				access.setOrga_id((int) objects[1]);
				access.setLoca_id((int) objects[2]);
				access.setDept_id((int) objects[3]);
				access.setOrga_name(objects[4].toString());
				access.setLoca_name(objects[5].toString());
				access.setDept_name(objects[6].toString());
				accessLevels.add(access);
			}
			return accessLevels;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	//Method Created : Harshad Padole
	//Method Purpose : get organization for user by user id
	@Override
	public ArrayList<AccessLevels> getOrgaForUserAccess(int user_id) {
		try {
			List<Object> accessList =  userDao.getOrgaForUserAccess(user_id);
			ArrayList<AccessLevels> accessLevels =  new ArrayList<>();
			Iterator<Object> iterator = accessList.iterator();
			while(iterator.hasNext()){
				Object[] objects = (Object[]) iterator.next();
				AccessLevels access = new AccessLevels();

				access.setOrga_id((int) objects[0]);
				access.setOrga_name(objects[1].toString());
				accessLevels.add(access);
			}
			return accessLevels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Harshad Padole
	//Method Purpose : get location for user by user id and orga id

	@Override
	public ArrayList<AccessLevels> getLocaForUserAccess(int user_id, int orga_id) {
		try {
			List<Object> accessList =  userDao.getLocaForUserAccess(user_id, orga_id);
			ArrayList<AccessLevels> accessLevels =  new ArrayList<>();
			Iterator<Object> iterator = accessList.iterator();
			while(iterator.hasNext()){
				Object[] objects = (Object[]) iterator.next();
				AccessLevels access = new AccessLevels();

				access.setOrga_id((int) objects[0]);
				access.setOrga_name(objects[1].toString());
				access.setLoca_id((int) objects[2]);
				access.setLoca_name(objects[3].toString());

				accessLevels.add(access);
			}
			return accessLevels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Harshad Padole
	//Method Purpose : get location for user by user id and orga id and loca_id
	@Override
	public ArrayList<AccessLevels> getDeptForUserAccess(int user_id, int orga_id, int loca_id) {
		try {
			List<Object> accessList =  userDao.getDeptForUserAccess(user_id, orga_id, loca_id);
			ArrayList<AccessLevels> accessLevels =  new ArrayList<>();
			Iterator<Object> iterator = accessList.iterator();
			while(iterator.hasNext()){
				Object[] objects = (Object[]) iterator.next();
				AccessLevels access = new AccessLevels();

				access.setOrga_id((int) objects[0]);
				access.setOrga_name(objects[1].toString());
				access.setLoca_id((int) objects[2]);
				access.setLoca_name(objects[3].toString());
				access.setDept_id((int) objects[4]);
				access.setDept_name(objects[5].toString());

				accessLevels.add(access);
			}
			return accessLevels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Harshad Padole
	//Method Purpose : Disable User mapped access
	@Override
	public int enableDisableUmap(int umap_id, int umap_status) {
		try {
			return userDao.enableDisableUmap(umap_id, umap_status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	//Method Created : Harshad Padole
	//Method Purpose : get remaining access to user while editing user access
	@Override
	public List<AccessLevels> getRemainUserAccess(int user_id, int orga_id, int loca_id,int dept_id) {
		try {
			List<Object> accessList =  userDao.getRemainUserAccess(user_id, orga_id, loca_id,dept_id);
			ArrayList<AccessLevels> accessLevels =  new ArrayList<>();
			Iterator<Object> iterator = accessList.iterator();
			while(iterator.hasNext()){
				Object[] objects = (Object[]) iterator.next();
				AccessLevels access = new AccessLevels();

				access.setOrga_id((int) objects[0]);
				access.setOrga_name(objects[1].toString());
				access.setLoca_id((int) objects[2]);
				access.setLoca_name(objects[3].toString());
				access.setDept_id((int) objects[4]);
				access.setDept_name(objects[5].toString());

				accessLevels.add(access);
			}
			return accessLevels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Harshad Padole
	//Method Purpose : Check employee id exist or not
	@Override
	public int CheckEmployeeExist(int user_id, String emp_id) {
		try {
			return userDao.CheckEmployeeExist(user_id, emp_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	//Method Created : Mugdha Chandratre
	//Method Purpose : Check email id exist or not
	@Override
	public int isEmailIdExist(int user_id, String email_id) {
		try {
			return userDao.isEmailIdExist(user_id, email_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	//Method Created : Mugdha Chandratre
	//Method Purpose : Check User name exist or not
	@Override
	public int isUserNameExist(int user_id, String user_name) {
		try {
			return userDao.isUserNameExist(user_id, user_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}



	//Method Created : Mahesh Kharote
	//Method Purpose : Get all Users assigned to Organization
	@Override
	public List<User> getUsersByOrganization(int orga_id) {
		try {
			if(userDao.getUsersByOrganization(orga_id) != null)
				return userDao.getUsersByOrganization(orga_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	//Method Created : Mahesh Kharote
	//Method Purpose : Get all Users assinged to OrganizationLocation
	@Override
	public List<User> getUsersByOrganizationLocation(int orga_id, int loca_id) {
		try {
			if(userDao.getUsersByOrganizationLocation(orga_id, loca_id) != null)
				return userDao.getUsersByOrganizationLocation(orga_id, loca_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Mahesh Kharote
	//Method Purpose : Get all Users assinged to OrganizationLocationDepartment
	@Override
	public List<User> getUsersByOrganizationLocationDepartment(int orga_id, int loca_id, int dept_id) {
		try {
			if(userDao.getUsersByOrganizationLocationDepartment(orga_id, loca_id, dept_id) != null)
				return userDao.getUsersByOrganizationLocationDepartment(orga_id, loca_id, dept_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Harshad Padole on 07-04-2016
	//Method Purpose : get reviewer and performer by user access and task

	@Override
	public <T> List<T> getPerformerReviewerByAccess(int user_id, String json) {
		try {
			List<T> result = userDao.getPerformerReviewerByAccess(user_id, json);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getUserProfile(int userId) {
		try {
			return userDao.getUserProfile(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateUserPic(String pic,int userId) {
		try {
			userDao.updateUserPic(pic,userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getPicName(int userId) {
		try {
			return userDao.getProfilePic(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getUserPassword(int UserId) {
		try {
			return userDao.getOriginalPassword(UserId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int changeNewPassword(String newPassword, int userId) {
		try {
			return userDao.changeNewPassword(newPassword,userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	//Method Created :Harshad Padole
	//Method Purpose : Check task exist for user or not while removing access
	@Override
	public String CheckTaskExistForUser(String json, HttpSession session) {
		try {
			return userDao.CheckTaskExistForUser(json, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String resetPassword(String user_username, String user_mailId) {
		try {
			return userDao.resetPassword(user_username,user_mailId);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

	//Method Created : Harshad Padole
	//Method Purpose : Get all users
	@Override
	public Map<Integer, String> getAllUser() {
		try {
			List<User> result = userDao.getAll(User.class);
			Map<Integer , String> User_list = new HashMap<Integer, String>();
			for(User temp:result){
				User_list.put(0, "--Select--");
				User_list.put(temp.getUser_id(), temp.getUser_first_name()+" "+temp.getUser_last_name());
			}
			return User_list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<User> getAllReportingUsersByOrganizationLocationDepartment(int orga_id, int loca_id, int dept_id) {
		try {
			if(userDao.getAllReportingUsersByOrganizationLocationDepartment(orga_id, loca_id, dept_id) != null)
				return userDao.getAllReportingUsersByOrganizationLocationDepartment(orga_id, loca_id, dept_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Mahesh Kharote
	//Method Purpose: Authenticate user from Harmony 
	@Override
	public String authenticateUserPeopleSoft(String empno, HttpSession session) {
		try {
			return userDao.authenticateUserPeopleSoft(empno, session);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> getAdminMailId() {
		try {
			return userDao.getAdminMailId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<User> getAllPOCUsers() {
		try {
			return userDao.getAllPOCUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}



