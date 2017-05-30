package kltn.toeic.dao;

import kltn.toeic.model.Introduction;

public interface IntroductionDAO {
	public Introduction select(int id);
	public Introduction select(String name);
	public void update(Introduction intro);
}