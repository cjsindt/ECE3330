import java.util.Scanner;

public class UPCAEncoderTester {

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        System.out.print("Enter an 11 digit product code: ");

        String input = s.nextLine();

        UPCAEncoder f = new UPCAEncoder(input);

        if(UPCAEncoder.isValidCode(input)) {
            System.out.println(f.getUpca());
        }
    }
}
