public class MachineLearningTester {

    public static void main(String[] args){
        MachineLearning m = new MachineLearning();

        double[] vect1 = {1,2,3};

        double[] vect2 = {2,6,3};

        //TODO: tostring() for vectors (maybe, maybe not)
        System.out.print("Cosine similarity of " + vect1.toString() + " and " + vect2.toString() + " = " + m.cosineSimilarity(vect1, vect2));
    }
}
