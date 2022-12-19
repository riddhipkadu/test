package lcmt.service;

import java.util.List;
import java.util.Map;

import lcmt.domain.LawFirm;

public interface LawFirmService {
	public void persist(LawFirm lawFirm);
	public List<LawFirm> getAll();
	public void updateLawFirm(LawFirm lawFirm);
	public LawFirm getLawFirmById(int lawf_id);
	public Map<Integer,String> getAllLawFirm();
	public int isLawFirmNameExist(int lawf_id, String lawf_name);
	public int deleteLawFirm(int lawf_id);
	public String checkLawFirmDependancy(int lawf_id);
	public String getAllLawFirmJson();

}
