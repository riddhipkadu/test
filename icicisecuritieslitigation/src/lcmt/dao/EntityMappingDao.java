package lcmt.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import lcmt.domain.Cities;
import lcmt.domain.Countries;
import lcmt.domain.EntityMapping;
import lcmt.domain.States;

public interface EntityMappingDao {

	public void persist(Object obj);
	public <T> List<T> getAll(Class<T> clazz);
	public <T> List<T> getJoinedAll();
	public EntityMapping getEntitiesMappingById(int enti_id);
	public void updateEntitiesMapping(EntityMapping entityMapping);
	//public void deleteEntitiesMapping(int enti_orga_id, int enti_loca_id);
	public int approveDisapproveEntity(int enti_id,int enti_status);
	public int enableDisableEntity(int enti_id,int enti_status);
	public int isExistEntityMapping(int enti_id,int orga_id,int loca_id,int dept_id);
	public <t> List<t> getUniqueOrganogramMapping();
	public <T> List<Object> getExecutorListMappingWise(int orgaid, int locaid, int deptid);
	public <T> List<Object> getEvaluatorListMappingWise(int orgaid, int locaid, int deptid);
	public <T> List<Object> getFunctionHeadListMappingWise(int orgaid, int locaid, int deptid);
	public List<String> getMappedDeptList(int orgaid, int locaid);
	public int getExecutorMappingCount(int orga_id, int loca_id, int dept_id);
	public int getEvaluatorMappingCount(int orga_id, int loca_id, int dept_id);
	public int getFunctionHeadMappingCount(int orga_id, int loca_id, int dept_id);
	public int deleteActualEntityMapping(int enti_id);
	public List<Object> getOrganizationByUserId(HttpSession session);
	public List<Object> getLocationByOrgaUserId(HttpSession session, int orga_id);
	public List<Object> getDepartmentsByLocaUserId(HttpSession session, int loca_id, int orga_id);
	public List<Countries> getAllCountries();
	public List<States> getAllStateByCounId(int coun_id);
	public List<Cities> getAllCityByStateId(int state_id);
	public List<Object> getAllState();
	public List<Object> getAllCity();
}
