package lcmt.domain;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;


public class ClauseLib_Reference {
	
	
	private int clause_Lib_Ids;
	private int clause_Ids;
	private String clause_name;
	private int lib_owner_Id;
	private String lib_owner_name;
	private Date lib_created_At;
	private Date lib_updated_At;
	private String lib_created_Ats;
	private String lib_updated_Ats;
	private int lib_act_deact;
	private Date lib_review_date;
	private String lib_review_dates;
	private int lib_added_by;
	
	@Size(max = 10000)
	private String lib_paragraph;
	
	
	public String getLib_created_Ats() {
		return lib_created_Ats;
	}
	public void setLib_created_Ats(String lib_created_Ats) {
		this.lib_created_Ats = lib_created_Ats;
	}
	public String getLib_updated_Ats() {
		return lib_updated_Ats;
	}
	public void setLib_updated_Ats(String lib_updated_Ats) {
		this.lib_updated_Ats = lib_updated_Ats;
	}
	public String getLib_review_dates() {
		return lib_review_dates;
	}
	public void setLib_review_dates(String lib_review_dates) {
		this.lib_review_dates = lib_review_dates;
	}
	public String getLib_paragraph() {
		return lib_paragraph;
	}
	public void setLib_paragraph(String lib_paragraph) {
		this.lib_paragraph = lib_paragraph;
	}
	public int getClause_Lib_Ids() {
		return clause_Lib_Ids;
	}
	public void setClause_Lib_Ids(int clause_Lib_Ids) {
		this.clause_Lib_Ids = clause_Lib_Ids;
	}
	public int getClause_Ids() {
		return clause_Ids;
	}
	public void setClause_Ids(int clause_Ids) {
		this.clause_Ids = clause_Ids;
	}
	public String getClause_name() {
		return clause_name;
	}
	public void setClause_name(String clause_name) {
		this.clause_name = clause_name;
	}
	public int getLib_owner_Id() {
		return lib_owner_Id;
	}
	public void setLib_owner_Id(int lib_owner_Id) {
		this.lib_owner_Id = lib_owner_Id;
	}
	public String getLib_owner_name() {
		return lib_owner_name;
	}
	public void setLib_owner_name(String lib_owner_name) {
		this.lib_owner_name = lib_owner_name;
	}
	public Date getLib_created_At() {
		return lib_created_At;
	}
	public void setLib_created_At(Date lib_created_At) {
		this.lib_created_At = lib_created_At;
	}
	public Date getLib_updated_At() {
		return lib_updated_At;
	}
	public void setLib_updated_At(Date lib_updated_At) {
		this.lib_updated_At = lib_updated_At;
	}
	public int getLib_act_deact() {
		return lib_act_deact;
	}
	public void setLib_act_deact(int lib_act_deact) {
		this.lib_act_deact = lib_act_deact;
	}
	public Date getLib_review_date() {
		return lib_review_date;
	}
	public void setLib_review_date(Date lib_review_date) {
		this.lib_review_date = lib_review_date;
	}
	public int getLib_added_by() {
		return lib_added_by;
	}
	public void setLib_added_by(int lib_added_by) {
		this.lib_added_by = lib_added_by;
	}
	
	

}
