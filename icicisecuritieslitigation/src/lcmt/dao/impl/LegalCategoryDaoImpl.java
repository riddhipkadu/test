package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import lcmt.dao.LegalCategoryDao;
import lcmt.domain.LegalCategory;

@Repository(value = "legalCategoryDao")
@Transactional
public class LegalCategoryDaoImpl implements LegalCategoryDao {

	@PersistenceContext
	EntityManager em;

	// Method Created :Tejashri Zurunge
	// Method purpose :Add Legal notice category
	@Override
	public void persist(Object obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :list Legal notice category
	@Override
	public <T> List<T> getAllLegalCategory(Class<T> clazz) {
		try {
			TypedQuery<T> query = em.createQuery("from " + clazz.getName(), clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :Get Legal notice category by Id
	@Override
	public LegalCategory getLegalCategoryById(int lega_noti_category_id) {
		try {
			String sql = "SELECT * FROM mst_legal_notice_category where lega_noti_category_id = "
					+ lega_noti_category_id;
			Query query = em.createNativeQuery(sql, LegalCategory.class);
			if (!query.getResultList().isEmpty()) {
				return (LegalCategory) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :Update Legal notice category
	@Override
	public void updateLegalNoticeCategory(LegalCategory legalCategory) {
		try {
			em.merge(legalCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created :Tejashri Zurunge
	// Method purpose :check Legal notice category exist or not
	@Override
	public int isLegalNoticeCategoryExist(int lega_noti_category_id, String lega_noti_category_name) {
		try {
			String sql = "Select count(*) as legacount from mst_legal_notice_category where lega_noti_category_name = '"
					+ lega_noti_category_name + "'" + "";

			if (lega_noti_category_id != 0) {
				sql += " AND lega_noti_category_id != " + lega_noti_category_id;
			}

			Query query = em.createNativeQuery(sql);
			String count = query.getResultList().get(0).toString();
			return Integer.parseInt(count);

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
			String sql = "DELETE FROM mst_legal_notice_category WHERE lega_noti_category_id = "
					+ lega_noti_category_id;
			Query query = em.createNativeQuery(sql);
			int deleteCount = query.executeUpdate();
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int checkDependancyLegalCategory(int lega_noti_category_id) {
		try {
			String sql = " Select TOP 1 case when lega.lega_noti_category_id > 0 then 1 else 0 END as legalCategory from mst_legal_notice_category category "
					+ " full join mst_legal_notice lega on category.lega_noti_category_id = lega.lega_noti_category_id "
					+ " where category.lega_noti_category_id = "+lega_noti_category_id;
			Query query = em.createNativeQuery(sql);
			return Integer.parseInt(query.getResultList().get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
