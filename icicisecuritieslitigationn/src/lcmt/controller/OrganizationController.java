package lcmt.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lcmt.domain.Organization;
import lcmt.service.OrganizationService;

/*
 * Author: Mahesh Kharote
 * Date: 19/02/2016
 * Updated By: Harshad Padole
 * Updated Date: 22/02/2016
 * 
 * */

@Controller
@RequestMapping("/*")
public class OrganizationController {

	@Autowired
	OrganizationService organizationService;

	// Method Created : Mahesh Kharote
	// Method purpose : Get all Organization and load listing view
	@RequestMapping(value = "/listOrganizations", method = RequestMethod.GET)
	public ModelAndView listOrganizations(HttpSession session) {

		try {
			if (session.getAttribute("username") != null) {
				ModelAndView modelandview = new ModelAndView("listOrganizations", "allOrganizations",
						organizationService.getJoinedAll());
				modelandview.addObject("username", session.getAttribute("username"));
				return modelandview;
			} else {

				ModelAndView modelandview = new ModelAndView("userLogin");
				return modelandview;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// Method Created : Mahesh Kharote
	// Method purpose : Get all Organization and load add organization view
	@RequestMapping(value = "/addOrganization", method = RequestMethod.GET)
	public ModelAndView addOrganization() {

		try {

			ModelAndView modelandview = new ModelAndView("addOrganization", "organization", new Organization());
			modelandview.addObject("organization_list", organizationService.getAllForAddingEntity());
			return modelandview;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// Method Created : Mahesh Kharote
	// Method purpose : Save Organization
	@SuppressWarnings({ "deprecation", "unused" })
	@RequestMapping(value = "/saveOrganization", method = RequestMethod.POST)
	public String saveOrganization(Organization organization, HttpSession session, BindingResult bindingResult) {
		try {
			Date dateobj = new Date(2016, 01, 05);
			organization.setOrga_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			organizationService.persist(organization);
			/*if (bindingResult.hasErrors()) {
				session.setAttribute("smsg", "You have successfully save record into the database!");
				return "redirect:addOrganization";
			} else {
				session.setAttribute("smsg", "Something went wrong!");
				return "redirect:listOrganizations";
			}
*/
			return "redirect:listOrganizations";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// Method Created : Mahesh Kharote
	// Method purpose : Get all Organization and load edit org view
	@RequestMapping(value = "/editOrganization", method = RequestMethod.GET)
	public ModelAndView editOrganization(@RequestParam("orga_id") int orga_id1) {
		try {
			List<Organization> organizationList = new ArrayList<>();
			organizationList = organizationService.getAll();
			Map<Integer, String> organization_list = new HashMap<Integer, String>();
			organization_list.put(1, "--NA--");
			for (Organization temp : organizationList) {
				organization_list.put(temp.getOrga_id(), temp.getOrga_name());
			}

			Organization organization = organizationService.getOrganizationById(orga_id1);
			ModelAndView modelandview = new ModelAndView("editOrganization", "organization", organization);
			modelandview.addObject("organization_list", organization_list);
			return modelandview;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Mahesh Kharote
	// Method purpose : Update Organization
	@RequestMapping(value = "/updateOrganization", method = RequestMethod.POST)
	public String updateOrganization(Organization organization1) {
		try {
			organizationService.updateOrganization(organization1);
			return "redirect:listOrganizations";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method purpose : Approve or disapprove organization
	@RequestMapping(value = "/approveDisapprove", method = RequestMethod.POST)
	public @ResponseBody String approveDisapprove(@RequestBody String jsonString) throws ParseException {
		JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString);
		int org_id = Integer.parseInt(jsonObj.get("orga_id").toString());
		int orga_status = Integer.parseInt(jsonObj.get("orga_status").toString());
		try {
			int res = organizationService.approveDisapprove(org_id, orga_status);
			return String.valueOf(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(0);
	}

	// Method Created : Harshad Padole
	// Method purpose : Enable or Disable organization
	@RequestMapping(value = "/enableDisable", method = RequestMethod.POST)
	public @ResponseBody String enableDisable(@RequestBody String jsonString) throws ParseException {
		JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonString);
		int org_id = Integer.parseInt(jsonObj.get("orga_id").toString());
		int orga_status = Integer.parseInt(jsonObj.get("orga_status").toString());
		try {
			int res = organizationService.enableDisable(org_id, orga_status);
			return String.valueOf(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(0);
	}

	// Method Created : Mugdha Chandratre
	// Method Purpose : Verify if Organization is exist or not
	@RequestMapping(value = "/isOrgaNameExist", method = RequestMethod.POST)
	public @ResponseBody String isOrgaNameExist(@RequestBody String jsonString) throws ParseException {

		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int orga_id = Integer.parseInt(jsonObject.get("orga_id").toString());
			String orga_name = jsonObject.get("orga_name").toString();
			int result = organizationService.isOrgaNameExist(orga_id, orga_name);
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created : Tejashri Zurunge
	// Method purpose : to check whether short name is exist or not
	@RequestMapping(value = "/isOrgaShortNameExist", method = RequestMethod.POST)
	public @ResponseBody String isOrgaShortNameExist(@RequestBody String jsonString) throws ParseException {

		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int orga_id = Integer.parseInt(jsonObject.get("orga_id").toString());
			String orga_short_name = jsonObject.get("orga_short_name").toString();
			int result = organizationService.isOrgaShortNameExist(orga_id, orga_short_name);
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
