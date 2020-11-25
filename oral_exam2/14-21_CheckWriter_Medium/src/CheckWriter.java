/**
 * Class that takes dollar amounts and converts it to words or to a check protection format
 * @author cjsindt
 */
public class CheckWriter {

    /**
     * Conversion table from integer to it's ones/teens representation in english
     */
    private static final String[] ones = {"", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN"};

    /**
     * Conversion table from integer to it's tens representation in english
     */
    private static final String[] tens = {"", "TEN", "TWENTY", "THIRTY", "FORTY", "FIFTY", "SIXTY", "SEVENTY", "EIGHTY", "NINETY"};

    /**
     * Converts a given dollar amount less than 1000 to it's representation in words
     * @param dollar    dollar amount to convert
     * @return  the words representation of the dollar amount
     */
    public static String dollarToText(double dollar){
        if(dollar > 1000){
            return "";
        } else {
            dollar = Math.round(dollar * 100.0) / 100.0; //round number to nearest hundredth
            int decimal = (int)(dollar*100)%100; //decimal of the dollar
            StringBuilder result = new StringBuilder(); //going to append a lot, so use a StringBuilder

            //check the hundredths place if needed
            if(dollar > 100){
                result.append(ones[(int) dollar / 100]).append(" hundred ");
                dollar = dollar%100;
            }
            //differentiate between a number < 20 or > 20
            if(dollar > 19){
                result.append(tens[(int) dollar / 10]).append(ones[(int) dollar % 10]);
            } else {
                result.append(ones[(int)Math.round(dollar)]);
            }
            //add the decimals
            result.append(" and ").append(decimal).append("/100");

            return result.toString();
        }
    }

    /**
     * Takes a dollar amount and converts it to a check protection form: 9 spots long with asterisks where there aren't any numbers
     * @param dollar    dollar amount to convert
     * @return  check protection form of dollar amount
     */
    public static String checkProtection(double dollar){
        if(dollar > 99999){
            return "";
        } else {
            dollar = Math.round(dollar * 100) / 100.0; //round number to nearest hundredth
            int decimal = (int)(dollar*100)%100; //decimal of the dollar
            StringBuilder result = new StringBuilder();

            //if greater than 1000 need a ,
            if(dollar > 1000){
                result.append((int)(dollar/1000)).append(',');
                dollar = dollar%1000;
            }
            //append the rest of the number with a . between the decimal and integer numbers
            result.append((int)dollar).append('.').append(decimal);
            //fill the void with * until result is 9 long
            for(int i = result.length(); i < 9; i++){
                result.insert(0,'*');
            }
            return result.toString();
        }
    }
}
