package src;

public class Score {

    static int recordScore;
    int currentScore;

    public Score() {
        currentScore = 0;
    }

    public static int getRecordScore() {
        return recordScore;
    }

    public static void setRecordScore(int recordScore) {
        Score.recordScore = recordScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public void addToScore(int addition) {
        currentScore += addition;
    }


}
