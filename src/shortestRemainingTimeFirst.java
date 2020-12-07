import java.util.Scanner;

public class shortestRemainingTimeFirst {
    void callMain() {
        main(null);
    }
    public static void main(String[] args) {
        System.out.println("");
        System.out.println("[1] CPU Scheduling / Preemptive Shortest Remaining Time First");
        
        Scanner sc = new Scanner(System.in);
        // Getting the number of processes
        System.out.print("Please Enter number of Process [2-9]: ");
        int num = sc.nextInt();
          
        //Checking if the number of processes is between 2 and 9
        while (num < 2 || num > 9) {
            System.out.println("Invalid input. Try again.");
            main(null);
        }

		int num_proc = num;
		int proc_id[] = new int[num_proc]; 
		int arv_time[] = new int[num_proc]; 
		int burst_time[] = new int[num_proc]; 
		int completion_time[] = new int[num_proc]; 
		int turnaround_time[] = new int[num_proc];
		int waiting_time[] = new int[num_proc]; 
		int p[] = new int[num_proc];  
		int k[]= new int[num_proc];   
	    int i, st=0, ToT=0;
	    double ave_waiting_time=0, Ave_turnaround=0;
 
	    for (i = 0; i < num_proc; i++) {	
        	proc_id[i] = i + 1;
			System.out.print("Enter Arrival Time for Process " + (i+1) + ": ");
			arv_time[i]= sc.nextInt();

			System.out.print("Enter Brust Time for Process " + (i+1) + ": ");
			burst_time[i]= sc.nextInt();

	    	k[i]= burst_time[i];
			p[i]= 0;	    		
	    }
	    
	    while(true){
	    	int min = 99, cur = num_proc;
	    	if (ToT == num_proc)
	    		break;
	    	
	    	for (i = 0 ; i < num_proc; i++) {
	    		if ((arv_time[i]<=st) && (p[i]==0) && (burst_time[i]<min)) {	
	    			min=burst_time[i];
	    			cur=i;
	    		}
	    	}
	    	
	    	if (cur==num_proc) st++;
	    	else {
	    		burst_time[cur]--;
	    		st++;
	    		if (burst_time[cur]==0) {
	    			completion_time[cur]= st;
	    			p[cur]=1;
	    			ToT++;
	    		}
	    	}
	    }
	    
	    for( i= 0 ;i < num_proc; i++) {
	    	turnaround_time[i] = completion_time[i] - burst_time[i];
	    	waiting_time[i] = turnaround_time[i] - k[i];
	    	ave_waiting_time += waiting_time[i];
	    	Ave_turnaround += turnaround_time[i];
	   	}
	   System.out.printf("%s %s %s %s %s %s\n", "ProcessID", "ArrivalTime", "BurstTime", "CompletionTime", "TurnAroundTime", "WaitingTime");
	    for (i = 0; i < num_proc; i++) {
	    	System.out.printf("%8s %12s %9s %13s %15s %10s\n", proc_id[i], arv_time[i], k[i], completion_time[i], turnaround_time[i], waiting_time[i]); 
	    };
	    
		System.out.println ("\n The average turn around time is: "+ (double)(Ave_turnaround/num_proc));
		System.out.println ("\n The average waiting time is: "+ (double)(ave_waiting_time/num_proc));

		sc.close();

    }
}
