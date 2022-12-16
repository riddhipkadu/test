package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

import lcmt.dao.CalendarDao;
import lcmt.domain.Litigation;

@Repository("calendarDao")
@Transactional
public class CalendarDaoImpl implements CalendarDao {


	@PersistenceContext
	private EntityManager em;
	
	//Method Created : Harshad Padole
	//Method Purpose : Get litigation for calendar
	@Override
	public <T> List<T> getLitigationForCalendar(Class<T> clazz,HttpSession session) {
		try {
			TypedQuery<T> query = null;
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			if(Integer.parseInt(session.getAttribute("sess_user_role").toString())==1)
			 query = em.createQuery(" from "+clazz.getName()+" where liti_status !='Completed'", clazz);
			
			if(Integer.parseInt(session.getAttribute("sess_user_role").toString())==2)
				 query = em.createQuery(" from "+clazz.getName()+" where liti_status !='Completed' AND (liti_internally_handled_by="+user_id+" OR liti_added_by="+user_id+" OR liti_added_by="+user_id+" OR liti_internally_handled_by IN(select DISTINCT user_id from mst_user where user_report_to="+user_id+") OR liti_added_by IN(select DISTINCT user_id from mst_user where user_report_to="+user_id+") )", clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//Method Created : Harshad Padole
	//Method Purpose : Search litigation for calendar
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> searchLitigationForCalendar(String json,HttpSession session) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
			int orga_id = Integer.parseInt(jsonObject.get("orga_id").toString());
			int loca_id = Integer.parseInt(jsonObject.get("loca_id").toString());
			int dept_id = Integer.parseInt(jsonObject.get("dept_id").toString());
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			String sql = "select * from mst_litigation where liti_orga_id ="+orga_id;
			
			if(loca_id!=0){
				sql +=" AND liti_loca_id="+loca_id;
			}
			if(dept_id!=0){
				sql +=" AND liti_dept_id="+dept_id;
			}
			if(Integer.parseInt(session.getAttribute("sess_user_role").toString())==2)
				sql +=" AND (liti_internally_handled_by="+user_id+" OR liti_added_by="+user_id+" OR liti_internally_handled_by IN(select DISTINCT user_id from mst_user where user_report_to="+user_id+") OR liti_added_by IN(select DISTINCT user_id from mst_user where user_report_to="+user_id+") )";
				
			sql +=" AND liti_status !='Completed'";
			Query query = em.createNativeQuery(sql, Litigation.class);
			return query.getResultList();
		} catch (Exception e) {
		  e.printStackTrace();
		}
		return null;
	}

}
