package lcmt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="mst_cities")
public class Cities {

	@Id
	@GeneratedValue
	private int city_id;
	private String city_name;
	private int city_state_id;
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public int getCity_state_id() {
		return city_state_id;
	}
	public void setCity_state_id(int city_state_id) {
		this.city_state_id = city_state_id;
	}
	
}
