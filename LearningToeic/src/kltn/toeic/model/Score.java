package kltn.toeic.model;

import javax.persistence.*;

@Entity(name="score")
public class Score {
	@Id
	@Column(name="id")
	private int id;
	@Column(name="list")
	private int list;
	@Column(name="read")
	private int read;
	
	public Score() {
		super();
	}
	public Score(int id, int list, int read) {
		super();
		this.id = id;
		this.list = list;
		this.read = read;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getList() {
		return list;
	}
	public void setList(int list) {
		this.list = list;
	}
	public int getRead() {
		return read;
	}
	public void setRead(int read) {
		this.read = read;
	}
}