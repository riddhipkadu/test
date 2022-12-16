package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name="mst_tech_support")
public class TechSupport {
	@Id
	@GeneratedValue
	private int supp_id;
	private String supp_user_name;
	private String supp_user_mobile;
	private String supp_user_email;
	@Size(max = 3900)
	private String supp_user_message;
	private Date supp_created_at;
	private int supp_added_by;
	
	public int getSupp_id() {
		return supp_id;
	}
	public void setSupp_id(int supp_id) {
		this.supp_id = supp_id;
	}
	public String getSupp_user_name() {
		return supp_user_name;
	}
	public void setSupp_user_name(String supp_user_name) {
		this.supp_user_name = supp_user_name;
	}
	public String getSupp_user_mobile() {
		return supp_user_mobile;
	}
	public void setSupp_user_mobile(String supp_user_mobile) {
		this.supp_user_mobile = supp_user_mobile;
	}
	public String getSupp_user_email() {
		return supp_user_email;
	}
	public void setSupp_user_email(String supp_user_email) {
		this.supp_user_email = supp_user_email;
	}
	public String getSupp_user_message() {
		return supp_user_message;
	}
	public void setSupp_user_message(String supp_user_message) {
		this.supp_user_message = supp_user_message;
	}
	public Date getSupp_created_at() {
		return supp_created_at;
	}
	public void setSupp_created_at(Date supp_created_at) {
		this.supp_created_at = supp_created_at;
	}
	public int getSupp_added_by() {
		return supp_added_by;
	}
	public void setSupp_added_by(int supp_added_by) {
		this.supp_added_by = supp_added_by;
	}
}
