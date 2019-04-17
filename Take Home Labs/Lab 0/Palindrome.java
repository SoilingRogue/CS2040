/*
 * author		: Yuan Xinran, Stanley
 * matric no.	: A0182555Y
 */

import java.util.*;

public class Palindrome {
    public static boolean isPalindrome(String word) {
        int length = word.length();
        for (int i = 0; i < length / 2; i++){
            if(word.charAt(i) != word.charAt(length - 1 - i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean tof;
        Scanner input;
        String firstWord, secondWord, combinedWord;

        input = new Scanner(System.in);
        firstWord = input.nextLine();
        secondWord = input.nextLine();
        combinedWord = firstWord.concat(secondWord);

        tof = isPalindrome(combinedWord);

        if(tof){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }
}
