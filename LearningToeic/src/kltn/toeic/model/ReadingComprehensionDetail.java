package kltn.toeic.model;

import java.io.Serializable;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="readingcomprehensiondetail")
public class ReadingComprehensionDetail implements Serializable {
	@Id
	@Column(name="id")
	private int id;
	@Column(name="question")
	private String question;
	@Column(name="answer")
	private String answer;
	@Column(name="correctanswer")
	private String correctanswer;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		  @JoinColumn(name = "testid", insertable = false, updatable = false),
		  @JoinColumn(name = "readingcomprehensionid", insertable = false, updatable = false)
		})
	@Id
	private ReadingComprehension readingComprehension;
	
	public ReadingComprehensionDetail() {
		super();
	}
	public ReadingComprehensionDetail(int id, String question, String answer, String correctanswer,
			ReadingComprehension readingComprehension) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.correctanswer = correctanswer;
		this.readingComprehension = readingComprehension;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public ReadingComprehension getReadingComprehension() {
		return readingComprehension;
	}
	public void setReadingComprehension(ReadingComprehension readingComprehension) {
		this.readingComprehension = readingComprehension;
	}
}