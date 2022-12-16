/*
 * Method created by : Tejashri Zurunge
 * Created at		 : 04/10/2016
 * Updated by		 :
 * 
 * */

package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="mst_currency")
public class Currency {

@Id
@GeneratedValue
private int curr_id;
private String curr_name;
private String curr_code;
private String curr_rate;
private int curr_added_by;
private Date curr_created_at;
private Date curr_updated_at;
public int getCurr_id() {
	return curr_id;
}
public void setCurr_id(int curr_id) {
	this.curr_id = curr_id;
}
public String getCurr_name() {
	return curr_name;
}
public void setCurr_name(String curr_name) {
	this.curr_name = curr_name;
}
public String getCurr_code() {
	return curr_code;
}
public void setCurr_code(String curr_code) {
	this.curr_code = curr_code;
}
public String getCurr_rate() {
	return curr_rate;
}
public void setCurr_rate(String curr_rate) {
	this.curr_rate = curr_rate;
}
public int getCurr_added_by() {
	return curr_added_by;
}
public void setCurr_added_by(int curr_added_by) {
	this.curr_added_by = curr_added_by;
}
public Date getCurr_created_at() {
	return curr_created_at;
}
public void setCurr_created_at(Date curr_created_at) {
	this.curr_created_at = curr_created_at;
}
public Date getCurr_updated_at() {
	return curr_updated_at;
}
public void setCurr_updated_at(Date curr_updated_at) {
	this.curr_updated_at = curr_updated_at;
}


}
