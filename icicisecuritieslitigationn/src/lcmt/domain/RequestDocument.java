package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="trn_request_document")
public class RequestDocument {

	@Id
	@GeneratedValue
	private int req_doc_id;
	private String req_doc_original_file_name;
	private String req_doc_generated_file_name;
	private int req_id;
	private int req_doc_last_generated_value_for_req_id;
	private String req_doc_document_type;//For showing whether document is query related or contract related or litigation related
	private Date req_doc_created_at;
	private int req_doc_added_by;
	public int getReq_doc_id() {
		return req_doc_id;
	}
	public void setReq_doc_id(int req_doc_id) {
		this.req_doc_id = req_doc_id;
	}
	public String getReq_doc_original_file_name() {
		return req_doc_original_file_name;
	}
	public void setReq_doc_original_file_name(String req_doc_original_file_name) {
		this.req_doc_original_file_name = req_doc_original_file_name;
	}
	public String getReq_doc_generated_file_name() {
		return req_doc_generated_file_name;
	}
	public void setReq_doc_generated_file_name(String req_doc_generated_file_name) {
		this.req_doc_generated_file_name = req_doc_generated_file_name;
	}
	public int getReq_id() {
		return req_id;
	}
	public void setReq_id(int req_id) {
		this.req_id = req_id;
	}
	public int getReq_doc_last_generated_value_for_req_id() {
		return req_doc_last_generated_value_for_req_id;
	}
	public void setReq_doc_last_generated_value_for_req_id(int req_doc_last_generated_value_for_req_id) {
		this.req_doc_last_generated_value_for_req_id = req_doc_last_generated_value_for_req_id;
	}
	public String getReq_doc_document_type() {
		return req_doc_document_type;
	}
	public void setReq_doc_document_type(String req_doc_document_type) {
		this.req_doc_document_type = req_doc_document_type;
	}
	public Date getReq_doc_created_at() {
		return req_doc_created_at;
	}
	public void setReq_doc_created_at(Date req_doc_created_at) {
		this.req_doc_created_at = req_doc_created_at;
	}
	public int getReq_doc_added_by() {
		return req_doc_added_by;
	}
	public void setReq_doc_added_by(int req_doc_added_by) {
		this.req_doc_added_by = req_doc_added_by;
	}
	
	
	
}
