public class UPCAEncoder {

    private String productCode; //product code to encode into UPC-A
    private String upca = "101"; //the binary representation of the code in UPC-A

    //double array of ints, first level array index is decimal number of code, second layer array is the bar lengths associated with that number
    //trying to do something different from POSTNET code
    final private static int[][] barLengths = {{3, 2, 1, 1}, {2, 2, 2, 1}, {2, 1, 2, 2}, {1, 4, 1, 1}, {1, 1, 3, 2}, {1, 2, 3, 1}, {1, 1, 1, 4}, {1, 3, 1, 2}, {1, 2, 1, 3}, {3, 1, 1, 2}};

    public UPCAEncoder(){
        productCode = "";
    }

    public UPCAEncoder(String code){
        setProductCode(code);
    }

    public String getUpca(){
        return upca;
    }

    public void setProductCode(String code){
        if (isValidCode(code)) {
            productCode = code;
            encodeToUPCA(code);
        }
    }

    public static boolean isValidCode(String code){
        //null check and length check
        if(code != null && code.length() == 11){
            //check every character in the code is a digit
            for(int i = 0; i < code.length(); i++){
                if(!Character.isDigit(code.charAt(i))){
                    System.out.println("Code must only consist of numerical digits.");
                    return false;
                }
            }
        } else {
            System.out.println("Code is invalid length or null.");
            return false;
        }

        return true;
    }

    private void encodeToUPCA(String code){
        //store the current digit value at. Makes the code a bit easier to read
        int currDigit = 0;
        //the check sum value
        int checkSum = 0;

        //if true, add white bars (0s); if false, add black bars (1s)
        boolean whiteBars = true;

        //loop through each value in code
        for(int i = 0; i < 12; i++){

            //for the first 11 digits of loop, use the numbers of the code
            if(i < 11) {
                currDigit = code.charAt(i) - 48; //subtract 48 from char value to get true decimal value

                //if the current digit is an odd index (beginning at 1), add 3*currDigit to checkSum
                if(i % 2 == 0) {
                    checkSum += 3 * currDigit; //add the current digit to checksum
                }
                //if the current digit is an even index (beginning at 1), add currDigit to checkSum
                else {
                    checkSum += currDigit;
                }
            }
            //for the last digit, make currDigit the calculated check sum so the loop can add it to upca
            else {
                //checkSum is calculated by taking 10 - (checkSum % 10)
                checkSum = checkSum % 10;
                if(checkSum != 0){
                    checkSum = 10 - checkSum;
                }
                currDigit = checkSum;
            }

            //4 different bars for each number
            for(int j = 0; j < 4; j++){
                //add to the upca encoding the proper amount of bars depending on the length
                for (int k = 0; k < barLengths[currDigit][j]; k++) {
                    if (whiteBars) {
                        upca += "0";
                    } else {
                        upca += "1";
                    }
                }
                //invert bar color after each section
                whiteBars = !whiteBars;
            }
            //add the middle bars and invert bar color for second half of encoding
            if(i == 5){
                upca += "01010";
                whiteBars = !whiteBars;
            }
        }
        upca += "101";
    }

}
