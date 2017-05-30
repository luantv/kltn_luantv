package kltn.toeic.model;

import java.io.Serializable;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="questionresponse")
public class QuestionResponse implements Serializable {
	@Id
	@Column(name="questionresponseid")
	private int questionresponseid;
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
	
	public QuestionResponse() {
		super();
	}
	public QuestionResponse(int questionresponseid, String question, String answer, String correctanswer, Test test) {
		super();
		this.questionresponseid = questionresponseid;
		this.question = question;
		this.answer = answer;
		this.correctanswer = correctanswer;
		this.test = test;
	}
	public int getQuestionresponseid() {
		return questionresponseid;
	}
	public void setQuestionresponseid(int questionresponseid) {
		this.questionresponseid = questionresponseid;
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