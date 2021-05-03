package LabAssignment03;

import java.util.Arrays;
import java.util.Random;

public class Task03 {
    public static void main(String[] args) {
        int[] arr = new int[99999];
        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(99999);
        }

        int m = arr.length / 2;

        int[] arr01 = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] arr02 = Arrays.copyOfRange(arr, arr.length / 2, arr.length);

        MergeSortThread ms01 = new MergeSortThread(arr01);
        MergeSortThread ms02 = new MergeSortThread(arr02);

        ms01.run();
        ms02.run();

        try {
            ms01.join();
            ms02.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int[] sorted01 = ms01.array;
        int[] sorted02 = ms02.array;

        int[] finalSortedArray = finalMerge(sorted01, sorted02);

        for (int i : finalSortedArray) {
            System.out.println(i);
        }
    }

    public static int[] finalMerge(int[] sortedArray01, int[] sortedArray02) {
        int[] array = new int[sortedArray01.length + sortedArray02.length];
        int i = 0, j = 0, k = 0;

        while (i < sortedArray01.length && j < sortedArray02.length) {
            if (sortedArray01[i] <= sortedArray02[j]) {
                array[k] = sortedArray01[i];
                i++;
            } else {
                array[k] = sortedArray02[j];
                j++;
            }

            k++;
        }

        while (i < sortedArray01.length) {
            array[k] = sortedArray01[i];
            i++;
            k++;
        }

        while (j < sortedArray02.length) {
            array[k] = sortedArray02[j];
            j++;
            k++;
        }

        return array;
    }
}

class MergeSortThread extends Thread {
    int[] array;

    public MergeSortThread(int[] array) {
        this.array = array;
    }

    public void merge(int[] array, int left, int mid, int right) {
        int[] leftArray = new int[mid - left + 1];
        int[] rightArray = new int[right - mid];

        for (int i = 0; i < leftArray.length; i++) {
            leftArray[i] = array[left + i];
        }

        for (int i = 0; i < rightArray.length; i++) {
            rightArray[i] = array[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;

        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }

            k++;
        }

        while (i < leftArray.length) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightArray.length) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public void sort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            sort(array, left, mid);
            sort(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }

    @Override
    public void run() {
        sort(array, 0, array.length - 1);
    }
}
