package lcmt.dao;

import java.util.List;

import lcmt.domain.ARCname;

public interface ARCnameDao  {
	public void persist(ARCname arcName);
	public <T> List<T> getAllARCname(Class<T> clazz);
	public ARCname getARCnameById(int arc_id); 
	public void updateARCname(ARCname arcName);
	public int deleteARCname(int arc_id);
	public int isARCNameExist(int arc_id, String arc_name);
	public int isARCNameEmailExist(int arc_id, String arc_email_id);
	public List<Object> checkARCnameDependancy(int arc_id);

}
