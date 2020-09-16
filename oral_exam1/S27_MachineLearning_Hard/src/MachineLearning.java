
public class MachineLearning {

    /**
     * Calculates the cosine similarity of 2 vectors. Found by taking the dot product (inner product) over the product
     * of the magnitudes (norms) of the vectors.
     * @param vector1   first vector represented as an array of doubles
     * @param vector2   second vector represented as an array of doubles
     * @return  the cosine similarity (value between -1 and 1) if the cosine similarity can be calculated, 2 if otherwise
     */
    public double cosineSimilarity(double[] vector1, double[] vector2){
        if(magnitude(vector1) > 0 && magnitude(vector2) > 0 && dotProduct(vector1, vector2) != Double.MAX_VALUE) {
            return dotProduct(vector1, vector2) / (magnitude(vector1) * magnitude(vector2));
        } else {
            return 2; //because 0 and -1 are valid values for cosine similarity, decided to return 2
        }
    }

    /**
     * Calculates the Hamming Distance between two strings.
     * @param str1  first string to compare
     * @param str2  second string to compare
     * @return  the hamming distance if the lengths are equal, -1 if lengths don't equal
     */
    public int hammingDistance(String str1, String str2){
        if(str1 == null || str2 == null || str1.length() != str2.length()){
            System.out.println("Strings must be of the same length and not be null.");
            return -1;
        } else {
            //hamming distance between the strings
            int distance = 0;
            //loop through indices of each string, comparing the values at each index
            for(int i = 0; i < str1.length(); i++){
                if(str1.charAt(i) != str2.charAt(i)){
                    distance += 1;
                }
            }
            return distance;
        }
    }

    /**
     * Calculates the Euclidean Distance between two vectors of the same dimensionality.
     * @param vect1 the first vector represented as an array of doubles
     * @param vect2 the second vector represented as an array of doubles
     * @return  the euclidean distance between the vectors if they are not null and have the same length, -1 otherwise
     */
    public double euclideanDistance(double[] vect1, double[] vect2){
        if(vect1 == null || vect2 == null || vect1.length == 0 || vect2.length == 0){
            System.out.println("Vectors must have at least one value.");
            return -1;
        } else if(vect1.length != vect2.length){
            System.out.println("Vectors must have the same dimensionality.");
            return -1;
        } else {
            double distance = 0;
            for(int i = 0; i < vect1.length; i++){
                distance += (vect1[i] - vect2[i]) * (vect1[i] - vect2[i]);
            }
            return Math.sqrt(distance);
        }
    }

    /**
     * Calculates the dot product (inner product) of two given vectors given as 2 arrays of doubles
     * @param vector1   an array of doubles for the 1st vector
     * @param vector2   an array of doubles for the 2nd vector
     * @return  the dot product (inner product) of the two vectors if they are the same length and non-zero, Double.MAX_VALUE otherwise
     */
    private double dotProduct(double[] vector1, double[] vector2){
        //if given a null value or array of length 0, print error message
        if (vector1 == null || vector2 == null || vector1.length == 0 || vector2.length == 0){
            System.out.println("Vectors must have at least 1 value in them.");
            return Double.MAX_VALUE;
        }
        //if the vector lengths do not match, print error message
        else if(vector1.length != vector2.length){
            System.out.println("Vector lengths do not equal");
            return Double.MAX_VALUE;
        } else{
            double result = 0;
            //sum the products of each term in each vector
            for(int i = 0; i < vector1.length; i++){
                result += (vector1[i] * vector2[i]);
            }
            return result;
        }
    }

    /**
     * Calculates the Magnitude (Norm) of the given vector given as an array of doubles
     * @param vector    an array of doubles representing a vector
     * @return  the magnitude (norm) of the vector
     */
    private double magnitude(double[] vector){

        //if given a null value or array of length 0, print error message
        if (vector == null || vector.length == 0){
            System.out.println("Vectors must have at least 1 value in them.");
            return -1;
        } else {
            double result = 0;
            //sum the squares of each term in the vector
            for(double i : vector){
                result += (i * i);
            }
            //return the square root of the sum of squares (distance formula)
            return Math.sqrt(result);
        }
    }
}
