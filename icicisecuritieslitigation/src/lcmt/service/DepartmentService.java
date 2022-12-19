package lcmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import lcmt.domain.Department;

public interface DepartmentService {

	public void persist(Department department);
	public List<Department> getAll();
	public Department getDepartmentById(int dept_id);
	public void updateDepartment(Department department);
	public <T> ArrayList<T> getMappedDepartments(int enti_orga_id, int enti_loca_id);//Here we have array list because we have on parameter i.e. id in data if there are more than one use list
	public Map<Integer, String> getAllDepartmentsForUserMapping(int user_orga_id, int user_loca_id);
	public String getDepartmentNameById(int dept_id);
	public int approveDisapproveDept(int dept_id,int dept_status);
	public int enableDisableDept(int dept_id,int dept_status);
	public int isDeptNameExist(int dept_id, String dept_name);
	public int isDeptShortNameExist(int dept_id, String dept_short_name);
}
