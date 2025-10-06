import java.util.*;
//Process
//1. exceution of program
//2. it requires seperate memory space.
//3. fully independent resource allocation
//4. failure doesnt affect others.
//5. Expensive context switching

//Thread
//1. Segment of process
//2. it uses shared memory within process.
//3. shared resources with other threads
//4. faliure can crash the entire program.
//5. Inexpensive context switching

//Threre are two ways to create threats
//1. Thread class
//2. Runablle Interface



//1. Extending the Thread Class 

// run() contains the code that will execute in the new thread.
// You override it when subclassing Thread or implementing Runnable.
//The start() method begins thread execution and calls the run() method, while the run() method simply contains the code to be executed. 

public class ThreadClass { 
    public static void main(String[] args) { 
        MyThread thread1 = new MyThread(); 
        MyThread thread2 = new MyThread(); 
         
        thread1.start(); // Start the first thread 
        thread2.start(); // Start the second thread 
    } 
}

class MyThread extends Thread { 
    @Override 
    public void run() { 
        for (int i = 0; i < 5; i++) { 
            System.out.println(Thread.currentThread().toString() + " is running: " + i); 
            try { 
                Thread.sleep(500); // Pause execution for 500 milliseconds 
            } catch (InterruptedException e) { 
                System.out.println("Thread interrupted"); 
            } 
        } 
    } 
} 
 


// Advantages: 
// Simpler to implement for beginners 
// Direct access to Thread methods 

// Disadvantages: 
// Limits inheritance (Java doesn't support multiple inheritance) 
// Each task requires a new thread instance 




// Questions

// 1. Can we call the start() method twice on the same Thread object? 
// Answer: No, calling start() twice on the same Thread object will throw an IllegalThreadStateException. A thread that has completed execution cannot be restarted. ⚠️ 

// 2. What is thread safety and how can it be achieved? 
// Answer: Thread safety refers to code that functions correctly during simultaneous execution by multiple threads. 
// It can be achieved through synchronization, immutable objects, concurrent collections(refers to a set of classes that allow multiple threads to access and modify a collection concurrently, without the need for explicit synchronization.), atomic variables, and thread-local variables. 🔒 

//3. difference between sleep() and wait()
// sleep() causes the current thread to pause for a specified time without releasing locks. 
// sleep() does NOT release any locks, meaning other threads can't access synchronized resources during sleep. 
// wait() causes the current thread  to wait until another thread invokes notify() or notifyAll() on the same object, and it releases the lock on the object. 

//Implementation of wait()

class SharedResource { 
    synchronized void waitExample() { 
        System.out.println(Thread.currentThread().getName() + " is waiting..."); 
        try { 
            wait(); // Releases the lock and waits 
        } catch (InterruptedException e) { 
            e.printStackTrace(); 
        } 
        System.out.println(Thread.currentThread().getName() + " resumed after notify."); 
    } 
 
 
    synchronized void notifyExample() { 
        System.out.println("Notifying a waiting thread..."); 
        notify(); // Wakes up one waiting thread 
    } 
} 
 
 
public class WaitNotifyExample { 
    public static void main(String[] args) { 
        SharedResource shared = new SharedResource(); 
 
        // Thread 1 (Waits) 
        Thread t1 = new Thread(() -> shared.waitExample(), "Thread-1"); 
         
        // Thread 2 (Notifies after 2 seconds) 
        Thread t2 = new Thread(() -> { 
            try { 
                Thread.sleep(2000); // Ensure Thread-1 goes to wait state 
                shared.notifyExample(); 
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            } 
        }, "Thread-2"); 
 
 
        t1.start(); 
        t2.start(); 
    } 
}


// Simulation Explanation: 
// 1. Thread-1 calls wait() and enters the waiting state, releasing the lock. 
// 2. Thread-2 starts and sleeps for 2 seconds to simulate delay. 
// 3. After 2 seconds, Thread-2 calls notify(), waking up Thread-1. 
// 4. Thread-1 resumes execution after wait() and prints "Thread-1 resumed after notify." 


// What Happens to the Resource a Thread Was Holding when the wait() method is called?  
// 1. When a thread calls wait(), it releases the lock on the synchronized object it was holding. 
// 2. Other threads can now acquire the lock and continue execution. 
// 3. The waiting thread remains idle until another thread calls notify() or notifyAll(). 

// Scenario: Restaurant Order Processing 🍔 
// Thread A (Waiter) takes the customer's order and waits for the chef to prepare the food. 
// Thread B (Chef) prepares the food and notifies the waiter when it's ready. 