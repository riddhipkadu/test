package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * Author : Harshad Padole
 * Created Date : 09/08/2016
 * Updated By : 
 * Updated Date : 
 *  
 * */

@Entity(name="mst_litigation_type")
public class LitigationType {

	@Id
	@GeneratedValue
	private int liti_type_id;
	private String liti_type_name;
	private int liti_type_parent_id;
	private String liti_type_approval_status;
	private String liti_type_enable_status;
	private int liti_type_added_by;
	private Date liti_type_created_at;
	public int getLiti_type_id() {
		return liti_type_id;
	}
	public void setLiti_type_id(int liti_type_id) {
		this.liti_type_id = liti_type_id;
	}
	public String getLiti_type_name() {
		return liti_type_name;
	}
	public void setLiti_type_name(String liti_type_name) {
		this.liti_type_name = liti_type_name;
	}
	public int getLiti_type_parent_id() {
		return liti_type_parent_id;
	}
	public void setLiti_type_parent_id(int liti_type_parent_id) {
		this.liti_type_parent_id = liti_type_parent_id;
	}
	public String getLiti_type_approval_status() {
		return liti_type_approval_status;
	}
	public void setLiti_type_approval_status(String liti_type_approval_status) {
		this.liti_type_approval_status = liti_type_approval_status;
	}
	public String getLiti_type_enable_status() {
		return liti_type_enable_status;
	}
	public void setLiti_type_enable_status(String liti_type_enable_status) {
		this.liti_type_enable_status = liti_type_enable_status;
	}
	public int getLiti_type_added_by() {
		return liti_type_added_by;
	}
	public void setLiti_type_added_by(int liti_type_added_by) {
		this.liti_type_added_by = liti_type_added_by;
	}
	public Date getLiti_type_created_at() {
		return liti_type_created_at;
	}
	public void setLiti_type_created_at(Date liti_type_created_at) {
		this.liti_type_created_at = liti_type_created_at;
	}
	
	
	
	
	
	
	
}
