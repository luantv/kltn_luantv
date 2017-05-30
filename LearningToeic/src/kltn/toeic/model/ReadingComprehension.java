package kltn.toeic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="readingcomprehension")
public class ReadingComprehension implements Serializable {
	@Id
	@Column(name="readingcomprehensionid")
	private int readingcomprehensionid;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "testid")
	@Id
	private Test test;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "readingComprehension", cascade = CascadeType.ALL)
	private List<ReadingComprehensionDetail> readingComprehensionDetails = new ArrayList<ReadingComprehensionDetail>();
	
	public ReadingComprehension() {
		super();
	}
	public ReadingComprehension(int readingcomprehensionid, Test test,
			List<ReadingComprehensionDetail> readingComprehensionDetails) {
		super();
		this.readingcomprehensionid = readingcomprehensionid;
		this.test = test;
		this.readingComprehensionDetails = readingComprehensionDetails;
	}
	public int getReadingcomprehensionid() {
		return readingcomprehensionid;
	}
	public void setReadingcomprehensionid(int readingcomprehensionid) {
		this.readingcomprehensionid = readingcomprehensionid;
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	public List<ReadingComprehensionDetail> getReadingComprehensionDetails() {
		return readingComprehensionDetails;
	}
	public void setReadingComprehensionDetails(List<ReadingComprehensionDetail> readingComprehensionDetails) {
		this.readingComprehensionDetails = readingComprehensionDetails;
	}
}