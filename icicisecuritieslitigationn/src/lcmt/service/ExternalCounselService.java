package lcmt.service;

import java.util.List;
import java.util.Map;

import lcmt.domain.Ext_Coun_Reference;
import lcmt.domain.ExternalCounsel;

public interface ExternalCounselService {
      
	public void persist(ExternalCounsel counsel);
	public List<Ext_Coun_Reference> getAll();
	public ExternalCounsel getExte_CounById(int exte_coun_id);
	public void updateExternalCounsel(ExternalCounsel counsel);
	public int isExternalCounselNameExist(int exte_coun_id, String exte_coun_name);
	public int deleteExternalCounsel(int exte_coun_id);
	public String searchExternalCounsel(int exte_coun_country_id, int exte_coun_state_id ,int exte_coun_city,int exte_coun_law_firm,int exte_coun_name,int exte_coun_area_of_expertise);
	public String getCounselByLawFirmId(int law_firm_id);
	public Map<Integer, String> getCountryByCounselId();
	public String getJoinedStateByCountryId(int exte_coun_country_id);
	public String getJoinedCityByStateId(int exte_coun_state_id);
	public String checkDependencyExternalCounsel(int exte_coun_id);
	public String getAllExternalCounselJson();
	
	
}

