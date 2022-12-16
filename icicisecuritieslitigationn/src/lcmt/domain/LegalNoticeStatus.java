package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Entity(name="trn_legal_notice_status")
public class LegalNoticeStatus {

	@Id
	@GeneratedValue
	private int lega_status_id;
	private int lega_notice_id;
	private Date lega_action_item_due_date;
	private Date lega_reminder_date;
	private int lega_person_responsible;
	private String lega_notice_status;
	private Date lega_created_at;
	private int lega_status_added_by;
	private Date lega_received_date;
	private String lega_person_responsible_others;
	
	@Size(max = 10000)
	private String lega_action_taken;
	private String lega_next_action_item;
	
	
	public Date getLega_received_date() {
		return lega_received_date;
	}
	public void setLega_received_date(Date lega_received_date) {
		this.lega_received_date = lega_received_date;
	}
	public String getLega_person_responsible_others() {
		return lega_person_responsible_others;
	}
	public void setLega_person_responsible_others(String lega_person_responsible_others) {
		this.lega_person_responsible_others = lega_person_responsible_others;
	}
	public int getLega_status_id() {
		return lega_status_id;
	}
	public void setLega_status_id(int lega_status_id) {
		this.lega_status_id = lega_status_id;
	}
	public int getLega_notice_id() {
		return lega_notice_id;
	}
	public void setLega_notice_id(int lega_notice_id) {
		this.lega_notice_id = lega_notice_id;
	}
	public String getLega_action_taken() {
		return lega_action_taken;
	}
	public void setLega_action_taken(String lega_action_taken) {
		this.lega_action_taken = lega_action_taken;
	}
	public String getLega_next_action_item() {
		return lega_next_action_item;
	}
	public void setLega_next_action_item(String lega_next_action_item) {
		this.lega_next_action_item = lega_next_action_item;
	}
	public Date getLega_action_item_due_date() {
		return lega_action_item_due_date;
	}
	public void setLega_action_item_due_date(Date lega_action_item_due_date) {
		this.lega_action_item_due_date = lega_action_item_due_date;
	}
	public Date getLega_reminder_date() {
		return lega_reminder_date;
	}
	public void setLega_reminder_date(Date lega_reminder_date) {
		this.lega_reminder_date = lega_reminder_date;
	}
	public int getLega_person_responsible() {
		return lega_person_responsible;
	}
	public void setLega_person_responsible(int lega_person_responsible) {
		this.lega_person_responsible = lega_person_responsible;
	}
	
	public String getLega_notice_status() {
		return lega_notice_status;
	}
	public void setLega_notice_status(String lega_notice_status) {
		this.lega_notice_status = lega_notice_status;
	}
	public Date getLega_created_at() {
		return lega_created_at;
	}
	public void setLega_created_at(Date lega_created_at) {
		this.lega_created_at = lega_created_at;
	}
	public int getLega_status_added_by() {
		return lega_status_added_by;
	}
	public void setLega_status_added_by(int lega_status_added_by) {
		this.lega_status_added_by = lega_status_added_by;
	}
	
	
}
