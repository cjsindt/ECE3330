import java.util.Scanner;

public class PostnetDecoderTester {

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        System.out.print("Enter a binary encoded Zip Code: ");

        String input = s.nextLine();

        PostnetDecoder p = new PostnetDecoder(input);

        System.out.println(p.getZipCode());
    }
}
