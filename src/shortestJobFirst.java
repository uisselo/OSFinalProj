import java.util.Scanner;

public class shortestJobFirst {

    static Algorithms mainClass = new Algorithms();

    void callMain() {
        main(null);
    }
    
    public static void main(String[] args) {
        System.out.println("");
        System.out.println("[6] CPU Scheduling / Non Preemptive SJF");

        Scanner sc = new Scanner(System.in);
        System.out.println ("Enter number of processes [2-9]");
        int n = sc.nextInt();

        while (n < 2 || n > 9) {
            System.out.println("Invalid input. Try again.");
            main(null);
        }

        int pid[] = new int[n];
        int at[] = new int[n];  // arrival time
        int bt[] = new int[n];  // burst time
        int ct[] = new int[n];  // complete time
        int ta[] = new int[n];  // turn around time
        int wt[] = new int[n];  // waiting time
        int f[] = new int[n];   // flag, it checks process is completed or not
        int st = 0, tot = 0;
        float avgwt = 0 , avgta = 0;
    
        for (int i=0;i<n;i++) {
            System.out.println ("Enter process " + (i+1) + " arrival time:");
            at[i] = sc.nextInt();

            System.out.println ("Enter process " + (i+1) + " brust time:");
            bt[i] = sc.nextInt();
            pid[i] = i+1;
            f[i] = 0;
        }
        
        boolean a = true;
        while (a) {
            int c = n, min = 999;
            if (tot == n) break; // total no of process = completed process loop will be terminated            
            
            for (int i=0; i<n; i++) {
                /*
                * If i'th process arrival time <= system time and its flag = 0 and burst < min 
                * That process will be executed first 
                */ 
                if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min)) {
                    min=bt[i];
                    c=i;
                }
            }
            
            /* If c == n means c value can not updated because no process arrival time < system time so we increase the system time */
            if (c == n) st++;
            else {
                ct[c] = st + bt[c];
                st += bt[c];
                ta[c] = ct[c] - at[c];
                wt[c] = ta[c] - bt[c];
                f[c] = 1;
                tot++;
            }
        }
        
        System.out.println("\npid  arrival brust  complete turn waiting");
        for(int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgta += ta[i];
            System.out.println(pid[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+ta[i]+"\t"+wt[i]);
        }
        System.out.println ("\nAverage Turn Around Time is "+ (float)(avgta/n));
        System.out.println ("Average Waiting Time is "+ (float)(avgwt/n));

        tryAgain();
        
        sc.close();
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
            System.out.println("[A] Try Non Preemptive Priority again");
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
