//Thread pools are a managed collection of reusable threads designed to execute tasks concurrently. They offer significant advantages in resource management, performance, and application stability. 🛠️ 

import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 
 
public class ThreadPoolExample { 
    public static void main(String[] args) { 
        // Create a fixed thread pool with 3 threads 
        ExecutorService executorService = Executors.newFixedThreadPool(3); 
 
        // Submit 5 tasks to the thread pool 
        for (int i = 1; i <= 5; i++) { 
            executorService.submit(new WorkerThread(i)); 
        } 
 
        // Shutdown the executor service 
        executorService.shutdown(); 
    } 
}

 
class WorkerThread implements Runnable { 
    private final int taskId; 
 
    public WorkerThread(int taskId) { 
        this.taskId = taskId; 
    } 
 
    @Override 
    public void run() { 
        System.out.println(Thread.currentThread().getName() + " is processing task: " + taskId); 
        try { 
            Thread.sleep(2000); // Simulate task execution time 
        } catch (InterruptedException e) { 
            System.out.println("Task interrupted: " + e.getMessage()); 
        } 
        System.out.println(Thread.currentThread().getName() + " finished task: " + taskId); 
    } 
} 
 


// Benefits of Thread Pools 🌟 
// 1. Resource Management: Limit the number of threads to prevent system overload. 🛑 
// 2. Performance Improvement: Reuse existing threads instead of creating new ones. ⚡ 
// 3. Predictability: Control thread creation and scheduling for better application behavior. 📊 
// 4. Task Management: Queuing, scheduling, and monitoring tasks becomes streamlined. 📋 
