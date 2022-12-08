public class Main {
   public static void main(String[] args) throws InterruptedException {
       MyPrint_ task = new MyPrint_();
       Print print = new Print(task);
       new Thread(print).start();
       /*new Thread(print).start();
       new Thread(()-> {
           try {
               task.action();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }).start();
       new Thread(()-> {
           try {
               task.up();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }).start();*/
    }
}
class MyPrint_ {
    private int j =1;
    public synchronized void action() throws InterruptedException {
        while (j<=5) {
            System.out.print("ABC");
            wait();
        }
        j++;
        notify();
    }
    public synchronized void up() throws InterruptedException {
        j++;
    }
}
class Print implements Runnable{
    MyPrint_ print;
    Print(MyPrint_ print){
        this.print = print;
    }
    public void run(){
        for (int j = 1; j < 6; j++) {
            try {
                print.action();
                j++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

