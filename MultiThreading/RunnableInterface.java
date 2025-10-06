import java.util.*;

//2. Runnable Interface:  It represents a blueprint for tasks that can be executed by a thread.
public class RunnableInterface { 
    public static void main(String[] args) { 
        MyRunnable runnable = new MyRunnable();
        Thread thread1 = new Thread(runnable); 
        Thread thread2 = new Thread(runnable);
         
        thread1.start(); // Start the first thread 
        thread2.start(); // Start the second thread 
    } 
}

class MyRunnable implements Runnable { 
    @Override 
    public void run() { 
        for (int i = 0; i < 5; i++) { 
            System.out.println("Runnable " + Thread.currentThread().getId() + " is running: " + i); 
            try { 
                Thread.sleep(500); // Pause execution for 500 milliseconds 
            } catch (InterruptedException e) { 
                System.out.println("Thread interrupted"); 
            } 
        } 
    } 
} 
 

// Advantages: Benefits of Thread Pools 🌟 
Resource Management: Limit the number of threads to prevent system overload. 🛑 
Performance Improvement: Reuse existing threads instead of creating new ones. ⚡ 
Predictability: Control thread creation and scheduling for better application behavior. 📊 
Task Management: Queuing, scheduling, and monitoring tasks becomes streamlined. 📋 
 ‍
// Better object-oriented design 
// Your class can still extend another class (since Java allows only one superclass).	 **
// Same Runnable instance can be shared across multiple threads 
// The Runnable object represents the task; the Thread object executes it. **
// More flexible for executor frameworks 

// Disadvantages: 
// Slightly more code to write 
// Indirect access to Thread methods 



