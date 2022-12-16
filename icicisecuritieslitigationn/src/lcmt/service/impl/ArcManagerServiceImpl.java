package lcmt.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.ArcManagerDao;
import lcmt.domain.ArcManager;
import lcmt.domain.ArcManagerRef;
import lcmt.service.ArcManagerService;
import lcmt.service.UtilitiesService;

@Service("ARCmanagerService")
public class ArcManagerServiceImpl implements ArcManagerService {
	@Autowired 
	ArcManagerDao arcManagerDao;
	@Autowired
	UtilitiesService utilitiService;
	
	@Autowired
	HttpSession session;
	
    @Override
	public List<ArcManagerRef> getAllArcManager() {
    	
    	try {
			List<Object> arcMgr = arcManagerDao.getAllArcManager();

			List<ArcManagerRef> SendList = new ArrayList<>();

			Iterator<Object> iterator = arcMgr.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				ArcManagerRef arcMangerRef = new ArcManagerRef();
				arcMangerRef.setMgr_arc_id(Integer.parseInt(objects[0].toString()));
				arcMangerRef.setMgr_name(objects[1].toString());
				arcMangerRef.setArc_id(Integer.parseInt(objects[2].toString()));
				
				if(objects[3] == null)
					arcMangerRef.setMgr_arc_contact_no("NA");
				else
					arcMangerRef.setMgr_arc_contact_no(objects[3].toString());
				if(objects[4] == null)
					arcMangerRef.setMgr_arc_email_id("NA");
				else
					arcMangerRef.setMgr_arc_email_id(objects[4].toString());
				
				if(objects[5] == null)
					arcMangerRef.setMgr_arc_address("NA");
					else
						arcMangerRef.setMgr_arc_address(objects[5].toString());
				
				arcMangerRef.setMgr_arc_name(objects[6].toString());
				SendList.add(arcMangerRef);
				
			}

			return SendList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void persist(ArcManager arcManager) {
		// TODO Auto-generated method stub
	  try {
		
	    arcManager.setMgr_arc_added_by(utilitiService.getCurrentSessionUserId(session));
		arcManager.setMgr_arc_created_at(utilitiService.getCurrentDate());
		arcManager.setMgr_arc_updated_at(utilitiService.getCurrentDate());
		
		arcManagerDao.persist(arcManager);
	    } catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
		}		
		
	}

	@Override
	public ArcManager getArcManagerById(int mgr_arc_id) {
		// TODO Auto-generated method stub
		try {
		return arcManagerDao.getArcManagerById(mgr_arc_id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateArcManager(ArcManager arcManager) {
		// TODO Auto-generated method stub
		try {
			ArcManager oldArcMgr=getArcManagerById(arcManager.getMgr_arc_id());
			
			ArcManager newArcMgr=new ArcManager();
			
			//Set old ARC name data to new ARC name 
			newArcMgr.setMgr_arc_enable_status(oldArcMgr.getMgr_arc_enable_status());
			newArcMgr.setMgr_arc_approval_status(oldArcMgr.getMgr_arc_approval_status());
			newArcMgr.setMgr_arc_added_by(oldArcMgr.getMgr_arc_added_by());
			newArcMgr.setMgr_arc_created_at(oldArcMgr.getMgr_arc_created_at());
			
		    //Set new data to new ARC name object
			newArcMgr.setMgr_arc_id(arcManager.getMgr_arc_id());
			newArcMgr.setMgr_name(arcManager.getMgr_name());
			newArcMgr.setMgr_arc_name(arcManager.getMgr_arc_name());
			newArcMgr.setMgr_arc_contact_no(arcManager.getMgr_arc_contact_no());
			newArcMgr.setMgr_arc_email_id(arcManager.getMgr_arc_email_id());
			newArcMgr.setMgr_arc_address(arcManager.getMgr_arc_address());
			
			arcManagerDao.updateArcManager(newArcMgr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int deleteArcManager(int mgr_id) {
		// TODO Auto-generated method stub
		try {
			int deleteCount= arcManagerDao.deleteArcManager(mgr_id);
			return deleteCount;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int isArcManagerExist(int mgr_arc_id, String mgr_name) {
		// TODO Auto-generated method stub
		try {
		   return arcManagerDao.isArcManagerExist(mgr_arc_id, mgr_name);
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String checkArcManagerDependancy(int mgr_id) {
		// TODO Auto-generated method stub
		try {
			
		int result=arcManagerDao.checkArcManagerDependancy(mgr_id);
		return  String.valueOf(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public int isARCManagerEmailExist(int arc_id, String mgr_arc_email_id) {
		// TODO Auto-generated method stub
		try {
			
		return arcManagerDao.isARCManagerEmailExist(arc_id, mgr_arc_email_id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
}
