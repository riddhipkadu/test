package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="mst_legal_notice_category")
public class LegalCategory {

	@Id
	@GeneratedValue
	private int lega_noti_category_id;
	private String lega_noti_category_name;
	private int lega_noti_category_added_by;
	private Date lega_noti_category_created_at;
	private Date lega_noti_category_updated_at;
	
	public int getLega_noti_category_id() {
		return lega_noti_category_id;
	}
	public void setLega_noti_category_id(int lega_noti_category_id) {
		this.lega_noti_category_id = lega_noti_category_id;
	}
	public String getLega_noti_category_name() {
		return lega_noti_category_name;
	}
	public void setLega_noti_category_name(String lega_noti_category_name) {
		this.lega_noti_category_name = lega_noti_category_name;
	}
	public int getLega_noti_category_added_by() {
		return lega_noti_category_added_by;
	}
	public void setLega_noti_category_added_by(int lega_noti_category_added_by) {
		this.lega_noti_category_added_by = lega_noti_category_added_by;
	}
	public Date getLega_noti_category_created_at() {
		return lega_noti_category_created_at;
	}
	public void setLega_noti_category_created_at(Date lega_noti_category_created_at) {
		this.lega_noti_category_created_at = lega_noti_category_created_at;
	}
	public Date getLega_noti_category_updated_at() {
		return lega_noti_category_updated_at;
	}
	public void setLega_noti_category_updated_at(Date lega_noti_category_updated_at) {
		this.lega_noti_category_updated_at = lega_noti_category_updated_at;
	} 
	
	
}
