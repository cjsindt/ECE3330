public class Game {

    /**
     * The home team
     */
    private Team homeTeam;
    /**
     * The away team
     */
    private Team awayTeam;
    /**
     * The current period of the game.
     */
    private int period;

    /**
     * Array of all the different scoring methods.
     */
    private static ScoringMethod[] scoringMethods;

    public Game(Team home, Team away){
        homeTeam = home;
        awayTeam = away;
        period = 0;
    }

    public void addScore(ScoringMethod score, Team team){
        team.addScore(score.getValue());
    }

    public void setScoringMethods(ScoringMethod[] methods){
        scoringMethods = methods;
    }

    public ScoringMethod[] getScoringMethods(){
        return scoringMethods;
    }
}
