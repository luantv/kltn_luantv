package kltn.toeic.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kltn.toeic.dao.IntroductionDAO;
import kltn.toeic.model.Introduction;

@Repository
@Transactional
@Component
public class IntroductionImpl implements IntroductionDAO {
	@Autowired
	SessionFactory mySessionFactory;

	@Override
	public Introduction select(int id) {
		String hql = "FROM " + Introduction.class.getName() + " WHERE id = :id";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		if (query.list().size() > 0) {
			return (Introduction) query.list().get(0);
		} else {
			return null;
		}
	}

	@Override
	public Introduction select(String name) {
		String hql = "FROM " + Introduction.class.getName() + " WHERE name = :name";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("name", name);
		if (query.list().size() > 0) {
			return (Introduction) query.list().get(0);
		} else {
			return null;
		}
	}

	@Override
	public void update(Introduction intro) {
		mySessionFactory.getCurrentSession().update(intro);
	}

}