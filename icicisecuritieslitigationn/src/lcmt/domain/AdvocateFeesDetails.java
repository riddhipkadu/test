package lcmt.domain;

import java.util.List;

import javax.validation.constraints.Size;

public class AdvocateFeesDetails {
	private int ladv_id;
	private int ladv_advocate_id;
	private String ladv_advocate_name;
	private int ladv_litigation_id;
	private String ladv_type_of_fees;
	private String ladv_effective_non_effective;
	private String ladv_advocate_fees_amount;
	@Size(max = 3900)
	private String ladv_comments;
	
	private int apaid_id;
	private String apaid_invoice_no;
	private double apaid_invoice_amt;
	private String apaid_invoice_date;
	private double apaid_fees_amount;
	private String apaid_currency_code;
	private String apaid_invoice_amt_currency;
	private int ladv_added_by;
	private List<LitigationFeesDocuments> fees_doc;
	
	public int getLadv_added_by() {
		return ladv_added_by;
	}
	public void setLadv_added_by(int ladv_added_by) {
		this.ladv_added_by = ladv_added_by;
	}
	public List<LitigationFeesDocuments> getFees_doc() {
		return fees_doc;
	}
	public void setFees_doc(List<LitigationFeesDocuments> fees_doc) {
		this.fees_doc = fees_doc;
	}
	public int getLadv_id() {
		return ladv_id;
	}
	public void setLadv_id(int ladv_id) {
		this.ladv_id = ladv_id;
	}
	public int getLadv_advocate_id() {
		return ladv_advocate_id;
	}
	public void setLadv_advocate_id(int ladv_advocate_id) {
		this.ladv_advocate_id = ladv_advocate_id;
	}
	public String getLadv_advocate_name() {
		return ladv_advocate_name;
	}
	public void setLadv_advocate_name(String ladv_advocate_name) {
		this.ladv_advocate_name = ladv_advocate_name;
	}
	public int getLadv_litigation_id() {
		return ladv_litigation_id;
	}
	public void setLadv_litigation_id(int ladv_litigation_id) {
		this.ladv_litigation_id = ladv_litigation_id;
	}
	public String getLadv_type_of_fees() {
		return ladv_type_of_fees;
	}
	public void setLadv_type_of_fees(String ladv_type_of_fees) {
		this.ladv_type_of_fees = ladv_type_of_fees;
	}
	public String getLadv_effective_non_effective() {
		return ladv_effective_non_effective;
	}
	public void setLadv_effective_non_effective(String ladv_effective_non_effective) {
		this.ladv_effective_non_effective = ladv_effective_non_effective;
	}
	public String getLadv_advocate_fees_amount() {
		return ladv_advocate_fees_amount;
	}
	public void setLadv_advocate_fees_amount(String ladv_advocate_fees_amount) {
		this.ladv_advocate_fees_amount = ladv_advocate_fees_amount;
	}
	public String getLadv_comments() {
		return ladv_comments;
	}
	public void setLadv_comments(String ladv_comments) {
		this.ladv_comments = ladv_comments;
	}
	public int getApaid_id() {
		return apaid_id;
	}
	public void setApaid_id(int apaid_id) {
		this.apaid_id = apaid_id;
	}
	public String getApaid_invoice_no() {
		return apaid_invoice_no;
	}
	public void setApaid_invoice_no(String apaid_invoice_no) {
		this.apaid_invoice_no = apaid_invoice_no;
	}
	public double getApaid_invoice_amt() {
		return apaid_invoice_amt;
	}
	public void setApaid_invoice_amt(double apaid_invoice_amt) {
		this.apaid_invoice_amt = apaid_invoice_amt;
	}
	public String getApaid_invoice_date() {
		return apaid_invoice_date;
	}
	public void setApaid_invoice_date(String apaid_invoice_date) {
		this.apaid_invoice_date = apaid_invoice_date;
	}
	public double getApaid_fees_amount() {
		return apaid_fees_amount;
	}
	public void setApaid_fees_amount(double apaid_fees_amount) {
		this.apaid_fees_amount = apaid_fees_amount;
	}
	public String getApaid_currency_code() {
		return apaid_currency_code;
	}
	public void setApaid_currency_code(String apaid_currency_code) {
		this.apaid_currency_code = apaid_currency_code;
	}
	public String getApaid_invoice_amt_currency() {
		return apaid_invoice_amt_currency;
	}
	public void setApaid_invoice_amt_currency(String apaid_invoice_amt_currency) {
		this.apaid_invoice_amt_currency = apaid_invoice_amt_currency;
	}
	
}
