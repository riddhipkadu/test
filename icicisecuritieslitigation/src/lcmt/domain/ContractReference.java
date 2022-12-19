package lcmt.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

public class ContractReference {

	private String orga_name;
	private String loca_name;
	private String dept_name;
	private String cont_agreement_name;
	private String user_requested_by_fullname;
	private String cont_requested_date;
	private String cont_expected_date;
	private String cont_party_one;
	private String cont_party_two;
	private String user_responsible_fullname;
	private String cont_targetted_date;
	private String cont_status;
	private int cont_id;
	private int cont_orga_id;
	private int cont_loca_id;
	private int cont_dept_id;
	private Date cont_requested_date_date;
	private Date cont_expected_date_date;
	private Date cont_targetted_date_date;
	private int cont_added_by;
	private Date cont_created_at;
	private List<ContractContractType> cont_type_list;
	private List<ContractParties> cont_parties;
	private String cont_responsible_user_id;
	private int cont_requested_by_user_id;
	private String cont_purpose;
	private List<ContractType> cont_type_list_name;
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
	private String cont_start_date_name;
	private String cont_end_date_name;
	private String cont_reminder_date_name;
	private int chst_poc_user_id;
	private int cont_req_id;
	
	public int getCont_req_id() {
		return cont_req_id;
	}
	public void setCont_req_id(int cont_req_id) {
		this.cont_req_id = cont_req_id;
	}
	public int getChst_poc_user_id() {
		return chst_poc_user_id;
	}
	public void setChst_poc_user_id(int chst_poc_user_id) {
		this.chst_poc_user_id = chst_poc_user_id;
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
	public Date getCont_reminder_date() {
		return cont_reminder_date;
	}
	public void setCont_reminder_date(Date cont_reminder_date) {
		this.cont_reminder_date = cont_reminder_date;
	}
	public String getCont_start_date_name() {
		return cont_start_date_name;
	}
	public void setCont_start_date_name(String cont_start_date_name) {
		this.cont_start_date_name = cont_start_date_name;
	}
	public String getCont_end_date_name() {
		return cont_end_date_name;
	}
	public void setCont_end_date_name(String cont_end_date_name) {
		this.cont_end_date_name = cont_end_date_name;
	}
	public String getCont_reminder_date_name() {
		return cont_reminder_date_name;
	}
	public void setCont_reminder_date_name(String cont_reminder_date_name) {
		this.cont_reminder_date_name = cont_reminder_date_name;
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
	public List<ContractType> getCont_type_list_name() {
		return cont_type_list_name;
	}
	public void setCont_type_list_name(List<ContractType> cont_type_list_name) {
		this.cont_type_list_name = cont_type_list_name;
	}
	public int getCont_id() {
		return cont_id;
	}
	public void setCont_id(int cont_id) {
		this.cont_id = cont_id;
	}
	public String getOrga_name() {
		return orga_name;
	}
	public void setOrga_name(String orga_name) {
		this.orga_name = orga_name;
	}
	public String getLoca_name() {
		return loca_name;
	}
	public void setLoca_name(String loca_name) {
		this.loca_name = loca_name;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getCont_agreement_name() {
		return cont_agreement_name;
	}
	public void setCont_agreement_name(String cont_agreement_name) {
		this.cont_agreement_name = cont_agreement_name;
	}
	public String getUser_requested_by_fullname() {
		return user_requested_by_fullname;
	}
	public void setUser_requested_by_fullname(String user_requested_by_fullname) {
		this.user_requested_by_fullname = user_requested_by_fullname;
	}
	public String getCont_requested_date() {
		return cont_requested_date;
	}
	public void setCont_requested_date(String cont_requested_date) {
		this.cont_requested_date = cont_requested_date;
	}
	public String getCont_expected_date() {
		return cont_expected_date;
	}
	public void setCont_expected_date(String cont_expected_date) {
		this.cont_expected_date = cont_expected_date;
	}
	public String getCont_party_one() {
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
	
	public String getUser_responsible_fullname() {
		return user_responsible_fullname;
	}
	public void setUser_responsible_fullname(String user_responsible_fullname) {
		this.user_responsible_fullname = user_responsible_fullname;
	}
	public String getCont_targetted_date() {
		return cont_targetted_date;
	}
	public void setCont_targetted_date(String cont_targetted_date) {
		this.cont_targetted_date = cont_targetted_date;
	}
	public String getCont_status() {
		return cont_status;
	}
	public void setCont_status(String cont_status) {
		this.cont_status = cont_status;
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
	public Date getCont_requested_date_date() {
		return cont_requested_date_date;
	}
	public void setCont_requested_date_date(Date cont_requested_date_date) {
		this.cont_requested_date_date = cont_requested_date_date;
	}
	public Date getCont_expected_date_date() {
		return cont_expected_date_date;
	}
	public void setCont_expected_date_date(Date cont_expected_date_date) {
		this.cont_expected_date_date = cont_expected_date_date;
	}
	public Date getCont_targetted_date_date() {
		return cont_targetted_date_date;
	}
	public void setCont_targetted_date_date(Date cont_targetted_date_date) {
		this.cont_targetted_date_date = cont_targetted_date_date;
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
	public List<ContractContractType> getCont_type_list() {
		return cont_type_list;
	}
	public void setCont_type_list(List<ContractContractType> cont_type_list) {
		this.cont_type_list = cont_type_list;
	}
	/**
	 * @return the cont_parties
	 */
	public List<ContractParties> getCont_parties() {
		return cont_parties;
	}
	/**
	 * @param cont_parties the cont_parties to set
	 */
	public void setCont_parties(List<ContractParties> cont_parties) {
		this.cont_parties = cont_parties;
	}
	/**
	 * @return the cont_responsible_user_id
	 */
	public String getCont_responsible_user_id() {
		return cont_responsible_user_id;
	}
	/**
	 * @param cont_responsible_user_id the cont_responsible_user_id to set
	 */
	public void setCont_responsible_user_id(String cont_responsible_user_id) {
		this.cont_responsible_user_id = cont_responsible_user_id;
	}
	/**
	 * @return the cont_requested_by_user_id
	 */
	
	/**
	 * @return the cont_purpose
	 */
	public String getCont_purpose() {
		return cont_purpose;
	}
	public int getCont_requested_by_user_id() {
		return cont_requested_by_user_id;
	}
	public void setCont_requested_by_user_id(int cont_requested_by_user_id) {
		this.cont_requested_by_user_id = cont_requested_by_user_id;
	}
	/**
	 * @param cont_purpose the cont_purpose to set
	 */
	public void setCont_purpose(String cont_purpose) {
		this.cont_purpose = cont_purpose;
	}
	
	
	
	
	
}
