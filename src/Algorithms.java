import java.util.Scanner;

public class Algorithms extends priorityNP {
    static roundRobin rr = new roundRobin();
    static priorityNP npp = new priorityNP();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Select an algorithm.");
        System.out.println("[1] Non Preemptive Priority");
        System.out.println("[2] Preemptive Round Robin");

        int userSelected = sc.nextInt();

        if (userSelected == 1) {
            rr.userIn();
        } else if (userSelected == 2) {
            npp.userIn();
        } else {
            System.out.println("Invalid input. Try again.");
        }

        sc.close();
    }

}
