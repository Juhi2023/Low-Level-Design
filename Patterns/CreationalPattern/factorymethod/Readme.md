# Factory Design Pattern
> #### ➡ It defines an interface for creating objects but allows sublasses to decide which class to initiate.
> #### ➡ The Factory Pattern will allow us to handle the object creation in a centralized manner, so that we don't need to keep repeating the logic of choosing which vehicle to create in multiple places.
#
### Advantages
>#### 1. Centralized Object Creation: 
The VehicleFactory class handles all the logic of creating vehicles. Now, you only need to call the getVehicle() method with the desired vehicle type, and the factory will take care of the rest. This makes the code much cleaner and easier to maintain. ✨


>#### 2. Scalability:

If you want to add a new vehicle type, say Bus, you only need to add the Bus class and update the VehicleFactory class. No changes are needed in the rest of the application. 🔄


>#### 3. Encapsulation:

The client code (in Main.java) no longer needs to know how to create the vehicles. The logic is abstracted away in the VehicleFactory class, which makes the system easier to manage. 🗝️

#
### Real-life Use Cases of the Strategy Pattern 🌎
> #### 1. Creating database connection (e.g., MySQL, PostgreSQL, Oracle)
> #### 2.  Logging: Depending on the logging requirements (e.g., logging to a file, console, or database), a factory can create the correct type of logger, allowing different components of the system to use the logger without knowing its exact implementation. 