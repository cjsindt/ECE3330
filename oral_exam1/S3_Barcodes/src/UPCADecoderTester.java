import java.util.Scanner;

public class UPCADecoderTester {

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        System.out.print("Enter barcode to decode: ");

        String input = s.nextLine();

        UPCADecoder d = new UPCADecoder(input);

        System.out.println(d.getProductCode());
    }
}
