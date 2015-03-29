import java.io.Serializable;


public class User implements Serializable{
	private static final long serialVersionUID = 2261492720508763943L;
	private String username;
	private String password;
	
	User(String login, String pass){
		setUsername(login);
		setPassword(pass);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
