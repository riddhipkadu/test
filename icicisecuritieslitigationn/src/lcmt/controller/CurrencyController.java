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

import lcmt.domain.Currency;
import lcmt.service.CurrencyService;

@Controller
@RequestMapping("/*")

public class CurrencyController {
	@Autowired
	CurrencyService currencyService;

	// Method Created: Tejashri Zurunge
	// Method Purpose: Add currency
	@RequestMapping(value = "/addCurrency", method = RequestMethod.GET)
	public ModelAndView addCurrency() {
		try {
			ModelAndView modelAndView = new ModelAndView("addCurrency", "addCurrency", new Currency());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: Save Currency
	@RequestMapping(value = "/saveCurrency", method = RequestMethod.POST)
	public String saveCurrency(Currency currency, HttpSession session) {
		try {
			currencyService.persist(currency);
			return "redirect:listCurrency";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: listing and view Currency
	@RequestMapping(value = "/listCurrency", method = RequestMethod.GET)
	public ModelAndView listCurrency(HttpSession session) {

		try {
			ModelAndView modelAndView = new ModelAndView("listCurrency", "listCurrency", currencyService.getAll());
			return modelAndView;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created :Tejashri Zurunge
	// Method purpose :Edit Currency
	@RequestMapping(value = "/editCurrency", method = RequestMethod.GET)
	public ModelAndView editCurrency(int curr_id) {
		try {
			ModelAndView modelAndView = new ModelAndView("editCurrency", "editCurrency",
					currencyService.getCurrencyById(curr_id));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Update Currency
	@RequestMapping(value = "/updateCurrency", method = RequestMethod.POST)
	public String updateCurrency(Currency currency) {
		try {
			currencyService.updateCurrency(currency);
			return "redirect:listCurrency";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Check whether Currency code Exist or not
	@RequestMapping(value = "/isCurrencyCodeExist", method = RequestMethod.POST)
	public @ResponseBody String isCurrencyCodeExist(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int curr_id = Integer.parseInt(jsonObject.get("curr_id").toString());
			String curr_code = jsonObject.get("curr_code").toString();
			int result = currencyService.isCurrencyCodeExist(curr_id, curr_code);
			return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Currency
	@RequestMapping(value = "/deleteCurrency", method = RequestMethod.POST)
	public @ResponseBody String deleteCurrency(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
			int curr_id = Integer.parseInt(jsonObject.get("curr_id").toString());
			int deleteCount = currencyService.deleteCurrency(curr_id);
			return String.valueOf(deleteCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : get all currency in json format
	@RequestMapping(value = "/getAllCurrencyJson", method = RequestMethod.POST)
	public @ResponseBody String getAllCurrencyJson() throws ParseException {
		try {
			return currencyService.getAllCurrencyJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: check dependancy 	
	@RequestMapping(value ="/checkDependancyCurrency" , method = RequestMethod.POST)
	public @ResponseBody String checkDependancyCurrency(@RequestBody String json) throws ParseException{
		try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
			int curr_id = Integer.parseInt(jsonObj.get("curr_id").toString());
			String count = currencyService.checkDependancyCurrency(curr_id);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}