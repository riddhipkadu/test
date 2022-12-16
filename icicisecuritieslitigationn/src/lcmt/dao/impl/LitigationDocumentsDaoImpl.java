package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.LitigationDocumentsDao;
import lcmt.domain.HearingStageDocuments;
import lcmt.domain.LitigationDocuments;
import lcmt.domain.LitigationFeesDocuments;

@Repository(value = "litigationDocumentsDao")
@Transactional
public class LitigationDocumentsDaoImpl implements LitigationDocumentsDao {

	@PersistenceContext
	private EntityManager em;

	// Method Created By: Mahesh Kharote
	// Method Purpose: Persist Document entry to database
	@Override
	public void saveDocuments(Object obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Get Last Generated value for adding documents
	@Override
	public int getLastGeneratedValueByLitiId(int liti_id) {
		try {
			String sql = "SELECT MAX(ldoc_last_generated_value_for_liti_id) FROM trn_litigation_documents where ldoc_liti_id = "
					+ liti_id;
			Query query = em.createNativeQuery(sql);

			if (query.getResultList().get(0) != null) {
				return Integer.parseInt(query.getResultList().get(0).toString());
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: All Litigation Documents for Listing
	@Override
	public <T> List<T> getAllDocumentByLitiId(Class<T> clazz, int liti_id) {
		try {
			TypedQuery<T> query = em.createQuery(" from " + clazz.getName() + " where ldoc_liti_id = " + liti_id,
					clazz);
			
		  System.out.println(query);
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Get Document for downloading
	@Override
	public String getLitigationDocumentFilePath(int ldoc_id) {
		try {
			String sql = "SELECT ldoc_generated_file_name FROM trn_litigation_documents where ldoc_id = " + ldoc_id;
			Query query = em.createNativeQuery(sql);
			if (!query.getResultList().isEmpty()) {
				if (query.getResultList().get(0) != null) {
					return query.getResultList().get(0).toString();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Get LitigationDocument By Id
	@Override
	public LitigationDocuments getLitigationDocumentById(int ldoc_id) {
		try {
			String sql = "SELECT * FROM trn_litigation_documents where ldoc_id = " + ldoc_id;
			Query query = em.createNativeQuery(sql, LitigationDocuments.class);
			if (!query.getResultList().isEmpty()) {
				return (LitigationDocuments) query.getResultList().get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Mahesh Kharote
	// Method Purpose: Delete Litigation Document
	@Override
	public int deleteLitigationDocument(LitigationDocuments litigationDocuments) {
		try {

			em.remove(em.merge(litigationDocuments));
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get last generated value for hearing stage documents
	@Override
	public int getLastGeneratedValueByHearingStageId(int stage_id) {
		try {
			String sql = "SELECT MAX(hear_stage_last_generated_value_for_hear_stage_id) FROM trn_hearing_stage_documents where hear_stage_id="
					+ stage_id;
			Query query = em.createNativeQuery(sql);

			if (query.getResultList().get(0) != null) {
				return Integer.parseInt(query.getResultList().get(0).toString());
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// Method Created : Harshad Padole
	// Method Purpose : Save hearing stage documents
	@Override
	public void saveHearingSatgeDocument(HearingStageDocuments documents) {
		try {
			em.persist(documents);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created : Harshad Padole
	// Method Purpose : Get all hearing stage documents by hearing stage id
	@Override
	public <T> List<T> getHearingDocumentByHearStageId(Class<T> clazz, int hear_stage_id) {

		try {
			TypedQuery<T> query = em.createQuery(" from " + clazz.getName() + " where hear_stage_id=" + hear_stage_id,
					clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get file path by hearing stage doc id
	@Override
	public String getHearingDocumentFilePath(int hearing_doc_id) {
		try {
			String sql = "SELECT hear_stage_generated_file_name FROM trn_hearing_stage_documents where hear_doc_id="
					+ hearing_doc_id;
			Query query = em.createNativeQuery(sql);
			if (!query.getResultList().isEmpty()) {

				if (query.getResultList().get(0) != null) {
					return query.getResultList().get(0).toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created : Tejashri Zurunge
	// Method purpose : get last generated value for fees id
	@Override
	public int getLastGeneratedValueByFeesId(int fees_id, String fees_doc_type) {
		try {
			String sql = "SELECT MAX(fdoc_last_generated_value_for_fees_id) FROM trn_liti_fees_documents where fdoc_fees_id = "
					+ fees_id;

			/*
			 * if(fees_doc_type.equals("PC" )||fees_doc_type.equals("PA"
			 * )||fees_doc_type.equals("AAC" )||fees_doc_type.equals("AAA" )){
			 * 
			 * sql += " AND fdoc_document_type = " +fees_doc_type; }
			 */
			Query query = em.createNativeQuery(sql);

			if (query.getResultList().get(0) != null) {
				return Integer.parseInt(query.getResultList().get(0).toString());
			} else {
				return 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method created : Tejashri Zurunge
	// Method purpose : get list for fees
	@Override
	public <T> List<T> getAllDocumentByFeesId(Class<T> clazz, int fees_id, String fees_type) {
		try {
			TypedQuery<T> query = em.createQuery(
					" from " + clazz.getName() + " where fdoc_fees_id = ? AND fdoc_document_type=?", clazz);
			query.setParameter(1, fees_id);
			query.setParameter(2, fees_type);
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// Method created : Tejashri Zurunge
	// Method purpose : download litigation fees documents
	@Override
	public String getLitigationFeesDocumentFilePath(int fdoc_id) {
		try {
			String sql = "SELECT fdoc_generated_file_name FROM trn_liti_fees_documents where fdoc_id = " + fdoc_id;
			Query query = em.createNativeQuery(sql);
			if (!query.getResultList().isEmpty()) {
				if (query.getResultList().get(0) != null) {
					return query.getResultList().get(0).toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}
	
	//Method created : Tejashri Zurunge
	//Method purpose : delete hearing stage document  
	@Override
	public int deleteHearingStageDoc(int doc_id) {
		try {
			String sql_one = "Delete from trn_hearing_stage_documents where hear_doc_id ="+ doc_id;
			Query query1 = em.createNativeQuery(sql_one);
			query1.executeUpdate();
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method created : Tejashri Zurunge
	// Method purpose : delete litigation fees document
	@Override
	public int deleteLitiFeesDocument(int fdoc_id) {
		try {
			String sql_one = "Delete from trn_liti_fees_documents where fdoc_id =" + fdoc_id;
			Query query1 = em.createNativeQuery(sql_one);
			query1.executeUpdate();
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public LitigationFeesDocuments getFeesDocumentById(int fdoc_id) {
		try {
			String sql = "select * from trn_liti_fees_documents where fdoc_id ="+fdoc_id;
			Query query = em.createNativeQuery(sql, LitigationFeesDocuments.class);
			return (LitigationFeesDocuments) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
