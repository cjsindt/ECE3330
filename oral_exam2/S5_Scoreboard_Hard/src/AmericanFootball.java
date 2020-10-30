public class AmericanFootball extends Game{

    public AmericanFootball(Team home, Team away){
        super(home, away);
        setScoringMethods(new ScoringMethod[]{new ScoringMethod("Touchdown", 6), new ScoringMethod("Field Goal", 3), new ScoringMethod("Extra Point", 1), new ScoringMethod("Two-point Conversion", 2), new ScoringMethod("Safety", 2)});
    }

    @Override
    public boolean isGameOver() {
        return getPeriod() == 5;
    }

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

    @Override
    String getPeriodLength() {
        return "Quarter";
    }
}

