import java.util.*;

public class Main {
    static  Queue<Process> readyQueue = new LinkedList<>();
    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");   //4
        int numProcesses = scanner.nextInt(); ///4
        for (int i = 1; i <= numProcesses; i++) {  // 2
            System.out.println("Enter details for Process " + i + ":"); // p2

            System.out.print("Arrival Time: ");  //1         2
            int arrivalTime = scanner.nextInt();
            System.out.print("Burst Time: ");          // 3
            int burstTime = scanner.nextInt();
            System.out.print("Priority: ");          //0
            int priority = scanner.nextInt();
            ////
            readyQueue.add(new Process(i,arrivalTime,burstTime,priority)); // p1

        }
//

////scheduler.runFCFS(readyQueue);
////        scheduler.runSJFNonPreemptive(readyQueue);
       scheduler.runSjfPreemptive(readyQueue);
//       scheduler.runRoundRobin(4,readyQueue);
 //scheduler.runPriorityNonPreemptive(readyQueue);
//       // scheduler.runPriorityPreemptive(readyQueue);
//

    }



}