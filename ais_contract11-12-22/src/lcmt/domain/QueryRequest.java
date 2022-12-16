package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Entity(name="trn_query_request")
public class QueryRequest {
	
	@Id
	@GeneratedValue
	private int req_query_id;
	private String req_query_from;
	@Size(max = 3900)
	private String req_query;
	private Date req_query_date;
	private int req_query_added_by;
	private Date req_query_created_at;
	private int req_query_approval_status;// 0: pending, 1: reject, 2: Accept, 3: reject by Admin
	private int req_query_entity_id;
	private int req_query_unit_id;
	private int req_query_function_id;
	private String req_query_criticality;
	private Date req_query_turnaround_time;
	
	public String getReq_query_criticality() {
		return req_query_criticality;
	}
	public void setReq_query_criticality(String req_query_criticality) {
		this.req_query_criticality = req_query_criticality;
	}
	public Date getReq_query_turnaround_time() {
		return req_query_turnaround_time;
	}
	public void setReq_query_turnaround_time(Date req_query_turnaround_time) {
		this.req_query_turnaround_time = req_query_turnaround_time;
	}
	public int getReq_query_entity_id() {
		return req_query_entity_id;
	}
	public void setReq_query_entity_id(int req_query_entity_id) {
		this.req_query_entity_id = req_query_entity_id;
	}
	public int getReq_query_unit_id() {
		return req_query_unit_id;
	}
	public void setReq_query_unit_id(int req_query_unit_id) {
		this.req_query_unit_id = req_query_unit_id;
	}
	public int getReq_query_function_id() {
		return req_query_function_id;
	}
	public void setReq_query_function_id(int req_query_function_id) {
		this.req_query_function_id = req_query_function_id;
	}
	public int getReq_query_approval_status() {
		return req_query_approval_status;
	}
	public void setReq_query_approval_status(int req_query_approval_status) {
		this.req_query_approval_status = req_query_approval_status;
	}
	public int getReq_query_id() {
		return req_query_id;
	}
	public void setReq_query_id(int req_query_id) {
		this.req_query_id = req_query_id;
	}
	public String getReq_query_from() {
		return req_query_from;
	}
	public void setReq_query_from(String req_query_from) {
		this.req_query_from = req_query_from;
	}
	public String getReq_query() {
		return req_query;
	}
	public void setReq_query(String req_query) {
		this.req_query = req_query;
	}
	public Date getReq_query_date() {
		return req_query_date;
	}
	public void setReq_query_date(Date req_query_date) {
		this.req_query_date = req_query_date;
	}
	public int getReq_query_added_by() {
		return req_query_added_by;
	}
	public void setReq_query_added_by(int req_query_added_by) {
		this.req_query_added_by = req_query_added_by;
	}
	public Date getReq_query_created_at() {
		return req_query_created_at;
	}
	public void setReq_query_created_at(Date req_query_created_at) {
		this.req_query_created_at = req_query_created_at;
	}
	
	
	
	
	
}
