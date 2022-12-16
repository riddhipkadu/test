package lcmt.domain;

import java.util.Date;

import javax.validation.constraints.Size;

public class LitigationReference {

	private int liti_id;
	private int liti_orga_id;
	private int liti_loca_id;
	private int liti_dept_id;
	private String liti_case_title;
	private int liti_type_id;
	private String liti_type_name;
	private String liti_client_liti_id;
	private String liti_against_by_id;
	private String liti_company_acting_as;
	private String liti_case_filing_date;
	private int liti_external_counsel_id;
	private String liti_external_counsel;
	private int liti_internally_handled_by;
	private String liti_internally_handled_name;
	private String liti_opposite_party;
	private String liti_opp_party_acting_as;
	private String liti_opposite_party_advocate;
	private String liti_case_reference_no;
	private String liti_court;
	private String liti_amount_involved;
	private String liti_relevant_law;
	private String liti_updated_at;
	private String liti_updated_by;
	private Date liti_acm_from;
	private Date liti_acm_to;
	private String liti_A;
	private String liti_B;

	
	@Size(max = 5000)
	private String liti_brief_description;
	private String liti_next_hearing_date;
	private String liti_status;
	private String liti_intenal_person;
	private int liti_added_by;
	private int liti_assigned_to;
	private String liti_assigned_name;
	private int liti_secondary_responsible;
	private String liti_secondary_responsible_name;
	private int liti_third_responsible_person;
	private String liti_third_responsible_person_name;
	private String liti_criticality;
	@Size(max = 3900)
	private String liti_oppo_party_address;
	private String liti_oppo_advo_law_firm;
	private String liti_oppo_advo_contact;
	private String liti_jurisdiction_name;
	@Size(max = 3900)
	private String liti_mail_id_cc;
	private String liti_party_by;
	private String liti_party_against;
	private String liti_litigation_result;
	
	
	
	
	
	
	public String getLiti_updated_by() {
		return liti_updated_by;
	}
	public String getLiti_B() {
		return liti_B;
	}
	public void setLiti_B(String liti_B) {
		this.liti_B = liti_B;
	}
	public String getLiti_A() {
		return liti_A;
	}
	public void setLiti_A(String liti_A) {
		this.liti_A = liti_A;
	}
	
	
	public String getLiti_updated_at() {
		return liti_updated_at;
	}
	public void setLiti_updated_at(String liti_updated_at) {
		this.liti_updated_at = liti_updated_at;
	}
	public void setLiti_updated_by(String liti_updated_by) {
		this.liti_updated_by = liti_updated_by;
	}
	public String getLiti_party_by() {
		return liti_party_by;
	}
	public void setLiti_party_by(String liti_party_by) {
		this.liti_party_by = liti_party_by;
	}
	public String getLiti_party_against() {
		return liti_party_against;
	}
	public void setLiti_party_against(String liti_party_against) {
		this.liti_party_against = liti_party_against;
	}
	public String getLiti_mail_id_cc() {
		return liti_mail_id_cc;
	}
	public void setLiti_mail_id_cc(String liti_mail_id_cc) {
		this.liti_mail_id_cc = liti_mail_id_cc;
	}
	public String getLiti_oppo_party_address() {
		return liti_oppo_party_address;
	}
	public void setLiti_oppo_party_address(String liti_oppo_party_address) {
		this.liti_oppo_party_address = liti_oppo_party_address;
	}
	public String getLiti_oppo_advo_law_firm() {
		return liti_oppo_advo_law_firm;
	}
	public void setLiti_oppo_advo_law_firm(String liti_oppo_advo_law_firm) {
		this.liti_oppo_advo_law_firm = liti_oppo_advo_law_firm;
	}
	public String getLiti_oppo_advo_contact() {
		return liti_oppo_advo_contact;
	}
	public void setLiti_oppo_advo_contact(String liti_oppo_advo_contact) {
		this.liti_oppo_advo_contact = liti_oppo_advo_contact;
	}
	public String getLiti_jurisdiction_name() {
		return liti_jurisdiction_name;
	}
	public void setLiti_jurisdiction_name(String liti_jurisdiction_name) {
		this.liti_jurisdiction_name = liti_jurisdiction_name;
	}
	public String getLiti_criticality() {
		return liti_criticality;
	}
	public void setLiti_criticality(String liti_criticality) {
		this.liti_criticality = liti_criticality;
	}
	public int getLiti_assigned_to() {
		return liti_assigned_to;
	}
	public void setLiti_assigned_to(int liti_assigned_to) {
		this.liti_assigned_to = liti_assigned_to;
	}
	public String getLiti_assigned_name() {
		return liti_assigned_name;
	}
	public void setLiti_assigned_name(String liti_assigned_name) {
		this.liti_assigned_name = liti_assigned_name;
	}
	public String getLiti_intenal_person() {
		return liti_intenal_person;
	}
	public void setLiti_intenal_person(String liti_intenal_person) {
		this.liti_intenal_person = liti_intenal_person;
	}
	public String getLiti_type_name() {
		return liti_type_name;
	}
	public void setLiti_type_name(String liti_type_name) {
		this.liti_type_name = liti_type_name;
	}
	public String getLiti_external_counsel() {
		return liti_external_counsel;
	}
	public void setLiti_external_counsel(String liti_external_counsel) {
		this.liti_external_counsel = liti_external_counsel;
	}
	public String getLiti_internally_handled_name() {
		return liti_internally_handled_name;
	}
	public void setLiti_internally_handled_name(String liti_internally_handled_name) {
		this.liti_internally_handled_name = liti_internally_handled_name;
	}
	public int getLiti_id() {
		return liti_id;
	}
	public void setLiti_id(int liti_id) {
		this.liti_id = liti_id;
	}
	public int getLiti_orga_id() {
		return liti_orga_id;
	}
	public void setLiti_orga_id(int liti_orga_id) {
		this.liti_orga_id = liti_orga_id;
	}
	public int getLiti_loca_id() {
		return liti_loca_id;
	}
	public void setLiti_loca_id(int liti_loca_id) {
		this.liti_loca_id = liti_loca_id;
	}
	public int getLiti_dept_id() {
		return liti_dept_id;
	}
	public void setLiti_dept_id(int liti_dept_id) {
		this.liti_dept_id = liti_dept_id;
	}
	public String getLiti_case_title() {
		return liti_case_title;
	}
	public void setLiti_case_title(String liti_case_title) {
		this.liti_case_title = liti_case_title;
	}
	public int getLiti_type_id() {
		return liti_type_id;
	}
	public void setLiti_type_id(int liti_type_id) {
		this.liti_type_id = liti_type_id;
	}
	public String getLiti_client_liti_id() {
		return liti_client_liti_id;
	}
	public void setLiti_client_liti_id(String liti_client_liti_id) {
		this.liti_client_liti_id = liti_client_liti_id;
	}
	public String getLiti_against_by_id() {
		return liti_against_by_id;
	}
	public void setLiti_against_by_id(String liti_against_by_id) {
		this.liti_against_by_id = liti_against_by_id;
	}
	public String getLiti_company_acting_as() {
		return liti_company_acting_as;
	}
	public void setLiti_company_acting_as(String liti_company_acting_as) {
		this.liti_company_acting_as = liti_company_acting_as;
	}
	public String getLiti_case_filing_date() {
		return liti_case_filing_date;
	}
	public void setLiti_case_filing_date(String liti_case_filing_date) {
		this.liti_case_filing_date = liti_case_filing_date;
	}
	public int getLiti_external_counsel_id() {
		return liti_external_counsel_id;
	}
	public void setLiti_external_counsel_id(int liti_external_counsel_id) {
		this.liti_external_counsel_id = liti_external_counsel_id;
	}
	public int getLiti_internally_handled_by() {
		return liti_internally_handled_by;
	}
	public void setLiti_internally_handled_by(int liti_internally_handled_by) {
		this.liti_internally_handled_by = liti_internally_handled_by;
	}
	public String getLiti_opposite_party() {
		return liti_opposite_party;
	}
	public void setLiti_opposite_party(String liti_opposite_party) {
		this.liti_opposite_party = liti_opposite_party;
	}
	public String getLiti_opp_party_acting_as() {
		return liti_opp_party_acting_as;
	}
	public void setLiti_opp_party_acting_as(String liti_opp_party_acting_as) {
		this.liti_opp_party_acting_as = liti_opp_party_acting_as;
	}
	public String getLiti_opposite_party_advocate() {
		return liti_opposite_party_advocate;
	}
	public void setLiti_opposite_party_advocate(String liti_opposite_party_advocate) {
		this.liti_opposite_party_advocate = liti_opposite_party_advocate;
	}
	public String getLiti_case_reference_no() {
		return liti_case_reference_no;
	}
	public void setLiti_case_reference_no(String liti_case_reference_no) {
		this.liti_case_reference_no = liti_case_reference_no;
	}
	public String getLiti_court() {
		return liti_court;
	}
	public void setLiti_court(String liti_court) {
		this.liti_court = liti_court;
	}
	public String getLiti_amount_involved() {
		return liti_amount_involved;
	}
	public void setLiti_amount_involved(String liti_amount_involved) {
		this.liti_amount_involved = liti_amount_involved;
	}
	public String getLiti_relevant_law() {
		return liti_relevant_law;
	}
	public void setLiti_relevant_law(String liti_relevant_law) {
		this.liti_relevant_law = liti_relevant_law;
	}
	public String getLiti_brief_description() {
		return liti_brief_description;
	}
	public void setLiti_brief_description(String liti_brief_description) {
		this.liti_brief_description = liti_brief_description;
	}
	public String getLiti_next_hearing_date() {
		return liti_next_hearing_date;
	}
	public void setLiti_next_hearing_date(String liti_next_hearing_date) {
		this.liti_next_hearing_date = liti_next_hearing_date;
	}
	public String getLiti_status() {
		return liti_status;
	}
	public void setLiti_status(String liti_status) {
		this.liti_status = liti_status;
	}
	public int getLiti_added_by() {
		return liti_added_by;
	}
	public void setLiti_added_by(int liti_added_by) {
		this.liti_added_by = liti_added_by;
	}
	public String getLiti_litigation_result() {
		return liti_litigation_result;
	}
	public void setLiti_litigation_result(String liti_litigation_result) {
		this.liti_litigation_result = liti_litigation_result;
	}
	public int getLiti_secondary_responsible() {
		return liti_secondary_responsible;
	}
	public void setLiti_secondary_responsible(int liti_secondary_responsible) {
		this.liti_secondary_responsible = liti_secondary_responsible;
	}
	public String getLiti_secondary_responsible_name() {
		return liti_secondary_responsible_name;
	}
	public void setLiti_secondary_responsible_name(String liti_secondary_responsible_name) {
		this.liti_secondary_responsible_name = liti_secondary_responsible_name;
	}
	public int getLiti_third_responsible_person() {
		return liti_third_responsible_person;
	}
	public void setLiti_third_responsible_person(int liti_third_responsible_person) {
		this.liti_third_responsible_person = liti_third_responsible_person;
	}
	public String getLiti_third_responsible_person_name() {
		return liti_third_responsible_person_name;
	}
	public void setLiti_third_responsible_person_name(String liti_third_responsible_person_name) {
		this.liti_third_responsible_person_name = liti_third_responsible_person_name;
	}
	public Date getLiti_acm_from() {
		return liti_acm_from;
	}
	public void setLiti_acm_from(Date liti_acm_from) {
		this.liti_acm_from = liti_acm_from;
	}
	public Date getLiti_acm_to() {
		return liti_acm_to;
	}
	public void setLiti_acm_to(Date liti_acm_to) {
		this.liti_acm_to = liti_acm_to;
	}
	
	
}
