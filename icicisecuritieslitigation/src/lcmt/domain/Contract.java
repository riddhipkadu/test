package lcmt.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name = "mst_contracts")
public class Contract {

	@Id
	@GeneratedValue
	private int cont_id;
	private int cont_orga_id;
	private int cont_loca_id;
	private int cont_dept_id;
	private String cont_agreement_name;
	private Date cont_requested_date;
	private int cont_requested_by_user_id;
	//private String cont_party_one;
	//private String cont_party_two;
	//private int cont_type_of_contract;
	private String cont_purpose;
	private Date cont_expected_date;
	private Date cont_targetted_date;
	private String cont_responsible_user_id;
	private int cont_added_by;
	private Date cont_created_at;
	private String cont_status;
	private String cont_criticality;
	@Size(max=2500)
	private String cont_nature;
	@Size(max=2500)
	private String cont_term;
	private String cont_payment;
	@Size(max=2500)
	private String cont_surviving_clause;
	@Size(max=2500)
	private String cont_major_clause;
	private Date cont_start_date;
	private Date cont_end_date;
	@Size(max=2500)
	private String cont_damages;
	@Size(max=2500)
	private String cont_instructions;
	private Date cont_reminder_date;
	private int cont_req_id;
	
	public int getCont_req_id() {
		return cont_req_id;
	}
	public void setCont_req_id(int cont_req_id) {
		this.cont_req_id = cont_req_id;
	}
	public Date getCont_reminder_date() {
		return cont_reminder_date;
	}
	public void setCont_reminder_date(Date cont_reminder_date) {
		this.cont_reminder_date = cont_reminder_date;
	}
	public Date getCont_start_date() {
		return cont_start_date;
	}
	public void setCont_start_date(Date cont_start_date) {
		this.cont_start_date = cont_start_date;
	}
	public Date getCont_end_date() {
		return cont_end_date;
	}
	public void setCont_end_date(Date cont_end_date) {
		this.cont_end_date = cont_end_date;
	}
	public String getCont_damages() {
		return cont_damages;
	}
	public void setCont_damages(String cont_damages) {
		this.cont_damages = cont_damages;
	}
	public String getCont_instructions() {
		return cont_instructions;
	}
	public void setCont_instructions(String cont_instructions) {
		this.cont_instructions = cont_instructions;
	}
	public String getCont_nature() {
		return cont_nature;
	}
	public void setCont_nature(String cont_nature) {
		this.cont_nature = cont_nature;
	}
	public String getCont_term() {
		return cont_term;
	}
	public void setCont_term(String cont_term) {
		this.cont_term = cont_term;
	}
	public String getCont_payment() {
		return cont_payment;
	}
	public void setCont_payment(String cont_payment) {
		this.cont_payment = cont_payment;
	}
	public String getCont_surviving_clause() {
		return cont_surviving_clause;
	}
	public void setCont_surviving_clause(String cont_surviving_clause) {
		this.cont_surviving_clause = cont_surviving_clause;
	}
	public String getCont_major_clause() {
		return cont_major_clause;
	}
	public void setCont_major_clause(String cont_major_clause) {
		this.cont_major_clause = cont_major_clause;
	}
	public String getCont_criticality() {
		return cont_criticality;
	}
	public void setCont_criticality(String cont_criticality) {
		this.cont_criticality = cont_criticality;
	}
	public int getCont_id() {
		return cont_id;
	}
	public void setCont_id(int cont_id) {
		this.cont_id = cont_id;
	}
	public int getCont_orga_id() {
		return cont_orga_id;
	}
	public void setCont_orga_id(int cont_orga_id) {
		this.cont_orga_id = cont_orga_id;
	}
	public int getCont_loca_id() {
		return cont_loca_id;
	}
	public void setCont_loca_id(int cont_loca_id) {
		this.cont_loca_id = cont_loca_id;
	}
	public int getCont_dept_id() {
		return cont_dept_id;
	}
	public void setCont_dept_id(int cont_dept_id) {
		this.cont_dept_id = cont_dept_id;
	}
	public String getCont_agreement_name() {
		return cont_agreement_name;
	}
	public void setCont_agreement_name(String cont_agreement_name) {
		this.cont_agreement_name = cont_agreement_name;
	}
	public Date getCont_requested_date() {
		return cont_requested_date;
	}
	public void setCont_requested_date(Date cont_requested_date) {
		this.cont_requested_date = cont_requested_date;
	}
	
	public int getCont_requested_by_user_id() {
		return cont_requested_by_user_id;
	}
	public void setCont_requested_by_user_id(int cont_requested_by_user_id) {
		this.cont_requested_by_user_id = cont_requested_by_user_id;
	}
	/*public String getCont_party_one() {
		return cont_party_one;
	}
	public void setCont_party_one(String cont_party_one) {
		this.cont_party_one = cont_party_one;
	}
	public String getCont_party_two() {
		return cont_party_two;
	}
	public void setCont_party_two(String cont_party_two) {
		this.cont_party_two = cont_party_two;
	}
	public int getCont_type_of_contract() {
		return cont_type_of_contract;
	}
	public void setCont_type_of_contract(int cont_type_of_contract) {
		this.cont_type_of_contract = cont_type_of_contract;
	}*/
	public String getCont_purpose() {
		return cont_purpose;
	}
	public void setCont_purpose(String cont_purpose) {
		this.cont_purpose = cont_purpose;
	}
	public Date getCont_expected_date() {
		return cont_expected_date;
	}
	public void setCont_expected_date(Date cont_expected_date) {
		this.cont_expected_date = cont_expected_date;
	}
	public Date getCont_targetted_date() {
		return cont_targetted_date;
	}
	public void setCont_targetted_date(Date cont_targetted_date) {
		this.cont_targetted_date = cont_targetted_date;
	}
	public String getCont_responsible_user_id() {
		return cont_responsible_user_id;
	}
	public void setCont_responsible_user_id(String cont_responsible_user_id) {
		this.cont_responsible_user_id = cont_responsible_user_id;
	}
	public int getCont_added_by() {
		return cont_added_by;
	}
	public void setCont_added_by(int cont_added_by) {
		this.cont_added_by = cont_added_by;
	}
	public Date getCont_created_at() {
		return cont_created_at;
	}
	public void setCont_created_at(Date cont_created_at) {
		this.cont_created_at = cont_created_at;
	}
	public String getCont_status() {
		return cont_status;
	}
	public void setCont_status(String cont_status) {
		this.cont_status = cont_status;
	}
	
	
}
