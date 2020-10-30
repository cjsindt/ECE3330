public class Basketball extends Game {

    public Basketball(Team home, Team away){
        super(home, away);
        setScoringMethods(new ScoringMethod[]{new ScoringMethod("3 Pointer", 3), new ScoringMethod("2 Pointer", 2), new ScoringMethod("1 Pointer", 1)});
    }
}
