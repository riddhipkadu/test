package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name="trn_counsel_paid_fees")
public class CounselPaidFees {

	@Id
	@GeneratedValue
	private int cpaid_id;
	private int cpaid_counsel_fees_id;
	private int cpaid_liti_id;
	private int cpaid_counsel_id;
	private String cpaid_invoice_no;
	private double cpaid_invoice_amt;
	private String cpaid_invoice_amt_currency;
	private Date cpaid_invoice_date;
	@Size(max = 3900)
	private String cpaid_comments;
	private double cpaid_fees_amount;
	private String cpaid_currency_code;
	private double cpaid_conversion_rate;
	private double cpaid_converted_amt;
	private String cpaid_converted_currency;
	private Date cpaid_created_at;
	private int cpaid_added_by;
	
	public int getCpaid_id() {
		return cpaid_id;
	}
	public void setCpaid_id(int cpaid_id) {
		this.cpaid_id = cpaid_id;
	}
	public int getCpaid_counsel_fees_id() {
		return cpaid_counsel_fees_id;
	}
	public void setCpaid_counsel_fees_id(int cpaid_counsel_fees_id) {
		this.cpaid_counsel_fees_id = cpaid_counsel_fees_id;
	}
	public int getCpaid_liti_id() {
		return cpaid_liti_id;
	}
	public void setCpaid_liti_id(int cpaid_liti_id) {
		this.cpaid_liti_id = cpaid_liti_id;
	}
	public int getCpaid_counsel_id() {
		return cpaid_counsel_id;
	}
	public void setCpaid_counsel_id(int cpaid_counsel_id) {
		this.cpaid_counsel_id = cpaid_counsel_id;
	}
	public String getCpaid_invoice_no() {
		return cpaid_invoice_no;
	}
	public void setCpaid_invoice_no(String cpaid_invoice_no) {
		this.cpaid_invoice_no = cpaid_invoice_no;
	}
	public double getCpaid_invoice_amt() {
		return cpaid_invoice_amt;
	}
	public void setCpaid_invoice_amt(double cpaid_invoice_amt) {
		this.cpaid_invoice_amt = cpaid_invoice_amt;
	}
	public String getCpaid_invoice_amt_currency() {
		return cpaid_invoice_amt_currency;
	}
	public void setCpaid_invoice_amt_currency(String cpaid_invoice_amt_currency) {
		this.cpaid_invoice_amt_currency = cpaid_invoice_amt_currency;
	}
	public Date getCpaid_invoice_date() {
		return cpaid_invoice_date;
	}
	public void setCpaid_invoice_date(Date cpaid_invoice_date) {
		this.cpaid_invoice_date = cpaid_invoice_date;
	}
	public String getCpaid_comments() {
		return cpaid_comments;
	}
	public void setCpaid_comments(String cpaid_comments) {
		this.cpaid_comments = cpaid_comments;
	}
	public double getCpaid_fees_amount() {
		return cpaid_fees_amount;
	}
	public void setCpaid_fees_amount(double cpaid_fees_amount) {
		this.cpaid_fees_amount = cpaid_fees_amount;
	}
	public String getCpaid_currency_code() {
		return cpaid_currency_code;
	}
	public void setCpaid_currency_code(String cpaid_currency_code) {
		this.cpaid_currency_code = cpaid_currency_code;
	}
	public double getCpaid_conversion_rate() {
		return cpaid_conversion_rate;
	}
	public void setCpaid_conversion_rate(double cpaid_conversion_rate) {
		this.cpaid_conversion_rate = cpaid_conversion_rate;
	}
	public double getCpaid_converted_amt() {
		return cpaid_converted_amt;
	}
	public void setCpaid_converted_amt(double cpaid_converted_amt) {
		this.cpaid_converted_amt = cpaid_converted_amt;
	}
	public String getCpaid_converted_currency() {
		return cpaid_converted_currency;
	}
	public void setCpaid_converted_currency(String cpaid_converted_currency) {
		this.cpaid_converted_currency = cpaid_converted_currency;
	}
	public Date getCpaid_created_at() {
		return cpaid_created_at;
	}
	public void setCpaid_created_at(Date cpaid_created_at) {
		this.cpaid_created_at = cpaid_created_at;
	}
	public int getCpaid_added_by() {
		return cpaid_added_by;
	}
	public void setCpaid_added_by(int cpaid_added_by) {
		this.cpaid_added_by = cpaid_added_by;
	}
	
	
}
