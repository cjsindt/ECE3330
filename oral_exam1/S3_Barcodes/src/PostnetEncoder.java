public class PostnetEncoder {

    private String zipCode;
    private String binaryEncoding;

    public PostnetEncoder(){
        zipCode = "";
    }

    public PostnetEncoder(String zip){
        setZipCode(zip);
    }

    private void setZipCode(String zip){
        boolean validZip = true; //assume the zipcode is a valid code

        //the only valid lengths for a zipcode are 5, 10, and 12. Don't test any input that has incorrect length
        if(zip.length() == 5 || zip.length() == 10 || zip.length() == 12) {
            //check every character in given string and make sure they are all numeric digits
            for(int i = 0; i < zip.length(); i++){
                //if the 6th digit is not a hyphen, not a valid zip
                //TODO: Fix this line, don't work :(
                if(i == 5 && zip.charAt(i) != '-'){
                    validZip = false;
                }
                //if the character at the current index is not a digit, the zip is invalid
                else if(!(Character.isDigit(zip.charAt(i)))){
                    validZip = false;
                }
            }
        }
        else{
            validZip = false;
        }

        if(validZip) {
            zipCode = zip;
            System.out.println("Valid Zip: " + zip);
        }
        else{
            System.out.println("Invalid Zip: " + zip);
        }
    }

    private String getZipCode(){
        return zipCode;
    }
}
