public class PostnetDecoder {

    private String zipCode = "";
    private String binaryEncoding = "";

    public PostnetDecoder(){
        binaryEncoding = "";
    }

    public PostnetDecoder(String bin){
        setBinaryEncoding(binaryEncoding);
    }

    public void setBinaryEncoding(String bin){
        binaryEncoding = bin;
    }

    public String getZipCode(){
        return zipCode;
    }

    public String getBinaryEncoding(){
        return binaryEncoding;
    }
}
