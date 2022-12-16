package lcmt.dao;

import java.util.List;

import lcmt.domain.Clause;



public interface ClauseDao {
	
	public void persist(Object obj);
	
	public <T> List<T> getAllClause(Class<T> clazz);
	
	public void updateClause(Clause clause);
	public Clause getClauseById(int cls_id);
	
	public int deleteClause(int cls_id);
//	public List<Object> checkDependancyClause(int cls_id);

}
