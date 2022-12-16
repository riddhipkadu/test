package lcmt.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lcmt.dao.SchedulerDao;
import lcmt.domain.LitigationEscalationMailId;
import lcmt.domain.TransactionalActionItem;
import lcmt.domain.User;
import lcmt.service.UtilitiesService;

@Repository(value = "schedulerDao")
@Transactional
public class SchedulerDaoImpl implements SchedulerDao {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	UtilitiesService utilitiesService;

	// Method created by : Tejashri Zurunge
	// Method purpose : get assign person according to reminder date
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAssignPersonFromReminderDate() {
		try {
			String sql = "SELECT distinct quer_assigned_to FROM mst_query WHERE quer_reminder_date = CURDATE() AND quer_status !='Draft'";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created by : Tejashri Zurunge
	// Method purpose : get query assigned to person
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getReminderQueryMailToAssignPerson(int assign_to) {
		try {
			String sql = "SELECT orga_name,loca_name,dept_name,assin.user_first_name, assin.user_last_name as assigned_to,assin.user_email as ass_user_mail, quer_from_id ,quer.quer_query_date,quer.quer_reply_date,quer.quer_reminder_date, quer.quer_id, quer.quer_query FROM mst_query quer "
					+ " JOIN mst_organization ON orga_id = quer.quer_entity_id "
					+ " JOIN mst_location ON mst_location.loca_id = quer.quer_unit_id "
					+ " JOIN mst_department ON dept_id = quer.quer_function_id "
					+ " JOIN mst_user assin ON assin.user_id = quer.quer_assigned_to WHERE quer_reminder_date = CURDATE()"
					+ " AND quer.quer_assigned_to = " + assign_to;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created by : Tejashri Zurunge
	// Method purpose : get assign person according to alert date
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAssignPersonFromAlertDate() {
		try {
			String sql = "select distinct ttrn_responsible_person from trn_hearing_stages where ttrn_stage_status!='Draft' AND ttrn_first_alert = CURDATE() or ttrn_second_alert = CURDATE() ";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created by : Tejashri Zurunge
	// Method purpose : get hearing stage details assigned to person
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAlertHearingStageMailToAssignPerson(int resp_person_id) {
		try {
			String sql = "SELECT ttrn_next_hearing_date,ttrn_first_alert,ttrn_second_alert,ttrn_third_alert,ttrn_stage_description,ttrn_hearing_stage,ttrn_responsible_person, user_first_name, user_last_name as responsiblePerson, user_email , ttrn_litigation_id FROM trn_hearing_stages Join mst_user on user_id = ttrn_responsible_person"
					+ " where ttrn_responsible_person = " + resp_person_id
					+ " AND ttrn_stage_status!='Draft' AND (ttrn_first_alert = CURDATE() OR ttrn_second_alert = CURDATE() OR ttrn_third_alert = CURDATE())";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created by : Tejashri Zurunge
	// Method purpose : get assign person according to alert date
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAssignPersonLegalNoticeFromReminderDate() {
		try {
			String sql = "select distinct lega_noti_assigned_to_id from mst_legal_notice where lega_noti_reminder_date = CURDATE() AND lega_noti_status !='Draft' ";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created by : Tejashri Zurunge
	// Method purpose : get legal notice details assigned to person
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getReminderLegalNoticeMailToAssignPerson(int matter_handled_by_id) {
		try {
			String sql = "SELECT orga.orga_name,loca.loca_name,dept.dept_name,leg_cat.lega_noti_category_name,lega_noti.lega_noti_id,CONVERT(varchar(12),lega_noti.lega_noti_dated,105) lega_noti_dated,CONVERT(varchar(12),lega_noti.lega_noti_recived_on,105) lega_noti_recived_on,CONVERT(varchar(12),lega_noti.lega_noti_reply_deadline,105) lega_noti_reply_deadline, usr.user_first_name, usr.user_last_name as fullname, usr.user_email as email, CONVERT(varchar(12),lega_noti.lega_noti_reminder_date,105) lega_noti_reminder_date FROM mst_legal_notice lega_noti JOIN mst_organization orga ON orga.orga_id = lega_noti.lega_noti_entity_id  "
					+ " JOIN mst_location loca ON loca.loca_id = lega_noti.lega_noti_unit_id "
					+ " JOIN mst_department  dept ON dept.dept_id = lega_noti.lega_noti_function_id "
					+ " JOIN mst_user usr ON usr.user_id = lega_noti.lega_noti_assigned_to_id"
					+ " JOIN mst_legal_notice_category leg_cat ON leg_cat.lega_noti_category_id = lega_noti.lega_noti_category_id"
					+ " where lega_noti.lega_noti_reminder_date = CURDATE() AND lega_noti.lega_noti_assigned_to_id = "
					+ matter_handled_by_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAssignedPersonLegalnoticeStatus() {
		try {
			String sql = "select DISTINCT lega_person_responsible from trn_legal_notice_status where lega_reminder_date =  CURDATE() AND lega_notice_status !='Draft'";
			//System.out.println(" query 1 "+sql);
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getReminderLegalNoticeStatusMailToAssignPerson(int id) {
		try {
			String sql = "SELECT orga.orga_name,loca.loca_name,dept.dept_name,lega_noti.lega_noti_id,lega_noti.lega_noti_reply_deadline,usr.user_first_name,usr.user_last_name, usr.user_email,lega_status.lega_action_taken,lega_status.lega_next_action_item,lega_status.lega_action_item_due_date,lega_status.lega_reminder_date,lega_status.lega_received_date,lega_status.lega_status_id FROM mst_legal_notice lega_noti JOIN mst_organization orga ON orga.orga_id = lega_noti.lega_noti_entity_id  "
					+ " JOIN mst_location loca ON loca.loca_id = lega_noti.lega_noti_unit_id "
					+ " JOIN mst_department  dept ON dept.dept_id = lega_noti.lega_noti_function_id "
					+ " JOIN trn_legal_notice_status lega_status ON lega_notice_id = lega_noti.lega_noti_id"
					+ " JOIN mst_user usr ON usr.user_id = lega_person_responsible"
					+ " where lega_status.lega_reminder_date = CURDATE() AND lega_status.lega_person_responsible ="+id;
			//System.out.println(" query 2 "+sql);
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getLitigationDetailsForReport() {
		try {
			String sql = "select DISTINCT liti_id,orga_name,loca_name,dept_name,liti_against_by_id,liti_company_acting_as,liti_opposite_party,liti_opp_party_acting_as,liti_amount_involved, "
						+" liti_involved_amt_currency,liti_status,liti_created_at FROM mst_litigation "
						+" JOIN mst_organization ON orga_id = liti_orga_id "
						+" JOIN mst_location ON loca_id = liti_loca_id "
						+" JOIN mst_department ON dept_id = liti_dept_id "
						+" where liti_status !='Draft' AND liti_created_at between (SELECT DATE_ADD(DATE_SUB(NOW(),INTERVAL DAYOFMONTH(NOW())-1 DAY),INTERVAL -1 MONTH) as FirstDate ) AND ( SELECT LAST_DAY(NOW() - INTERVAL 1 MONTH) as LastDate) ";
			//System.out.println(" query 2 "+sql);
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllAdminUser() {
		try {
			String sql = "select user_id,user_email from mst_user where user_role_id = 1";
			Query query = em.createNativeQuery(sql);
			//System.out.println(query);
			return query.getResultList();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return null;
	}

	// Method created by : Tejashri Zurunge
	// Method purpose 	 : Send previous month report to admin on every first day of a month
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getQueryDetailsForReport() {
		try {
			String sql = "SELECT orga_name,loca_name,dept_name,assin.user_first_name as assin_fname,assin.user_last_name as assin_lname,quer_from_id,quer.quer_query_date, quer.quer_reply_date, quer.quer_reminder_date, quer.quer_id, quer.quer_query, quer.quer_status, quer.quer_assigned_to "
					+ " FROM mst_query quer " 
					+ " LEFT JOIN mst_organization ON orga_id = quer.quer_entity_id "
					+ " LEFT JOIN mst_location ON mst_location.loca_id = quer.quer_unit_id "
					+ " LEFT JOIN mst_department ON dept_id = quer.quer_function_id "
					+ " LEFT JOIN mst_user assin ON assin.user_id = quer.quer_assigned_to "
					+ " where quer_status !='Draft' AND quer_created_at between (SELECT DATE_ADD(DATE_SUB(NOW(),INTERVAL DAYOFMONTH(NOW())-1 DAY),INTERVAL -1 MONTH) as FirstDate ) AND (SELECT LAST_DAY(NOW() - INTERVAL 1 MONTH) as LastDate)";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created by : Tejashri Zurunge
	// Method purpose 	 : Send previous month report to admin on every first day of a month
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getLegalNoticeDetailsForReport() {
		try {
			String sql = " SELECT orga.orga_name,loca.loca_name,dept.dept_name,lega_noti.lega_noti_id,lega_noti.lega_noti_reply_deadline ,usr.user_first_name, usr.user_last_name, lega_noti.lega_noti_amount_involved,lega_noti.lega_noti_involved_amt_currency , lega_noti.lega_noti_added_by, lega_noti.lega_noti_by_against, lega_noti.lega_noti_reference_no FROM mst_legal_notice lega_noti "
					+ "  JOIN mst_organization orga ON orga.orga_id = lega_noti.lega_noti_entity_id "
					+ "  JOIN mst_location loca ON loca.loca_id = lega_noti.lega_noti_unit_id "
					+ "  JOIN mst_department dept ON dept.dept_id = lega_noti.lega_noti_function_id "
					+ "  JOIN mst_user usr ON usr.user_id = lega_noti.lega_noti_assigned_to_id "
					+ " where lega_noti.lega_noti_status !='Draft' AND lega_noti.lega_noti_created_at between (SELECT DATE_ADD(DATE_SUB(NOW(),INTERVAL DAYOFMONTH(NOW())-1 DAY),INTERVAL -1 MONTH) as FirstDate ) AND (  SELECT LAST_DAY(NOW() - INTERVAL 1 MONTH) as LastDate)";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	// Method created by : Tejashri Zurunge
	// Method purpose 	 : Send previous month report to admin on every first day of a month
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getContractDetailsForReport() {
		try {
			String sql = " SELECT orga_name, loca_name, dept_name, cont_agreement_name, cont_requested_date, cont_expected_date,u.user_first_name , u.user_last_name ,cont_targetted_date,cont_id,cont_status "
						+" FROM mst_contracts LEFT join mst_organization on orga_id = cont_orga_id "
						+ "join mst_location on loca_id = cont_loca_id "
						+ "join mst_department on dept_id = cont_dept_id "
						+ " join mst_user u on u.user_id = cont_responsible_user_id where cont_status != 'Draft' "
						+ " AND cont_created_at between (SELECT DATE_ADD(DATE_SUB(NOW(),INTERVAL DAYOFMONTH(NOW())-1 DAY),INTERVAL -1 MONTH) as FirstDate ) AND (SELECT LAST_DAY(NOW() - INTERVAL 1 MONTH) as LastDate) ";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created by : Tejashri Zurunge
	// Method purpose 	 : Send previous month report to admin on every first day of a month
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getExecutedContractDetailsForReport() {
		try {
			String sql = "SELECT orga_name,loca_name,dept_name,exe.exec_contract_id, cont.cont_type_name, exe.exec_contract_description, exe.exec_contract_date "
					+ "FROM mst_executed_contacts exe "
					+ "JOIN mst_contract_type cont ON cont.cont_type_id = exe.exec_contract_type_id "
					+ "JOIN mst_organization ON orga_id = exe.exec_contract_entity_id "
					+ "JOIN mst_location ON loca_id = exe.exec_contract_unit_id "
					+ "JOIN mst_department ON dept_id = exe.exec_contract_function_id WHERE exec_contract_created_at between (SELECT DATE_ADD(DATE_SUB(NOW(),INTERVAL DAYOFMONTH(NOW())-1 DAY),INTERVAL -1 MONTH) as FirstDate ) AND (  SELECT LAST_DAY(NOW() - INTERVAL 1 MONTH) as LastDate) ";
			System.out.println("getExecutedContractDetailsForReport"+sql);
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAssignPersonTaskCompletionFromReminderDate() {
		try {
			String sql = "Select distinct atrn_resposible_user from ttrn_action_item_transactional where atrn_status = 'Pending' and curdate() = (atrn_action_due_date - interval atrn_first_alert_prior_days day) or curdate() = (atrn_action_due_date - interval atrn_second_alert_prior_days day); ";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getReminderTaskCompletionMailToAssignPerson(int task_assigned_id) {
		try {
		String sql = " select orga_name,loca_name,dept_name,user_first_name, user_last_name, user_email, atrn_resposible_user, exec_action_id, atrn_action_due_date, atrn_frequency, exec_action_item from ttrn_action_item_transactional "
					+ " join ttrn_execute_action_item on atrn_action_id = exec_action_id "
					+ " join mst_executed_contacts exe on atrn_contract_id = exe.exec_contract_id "
					+ " join mst_organization on exe.exec_contract_entity_id = orga_id "
					+ " join mst_location on exe.exec_contract_unit_id = loca_id "
					+ " join mst_department on exe.exec_contract_function_id = dept_id "
					+ " join mst_user on atrn_resposible_user = user_id "
					+ " where atrn_resposible_user = "+task_assigned_id+" and atrn_status = 'Pending' and (curdate() = (atrn_action_due_date - interval atrn_first_alert_prior_days day) or curdate() = (atrn_action_due_date - interval atrn_second_alert_prior_days day)) ";
		Query query = em.createNativeQuery(sql);
		return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created by : Tejashri Zurunge
	// Method purpose 	 : auto activation of task at certain period(frequency)
	@SuppressWarnings("unchecked")
	@Override
	public void autoActivateActionItemTask() {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//get all distinct id 
			String distinctId = "select distinct atrn_action_id from ttrn_action_item_transactional where 1";
			Query query1= em.createNativeQuery(distinctId);
			List<Object> allTaskId = query1.getResultList();
			Iterator<Object> iterator = allTaskId.iterator();
			while (iterator.hasNext()) {
				String taskId = iterator.next().toString();
				String sql1= " SELECT * FROM ttrn_action_item_transactional WHERE atrn_action_due_date < now() AND atrn_action_id = "+taskId+" AND atrn_created_date IN ( SELECT MAX(atrn_created_date) FROM ttrn_action_item_transactional WHERE atrn_action_id = "+ taskId +" ) ";
				Query query2 = em.createNativeQuery(sql1, TransactionalActionItem.class);
				List<TransactionalActionItem> tasks = query2.getResultList();
				Iterator<TransactionalActionItem> iterator2 = tasks.iterator();
				while (iterator2.hasNext()) {
					TransactionalActionItem actionItem = (TransactionalActionItem) iterator2.next();
					Date currentDate = new Date(); 
					if(actionItem.getAtrn_action_due_date().before(currentDate)){
						int flag = 0;
						String due_date = "";
						Date action_due_date = null;
						int action_id = actionItem.getAtrn_id();
						//System.out.println("id is: "+action_id);
						if(actionItem.getAtrn_frequency().equals("Weekly")){
							String sql = "SELECT date_add(atrn_action_due_date, interval 7 day) from ttrn_action_item_transactional where atrn_id = "+action_id;
							Query query = em.createNativeQuery(sql);
							due_date = query.getResultList().get(0).toString();
							//System.out.println("due date weekly is :"+due_date);
							action_due_date = sdfIn.parse(due_date);
							flag = 1;
						}
						else if(actionItem.getAtrn_frequency().equals("Monthly")){
							String sql = "SELECT date_add(atrn_action_due_date, interval 1 month) from ttrn_action_item_transactional where atrn_id = "+action_id;
							Query query = em.createNativeQuery(sql);
							due_date = query.getResultList().get(0).toString();
							//System.out.println("due date monthly is :"+due_date);
							action_due_date = sdfIn.parse(due_date);
							flag = 1;
						}
						else if(actionItem.getAtrn_frequency().equals("Quarterly")){
							String sql = "SELECT date_add(atrn_action_due_date, interval 3 month) from ttrn_action_item_transactional where atrn_id = "+action_id;
							Query query = em.createNativeQuery(sql);
							due_date = query.getResultList().get(0).toString();
							//System.out.println("due date quarterly is :"+due_date);
							action_due_date = sdfIn.parse(due_date);
							flag = 1;
						}
						else if(actionItem.getAtrn_frequency().equals("Half yearly")){
							String sql = "SELECT date_add(atrn_action_due_date, interval 6 month) from ttrn_action_item_transactional where atrn_id = "+action_id;
							Query query = em.createNativeQuery(sql);
							due_date = query.getResultList().get(0).toString();
							//System.out.println("due date half yearly is :"+due_date);
							action_due_date = sdfIn.parse(due_date);
							flag = 1;
						}
						else if(actionItem.getAtrn_frequency().equals("Yearly")){
							String sql = "SELECT date_add(atrn_action_due_date, interval 1 year) from ttrn_action_item_transactional where atrn_id = "+action_id;
							Query query = em.createNativeQuery(sql);
							due_date = query.getResultList().get(0).toString();
							//System.out.println("due date yearly is :"+due_date);
							action_due_date = sdfIn.parse(due_date);
							flag = 1;
						}
						
						if(flag == 1){
							TransactionalActionItem item = new TransactionalActionItem();
							//set old & common records
							item.setAtrn_action_id(actionItem.getAtrn_action_id());
							item.setAtrn_contract_id(actionItem.getAtrn_contract_id());
							item.setAtrn_created_date(utilitiesService.getCurrentDate());
							item.setAtrn_first_alert_prior_days(actionItem.getAtrn_first_alert_prior_days());
							item.setAtrn_second_alert_prior_days(actionItem.getAtrn_second_alert_prior_days());
							item.setAtrn_frequency(actionItem.getAtrn_frequency());
							item.setAtrn_resposible_user(actionItem.getAtrn_resposible_user());
							item.setAtrn_status("Pending");
							//set new records
							item.setAtrn_action_due_date(action_due_date);
							//System.out.println("due date is :"+action_due_date);
							
							em.persist(item);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getActionItemCurrentMonth() {
		try {
			String sql = "select orga_name,loca_name,dept_name,user_first_name, user_last_name, user_email, atrn_resposible_user, exec_action_id, atrn_action_due_date, atrn_frequency, exec_action_item from ttrn_action_item_transactional "
					+ "JOIN ttrn_execute_action_item on atrn_action_id = exec_action_id "
					+ "JOIN mst_executed_contacts exe on atrn_contract_id = exe.exec_contract_id "
					+ "JOIN mst_organization on exe.exec_contract_entity_id = orga_id "
					+ "JOIN mst_location on exe.exec_contract_unit_id = loca_id "
					+ "JOIN mst_department on exe.exec_contract_function_id = dept_id "
					+ "JOIN mst_user on atrn_resposible_user = user_id where MONTH(atrn_action_due_date) = MONTH(current_date()) AND YEAR(atrn_action_due_date) = YEAR(current_date())";
			
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getContractSPOCuser() {
		try {
			@SuppressWarnings("rawtypes")
			TypedQuery query = em.createQuery("FROM "+User.class.getName()+" WHERE user_role_id=4",User.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAssignPersonFrom3rdAlertDate() {
		try {
			String sql = "select distinct ttrn_responsible_person from trn_hearing_stages where ttrn_stage_status!='Draft' AND ttrn_third_alert = CURDATE() ";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> get3rdAlertHearingStageMailToAssignPerson(int matter_handled_by_id) {
		try {
			String sql = "SELECT ttrn_next_hearing_date,ttrn_first_alert,ttrn_second_alert,ttrn_third_alert,ttrn_stage_description,ttrn_hearing_stage,ttrn_responsible_person, user_first_name, user_last_name as responsiblePerson, user_email , ttrn_litigation_id, liti.liti_case_title, liti.liti_court, court.court_name FROM trn_hearing_stages "
					+ " Join mst_user on user_id = ttrn_responsible_person JOIN mst_litigation liti on liti.liti_id = ttrn_litigation_id "
					+ " JOIN mst_court court on court.court_id = liti.liti_court "
					+ " where ttrn_responsible_person = "+ matter_handled_by_id +" AND ttrn_stage_status!='Draft' AND ttrn_third_alert = CURDATE()";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getLitigationSPOCuser() {
		try {
			@SuppressWarnings("rawtypes")
			TypedQuery query = em.createQuery("FROM "+User.class.getName()+" WHERE user_role_id=6",User.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllSPOCuser() {
		try {
			@SuppressWarnings("rawtypes")
			TypedQuery query = em.createQuery("FROM "+User.class.getName()+" WHERE user_role_id = 6 OR user_role_id = 4",User.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getHearingStageOnHearing() {
		try {
			String sql = "SELECT ttrn_litigation_id,ttrn_next_hearing_date, ttrn_first_alert,ttrn_second_alert,ttrn_third_alert,ttrn_stage_description,ttrn_hearing_stage,ttrn_responsible_person FROM trn_hearing_stages "
					+ " where ttrn_stage_status!='Draft' AND ttrn_next_hearing_date = CURDATE()";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LitigationEscalationMailId getLawFirmLitigationMailId(int liti_id) {
		try {
			String sql = "SELECT * FROM `trn_escalation_mail_id` WHERE esc_liti_id = "+liti_id;
			Query query = em.createNativeQuery(sql, LitigationEscalationMailId.class);
			if(!query.getResultList().isEmpty())
			return (LitigationEscalationMailId) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getHearingStageDetailsForReport() {
		try {
			String sql = " select DISTINCT liti_id,orga_name,loca_name,dept_name,liti_type_id,liti_case_reference_no,liti_case_title,liti_brief_description,stages.ttrn_next_hearing_date,liti_status FROM mst_litigation JOIN mst_organization ON orga_id = liti_orga_id JOIN mst_location ON loca_id = liti_loca_id JOIN mst_department ON dept_id = liti_dept_id " 
						+" join trn_hearing_stages stages on ttrn_litigation_id = liti_id where liti_status !='Draft' AND stages.ttrn_next_hearing_date between (SELECT DATE_SUB(NOW(),INTERVAL DAYOFMONTH(NOW())-1 DAY) as FirstDate) AND (SELECT LAST_DAY(NOW()) as LastDate)  ";
		Query query = em.createNativeQuery(sql);
		return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAssignPersonExecutedContract() {
		// TODO Auto-generated method stub
		try {
			String sql = "select distinct exec_contract_resposible_person from mst_executed_contacts where exec_remind_date = CURDATE() OR exec_remind_date_second = CURDATE() OR exec_remind_date_third = CURDATE()";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getReminderContractExpiryAlertToAssignPerson(int assign_to) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT exe.exec_contract_id, cont.cont_type_name,orga_name,loca_name,dept_name, "
					+ "exe.exec_contract_added_by, exe.exec_contract_start_date, user.user_first_name,user.user_last_name, user.user_email, exe.exec_contract_end_date,orga_id"
					+ " FROM mst_executed_contacts exe"
					+ " JOIN mst_contract_type cont ON cont.cont_type_id = exe.exec_contract_type_id "
					+ "	JOIN mst_organization ON orga_id = exe.exec_contract_entity_id "
					+ "	JOIN mst_location ON loca_id = exe.exec_contract_unit_id "
					+ "	JOIN mst_department ON dept_id = exe.exec_contract_function_id "
					+ " JOIN mst_user user ON user.user_id = exe.exec_contract_resposible_person "
					+ " where exec_contract_resposible_person = " + assign_to
					+ " AND (exec_remind_date = CURDATE() OR exec_remind_date_second = CURDATE() OR exec_remind_date_third = CURDATE() )"; 
			System.out.println("Expiry Reminder"+sql);
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllEntityHeadByOrgaId( int orga_id) {
		try {
			String sql = "select DISTINCT user_id,user_email from mst_user where user_organization_id = " +orga_id + " and user_role_id = 7 or user_role_id = 1";
			Query query = em.createNativeQuery(sql);
			//System.out.println("sql:" +sql);
			return query.getResultList();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return null;
	}
	
}
