/**
 * Name         : Yuan Xinran, Stanley
 * Matric. No   : A0182555Y
 * PLab Acct.   :
 */

import java.util.*;
import java.awt.*;

public class Boxes {
    private int noOfOp;
    private HashMap<Point, Integer> box;
    private HashMap<Integer, Integer> row;
    private HashMap<Integer, Integer> col;

    private void run() {
        Scanner sc = new Scanner(System.in);
        noOfOp = sc.nextInt();
        sc.nextInt();
        sc.nextInt();

        box = new HashMap<>();
        row = new HashMap<>();
        col = new HashMap<>();

        for (int i = 0; i < noOfOp; i++){
            final String command = sc.next();
            switch (command){
                case "SIT":
                    int r = sc.nextInt();
                int c = sc.nextInt();
                box.put(new Point(r, c), 1);
                row.put(r, 1);
                col.put(c, 1);
                break;
                case "BOX":
                    Point p = new Point(sc.nextInt(), sc.nextInt());
                if (box.containsKey(p)){
                    System.out.println("Y");
                } else {
                    System.out.println("N");
                }
                break;
                case "ROW":
                    if (row.containsKey(sc.nextInt())){
                        System.out.println("Y");
                    } else {
                        System.out.println("N");
                    }
                break;
                case "COL":
                    if (col.containsKey(sc.nextInt())){
                        System.out.println("Y");
                    } else {
                        System.out.println("N");
                    }
                break;
            }
        }
    }

    public static void main(String[] args) {
        Boxes newBoxes = new Boxes();
        newBoxes.run();
    }
}
