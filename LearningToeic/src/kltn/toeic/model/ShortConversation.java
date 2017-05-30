package kltn.toeic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="shortconversation")
public class ShortConversation implements Serializable {
	@Id
	@Column(name="shortconversationid")
	private int shortconversationid;
	@Column(name="script")
	private String script;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "testid")
	@Id
	private Test test;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shortConversation", cascade = CascadeType.ALL)
	private List<ShortConversationDetail> shortConversationDetails = new ArrayList<ShortConversationDetail>();
	
	public ShortConversation() {
		super();
	}
	public ShortConversation(int shortconversationid, String script, Test test,
			List<ShortConversationDetail> shortConversationDetails) {
		super();
		this.shortconversationid = shortconversationid;
		this.script = script;
		this.test = test;
		this.shortConversationDetails = shortConversationDetails;
	}
	public int getShortconversationid() {
		return shortconversationid;
	}
	public void setShortconversationid(int shortconversationid) {
		this.shortconversationid = shortconversationid;
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
	public List<ShortConversationDetail> getShortConversationDetails() {
		return shortConversationDetails;
	}
	public void setShortConversationDetails(List<ShortConversationDetail> shortConversationDetails) {
		this.shortConversationDetails = shortConversationDetails;
	}
}