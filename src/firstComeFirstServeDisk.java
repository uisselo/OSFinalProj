import java.util.Scanner;

public class firstComeFirstServeDisk {
	void callMain() {
		main(null);
	}
	public static void main(String[] args) {
		System.out.println("");
        System.out.println("[8] Disk Scheduling / FCFS");

		Scanner console = new Scanner (System.in);
		
		int head, numreqs;
		int total = 0; 
		int distance, cur_track; 		
		float avg = 0;

		System.out.println("Enter number of Requests [5-10]:");
		numreqs = console.nextInt(); 
		int reqs[] = new int[numreqs];

		while (numreqs < 5 || numreqs > 10) {
            System.out.println("Invalid input. Try again.");
            main(null);
        }

		System.out.println("Enter Head:");
		head  = console.nextInt();

		System.out.println("Enter Requests:");
		for (int i=0;i<numreqs;i++) {
			reqs[i]=console.nextInt();    
		}

		for (int i = 0; i < numreqs; i++) {
			cur_track = reqs[i]; 
			distance = Math.abs(cur_track - head); 
			total += distance; 
			head = cur_track; 
			avg = total/numreqs;
		} 	
		
		System.out.println("Seek Sequence is"); 

		for (int i = 0; i < numreqs; i++) { 
			System.out.println(reqs[i]); 
		}  

		System.out.println("Total head movement: " + total); 
		System.out.println("Average seek time: " +  avg); 
	

		console.close();
	}	
}
