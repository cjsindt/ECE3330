public class Football extends Game{

    public Football(Team home, Team away){
        super(home, away);
        setScoringMethods(new ScoringMethod[]{new ScoringMethod("Goal", 1)});
    }

    @Override
    public boolean isGameOver(){
        return getPeriod() == 3;
    }

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

    @Override
    String getPeriodLength() {
        return "Half";
    }
}
