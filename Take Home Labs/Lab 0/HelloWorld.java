/**
 *
 * author	: Yuan Xinran, Stanley
 * matric no: A0182555Y
 * 
 */

import java.util.*;

public class HelloWorld {
    public static void main(String[] args) {
        // Taking inputs
        Scanner input = new Scanner(System.in);
        int parsedInt = input.nextInt();

        // Looping through different cases 
        switch(parsedInt){
            case 1:
                final int numOfInput = input.nextInt();
                for(int i = 0; i < numOfInput; i++){
                    String logicOp = input.next();
                    int bit1 = input.nextInt();
                    int bit2 = input.nextInt();
                    HelloWorld.truthCheck(logicOp, bit1, bit2);
                }
                break;      
            case 2:
                while(input.hasNext()){
                    String logicOp = input.next();
                    if(!logicOp.equals("0")){
                        int bit1 = input.nextInt();
                        int bit2 = input.nextInt();
                        HelloWorld.truthCheck(logicOp, bit1, bit2);
                    } else {
                        break;
                    }
                }
                break;
            case 3:
                while(input.hasNext()){
                    String logicOp = input.next();
                    int bit1 = input.nextInt();
                    int bit2 = input.nextInt();
                    HelloWorld.truthCheck(logicOp, bit1, bit2);
                }
                break;
        }
    }

    public static void truthCheck(String tof, int firstBit, int secondBit){
        final int truthVal = firstBit + secondBit;
        if(tof.equals("AND")){
            System.out.println(truthVal == 2? 1 : 0);
        }
        else if(tof.equals("OR")){
            System.out.println(truthVal > 0 ? 1 : 0);
        }
    }
}
