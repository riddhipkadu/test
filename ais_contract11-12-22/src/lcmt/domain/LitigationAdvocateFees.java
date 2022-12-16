package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name="trn_litigation_advocate_fees")
public class LitigationAdvocateFees {

	@Id
	@GeneratedValue
	private int ladv_id;
	private int ladv_advocate_id;
	private int ladv_litigation_id;
	private String ladv_type_of_fees;
	private String ladv_effective_non_effective;
	private double ladv_advocate_fees_amount;
	@Size(max = 3900)
	private String ladv_comments;
	private Date ladv_created_at;
	private String ladv_currency;
	private double ladv_conversion_rate;
	private double ladv_converted_amt;
	private String ladv_converted_currency;
	private int ladv_advocate_law_firm_id;
	private int ladv_added_by;
	
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
	public double getLadv_advocate_fees_amount() {
		return ladv_advocate_fees_amount;
	}
	public void setLadv_advocate_fees_amount(double ladv_advocate_fees_amount) {
		this.ladv_advocate_fees_amount = ladv_advocate_fees_amount;
	}
	public String getLadv_comments() {
		return ladv_comments;
	}
	public void setLadv_comments(String ladv_comments) {
		this.ladv_comments = ladv_comments;
	}
	public Date getLadv_created_at() {
		return ladv_created_at;
	}
	public void setLadv_created_at(Date ladv_created_at) {
		this.ladv_created_at = ladv_created_at;
	}
	public String getLadv_currency() {
		return ladv_currency;
	}
	public double getLadv_conversion_rate() {
		return ladv_conversion_rate;
	}
	public double getLadv_converted_amt() {
		return ladv_converted_amt;
	}
	public String getLadv_converted_currency() {
		return ladv_converted_currency;
	}
	public void setLadv_currency(String ladv_currency) {
		this.ladv_currency = ladv_currency;
	}
	public void setLadv_conversion_rate(double ladv_conversion_rate) {
		this.ladv_conversion_rate = ladv_conversion_rate;
	}
	public void setLadv_converted_amt(double ladv_converted_amt) {
		this.ladv_converted_amt = ladv_converted_amt;
	}
	public void setLadv_converted_currency(String ladv_converted_currency) {
		this.ladv_converted_currency = ladv_converted_currency;
	}
	public int getLadv_advocate_law_firm_id() {
		return ladv_advocate_law_firm_id;
	}
	public void setLadv_advocate_law_firm_id(int ladv_advocate_law_firm_id) {
		this.ladv_advocate_law_firm_id = ladv_advocate_law_firm_id;
	}
	public int getLadv_added_by() {
		return ladv_added_by;
	}
	public void setLadv_added_by(int ladv_added_by) {
		this.ladv_added_by = ladv_added_by;
	}
	
	
}
