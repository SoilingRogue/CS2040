
import java.util.Scanner;

public class StringComparison {

    public static void main(String[] args) {

        String firstString, secondString;
        Scanner input;
        int returnVal;

        input = new Scanner(System.in);
        firstString = input.nextLine();
        secondString = input.nextLine();
        returnVal = firstString.compareToIgnoreCase(secondString);

        if (returnVal < 0) {
            System.out.println("1");
        } else if (returnVal == 0) {
            System.out.println("0");
        } else {
            System.out.println("2");
        }
    }

}
