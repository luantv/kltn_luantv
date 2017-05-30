package kltn.toeic.dao;

import java.util.List;

import kltn.toeic.model.Activity;

public interface ActivityDAO {
	public void createActivity(Activity activity);
	public List<Activity> getActivityByUser(String email, int numPage);
	public long getNumberActivityByUser(String email);
}