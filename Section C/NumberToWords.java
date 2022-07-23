
import java.text.DecimalFormat;
import java.util.*;

/**
 *
 * @author Kaelo Mokalake
 */
public class NumberToWords {

    /**
     * @param args the command line arguments
     */
    
    // array for tens numbers     
    static final String[] tens = {"", " Ten", " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety"};
    
    // array for units & two digits numbers   
    static final String[] oneTo19 = {"", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine", " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen", " Nineteen"};

    // a method that converts a number to words (up to 1000)  
    private static String convert_to_Thousand(int number) {
        
        // variable declaration
        String temp;
        
        if (number % 100 < 20) {
            temp = oneTo19[number % 100];
            number = number / 100;
        } else {
            temp = oneTo19[number % 10];
            number = number / 10;
            temp = tens[number % 10] + temp;
            number = number / 10;
        }
        if (number == 0) {
            return temp;
        }
        return oneTo19[number] + " Hundred " + temp;
    }
    
    // a method that converts numbers to words    
    public static String numbersToWords(long number) {
        
        // evaluate whether the number is zero or not  
        if (number == 0) {
            return "zero";
        }
        
        // using the toString() to return a String object that represents the long  
        String num = Long.toString(number);

        // masking with 0   
        String pattern = "000000000000";

        // DecimalFormat using the pattern variable  
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        //format a number of the DecimalFormat instance  
        num = decimalFormat.format(number);

        // returning a substring of a given string and converting it to an integer ( to get a billion)
        int billion = Integer.parseInt(num.substring(0, 3));

        // returning a substring of a given string and converting it to an integer ( to get a million)
        int million = Integer.parseInt(num.substring(3, 6));
        
        // returning a substring of a given string and converting it to an integer ( to get hundred thousand)
        int hundredThousand = Integer.parseInt(num.substring(6, 9));

        // returning a substring of a given string and converting it to an integer ( to get thousand)
        int thousand = Integer.parseInt(num.substring(9, 12));
        
        // Variable declaration
        String tradBillions;
        
        tradBillions = switch (billion) {
            case 0 -> "";
            case 1 -> convert_to_Thousand(billion) + " Billion ";
            default -> convert_to_Thousand(billion) + " Billion ";
        };
        
        // Variable declaration
        String result = tradBillions;
        String tradMillions;
        
        tradMillions = switch (million) {
            case 0 -> "";
            case 1 -> convert_to_Thousand(million) + " Million ";
            default -> convert_to_Thousand(million) + " Million ";
        };
        
        // Variable declaration
        String tradHundredThousands;
        result = result + tradMillions;
        
        tradHundredThousands = switch (hundredThousand) {
            case 0 -> "";
            case 1 -> "One Thousand ";
            default -> convert_to_Thousand(hundredThousand) + " Thousand ";
        };
        // variable declaration
        String tradThousand;
        
        result = result + tradHundredThousands;
        tradThousand = convert_to_Thousand(thousand);
        result = result + tradThousand;
        
        return result;
    }

    public static void main(String args[]) {
        
        Scanner input = new Scanner(System.in);
        
        // variable declaration
        String strNumber;
        
        // getting user input
        System.out.print("Enter your number: ");
        strNumber = input.nextLine();
        
        // evaluating whether the length of the given value is <= 9
        // if so displaying the words representation of the given integer
        if (strNumber.length() <= 9) {
            System.out.println("\n" + numbersToWords(Integer.parseInt(strNumber)));
        
        // evaluating whether the length of the given value is > 9
        // if so displaying the words representation of the given Long
        }else if (strNumber.length() > 9 && strNumber.length() <= 15){
            System.out.println("\n" + numbersToWords(Long.parseLong(strNumber)));
        
        }else{
            System.out.println("\nNumber entered is out of program's range.");
        }
    }
}
