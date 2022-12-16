package lcmt.service;

import java.util.List;
import java.util.Map;

import lcmt.domain.Designation;

public interface DesignationService {

	public void persist(Designation designation);
	public List<Designation> getAll();
	public Designation getDesignationById(int desi_id);
	public void updateDesignation(Designation designation);
	public Map<Integer, String> getAllDesignationForDropDown();
	public int approveDisapproveDesi(int desi_id,int desi_status);
	public int enableDisableDesi(int desi_id,int desi_status);
	public int isDesiNameExist(int desi_id, String desi_name);
}
