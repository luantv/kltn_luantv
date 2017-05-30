package kltn.toeic.dao;

import java.util.List;

import kltn.toeic.model.Test;

public interface TestDAO {
	public void createTest(Test test);
	public Test select(int testid);
	public Test select_easy();
	public Test select_medium();
	public Test select_hard();
	public List<Test> testList();
	public void delete(Test test);
	public long getTest();
	public List<Test> getTestPaging(int numPage);
	public List<Test> searchTest(String search, int numPage);
	public long getSearchTest(String search);
	public boolean checkExits(String testname);
}