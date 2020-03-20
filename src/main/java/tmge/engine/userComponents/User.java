package tmge.engine.userComponents;

import java.util.Hashtable;

public class User {
    private static Hashtable<String, User> table;
    private static User activeUser = null;
    private String name;
    private int bejeweledHighScore;
    private int tetrisHighScore;
    
    private User(String userName, int bejeweledScore, int tetrisScore) {
    	name = userName;
    	bejeweledHighScore = bejeweledScore;
    	tetrisHighScore = tetrisScore;
    }

    public static User login(String userName) {
    	if (!table.containsKey(userName)) {
    		table.put(userName, new User(userName, 0, 0));
    	}
    	User.activeUser = table.get(userName);
    	return User.activeUser;
    }
    
    public void logout() {
    	User.activeUser = null;
    }
    
    public String getName() {
    	return name;
    }
    
    public Score getScore() {
    	return new Score(name, bejeweledHighScore, tetrisHighScore);
    }
    
    public void setName(String newName) {
    	table.remove(name);
    	name = newName;
    	table.put(newName, this);
    }
    
    public void setBejeweledScore(int score) {
    	if (activeUser == null) return;
    	activeUser.bejeweledHighScore = score;
    }
    
    public void setTetrisScore(int score) {
    	if (activeUser == null) return;
    	activeUser.tetrisHighScore = score;
    }
    
    public static User getActiveUser() {
    	return activeUser;
    }
    
    public static Score getUserScore(String name) {
    	if (!table.containsKey(name)) return new Score(name, 0, 0);
    	User user = table.get(name);
    	return new Score(name, user.bejeweledHighScore, user.tetrisHighScore);
    }
    
}