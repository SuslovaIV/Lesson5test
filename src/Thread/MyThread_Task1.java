package Thread;
public class MyThread_Task1 {
    public static void main(String[] args) {
        TestThread thread=new TestThread();
        Thread1 thread1 = new Thread1(thread);
        Thread2 thread2 = new Thread2(thread);
        new Thread(thread1).start();
        new Thread(thread2).start();
    }
}
class TestThread{
    private boolean running1 = false;
    private boolean running2 = false;
    public synchronized void action1() {
        while (running1) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        running1 = true;
        running2 = false;
        System.out.print("ABC");
        notify();
    }
    public synchronized void action2() {
        while (running2) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        running2 = true;
        running1 = false;
        System.out.print("ABC");
        notify();
    }
}
class Thread1 implements Runnable{
    TestThread thread;
    Thread1(TestThread thread){
        this.thread=thread;
    }
    public void run(){
        for (int i = 1; i < 6; i++) {
            thread.action1();
        }
    }
}
class Thread2 implements Runnable{
    TestThread thread;
    Thread2(TestThread thread){
        this.thread=thread;
    }
    public void run(){
        for (int i = 1; i < 6; i++) {
            thread.action2();
        }
    }
}
