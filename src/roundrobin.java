import java.util.Scanner;

public class roundRobin {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes [2-9]: ");
        int userIn = sc.nextInt();

        int p[] = new int[userIn], at[] = new int[userIn], bt[] = new int[userIn];
        int n = p.length;

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
        int quantum = sc.nextInt();

        avgTime(p, n, bt, quantum, at);

        sc.close();

    }   

    static void waitingTime(int processes[], int n, int bt[], int wt[], int quantum, int ct[], int at[]) {
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

                    if (rem_bt[i] > quantum & at[i] <= arrival) {
                        t += quantum;
                        rem_bt[i] -= quantum;
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

    static void turnaroundTime(int processes[], int n, int bt[], int wt[], int tat[], int ct[], int at[]) {
        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }
    }
    
    static void avgTime(int processes[], int n, int bt[], int quantum, int at[]) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;
        int ct[] = new int[n];

        waitingTime(processes, n, bt, wt, quantum, ct, at);
        turnaroundTime(processes, n, bt, wt, tat, ct, at);

        System.out.println("Processes " + "Arrival Time " + "Burst Time " + "Waiting Time " + "Turn Around Time");

        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println("P" + (i+1) + "\t\t" + at[i] + "\t" + bt[i] + "\t" + wt[i] + "\t\t" + tat[i]);
        }

        System.out.println("Average waiting time: " + (float)total_wt / (float)n);
        System.out.println("Average turn around time: " + (float)total_tat / (float)n);
    }
}
