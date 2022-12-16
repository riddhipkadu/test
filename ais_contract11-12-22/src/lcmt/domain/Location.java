package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/*
 * Author : Harshad Padole
 * Created Date : 19/02/2016
 * Updated By : 
 * Updated Date : 
 *  
 * */

@Entity(name="mst_location")
public class Location {

	@Id
	@GeneratedValue
	private int loca_id;
	private String loca_name;
	private int loca_parent_id;
	private String loca_approval_status;
	private String loca_enable_status;
	private int loca_added_by;
	private Date loca_created_at;
	private String loca_short_name;
	
	public String getLoca_short_name() {
		return loca_short_name;
	}
	public void setLoca_short_name(String loca_short_name) {
		this.loca_short_name = loca_short_name;
	}
	public int getLoca_id() {
		return loca_id;
	}
	public void setLoca_id(int loca_id) {
		this.loca_id = loca_id;
	}
	public String getLoca_name() {
		return loca_name;
	}
	public void setLoca_name(String loca_name) {
		this.loca_name = loca_name;
	}
	public int getLoca_parent_id() {
		return loca_parent_id;
	}
	public void setLoca_parent_id(int loca_parent_id) {
		this.loca_parent_id = loca_parent_id;
	}
	public String getLoca_approval_status() {
		return loca_approval_status;
	}
	public void setLoca_approval_status(String loca_approval_status) {
		this.loca_approval_status = loca_approval_status;
	}
	public String getLoca_enable_status() {
		return loca_enable_status;
	}
	public void setLoca_enable_status(String loca_enable_status) {
		this.loca_enable_status = loca_enable_status;
	}
	public int getLoca_added_by() {
		return loca_added_by;
	}
	public void setLoca_added_by(int loca_added_by) {
		this.loca_added_by = loca_added_by;
	}
	public Date getLoca_created_at() {
		return loca_created_at;
	}
	public void setLoca_created_at(Date loca_created_at) {
		this.loca_created_at = loca_created_at;
	}
	
	
}
