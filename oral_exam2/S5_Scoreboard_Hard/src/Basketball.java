/**
 * This class represents a game of Basketball. Initializing all proper ways of scoring points as well as how many periods there are.
 * @author  cjsindt
 * @version 1.0.0 30 OCT 2020
 */
public class Basketball extends Game {

    /**
     * Constructor that takes a home Team and an away Team object and generates a new game of Basketball between the teams
     * @param home  the home team
     * @param away  the away team
     */
    public Basketball(Team home, Team away){
        super(home, away);
        setScoringMethods(new ScoringMethod[]{new ScoringMethod("3 Pointer", 3), new ScoringMethod("2 Pointer", 2), new ScoringMethod("1 Pointer", 1)});
    }

    /**
     * Overrides the isGameOver() method from Game and ends the game if the current period is 3
     * @return  true if the game is over, false otherwise
     */
    @Override
    public boolean isGameOver(){
        return getPeriod() == 3;
    }

    /**
     * Returns the period name
     * @return  the name of the period
     */
    @Override
    String getPeriodName() {
        switch(getPeriod()){
            case 1:
                return "First  Half";
            case 2:
                return "Second Half";
            default:
                return "";
        }
    }

    /**
     * Returns the length of a single period
     * @return  the length of a single period
     */
    @Override
    String getPeriodLength() {
        return "Half";
    }
}
