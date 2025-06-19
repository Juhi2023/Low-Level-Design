# Strategy Design Pattern
> #### ➡ The Strategy Design Pattern is a behavioral design pattern that allows you to define a family of algorithms or behaviors, put each of them in a separate class, and make them interchangeable at runtime.
> #### ➡  This pattern is useful when you want to dynamically change the behavior of a class without modifying its code.

#
### How the Strategy Pattern Works 🔧
> ####  1. Context: A class or object known as the Context assigns the task to a strategy object and contains a reference to it.
> #### 2. Strategy Interface: An abstract class or interface known as the Strategy Interface specifies a set of methods that all concrete strategies must implement.
> #### 3. Concrete Strategies: Concrete Strategies are the various implementations of the Strategy Interface
>#### 4. Client: The Client is responsible for selecting and configuring the appropriate strategy and providing it to the Context.

#
### Real-life Use Cases of the Strategy Pattern 🌎
> #### 1. Payment Methods: Process payments via different methods like Credit Card, PayPal, Crypto, etc.
> #### 2. Sorting Algorithms: Use different sorting strategies (e.g., quick sort, merge sort) depending on the situation.
> #### 3. Shipping Costs: Calculate shipping costs based on various factors such as location, delivery speed, and package size.