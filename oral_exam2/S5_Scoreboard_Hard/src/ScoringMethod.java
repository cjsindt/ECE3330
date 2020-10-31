/**
 * This class represents a scoring method for a particular game. Each method has a name and an associated value
 */
public class ScoringMethod {

    /**
     * The name of the scoring method
     */
    private String name;

    /**
     * How many points the method is worth
     */
    private int value;

    /**
     * Constructor that takes the name of the scoring method, as well as the point value
     * @param n the name of the method
     * @param v the point value of the method
     */
    public ScoringMethod(String n, int v){
        name = n;
        value = v;
    }

    /**
     * Returns the point value of the scoring method
     * @return  the point value
     */
    public int getValue(){
        return value;
    }

    /**
     * Returns the name of the scoring method
     * @return  the name
     */
    public String getName(){
        return name;
    }
}
