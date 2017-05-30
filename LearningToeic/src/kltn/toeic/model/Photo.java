package kltn.toeic.model;

import java.io.Serializable;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="photo")
public class Photo implements Serializable {
	@Id
	@Column(name="photoid")
	private int photoid;
	@Column(name="answer")
	private String answer;
	@Column(name="correctanswer")
	private String correctanswer;
//	@Column(name="image")
//	private String image;
//	@Column(name="video")
//	private String video;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "testid")
	@Id
	private Test test;
	
	public Photo() {
		super();
	}
	public Photo(int photoid, String answer, String correctanswer, Test test) {
		super();
		this.photoid = photoid;
		this.answer = answer;
		this.correctanswer = correctanswer;
		this.test = test;
	}
	public int getPhotoid() {
		return photoid;
	}
	public void setPhotoid(int photoid) {
		this.photoid = photoid;
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
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
//	public String getImage() {
//		return image;
//	}
//	public void setImage(String image) {
//		this.image = image;
//	}
//	public String getVideo() {
//		return video;
//	}
//	public void setVideo(String video) {
//		this.video = video;
//	}
}