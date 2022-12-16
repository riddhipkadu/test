package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.StandardFormContractDao;
import lcmt.domain.Sfco_documents;
import lcmt.domain.StandardFormContractType;
import lcmt.domain.StandardFormContracts;

@Repository(value = "standardFormContractDao")
@Transactional
public class StandardFormContractDaoImpl implements StandardFormContractDao {

	@PersistenceContext
	private EntityManager em;

	// Method Created By: Tejashri Zurunge
	// Method Purpose: Add Standard Form Contract Type
	@Override
	public void saveSfcoType(StandardFormContractType standardFormContractType) {
		try {
			em.persist(standardFormContractType);
			// return standardFormContractType;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: List Standard Form Contract Type
	@Override
	public <T> List<T> getAllStandardFormContractType(Class<T> clazz) {
		try {
			TypedQuery<T> query = em.createQuery(" from " + clazz.getName(), clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: update Standard Form Contract Type by id
	@Override
	public void updateStandardFormContractType(StandardFormContractType standardFormContractType) {
		try {
			em.merge(standardFormContractType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: get the all values by id
	@Override
	public StandardFormContractType getStandardFormContractTypeById(int sfco_type_id) {
		try {
			String sql = "SELECT * FROM mst_sfco_type where sfco_type_id = " + sfco_type_id;
			Query query = em.createNativeQuery(sql, StandardFormContractType.class);
			if (!query.getResultList().isEmpty()) {
				return (StandardFormContractType) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created By: Tejashri Zurunge
	// Method Purpose: To Verify Whether Standard Form Contract Type is exist or
	// not
	@Override
	public int isStandardFormContractTypeExist(int sfco_type_id, String sfco_type_name) {
		try {
			String sql = "select count(*) as sfcocount from mst_sfco_type where sfco_type_name='" + sfco_type_name
					+ "' " + " ";
			if (sfco_type_id != 0) {
				sql += " AND sfco_type_id !=" + sfco_type_id;
			}
			Query query = em.createNativeQuery(sql);
			String count = query.getResultList().get(0).toString();
			return Integer.parseInt(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Delete Standard Form Contract Type
	@Override
	public int deleteStandardFormContractType(int sfco_type_id) {
		try {
			String sql = "DELETE FROM mst_sfco_type WHERE sfco_type_id = " + sfco_type_id;
			Query query = em.createNativeQuery(sql);
			int deleteCount = query.executeUpdate();
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int checkDependancySFCType(int sfco_type_id) {
		try {
			String sql = " Select TOP 1 case when std.sfco_type_id > 0 then 1 else 0 END as sfcoType from mst_sfco_type sfco_type "
					+ " full join mst_standard_form_contract std on sfco_type.sfco_type_id = std.sfco_type_id "
					+ " where sfco_type.sfco_type_id = "+sfco_type_id ;
			Query query = em.createNativeQuery(sql);
			return Integer.parseInt(query.getResultList().get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}	

	/*-----------------------------------------"Standard Form Contracts" methods starts from here----------------------------------------------------   */
	// Method Created : Tejashri Zurunge
	// Method purpose : Save Standard Form Contract
	@Override
	public int persist(StandardFormContracts contracts) {
		try {
			em.persist(contracts);
			em.flush();
			return contracts.getSfco_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Get all Standard Form Contract
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getListOfSfc(HttpSession session) {
		try {
			int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			String sql = "Select sfco.sfco_id, sfcoType.sfco_type_name, sfco.sfco_name,sfco.sfco_description, sfco.sfco_added_by, sfco.sfco_abbreviation, sfco.sfco_contract_type, sfco.sfco_type_id from mst_standard_form_contract sfco LEFT JOIN mst_sfco_type sfcoType ON sfco.sfco_type_id = sfcoType.sfco_type_id ";
					/*if(Integer.parseInt(session.getAttribute("sess_user_role").toString())==2){
				sql += " Where (sfco.sfco_added_by = "+user_id+" OR sfco.sfco_added_by IN(select DISTINCT user_id from mst_user where user_report_to="+user_id+"))";
					}*/
			Query query = em.createNativeQuery(sql);
			//query.setParameter(1, user_id);
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Get Standard Form Contract by Id
	@Override
	public StandardFormContracts getStandardFormContractsById(int sfco_id) {
		try {
			String sql = "select * from mst_standard_form_contract where sfco_id = " + sfco_id;
			Query query = em.createNativeQuery(sql, StandardFormContracts.class);
			if (!query.getResultList().isEmpty()) {
				return (StandardFormContracts) query.getResultList().get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Update Standard Form Contract
	@Override
	public void updateStandardFormContracts(StandardFormContracts standardFormContracts) {
		try {
			em.merge(standardFormContracts);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Delete Standard Form Contract
	@Override
	public int deleteStandardFormContracts(int sfco_id) {
		try {
			String sql = "DELETE FROM mst_standard_form_contract WHERE sfco_id = " + sfco_id;
			Query query = em.createNativeQuery(sql);
			int deleteCount = query.executeUpdate();
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getLastGeneratedValueForSfcDoc(int sfco_id) {
		try {
			String sql = "select MAX(sfco_doc_last_generated_value_for_sfco_id) from trn_sfc_documents where sfco_sfco_id ="
					+ sfco_id;
			Query query = em.createNativeQuery(sql);
			if (query.getResultList().get(0) != null) {
				return Integer.parseInt(query.getResultList().get(0).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : save Standard Form Contract document
	@Override
	public void saveSfcDocuments(Sfco_documents documents) {
		try {
			em.persist(documents);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : listing Standard Form Contract documents
	@SuppressWarnings("unchecked")
	@Override
	public List<Sfco_documents> getDocumentBySfcId(int sfco_id) {
		try {
			String sql = "select * from trn_sfc_documents where sfco_sfco_id =" + sfco_id;
			Query query = em.createNativeQuery(sql, Sfco_documents.class);
			if (!query.getResultList().isEmpty()) {
				return query.getResultList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Delete Standard Form Contract
	@Override
	public int deleteSfcoDocument(Sfco_documents sfco_documents) {
		try {

			em.remove(em.merge(sfco_documents));
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Get Standard Form Contract by id
	@Override
	public Sfco_documents getSfcoDocumentById(int sfco_doc_id) {
		try {
			String sql = "select * from trn_sfc_documents where sfco_doc_id =" + sfco_doc_id;
			Query query = em.createNativeQuery(sql, Sfco_documents.class);
			if (!query.getResultList().isEmpty()) {
				return (Sfco_documents) query.getResultList().get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Download document by id
	@Override
	public String getSfcoDocumentFilePath(int sfco_doc_id) {
		try {
			String sql = "select sfco_doc_generated_file_name from trn_sfc_documents where sfco_doc_id =" + sfco_doc_id;
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

	// Method Created : Tejashri Zurunge
	// Method Purpose : save sfco logs
	@Override
	public int saveLogs(Object obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	

}
