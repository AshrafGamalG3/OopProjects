public class Process {
    int processId;
    int arrivalTime;
    int burstTime;
    int priorityPP_NonPP;
    int prioritySjf;
    int remainingBurstTimeSjf;
    int remainingBurstTimePP_NONPP;
    int remainingBurstTimeRR;
    int waitTime;
    int turnaroundTime;


    public Process(int processId, int arrivalTime, int burstTime, int priority) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityPP_NonPP = priority;
        this.prioritySjf=priority;
        remainingBurstTimeSjf=burstTime;
        remainingBurstTimePP_NONPP=burstTime;
        remainingBurstTimeRR=burstTime;
    }

    public int getRemainingBurstTimeRR() {
        return remainingBurstTimeRR;
    }

    public void setRemainingBurstTimeRR(int remainingBurstTimeRR) {
        this.remainingBurstTimeRR = remainingBurstTimeRR;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getPriorityPP_NonPP() {
        return priorityPP_NonPP;
    }

    public void setPriorityPP_NonPP(int priorityPP_NonPP) {
        this.priorityPP_NonPP = priorityPP_NonPP;
    }

    public int getPrioritySjf() {
        return prioritySjf;
    }

    public void setPrioritySjf(int prioritySjf) {
        this.prioritySjf = prioritySjf;
    }


    public int getRemainingBurstTimeSjf() {
        return remainingBurstTimeSjf;
    }

    public void setRemainingBurstTimeSjf(int remainingBurstTimeSjf) {
        this.remainingBurstTimeSjf = remainingBurstTimeSjf;
    }

    public int getRemainingBurstTimePP_NONPP() {
        return remainingBurstTimePP_NONPP;
    }

    public void setRemainingBurstTimePP_NONPP(int remainingBurstTimePP_NONPP) {
        this.remainingBurstTimePP_NONPP = remainingBurstTimePP_NONPP;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }


}
