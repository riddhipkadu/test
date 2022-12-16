package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

	@Entity(name="trn_notice_request")
	public class LegalNoticeRequest {
	
	@Id
	@GeneratedValue
	private int req_noti_id;
	private String req_noti_oppo_party;
	@Size(max = 3900)
	private String req_noti_des;
	private String req_noti_type_by_against;
	private int req_noti_entity_id;
	private int req_noti_unit_id;
	private int req_noti_function_id;
	private Date req_noti_date;
	private Date req_noti_created_at;
	private int req_noti_added_by;
	private int req_noti_approval_status;// 0: pending, 1: reject, 2: Accept, 3: reject by Admin
	public int getReq_noti_id() {
		return req_noti_id;
	}
	public void setReq_noti_id(int req_noti_id) {
		this.req_noti_id = req_noti_id;
	}
	public String getReq_noti_oppo_party() {
		return req_noti_oppo_party;
	}
	public void setReq_noti_oppo_party(String req_noti_oppo_party) {
		this.req_noti_oppo_party = req_noti_oppo_party;
	}
	public String getReq_noti_des() {
		return req_noti_des;
	}
	public void setReq_noti_des(String req_noti_des) {
		this.req_noti_des = req_noti_des;
	}
	public String getReq_noti_type_by_against() {
		return req_noti_type_by_against;
	}
	public void setReq_noti_type_by_against(String req_noti_type_by_against) {
		this.req_noti_type_by_against = req_noti_type_by_against;
	}
	public int getReq_noti_entity_id() {
		return req_noti_entity_id;
	}
	public void setReq_noti_entity_id(int req_noti_entity_id) {
		this.req_noti_entity_id = req_noti_entity_id;
	}
	public int getReq_noti_unit_id() {
		return req_noti_unit_id;
	}
	public void setReq_noti_unit_id(int req_noti_unit_id) {
		this.req_noti_unit_id = req_noti_unit_id;
	}
	public int getReq_noti_function_id() {
		return req_noti_function_id;
	}
	public void setReq_noti_function_id(int req_noti_function_id) {
		this.req_noti_function_id = req_noti_function_id;
	}
	public Date getReq_noti_date() {
		return req_noti_date;
	}
	public void setReq_noti_date(Date req_noti_date) {
		this.req_noti_date = req_noti_date;
	}
	public Date getReq_noti_created_at() {
		return req_noti_created_at;
	}
	public void setReq_noti_created_at(Date req_noti_created_at) {
		this.req_noti_created_at = req_noti_created_at;
	}
	public int getReq_noti_added_by() {
		return req_noti_added_by;
	}
	public void setReq_noti_added_by(int req_noti_added_by) {
		this.req_noti_added_by = req_noti_added_by;
	}
	public int getReq_noti_approval_status() {
		return req_noti_approval_status;
	}
	public void setReq_noti_approval_status(int req_noti_approval_status) {
		this.req_noti_approval_status = req_noti_approval_status;
	}

}
