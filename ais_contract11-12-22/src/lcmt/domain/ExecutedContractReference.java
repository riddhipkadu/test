package lcmt.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

/*
 * Author: Harshad Padole
 * Date: 13/09/2016
 * Updated By: 
 * Updated Date: 
 * 
 * */

public class ExecutedContractReference {

	private int exec_contract_id;
	private int exec_contract_entity_id;
	private String exec_contract_entity_name;
	private int exec_contract_unit_id;
	private String exec_contract_unit_name;
	private int exec_contract_function_id;
	private String exec_contract_function_name;
	private int exec_contract_type_id;
	private String exec_contract_type_name;
	private String exec_contract_description;
	private String exec_contract_executed_with;
	private String exec_contract_date;
	private String exec_contract_document_name;
	private String exec_contract_file_size;
	private String exec_contract_file_path;
	private int exec_contract_doc_id;
	private String exec_contract_internal_contact_person;
	private String exec_contract_contact_person;
	private List<ExecutedContractDocuments> exeCont_doc;
	private List<ContractParties> exec_parties;
	private Date exec_contract_date_date;
	private int exec_contract_added_by;
	private int exec_contract_executed_type_status; // if status = 1 then it is executed contract and if status =2 its pre executed contract
	private List<ContractContractType> exec_cont_type_list;
	private List<ContractType> exec_cont_type_list_name;
	private Date exec_contract_created_at;
	private Date exec_contract_updated_at;
	private int exec_contract_resposible_person;
	private String exec_contract_resposible_person_name;
	private Date exec_contract_start_date;
	private Date exec_contract_end_date;
	private String exec_contract_start_date_name;
	private String exec_contract_end_date_name;
	private int exec_id;
	@Size(max = 5000)
	private String exec_contract_surviving_obl;
	private String exec_contract_payment;
	private String exec_contract_notice_period;
	private String exec_contract_lockin_period;
	@Size(max = 5000)
	private String exec_contract_insurance;
	private String exec_contract_termination_clause;
	private String exec_contract_consider_involve;
	private String exec_contract_signatories_assign;
	@Size(max = 5000)
	private String exec_contract_damages;
	@Size(max = 5000)
	private String exec_contract_jurisdiction;
	@Size(max = 5000)
	private String exec_contract_arbitration;
	private String exec_contract_criticality;
	private String exec_contract_cont_person_email;
	private String exec_contract_cont_person_number;
	private String exec_contract_executed_contact_name;
	private int exec_contract_pre_cont_id;
	private String exec_contract_execution_date;
	private String exec_contract_execution_date_name;
	private String exec_contract_renewal_date;
	private String exec_contract_renewal_date_name;
	private String exec_contract_ammendment;
	private String exec_contract_agree_status;
	private String exec_contract_renewal_status;
	
	private String exec_remind_date;
	private String exec_remind_date_name;
	private String exec_remind_date_second;
	private String exec_remind_date_second_name;
	private String exec_remind_date_third;
	private String exec_remind_date_third_name;
	private String exec_contract_division;
	@Size(max = 5000)
	private String exec_force_majeure;
	
	private String exec_royalty_number;
	private String exec_option_number;
	private String exec_territory_number;
	private String exec_crop_number;
	private String exec_technology_number;
	private String exec_report_number;
	private String exec_milestone_number;
	private String exec__assignability;
	
	
	
	
	
	public String getExec__assignability() {
		return exec__assignability;
	}
	public void setExec__assignability(String exec__assignability) {
		this.exec__assignability = exec__assignability;
	}
	public String getExec_royalty_number() {
		return exec_royalty_number;
	}
	public void setExec_royalty_number(String exec_royalty_number) {
		this.exec_royalty_number = exec_royalty_number;
	}
	public String getExec_option_number() {
		return exec_option_number;
	}
	public void setExec_option_number(String exec_option_number) {
		this.exec_option_number = exec_option_number;
	}
	public String getExec_territory_number() {
		return exec_territory_number;
	}
	public void setExec_territory_number(String exec_territory_number) {
		this.exec_territory_number = exec_territory_number;
	}
	public String getExec_crop_number() {
		return exec_crop_number;
	}
	public void setExec_crop_number(String exec_crop_number) {
		this.exec_crop_number = exec_crop_number;
	}
	public String getExec_technology_number() {
		return exec_technology_number;
	}
	public void setExec_technology_number(String exec_technology_number) {
		this.exec_technology_number = exec_technology_number;
	}
	public String getExec_report_number() {
		return exec_report_number;
	}
	public void setExec_report_number(String exec_report_number) {
		this.exec_report_number = exec_report_number;
	}
	public String getExec_milestone_number() {
		return exec_milestone_number;
	}
	public void setExec_milestone_number(String exec_milestone_number) {
		this.exec_milestone_number = exec_milestone_number;
	}
	public String getExec_contract_division() {
		return exec_contract_division;
	}
	public void setExec_contract_division(String exec_contract_division) {
		this.exec_contract_division = exec_contract_division;
	}
	public int getExec_id() {
		return exec_id;
	}
	public void setExec_id(int exec_id) {
		this.exec_id = exec_id;
	}
	public String getExec_remind_date_name() {
		return exec_remind_date_name;
	}
	public void setExec_remind_date_name(String exec_remind_date_name) {
		this.exec_remind_date_name = exec_remind_date_name;
	}
	public String getExec_remind_date_second_name() {
		return exec_remind_date_second_name;
	}
	public void setExec_remind_date_second_name(String exec_remind_date_second_name) {
		this.exec_remind_date_second_name = exec_remind_date_second_name;
	}
	public String getExec_remind_date_third_name() {
		return exec_remind_date_third_name;
	}
	public void setExec_remind_date_third_name(String exec_remind_date_third_name) {
		this.exec_remind_date_third_name = exec_remind_date_third_name;
	}
	public String getExec_remind_date() {
		return exec_remind_date;
	}
	public void setExec_remind_date(String exec_remind_date) {
		this.exec_remind_date = exec_remind_date;
	}
	public String getExec_remind_date_second() {
		return exec_remind_date_second;
	}
	public void setExec_remind_date_second(String exec_remind_date_second) {
		this.exec_remind_date_second = exec_remind_date_second;
	}
	public String getExec_remind_date_third() {
		return exec_remind_date_third;
	}
	public void setExec_remind_date_third(String exec_remind_date_third) {
		this.exec_remind_date_third = exec_remind_date_third;
	}
	public String getExec_contract_execution_date_name() {
		return exec_contract_execution_date_name;
	}
	public void setExec_contract_execution_date_name(String exec_contract_execution_date_name) {
		this.exec_contract_execution_date_name = exec_contract_execution_date_name;
	}
	public String getExec_contract_renewal_date_name() {
		return exec_contract_renewal_date_name;
	}
	public void setExec_contract_renewal_date_name(String exec_contract_renewal_date_name) {
		this.exec_contract_renewal_date_name = exec_contract_renewal_date_name;
	}
	public String getExec_contract_lockin_period() {
		return exec_contract_lockin_period;
	}
	public void setExec_contract_lockin_period(String exec_contract_lockin_period) {
		this.exec_contract_lockin_period = exec_contract_lockin_period;
	}
	public String getExec_contract_termination_clause() {
		return exec_contract_termination_clause;
	}
	public void setExec_contract_termination_clause(String exec_contract_termination_clause) {
		this.exec_contract_termination_clause = exec_contract_termination_clause;
	}
	public String getExec_contract_consider_involve() {
		return exec_contract_consider_involve;
	}
	public void setExec_contract_consider_involve(String exec_contract_consider_involve) {
		this.exec_contract_consider_involve = exec_contract_consider_involve;
	}
	public String getExec_contract_signatories_assign() {
		return exec_contract_signatories_assign;
	}
	public void setExec_contract_signatories_assign(String exec_contract_signatories_assign) {
		this.exec_contract_signatories_assign = exec_contract_signatories_assign;
	}
	
	public String getExec_contract_execution_date() {
		return exec_contract_execution_date;
	}
	public void setExec_contract_execution_date(String exec_contract_execution_date) {
		this.exec_contract_execution_date = exec_contract_execution_date;
	}
	public String getExec_contract_renewal_date() {
		return exec_contract_renewal_date;
	}
	public void setExec_contract_renewal_date(String exec_contract_renewal_date) {
		this.exec_contract_renewal_date = exec_contract_renewal_date;
	}
	public String getExec_contract_ammendment() {
		return exec_contract_ammendment;
	}
	public void setExec_contract_ammendment(String exec_contract_ammendment) {
		this.exec_contract_ammendment = exec_contract_ammendment;
	}
	public String getExec_contract_agree_status() {
		return exec_contract_agree_status;
	}
	public void setExec_contract_agree_status(String exec_contract_agree_status) {
		this.exec_contract_agree_status = exec_contract_agree_status;
	}
	public String getExec_contract_renewal_status() {
		return exec_contract_renewal_status;
	}
	public void setExec_contract_renewal_status(String exec_contract_renewal_status) {
		this.exec_contract_renewal_status = exec_contract_renewal_status;
	}
	private List<ContractDocuments> contDocs;
	
	public List<ContractDocuments> getContDocs() {
		return contDocs;
	}
	public void setContDocs(List<ContractDocuments> contDocs) {
		this.contDocs = contDocs;
	}
	public int getExec_contract_pre_cont_id() {
		return exec_contract_pre_cont_id;
	}
	public void setExec_contract_pre_cont_id(int exec_contract_pre_cont_id) {
		this.exec_contract_pre_cont_id = exec_contract_pre_cont_id;
	}
	public String getExec_contract_executed_contact_name() {
		return exec_contract_executed_contact_name;
	}
	public void setExec_contract_executed_contact_name(String exec_contract_executed_contact_name) {
		this.exec_contract_executed_contact_name = exec_contract_executed_contact_name;
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
	public void setExec_contract_start_date(Date string) {
		this.exec_contract_start_date = string;
	}
	public Date getExec_contract_end_date() {
		return exec_contract_end_date;
	}
	public void setExec_contract_end_date(Date string) {
		this.exec_contract_end_date = string;
	}
	public String getExec_contract_start_date_name() {
		return exec_contract_start_date_name;
	}
	public void setExec_contract_start_date_name(String exec_contract_start_date_name) {
		this.exec_contract_start_date_name = exec_contract_start_date_name;
	}
	public String getExec_contract_end_date_name() {
		return exec_contract_end_date_name;
	}
	public void setExec_contract_end_date_name(String exec_contract_end_date_name) {
		this.exec_contract_end_date_name = exec_contract_end_date_name;
	}
	public String getExec_contract_resposible_person_name() {
		return exec_contract_resposible_person_name;
	}
	public void setExec_contract_resposible_person_name(String exec_contract_resposible_person_name) {
		this.exec_contract_resposible_person_name = exec_contract_resposible_person_name;
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
	public List<ContractType> getExec_cont_type_list_name() {
		return exec_cont_type_list_name;
	}
	public void setExec_cont_type_list_name(List<ContractType> exec_cont_type_list_name) {
		this.exec_cont_type_list_name = exec_cont_type_list_name;
	}
	public List<ContractContractType> getExec_cont_type_list() {
		return exec_cont_type_list;
	}
	public void setExec_cont_type_list(List<ContractContractType> exec_cont_type_list) {
		this.exec_cont_type_list = exec_cont_type_list;
	}
	public int getExec_contract_executed_type_status() {
		return exec_contract_executed_type_status;
	}
	public void setExec_contract_executed_type_status(int exec_contract_executed_type_status) {
		this.exec_contract_executed_type_status = exec_contract_executed_type_status;
	}
	public int getExec_contract_added_by() {
		return exec_contract_added_by;
	}
	public void setExec_contract_added_by(int exec_contract_added_by) {
		this.exec_contract_added_by = exec_contract_added_by;
	}
	public String getExec_contract_internal_contact_person() {
		return exec_contract_internal_contact_person;
	}
	public void setExec_contract_internal_contact_person(String exec_contract_internal_contact_person) {
		this.exec_contract_internal_contact_person = exec_contract_internal_contact_person;
	}
	public String getExec_contract_contact_person() {
		return exec_contract_contact_person;
	}
	public void setExec_contract_contact_person(String exec_contract_contact_person) {
		this.exec_contract_contact_person = exec_contract_contact_person;
	}
	public List<ExecutedContractDocuments> getExeCont_doc() {
		return exeCont_doc;
	}
	public void setExeCont_doc(List<ExecutedContractDocuments> exeCont_doc) {
		this.exeCont_doc = exeCont_doc;
	}
	public int getExec_contract_doc_id() {
		return exec_contract_doc_id;
	}
	public void setExec_contract_doc_id(int exec_contract_doc_id) {
		this.exec_contract_doc_id = exec_contract_doc_id;
	}
	public int getExec_contract_id() {
		return exec_contract_id;
	}
	public int getExec_contract_entity_id() {
		return exec_contract_entity_id;
	}
	public String getExec_contract_entity_name() {
		return exec_contract_entity_name;
	}
	public int getExec_contract_unit_id() {
		return exec_contract_unit_id;
	}
	public String getExec_contract_unit_name() {
		return exec_contract_unit_name;
	}
	public int getExec_contract_function_id() {
		return exec_contract_function_id;
	}
	public String getExec_contract_function_name() {
		return exec_contract_function_name;
	}
	public int getExec_contract_type_id() {
		return exec_contract_type_id;
	}
	public String getExec_contract_type_name() {
		return exec_contract_type_name;
	}
	public String getExec_contract_description() {
		return exec_contract_description;
	}
	public String getExec_contract_executed_with() {
		return exec_contract_executed_with;
	}
	public String getExec_contract_date() {
		return exec_contract_date;
	}
	public String getExec_contract_document_name() {
		return exec_contract_document_name;
	}
	public String getExec_contract_file_size() {
		return exec_contract_file_size;
	}
	public void setExec_contract_id(int exec_contract_id) {
		this.exec_contract_id = exec_contract_id;
	}
	public void setExec_contract_entity_id(int exec_contract_entity_id) {
		this.exec_contract_entity_id = exec_contract_entity_id;
	}
	public void setExec_contract_entity_name(String exec_contract_entity_name) {
		this.exec_contract_entity_name = exec_contract_entity_name;
	}
	public void setExec_contract_unit_id(int exec_contract_unit_id) {
		this.exec_contract_unit_id = exec_contract_unit_id;
	}
	public void setExec_contract_unit_name(String exec_contract_unit_name) {
		this.exec_contract_unit_name = exec_contract_unit_name;
	}
	public void setExec_contract_function_id(int exec_contract_function_id) {
		this.exec_contract_function_id = exec_contract_function_id;
	}
	public void setExec_contract_function_name(String exec_contract_function_name) {
		this.exec_contract_function_name = exec_contract_function_name;
	}
	public void setExec_contract_type_id(int exec_contract_type_id) {
		this.exec_contract_type_id = exec_contract_type_id;
	}
	public void setExec_contract_type_name(String exec_contract_type_name) {
		this.exec_contract_type_name = exec_contract_type_name;
	}
	public void setExec_contract_description(String exec_contract_description) {
		this.exec_contract_description = exec_contract_description;
	}
	public void setExec_contract_executed_with(String exec_contract_executed_with) {
		this.exec_contract_executed_with = exec_contract_executed_with;
	}
	public void setExec_contract_date(String exec_contract_date) {
		this.exec_contract_date = exec_contract_date;
	}
	public void setExec_contract_document_name(String exec_contract_document_name) {
		this.exec_contract_document_name = exec_contract_document_name;
	}
	public void setExec_contract_file_size(String exec_contract_file_size) {
		this.exec_contract_file_size = exec_contract_file_size;
	}
	public String getExec_contract_file_path() {
		return exec_contract_file_path;
	}
	public void setExec_contract_file_path(String exec_contract_file_path) {
		this.exec_contract_file_path = exec_contract_file_path;
	}
	/**
	 * @return the exec_parties
	 */
	public List<ContractParties> getExec_parties() {
		return exec_parties;
	}
	/**
	 * @param exec_parties the exec_parties to set
	 */
	public void setExec_parties(List<ContractParties> exec_parties) {
		this.exec_parties = exec_parties;
	}
	/**
	 * @return the exec_contract_date_date
	 */
	public Date getExec_contract_date_date() {
		return exec_contract_date_date;
	}
	/**
	 * @param exec_contract_date_date the exec_contract_date_date to set
	 */
	public void setExec_contract_date_date(Date exec_contract_date_date) {
		this.exec_contract_date_date = exec_contract_date_date;
	}
	public int getExec_contract_resposible_person() {
		return exec_contract_resposible_person;
	}
	public void setExec_contract_resposible_person(int exec_contract_resposible_person) {
		this.exec_contract_resposible_person = exec_contract_resposible_person;
	}
	public String getExec_force_majeure() {
		return exec_force_majeure;
	}
	public void setExec_force_majeure(String exec_force_majeure) {
		this.exec_force_majeure = exec_force_majeure;
	}
	
}
