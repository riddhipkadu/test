package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="trn_escalation_mail_id")
public class LitigationEscalationMailId {
	
	@Id
	@GeneratedValue
	private int esc_id;
	private String esc_internal_resource;
	private String esc_law_firm;
	private String esc_appear_counsel;
	private String esc_others;
	private int esc_liti_id;
	private int esc_added_by;
	private Date esc_created_at;
	
	public int getEsc_liti_id() {
		return esc_liti_id;
	}
	public void setEsc_liti_id(int esc_liti_id) {
		this.esc_liti_id = esc_liti_id;
	}
	public int getEsc_id() {
		return esc_id;
	}
	public void setEsc_id(int esc_id) {
		this.esc_id = esc_id;
	}
	public String getEsc_internal_resource() {
		return esc_internal_resource;
	}
	public void setEsc_internal_resource(String esc_internal_resource) {
		this.esc_internal_resource = esc_internal_resource;
	}
	public String getEsc_law_firm() {
		return esc_law_firm;
	}
	public void setEsc_law_firm(String esc_law_firm) {
		this.esc_law_firm = esc_law_firm;
	}
	public String getEsc_appear_counsel() {
		return esc_appear_counsel;
	}
	public void setEsc_appear_counsel(String esc_appear_counsel) {
		this.esc_appear_counsel = esc_appear_counsel;
	}
	public String getEsc_others() {
		return esc_others;
	}
	public void setEsc_others(String esc_others) {
		this.esc_others = esc_others;
	}
	public int getEsc_added_by() {
		return esc_added_by;
	}
	public void setEsc_added_by(int esc_added_by) {
		this.esc_added_by = esc_added_by;
	}
	public Date getEsc_created_at() {
		return esc_created_at;
	}
	public void setEsc_created_at(Date esc_created_at) {
		this.esc_created_at = esc_created_at;
	}
	
}
