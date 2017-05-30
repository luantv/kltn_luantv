package kltn.toeic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kltn.toeic.dao.GrammarDAO;
import kltn.toeic.model.Grammar;
import kltn.toeic.util.ConstantValues;

@Repository
@Transactional
@Component
public class GrammarImpl implements GrammarDAO {
	@Autowired
	private SessionFactory mySessionFactory;	

	@Override
	public void create(Grammar lesson) {
		lesson.setEnable(1);
		mySessionFactory.getCurrentSession().save(lesson);
	}

	@Override
	public Grammar select(int grammarid) {
		String hql = "FROM " + Grammar.class.getName()+ " WHERE grammarid = :grammarid and enable=1";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("grammarid",grammarid);
		try {
			return (Grammar) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Grammar select(String name) {
		String hql = "FROM " + Grammar.class.getName()+ " WHERE name = :name and enable=1";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("name",name);
		try {
			return (Grammar) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Grammar> listGrammar() {
		String hql = "FROM " + Grammar.class.getName()+ " WHERE enable=1 Order By grammarid desc ";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		return (List<Grammar>)query.list();
	}

	@Override
	public void update(Grammar lesson) {
		lesson.setEnable(1);
		mySessionFactory.getCurrentSession().update(lesson);
	}

	@Override
	public void delete(Grammar grammar) {
		try{
			grammar.setEnable(0);
			mySessionFactory.getCurrentSession().delete(grammar);
		}
		catch(Exception ex ){
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Grammar> listGrammar(String key) {
		String hql = "FROM " + Grammar.class.getName()+ " WHERE name like :key and enable=1 Order By grammarid desc";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("key","%"+key+"%");
		return (List<Grammar>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Grammar> getGrammarPaging(int numPage) {
		String hql = "FROM " + Grammar.class.getName() + " WHERE enable = 1";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult((numPage-1)*ConstantValues.NUMBER_ROW_6);
		query.setMaxResults(ConstantValues.NUMBER_ROW_6);
		try {
			return query.list();
		} catch (Exception e) {
			return new ArrayList<Grammar>();
		}
	}

	@Override
	public long getGrammar() {
		String hql = "FROM " + Grammar.class.getName() + " WHERE enable = 1";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		try {
			return query.list().size();
		} catch (Exception e) {
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Grammar> searchGrammar(String search, int numPage) {
		String hql = "FROM " + Grammar.class.getName() + " WHERE enable = 1 AND name LIKE :search";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("search", "%"+search+"%");
		query.setFirstResult((numPage-1)*ConstantValues.NUMBER_ROW_6);
		query.setMaxResults(ConstantValues.NUMBER_ROW_6);
		try {
			return query.list();
		} catch (Exception e) {
			return new ArrayList<Grammar>();
		}
	}

	@Override
	public long getSearchGrammar(String search) {
		String hql = "FROM " + Grammar.class.getName() + " WHERE enable = 1 AND name LIKE :search";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("search", "%"+search+"%");
		try {
			return query.list().size();
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public boolean checkExits(String grammarname) {
		String hql = "FROM " + Grammar.class.getName() + " WHERE name = :grammarname";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("grammarname", grammarname);
		if (query.list().size() > 0) {
			return true;
		}
		return false;
	}
}