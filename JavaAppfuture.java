
package javaappfuture;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class JavaAppfuture {     
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
    ArrayList<Future<String>> results = new ArrayList<>();
    ExecutorService exec = Executors.newWorkStealingPool(2);//newFixedThreadPool(5);//здесь создаем 5 потоков
        
        for (int i = 0; i < 12; i++) {//здесь выполняют 12 задач
            results.add(exec.submit(new Callable<String>() {//Callable -это надстойка над интерфейсом Runnable
                @Override
                public String call() throws Exception {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                   return Thread.currentThread().getName();
                }
            }));
        }
        exec.shutdown();
        
        for (Future<String> result : results) {
            System.out.println("main() : " + result.get());
            
        }
    }
    
}
