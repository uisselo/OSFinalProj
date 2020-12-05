import java.util.Scanner;

public class roundrobin {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes [2-9]: ");
        int userIn = sc.nextInt();

        int p[] = new int[userIn], bt[] = new int[userIn];
        int n = p.length;

        System.out.println("Burst Time");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for P" + (i+1) + ": ");
            bt[i] = sc.nextInt();
        }

        System.out.print("Enter Time Quantum: ");
        int quantum = sc.nextInt();
        avgTime(p, n, bt, quantum);

        sc.close();

    }   

    static void waitingTime(int processes[], int n, int bt[], int wt[], int quantum) {
        int rem_bt[] = new int[n];

        for (int i = 0; i < n; i++)
        rem_bt[i] = bt[i];

        int t = 0;

        while(true) {
            boolean done = true;
            for (int i = 0; i < n; i++) {
                if (rem_bt[i] > 0) {
                    done = false;

                    if (rem_bt[i] > quantum) {
                        t += quantum;
                        rem_bt[i] -= quantum;
                    } else {
                        t = t + rem_bt[i];
                        wt[i] = t - bt[i];
                        rem_bt[i] = 0;
                    }
                }
            }
            if (done == true)
            break;
        }        
    }

    static void turnaroundTime(int processes[], int n, int bt[], int wt[], int tat[]) {
        for (int i = 0; i < n; i++)
        tat[i] = bt[i] + wt[i];
    }
    
    static void avgTime(int processes[], int n, int bt[], int quantum) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        waitingTime(processes, n, bt, wt, quantum);
        turnaroundTime(processes, n, bt, wt, tat);

        System.out.println("Processes " + "Burst Time " + "Waiting Time " + "Turn Around Time");

        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println("P" + (i+1) + "\t\t" + bt[i] + "\t" + wt[i] + "\t\t" + tat[i]);
        }

        System.out.println("Average waiting time: " + (float)total_wt / (float)n);
        System.out.println("Average turn around time: " + (float)total_tat / (float)n);
    }
}
