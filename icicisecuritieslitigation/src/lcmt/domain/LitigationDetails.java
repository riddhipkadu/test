package lcmt.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;


/*
 * Author : Mahesh Kharote
 * Created Date : 26/08/2016
 * Updated By : 
 * Updated Date : 
 *  
 * */
public class LitigationDetails {

	private int liti_id;
	private String liti_against_by_id;
	private String liti_type_name;
	private String liti_case_reference_no;
	private String liti_case_filing_date;
	private String liti_receipt_notice_date;
	private String exte_coun_name;
	private String liti_internally_handled_name;
	private String liti_opposite_party;
	private String liti_court;
	//private String liti_forum;
	private String liti_amount_involved;
	private String liti_relevant_law;
	private String liti_brief_description;
	private String liti_nature_of_complaint;
	private String liti_status_of_case;
	private String liti_link_of_website;	
	private String liti_interest;
	private String liti_total_amount;
	private String liti_status;
	//private String liti_litigation_reference;
	private String liti_litigation_result;
	private String liti_disposal_date;
	private String liti_synopsis_of_order;
	private String liti_last_date_for_filling_appeal;
	private String liti_comments;
	private List<LitigationDocuments> liti_documents;
	private int exte_coun_id;
	private int liti_advocate_id;
	private String liti_advocate_name;
	private String liti_completion_court_name;
	private String liti_client_liti_id;
	private String liti_intenal_person; 
	private String liti_involved_amt_currency;
	private String liti_assigned_name;
	private String liti_secondary_responsible_name;
	private String liti_third_responsible_name;
	private String liti_fourth_responsible_name;
	private String liti_criticality;
	private int liti_internal_liti_code;
	private String liti_internal_liti_code_name;
	private String liti_pay;
	private String liti_part_acm;
	private String liti_acm_note_date;
	@Size(max = 3900)
	private String liti_oppo_party_address;
	private String liti_oppo_advo_law_firm;
	private String liti_oppo_advo_contact;
	private String liti_jurisdiction_name;
	private String liti_opposite_party_advocate;
	private String liti_previous_liti_ref_no;
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
	public String getLiti_previous_liti_ref_no() {
		return liti_previous_liti_ref_no;
	}
	public void setLiti_previous_liti_ref_no(String liti_previous_liti_ref_no) {
		this.liti_previous_liti_ref_no = liti_previous_liti_ref_no;
	}
	public String getLiti_opposite_party_advocate() {
		return liti_opposite_party_advocate;
	}
	public void setLiti_opposite_party_advocate(String liti_opposite_party_advocate) {
		this.liti_opposite_party_advocate = liti_opposite_party_advocate;
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
	public String getLiti_internal_liti_code_name() {
		return liti_internal_liti_code_name;
	}
	public void setLiti_internal_liti_code_name(String liti_internal_liti_code_name) {
		this.liti_internal_liti_code_name = liti_internal_liti_code_name;
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
	public String getLiti_assigned_name() {
		return liti_assigned_name;
	}
	public void setLiti_assigned_name(String liti_assigned_name) {
		this.liti_assigned_name = liti_assigned_name;
	}
	public String getLiti_involved_amt_currency() {
		return liti_involved_amt_currency;
	}
	public void setLiti_involved_amt_currency(String liti_involved_amt_currency) {
		this.liti_involved_amt_currency = liti_involved_amt_currency;
	}
	public String getLiti_intenal_person() {
		return liti_intenal_person;
	}
	public void setLiti_intenal_person(String liti_intenal_person) {
		this.liti_intenal_person = liti_intenal_person;
	}
	public String getLiti_client_liti_id() {
		return liti_client_liti_id;
	}
	public void setLiti_client_liti_id(String liti_client_liti_id) {
		this.liti_client_liti_id = liti_client_liti_id;
	}
	public String getLiti_completion_court_name() {
		return liti_completion_court_name;
	}
	public void setLiti_completion_court_name(String liti_completion_court_name) {
		this.liti_completion_court_name = liti_completion_court_name;
	}
	public int getLiti_id() {
		return liti_id;
	}
	public void setLiti_id(int liti_id) {
		this.liti_id = liti_id;
	}
	public String getLiti_against_by_id() {
		return liti_against_by_id;
	}
	public void setLiti_against_by_id(String liti_against_by_id) {
		this.liti_against_by_id = liti_against_by_id;
	}
	public String getLiti_type_name() {
		return liti_type_name;
	}
	public void setLiti_type_name(String liti_type_name) {
		this.liti_type_name = liti_type_name;
	}
	public String getLiti_case_reference_no() {
		return liti_case_reference_no;
	}
	public void setLiti_case_reference_no(String liti_case_reference_no) {
		this.liti_case_reference_no = liti_case_reference_no;
	}
	
	public String getLiti_case_filing_date() {
		return liti_case_filing_date;
	}
	public void setLiti_case_filing_date(String liti_case_filing_date) {
		this.liti_case_filing_date = liti_case_filing_date;
	}
	public String getExte_coun_name() {
		return exte_coun_name;
	}
	public void setExte_coun_name(String exte_coun_name) {
		this.exte_coun_name = exte_coun_name;
	}
	public String getLiti_internally_handled_name() {
		return liti_internally_handled_name;
	}
	public void setLiti_internally_handled_name(String liti_internally_handled_name) {
		this.liti_internally_handled_name = liti_internally_handled_name;
	}
	public String getLiti_opposite_party() {
		return liti_opposite_party;
	}
	public void setLiti_opposite_party(String liti_opposite_party) {
		this.liti_opposite_party = liti_opposite_party;
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
	public String getLiti_status() {
		return liti_status;
	}
	public void setLiti_status(String liti_status) {
		this.liti_status = liti_status;
	}
	public String getLiti_litigation_result() {
		return liti_litigation_result;
	}
	public void setLiti_litigation_result(String liti_litigation_result) {
		this.liti_litigation_result = liti_litigation_result;
	}
	public String getLiti_disposal_date() {
		return liti_disposal_date;
	}
	public void setLiti_disposal_date(String liti_disposal_date) {
		this.liti_disposal_date = liti_disposal_date;
	}
	public String getLiti_synopsis_of_order() {
		return liti_synopsis_of_order;
	}
	public void setLiti_synopsis_of_order(String liti_synopsis_of_order) {
		this.liti_synopsis_of_order = liti_synopsis_of_order;
	}
	public String getLiti_last_date_for_filling_appeal() {
		return liti_last_date_for_filling_appeal;
	}
	public void setLiti_last_date_for_filling_appeal(String liti_last_date_for_filling_appeal) {
		this.liti_last_date_for_filling_appeal = liti_last_date_for_filling_appeal;
	}
	public String getLiti_comments() {
		return liti_comments;
	}
	public void setLiti_comments(String liti_comments) {
		this.liti_comments = liti_comments;
	}
	public List<LitigationDocuments> getLiti_documents() {
		return liti_documents;
	}
	public void setLiti_documents(List<LitigationDocuments> liti_documents) {
		this.liti_documents = liti_documents;
	}
	public int getExte_coun_id() {
		return exte_coun_id;
	}
	public void setExte_coun_id(int exte_coun_id) {
		this.exte_coun_id = exte_coun_id;
	}
	public int getLiti_advocate_id() {
		return liti_advocate_id;
	}
	public void setLiti_advocate_id(int liti_advocate_id) {
		this.liti_advocate_id = liti_advocate_id;
	}
	public String getLiti_advocate_name() {
		return liti_advocate_name;
	}
	public void setLiti_advocate_name(String liti_advocate_name) {
		this.liti_advocate_name = liti_advocate_name;
	}
	public String getLiti_nature_of_complaint() {
		return liti_nature_of_complaint;
	}
	public void setLiti_nature_of_complaint(String liti_nature_of_complaint) {
		this.liti_nature_of_complaint = liti_nature_of_complaint;
	}
	public String getLiti_status_of_case() {
		return liti_status_of_case;
	}
	public void setLiti_status_of_case(String liti_status_of_case) {
		this.liti_status_of_case = liti_status_of_case;
	}
	public String getLiti_link_of_website() {
		return liti_link_of_website;
	}
	public void setLiti_link_of_website(String liti_link_of_website) {
		this.liti_link_of_website = liti_link_of_website;
	}
	public String getLiti_interest() {
		return liti_interest;
	}
	public void setLiti_interest(String liti_interest) {
		this.liti_interest = liti_interest;
	}
	public String getLiti_total_amount() {
		return liti_total_amount;
	}
	public void setLiti_total_amount(String liti_total_amount) {
		this.liti_total_amount = liti_total_amount;
	}
	public String getLiti_receipt_notice_date() {
		return liti_receipt_notice_date;
	}
	public void setLiti_receipt_notice_date(String liti_receipt_notice_date) {
		this.liti_receipt_notice_date = liti_receipt_notice_date;
	}
	public String getLiti_secondary_responsible_name() {
		return liti_secondary_responsible_name;
	}
	public void setLiti_secondary_responsible_name(String liti_secondary_responsible_name) {
		this.liti_secondary_responsible_name = liti_secondary_responsible_name;
	}
	public String getLiti_third_responsible_name() {
		return liti_third_responsible_name;
	}
	public void setLiti_third_responsible_name(String liti_third_responsible_name) {
		this.liti_third_responsible_name = liti_third_responsible_name;
	}
	public String getLiti_fourth_responsible_name() {
		return liti_fourth_responsible_name;
	}
	public void setLiti_fourth_responsible_name(String liti_fourth_responsible_name) {
		this.liti_fourth_responsible_name = liti_fourth_responsible_name;
	}
	public String getLiti_pay() {
		return liti_pay;
	}
	public void setLiti_pay(String liti_pay) {
		this.liti_pay = liti_pay;
	}
	public String getLiti_part_acm() {
		return liti_part_acm;
	}
	public void setLiti_part_acm(String liti_part_acm) {
		this.liti_part_acm = liti_part_acm;
	}
	public String getLiti_acm_note_date() {
		return liti_acm_note_date;
	}
	public void setLiti_acm_note_date(String liti_acm_note_date) {
		this.liti_acm_note_date = liti_acm_note_date;
	}
	
	

	
	
	
	
	
}
