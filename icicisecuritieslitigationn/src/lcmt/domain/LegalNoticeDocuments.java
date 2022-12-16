package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity(name="trn_legal_notice_documents")
public class LegalNoticeDocuments {
	@Id
	@GeneratedValue
	private int lega_doc_id;
	private String lega_doc_original_file_name;
	private String lega_doc_generated_file_name;
	private int lega_notice_id;// Legal notice id and legal status id stored in same column
	private int lega_doc_last_generated_value_for_notice_id;
	private Date lega_doc_created_date;
	private int lega_doc_added_by;
	private int lega_doc_related_to; // value 1 for legal doc and value 2 for status doc of legal notice
	
	public int getLega_doc_related_to() {
		return lega_doc_related_to;
	}
	public void setLega_doc_related_to(int lega_doc_related_to) {
		this.lega_doc_related_to = lega_doc_related_to;
	}
	public int getLega_doc_id() {
		return lega_doc_id;
	}
	public String getLega_doc_original_file_name() {
		return lega_doc_original_file_name;
	}
	public String getLega_doc_generated_file_name() {
		return lega_doc_generated_file_name;
	}
	public int getLega_notice_id() {
		return lega_notice_id;
	}
	public int getLega_doc_last_generated_value_for_notice_id() {
		return lega_doc_last_generated_value_for_notice_id;
	}
	public Date getLega_doc_created_date() {
		return lega_doc_created_date;
	}
	public int getLega_doc_added_by() {
		return lega_doc_added_by;
	}
	public void setLega_doc_id(int lega_doc_id) {
		this.lega_doc_id = lega_doc_id;
	}
	public void setLega_doc_original_file_name(String lega_doc_original_file_name) {
		this.lega_doc_original_file_name = lega_doc_original_file_name;
	}
	public void setLega_doc_generated_file_name(String lega_doc_generated_file_name) {
		this.lega_doc_generated_file_name = lega_doc_generated_file_name;
	}
	public void setLega_notice_id(int lega_notice_id) {
		this.lega_notice_id = lega_notice_id;
	}
	public void setLega_doc_last_generated_value_for_notice_id(int lega_doc_last_generated_value_for_notice_id) {
		this.lega_doc_last_generated_value_for_notice_id = lega_doc_last_generated_value_for_notice_id;
	}
	public void setLega_doc_created_date(Date lega_doc_created_date) {
		this.lega_doc_created_date = lega_doc_created_date;
	}
	public void setLega_doc_added_by(int lega_doc_added_by) {
		this.lega_doc_added_by = lega_doc_added_by;
	}
	
	
}
