public class KNearestNeighborsTester {

    public static void main(String[] args){
        MachineLearning m = new MachineLearning();
        double[] point0 = {1.5,3.5,2,2,8};
        double[] point1 = {3,3,2,2,1};
        System.out.println(m.kNearestNeighbors("/iahome/c/cj/cjsindt/Desktop/cjsindt_swd/oral_exam1/S27_MachineLearning_Hard/S27-MLMedium.csv", point0, 5));
    }


}
