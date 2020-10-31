/**
 * This class represents a game of American Football. Initializing all proper ways of scoring points as well as how many periods there are.
 * @author  cjsindt
 * @version 1.0.0 30 OCT 2020
 */
public class AmericanFootball extends Game{

    /**
     * Constructor that takes a home Team and an away Team object and generates a new game of American Football between the teams
     * @param home  the home team
     * @param away  the away team
     */
    public AmericanFootball(Team home, Team away){
        super(home, away);
        setScoringMethods(new ScoringMethod[]{new ScoringMethod("Touchdown", 6), new ScoringMethod("Field Goal", 3), new ScoringMethod("Extra Point", 1), new ScoringMethod("Two-point Conversion", 2), new ScoringMethod("Safety", 2)});
    }

    /**
     * Overrides the isGameOver() method from Game and ends the game if the current period is 5
     * @return  true if the game is over, false otherwise
     */
    @Override
    public boolean isGameOver() {
        return getPeriod() == 5;
    }

    /**
     * Returns the period name
     * @return  the name of the period
     */
    @Override
    String getPeriodName() {
        switch(getPeriod()){
            case 1:
                return "First Quarter";
            case 2:
                return "Second Quarter";
            case 3:
                return "Third Quarter";
            case 4:
                return "Fourth Quarter";
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
        return "Quarter";
    }
}

