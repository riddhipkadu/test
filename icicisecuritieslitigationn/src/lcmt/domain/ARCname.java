package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
@Entity(name="mst_ARC_name")
public class ARCname {
	@Id
	@GeneratedValue
	private int arc_id;
	private String arc_name;
	private String arc_contact_person;
	private String arc_contact_no;
	private String arc_email_id;
	@Size(max = 3900)
	private String arc_address;
	private Date arc_created_at;
	private Date arc_updated_at;
	private int arc_added_by;
	private String arc_enable_status;
	private String arc_approval_status;
	
	public int getArc_id() {
		return arc_id;
	}
	public void setArc_id(int arc_id) {
		this.arc_id = arc_id;
	}
	public String getArc_name() {
		return arc_name;
	}
	public void setArc_name(String arc_name) {
		this.arc_name = arc_name;
	}
	
	public String getArc_contact_person() {
		return arc_contact_person;
	}
	public void setArc_contact_person(String arc_contact_person) {
		this.arc_contact_person = arc_contact_person;
	}
	public String getArc_contact_no() {
		return arc_contact_no;
	}
	public void setArc_contact_no(String arc_contact_no) {
		this.arc_contact_no = arc_contact_no;
	}
	public String getArc_email_id() {
		return arc_email_id;
	}
	public void setArc_email_id(String arc_email_id) {
		this.arc_email_id = arc_email_id;
	}
	public String getArc_address() {
		return arc_address;
	}
	public void setArc_address(String arc_address) {
		this.arc_address = arc_address;
	}
	public Date getArc_created_at() {
		return arc_created_at;
	}
	public void setArc_created_at(Date arc_created_at) {
		this.arc_created_at = arc_created_at;
	}
	public Date getArc_updated_at() {
		return arc_updated_at;
	}
	public void setArc_updated_at(Date arc_updated_at) {
		this.arc_updated_at = arc_updated_at;
	}
	public int getArc_added_by() {
		return arc_added_by;
	}
	public void setArc_added_by(int arc_added_by) {
		this.arc_added_by = arc_added_by;
	}
	public String getArc_enable_status() {
		return arc_enable_status;
	}
	public void setArc_enable_status(String arc_enable_status) {
		this.arc_enable_status = arc_enable_status;
	}
	public String getArc_approval_status() {
		return arc_approval_status;
	}
	public void setArc_approval_status(String arc_approval_status) {
		this.arc_approval_status = arc_approval_status;
	}
	
	
}
