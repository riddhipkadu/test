package lcmt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="mst_states")
public class States {
	
	@Id
	@GeneratedValue
	private int stat_id;
	private String stat_name;
	private int stat_country_id;
	public int getStat_id() {
		return stat_id;
	}
	public void setStat_id(int stat_id) {
		this.stat_id = stat_id;
	}
	public String getStat_name() {
		return stat_name;
	}
	public void setStat_name(String stat_name) {
		this.stat_name = stat_name;
	}
	public int getStat_country_id() {
		return stat_country_id;
	}
	public void setStat_country_id(int stat_country_id) {
		this.stat_country_id = stat_country_id;
	}
	

}
