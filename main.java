import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class Main{

    public static void computeFCFS(int array[]){
        //calculating turnaround time


        //starting value will be 0
        int currentBurstTime = 0;

        //turn around time starts at 0
        int turnAround = 0;

        //result1 will output turn around time
        int result1[] = new int[array.length];

        //calculating the turn around time
        for (int i = 0; i < array.length; i++) {


            //calculating the turn around time by adding the current burst time with
            //the current element in the array (ex. 0 + 2 = 2)
            turnAround = currentBurstTime + array[i];

            //setting the current burst time to the value of turn around time
            currentBurstTime = turnAround;

            //adding the turn around time to the result array
            result1[i] = turnAround;
        }



        //calculating the wait time

        //current burst time starts at 0
        currentBurstTime = array[0];
        int result2[] = new int[array.length];

        //the array will have the wait time start at 0
        result2[0] = 0;

        //calculating the following wait times
        for (int i = 1; i < array.length; i++) {
            result2[i] = currentBurstTime;

            //adding wait time to the next burst
            currentBurstTime = currentBurstTime + array[i];

        }

        //printing the results
        printFCFS_Results(result1, result2);

    }

    

    //computing turn around time and wait time for SJF
    public static void computeSJF(int array[]) {

        //clone the array to sort it
        int[] sortedArray = array.clone();
        int size = array.length;

        //create an array of indices to keep track of the original order
        Integer[] indices = new Integer[size];


        
        //initialize indices
        for (int i = 0; i < size; i++) {
            indices[i] = i;
        }
        
        //sort indices based on array values
        Arrays.sort(indices, Comparator.comparingInt(a -> sortedArray[a]));
        

        //initialize arrays for turn around time and wait time
        int[] turnAroundTime = new int[size];
        int[] waitTime = new int[size];
        int currentTime = 0;
        
        
        //calculate Turn Around Time and Wait Time
        for (int i = 0; i < size; i++) {
            //getting the original index of the process in the sorted order
            int id = indices[i];

            //updating the current time by adding the burst time of the current process
            currentTime += array[id];

            //setting the turnaround time for the current process
            turnAroundTime[id] = currentTime;

            //calculating and setting the wait time for the current process
            waitTime[id] = currentTime - sortedArray[id];
        }
        
        //printing the results
        printSJF_Results(turnAroundTime, waitTime);
    }
    
    
    //printing the results for FCFS
    public static void printFCFS_Results(int array1[], int array2[]){
        System.out.println("----------------- FCFS -----------------");

        System.out.println("Process ID |\t    Waiting Time   |\tTurnaround Time");

        //displaying the results
        for (int i = 0; i < array1.length; i++) {
            System.out.println((i + 1) + "\t   |\t\t" + array2[i] + "\t   |\t\t" + array1[i]);
        }
    }


    //printing the results for SJF
    public static void printSJF_Results(int array1[], int array2[]){
        System.out.println("----------------- SJF -----------------");

        System.out.println("Process ID |\t    Waiting Time   |\tTurnaround Time");

        //displaying the results
        for (int i = 0; i < array1.length; i++) {
            System.out.println((i + 1) + "\t   |\t\t" + array2[i] + "\t   |\t\t" + array1[i]);
        }
    }

    
    public static void main(String[] args) {
        int processes[] = new int[5];

        processes[0] = 2;
        processes[1] = 1;
        processes[2] = 8;
        processes[3] = 4;
        processes[4] = 5;

        //two separate methods for calculating FCFS and SJF
        System.out.println();
        computeFCFS(processes);

        System.out.println("\n");

        computeSJF(processes);
        
    }
}