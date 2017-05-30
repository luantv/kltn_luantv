package kltn.toeic.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "grammar")
public class Grammar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="grammarid")
	private  int grammarid;
	@Column(name="name")
	private String name ;
	@Column(name="content")
	private String content;
	@Column(name="description")
	private String description;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grammar", cascade = CascadeType.ALL)
	private List<Exercise> exercises;
	
	public Grammar() {
		super();
	}
	
	public Grammar(int grammarid, String name, String content, String description, List<Exercise> exercises,
			int enable) {
		super();
		this.grammarid = grammarid;
		this.name = name;
		this.content = content;
		this.description = description;
		this.exercises = exercises;
		this.enable = enable;
	}

	public int getGrammarid() {
		return grammarid;
	}
	public void setGrammarid(int lessonid) {
		this.grammarid = lessonid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private int enable;
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enabled) {
		this.enable = enabled;
	}
	public List<Exercise> getExercises() {
		return exercises;
	}
	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}	
}