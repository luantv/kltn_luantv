package kltn.toeic.dao;

import java.util.List;

import kltn.toeic.model.Exercise;

public interface ExerciseDAO {
	public List<Exercise> select(int lessonid);
	public Exercise selected(int exerciseid, int grammarid);
	public void create(Exercise exercise);
	public void delete(Exercise exercise);
	public List<Exercise> select_limit(int lessonid);
	public List<Exercise> getExercisePaging(int lessonid, int numPage);
	public List<Exercise> listExercise(int lessonid);
	public long getExercise(int lessonid);
}