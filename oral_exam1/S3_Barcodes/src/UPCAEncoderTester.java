public class UPCAEncoderTester {

    public static void main(String[] args){
        UPCAEncoder f = new UPCAEncoder("01254667375");
        System.out.println("10100011010011001001001101100010100011010111101010101000010001001000010100010010011101011100101");
        System.out.println(f.getUpca());
    }
}
