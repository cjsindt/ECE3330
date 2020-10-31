/**
 * This class represents a team who is playing a game. Each team has a name and a score associated with them.
 * @author cjsindt
 * @version 1.0.0 30 OCT 2020
 */
public class Team {
    /**
     * The team name
     */
    private String teamName;

    /**
     * The team's score
     */
    private int score;

    /**
     * A constructor that takes the team's name and sets the score to 0
     * @param name  the team name
     */
    public Team(String name){
        teamName = name;
        score = 0;
    }

    /**
     * Returns the current score of the team
     * @return  the current score
     */
    public int getScore(){
        return score;
    }

    /**
     * Adds to the score the value specified
     * @param add   the amount to add to the score
     */
    public void addScore(int add){
        score += add;
    }

    /**
     * Returns the team's name
     * @return  the team name
     */
    public String getTeamName(){
        return teamName;
    }
}
