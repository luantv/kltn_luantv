package kltn.toeic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="myscore")
public class MyScore implements Serializable {
	@Id
	@Column(name="myscoreid")
	private int myscoreid;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="email")
	@Id
	private User user;
	
	@Column(name="testid")
	private int testid;
	@Column(name="score")
	private int score;
	@Column(name="timeTest")
	private Date timeTest;
	
	public MyScore() {
		super();
	}
	public MyScore(int myscoreid, User user, int testid, int score, Date timeTest) {
		super();
		this.myscoreid = myscoreid;
		this.user = user;
		this.testid = testid;
		this.score = score;
		this.timeTest = timeTest;
	}
	public MyScore(int myscoreid, int score, Date timeTest) {
		super();
		this.myscoreid = myscoreid;
		this.score = score;
		this.timeTest = timeTest;
	}
	public int getMyscoreid() {
		return myscoreid;
	}
	public void setMyscoreid(int myscoreid) {
		this.myscoreid = myscoreid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getTestid() {
		return testid;
	}
	public void setTestid(int testid) {
		this.testid = testid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Date getTimeTest() {
		return timeTest;
	}
	public void setTimeTest(Date timeTest) {
		this.timeTest = timeTest;
	}
}