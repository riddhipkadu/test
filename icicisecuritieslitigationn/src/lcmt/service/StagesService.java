package lcmt.service;

import java.util.List;

import lcmt.domain.Stages;

public interface StagesService {
	
	public void persist(Stages stages);
	public List<Stages> getAll();
	public Stages getStagesById(int stage_id);
	public void updateStages(Stages stages);
	public int isStagesNameExist(int stage_id, String stage_name);
	public int deleteStages(int stage_id);
	public int checkDependancyStages(int stage_id);
	public String getAllStagesJson();
	
}
