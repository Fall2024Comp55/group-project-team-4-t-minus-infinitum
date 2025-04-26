
public class GameData {
	private int totalScore = 0;
    private int totalBonusPoints = 0;
    
    public void addScore(int score) {
        totalScore += score;
    }
    
    public void addBonus(int bonus) {
        totalBonusPoints += bonus;
    }

    public int getTotalScore() {
        return totalScore;
    }
    
    public int getTotalBonusPoints() {
        return totalBonusPoints;
    }
}
