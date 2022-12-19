package lcmt.dao;

import java.util.List;

import lcmt.domain.Location;

public interface LocationDao {
	public void persist(Object obj);
	public <T> List<T> getAll(Class<T> clazz);
	public <T> List<T> getJoinedAll();
	public Location getLocationById(int loca_id);
	public void updateLocation(Location location);
	public <T> List<T> getAllLocationsForOrganization(int orga_id);
	public int approveDisapproveLoc(int loc_id , int loca_status);
	public int enableDisableLoc(int loc_id , int loca_status);
	public int isLocaNameExist(int loca_id, String loca_name);
	public int islocaShortNameExist(int loca_id, String loca_short_name);
}
