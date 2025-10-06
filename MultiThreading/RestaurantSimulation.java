public class RestaurantSimulation { 
    public static void main(String[] args) { 
        Object lock = new Object(); 
        Thread waiter = new WaiterThread(lock); 
        Thread chef = new ChefThread(lock); 
 
        waiter.start(); 
        chef.start(); 
    } 
}


class ChefThread extends Thread { 
    private final Object lock; 
 
    public ChefThread(Object lock) { 
        this.lock = lock; 
    } 
 
    @Override 
    public void run() { 
        try { 
            Thread.sleep(2000); // Simulate food preparation time 
            synchronized (lock) { 
                System.out.println("Chef: Food is ready! Notifying the waiter. 🔔"); 
                lock.notify(); // Wake up the waiting waiter thread 
            } 
        } catch (InterruptedException e) { 
            Thread.currentThread().interrupt(); 
        } 
    } 
}

class WaiterThread extends Thread { 
    private final Object lock; 
 
    public WaiterThread(Object lock) { 
        this.lock = lock; 
    } 
 
    @Override 
    public void run() { 
        synchronized (lock) { 
            try { 
                System.out.println("Waiter: Waiting for the food to be ready... ⏳"); 
                lock.wait(); // Waiter enters WAITING state 
                System.out.println("Waiter: Food is ready! Delivering to the customer. 🍽️"); 
            } catch (InterruptedException e) { 
                Thread.currentThread().interrupt(); 
            } 
        } 
    } 
}

// Sequence with synchronized
// 1. WaiterThread enters synchronized(lock) → owns lock.
// 2. Calls lock.wait() → releases lock and waits.
// 3. ChefThread later enters synchronized(lock), prepares food, calls lock.notify().
// 4. Waiter is awakened, reacquires the lock before proceeding.

// Prevents race conditions
// --> Without synchronized, it’s possible that:
//     1. The ChefThread could call notify() before the WaiterThread actually starts waiting.
//     2. Or the shared state (in this case, the readiness of the food) could be accessed in an inconsistent way.

// -->By synchronizing on the same lock object:
//     Only one thread can enter a synchronized(lock) block at a time.
//     This ensures that the wait-notify mechanism works reliably.