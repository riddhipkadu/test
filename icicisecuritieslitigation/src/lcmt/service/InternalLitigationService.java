package lcmt.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import lcmt.domain.InternalLitigation;

public interface InternalLitigationService {
	public void persist(InternalLitigation internal, HttpSession session);
	public List<InternalLitigation> getAll();
	public InternalLitigation getInternalLitiById(int inter_id);
	public void updateInternalLitigation(InternalLitigation intern);
	public int deleteInternalLitigation(int internal_liti_id);
	public int isLitigationCodeExist(String jsonString);

}
