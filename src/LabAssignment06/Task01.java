package LabAssignment06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Task01 {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(("input.txt")));
            int row = Integer.parseInt(reader.readLine());
            int column = Integer.parseInt(reader.readLine());
            char[] process = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            int[][] max = new int[row][column];
            int[][] allocation = new int[row][column];
            int[][] need = new int[row][column];
            int[][] available = new int[row + 1][column];
            LinkedList<Character> track = new LinkedList<>();

            reader.readLine();

            for (int i = 0; i < row; i++) {
                StringTokenizer st = new StringTokenizer(reader.readLine(), " ");

                int j = 0;
                while (st.hasMoreTokens()) {
                    max[i][j] = Integer.parseInt(st.nextToken());
                    j++;
                }
            }

            reader.readLine();

            for (int i = 0; i < row; i++) {
                StringTokenizer st = new StringTokenizer(reader.readLine(), " ");

                int j = 0;
                while (st.hasMoreTokens()) {
                    allocation[i][j] = Integer.parseInt(st.nextToken());
                    need[i][j] = max[i][j] - allocation[i][j];
                    j++;
                }
            }

            reader.readLine();
            StringTokenizer st = new StringTokenizer(reader.readLine(), " ");

            int counter = 0;
            while (st.hasMoreTokens()) {
                available[0][counter] = Integer.parseInt(st.nextToken());
                counter++;
            }

            counter = 0;
            for (int i = 0; ; i++) {
                i = i % row;
                for (int j = 0; j < column; j++) {
                    if (need[i][j] > available[counter][j]) {
                        break;
                    } else {
                        if (j == (column - 1) && !track.contains(process[i])) {
                            for (int k = 0; k < column; k++) {
                                available[counter + 1][k] = available[counter][k] + allocation[i][k];
                            }

                            track.addLast(process[i]);
                            counter++;
                        }
                    }
                }

                if (track.size() == row) {
                    break;
                }
            }

            System.out.println("Need Matrix :");
            for (int i = 0; i < need.length; i++) {
                for (int j = 0; j < column; j++) {
                    if (j < (column - 1)) {
                        System.out.print(need[i][j] + " ");
                    } else {
                        System.out.println(need[i][j]);
                    }
                }
            }

            System.out.println();
            System.out.println("Safe sequence is :");
            for (int i = 0; i < track.size(); i++) {
                System.out.print(track.get(i) + " ");
            }

            System.out.println();
            System.out.println("Change in available resource matrix :");
            for (int i = 1; i < available.length; i++) {
                System.out.println();
                for (int j = 0; j < column; j++) {
                    System.out.print(available[i][j] + " ");
                }
            }

            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
