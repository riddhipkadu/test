package lcmt.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lcmt.dao.LegalCategoryDao;
import lcmt.domain.LegalCategory;
import lcmt.domain.LitigationType;
import lcmt.service.LegalCategoryService;
import lcmt.service.UtilitiesService;

@Service("legalCategoryService")
public class LegalCategoryServiceImpl implements LegalCategoryService {

	@Autowired
	UtilitiesService utilitiesService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	LegalCategoryDao legalCategoryDao;

	// Method Created :Tejashri Zurunge
	// Method purpose :add Legal notice category
	@Override
	public void persist(LegalCategory legalCategory) {
		try {
			legalCategory.setLega_noti_category_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
			legalCategory.setLega_noti_category_created_at(utilitiesService.getCurrentDate());
			legalCategory.setLega_noti_category_updated_at(utilitiesService.getCurrentDate());
			legalCategoryDao.persist(legalCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :list Legal notice category
	@Override
	public List<LegalCategory> getAll() {
		try {
			return legalCategoryDao.getAllLegalCategory(LegalCategory.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :get Legal notice category by Id
	@Override
	public LegalCategory getLegalCategoryById(int lega_noti_category_id) {
		try {
			return legalCategoryDao.getLegalCategoryById(lega_noti_category_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :update Legal notice category
	@Override
	public void updateLegalNoticeCategory(LegalCategory legalCategory) {
		try {
			LegalCategory oldLegalCategory = getLegalCategoryById(legalCategory.getLega_noti_category_id());
			LegalCategory newLegalCategory = new LegalCategory();

			newLegalCategory.setLega_noti_category_id(oldLegalCategory.getLega_noti_category_id());
			newLegalCategory.setLega_noti_category_added_by(oldLegalCategory.getLega_noti_category_added_by());
			newLegalCategory.setLega_noti_category_created_at(oldLegalCategory.getLega_noti_category_created_at());
			newLegalCategory.setLega_noti_category_updated_at(utilitiesService.getCurrentDate());

			newLegalCategory.setLega_noti_category_name(legalCategory.getLega_noti_category_name());
			legalCategoryDao.updateLegalNoticeCategory(newLegalCategory);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :check whether Legal notice category exist or not
	@Override
	public int isLegalNoticeCategoryExist(int lega_noti_category_id, String lega_noti_category_name) {
		try {
			return legalCategoryDao.isLegalNoticeCategoryExist(lega_noti_category_id, lega_noti_category_name);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :delete Legal notice category
	@Override
	public int deleteLegalCategory(int lega_noti_category_id) {
		try {
			int deleteCount = legalCategoryDao.deleteLegalCategory(lega_noti_category_id);
			return deleteCount;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return 0;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :delete Legal notice category
	@Override
	public int checkDependancyLegalCategory(int lega_noti_category_id) {
		try {
			return legalCategoryDao.checkDependancyLegalCategory(lega_noti_category_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String getAllLegalCategoryJson() {
		try {
			 JSONArray sendList = new JSONArray();
			 List<LegalCategory> res = legalCategoryDao.getAllLegalCategory(LegalCategory.class);
			 //System.out.println("Count "+res.size());
			 Iterator<LegalCategory> iterator = res.iterator();
			 while(iterator.hasNext()){
				 LegalCategory category = iterator.next();
				 
				 JSONObject jsonObject = new JSONObject();
				 jsonObject.put("label",category.getLega_noti_category_name());
				 jsonObject.put("value",category.getLega_noti_category_name());
				 sendList.add(jsonObject);
			 }
			 return sendList.toJSONString();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
