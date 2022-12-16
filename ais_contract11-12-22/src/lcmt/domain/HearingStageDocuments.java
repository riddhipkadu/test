package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name="trn_hearing_stage_documents")
public class HearingStageDocuments {

	@Id
	@GeneratedValue
	private int hear_doc_id;
	private int hear_stage_id;
	private String hear_stage_original_file_name;
	private String hear_stage_generated_file_name;
	private int hear_stage_last_generated_value_for_hear_stage_id;
	private int hear_stage_added_by;
	private Date hear_stage_created_at;
	public int getHear_doc_id() {
		return hear_doc_id;
	}
	public int getHear_stage_id() {
		return hear_stage_id;
	}
	public String getHear_stage_original_file_name() {
		return hear_stage_original_file_name;
	}
	public String getHear_stage_generated_file_name() {
		return hear_stage_generated_file_name;
	}
	public int getHear_stage_last_generated_value_for_hear_stage_id() {
		return hear_stage_last_generated_value_for_hear_stage_id;
	}
	public int getHear_stage_added_by() {
		return hear_stage_added_by;
	}
	public Date getHear_stage_created_at() {
		return hear_stage_created_at;
	}
	public void setHear_doc_id(int hear_doc_id) {
		this.hear_doc_id = hear_doc_id;
	}
	public void setHear_stage_id(int hear_stage_id) {
		this.hear_stage_id = hear_stage_id;
	}
	public void setHear_stage_original_file_name(String hear_stage_original_file_name) {
		this.hear_stage_original_file_name = hear_stage_original_file_name;
	}
	public void setHear_stage_generated_file_name(String hear_stage_generated_file_name) {
		this.hear_stage_generated_file_name = hear_stage_generated_file_name;
	}
	public void setHear_stage_last_generated_value_for_hear_stage_id(
			int hear_stage_last_generated_value_for_hear_stage_id) {
		this.hear_stage_last_generated_value_for_hear_stage_id = hear_stage_last_generated_value_for_hear_stage_id;
	}
	public void setHear_stage_added_by(int hear_stage_added_by) {
		this.hear_stage_added_by = hear_stage_added_by;
	}
	public void setHear_stage_created_at(Date hear_stage_created_at) {
		this.hear_stage_created_at = hear_stage_created_at;
	}
	
	
	
	
}
