/**
 * Class to encode a ZIP, ZIP+4, or ZIP+4+Delivery Code into the POSTNET barcode standard.
 * @author cjsindt
 * @version 1.0.0, 8 Sept 2020
 */
public class PostnetEncoder {

    private String zipCode = ""; //the zipcode given by the user
    private String binaryEncoding = "1"; //the binary encoding of the zipcode, always starts with 1
    private String barEncoding = "|"; //the bar encoding of the zipcode, always starts with |

    //conversion chart from decimal to binary based on index
    final private static String[] binaryEncodingChart = {"11000", "00011", "00101", "00110", "01001", "01010", "01100", "10001", "10010", "10100"};

    //conversion chart from decimal to bars based on index
    final private static String[] barEncodingChart = {"||...", "...||", "..|.|", "..||.", ".|..|", ".|.|.", ".||..", "|...|", "|..|.", "|.|.."};

    /**
     * Default Constructor with no parameters
     */
    public PostnetEncoder(){
        zipCode = "";
    }

    /**
     * Constructor that takes a Zip Code as a parameter with hyphens separating the ZIP from the +4 and Delivery code.
     * @param zip   The Zip Code (ZIP, ZIP+4, ZIP+4+Delivery Code)
     */
    public PostnetEncoder(String zip){
        setZipCode(zip);
    }

    /**
     * Sets the Zip code to zip and encodes it into its Binary and POSTNET representations.
     * @param zip   The Zip Code (ZIP, ZIP+4, ZIP+4+Delivery Code)
     */
    public void setZipCode(String zip){
        if(isValidZip(zip)) {
            zipCode = zip;
            encodeZipCode(zip);
        } else {
            System.out.println("Invalid Zip Code.");
        }
    }

    /**
     *
     * @return
     */
    public String getZipCode(){
        return zipCode;
    }

    public String getBinaryEncoding(){
        return binaryEncoding;
    }

    public String getBarEncoding(){
        return barEncoding;
    }

    public static boolean isValidZip(String zip){

        //null check
        if(zip != null) {
            //zip cannot be null, and the only valid lengths for a zip code are 5, 10, and 12. Don't test any input that has incorrect length
            if (zip.length() == 5 || zip.length() == 10 || zip.length() == 13) {
                //check every character in given string and make sure they are all numeric digits
                for (int i = 0; i < zip.length(); i++) {
                    //if the 6th  or 11th digit is not a hyphen, not a valid zip
                    if (i == 5 || i == 10) {
                        //great, we have a hyphen, add one to i and keep checking numbers
                        if (zip.charAt(i) == '-') {
                            i += 1;
                            //no hyphen = invalid zip
                        } else {
                            return false;
                        }
                    }
                    //if the character at the current index is not a digit, the zip is invalid
                    //can check every time b/c if we have a hyphen, we add one to i
                    if (!(Character.isDigit(zip.charAt(i)))) {
                        return false;
                    }
                }
            }
            //zip code is an invalid length, return false
            else {
                return false;
            }

            //zip code is null, return false
        } else{
            return false;
        }

        return true;
    }


    private void encodeZipCode(String zip){

            //the checksum of the encoding
            int checkSum = 0;

            for(int i = 0; i < zip.length(); i++){
                //if there is a hyphen, advance the index to next number
                if(zip.charAt(i) == '-'){
                    i+=1;
                }
                checkSum += (zip.charAt(i))-48; //add to checkSum the char value offset by 48 (0 in ASCII is 48)

                //convert the char to a number between 0-9 and use that as the index for the encoding chart
                binaryEncoding += binaryEncodingChart[zip.charAt(i)-48];
                barEncoding += barEncodingChart[zip.charAt(i)-48];
            }

            //checkSum value equals 10 - (the sum of the digits modulo 10)
            checkSum = 10 - (checkSum % 10);

            //add the checkSum encoding to the end, plus an extra 1 or |
            binaryEncoding += binaryEncodingChart[checkSum] + "1";
            barEncoding += barEncodingChart[checkSum] + "|";

    }
}
