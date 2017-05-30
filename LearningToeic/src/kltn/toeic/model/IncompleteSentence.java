package kltn.toeic.model;

import java.io.Serializable;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="incompletesentence")
public class IncompleteSentence implements Serializable {
	@Id
	@Column(name="incompletesentenceid")
	private int incompletesentenceid;
	@Column(name="question")
	private String question;
	@Column(name="answer")
	private String answer;
	@Column(name="correctanswer")
	private String correctanswer;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "testid")
	@Id
	private Test test;
	
	public IncompleteSentence() {
		super();
	}
	public IncompleteSentence(int incompletesentenceid, String question, String answer, String correctanswer,
			Test test) {
		super();
		this.incompletesentenceid = incompletesentenceid;
		this.question = question;
		this.answer = answer;
		this.correctanswer = correctanswer;
		this.test = test;
	}
	public int getIncompletesentenceid() {
		return incompletesentenceid;
	}
	public void setIncompletesentenceid(int incompletesentenceid) {
		this.incompletesentenceid = incompletesentenceid;
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
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getCorrectanswer() {
		return correctanswer;
	}
	public void setCorrectanswer(String correctanswer) {
		this.correctanswer = correctanswer;
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
}