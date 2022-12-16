package lcmt.dao;

import java.util.List;

import lcmt.domain.Stages;

public interface StagesDao {
	
	public void persist(Object obj);
	public <T> List<T> getAllStages(Class<T> clazz);
	public Stages getStagesById(int stage_id);
	public void updateStages(Stages stages);
	public int isStagesNameExist(int stage_id, String stage_name);
	public int deleteStages(int stage_id);
	public int checkDependancyStages(int stage_id);
}
