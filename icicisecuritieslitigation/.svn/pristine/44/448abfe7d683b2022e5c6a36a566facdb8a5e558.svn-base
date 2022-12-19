package lcmt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.DepartmentDao;
import lcmt.dao.EntityMappingDao;
import lcmt.dao.LocationDao;
import lcmt.dao.OrganizationDao;
import lcmt.domain.Cities;
import lcmt.domain.Countries;
import lcmt.domain.Department;
import lcmt.domain.EntityMapping;
import lcmt.domain.EntityMappingReference;
import lcmt.domain.Location;
import lcmt.domain.Organization;
import lcmt.domain.OrganogramSummary;
import lcmt.domain.States;
import lcmt.service.EntityMappingService;
import lcmt.service.UtilitiesService;

/*
 * Author: Mahesh Kharote
 * Date: 19/02/2016
 * Updated By: Mugdha Chandratre
 * Updated Date: 23/02/2016
 * 
 * */

@Service("entityMappingService")
public class EntityMappingServiceImpl implements EntityMappingService {

	@Autowired
	EntityMappingDao entityMappingDao;

	@Autowired
	OrganizationDao organizationDao;

	@Autowired
	LocationDao locationDao;

	@Autowired
	DepartmentDao departmentDao;

	@Autowired
	UtilitiesService utilitiesService;

	@Autowired
	HttpSession httpSession;

	// Method Created By: Mugdha Chandratre
	// Method Purpose: Add New Entity Mapping to Database
	@Override
	public void persist(EntityMappingReference entityMappingReference) {
		try {
			// entityMappingDao.deleteEntitiesMapping(entityMappingReference.getEnti_orga_id()
			// , entityMappingReference.getEnti_loca_id());

			System.out.println("all dept ids:" + entityMappingReference.getEnti_dept_id());
			ArrayList<Integer> all_dept_id = entityMappingReference.getEnti_dept_id();

			if (all_dept_id != null) {

				for (int i = 0; i < all_dept_id.size(); i++) {
					EntityMapping entitiesMapping2 = new EntityMapping();

					entitiesMapping2.setEnti_orga_id(entityMappingReference.getEnti_orga_id());
					entitiesMapping2.setEnti_loca_id(entityMappingReference.getEnti_loca_id());
					entitiesMapping2.setEnti_dept_id(all_dept_id.get(i));
					// entitiesMapping2.setEnti_added_by(1);
					entitiesMapping2.setEnti_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
					entitiesMapping2.setEnti_approval_status("1");
					entitiesMapping2.setEnti_enable_status("1");
					entitiesMapping2.setEnti_created_at(utilitiesService.getCurrentDate());

					entityMappingDao.persist(entitiesMapping2);
				}
			} else {

				EntityMapping entitiesMapping2 = new EntityMapping();
				entitiesMapping2.setEnti_orga_id(entityMappingReference.getEnti_orga_id());
				entitiesMapping2.setEnti_loca_id(entityMappingReference.getEnti_loca_id());
				entitiesMapping2.setEnti_dept_id(0);
				entitiesMapping2.setEnti_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
				entitiesMapping2.setEnti_approval_status("1");
				entitiesMapping2.setEnti_enable_status("1");
				entitiesMapping2.setEnti_created_at(utilitiesService.getCurrentDate());

				entityMappingDao.persist(entitiesMapping2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created By: Mugdha Chandratre
	// Method Purpose: Get list of all Entity Mapping
	@Override
	public List<EntityMapping> getAll() {
		try {
			return entityMappingDao.getAll(EntityMapping.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Mugdha Chandratre
	// Method Purpose: Fetch List of all joined Entity Mapping from database
	@Override
	public List<EntityMapping> getJoinedAll() {
		try {
			return entityMappingDao.getJoinedAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Mugdha Chandratre
	// Method Purpose: Fetch Entity Mapping by id from database
	@Override
	public EntityMapping getEntitiesMappingById(int enti_id) {
		try {
			return entityMappingDao.getEntitiesMappingById(enti_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Mugdha Chandratre
	// Method Purpose: Update Entity Mapping
	@Override
	public void updateEntitiesMapping(EntityMapping entityMapping) {
		try {

			EntityMapping oldEntityMapping = getEntitiesMappingById(entityMapping.getEnti_id());

			EntityMapping newEntityMapping = new EntityMapping();

			// Set old data to newEntityMapping object
			newEntityMapping.setEnti_id(oldEntityMapping.getEnti_id());
			newEntityMapping.setEnti_added_by(oldEntityMapping.getEnti_added_by());
			newEntityMapping.setEnti_created_at(oldEntityMapping.getEnti_created_at());
			newEntityMapping.setEnti_approval_status(oldEntityMapping.getEnti_approval_status());
			newEntityMapping.setEnti_enable_status(oldEntityMapping.getEnti_enable_status());

			// Set new data to newEntityMapping object
			newEntityMapping.setEnti_orga_id(entityMapping.getEnti_orga_id());
			newEntityMapping.setEnti_loca_id(entityMapping.getEnti_loca_id());
			newEntityMapping.setEnti_dept_id(entityMapping.getEnti_dept_id());

			entityMappingDao.updateEntitiesMapping(newEntityMapping);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created By: Mugdha Chandratre
	// Method Purpose: Fetch all list of organizations
	@Override
	public Map<Integer, String> getAllOrganizations() {
		try {
			List<Organization> orgaList = organizationDao.getAll(Organization.class);
			Map<Integer, String> organization_list = new HashMap<Integer, String>();
			for (Organization temp : orgaList) {
				organization_list.put(0, "--Select--");
				organization_list.put(temp.getOrga_id(), temp.getOrga_name());
			}

			return organization_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Mugdha Chandratre
	// Method Purpose: Fetch all list of locations
	@Override
	public Map<Integer, String> getAllLocations() {
		try {
			List<Location> locaList = locationDao.getAll(Location.class);
			Map<Integer, String> location_list = new HashMap<Integer, String>();
			for (Location temp : locaList) {
				location_list.put(0, "--Select--");
				location_list.put(temp.getLoca_id(), temp.getLoca_name());
			}
			return location_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Mugdha Chandratre
	// Method Purpose: Fetch all list of departments
	@Override
	public Map<Integer, String> getAllDepartments() {
		try {

			List<Department> deptList = departmentDao.getAll(Department.class);
			Map<Integer, String> department_list = new HashMap<Integer, String>();
			for (Department temp : deptList) {
				// department_list.put(0, "--Select--");
				department_list.put(temp.getDept_id(), temp.getDept_name());
			}
			return department_list;

			/*
			 * List<Department> deptList =
			 * departmentDao.getAll(Department.class); Map<Integer, String>
			 * department_list = new HashMap<>(); for(Department temp:deptList){
			 * department_list.put(temp.getDept_id(), temp.getDept_name()); }
			 * return department_list;
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By:Harshad Padole
	// Method Purpose: Approve or disapprove entity
	@Override
	public int approveDisapproveEntity(int enti_id, int enti_status) {
		try {
			return entityMappingDao.approveDisapproveEntity(enti_id, enti_status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created By:Harshad Padole
	// Method Purpose: Enable or disable entity
	@Override
	public int enableDisableEntity(int enti_id, int enti_status) {
		try {
			return entityMappingDao.enableDisableEntity(enti_id, enti_status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Check mapped entity
	@Override
	public int isExistEntityMapping(int enti_id, int orga_id, int loca_id, int dept_id) {
		try {
			return entityMappingDao.isExistEntityMapping(enti_id, orga_id, loca_id, dept_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Mugdha Chandratre (15-07-2016)
	// Method Purpose : Get all mapped user list as per entity mapping
	@SuppressWarnings("unused")
	@Override
	public <t> List<OrganogramSummary> getUniqueOrganogramMapping() {
		try {
			List<OrganogramSummary> completeList = new ArrayList<>();

			List<Object> allMappingList = entityMappingDao.getUniqueOrganogramMapping();
			List<Object> subcompleteList = new ArrayList<>();
			Iterator<Object> itr = allMappingList.iterator();
			while (itr.hasNext()) {
				OrganogramSummary organogramSummary = new OrganogramSummary();
				Object[] mappingList = (Object[]) (itr.next());

				organogramSummary.setEntityName(mappingList[1].toString());
				organogramSummary.setUnitName(mappingList[3].toString());
				organogramSummary.setFunctionName(mappingList[5].toString());

				int orgaid = Integer.parseInt(mappingList[0].toString());
				int locaid = Integer.parseInt(mappingList[2].toString());
				int deptid = Integer.parseInt(mappingList[4].toString());

				List<Object> Executorlist = entityMappingDao.getExecutorListMappingWise(orgaid, locaid, deptid);

				organogramSummary.setExecutorList(Executorlist);

				List<Object> EvaluatorList = entityMappingDao.getEvaluatorListMappingWise(orgaid, locaid, deptid);

				organogramSummary.setEvaluatorList(EvaluatorList);

				List<Object> FunctionHeadList = entityMappingDao.getFunctionHeadListMappingWise(orgaid, locaid, deptid);

				organogramSummary.setFunctionHeadList(FunctionHeadList);

				completeList.add(organogramSummary);

			}

			return completeList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> getMappedDeptList(int orgaid, int locaid) {
		try {
			List<String> allMappingList = entityMappingDao.getMappedDeptList(orgaid, locaid);
			//System.out.println("service list:" + allMappingList.size());
			return allMappingList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getExecutorMappingCount(int orga_id, int loca_id, int dept_id) {
		try {
			int executorCount = entityMappingDao.getExecutorMappingCount(orga_id, loca_id, dept_id);
			//System.out.println("execount: " + executorCount);
			return executorCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getEvaluatorMappingCount(int orga_id, int loca_id, int dept_id) {
		try {
			int evaluatorCount = entityMappingDao.getEvaluatorMappingCount(orga_id, loca_id, dept_id);
			//System.out.println("evecount: " + evaluatorCount);
			return evaluatorCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getFunctionHeadMappingCount(int orga_id, int loca_id, int dept_id) {
		try {
			int functionHeadCount = entityMappingDao.getFunctionHeadMappingCount(orga_id, loca_id, dept_id);
			//System.out.println("funccount: " + functionHeadCount);
			return functionHeadCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteActualEntityMapping(int enti_id) {
		try {
			int deleteCount = entityMappingDao.deleteActualEntityMapping(enti_id);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Map<Integer, String> getOrganizationByUserId(HttpSession session) {
		try {
			List<Object> orgaList = entityMappingDao.getOrganizationByUserId(session);
			Map<Integer, String> organization_list = new HashMap<Integer, String>();

			Iterator<Object> iterator = orgaList.iterator();
			organization_list.put(0, "--Select--");
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				organization_list.put(Integer.parseInt(objects[0].toString()), objects[1].toString());
			}
			return organization_list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getLocationByOrgaUserId(HttpSession session, int orga_id) {
		try {
			List<Object> locaList = entityMappingDao.getLocationByOrgaUserId(session, orga_id);
			JSONArray location_list = new JSONArray();

			Iterator<Object> iterator = locaList.iterator();

			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("loca_id", objects[0]);
				jsonObject.put("loca_name", objects[1]);
				location_list.add(jsonObject);
			}

			return location_list.toJSONString();

		} catch (Exception e) {

		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getDepartmentsByLocaUserId(HttpSession session, int loca_id, int orga_id) {
		try {
			List<Object> deptList = entityMappingDao.getDepartmentsByLocaUserId(session, loca_id, orga_id);
			JSONArray department_list = new JSONArray();

			Iterator<Object> iterator = deptList.iterator();

			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				JSONObject jsonObject = new JSONObject();

				jsonObject.put("dept_id", objects[0]);
				jsonObject.put("dept_name", objects[1]);
				department_list.add(jsonObject);
			}

			return department_list.toJSONString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Method created : Harshad Padole
	// Method purpose : get all countries
	@Override
	public Map<Integer, String> getAllCountries() {
		try {
			Map<Integer, String> country_list = new HashMap<Integer, String>();
			List<Countries> res = entityMappingDao.getAllCountries();
			Iterator<Countries> iterator = res.iterator();
			country_list.put(0, "--Select--");
			while (iterator.hasNext()) {
				Countries countries = iterator.next();
				country_list.put(countries.getCoun_id(), countries.getCoun_name());
			}
			return country_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created : Harshad Padole
	// Method purpose : get State by country id
	@SuppressWarnings("unchecked")
	@Override
	public String getAllStateByCounId(int coun_id) {
		try {
			JSONArray state_list = new JSONArray();
			List<States> res = entityMappingDao.getAllStateByCounId(coun_id);
			Iterator<States> iterator = res.iterator();
			while (iterator.hasNext()) {
				States states = iterator.next();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("state_id", states.getStat_id());
				jsonObject.put("state_name", states.getStat_name());
				state_list.add(jsonObject);

			}
			return state_list.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created : Harshad Padole
	// Method purpose : get city by state id
	@SuppressWarnings("unchecked")
	@Override
	public String getAllCitiesByStateId(int state_id) {
		try {
			JSONArray city_list = new JSONArray();
			List<Cities> res = entityMappingDao.getAllCityByStateId(state_id);
			Iterator<Cities> iterator = res.iterator();
			while (iterator.hasNext()) {
				Cities cities = iterator.next();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("city_id", cities.getCity_id());
				jsonObject.put("city_name", cities.getCity_name());
				city_list.add(jsonObject);

			}
			return city_list.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<Integer, String> getAllState() {
		try {
			Map<Integer, String> state_list = new HashMap<Integer, String>();
			List<Object> res = entityMappingDao.getAllState();
			Iterator<Object> iterator = res.iterator();
			state_list.put(0, "--Select--");
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				state_list.put(Integer.parseInt(objects[0].toString()), objects[1].toString());
			}
			return state_list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Map<Integer, String> getAllCity() {
		try {
			Map<Integer, String> city_list = new HashMap<Integer, String>();
			List<Object> res = entityMappingDao.getAllCity();
			Iterator<Object> iterator = res.iterator();
			city_list.put(0, "--Select--");
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				city_list.put(Integer.parseInt(objects[0].toString()), objects[2].toString());
			}
			return city_list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method created : Tejashri Zurunge
	// Method purpose : get state by city id
	@Override
	public Map<Integer, String> getStatesByCountryId(int coun_id) {
		try {
			List<States> states = entityMappingDao.getAllStateByCounId(coun_id);
			Map<Integer, String> stateList = new HashMap<Integer, String>();
			Iterator<States> iterator = states.iterator();
			while (iterator.hasNext()) {
				States newStates = iterator.next();
				stateList.put(newStates.getStat_id(), newStates.getStat_name());
			}
			return stateList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created : Tejashri Zurunge
	// Method purpose : get city by state id
	@Override
	public Map<Integer, String> getCityByStateId(int state_id) {
		try {
			List<Cities> city = entityMappingDao.getAllCityByStateId(state_id);
			Map<Integer, String> cityList = new HashMap<Integer, String>();
			Iterator<Cities> iterator = city.iterator();
			while (iterator.hasNext()) {
				Cities newCity = iterator.next();
				cityList.put(newCity.getCity_id(), newCity.getCity_name());
			}
			return cityList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<Integer, String> getLocationByUserId(HttpSession session, int orga_id) {
		try {
			List<Object> locaList = entityMappingDao.getLocationByOrgaUserId(session, orga_id);
			Map<Integer, String> location_list = new HashMap<Integer, String>();

			Iterator<Object> iterator = locaList.iterator();
			location_list.put(0, "--Select--");
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				location_list.put(Integer.parseInt(objects[0].toString()), objects[1].toString());
			}
			return location_list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<Integer, String> getDeptByUserId(HttpSession session, int loca_id, int orga_id) {
		try {
			List<Object> deptList = entityMappingDao.getDepartmentsByLocaUserId(session, loca_id, orga_id);
			Map<Integer, String> dept_list = new HashMap<Integer, String>();

			Iterator<Object> iterator = deptList.iterator();
			dept_list.put(0, "--Select--");
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				dept_list.put(Integer.parseInt(objects[0].toString()), objects[1].toString());
			}
			return dept_list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
