package dbresource;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Member {
	private long id;
	private String username;
	private String password;
	private String name;
	private String email;
	private String phone;
	private LocalDateTime createDate;
	int score;
	
	public Member() {
	
	}

	public Member(String username, String password, String name, String email, String phone,
			LocalDateTime createDate) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.createDate = createDate;
		score = 0;
	}

	public int getScore() {return score;}
	public void setScore(int score) {this.score = score;}
	public long getId() {return id;}
	public void setId(long id) {this.id = id;}
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getPhone() {return phone;}
	public void setPhone(String phone) {this.phone = phone;}
	public LocalDateTime getCreateDate() {return createDate;}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
}
