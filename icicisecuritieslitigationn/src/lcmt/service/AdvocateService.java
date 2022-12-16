package lcmt.service;

import java.util.List;

import lcmt.domain.Advocate;
import lcmt.domain.Advocate_reference;

public interface AdvocateService {

	public void persist(Advocate advocate);
	public List<Advocate_reference> getAll();
	public Advocate getAdvocateById(int advo_id);
	public void updateAdvocate(Advocate advocate);
	public int deleteAdvocate(int advo_id);
	public String searchAdvocate(int advo_law_firm, int advo_city, int advo_area_of_expertise,int advo_country_id,int advo_state_id,int advo_advocate_id);
	public String getAdvocateByLawFirmId(int law_firm_id);
	public List<Object> getJoinedCountries();
	public String getJoinedStateByCountryId(int country_id);
	public String getJoinedCityByStateId(int state_id);
	public String getAllAdvocateJson();
	public int isAdvocateNameExist(String json);
	public String checkDependancyAdvocate(int advo_id);
	
}
