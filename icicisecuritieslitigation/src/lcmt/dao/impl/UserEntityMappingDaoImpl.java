package lcmt.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import lcmt.dao.UserEntityMappingDao;

@Repository(value = "userEntityMappingDao")
@Transactional
public class UserEntityMappingDaoImpl implements UserEntityMappingDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void persist(Object obj) {
		try {
			em.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
