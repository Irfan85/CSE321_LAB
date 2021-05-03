package LabAssignment04;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Task01 {
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
        int[] discoveredFlag = new int[n];
        int systemTime = 0, completedProcess = 0;
        float averageWaitingTime = 0.0f, averageTurnaroundTime = 0.0f;

        System.out.println("Enter process information in the following format:");
        System.out.println("ArrivalTime BurstTime");
        for (int i = 0; i < n; i++) {
            arrivalTime[i] = sc.nextInt();
            burstTime[i] = sc.nextInt();
            remainingBurstTime[i] = burstTime[i];
        }

        LinkedList<Integer> queue = new LinkedList<>();

        while (completedProcess != n) {
            int selectedProcess = -1;

            for (int i = 0; i < n; i++) {
                if((arrivalTime[i] <= systemTime) && (discoveredFlag[i] == 0)){
                   queue.add(i);
                   discoveredFlag[i] = 1;
                }
            }

            queue = arrangeQueue(queue, remainingBurstTime);

            systemTime++;

            if (queue.size() > 0) {
                selectedProcess = queue.get(0);
                remainingBurstTime[selectedProcess]--;

                if(remainingBurstTime[selectedProcess] == 0){
                    completeTime[selectedProcess] = systemTime;
                    waitingTime[selectedProcess] = completeTime[selectedProcess] - burstTime[selectedProcess] - arrivalTime[selectedProcess];
                    turnaroundTime[selectedProcess] = completeTime[selectedProcess] - arrivalTime[selectedProcess];

                    averageWaitingTime += (waitingTime[selectedProcess] * 1.0) / n;
                    averageTurnaroundTime += (turnaroundTime[selectedProcess] * 1.0) / n;
                    completedProcess++;

                    queue.remove(0);
                }

                queue = arrangeQueue(queue, remainingBurstTime);
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

    private static LinkedList<Integer> arrangeQueue(LinkedList<Integer> queue, int[] remainingBurstTime){
        queue.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(remainingBurstTime[o1] < remainingBurstTime[o2]){
                    return -1;
                }else if(remainingBurstTime[o1] > remainingBurstTime[o2]){
                    return 1;
                }else{
                    return 0;
                }
            }
        });

        return queue;
    }
}
