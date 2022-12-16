package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name="trn_hearing_stages")
public class HearingStage {

	@Id
	@GeneratedValue
	private int ttrn_hearing_stage;
	private int ttrn_litigation_id;
	private int ttrn_stage_id;
	private Date ttrn_hearing_stage_date;
	private Date ttrn_next_hearing_date;
	private Date ttrn_first_alert;
	private Date ttrn_second_alert;
	private Date ttrn_third_alert;
	private String ttrn_additional_mail_id_1;
	private String ttrn_additional_mail_id_2;
	private String ttrn_additional_mail_id_3;
	@Size(max = 3900)
	private String ttrn_stage_description;
	@Size(max = 3900)
	private String ttrn_action_item;
	private int ttrn_responsible_person;
	private int ttrn_secondary_person;
	private int ttrn_counsel_id;
	private Date ttrn_stage_due_date;
	private int ttrn_added_by;
	private Date ttrn_created_at;
	private Date ttrn_updated_at;
	private int ttrn_counsel_law_firm_id;
	private String ttrn_stage_status;
	
	public int getTtrn_hearing_stage() {
		return ttrn_hearing_stage;
	}
	public void setTtrn_hearing_stage(int ttrn_hearing_stage) {
		this.ttrn_hearing_stage = ttrn_hearing_stage;
	}
	public int getTtrn_litigation_id() {
		return ttrn_litigation_id;
	}
	public void setTtrn_litigation_id(int ttrn_litigation_id) {
		this.ttrn_litigation_id = ttrn_litigation_id;
	}
	public int getTtrn_stage_id() {
		return ttrn_stage_id;
	}
	public void setTtrn_stage_id(int ttrn_stage_id) {
		this.ttrn_stage_id = ttrn_stage_id;
	}
	public Date getTtrn_hearing_stage_date() {
		return ttrn_hearing_stage_date;
	}
	public void setTtrn_hearing_stage_date(Date ttrn_hearing_stage_date) {
		this.ttrn_hearing_stage_date = ttrn_hearing_stage_date;
	}
	public Date getTtrn_next_hearing_date() {
		return ttrn_next_hearing_date;
	}
	public void setTtrn_next_hearing_date(Date ttrn_next_hearing_date) {
		this.ttrn_next_hearing_date = ttrn_next_hearing_date;
	}
	public Date getTtrn_first_alert() {
		return ttrn_first_alert;
	}
	public void setTtrn_first_alert(Date ttrn_first_alert) {
		this.ttrn_first_alert = ttrn_first_alert;
	}
	public Date getTtrn_second_alert() {
		return ttrn_second_alert;
	}
	public void setTtrn_second_alert(Date ttrn_second_alert) {
		this.ttrn_second_alert = ttrn_second_alert;
	}
	public Date getTtrn_third_alert() {
		return ttrn_third_alert;
	}
	public void setTtrn_third_alert(Date ttrn_third_alert) {
		this.ttrn_third_alert = ttrn_third_alert;
	}
	public String getTtrn_additional_mail_id_1() {
		return ttrn_additional_mail_id_1;
	}
	public void setTtrn_additional_mail_id_1(String ttrn_additional_mail_id_1) {
		this.ttrn_additional_mail_id_1 = ttrn_additional_mail_id_1;
	}
	public String getTtrn_additional_mail_id_2() {
		return ttrn_additional_mail_id_2;
	}
	public void setTtrn_additional_mail_id_2(String ttrn_additional_mail_id_2) {
		this.ttrn_additional_mail_id_2 = ttrn_additional_mail_id_2;
	}
	public String getTtrn_additional_mail_id_3() {
		return ttrn_additional_mail_id_3;
	}
	public void setTtrn_additional_mail_id_3(String ttrn_additional_mail_id_3) {
		this.ttrn_additional_mail_id_3 = ttrn_additional_mail_id_3;
	}
	public String getTtrn_stage_description() {
		return ttrn_stage_description;
	}
	public void setTtrn_stage_description(String ttrn_stage_description) {
		this.ttrn_stage_description = ttrn_stage_description;
	}
	public String getTtrn_action_item() {
		return ttrn_action_item;
	}
	public void setTtrn_action_item(String ttrn_action_item) {
		this.ttrn_action_item = ttrn_action_item;
	}
	public int getTtrn_responsible_person() {
		return ttrn_responsible_person;
	}
	public void setTtrn_responsible_person(int ttrn_responsible_person) {
		this.ttrn_responsible_person = ttrn_responsible_person;
	}
	public int getTtrn_counsel_id() {
		return ttrn_counsel_id;
	}
	public void setTtrn_counsel_id(int ttrn_counsel_id) {
		this.ttrn_counsel_id = ttrn_counsel_id;
	}
	public Date getTtrn_stage_due_date() {
		return ttrn_stage_due_date;
	}
	public void setTtrn_stage_due_date(Date ttrn_stage_due_date) {
		this.ttrn_stage_due_date = ttrn_stage_due_date;
	}
	public int getTtrn_added_by() {
		return ttrn_added_by;
	}
	public void setTtrn_added_by(int ttrn_added_by) {
		this.ttrn_added_by = ttrn_added_by;
	}
	public Date getTtrn_created_at() {
		return ttrn_created_at;
	}
	public void setTtrn_created_at(Date ttrn_created_at) {
		this.ttrn_created_at = ttrn_created_at;
	}
	public Date getTtrn_updated_at() {
		return ttrn_updated_at;
	}
	public void setTtrn_updated_at(Date ttrn_updated_at) {
		this.ttrn_updated_at = ttrn_updated_at;
	}
	public int getTtrn_counsel_law_firm_id() {
		return ttrn_counsel_law_firm_id;
	}
	public void setTtrn_counsel_law_firm_id(int ttrn_counsel_law_firm_id) {
		this.ttrn_counsel_law_firm_id = ttrn_counsel_law_firm_id;
	}
	public String getTtrn_stage_status() {
		return ttrn_stage_status;
	}
	public void setTtrn_stage_status(String ttrn_stage_status) {
		this.ttrn_stage_status = ttrn_stage_status;
	}
	public int getTtrn_secondary_person() {
		return ttrn_secondary_person;
	}
	public void setTtrn_secondary_person(int ttrn_secondary_person) {
		this.ttrn_secondary_person = ttrn_secondary_person;
	}
	
	
	
	
}
