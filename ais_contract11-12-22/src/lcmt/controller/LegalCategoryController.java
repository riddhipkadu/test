package lcmt.controller;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lcmt.domain.LegalCategory;
import lcmt.service.LegalCategoryService;

@Controller
@RequestMapping("/*")
public class LegalCategoryController {

	@Autowired
	LegalCategoryService legalCategoryService;
	@Autowired
	HttpSession httpSession;

	// Method Created : Tejashri Zurunge
	// Method purpose: add new Legal notice category
	@RequestMapping(value = "/addLegalNoticeCatagory", method = RequestMethod.GET)
	public ModelAndView addLegalNoticeCatagory() {
		try {
			ModelAndView addLegalNoticeCatagory = new ModelAndView("addLegalNoticeCatagory", "addLegalNoticeCatagory",
					new LegalCategory());
			return addLegalNoticeCatagory;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: Save Legal notice category
	@RequestMapping(value = "/saveLegalNoticeCategory", method = RequestMethod.POST)
	public String saveLegalNoticeCategory(LegalCategory legalCategory) {
		try {
			legalCategoryService.persist(legalCategory);
			return "redirect:listLegalNoticeCategory";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: listing and view Legal notice category
	@RequestMapping(value = "/listLegalNoticeCategory", method = RequestMethod.GET)
	public ModelAndView listLegalNoticeCategory(HttpSession httpSession) {

		try {
			ModelAndView modelAndView = new ModelAndView("listLegalNoticeCategory", "listLegalNoticeCategory",
					legalCategoryService.getAll());
			return modelAndView;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :Edit Legal notice category
	@RequestMapping(value = "/editLegalNoticeCategory", method = RequestMethod.GET)
	public ModelAndView editLegalNoticeCategory(int lega_noti_category_id) {
		try {
			ModelAndView modelAndView = new ModelAndView("editLegalNoticeCategory", "editLegalNoticeCategory",
					legalCategoryService.getLegalCategoryById(lega_noti_category_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :update Legal notice category
	@RequestMapping(value = "updateLegalNoticeCategory", method = RequestMethod.POST)
	public String updateLegalNoticeCategory(LegalCategory legalCategory) {
		try {
			legalCategoryService.updateLegalNoticeCategory(legalCategory);
			return "redirect:listLegalNoticeCategory";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :Check whether Legal notice category exist or not
	@RequestMapping(value = "isLegalNoticeCategoryExist", method = RequestMethod.POST)
	public @ResponseBody String isLegalNoticeCategoryExist(@RequestBody String jsonString) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int lega_noti_category_id = Integer.parseInt(jsonObject.get("lega_noti_category_id").toString());
			String lega_noti_category_name = jsonObject.get("lega_noti_category_name").toString();
			int result = legalCategoryService.isLegalNoticeCategoryExist(lega_noti_category_id,
					lega_noti_category_name);
			return String.valueOf(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :delete Legal notice category
	@RequestMapping(value = "/deleteLegalCategory", method = RequestMethod.POST)
	public @ResponseBody String deleteLegalCategory(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int lega_noti_category_id = Integer.parseInt(jsonObject.get("lega_noti_category_id").toString());
			int deleteCount = legalCategoryService.deleteLegalCategory(lega_noti_category_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Method Created :Tejashri Zurunge
	// Method purpose :delete Legal notice category
	@RequestMapping(value = "/checkDependancyLegalCategory", method = RequestMethod.POST)
	public @ResponseBody String checkDependancyLegalCategory(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int lega_noti_category_id = Integer.parseInt(jsonObject.get("lega_noti_category_id").toString());
			int deleteCount = legalCategoryService.checkDependancyLegalCategory(lega_noti_category_id);
			return String.valueOf(deleteCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : get all category in json format
	@RequestMapping(value = "/getAllLegalCategory", method = RequestMethod.POST)
	public @ResponseBody String getAllLegalCategory(HttpSession session) {
		try {
			return legalCategoryService.getAllLegalCategoryJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
