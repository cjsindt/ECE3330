public class ScoringMethod {

    private String name;
    private int value;

    public ScoringMethod(String n, int v){
        name = n;
        value = v;
    }

    public int getValue(){
        return value;
    }

    public String getName(){
        return name;
    }
}
