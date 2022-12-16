package lcmt.dao;


import java.util.List;

import lcmt.domain.Clause;
import lcmt.domain.ClauseLib;

public interface ClauseLibDao {

	public void persist(ClauseLib clauseLib);

	public ClauseLib getClauseLibById(int clause_Lib_Id);

	public <T> List<T> getJoinedAll();
	
	public <T> List<T> getAllClauseLib(Class<T> clazz);

	public int deleteClauseLib(int clause_Lib_Id);

	public void updateClauseLib(ClauseLib clauseLib);

	public ClauseLib getClauseLibById_ref(int clause_Lib_Id);
	


}
