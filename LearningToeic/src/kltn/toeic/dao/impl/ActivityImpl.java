package kltn.toeic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kltn.toeic.dao.ActivityDAO;
import kltn.toeic.model.Activity;
import kltn.toeic.util.ConstantValues;

@Repository
@Transactional
public class ActivityImpl implements ActivityDAO {
	@Autowired
	private SessionFactory mySessionFactory;
	
	@Override
	public void createActivity(Activity activity) {
		mySessionFactory.getCurrentSession().save(activity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> getActivityByUser(String email, int numPage) {
		String hql = "FROM " + Activity.class.getName() + " WHERE email = :email ORDER BY activityid DESC";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		query.setFirstResult((numPage-1)*ConstantValues.NUMBER_ROW_9);
		query.setMaxResults(ConstantValues.NUMBER_ROW_9);
		try{
			return query.list();
		} catch (Exception e) {
			return new ArrayList<Activity>();
		}
	}

	@Override
	public long getNumberActivityByUser(String email) {
		String hql = "FROM " + Activity.class.getName() + " WHERE email = :email";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		try {
			return query.list().size();
		} catch(Exception e) {
			return 0;
		}
	}

}