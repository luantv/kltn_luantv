package kltn.toeic.dao;

import java.util.List;

import kltn.toeic.model.Grammar;

public interface GrammarDAO {
	public void create(Grammar lesson);
	public void delete(Grammar lesson);
	public Grammar select(int lessonid);
	public Grammar select(String name);
	public List<Grammar> listGrammar();
	public List<Grammar> listGrammar(String key);
	public void update (Grammar lesson);
	public List<Grammar> getGrammarPaging(int numPage);
	public List<Grammar> searchGrammar(String search, int numPage);
	public long getSearchGrammar(String search);
	public long getGrammar();
	public boolean checkExits(String grammarname);
}