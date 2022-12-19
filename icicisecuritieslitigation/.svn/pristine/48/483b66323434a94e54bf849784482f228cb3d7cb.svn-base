package lcmt.service;

import java.util.List;

import lcmt.domain.ArcManager;
import lcmt.domain.ArcManagerRef;

public interface ArcManagerService {
	public List<ArcManagerRef> getAllArcManager();
	public void persist(ArcManager arcManager); 
	public ArcManager getArcManagerById(int mgr_arc_id);
	public void updateArcManager(ArcManager arcManager);
	public int deleteArcManager(int mgr_id);
	public int isArcManagerExist(int mgr_arc_id, String mgr_name);
	public String checkArcManagerDependancy(int mgr_id);
	public int isARCManagerEmailExist(int arc_id, String mgr_arc_email_id);
	
}
