public class roundrobin {

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
            System.out.println(" " + (i+1) + "\t\t" + bt[i] + "\t" + wt[i] + "\t\t" + tat[i]);
        }

        System.out.println("Average waiting time: " + (float)total_wt / (float)n);
        System.out.println("Average turn around time: " + (float)total_tat / (float)n);
    }

    public static void main(String[] args) throws Exception {
         
        int processes[] = { 1, 2, 3 }; 
        int n = processes.length; 
       
        int burst_time[] = { 10, 5, 8 }; 
       
        int quantum = 2; 
        avgTime(processes, n, burst_time, quantum); 

    }   
}
