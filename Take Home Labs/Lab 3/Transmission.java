/*
 * Name       : Yuan Xinran, Stanley
 * Matric No. : A0182555Y
 * Plab Acct. :
 */
import java.util.*;

public class Transmission {
    private void run() {
        final Scanner sc = new Scanner(System.in);
        TreeSet<Integer> sleepingCats = new TreeSet<>();

        sc.nextInt();
        int numOfOp = sc.nextInt();
        for (int i = 0; i < numOfOp; ++i){
            final String command = sc.next();
            switch (command){
                case "WAKE":
                    sleepingCats.remove((Integer) sc.nextInt());
                break;
                case "SLEEP":
                    sleepingCats.add(sc.nextInt());
                break;
                case "TRANSMIT":
                    final int firstIndex = sc.nextInt();
                final int lastIndex = sc.nextInt();
                final Integer floorKey = sleepingCats.floor(lastIndex);
                final Integer ceilKey = sleepingCats.ceiling(firstIndex);
                if (floorKey == null && ceilKey == null){
                    System.out.println("YES");
                } else if (ceilKey == null) {
                    if (floorKey.intValue() < firstIndex) {
                        System.out.println("YES");
                    } else {
                        System.out.println("No");
                    } 
                } else if (floorKey == null) {
                    if (ceilKey.intValue() > lastIndex) {
                        System.out.println("YES");
                    } else {
                        System.out.println("No");
                    }
                } else {
                    if (floorKey.intValue() < firstIndex && ceilKey.intValue() > lastIndex) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        Transmission newTransmission = new Transmission();
        newTransmission.run();
    }
}
