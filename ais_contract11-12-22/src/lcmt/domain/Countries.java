package lcmt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="mst_countries")
public class Countries {

	@Id
	@GeneratedValue
	private int coun_id;
	private String coun_sortname;
	private String coun_name;
	public int getCoun_id() {
		return coun_id;
	}
	public void setCoun_id(int coun_id) {
		this.coun_id = coun_id;
	}
	public String getCoun_sortname() {
		return coun_sortname;
	}
	public void setCoun_sortname(String coun_sortname) {
		this.coun_sortname = coun_sortname;
	}
	public String getCoun_name() {
		return coun_name;
	}
	public void setCoun_name(String coun_name) {
		this.coun_name = coun_name;
	}
	
}
