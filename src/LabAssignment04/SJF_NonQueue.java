package LabAssignment04;

import java.util.Scanner;

public class SJF_NonQueue {
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
        int[] completeFlag = new int[n];
        int systemTime = 0, completedProcess = 0;
        float averageWaitingTime = 0.0f, averageTurnaroundTime = 0.0f;

        System.out.println("ArrivalTime BurstTime");
        for (int i = 0; i < n; i++) {
            arrivalTime[i] = sc.nextInt();
            burstTime[i] = sc.nextInt();
            remainingBurstTime[i] = burstTime[i];
        }

        while (completedProcess != n) {
            int minBurstTime = Integer.MAX_VALUE, selectedProcess = -1;

            for (int i = 0; i < n; i++) {
                if((arrivalTime[i] <= systemTime) && (completeFlag[i] == 0) && (remainingBurstTime[i] < minBurstTime)){
                    minBurstTime = remainingBurstTime[i];
                    selectedProcess = i;
                }
            }

            systemTime++;

            if (selectedProcess != -1) {
                remainingBurstTime[selectedProcess]--;

                if(remainingBurstTime[selectedProcess] == 0){
                    completeFlag[selectedProcess] = 1;
                    completeTime[selectedProcess] = systemTime;
                    waitingTime[selectedProcess] = completeTime[selectedProcess] - burstTime[selectedProcess] - arrivalTime[selectedProcess];
                    turnaroundTime[selectedProcess] = completeTime[selectedProcess] - arrivalTime[selectedProcess];

                    averageWaitingTime += (waitingTime[selectedProcess] * 1.0) / n;
                    averageTurnaroundTime += (turnaroundTime[selectedProcess] * 1.0) / n;
                    completedProcess++;
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
