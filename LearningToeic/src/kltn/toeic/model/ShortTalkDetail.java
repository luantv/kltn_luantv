package kltn.toeic.model;

import java.io.Serializable;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="shorttalkdetail")
public class ShortTalkDetail implements Serializable {
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
		@JoinColumn(name = "shorttalkid", insertable = false, updatable = false)
	})
	@Id
	private ShortTalk shortTalk;
	
	public ShortTalkDetail() {
		super();
	}
	public ShortTalkDetail(int id, String question, String answer, String correctanswer, ShortTalk shortTalk) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.correctanswer = correctanswer;
		this.shortTalk = shortTalk;
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
	public ShortTalk getShortTalk() {
		return shortTalk;
	}
	public void setShortTalk(ShortTalk shortTalk) {
		this.shortTalk = shortTalk;
	}
}