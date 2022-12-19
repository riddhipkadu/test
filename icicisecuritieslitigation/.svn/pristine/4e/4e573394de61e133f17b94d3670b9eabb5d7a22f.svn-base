package lcmt.dao;

import java.util.List;

import lcmt.domain.Designation;

public interface DesignationDao {

	public void persist(Object obj);
	public <T> List<T> getAll(Class<T> clazz);
	public <T> List<T> getJoinedAll();
	public Designation getDesignationById(int desi_id);
	public void updateDesignation(Designation designation);
	public int approveDisapproveDesi(int desi_id,int desi_status);
	public int enableDisableDesi(int desi_id,int desi_status);
	public int isDesiNameExist(int desi_id, String desi_name);
}
