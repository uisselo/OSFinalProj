import java.util.Scanner;

public class firstComeFirstServeNP {

    static Algorithms mainClass = new Algorithms();

    void callMain() {
        main(null);
    }
    
    public static void main(String[] args) {
        System.out.println("");
        System.out.println("[4] CPU Scheduling / Non Preemptive FCFS");

        Scanner console= new Scanner (System.in);

        System.out.println("Enter No. of Process [2-9]: ");
        int n = console.nextInt();

        while (n < 2 || n > 9) {
            System.out.println("Invalid input. Try again.");
            main(null);
        }       
        
        int pid[] = new int[n];   // process ids
        int ar[] = new int[n];    // arrival times
        int bt[] = new int[n];    // burst or execution times
        int ct[] = new int[n];    // completion times
        int ta[] = new int[n];    // turn around times
        int wt[] = new int[n];    // waiting times.
        int f[] = new int[n];
        int st = 0, tot = 0;
        int temp;

        float avgwt = 0, avgta = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("Enter Process " + (i + 1) + " Arrival time: ");
            ar[i] = console.nextInt();
            System.out.println("Enter process " + (i + 1) + " Burst time: ");
            bt[i] = console.nextInt();
            pid[i] = i + 1;
            f[i] = 0;
        }

        // sorting according to arrival times
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - (i + 1); j++) {
                if (ar[j] > ar[j + 1]) {
                    temp = ar[j];
                    ar[j] = ar[j + 1];
                    ar[j + 1] = temp;

                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    temp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp;
                }
            }
        }

        // finding completion times
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ct[i] = ar[i] + bt[i];
            } else {
                if (ar[i] > ct[i - 1]) {
                    ct[i] = ar[i] + bt[i];
                } else
                    ct[i] = ct[i - 1] + bt[i];
            }
            ta[i] = ct[i] - ar[i];         // turnaround time = completion time - arrival time
            wt[i] = ta[i] - bt[i];         // waiting time = turnaround time - burst time
            avgwt += wt[i];                // total waiting time
            avgta += ta[i];                // total turnaround time
        }

        System.out.println("\nProcess  Arrival  Burst  Complete Turnaround Waiting");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "   \t " + ar[i] + "     \t" + bt[i] + "    \t" + ct[i] + "       \t" + ta[i] + "     \t" + wt[i]);
        }
        console.close();
        System.out.println("\nAverage Waiting time: " + (avgwt / n));     // average waiting time
        System.out.println("Average Turnaround time:" + (avgta / n));     // average turnaround time

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
            System.out.println("[A] Try Non Preemptive FCFS again");
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
}
