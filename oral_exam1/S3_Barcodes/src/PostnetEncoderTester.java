import java.util.Scanner;

public class PostnetEncoderTester {

    public static void main(String[] args){

        Scanner s = new Scanner(System.in);

        System.out.print("Enter Zip: ");

        String input = s.nextLine();

        PostnetEncoder p = new PostnetEncoder(input);

        if(PostnetEncoder.isValidZip(p.getZipCode())) {
            System.out.println(p.getBarEncoding() + "\n" + p.getBinaryEncoding());
        }
    }
}
