package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import lcmt.dao.LogsDao;

@Repository(value = "logsDao")
public class LogsDaoImpl implements LogsDao {

	@PersistenceContext
	private EntityManager em;

	// Method Created : Tejashri Zurunge
	// Method purpose : get list of All Logs
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllPersistLogs(String related_to, String from_date, String to_date) {
		try {
			String sql = "SELECT log_id,log_activity,log_activity_id,log_assinged_to_id, log_created_at, log_related_to, log_sub_activity, log_sub_activity_id, log_related_name FROM trn_activity_logs where log_activity = 'Add' AND log_sub_activity_id = 0 "
					+ " AND log_related_to = '" + related_to + "'";

			if (!from_date.equals("") && !to_date.equals("")) {
				sql += " AND CAST(log_created_at as DATE) BETWEEN '" + from_date + " ' AND '" + to_date + "'";
			}
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : get list of All Logs
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getActivityLogs(String related_to, int log_activity_id) {
		try {
			String sql = "SELECT log_id,log_activity,log_activity_id,log_assinged_to_id, log_created_at, log_related_to, log_sub_activity, log_sub_activity_id, log_related_name FROM trn_activity_logs"
					+ " where log_activity_id = " + log_activity_id + " AND log_related_to = '" + related_to + "'";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
