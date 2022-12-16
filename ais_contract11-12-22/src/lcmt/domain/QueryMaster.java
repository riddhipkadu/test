package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name="mst_query")
public class QueryMaster {
	
	@Id
	@GeneratedValue
	private int quer_id;
	private int quer_entity_id;
	private int quer_unit_id;
	private int quer_function_id;
	private String quer_from_id;
	@Size(max = 3900)
	private String quer_query;
	private int quer_assigned_to;
	private Date quer_reply_date;
	private Date quer_replied_date;
	private int quer_replied_by;
	@Size(max = 3900)
	private String quer_comments;
	private Date quer_reminder_date;
	private String quer_status;
	private Date quer_created_at;
	private Date quer_updated_at;
	private int quer_added_by;
	private Date quer_query_date;
	private String quer_criticality;
	private Date quer_turnaround_time;
	
	public String getQuer_criticality() {
		return quer_criticality;
	}
	public void setQuer_criticality(String quer_criticality) {
		this.quer_criticality = quer_criticality;
	}
	public Date getQuer_turnaround_time() {
		return quer_turnaround_time;
	}
	public void setQuer_turnaround_time(Date quer_turnaround_time) {
		this.quer_turnaround_time = quer_turnaround_time;
	}
	public int getQuer_id() {
		return quer_id;
	}
	public void setQuer_id(int quer_id) {
		this.quer_id = quer_id;
	}
	public int getQuer_entity_id() {
		return quer_entity_id;
	}
	public void setQuer_entity_id(int quer_entity_id) {
		this.quer_entity_id = quer_entity_id;
	}
	public int getQuer_unit_id() {
		return quer_unit_id;
	}
	public void setQuer_unit_id(int quer_unit_id) {
		this.quer_unit_id = quer_unit_id;
	}
	public int getQuer_function_id() {
		return quer_function_id;
	}
	public void setQuer_function_id(int quer_function_id) {
		this.quer_function_id = quer_function_id;
	}
	
	public String getQuer_from_id() {
		return quer_from_id;
	}
	public void setQuer_from_id(String quer_from_id) {
		this.quer_from_id = quer_from_id;
	}
	public String getQuer_query() {
		return quer_query;
	}
	public void setQuer_query(String quer_query) {
		this.quer_query = quer_query;
	}
	public int getQuer_assigned_to() {
		return quer_assigned_to;
	}
	public void setQuer_assigned_to(int quer_assigned_to) {
		this.quer_assigned_to = quer_assigned_to;
	}
	public Date getQuer_reply_date() {
		return quer_reply_date;
	}
	public void setQuer_reply_date(Date quer_reply_date) {
		this.quer_reply_date = quer_reply_date;
	}
	public Date getQuer_replied_date() {
		return quer_replied_date;
	}
	public void setQuer_replied_date(Date quer_replied_date) {
		this.quer_replied_date = quer_replied_date;
	}
	public int getQuer_replied_by() {
		return quer_replied_by;
	}
	public void setQuer_replied_by(int quer_replied_by) {
		this.quer_replied_by = quer_replied_by;
	}
	public String getQuer_comments() {
		return quer_comments;
	}
	public void setQuer_comments(String quer_comments) {
		this.quer_comments = quer_comments;
	}
	public Date getQuer_reminder_date() {
		return quer_reminder_date;
	}
	public void setQuer_reminder_date(Date quer_reminder_date) {
		this.quer_reminder_date = quer_reminder_date;
	}
	public String getQuer_status() {
		return quer_status;
	}
	public void setQuer_status(String quer_status) {
		this.quer_status = quer_status;
	}
	public Date getQuer_created_at() {
		return quer_created_at;
	}
	public void setQuer_created_at(Date quer_created_at) {
		this.quer_created_at = quer_created_at;
	}
	public Date getQuer_updated_at() {
		return quer_updated_at;
	}
	public void setQuer_updated_at(Date quer_updated_at) {
		this.quer_updated_at = quer_updated_at;
	}
	public int getQuer_added_by() {
		return quer_added_by;
	}
	public void setQuer_added_by(int quer_added_by) {
		this.quer_added_by = quer_added_by;
	}
	public Date getQuer_query_date() {
		return quer_query_date;
	}
	public void setQuer_query_date(Date quer_query_date) {
		this.quer_query_date = quer_query_date;
	}
	
	   
	
}
