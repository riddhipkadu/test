package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity(name="trn_hearing_stage_counsel")
public class HearingStageCounsels {

	@Id
	@GeneratedValue
	private int hsco_id;
	private int hsco_hearing_stage_id;
	private int hsco_counsel_law_firm_id;
	private int hsco_counsel_id;
	private int hsco_added_by;
	private Date hsco_created_at;
	public int getHsco_id() {
		return hsco_id;
	}
	public void setHsco_id(int hsco_id) {
		this.hsco_id = hsco_id;
	}
	public int getHsco_hearing_stage_id() {
		return hsco_hearing_stage_id;
	}
	public void setHsco_hearing_stage_id(int hsco_hearing_stage_id) {
		this.hsco_hearing_stage_id = hsco_hearing_stage_id;
	}
	public int getHsco_counsel_law_firm_id() {
		return hsco_counsel_law_firm_id;
	}
	public void setHsco_counsel_law_firm_id(int hsco_counsel_law_firm_id) {
		this.hsco_counsel_law_firm_id = hsco_counsel_law_firm_id;
	}
	public int getHsco_counsel_id() {
		return hsco_counsel_id;
	}
	public void setHsco_counsel_id(int hsco_counsel_id) {
		this.hsco_counsel_id = hsco_counsel_id;
	}
	public int getHsco_added_by() {
		return hsco_added_by;
	}
	public void setHsco_added_by(int hsco_added_by) {
		this.hsco_added_by = hsco_added_by;
	}
	public Date getHsco_created_at() {
		return hsco_created_at;
	}
	public void setHsco_created_at(Date hsco_created_at) {
		this.hsco_created_at = hsco_created_at;
	}
	
}
