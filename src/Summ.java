import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Summ {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int size = 1000000;
        Random random=new Random();
        int[] elements=new int[size];
        for(int i=0;i<size;i++){
            elements[i]=random.nextInt(100);
        }
        MainSum result=new MainSum();
        result.sumElements(elements);
        System.out.println(result.getValue());
    }
}
class MainSum {
   private long sum=0;
   ExecutorService service = Executors.newCachedThreadPool();
    private long task(int[] elements,int i_first,int i_last){
        for(int i=i_first;i<i_last;i++){
            sum+=elements[i];
        }
        return sum;
    }
   public void sumElements(int[] elements) throws ExecutionException, InterruptedException {
       int i_first = 0;
       int i_last = 200000;
       List<Future<Long>> futureList=new ArrayList<>();
       for (int i = 0; i < 1; i++) {
           int finalI_first = i_first;
           int finalI_last = i_last;
           futureList.add(service.submit(() -> task(elements, finalI_first, finalI_last)));
           i_first = i_last;
           i_last = i_last+200000;
       }
       service.shutdown();
       for(Future<Long> future:futureList){
           sum+=future.get();
       }
    }
    public long getValue() {
        return this.sum;
    }
}



