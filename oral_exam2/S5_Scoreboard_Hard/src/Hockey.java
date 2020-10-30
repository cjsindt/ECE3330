public class Hockey extends Game{

    public Hockey(Team home, Team away){
        super(home, away);
        setScoringMethods(new ScoringMethod[]{new ScoringMethod("Goal", 1)});
    }

    @Override
    boolean isGameOver() {
        return false;
    }

    @Override
    String getPeriodName() {
        return null;
    }

    @Override
    String getPeriodLength() {
        return null;
    }
}
