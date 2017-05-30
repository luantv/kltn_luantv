package kltn.toeic.model;

import java.io.Serializable;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="shortconversationdetail")
public class ShortConversationDetail implements Serializable {
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
		  @JoinColumn(name = "shortconversationid", insertable = false, updatable = false)
		})
	@Id
	private ShortConversation shortConversation;
	
	public ShortConversationDetail() {
		super();
	}
	public ShortConversationDetail(int id, String question, String answer, String correctanswer,
			ShortConversation shortConversation) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.correctanswer = correctanswer;
		this.shortConversation = shortConversation;
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
	public ShortConversation getShortConversation() {
		return shortConversation;
	}
	public void setShortConversation(ShortConversation shortConversation) {
		this.shortConversation = shortConversation;
	}
}