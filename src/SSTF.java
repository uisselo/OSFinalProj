import java.util.Scanner;

public class SSTF {

    static Algorithms mainClass = new Algorithms();

    void callMain() {
        main(null);
    }
    
    public static void main(String[] args) {
        System.out.println("");
        System.out.println("[9] Disk Scheduling / SSTF");
        Scanner input  = new Scanner(System.in);
        int queue[] = new int[100];
        int queue2[] = new int[100];
        int head;
        int seek = 0;
        int qSize;
        int temp;
        float avg;

        System.out.println("Enter Number of Requests [5-10]:");
        qSize = input.nextInt();

        while (qSize < 5 || qSize > 10) {
            System.out.println("Invalid input. Try again.");
            main(null);
        }

        System.out.println("Input Elements:");
        for (int i = 0; i < qSize; i++) {
            queue[i] = input.nextInt();
        }

        System.out.println("Enter initial head position:");
        head = input.nextInt();

        for (int i = 0; i < qSize; i++) {
            queue2[i] = Math.abs(head - queue[i]);
        }

        for (int i=0; i<qSize; i++) {

            for (int j = i + 1; j < qSize; j++) {

                if (queue2[i] >queue2[j]) {
                    temp = queue2[i];
                    queue2[i] = queue2[j];
                    queue2[j] = temp;

                    temp = queue[i];
                    queue[i] = queue[j];
                    queue[j] = temp;
                }

            }
        }

        for (int i=1; i<qSize; i++) {
            seek = seek + Math.abs(head - queue[i]);
            head = queue[i];
        }

        System.out.println("Total seek time is: " + seek);
        avg = seek/(float)qSize;
        System.out.println("Average seek time is: " + avg);

        tryAgain();

        input.close();
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
            System.out.println("[A] Try Disk SSTF again");
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
