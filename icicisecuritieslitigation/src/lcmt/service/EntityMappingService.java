package lcmt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import lcmt.domain.EntityMapping;
import lcmt.domain.EntityMappingReference;
import lcmt.domain.OrganogramSummary;

public interface EntityMappingService {

	public void persist(EntityMappingReference entityMappingReference);
	public List<EntityMapping> getAll();
	public List<EntityMapping> getJoinedAll();
	public EntityMapping getEntitiesMappingById(int enti_id);
	public void updateEntitiesMapping(EntityMapping entityMapping);
	public Map<Integer, String> getAllOrganizations();
	public Map<Integer, String> getAllLocations();
	public Map<Integer, String> getAllDepartments();
	public int approveDisapproveEntity(int enti_id,int enti_status);
	public int enableDisableEntity(int enti_id,int enti_status);
	public int isExistEntityMapping(int enti_id,int orga_id,int loca_id,int dept_id);
	public <t> List<OrganogramSummary> getUniqueOrganogramMapping();
	public List<String> getMappedDeptList(int orgaid, int locaid);
	public int getExecutorMappingCount(int orga_id, int loca_id, int dept_id);
	public int getEvaluatorMappingCount(int orga_id, int loca_id, int dept_id);
	public int getFunctionHeadMappingCount(int orga_id, int loca_id, int dept_id);
	public int deleteActualEntityMapping(int enti_id);
	public Map<Integer, String> getOrganizationByUserId(HttpSession session);
	public String getLocationByOrgaUserId(HttpSession session, int orga_id);
	public String getDepartmentsByLocaUserId(HttpSession session, int loca_id, int orga_id);
	public Map<Integer,String> getAllCountries();
	public String getAllStateByCounId(int coun_id);
	public String getAllCitiesByStateId(int state_id);
	public Map<Integer,String> getAllState();
	public Map<Integer,String> getAllCity();
	public Map<Integer, String> getStatesByCountryId(int coun_id);
	public Map<Integer, String> getCityByStateId(int state_id);
	public Map<Integer, String> getLocationByUserId(HttpSession session,int orga_id);
	public Map<Integer, String> getDeptByUserId(HttpSession session,int loca_id, int orga_id);
}
