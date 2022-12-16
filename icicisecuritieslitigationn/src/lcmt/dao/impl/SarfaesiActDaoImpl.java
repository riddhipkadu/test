package lcmt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import lcmt.dao.SarfaesiActDao;
import lcmt.domain.ArcManager;
import lcmt.domain.ExternalCounsel;
import lcmt.domain.LitigationDocuments;
import lcmt.domain.SarfaesiAct;
import lcmt.domain.SarfaesiActDocument;
import lcmt.domain.SarfaesiAction;

@Repository(value = "SarfaesiActDao")
@Transactional
public class SarfaesiActDaoImpl implements SarfaesiActDao {
	@PersistenceContext
	private EntityManager em;

	// Method Created : Pranjali Kawale
	// Method Purpose : Save Sarfaesi Act
	@Override
	public int persist(SarfaesiAct obj) {
		// TODO Auto-generated method stub
		try {
			em.persist(obj);
			return obj.getSarf_act_id();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getLastGeneratedValueBySarfActId(int sarf_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT MAX(sarf_last_generated_value_for_act_id) FROM trn_SarfaesiAct_documents where doc_SarfAct_id= "
					+ sarf_id;
			Query query = em.createNativeQuery(sql);
			// System.out.println(sql);
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

	// Method Created : Pranjali Kawale
	// Method Purpose :Save Sarfaesi Act Document
	@Override
	public void saveSarfaesiActDocument(SarfaesiActDocument sarfaesiActDocument) {
		// TODO Auto-generated method stub
		try {
			em.persist(sarfaesiActDocument);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	// Method Created : Pranjali Kawale
	// Method Purpose :get all Sarfaesi Act

	@Override
	public List<Object> getAllSarfaesiAct(HttpSession session) {
		// TODO Auto-generated method stub
		try {
			String sql = "select sarf_act_id, sarf_loan_no,sarf_borrower,sarf_security_type,sarf_security_Interest,sarf_npa_date FROM mst_sarfaesiact order by sarf_act_id DESC";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getLoanNumber() {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT sarf_act_id, sarf_loan_no FROM mst_sarfaesiact where sarf_loan_no != '' ";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getBorrower() {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT sarf_act_id, sarf_borrower FROM mst_sarfaesiact where sarf_borrower != ''";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Pranjali Kawale
	// Method Purpose : Search Sarfaesi Act
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> searchSarfaesiAct(int sarf_orga_id, int sarf_loca_id, String sarf_security_type,
			String sarf_loan_no, String sarf_borrower, String sarf_security_Interest, String SarfAct_from_date,
			String SarfAct_to_date) {
		// TODO Auto-generated method stub
		try {

			String sql = "select sarf_act_id,sarf_loan_no,sarf_borrower,sarf_security_type,sarf_security_Interest,sarf_npa_date,sarf_orga_id,sarf_loca_id FROM mst_sarfaesiact where 1";

			if (sarf_orga_id > 0) {
				sql += " AND sarf_orga_id='" + sarf_orga_id + "'";
				// System.out.println(sql);
			}

			if (sarf_loca_id > 0) {
				sql += " AND sarf_loca_id='" + sarf_loca_id + "'";
				// System.out.println(sql);
			}

			if (!sarf_security_type.equals("NA")) {
				sql += " AND sarf_security_type='" + sarf_security_type + "'";
				// System.out.println(sql);
			}

			if (!sarf_loan_no.equals("0")) {
				sql += " AND sarf_loan_no='" + sarf_loan_no + "'";
				// System.out.println(sql);
			}

			if (!sarf_borrower.equals("0")) {
				sql += " AND sarf_borrower='" + sarf_borrower + "'";
				// System.out.println(sql);
			}

			if (!sarf_security_Interest.equals("NA")) {
				sql += " AND sarf_security_Interest='" + sarf_security_Interest + "'";
			}

			if (!SarfAct_from_date.equals("") && !SarfAct_to_date.equals("")) {
				sql += " AND sarf_npa_date Between '" + SarfAct_from_date + "' AND '" + SarfAct_to_date + "'";
			}

			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SarfaesiAct getSarfaesiActById(int sarf_act_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from mst_sarfaesiact where sarf_act_id=" + sarf_act_id;
			Query query = em.createNativeQuery(sql, SarfaesiAct.class);

			if (!query.getResultList().isEmpty()) {
				return (SarfaesiAct) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Pranjali Kawale
	// Method Purpose : Update Sarfaesi Act
	@Override
	public void updateSarfaesiAct(SarfaesiAct sarfaesiAct) {
		// TODO Auto-generated method stub
		try {
			em.merge(sarfaesiAct);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	// Method Created : Pranjali Kawale
	// Method Purpose : Delete Sarfaesi Act

	@Override
	public int deleteSarfaesiAct(int sarf_act_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "Delete from mst_sarfaesiact where sarf_act_id=" + sarf_act_id;
			Query query = em.createNativeQuery(sql);
			return query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Object[] getJoinedSarfaesiAct(int sarf_act_id) {
		// TODO Auto-generated method stub
		try {

			String sql = "select sarf_act_id, sarf_orga_id,sarf_loca_id,sarf_loan_no,sarf_borrower,sarf_contact_no,sarf_email,sarf_address,sarf_Executor_id,sarf_mgr_name,sarf_security_type,sarf_security_Interest,sarf_security_loc,sarf_npa_date,sarf_filling_date,sarf_NotiIssue_date,sarf_first_reminder,sarf_second_reminder,sarf_arc_name,sarf_dept_id,sarf_loan_repay_amount FROM mst_sarfaesiact where sarf_act_id="
					+ sarf_act_id;
			Query query = em.createNativeQuery(sql);
			return (Object[]) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> List<T> getALLSarfaesiActDocByid(Class<T> clazz, int sarfact_id) {
		// TODO Auto-generated method stub
		try {
			// System.out.println("Daoimpl");

			TypedQuery<T> query = em.createQuery(" from " + clazz.getName() + " where doc_SarfAct_id = " + sarfact_id,
					clazz);
			return query.getResultList();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getSarfaesiActDocumentFilePath(int sarf_doc_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "select sarf_generated_file_name from trn_sarfaesiact_documents where sarf_doc_id  = "
					+ sarf_doc_id;
			// System.out.println(sql);
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

	@Override
	public SarfaesiActDocument getSarfaesiActDocByid(int sarfact_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM trn_sarfaesiact_documents where sarf_doc_id = " + sarfact_id;
			// System.out.println(sql);
			Query query = em.createNativeQuery(sql, SarfaesiActDocument.class);
			if (!query.getResultList().isEmpty()) {
				return (SarfaesiActDocument) query.getResultList().get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public int deleteSarfaesiActDocument(SarfaesiActDocument sarfaesiActDocument) {
		// TODO Auto-generated method stub

		try {

			em.remove(em.merge(sarfaesiActDocument));
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	// Method Created : Pranjali Kawale
	// Method Purpose : Delete Sarfaesi Act Document
	@Override
	public int deleteSarfaesiActDocument(int sarf_doc_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "Delete from trn_sarfaesiact_documents where sarf_doc_id= " + sarf_doc_id;
			Query query = em.createNativeQuery(sql);
			query.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Pranjali Kawale
	// Method Purpose : Save Sarfaesi Act Action
	@Override
	public int saveSarfaesiAction(SarfaesiAction sarfaesiAction) {
		// TODO Auto-generated method stub
		try {

			em.persist(sarfaesiAction);
			return sarfaesiAction.getSarf_action_id();

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public List<Object> getAllSarfaesiActActionItem(int sarfact_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "select sarf_action_id,sarf_action_status,sarf_action_item,sarf_nextaction_item,sarf_action_NotiIssue_date,sarf_action_due_date,action_first_remind_date from mst_sarfaesiaction where action_sarf_act_id="
					+ sarfact_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SarfaesiAction getSarfaesiActActionById(int sarf_action_id) {
		// TODO Auto-generated method stub
		// System.out.println("id:" +sarf_action_id);
		try {
			String sql = "select * from mst_sarfaesiaction where sarf_action_id =" + sarf_action_id;
			Query query = em.createNativeQuery(sql, SarfaesiAction.class);
			// System.out.println("count:" +query.getResultList());
			if (!query.getResultList().isEmpty()) {

				return (SarfaesiAction) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteSarfaesiActAction(int sarfaction_id) {
		// TODO Auto-generated method stub
		try {
			// System.out.println(sarfaction_id);
			String sql = "Delete from mst_sarfaesiaction where sarf_action_id =" + sarfaction_id;
			Query query = em.createNativeQuery(sql);
			return query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void updateSarfaesiActAction(SarfaesiAction sarfaesiAction) {
		// TODO Auto-generated method stub
		try {

			em.merge(sarfaesiAction);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public int isLoanNumberExistOrNot(String sarf_loan_no) {
		// TODO Auto-generated method stub
		try {
			String sql = "Select count(*) as LoanNumberCount from mst_sarfaesiact where sarf_loan_no ='" + sarf_loan_no
					+ "'";
			Query query = em.createNativeQuery(sql);
			// System.out.println(sql);
			String count = query.getResultList().get(0).toString();
			return Integer.parseInt(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArcManager> getArcManagerByARCnameId(int arc_name_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from mst_arc_manager where mgr_arc_name =" + arc_name_id;
			Query query = em.createNativeQuery(sql, ArcManager.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getSarfaesiNoticedateByid(int sarfact_id) {
		// TODO Auto-generated method stub
		try {

			String sql = "select DATE_FORMAT(sarf_NotiIssue_date,'%d-%m-%Y') from mst_sarfaesiact where sarf_act_id="
					+ sarfact_id;
			Query query = em.createNativeQuery(sql);
			List<Object> mList = query.getResultList();
			if (!mList.isEmpty()) {
				if (mList.get(0) != null) {
					return mList.get(0).toString();
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

}
