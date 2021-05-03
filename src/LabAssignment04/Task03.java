package LabAssignment04;

import java.util.Scanner;

public class Task03 {
    private static final int TIME_QUANTUM = 4;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of processes:");
        int n = sc.nextInt();

        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] remainingBurstTime = new int[n];
        int[] completeTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] waitingTime = new int[n];
        int systemTime = 0, completedProcess = 0;
        float averageWaitingTime = 0.0f, averageTurnaroundTime = 0.0f;

        System.out.println("Enter process information in the following format:");
        System.out.println("ArrivalTime BurstTime");
        for (int i = 0; i < n; i++) {
            arrivalTime[i] = sc.nextInt();
            burstTime[i] = sc.nextInt();
            remainingBurstTime[i] = burstTime[i];
        }

        while (completedProcess != n){
            for(int i = 0; i < n; i++){
                if((arrivalTime[i] <= systemTime) && (remainingBurstTime[i] > 0)){
                    if(remainingBurstTime[i] <= TIME_QUANTUM){
                        systemTime += remainingBurstTime[i];
                        completeTime[i] = systemTime;
                        remainingBurstTime[i] = 0;
                        turnaroundTime[i] = completeTime[i] - arrivalTime[i];
                        waitingTime[i] = completeTime[i] - burstTime[i] - arrivalTime[i];

                        averageWaitingTime += (waitingTime[i] * 1.0) / n;
                        averageTurnaroundTime += (turnaroundTime[i] * 1.0) / n;
                        completedProcess++;
                    }else{
                        remainingBurstTime[i] -= TIME_QUANTUM;
                        systemTime += TIME_QUANTUM;
                    }
                }
            }
        }

        for(int i = 0; i < n; i++){
            System.out.println("--------------------");
            System.out.println("Process: " + (i + 1));
            System.out.println("Complete Time: " + completeTime[i]);
            System.out.println("Turnaround Time: " + turnaroundTime[i]);
            System.out.println("Waiting Time: " + waitingTime[i]);
            System.out.println("--------------------");
        }

        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
        System.out.println("Average Waiting Time: " + averageWaitingTime);
    }
}
