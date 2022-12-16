package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.ClauseDao;

import lcmt.domain.Clause;



@Repository(value = "clauseDao")
@Transactional
public class ClauseDaoImpl implements ClauseDao {
	
	@PersistenceContext
	private EntityManager em;
	
	
	// Method Created : Mahesh Kharote
		// Method Purpose : Save Contract For Execution page
		@Override
		public void persist(Object obj) {
			try {
				em.persist(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	
	// Method Created: Akash Jadhav
	// Method Purpose: listing and view clause
		@Override
		public <T> List<T> getAllClause(Class<T> clazz) {
			try {
				TypedQuery<T> query = em.createQuery(" from " + clazz.getName(), clazz);
				return query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		// Method Created: Akash Jadhav
		// Method Purpose: Updating Clause By Id
		@Override
		public Clause getClauseById(int cls_id) {
			try {
				String sql = "SELECT * FROM mst_clause_name where cls_id = " + cls_id;
				Query query = em.createNativeQuery(sql, Clause.class);
				if (!query.getResultList().isEmpty()) {
					return (Clause) query.getResultList().get(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}		
		
		
		// Method Created : Akash Jadhav
		// Method purpose : Update Clause name
		@Override
		public void updateClause(Clause clause) {
			try {
				em.merge(clause);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}		

		// Method Created : Akash Jadhav
		// Method Purpose : Delete Clause
		@Override
		public int deleteClause(int cls_id) {
			try {
				String sql = "DELETE FROM mst_clause_name WHERE cls_id = " + cls_id;
				Query query = em.createNativeQuery(sql);
				int deleteCount = query.executeUpdate();
				// System.out.println("This is no of rows deleted:"+deleteCount);
				return deleteCount;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		
		
		/*
		 * // Method Created : Akash Jadhav // Method Purpose : Delete Clause
		 * 
		 * @SuppressWarnings("unchecked")
		 * 
		 * @Override public List<Object> checkDependancyClause(int cls_id) { try {
		 * String sql =
		 * " Select  case when cont.cont_contract_type_id > 0 then 1 else 0 END as contractType, "
		 * +
		 * " case when execCont.exec_contract_type_id > 0 then 1 else 0 END as execContractType from mst_contract_type contType "
		 * +
		 * " left join trn_contract_contract_type cont on contType.cont_type_id = cont.cont_contract_type_id "
		 * +
		 * " left join mst_executed_contacts execCont on contType.cont_type_id = execCont.exec_contract_type_id "
		 * + " where contType.cont_type_id = "+cont_type_id+ " limit 1"; Query query =
		 * em.createNativeQuery(sql); return query.getResultList(); } catch (Exception
		 * e) { e.printStackTrace(); } return null; }
		 */
}
