
import java.util.Random;
import java.util.Scanner;


public class Test1 {

    public static void main(String[] args){

        int size=10000000;
        Random random=new Random();
        int[] elements=new int[size];
        for(int i=0;i<size;i++){
            elements[i]=random.nextInt(100);
        }

        MyThreadMain mainThread = new MyThreadMain();
        mainThread.setValue(1);
        mainThread.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        mainThread.shutdown();

        System.out.println(mainThread.getValue());
    }
}
class MyThread extends Thread implements Runnable{
    private volatile long sum;
    private volatile int i;
    public void run(){
      while (i<=200000){
         sum = sum+i;
         i++;
      }
    }
    public long getValue() {
        return this.sum;
    }

    public void setValue(int s) {
        this.sum = s;
    }

    private long task(int[] elements,int first,int last){
        long sum=0l;
        for(int i=first;i<=last;i++){
            sum+=elements[i];
        }
        return sum;
    }
}
class MyThreadMain extends Thread {
    private volatile long mainSum=0;
    private volatile boolean running=true;
    private int countThread;
    //private int[] elements=new int[];

    public void run(){
        mainSum = 0;
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        MyThread myThread3 = new MyThread();
        MyThread myThread4 = new MyThread();
        MyThread myThread5 = new MyThread();

      while (running){
            try {
                /*myThread1.setValue(mainSum);
                myThread2.setValue(mainSum);
                myThread3.setValue(mainSum);
                myThread4.setValue(mainSum);
                myThread5.setValue(mainSum);*/
                myThread1.start();
                myThread2.start();
                myThread3.start();
                myThread4.start();
                myThread5.start();
                Thread.sleep(100000);
                System.out.println(myThread1.getValue());
                mainSum = mainSum + myThread1.getValue() + myThread2.getValue() + myThread3.getValue() + myThread4.getValue() + myThread5.getValue();
                running = false;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void shutdown(){
        this.running = false;
    }
    public long getValue() {
        return this.mainSum;
    }
    public void setValue(int s) {
        this.mainSum = s;
    }
}