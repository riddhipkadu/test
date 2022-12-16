package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/*
 * Author : Mahesh Kharote
 * Created Date : 26/08/2016
 * Updated By : 
 * Updated Date : 
 *  
 * */
@Entity(name = "mst_litigation")
public class Litigation {
	
	@Id
	@GeneratedValue
	private int liti_id;
	private int liti_orga_id;
	private int liti_loca_id;
	private int liti_dept_id;
	private String liti_case_title;
	private int liti_type_id;
	private String liti_client_liti_id;
	private int liti_last_generated_value_for_client_liti_id;
	private String liti_against_by_id;
	private String liti_company_acting_as;
	private Date liti_case_filing_date;
	private int liti_external_counsel_id;
	private int liti_internally_handled_by;
	private String liti_opposite_party;
	private String liti_opp_party_acting_as;
	private String liti_opposite_party_advocate;
	private String liti_case_reference_no;
	private String liti_court;
	private String liti_amount_involved;
	@Size(max = 3900)
	private String liti_relevant_law;
	@Size(max = 3900)
	private String liti_brief_description;
	private int liti_added_by;
	private Date liti_created_at;
	private String liti_litigation_result;
	private Date liti_disposal_date;
	private String liti_synopsis_of_order;
	private Date liti_last_date_for_filling_appeal;
	@Size(max = 3900)
	private String liti_comments;
	private String liti_status;
	private int liti_advocate_id;
	private String liti_involved_amt_currency;
	private double liti_conversion_rate;
	private double liti_converted_amt;
	private String liti_converted_amt_currency;
	private int liti_counsel_law_firm;
	private int liti_advocate_law_firm;
	private int liti_completion_court_id;
	private String liti_intenal_person;
	private int liti_assigned_to;
	private String liti_criticality;
	private int liti_internal_liti_code;
	@Size(max = 3900)
	private String liti_oppo_party_address;
	private String liti_oppo_advo_law_firm;
	private String liti_oppo_advo_contact;
	private String liti_jurisdiction_name;
	private String liti_previous_liti_ref_no;
	@Size(max = 3900)
	private String liti_mail_id_cc;
	private String file_appeal_result;
	private String liti_party_against;
	private String liti_party_by;
	
	public String getLiti_party_against() {
		return liti_party_against;
	}
	public void setLiti_party_against(String liti_party_against) {
		this.liti_party_against = liti_party_against;
	}
	public String getLiti_party_by() {
		return liti_party_by;
	}
	public void setLiti_party_by(String liti_party_by) {
		this.liti_party_by = liti_party_by;
	}
	public String getFile_appeal_result() {
		return file_appeal_result;
	}
	public void setFile_appeal_result(String file_appeal_result) {
		this.file_appeal_result = file_appeal_result;
	}
	public String getLiti_mail_id_cc() {
		return liti_mail_id_cc;
	}
	public void setLiti_mail_id_cc(String liti_mail_id_cc) {
		this.liti_mail_id_cc = liti_mail_id_cc;
	}
	public String getLiti_previous_liti_ref_no() {
		return liti_previous_liti_ref_no;
	}
	public void setLiti_previous_liti_ref_no(String liti_previous_liti_ref_no) {
		this.liti_previous_liti_ref_no = liti_previous_liti_ref_no;
	}
	public String getLiti_jurisdiction_name() {
		return liti_jurisdiction_name;
	}
	public void setLiti_jurisdiction_name(String liti_jurisdiction_name) {
		this.liti_jurisdiction_name = liti_jurisdiction_name;
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
	public int getLiti_internal_liti_code() {
		return liti_internal_liti_code;
	}
	public void setLiti_internal_liti_code(int liti_internal_liti_code) {
		this.liti_internal_liti_code = liti_internal_liti_code;
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
	public String getLiti_intenal_person() {
		return liti_intenal_person;
	}
	public void setLiti_intenal_person(String liti_intenal_person) {
		this.liti_intenal_person = liti_intenal_person;
	}
	public int getLiti_completion_court_id() {
		return liti_completion_court_id;
	}
	public void setLiti_completion_court_id(int liti_completion_court_id) {
		this.liti_completion_court_id = liti_completion_court_id;
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
	public int getLiti_last_generated_value_for_client_liti_id() {
		return liti_last_generated_value_for_client_liti_id;
	}
	public void setLiti_last_generated_value_for_client_liti_id(int liti_last_generated_value_for_client_liti_id) {
		this.liti_last_generated_value_for_client_liti_id = liti_last_generated_value_for_client_liti_id;
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
	public Date getLiti_case_filing_date() {
		return liti_case_filing_date;
	}
	public void setLiti_case_filing_date(Date liti_case_filing_date) {
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
	public int getLiti_added_by() {
		return liti_added_by;
	}
	public void setLiti_added_by(int liti_added_by) {
		this.liti_added_by = liti_added_by;
	}
	public Date getLiti_created_at() {
		return liti_created_at;
	}
	public void setLiti_created_at(Date liti_created_at) {
		this.liti_created_at = liti_created_at;
	}
	public String getLiti_litigation_result() {
		return liti_litigation_result;
	}
	public void setLiti_litigation_result(String liti_litigation_result) {
		this.liti_litigation_result = liti_litigation_result;
	}
	public Date getLiti_disposal_date() {
		return liti_disposal_date;
	}
	public void setLiti_disposal_date(Date liti_disposal_date) {
		this.liti_disposal_date = liti_disposal_date;
	}
	public String getLiti_synopsis_of_order() {
		return liti_synopsis_of_order;
	}
	public void setLiti_synopsis_of_order(String liti_synopsis_of_order) {
		this.liti_synopsis_of_order = liti_synopsis_of_order;
	}
	public Date getLiti_last_date_for_filling_appeal() {
		return liti_last_date_for_filling_appeal;
	}
	public void setLiti_last_date_for_filling_appeal(Date liti_last_date_for_filling_appeal) {
		this.liti_last_date_for_filling_appeal = liti_last_date_for_filling_appeal;
	}
	public String getLiti_comments() {
		return liti_comments;
	}
	public void setLiti_comments(String liti_comments) {
		this.liti_comments = liti_comments;
	}
	public String getLiti_status() {
		return liti_status;
	}
	public void setLiti_status(String liti_status) {
		this.liti_status = liti_status;
	}
	public int getLiti_advocate_id() {
		return liti_advocate_id;
	}
	public void setLiti_advocate_id(int liti_advocate_id) {
		this.liti_advocate_id = liti_advocate_id;
	}
	public String getLiti_involved_amt_currency() {
		return liti_involved_amt_currency;
	}
	public void setLiti_involved_amt_currency(String liti_involved_amt_currency) {
		this.liti_involved_amt_currency = liti_involved_amt_currency;
	}
	public Double getLiti_conversion_rate() {
		return liti_conversion_rate;
	}
	public void setLiti_conversion_rate(Double liti_conversion_rate) {
		this.liti_conversion_rate = liti_conversion_rate;
	}
	public Double getLiti_converted_amt() {
		return liti_converted_amt;
	}
	public void setLiti_converted_amt(Double liti_converted_amt) {
		this.liti_converted_amt = liti_converted_amt;
	}
	public String getLiti_converted_amt_currency() {
		return liti_converted_amt_currency;
	}
	public void setLiti_converted_amt_currency(String liti_converted_amt_currency) {
		this.liti_converted_amt_currency = liti_converted_amt_currency;
	}
	public int getLiti_counsel_law_firm() {
		return liti_counsel_law_firm;
	}
	public void setLiti_counsel_law_firm(int liti_counsel_law_firm) {
		this.liti_counsel_law_firm = liti_counsel_law_firm;
	}
	public int getLiti_advocate_law_firm() {
		return liti_advocate_law_firm;
	}
	public void setLiti_advocate_law_firm(int liti_advocate_law_firm) {
		this.liti_advocate_law_firm = liti_advocate_law_firm;
	}
	public void setLiti_conversion_rate(double liti_conversion_rate) {
		this.liti_conversion_rate = liti_conversion_rate;
	}
	public void setLiti_converted_amt(double liti_converted_amt) {
		this.liti_converted_amt = liti_converted_amt;
	}
	
	
	
}
