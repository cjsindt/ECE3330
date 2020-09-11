/**
 * Class to encode a ZIP, ZIP+4, or ZIP+4+Delivery Code into the POSTNET barcode standard.
 * @author cjsindt
 * @version 1.0.0, 8 Sept 2020
 */
public class PostnetEncoder {

    private String zipCode; //the zipcode given by the user
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
     * Constructor that takes a Zip Code as a parameter with hyphens separating the ZIP from the +4 and Delivery code and encodes it into a POSTNET bar code
     * @param zip   The Zip Code (ZIP, ZIP+4, ZIP+4+Delivery Code)
     */
    public PostnetEncoder(String zip){
        if(isValidZip(zip)){
            zipCode = zip;
            encodeZipCode(zipCode);
        } else {
            System.out.println("Invalid zip code, setting zip code to \"\".");
            zipCode = "";
        }
    }

    /**
     * Sets the Zip code to zip if given a valid zip code
     * @param zip   The Zip Code (ZIP, ZIP+4, ZIP+4+Delivery Code)
     */
    public void setZipCode(String zip){
        if(isValidZip(zip)) {
            zipCode = zip;
        } else {
            System.out.println("Invalid Zip Code.");
        }
    }

    /**
     * Returns the Zip Code.
     * @return  The Zip Code
     */
    public String getZipCode(){
        return zipCode;
    }

    /**
     * Returns the binary encoding of the Zip Code
     * @return  binary encoding of Zip Code
     */
    public String getBinaryEncoding(){
        return binaryEncoding;
    }

    /**
     * Returns the POSTNET barcode of the Zip Code
     * @return  The POSTNET barcode of the Zip Code
     */
    public String getBarEncoding(){
        return barEncoding;
    }

    /**
     * Checks if zip is a valid zip code format. Either ZIP, ZIP+4, or ZIP+4+Delivery Code
     * i.e. 55555 / 55555-1234 / 55555-1234-99
     * @param zip   The Zip Code
     * @return  true if the zip code is valid, false otherwise
     */
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
                            System.out.println("Separate the Zip from the +4 by a hyphen, same for delivery code.");
                            return false;
                        }
                    }
                    //if the character at the current index is not a digit, the zip is invalid
                    //can check every time b/c if we have a hyphen, we add one to i
                    if (!(Character.isDigit(zip.charAt(i)))) {
                        System.out.println("Invalid character in Zip Code.");
                        return false;
                    }
                }
            }
            //zip code is an invalid length, return false
            else {
                System.out.println("Invalid Length.");
                return false;
            }

            //zip code is null, return false
        } else{
            System.out.println("Input is null.");
            return false;
        }

        return true;
    }

    /**
     * Encodes the ZipCode into it's Binary and POSTNET representations
     * @param zip   the zip code
     */
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

            //checkSum value equals 10 - (the sum of the digits modulo 10) unless it is already 0
            if(checkSum % 10 != 0) {
                checkSum = 10 - (checkSum % 10);
            } else{
                checkSum = 0;
            }

            //add the checkSum encoding to the end, plus an extra 1 or |
            binaryEncoding += binaryEncodingChart[checkSum] + "1";
            barEncoding += barEncodingChart[checkSum] + "|";

    }
}
