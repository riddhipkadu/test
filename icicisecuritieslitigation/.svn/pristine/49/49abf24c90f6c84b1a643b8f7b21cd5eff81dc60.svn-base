package lcmt.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.InternalLitigationDao;
import lcmt.domain.InternalLitigation;
import lcmt.service.InternalLitigationService;
import lcmt.service.UtilitiesService;

@Service(value="internalLitigationService")
public class InternalLitigationServiceImpl implements InternalLitigationService {
	
	@Autowired
	UtilitiesService utilitiesService;
	
	@Autowired
	InternalLitigationDao internalLitigationDao;

	@Override
	public void persist(InternalLitigation internal, HttpSession session) {
		try {
			//System.out.println("internal details are :"+internal.getInternal_liti_code());
			internal.setInternal_liti_added_by(utilitiesService.getCurrentSessionUserId(session));
			internal.setInternal_liti_created_by(utilitiesService.getCurrentDate());
			internalLitigationDao.persist(internal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<InternalLitigation> getAll() {
		try {
			return internalLitigationDao.getAll(InternalLitigation.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public InternalLitigation getInternalLitiById(int inter_id) {
		try {
			return internalLitigationDao.getInternalLitiById(inter_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateInternalLitigation(InternalLitigation intern) {
		try {
			InternalLitigation oldLiti = internalLitigationDao.getInternalLitiById(intern.getInternal_liti_id());
			
			InternalLitigation newLiti = new InternalLitigation();
			newLiti.setInternal_liti_added_by(oldLiti.getInternal_liti_added_by());
			newLiti.setInternal_liti_created_by(oldLiti.getInternal_liti_created_by());
			newLiti.setInternal_liti_id(oldLiti.getInternal_liti_id());
			newLiti.setInternal_liti_code(intern.getInternal_liti_code());
			newLiti.setInternal_liti_desc(intern.getInternal_liti_desc());
			
			internalLitigationDao.updateInternalLitigation(newLiti);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int deleteInternalLitigation(int internal_liti_id) {
		try {
			return internalLitigationDao.deleteInternalLitigation(internal_liti_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int isLitigationCodeExist(String jsonString) {
		try {
			return internalLitigationDao.isLitigationCodeExist(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	

}
