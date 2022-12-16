package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity(name="trn_sfc_documents")
public class Sfco_documents {
	
		@Id
		@GeneratedValue
		private int sfco_doc_id;
		private String sfco_doc_original_file_name;
		private String sfco_doc_generated_file_name;
		private int sfco_sfco_id;
		private int sfco_doc_last_generated_value_for_sfco_id;
		private Date sfco_doc_created_date;
		private int sfco_doc_added_by;
		public int getSfco_doc_id() {
			return sfco_doc_id;
		}
		public void setSfco_doc_id(int sfco_doc_id) {
			this.sfco_doc_id = sfco_doc_id;
		}
		public String getSfco_doc_original_file_name() {
			return sfco_doc_original_file_name;
		}
		public void setSfco_doc_original_file_name(String sfco_doc_original_file_name) {
			this.sfco_doc_original_file_name = sfco_doc_original_file_name;
		}
		public String getSfco_doc_generated_file_name() {
			return sfco_doc_generated_file_name;
		}
		public void setSfco_doc_generated_file_name(String sfco_doc_generated_file_name) {
			this.sfco_doc_generated_file_name = sfco_doc_generated_file_name;
		}
		public int getSfco_sfco_id() {
			return sfco_sfco_id;
		}
		public void setSfco_sfco_id(int sfco_sfco_id) {
			this.sfco_sfco_id = sfco_sfco_id;
		}
		public int getSfco_doc_last_generated_value_for_sfco_id() {
			return sfco_doc_last_generated_value_for_sfco_id;
		}
		public void setSfco_doc_last_generated_value_for_sfco_id(int sfco_doc_last_generated_value_for_sfco_id) {
			this.sfco_doc_last_generated_value_for_sfco_id = sfco_doc_last_generated_value_for_sfco_id;
		}
		public Date getSfco_doc_created_date() {
			return sfco_doc_created_date;
		}
		public void setSfco_doc_created_date(Date sfco_doc_created_date) {
			this.sfco_doc_created_date = sfco_doc_created_date;
		}
		public int getSfco_doc_added_by() {
			return sfco_doc_added_by;
		}
		public void setSfco_doc_added_by(int sfco_doc_added_by) {
			this.sfco_doc_added_by = sfco_doc_added_by;
		}
		

}
