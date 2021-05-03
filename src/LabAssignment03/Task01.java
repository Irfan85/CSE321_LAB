package LabAssignment03;

public class Task01 {
    public static void main(String[] args) {
        MyThread thread01 = new MyThread(1, 30);
        MyThread thread02 = new MyThread(11, 20);
        thread01.start();
        thread02.start();
    }
}

class MyThread extends Thread {
    private int start, end;

    public MyThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        int jump = start + 9;
        for (int i = start; i <= end; i++) {
            System.out.println(i);

            if (i == jump && i != end) {
                i = jump + 10;
                jump = i + 10;

                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}