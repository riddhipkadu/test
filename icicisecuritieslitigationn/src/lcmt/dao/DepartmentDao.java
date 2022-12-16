package lcmt.dao;

import java.util.List;

import lcmt.domain.Department;

public interface DepartmentDao {
	public void persist(Object obj);
	public <T> List<T> getAll(Class<T> clazz);
	public Department getDepartmentById(int dept_id);
	public void updateDepartment(Department department);
	public <T> List<T> getMappedDepartments(int enti_orga_id, int enti_loca_id);
	public <T> List<T> getAllDepartmentsForUserMapping(int user_orga_id, int user_loca_id);	
	public int approveDisapproveDept(int dept_id,int dept_status);
	public int enableDisableDept(int dept_id,int dept_status);
	public int isDeptNameExist(int dept_id, String dept_name);
	public int isDeptShortNameExist(int dept_id, String dept_short_name);

}
