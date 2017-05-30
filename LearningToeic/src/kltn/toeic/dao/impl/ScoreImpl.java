package kltn.toeic.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kltn.toeic.dao.ScoreDAO;
import kltn.toeic.model.Score;

@Repository
@Transactional
public class ScoreImpl implements ScoreDAO {
	@Autowired
	SessionFactory mySessionFactory;
	
	@Override
	public int List(int id) {
//		String hql = "FROM " + Score.class.getName() + " WHERE id = :id";
//		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
//		query.setParameter("id", id);
//		Score sc = (Score) query.list().get(0);
//		return sc.getList();
		return (int)mySessionFactory.getCurrentSession().get(Score.class, id).getList();
	}

	@Override
	public int Read(int id) {
//		String hql = "FROM " + Score.class.getName() + " WHERE id = :id";
//		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
//		query.setParameter("id", id);
//		Score sc = (Score) query.list().get(0);
//		return sc.getRead();
		return (int)mySessionFactory.getCurrentSession().get(Score.class, id).getRead();
	}

}