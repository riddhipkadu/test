package lcmt.domain;

import java.util.Date;

import javax.validation.constraints.Size;

public class RequestRejectStatusReference {
	private int req_id;
	private int req_reject_spoc_id;
	private String req_reject_spoc_name;
	@Size(max = 3900)
	private String req_reject_spoc_comments;
	private int req_reject_admin_id;
	private String req_reject_admin_name;
	@Size(max = 3900)
	private String req_reject_admin_comments;
	private int req_reject_admin_request_status;
	private String req_reject_related_to; // related to either litigation, query or contract
	private int req_reject_related_id;
	private Date req_created_at;
	
	public int getReq_id() {
		return req_id;
	}
	public void setReq_id(int req_id) {
		this.req_id = req_id;
	}
	public Date getReq_created_at() {
		return req_created_at;
	}
	public void setReq_created_at(Date req_created_at) {
		this.req_created_at = req_created_at;
	}
	public int getReq_reject_spoc_id() {
		return req_reject_spoc_id;
	}
	public void setReq_reject_spoc_id(int req_reject_spoc_id) {
		this.req_reject_spoc_id = req_reject_spoc_id;
	}
	public String getReq_reject_spoc_name() {
		return req_reject_spoc_name;
	}
	public void setReq_reject_spoc_name(String req_reject_spoc_name) {
		this.req_reject_spoc_name = req_reject_spoc_name;
	}
	public String getReq_reject_spoc_comments() {
		return req_reject_spoc_comments;
	}
	public void setReq_reject_spoc_comments(String req_reject_spoc_comments) {
		this.req_reject_spoc_comments = req_reject_spoc_comments;
	}
	public int getReq_reject_admin_id() {
		return req_reject_admin_id;
	}
	public void setReq_reject_admin_id(int req_reject_admin_id) {
		this.req_reject_admin_id = req_reject_admin_id;
	}
	public String getReq_reject_admin_name() {
		return req_reject_admin_name;
	}
	public void setReq_reject_admin_name(String req_reject_admin_name) {
		this.req_reject_admin_name = req_reject_admin_name;
	}
	public String getReq_reject_admin_comments() {
		return req_reject_admin_comments;
	}
	public void setReq_reject_admin_comments(String req_reject_admin_comments) {
		this.req_reject_admin_comments = req_reject_admin_comments;
	}
	public int getReq_reject_admin_request_status() {
		return req_reject_admin_request_status;
	}
	public void setReq_reject_admin_request_status(int req_reject_admin_request_status) {
		this.req_reject_admin_request_status = req_reject_admin_request_status;
	}
	public String getReq_reject_related_to() {
		return req_reject_related_to;
	}
	public void setReq_reject_related_to(String req_reject_related_to) {
		this.req_reject_related_to = req_reject_related_to;
	}
	public int getReq_reject_related_id() {
		return req_reject_related_id;
	}
	public void setReq_reject_related_id(int req_reject_related_id) {
		this.req_reject_related_id = req_reject_related_id;
	} 

	
}
