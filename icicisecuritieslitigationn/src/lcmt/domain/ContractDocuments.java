package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="trn_contract_documents")
public class ContractDocuments {

	@Id
	@GeneratedValue
	private int cdoc_id;
	private String cdoc_original_file_name;
	private String cdoc_generated_file_name;
	private int cdoc_contract_id;
	private int cdoc_last_generated_value_for_contract_id;
	private String cdoc_document_type;//For showing whether document is term related or contract related
	private Date cdoc_created_at;
	private int cdoc_added_by;
	
	public int getCdoc_id() {
		return cdoc_id;
	}
	public void setCdoc_id(int cdoc_id) {
		this.cdoc_id = cdoc_id;
	}
	public String getCdoc_original_file_name() {
		return cdoc_original_file_name;
	}
	public void setCdoc_original_file_name(String cdoc_original_file_name) {
		this.cdoc_original_file_name = cdoc_original_file_name;
	}
	public String getCdoc_generated_file_name() {
		return cdoc_generated_file_name;
	}
	public void setCdoc_generated_file_name(String cdoc_generated_file_name) {
		this.cdoc_generated_file_name = cdoc_generated_file_name;
	}
	public int getCdoc_contract_id() {
		return cdoc_contract_id;
	}
	public void setCdoc_contract_id(int cdoc_contract_id) {
		this.cdoc_contract_id = cdoc_contract_id;
	}
	public int getCdoc_last_generated_value_for_contract_id() {
		return cdoc_last_generated_value_for_contract_id;
	}
	public void setCdoc_last_generated_value_for_contract_id(int cdoc_last_generated_value_for_contract_id) {
		this.cdoc_last_generated_value_for_contract_id = cdoc_last_generated_value_for_contract_id;
	}
	public String getCdoc_document_type() {
		return cdoc_document_type;
	}
	public void setCdoc_document_type(String cdoc_document_type) {
		this.cdoc_document_type = cdoc_document_type;
	}
	public Date getCdoc_created_at() {
		return cdoc_created_at;
	}
	public void setCdoc_created_at(Date cdoc_created_at) {
		this.cdoc_created_at = cdoc_created_at;
	}
	public int getCdoc_added_by() {
		return cdoc_added_by;
	}
	public void setCdoc_added_by(int cdoc_added_by) {
		this.cdoc_added_by = cdoc_added_by;
	}
	
}
