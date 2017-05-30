package kltn.toeic.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="fullname")
	private String fullname;
	@Column(name="enabled")
	private int enabled;
	@Column(name="confirm")
	private String confirm;
	@Column(name="role")
	private String role;
	@Column(name="username")
	private String username;
	@Column(name="image")
	private String image;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private List<Activity> activities;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private List<MyScore> myscores;
	
	public User(){
	}
	public User(String email, String password, String fullname, int enabled, String confirm, String role,
			String username, String image, List<Activity> activities, List<MyScore> myscores) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.enabled = enabled;
		this.confirm = confirm;
		this.role = role;
		this.username = username;
		this.image = image;
		this.activities = activities;
		this.myscores = myscores;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	public List<MyScore> getMyscores() {
		return myscores;
	}
	public void setMyscores(List<MyScore> myscores) {
		this.myscores = myscores;
	}
}