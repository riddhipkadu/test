package lcmt.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

public class TermRenwContractRef {
	private int reftermren_contract_id;//primary key
	private Date reftermren_contract_start_date;
	private String reftermren_contract_start_date_name;
	private Date reftermren_contract_end_date;
	private String reftermren_contract_end_date_name;
	private Date reftermren_contract_created_at;
	private int reftermren_contract_addedby;
   
	@Size(max = 3900)
	private String reftermren_contract_term;
	private int reftermren_responsible_person;
	private String reftermren_responsible_person_name;
	private int reftermren_exec_contract_id;
	@Size(max = 3900)
	private String reftermren_contract_clauses;
	private String reftermren_contract_status;
	private List<ExecutedContractDocuments> reftermren_contract_doc;
	public int getReftermren_contract_id() {
		return reftermren_contract_id;
	}
	public void setReftermren_contract_id(int reftermren_contract_id) {
		this.reftermren_contract_id = reftermren_contract_id;
	}
	public Date getReftermren_contract_start_date() {
		return reftermren_contract_start_date;
	}
	public void setReftermren_contract_start_date(Date reftermren_contract_start_date) {
		this.reftermren_contract_start_date = reftermren_contract_start_date;
	}
	public String getReftermren_contract_start_date_name() {
		return reftermren_contract_start_date_name;
	}
	public void setReftermren_contract_start_date_name(String reftermren_contract_start_date_name) {
		this.reftermren_contract_start_date_name = reftermren_contract_start_date_name;
	}
	public Date getReftermren_contract_end_date() {
		return reftermren_contract_end_date;
	}
	public void setReftermren_contract_end_date(Date reftermren_contract_end_date) {
		this.reftermren_contract_end_date = reftermren_contract_end_date;
	}
	public String getReftermren_contract_end_date_name() {
		return reftermren_contract_end_date_name;
	}
	public void setReftermren_contract_end_date_name(String reftermren_contract_end_date_name) {
		this.reftermren_contract_end_date_name = reftermren_contract_end_date_name;
	}
	public Date getReftermren_contract_created_at() {
		return reftermren_contract_created_at;
	}
	public void setReftermren_contract_created_at(Date reftermren_contract_created_at) {
		this.reftermren_contract_created_at = reftermren_contract_created_at;
	}
	public int getReftermren_contract_addedby() {
		return reftermren_contract_addedby;
	}
	public void setReftermren_contract_addedby(int reftermren_contract_addedby) {
		this.reftermren_contract_addedby = reftermren_contract_addedby;
	}
	public String getReftermren_contract_term() {
		return reftermren_contract_term;
	}
	public void setReftermren_contract_term(String reftermren_contract_term) {
		this.reftermren_contract_term = reftermren_contract_term;
	}
	public int getReftermren_responsible_person() {
		return reftermren_responsible_person;
	}
	public void setReftermren_responsible_person(int reftermren_responsible_person) {
		this.reftermren_responsible_person = reftermren_responsible_person;
	}
	public String getReftermren_responsible_person_name() {
		return reftermren_responsible_person_name;
	}
	public void setReftermren_responsible_person_name(String reftermren_responsible_person_name) {
		this.reftermren_responsible_person_name = reftermren_responsible_person_name;
	}
	public int getReftermren_exec_contract_id() {
		return reftermren_exec_contract_id;
	}
	public void setReftermren_exec_contract_id(int reftermren_exec_contract_id) {
		this.reftermren_exec_contract_id = reftermren_exec_contract_id;
	}
	public String getReftermren_contract_clauses() {
		return reftermren_contract_clauses;
	}
	public void setReftermren_contract_clauses(String reftermren_contract_clauses) {
		this.reftermren_contract_clauses = reftermren_contract_clauses;
	}
	public String getReftermren_contract_status() {
		return reftermren_contract_status;
	}
	public void setReftermren_contract_status(String reftermren_contract_status) {
		this.reftermren_contract_status = reftermren_contract_status;
	}
	public List<ExecutedContractDocuments> getReftermren_contract_doc() {
		return reftermren_contract_doc;
	}
	public void setReftermren_contract_doc(List<ExecutedContractDocuments> reftermren_contract_doc) {
		this.reftermren_contract_doc = reftermren_contract_doc;
	}
	
	}
