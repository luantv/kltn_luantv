package kltn.toeic.model;

import java.io.Serializable;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="exercise")
public class Exercise implements Serializable{
	@Id
	@Column(name ="exerciseid")
	private int exerciseid;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grammarid")
	@Id
	private Grammar grammar;
	@Column(name="question")
	private String question;
	@Column(name="answer")
	private String answer;
	@Column(name="correctanswer")
	private String correctanswer;
	
	public Exercise() {
		super();
	}
	
	public Exercise(int exerciseid, Grammar grammar, String question, String answer, String correctanswer) {
		super();
		this.exerciseid = exerciseid;
		this.grammar = grammar;
		this.question = question;
		this.answer = answer;
		this.correctanswer = correctanswer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getExerciseid() {
		return exerciseid;
	}
	public void setExerciseid(int exerciseid) {
		this.exerciseid = exerciseid;
	}
	
	public Grammar getGrammar() {
		return grammar;
	}
	public void setGrammar(Grammar lesson) {
		this.grammar = lesson;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public String getCorrectanswer() {
		return correctanswer;
	}
	public void setCorrectanswer(String correctanswer) {
		this.correctanswer = correctanswer;
	}	
}