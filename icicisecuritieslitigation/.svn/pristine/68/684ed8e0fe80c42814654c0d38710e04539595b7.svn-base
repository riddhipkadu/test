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
import lcmt.domain.ARCname;
import lcmt.service.ARCnameService;

@Controller
@RequestMapping("/*")
public class ARCnameController {
	
	@Autowired
	ARCnameService arcNameService;
	
	
	        //Method Created : Pranjali Kawale
			//Method Purpose : add Asset Reconstruction Company Name
			
			@RequestMapping(value="/addARCname", method= RequestMethod.GET)
			public ModelAndView addARCname(){
				try {
					ModelAndView modelAndView = new ModelAndView("addARCname","addARCname",new ARCname());
					return modelAndView;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			//Method Created : Pranjali Kawale
			//Method Purpose : list Asset Reconstruction Company Name
			@RequestMapping(value = "/listARCname", method = RequestMethod.GET)
			public ModelAndView listARCname(HttpSession session){
				try {
					
					ModelAndView modelAndView = new ModelAndView("listARCname","listARCname",arcNameService.getAllARCname());
					return modelAndView;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			
			//Method Created :  Pranjali Kawale
			//Method Purpose : Save Asset Reconstruction Company Name
			@RequestMapping(value = "/saveARCname", method = RequestMethod.POST)
			public String saveARCname(ARCname arcName){
				try {
					arcNameService.persist(arcName);
					return "redirect:listARCname";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			
			//Method Created : Pranjali Kawale
			//Method Purpose : edit the Asset Reconstruction Company Name
			@RequestMapping(value = "/editARCname", method = RequestMethod.GET)
			public ModelAndView editARCname(int arc_id){
				try {
					ModelAndView modelAndView = new ModelAndView("editARCname","editARCname",arcNameService.getARCnameById(arc_id));
					return modelAndView;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			//Method Created : Pranjali Kawale
			//Method Purpose : update the new Asset Reconstruction Company Name
			@RequestMapping(value="/updateARCname",method = RequestMethod.POST)
			public String updateARCname(ARCname arcName){
				try {
					arcNameService.updateARCname(arcName);
					return "redirect:listARCname";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			// Method Created : Pranjali Kawale
			// Method Purpose : Delete Asset Reconstruction Company Name
			@RequestMapping(value = "/deleteARCname", method = RequestMethod.POST)
			public @ResponseBody String deleteARCname(@RequestBody String jsonString) throws ParseException {
				try {
					JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
					int arc_id = Integer.parseInt(jsonobj.get("arc_id").toString());
					int deleteCount = arcNameService.deleteARCname(arc_id);
					return String.valueOf(deleteCount);

				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			//Method Created : Pranjali Kawale
			//Method Purpose : To check the Asset Reconstruction Company Name exist or not
					
			@RequestMapping(value="/isARCNameExist" , method = RequestMethod.POST)
			public @ResponseBody String isARCNameExist(@RequestBody String jsonString) throws ParseException {
				try {
					   JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
				       int arc_id = Integer.parseInt(jsonObject.get("arc_id").toString());
				       String arc_name = jsonObject.get("arc_name").toString();
				       int result=arcNameService.isARCNameExist(arc_id, arc_name);
					   return String.valueOf(result);
				} catch (Exception e) {
					e.printStackTrace();
				}
			       
				return null;
				}
			
			// Method Created :  Pranjali Kawale
			// Method Purpose : get all Asset Reconstruction Company Name  as json
			@RequestMapping(value = "/getAllArcManagerJson", method = RequestMethod.POST)
			public @ResponseBody String getAllArcManagerJson(HttpSession session) {
				try {
					return arcNameService.getAllArcManagerJson();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;

			}
			
			// Method Created : Pranjali Kawale
			// Method Purpose : Check Whether ARC name is used in other module before
			// delete it
			@RequestMapping(value = "/checkARCnameDependancy", method = RequestMethod.POST)
			public @ResponseBody String checkARCnameDependancy(@RequestBody String json) throws ParseException {
				try {
					JSONObject jsonobj = (JSONObject) new JSONParser().parse(json);
					int arc_id = Integer.parseInt(jsonobj.get("arc_id").toString());
					String deleteCount = arcNameService.checkARCnameDependancy(arc_id);
					return deleteCount;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			//Method Created : Pranjali Kawale
			//Method Purpose : To check the Email Id Name exist or not
					
			@RequestMapping(value="/isARCNameEmailExist" , method = RequestMethod.POST)
			public @ResponseBody String isARCNameEmailExist(@RequestBody String jsonString) throws ParseException {
				try {

					   JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
				       int arc_id = Integer.parseInt(jsonObject.get("arc_id").toString());
				       String arc_email_id = jsonObject.get("arc_email_id").toString();
				       int result=arcNameService.isARCNameEmailExist(arc_id, arc_email_id);
				      
					   return String.valueOf(result);
				} catch (Exception e) {
					e.printStackTrace();
				}
			       
				return null;
				}

}
