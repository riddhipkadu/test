package lcmt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="trn_query_raised")
public class QueryRaised {
	
	@Id
	@GeneratedValue
	private int trn_query_id;
	private int trn_query_raised_by_id;
	private String trn_query;
	private int trn_query_answer_by_id;
	private String trn_query_answer;
	private int trn_query_related_id;
	private String trn_query_related_to;
	private Date trn_created_at;
	private Date trn_query_answer_at;
	
	public Date getTrn_query_answer_at() {
		return trn_query_answer_at;
	}
	public void setTrn_query_answer_at(Date trn_query_answer_at) {
		this.trn_query_answer_at = trn_query_answer_at;
	}
	public int getTrn_query_id() {
		return trn_query_id;
	}
	public void setTrn_query_id(int trn_query_id) {
		this.trn_query_id = trn_query_id;
	}
	public int getTrn_query_raised_by_id() {
		return trn_query_raised_by_id;
	}
	public void setTrn_query_raised_by_id(int trn_query_raised_by_id) {
		this.trn_query_raised_by_id = trn_query_raised_by_id;
	}
	public String getTrn_query() {
		return trn_query;
	}
	public void setTrn_query(String trn_query) {
		this.trn_query = trn_query;
	}
	public int getTrn_query_answer_by_id() {
		return trn_query_answer_by_id;
	}
	public void setTrn_query_answer_by_id(int trn_query_answer_by_id) {
		this.trn_query_answer_by_id = trn_query_answer_by_id;
	}
	public String getTrn_query_answer() {
		return trn_query_answer;
	}
	public void setTrn_query_answer(String trn_query_answer) {
		this.trn_query_answer = trn_query_answer;
	}
	public int getTrn_query_related_id() {
		return trn_query_related_id;
	}
	public void setTrn_query_related_id(int trn_query_related_id) {
		this.trn_query_related_id = trn_query_related_id;
	}
	public String getTrn_query_related_to() {
		return trn_query_related_to;
	}
	public void setTrn_query_related_to(String trn_query_related_to) {
		this.trn_query_related_to = trn_query_related_to;
	}
	public Date getTrn_created_at() {
		return trn_created_at;
	}
	public void setTrn_created_at(Date trn_created_at) {
		this.trn_created_at = trn_created_at;
	}
	
	
	
	
	
}
