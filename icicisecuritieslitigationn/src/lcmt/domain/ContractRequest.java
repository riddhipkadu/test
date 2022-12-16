package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Required;

@Entity(name="trn_contract_request")
public class ContractRequest {
	
	@Id
	@GeneratedValue
	private int req_contract_id;
	private int req_contract_type;
	private Date req_contract_date;
	private int req_party_no;
	@Size(max = 2500)
	private String req_contract_desc;
	private int req_contract_added_by;
	private Date req_contract_created_at;
	private int req_contract_approval_status;// 0: pending, 1: reject, 2: Accept, 3: reject by Admin
	private int req_contract_entity_id;
	private int req_contract_unit_id;
	private int req_contract_function_id;
	private String req_contract_name;
	private Date req_contract_start_date;
	private Date req_contract_end_date;
	@Size(max = 2500)
	private String req_contract_major_clause;
	@Size(max = 2500)
	private String req_contract_surviving_clause;
	@Size(max = 2500)
	private String req_contract_perform_rel_payment;
	@Size(max = 2500)
	private String req_contract_damage;
	@Size(max = 2500)
	private String req_contract_insurance;
	private String req_contract_notice_period;
	private String req_contract_jurisdiction;
	private String req_contract_total_value;
	private Date req_contract_expected_date;
	private String req_contract_email_id;
	private String req_contract_criticality;
	
	public Date getReq_contract_expected_date() {
		return req_contract_expected_date;
	}
	public void setReq_contract_expected_date(Date req_contract_expected_date) {
		this.req_contract_expected_date = req_contract_expected_date;
	}
	public String getReq_contract_email_id() {
		return req_contract_email_id;
	}
	public void setReq_contract_email_id(String req_contract_email_id) {
		this.req_contract_email_id = req_contract_email_id;
	}
	public String getReq_contract_criticality() {
		return req_contract_criticality;
	}
	public void setReq_contract_criticality(String req_contract_criticality) {
		this.req_contract_criticality = req_contract_criticality;
	}
	public String getReq_contract_jurisdiction() {
		return req_contract_jurisdiction;
	}
	public void setReq_contract_jurisdiction(String req_contract_jurisdiction) {
		this.req_contract_jurisdiction = req_contract_jurisdiction;
	}
	public String getReq_contract_total_value() {
		return req_contract_total_value;
	}
	public void setReq_contract_total_value(String req_contract_total_value) {
		this.req_contract_total_value = req_contract_total_value;
	}
	public String getReq_contract_insurance() {
		return req_contract_insurance;
	}
	public void setReq_contract_insurance(String req_contract_insurance) {
		this.req_contract_insurance = req_contract_insurance;
	}
	public String getReq_contract_notice_period() {
		return req_contract_notice_period;
	}
	public void setReq_contract_notice_period(String req_contract_notice_period) {
		this.req_contract_notice_period = req_contract_notice_period;
	}
	public Date getReq_contract_start_date() {
		return req_contract_start_date;
	}
	public void setReq_contract_start_date(Date req_contract_start_date) {
		this.req_contract_start_date = req_contract_start_date;
	}
	public Date getReq_contract_end_date() {
		return req_contract_end_date;
	}
	public void setReq_contract_end_date(Date req_contract_end_date) {
		this.req_contract_end_date = req_contract_end_date;
	}
	public String getReq_contract_major_clause() {
		return req_contract_major_clause;
	}
	public void setReq_contract_major_clause(String req_contract_major_clause) {
		this.req_contract_major_clause = req_contract_major_clause;
	}
	public String getReq_contract_surviving_clause() {
		return req_contract_surviving_clause;
	}
	public void setReq_contract_surviving_clause(String req_contract_surviving_clause) {
		this.req_contract_surviving_clause = req_contract_surviving_clause;
	}
	public String getReq_contract_perform_rel_payment() {
		return req_contract_perform_rel_payment;
	}
	public void setReq_contract_perform_rel_payment(String req_contract_perform_rel_payment) {
		this.req_contract_perform_rel_payment = req_contract_perform_rel_payment;
	}
	public String getReq_contract_damage() {
		return req_contract_damage;
	}
	public void setReq_contract_damage(String req_contract_damage) {
		this.req_contract_damage = req_contract_damage;
	}
	public String getReq_contract_name() {
		return req_contract_name;
	}
	public void setReq_contract_name(String req_contract_name) {
		this.req_contract_name = req_contract_name;
	}
	public int getReq_contract_entity_id() {
		return req_contract_entity_id;
	}
	public void setReq_contract_entity_id(int req_contract_entity_id) {
		this.req_contract_entity_id = req_contract_entity_id;
	}
	public int getReq_contract_unit_id() {
		return req_contract_unit_id;
	}
	public void setReq_contract_unit_id(int req_contract_unit_id) {
		this.req_contract_unit_id = req_contract_unit_id;
	}
	public int getReq_contract_function_id() {
		return req_contract_function_id;
	}
	public void setReq_contract_function_id(int req_contract_function_id) {
		this.req_contract_function_id = req_contract_function_id;
	}
	public int getReq_contract_approval_status() {
		return req_contract_approval_status;
	}
	public void setReq_contract_approval_status(int req_contract_approval_status) {
		this.req_contract_approval_status = req_contract_approval_status;
	}
	public int getReq_contract_id() {
		return req_contract_id;
	}
	public void setReq_contract_id(int req_contract_id) {
		this.req_contract_id = req_contract_id;
	}
	public int getReq_contract_type() {
		return req_contract_type;
	}
	public void setReq_contract_type(int req_contract_type) {
		this.req_contract_type = req_contract_type;
	}
	public Date getReq_contract_date() {
		return req_contract_date;
	}
	public void setReq_contract_date(Date req_contract_date) {
		this.req_contract_date = req_contract_date;
	}
	public int getReq_party_no() {
		return req_party_no;
	}
	public void setReq_party_no(int req_party_no) {
		this.req_party_no = req_party_no;
	}
	public String getReq_contract_desc() {
		return req_contract_desc;
	}
	public void setReq_contract_desc(String req_contract_desc) {
		this.req_contract_desc = req_contract_desc;
	}
	public int getReq_contract_added_by() {
		return req_contract_added_by;
	}
	public void setReq_contract_added_by(int req_contract_added_by) {
		this.req_contract_added_by = req_contract_added_by;
	}
	public Date getReq_contract_created_at() {
		return req_contract_created_at;
	}
	public void setReq_contract_created_at(Date req_contract_created_at) {
		this.req_contract_created_at = req_contract_created_at;
	}
	
	
	
}
