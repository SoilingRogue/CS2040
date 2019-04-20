/*
 * Name       : Yuan Xinran, Stanley 
 * Matric No. : A0182555Y
 * Plab Acct. :
 */
import java.util.*;

public class Brackets {
    private void run() {
        Scanner sc = new Scanner(System.in);
        final int numCases = sc.nextInt();
        for (int i = 0; i < numCases; ++i){
            final int length = sc.nextInt();
            sc.nextLine();
            final String seq = sc.nextLine();
            if (length % 2 == 0 && isValid(seq)){
                System.out.println("Valid");
            } else {
                System.out.println("Invalid");
            }
        }
    }

    public boolean isValid(String seq){
        final char[] arr = seq.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < arr.length; ++i){
            final char c = arr[i];
            switch (c){
                case '}':
                    if (stack.size() == 0){
                        return false;
                    } else if (stack.peek().equals('{')){
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.size() == 0){
                        return false;
                    } else if (stack.peek().equals('[')){
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case ')':
                    if (stack.size() == 0){
                        return false;
                    } else if (stack.peek().equals('(')){
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                default:
                    stack.push(c);
                    break;
            }
        }
        return stack.size() == 0;
    }

    public static void main(String[] args) {
        Brackets newBrackets = new Brackets();
        newBrackets.run();
    }
}
