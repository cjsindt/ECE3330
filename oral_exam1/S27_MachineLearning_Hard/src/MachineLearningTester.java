import java.util.Arrays;

public class MachineLearningTester {

    public static void main(String[] args){
        MachineLearning m = new MachineLearning();

        double[] vect1 = {1,2,3};
        double[] vect2 = {2,6,3};

        String str1 = "010101";
        String str2 = "010111";

        //TODO: tostring() for vectors (maybe, maybe not)
        System.out.println("Cosine similarity of " + Arrays.toString(vect1) + " and " + Arrays.toString(vect2) + " = " + m.cosineSimilarity(vect1, vect2));
        System.out.println("Hamming Distance between " + str1 + " and " + str2 + " = " + m.hammingDistance(str1, str2));
        System.out.println("Euclidean Distance between " + Arrays.toString(vect1) + " and " + Arrays.toString(vect2) + " = " + m.euclideanDistance(vect1, vect2));
    }
}
