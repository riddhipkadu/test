package lcmt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lcmt.service.CalendarService;
import lcmt.service.EntityMappingService;

@Controller
@RequestMapping("/*")
public class CalendarController {

	@Autowired
	CalendarService calendarService;
	@Autowired
	EntityMappingService entityMappingService;
	
	//Method Created : Harshad Padole
	//Method Purpose : Get litigation for calendar
	@RequestMapping(value="/showLitigationCalendar",method = RequestMethod.GET)
	public ModelAndView showLitigationCalendar(HttpSession session){
		try {
			ModelAndView modelAndView = new ModelAndView("LitigationCalendar","Liti_details",calendarService.getLitigationForCalendar(session));
			modelAndView.addObject("allOrganizations", entityMappingService.getOrganizationByUserId(session));
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	// Method Created : Harshad Padole
	// Method Purpose : Search litigation for calendar
	@RequestMapping(value = "/searchLitigationForCalendar", method = RequestMethod.POST)
	public @ResponseBody String searchLitigationForCalendar(@RequestBody String json,HttpSession session) {
		try {
			return calendarService.searchLitigationForCalendar(json,session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
