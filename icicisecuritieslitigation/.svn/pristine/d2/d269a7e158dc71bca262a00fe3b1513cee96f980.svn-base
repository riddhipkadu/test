package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="trn_poc_document")
public class PocNegotiationDocument {

	@Id
	@GeneratedValue
	private int poc_doc_id;
	private String poc_doc_original_file_name;
	private String poc_doc_generated_file_name;
	private int poc_doc_contract_id;// primary key of poc status table 
	private int poc_doc_last_generated_value_for_contract_id;
	private String poc_document_type;//For showing whether document is term related or contract related
	private Date poc_doc_created_at;
	private int poc_doc_added_by;
	public int getPoc_doc_id() {
		return poc_doc_id;
	}
	public void setPoc_doc_id(int poc_doc_id) {
		this.poc_doc_id = poc_doc_id;
	}
	public String getPoc_doc_original_file_name() {
		return poc_doc_original_file_name;
	}
	public void setPoc_doc_original_file_name(String poc_doc_original_file_name) {
		this.poc_doc_original_file_name = poc_doc_original_file_name;
	}
	public String getPoc_doc_generated_file_name() {
		return poc_doc_generated_file_name;
	}
	public void setPoc_doc_generated_file_name(String poc_doc_generated_file_name) {
		this.poc_doc_generated_file_name = poc_doc_generated_file_name;
	}
	public int getPoc_doc_contract_id() {
		return poc_doc_contract_id;
	}
	public void setPoc_doc_contract_id(int poc_doc_contract_id) {
		this.poc_doc_contract_id = poc_doc_contract_id;
	}
	public int getPoc_doc_last_generated_value_for_contract_id() {
		return poc_doc_last_generated_value_for_contract_id;
	}
	public void setPoc_doc_last_generated_value_for_contract_id(int poc_doc_last_generated_value_for_contract_id) {
		this.poc_doc_last_generated_value_for_contract_id = poc_doc_last_generated_value_for_contract_id;
	}
	public String getPoc_document_type() {
		return poc_document_type;
	}
	public void setPoc_document_type(String poc_document_type) {
		this.poc_document_type = poc_document_type;
	}
	public Date getPoc_doc_created_at() {
		return poc_doc_created_at;
	}
	public void setPoc_doc_created_at(Date poc_doc_created_at) {
		this.poc_doc_created_at = poc_doc_created_at;
	}
	public int getPoc_doc_added_by() {
		return poc_doc_added_by;
	}
	public void setPoc_doc_added_by(int poc_doc_added_by) {
		this.poc_doc_added_by = poc_doc_added_by;
	}
	
	
	
	
}
