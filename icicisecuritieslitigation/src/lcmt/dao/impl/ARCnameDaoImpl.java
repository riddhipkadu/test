package lcmt.dao.impl;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lcmt.dao.ARCnameDao;
import lcmt.domain.ARCname;


@Repository(value = "ARCnameDao")
@Transactional
public class ARCnameDaoImpl implements ARCnameDao {

	@PersistenceContext
	private EntityManager em;
	
//Method Created :  Pranjali Kawale
//Method Purpose : Save Asset Reconstruction Company Name
	@Override
	public void persist(ARCname arcName) {
		// TODO Auto-generated method stub
		try {
	          em.persist(arcName);
			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} 
		
		
	}
	//Method Created : Pranjali Kawale
	//Method Purpose : get All Asset Reconstruction Company Name
	@Override
	public <T> List<T> getAllARCname(Class<T> clazz) {
		// TODO Auto-generated method stub
		try {
			TypedQuery<T> query = em.createQuery("from " + clazz.getName(), clazz);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//Method Created : Pranjali Kawale
//Method Purpose : get the Asset Reconstruction Company Name by id
	@Override
	public ARCname getARCnameById(int arc_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from mst_arc_name where arc_id=" + arc_id;
			Query query = em.createNativeQuery(sql, ARCname.class);
			if (!query.getResultList().isEmpty()) {
				return (ARCname) query.getResultList().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


//Method Created : Pranjali Kawale
//Method Purpose : update the new Asset Reconstruction Company Name
	@Override
	public void updateARCname(ARCname arcName) {
		// TODO Auto-generated method stub
	   try {
		em.merge(arcName);
	   } catch (Exception e) {
			// TODO: handle exception
		   e.printStackTrace();
		}
		
	}

// Method Created : Pranjali Kawale
// Method Purpose : Delete Asset Reconstruction Company Name		
	@Override
	public int deleteARCname(int arc_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM mst_arc_name WHERE arc_id =" + arc_id;
			Query query = em.createNativeQuery(sql);
			int deleteCount = query.executeUpdate();
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int isARCNameExist(int arc_id, String arc_name) {
		// TODO Auto-generated method stub
		try {
			String sql = "select count(*) as ARCcount from mst_arc_name where arc_name='" + arc_name + "' " + " ";
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
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> checkARCnameDependancy(int arc_id) {
		// TODO Auto-generated method stub
		try{
		String sql="Select  case when sarf.sarf_arc_name> 0 then 1 else 0 END as sarf_ARC,"
                  + "case when mgr.mgr_arc_name> 0 then 1 else 0 END as mgr_ARC"
                  + " from mst_arc_name arc left join mst_sarfaesiact sarf on arc.arc_id = sarf.sarf_act_id"
                  + " left join mst_arc_manager mgr on arc.arc_id = mgr.mgr_arc_id where arc_id="+arc_id ;
		//System.out.println(arc_id);
		Query query=em.createNativeQuery(sql);
		//System.out.println(sql);
		return query.getResultList();
		 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
	@Override
	public int isARCNameEmailExist(int arc_id, String arc_email_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "select count(*) as Emailcount from mst_arc_name where arc_email_id='" + arc_email_id + "' " + " ";
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
