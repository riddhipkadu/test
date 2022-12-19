package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="mst_sarfaesiact")
public class SarfaesiAct {

	@Id
	@GeneratedValue
	private int sarf_act_id;
	private String sarf_loan_no;
	private String sarf_borrower;
	private String sarf_contact_no;
	private String sarf_email;
	private String sarf_security_type;
	private String sarf_security_Interest;
	private String sarf_security_loc;
	private String sarf_address;
	private Date sarf_npa_date;
	private Date sarf_filling_date;
	private int sarf_Executor_id;
    private Date sarf_first_reminder;
	private Date sarf_second_reminder;
	private Date sarf_NotiIssue_date;
	private int sarf_added_by;
	private Date sarf_created_at;
	private Date sarf_updated_at;
	private int sarf_orga_id;
	private int sarf_loca_id;
	private int sarf_dept_id;
	private int sarf_mgr_name;
	private int sarf_arc_name;
	private double sarf_total_amount;
	private double sarf_paid_amount;
	private double sarf_balance_amount;
	private double sarf_loan_repay_amount;
	
	public int getSarf_act_id() {
		return sarf_act_id;
	}
	public void setSarf_act_id(int sarf_act_id) {
		this.sarf_act_id = sarf_act_id;
	}
	public String getSarf_loan_no() {
		return sarf_loan_no;
	}
	public void setSarf_loan_no(String sarf_loan_no) {
		this.sarf_loan_no = sarf_loan_no;
	}
	public String getSarf_borrower() {
		return sarf_borrower;
	}
	public void setSarf_borrower(String sarf_borrower) {
		this.sarf_borrower = sarf_borrower;
	}
	public String getSarf_contact_no() {
		return sarf_contact_no;
	}
	public void setSarf_contact_no(String sarf_contact_no) {
		this.sarf_contact_no = sarf_contact_no;
	}
	public String getSarf_email() {
		return sarf_email;
	}
	public void setSarf_email(String sarf_email) {
		this.sarf_email = sarf_email;
	}
	public String getSarf_security_type() {
		return sarf_security_type;
	}
	public void setSarf_security_type(String sarf_security_type) {
		this.sarf_security_type = sarf_security_type;
	}
	public String getSarf_security_Interest() {
		return sarf_security_Interest;
	}
	public void setSarf_security_Interest(String sarf_security_Interest) {
		this.sarf_security_Interest = sarf_security_Interest;
	}
	public String getSarf_security_loc() {
		return sarf_security_loc;
	}
	public void setSarf_security_loc(String sarf_security_loc) {
		this.sarf_security_loc = sarf_security_loc;
	}
	public String getSarf_address() {
		return sarf_address;
	}
	public void setSarf_address(String sarf_address) {
		this.sarf_address = sarf_address;
	}
	public Date getSarf_npa_date() {
		return sarf_npa_date;
	}
	public void setSarf_npa_date(Date sarf_npa_date) {
		this.sarf_npa_date = sarf_npa_date;
	}
	public Date getSarf_filling_date() {
		return sarf_filling_date;
	}
	public void setSarf_filling_date(Date sarf_filling_date) {
		this.sarf_filling_date = sarf_filling_date;
	}
	public int getSarf_Executor_id() {
		return sarf_Executor_id;
	}
	public void setSarf_Executor_id(int sarf_Executor_id) {
		this.sarf_Executor_id = sarf_Executor_id;
	}
	public Date getSarf_first_reminder() {
		return sarf_first_reminder;
	}
	public void setSarf_first_reminder(Date sarf_first_reminder) {
		this.sarf_first_reminder = sarf_first_reminder;
	}
	public Date getSarf_second_reminder() {
		return sarf_second_reminder;
	}
	public void setSarf_second_reminder(Date sarf_second_reminder) {
		this.sarf_second_reminder = sarf_second_reminder;
	}
	public int getSarf_added_by() {
		return sarf_added_by;
	}
	public void setSarf_added_by(int sarf_added_by) {
		this.sarf_added_by = sarf_added_by;
	}
	public Date getSarf_created_at() {
		return sarf_created_at;
	}
	public void setSarf_created_at(Date sarf_created_at) {
		this.sarf_created_at = sarf_created_at;
	}
	public Date getSarf_updated_at() {
		return sarf_updated_at;
	}
	public void setSarf_updated_at(Date sarf_updated_at) {
		this.sarf_updated_at = sarf_updated_at;
	}
	public int getSarf_orga_id() {
		return sarf_orga_id;
	}
	public void setSarf_orga_id(int sarf_orga_id) {
		this.sarf_orga_id = sarf_orga_id;
	}
	public int getSarf_loca_id() {
		return sarf_loca_id;
	}
	public void setSarf_loca_id(int sarf_loca_id) {
		this.sarf_loca_id = sarf_loca_id;
	}
	public int getSarf_mgr_name() {
		return sarf_mgr_name;
	}
	public void setSarf_mgr_name(int sarf_mgr_name) {
		this.sarf_mgr_name = sarf_mgr_name;
	}
	public int getSarf_arc_name() {
		return sarf_arc_name;
	}
	public void setSarf_arc_name(int sarf_arc_name) {
		this.sarf_arc_name = sarf_arc_name;
	}
	public double getSarf_total_amount() {
		return sarf_total_amount;
	}
	public void setSarf_total_amount(double sarf_total_amount) {
		this.sarf_total_amount = sarf_total_amount;
	}
	public double getSarf_paid_amount() {
		return sarf_paid_amount;
	}
	public void setSarf_paid_amount(double sarf_paid_amount) {
		this.sarf_paid_amount = sarf_paid_amount;
	}
	public double getSarf_balance_amount() {
		return sarf_balance_amount;
	}
	public void setSarf_balance_amount(double sarf_balance_amount) {
		this.sarf_balance_amount = sarf_balance_amount;
	}
	
	public Date getSarf_NotiIssue_date() {
		return sarf_NotiIssue_date;
	}
	public void setSarf_NotiIssue_date(Date sarf_NotiIssue_date) {
		this.sarf_NotiIssue_date = sarf_NotiIssue_date;
	}
	public int getSarf_dept_id() {
		return sarf_dept_id;
	}
	public void setSarf_dept_id(int sarf_dept_id) {
		this.sarf_dept_id = sarf_dept_id;
	}
	public double getSarf_loan_repay_amount() {
		return sarf_loan_repay_amount;
	}
	public void setSarf_loan_repay_amount(double sarf_loan_repay_amount) {
		this.sarf_loan_repay_amount = sarf_loan_repay_amount;
	}
	
	
}
