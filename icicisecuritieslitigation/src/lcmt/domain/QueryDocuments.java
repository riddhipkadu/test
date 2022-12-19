package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="trn_query_documents")
public class QueryDocuments {

	@Id
	@GeneratedValue
	private int quer_doc_id;
	private int quer_hst_doc_id;
	private int quer_reply_doc_related_value_for_query_id;
	private String quer_doc_original_file_name;
	private String quer_doc_generated_file_name;
	private int quer_query_id;
	private int quer_doc_last_generated_value_for_query_id;
	private Date quer_doc_created_date;
	private int quer_doc_added_by;
	public int getQuer_doc_id() {
		return quer_doc_id;
	}
	public String getQuer_doc_original_file_name() {
		return quer_doc_original_file_name;
	}
	public String getQuer_doc_generated_file_name() {
		return quer_doc_generated_file_name;
	}
	public int getQuer_query_id() {
		return quer_query_id;
	}
	public int getQuer_doc_last_generated_value_for_query_id() {
		return quer_doc_last_generated_value_for_query_id;
	}
	public Date getQuer_doc_created_date() {
		return quer_doc_created_date;
	}
	public int getQuer_doc_added_by() {
		return quer_doc_added_by;
	}
	public void setQuer_doc_id(int quer_doc_id) {
		this.quer_doc_id = quer_doc_id;
	}
	public void setQuer_doc_original_file_name(String quer_doc_original_file_name) {
		this.quer_doc_original_file_name = quer_doc_original_file_name;
	}
	public void setQuer_doc_generated_file_name(String quer_doc_generated_file_name) {
		this.quer_doc_generated_file_name = quer_doc_generated_file_name;
	}
	public void setQuer_query_id(int quer_query_id) {
		this.quer_query_id = quer_query_id;
	}
	public void setQuer_doc_last_generated_value_for_query_id(int quer_doc_last_generated_value_for_query_id) {
		this.quer_doc_last_generated_value_for_query_id = quer_doc_last_generated_value_for_query_id;
	}
	public void setQuer_doc_created_date(Date quer_doc_created_date) {
		this.quer_doc_created_date = quer_doc_created_date;
	}
	public void setQuer_doc_added_by(int quer_doc_added_by) {
		this.quer_doc_added_by = quer_doc_added_by;
	}
	public int getQuer_hst_doc_id() {
		return quer_hst_doc_id;
	}
	public void setQuer_hst_doc_id(int quer_hst_doc_id) {
		this.quer_hst_doc_id = quer_hst_doc_id;
	}
	public int getQuer_reply_doc_related_value_for_query_id() {
		return quer_reply_doc_related_value_for_query_id;
	}
	public void setQuer_reply_doc_related_value_for_query_id(int quer_reply_doc_related_value_for_query_id) {
		this.quer_reply_doc_related_value_for_query_id = quer_reply_doc_related_value_for_query_id;
	}
	
}
