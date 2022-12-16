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
@Entity (name="mst_contract_type")
public class ContractType {
	@Id
	@GeneratedValue
	private int cont_type_id;
	private String cont_type_name;
	private int cont_type_added_by;
	private Date cont_type_created_at;
	private Date cont_type_updated_at;

	public int getCont_type_id() {
		return cont_type_id;
	}
	public String getCont_type_name() {
		return cont_type_name;
	}
	public int getCont_type_added_by() {
		return cont_type_added_by;
	}
	public Date getCont_type_created_at() {
		return cont_type_created_at;
	}
	public Date getCont_type_updated_at() {
		return cont_type_updated_at;
	}
	public void setCont_type_id(int cont_type_id) {
		this.cont_type_id = cont_type_id;
	}
	public void setCont_type_name(String cont_type_name) {
		this.cont_type_name = cont_type_name;
	}
	public void setCont_type_added_by(int cont_type_added_by) {
		this.cont_type_added_by = cont_type_added_by;
	}
	public void setCont_type_created_at(Date cont_type_created_at) {
		this.cont_type_created_at = cont_type_created_at;
	}
	public void setCont_type_updated_at(Date cont_type_updated_at) {
		this.cont_type_updated_at = cont_type_updated_at;
	}

}
