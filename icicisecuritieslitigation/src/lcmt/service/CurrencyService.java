package lcmt.service;

import java.util.List;

import lcmt.domain.Currency;

public interface CurrencyService {

	public void persist(Currency currency);
	public List<Currency> getAll();
	public void updateCurrency(Currency currency);
	public Currency getCurrencyById(int curr_id);
	public int isCurrencyCodeExist(int curr_id, String curr_code);
	public int deleteCurrency(int curr_id);
	public String getAllCurrencyJson();
	public String checkDependancyCurrency(int curr_id);
}
