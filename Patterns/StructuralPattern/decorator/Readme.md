# Decorator Design Pattern
> #### ➡ The Decorator Pattern is designed to address this problem by dynamically adding new functionalities to objects without modifying their code. 
> #### ➡ It allows you to wrap objects in layers of functionality, creating flexible and extensible systems.

#
### How the Decorator Pattern Works 🔧
> #### 1. Component Interface
> #### 2. Concrete  Component
> #### 3. Decorator Interface
> #### 4. Concrete  Decorator

#
### Advantage of Decorator Design Pattern
> #### ➡ Flexibility: It allows you to add or remove responsibilities (i.e., behaviors) from objects at runtime. 
> #### ➡ Reusable Code: Decorators are reusable components. You can create a library of decorator classes and apply them to different objects and classes as needed, reducing code duplication.
> #### ➡ Composition over Inheritance: Unlike traditional inheritance, which can lead to a deep and inflexible class hierarchy, the decorator pattern uses composition.
> #### ➡ Dynamic Behavior Modification: Decorators can be applied or removed at runtime, providing dynamic behavior modification for objects. 

#
### Real-life Use Cases of the Decorator Pattern 🌎
> #### 1. Coffee Shop Systems: As shown in this example, the pattern is used to manage dynamic customizations of coffee orders.
> #### 2. GUI Frameworks: Decorators are used to add functionalities like borders, shadows, or scrollbars to graphical components.
> #### 3. Logging Frameworks: The pattern is used to dynamically add logging, authentication, or security layers to a system.
> #### 4. File I/O Streams: Java’s I/O streams use decorators to add functionalities like buffering, compression, or encryption to file streams.