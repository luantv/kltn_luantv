package kltn.toeic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kltn.toeic.dao.VocabularyDAO;
import kltn.toeic.model.Vocabulary;
import kltn.toeic.util.ConstantValues;

@Repository
@Transactional
public class VocabularyImpl implements VocabularyDAO {
	@Autowired
	private SessionFactory mySessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Vocabulary> vocabularies() {
		String hql = "FROM " + Vocabulary.class.getName()+ " where enable=1 Order By vocabularyid ";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		return (List<Vocabulary>)query.list();
	}
	@Override
	public void create(Vocabulary vocabulary) {
		vocabulary.setEnable(1);
		mySessionFactory.getCurrentSession().save(vocabulary);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Vocabulary> vocabularies(String Search) {
		String hql = "FROM " + Vocabulary.class.getName()+" where vocabularyname like :search and enable=1 Order By vocabularyid desc";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("search", "%"+Search+"%");
		return (List<Vocabulary>)query.list();
	}
	@Override
	public Vocabulary select(int vocabularyid) {
		String hql = "FROM " + Vocabulary.class.getName()+ " WHERE vocabularyid = :vocabularyid and enable=1";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("vocabularyid",vocabularyid);
		try {
			return (Vocabulary) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
	}
	@Override
	public void delete(Vocabulary vocabulary) {
//		vocabulary.setEnable(0);
		mySessionFactory.getCurrentSession().delete(vocabulary);
	}
	@Override
	public void update(Vocabulary vocabulary) {
		mySessionFactory.getCurrentSession().update(vocabulary);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Vocabulary> getVocabularyPaging(int numPage) {
		String hql = "FROM " + Vocabulary.class.getName() + " WHERE enable = 1";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult((numPage-1)*ConstantValues.NUMBER_ROW_8);
		query.setMaxResults(ConstantValues.NUMBER_ROW_8);
		try {
			return query.list();
		} catch (Exception e) {
			return new ArrayList<Vocabulary>();
		}
	}
	@Override
	public long getVocabulary() {
		String hql = "FROM " + Vocabulary.class.getName() + " WHERE enable = 1";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		try {
			return query.list().size();
		} catch (Exception e) {
			return 0;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Vocabulary> searchVocabulary(String search, int numPage) {
		String hql = "FROM " + Vocabulary.class.getName() + " WHERE enable = 1 AND vocabularyname LIKE :search";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("search", "%"+search+"%");
		query.setFirstResult((numPage-1)*ConstantValues.NUMBER_ROW_8);
		query.setMaxResults(ConstantValues.NUMBER_ROW_8);
		try {
			return query.list();
		} catch (Exception e) {
			return new ArrayList<Vocabulary>();
		}
	}
	@Override
	public long getSearchVocabulary(String search) {
		String hql = "FROM " + Vocabulary.class.getName() + " WHERE enable = 1 AND vocabularyname LIKE :search";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("search", "%"+search+"%");
		try {
			return query.list().size();
		} catch (Exception e) {
			return 0;
		}
	}
	@Override
	public boolean checkExits(String vocabularyname) {
		String hql = "FROM " + Vocabulary.class.getName() + " WHERE vocabularyname = :vocabularyname";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("vocabularyname", vocabularyname);
		if (query.list().size() > 0) {
			return true;
		}
		return false;
	}
}
