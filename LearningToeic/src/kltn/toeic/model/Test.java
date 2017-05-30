package kltn.toeic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="test")
public class Test implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="testid")
	private int testid;
	@Column(name="testname")
	private String testname;
	@Column(name="enable")
	private int enable;
	@Column(name="level")
	private String level;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "test", cascade = CascadeType.ALL)
	private List<Photo> photos = new ArrayList<Photo>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "test", cascade = CascadeType.ALL)
	private List<IncompleteSentence> incompleteSentences;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "test", cascade = CascadeType.ALL)
	private List<QuestionResponse> questionResponses;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "test", cascade = CascadeType.ALL)
	private List<ShortConversation> shortConversations;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "test", cascade = CascadeType.ALL)
	private List<ShortTalk> shortTalks;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "test", cascade = CascadeType.ALL)
	private List<TextCompletion> textCompletions;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "test", cascade = CascadeType.ALL)
	private List<ReadingComprehension> readingComprehensions;
	
	public Test() {
		super();
	}
	public Test(int testid, String testname, int enable, String level, List<Photo> photos,
			List<IncompleteSentence> incompleteSentences, List<QuestionResponse> questionResponses,
			List<ShortConversation> shortConversations, List<ShortTalk> shortTalks,
			List<TextCompletion> textCompletions, List<ReadingComprehension> readingComprehensions) {
		super();
		this.testid = testid;
		this.testname = testname;
		this.enable = enable;
		this.level = level;
		this.photos = photos;
		this.incompleteSentences = incompleteSentences;
		this.questionResponses = questionResponses;
		this.shortConversations = shortConversations;
		this.shortTalks = shortTalks;
		this.textCompletions = textCompletions;
		this.readingComprehensions = readingComprehensions;
	}
	public int getTestid() {
		return testid;
	}
	public void setTestid(int testid) {
		this.testid = testid;
	}
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	public List<IncompleteSentence> getIncompleteSentences() {
		return incompleteSentences;
	}
	public void setIncompleteSentences(List<IncompleteSentence> incompleteSentences) {
		this.incompleteSentences = incompleteSentences;
	}
	public List<QuestionResponse> getQuestionResponses() {
		return questionResponses;
	}
	public void setQuestionResponses(List<QuestionResponse> questionResponses) {
		this.questionResponses = questionResponses;
	}
	public List<ShortConversation> getShortConversations() {
		return shortConversations;
	}
	public void setShortConversations(List<ShortConversation> shortConversations) {
		this.shortConversations = shortConversations;
	}
	public List<ShortTalk> getShortTalks() {
		return shortTalks;
	}
	public void setShortTalks(List<ShortTalk> shortTalks) {
		this.shortTalks = shortTalks;
	}
	public List<TextCompletion> getTextCompletions() {
		return textCompletions;
	}
	public void setTextCompletions(List<TextCompletion> textCompletions) {
		this.textCompletions = textCompletions;
	}
	public List<ReadingComprehension> getReadingComprehensions() {
		return readingComprehensions;
	}
	public void setReadingComprehensions(List<ReadingComprehension> readingComprehensions) {
		this.readingComprehensions = readingComprehensions;
	}
}