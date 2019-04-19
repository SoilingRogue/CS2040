/**
 * Name         : Yuan Xinran, Stanley
 * Matric. No   : A0182555Y
 * PLab Acct.   :
 */

import java.util.*;

public class Generate {
    private ArrayList<String> permArr = new ArrayList<String>();
    private TreeSet<String> subArr = new TreeSet<String>();

    private void run() {
        Scanner sc = new Scanner(System.in);
        final String word = sc.nextLine();
        permutate("", word);
        Collections.sort(permArr);
        subseq(word);
        printAll();
    }

    private void permutate(String curr, String toPerm){
        int size = toPerm.length();
        if (size == 0) {
            permArr.add(curr);
        } else {
            for (int i = 0; i < size; i++){
                char c = toPerm.charAt(i);
                String s = toPerm.substring(0, i).concat(toPerm.substring(i + 1, size));
                permutate(curr + c, s);    
            }
        }
    }

    private void subseq(String word){
        final int size = word.length();
        if (size == 0) {
        } else {
            subArr.add(word);
            for (int i = 0; i < size; i++){
                String s = word.substring(0, i).concat(word.substring(i + 1, size));
                subseq(s);
            }
        }
    }

    private void printAll(){
        for (String s: permArr){
            System.out.println(s);
        }
        for (String s: subArr){
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        Generate newGenerate = new Generate();
        newGenerate.run();
    }
}
