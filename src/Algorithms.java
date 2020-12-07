import java.util.Scanner;

public class Algorithms extends priorityNP {
    // preemptive cpu sched
    static priorityP pp = new priorityP();
    static roundRobin rr = new roundRobin();

    // nonpreemptive cpu sched
    static firstComeFirstServeNP fcfsNP = new firstComeFirstServeNP();
    static priorityNP npp = new priorityNP();
    static shortestJobFirst sjf = new shortestJobFirst();

    // disk sched
    static CLOOK clook = new CLOOK();
    static firstComeFirstServeDisk fcfsDisk = new firstComeFirstServeDisk();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Select an algorithm. [1-9]");
        System.out.println("CPU Scheduling");
        System.out.println("Preemptive");
        System.out.println("[1] Priority");
        System.out.println("[2] Round Robin");
        System.out.println("[3] Shortest Remaining Time");
        System.out.println("Non Peemptive");
        System.out.println("[4] First Come First Serve");
        System.out.println("[5] Priority");
        System.out.println("[6] Shortest Job First");
        System.out.println("Disk Scheduling");
        System.out.println("[7] CLOOK");
        System.out.println("[8] FCFS");
        System.out.println("[9] SSTF");

        int userSelected = sc.nextInt();

        if (userSelected == 1) pp.callMain();
        else if (userSelected == 2) rr.userIn();
        else if (userSelected == 3) System.out.println("wala pa"); 
        else if (userSelected == 4) fcfsNP.callMain();
        else if (userSelected == 5) npp.userIn();
        else if (userSelected == 6) sjf.callMain();
        else if (userSelected == 7) clook.callMain();
        else if (userSelected == 8) fcfsDisk.callMain();
        else if (userSelected == 9) System.out.println("wala pa");
        else System.out.println("Invalid input.");

        sc.close();
    }

}
