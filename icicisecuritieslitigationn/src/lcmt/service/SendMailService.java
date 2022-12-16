package lcmt.service;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpSession;

public interface SendMailService {

	public void sendQueryAssignMailToResponsiblePerson(int query_id, HttpSession session);
	public void sendLitigationHandledMailToInternallyHandledPerson(int liti_id, HttpSession session);
	public void sendMail(String email_subject, String email_body, InternetAddress[] addresses, InternetAddress[] addressCC, String id);
	public void sendPreExecutionAssignMailToResponsiblePerson(int cont_id, HttpSession session);
	public void sendLegalNoticeMailToAssignPerson(int lega_noti_id, HttpSession session);
	public void sendSarfaesiActHandledMailToInternallyHandledPerson(int sarfact_id, HttpSession session);
}
