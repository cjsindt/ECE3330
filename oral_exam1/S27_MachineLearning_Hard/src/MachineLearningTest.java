import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MachineLearningTest {

    @Test
    void cosineSimilarity() {
        MachineLearning m = new MachineLearning();
        double[] v0 = {1,2,3};
        double[] v1 = {2,6,3};
        double[] v2 = {1,5,9,7};
        double[] v3 = {};

        assertEquals(0.8781, Math.floor(m.cosineSimilarity(v0, v1) * 10000) / 10000); //want to round to 4 decimal places
        assertEquals(2, m.cosineSimilarity(v1,v2)); //different lengths
        assertEquals(2, m.cosineSimilarity(v3, v1)); //one with length 0, one not
        assertEquals(2, m.cosineSimilarity(v3, v3)); //both length 0
        assertEquals(1, m.cosineSimilarity(v2, v2)); //same vectors
    }

    @Test
    void hammingDistance() {
        MachineLearning m = new MachineLearning();
        String s0 = "10101";
        String s1 = "10111";
        String s2 = "01010";
        String s3 = "";
        String s4 = "0101010";

        assertEquals(0, m.hammingDistance(s0, s0)); //same string
        assertEquals(1, m.hammingDistance(s0, s1)); //1 different character
        assertEquals(5, m.hammingDistance(s0, s2)); //all characters different
        assertEquals(-1, m.hammingDistance(s1, s4)); //different length
        assertEquals(-1, m.hammingDistance(s3, s1)); //one empty
    }

    @Test
    void euclideanDistance() {
        MachineLearning m = new MachineLearning();
        double[] v0 = {1,2,3};
        double[] v1 = {2,6,3};
        double[] v2 = {1,5,9,7};
        double[] v3 = {};

        assertEquals(4.1231, Math.floor(m.euclideanDistance(v0, v1) * 10000) / 10000); //want to round to 4 decimal places
        assertEquals(-1, m.euclideanDistance(v1,v2)); //different lengths
        assertEquals(-1, m.euclideanDistance(v3, v1)); //one with length 0, one not
        assertEquals(-1, m.euclideanDistance(v3, v3)); //both length 0
        assertEquals(0, m.euclideanDistance(v2, v2)); //same vectors

    }

}