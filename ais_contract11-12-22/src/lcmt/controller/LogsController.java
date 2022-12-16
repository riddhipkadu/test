package lcmt.controller;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lcmt.service.LogsService;

@Controller
@RequestMapping("/*")
public class LogsController {
	@Autowired
	LogsService logsService;

	// Method Created : Tejashri Zurunge
	// Method purpose : list of All Logs
	@RequestMapping(value = "/listLogs", method = RequestMethod.GET)
	public ModelAndView listLogs() {
		try {
			ModelAndView modelAndView = new ModelAndView("listLogs");
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : search All Logs
	@RequestMapping(value = "/searchLogs", method = RequestMethod.POST)
	public @ResponseBody String searchLogs(@RequestBody String json) throws ParseException {
		try {
			String result = logsService.searchLogs(json);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : get list of All Logs
	@RequestMapping(value = "/getActivityLogs")
	public @ResponseBody String getActivityLogs(@RequestBody String json) {
		try {
			String result = logsService.getActivityLogs(json);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
