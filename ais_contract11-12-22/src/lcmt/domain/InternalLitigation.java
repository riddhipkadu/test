package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name="mst_internal_litigation_code")
public class InternalLitigation {

	@Id
	@GeneratedValue
	private int internal_liti_id;
	private String internal_liti_code;
	@Size(max=2900)
	private String internal_liti_desc;
	private int internal_liti_added_by;
	private Date internal_liti_created_by;
	public int getInternal_liti_id() {
		return internal_liti_id;
	}
	public void setInternal_liti_id(int internal_liti_id) {
		this.internal_liti_id = internal_liti_id;
	}
	public String getInternal_liti_code() {
		return internal_liti_code;
	}
	public void setInternal_liti_code(String internal_liti_code) {
		this.internal_liti_code = internal_liti_code;
	}
	public String getInternal_liti_desc() {
		return internal_liti_desc;
	}
	public void setInternal_liti_desc(String internal_liti_desc) {
		this.internal_liti_desc = internal_liti_desc;
	}
	public int getInternal_liti_added_by() {
		return internal_liti_added_by;
	}
	public void setInternal_liti_added_by(int internal_liti_added_by) {
		this.internal_liti_added_by = internal_liti_added_by;
	}
	public Date getInternal_liti_created_by() {
		return internal_liti_created_by;
	}
	public void setInternal_liti_created_by(Date internal_liti_created_by) {
		this.internal_liti_created_by = internal_liti_created_by;
	}
	
	
}
