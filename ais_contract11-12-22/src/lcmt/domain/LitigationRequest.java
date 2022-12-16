package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name="trn_litigation_request")
public class LitigationRequest {

	@Id
	@GeneratedValue
	private int req_liti_id;
	private int req_liti_category;
	private String req_liti_company_type;
	private Date req_liti_case_filing_date;
	private String req_liti_case_ref_no;
	private String req_liti_party_by;
	@Size(max = 3900)
	private String req_liti_des;
	private Date req_liti_created_at;
	private int req_liti_added_by;
	private int req_liti_approval_status;// 0: pending, 1: reject, 2: Accept, 3: reject by Admin
	private int req_liti_entity_id;
	private int req_liti_unit_id;
	private int req_liti_function_id;
	private Date req_liti_received_date;
	private String req_liti_party_against;
	
	public String getReq_liti_party_by() {
		return req_liti_party_by;
	}
	public void setReq_liti_party_by(String req_liti_party_by) {
		this.req_liti_party_by = req_liti_party_by;
	}
	public String getReq_liti_party_against() {
		return req_liti_party_against;
	}
	public void setReq_liti_party_against(String req_liti_party_against) {
		this.req_liti_party_against = req_liti_party_against;
	}
	public Date getReq_liti_received_date() {
		return req_liti_received_date;
	}
	public void setReq_liti_received_date(Date req_liti_received_date) {
		this.req_liti_received_date = req_liti_received_date;
	}
	public int getReq_liti_entity_id() {
		return req_liti_entity_id;
	}
	public void setReq_liti_entity_id(int req_liti_entity_id) {
		this.req_liti_entity_id = req_liti_entity_id;
	}
	public int getReq_liti_unit_id() {
		return req_liti_unit_id;
	}
	public void setReq_liti_unit_id(int req_liti_unit_id) {
		this.req_liti_unit_id = req_liti_unit_id;
	}
	public int getReq_liti_function_id() {
		return req_liti_function_id;
	}
	public void setReq_liti_function_id(int req_liti_function_id) {
		this.req_liti_function_id = req_liti_function_id;
	}
	public int getReq_liti_approval_status() {
		return req_liti_approval_status;
	}
	public void setReq_liti_approval_status(int req_liti_approval_status) {
		this.req_liti_approval_status = req_liti_approval_status;
	}
	public int getReq_liti_id() {
		return req_liti_id;
	}
	public void setReq_liti_id(int req_liti_id) {
		this.req_liti_id = req_liti_id;
	}
	public int getReq_liti_category() {
		return req_liti_category;
	}
	public void setReq_liti_category(int req_liti_category) {
		this.req_liti_category = req_liti_category;
	}
	public String getReq_liti_company_type() {
		return req_liti_company_type;
	}
	public void setReq_liti_company_type(String req_liti_company_type) {
		this.req_liti_company_type = req_liti_company_type;
	}
	public Date getReq_liti_case_filing_date() {
		return req_liti_case_filing_date;
	}
	public void setReq_liti_case_filing_date(Date req_liti_case_filing_date) {
		this.req_liti_case_filing_date = req_liti_case_filing_date;
	}
	public String getReq_liti_case_ref_no() {
		return req_liti_case_ref_no;
	}
	public void setReq_liti_case_ref_no(String req_liti_case_ref_no) {
		this.req_liti_case_ref_no = req_liti_case_ref_no;
	}
	public String getReq_liti_des() {
		return req_liti_des;
	}
	public void setReq_liti_des(String req_liti_des) {
		this.req_liti_des = req_liti_des;
	}
	public Date getReq_liti_created_at() {
		return req_liti_created_at;
	}
	public void setReq_liti_created_at(Date req_liti_created_at) {
		this.req_liti_created_at = req_liti_created_at;
	}
	public int getReq_liti_added_by() {
		return req_liti_added_by;
	}
	public void setReq_liti_added_by(int req_liti_added_by) {
		this.req_liti_added_by = req_liti_added_by;
	}
	
}
	
	
