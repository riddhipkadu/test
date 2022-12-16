package lcmt.domain;

import java.util.List;

public class CounselFeesDetails {

	private int lcou_id;
	private int lcou_law_firm_id;
	private int lcou_counsel_id;
	private String lcou_counsel_name;
	private int lcou_liti_id;
	private String lcou_type_of_fees;
	private String lcou_effective_non_effective;
	private String lcou_counsel_fees_amount;
	private String lcou_comments;
	
	private int cpaid_id;
	private String cpaid_invoice_no;
	private double cpaid_invoice_amt;
	private String cpaid_invoice_date;
	private double cpaid_fees_amount;
	private String cpaid_currency_code;
	private String cpaid_invoice_amt_currency;
	private List<LitigationFeesDocuments> fees_doc;
	private int lcoun_fees_added_by;
	
	public List<LitigationFeesDocuments> getFees_doc() {
		return fees_doc;
	}
	public void setFees_doc(List<LitigationFeesDocuments> fees_doc) {
		this.fees_doc = fees_doc;
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
	public String getCpaid_invoice_date() {
		return cpaid_invoice_date;
	}
	public void setCpaid_invoice_date(String cpaid_invoice_date) {
		this.cpaid_invoice_date = cpaid_invoice_date;
	}
	public double getCpaid_fees_amount() {
		return cpaid_fees_amount;
	}
	public void setCpaid_fees_amount(double cpaid_fees_amount) {
		this.cpaid_fees_amount = cpaid_fees_amount;
	}
	
	public int getCpaid_id() {
		return cpaid_id;
	}
	public void setCpaid_id(int cpaid_id) {
		this.cpaid_id = cpaid_id;
	}
	public int getLcou_id() {
		return lcou_id;
	}
	public void setLcou_id(int lcou_id) {
		this.lcou_id = lcou_id;
	}
	public int getLcou_law_firm_id() {
		return lcou_law_firm_id;
	}
	public void setLcou_law_firm_id(int lcou_law_firm_id) {
		this.lcou_law_firm_id = lcou_law_firm_id;
	}
	public int getLcou_counsel_id() {
		return lcou_counsel_id;
	}
	public void setLcou_counsel_id(int lcou_counsel_id) {
		this.lcou_counsel_id = lcou_counsel_id;
	}
	
	public String getLcou_counsel_name() {
		return lcou_counsel_name;
	}
	public void setLcou_counsel_name(String lcou_counsel_name) {
		this.lcou_counsel_name = lcou_counsel_name;
	}
	public int getLcou_liti_id() {
		return lcou_liti_id;
	}
	public void setLcou_liti_id(int lcou_liti_id) {
		this.lcou_liti_id = lcou_liti_id;
	}
	public String getLcou_type_of_fees() {
		return lcou_type_of_fees;
	}
	public void setLcou_type_of_fees(String lcou_type_of_fees) {
		this.lcou_type_of_fees = lcou_type_of_fees;
	}
	public String getLcou_effective_non_effective() {
		return lcou_effective_non_effective;
	}
	public void setLcou_effective_non_effective(String lcou_effective_non_effective) {
		this.lcou_effective_non_effective = lcou_effective_non_effective;
	}
	public String getLcou_counsel_fees_amount() {
		return lcou_counsel_fees_amount;
	}
	public void setLcou_counsel_fees_amount(String lcou_counsel_fees_amount) {
		this.lcou_counsel_fees_amount = lcou_counsel_fees_amount;
	}
	public String getLcou_comments() {
		return lcou_comments;
	}
	public void setLcou_comments(String lcou_comments) {
		this.lcou_comments = lcou_comments;
	}
	public String getCpaid_currency_code() {
		return cpaid_currency_code;
	}
	public void setCpaid_currency_code(String cpaid_currency_code) {
		this.cpaid_currency_code = cpaid_currency_code;
	}
	public String getCpaid_invoice_amt_currency() {
		return cpaid_invoice_amt_currency;
	}
	public void setCpaid_invoice_amt_currency(String cpaid_invoice_amt_currency) {
		this.cpaid_invoice_amt_currency = cpaid_invoice_amt_currency;
	}
	public int getLcoun_fees_added_by() {
		return lcoun_fees_added_by;
	}
	public void setLcoun_fees_added_by(int lcoun_fees_added_by) {
		this.lcoun_fees_added_by = lcoun_fees_added_by;
	}
	
}
