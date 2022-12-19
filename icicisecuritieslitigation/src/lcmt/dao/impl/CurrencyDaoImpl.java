package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.CurrencyDao;
import lcmt.domain.Currency;

@Repository(value = "currencyDao")
@Transactional
public class CurrencyDaoImpl implements CurrencyDao {

	@PersistenceContext
	EntityManager em;

	// Method Created: Tejashri Zurunge
	// Method Purpose: Save Currency
	@Override
	public void persist(Object object) {
		try {
			em.persist(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: List Currency
	@Override
	public <T> List<T> getAllCurrency(Class<T> clazz) {
		try {
			TypedQuery<T> query = em.createQuery(" from " + clazz.getName(), clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: Update Currency
	@Override
	public void updateCurrency(Currency currency) {
		try {
			em.merge(currency);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: Get Currency by Id
	@Override
	public Currency getCurrencyById(int curr_id) {
		try {
			String sql = "SELECT * FROM mst_currency where curr_id = " + curr_id;
			Query query = em.createNativeQuery(sql, Currency.class);
			if (!query.getResultList().isEmpty()) {
				return (Currency) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: check Currency is exist or not
	@Override
	public int isCurrencyCodeExist(int curr_id, String curr_code) {
		try {
			String sql = "select count(*) as scount from mst_currency where curr_code='" + curr_code + "' " + " ";
			if (curr_id != 0) {
				sql += " AND curr_id !=" + curr_id;
			}
			Query query = em.createNativeQuery(sql);
			String count = query.getResultList().get(0).toString();
			return Integer.parseInt(count);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: delete Currency
	@Override
	public int deleteCurrency(int curr_id) {
		try {
			String sql = "DELETE FROM mst_currency WHERE curr_id = " + curr_id;
			Query query = em.createNativeQuery(sql);
			int deleteCount = query.executeUpdate();
			// System.out.println("This is no of rows deleted:"+deleteCount);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: check dependancy 	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> checkDependancyCurrency(int curr_id) {
		try {
			String sql = " Select  case when liti.liti_involved_amt_currency = curr_code OR liti.liti_converted_amt_currency = curr_code then 1 else 0 END as liti_curr , "
					+ " case when advo.ladv_currency = curr_code OR advo.ladv_converted_currency = curr_code then 1 else 0 END as fees_advo, "
					+ " case when coun.lcou_currency = curr_code OR coun.lcou_converted_currency = curr_code then 1 else 0 END as fees_coun, "
					+ " case when advocate.apaid_currency_code = curr_code OR advocate.apaid_converted_currency = curr_code then 1 else 0 END as advo_paid, "
					+ " case when counsel.cpaid_currency_code = curr_code OR counsel.cpaid_converted_currency = curr_code then 1 else 0 END as coun_paid, "
					+ " case when lega.lega_noti_involved_amt_currency = curr_code OR lega.lega_noti_converted_amt_currency = curr_code then 1 else 0 END as lega_noti "
					+ " from mst_currency currency "
					+ " left join mst_litigation liti on currency.curr_code = liti.liti_involved_amt_currency OR currency.curr_code = liti.liti_converted_amt_currency "
					+ " left join trn_advocate_paid_fees advocate on currency.curr_code = advocate.apaid_currency_code OR currency.curr_code = advocate.apaid_converted_currency "
					+ " left join trn_litigation_advocate_fees advo on currency.curr_code = advo.ladv_converted_currency OR currency.curr_code = advo.ladv_currency "
					+ " left join trn_counsel_paid_fees counsel on currency.curr_code = counsel.cpaid_currency_code OR currency.curr_code = counsel.cpaid_converted_currency "
					+ " left join trn_litigation_counsel_fees coun on currency.curr_code = coun.lcou_currency OR currency.curr_code = coun.lcou_converted_currency "
					+ " left join mst_legal_notice lega on currency.curr_code = lega.lega_noti_involved_amt_currency OR currency.curr_code = lega.lega_noti_converted_amt_currency "
					+ " where currency.curr_id = "+curr_id+" limit 1";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
