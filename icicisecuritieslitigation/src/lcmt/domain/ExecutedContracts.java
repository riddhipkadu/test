package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name="mst_executed_contacts")
public class ExecutedContracts {

	@Id
	@GeneratedValue
	private int exec_contract_id;
	private int exec_contract_entity_id;
	private int exec_contract_unit_id;
	private int exec_contract_function_id;
	private int exec_contract_type_id;
	@Size(max = 3900)
	private String exec_contract_description;
	private String exec_contract_executed_with;
	private Date exec_contract_date;
	private Date exec_contract_created_at;
	private Date exec_contract_updated_at;
	private int exec_contract_added_by;
	private String exec_contract_internal_contact_name;
	private String exec_contract_executed_contact_name;
	private int exec_contract_resposible_person;
	private Date exec_contract_start_date;
	private Date exec_contract_end_date;
	@Size(max = 3900)
	private String exec_contract_surviving_obl;
	private String exec_contract_payment;
	private String exec_contract_notice_period;
	@Size(max = 3900)
	private String exec_contract_insurance;
	@Size(max = 3900)
	private String exec_contract_damages;
	@Size(max = 3900)
	private String exec_contract_jurisdiction;
	@Size(max = 3900)
	private String exec_contract_arbitration;
	private String exec_contract_criticality;
	private String exec_contract_cont_person_email;
	private String exec_contract_cont_person_number;
	
	public String getExec_contract_cont_person_email() {
		return exec_contract_cont_person_email;
	}
	public void setExec_contract_cont_person_email(String exec_contract_cont_person_email) {
		this.exec_contract_cont_person_email = exec_contract_cont_person_email;
	}
	public String getExec_contract_cont_person_number() {
		return exec_contract_cont_person_number;
	}
	public void setExec_contract_cont_person_number(String exec_contract_cont_person_number) {
		this.exec_contract_cont_person_number = exec_contract_cont_person_number;
	}
	public Date getExec_contract_start_date() {
		return exec_contract_start_date;
	}
	public void setExec_contract_start_date(Date exec_contract_start_date) {
		this.exec_contract_start_date = exec_contract_start_date;
	}
	public Date getExec_contract_end_date() {
		return exec_contract_end_date;
	}
	public void setExec_contract_end_date(Date exec_contract_end_date) {
		this.exec_contract_end_date = exec_contract_end_date;
	}
	public String getExec_contract_surviving_obl() {
		return exec_contract_surviving_obl;
	}
	public void setExec_contract_surviving_obl(String exec_contract_surviving_obl) {
		this.exec_contract_surviving_obl = exec_contract_surviving_obl;
	}
	public String getExec_contract_payment() {
		return exec_contract_payment;
	}
	public void setExec_contract_payment(String exec_contract_payment) {
		this.exec_contract_payment = exec_contract_payment;
	}
	public String getExec_contract_notice_period() {
		return exec_contract_notice_period;
	}
	public void setExec_contract_notice_period(String exec_contract_notice_period) {
		this.exec_contract_notice_period = exec_contract_notice_period;
	}
	public String getExec_contract_insurance() {
		return exec_contract_insurance;
	}
	public void setExec_contract_insurance(String exec_contract_insurance) {
		this.exec_contract_insurance = exec_contract_insurance;
	}
	public String getExec_contract_damages() {
		return exec_contract_damages;
	}
	public void setExec_contract_damages(String exec_contract_damages) {
		this.exec_contract_damages = exec_contract_damages;
	}
	public String getExec_contract_jurisdiction() {
		return exec_contract_jurisdiction;
	}
	public void setExec_contract_jurisdiction(String exec_contract_jurisdiction) {
		this.exec_contract_jurisdiction = exec_contract_jurisdiction;
	}
	public String getExec_contract_arbitration() {
		return exec_contract_arbitration;
	}
	public void setExec_contract_arbitration(String exec_contract_arbitration) {
		this.exec_contract_arbitration = exec_contract_arbitration;
	}
	public String getExec_contract_criticality() {
		return exec_contract_criticality;
	}
	public void setExec_contract_criticality(String exec_contract_criticality) {
		this.exec_contract_criticality = exec_contract_criticality;
	}
	public int getExec_contract_id() {
		return exec_contract_id;
	}
	public void setExec_contract_id(int exec_contract_id) {
		this.exec_contract_id = exec_contract_id;
	}
	public int getExec_contract_entity_id() {
		return exec_contract_entity_id;
	}
	public void setExec_contract_entity_id(int exec_contract_entity_id) {
		this.exec_contract_entity_id = exec_contract_entity_id;
	}
	public int getExec_contract_unit_id() {
		return exec_contract_unit_id;
	}
	public void setExec_contract_unit_id(int exec_contract_unit_id) {
		this.exec_contract_unit_id = exec_contract_unit_id;
	}
	public int getExec_contract_function_id() {
		return exec_contract_function_id;
	}
	public void setExec_contract_function_id(int exec_contract_function_id) {
		this.exec_contract_function_id = exec_contract_function_id;
	}
	public int getExec_contract_type_id() {
		return exec_contract_type_id;
	}
	public void setExec_contract_type_id(int exec_contract_type_id) {
		this.exec_contract_type_id = exec_contract_type_id;
	}
	public String getExec_contract_description() {
		return exec_contract_description;
	}
	public void setExec_contract_description(String exec_contract_description) {
		this.exec_contract_description = exec_contract_description;
	}
	public String getExec_contract_executed_with() {
		return exec_contract_executed_with;
	}
	public void setExec_contract_executed_with(String exec_contract_executed_with) {
		this.exec_contract_executed_with = exec_contract_executed_with;
	}
	public Date getExec_contract_date() {
		return exec_contract_date;
	}
	public void setExec_contract_date(Date exec_contract_date) {
		this.exec_contract_date = exec_contract_date;
	}
	public Date getExec_contract_created_at() {
		return exec_contract_created_at;
	}
	public void setExec_contract_created_at(Date exec_contract_created_at) {
		this.exec_contract_created_at = exec_contract_created_at;
	}
	public Date getExec_contract_updated_at() {
		return exec_contract_updated_at;
	}
	public void setExec_contract_updated_at(Date exec_contract_updated_at) {
		this.exec_contract_updated_at = exec_contract_updated_at;
	}
	public int getExec_contract_added_by() {
		return exec_contract_added_by;
	}
	public void setExec_contract_added_by(int exec_contract_added_by) {
		this.exec_contract_added_by = exec_contract_added_by;
	}
	public String getExec_contract_internal_contact_name() {
		return exec_contract_internal_contact_name;
	}
	public void setExec_contract_internal_contact_name(String exec_contract_internal_contact_name) {
		this.exec_contract_internal_contact_name = exec_contract_internal_contact_name;
	}
	public String getExec_contract_executed_contact_name() {
		return exec_contract_executed_contact_name;
	}
	public void setExec_contract_executed_contact_name(String exec_contract_executed_contact_name) {
		this.exec_contract_executed_contact_name = exec_contract_executed_contact_name;
	}
	public int getExec_contract_resposible_person() {
		return exec_contract_resposible_person;
	}
	public void setExec_contract_resposible_person(int exec_contract_resposible_person) {
		this.exec_contract_resposible_person = exec_contract_resposible_person;
	}
	
	
	
}
