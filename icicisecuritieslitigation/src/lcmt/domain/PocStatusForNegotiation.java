package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity(name="trn_poc_status_negotiation")
public class PocStatusForNegotiation {

	@Id
	@GeneratedValue
	private int poc_status_id;
	private String poc_action_item;
	private String poc_status;
	private Date poc_status_date;
	private Date poc_status_created_at;
	private int poc_status_added_by;
	private int poc_contract_req_id;	// contract request id
	private int poc_contract_id;		// contract id
	
	public int getPoc_contract_id() {
		return poc_contract_id;
	}
	public void setPoc_contract_id(int poc_contract_id) {
		this.poc_contract_id = poc_contract_id;
	}
	public int getPoc_contract_req_id() {
		return poc_contract_req_id;
	}
	public void setPoc_contract_req_id(int poc_contract_req_id) {
		this.poc_contract_req_id = poc_contract_req_id;
	}
	public int getPoc_status_id() {
		return poc_status_id;
	}
	public void setPoc_status_id(int poc_status_id) {
		this.poc_status_id = poc_status_id;
	}
	public String getPoc_action_item() {
		return poc_action_item;
	}
	public void setPoc_action_item(String poc_action_item) {
		this.poc_action_item = poc_action_item;
	}
	public String getPoc_status() {
		return poc_status;
	}
	public void setPoc_status(String poc_status) {
		this.poc_status = poc_status;
	}
	public Date getPoc_status_date() {
		return poc_status_date;
	}
	public void setPoc_status_date(Date poc_status_date) {
		this.poc_status_date = poc_status_date;
	}
	public Date getPoc_status_created_at() {
		return poc_status_created_at;
	}
	public void setPoc_status_created_at(Date poc_status_created_at) {
		this.poc_status_created_at = poc_status_created_at;
	}
	public int getPoc_status_added_by() {
		return poc_status_added_by;
	}
	public void setPoc_status_added_by(int poc_status_added_by) {
		this.poc_status_added_by = poc_status_added_by;
	}
	
	
	
	
}
