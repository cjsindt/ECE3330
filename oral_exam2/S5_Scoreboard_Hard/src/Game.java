/**
 * This class generalizes a sports game. The four sports associated with it are American Football, Basketball, Football, and Hockey.
 * @author  cjsindt
 * @version 1.0.0 30 OCT 2020
 */
public abstract class Game {

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

    /**
     * Constructor that takes two teams and creates a game between the two
     * @param home  the home team
     * @param away  the away team
     */
    public Game(Team home, Team away){
        homeTeam = home;
        awayTeam = away;
        period = 1;
    }

    /**
     * Sets the teams playing in the game to the teams given
     * @param home  the home team
     * @param away  the away team
     */
    public void setTeams(Team home, Team away){
        homeTeam = home;
        awayTeam = away;
    }

    /**
     * Returns the home team object
     * @return  the home team
     */
    public Team getHomeTeam(){
        return homeTeam;
    }

    /**
     * Returns the away team object
     * @return  the away team
     */
    public Team getAwayTeam(){
        return awayTeam;
    }

    /**
     * Sets the different scoring methods of the game
     * @param methods   an array of each different scoring method possible
     */
    public void setScoringMethods(ScoringMethod[] methods){
        scoringMethods = methods;
    }

    /**
     * Returns the different scoring methods
     * @return  the ScoringMethods array
     */
    public ScoringMethod[] getScoringMethods(){
        return scoringMethods;
    }

    /**
     * Returns the current period of the game
     * @return  the current period
     */
    public int getPeriod(){
        return period;
    }

    /**
     * Ends the current period (advances to the next one)
     */
    public void endPeriod(){
        period++;
    }

    /**
     * Starts the game by setting the period to 1
     */
    public void startGame(){
        period = 1;
    }

    /**
     * Returns whichever team has the higher score. Home team wins ties.
     * @return  the team with the greater score
     */
    public Team winner(){
        return (homeTeam.getScore() >= awayTeam.getScore()) ? homeTeam : awayTeam;
    }

    /**
     * An abstract method that determines when the game is over depending on the specific game
     * @return  true if the game is over, false otherwise
     */
    abstract boolean isGameOver();

    /**
     * An abstract method that gets the period name in full. ex. First Quarter; Second Half.
     * Depends on the specific game
     * @return  the name of the current period
     */
    abstract String getPeriodName();

    /**
     * An abstract method that returns the length of a single period depending on the game being played
     * @return  the length of the period
     */
    abstract String getPeriodLength();

}
