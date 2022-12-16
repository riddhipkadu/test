package lcmt.dao;

import java.util.List;

import lcmt.domain.InternalLitigation;


public interface InternalLitigationDao {
	public void persist(Object obj);
	public <T> List<T> getAll(Class<T> claz);
	public InternalLitigation getInternalLitiById(int inter_id); 
	public void updateInternalLitigation(InternalLitigation newLiti);
	public int deleteInternalLitigation(int internal_liti_id);
	public int isLitigationCodeExist(String jsonString );
}
