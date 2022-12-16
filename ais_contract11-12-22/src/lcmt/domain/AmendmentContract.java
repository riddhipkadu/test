package lcmt.domain;

/*
 * Author: Pranit Hanamghar
 * Date: 21/3/2018
 * Updated By: 
 * Updated Date: 
 * 
 * */

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;




@Entity(name="mst_amendment_contract")
public class AmendmentContract {
	

	@Id
	@GeneratedValue
	private int amend_contract_id;//primary key
	private Date amend_contract_start_date;
	private Date amend_contract_end_date;
	private Date amend_contract_created_at;
	private int amend_contract_addedby;
   // private String amend_contract_parties;
	@Size(max = 3900)
	private String amend_contract_term;
	private int amend_responsible_person;
	private int amend_exec_contract_id;
	@Size(max = 3900)
	private String amend_contract_clauses;
	private String amend_contract_status;
	
	
	
	
	public int getAmend_contract_id() {
		return amend_contract_id;
	}
	public void setAmend_contract_id(int amend_contract_id) {
		this.amend_contract_id = amend_contract_id;
	}
	public Date getAmend_contract_start_date() {
		return amend_contract_start_date;
	}
	public void setAmend_contract_start_date(Date amend_contract_start_date) {
		this.amend_contract_start_date = amend_contract_start_date;
	}
	public Date getAmend_contract_end_date() {
		return amend_contract_end_date;
	}
	public void setAmend_contract_end_date(Date amend_contract_end_date) {
		this.amend_contract_end_date = amend_contract_end_date;
	}
	public Date getAmend_contract_created_at() {
		return amend_contract_created_at;
	}
	public void setAmend_contract_created_at(Date amend_contract_created_at) {
		this.amend_contract_created_at = amend_contract_created_at;
	}
	public int getAmend_contract_addedby() {
		return amend_contract_addedby;
	}
	public void setAmend_contract_addedby(int amend_contract_addedby) {
		this.amend_contract_addedby = amend_contract_addedby;
	}
	public String getAmend_contract_term() {
		return amend_contract_term;
	}
	public void setAmend_contract_term(String amend_contract_term) {
		this.amend_contract_term = amend_contract_term;
	}
	public int getAmend_responsible_person() {
		return amend_responsible_person;
	}
	public void setAmend_responsible_person(int amend_responsible_person) {
		this.amend_responsible_person = amend_responsible_person;
	}
	public int getAmend_exec_contract_id() {
		return amend_exec_contract_id;
	}
	public void setAmend_exec_contract_id(int amend_exec_contract_id) {
		this.amend_exec_contract_id = amend_exec_contract_id;
	}
	public String getAmend_contract_clauses() {
		return amend_contract_clauses;
	}
	public void setAmend_contract_clauses(String amend_contract_clauses) {
		this.amend_contract_clauses = amend_contract_clauses;
	}
	public String getAmend_contract_status() {
		return amend_contract_status;
	}
	public void setAmend_contract_status(String amend_contract_status) {
		this.amend_contract_status = amend_contract_status;
	}
	/*public String getAmend_contract_parties() {
		return amend_contract_parties;
	}
	public void setAmend_contract_parties(String amend_contract_parties) {
		this.amend_contract_parties = amend_contract_parties;
	}
	*/
	
	
	
	

}
