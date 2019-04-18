/**
 * Name         :
 * Matric. No   :
 * PLab Acct.   :
 */

import java.util.*;

public class Sequences {
    // Variable declaration
    private int noOfTerms, type;
    private long term, multiplier;
    private Scanner input;

    private void run() {
        // Getting input
        input = new Scanner(System.in);
        noOfTerms = input.nextInt();
        type = input.nextInt();
        term = input.nextLong();
        multiplier = input.nextLong();
        System.out.printf("%d", term);

        // Incrementation and printing
        switch(type){
            case 1:
                for(int i = 1; i < noOfTerms; i++)
                    System.out.printf(" %d", term += multiplier);
                break;
            case 2: 
                for(int i = 1; i < noOfTerms; i++)
                    System.out.printf(" %d", term *= multiplier);
                break;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Sequences newSequences = new Sequences();
        newSequences.run();
    }
}
