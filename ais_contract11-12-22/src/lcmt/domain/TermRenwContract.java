package lcmt.domain;


//Author: Pranit Hanamghar

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
@Entity(name="trn_termrenw_contract")

public class TermRenwContract {
	
	@Id
	@GeneratedValue
	private int termren_contract_id;//primary key
	private Date termren_contract_start_date;
	private Date termren_contract_end_date;
	private Date termren_contract_created_at;
	private int termren_contract_addedby;
   
	@Size(max = 3900)
	private String termren_contract_term;
	private int termren_responsible_person;
	private int termren_exec_contract_id;
	@Size(max = 3900)
	private String termren_contract_clauses;
	private String termren_contract_status;
	
	
	
	public int getTermren_contract_id() {
		return termren_contract_id;
	}
	public void setTermren_contract_id(int termren_contract_id) {
		this.termren_contract_id = termren_contract_id;
	}
	public Date getTermren_contract_start_date() {
		return termren_contract_start_date;
	}
	public void setTermren_contract_start_date(Date termren_contract_start_date) {
		this.termren_contract_start_date = termren_contract_start_date;
	}
	public Date getTermren_contract_end_date() {
		return termren_contract_end_date;
	}
	public void setTermren_contract_end_date(Date termren_contract_end_date) {
		this.termren_contract_end_date = termren_contract_end_date;
	}
	public Date getTermren_contract_created_at() {
		return termren_contract_created_at;
	}
	public void setTermren_contract_created_at(Date termren_contract_created_at) {
		this.termren_contract_created_at = termren_contract_created_at;
	}
	public int getTermren_contract_addedby() {
		return termren_contract_addedby;
	}
	public void setTermren_contract_addedby(int termren_contract_addedby) {
		this.termren_contract_addedby = termren_contract_addedby;
	}
	public String getTermren_contract_term() {
		return termren_contract_term;
	}
	public void setTermren_contract_term(String termren_contract_term) {
		this.termren_contract_term = termren_contract_term;
	}
	public int getTermren_responsible_person() {
		return termren_responsible_person;
	}
	public void setTermren_responsible_person(int termren_responsible_person) {
		this.termren_responsible_person = termren_responsible_person;
	}
	public int getTermren_exec_contract_id() {
		return termren_exec_contract_id;
	}
	public void setTermren_exec_contract_id(int termren_exec_contract_id) {
		this.termren_exec_contract_id = termren_exec_contract_id;
	}
	public String getTermren_contract_clauses() {
		return termren_contract_clauses;
	}
	public void setTermren_contract_clauses(String termren_contract_clauses) {
		this.termren_contract_clauses = termren_contract_clauses;
	}
	public String getTermren_contract_status() {
		return termren_contract_status;
	}
	public void setTermren_contract_status(String termren_contract_status) {
		this.termren_contract_status = termren_contract_status;
	}
	
	

}
