package lcmt.dao;

import java.util.List;

import lcmt.domain.Advocate;

public interface AdvocateDao {

	public void persist(Advocate advocate);
	public List<Object> getAll();
	public Advocate getAdvocateById(int advo_id);
	public void updateAdvocate(Advocate advocate);
	public int deleteAdvocate(int advo_id);
	public List<Object> searchAdvocate(int advo_law_firm, int advo_city,int advo_area_of_expertise,int advo_country_id,int advo_state_id,int advo_advocate_id);
	public List<Advocate> getAdvocateByLawFirmId(int law_firm_id);
	public List<Object> getJoinedCountries();
	public List<Object> getJoinedStateByCountryId(int country_id);
	public List<Object> getJoinedCityByStateId(int state_id);
	public int isAdvocateNameExist(String json);
	public List<Object> checkDependancyAdvocate(int advo_id);
	public List<Object> searchAdvocate(int advo_law_firm, int advo_area_of_expertise, int advo_advocate_id);
	
}
