package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name="trn_litigation_counsel_fees")
public class LitigationCounselFees {

	@Id
	@GeneratedValue
	private int lcou_id;
	private int lcou_law_firm_id;
	private int lcou_counsel_id;
	private int lcou_liti_id;
	private String lcou_type_of_fees;
	private String lcou_effective_non_effective;
	private double lcou_counsel_fees_amount;
	private String lcou_currency;
	@Size(max = 3900)
	private String lcou_comments;
	private Date lcou_created_at;
	private int lcou_counsel_added_by;
	private double lcou_conversion_rate;
	private double lcou_converted_amt;
	private String lcou_converted_currency;
	
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
	public double getLcou_counsel_fees_amount() {
		return lcou_counsel_fees_amount;
	}
	public void setLcou_counsel_fees_amount(double lcou_counsel_fees_amount) {
		this.lcou_counsel_fees_amount = lcou_counsel_fees_amount;
	}
	public String getLcou_currency() {
		return lcou_currency;
	}
	public void setLcou_currency(String lcou_currency) {
		this.lcou_currency = lcou_currency;
	}
	public String getLcou_comments() {
		return lcou_comments;
	}
	public void setLcou_comments(String lcou_comments) {
		this.lcou_comments = lcou_comments;
	}
	public Date getLcou_created_at() {
		return lcou_created_at;
	}
	public void setLcou_created_at(Date lcou_created_at) {
		this.lcou_created_at = lcou_created_at;
	}
	public int getLcou_counsel_added_by() {
		return lcou_counsel_added_by;
	}
	public void setLcou_counsel_added_by(int lcou_counsel_added_by) {
		this.lcou_counsel_added_by = lcou_counsel_added_by;
	}
	public double getLcou_conversion_rate() {
		return lcou_conversion_rate;
	}
	public void setLcou_conversion_rate(double lcou_conversion_rate) {
		this.lcou_conversion_rate = lcou_conversion_rate;
	}
	public double getLcou_converted_amt() {
		return lcou_converted_amt;
	}
	public void setLcou_converted_amt(double lcou_converted_amt) {
		this.lcou_converted_amt = lcou_converted_amt;
	}
	public String getLcou_converted_currency() {
		return lcou_converted_currency;
	}
	public void setLcou_converted_currency(String lcou_converted_currency) {
		this.lcou_converted_currency = lcou_converted_currency;
	}
	
	
}
