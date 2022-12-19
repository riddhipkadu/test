package lcmt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.DepartmentDao;
import lcmt.domain.Department;
import lcmt.service.DepartmentService;
import lcmt.service.UtilitiesService;



/*
 * Author: Mahesh Kharote
 * Date: 19/02/2016
 * Updated By: Mahesh Kharote
 * Updated Date: 19/02/2016
 * 
 * */



@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	DepartmentDao departmentDao;
	
	@Autowired
	UtilitiesService utilitiesService;
	
	@Autowired
	HttpSession HttpSession;
	
	//Method Created By: Mahesh Kharote
	//Method Purpose: Fetch values from controller, add additional values and send to dao
	@Override
	public void persist(Department department) {
		try {
			
			department.setDept_added_by(utilitiesService.getCurrentSessionUserId(HttpSession));
			department.setDept_created_at(utilitiesService.getCurrentDate());
			department.setDept_approval_status("1");
			department.setDept_enable_status("1");
			
			departmentDao.persist(department);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	//Method Created By: Harsahad Padole
		//Method Purpose: Update Department
		@Override
		public void updateDepartment(Department department) {
	        try {
	        	Department oldDepartment = getDepartmentById(department.getDept_id());
	        	Department newDepartment = new Department();
	        	//Set old data to newDepartment object
	        	newDepartment.setDept_id(oldDepartment.getDept_id());
	        	newDepartment.setDept_added_by(oldDepartment.getDept_added_by());
	        	newDepartment.setDept_created_at(oldDepartment.getDept_created_at());
	        	newDepartment.setDept_approval_status(oldDepartment.getDept_approval_status());
	        	newDepartment.setDept_enable_status(oldDepartment.getDept_enable_status());
	        	
	        	//Set new data to newDepartment object
	        	newDepartment.setDept_name(department.getDept_name());
	        	newDepartment.setDept_parent_id(department.getDept_parent_id());
	        	newDepartment.setDept_short_name(department.getDept_short_name());
	        	departmentDao.updateDepartment(newDepartment);
	        	
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	

	//Method Created By: Mahesh Kharote
	//Method Purpose: Fetch list of departments sent from dao
	@Override
	public List<Department> getAll() {
		try {
			return departmentDao.getAll(Department.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Mahesh Kharote
	//Method Purpose: Fetch department for edit from dao
	@Override
	public Department getDepartmentById(int dept_id) {

		try {
			return departmentDao.getDepartmentById(dept_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	

	//Method Created By: Harsahad Padole
	//Method Purpose: Update Department
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Integer> getMappedDepartments(int enti_orga_id, int enti_loca_id) {
		try {
			List<Integer> mappedDeptIds= new ArrayList<Integer>();
			mappedDeptIds = departmentDao.getMappedDepartments(enti_orga_id, enti_loca_id);
			return (ArrayList<Integer>) mappedDeptIds;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Harsahad Padole
	//Method Purpose: get all Department for user mapping
	@Override
	public Map<Integer , String> getAllDepartmentsForUserMapping(int user_orga_id, int user_loca_id) {
		try {
			List<Object> deptListForUserMapping = departmentDao.getAllDepartmentsForUserMapping(user_orga_id, user_loca_id);
			Map<Integer, String> dept_ListForUserMapping = new HashMap<Integer, String>();
			Iterator<Object> itr = deptListForUserMapping.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
				
				dept_ListForUserMapping.put(Integer.parseInt(object[0].toString()), object[1].toString());
				
			}
			
			return dept_ListForUserMapping;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Harsahad Padole
	//Method Purpose: get Department by id
	@Override
	public String getDepartmentNameById(int dept_id) {
		// TODO Auto-generated method stub
		Department dept = departmentDao.getDepartmentById(dept_id);
		return dept.getDept_name();
	}

	//Method Created : Harshad Padole
	//Method Purpose : Approve or disapprove Department
	@Override
	public int approveDisapproveDept(int dept_id, int dept_status) {
		// TODO Auto-generated method stub
		try {
			return departmentDao.approveDisapproveDept(dept_id, dept_status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
 
	//Method Created : Harshad Padole
	//Method Purpose : Enable or disable Department
	@Override
	public int enableDisableDept(int dept_id, int dept_status) {
		// TODO Auto-generated method stub
		try {
			return departmentDao.enableDisableDept(dept_id, dept_status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int isDeptNameExist(int dept_id, String dept_name) {
		try {
			return departmentDao.isDeptNameExist(dept_id, dept_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int isDeptShortNameExist(int dept_id, String dept_short_name) {
		try {
			return departmentDao.isDeptShortNameExist(dept_id, dept_short_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
