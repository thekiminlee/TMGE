package tmge.engine.userComponents;

import java.util.ArrayList;

public class LeaderBoard {
	ArrayList<Score> userScores;
	
	LeaderBoard() {
		userScores = new ArrayList<Score>();
		
		User user = User.getActiveUser();
		if (user != null) {
			userScores.add(user.getScore());
		}
	}
	
	
	
	
}
