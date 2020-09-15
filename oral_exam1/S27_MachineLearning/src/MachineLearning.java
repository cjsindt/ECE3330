import java.lang.Math;

public class MachineLearning {

    /**
     * Calculates the cosine similarity of 2 vectors. Found by taking the dot product (inner product) over the product
     * of the magnitudes (norms) of the vectors.
     * @param vector1   first vector represented as an array of doubles
     * @param vector2   second vector represented as an array of doubles
     * @return  the cosine similarity (value between 0 and 1) if the cosine similarity can be calculated, 2 if otherwise
     */
    public double cosineSimilarity(double[] vector1, double[] vector2){
        if(magnitude(vector1) != 0 && magnitude(vector2) != 0) {
            return dotProduct(vector1, vector2) / (magnitude(vector1) * magnitude(vector2));
        } else {
            System.out.println("Divide by 0 error.");
            return 2; //because 0 is a valid value for cosine similarity, decided to return 2
        }
    }

    /**
     * Calculates the dot product (inner product) of two given vectors given as 2 arrays of doubles
     * @param vector1   an array of doubles for the 1st vector
     * @param vector2   an array of doubles for the 2nd vector
     * @return  the dot product (inner product) of the two vectors
     * @throws ArithmeticException  throws arithmetic exception if vectors do not match in length, either of their lengths are 0, or either of them are null
     */
    private double dotProduct(double[] vector1, double[] vector2){
        double result = 0;
        //if given a null value or array of length 0, print error message
        if (vector1 == null || vector2 == null || vector1.length == 0 || vector2.length == 0){
            System.out.println("Vectors must have at least 1 value in them.");
            return 0;
        }
        //if the vector lengths do not match, print error message
        else if(vector1.length != vector2.length){
            System.out.println("Vector lengths do not equal");
            return 0;
        } else{
            //sum the products of each term in each vector
            for(int i = 0; i < vector1.length; i++){
                result += (vector1[i] * vector2[i]);
            }
        }
        return result;
    }

    /**
     * Calculates the Magnitude (Norm) of the given vector given as an array of doubles
     * @param vector    an array of doubles representing a vector
     * @return  the magnitude (norm) of the vector
     */
    private double magnitude(double[] vector){
        double result = 0;
        //if given a null value or array of length 0, print error message
        if (vector == null || vector.length == 0){
            System.out.println("Vectors must have at least 1 value in them.");
            return 0;
        } else {
            //sum the squares of each term in the vector
            for(double i : vector){
                result += (i * i);
            }
        }
        //return the square root of the sum of squares (distance formula)
        return Math.sqrt(result);
    }
}
