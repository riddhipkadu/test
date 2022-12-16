package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity(name = "mst_area_of_expertise")
public class AreaOfExpertise {

	@Id
	@GeneratedValue
	private int area_expe_id;
	private String area_expe_name;
	private String area_expe_enable_status;
	private String area_expe_approval_status;
	private Date area_expe_created_at;
	private int area_expe_added_by;
	
	public int getArea_expe_id() {
		return area_expe_id;
	}
	public void setArea_expe_id(int area_expe_id) {
		this.area_expe_id = area_expe_id;
	}
	public String getArea_expe_name() {
		return area_expe_name;
	}
	public void setArea_expe_name(String area_expe_name) {
		this.area_expe_name = area_expe_name;
	}
	public String getArea_expe_enable_status() {
		return area_expe_enable_status;
	}
	public void setArea_expe_enable_status(String area_expe_enable_status) {
		this.area_expe_enable_status = area_expe_enable_status;
	}
	public String getArea_expe_approval_status() {
		return area_expe_approval_status;
	}
	public void setArea_expe_approval_status(String area_expe_approval_status) {
		this.area_expe_approval_status = area_expe_approval_status;
	}
	public Date getArea_expe_created_at() {
		return area_expe_created_at;
	}
	public void setArea_expe_created_at(Date area_expe_created_at) {
		this.area_expe_created_at = area_expe_created_at;
	}
	public int getArea_expe_added_by() {
		return area_expe_added_by;
	}
	public void setArea_expe_added_by(int area_expe_added_by) {
		this.area_expe_added_by = area_expe_added_by;
	}
	
	
	
}
