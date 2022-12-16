package lcmt.service;

import java.util.List;
import java.util.Map;

import lcmt.domain.Clause;


public interface ClauseService {
	
public void persist(Clause clause);	

public Clause getClauseById(int cls_id);
public void updateClause(Clause clause);
public int deleteClause(int cls_id);
public List<Clause> getAll();

Map<Integer, String> getAllClause();

	

//public String checkDependancyClause(int cls_id);
	
}
