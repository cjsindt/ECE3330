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
    private int period = 1;

    /**
     * Array of all the different scoring methods.
     */
    private static ScoringMethod[] scoringMethods;

    public Game(Team home, Team away){
        homeTeam = home;
        awayTeam = away;
        period = 0;
    }

    public void setTeams(Team home, Team away){
        homeTeam = home;
        awayTeam = away;
    }

    public Team getHomeTeam(){
        return homeTeam;
    }

    public Team getAwayTeam(){
        return awayTeam;
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

    public int getPeriod(){
        return period;
    }

    public void endPeriod(){
        period++;
    }

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

    abstract boolean isGameOver();

    abstract String getPeriodName();

    abstract String getPeriodLength();

}
