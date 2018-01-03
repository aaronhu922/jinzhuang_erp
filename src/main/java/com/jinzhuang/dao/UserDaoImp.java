package com.jinzhuang.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jinzhuang.model.User;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SessionFactory sessionFactory;

	public long save(User user) {
		sessionFactory.getCurrentSession().save(user);
		return user.getId();
	}

	public List<User> list() {
		@SuppressWarnings("unchecked")
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
		for (User user : query.getResultList()) {
			logger.info(user.toString());
		}
		return query.getResultList();
	}

	public User login(User user) {
		User rsUser = (User) sessionFactory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("userName", user.getUserName()))
				.add(Restrictions.eq("password", user.getPassword())).list().get(0);

		return rsUser;
		

	}

	public List<User> findUsers(Map<String, Object> map) {
		// TODO Auto-generated method stub
//		CriteriaQuery<User> cq = sessionFactory.getCurrentSession().createQuery(User.class);
//		Metamodel m = em.getMetamodel();
//		EntityType<Pet> Pet_ = m.entity(Pet.class);
//		Root<Pet> pet = cq.from(Pet.class);
//		cq.where(cb.equal(pet.get(Pet_.name), "Fido")
//		    .and(cb.equal(pet.get(Pet_.color), "brown");

		return null;
	}

	public Long getTotalUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	public long updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
		return 0;
	}

	public int addUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long deleteUser(Long id) {
//		Query query = sessionFactory.getCurrentSession().createQuery("delete user where uid = :uid");
//		query.setParameter("uid", id);
//		int result = query.executeUpdate();
//		return result;
		
		Session session = sessionFactory.getCurrentSession();
		User user = session.byId(User.class).load(id);
		session.delete(user);
		logger.info("Delete user id= "+id+", name= "+user.getUserName());
		return id;
	}

}
