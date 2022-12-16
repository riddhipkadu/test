package lcmt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lcmt.dao.ArcManagerDao;
import lcmt.domain.ARCname;
import lcmt.domain.ArcManager;

@Repository(value="ARCmanagerDao")
@Transactional
public class ARCmanagerDaoImpl implements ArcManagerDao {
	@PersistenceContext
	private EntityManager em;

    //Method Created : Pranjali Kawale 
	@SuppressWarnings("unchecked")
	//Method Purpose : get All Asset Reconstruction Company Manager Name	
	@Override
	public List<Object> getAllArcManager() {
		// TODO Auto-generated method stub
		try {
			String sql = "select  mgr.mgr_arc_id, mgr.mgr_name,arc.arc_id, mgr.mgr_arc_contact_no, mgr.mgr_arc_email_id,mgr_arc_address,arc.arc_name from mst_arc_manager as mgr,  mst_arc_name as arc where mgr.mgr_arc_name = arc.arc_id";
			Query query = em.createNativeQuery(sql);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


 //Method Created : Pranjali Kawale
//Method Purpose : save Asset Reconstruction Company Manager Name	
	@Override
	public void persist(ArcManager arcManager) {
		// TODO Auto-generated method stub
		try {
		   em.persist(arcManager);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
//Method Created : Pranjali Kawale
//Method Purpose : get all Asset Reconstruction Company Manager Name by id	
	@Override
	public ArcManager getArcManagerById(int mgr_arc_id) {
		// TODO Auto-generated method stub
		try {
		String sql="select * from mst_arc_manager where mgr_arc_id=" + mgr_arc_id;
		Query query = em.createNativeQuery(sql, ArcManager.class);
		if (!query.getResultList().isEmpty()) {
			return (ArcManager) query.getResultList().get(0);
		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
		
	}


	@Override
	public void updateArcManager(ArcManager arcManager) {
		// TODO Auto-generated method stub
		try {
			
		em.merge(arcManager);
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}


	@Override
	public int deleteArcManager(int mgr_id) {
		// TODO Auto-generated method stub
		try {
			
			String sql="Delete from mst_arc_manager where mgr_arc_id="+mgr_id;
			Query query=em.createNativeQuery(sql);
			int deleteCount=query.executeUpdate();
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int isArcManagerExist(int mgr_arc_id, String mgr_name) {
		// TODO Auto-generated method stub
		try {
			String sql = "select count(*) as Mgrcount from mst_arc_manager where mgr_name='" + mgr_name + "' " + " ";
			if (mgr_arc_id != 0) {
				sql += " AND mgr_arc_id !=" + mgr_arc_id;
			}
			Query query = em.createNativeQuery(sql);
			String count = query.getResultList().get(0).toString();
			return Integer.parseInt(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	@SuppressWarnings("unchecked")
	@Override
	public int  checkArcManagerDependancy(int mgr_id) {
		// TODO Auto-generated method stub
		try {
		
		String sql=" Select  case when sarf.sarf_mgr_name> 0 then 1 else 0 END as sarf_manger "
				+ "from mst_arc_manager mgr left join mst_sarfaesiact sarf on mgr.mgr_arc_id = sarf.sarf_act_id where mgr_arc_id=" +mgr_id +" LIMIT 1";
		Query query=em.createNativeQuery(sql);
		int count = Integer.parseInt(query.getResultList().get(0).toString());
		return count;
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return 0;
	}


	@Override
	public int isARCManagerEmailExist(int arc_id, String mgr_arc_email_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "select count(*) as Emailcount from mst_arc_manager where mgr_arc_email_id='" + mgr_arc_email_id + "' " + " ";
			if (arc_id != 0) {
				sql += " AND arc_id !=" + arc_id;
			}
			Query query = em.createNativeQuery(sql);
			String count = query.getResultList().get(0).toString();
			return Integer.parseInt(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	
}
