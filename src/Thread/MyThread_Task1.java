package Thread;
public class MyThread_Task1 {
    public static void main(String[] args) {
        TestThread thread=new TestThread();
        Thread1 thread1 = new Thread1(thread);
        Thread2 thread2 = new Thread2(thread);
        Thread3 thread3 = new Thread3(thread);
        new Thread(thread1).start();
        new Thread(thread2).start();
        new Thread(thread3).start();
    }
}
class TestThread{
    private int sleep = 1;
    public synchronized void action1() {
        while (sleep!=1) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        sleep=2;
        System.out.print("A");
        notifyAll();
    }
    public synchronized void action2() {
        while (sleep!=2) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        sleep=3;
        System.out.print("B");
        notifyAll();
    }
    public synchronized void action3() {
        while (sleep!=3) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        sleep=1;
        System.out.print("C");
        notifyAll();
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
class Thread3 implements Runnable{
    TestThread thread;
    Thread3(TestThread thread){
        this.thread=thread;
    }
    public void run(){
        for (int i = 1; i < 6; i++) {
            thread.action3();
        }
    }
}
