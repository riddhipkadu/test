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

@Entity(name="mst_court")
public class Court {
@Id
@GeneratedValue

private int court_id;
private String court_name;
private int court_added_by;
private Date court_created_at;
private Date court_updated_at;
public int getCourt_id() {
	return court_id;
}
public void setCourt_id(int court_id) {
	this.court_id = court_id;
}
public String getCourt_name() {
	return court_name;
}
public void setCourt_name(String court_name) {
	this.court_name = court_name;
}
public int getCourt_added_by() {
	return court_added_by;
}
public void setCourt_added_by(int court_added_by) {
	this.court_added_by = court_added_by;
}
public Date getCourt_created_at() {
	return court_created_at;
}
public void setCourt_created_at(Date court_created_at) {
	this.court_created_at = court_created_at;
}
public Date getCourt_updated_at() {
	return court_updated_at;
}
public void setCourt_updated_at(Date court_updated_at) {
	this.court_updated_at = court_updated_at;
}


}
