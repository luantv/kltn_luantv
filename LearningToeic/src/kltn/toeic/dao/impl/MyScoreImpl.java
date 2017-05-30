package kltn.toeic.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kltn.toeic.dao.MyScoreDAO;
import kltn.toeic.model.MyScore;

@Repository
@Transactional
public class MyScoreImpl implements MyScoreDAO {
	@Autowired
	private SessionFactory mySessionFactory;
	
	@Override
	public void create(MyScore myscore) {
		mySessionFactory.getCurrentSession().save(myscore);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MyScore> listScore(String email) {
		String hql = "FROM " + MyScore.class.getName() + " WHERE email = :email";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		try {
			return (List<MyScore>)query.list();
		} catch (Exception e) {
			return null;
		}
	}
}
