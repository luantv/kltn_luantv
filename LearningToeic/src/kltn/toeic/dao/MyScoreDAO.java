package kltn.toeic.dao;

import java.util.List;

import kltn.toeic.model.MyScore;

public interface MyScoreDAO {
	public void create(MyScore myscore);
	public List<MyScore> listScore(String email);
}