package lcmt.service;

import lcmt.domain.Clause;
import lcmt.domain.ClauseLib;
import lcmt.domain.ClauseLib_Reference;
import lcmt.domain.Organization;
import lcmt.domain.Query_Reference;

import java.util.Map;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface ClauseLibService {

	public void persist(ClauseLib clauseLib);

	public List<ClauseLib> getAll();


	

	public ClauseLib getClauseLibById(int clause_Lib_Id);

	public List<ClauseLib> getJoinedAll();

	public int deleteClauseLib(int clause_Lib_Id);

	public void updateClauseLib(ClauseLib clauseLib);

	

	public ClauseLib_Reference getClauseLibById_ref(int clause_Lib_Id);

	public void updateClauseLib(ClauseLib_Reference clauseLib_Reference, HttpSession session);
	
	
	



	
		
	
	

	
}
