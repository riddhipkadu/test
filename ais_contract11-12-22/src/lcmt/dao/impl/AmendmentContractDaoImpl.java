package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.AmendmentContractDao;
import lcmt.domain.AmendmentContract;
import lcmt.domain.Contract;
import lcmt.domain.ContractParties;
import lcmt.domain.ContractType;

@Repository(value = "amendmentcontractDao")
@Transactional
public class AmendmentContractDaoImpl implements AmendmentContractDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public int saveAmendmentContract(AmendmentContract amendmentcontract) {
		try {
			em.persist(amendmentcontract);
			em.flush();
			return amendmentcontract.getAmend_contract_id();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public void savePartyDetails(Object object) {
		try {
			em.persist(object);
		} catch (Exception e) {
		 e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getJoinedAmendmentContractDetails(int exec_id, HttpSession session) {
		try {
		
			//int user_id = Integer.parseInt(session.getAttribute("sess_user_id").toString());
			String sql = "select amend_contract_start_date, amend_contract_end_date, u.user_first_name, u.user_last_name as resposibleperson, amend_contract_term, amend_contract_clauses,amend_contract_status,amend_contract_id from mst_amendment_contract left join mst_user u on u.user_id =amend_responsible_person where amend_exec_contract_id ="+exec_id+" order by amend_contract_id DESC ";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractParties> getContractPartiesByContractId(int contract_id) {
		try {
			String sql = " select * from trn_contract_parties where cont_party_contract_id =?  AND cont_party_type = 4";
			Query query = em.createNativeQuery(sql, ContractParties.class);
			query.setParameter(1, contract_id);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//For edit (In object)
	@Override
	public Object[] getAmendmentContractById(int amend_contract_id) {
		try {
			String sql = "select amend_contract_start_date, amend_contract_end_date, u.user_first_name, u.user_last_name as resposibleperson, amend_contract_term, amend_contract_clauses,amend_contract_status,amend_contract_id,amend_responsible_person, amend_exec_contract_id from mst_amendment_contract left join mst_user u on u.user_id = amend_responsible_person where amend_contract_id ="+amend_contract_id;
			Query query = em.createNativeQuery(sql);
			return (Object[]) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// For Update 
	
	
	@Override
	public AmendmentContract getAmendmentContById(int amend_contract_id) {
		try {
			String sql = "select * from mst_amendment_contract  where amend_contract_id ="+amend_contract_id;
			Query query = em.createNativeQuery(sql, AmendmentContract.class);
			return (AmendmentContract) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void updateRecord(Object object){
		
		try {
			em.merge(object);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
	}
	
	@Override
	public ContractParties getPartyById(int party_id) {
		try {
			String sql = " select * from trn_contract_parties where cont_party_id =?";
			Query query = em.createNativeQuery(sql, ContractParties.class);
			query.setParameter(1,party_id);
			return (ContractParties) query.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void mergeParties(Object object) {
		try {
			em.merge(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
@Override
public int deleteAmendmentContract(int amend_contract_id){
	try {
		String sql_one = "Delete from mst_amendment_contract where amend_contract_id =" + amend_contract_id;
		String sql_two = "Delete from trn_contract_parties where cont_party_contract_id= " + amend_contract_id
				+ " AND cont_party_type = 4";
		Query query1 = em.createNativeQuery(sql_one);
		Query query2 = em.createNativeQuery(sql_two);
		query1.executeUpdate();
		query2.executeUpdate();
		return 1;
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return 0;



}
    	
	
	
}
