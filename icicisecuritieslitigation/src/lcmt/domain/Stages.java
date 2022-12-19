package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/*
 * Author : Tejashri Zurunge
 * Created Date : 12/09/2016
 * Updated By : 
 * Updated Date : 
 *  
 * */

@Entity (name="mst_stages")
public class Stages {

	@Id
	@GeneratedValue
	private int stage_id;
	private String stage_name;
	private int stage_added_by;
	private Date stage_created_at;
	private Date stage_updates_at;
	
	public int getStage_id() {
		return stage_id;
	}
	public String getStage_name() {
		return stage_name;
	}
	public int getStage_added_by() {
		return stage_added_by;
	}
	public Date getStage_created_at() {
		return stage_created_at;
	}
	public Date getStage_updates_at() {
		return stage_updates_at;
	}
	public void setStage_id(int stage_id) {
		this.stage_id = stage_id;
	}
	public void setStage_name(String stage_name) {
		this.stage_name = stage_name;
	}
	public void setStage_added_by(int stage_added_by) {
		this.stage_added_by = stage_added_by;
	}
	public void setStage_created_at(Date stage_created_at) {
		this.stage_created_at = stage_created_at;
	}
	public void setStage_updates_at(Date stage_updates_at) {
		this.stage_updates_at = stage_updates_at;
	}

	
	
}
