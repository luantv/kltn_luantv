package kltn.toeic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kltn.toeic.dao.ExerciseDAO;
import kltn.toeic.model.Exercise;
import kltn.toeic.util.ConstantValues;

@Repository
@Transactional
public class ExerciseImpl implements ExerciseDAO {
	@Autowired
	private SessionFactory mySessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Exercise> select(int lessonid) {
		String hql = "FROM " + Exercise.class.getName()+ " WHERE grammarid = :lessonid";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql).setMaxResults(5);
		query.setParameter("lessonid",lessonid);
		try {
			return (List<Exercise>)query.list();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void create(Exercise exercise) {
		mySessionFactory.getCurrentSession().save(exercise);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exercise> select_limit(int lessonid) {
		String hql = "FROM " + Exercise.class.getName() + " WHERE grammarid = :lessonid ORDER BY RAND()";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql).setMaxResults(5);
		query.setParameter("lessonid", lessonid);
		try {
			return (List<Exercise>)query.list();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void delete(Exercise exercise) {
		mySessionFactory.getCurrentSession().delete(exercise);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exercise> getExercisePaging(int lessonid, int numPage) {
		String hql = "FROM " + Exercise.class.getName() + " WHERE grammarid = :lessonid";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("lessonid", lessonid);
		query.setFirstResult((numPage-1)*ConstantValues.NUMBER_ROW_9);
		query.setMaxResults(ConstantValues.NUMBER_ROW_9);
		try {
			return query.list();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public long getExercise(int lessonid) {
		String hql = "FROM " + Exercise.class.getName() + " WHERE grammarid = :lessonid";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("lessonid", lessonid);
		try {
			return query.list().size();
		} catch (Exception e) {
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exercise> listExercise(int lessonid) {
		String hql = "FROM " + Exercise.class.getName() + " WHERE grammarid = :lessonid";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("lessonid", lessonid);
		try {
			return query.list();
		} catch (Exception e) {
			return new ArrayList<Exercise>();
		}
	}

	@Override
	public Exercise selected(int exerciseid, int grammarid) {
		String hql = "FROM " + Exercise.class.getName() + " WHERE exerciseid = :exerciseid AND grammarid = :grammarid";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("exerciseid", exerciseid);
		query.setParameter("grammarid", grammarid);
		try {
			return (Exercise) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
	}
}