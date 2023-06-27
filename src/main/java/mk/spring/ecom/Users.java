package mk.spring.ecom;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userid;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	@Column(name = "loggedIn")
	private boolean loggedIn;

	public boolean isLoggedIn() {
	    return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
	    this.loggedIn = loggedIn;
	}

	public boolean getLoggedIn() {
	    return loggedIn;
	}


	public Users(Long userid, String username, String password, String email,boolean loggedIn) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.loggedIn = false;
		
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
	public Long getId() {
		return userid;
	}
	public void setId(Long id) {
		this.userid = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Users() {
		
	}
	@Override
	public String toString() {
		return "Users [id=" + userid + ", username=" + username + ", password=" + password + ", email="+ email+", loggedIn="+ loggedIn+"]";
	}
	
	
	
	
	
}
