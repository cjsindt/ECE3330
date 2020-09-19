import java.util.Arrays;

public class MachineLearningTester {

    public static void main(String[] args){
        MachineLearning m = new MachineLearning();
        double[] point0 = {1.5,3.5,2,2,8};
        double[] point1 = {3,3,2,2,1};
        double[] vect0 = {1,2,3,4,5};
        double[] vect1 = {6,7,8,9,2};
        String str0 = "01010";
        String str1 = "01011";


        System.out.println("Cosine Similarity of " + Arrays.toString(vect0) + " and " + Arrays.toString(vect1) + " = " + m.cosineSimilarity(vect0, vect1));
        System.out.println("Euclidean distance between " + Arrays.toString(vect0) + " and " + Arrays.toString(vect1) + " = " + m.euclideanDistance(vect0, vect1));
        System.out.println("Hamming distance between " + str0 + " and " + str1 + " = " + m.hammingDistance(str0, str1));

        System.out.println(m.kNearestNeighbors("/iahome/c/cj/cjsindt/Desktop/cjsindt_swd/oral_exam1/S27_MachineLearning_Hard/S27-MLMedium.csv", point0, 5));
        System.out.println(m.kNearestNeighbors("/iahome/c/cj/cjsindt/Desktop/cjsindt_swd/oral_exam1/S27_MachineLearning_Hard/S27-MLMedium.csv", point1, 5));

        m.kMeansClustering("/iahome/c/cj/cjsindt/Desktop/cjsindt_swd/oral_exam1/S27_MachineLearning_Hard/S27-MLHard.csv", 4);
    }


}
