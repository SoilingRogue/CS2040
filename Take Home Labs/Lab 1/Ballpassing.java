/**
 * Name         : Yuan Xinran, Stanley 
 * Matric. No   : A0182555Y
 * PLab Acct.   :
 */

import java.util.*;

public class Ballpassing {
    // Variable Declarations
    Scanner input;
    String command;
    int noOfStudents, noOfCommands;
    LinkedList<String> studentList = new LinkedList<String>();

    private void run() {
        input = new Scanner(System.in);
        noOfStudents = input.nextInt();
        // Adding students to the list
        for(int i = 0; i < noOfStudents; i++){
            studentList.add(input.next());
            //System.out.println(studentList.get(i));
        }

        noOfCommands = input.nextInt();
        input.nextLine();

        // Test for errors
        // System.out.println(noOfCommands);
        for(int i = 0; i < noOfCommands; i++){
            command = input.nextLine();
            // Test for errors
            /* for(int j = 0; j < noOfStudents; j++){
               System.out.println(studentList.get(j));
               }            System.out.println(command);
             */
            // Loop for commands
            if(command.equals("NEXT")){
                studentList.add(studentList.remove());
                System.out.println(studentList.peekFirst());
            }else if (command.equals("LEAVE")){
                studentList.remove();
                System.out.println(studentList.peekFirst());
            }else if(command.contains("JOIN")){
                String name = command.substring(5);
                studentList.add(studentList.remove());
                studentList.addFirst(name);
                System.out.println(name);
            }

            // Alternative loop - same results
            /*  switch(command){
                case "NEXT":
                studentList.add(studentList.remove());
                System.out.println(studentList.peekFirst());
                break;
                case "LEAVE":
                studentList.remove();
                System.out.println(studentList.peekFirst());
                break;
                default: 
                String name = command.substring(5);
                studentList.add(studentList.remove());
                studentList.addFirst(name);
                System.out.println(name);
                break;
                }*/
        }
    }
    public static void main(String[] args) {
        Ballpassing newBallpassing = new Ballpassing();
        newBallpassing.run();
    }
}
