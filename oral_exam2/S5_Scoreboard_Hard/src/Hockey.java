/**
 * This class represents a game of Hockey. Initializing all proper ways of scoring points as well as how many periods there are.
 * @author  cjsindt
 * @version 1.0.0 30 OCT 2020
 */
public class Hockey extends Game{

    /**
     * Constructor that takes a home Team and an away Team object and generates a new game of Hockey between the teams
     * @param home  the home team
     * @param away  the away team
     */
    public Hockey(Team home, Team away){
        super(home, away);
        setScoringMethods(new ScoringMethod[]{new ScoringMethod("Goal", 1)});
    }

    /**
     * Overrides the isGameOver() method from Game and ends the game if the current period is 4
     * @return  true if the game is over, false otherwise
     */
    @Override
    boolean isGameOver() {
        return getPeriod() == 4;
    }

    /**
     * Returns the period name
     * @return  the name of the period
     */
    @Override
    String getPeriodName() {
        switch(getPeriod()){
            case 1:
                return "First Third";
            case 2:
                return "Second Third";
            case 3:
                return "Third Third";
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
        return "Third";
    }
}
