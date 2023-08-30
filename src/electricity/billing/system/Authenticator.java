package electricity.billing.system;

public class Authenticator {
    public static boolean login(String username, String password, String usertype) {
        String[] userInfo = DataSource.getUserByName(username);
        String userPassword = userInfo[0];
        String userType = userInfo[1];

        if (userPassword.isEmpty() || !password.equals(userPassword) ||
                userType.isEmpty() || !usertype.equals(userType)) {
            return false;
        }
        return true;
    }
}
