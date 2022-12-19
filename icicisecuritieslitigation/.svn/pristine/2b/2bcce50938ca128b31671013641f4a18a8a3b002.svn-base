package lcmt.service;

import java.util.List;


import lcmt.domain.Organization;

public interface OrganizationService {
	public void persist(Organization organization);
	public List<Organization> getAll();
	public List<Organization> getAllForAddingEntity();
	public List<Organization> getJoinedAll();
	public Organization getOrganizationById(int orga_id);
	public void updateOrganization(Organization organization);
	public int approveDisapprove(int org_id , int orga_status);
	public int enableDisable(int org_id , int orga_status);
	public int isOrgaNameExist(int orga_id, String orga_name);
	public int isOrgaShortNameExist(int orga_id, String orga_short_name);
	
}
