package kltn.toeic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="shorttalk")
public class ShortTalk implements Serializable {
	@Id
	@Column(name="shorttalkid")
	private int shorttalkid;
	@Column(name="script")
	private String script;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "testid")
	@Id
	private Test test;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shortTalk", cascade = CascadeType.ALL)
	private List<ShortTalkDetail> shortTalkDetails = new ArrayList<ShortTalkDetail>();
	
	public ShortTalk() {
		super();
	}
	public ShortTalk(int shorttalkid, String script, Test test, List<ShortTalkDetail> shortTalkDetails) {
		super();
		this.shorttalkid = shorttalkid;
		this.script = script;
		this.test = test;
		this.shortTalkDetails = shortTalkDetails;
	}
	public int getShorttalkid() {
		return shorttalkid;
	}
	public void setShorttalkid(int shorttalkid) {
		this.shorttalkid = shorttalkid;
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
	public List<ShortTalkDetail> getShortTalkDetails() {
		return shortTalkDetails;
	}
	public void setShortTalkDetails(List<ShortTalkDetail> shortTalkDetails) {
		this.shortTalkDetails = shortTalkDetails;
	}
}