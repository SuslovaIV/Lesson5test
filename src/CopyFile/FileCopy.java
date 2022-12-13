package CopyFile;
import java.io.*;
import java.util.Scanner;

public class FileCopy {
    public static void main(String[] args) throws IOException {
        System.out.println("Текущий каталог: " + System.getProperty("user.dir"));
        CopyFile copyFile = new CopyFile();
        copyFile.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        copyFile.shutdown();
    }
}
class CopyFile extends Thread{
    private volatile boolean running=true;
    private static volatile double bites;
    private static volatile double size;
    String fileName = "DJI_0548.MP4";
    public synchronized void run(){
        while (running){
            try {
                testCopyBuffer(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        running = false;
    }
    public void testCopyBuffer(String myFile) throws IOException {
        var file = new File(myFile);
        int s = 200;
        bites = 0;
        size = file.length();
        Thread thread = getStatusThread();
        thread.start();

        try(var bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            var bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(myFile + "_копия.MP4"))) {

            var buffer = new byte[s];
            while ((s = bufferedInputStream.read(buffer, 0, buffer.length))  > 0
            && running) {
                bites += s;
                bufferedOutputStream.write(buffer, 0, s);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        thread.interrupt();
        running = false;
    }
    public void shutdown(){
        System.out.println("Копирование файла прервано пользователем");
        this.running = false;
    }
    private Thread getStatusThread() {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(2000);
                    System.out.println("Процент выполнения - " + (bites / size) * 100);
                } catch (InterruptedException ex) {
                    System.out.println("Процент выполнения - " + (bites / size) * 100);
                    Thread.currentThread().interrupt();
                }
            }
        });
        return thread;
    }
}