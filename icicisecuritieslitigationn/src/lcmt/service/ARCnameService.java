package lcmt.service;

import java.util.List;
import java.util.Map;

import lcmt.domain.ARCname;

public interface ARCnameService {
	public void persist(ARCname arcName);
	public List<ARCname> getAllARCname();
	public ARCname getARCnameById(int arc_id);
	public void updateARCname(ARCname arcName);
	public int deleteARCname(int arc_id);
	public int isARCNameExist(int arc_id, String arc_name);
	public String getAllArcManagerJson();
	public Map<Integer, String> getAllArcName();
	public String checkARCnameDependancy(int arc_id);
	public int isARCNameEmailExist(int arc_id, String arc_email_id);


}
