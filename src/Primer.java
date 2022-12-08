public class Primer {

    public static void main(String[] args) {
        Store1 store=new Store1();
        Producer1 producer = new Producer1(store);
        new Thread(producer).start();
        //new Thread(consumer).start();
    }
}
// Класс Магазин, хранящий произведенные товары
class Store1{
    private int i=0;
    public synchronized void get() {
        while (i<5) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        i++;
        System.out.println("ABC");
        System.out.println("Товаров на складе: " + i);
        notify();
    }
    public synchronized void put() {
        while (i>=3) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        i--;
        System.out.println("Производитель добавил 1 товар");
        System.out.println("Товаров на складе: " + i);
        notify();
    }
}


class Producer1 implements Runnable{
    Store1 store;
    Producer1(Store1 store){
        this.store=store;
    }
    public void run(){
        for (int i = 1; i < 6; i++) {
            store.get();
        }
    }
}
class Consumer1 implements Runnable{

    Store1 store;
    Consumer1(Store1 store){
        this.store=store;
    }
    public void run(){
        for (int i = 1; i < 6; i++) {
            store.get();
        }
    }
}

