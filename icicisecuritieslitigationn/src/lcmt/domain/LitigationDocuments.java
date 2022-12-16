package lcmt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "trn_litigation_documents")
public class LitigationDocuments {

	@Id
	@GeneratedValue
	private int ldoc_id;
	private String ldoc_original_file_name;
	private String ldoc_generated_file_name;
	private int ldoc_liti_id;
	private int ldoc_last_generated_value_for_liti_id;
	private String ldoc_document_type;//For showing whether document is litigation related or reference document
	
	
	public int getLdoc_id() {
		return ldoc_id;
	}
	public void setLdoc_id(int ldoc_id) {
		this.ldoc_id = ldoc_id;
	}
	public String getLdoc_original_file_name() {
		return ldoc_original_file_name;
	}
	public void setLdoc_original_file_name(String ldoc_original_file_name) {
		this.ldoc_original_file_name = ldoc_original_file_name;
	}
	public String getLdoc_generated_file_name() {
		return ldoc_generated_file_name;
	}
	public void setLdoc_generated_file_name(String ldoc_generated_file_name) {
		this.ldoc_generated_file_name = ldoc_generated_file_name;
	}
	
	public int getLdoc_liti_id() {
		return ldoc_liti_id;
	}
	public void setLdoc_liti_id(int ldoc_liti_id) {
		this.ldoc_liti_id = ldoc_liti_id;
	}
	public int getLdoc_last_generated_value_for_liti_id() {
		return ldoc_last_generated_value_for_liti_id;
	}
	public void setLdoc_last_generated_value_for_liti_id(int ldoc_last_generated_value_for_liti_id) {
		this.ldoc_last_generated_value_for_liti_id = ldoc_last_generated_value_for_liti_id;
	}
	public String getLdoc_document_type() {
		return ldoc_document_type;
	}
	public void setLdoc_document_type(String ldoc_document_type) {
		this.ldoc_document_type = ldoc_document_type;
	}
	
	
	
}
