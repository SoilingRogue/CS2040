/*
 * Name       : Yuan Xinran, Stanley
 * Matric No. : A0182555Y
 * Plab Acct. :
 */
import java.util.*;

public class Classphoto {

    private void run() {
        Scanner sc = new Scanner(System.in);
        TreeMap<Integer, LinkedList<String>> order = new TreeMap<>();

        final int numStudent = sc.nextInt();
        final LinkedList<String> rarList = new LinkedList<>();
        rarList.add("Rar");
        order.put(0, rarList);
        for (int i = 1; i <= numStudent; ++i){
            final String name = sc.next();
            final Integer height = Integer.valueOf(sc.nextInt());
            if (order.containsKey(height)){
                final LinkedList<String> temp = order.get(height);
                temp.addFirst(name);
                System.out.println(order.lowerEntry(height).getValue().peekLast());
            } else {
                final LinkedList<String> lst = new LinkedList<>();
                lst.add(name);
                order.put(height, lst);
                System.out.println(order.lowerEntry(height).getValue().peekLast());
            }
        }
        boolean first = true;
        for (Map.Entry<Integer, LinkedList<String>> entry: order.entrySet()){
            for (String s: entry.getValue()){
                if (first){
                    System.out.print(s);
                    first = false;
                } else {
                    System.out.print(" " + s);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Classphoto newClassphoto = new Classphoto();
        newClassphoto.run();
    }
}
