import java.util.Scanner;

public class SSTF {
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

        input.close();
    }
}
