package kltn.toeic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="activity")
public class Activity implements Serializable {
	@Id
	@Column(name="activityid")
	private int activityid;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="email")
	@Id
	private User user;
	
	@Column(name="content", columnDefinition="TEXT")
	private String content;
	
	@Column(name="createDate")
	private Date createDate;

	public Activity() {
		super();
	}
	public Activity(int activityid, String content, Date createDate, User user) {
		super();
		this.activityid = activityid;
		this.content = content;
		this.createDate = createDate;
		this.user = user;
	}
	public int getActivityid() {
		return activityid;
	}
	public void setActivityid(int activityid) {
		this.activityid = activityid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}