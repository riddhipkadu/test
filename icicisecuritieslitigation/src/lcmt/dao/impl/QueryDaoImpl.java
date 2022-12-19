package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import lcmt.dao.QueryDao;
import lcmt.domain.QueryDocuments;
import lcmt.domain.QueryHistory;
import lcmt.domain.QueryMaster;

@Repository(value = "queryDao")
@Transactional
public class QueryDaoImpl implements QueryDao {

	@PersistenceContext
	private EntityManager em;

	// Method Created : Harshad Padole
	// Method Purpose : Save query
	@Override
	public int persist(QueryMaster query) {
		try {
			em.persist(query);
			em.flush();
			return query.getQuer_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get List of query
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getListOfQuery(HttpSession session) {
		try {
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			String sql = "SELECT orga_name,loca_name,dept_name,assin.user_first_name as assin_fname,assin.user_last_name as assin_lname,quer.quer_query, quer.quer_reply_date, quer.quer_status, quer.quer_id, quer.quer_added_by, quer.quer_assigned_to FROM mst_query quer "
					+ "LEFT JOIN mst_organization ON orga_id = quer.quer_entity_id "
					+ "LEFT JOIN mst_location ON mst_location.loca_id = quer.quer_unit_id LEFT JOIN mst_department ON dept_id = quer.quer_function_id "
					+ "LEFT JOIN mst_user assin ON assin.user_id = quer.quer_assigned_to ";

			if (Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 2) {
				sql += " where (quer.quer_assigned_to=" + user_id + " OR quer.quer_added_by=" + user_id
						+ " OR quer.quer_assigned_to IN(select DISTINCT user_id from mst_user where user_report_to="
						+ user_id
						+ ") OR quer.quer_added_by IN(select DISTINCT user_id from mst_user where user_report_to="
						+ user_id + "))";
			}
			Query query = em.createNativeQuery(sql);
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get query by id
	@Override
	public QueryMaster getQueryById(int query_id) {
		try {
			String sql = "select * from mst_query where quer_id = " + query_id;
			Query query = em.createNativeQuery(sql, QueryMaster.class);
			if (!query.getResultList().isEmpty()) {
				return (QueryMaster) query.getResultList().get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Update Query
	@Override
	public void updateQuery(QueryMaster master) {
		try {
			em.merge(master);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Search Query
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> searchQuery(int quer_entity_id, int quer_unit_id, int quer_function_id, int quer_assigned_to,
			String from_date, String to_date, HttpSession session) {
		try {
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			int report_to = Integer.parseInt(session.getAttribute("sess_user_report_to").toString());

			String sql = " SELECT orga.orga_name,loca.loca_name,dept.dept_name,assin.user_first_name as assin_fname,assin.user_last_name as assin_lname , "
					+ " quer.quer_reply_date, quer.quer_status, quer.quer_id, quer.quer_query, quer.quer_added_by, quer.quer_assigned_to"
					+ " FROM mst_query quer " + "LEFT JOIN mst_organization orga ON orga.orga_id = quer.quer_entity_id"
					+ " LEFT JOIN mst_location loca ON loca.loca_id = quer.quer_unit_id"
					+ " LEFT JOIN mst_department dept ON dept.dept_id = quer.quer_function_id"
					+ " LEFT JOIN mst_user assin ON assin.user_id = quer.quer_assigned_to where quer.quer_entity_id =  "
					+ quer_entity_id;
			if (quer_unit_id > 0) {
				sql += " AND quer_unit_id = " + quer_unit_id;
			}
			if (quer_function_id > 0) {
				sql += " AND quer.quer_function_id = " + quer_function_id;
			}

			if (!from_date.equals("") && !to_date.equals("")) {
				sql += " AND quer.quer_query_date BETWEEN '" + from_date + "' AND '" + to_date + "'";
			}
			if (Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 2
					&& user_id != quer_assigned_to) {

				if (quer_assigned_to > 0) {
					sql += " AND (quer.quer_assigned_to IN(select user_id from mst_user where user_report_to=" + user_id
							+ " AND user_id = " + quer_assigned_to + ")";
					if (report_to == quer_assigned_to) {
						String check_assigned = "select count(quer_id) from mst_query where quer_added_by =" + user_id
								+ " AND quer_assigned_to=" + report_to;
						Query query = em.createNativeQuery(check_assigned);
						int count = query.getFirstResult();
						// System.out.println(" COunt "+count);
						if (count != 0)
							sql += " OR quer.quer_assigned_to=" + report_to;
					}
					sql += " )";

				} else {
					sql += " AND (quer.quer_assigned_to=" + user_id + " OR quer.quer_added_by=" + user_id
							+ " OR quer.quer_assigned_to IN(select DISTINCT user_id from mst_user where user_report_to="
							+ user_id
							+ ") OR quer.quer_added_by IN(select DISTINCT user_id from mst_user where user_report_to="
							+ user_id + "))";
				}
			} else {
				if (quer_assigned_to > 0) {
					sql += " AND quer.quer_assigned_to = " + quer_assigned_to;
				}
			}

			String sql_two = " SELECT orga.orga_name,loca.loca_name,dept.dept_name,assin.user_first_name as assin_fname,assin.user_last_name as assin_lname , "
					+ " quer.quer_reply_date, quer.quer_status, quer.quer_id, quer.quer_query, quer.quer_added_by, quer.quer_assigned_to"
					+ " FROM mst_query quer " + " JOIN mst_organization orga ON orga.orga_id = quer.quer_entity_id"
					+ " JOIN mst_location loca ON loca.loca_id = quer.quer_unit_id"
					+ " JOIN mst_department dept ON dept.dept_id = quer.quer_function_id"
					+ " JOIN mst_user assin ON assin.user_id = quer.quer_assigned_to where quer.quer_entity_id =  "
					+ quer_entity_id;
			if (quer_unit_id > 0) {
				sql_two += " AND quer_unit_id = " + quer_unit_id;
			}
			if (quer_function_id > 0) {
				sql_two += " AND quer.quer_function_id = " + quer_function_id;
			}

			if (!from_date.equals("") && !to_date.equals("")) {
				sql_two += " AND quer.quer_query_date BETWEEN '" + from_date + "' AND '" + to_date + "'";
			}
			if (Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 2
					&& user_id != quer_assigned_to) {

				if (quer_assigned_to > 0) {
					sql_two += " AND (quer.quer_assigned_to =" + quer_assigned_to + " AND quer.quer_added_by = "
							+ user_id + " )";
				} else {
					sql_two += " AND (quer.quer_assigned_to=" + user_id + " OR quer.quer_added_by=" + user_id
							+ " OR quer.quer_assigned_to IN(select DISTINCT user_id from mst_user where user_report_to="
							+ user_id
							+ ") OR quer.quer_added_by IN(select DISTINCT user_id from mst_user where user_report_to="
							+ user_id + "))";
				}
			} else {
				if (quer_assigned_to > 0) {
					sql_two += " AND quer.quer_assigned_to =" + quer_assigned_to;
				}
			}

			String sql_three = " SELECT orga.orga_name,loca.loca_name,dept.dept_name,assin.user_first_name as assin_fname,assin.user_last_name as assin_lname , "
					+ " quer.quer_reply_date, quer.quer_status, quer.quer_id, quer.quer_query, quer.quer_added_by, quer.quer_assigned_to"
					+ " FROM mst_query quer " + " JOIN mst_organization orga ON orga.orga_id = quer.quer_entity_id"
					+ " JOIN mst_location loca ON loca.loca_id = quer.quer_unit_id"
					+ " JOIN mst_department dept ON dept.dept_id = quer.quer_function_id"
					+ " JOIN mst_user assin ON assin.user_id = quer.quer_assigned_to where quer.quer_entity_id =  "
					+ quer_entity_id;
			if (quer_unit_id > 0) {
				sql_three += " AND quer_unit_id = " + quer_unit_id;
			}
			if (quer_function_id > 0) {
				sql_three += " AND quer.quer_function_id = " + quer_function_id;
			}

			if (!from_date.equals("") && !to_date.equals("")) {
				sql_three += " AND quer.quer_query_date BETWEEN '" + from_date + "' AND '" + to_date + "'";
			}
			if (Integer.parseInt(session.getAttribute("sess_user_role").toString()) == 2
					&& user_id != quer_assigned_to) {

				if (quer_assigned_to > 0) {
					sql_three += " AND (quer.quer_assigned_to =" + quer_assigned_to
							+ " AND quer.quer_added_by IN(select DISTINCT user_id from mst_user where user_report_to="
							+ user_id + ") )";
				} else {
					sql_three += " AND (quer.quer_assigned_to=" + user_id + " OR quer.quer_added_by=" + user_id
							+ " OR quer.quer_assigned_to IN(select DISTINCT user_id from mst_user where user_report_to="
							+ user_id
							+ ") OR quer.quer_added_by IN(select DISTINCT user_id from mst_user where user_report_to="
							+ user_id + "))";
				}
			} else {
				if (quer_assigned_to > 0) {
					sql_three += " AND quer.quer_assigned_to =" + quer_assigned_to;
				}
			}

			// System.out.println("sql "+sql);
			Query query = em.createNativeQuery(sql + " UNION " + sql_two + " UNION " + sql_three);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get last generated id for query doc
	@Override
	public int getLastGeneratedValueForQueryDoc(int query_id) {
		try {
			String sql = "select MAX(quer_doc_last_generated_value_for_query_id) from trn_query_documents where quer_query_id ="
					+ query_id;
			Query query = em.createNativeQuery(sql);
			if (query.getResultList().get(0) != null) {
				return Integer.parseInt(query.getResultList().get(0).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Save document in db
	@Override
	public void saveQueryDocuments(QueryDocuments documents) {
		try {
			em.persist(documents);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// Method Created : Tejashri Zurunge
	// Method Purpose : get all Query documents by id

	@SuppressWarnings("unchecked")
	@Override
	public List<QueryDocuments> getDocumentByQueryId(int query_id) {
		try {
			String sql = "select * from trn_query_documents where quer_query_id =" + query_id;
			Query query = em.createNativeQuery(sql, QueryDocuments.class);
			if (!query.getResultList().isEmpty()) {
				return query.getResultList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// Method Created : Tejashri Zurunge
	// Method Purpose : get created Query documents by id

	@SuppressWarnings("unchecked")
	public <T> List<T> getRaisedDocumentByQueryId(int query_id) {
		try {
			String sql = "select * from trn_query_documents where quer_query_id =" + query_id
					+ " AND quer_reply_doc_related_value_for_query_id = 1";
			Query query = em.createNativeQuery(sql, QueryDocuments.class);
			if (!query.getResultList().isEmpty()) {
				return query.getResultList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// Method Created : Tejashri Zurunge
	// Method Purpose : get reply Query documents by id

	@SuppressWarnings("unchecked")
	public List<QueryDocuments> getReplyDocumentByQueryId(int query_id) {
		try {
			String sql = "select * from trn_query_documents where quer_query_id =" + query_id
					+ " AND quer_reply_doc_related_value_for_query_id = 2";
			Query query = em.createNativeQuery(sql, QueryDocuments.class);
			if (!query.getResultList().isEmpty()) {
				return query.getResultList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get all Query documents by document id
	@Override
	public QueryDocuments getQueryDocumentById(int quer_doc_id) {
		try {
			String sql = "select * from trn_query_documents where quer_doc_id =" + quer_doc_id;
			Query query = em.createNativeQuery(sql, QueryDocuments.class);
			if (!query.getResultList().isEmpty()) {
				return (QueryDocuments) query.getResultList().get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : delete Query document
	@Override
	public int deleteQueryDocument(QueryDocuments queryDocuments) {
		try {
			em.remove(em.merge(queryDocuments));
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get Query history
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getReplyQueryById(int query_id) {
		try {
			String sql = "select query.query_hst_id, query.query_hst_comments, query.query_hst_replied_date, query.query_hst_status, query.query_quer_id, query.query_hst_action_tobe_performed,"
					+ " query.query_hst_action_tobe_performed_by,assin.user_first_name as assin_fname,assin.user_last_name as assin_lname, "
					+ " query.query_hst_action_performed_others, query.query_hst_action_performed_by_others, query.query_hst_others, query.query_hst_action_assigned_to, query.query_hst_added_by, query.query_hst_type "
					+ " from ttrn_query_history query "
					+ " LEFT JOIN mst_user assin on  assin.user_id = query.query_hst_action_assigned_to "
					+ " where query.query_quer_id = " + query_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get Query details by id
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getJoinedQueryDetailsById(int query_id, HttpSession session) {
		try {
			String sql = "SELECT orga_name,loca_name,dept_name,assin.user_first_name as assin_fname,assin.user_last_name as assin_lname,quer_from_id,quer.quer_query_date, quer.quer_reply_date, quer.quer_reminder_date, quer.quer_id, quer.quer_query, quer.quer_status, quer.quer_assigned_to , quer.quer_criticality, quer.quer_turnaround_time "
					+ " FROM mst_query quer " 
					+ " LEFT JOIN mst_organization ON orga_id = quer.quer_entity_id "
					+ " LEFT JOIN mst_location ON mst_location.loca_id = quer.quer_unit_id "
					+ " LEFT JOIN mst_department ON dept_id = quer.quer_function_id "
					+ " LEFT JOIN mst_user assin ON assin.user_id = quer.quer_assigned_to WHERE quer.quer_id = " + query_id;
			/*
			 * if
			 * (Integer.parseInt(session.getAttribute("sess_user_role").toString
			 * ()) == 2) { sql += " AND quer.quer_assigned_to=" +
			 * Integer.parseInt(session.getAttribute("sess_user_id").toString())
			 * ; }
			 */
			// System.out.println("query is :"+sql);
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllUserByFunction(int orga_id, int loca_id, int dept_id) {
		try {
			String sql = "select distinct us.user_id, us.user_first_name, us.user_last_name from mst_query query join mst_user us on query.quer_assigned_to = us.user_id where quer_entity_id = "
					+ orga_id + " and quer_unit_id = " + loca_id + " and quer_function_id = " + dept_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getQueryDocumentFilePath(int quer_doc_id) {
		try {
			String sql = "select quer_doc_generated_file_name from trn_query_documents where quer_doc_id = "
					+ quer_doc_id;
			Query query = em.createNativeQuery(sql);
			if (!query.getResultList().isEmpty()) {
				if (query.getResultList().get(0) != null) {
					return query.getResultList().get(0).toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : save reply query

	@Override
	public int persistQueryReply(QueryHistory obj) {
		try {
			em.persist(obj);
			em.flush();
			return obj.getQuery_hst_id();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : save reply query
	@SuppressWarnings("unchecked")
	@Override
	public List<QueryDocuments> getDocumentByQueryHistoryId(int query_id) {
		try {
			String sql = "select * from trn_query_documents where quer_hst_doc_id =" + query_id;
			Query query = em.createNativeQuery(sql, QueryDocuments.class);
			if (!query.getResultList().isEmpty()) {
				return query.getResultList();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : save query logs
	@Override
	public int saveQueryLogs(Object obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get query logs
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getQueryHistoryLogs(String json) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
			int hst_log_id = Integer.parseInt(jsonObject.get("query_hst_id").toString());

			String sql = "select query.query_hst_id, query.query_hst_comments, query.query_hst_replied_date, query.query_hst_status, query.query_quer_id, query.query_hst_action_tobe_performed,"
					+ " query.query_hst_action_tobe_performed_by,assin.user_first_name as assin_fname,assin.user_last_name as assin_lname, perform.user_first_name as perform_fname,perform.user_last_name as perform_lname, query.query_hst_action_performed_others, query.query_hst_action_performed_by_others, query.query_hst_others,query.query_hst_log_status "
					+ " from trn_query_history_logs query "
					+ " join mst_user assin on  assin.user_id = query.query_hst_action_assigned_to "
					+ " join mst_user perform on perform.user_id = query.query_hst_replied_by where query.query_hst_id = "
					+ hst_log_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : get query logs
	@Override
	public int deleteQuery(int quer_id) {
		try {
			String sql_one = "Delete from mst_query where quer_id =" + quer_id;
			String sql_two = "Delete from ttrn_query_history where query_quer_id =" + quer_id;
			String sql_three = "Delete from trn_query_documents where quer_query_id =" + quer_id;
			Query query_one = em.createNativeQuery(sql_one);
			Query query_two = em.createNativeQuery(sql_two);
			Query query_three = em.createNativeQuery(sql_three);
			query_one.executeUpdate();
			query_two.executeUpdate();
			query_three.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public QueryHistory getQueryHistoryByHstId(int query_hst_id) {
		try {
			String sql = "Select * from ttrn_query_history where query_hst_id ="+ query_hst_id;
			Query query = em.createNativeQuery(sql, QueryHistory.class);
			if(! query.getResultList().isEmpty()){
				return (QueryHistory) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//Method created : Tejashri Zurunge
	//Method purpose : update query history
	@Override
	public void updateQueryHistory(QueryHistory history) {
		try {
			em.merge(history);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//Method created : Tejashri Zurunge
	//Method purpose : delete query history
	@Override
	public int deleteQueryHistory(int query_hst_id) {
		try {
			String sql = "Delete from ttrn_query_history where query_hst_id = "+query_hst_id;
			Query query = em.createNativeQuery(sql);
			return query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
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
	public void saveRecord(Object obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
