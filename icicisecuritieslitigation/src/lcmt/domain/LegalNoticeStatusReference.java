package lcmt.domain;

import java.util.List;

import javax.validation.constraints.Size;

public class LegalNoticeStatusReference {

	private int lega_status_id;
	private int lega_notice_id;
	private String lega_action_item_due_date;
	private String lega_reminder_date;
	private String lega_person_responsible;
	private String lega_notice_status;
	private String lega_created_at;
	private int lega_status_added_by;
	private List<LegalNoticeDocuments> lega_documents;
	private String lega_log_status;
	private String lega_received_date;
	private String lega_person_responsible_others;
	
	@Size(max = 10000)
	private String lega_action_taken;
	private String lega_next_action_item;
	
	
	public String getLega_received_date() {
		return lega_received_date;
	}
	public void setLega_received_date(String lega_received_date) {
		this.lega_received_date = lega_received_date;
	}
	public String getLega_person_responsible_others() {
		return lega_person_responsible_others;
	}
	public void setLega_person_responsible_others(String lega_person_responsible_others) {
		this.lega_person_responsible_others = lega_person_responsible_others;
	}
	public String getLega_log_status() {
		return lega_log_status;
	}
	public void setLega_log_status(String lega_log_status) {
		this.lega_log_status = lega_log_status;
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

	public String getLega_person_responsible() {
		return lega_person_responsible;
	}
	public void setLega_person_responsible(String lega_person_responsible) {
		this.lega_person_responsible = lega_person_responsible;
	}
	public String getLega_notice_status() {
		return lega_notice_status;
	}
	public void setLega_notice_status(String lega_notice_status) {
		this.lega_notice_status = lega_notice_status;
	}

	public int getLega_status_added_by() {
		return lega_status_added_by;
	}
	public void setLega_status_added_by(int lega_status_added_by) {
		this.lega_status_added_by = lega_status_added_by;
	}
	public String getLega_action_item_due_date() {
		return lega_action_item_due_date;
	}
	public void setLega_action_item_due_date(String lega_action_item_due_date) {
		this.lega_action_item_due_date = lega_action_item_due_date;
	}
	public String getLega_reminder_date() {
		return lega_reminder_date;
	}
	public void setLega_reminder_date(String lega_reminder_date) {
		this.lega_reminder_date = lega_reminder_date;
	}
	public String getLega_created_at() {
		return lega_created_at;
	}
	public void setLega_created_at(String lega_created_at) {
		this.lega_created_at = lega_created_at;
	}
	public List<LegalNoticeDocuments> getLega_documents() {
		return lega_documents;
	}
	public void setLega_documents(List<LegalNoticeDocuments> lega_documents) {
		this.lega_documents = lega_documents;
	}
	
	
}
