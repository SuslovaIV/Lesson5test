public class Main {
   public static void main(String[] args) throws InterruptedException {
       MyPrint_ task = new MyPrint_();
       Print print = new Print(task);
       new Thread(print).start();
       new Thread(print).start();
    }
}
class MyPrint_ {
    private int j =1;
    public synchronized void action() {
        while (j<=5) {
            System.out.print("ABC");
            //wait();
            j++;
        }
        notify();
    }
}
class Print implements Runnable{
    MyPrint_ print;
    Print(MyPrint_ print){
        this.print = print;
    }
    public void run(){
        for (int i = 1; i < 6; i++) {
            print.action();
        }
    }
}

