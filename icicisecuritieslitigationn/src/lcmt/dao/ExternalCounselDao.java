package lcmt.dao;

import java.util.List;

import lcmt.domain.ExternalCounsel;

public interface ExternalCounselDao {
            public void persist(ExternalCounsel counsel);
            public List<Object> getAll();
            public ExternalCounsel getExte_CounById(int exte_coun_id);
            public void updateExternalCounsel(ExternalCounsel counsel);
            public int isExternalCounselNameExist(int exte_coun_id, String exte_coun_name);
            public int deleteExternalCounsel(int exte_coun_id);
            public List<Object> searchExternalCounsel(int exte_coun_country_id,int exte_coun_state_id ,int exte_coun_city,int exte_coun_law_firm,int exte_coun_name,int exte_coun_area_of_expertise);
            public List<ExternalCounsel> getConselAsPerLawFirm(int law_firm_id);
            public List<Object> getCountryByCounselId();
            public List<Object> getJoinedStateByCountryId(int exte_coun_country_id);
            public List<Object> getJoinedCityByStateId(int exte_coun_state_id);
            public List<Object> checkDependencyExternalCounsel(int exte_coun_id);
}
