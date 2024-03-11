import java.util.*;

public class Scheduler {


    public void runFCFS( Queue<Process> readyQueueInputs) {
        System.out.println("FCFS Scheduling Order:");
        System.out.println();
        List<Process> processes = new ArrayList<>();
        Queue<Process> readyQueue = new LinkedList<>(processes);
        processes.addAll(readyQueueInputs);   // p1 , p2 ,p3 ,p4

        int currentTime = 0;
        float averageWaitTime = 0;
        float averageTurnaroundTime = 0;
        float n = processes.size();
        System.out.println("Process\t\tCT\t\tAT\t\tWT\t\tTAT");
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
        readyQueue.addAll(processes);

        while (!readyQueue.isEmpty()){
            Process currentProcess = readyQueue.poll ();  //p3
            currentTime+=currentProcess.getBurstTime();
            currentProcess.setTurnaroundTime (currentTime- currentProcess. getArrivalTime() ) ;
            currentProcess.setWaitTime(currentProcess.getTurnaroundTime()-currentProcess.getBurstTime());
            System.out.println(currentProcess.getProcessId()+"\t\t\t"+ currentProcess.getBurstTime()+"\t\t"+
                    currentProcess.getArrivalTime()+"\t\t" + currentProcess.getWaitTime() +"\t\t"
                    +currentProcess.getTurnaroundTime() );
            averageWaitTime+=currentProcess.getWaitTime();
            averageTurnaroundTime+=currentProcess.getTurnaroundTime();
            processes.remove(currentProcess);
        }
        System.out.println();
        System.out.println("The average of Waiting Time: " + averageWaitTime / n);
        System.out.println("The average of Turnaround Time: " + averageTurnaroundTime / n);


        System.out.println();

        System.out.println("=================================================================================================================");
        }

    public void runSJFNonPreemptive(Queue<Process> readyQueueInputs) {
        System.out.println("SJF Non-Preemptive Scheduling :");
        System.out.println();
        List<Process> processesUse = new ArrayList<>();
        List<Process> processes = new ArrayList<>(readyQueueInputs);
        int currentTime = 0;
        float averageWaitTime=0;
        float averageTurnaroundTime=0;
        float n=processes.size();
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));// sort processes by arrival time
        System.out.println("Process\t\tCT\t\tAT\t\tWT\t\tTAT");
        while (!processes.isEmpty() || !processesUse.isEmpty()) {// finish when processes & processesUse isEmpty
            while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= currentTime) {//7
                processesUse.add(processes.remove(0)); // add p1 to processesUse
            }
            if (!processesUse.isEmpty()) {
              processesUse.sort(Comparator.comparingInt(Process::getBurstTime));
                Process currentProcess = processesUse.get(0);
//                System.out.println("Time " + currentTime + ": Running process " + currentProcess.processId);
                currentTime += currentProcess.getBurstTime();
//                System.out.println("Process " + currentProcess.processId + " completed at time " + currentTime);
                currentProcess.setTurnaroundTime(currentTime-currentProcess.getArrivalTime());
                currentProcess.setWaitTime(currentProcess.getTurnaroundTime()-currentProcess.getBurstTime());
                System.out.println(currentProcess.getProcessId()+"\t\t\t"+ currentProcess.getBurstTime()+"\t\t"+
                        currentProcess.getArrivalTime()+"\t\t" + currentProcess.getWaitTime() +"\t\t"
                        +currentProcess.getTurnaroundTime() );
                averageWaitTime+=currentProcess.getWaitTime();
                averageTurnaroundTime+=currentProcess.getTurnaroundTime();
                processesUse.remove(currentProcess);
            } else {
                currentTime++;
            }
        }
        System.out.println();
        System.out.println("the average of Waiting Time : "+averageWaitTime/n);
        System.out.println("the average of Turnaround Time : "+averageTurnaroundTime/n);


        System.out.println();

        System.out.println("=================================================================================================================");
    }

    public void runSjfPreemptive(Queue<Process> readyQueueInputs) {
        System.out.println("Executing SJF  Preemptive Scheduling Algorithm");
        System.out.println();
        List<Process> processesUse = new ArrayList<>();
        ////
        List<Process> processes = new ArrayList<>(readyQueueInputs);
        int currentTime = 0;
        float averageWaitTime = 0;
        float averageTurnaroundTime = 0;
        float n = processes.size();
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
        System.out.println("Process\t\tCT\t\tAT\t\tWT\t\tTAT");
        while (!processes.isEmpty() || !processesUse.isEmpty()) { // processes &&

            while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= currentTime) {
                processesUse.add(processes.remove(0));
            }

            if (!processesUse.isEmpty()) {
                    processesUse.sort(Comparator.comparingInt(Process::getRemainingBurstTimeSjf)); // Sort by remaining burst time
                    Process currentProcess = processesUse.get(0);

                if (currentProcess.getRemainingBurstTimeSjf() > 1) {
                    // Process is not completed
                    currentTime++;  //2
                    currentProcess.remainingBurstTimeSjf--; //5
                } else {
                    // Process is completed
                    currentTime += currentProcess.getRemainingBurstTimeSjf();
                    currentProcess.setTurnaroundTime(currentTime - currentProcess.getArrivalTime());
                    currentProcess.setWaitTime(currentProcess.getTurnaroundTime() - currentProcess.getBurstTime());
                    System.out.println(currentProcess.getProcessId() + "\t\t\t" + currentProcess.getBurstTime() + "\t\t" +
                            currentProcess.getArrivalTime() + "\t\t" + currentProcess.getWaitTime() + "\t\t"
                            + currentProcess.getTurnaroundTime());
                    averageWaitTime += currentProcess.getWaitTime();
                    averageTurnaroundTime += currentProcess.getTurnaroundTime();
                    processesUse.remove(currentProcess);
                }
            } else {
                currentTime++;
            }
        }
        System.out.println();
        System.out.println("Average Waiting Time: " + (averageWaitTime / n));
        System.out.println("Average Turnaround Time: " + (averageTurnaroundTime / n));


        System.out.println();

        System.out.println("=================================================================================================================");
    }
    public void runRoundRobin(int timeQuantum,Queue<Process> readyQueueInputs) {
        System.out.println("Round Robin Scheduling with Time Quantum " + timeQuantum + " :");
        System.out.println();
        Queue<Process> readyQueue = new LinkedList<>();
        List<Process> processes = new ArrayList<>(readyQueueInputs);
        int currentTime = 0;
        float averageWaitTime=0;
        float averageTurnaroundTime=0;
        float n=processes.size();
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
        while (!processes.isEmpty() || !readyQueue.isEmpty()) {
            // Move processes to the ready queue when they arrive
            while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= currentTime) {
                readyQueue.add(processes.remove(0));
            }
            if (!readyQueue.isEmpty()) {

                Process currentProcess = readyQueue.poll();
//                System.out.println("Time " + currentTime + ": Running process " + currentProcess.processId);
                int executionTime = Math.min(timeQuantum, currentProcess.getRemainingBurstTimeRR());
                currentTime += executionTime;
                currentProcess.remainingBurstTimeRR -= executionTime;
                if (currentProcess.getRemainingBurstTimeRR() > 0) {
                    readyQueue.add(currentProcess);
                } else {
//                    System.out.println("Process " + currentProcess.processId + " completed at time " + currentTime);
                    currentProcess.setTurnaroundTime(currentTime-currentProcess.getArrivalTime());
                    currentProcess.setWaitTime(currentProcess.getTurnaroundTime()-currentProcess.getBurstTime());
                    System.out.println(currentProcess.getProcessId()+"\t\t\t"+ currentProcess.getBurstTime()+"\t\t"+
                            currentProcess.getArrivalTime()+"\t\t" + currentProcess.getWaitTime() +"\t\t"
                            +currentProcess.getTurnaroundTime() );
                    averageWaitTime+=currentProcess.getWaitTime();
                    averageTurnaroundTime+=currentProcess.getTurnaroundTime();

                }
            } else {
                currentTime++;
            }
        }
        System.out.println();
        System.out.println("the average of Waiting Time : "+ averageWaitTime/n);
        System.out.println("the average of Turnaround Time : "+averageTurnaroundTime/n);

        System.out.println();

        System.out.println("=================================================================================================================");

    }
    public void runPriorityNonPreemptive(Queue<Process> readyQueueInputs) {
        System.out.println("Executing Priority Non-Preemptive Scheduling Algorithm");
        System.out.println();
        List<Process> processesUse = new ArrayList<>();
        /// processes of user
        List<Process> processes = new ArrayList<>(readyQueueInputs);

        int currentTime = 0;
        float averageWaitTime=0;
        float averageTurnaroundTime=0;
        float n=processes.size();
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
        System.out.println("Process\t\tCT\t\tAT\t\tWT\t\tTAT");
            while (!processes.isEmpty() || !processesUse.isEmpty()) {
            // Move processes to the ready queue when they arrive
            while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= currentTime) {
                processesUse.add(processes.remove(0));
            }
            if (!processesUse.isEmpty()) {
                processesUse.sort(Comparator.comparingInt(Process::getPriorityPP_NonPP)); // Sort by priority
                Process currentProcess = processesUse.remove(0);
//                System.out.println("Time " + currentTime + ": Running process " + currentProcess.processId);
                currentTime += currentProcess.getBurstTime();
                currentProcess.setTurnaroundTime(currentTime-currentProcess.getArrivalTime());
                currentProcess.setWaitTime(currentProcess.getTurnaroundTime()-currentProcess.getBurstTime());
                System.out.println(currentProcess.getProcessId()+"\t\t\t"+ currentProcess.getBurstTime()+"\t\t"+
                        currentProcess.getArrivalTime()+"\t\t" + currentProcess.getWaitTime() +"\t\t"
                        +currentProcess.getTurnaroundTime() );
                averageWaitTime+=currentProcess.getWaitTime();
                averageTurnaroundTime+=currentProcess.getTurnaroundTime();
//                System.out.println("Process " + currentProcess.processId + " completed at time " + currentTime);
            } else {
                currentTime++;
            }
        }
        System.out.println();
        System.out.println("The average of Waiting Time: " +  averageWaitTime / n);
        System.out.println("The average of Turnaround Time: " +  averageTurnaroundTime / n);


        System.out.println();

        System.out.println("=================================================================================================================");
    }


    public void runPriorityPreemptive(Queue<Process> readyQueueInputs) {
        System.out.println("Executing Priority Preemptive Scheduling Algorithm");
        System.out.println();
        List<Process> processesUse = new ArrayList<>();
        List<Process> processes = new ArrayList<>(readyQueueInputs);
        int currentTime = 0;
        float averageWaitTime = 0;
        float averageTurnaroundTime = 0;
        float n = processes.size();

        System.out.println("Process\t\tCT\t\tAT\t\tWT\t\tTAT");
        while (!processes.isEmpty() || !processesUse.isEmpty()) {
            // Move processes to the ready queue when they arrive

            while (!processes.isEmpty() && processes.get(0).arrivalTime <= currentTime) {
                processesUse.add(processes.remove(0));
            }

            if (!processesUse.isEmpty()) {
                processesUse.sort(Comparator.comparingInt(Process::getPriorityPP_NonPP)); // Sort by priority (lower value has higher priority)
                Process currentProcess = processesUse.get(0);

                if (currentProcess.getRemainingBurstTimePP_NONPP() > 1) {
                    // Process is not completed
                    currentTime++;
                    currentProcess.remainingBurstTimePP_NONPP--;
                } else {
                    // Process is completed
                    currentTime += currentProcess.getRemainingBurstTimePP_NONPP();
                    currentProcess.setTurnaroundTime(currentTime - currentProcess.getArrivalTime());
                    currentProcess.setWaitTime(currentProcess.getTurnaroundTime() - currentProcess.getBurstTime());
                    System.out.println(currentProcess.getProcessId() + "\t\t\t" + currentProcess.getBurstTime() + "\t\t" +
                            currentProcess.getArrivalTime() + "\t\t" + currentProcess.getWaitTime() + "\t\t"
                            + currentProcess.getTurnaroundTime());
                    averageWaitTime += currentProcess.getWaitTime();
                    averageTurnaroundTime += currentProcess.getTurnaroundTime();
                    processesUse.remove(currentProcess);
                }
            } else {
                currentTime++;
            }
        }
        System.out.println();
        System.out.println("Average Waiting Time: " + (averageWaitTime / n));
        System.out.println("Average Turnaround Time: " + (averageTurnaroundTime / n));

        System.out.println();

        System.out.println("=================================================================================================================");

    }


}
