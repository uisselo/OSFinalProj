import java.util.Arrays;
import java.util.Scanner;

public class priorityP {
	void callMain() {
		main(null);
	}
	public static void main(String[] args) {		
		Scanner a = new Scanner(System.in);

		int n;
		int TotalTurnaroundTime = 0;
		int TotalWaitingTime = 0;
		int TotalResponseTime = 0;
		int total_idle_time = 0;
		double avgTT;
		double avgWT;
		double avgRT;
		double throughput;
		int burst_remaining[] = new int [100];
		int is_completed[] = new int [100];
		Arrays.fill(is_completed, 0);
		Model p[] = new Model[100];
		System.out.print("Enter number of processes [2-9]: ");
		n = a.nextInt();
		
		for (int i = 0; i < n; i++){
			p[i] = new Model();
		}
		for (int i = 0; i < n; i++){
			p[i].pid = i+1;
		}

		System.out.println();
		for (int i = 0; i < n; i++){
			System.out.print("Arrival Time for P["+p[i].pid+"]: ");
			p[i].at = a.nextInt();
			System.out.print("Burst Time for P["+p[i].pid +"]: ");
			p[i].bt = a.nextInt();
			System.out.print("Priority of P["+p[i].pid +"]: ");
			p[i].priority = a.nextInt();
			burst_remaining[i] = p[i].bt;
			System.out.println();	
		}
		int completed = 0;
		int prev = 0;
		int currentTime = 0;
		while (completed != n) {
			int idx = -1;
			int mx = -1;
			for (int i = 0; i < n; i++) {
				if ((p[i].at <= currentTime) && (is_completed[i] == 0)) {
					if (p[i].priority > mx) {
						mx = p[i].priority;
						idx = i;
					}
					if (p[i].priority == mx) {
						if (p[i].at < p[idx].at) {
							mx = p[i].priority;
							idx = i;
						}
					}
				}
			}
			
			if (idx != -1) {
				if (burst_remaining[idx] == p[idx].bt) {
					p[idx].st = currentTime;
					total_idle_time += p[idx].st - prev;
				}
				
				burst_remaining[idx] -= 1;
				currentTime++;
				prev = currentTime;
				
				if (burst_remaining[idx] == 0) {
					p[idx].ct = currentTime;
					p[idx].tt = (p[idx].ct - p[idx].at);
					p[idx].wt = (p[idx].tt - p[idx].bt);
					p[idx].rt = (p[idx].st - p[idx].at);
					
					TotalTurnaroundTime += p[idx].tt;
					TotalWaitingTime += p[idx].wt;
					TotalResponseTime += p[idx].rt;
					
					is_completed[idx] = 1;
					completed++;
				}
			}
			else {
				currentTime++;
			}
		}

		int min_arrival_time = 10000000;
		int max_completion_time = -1;
		
		for (int i = 0; i < n; i++) {
			min_arrival_time = Math.min(min_arrival_time, p[i].at);
			max_completion_time = Math.max(max_completion_time, p[i].ct);
		}

		avgTT =(double) TotalTurnaroundTime/n;
		avgWT = (double) TotalWaitingTime/n;
		avgRT; = (double) TotalResponseTime/n;
		cpu_utilisation = ((max_completion_time - total_idle_time) / (double)max_completion_time) *100.00;
		throughput = (double) n / (max_completion_time - min_arrival_time);
		
		System.out.println();
		System.out.println("Process\t"+"AT\t"+"BT\t"+"Priority\t"+"TAT\t"+"WT\t"+"RT");
		
		for(int i = 0; i < n; i++){
			System.out.println(p[i].pid+"\t"+p[i].at+"\t"+p[i].bt+"\t"+p[i].priority+"\t"+"\t" +p[i].tt+"\t"+p[i].wt+"\t"+p[i].rt);
		}

		System.out.println("Average turn around time: "+ avgTT);
		System.out.println("Average waiting time: "+ avgWT);
		System.out.println("Average response time: "+ avgRT;);

		a.close();
		
	}
}

class Model {
	int pid = 0;
	int at = 0;
	int bt = 0;
	int priority = 0;
	int st = 0;
	int ct = 0;
	int tt = 0;
	int wt = 0;
	int rt = 0;
}