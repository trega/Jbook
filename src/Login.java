
public class Login {
	 
    public static boolean authenticate(String username, String password) {
        if (username.equals("trega") && password.equals("trega")) {
            return true;
        }
        return false;
    }
}
