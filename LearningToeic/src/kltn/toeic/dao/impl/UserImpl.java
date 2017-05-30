package kltn.toeic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kltn.toeic.dao.UserDAO;
import kltn.toeic.model.User;
import kltn.toeic.util.ConstantValues;

@Repository
@Transactional
public class UserImpl implements UserDAO {
	@Autowired
	private SessionFactory mySessionFactory;
	
	@Override
	public User select(String email) {
		String hql = "FROM " + User.class.getName()+ " WHERE email = :email";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("email",email);
		return (User) query.list().get(0);
	}
	
	@Override
	public void createUser(User user) {
		mySessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public boolean checkUser(String email) {
		String hql="from "+ User.class.getName()+" where email = :email";
		Query query=mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		return !query.list().isEmpty();
	}
	
	@Override
	public boolean checkConfirm(String confirmCode) {
		String hql="from "+ User.class.getName()+" where confirm = :confirm";
		Query query=mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("confirm", confirmCode);
		return !query.list().isEmpty();
	}
	
	@Override
	public void enable(String confirmCode) {
		String hql = "UPDATE "+User.class.getName()+" set enabled = 1,confirm='' where confirm = :confirmCode";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("confirmCode", confirmCode);
		query.executeUpdate();
	}
	
	@Override
	public void update(User user) {
		mySessionFactory.getCurrentSession().update(user);
	}
	
	@Override
	public void delete(User user) {
		mySessionFactory.getCurrentSession().delete(user);
	}
	
	@Override
	public boolean checkAdmin(User user) {
		if (user.getRole().equals("ROLE_ADMIN")) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean checkSubAdmin(User user) {
		if (user.getRole().equals("ROLE_SUBADMIN")) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserPaging(int numPage) {
		String hql = "FROM " + User.class.getName() + " WHERE role <>'ROLE_SUBADMIN'";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult((numPage-1)*ConstantValues.NUMBER_ROW_6);
		query.setMaxResults(ConstantValues.NUMBER_ROW_6);
		try {
			return query.list();
		} catch (Exception e) {
			return new ArrayList<User>();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserActivePaging(int numPage) {
		String hql = "FROM " + User.class.getName() + " WHERE role <>'ROLE_SUBADMIN' AND enabled = 1";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult((numPage-1)*ConstantValues.NUMBER_ROW_6);
		query.setMaxResults(ConstantValues.NUMBER_ROW_6);
		try {
			return query.list();
		} catch (Exception e) {
			return new ArrayList<User>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserBlockedPaging(int numPage) {
		String hql = "FROM " + User.class.getName() + " WHERE role <>'ROLE_SUBADMIN' AND enabled = 0";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult((numPage-1)*ConstantValues.NUMBER_ROW_6);
		query.setMaxResults(ConstantValues.NUMBER_ROW_6);
		try {
			return query.list();
		} catch (Exception e) {
			return new ArrayList<User>();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() {
		String hql = "FROM " + User.class.getName() + " WHERE role <>'ROLE_SUBADMIN'";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		try {
			return query.list();
		} catch (Exception e) {
			return new ArrayList<User>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> searchUser(String search, int numPage) {
		String hql = "FROM " + User.class.getName() + " WHERE role <>'ROLE_SUBADMIN' AND email LIKE :search";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("search", "%"+search+"%");
		query.setFirstResult((numPage-1)*ConstantValues.NUMBER_ROW_6);
		query.setMaxResults(ConstantValues.NUMBER_ROW_6);
		try {
			return query.list();
		} catch (Exception e) {
			return new ArrayList<User>();
		}
	}

	@Override
	public long getSearchUser(String search) {
		String hql = "FROM " + User.class.getName() + " WHERE role <>'ROLE_SUBADMIN' AND email LIKE :search";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("search", "%"+search+"%");
		try {
			return query.list().size();
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public long getUser() {
		String hql = "FROM " + User.class.getName() + " WHERE role <>'ROLE_SUBADMIN'";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		try {
			return query.list().size();
		} catch (Exception e) {
			return 0;
		}
	}
	
	@Override
	public long getUserActive() {
		String hql = "FROM " + User.class.getName() + " WHERE role <>'ROLE_SUBADMIN' AND enabled = 1";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		try {
			return query.list().size();
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public long getUserBlocked() {
		String hql = "FROM " + User.class.getName() + " WHERE role <>'ROLE_SUBADMIN' AND enabled = 0";
		Query query = mySessionFactory.getCurrentSession().createQuery(hql);
		try {
			return query.list().size();
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public boolean activeUser(User user) {
		if (user != null) {
			user.setEnabled(1);
			return true;
		}
		return false;
	}

	@Override
	public boolean blockUser(User user) {
		if (user != null) {
			user.setEnabled(0);
			return true;
		}
		return false;
	}
	
}