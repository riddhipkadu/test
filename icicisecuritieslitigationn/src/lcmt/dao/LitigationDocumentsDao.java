package lcmt.dao;

import java.util.List;


import lcmt.domain.LitigationDocuments;
import lcmt.domain.LitigationFeesDocuments;
import lcmt.domain.HearingStageDocuments;


public interface LitigationDocumentsDao {

	public void saveDocuments(Object obj);
	public int getLastGeneratedValueByLitiId(int liti_id);
	public <T> List<T> getAllDocumentByLitiId(Class<T> clazz,int liti_id);
	public String getLitigationDocumentFilePath(int ldoc_id);
	public LitigationDocuments getLitigationDocumentById(int ldoc_id);
	public int deleteLitigationDocument(LitigationDocuments litigationDocuments);
	public int getLastGeneratedValueByHearingStageId(int stage_id);
	public void saveHearingSatgeDocument(HearingStageDocuments documents);
	public <T>List<T> getHearingDocumentByHearStageId(Class<T> clazz,int hear_stage_id);
	public String getHearingDocumentFilePath(int hearing_doc_id);
	public int getLastGeneratedValueByFeesId(int fees_id, String fees_doc_type);
	public <T> List<T> getAllDocumentByFeesId(Class<T> clazz, int fees_id, String fees_type);
	public String getLitigationFeesDocumentFilePath(int fdoc_id);
	public int deleteHearingStageDoc(int doc_id);
	public int deleteLitiFeesDocument(int fdoc_id);
	public LitigationFeesDocuments getFeesDocumentById(int fdoc_id);
	
}
