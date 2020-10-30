public class AmericanFootball extends Game{

    public AmericanFootball(Team home, Team away){
        super(home, away);
        setScoringMethods(new ScoringMethod[]{new ScoringMethod("Touchdown", 6), new ScoringMethod("Field Goal", 3), new ScoringMethod("Extra Point", 1), new ScoringMethod("Two-point Conversion", 2), new ScoringMethod("Safety", 2)});
    }
}

