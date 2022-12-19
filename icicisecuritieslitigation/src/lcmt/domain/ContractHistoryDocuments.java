package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="trn_contract_history_documents")
public class ContractHistoryDocuments {

	@Id
	@GeneratedValue
	private int chst_doc_id;
	private String chst_doc_original_file_name;
	private String chst_doc_generated_file_name;
	private int chst_doc_contract_id;
	private int chst_doc_contract_history_id;
	private int chst_doc_last_generated_value_for_contract_history_id;
	private String chst_document_type;//For showing document type
	private Date chst_created_at;
	private int chst_added_by;
	public int getChst_doc_id() {
		return chst_doc_id;
	}
	public void setChst_doc_id(int chst_doc_id) {
		this.chst_doc_id = chst_doc_id;
	}
	public String getChst_doc_original_file_name() {
		return chst_doc_original_file_name;
	}
	public void setChst_doc_original_file_name(String chst_doc_original_file_name) {
		this.chst_doc_original_file_name = chst_doc_original_file_name;
	}
	public String getChst_doc_generated_file_name() {
		return chst_doc_generated_file_name;
	}
	public void setChst_doc_generated_file_name(String chst_doc_generated_file_name) {
		this.chst_doc_generated_file_name = chst_doc_generated_file_name;
	}
	public int getChst_doc_contract_id() {
		return chst_doc_contract_id;
	}
	public void setChst_doc_contract_id(int chst_doc_contract_id) {
		this.chst_doc_contract_id = chst_doc_contract_id;
	}
	public int getChst_doc_contract_history_id() {
		return chst_doc_contract_history_id;
	}
	public void setChst_doc_contract_history_id(int chst_doc_contract_history_id) {
		this.chst_doc_contract_history_id = chst_doc_contract_history_id;
	}
	public int getChst_doc_last_generated_value_for_contract_history_id() {
		return chst_doc_last_generated_value_for_contract_history_id;
	}
	public void setChst_doc_last_generated_value_for_contract_history_id(
			int chst_doc_last_generated_value_for_contract_history_id) {
		this.chst_doc_last_generated_value_for_contract_history_id = chst_doc_last_generated_value_for_contract_history_id;
	}
	public String getChst_document_type() {
		return chst_document_type;
	}
	public void setChst_document_type(String chst_document_type) {
		this.chst_document_type = chst_document_type;
	}
	public Date getChst_created_at() {
		return chst_created_at;
	}
	public void setChst_created_at(Date chst_created_at) {
		this.chst_created_at = chst_created_at;
	}
	public int getChst_added_by() {
		return chst_added_by;
	}
	public void setChst_added_by(int chst_added_by) {
		this.chst_added_by = chst_added_by;
	}
	
	
}
