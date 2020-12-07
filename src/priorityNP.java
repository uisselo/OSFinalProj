import java.util.Scanner;

public class priorityNP {

    void sortProcesses(String pid[], int n, int at[], int bt[], int prt[]) {
        int temp;
        String stemp;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                if (at[j] > at[j + 1]) {
                    // swapping arrival time
                    temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;

                    // swapping burst time
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    // swapping priority
                    temp = prt[j];
                    prt[j] = prt[j + 1];
                    prt[j + 1] = temp;

                    // swapping process identity
                    stemp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = stemp;
                }

                if (at[j] == at[j + 1]) {
                    if (prt[j] > prt[j + 1]) {
                        // swapping arrival time
                        temp = at[j];
                        at[j] = at[j + 1];
                        at[j + 1] = temp;

                        // swapping burst time
                        temp = bt[j];
                        bt[j] = bt[j + 1];
                        bt[j + 1] = temp;

                        // swapping priority
                        temp = prt[j];
                        prt[j] = prt[j + 1];
                        prt[j + 1] = temp;

                        // swapping process identity
                        stemp = pid[j];
                        pid[j] = pid[j + 1];
                        pid[j + 1] = stemp;
                    }
                }
            }
        }
    }

    void algorithm(String pid[], int n, int at[], int bt[], int prt[]) {

        int ct[] = new int[n], tat[] = new int[n], wt[] = new int[n], total_wt = 0, total_tat = 0;

        sortProcesses(pid, n, at, bt, prt);

        ct[0] = at[0] + bt[0];
        tat[0] = ct[0] - at[0];
        wt[0] = tat[0] - bt[0];

        for (int i = 1; i < n; i++) {
            ct[i] = bt[i] + ct[i - 1];
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }

        System.out.println("Processes | " + "Arrival Time | " + "Burst Time | " + "Completion Time | " + "Turn Around Time | " + "Waiting Time");

        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];

            System.out.println("P" + (i+1) + "\t\t" + at[i] + "\t\t" + bt[i] + "\t\t" + ct[i] + "\t\t" + tat[i] + "\t\t" + wt[i] );
        }   
        
        System.out.println("Average waiting time: " + (float)total_wt / (float)n);
        System.out.println("Average turn around time: " + (float)total_tat / (float)n);

    }

    void userIn() {
        System.out.println("");
        System.out.println("[5] CPU Scheduling / Non Preemptive Priority");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes [2-9]: ");
        int userIn = sc.nextInt();

        String pid[] = new String[userIn];
        int at[] = new int[userIn], bt[] = new int[userIn], prt[] = new int[userIn];
        int n = pid.length;

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

        System.out.println("Priority");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter priority for P" + (i+1) + ": ");
            prt[i] = sc.nextInt();
        }     

        algorithm(pid, n, at, bt, prt);    

        sc.close();
    }
}
