/**
 * Class to represent datapoints for use in a K-Nearest Neighbors algorithm. Holds data for a distance and a classification.
 * @author cjsindt
 * @version 1.0.0 17 SEPT 2020
 */
public class DataPoint {
    /**
     * The distance between the data point and the point to classify.
     */
    private double distance;

    /**
     * The classification of the data point
     */
    private String classification;

    /**
     * Constructor that sets the distance and classification to given values.
     * @param d the distance between the data point and the point to classify
     * @param c the classification of the data point
     */
    public DataPoint(double d, String c){
        distance = d;
        classification = c;
    }

    /**
     * Returns the distance between the data point and the point to classify.
     * @return  the distance
     */
    public double getDistance(){
        return distance;
    }

    /**
     * Returns the classification of the data point.
     * @return  the classification
     */
    public String getClassification(){
        return classification;
    }
}
