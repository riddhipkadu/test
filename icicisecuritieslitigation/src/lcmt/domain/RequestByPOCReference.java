package lcmt.domain;


import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

public class RequestByPOCReference {
	
	private int req_id;
	private int req_entity_id;
	private int req_unit_id;
	private int req_function_id;
	private String req_related_to;
	@Size(max = 3900)
	private String req_comments;
	private String req_entity_name;
	private String req_unit_name;
	private String req_function_name;
	private int req_contract_id;
	private int req_contract_type;
	private String req_contract_type_name;
	private String req_contract_date;
	private int req_party_no;
	@Size(max = 3900)
	private String req_contract_desc;
	private int req_contract_added_by;
	private Date req_contract_created_at;
	private List<ContractParties> req_cont_parties;
	private List<RequestDocument> req_doc_list;
	private int req_query_id;
	private String req_query_from;
	@Size(max = 3900)
	private String req_query;
	private Date req_query_date;
	private String req_query_date_name;
	private int req_liti_id;
	private int req_liti_category;
	private String req_liti_category_name;
	private String req_liti_company_type;
	private Date req_liti_case_filing_date;
	private String req_liti_case_filing_date_name;
	private String req_liti_case_ref_no;
	@Size(max = 3900)
	private String req_liti_des;
	private int req_query_approval_status;
	private int req_liti_approval_status;
	private int req_contract_approval_status;
	private String req_added_by_name;
	private int req_added_by;
	private String req_contract_name;
	private Date req_liti_received_date;
	private String req_liti_received_date_name;
	private List<RequestDocument> req_term_doc_list;
	private String created_at;
	private String req_query_criticality;
	private Date req_query_turnaround_time;
	private String req_query_turnaround_time_name;
	private String req_liti_party_against;
	private String req_liti_party_by;
	private int req_noti_id;
	private String req_noti_oppo_party;
	@Size(max = 3900)
	private String req_noti_des;
	private String req_noti_type_by_against;
	private int req_noti_entity_id;
	private int req_noti_unit_id;
	private int req_noti_function_id;
	private Date req_noti_date;
	private String req_noti_date_name;
	private int req_noti_approval_status;
	
	public int getReq_noti_approval_status() {
		return req_noti_approval_status;
	}
	public void setReq_noti_approval_status(int req_noti_approval_status) {
		this.req_noti_approval_status = req_noti_approval_status;
	}
	public String getReq_noti_date_name() {
		return req_noti_date_name;
	}
	public void setReq_noti_date_name(String req_noti_date_name) {
		this.req_noti_date_name = req_noti_date_name;
	}
	public int getReq_noti_id() {
		return req_noti_id;
	}
	public void setReq_noti_id(int req_noti_id) {
		this.req_noti_id = req_noti_id;
	}
	public String getReq_noti_oppo_party() {
		return req_noti_oppo_party;
	}
	public void setReq_noti_oppo_party(String req_noti_oppo_party) {
		this.req_noti_oppo_party = req_noti_oppo_party;
	}
	public String getReq_noti_des() {
		return req_noti_des;
	}
	public void setReq_noti_des(String req_noti_des) {
		this.req_noti_des = req_noti_des;
	}
	public String getReq_noti_type_by_against() {
		return req_noti_type_by_against;
	}
	public void setReq_noti_type_by_against(String req_noti_type_by_against) {
		this.req_noti_type_by_against = req_noti_type_by_against;
	}
	public int getReq_noti_entity_id() {
		return req_noti_entity_id;
	}
	public void setReq_noti_entity_id(int req_noti_entity_id) {
		this.req_noti_entity_id = req_noti_entity_id;
	}
	public int getReq_noti_unit_id() {
		return req_noti_unit_id;
	}
	public void setReq_noti_unit_id(int req_noti_unit_id) {
		this.req_noti_unit_id = req_noti_unit_id;
	}
	public int getReq_noti_function_id() {
		return req_noti_function_id;
	}
	public void setReq_noti_function_id(int req_noti_function_id) {
		this.req_noti_function_id = req_noti_function_id;
	}
	public Date getReq_noti_date() {
		return req_noti_date;
	}
	public void setReq_noti_date(Date req_noti_date) {
		this.req_noti_date = req_noti_date;
	}
	public String getReq_liti_party_against() {
		return req_liti_party_against;
	}
	public void setReq_liti_party_against(String req_liti_party_against) {
		this.req_liti_party_against = req_liti_party_against;
	}
	public String getReq_liti_party_by() {
		return req_liti_party_by;
	}
	public void setReq_liti_party_by(String req_liti_party_by) {
		this.req_liti_party_by = req_liti_party_by;
	}
	public String getReq_query_criticality() {
		return req_query_criticality;
	}
	public void setReq_query_criticality(String req_query_criticality) {
		this.req_query_criticality = req_query_criticality;
	}
	public Date getReq_query_turnaround_time() {
		return req_query_turnaround_time;
	}
	public void setReq_query_turnaround_time(Date req_query_turnaround_time) {
		this.req_query_turnaround_time = req_query_turnaround_time;
	}
	public String getReq_query_turnaround_time_name() {
		return req_query_turnaround_time_name;
	}
	public void setReq_query_turnaround_time_name(String req_query_turnaround_time_name) {
		this.req_query_turnaround_time_name = req_query_turnaround_time_name;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public int getReq_added_by() {
		return req_added_by;
	}
	public void setReq_added_by(int req_added_by) {
		this.req_added_by = req_added_by;
	}
	public List<RequestDocument> getReq_term_doc_list() {
		return req_term_doc_list;
	}
	public void setReq_term_doc_list(List<RequestDocument> req_term_doc_list) {
		this.req_term_doc_list = req_term_doc_list;
	}
	public String getReq_liti_received_date_name() {
		return req_liti_received_date_name;
	}
	public void setReq_liti_received_date_name(String req_liti_received_date_name) {
		this.req_liti_received_date_name = req_liti_received_date_name;
	}
	public Date getReq_liti_received_date() {
		return req_liti_received_date;
	}
	public void setReq_liti_received_date(Date req_liti_received_date) {
		this.req_liti_received_date = req_liti_received_date;
	}
	public String getReq_contract_name() {
		return req_contract_name;
	}
	public void setReq_contract_name(String req_contract_name) {
		this.req_contract_name = req_contract_name;
	}
	public String getReq_added_by_name() {
		return req_added_by_name;
	}
	public void setReq_added_by_name(String req_added_by_name) {
		this.req_added_by_name = req_added_by_name;
	}
	public int getReq_query_approval_status() {
		return req_query_approval_status;
	}
	public void setReq_query_approval_status(int req_query_approval_status) {
		this.req_query_approval_status = req_query_approval_status;
	}
	public int getReq_liti_approval_status() {
		return req_liti_approval_status;
	}
	public void setReq_liti_approval_status(int req_liti_approval_status) {
		this.req_liti_approval_status = req_liti_approval_status;
	}
	public int getReq_contract_approval_status() {
		return req_contract_approval_status;
	}
	public void setReq_contract_approval_status(int req_contract_approval_status) {
		this.req_contract_approval_status = req_contract_approval_status;
	}
	public int getReq_liti_id() {
		return req_liti_id;
	}
	public void setReq_liti_id(int req_liti_id) {
		this.req_liti_id = req_liti_id;
	}
	public int getReq_liti_category() {
		return req_liti_category;
	}
	public void setReq_liti_category(int req_liti_category) {
		this.req_liti_category = req_liti_category;
	}
	public String getReq_liti_category_name() {
		return req_liti_category_name;
	}
	public void setReq_liti_category_name(String req_liti_category_name) {
		this.req_liti_category_name = req_liti_category_name;
	}
	public String getReq_liti_company_type() {
		return req_liti_company_type;
	}
	public void setReq_liti_company_type(String req_liti_company_type) {
		this.req_liti_company_type = req_liti_company_type;
	}
	public Date getReq_liti_case_filing_date() {
		return req_liti_case_filing_date;
	}
	public void setReq_liti_case_filing_date(Date req_liti_case_filing_date) {
		this.req_liti_case_filing_date = req_liti_case_filing_date;
	}
	public String getReq_liti_case_filing_date_name() {
		return req_liti_case_filing_date_name;
	}
	public void setReq_liti_case_filing_date_name(String req_liti_case_filing_date_name) {
		this.req_liti_case_filing_date_name = req_liti_case_filing_date_name;
	}
	public String getReq_liti_case_ref_no() {
		return req_liti_case_ref_no;
	}
	public void setReq_liti_case_ref_no(String req_liti_case_ref_no) {
		this.req_liti_case_ref_no = req_liti_case_ref_no;
	}
	public String getReq_liti_des() {
		return req_liti_des;
	}
	public void setReq_liti_des(String req_liti_des) {
		this.req_liti_des = req_liti_des;
	}
	public int getReq_query_id() {
		return req_query_id;
	}
	public void setReq_query_id(int req_query_id) {
		this.req_query_id = req_query_id;
	}
	public String getReq_query_from() {
		return req_query_from;
	}
	public void setReq_query_from(String req_query_from) {
		this.req_query_from = req_query_from;
	}
	public String getReq_query() {
		return req_query;
	}
	public void setReq_query(String req_query) {
		this.req_query = req_query;
	}
	public Date getReq_query_date() {
		return req_query_date;
	}
	public void setReq_query_date(Date req_query_date) {
		this.req_query_date = req_query_date;
	}
	public String getReq_query_date_name() {
		return req_query_date_name;
	}
	public void setReq_query_date_name(String req_query_date_name) {
		this.req_query_date_name = req_query_date_name;
	}
	public List<RequestDocument> getReq_doc_list() {
		return req_doc_list;
	}
	public void setReq_doc_list(List<RequestDocument> req_doc_list) {
		this.req_doc_list = req_doc_list;
	}
	public List<ContractParties> getReq_cont_parties() {
		return req_cont_parties;
	}
	public void setReq_cont_parties(List<ContractParties> req_cont_parties) {
		this.req_cont_parties = req_cont_parties;
	}
	public int getReq_contract_id() {
		return req_contract_id;
	}
	public void setReq_contract_id(int req_contract_id) {
		this.req_contract_id = req_contract_id;
	}
	public int getReq_contract_type() {
		return req_contract_type;
	}
	public void setReq_contract_type(int req_contract_type) {
		this.req_contract_type = req_contract_type;
	}
	public String getReq_contract_type_name() {
		return req_contract_type_name;
	}
	public void setReq_contract_type_name(String req_contract_type_name) {
		this.req_contract_type_name = req_contract_type_name;
	}
	public String getReq_contract_date() {
		return req_contract_date;
	}
	public void setReq_contract_date(String req_contract_date) {
		this.req_contract_date = req_contract_date;
	}
	public int getReq_party_no() {
		return req_party_no;
	}
	public void setReq_party_no(int req_party_no) {
		this.req_party_no = req_party_no;
	}
	public String getReq_contract_desc() {
		return req_contract_desc;
	}
	public void setReq_contract_desc(String req_contract_desc) {
		this.req_contract_desc = req_contract_desc;
	}
	public int getReq_contract_added_by() {
		return req_contract_added_by;
	}
	public void setReq_contract_added_by(int req_contract_added_by) {
		this.req_contract_added_by = req_contract_added_by;
	}
	public Date getReq_contract_created_at() {
		return req_contract_created_at;
	}
	public void setReq_contract_created_at(Date req_contract_created_at) {
		this.req_contract_created_at = req_contract_created_at;
	}
	public int getReq_id() {
		return req_id;
	}
	public void setReq_id(int req_id) {
		this.req_id = req_id;
	}
	public int getReq_entity_id() {
		return req_entity_id;
	}
	public void setReq_entity_id(int req_entity_id) {
		this.req_entity_id = req_entity_id;
	}
	public int getReq_unit_id() {
		return req_unit_id;
	}
	public void setReq_unit_id(int req_unit_id) {
		this.req_unit_id = req_unit_id;
	}
	public int getReq_function_id() {
		return req_function_id;
	}
	public void setReq_function_id(int req_function_id) {
		this.req_function_id = req_function_id;
	}
	public String getReq_related_to() {
		return req_related_to;
	}
	public void setReq_related_to(String req_related_to) {
		this.req_related_to = req_related_to;
	}
	public String getReq_comments() {
		return req_comments;
	}
	public void setReq_comments(String req_comments) {
		this.req_comments = req_comments;
	}
	public String getReq_entity_name() {
		return req_entity_name;
	}
	public void setReq_entity_name(String req_entity_name) {
		this.req_entity_name = req_entity_name;
	}
	public String getReq_unit_name() {
		return req_unit_name;
	}
	public void setReq_unit_name(String req_unit_name) {
		this.req_unit_name = req_unit_name;
	}
	public String getReq_function_name() {
		return req_function_name;
	}
	public void setReq_function_name(String req_function_name) {
		this.req_function_name = req_function_name;
	}
	
}
