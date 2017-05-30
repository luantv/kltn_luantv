package kltn.toeic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kltn.toeic.dao.TestDAO;
import kltn.toeic.model.Test;
import kltn.toeic.util.ConstantValues;

@Repository
@Transactional
@Component
public class TestImpl implements TestDAO {
	@Autowired
	SessionFactory mySessionFactory;
	
	@Override
	public void createTest(Test test) {
		mySessionFactory.getCurrentSession().save(test);
	}

	@Override
	public Test select(int testid) {
		String hql = "FROM " + Test.class.getName()+ " WHERE testid = :testid AND enable=1";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("testid",testid);
		return (Test) query.list().get(0);
//		 return mySessionFactory.getCurrentSession().get(Test.class, testid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> testList() {
		String hql = "FROM " + Test.class.getName()+ " WHERE enable=1 ORDER BY testid DESC";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		return (List<Test>)query.list();
//		return mySessionFactory.getCurrentSession().createQuery("FROM " + Test.class.getName()).list();
	}

	@Override
	public void delete(Test test) {
		mySessionFactory.getCurrentSession().delete(test);
	}

	@Override
	public Test select_easy() {
		String hql = "FROM " + Test.class.getName() + " WHERE enable=1 AND level='Easy' ORDER BY RAND()";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql).setMaxResults(1);
		try {
			return (Test) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Test select_medium() {
		String hql = "FROM " + Test.class.getName() + " WHERE enable=1 AND level='Medium' ORDER BY RAND()";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql).setMaxResults(1);
		try {
			return (Test) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Test select_hard() {
		String hql = "FROM " + Test.class.getName() + " WHERE enable=1 AND level='Hard' ORDER BY RAND()";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql).setMaxResults(1);
		try {
			return (Test) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public long getTest() {
		String hql = "FROM " + Test.class.getName() + " WHERE enable = 1";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		try {
			return query.list().size();
		} catch (Exception e) {
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> getTestPaging(int numPage) {
		String hql = "FROM " + Test.class.getName() + " WHERE enable = 1";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult((numPage-1)*ConstantValues.NUMBER_ROW_6);
		query.setMaxResults(ConstantValues.NUMBER_ROW_6);
		try {
			return query.list();
		} catch (Exception e) {
			return new ArrayList<Test>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> searchTest(String search, int numPage) {
		String hql = "FROM " + Test.class.getName() + " WHERE enable = 1 AND testname LIKE :search";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("search", "%"+search+"%");
		query.setFirstResult((numPage-1)*ConstantValues.NUMBER_ROW_6);
		query.setMaxResults(ConstantValues.NUMBER_ROW_6);
		try {
			return query.list();
		} catch (Exception e) {
			return new ArrayList<Test>();
		}
	}
	@Override
	public long getSearchTest(String search) {
		String hql = "FROM " + Test.class.getName() + " WHERE enable = 1 AND testname LIKE :search";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("search", "%"+search+"%");
		try {
			return query.list().size();
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public boolean checkExits(String testname) {
		String hql = "FROM " + Test.class.getName() + " WHERE testname = :testname";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("testname", testname);
		if (query.list().size() > 0) {
			return true;
		}
		return false;
	}
}
