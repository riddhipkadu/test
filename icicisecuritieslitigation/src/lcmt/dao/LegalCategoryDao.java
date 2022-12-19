package lcmt.dao;
import java.util.List;

import lcmt.domain.LegalCategory;
public interface LegalCategoryDao {

	public void persist(Object obj);
	public <T> List<T> getAllLegalCategory(Class<T> clazz);
	public LegalCategory getLegalCategoryById(int lega_noti_category_id);
	public void updateLegalNoticeCategory(LegalCategory legalCategory);
	public int isLegalNoticeCategoryExist(int lega_noti_category_id, String lega_noti_category_name);
	public int deleteLegalCategory(int lega_noti_category_id);
	public int checkDependancyLegalCategory(int lega_noti_category_id);
}
