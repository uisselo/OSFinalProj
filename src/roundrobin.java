import java.util.Scanner;

public class roundRobin {

    static Algorithms mainClass = new Algorithms();

    void callMain() {
        main(null);
    }

    static void waitingTime(String pid[], int n, int at[], int bt[], int wt[], int qt, int ct[]) {
        
        int rem_bt[] = new int[n];

        for (int i = 0; i < n; i++)
        rem_bt[i] = bt[i];

        int t = 0;
        int arrival = 0;

        while(true) {
            boolean done = true;
            for (int i = 0; i < n; i++) {
                if (rem_bt[i] > 0) {
                    done = false;

                    if (rem_bt[i] > qt & at[i] <= arrival) {
                        t += qt;
                        rem_bt[i] -= qt;
                        arrival++;
                    } else {
                        t = t + rem_bt[i];
                        wt[i] = t - bt[i];
                        rem_bt[i] = 0;
                        ct[i]=t;
                    }
                }
            }
            if (done == true)
            break;
        }        
    }

    static void turnaroundTime(String pid[], int n, int at[], int bt[], int tat[], int wt[], int ct[]) {
        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }
    }
    
    static void avgTime(String pid[], int n, int at[], int bt[], int qt) {

        int wt[] = new int[n], tat[] = new int[n], ct[] = new int[n], total_wt = 0, total_tat = 0;

        waitingTime(pid, n, at, bt, wt, qt, ct);
        turnaroundTime(pid, n, at, bt, tat, wt, ct);

        System.out.println("Processes | " + "Arrival Time | " + "Burst Time | " + "Completion Time | " + "Turn Around Time | " + "Waiting Time");

        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];

            System.out.println("P" + (i+1) + "\t\t" + at[i] + "\t\t" + bt[i] + "\t\t" + ct[i] + "\t\t" + tat[i] + "\t\t" + wt[i] );
        }

        System.out.println("Average waiting time: " + (float)total_wt / (float)n);
        System.out.println("Average turn around time: " + (float)total_tat / (float)n);

        tryAgain();
    }

    static void tryAgain() {
        System.out.println("");
        System.out.print("Do you want to try again? [Y/N]: ");
        Scanner in = new Scanner(System.in);
        char yn = in.next().charAt(0);

        while (yn != 'Y' && yn != 'N') {
            System.out.print("Enter valid input. [Y/N]: ");
            yn = in.next().charAt(0);
        }

        if (yn == 'Y') {
            System.out.println("");
            System.out.println("[A] Try Preemptive RR again");
            System.out.println("[B] Try another algorithm");
            System.out.print("Enter your choice: ");
            char userIn = in.next().charAt(0);
            
            while (userIn != 'A' && userIn != 'B') {
                System.out.print("Enter valid input. [A/B]: ");
                userIn = in.next().charAt(0);
            }

            if (userIn == 'A') main(null);
            else if (userIn == 'B') mainClass.callMain();
        }
        else if (yn == 'N') {
            System.out.println("");
            System.out.println("Thank you!");
            System.exit(0);
        }

        in.close();
    }

    public static void main(String[] args) {
        System.out.println("");
        System.out.println("[2] CPU Scheduling / Preemptive Round Robin");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes [2-9]: ");
        int userIn = sc.nextInt();

        while (userIn < 2 || userIn > 9) {
            System.out.println("Invalid input. Try again.");
            main(null);
        }

        String pid[] = new String[userIn];
        int at[] = new int[userIn], bt[] = new int[userIn], n = pid.length;

        System.out.println("Arrival Time");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time for P" + (i+1) + ": ");
            at[i] = sc.nextInt();
        }

        System.out.println("Burst Time");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for P" + (i+1) + ": ");
            bt[i] = sc.nextInt();
        }

        System.out.print("Enter Time Quantum: ");
        int qt = sc.nextInt();

        avgTime(pid, n, at, bt, qt);

        sc.close();
    }
}
