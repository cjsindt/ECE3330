/**
 * This class takes a UPC-A format barcode in binary and decodes it into its respective 11 digit product code
 * @author cjsindt
 * @version 1.0.0 10 SEPT 2020
 */
public class UPCADecoder {

    /**
     * String representation of the UPC-A barcode to decode
     */
    private String upca;

    /**
     * The product code resulting from the decoded UPC-A barcode
     */
    private String productCode = ""; //the product code resulting from the upca code

    /**
     * Array of strings that maps the decimal value (index) to its corresponding binary value for the first half of the bar code
     */
    final private static String[] firstHalfEncodings = {"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};

    /**
     * Array of strings that maps the decimal value (index) to its corresponding binary value for the second half of the bar code
     */
    final private static String[] secondHalfEncodings ={"1110010", "1100110", "1101100", "1000010", "1011100", "1001110", "1010000", "1000100", "1001000", "1110100"};

    /**
     * No-Argument constructor
     */
    public UPCADecoder(){
        upca = "";
    }

    /**
     * Constructor that takes a code from the user and decodes it if it is valid
     * @param code  a UPC-A code
     */
    public UPCADecoder(String code){
        if(isValidCode(code)){
            upca = code;
            decodeUPCA(upca);
        } else {
            System.out.println("Invalid UPC-A code, setting code to \"\".");
            upca = "";
        }
    }

    /**
     * Sets the upca code to input
     * @param code  a UPC-A code
     */
    public void setUPCA(String code){
        if(isValidCode(code)){
            upca = code;
        }
    }

    /**
     * Returns the 11 digit product code
     * @return  product code
     */
    public String getProductCode(){
        return productCode;
    }

    /**
     * Evaluates whether a given code is valid or not
     * @param code  a UPC-A code
     * @return  true if code is valid, false otherwise
     */
    public static boolean isValidCode(String code){

        //calculate the checksum
        int checkSum = 0;

        //null and length check
        if(code != null && code.length() == 95){
            //make sure code begins and ends with 101 and have a middle section
            if(code.substring(0, 3).equals("101") && code.substring(92,95).equals("101") && code.substring(45, 50).equals("01010")){
                //loop through 7 bit sections of the code until the middle section
                for(int i = 3; i < 45; i+=7){
                    //loop through the amount of different bit combinations
                    for(int j = 0; j < 10; j++){
                        //if we find a valid set of bits, move onto the next set
                        if(code.substring(i, i+7).equals(firstHalfEncodings[j])){
                            //if even row, add only the digit. If odd, add 3 times the digit
                            if(i % 2 == 0){
                                checkSum += j;
                            } else {
                                checkSum += 3 * j;
                            }
                            j = 10;
                        }
                        //if we reach the end of the loop with no match, section is invalid
                        else if(j == 9){
                            System.out.println("Invalid encoding of bits.");
                            return false;
                        }
                    }
                }
                //same as loop above, but using the second half encodings and only go to check sum
                for(int i = 50; i < 85; i+=7){
                    //loop through the amount of different bit combinations
                    for(int j = 0; j < 10; j++){
                        //if we find a valid set of bits, move onto the next set
                        if(code.substring(i, i+7).equals(secondHalfEncodings[j])){
                            //if even row, add only the digit. If odd, add 3 times the digit
                            if(i % 2 == 0){
                                checkSum += 3 * j;
                            } else {
                                checkSum += j;
                            }
                            j = 10;
                        }
                        //if we reach the end of the loop with no match, section is invalid
                        else if(j == 9){
                            System.out.println("Invalid encoding of bits.");
                            return false;
                        }
                    }
                }

                //calculate the checksum
                checkSum = checkSum % 10;
                if(checkSum != 0){
                    checkSum = 10 - checkSum;
                }

                //validate the checksum
                for(int i = 0; i < 10; i++){
                    //if there is a matching set of bits and those bits equal the check sum, valid check sum
                    if(code.substring(85, 92).equals(secondHalfEncodings[i]) && checkSum == i){
                        System.out.println("Checksum - " + i + ", ok");
                        i = 10;
                    } else if(i == 9){
                        System.out.println("Invalid Checksum");
                        return false;
                    }
                }
            }
            else {
                System.out.println("Code must begin and end with 101 and contain middle bars of 01010");
                return false;
            }
        }
        //code is null or 0 length
        else {
            System.out.println("Code is null or incorrect length.");
            return false;
        }

        return true;
    }

    /**
     * Decodes a UPC-A code into an 11 digit product code
     * @param code  UPC-A code to decode
     */
    private void decodeUPCA(String code){

        for(int i = 3; i < 45; i+=7){
            //loop through the amount of different bit combinations
            for(int j = 0; j < 10; j++){
                //if we find a valid set of bits, move onto the next set
                if(code.substring(i, i+7).equals(firstHalfEncodings[j])){
                    productCode += j;
                    j = 10;
                }
                //if we reach the end of the loop with no match, section is invalid
                else if(j == 9){
                    System.out.println("Invalid encoding of bits.");
                }
            }
        }
        //same as loop above, but using the second half encodings and only go to check sum
        for(int i = 50; i < 85; i+=7){
            //loop through the amount of different bit combinations
            for(int j = 0; j < 10; j++){
                //if we find a valid set of bits, move onto the next set
                if(code.substring(i, i+7).equals(secondHalfEncodings[j])){
                    productCode += j;
                    j = 10;
                }
                //if we reach the end of the loop with no match, section is invalid
                else if(j == 9){
                    System.out.println("Invalid encoding of bits.");
                }
            }
        }
    }

}
