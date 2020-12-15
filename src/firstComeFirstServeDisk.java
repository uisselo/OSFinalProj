import java.util.Scanner;

public class firstComeFirstServeDisk {

	static Algorithms mainClass = new Algorithms();

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

		tryAgain();

		console.close();
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
            System.out.println("[A] Try Disk FCFS again");
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
