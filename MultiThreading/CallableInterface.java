import java.util.*;

//3. callable interface
// n Java, Callable is an interface within the java.util.concurrent package, introduced in Java 5. 
// It is designed to represent a task that can be executed by a thread and, unlike the Runnable interface, it can return a result and throw a checked exception.
//The Callable interface works with the ExecutorService framework rather than directly extending Thread

public class CallableInterface{ 
    public static void main(String[] args) { 
        ExecutorService executor = Executors.newFixedThreadPool(2); 
         
        Callable<String> callable1 = new MyCallable("Task 1"); 
        Callable<String> callable2 = new MyCallable("Task 2"); 
         
        try { 
            // Submit Callable tasks to the executor and get Future objects 
            Future<String> future1 = executor.submit(callable1); 
            Future<String> future2 = executor.submit(callable2); 
             
            // Get results from Future objects 
            System.out.println("Result from first task:"); 
            System.out.println(future1.get()); // Blocks until the task completes 
             
            System.out.println("Result from second task:"); 
            System.out.println(future2.get()); // Blocks until the task completes 
             
        } catch (InterruptedException | ExecutionException e) { 
            System.out.println("Task execution interrupted: " + e.getMessage()); 
        } finally { 
            // Shutdown the executor 
            executor.shutdown(); 
        } 
    } 
}

class MyCallable implements Callable<String> { 
     
    private final String name; 
     
    public MyCallable(String name) { 
        this.name = name; 
    } 
     
    @Override 
    public String call() throws Exception { 
        StringBuilder result = new StringBuilder(); 
        for (int i = 0; i < 5; i++) { 
            result.append("Callable ").append(name).append(" is running: ").append(i).append("n"); 
            Thread.sleep(500); // Pause execution for 500 milliseconds 
        } 
        return result.toString(); 
    } 
} 
 
 
