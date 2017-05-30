package kltn.toeic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="textcompletion")
public class TextCompletion implements Serializable {
	@Id
	@Column(name="textcompletionid")
	private int textcompletionid;
	@Column(name="script")
	private String script;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "testid")
	@Id
	private Test test;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "textCompletion", cascade = CascadeType.ALL)
	private List<TextCompletionDetail> textCompletionDetails = new ArrayList<TextCompletionDetail>();

	public TextCompletion() {
		super();
	}
	public TextCompletion(int textcompletionid, String script, Test test,
			List<TextCompletionDetail> textCompletionDetails) {
		super();
		this.textcompletionid = textcompletionid;
		this.script = script;
		this.test = test;
		this.textCompletionDetails = textCompletionDetails;
	}
	public int getTextcompletionid() {
		return textcompletionid;
	}
	public void setTextcompletionid(int textcompletionid) {
		this.textcompletionid = textcompletionid;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	public List<TextCompletionDetail> getTextCompletionDetails() {
		return textCompletionDetails;
	}
	public void setTextCompletionDetails(List<TextCompletionDetail> textCompletionDetails) {
		this.textCompletionDetails = textCompletionDetails;
	}
}