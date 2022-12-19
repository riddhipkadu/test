package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name="ttrn_query_history")
public class QueryHistory {

	@Id
	@GeneratedValue
	private int query_hst_id;
	@Size(max = 3900)
	private String query_hst_comments;
	private Date query_hst_created_at;
	private Date query_hst_replied_date;
	private String query_hst_status;
	private int query_quer_id;
	private int query_hst_replied_by;
	private int query_hst_added_by;
	private String query_hst_others;
	private String query_hst_action_tobe_performed;
	private String query_hst_action_performed_others;
	private String query_hst_action_tobe_performed_by;
	private String query_hst_action_performed_by_others;
	private int query_hst_action_assigned_to;
	private String query_hst_type; // whether it is replied or received type
	
	public String getQuery_hst_type() {
		return query_hst_type;
	}
	public void setQuery_hst_type(String query_hst_type) {
		this.query_hst_type = query_hst_type;
	}
	public String getQuery_hst_action_performed_others() {
		return query_hst_action_performed_others;
	}
	public void setQuery_hst_action_performed_others(String query_hst_action_performed_others) {
		this.query_hst_action_performed_others = query_hst_action_performed_others;
	}
	public String getQuery_hst_action_performed_by_others() {
		return query_hst_action_performed_by_others;
	}
	public void setQuery_hst_action_performed_by_others(String query_hst_action_performed_by_others) {
		this.query_hst_action_performed_by_others = query_hst_action_performed_by_others;
	}
	public String getQuery_hst_action_tobe_performed() {
		return query_hst_action_tobe_performed;
	}
	public void setQuery_hst_action_tobe_performed(String query_hst_action_tobe_performed) {
		this.query_hst_action_tobe_performed = query_hst_action_tobe_performed;
	}
	public String getQuery_hst_action_tobe_performed_by() {
		return query_hst_action_tobe_performed_by;
	}
	public void setQuery_hst_action_tobe_performed_by(String query_hst_action_tobe_performed_by) {
		this.query_hst_action_tobe_performed_by = query_hst_action_tobe_performed_by;
	}
	public int getQuery_hst_action_assigned_to() {
		return query_hst_action_assigned_to;
	}
	public void setQuery_hst_action_assigned_to(int query_hst_action_assigned_to) {
		this.query_hst_action_assigned_to = query_hst_action_assigned_to;
	}
	public String getQuery_hst_others() {
		return query_hst_others;
	}
	public void setQuery_hst_others(String query_hst_others) {
		this.query_hst_others = query_hst_others;
	}
	public Date getQuery_hst_replied_date() {
		return query_hst_replied_date;
	}
	public void setQuery_hst_replied_date(Date query_hst_replied_date) {
		this.query_hst_replied_date = query_hst_replied_date;
	}
	public int getQuery_hst_replied_by() {
		return query_hst_replied_by;
	}
	public void setQuery_hst_replied_by(int query_hst_replied_by) {
		this.query_hst_replied_by = query_hst_replied_by;
	}
	public int getQuery_hst_id() {
		return query_hst_id;
	}
	public void setQuery_hst_id(int query_hst_id) {
		this.query_hst_id = query_hst_id;
	}
	public String getQuery_hst_comments() {
		return query_hst_comments;
	}
	public void setQuery_hst_comments(String query_hst_comments) {
		this.query_hst_comments = query_hst_comments;
	}
	public Date getQuery_hst_created_at() {
		return query_hst_created_at;
	}
	public void setQuery_hst_created_at(Date query_hst_created_at) {
		this.query_hst_created_at = query_hst_created_at;
	}
	public String getQuery_hst_status() {
		return query_hst_status;
	}
	public void setQuery_hst_status(String query_hst_status) {
		this.query_hst_status = query_hst_status;
	}
	public int getQuery_quer_id() {
		return query_quer_id;
	}
	public void setQuery_quer_id(int query_quer_id) {
		this.query_quer_id = query_quer_id;
	}
	public int getQuery_hst_added_by() {
		return query_hst_added_by;
	}
	public void setQuery_hst_added_by(int query_hst_added_by) {
		this.query_hst_added_by = query_hst_added_by;
	}
	
}
