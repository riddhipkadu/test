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

import lcmt.domain.ArcManager;
import lcmt.domain.ARCname;
import lcmt.service.ARCnameService;
import lcmt.service.ArcManagerService;


@Controller
@RequestMapping("/*")
public class ArcManagerController {
	@Autowired
	ArcManagerService arcManagerService;
	
	@Autowired
	ARCnameService arcNameService;
	
	     //Method Created : Pranjali Kawale
		//Method Purpose : list Asset Reconstruction Company Manager
		@RequestMapping(value = "/listArcManager", method = RequestMethod.GET)
		public ModelAndView listArcManager(HttpSession session){
			try {
				
				ModelAndView modelAndView = new ModelAndView("listArcManager","listArcManager",arcManagerService.getAllArcManager());
				/* modelAndView.addObject("listArcManager", arcNameService.getAllArcName());*/
				return modelAndView;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
	
	 //Method Created : Pranjali Kawale
	//Method Purpose : add Asset Reconstruction Company Manager Name
				
	@RequestMapping(value="/addArcManager", method= RequestMethod.GET)
	public ModelAndView addArcManager(){
		try {
			    ModelAndView modelAndView = new ModelAndView("addArcManager","addArcManager",new ArcManager());
			    modelAndView.addObject("allArcMgr", arcNameService.getAllArcName());
					 return modelAndView;
			} catch (Exception e) {
						e.printStackTrace();
				}
				return null;
			}
	
	//Method Created :  Pranjali Kawale
	//Method Purpose : Save Asset Reconstruction Company Manager Name
	@RequestMapping(value = "/saveArcManager", method = RequestMethod.POST)
	public String saveArcManager(ArcManager arcManager){
		try {
			arcManagerService.persist(arcManager);
			return "redirect:listArcManager";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//Method Created : Pranjali Kawale
	//Method Purpose : edit the Asset Reconstruction Company Manager Name
	@RequestMapping(value = "/editArcManager", method = RequestMethod.GET)
	public ModelAndView editArcManager(int mgr_arc_id){
		try {
			ModelAndView modelAndView = new ModelAndView("editArcManager","editArcManager",arcManagerService.getArcManagerById(mgr_arc_id));
			 modelAndView.addObject("allArcMgr", arcNameService.getAllArcName());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Method Created : Pranjali Kawale
	//Method Purpose : update the new Asset Reconstruction Company Manager Name
	@RequestMapping(value="/updateArcManager",method = RequestMethod.POST)
	public String updateArcManager(ArcManager arcmgr){
		try {
			arcManagerService.updateArcManager(arcmgr);
			return "redirect:listArcManager";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	// Method Created : Pranjali Kawale
	// Method Purpose : Delete Asset Reconstruction Company Manager
	@RequestMapping(value = "/deleteArcManager", method = RequestMethod.POST)
	public @ResponseBody String deleteArcManager(@RequestBody String jsonString) throws ParseException {
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(jsonString);
			int mgr_arc_id = Integer.parseInt(jsonobj.get("mgr_arc_id").toString());
			int deleteCount = arcManagerService.deleteArcManager(mgr_arc_id);
			return String.valueOf(deleteCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Method Created : Pranjali Kawale
	//Method Purpose : To check the Asset Reconstruction Company Manager exist or not
			
	@RequestMapping(value="/isArcManagerExist" , method = RequestMethod.POST)
	public @ResponseBody String isArcManagerExist(@RequestBody String jsonString) throws ParseException {
		try {
			   JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
		       int mgr_arc_id = Integer.parseInt(jsonObject.get("mgr_arc_id").toString());
		       String mgr_name = jsonObject.get("mgr_name").toString();
		       int result=arcManagerService.isArcManagerExist(mgr_arc_id, mgr_name);
			   return String.valueOf(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	       
		return null;
		}
	             // Method Created : Pranjali Kawale
				// Method Purpose : Check Whether ARC Manager is used in other module before
				// delete it
				@RequestMapping(value = "/checkArcManagerDependancy", method = RequestMethod.POST)
				public @ResponseBody String checkArcManagerDependancy(@RequestBody String json) throws ParseException {
					try {
						
						JSONObject jsonobj = (JSONObject) new JSONParser().parse(json);
						int mgr_arc_id = Integer.parseInt(jsonobj.get("mgr_arc_id").toString());
						String deleteCount =arcManagerService.checkArcManagerDependancy(mgr_arc_id); 
						return deleteCount;
					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;
				}
				
				//Method Created : Pranjali Kawale
				//Method Purpose : To check the Email Id Name exist or not
						
				@RequestMapping(value="/isARCManagerEmailExist" , method = RequestMethod.POST)
				public @ResponseBody String isARCManagerEmailExist(@RequestBody String jsonString) throws ParseException {
					try {
						   JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
					       int arc_id = Integer.parseInt(jsonObject.get("arc_id").toString());
					       String mgr_arc_email_id = jsonObject.get("mgr_arc_email_id").toString();
					       int result=arcManagerService.isARCManagerEmailExist(arc_id, mgr_arc_email_id);
					      
						   return String.valueOf(result);
					} catch (Exception e) {
						e.printStackTrace();
					}
				       
					return null;
					}

}
