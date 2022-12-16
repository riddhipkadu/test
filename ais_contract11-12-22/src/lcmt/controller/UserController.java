package lcmt.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lcmt.domain.AccessLevels;
import lcmt.domain.User;
import lcmt.service.DepartmentService;
import lcmt.service.DesignationService;
import lcmt.service.EntityMappingService;
import lcmt.service.LocationService;
import lcmt.service.OrganizationService;
import lcmt.service.UserService;

@Controller
@RequestMapping("/*")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	OrganizationService organizationService;
	@Autowired
	DesignationService designationService;
	@Autowired
	LocationService locationService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	EntityMappingService entityMappingService;

	private @Value("#{config['project_name'] ?: 'null'}") String project_name;

	//Method Created By: Mahesh Kharote
	//Method Purpose: Check username and password and set session attributes for further use
	@RequestMapping(value = "/authenticateUserPeopleSoft" , method = RequestMethod.POST)
	public String authenticateUser(HttpSession session, String empno){
		try {
			//System.out.println("In authenticate user from PeopleSoft");
			String loginStatus = userService.authenticateUserPeopleSoft(empno, session);
			if(loginStatus == null ){

				return "redirect:userLogin";
			}
			else{
				User userForUsername = userService.getUserById(Integer.parseInt(loginStatus));
				session.setAttribute("username", userForUsername.getUser_username());
				session.setAttribute("sess_user_id", loginStatus);

				session.setAttribute("fullName", userService.getUserFullNameById(Integer.parseInt(session.getAttribute("sess_user_id").toString())));
				if(session.getAttribute("firstLogin").equals("1")){
					return "redirect:ChangePassword";
				}
				else{
					 
					return "redirect:myAccount";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}





	//Method Created By: Mugdha Chandratre
	//Method Purpose: To set date and time format
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			sdf.setLenient(true);
			binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//Method Created By: Mahesh Kharote
	//Method Purpose: Test page for harmony
	@RequestMapping(value = "/pageForPeopleSoft" , method = RequestMethod.GET)
	public ModelAndView pageForHarmony(){
		try {
			ModelAndView modelAndView = new ModelAndView("pageForPeopleSoft");
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	//Method Created By: Mahesh Kharote
	//Method Purpose: Check username and password and set session attributes for further use
	@RequestMapping(value = "/userLogin" , method = RequestMethod.GET)
	public ModelAndView userLogin(HttpSession session){
		try {
			session.setAttribute("sess_user_id", 0);
			session.setAttribute("username", "Guest");
			ModelAndView modelAndView = new ModelAndView("userLogin");
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Mahesh Kharote
	//Method Purpose: Check username and password and set session attributes for further use
	@RequestMapping(value = "/authenticateUser" , method = RequestMethod.POST)
	public String authenticateUser(HttpSession session, String user_username, String user_userpassword){
		try {
			String loginStatus = userService.authenticateUser(user_username, user_userpassword,session);
			if(loginStatus == "Incorrect password"  || loginStatus == "Incorrect username" || loginStatus == null ){
				if(loginStatus.equals("Incorrect username")){
					session.setAttribute("loginErrorType", "InvalidUsername");
				}
				else{
					if(loginStatus == "Incorrect password"){
						session.setAttribute("loginErrorType", "InvalidPassword");
					}
				}
				return "redirect:userLogin";
			}
			else{
				session.setAttribute("username", user_username);
				session.setAttribute("sess_user_id", loginStatus);
				session.setAttribute("fullName", userService.getUserFullNameById(Integer.parseInt(session.getAttribute("sess_user_id").toString())));
				if(session.getAttribute("firstLogin").equals(1)){
					return "redirect:ChangePassword";
				}
				else{
					return "redirect:dashboard";
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//Method Created By: Mahesh Kharote
	//Method Purpose: Check username and password and set session attributes for further use
	@RequestMapping(value = "/authenticationFailed", method = RequestMethod.GET)
	public ModelAndView authenticationFailed(HttpSession session){
		try {
			ModelAndView modelAndView = new ModelAndView("userLogin");
			if(session.getAttribute("loginErrorType").toString().equals("InvalidUsername")){
				//modelAndView.addObject("errorType", "InvalidUsername");
			}
			else{
				if(session.getAttribute("loginErrorType").toString().equals("InvalidPassword")){
					//modelAndView.addObject("errorType", "InvalidPassword");
				}
			}
			return modelAndView;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//Method Created By: Mahesh Kharote
	//Method Purpose: Check username and password and set session attributes for further use
	@RequestMapping(value = "/userLogout" , method = RequestMethod.GET)
	public ModelAndView userLogout(HttpSession session){
		try {

			session.invalidate();
			ModelAndView model = new ModelAndView("userLogin");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Rahul Shinde
	//Method Purpose: Show User Profile
	@RequestMapping(value = "/myAccount" , method = RequestMethod.GET)
	public ModelAndView myAccount(HttpSession session){
		try {
			ModelAndView model = new ModelAndView("myAccount");
			model.addObject("userInfo", userService.getUserProfile(Integer.parseInt(session.getAttribute("sess_user_id").toString())));
			return model;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	//Method Created By: Mugdha Chandratre
	//Method Purpose: Fetch all list of user from database
	@RequestMapping(value = "/listUsers" , method = RequestMethod.GET)
	public ModelAndView listUsers(){
		try {
			ModelAndView modelAndView = new ModelAndView("listUsers","allUsers",userService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Mugdha Chandratre
	//Method Purpose: Load new user form
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addUser(){
		try {
			ModelAndView modelAndView = new ModelAndView("addUser","user",new User());
			modelAndView.addObject("allDesignations", designationService.getAll());
			modelAndView.addObject("allOrganizations", organizationService.getAll());
			//modelAndView.addObject("allUser", userService.getUsersByOrganizationLocationDepartment(orga_id, loca_id, dept_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Mugdha Chandratre
	//Method Purpose: Add new user into the database
	@RequestMapping(value = "/saveUser",method = RequestMethod.POST)
	public String saveUser(User user){
		try {
			userService.persist(user);
			return "redirect:listUsers";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	//Method Created By: Mugdha Chandratre
	//Method Purpose: Load edit user form
	//Updated By: Mahesh Kharote
	//Purpose: Edit user name on edit user page
	@RequestMapping(value = "/editUser" , method = RequestMethod.GET)
	public ModelAndView editUser(int user_id){
		try {
			User user = userService.getUserById(user_id);

			Map<Integer ,String> user_role = new HashMap<Integer , String>();
			user_role.put(0, "-- Select --");
			user_role.put(1, "Executor");
			user_role.put(2 , "Evaluator");
			user_role.put(3 , "Function Head");
			user_role.put(4 , "Unit Head"); //Added by Harshad FH,UH,EH on 2 May 2016
			user_role.put(5 , "Entity Head");
			ModelAndView modelAndView = new ModelAndView("editUser","user",user);
			int orga = user.getUser_organization_id();
			int loca = user.getUser_location_id();
			int dept = user.getUser_department_id();
			modelAndView.addObject("user_by_department", userService.getAllReportingUsersByOrganizationLocationDepartment(orga, loca, dept));
			modelAndView.addObject("allOrganizations", entityMappingService.getAllOrganizations());
			modelAndView.addObject("allLocations", entityMappingService.getAllLocations());
			modelAndView.addObject("allDepartments", entityMappingService.getAllDepartments());
			modelAndView.addObject("allDesignations",designationService.getAllDesignationForDropDown());
			modelAndView.addObject("allUser", userService.getAll());
			modelAndView.addObject("roleList", user_role);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	//Method Created By: Mugdha Chandratre
	//Method Purpose: Update particular user into the database
	@RequestMapping(value = "/updateUser" , method = RequestMethod.POST)
	public String updateUser(User user){
		try {
			userService.updateUser(user);
			return "redirect:listUsers";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}


	//Method created : Harshad Padole
	//Method purpose : Approve or disapprove entity
	@RequestMapping(value = "/approveDisapproveUser" , method = RequestMethod.POST)
	public @ResponseBody String approveDisapproveUser(@RequestBody String jsonString) throws ParseException{
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int user_id = Integer.parseInt(jsonobj.get("user_id").toString());
			int user_status = Integer.parseInt(jsonobj.get("user_status").toString());
			int res = userService.approveDisapproveUser(user_id, user_status);
			return String.valueOf(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(0);

	}

	//Method created : Harshad Padole
	//Method purpose : Enable or disable entity
	@RequestMapping(value = "/enableDisableUser" , method = RequestMethod.POST)
	public @ResponseBody String enableDisableUser(@RequestBody String jsonString) throws ParseException{
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int user_id = Integer.parseInt(jsonobj.get("user_id").toString());
			int user_status = Integer.parseInt(jsonobj.get("user_status").toString());
			int res = userService.enableDisableUser(user_id, user_status);
			return String.valueOf(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(0);

	}

	//Method Created : Harshad padole
	//Method Purpose : Load Set access Level page 
	@RequestMapping(value = "/setAccessLevel", method = RequestMethod.GET)
	public ModelAndView setAccessLevel(){
		try {
			ModelAndView modelAndView = new ModelAndView("setAccessLevel");
			modelAndView.addObject("allUsers", userService.getAllUserNotSetAccess());
			modelAndView.addObject("allOrganizations", organizationService.getAll());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//Method Created : Harshad Padole
	//Method Purpose : Get mapped access using ajax call
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getAccessList" , method = RequestMethod.POST)
	public @ResponseBody String getMappedaccess(@RequestBody String jsonString) throws ParseException{
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString);
			int org_id = Integer.parseInt(jsonObj.get("user_orga_id").toString());
			int loc_id = Integer.parseInt(jsonObj.get("user_loca_id").toString());
			int dept_id = Integer.parseInt(jsonObj.get("user_dept_id").toString());

			List<AccessLevels> getAccessList = userService.getUserAccess(org_id, loc_id,dept_id);
			JSONArray jsonArray = new JSONArray();
			Iterator<AccessLevels> itr = getAccessList.iterator();
			while (itr.hasNext()) {
				JSONObject jsonobject = new JSONObject();
				AccessLevels user = itr.next();

				jsonobject.put("org_id",user.getOrga_id());
				jsonobject.put("loc_id",user.getLoca_id());
				jsonobject.put("dept_id",user.getDept_id());
				jsonobject.put("orga_name",user.getOrga_name());
				jsonobject.put("loca_name",user.getLoca_name());
				jsonobject.put("dept_name",user.getDept_name());
				jsonArray.add(jsonobject);

			}
			return jsonArray.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//Method Created : Harshad Padole
	//Method Purpose : Save user access
	@RequestMapping(value = "/setAccessToUser",method = RequestMethod.POST)
	public @ResponseBody String setAccessToUser(@RequestBody String jsonStringStoreData) throws ParseException{
		try {
			userService.serAccessToUser(jsonStringStoreData);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error";

	}
	//Method Created : Harshad Padole
	//Method Purpose : Load edit access level page
	@RequestMapping(value = "/editAcccessLevel",method = RequestMethod.GET)
	public ModelAndView editAccesslevel(@RequestParam("user_id") int user_id){
		try {
			ModelAndView modelAndView = new ModelAndView("editAccessLevel");
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	//Method Created : Harshad Padole
	//Method Purpose : get user mapped access
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getUserMappedAccess",method = RequestMethod.POST)
	public @ResponseBody String getUserMappedAccess(@RequestBody String jsonString)throws ParseException{
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString);
			int user_id = Integer.parseInt(jsonObj.get("user_id").toString());
			List<AccessLevels> getAccessList = userService.getUserAccessByUserId(user_id);
			JSONArray jsonArray = new JSONArray();
			Iterator<AccessLevels> itr = getAccessList.iterator();
			while (itr.hasNext()) {
				JSONObject jsonobject = new JSONObject();
				AccessLevels user = itr.next();
				jsonobject.put("umap_id",user.getUmap_id());
				jsonobject.put("org_id",user.getOrga_id());
				jsonobject.put("loc_id",user.getLoca_id());
				jsonobject.put("dept_id",user.getDept_id());
				jsonobject.put("orga_name",user.getOrga_name());
				jsonobject.put("loca_name",user.getLoca_name());
				jsonobject.put("dept_name",user.getDept_name());
				jsonArray.add(jsonobject);

			}
			return jsonArray.toJSONString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString;

	}

	//Method Created : Harshad Padole
	//Method Purpose : get organization for user access by user id
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getOrgaforUserAccess",method = RequestMethod.POST)
	public @ResponseBody String getOrgaforUserAccess(@RequestBody String jsonString)throws ParseException{
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString);
			int user_id = Integer.parseInt(jsonObj.get("user_id").toString());
			List<AccessLevels> getAccessList = userService.getOrgaForUserAccess(user_id);
			JSONArray jsonArray = new JSONArray();
			Iterator<AccessLevels> itr = getAccessList.iterator();
			while (itr.hasNext()) {
				JSONObject jsonobject = new JSONObject();
				AccessLevels user = itr.next();
				jsonobject.put("orga_id",user.getOrga_id());
				jsonobject.put("orga_name",user.getOrga_name());
				jsonArray.add(jsonobject);

			}
			return jsonArray.toJSONString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString;

	}

	//Method Created : Harshad Padole
	//Method Purpose : get Location for user access by user id and orga_id
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getLocaforUserAccess",method = RequestMethod.POST)
	public @ResponseBody String getLocaforUserAccess(@RequestBody String jsonString)throws ParseException{
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString);
			int user_id = Integer.parseInt(jsonObj.get("user_id").toString());
			int orga_id = Integer.parseInt(jsonObj.get("orga_id").toString());
			List<AccessLevels> getAccessList = userService.getLocaForUserAccess(user_id, orga_id);
			JSONArray jsonArray = new JSONArray();
			Iterator<AccessLevels> itr = getAccessList.iterator();
			while (itr.hasNext()) {
				JSONObject jsonobject = new JSONObject();
				AccessLevels user = itr.next();
				jsonobject.put("orga_id",user.getOrga_id());
				jsonobject.put("orga_name",user.getOrga_name());
				jsonobject.put("loca_id", user.getLoca_id());
				jsonobject.put("loca_name", user.getLoca_name());
				jsonArray.add(jsonobject);

			}
			return jsonArray.toJSONString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString;

	}

	//Method Created : Harshad Padole
	//Method Purpose : get Department for user access by user id,orga_id and loc_id
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getDeptforUserAccess",method = RequestMethod.POST)
	public @ResponseBody String getDeptforUserAccess(@RequestBody String jsonString)throws ParseException{
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString);
			int user_id = Integer.parseInt(jsonObj.get("user_id").toString());
			int orga_id = Integer.parseInt(jsonObj.get("orga_id").toString());
			int loca_id = Integer.parseInt(jsonObj.get("loca_id").toString());
			List<AccessLevels> getAccessList = userService.getDeptForUserAccess(user_id, orga_id, loca_id);
			JSONArray jsonArray = new JSONArray();
			Iterator<AccessLevels> itr = getAccessList.iterator();
			while (itr.hasNext()) {
				JSONObject jsonobject = new JSONObject();
				AccessLevels user = itr.next();
				jsonobject.put("orga_id",user.getOrga_id());
				jsonobject.put("orga_name",user.getOrga_name());
				jsonobject.put("loca_id", user.getLoca_id());
				jsonobject.put("loca_name", user.getLoca_name());
				jsonobject.put("dept_id", user.getDept_id());
				jsonobject.put("dept_name", user.getDept_name());
				jsonArray.add(jsonobject);

			}
			return jsonArray.toJSONString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString;

	}

	//Method Created : Harshad Padole
	//Method purpose : Disable User mapped access
	@RequestMapping(value = "/disableUmapAccess" , method = RequestMethod.POST)
	public @ResponseBody String disableUmapAccess(@RequestBody String jsonString) throws ParseException{
		JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString);
		int umap_id      = Integer.parseInt(jsonObj.get("umap_id").toString());
		int umap_status = Integer.parseInt(jsonObj.get("umap_status").toString());
		try {
			int res = userService.enableDisableUmap(umap_id, umap_status);
			return String.valueOf(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(0);
	}

	//Method Created : Harshad Padole
	//Method Purpose : get remaining access to user while editing user access
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getRemainUserAccess",method = RequestMethod.POST)
	public @ResponseBody String getRemainUserAccess(@RequestBody String jsonString)throws ParseException{
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString);
			int user_id = Integer.parseInt(jsonObj.get("user_id").toString());
			int orga_id = Integer.parseInt(jsonObj.get("orga_id").toString());
			int loca_id = Integer.parseInt(jsonObj.get("loca_id").toString());
			int dept_id = Integer.parseInt(jsonObj.get("dept_id").toString());

			List<AccessLevels> getAccessList = userService.getRemainUserAccess(user_id, orga_id, loca_id,dept_id);
			JSONArray jsonArray = new JSONArray();
			Iterator<AccessLevels> itr = getAccessList.iterator();
			while (itr.hasNext()) {
				JSONObject jsonobject = new JSONObject();
				AccessLevels user = itr.next();
				jsonobject.put("orga_id",user.getOrga_id());
				jsonobject.put("orga_name",user.getOrga_name());
				jsonobject.put("loca_id", user.getLoca_id());
				jsonobject.put("loca_name", user.getLoca_name());
				jsonobject.put("dept_id", user.getDept_id());
				jsonobject.put("dept_name", user.getDept_name());
				jsonArray.add(jsonobject);

			}
			return jsonArray.toJSONString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString;

	}

	//Method Created : Harshad Padole
	//Method Purpose : Check employee id exist or not
	@RequestMapping(value="/checkExistEmployeeId" , method = RequestMethod.POST)
	public @ResponseBody String checkExistEmployeeId(@RequestBody String jsonString) throws ParseException {

		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int user_id = Integer.parseInt(jsonObject.get("user_id").toString());
			String emp_id = jsonObject.get("emp_id").toString();
			int res = userService.CheckEmployeeExist(user_id, emp_id);
			return String.valueOf(res);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	//Method Created : Mugdha Chandratre
	//Method Purpose : Verify if email id is exist or not
	@RequestMapping(value="/isEmailIdExist" , method = RequestMethod.POST)
	public @ResponseBody String isEmailIdExist(@RequestBody String jsonString) throws ParseException {

		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int user_id = Integer.parseInt(jsonObject.get("user_id").toString());
			String email_id = jsonObject.get("email_id").toString();
			int result = userService.isEmailIdExist(user_id, email_id);
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	//Method Created :Harshad Padole
	//Method Purpose : Verify if user name id is exist or not
	@RequestMapping(value="/isUserNameExist" , method = RequestMethod.POST)
	public @ResponseBody String isUserNameExist(@RequestBody String jsonString) throws ParseException {

		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int user_id = Integer.parseInt(jsonObject.get("user_id").toString());
			String user_name = jsonObject.get("user_name").toString();
			int result = userService.isUserNameExist(user_id, user_name);
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	//Author : Rahul Shinde
	@RequestMapping(value = "/uploadProfilePic", method = RequestMethod.POST)
	public @ResponseBody  ModelAndView uploadProfilePic(@RequestParam("profile_pic") MultipartFile file1,HttpSession session) throws IOException {

		if(file1.getSize() > 0){

			// Creating the directory to store file
			//String rootPath = System.getProperty("catalina.base");
			File dir = new File("D:/"+project_name+"/profile_pics/");
			if (!dir.exists())
				dir.mkdirs();

			File newFile = new File("D:/"+project_name+"/profile_pics/pic_"+session.getAttribute("sess_user_id")+".jpg");
			if (!newFile.exists()) {  
				newFile.createNewFile();  
			}  

			String pic = "pic_"+session.getAttribute("sess_user_id")+".jpg";
			userService.updateUserPic(pic,Integer.parseInt(session.getAttribute("sess_user_id").toString()));

			@SuppressWarnings("resource")
			OutputStream outputStream = new FileOutputStream(newFile);
			outputStream.write(file1.getBytes());
		}

		ModelAndView modelAndView = new ModelAndView("myAccount");
		modelAndView.addObject("userInfo", userService.getUserProfile(Integer.parseInt(session.getAttribute("sess_user_id").toString())));
		return modelAndView;
	}

	//Author : Rahul Shinde
	@RequestMapping(value = "/getProfilePic", method = RequestMethod.GET, produces = "images/jpeg; charset=utf-8")
	public @ResponseBody byte[] getProfilePicData(HttpSession session) {
		try {

			String fileName = userService.getPicName(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			if(fileName.equals("NA") || fileName.equals("")){
				Path path = Paths.get("D:/"+project_name+"/profile_pics/defaultUser.jpg");
				byte[] imageData = Files.readAllBytes(path);
				return imageData;
			}
			else{
				Path path = Paths.get("D:/"+project_name+"/profile_pics/"+fileName);
				byte[] imageData = Files.readAllBytes(path);
				return imageData;
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Rahul Shinde
	//Method Purpose: To Change Password
	@RequestMapping(value = "/ChangePassword" , method = RequestMethod.GET)
	public ModelAndView ChangePassword(HttpSession session){
		try {

			ModelAndView modelAndView = new ModelAndView("ChangePassword");
			return modelAndView;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	


	//Method Created :Rahul Shinde
	//Method Purpose : get Original Password
	@RequestMapping(value="/getOriginalPassword" , method = RequestMethod.POST)
	public @ResponseBody String getOriginalPass(HttpSession session) throws ParseException {

		try {
			String oldPassword = userService.getUserPassword(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			return oldPassword;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	//Method Created By: Rahul Shinde
	//Method Purpose: To Change Password
	@RequestMapping(value = "/ChangeNewPassword" , method = RequestMethod.POST)
	public ModelAndView ChangeNewPassword(@RequestParam("confPassword") String newPass, HttpSession session){
		try {
			int res = userService.changeNewPassword(newPass,Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			//session.setAttribute("firstLogin", null);
			ModelAndView modelAndView = new ModelAndView("ChangePassword");
			modelAndView.addObject("result",res);
			session.setAttribute("firstLogin", "0");
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//Method Created :Harshad Padole
	//Method Purpose : Check task exist for user or not while removing access
	@RequestMapping(value="/checkExistTaskForUser" , method = RequestMethod.POST)
	public @ResponseBody String checkExistTaskForUser(@RequestBody String json, HttpSession session) throws ParseException {
		try {
			String count = userService.CheckTaskExistForUser(json, session);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@RequestMapping(value = "/resetUserPassword", method = RequestMethod.POST)
	public ModelAndView resetUserPassword(@RequestParam("user_reset_password_mail_id") String user_reset_password_mail_id,@RequestParam("user_reset_password_username") String user_reset_username){
		try {

			ModelAndView modelAndView = new ModelAndView("userLogin");

			String resultStatus = userService.resetPassword(user_reset_username, user_reset_password_mail_id);
			if(resultStatus == "fail"){
				modelAndView.addObject("ValidationFalied","Failed");
				modelAndView.addObject("loginErrorType"," ");
			}
			if(resultStatus == "true"){
				modelAndView.addObject("ValidationFalied","Success");
				modelAndView.addObject("loginErrorType"," ");
			}
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="/masters",method = RequestMethod.GET)
	public ModelAndView masters(){
		try {
			ModelAndView modelAndView = new ModelAndView("masters");
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getAllUsersByOrganizationLocationDepartment" , method = RequestMethod.POST)
	public @ResponseBody String getAllUsersByOrganizationLocationDepartment(@RequestBody String jsonString) throws ParseException{
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString);
			JSONArray userssends = new JSONArray();
			List<User> usersByOrganizationLocationDepartment = userService.getUsersByOrganizationLocationDepartment(Integer.parseInt(jsonObj.get("tmap_orga_id").toString()), Integer.parseInt(jsonObj.get("tmap_loca_id").toString()) , Integer.parseInt(jsonObj.get("tmap_dept_id").toString()));

			if(usersByOrganizationLocationDepartment != null){
				Iterator<User> userList = usersByOrganizationLocationDepartment.iterator();
				while (userList.hasNext()) {
					User user = (User) userList.next();

					JSONObject addUserToSend = new JSONObject();
					addUserToSend.put("user_id", user.getUser_id());
					addUserToSend.put("user_name", user.getUser_first_name()+" "+user.getUser_last_name());
					addUserToSend.put("user_role", user.getUser_role_id());

					userssends.add(addUserToSend);
				}
			}

			return userssends.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value="/logs",method = RequestMethod.GET)
	public ModelAndView logs(){
		try {
			ModelAndView modelAndView = new ModelAndView("logs");
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created :	Tejashri Zurunge
	//Method Purpose : 	get all reporting person 
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getAllReportingUsersByOrganizationLocationDepartment" , method = RequestMethod.POST)
	public @ResponseBody String getAllReportingUsersByOrganizationLocationDepartment(@RequestBody String jsonString) throws ParseException{
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString);
			JSONArray userssends = new JSONArray();
			List<User> usersByOrganizationLocationDepartment = userService.getAllReportingUsersByOrganizationLocationDepartment(Integer.parseInt(jsonObj.get("tmap_orga_id").toString()), Integer.parseInt(jsonObj.get("tmap_loca_id").toString()) , Integer.parseInt(jsonObj.get("tmap_dept_id").toString()));

			if(usersByOrganizationLocationDepartment != null){
				Iterator<User> userList = usersByOrganizationLocationDepartment.iterator();
				while (userList.hasNext()) {
					User user = (User) userList.next();

					JSONObject addUserToSend = new JSONObject();
					addUserToSend.put("user_id", user.getUser_id());
					addUserToSend.put("user_name", user.getUser_first_name()+" "+user.getUser_last_name());
					addUserToSend.put("user_role", user.getUser_role_id());

					userssends.add(addUserToSend);
				}
			}

			return userssends.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}