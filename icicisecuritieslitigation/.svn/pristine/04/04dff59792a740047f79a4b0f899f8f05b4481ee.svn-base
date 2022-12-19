package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.ContractTypeDao;
import lcmt.domain.ContractType;

@Repository(value = "contractTypeDao")
@Transactional
public class ContractTypeDaoImpl implements ContractTypeDao {

	@PersistenceContext
	private EntityManager em;

	// Method Created By: Tejashri Zurunge
	// Method Purpose : Add New Contract Type
	@Override
	public void persist(Object obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: listing and view Contract Type
	@Override
	public <T> List<T> getAllContractType(Class<T> clazz) {
		try {
			TypedQuery<T> query = em.createQuery(" from " + clazz.getName(), clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Update Contract Type
	@Override
	public void updateContractType(ContractType contractType) {
		try {
			em.merge(contractType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created: Tejashri Zurunge
	// Method Purpose: Updating Contract Type By Id
	@Override
	public ContractType getContractTypeById(int cont_type_id) {
		try {
			String sql = "SELECT * FROM mst_contract_type where cont_type_id = " + cont_type_id;
			Query query = em.createNativeQuery(sql, ContractType.class);
			if (!query.getResultList().isEmpty()) {
				return (ContractType) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Check whether Contract Type Name Exist or not
	@Override
	public int isContractTypeNameExist(int cont_type_id, String cont_type_name) {
		try {
			String sql = "select count(*) as scount from mst_contract_type where cont_type_name='" + cont_type_name
					+ "' " + " ";
			if (cont_type_id != 0) {
				sql += " AND cont_type_id !=" + cont_type_id;
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
	// Method Purpose : Delete Contract Type
	@Override
	public int deleteContractType(int cont_type_id) {
		try {
			String sql = "DELETE FROM mst_contract_type WHERE cont_type_id = " + cont_type_id;
			Query query = em.createNativeQuery(sql);
			int deleteCount = query.executeUpdate();
			// System.out.println("This is no of rows deleted:"+deleteCount);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Delete Contract Type	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> checkDependancyContractType(int cont_type_id) {
		try {
			String sql = " Select  case when cont.cont_contract_type_id > 0 then 1 else 0 END as contractType, "
					+ " case when execCont.exec_contract_type_id > 0 then 1 else 0 END as execContractType from mst_contract_type contType "
					+ " left join trn_contract_contract_type cont on contType.cont_type_id = cont.cont_contract_type_id "
					+ " left join mst_executed_contacts execCont on contType.cont_type_id = execCont.exec_contract_type_id "
					+ " where contType.cont_type_id = "+cont_type_id+ " limit 1";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getContractTypeByContractID(int cont_id) {
		try {
			//int sess_user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			String sql = "SELECT DISTINCT cont_type_id,cont_type_name FROM mst_contract_type WHERE cont_type_id = "+cont_id;
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
