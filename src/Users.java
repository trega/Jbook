import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Users implements Serializable{
	
	private static final long serialVersionUID = -4493929646087794726L;
	private Map<String,String> registered_users;
	private  String users_filename = "users.log";
	
	public Users() {
		registered_users = new HashMap<String, String>();
		deserializeUsers();
	}
	
	public boolean addUser(User u){
		if(registered_users.containsKey(u.getUsername()) == false){
			registered_users.put(u.getUsername(), u.getPassword());
			serializeUsers();
			return true;
		}else{
			return false;
		}
	}
	
	public boolean verifyLogin(String login, String pass){
		if(registered_users.containsKey(login) == false){
			return false;
		}else if (registered_users.get(login).equals(pass)){
			return true;
		}else{
			return false;
		}
	}
	
	private void serializeUsers(){
		try {
			FileOutputStream fileOut = new FileOutputStream(users_filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(registered_users);
			out.flush();
			out.close();
			fileOut.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void deserializeUsers(){
		ObjectInputStream in = null;
		try {
			FileInputStream file = new FileInputStream(users_filename);
			in = new ObjectInputStream(file);
		}catch (FileNotFoundException e) {			
			addUser(new User("trega","trega"));
			return;
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		try {
			registered_users = (Map<String,String>)in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		 try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
