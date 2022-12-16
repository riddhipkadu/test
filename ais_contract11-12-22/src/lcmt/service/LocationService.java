package lcmt.service;

import java.util.List;
import java.util.Map;

import lcmt.domain.Location;

public interface LocationService {
	public void persist(Location location);
	public List<Location> getAll();
	public Location getLocationById(int loca_id);
	public void updateLocation(Location location);
	public Map<Integer, String> getAllLocationsForOrganization(int orga_id);
	public String getLocationNameById(int loca_id);
	public int approveDisapprove(int loc_id , int loca_status);
	public int enableDisable(int loc_id , int loca_status);
	public int isLocaNameExist(int loca_id, String loca_name);
	public int islocaShortNameExist(int loca_id, String loca_short_name);
}
