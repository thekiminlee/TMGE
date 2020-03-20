package tmge.engine.userComponents;

public class Score {
	public int bejeweledScore;
	public int tetrisScore;
	public String userName;
	
	Score(String name, int bjScore, int tetScore) {
		userName = name;
		bejeweledScore = bjScore;
		tetrisScore = tetScore;
	}
}
