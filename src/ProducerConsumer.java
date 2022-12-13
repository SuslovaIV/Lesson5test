import java.util.Random;

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        Box box = new Box();
        Producer producer = new Producer(box);
        Consumer consumer = new Consumer(box);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
class Box{
    private int value=0;
    public synchronized void setValue(){
        Random random=new Random();
        while (value!=0){
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        value = random.nextInt(20);
        System.out.println("P: "+value);
        notify();
    }
    public synchronized void getValue(){
        while (value==0){
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        System.out.println("C: "+value);
        value = 0;
        notify();
    }
}
class Producer implements Runnable{
    Box box;
    Producer(Box box){
        this.box=box;
    }
    public void run(){
       for (int i = 1; i < 6; i++) {
         box.setValue();
       }
    }
}
class Consumer implements Runnable{
    Box box;
    Consumer(Box box){
        this.box=box;
    }
    public void run(){
       for (int i = 1; i < 6; i++) {
            box.getValue();
        }
    }
}