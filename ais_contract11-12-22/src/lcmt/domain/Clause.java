package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * Author : Akash Jadhav
 * Created Date : 12/10/2022
 * Updated By : 
 * Updated Date : 
 *  
 * */

@Entity (name="mst_clause_name")
public class Clause {
	
	
	@Id
	@GeneratedValue
	
	private int cls_id;
	private String cls_name;
	private int cls_added_by;
	private Date cls_created_at;
	private Date cls_updated_at;
	
	public int getCls_id() {
		return cls_id;
	}
	public void setCls_id(int cls_id) {
		this.cls_id = cls_id;
	}
	public String getCls_name() {
		return cls_name;
	}
	public void setCls_name(String cls_name) {
		this.cls_name = cls_name;
	}
	public int getCls_added_by() {
		return cls_added_by;
	}
	public void setCls_added_by(int cls_added_by) {
		this.cls_added_by = cls_added_by;
	}
	public Date getCls_created_at() {
		return cls_created_at;
	}
	public void setCls_created_at(Date cls_created_at) {
		this.cls_created_at = cls_created_at;
	}
	public Date getCls_updated_at() {
		return cls_updated_at;
	}
	public void setCls_updated_at(Date cls_updated_at) {
		this.cls_updated_at = cls_updated_at;
	}

}
