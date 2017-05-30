package kltn.toeic.dao;

import java.util.List;

import kltn.toeic.model.User;

public interface UserDAO {
	public User select(String email);
	public void createUser(User user);
	public boolean checkUser(String email);
	public boolean checkAdmin(User user);
	public boolean checkSubAdmin(User user);
	public boolean checkConfirm(String confirmCode);
	public void enable(String confirmCode);
	public void update(User user);
	public List<User> listUsers();
	public List<User> getUserPaging(int numPage);
	public List<User> getUserActivePaging(int numPage);
	public List<User> getUserBlockedPaging(int numPage);
	public List<User> searchUser(String search, int numPage);
	public long getSearchUser(String search);
	public long getUser();
	public long getUserActive();
	public long getUserBlocked();
	public void delete(User user);
	public boolean activeUser(User user);
	public boolean blockUser(User user);
}