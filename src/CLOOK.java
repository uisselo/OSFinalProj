import java.util.*;

public class CLOOK {
    void callMain() {
        main(null);
    }

    static void algorithm(int length, int arr[], int head) {
        int disk_size = length;
        int seek_count = 0; 
        int distance, cur_track; 
        float sum_seek = 0;
        float head_movement = 0; 
        
        Vector<Integer> left = new Vector<Integer>(); 
        Vector<Integer> right = new Vector<Integer>(); 
        Vector<Integer> seek_sequence = new Vector<Integer>(); 
    
        // Tracks on the left of the 
        // head will be serviced when 
        // once the head comes back 
        // to the beggining (left end) 
        for (int i = 0; i < disk_size; i++) {
            if (arr[i] < head) 
            left.add(arr[i]); 
            if (arr[i] > head) 
            right.add(arr[i]); 
        } 
    
        // Sorting left and right vectors 
        Collections.sort(left);  
        Collections.sort(right);  
    
        // First service the requests 
        // on the right side of the 
        // head 
        for (int i = 0; i < right.size(); i++) {
            cur_track = right.get(i); 
    
            // Appending current track 
            // to seek sequence 
            seek_sequence.add(cur_track); 
    
            // Calculate absolute distance 
            distance = Math.abs(cur_track - head); 
    
            // Increase the total count 
            seek_count += distance; 
    
            // Accessed track is now new head 
            head = cur_track; 
        } 
    
        // Once reached the right end 
        // jump to the last track that 
        // is needed to be serviced in 
        // left direction 
        seek_count += Math.abs(head - left.get(0)); 
        head = left.get(0); 
    
        // Now service the requests again 
        // which are left 
        for (int i = 0; i < left.size(); i++) { 
            cur_track = left.get(i); 
    
            // Appending current track to
            // seek sequence 
            seek_sequence.add(cur_track); 
    
            // Calculate absolute distance 
            distance = Math.abs(cur_track - head); 
    
            // Increase the total count 
            seek_count += distance; 
    
            // Accessed track is now the new head 
            head = cur_track; 
        }  
        // Results 
        System.out.println("Seek Sequence is"); 
    
        for (int i = 0; i < seek_sequence.size(); i++) {
            System.out.println(seek_sequence.get(i));
            sum_seek =+ seek_sequence.get(i);
        } 
        
        System.out.println("Total number of seek " + "operations = " + seek_count);

        head_movement = sum_seek;
        System.out.println("Total head movement = " + head_movement);
    }

    public static void main(String[] args) {
        System.out.println("");
        System.out.println("[7] Disk Scheduling / CLOOK");
        Scanner in = new Scanner(System.in);
        // Number of requests
        System.out.println("Input number of requests(5-10): ");
        int length = in.nextInt();
        
        // Request array 
        System.out.println("Input the requests");        
        int arr[] = new int[length];
        for(int i=0; i<length; i++) {
            arr[i]= in.nextInt();
        }

        // Head input
        System.out.println("Input the head:");
        int head = in.nextInt(); 

        //Print initial head
        System.out.println("Initial position of head: " + head); 

        //Call method
        algorithm(length, arr, head); 

        in.close();
    }
}
