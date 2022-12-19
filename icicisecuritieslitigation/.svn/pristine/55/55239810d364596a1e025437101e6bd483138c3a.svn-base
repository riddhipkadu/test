package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="trn_contract_contract_type")
public class ContractContractType {

	@Id
	@GeneratedValue
	private int cont_id;
	private int cont_contract_id; // Contract id and contract history id saved in same column
	private String cont_contract_type_id;
	private int cont_contract_type; //1 for pre execution contract and 2 contract history and 3 for contract request
	private Date cont_contract_type_created_at;
	private int cont_contract_type_added_by;
	public int getCont_id() {
		return cont_id;
	}
	public void setCont_id(int cont_id) {
		this.cont_id = cont_id;
	}
	public int getCont_contract_id() {
		return cont_contract_id;
	}
	public void setCont_contract_id(int cont_contract_id) {
		this.cont_contract_id = cont_contract_id;
	}
	public String getCont_contract_type_id() {
		return cont_contract_type_id;
	}
	public void setCont_contract_type_id(String cont_contract_type_id) {
		this.cont_contract_type_id = cont_contract_type_id;
	}
	public int getCont_contract_type() {
		return cont_contract_type;
	}
	public void setCont_contract_type(int cont_contract_type) {
		this.cont_contract_type = cont_contract_type;
	}
	public Date getCont_contract_type_created_at() {
		return cont_contract_type_created_at;
	}
	public void setCont_contract_type_created_at(Date cont_contract_type_created_at) {
		this.cont_contract_type_created_at = cont_contract_type_created_at;
	}
	public int getCont_contract_type_added_by() {
		return cont_contract_type_added_by;
	}
	public void setCont_contract_type_added_by(int cont_contract_type_added_by) {
		this.cont_contract_type_added_by = cont_contract_type_added_by;
	}
	
	
	
}
