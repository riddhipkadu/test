package lcmt.service;

import java.util.List;

import lcmt.domain.Court;

public interface CourtService {
	
	public void persist(Court court);
	public List<Court> getAll();
	public Court getCourtById(int court_id);
	public void updateCourt(Court court);
	public int isCourtExistOrNot(int court_id, String court_name);
	public int deleteCourt(int court_id);
	public int checkDependancyCourt(int court_id);
	public String getAllCourtJson();
}
