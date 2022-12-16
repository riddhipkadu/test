package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Entity(name="ttrn_action_item_transactional")
public class TransactionalActionItem {

	@Id
	@GeneratedValue
	private int atrn_id;
	private int atrn_action_id; //primary key of Execute action table
	private int atrn_contract_id;
	private Date atrn_action_due_date;
	private int atrn_resposible_user;
	private int atrn_first_alert_prior_days;
	private int atrn_second_alert_prior_days;
	private String atrn_frequency;
	private int atrn_completed_by;
	private Date atrn_completed_date;
	private String atrn_status;
	private Date atrn_created_date;
	@Size(max=2000)
	private String atrn_remark;
	
	public String getAtrn_remark() {
		return atrn_remark;
	}
	public void setAtrn_remark(String atrn_remark) {
		this.atrn_remark = atrn_remark;
	}
	public int getAtrn_id() {
		return atrn_id;
	}
	public void setAtrn_id(int atrn_id) {
		this.atrn_id = atrn_id;
	}
	public int getAtrn_action_id() {
		return atrn_action_id;
	}
	public void setAtrn_action_id(int atrn_action_id) {
		this.atrn_action_id = atrn_action_id;
	}
	public int getAtrn_contract_id() {
		return atrn_contract_id;
	}
	public void setAtrn_contract_id(int atrn_contract_id) {
		this.atrn_contract_id = atrn_contract_id;
	}
	public Date getAtrn_action_due_date() {
		return atrn_action_due_date;
	}
	public void setAtrn_action_due_date(Date atrn_action_due_date) {
		this.atrn_action_due_date = atrn_action_due_date;
	}
	public int getAtrn_resposible_user() {
		return atrn_resposible_user;
	}
	public void setAtrn_resposible_user(int atrn_resposible_user) {
		this.atrn_resposible_user = atrn_resposible_user;
	}
	public int getAtrn_first_alert_prior_days() {
		return atrn_first_alert_prior_days;
	}
	public void setAtrn_first_alert_prior_days(int atrn_first_alert_prior_days) {
		this.atrn_first_alert_prior_days = atrn_first_alert_prior_days;
	}
	public int getAtrn_second_alert_prior_days() {
		return atrn_second_alert_prior_days;
	}
	public void setAtrn_second_alert_prior_days(int atrn_second_alert_prior_days) {
		this.atrn_second_alert_prior_days = atrn_second_alert_prior_days;
	}
	public String getAtrn_frequency() {
		return atrn_frequency;
	}
	public void setAtrn_frequency(String atrn_frequency) {
		this.atrn_frequency = atrn_frequency;
	}
	public int getAtrn_completed_by() {
		return atrn_completed_by;
	}
	public void setAtrn_completed_by(int atrn_completed_by) {
		this.atrn_completed_by = atrn_completed_by;
	}
	public Date getAtrn_completed_date() {
		return atrn_completed_date;
	}
	public void setAtrn_completed_date(Date atrn_completed_date) {
		this.atrn_completed_date = atrn_completed_date;
	}
	public String getAtrn_status() {
		return atrn_status;
	}
	public void setAtrn_status(String atrn_status) {
		this.atrn_status = atrn_status;
	}
	public Date getAtrn_created_date() {
		return atrn_created_date;
	}
	public void setAtrn_created_date(Date atrn_created_date) {
		this.atrn_created_date = atrn_created_date;
	}
	
	
	
}
