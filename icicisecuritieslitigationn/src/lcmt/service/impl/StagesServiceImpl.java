package lcmt.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.StagesDao;
import lcmt.domain.Stages;
import lcmt.service.StagesService;
import lcmt.service.UtilitiesService;

@Service("stagesService")
public class StagesServiceImpl implements StagesService {

	@Autowired
	UtilitiesService utilitiService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	StagesDao stagesDao;

	// Method Created By: Tejashri Zurunge
	// Method Purpose: Add New Stage
	@Override
	public void persist(Stages stages) {
		try {
			stages.setStage_added_by(utilitiService.getCurrentSessionUserId(httpSession));
			stages.setStage_created_at(utilitiService.getCurrentDate());
			stages.setStage_updates_at(utilitiService.getCurrentDate());
			stagesDao.persist(stages);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: listing and view Stage
	@Override
	public List<Stages> getAll() {
		try {
			return stagesDao.getAllStages(Stages.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: listing Stage by Id
	@Override
	public Stages getStagesById(int stage_id) {
		try {
			return stagesDao.getStagesById(stage_id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Update Stages
	@Override
	public void updateStages(Stages stages) {
		try {

			Stages oldStages = getStagesById(stages.getStage_id());

			Stages newStages = new Stages();

			// Set old AreaOfExpertise data to new AreaOfExpertise
			newStages.setStage_id(oldStages.getStage_id());
			newStages.setStage_added_by(oldStages.getStage_added_by());
			newStages.setStage_created_at(oldStages.getStage_created_at());
			newStages.setStage_updates_at(utilitiService.getCurrentDate());

			// Set new data to newAreaOfExpertise object
			newStages.setStage_name(stages.getStage_name());

			stagesDao.updateStages(newStages);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Check whether Stages Exist or not
	@Override
	public int isStagesNameExist(int stage_id, String stage_name) {
		try {
			return stagesDao.isStagesNameExist(stage_id, stage_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Delete Stages
	@Override
	public int deleteStages(int stage_id) {

		try {
			int deleteCount = stagesDao.deleteStages(stage_id);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public int checkDependancyStages(int stage_id) {
		try {
			int deleteCount = stagesDao.checkDependancyStages(stage_id);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAllStagesJson() {
		try {
			JSONArray sendList = new JSONArray();
			 List<Stages> stage = stagesDao.getAllStages(Stages.class);
			 //System.out.println("Count "+res.size());
			 Iterator<Stages> iterator = stage.iterator();
			 while(iterator.hasNext()){
				Stages stages = iterator.next();
				 
				 JSONObject jsonObject = new JSONObject();
				 jsonObject.put("label",stages.getStage_name());
				 jsonObject.put("value",stages.getStage_name());
				 sendList.add(jsonObject);
			 }
			 return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
