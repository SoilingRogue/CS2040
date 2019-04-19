/**
 * Name         : Yuan Xinran, Stanley
 * Matric. No   : A0182555Y
 * PLab Acct.   :
 */

import java.util.*;

public class Ranking {
    private int noOfStudents;
    private ArrayList<Student> studentList;
    private PriorityQueue<Student> rankList;

    private class Student implements Comparable<Student>{
        private String name;
        private int score, rank = 0;

        public Student(String name, int score){
            this.name = name;
            this.score = score;
        }

        public String getName(){
            return this.name;
        }

        public int getScore(){
            return this.score;
        }

        public void setRank(int rank){
            this.rank = rank;
        }

        public int getRank(){
            return this.rank;
        }

        @Override
            public int compareTo(Student student){
                return student.getScore() - this.getScore();     
            }
    }

    private void run() {
        Scanner sc = new Scanner(System.in);	
        noOfStudents = sc.nextInt();
        studentList = new ArrayList<Student>();
        rankList = new PriorityQueue<Student>();

        for (int i = 0; i < noOfStudents; ++i){
            Student student = new Student(sc.next(), sc.nextInt());
            studentList.add(student);
            rankList.add(student);
        }

        rank();
        print();
    }

    private void rank(){
        Student first = rankList.poll();
        int counter = 1;
        int rank = 1;
        // int total = 1;
        int currScore = first.getScore();
        first.setRank(rank);
        // System.out.println(rankList.size());
        final int size = rankList.size();
        for (int i = 0; i < size; ++i){
            Student temp = rankList.poll();
            if (temp.getScore() == currScore){
                temp.setRank(rank);
                ++counter;
                // total ++;
            } else {
                currScore = temp.getScore();
                rank += counter;
                counter = 1;
                temp.setRank(rank);
                // total++;
            }
            // total++;
        }
        // System.out.println(total);
    }

    private void print(){
        for (Student student: studentList){
            System.out.printf("%s %d\n", student.getName(), student.getRank());
        }
    }

    public static void main(String[] args) {
        Ranking newRanking = new Ranking();
        newRanking.run();
    }
}
