public class Hockey extends Game{

    public Hockey(Team home, Team away){
        super(home, away);
        setScoringMethods(new ScoringMethod[]{new ScoringMethod()});
    }
}
