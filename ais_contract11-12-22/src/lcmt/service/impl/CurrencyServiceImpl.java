package lcmt.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.CurrencyDao;
import lcmt.domain.Currency;
import lcmt.service.CurrencyService;
import lcmt.service.UtilitiesService;

@Service("currencyService")
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	UtilitiesService utilitiesService;

	@Autowired
	HttpSession session;

	@Autowired
	CurrencyDao currencyDao;

	// Method Created: Tejashri Zurunge
	// Method Purpose: Save Currency
	@Override
	public void persist(Currency currency) {
		try {
			currency.setCurr_added_by(utilitiesService.getCurrentSessionUserId(session));
			currency.setCurr_created_at(utilitiesService.getCurrentDate());
			currency.setCurr_updated_at(utilitiesService.getCurrentDate());
			currencyDao.persist(currency);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: Listing Currency
	@Override
	public List<Currency> getAll() {
		try {
			return currencyDao.getAllCurrency(Currency.class);
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
			Currency oldCurrency = getCurrencyById(currency.getCurr_id());
			Currency newCurrency = new Currency();
			// set old values
			newCurrency.setCurr_id(oldCurrency.getCurr_id());
			newCurrency.setCurr_added_by(oldCurrency.getCurr_added_by());
			newCurrency.setCurr_created_at(oldCurrency.getCurr_created_at());

			// set new values
			newCurrency.setCurr_code(currency.getCurr_code());
			newCurrency.setCurr_name(currency.getCurr_name());
			newCurrency.setCurr_rate(currency.getCurr_rate());
			newCurrency.setCurr_updated_at(utilitiesService.getCurrentDate());

			currencyDao.updateCurrency(newCurrency);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: Get Currency By Id
	@Override
	public Currency getCurrencyById(int curr_id) {
		try {
			return currencyDao.getCurrencyById(curr_id);
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
			return currencyDao.isCurrencyCodeExist(curr_id, curr_code);
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
			int deleteCount = currencyDao.deleteCurrency(curr_id);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created :Harshad Padole
	// Method Purpose : get all currency in json format
	@SuppressWarnings("unchecked")
	@Override
	public String getAllCurrencyJson() {
		try {
			JSONArray sendList = new JSONArray();
			List<Currency> currencies = currencyDao.getAllCurrency(Currency.class);
			Iterator<Currency> iterator = currencies.iterator();
			while (iterator.hasNext()) {
				Currency currency = iterator.next();
				JSONObject curr = new JSONObject();
				curr.put("label", currency.getCurr_code());
				curr.put("value", currency.getCurr_code());
				sendList.add(curr);
			}
			return sendList.toJSONString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: check dependancy 	
	@SuppressWarnings("unchecked")
	@Override
	public String checkDependancyCurrency(int curr_id) {
		try {
			JSONArray sendList = new JSONArray();
			List<Object> currency =  currencyDao.checkDependancyCurrency(curr_id);
			Iterator<Object> iterator = currency.iterator();
			while(iterator.hasNext()){
			JSONObject JSONObj = new JSONObject();	
			Object objects[] = (Object[]) iterator.next();
			JSONObj.put("liti_curr", objects[0]);
			JSONObj.put("fees_advo", objects[1]);
			JSONObj.put("fees_coun", objects[2]);
			JSONObj.put("advo_paid", objects[3]);
			JSONObj.put("coun_paid", objects[4]);
			JSONObj.put("lega_noti", objects[5]);
			
			sendList.add(JSONObj);
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
