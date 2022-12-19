package lcmt.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.ARCnameDao;
import lcmt.domain.ARCname;
import lcmt.domain.LawFirm;
import lcmt.service.ARCnameService;
import lcmt.service.UtilitiesService;

@Service("ARCnameService")
public class ARCnameServiceImpl implements ARCnameService {
	@Autowired
	ARCnameDao arcNameDao;
	
	@Autowired
	UtilitiesService utilitiService;
	
	@Autowired
	HttpSession session;
	

//Method Created :  Pranjali Kawale
//Method Purpose : Save Asset Reconstruction Company Name
	@Override
	public void persist(ARCname arcName) {
		// TODO Auto-generated method stub
		try {
			
		arcName.setArc_added_by(utilitiService.getCurrentSessionUserId(session));
		arcName.setArc_created_at(utilitiService.getCurrentDate());
		arcName.setArc_updated_at(utilitiService.getCurrentDate());
		arcNameDao.persist(arcName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	//Method Created : Pranjali Kawale
	//Method Purpose : get All Asset Reconstruction Company Name
	@Override
	public List<ARCname> getAllARCname() {
		// TODO Auto-generated method stub
		try {
			return arcNameDao.getAllARCname(ARCname.class);
		} catch (Exception e) {
			e.printStackTrace();				
		}
		return null;
	}

//Method Created : Pranjali Kawale
//Method Purpose : get the Asset Reconstruction Company Name by id
	@Override
	public ARCname getARCnameById(int arc_id) {
		// TODO Auto-generated method stub
		try {
			return arcNameDao.getARCnameById(arc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//Method Created : Pranjali Kawale
//Method Purpose : update the new Asset Reconstruction Company Name
	@Override
	public void updateARCname(ARCname arcName) {
		// TODO Auto-generated method stub
		try {
			ARCname oldARCname=getARCnameById(arcName.getArc_id());
			
			ARCname newARCname=new ARCname();
			
			//Set old ARC name data to new ARC name 
			newARCname.setArc_id(oldARCname.getArc_id());
			newARCname.setArc_added_by(oldARCname.getArc_added_by());
			newARCname.setArc_created_at(oldARCname.getArc_created_at());
			
			//Set new data to new ARC name object
			newARCname.setArc_name(arcName.getArc_name());
			newARCname.setArc_contact_person(arcName.getArc_contact_person());
			newARCname.setArc_contact_no(arcName.getArc_contact_no());
			newARCname.setArc_email_id(arcName.getArc_email_id());
			newARCname.setArc_address(arcName.getArc_address());
			arcNameDao.updateARCname(newARCname);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

// Method Created : Pranjali Kawale
// Method Purpose : Delete Asset Reconstruction Company Name	
	@Override
	public int deleteARCname(int arc_id) {
		// TODO Auto-generated method stub
		try {
			int deleteCount = arcNameDao.deleteARCname(arc_id);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

//Method Created : Pranjali Kawale
//Method Purpose : To check the Asset Reconstruction Company Name exist or not	
	@Override
	public int isARCNameExist(int arc_id, String arc_name) {
		// TODO Auto-generated method stub
		try {
			return arcNameDao.isARCNameExist(arc_id, arc_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
//Method Created : Pranjali Kawale
//Method Purpose : get All Asset Reconstruction Company in json	
	@SuppressWarnings("unchecked")
	@Override
	public String getAllArcManagerJson() {
		// TODO Auto-generated method stub
		try {
			 JSONArray sendList = new JSONArray();
			 List<ARCname> ArcName = arcNameDao.getAllARCname(ARCname.class);
			 Iterator<ARCname> iterator = ArcName.iterator();
			 while(iterator.hasNext()){
				 ARCname arcName = iterator.next();
				 
				 JSONObject jsonObject = new JSONObject();
				 jsonObject.put("label",arcName.getArc_name());
				 jsonObject.put("value",arcName.getArc_name());
				 sendList.add(jsonObject);
			 }
			 return sendList.toJSONString();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//Method Created : Pranjali Kawale
//Method Purpose : get All Asset Reconstruction Company 	
	@Override
	public Map<Integer, String> getAllArcName() {
		// TODO Auto-generated method stub
		try {
			List<ARCname> result = arcNameDao.getAllARCname(ARCname.class);
			Map<Integer,String> sendList = new HashMap<Integer,String>();
			sendList.put(0, "--Select--");
			for(ARCname list:result){
				sendList.put(list.getArc_id(), list.getArc_name());
			}
			return sendList;
			
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String checkARCnameDependancy(int arc_id) {
		// TODO Auto-generated method stub
		try {
			
		JSONArray sendList=new JSONArray();
		List<Object> result=arcNameDao.checkARCnameDependancy(arc_id);
		Iterator<Object> iterator=result.iterator();
		while(iterator.hasNext())
			{
			Object object[] = (Object[]) iterator.next();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("sarf_arc_name", Integer.parseInt(object[0].toString()));
			jsonObject.put("mgr_arc_name", Integer.parseInt(object[1].toString()));
			sendList.add(jsonObject);
			}
		return sendList.toJSONString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int isARCNameEmailExist(int arc_id, String arc_email_id) {
		// TODO Auto-generated method stub
		try {
		return arcNameDao.isARCNameEmailExist(arc_id, arc_email_id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

}
