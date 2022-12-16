package lcmt.domain;

import java.util.List;

public class ContractHistoryReference {

	private int chst_id;
	private String chst_comments;
	private String chst_status_others;
	private String chst_action_performed;
	private String chst_performed_by_others;
	private String chst_performed_by;
	private String chst_action_performed_others;
	private String chst_assigned_to;
	private String chst_status;
	private int chst_contract_id;
	private List<ContractHistoryDocuments> hst_doc;
	private String chst_created_at;
	private String chst_log_status;
	private List<ContractContractType> chst_contract_type;
	private int chst_assigned_to_id;
	private String chst_received_date;
	private String chst_assign_others;
	private int chst_added_by;
	private int chst_poc_user_id;
	
	public int getChst_poc_user_id() {
		return chst_poc_user_id;
	}
	public void setChst_poc_user_id(int chst_poc_user_id) {
		this.chst_poc_user_id = chst_poc_user_id;
	}
	public String getChst_assign_others() {
		return chst_assign_others;
	}
	public void setChst_assign_others(String chst_assign_others) {
		this.chst_assign_others = chst_assign_others;
	}
	public String getChst_received_date() {
		return chst_received_date;
	}
	public void setChst_received_date(String chst_received_date) {
		this.chst_received_date = chst_received_date;
	}
	public String getChst_log_status() {
		return chst_log_status;
	}
	public void setChst_log_status(String chst_log_status) {
		this.chst_log_status = chst_log_status;
	}
	public String getChst_status_others() {
		return chst_status_others;
	}
	public void setChst_status_others(String chst_status_others) {
		this.chst_status_others = chst_status_others;
	}
	public String getChst_action_performed() {
		return chst_action_performed;
	}
	public void setChst_action_performed(String chst_action_performed) {
		this.chst_action_performed = chst_action_performed;
	}
	public String getChst_performed_by_others() {
		return chst_performed_by_others;
	}
	public void setChst_performed_by_others(String chst_performed_by_others) {
		this.chst_performed_by_others = chst_performed_by_others;
	}
	public String getChst_performed_by() {
		return chst_performed_by;
	}
	public void setChst_performed_by(String chst_performed_by) {
		this.chst_performed_by = chst_performed_by;
	}
	public String getChst_action_performed_others() {
		return chst_action_performed_others;
	}
	public void setChst_action_performed_others(String chst_action_performed_others) {
		this.chst_action_performed_others = chst_action_performed_others;
	}
	public String getChst_assigned_to() {
		return chst_assigned_to;
	}
	public void setChst_assigned_to(String chst_assigned_to) {
		this.chst_assigned_to = chst_assigned_to;
	}
	public int getChst_id() {
		return chst_id;
	}
	public void setChst_id(int chst_id) {
		this.chst_id = chst_id;
	}
	public String getChst_comments() {
		return chst_comments;
	}
	public void setChst_comments(String chst_comments) {
		this.chst_comments = chst_comments;
	}
	public String getChst_status() {
		return chst_status;
	}
	public void setChst_status(String chst_status) {
		this.chst_status = chst_status;
	}
	public int getChst_contract_id() {
		return chst_contract_id;
	}
	public void setChst_contract_id(int chst_contract_id) {
		this.chst_contract_id = chst_contract_id;
	}
	public List<ContractHistoryDocuments> getHst_doc() {
		return hst_doc;
	}
	public void setHst_doc(List<ContractHistoryDocuments> hst_doc) {
		this.hst_doc = hst_doc;
	}
	public String getChst_created_at() {
		return chst_created_at;
	}
	public void setChst_created_at(String chst_created_at) {
		this.chst_created_at = chst_created_at;
	}
	public List<ContractContractType> getChst_contract_type() {
		return chst_contract_type;
	}
	public void setChst_contract_type(List<ContractContractType> chst_contract_type) {
		this.chst_contract_type = chst_contract_type;
	}
	public int getChst_assigned_to_id() {
		return chst_assigned_to_id;
	}
	public void setChst_assigned_to_id(int chst_assigned_to_id) {
		this.chst_assigned_to_id = chst_assigned_to_id;
	}
	public int getChst_added_by() {
		return chst_added_by;
	}
	public void setChst_added_by(int chst_added_by) {
		this.chst_added_by = chst_added_by;
	}
	
}
