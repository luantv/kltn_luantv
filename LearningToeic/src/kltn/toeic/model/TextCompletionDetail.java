package kltn.toeic.model;

import java.io.Serializable;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="textcompletiondetail")
public class TextCompletionDetail implements Serializable {
	@Id
	private int id;
	@Column(name="answer")
	private String answer;
	@Column(name="correctanswer")
	private String correctanswer;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		  @JoinColumn(name = "textcompletionid", insertable = false, updatable = false),
		  @JoinColumn(name = "testid", insertable = false, updatable = false)
		})
	@Id
	private TextCompletion textCompletion;
	
	public TextCompletionDetail() {
		super();
	}
	public TextCompletionDetail(int id, String answer, String correctanswer, TextCompletion textCompletion) {
		super();
		this.id = id;
		this.answer = answer;
		this.correctanswer = correctanswer;
		this.textCompletion = textCompletion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public TextCompletion getTextCompletion() {
		return textCompletion;
	}
	public void setTextCompletion(TextCompletion textCompletion) {
		this.textCompletion = textCompletion;
	}
}