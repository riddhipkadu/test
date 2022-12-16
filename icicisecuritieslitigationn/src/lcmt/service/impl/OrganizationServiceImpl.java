package lcmt.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.OrganizationDao;
import lcmt.domain.Organization;
import lcmt.service.OrganizationService;
import lcmt.service.UtilitiesService;

@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	OrganizationDao organizationDao;

	@Autowired
	UtilitiesService utilitiesService;

	@Autowired
	HttpSession httpSession;

	//Method Created By: Mugdha Chandratre
	//Method Purpose: Add New Organization to Database
	@Override
	public void persist(Organization organization) {
		try {

			organization.setOrga_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
			organization.setOrga_created_at(utilitiesService.getCurrentDate());
			organization.setOrga_approval_status("1");
			organization.setOrga_enable_status("1");

			organizationDao.persist(organization);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	//Method Created By: Mugdha Chandratre
	//Method Purpose: Update Organization
	@Override
	public void updateOrganization(Organization organization) {
		try {

			Organization oldOrganization = getOrganizationById(organization.getOrga_id());

			Organization newOrganization = new Organization();

			//Set existed data of old organization 
			newOrganization.setOrga_id(oldOrganization.getOrga_id());
			newOrganization.setOrga_added_by(oldOrganization.getOrga_added_by());
			newOrganization.setOrga_created_at(oldOrganization.getOrga_created_at());
			newOrganization.setOrga_enable_status(oldOrganization.getOrga_enable_status());
			newOrganization.setOrga_approval_status(oldOrganization.getOrga_approval_status());

			//Old data of organization
			newOrganization.setOrga_name(organization.getOrga_name());
			newOrganization.setOrga_parent_id(organization.getOrga_parent_id());
			newOrganization.setOrga_short_name(organization.getOrga_short_name());

			organizationDao.updateOrganization(newOrganization);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	//Method Created By: Mugdha Chandratre
	//Method Purpose: get all Organization from Database
	@Override
	public List<Organization> getAll() {
		try {
			return organizationDao.getAll(Organization.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//Method Created By: Mahesh Kharte
	//Method Purpose: Adding entity requires NA
	@Override
	public List<Organization> getAllForAddingEntity() {
		try {
			return organizationDao.getAllForAddingEntity(Organization.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Mugdha Chandratre
	//Method Purpose: get Parent and Children Organization from Database
	@Override
	public List<Organization> getJoinedAll() {
		try {
			return organizationDao.getJoinedAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created By: Mugdha Chandratre
	//Method Purpose: get Organization by id from Database
	@Override
	public Organization getOrganizationById(int orga_id) {
		try {
			return organizationDao.getOrganizationById(orga_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	//Method Created : Harshad Padole
	//Method purpose : Approve or disapprove organization
	@Override
	public int approveDisapprove(int org_id , int orga_status) {
		try {
			int res = organizationDao.approveDisapproveOrg(org_id, orga_status);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	//Method Created : Harshad Padole
	//Method purpose : Enable or Disable organization
	@Override
	public int enableDisable(int org_id, int orga_status) {
		try {
			int res = organizationDao.enableDisableOrg(org_id, orga_status);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int isOrgaNameExist(int orga_id, String orga_name) {
		try {
			return organizationDao.isOrgaNameExist(orga_id, orga_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int isOrgaShortNameExist(int orga_id, String orga_short_name) {
		try {
			return organizationDao.isOrgaShortNameExist(orga_id, orga_short_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	

	
	
	
	
}

