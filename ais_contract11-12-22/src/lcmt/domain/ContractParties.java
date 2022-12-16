package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="trn_contract_parties")
public class ContractParties {

	@Id
	@GeneratedValue
	private int cont_party_id;
	private int cont_party_contract_id;
	private String cont_party_name;
	private int cont_party_type; //1 :pre execution contract, 2 :executed contracts ,3 :contract request by POC
	private int cont_party_series;
	private Date cont_party_created_at;
	private int cont_party_added_by;
	
	public int getCont_party_id() {
		return cont_party_id;
	}
	public void setCont_party_id(int cont_party_id) {
		this.cont_party_id = cont_party_id;
	}
	public int getCont_party_contract_id() {
		return cont_party_contract_id;
	}
	public void setCont_party_contract_id(int cont_party_contract_id) {
		this.cont_party_contract_id = cont_party_contract_id;
	}
	public String getCont_party_name() {
		return cont_party_name;
	}
	public void setCont_party_name(String cont_party_name) {
		this.cont_party_name = cont_party_name;
	}
	public int getCont_party_type() {
		return cont_party_type;
	}
	public void setCont_party_type(int cont_party_type) {
		this.cont_party_type = cont_party_type;
	}
	public int getCont_party_series() {
		return cont_party_series;
	}
	public void setCont_party_series(int cont_party_series) {
		this.cont_party_series = cont_party_series;
	}
	public Date getCont_party_created_at() {
		return cont_party_created_at;
	}
	public void setCont_party_created_at(Date cont_party_created_at) {
		this.cont_party_created_at = cont_party_created_at;
	}
	public int getCont_party_added_by() {
		return cont_party_added_by;
	}
	public void setCont_party_added_by(int cont_party_added_by) {
		this.cont_party_added_by = cont_party_added_by;
	}
	
	
	
}
