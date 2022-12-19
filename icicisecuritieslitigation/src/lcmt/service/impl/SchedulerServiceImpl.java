package lcmt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcmt.dao.SchedulerDao;
import lcmt.domain.LitigationEscalationMailId;
import lcmt.domain.User;
import lcmt.service.SchedulerService;

@Service("schedulerService")
public class SchedulerServiceImpl implements SchedulerService {

	@Autowired
	SchedulerDao schedulerDao;

	// Method created by : Tejashri Zurunge
	// Method purpose : get query assigned to person
	@Override
	public List<Object> getReminderQueryMailToAssignPerson(int assign_to) {
		try {
			return schedulerDao.getReminderQueryMailToAssignPerson(assign_to);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created by : Tejashri Zurunge
	// Method purpose : get assign person according to reminder date
	@Override
	public List<Object> getAssignPersonFromReminderDate() {
		try {
			return schedulerDao.getAssignPersonFromReminderDate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created by : Tejashri Zurunge
	// Method purpose : get hearing stage details assigned to person
	@Override
	public List<Object> getAlertHearingStageMailToAssignPerson(int resp_person_id) {
		try {
			return schedulerDao.getAlertHearingStageMailToAssignPerson(resp_person_id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created by : Tejashri Zurunge
	// Method purpose : get assign person according to alert date of hearing
	// stage
	@Override
	public List<Object> getAssignPersonFromAlertDate() {
		try {
			return schedulerDao.getAssignPersonFromAlertDate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Object> getAssignPersonLegalNoticeFromReminderDate() {
		try {
			return schedulerDao.getAssignPersonLegalNoticeFromReminderDate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getReminderLegalNoticeMailToAssignPerson(int matter_handled_by_id) {
		try {
			return schedulerDao.getReminderLegalNoticeMailToAssignPerson(matter_handled_by_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getAssignedPersonLegalnoticeStatus() {
		try {
			return schedulerDao.getAssignedPersonLegalnoticeStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getReminderLegalNoticeStatusMailToAssignPerson(int id) {
		try {
			return schedulerDao.getReminderLegalNoticeStatusMailToAssignPerson(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getLitigationDetailsForReport() {
		try {
			 return schedulerDao.getLitigationDetailsForReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getAllAdminUser() {
		try {
			return schedulerDao.getAllAdminUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getQueryDetailsForReport() {
		try {
			return schedulerDao.getQueryDetailsForReport();
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public List<Object> getLegalNoticeDetailsForReport() {
		try {
			return schedulerDao.getLegalNoticeDetailsForReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getContractDetailsForReport() {
		try {
			return schedulerDao.getContractDetailsForReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getExecutedContractDetailsForReport() {
		try {
			return schedulerDao.getExecutedContractDetailsForReport(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getAssignPersonTaskCompletionFromReminderDate() {
		try {
			return schedulerDao.getAssignPersonTaskCompletionFromReminderDate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getReminderTaskCompletionMailToAssignPerson(int task_assigned_id) {
		try {
			return schedulerDao.getReminderTaskCompletionMailToAssignPerson(task_assigned_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void autoActivateActionItemTask() {
		try {
			schedulerDao.autoActivateActionItemTask();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> List<T> getActionItemCurrentMonth() {
		try {
			return schedulerDao.getActionItemCurrentMonth();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getContractSPOCuser() {
		try {
			return schedulerDao.getContractSPOCuser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getAssignPersonFrom3rdAlertDate() {
		try {
			return schedulerDao.getAssignPersonFrom3rdAlertDate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> get3rdAlertHearingStageMailToAssignPerson(int matter_handled_by_id) {
		try {
			return schedulerDao.get3rdAlertHearingStageMailToAssignPerson(matter_handled_by_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getLitigationSPOCuser() {
	 try {
		return schedulerDao.getLitigationSPOCuser();
	} catch (Exception e) {
		e.printStackTrace();
	}
	 return null;
	}

	@Override
	public List<User> getAllSPOCuser() {
		try {
			return schedulerDao.getAllSPOCuser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getHearingStageOnHearing() {
		try {
			return schedulerDao.getHearingStageOnHearing();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LitigationEscalationMailId getLawFirmLitigationMailId(int liti_id) {
		try {
			return schedulerDao.getLawFirmLitigationMailId(liti_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getHearingStageDetailsForReport() {
		try {
			return schedulerDao.getHearingStageDetailsForReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Object> getAssignPersonFromAlertSarfaesiactDate() {
		// TODO Auto-generated method stub
		try {
			//System.out.println("SrfactAssign");
			return schedulerDao.getAssignPersonFromAlertSarfaesiactDate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getAlertSarfaesiactMailToExecutor(int sarf_act_id) {
		// TODO Auto-generated method stub
		try {
			//System.out.println("SrfactAlert");
			return schedulerDao.getAlertSarfaesiactMailToExecutor(sarf_act_id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getAssignPersonFromAlertSarfaesiActionDate() {
		// TODO Auto-generated method stub
		try {
		return schedulerDao.getAssignPersonFromAlertSarfaesiActionDate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getAlertSarfaesiActionMailToExecutor(int sarf_action_id) {
		// TODO Auto-generated method stub
		try {
		return schedulerDao.getAlertSarfaesiActionMailToExecutor(sarf_action_id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}




}
