package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name="ttrn_execute_action_item")
public class ExecuteActionItem {

	@Id
	@GeneratedValue
	private int exec_action_id;
	private int exec_contract_id;
	@Size(max= 5000)
	private String exec_action_item;
	private int exec_responsible_user_id;
	private String exec_frequency;
	private Date exec_due_date;
	private int exec_first_alert_prior_days;
	private int exec_second_alert_prior_days;
	private int exec_added_by;
	private Date exec_created_at;
	public int getExec_action_id() {
		return exec_action_id;
	}
	public void setExec_action_id(int exec_action_id) {
		this.exec_action_id = exec_action_id;
	}
	public int getExec_contract_id() {
		return exec_contract_id;
	}
	public void setExec_contract_id(int exec_contract_id) {
		this.exec_contract_id = exec_contract_id;
	}
	public String getExec_action_item() {
		return exec_action_item;
	}
	public void setExec_action_item(String exec_action_item) {
		this.exec_action_item = exec_action_item;
	}
	public int getExec_responsible_user_id() {
		return exec_responsible_user_id;
	}
	public void setExec_responsible_user_id(int exec_responsible_user_id) {
		this.exec_responsible_user_id = exec_responsible_user_id;
	}
	public String getExec_frequency() {
		return exec_frequency;
	}
	public void setExec_frequency(String exec_frequency) {
		this.exec_frequency = exec_frequency;
	}
	public Date getExec_due_date() {
		return exec_due_date;
	}
	public void setExec_due_date(Date exec_due_date) {
		this.exec_due_date = exec_due_date;
	}
	public int getExec_first_alert_prior_days() {
		return exec_first_alert_prior_days;
	}
	public void setExec_first_alert_prior_days(int exec_first_alert_prior_days) {
		this.exec_first_alert_prior_days = exec_first_alert_prior_days;
	}
	public int getExec_second_alert_prior_days() {
		return exec_second_alert_prior_days;
	}
	public void setExec_second_alert_prior_days(int exec_second_alert_prior_days) {
		this.exec_second_alert_prior_days = exec_second_alert_prior_days;
	}
	public int getExec_added_by() {
		return exec_added_by;
	}
	public void setExec_added_by(int exec_added_by) {
		this.exec_added_by = exec_added_by;
	}
	public Date getExec_created_at() {
		return exec_created_at;
	}
	public void setExec_created_at(Date exec_created_at) {
		this.exec_created_at = exec_created_at;
	}
	
	
}
