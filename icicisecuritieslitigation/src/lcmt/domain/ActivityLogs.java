package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="trn_activity_logs")
public class ActivityLogs {

	@Id
	@GeneratedValue
	private int log_id;
	private int log_activity_id; // activity primary key 
	private int log_sub_activity_id;
	private int log_assinged_to_id;
	private int log_secondary_responsible_person;
	private int log_third_responsible_person;
	private int log_other_responsible_person;
	private String log_activity;
	private String log_sub_activity; // sub-activity primary key
	private String log_related_to;
	private Date log_created_at;
	private int log_added_by;
	private String log_related_name; // name of specific log related
	
	public String getLog_related_name() {
		return log_related_name;
	}
	public void setLog_related_name(String log_related_name) {
		this.log_related_name = log_related_name;
	}
	public int getLog_id() {
		return log_id;
	}
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}
	public int getLog_activity_id() {
		return log_activity_id;
	}
	public void setLog_activity_id(int log_activity_id) {
		this.log_activity_id = log_activity_id;
	}
	public int getLog_sub_activity_id() {
		return log_sub_activity_id;
	}
	public void setLog_sub_activity_id(int log_sub_activity_id) {
		this.log_sub_activity_id = log_sub_activity_id;
	}
	public int getLog_assinged_to_id() {
		return log_assinged_to_id;
	}
	public void setLog_assinged_to_id(int log_assinged_to_id) {
		this.log_assinged_to_id = log_assinged_to_id;
	}
	public String getLog_activity() {
		return log_activity;
	}
	public void setLog_activity(String log_activity) {
		this.log_activity = log_activity;
	}
	public String getLog_sub_activity() {
		return log_sub_activity;
	}
	public void setLog_sub_activity(String log_sub_activity) {
		this.log_sub_activity = log_sub_activity;
	}
	public String getLog_related_to() {
		return log_related_to;
	}
	public void setLog_related_to(String log_related_to) {
		this.log_related_to = log_related_to;
	}
	public Date getLog_created_at() {
		return log_created_at;
	}
	public void setLog_created_at(Date log_created_at) {
		this.log_created_at = log_created_at;
	}
	public int getLog_added_by() {
		return log_added_by;
	}
	public void setLog_added_by(int log_added_by) {
		this.log_added_by = log_added_by;
	}
	public int getLog_secondary_responsible_person() {
		return log_secondary_responsible_person;
	}
	public void setLog_secondary_responsible_person(int log_secondary_responsible_person) {
		this.log_secondary_responsible_person = log_secondary_responsible_person;
	}
	public int getLog_third_responsible_person() {
		return log_third_responsible_person;
	}
	public void setLog_third_responsible_person(int log_third_responsible_person) {
		this.log_third_responsible_person = log_third_responsible_person;
	}
	public int getLog_other_responsible_person() {
		return log_other_responsible_person;
	}
	public void setLog_other_responsible_person(int log_other_responsible_person) {
		this.log_other_responsible_person = log_other_responsible_person;
	}
	
	
	
	
}
