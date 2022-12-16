package lcmt.domain;

import java.util.List;

public class QueryHistoryReference {

	private int query_hst_id;
	private String query_hst_comments;
	private String query_hst_created_at;
	private String query_hst_status;
	private int query_quer_id;
	private List<QueryDocuments> hst_doc;
	private String query_hst_replied_date;
	private String query_hst_action_tobe_performed;
	private String query_hst_action_performed_others;
	private String query_hst_action_tobe_performed_by;
	private String query_hst_action_performed_by_others;
	private String query_hst_action_assigned_to;
	private int query_action_assign_to_id;
	private String query_hst_replied_by;
	private String query_hst_others;
	private int query_hst_added_by;
	private String query_hst_type; // whether it is replied or received type
	
	public String getQuery_hst_type() {
		return query_hst_type;
	}
	public void setQuery_hst_type(String query_hst_type) {
		this.query_hst_type = query_hst_type;
	}
	public int getQuery_hst_added_by() {
		return query_hst_added_by;
	}
	public void setQuery_hst_added_by(int query_hst_added_by) {
		this.query_hst_added_by = query_hst_added_by;
	}
	public int getQuery_action_assign_to_id() {
		return query_action_assign_to_id;
	}
	public void setQuery_action_assign_to_id(int query_action_assign_to_id) {
		this.query_action_assign_to_id = query_action_assign_to_id;
	}
	public String getQuery_hst_others() {
		return query_hst_others;
	}
	public void setQuery_hst_others(String query_hst_others) {
		this.query_hst_others = query_hst_others;
	}
	public String getQuery_hst_action_tobe_performed() {
		return query_hst_action_tobe_performed;
	}
	public void setQuery_hst_action_tobe_performed(String query_hst_action_tobe_performed) {
		this.query_hst_action_tobe_performed = query_hst_action_tobe_performed;
	}
	public String getQuery_hst_action_performed_others() {
		return query_hst_action_performed_others;
	}
	public void setQuery_hst_action_performed_others(String query_hst_action_performed_others) {
		this.query_hst_action_performed_others = query_hst_action_performed_others;
	}
	public String getQuery_hst_action_tobe_performed_by() {
		return query_hst_action_tobe_performed_by;
	}
	public void setQuery_hst_action_tobe_performed_by(String query_hst_action_tobe_performed_by) {
		this.query_hst_action_tobe_performed_by = query_hst_action_tobe_performed_by;
	}
	public String getQuery_hst_action_performed_by_others() {
		return query_hst_action_performed_by_others;
	}
	public void setQuery_hst_action_performed_by_others(String query_hst_action_performed_by_others) {
		this.query_hst_action_performed_by_others = query_hst_action_performed_by_others;
	}
	public String getQuery_hst_action_assigned_to() {
		return query_hst_action_assigned_to;
	}
	public void setQuery_hst_action_assigned_to(String query_hst_action_assigned_to) {
		this.query_hst_action_assigned_to = query_hst_action_assigned_to;
	}
	public String getQuery_hst_replied_by() {
		return query_hst_replied_by;
	}
	public void setQuery_hst_replied_by(String query_hst_replied_by) {
		this.query_hst_replied_by = query_hst_replied_by;
	}
	public String getQuery_hst_replied_date() {
		return query_hst_replied_date;
	}
	public void setQuery_hst_replied_date(String query_hst_replied_date) {
		this.query_hst_replied_date = query_hst_replied_date;
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
	public String getQuery_hst_created_at() {
		return query_hst_created_at;
	}
	public void setQuery_hst_created_at(String query_hst_created_at) {
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
	public List<QueryDocuments> getHst_doc() {
		return hst_doc;
	}
	public void setHst_doc(List<QueryDocuments> hst_doc) {
		this.hst_doc = hst_doc;
	}
	
	
}
