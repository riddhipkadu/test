package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
@Entity(name="trn_hearing_stages_onHearing_status")
public class HearingStageOnHearing {

	@Id
	@GeneratedValue
	private int trn_status_id;
	private int trn_liti_id;
	private int trn_hearing_id;
	private int trn_newHear_id;
	private int trn_hearing_status;
	private Date trn_hearing_created_at;
	
	public int getTrn_newHear_id() {
		return trn_newHear_id;
	}
	public void setTrn_newHear_id(int trn_newHear_id) {
		this.trn_newHear_id = trn_newHear_id;
	}
	public int getTrn_status_id() {
		return trn_status_id;
	}
	public void setTrn_status_id(int trn_status_id) {
		this.trn_status_id = trn_status_id;
	}
	public int getTrn_liti_id() {
		return trn_liti_id;
	}
	public void setTrn_liti_id(int trn_liti_id) {
		this.trn_liti_id = trn_liti_id;
	}
	public int getTrn_hearing_id() {
		return trn_hearing_id;
	}
	public void setTrn_hearing_id(int trn_hearing_id) {
		this.trn_hearing_id = trn_hearing_id;
	}
	public int getTrn_hearing_status() {
		return trn_hearing_status;
	}
	public void setTrn_hearing_status(int trn_hearing_status) {
		this.trn_hearing_status = trn_hearing_status;
	}
	public Date getTrn_hearing_created_at() {
		return trn_hearing_created_at;
	}
	public void setTrn_hearing_created_at(Date trn_hearing_created_at) {
		this.trn_hearing_created_at = trn_hearing_created_at;
	}
	
	
	
	
}
