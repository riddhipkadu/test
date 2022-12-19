/*
 * Author 		: Tejashri Zurunge
 * Created Date : 30/09/2016
 * Updated By 	: 
 * Updated Date : 
 *  
*/
package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
@Entity(name="mst_advocate")
public class Advocate {

@Id
@GeneratedValue
private int advo_id;
private String advo_name;
private int advo_country_id;
private int advo_state_id;
private int advo_city_id;
private int advo_law_firm;
private String advo_mobile_no;
private String advo_email_id;
@Size(max = 2500)
private String advo_address;
private int advo_area_of_expertise;
private int advo_added_by;
private Date advo_created_at;
private Date advo_updated_at;

public int getAdvo_id() {
	return advo_id;
}
public void setAdvo_id(int advo_id) {
	this.advo_id = advo_id;
}
public String getAdvo_name() {
	return advo_name;
}
public void setAdvo_name(String advo_name) {
	this.advo_name = advo_name;
}
public int getAdvo_country_id() {
	return advo_country_id;
}
public void setAdvo_country_id(int advo_country_id) {
	this.advo_country_id = advo_country_id;
}
public int getAdvo_state_id() {
	return advo_state_id;
}
public void setAdvo_state_id(int advo_state_id) {
	this.advo_state_id = advo_state_id;
}
public int getAdvo_city_id() {
	return advo_city_id;
}
public void setAdvo_city_id(int advo_city_id) {
	this.advo_city_id = advo_city_id;
}
public int getAdvo_law_firm() {
	return advo_law_firm;
}
public void setAdvo_law_firm(int advo_law_firm) {
	this.advo_law_firm = advo_law_firm;
}
public String getAdvo_mobile_no() {
	return advo_mobile_no;
}
public void setAdvo_mobile_no(String advo_mobile_no) {
	this.advo_mobile_no = advo_mobile_no;
}
public String getAdvo_email_id() {
	return advo_email_id;
}
public void setAdvo_email_id(String advo_email_id) {
	this.advo_email_id = advo_email_id;
}
public String getAdvo_address() {
	return advo_address;
}
public void setAdvo_address(String advo_address) {
	this.advo_address = advo_address;
}
public int getAdvo_area_of_expertise() {
	return advo_area_of_expertise;
}
public void setAdvo_area_of_expertise(int advo_area_of_expertise) {
	this.advo_area_of_expertise = advo_area_of_expertise;
}
public int getAdvo_added_by() {
	return advo_added_by;
}
public void setAdvo_added_by(int advo_added_by) {
	this.advo_added_by = advo_added_by;
}
public Date getAdvo_created_at() {
	return advo_created_at;
}
public void setAdvo_created_at(Date advo_created_at) {
	this.advo_created_at = advo_created_at;
}
public Date getAdvo_updated_at() {
	return advo_updated_at;
}
public void setAdvo_updated_at(Date advo_updated_at) {
	this.advo_updated_at = advo_updated_at;
}
	

}
