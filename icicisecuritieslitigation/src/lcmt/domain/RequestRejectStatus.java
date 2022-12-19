package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name="trn_req_reject_status")
public class RequestRejectStatus {
	
	@Id
	@GeneratedValue
	private int req_id;
	private int req_spoc_id;
	@Size(max = 3900)
	private String req_spoc_comments;
	private int req_admin_id;
	@Size(max = 3900)
	private String req_admin_comments;
	private int req_admin_request_status;
	private String req_related_to; // related to either litigation, query or contract
	private Date req_created_at;
	private int req_related_id; // primary key of request 
	
	public int getReq_related_id() {
		return req_related_id;
	}
	public void setReq_related_id(int req_related_id) {
		this.req_related_id = req_related_id;
	}
	public int getReq_id() {
		return req_id;
	}
	public void setReq_id(int req_id) {
		this.req_id = req_id;
	}
	public int getReq_spoc_id() {
		return req_spoc_id;
	}
	public void setReq_spoc_id(int req_spoc_id) {
		this.req_spoc_id = req_spoc_id;
	}
	public String getReq_spoc_comments() {
		return req_spoc_comments;
	}
	public void setReq_spoc_comments(String req_spoc_comments) {
		this.req_spoc_comments = req_spoc_comments;
	}
	public int getReq_admin_id() {
		return req_admin_id;
	}
	public void setReq_admin_id(int req_admin_id) {
		this.req_admin_id = req_admin_id;
	}
	public String getReq_admin_comments() {
		return req_admin_comments;
	}
	public void setReq_admin_comments(String req_admin_comments) {
		this.req_admin_comments = req_admin_comments;
	}
	public int getReq_admin_request_status() {
		return req_admin_request_status;
	}
	public void setReq_admin_request_status(int req_admin_request_status) {
		this.req_admin_request_status = req_admin_request_status;
	}
	public String getReq_related_to() {
		return req_related_to;
	}
	public void setReq_related_to(String req_related_to) {
		this.req_related_to = req_related_to;
	}
	public Date getReq_created_at() {
		return req_created_at;
	}
	public void setReq_created_at(Date req_created_at) {
		this.req_created_at = req_created_at;
	}
	
}
