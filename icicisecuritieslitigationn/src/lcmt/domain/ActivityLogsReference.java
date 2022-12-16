package lcmt.domain;


public class ActivityLogsReference {
	
	private int log_id;
	private int log_activity_id;
	private int log_sub_activity_id;
	private String log_assinged_to_name;
	private String log_activity;
	private String log_sub_activity;
	private String log_related_to;
	private String log_created_at;
	private int log_added_by;
	private String log_related_to_name;
	
	public String getLog_related_to_name() {
		return log_related_to_name;
	}
	public void setLog_related_to_name(String log_related_to_name) {
		this.log_related_to_name = log_related_to_name;
	}
	public int getLog_id() {
		return log_id;
	}
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}
	public int getLog_activity_id() {
		return log_activity_id;
	}
	public void setLog_activity_id(int log_activity_id) {
		this.log_activity_id = log_activity_id;
	}
	public int getLog_sub_activity_id() {
		return log_sub_activity_id;
	}
	public void setLog_sub_activity_id(int log_sub_activity_id) {
		this.log_sub_activity_id = log_sub_activity_id;
	}
	public String getLog_assinged_to_name() {
		return log_assinged_to_name;
	}
	public void setLog_assinged_to_name(String log_assinged_to_name) {
		this.log_assinged_to_name = log_assinged_to_name;
	}
	public String getLog_activity() {
		return log_activity;
	}
	public void setLog_activity(String log_activity) {
		this.log_activity = log_activity;
	}
	public String getLog_sub_activity() {
		return log_sub_activity;
	}
	public void setLog_sub_activity(String log_sub_activity) {
		this.log_sub_activity = log_sub_activity;
	}
	public String getLog_related_to() {
		return log_related_to;
	}
	public void setLog_related_to(String log_related_to) {
		this.log_related_to = log_related_to;
	}
	public String getLog_created_at() {
		return log_created_at;
	}
	public void setLog_created_at(String log_created_at) {
		this.log_created_at = log_created_at;
	}
	public int getLog_added_by() {
		return log_added_by;
	}
	public void setLog_added_by(int log_added_by) {
		this.log_added_by = log_added_by;
	}
	
	
}
