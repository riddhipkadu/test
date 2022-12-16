package lcmt.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lcmt.dao.ClauseDao;
import lcmt.domain.Clause;
import lcmt.domain.ClauseLib;
import lcmt.domain.User;
import lcmt.service.ClauseService;
import lcmt.service.UtilitiesService;


@Service("clauseService")
public class ClauseServiceImpl  implements ClauseService {

	@Autowired
	UtilitiesService utilitiService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	ClauseDao clauseDao;
	
	// Method Created By: Tejashri Zurunge
		// Method Purpose: Add New Stage
		@Override
		public void persist(Clause clause) {
			try {

				clause.setCls_added_by(utilitiService.getCurrentSessionUserId(httpSession));
				clause.setCls_created_at(utilitiService.getCurrentDate());
				clause.setCls_updated_at(utilitiService.getCurrentDate());
				clauseDao.persist(clause);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}	
		
		
		
		
	
	// Method Created: Akash
		// Method Purpose: listing and view of clause
		@Override
		public List<Clause> getAll() {
			try {
				return clauseDao.getAllClause(Clause.class);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		}	
		
		
		// Method Created: Akshay Patkar
		// Method Purpose: To call Clause List
		
		@Override
		public Map<Integer, String> getAllClause() {
			try {
				List<Clause> result = clauseDao.getAllClause(Clause.class);
				Map<Integer , String> Clause_list = new HashMap<Integer, String>();
				for(Clause temp:result){
					Clause_list.put(0, "--Select--");
					Clause_list.put(temp.getCls_id(), temp.getCls_name());
				}
				return Clause_list;

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}
		
		
		// Method Created: Akash jadhav 
		// Method Purpose: Updating Clause By Id
		@Override
		public Clause getClauseById(int cls_id) {

			try {
				return clauseDao.getClauseById(cls_id);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}	
	
		// Method Created : Akash jadhav
		// Method purpose : Update Clause
		@Override
		public void updateClause(Clause clause) {
			try {

				Clause oldClause = getClauseById(clause.getCls_id());

				Clause newClause = new Clause();

				// Set old AreaOfExpertise data to new AreaOfExpertise
				newClause.setCls_id(oldClause.getCls_id());
				newClause.setCls_added_by(oldClause.getCls_added_by());
				newClause.setCls_created_at(oldClause.getCls_created_at());
				newClause.setCls_updated_at(utilitiService.getCurrentDate());

				// Set new data to newAreaOfExpertise object
				newClause.setCls_name(clause.getCls_name());

				clauseDao.updateClause(newClause);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
		
		// Method Created : Akash jadhav
		// Method Purpose : Delete Clause
		@Override
		public int deleteClause(int cls_id) {
			try {
				int deleteCount = clauseDao.deleteClause(cls_id);
				return deleteCount;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		/*
		 * // Method Created : Akash JAdhav // Method Purpose : Delete Clause
		 * 
		 * @SuppressWarnings("unchecked")
		 * 
		 * @Override public String checkDependancyClause(int cls_id) { try { JSONArray
		 * sendList = new JSONArray(); List<Object> clause =
		 * clauseDao.checkDependancyClause(cls_id); Iterator<Object> iterator =
		 * clause.iterator(); while(iterator.hasNext()){ JSONObject JSONObj = new
		 * JSONObject(); Object objects[] = (Object[]) iterator.next();
		 * JSONObj.put("cls_type", objects[0]); JSONObj.put("exec_type", objects[1]);
		 * sendList.add(JSONObj); } return sendList.toJSONString(); } catch (Exception
		 * e) { e.printStackTrace(); } return null; }
		 */
}


