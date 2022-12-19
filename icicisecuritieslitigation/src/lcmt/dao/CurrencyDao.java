package lcmt.dao;

import java.util.List;

import lcmt.domain.Currency;

public interface CurrencyDao {

	public void persist(Object object);
	public <T> List<T> getAllCurrency(Class<T> clazz);
	public void updateCurrency(Currency currency);
	public Currency getCurrencyById(int curr_id);
	public int isCurrencyCodeExist(int curr_id, String curr_code);
	public int deleteCurrency(int curr_id);
	public List<Object> checkDependancyCurrency(int curr_id);
	
}
