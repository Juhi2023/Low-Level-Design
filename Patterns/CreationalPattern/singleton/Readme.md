# Singleton Design Pattern
> #### ➡ Singleton pattern is a design pattern which restricts a class to instantiate its multiple objects. It is nothing but a way of defining a class. Class is defined in such a way that only one instance of the class is created in the complete execution of a program or project. 
#


### How the Singleton Pattern Works 🔧
> #### 1. Create private constructor
> #### 2. create static method to get instance

#
### When to use Singleton Design Pattern
> #### ➡ When you need to create a single instance only
#
### Real-life Use Cases of the Singleton Pattern 🌎
> #### 1. Logging Systems:  If different parts of the system are creating multiple instances of the Logger, it leads to inefficient resource usage. If you are logging to a file, for example, each logger might try to access and write to the file at the same time, leading to potential conflicts or overhead. 
> #### 2.  Database Connections: We often need a single database connection throughout the application to avoid multiple connections that could lead to inefficiency or resource exhaustion. 💻
> #### 3. Configuration Settings:Imagine having configuration settings for your application that need to be consistent across the app. Using a Singleton pattern ensures that only one instance of the settings object exists. ⚙️