/**
 * Name         : Yuan Xinran, Stanley
 * Matric. No   : A0182555Y
 * PLab Acct.   :
 */

import java.util.*;

public class Censorship {
    // Variable declaration
    private Scanner input;
    private int noOfWords;
    private ArrayList<String> badWords = new ArrayList<String>();
    private ArrayList<String> story;

    private void run() {
        // Input handling
        input = new Scanner(System.in);
        noOfWords = input.nextInt();

        // Adding words to list of badWords
        for (int i = 0; i < noOfWords; i++){
            badWords.add(input.next());
        }

        input.nextLine();

        // Adding words to input story
        String temp = input.nextLine();
        story = new ArrayList<String>(Arrays.asList(temp.split(" ")));

        // Checking, "replacing" and printing bad words from story
        for (int i = 0; i < story.size(); i++){
check:{
          for(int j = 0; j < badWords.size(); j++){
              if (story.get(i).equalsIgnoreCase(badWords.get(j))){
                  for (int numStars = 0; numStars < badWords.get(j).length();
                          numStars++){
                      System.out.print("*");
                  }
                  break check;
              }
          }
          System.out.print(story.get(i));
      }
      if (i < story.size() - 1) System.out.print(" ");
        }
        System.out.println();

        // System.out.printf("%s ", story.get(i));
        // }
}
public static void main(String[] args) {
    Censorship newCensorship = new Censorship();
    newCensorship.run();
}
}
