package jfx.game.Library;

import java.util.HashMap;

public class Users {
    private User currentUser;

    private HashMap<String, User> users;

    public Users() {
        users = new HashMap<>();
    }

    public boolean login(String userName, String password) {
        if (users.containsKey(userName)) {
            return authenticateUser(users.get(userName), password);
        }
        return false;
    }

    public boolean createUser(String userName, String password) {
        if (!(users.containsKey(userName))) {
            User user = new User(userName, password);
            users.put(userName, user);
            return true;
        }
        return false;
    }

    private boolean authenticateUser(User user, String password) {
        if (user.authenticate(password)) {
            this.currentUser = user;
            return true;
        }
        return false;
    }
}