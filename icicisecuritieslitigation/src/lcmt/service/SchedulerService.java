package lcmt.service;

import java.util.List;

import lcmt.domain.LitigationEscalationMailId;
import lcmt.domain.User;

public interface SchedulerService {

	public List<Object> getReminderQueryMailToAssignPerson(int assign_to);
	public List<Object> getAssignPersonFromReminderDate();
	public List<Object> getAlertHearingStageMailToAssignPerson(int resp_person_id);
	public List<Object> getAssignPersonFromAlertDate();
	public List<Object> getAssignPersonLegalNoticeFromReminderDate();
	public List<Object> getReminderLegalNoticeMailToAssignPerson(int matter_handled_by_id);
	public List<Object> getAssignedPersonLegalnoticeStatus();
	public List<Object> getReminderLegalNoticeStatusMailToAssignPerson(int id);
	public List<Object> getLitigationDetailsForReport();
	public List<Object> getAllAdminUser();
	public List<Object> getQueryDetailsForReport();
	public List<Object> getLegalNoticeDetailsForReport();
	public List<Object> getContractDetailsForReport();
	public List<Object> getExecutedContractDetailsForReport();
	public List<Object> getAssignPersonTaskCompletionFromReminderDate();
	public List<Object> getReminderTaskCompletionMailToAssignPerson(int task_assigned_id);
	public void autoActivateActionItemTask();
	public <T>List<T> getActionItemCurrentMonth();
	public List<User> getContractSPOCuser();
	public List<User> getLitigationSPOCuser();
	public List<Object> getAssignPersonFrom3rdAlertDate();
	public List<Object> get3rdAlertHearingStageMailToAssignPerson(int matter_handled_by_id);
	public List<User> getAllSPOCuser();
	public List<Object> getHearingStageOnHearing();
	public LitigationEscalationMailId getLawFirmLitigationMailId(int liti_id);
	public List<Object> getHearingStageDetailsForReport();
	public List<Object> getAssignPersonFromAlertSarfaesiactDate();
	public List<Object> getAlertSarfaesiactMailToExecutor(int sarf_act_id);
	public List<Object> getAssignPersonFromAlertSarfaesiActionDate();
	public List<Object> getAlertSarfaesiActionMailToExecutor(int sarf_action_id);
}

