package lcmt.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import lcmt.dao.LitigationDao;
import lcmt.domain.AdvocatePaidFees;
import lcmt.domain.CounselPaidFees;
import lcmt.domain.ExternalCounsel;
import lcmt.domain.HearingStage;
import lcmt.domain.HearingStageOnHearing;
import lcmt.domain.Litigation;
import lcmt.domain.LitigationAdvocateFees;
import lcmt.domain.LitigationCounselFees;
import lcmt.domain.LitigationEscalationMailId;
import lcmt.domain.LitigationReference;
import lcmt.domain.LitigationType;

/*
 * Author: Harshad Padole
 * Date: 09/08/2016
 * Updated By: Mahesh Kharote
 * Updated Date:
 * 
 * */

@Repository(value = "litigationDao")
@Transactional
public class LitigationDaoImpl implements LitigationDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void persist(Object object) {
		try {
			em.persist(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int isLitiNameExist(int liti_id, String liti_name) {
		try {
			String sql = "select count(*) as liti from mst_litigation_type where liti_type_name='" + liti_name + "' ";
			if (liti_id != 0) {
				sql += " AND liti_type_id !=" + liti_id;
			}
			Query query = em.createNativeQuery(sql);
			String count = query.getResultList().get(0).toString();
			return Integer.parseInt(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public <T> List<T> getAllLitiType(Class<T> clazz) {
		try {
			TypedQuery<T> query = em.createQuery(" from " + clazz.getName(), clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LitigationType getLiti_typeById(int liti_id) {
		try {
			String sql = "SELECT * from mst_litigation_type where liti_type_id=" + liti_id;
			Query query = em.createNativeQuery(sql, LitigationType.class);
			if (!query.getResultList().isEmpty()) {
				return (LitigationType) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateLitigation_type(LitigationType litigation) {
		try {
			em.merge(litigation);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public int deleteLitigationType(int liti_id) {
		try {
			String sql = "DELETE FROM mst_litigation_type WHERE liti_type_id = " + liti_id;
			Query query = em.createNativeQuery(sql);
			int deleteCount = query.executeUpdate();
			// System.out.println("This is no of rows deleted:" + deleteCount);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int checkDependancyLitigationType(int liti_id) {
		try {
			String sql = " Select case when liti.liti_type_id > 0 then 1 else 0 END as litiType from mst_litigation_type liti_type " 
					+ " Left join mst_litigation liti on liti_type.liti_type_id = liti.liti_type_id "
					+ " where liti_type.liti_type_id = "+liti_id+" LIMIT 1";
			Query query = em.createNativeQuery(sql);
			int count = Integer.parseInt(query.getResultList().get(0).toString());
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Add New Litigation to Database
	@Override
	public int persistLitigation(Litigation object) {
		try {
			em.persist(object);
			em.flush();
			return object.getLiti_id();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: List all Litigation from Database
	@Override
	public <T> List<T> getAllLitigation(Class<T> clazz) {
		try {
			TypedQuery<T> query = em.createQuery(" from " + clazz.getName(), clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Get Max Generated value for further processing
	@Override
	public int getMaxLastGeneratedValueForClientLitiId() {
		try {
			String sql = "SELECT MAX(liti_last_generated_value_for_client_liti_id) FROM mst_litigation";
			Query query = em.createNativeQuery(sql);
			if (!query.getResultList().isEmpty()) {
				if (query.getResultList().get(0) != null) {
					return Integer.parseInt(query.getResultList().get(0).toString());
				} else {
					return 0;
				}

			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Get details for litigation
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getJoinedLitigationDetailsById(int liti_id) {
		try {
			String sql = "SELECT liti_id,liti_against_by_id,liti_type_name,liti_case_reference_no,liti_case_filing_date,liti_external_counsel_id,intern.user_first_name, intern.user_last_name AS liti_internally_handled_name, "
					+ " liti_party_against,liti_court, liti_amount_involved, liti_relevant_law, liti_brief_description,liti_status ,liti_litigation_result,liti_disposal_date,liti_synopsis_of_order,"
					+ " liti_last_date_for_filling_appeal,liti_comments, advo_id, advo_name,court_name, liti_completion_court_id, liti_client_liti_id, liti_intenal_person,liti_involved_amt_currency,liti_assigned_to, liti_criticality, internal_liti.internal_liti_code, "
					+ " liti_oppo_party_address, liti_oppo_advo_law_firm, liti_opposite_party_advocate, liti_oppo_advo_contact, liti_jurisdiction_name, liti_previous_liti_ref_no,liti_party_by,"
					+ " liti_nature_of_complaint, liti_status_of_case, liti_link_of_website, liti_interest, liti_total_amount, liti_receipt_notice_date, liti_secondary_responsible, liti_third_responsible_person, liti_fourth_responsible_person, liti_pay, liti_part_acm, liti_acm_note_date FROM mst_litigation "
					+ " LEFT join mst_litigation_type on mst_litigation_type.liti_type_id = mst_litigation.liti_type_id "
					+ " LEFT join mst_user intern on intern.user_id = liti_internally_handled_by "
					+ " LEFT join mst_internal_litigation_code internal_liti on internal_liti.internal_liti_id = liti_internal_liti_code "
					//+ " join mst_external_counsel on exte_coun_id = liti_external_counsel_id "
					+ " LEFT join mst_advocate on mst_advocate.advo_id = liti_advocate_id "
					+ " LEFT join mst_court ON court_id = liti_court where liti_id = " + liti_id ;
			 System.out.println(" Details "+sql);
			Query query = em.createNativeQuery(sql);
			// System.out.println(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created: Harshad padole
	// Method Purpose: Save hearing stage details
	@Override
	public int saveHearingStage(HearingStage hearingStage) {
		try {
			em.persist(hearingStage);
			em.flush();
			return hearingStage.getTtrn_hearing_stage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created: Harshad padole
	// Method Purpose: Get hearing stage details by id
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getHearingDetailsByLitiId(int liti_id) {
		try {
			String sql = "SELECT ttrn_next_hearing_date,ttrn_first_alert,ttrn_second_alert,ttrn_third_alert,ttrn_stage_description,ttrn_hearing_stage,ttrn_stage_id,ttrn_responsible_person,ttrn_counsel_id,ttrn_added_by, ttrn_stage_status FROM trn_hearing_stages where ttrn_stage_status != 'Waiting for Approval' AND ttrn_litigation_id="+ liti_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Harshad padole
	// Method Purpose: Get hearing stage details by Hearing stage id for Edit
	@Override
	public HearingStage getHearingDetailsByStageId(int hear_stage_id) {
		try {
			String sql = "select * from trn_hearing_stages where ttrn_hearing_stage =" + hear_stage_id;
			Query query = em.createNativeQuery(sql, HearingStage.class);
			return (HearingStage) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Harshad Padole
	// Method Purpose: Update hearing stages
	@Override
	public void updateHearingStage(HearingStage hearingStage) {
		try {
			em.merge(hearingStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created By: Harshad Padole
	// Method Purpose: Update Litigation
	@Override
	public void updateLitigation(Litigation litigation) {
		try {
			em.merge(litigation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created By: Harshad Padole
	// Method Purpose: Get Litigation by id
	@Override
	public Litigation getLitigationByLitiId(int liti_id) {
		try {
			String sql = "select * from mst_litigation where liti_id=" + liti_id;
			Query query = em.createNativeQuery(sql, Litigation.class);
			if (!query.getResultList().isEmpty()) {
				return (Litigation) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Harshad Padole
	// Method Purpose: Search Litigation
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> searchLitigation(String json, HttpSession session) {
		try {
			
			SimpleDateFormat sdfIn = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdfOut = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
			int orga_id = Integer.parseInt(jsonObject.get("orga_id").toString());
			//int loca_id = Integer.parseInt(jsonObject.get("loca_id").toString());
			//int dept_id = Integer.parseInt(jsonObject.get("dept_id").toString());
			String liti_by_against = jsonObject.get("liti_by_against").toString();
			int liti_category = Integer.parseInt(jsonObject.get("liti_category").toString());
			String liti_litigation_result = jsonObject.get("liti_litigation_result").toString();
			String liti_case_from = jsonObject.get("liti_case_from").toString();
			String liti_case_to = jsonObject.get("liti_case_to").toString();
			String liti_hearing_date_from = jsonObject.get("liti_hearing_date_from").toString();
			String liti_hearing_date_to = jsonObject.get("liti_hearing_date_to").toString();
			String liti_criticality = jsonObject.get("liti_criticality").toString();
		    int liti_assigned_to = Integer.parseInt(jsonObject.get("liti_assigned_to").toString());
		    int liti_secondary_responsible = Integer.parseInt(jsonObject.get("liti_secondary_responsible").toString());
		    int liti_third_responsible_person = Integer.parseInt(jsonObject.get("liti_third_responsible_person").toString());
		//	int liti_internal_liti_code = Integer.parseInt(jsonObject.get("liti_internal_liti_code").toString());
			
			//int liti_handle_by = Integer.parseInt(jsonObject.get("liti_handle_by").toString());
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			int report_to = Integer.parseInt(session.getAttribute("sess_user_report_to").toString());
			String hear_date_sql = "";
			String sql = "select * from mst_litigation where liti_orga_id =" + orga_id;

			/*
			 * if (loca_id != 0) { sql += " AND liti_loca_id=" + loca_id; } if (dept_id !=
			 * 0) { sql += " AND liti_dept_id=" + dept_id; }
			 */
			if (liti_category != 0) {
				sql += " AND liti_type_id=" + liti_category;
			}
			if (!liti_by_against.equals("NA")) {
				sql += " AND liti_against_by_id='" + liti_by_against + "'";
			}
			if (!liti_litigation_result.equals("NA")) {
				sql += " AND liti_litigation_result='" + liti_litigation_result + "'";
			}
			if (!liti_case_from.equals("") && !liti_case_to.equals("")) {
				liti_case_from = sdfOut.format(sdfIn.parse(liti_case_from));
				liti_case_to = sdfOut.format(sdfIn.parse(liti_case_to));
				sql += " AND liti_case_filing_date BETWEEN '" + liti_case_from + "' AND '" + liti_case_to + "'";
			}
			if (!liti_hearing_date_from.equals("") && !liti_hearing_date_to.equals("")) {
				liti_hearing_date_from = sdfOut.format(sdfIn.parse(liti_hearing_date_from));
				liti_hearing_date_to = sdfOut.format(sdfIn.parse(liti_hearing_date_to));
				hear_date_sql = "SELECT DISTINCT out_data.ttrn_litigation_id from trn_hearing_stages as out_data INNER JOIN (SELECT MAX(date(ttrn_next_hearing_date)) as next_date,ttrn_litigation_id FROM trn_hearing_stages as inn_join group by inn_join.ttrn_litigation_id ) as i ON out_data.ttrn_litigation_id = i.ttrn_litigation_id where next_date BETWEEN '"
						+ liti_hearing_date_from + "' AND '" + liti_hearing_date_to + "'";
				sql += " AND liti_id IN(" + hear_date_sql + ") ";
			}
			if (!liti_criticality.equals("NA")) {
				sql += " AND liti_criticality='" + liti_criticality + "'";
			}
			
			
			/*if(liti_internal_liti_code != 0){
				sql += " AND liti_internal_liti_code = "+liti_internal_liti_code;
			}*/
		
			
			
			if (Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 2
					&& user_id != liti_assigned_to) {

				if (liti_assigned_to > 0) {
					sql += " AND (liti_assigned_to IN(select user_id from mst_user where user_report_to="
							+ user_id + " AND user_id = " + liti_assigned_to + " ) ";
					if (report_to == liti_assigned_to) {
						String check_assigned = "select count(liti_id) from mst_litigation where liti_added_by ="
								+ user_id + " AND liti_added_by=" + report_to;
						Query query = em.createNativeQuery(check_assigned);
						int count = query.getFirstResult();
						// System.out.println(" COunt "+count);
						if (count != 0)
							sql += "  OR liti_added_by=" + report_to;
					}
					sql += " )";
				} else {
					sql += " AND (liti_added_by=" + user_id + " OR liti_added_by="
							+ user_id
							+ " OR liti_added_by IN(select DISTINCT user_id from mst_user where user_report_to="
							+ user_id
							+ ") OR liti_added_by IN(select DISTINCT user_id from mst_user where user_report_to="
							+ user_id + "))";
				}
			} else {
				if (liti_assigned_to > 0) {
					sql += " AND liti_assigned_to = " + liti_assigned_to;
				}
				
				if (liti_secondary_responsible > 0) {
					sql += " AND liti_secondary_responsible = " + liti_secondary_responsible;
				}
				
				if (liti_third_responsible_person > 0) {
					sql += " AND liti_third_responsible_person = " + liti_third_responsible_person;
				}
			}

			
			String sql_two = "select * from mst_litigation where liti_orga_id =" + orga_id;

			/*
			 * if (loca_id != 0) { sql_two += " AND liti_loca_id=" + loca_id; } if (dept_id
			 * != 0) { sql_two += " AND liti_dept_id=" + dept_id; }
			 */
			if (liti_category != 0) {
				sql_two += " AND liti_type_id=" + liti_category;
			}

			if (!liti_by_against.equals("NA")) {
				sql_two += " AND liti_against_by_id='" + liti_by_against + "'";
			}
			if (!liti_litigation_result.equals("NA")) {
				sql_two += " AND liti_status='" + liti_litigation_result + "'";
			}

			if (!liti_case_from.equals("") && !liti_case_to.equals("")) {
				liti_case_from = sdfOut.format(sdfIn.parse(liti_case_from));
				liti_case_to = sdfOut.format(sdfIn.parse(liti_case_to));
				sql_two += " AND liti_case_filing_date BETWEEN '" + liti_case_from + "' AND '" + liti_case_to + "'";
			}
			if (!liti_hearing_date_from.equals("") && !liti_hearing_date_to.equals("")) {
				liti_hearing_date_from = sdfOut.format(sdfIn.parse(liti_hearing_date_from));
				liti_hearing_date_to = sdfOut.format(sdfIn.parse(liti_hearing_date_to));
				hear_date_sql = "SELECT DISTINCT out_data.ttrn_litigation_id from trn_hearing_stages as out_data INNER JOIN (SELECT MAX(date(ttrn_next_hearing_date)) as next_date,ttrn_litigation_id FROM trn_hearing_stages as inn_join group by inn_join.ttrn_litigation_id ) as i ON out_data.ttrn_litigation_id = i.ttrn_litigation_id where next_date BETWEEN '"
						+ liti_hearing_date_from + "' AND '" + liti_hearing_date_to + "'";
				sql_two += " AND liti_id IN(" + hear_date_sql + ") ";
			}
			if (!liti_criticality.equals("NA")) {
				sql_two += " AND liti_criticality='" + liti_criticality + "'";
			}
			/*if(liti_internal_liti_code != 0){
				sql_two += " AND liti_internal_liti_code = "+liti_internal_liti_code;
			}*/
			if (Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 2
					&& user_id != liti_assigned_to) {

				if (liti_assigned_to > 0) {
					sql_two += " AND (liti_assigned_to = " + liti_assigned_to
							+ "  AND liti_added_by =" + user_id + ")";

				} else {
					sql_two += " AND (liti_assigned_to=" + user_id
							+ " OR liti_added_by=" + user_id
							+ " OR liti_assigned_to IN(select DISTINCT user_id from mst_user where user_report_to="
							+ user_id
							+ ") OR liti_added_by IN(select DISTINCT user_id from mst_user where user_report_to="
							+ user_id + "))";
				}
			} else {
				if (liti_assigned_to > 0) {
					sql_two += " AND liti_assigned_to = " + liti_assigned_to;
				}
				
				if (liti_secondary_responsible > 0) {
					sql_two += " AND liti_secondary_responsible = " + liti_secondary_responsible;
				}
				
				if (liti_third_responsible_person > 0) {
					sql_two += " AND liti_third_responsible_person = " + liti_third_responsible_person;
				}
			}

			String sql_three = "select * from mst_litigation where liti_orga_id =" + orga_id;

			/*
			 * if (loca_id != 0) { sql_three += " AND liti_loca_id=" + loca_id; } if
			 * (dept_id != 0) { sql_three += " AND liti_dept_id=" + dept_id; }
			 */
			if (liti_category != 0) {
				sql_three += " AND liti_type_id=" + liti_category;
			}

			if (!liti_by_against.equals("NA")) {
				sql_three += " AND liti_against_by_id='" + liti_by_against + "'";
			}
			if (!liti_litigation_result.equals("NA")) {
				sql_three += " AND liti_litigation_result='" + liti_litigation_result + "'";
			}

			if (!liti_case_from.equals("") && !liti_case_to.equals("")) {
				liti_case_from = sdfOut.format(sdfIn.parse(liti_case_from));
				liti_case_to = sdfOut.format(sdfIn.parse(liti_case_to));
				sql_three += " AND liti_case_filing_date BETWEEN '" + liti_case_from + "' AND '" + liti_case_to + "'";
			}
			if (!liti_hearing_date_from.equals("") && !liti_hearing_date_to.equals("")) {
				liti_hearing_date_from = sdfOut.format(sdfIn.parse(liti_hearing_date_from));
				liti_hearing_date_to = sdfOut.format(sdfIn.parse(liti_hearing_date_to));
				hear_date_sql = "SELECT DISTINCT out_data.ttrn_litigation_id from trn_hearing_stages as out_data INNER JOIN (SELECT MAX(date(ttrn_next_hearing_date)) as next_date,ttrn_litigation_id FROM trn_hearing_stages as inn_join group by inn_join.ttrn_litigation_id ) as i ON out_data.ttrn_litigation_id = i.ttrn_litigation_id where next_date BETWEEN '"
						+ liti_hearing_date_from + "' AND '" + liti_hearing_date_to + "'";
				sql_three += " AND liti_id IN(" + hear_date_sql + ") ";
			}
			if (!liti_criticality.equals("NA")) {
				sql_three += " AND liti_criticality='" + liti_criticality + "'";
			}
			/*if(liti_internal_liti_code != 0){
				sql_three += " AND liti_internal_liti_code = "+liti_internal_liti_code;
			}*/
			if (Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 2
					&& user_id != liti_assigned_to) {

				if (liti_assigned_to > 0) {
					sql_three += " AND (liti_assigned_to = " + liti_assigned_to
							+ "  AND liti_added_by IN(select DISTINCT user_id from mst_user where user_report_to="
							+ user_id + "))";

				} else {
					sql_three += " AND (liti_assigned_to_id=" + user_id
							+ " OR liti_added_by=" + user_id
							+ " OR liti_assigned_to_id IN(select DISTINCT user_id from mst_user where user_report_to="
							+ user_id
							+ ") OR liti_added_by IN(select DISTINCT user_id from mst_user where user_report_to="
							+ user_id + "))";
				}
			} else {
				if (liti_assigned_to > 0) {
					sql_three += " AND liti_assigned_to = " + liti_assigned_to;
				}
				
				if (liti_secondary_responsible > 0) {
					sql_three += " AND liti_secondary_responsible = " + liti_secondary_responsible;
				}
				
				if (liti_third_responsible_person > 0) {
					sql_three += " AND liti_third_responsible_person = " + liti_third_responsible_person;
				}
			}
			Query query = em.createNativeQuery(sql + " UNION " + sql_two + " UNION " + sql_three, Litigation.class);
			
			/*
			 * System.out.println("sql1:"+ sql); System.out.println("sql2:"+ sql_two);
			 * System.out.println("sql3:"+ sql_three);
			 */
			
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Harshad Padole
	// Method Purpose: Get latest next hearing date
	@Override
	public String getLatestNextHearingStageDate(int liti_id) {
		try {
			String sql = "SELECT ttrn_next_hearing_date FROM trn_hearing_stages where ttrn_litigation_id ="
					+ liti_id + " ORDER BY ttrn_hearing_stage DESC offset 1 rows fetch next 1 rows only";
			Query query = em.createNativeQuery(sql);
			if (query.getResultList().size() > 0 && query.getResultList().get(0) != null) {
				return query.getResultList().get(0).toString();
			} else {
				return "NA";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : save counsel fees
	@Override
	public int saveCounselFees(LitigationCounselFees counselFees) {
		try {
			em.persist(counselFees);
			em.flush();
			return counselFees.getLcou_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method Purpose : get counsel fees details by litigation id
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getCounselFeesDeatilsByLitiId(int liti_id) {
		try {
			String sql = "SELECT lcou_id,exte_coun_name,lcou_type_of_fees,lcou_effective_non_effective,ROUND(lcou_counsel_fees_amount,2),lcou_comments,lcou_currency,lcou_counsel_added_by FROM trn_litigation_counsel_fees JOIN mst_external_counsel ON exte_coun_id = lcou_counsel_id WHERE lcou_liti_id="
					+ liti_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : get only list of user who handle the litigation matters
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getMatterHandleByUsers() {
		try {
			String sql = "SELECT DISTINCT user_id,user_first_name, user_last_name FROM mst_user JOIN mst_litigation ON liti_internally_handled_by = user_id";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : get Counsel as per Law firm
	@SuppressWarnings("unchecked")
	@Override
	public List<ExternalCounsel> getConselAsPerLawFirm(int law_firm_id) {
		try {
			String sql = "select * from mst_external_counsel where exte_coun_law_firm =" + law_firm_id;
			Query query = em.createNativeQuery(sql, ExternalCounsel.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : get counsel and law firm
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getLawFirmExternalCounsel(int liti_id) {
		try {
			String sql = "SELECT exte_coun_id,exte_coun_name,lawf_id,lawf_name FROM mst_litigation join mst_external_counsel ON exte_coun_id = liti_external_counsel_id JOIN mst_law_firm ON lawf_id = exte_coun_law_firm where liti_id ="
					+ liti_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get Advocate fees details
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getAdvocateFeesDeatilsByLitiId(int liti_id) {
		try {
			String sql = "SELECT advo_id,advo_name,ladv_type_of_fees,ladv_effective_non_effective,ROUND(ladv_advocate_fees_amount,2),ladv_comments,ladv_litigation_id,ladv_id,ladv_currency, ladv_added_by FROM trn_litigation_advocate_fees JOIN mst_advocate ON advo_id = ladv_advocate_id where ladv_litigation_id ="
					+ liti_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get Counsel fees details to update
	@Override
	public LitigationCounselFees getCounselFeesDetailsById(int lcou_id) {
		try {
			String sql = "select * from trn_litigation_counsel_fees where lcou_id=" + lcou_id;
			Query query = em.createNativeQuery(sql, LitigationCounselFees.class);
			return (LitigationCounselFees) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get Advocate fees details to update
	@Override
	public LitigationAdvocateFees getAdvocateFeesDetailsById(int ladv_id) {
		try {
			String sql = "select * from trn_litigation_advocate_fees where ladv_id =" + ladv_id;
			Query query = em.createNativeQuery(sql, LitigationAdvocateFees.class);
			return (LitigationAdvocateFees) query.getResultList().get(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : update object
	@Override
	public void merge(Object object) {
		try {
			em.merge(object);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created : Harshad Padole
	// Method Purpose : Get all Litigation As per access and role
	@Override
	public <T> List<T> getAllLitigationAccessWise(Class<T> clazz, HttpSession session) {
		try {
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			TypedQuery<T> query = em.createQuery(" from " + clazz.getName() + " where (liti_internally_handled_by="
					+ user_id
					+ " OR liti_internally_handled_by IN(select DISTINCT user_id from mst_user where user_report_to="
					+ user_id + ") OR liti_added_by IN(select DISTINCT user_id from mst_user where user_report_to="
					+ user_id + ") OR liti_added_by=" + user_id + ")", clazz);
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getAllListLitigation() {
		try {
			String sql = "SELECT liti_id,liti_against_by_id,liti_type_name,liti_case_reference_no,liti_case_filing_date,exte_coun_name, user_first_name, user_last_name AS liti_internally_handled_name, liti_opposite_party,liti_court, liti_amount_involved, liti_relevant_law, liti_brief_description,liti_status,liti_litigation_result,liti_disposal_date,liti_synopsis_of_order,liti_last_date_for_filling_appeal,liti_comments,liti_external_counsel_id, advo_id, advo_name,court_name, liti_completion_court_id, liti_client_liti_id,liti_intenal_person "
					+ " FROM mst_litigation join mst_litigation_type on mst_litigation_type.liti_type_id = mst_litigation.liti_type_id join mst_user on user_id = liti_internally_handled_by join mst_external_counsel on exte_coun_id = liti_external_counsel_id join mst_advocate on mst_advocate.advo_id = liti_advocate_id join mst_court ON court_id = liti_court ";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllListLitigationAccessWise(HttpSession session) {
		try {
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			String sql = "SELECT liti_id, liti_against_by_id, liti_type_name, liti_case_reference_no, liti_case_filing_date, liti_external_counsel_id, user_first_name, user_last_name AS liti_internally_handled_name,"
					+ " liti_party_by, liti_court, liti_amount_involved, liti_relevant_law, liti_brief_description, liti_status, liti_litigation_result, liti_disposal_date, liti_synopsis_of_order,"
					+ " liti_last_date_for_filling_appeal, liti_comments, advo_id, advo_name, court_name, liti_completion_court_id, liti_client_liti_id, liti_case_title, liti_intenal_person, liti_added_by, "
					+ " liti_oppo_party_address, liti_oppo_advo_law_firm, liti_opposite_party_advocate, liti_oppo_advo_contact, liti_jurisdiction_name, liti_party_against, liti_assigned_to, liti_secondary_responsible,  liti_third_responsible_person, liti_updated_by,"
					+ " liti_updated_at, concat(user_first_name, ' ' ,user_last_name) "
					+ " FROM mst_litigation"
					+ " LEFT join mst_litigation_type on mst_litigation_type.liti_type_id = mst_litigation.liti_type_id "
					+ " LEFT join mst_user on user_id = liti_updated_by "
					//+ " join mst_external_counsel on exte_coun_id = liti_external_counsel_id "
					+ " LEFT join mst_advocate on advo_id = liti_advocate_id " 
					+ " LEFT join mst_court ON court_id = liti_court order by liti_id";
			if (Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 2) {
				sql += "where (liti_added_by = " + user_id + " OR liti_assigned_to=" + user_id
						+ " OR liti_assigned_to IN(select DISTINCT user_id from mst_user where user_report_to="
						+ user_id + ") OR liti_added_by IN(select DISTINCT user_id from mst_user where user_report_to="
						+ user_id + ") )";
			}
			System.out.println("Listliti  "+sql);
			Query query = em.createNativeQuery(sql);
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get paid fees details by counsel fees id(primary key)
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getPaidFeesDetailsByConFeesId(int counsel_fees_id) {
		try {
			String sql = "select * from trn_counsel_paid_fees where cpaid_counsel_fees_id= " + counsel_fees_id;
			Query query = em.createNativeQuery(sql, CounselPaidFees.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getPaidFeesDetailsByAdvocateFeesId(int advocate_fees_id) {
		try {
			String sql = "select * from trn_advocate_paid_fees where apaid_advocate_fees_id= ?";
			Query query = em.createNativeQuery(sql, AdvocatePaidFees.class);
			query.setParameter(1, advocate_fees_id);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get paid advocate fees by id(primary key)
	@Override
	public AdvocatePaidFees getAdvocatePaidFeesById(int apaid_id) {
		try {
			String sql = "Select * from trn_advocate_paid_fees where apaid_id= " + apaid_id;
			Query query = em.createNativeQuery(sql, AdvocatePaidFees.class);
			if (!(query.getResultList()).isEmpty()) {
				return (AdvocatePaidFees) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get counsel paid fees by id(primary key)
	@Override
	public CounselPaidFees getCounselPaidFeesById(int cpaid_id) {
		try {
			String sql = "Select * from trn_counsel_paid_fees where cpaid_id = " + cpaid_id;
			Query query = em.createNativeQuery(sql, CounselPaidFees.class);
			if (!(query.getResultList()).isEmpty()) {
				return (CounselPaidFees) query.getResultList().get(0);
			}
		} catch (Exception e) {

		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get hearing stage logs (primary key)
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getHearingStageLogs(String json) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
			int stage_id = Integer.parseInt(jsonObject.get("hearing_stage_id").toString());

			String sql = "SELECT ttrn_next_hearing_date,ttrn_first_alert,ttrn_second_alert,ttrn_third_alert,ttrn_stage_description,ttrn_hearing_stage,stage_name,ttrn_responsible_person,ttrn_counsel_id,exte_coun_name,ttrn_added_by, hearing_stage_log_status "
					+ " FROM trn_hearing_stage_log JOIN mst_stages ON stage_id = ttrn_stage_id "
					+ " JOIN mst_external_counsel ON exte_coun_id = ttrn_counsel_id where ttrn_hearing_stage= "
					+ stage_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : delete litigation
	@Override
	public int deleteLitigation(int liti_id) {
		try {
			String sql_one = "Delete from mst_litigation where liti_id =" + liti_id;
			String sql_two = "Delete from trn_litigation_documents where ldoc_liti_id =" + liti_id;
			String sql_three = "Delete from trn_hearing_stage_documents where hear_stage_id IN(Select ttrn_hearing_stage from trn_hearing_stages where ttrn_litigation_id = "
					+ liti_id + ")";
			String sql_four = "Delete from trn_hearing_stages where ttrn_litigation_id =" + liti_id;
			String sql_five = "Delete from trn_liti_fees_documents where fdoc_fees_id IN(select lcou_id from trn_litigation_counsel_fees where lcou_liti_id = "
					+ liti_id + " ) AND fdoc_document_type ='AAC' ";
			String sql_six = "Delete from trn_liti_fees_documents where fdoc_fees_id IN (select ladv_id from trn_litigation_advocate_fees where ladv_litigation_id= "
					+ liti_id + ") AND fdoc_document_type ='AAA'";
			String sql_seven = "Delete from trn_litigation_counsel_fees where lcou_liti_id =" + liti_id;
			String sql_eight = "Delete from trn_litigation_advocate_fees where ladv_litigation_id =" + liti_id;
			Query query1 = em.createNativeQuery(sql_one);
			Query query2 = em.createNativeQuery(sql_two);
			Query query3 = em.createNativeQuery(sql_three);
			Query query4 = em.createNativeQuery(sql_four);
			Query query5 = em.createNativeQuery(sql_five);
			Query query6 = em.createNativeQuery(sql_six);
			Query query7 = em.createNativeQuery(sql_seven);
			Query query8 = em.createNativeQuery(sql_eight);
			query1.executeUpdate();
			query2.executeUpdate();
			query3.executeUpdate();
			query3.executeUpdate();
			query4.executeUpdate();
			query5.executeUpdate();
			query6.executeUpdate();
			query7.executeUpdate();
			query8.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : check whether paid amount greater than agreed amount or
	// not
	@Override
	public int isCounselPaidFeesGreaterThanAgreedFees(int cpaid_fees_amount, int cpaid_counsel_fees_id) {
		try {
			String sql = "SELECT DISTINCT CASE WHEN ((select lcou_counsel_fees_amount from trn_litigation_counsel_fees where lcou_id = "
					+ cpaid_counsel_fees_id + ") - (Select SUM(cpaid_fees_amount)+" + cpaid_fees_amount
					+ " as total from trn_counsel_paid_fees where cpaid_counsel_fees_id = " + cpaid_counsel_fees_id
					+ ") < 0) " + " THEN 1 ELSE 0 END as result FROM trn_litigation_counsel_fees ";
			Query query = em.createNativeQuery(sql);
			//System.out.println("Query "+sql);
			String result = query.getResultList().get(0).toString();
			//System.out.println("RESULT "+result);
			return Integer.parseInt(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : check whether paid amount greater than agreed amount or
	// not
	@Override
	public int isAdvocatePaidFeesGreaterThanAgreedFees(int apaid_fees_amount, int apaid_advocate_fees_id) {
		try {
			String sql = " SELECT DISTINCT CASE WHEN ((select ladv_advocate_fees_amount from trn_litigation_advocate_fees where ladv_id = "+apaid_advocate_fees_id+") - (Select SUM(apaid_fees_amount)+"+apaid_fees_amount+" as total from trn_advocate_paid_fees where apaid_advocate_fees_id = "+apaid_advocate_fees_id+") < 0) "
					+ " THEN 1 ELSE 0 END as result FROM trn_litigation_advocate_fees ";
			Query query = em.createNativeQuery(sql);
			String result = query.getResultList().get(0).toString();
			return Integer.parseInt(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : delete Hearing Stage
	@Override
	public int deleteHearingStage(int hear_stage_id) {
		try {
			String sql_one = "Delete from trn_hearing_stage_documents where hear_stage_id ="+ hear_stage_id;
			String sql_two = "Delete from trn_hearing_stages where ttrn_hearing_stage =" + hear_stage_id;
			Query query2 = em.createNativeQuery(sql_two);
			Query query1 = em.createNativeQuery(sql_one);
			query2.executeUpdate();
			query1.executeUpdate();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getCounselByHearingStageId(int hearing_stage_id) {
		try {
			String sql = "select hsco_id,hsco_counsel_law_firm_id,lawf_name,hsco_counsel_id,exte_coun_name from trn_hearing_stage_counsel JOIN mst_external_counsel ON exte_coun_id=hsco_counsel_id JOIN mst_law_firm ON lawf_id = hsco_counsel_law_firm_id where hsco_hearing_stage_id=?";
			Query query = em.createNativeQuery(sql);
			query.setParameter(1, hearing_stage_id);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteHearingCounselById(int hsco_id) {
		try {
			String sql = "Delete from trn_hearing_stage_counsel where hsco_id=?";
			Query query = em.createNativeQuery(sql);
			query.setParameter(1, hsco_id);
			return query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public <T> List<T> getAllPendingLitigationClientId(Class<T> clazz) {
		try {
			TypedQuery<T> query = em.createQuery(" from " + clazz.getName() +" where liti_status ='Completed'", clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getpreviouslitiId(String liti_id) {
		try {
			String sql = " SELECT liti_id FROM mst_litigation where liti_client_liti_id = '"+liti_id+"'";
			Query query = em.createNativeQuery(sql);
			if(!query.getResultList().isEmpty()){
			String result = query.getResultList().get(0).toString();
			return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Object obj) {
	try {
			em.merge(obj);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public LitigationEscalationMailId getEscalationMailByLitiId(int liti_id) {
		try {
			String sql = "Select * from trn_escalation_mail_id where esc_liti_id ="+liti_id;
			Query query = em.createNativeQuery(sql, LitigationEscalationMailId.class);
			if (!query.getResultList().isEmpty()) {
				return (LitigationEscalationMailId) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int persistHearingStageOnHearing(HearingStage hearingStage) {
		try {
			em.persist(hearingStage);
			em.flush();
			return hearingStage.getTtrn_hearing_stage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	//Method created	:	Tejashri Zurunge
	//Method purpose	:	Get Hearing stage details before approval
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getHearingDetailsOnHearingDay(int liti_id) {
		try {
			String sql = "SELECT ttrn_next_hearing_date,ttrn_first_alert,ttrn_second_alert,ttrn_third_alert,ttrn_stage_description"
					+ ",ttrn_hearing_stage,ttrn_stage_id,ttrn_responsible_person,ttrn_counsel_id,ttrn_added_by, ttrn_stage_status FROM trn_hearing_stages where ttrn_stage_status = 'Waiting for Approval' AND ttrn_litigation_id="+ liti_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HearingStageOnHearing getHearingOnHearingDetailsById(int hear_id) {
		try {
			String sql = "SELECT * FROM trn_hearing_stages_onhearing_status where trn_hearing_id="+ hear_id;
			Query query = em.createNativeQuery(sql, HearingStageOnHearing.class);
			return (HearingStageOnHearing) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unused")
	@Override
	public List<Object> acmLitigation(String json, HttpSession session) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
			SimpleDateFormat sdfIn = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdfOut = new SimpleDateFormat("yyyy-MM-dd");
			String from_date = jsonObject.get("liti_acm_from").toString();
			String to_date = jsonObject.get("liti_acm_to").toString();
			from_date = sdfOut.format(sdfIn.parse(from_date));
			to_date = sdfOut.format(sdfIn.parse(to_date));
			

		
		String sql ="SELECT distinct  (SELECT COUNT(*) FROM mst_litigation WHERE liti_amount_involved <= 10 and liti_status='Pending') as liti_A,"
			   + "(SELECT COUNT(*) FROM mst_litigation WHERE liti_amount_involved >= 10 and liti_status='Pending') as B," 
			   + "(SELECT SUM(liti_amount_involved) FROM mst_litigation  WHERE liti_amount_involved <= 10 and liti_status='Pending') as 'Amount Less Than 10 Lakh'," 
			   + "(SELECT SUM(liti_amount_involved) FROM mst_litigation WHERE liti_amount_involved >= 10 and liti_status='Pending') as 'Amount Grater Than 10 Lakh'" 
			   + ", (SELECT COUNT(*) FROM mst_litigation WHERE liti_status='Pending') as Total_Case,(SELECT sum(liti_amount_involved) mst_litigation WHERE liti_status='Pending') as Total_Case_Amount,(SELECT COUNT(*) FROM mst_litigation WHERE liti_amount_involved <= 10 and liti_status='Pending' and liti_criticality='Possible') as Possible_count_lessthan_10l	,"  
			   + "(SELECT COUNT(*) FROM mst_litigation WHERE liti_amount_involved >= 10 and liti_status='Pending' and liti_criticality='Possible') as Possible_count_morethan_10l," 
			   + "(SELECT COUNT(*) FROM mst_litigation WHERE liti_status='Pending'and liti_criticality='Possible') as Possible_Total_case,(SELECT SUM(liti_amount_involved) FROM mst_litigation  WHERE liti_amount_involved <= 10 and liti_status='Pending'and liti_criticality='Possible') as 'Possible Amount Less Than 10 Lakh'," 
			   + "(SELECT SUM(liti_amount_involved) FROM mst_litigation WHERE liti_amount_involved >= 10 and liti_status='Pending'and liti_criticality='Possible') as 'Possible Amount Grater Than 10 Lakh'," 
			   + "(SELECT sum(liti_amount_involved) FROM mst_litigation WHERE liti_status='Pending' and liti_criticality='Possible' ) as Possible_Total_Amount,(SELECT COUNT(*) FROM mst_litigation WHERE liti_amount_involved <= 10 and liti_status='Pending' and liti_criticality !='Possible') as Without_Possible_count_lessthan_10," 
			   + "(SELECT COUNT(*) FROM mst_litigation WHERE liti_amount_involved >= 10 and liti_status='Pending' and liti_criticality !='Possible') as Without_Possible_count_morethan_10l," 
			   + "(SELECT COUNT(*) FROM mst_litigation WHERE liti_status='Pending'and liti_criticality !='Possible') as Without_Possible_Total_case"
			   + "	FROM mst_litigation  where liti_created_at between '" + from_date + "' AND '" + to_date + "'";
		System.out.println(sql);
		Query query = em.createNativeQuery(sql);
		return query.getResultList();
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

	public String getUserFullName(int user_id) {
		String sql = "SELECT concat(user_first_name,' ',user_last_name)as UserFullName from mst_user  where user_id="+ user_id+" ";
		Query query = em.createNativeQuery(sql);
		return  (String) query.getResultList().get(0);
	}
	
	

}
