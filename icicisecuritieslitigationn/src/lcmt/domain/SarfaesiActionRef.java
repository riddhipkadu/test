package lcmt.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

public class SarfaesiActionRef {
	
	private int sarf_action_id;
	private int action_sarf_act_id;
	private String sarf_action_status;
	@Size(max= 10000)
	private String sarf_action_item;
	@Size(max= 10000)
	private String sarf_nextaction_item;
	private Date sarf_action_NotiIssue_date;
	private String sarf_action_NotiIssue_dates;
	private Date sarf_action_due_date;
	private String sarf_action_due_dates;
	private Date action_first_remind_date;
	private String action_first_remind_dates;
	private Date action_second_remind_date;
	private String action_second_remind_dates;
	private int sarf_action_added_by;
	private Date sarf_action_created_at;
	private String sarf_actions_created_at;
	private Date sarf_action_updated_at;
	private String sarf_actions_updated_at;
	public int getSarf_action_id() {
		return sarf_action_id;
	}
	public void setSarf_action_id(int sarf_action_id) {
		this.sarf_action_id = sarf_action_id;
	}
	public int getAction_sarf_act_id() {
		return action_sarf_act_id;
	}
	public void setAction_sarf_act_id(int action_sarf_act_id) {
		this.action_sarf_act_id = action_sarf_act_id;
	}
	public String getSarf_action_status() {
		return sarf_action_status;
	}
	public void setSarf_action_status(String sarf_action_status) {
		this.sarf_action_status = sarf_action_status;
	}
	public String getSarf_action_item() {
		return sarf_action_item;
	}
	public void setSarf_action_item(String sarf_action_item) {
		this.sarf_action_item = sarf_action_item;
	}
	public String getSarf_nextaction_item() {
		return sarf_nextaction_item;
	}
	public void setSarf_nextaction_item(String sarf_nextaction_item) {
		this.sarf_nextaction_item = sarf_nextaction_item;
	}
	public Date getSarf_action_NotiIssue_date() {
		return sarf_action_NotiIssue_date;
	}
	public void setSarf_action_NotiIssue_date(Date sarf_action_NotiIssue_date) {
		this.sarf_action_NotiIssue_date = sarf_action_NotiIssue_date;
	}
	public String getSarf_action_NotiIssue_dates() {
		return sarf_action_NotiIssue_dates;
	}
	public void setSarf_action_NotiIssue_dates(String sarf_action_NotiIssue_dates) {
		this.sarf_action_NotiIssue_dates = sarf_action_NotiIssue_dates;
	}
	public Date getSarf_action_due_date() {
		return sarf_action_due_date;
	}
	public void setSarf_action_due_date(Date sarf_action_due_date) {
		this.sarf_action_due_date = sarf_action_due_date;
	}
	public String getSarf_action_due_dates() {
		return sarf_action_due_dates;
	}
	public void setSarf_action_due_dates(String sarf_action_due_dates) {
		this.sarf_action_due_dates = sarf_action_due_dates;
	}
	public Date getAction_first_remind_date() {
		return action_first_remind_date;
	}
	public void setAction_first_remind_date(Date action_first_remind_date) {
		this.action_first_remind_date = action_first_remind_date;
	}
	public String getAction_first_remind_dates() {
		return action_first_remind_dates;
	}
	public void setAction_first_remind_dates(String action_first_remind_dates) {
		this.action_first_remind_dates = action_first_remind_dates;
	}
	public Date getAction_second_remind_date() {
		return action_second_remind_date;
	}
	public void setAction_second_remind_date(Date action_second_remind_date) {
		this.action_second_remind_date = action_second_remind_date;
	}
	public String getAction_second_remind_dates() {
		return action_second_remind_dates;
	}
	public void setAction_second_remind_dates(String action_second_remind_dates) {
		this.action_second_remind_dates = action_second_remind_dates;
	}
	public int getSarf_action_added_by() {
		return sarf_action_added_by;
	}
	public void setSarf_action_added_by(int sarf_action_added_by) {
		this.sarf_action_added_by = sarf_action_added_by;
	}
	public Date getSarf_action_created_at() {
		return sarf_action_created_at;
	}
	public void setSarf_action_created_at(Date sarf_action_created_at) {
		this.sarf_action_created_at = sarf_action_created_at;
	}
	public String getSarf_actions_created_at() {
		return sarf_actions_created_at;
	}
	public void setSarf_actions_created_at(String sarf_actions_created_at) {
		this.sarf_actions_created_at = sarf_actions_created_at;
	}
	public Date getSarf_action_updated_at() {
		return sarf_action_updated_at;
	}
	public void setSarf_action_updated_at(Date sarf_action_updated_at) {
		this.sarf_action_updated_at = sarf_action_updated_at;
	}
	public String getSarf_actions_updated_at() {
		return sarf_actions_updated_at;
	}
	public void setSarf_actions_updated_at(String sarf_actions_updated_at) {
		this.sarf_actions_updated_at = sarf_actions_updated_at;
	}
	
	

}
