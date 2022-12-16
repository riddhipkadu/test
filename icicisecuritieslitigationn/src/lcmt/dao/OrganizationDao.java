package lcmt.dao;

import java.util.List;

import lcmt.domain.Organization;

public interface OrganizationDao {

	public void persist(Object obj);
	public <T> List<T> getAll(Class<T> clazz);
	public <T> List<T> getAllForAddingEntity(Class<T> clazz);
	public <T> List<T> getJoinedAll();
	public Organization getOrganizationById(int orga_id);
	public void updateOrganization(Organization organization);
	public int approveDisapproveOrg(int org_id , int orga_status);
	public int enableDisableOrg(int org_id , int orga_status);
	public int isOrgaNameExist(int orga_id, String orga_name);
	public int isOrgaShortNameExist(int orga_id , String orga_short_name);
	
	
}
