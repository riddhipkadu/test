package lcmt.dao;

import java.util.List;

import lcmt.domain.Court;

public interface CourtDao {

	public void persist(Court court);
	public <T>List<T> getAllCourt(Class<T> clazz);
	public Court getCourtById(int court_id);
	public void updateCourt(Court court);
	public int isCourtExistOrNot(int court_id, String court_name);
	public int deleteCourt(int court_id);
	public List<Object> checkDependancyCourt(int court_id);
}
