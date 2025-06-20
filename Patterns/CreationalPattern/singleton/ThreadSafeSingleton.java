class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton() {}
    
    public static synchronized ThreadSafeSingleton getInstance() {
        // Check if instance is null
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        // Return the instance (either newly created or existing)
        return instance;
    }
}