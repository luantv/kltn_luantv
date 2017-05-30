package kltn.toeic.dao;

import java.util.List;

import kltn.toeic.model.Vocabulary;

public interface VocabularyDAO {
	public List<Vocabulary> vocabularies();
	public void create(Vocabulary vocabulary);
	public List<Vocabulary> vocabularies(String Search);
	public Vocabulary select(int vocabularyid);
	public boolean checkExits(String vocabularyname);
	public void delete(Vocabulary vocabulary);
	public void update(Vocabulary vocabulary);
	public List<Vocabulary> getVocabularyPaging(int numPage);
	public List<Vocabulary> searchVocabulary(String search, int numPage);
	public long getSearchVocabulary(String search);
	public long getVocabulary();
}