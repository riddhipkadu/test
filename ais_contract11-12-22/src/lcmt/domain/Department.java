package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * Author : Mahesh Kharote
 * Created Date : 19/02/2016
 * Updated By : 
 * Updated Date : 
 *  
 * */
@Entity(name = "mst_department")
public class Department {
	
	@Id
	@GeneratedValue
	private int dept_id;
	private String dept_name;
	private int dept_parent_id;
	private String dept_approval_status;
	private String dept_enable_status;
	private int dept_added_by;
	private Date dept_created_at;
	private String dept_short_name;
	
	public String getDept_short_name() {
		return dept_short_name;
	}
	public void setDept_short_name(String dept_short_name) {
		this.dept_short_name = dept_short_name;
	}
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	public int getDept_parent_id() {
		return dept_parent_id;
	}
	public void setDept_parent_id(int dept_parent_id) {
		this.dept_parent_id = dept_parent_id;
	}
	public String getDept_approval_status() {
		return dept_approval_status;
	}
	public void setDept_approval_status(String dept_approval_status) {
		this.dept_approval_status = dept_approval_status;
	}
	public String getDept_enable_status() {
		return dept_enable_status;
	}
	public void setDept_enable_status(String dept_enable_status) {
		this.dept_enable_status = dept_enable_status;
	}
	public int getDept_added_by() {
		return dept_added_by;
	}
	public void setDept_added_by(int dept_added_by) {
		this.dept_added_by = dept_added_by;
	}
	public Date getDept_created_at() {
		return dept_created_at;
	}
	public void setDept_created_at(Date dept_created_at) {
		this.dept_created_at = dept_created_at;
	}
	
	
	

}

