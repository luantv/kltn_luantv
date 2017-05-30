package kltn.toeic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vocabulary")
public class Vocabulary {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="vocabularyid")
	private int vocabularyid;
	@Column(name="vocabularyname")
	private String vocabularyname;
	@Column(name="document")
	private String document;
	@Column(name="video")
	private String video;
	@Column(name="enable")
	private int enable;
	@Column(name="image")
	private String image;
	
	public Vocabulary() {
		super();
	}
	
	public Vocabulary(int vocabularyid, String vocabularyname, String document, String video, int enable, String image) {
		super();
		this.vocabularyid = vocabularyid;
		this.vocabularyname = vocabularyname;
		this.document = document;
		this.video = video;
		this.enable = enable;
		this.image = image;
	}

	public int getVocabularyid() {
		return vocabularyid;
	}

	public void setVocabularyid(int vocabularyid) {
		this.vocabularyid = vocabularyid;
	}

	public String getVocabularyname() {
		return vocabularyname;
	}

	public void setVocabularyname(String vocabularyname) {
		this.vocabularyname = vocabularyname;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}