public class MyPrint {
    private int i =1;
    synchronized void action() {
         while (i < 5) {
             try {
                 wait();
                 System.out.print("ABC");
             }
             catch (InterruptedException e) {
             }
         }
         i++;
            /*    for (int i = 0; i < 5; i++) {
                    System.out.print("ABC");
                }*/
        notifyAll();

    }

}
