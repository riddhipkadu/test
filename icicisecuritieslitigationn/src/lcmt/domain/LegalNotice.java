package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/*
 * Author : Harshad Padole
 * Created Date : 2/09/2016
 * Updated By : 
 * Updated Date : 
 *  
 * */

@Entity(name="mst_legal_notice")
public class LegalNotice {
	
	@Id
	@GeneratedValue
	private int lega_noti_id;
	private int lega_noti_entity_id;
	private int lega_noti_unit_id;
	private int lega_noti_function_id;
	private String lega_noti_by_against;
	private String lega_noti_opposite_party;
	private int lega_noti_category_id;
	private double lega_noti_amount_involved;
	private double lega_noti_interest;
	private double lega_noti_total_amount;
	private String lega_noti_reference_no;
	private String lega_noti_addressed_to;
	private Date lega_noti_dated;
	private Date lega_noti_recived_on;
	private Date lega_noti_reply_deadline;
	private int lega_noti_assigned_to_id;
	private int lega_noti_secondary_responsible_person;
	private int lega_noti_third_responsible_person;
	private int lega_noti_other_responsible_person;
	private int lega_noti_external_counsel_id;
	private String lega_noti_opposite_party_advocate;
	private String lega_noti_relevant_law;
	@Size(max = 3900)
	private String lega_noti_comments;
	@Size(max = 3900)
	private String lega_noti_prayer_details;
	private String lega_noti_status;
	private Date lega_noti_reminder_date;
	private Date lega_noti_created_at;
	private Date lega_noti_updated_at;
	private int lega_noti_added_by;
	private String lega_noti_intern_cont_person;
	private String lega_noti_involved_amt_currency;
	private double lega_noti_conversion_rate;
	private double lega_noti_converted_amt;
	private String lega_noti_converted_amt_currency;
	private String lega_noti_liti_ref_id;
	
	public String getLega_noti_liti_ref_id() {
		return lega_noti_liti_ref_id;
	}
	public void setLega_noti_liti_ref_id(String lega_noti_liti_ref_id) {
		this.lega_noti_liti_ref_id = lega_noti_liti_ref_id;
	}
	public String getLega_noti_involved_amt_currency() {
		return lega_noti_involved_amt_currency;
	}
	public void setLega_noti_involved_amt_currency(String lega_noti_involved_amt_currency) {
		this.lega_noti_involved_amt_currency = lega_noti_involved_amt_currency;
	}
	public double getLega_noti_conversion_rate() {
		return lega_noti_conversion_rate;
	}
	public void setLega_noti_conversion_rate(double lega_noti_conversion_rate) {
		this.lega_noti_conversion_rate = lega_noti_conversion_rate;
	}
	public double getLega_noti_converted_amt() {
		return lega_noti_converted_amt;
	}
	public void setLega_noti_converted_amt(double lega_noti_converted_amt) {
		this.lega_noti_converted_amt = lega_noti_converted_amt;
	}
	public String getLega_noti_converted_amt_currency() {
		return lega_noti_converted_amt_currency;
	}
	public void setLega_noti_converted_amt_currency(String lega_noti_converted_amt_currency) {
		this.lega_noti_converted_amt_currency = lega_noti_converted_amt_currency;
	}
	public String getLega_noti_intern_cont_person() {
		return lega_noti_intern_cont_person;
	}
	public void setLega_noti_intern_cont_person(String lega_noti_intern_cont_person) {
		this.lega_noti_intern_cont_person = lega_noti_intern_cont_person;
	}
	public int getLega_noti_id() {
		return lega_noti_id;
	}
	public void setLega_noti_id(int lega_noti_id) {
		this.lega_noti_id = lega_noti_id;
	}
	public int getLega_noti_entity_id() {
		return lega_noti_entity_id;
	}
	public void setLega_noti_entity_id(int lega_noti_entity_id) {
		this.lega_noti_entity_id = lega_noti_entity_id;
	}
	public int getLega_noti_unit_id() {
		return lega_noti_unit_id;
	}
	public void setLega_noti_unit_id(int lega_noti_unit_id) {
		this.lega_noti_unit_id = lega_noti_unit_id;
	}
	public int getLega_noti_function_id() {
		return lega_noti_function_id;
	}
	public void setLega_noti_function_id(int lega_noti_function_id) {
		this.lega_noti_function_id = lega_noti_function_id;
	}
	public String getLega_noti_by_against() {
		return lega_noti_by_against;
	}
	public void setLega_noti_by_against(String lega_noti_by_against) {
		this.lega_noti_by_against = lega_noti_by_against;
	}
	public String getLega_noti_opposite_party() {
		return lega_noti_opposite_party;
	}
	public void setLega_noti_opposite_party(String lega_noti_opposite_party) {
		this.lega_noti_opposite_party = lega_noti_opposite_party;
	}
	public int getLega_noti_category_id() {
		return lega_noti_category_id;
	}
	public void setLega_noti_category_id(int lega_noti_category_id) {
		this.lega_noti_category_id = lega_noti_category_id;
	}
	public double getLega_noti_amount_involved() {
		return lega_noti_amount_involved;
	}
	public void setLega_noti_amount_involved(double lega_noti_amount_involved) {
		this.lega_noti_amount_involved = lega_noti_amount_involved;
	}
	public String getLega_noti_reference_no() {
		return lega_noti_reference_no;
	}
	public void setLega_noti_reference_no(String lega_noti_reference_no) {
		this.lega_noti_reference_no = lega_noti_reference_no;
	}
	public String getLega_noti_addressed_to() {
		return lega_noti_addressed_to;
	}
	public void setLega_noti_addressed_to(String lega_noti_addressed_to) {
		this.lega_noti_addressed_to = lega_noti_addressed_to;
	}
	public Date getLega_noti_dated() {
		return lega_noti_dated;
	}
	public void setLega_noti_dated(Date lega_noti_dated) {
		this.lega_noti_dated = lega_noti_dated;
	}
	public Date getLega_noti_recived_on() {
		return lega_noti_recived_on;
	}
	public void setLega_noti_recived_on(Date lega_noti_recived_on) {
		this.lega_noti_recived_on = lega_noti_recived_on;
	}
	public Date getLega_noti_reply_deadline() {
		return lega_noti_reply_deadline;
	}
	public void setLega_noti_reply_deadline(Date lega_noti_reply_deadline) {
		this.lega_noti_reply_deadline = lega_noti_reply_deadline;
	}
	public int getLega_noti_assigned_to_id() {
		return lega_noti_assigned_to_id;
	}
	public void setLega_noti_assigned_to_id(int lega_noti_assigned_to_id) {
		this.lega_noti_assigned_to_id = lega_noti_assigned_to_id;
	}
	public int getLega_noti_external_counsel_id() {
		return lega_noti_external_counsel_id;
	}
	public void setLega_noti_external_counsel_id(int lega_noti_external_counsel_id) {
		this.lega_noti_external_counsel_id = lega_noti_external_counsel_id;
	}
	public String getLega_noti_opposite_party_advocate() {
		return lega_noti_opposite_party_advocate;
	}
	public void setLega_noti_opposite_party_advocate(String lega_noti_opposite_party_advocate) {
		this.lega_noti_opposite_party_advocate = lega_noti_opposite_party_advocate;
	}
	public String getLega_noti_relevant_law() {
		return lega_noti_relevant_law;
	}
	public void setLega_noti_relevant_law(String lega_noti_relevant_law) {
		this.lega_noti_relevant_law = lega_noti_relevant_law;
	}
	public String getLega_noti_comments() {
		return lega_noti_comments;
	}
	public void setLega_noti_comments(String lega_noti_comments) {
		this.lega_noti_comments = lega_noti_comments;
	}
	public String getLega_noti_status() {
		return lega_noti_status;
	}
	public void setLega_noti_status(String lega_noti_status) {
		this.lega_noti_status = lega_noti_status;
	}
	public Date getLega_noti_reminder_date() {
		return lega_noti_reminder_date;
	}
	public void setLega_noti_reminder_date(Date lega_noti_reminder_date) {
		this.lega_noti_reminder_date = lega_noti_reminder_date;
	}
	public Date getLega_noti_created_at() {
		return lega_noti_created_at;
	}
	public void setLega_noti_created_at(Date lega_noti_created_at) {
		this.lega_noti_created_at = lega_noti_created_at;
	}
	public Date getLega_noti_updated_at() {
		return lega_noti_updated_at;
	}
	public void setLega_noti_updated_at(Date lega_noti_updated_at) {
		this.lega_noti_updated_at = lega_noti_updated_at;
	}
	public int getLega_noti_added_by() {
		return lega_noti_added_by;
	}
	public void setLega_noti_added_by(int lega_noti_added_by) {
		this.lega_noti_added_by = lega_noti_added_by;
	}
	public String getLega_noti_prayer_details() {
		return lega_noti_prayer_details;
	}
	public void setLega_noti_prayer_details(String lega_noti_prayer_details) {
		this.lega_noti_prayer_details = lega_noti_prayer_details;
	}
	public int getLega_noti_secondary_responsible_person() {
		return lega_noti_secondary_responsible_person;
	}
	public void setLega_noti_secondary_responsible_person(int lega_noti_secondary_responsible_person) {
		this.lega_noti_secondary_responsible_person = lega_noti_secondary_responsible_person;
	}
	public int getLega_noti_third_responsible_person() {
		return lega_noti_third_responsible_person;
	}
	public void setLega_noti_third_responsible_person(int lega_noti_third_responsible_person) {
		this.lega_noti_third_responsible_person = lega_noti_third_responsible_person;
	}
	public int getLega_noti_other_responsible_person() {
		return lega_noti_other_responsible_person;
	}
	public void setLega_noti_other_responsible_person(int lega_noti_other_responsible_person) {
		this.lega_noti_other_responsible_person = lega_noti_other_responsible_person;
	}
	public double getLega_noti_interest() {
		return lega_noti_interest;
	}
	public void setLega_noti_interest(double lega_noti_interest) {
		this.lega_noti_interest = lega_noti_interest;
	}
	public double getLega_noti_total_amount() {
		return lega_noti_total_amount;
	}
	public void setLega_noti_total_amount(double lega_noti_total_amount) {
		this.lega_noti_total_amount = lega_noti_total_amount;
	}
	
	
	
	

}
