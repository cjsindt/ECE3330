import java.io.*;
import java.util.*;

/**
 * Class to implement different similarity functions as well as a K-Nearest Neighbor algorithm and K-Means Clustering algorithm
 * @author cjsindt
 * @version 1.0.0 17 SEPT 2020
 */
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

    /**
     * Classifies a given data point based on the provided dataset using K-Nearest Neighbors. Designed to run the included dataset (S27-MLMedium.csv)
     * @param filePath  path to the dataset
     * @param feats features of the new datapoint to classify
     * @param k the k term
     * @return  a string describing what class the point is classified to
     */
    public String kNearestNeighbors(String filePath, double[] feats, int k){

        //input sanitization
        if(feats != null  && feats.length == 5 && k > 0){

            //stores the class value of each point in order of increasing distance
            ArrayList<DataPoint> classes = new ArrayList<DataPoint>();
            //stores features of each data point to compare
            double[] features = new double[5];

            //amount of points in in k that are classified as class1 or class2
            int amtClass1 = 0;
            int amtClass2 = 0;

            try{
                //create a new buffered reader
                BufferedReader b = new BufferedReader(new FileReader(filePath));
                //the current line read by the buffered reader
                String currLine = "";
                //an array to hold the split version of the current line
                String[] splitCurrLine;
                //store the distance between current line and point
                double distance = 0;
                //parse doubles from each value in the split string
                while((currLine = b.readLine()) != null){

                    //split the line by commas
                    splitCurrLine = currLine.split(",");

                    //parse doubles
                    for(int i = 0; i < 5; i++){
                        features[i] = Double.parseDouble(splitCurrLine[i]);
                    }

                    //calculate euclidean distance between points
                    distance = euclideanDistance(features, feats);

                    //if list is empty, add to the beginning
                    if(classes.size() == 0){
                        classes.add(new DataPoint(distance, splitCurrLine[5]));
                    } else {
                        //if there are elements, search for where the new datapoint sits
                        for (int i = 0; i < classes.size() && i != -1; i++) {
                            if (distance < classes.get(i).getDistance()) {
                                classes.add(i, new DataPoint(distance, splitCurrLine[5]));
                                i = -2;
                            }
                            //if reached end of list without adding, add datapoint to end
                            else if(i == classes.size()-1){
                                classes.add(new DataPoint(distance, splitCurrLine[5]));
                                i = -2;
                            }
                        }
                    }
                }
            } catch (Exception e){
                //print exception details
                e.printStackTrace();
            }

            //search through the first k entries (they will be the closest) and decide what to classify
            for (int i = 0; i < k; i++) {
                if (classes.get(i).getClassification().equals("\"class1\"")) {
                    amtClass1++;
                } else {
                    amtClass2++;
                }
            }

            if (amtClass1 >= amtClass2) {
                return "New data point belongs to class1";
            } else {
                return "New data point belongs to class2";
            }

        } else {
            return "Invalid Parameters.";
        }
    }

    /**
     * Classifies data points into k clusters using K-Means Clustering.
     * @param filePath  file path to data set
     * @param k amount of clusters
     */
    public void kMeansClustering(String filePath, int k){
        //null check
        if(filePath != null && k > 0){

            //stores the data points for processing later
            ArrayList<double[]> points = new ArrayList<>();


            //STEP 1: read in data
            try{
                //make a new buffered reader
                BufferedReader b = new BufferedReader(new FileReader(filePath));
                //the current line being read
                String currLine = "";

                //stores the current line split by commas
                String[] currLineSplit;

                //read every line
                while((currLine = b.readLine()) != null){
                    //store the split line
                    currLineSplit = currLine.split(",");
                    //parse doubles from the split line and store it in the list of points
                    points.add(new double[]{Double.parseDouble(currLineSplit[0]), Double.parseDouble(currLineSplit[1])});
                }

            } catch (Exception e){
                e.printStackTrace();
                return;
            }


            //STEP 2: determine centroids

            //int array of indices of the centroids
            List<double[]> centroids = new ArrayList<>();

            int[] prevKeys = new int[k];

            //the key value to store
            int key;

            //generate random numbers until k distinct numbers have been added to centroids
            for(int i = 0; i < k; i++){
                key = (int) (Math.random() * (points.size()));
                while (isIn(prevKeys, key)) {
                    key = (int) (Math.random() * (points.size()));
                }
                prevKeys[i] = key;
                centroids.add(points.get(key));
            }

            //a list of lists of points to represent clusters :)
            List<List<double[]>> clusters = new ArrayList<>();

            //shortest distance calculated thus far for each point
            int shortDistIndex;

            //calculate the new centroid and compare to old centroid
            double[] newCentroid = new double[2];

            //if a centroid changes, we must do this process again until no centroids change
            boolean deltaCentroid = true;

            while(deltaCentroid) {
                //STEP 3: assign each point to a centroid

                //add nested arraylists for each cluster
                for (int i = 0; i < k; i++) {
                    clusters.add(new ArrayList<double[]>());
                }

                //go through each point
                for (double[] p : points) {
                    //assume the closest centroid is the one at index 0
                    shortDistIndex = 0;
                    //loop through the other centroids
                    for (int i = 1; i < k; i++) {
                        //if the distance from the i'th centroid is closer than the one at shortDistIndex, set shortDistIndex to i
                        if (euclideanDistance(centroids.get(i), p) < euclideanDistance(centroids.get(shortDistIndex), p)) {
                            shortDistIndex = i;
                        }
                    }
                    //add the point to the proper cluster
                    clusters.get(shortDistIndex).add(p);
                }


                //STEP 4: find the mean of each cluster

                //assume no centroids change
                deltaCentroid = false;

                //loop through each cluster
                for (int i = 0; i < k; i++) {
                    //loop through each point in each cluster
                    for (int j = 0; j < clusters.get(i).size(); j++) {
                        //sum the data points in each cluster to find the mean
                        newCentroid[0] += clusters.get(i).get(j)[0];
                        newCentroid[1] += clusters.get(i).get(j)[1];
                    }
                    //divide by the amount of points in the cluster to find the mean
                    newCentroid[0] = newCentroid[0] / clusters.get(i).size();
                    newCentroid[1] = newCentroid[1] / clusters.get(i).size();

                    //if we haven't found a different centroid yet, check to see if they differ
                    if (!deltaCentroid) {
                        //if the points differ, we have a changed centroid
                        if (newCentroid[0] != centroids.get(i)[0] && newCentroid[1] != centroids.get(i)[1]) {
                            deltaCentroid = true;
                        }
                    }

                    //System.out.println("\nCentroid " + i);
                    //System.out.println("Old Centroid: " + Arrays.toString(centroids.get(i)));

                    //replace current centroid
                    centroids.set(i, newCentroid.clone());

                    //System.out.println("New Centroid: " + Arrays.toString(newCentroid));

                    newCentroid[0] = 0;
                    newCentroid[1] = 0;


                }

                //if iterating again, clear the clusters
                if(deltaCentroid){
                    clusters.clear();
                }
            }

            for(int i = 0; i < k; i++){
                System.out.println(String.format("Cluster%s: %s data points", i, clusters.get(i).size()));
            }


        } else {
            System.out.println("Please provide valid parameters.");
        }
    }

    /**
     * Checks if the given value i is in the given array nums. For use in kMeansClustering
     * @param nums  the array to check
     * @param i the value to check
     * @return  true if i is in nums, false otherwise
     */
    private boolean isIn(int[] nums, int i){
        for(int x:nums){
            if(i == x){
                return true;
            }
        }
        return false;
    }
}
