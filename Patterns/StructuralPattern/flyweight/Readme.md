# Flyweight Design Pattern
> #### ➡ The Flyweight design pattern is a structural pattern that optimizes memory usage by sharing a common state among multiple objects.
> #### ➡ Instead of creating a new object for each instance, the Flyweight pattern reuses existing ones wherever possible, sharing common parts between objects.
> #### ➡ Shared vs. Unique Data: Objects are split into shared (intrinsic) data and unique (extrinsic) data. The shared data is stored in a central place and reused, while the unique data is kept separately.
#
### How the Flyweight Pattern Works 🔧
> #### 1. Intrinsic State: Shared properties stored in flyweight objects
> #### 2. Extrinsic State: Unique properties passed as parameters
> #### 3. Flyweight Factory: Manages flyweight object creation and sharing

#
### When to use Flyweight Design Pattern
> #### ➡ When you need to create a large number of similar objects.
> #### ➡ When memory consumption is a concern.
> #### ➡ When performance optimization is needed.

#
### Real-life Use Cases of the Flyweight Pattern 🌎
> #### 1. Text Editors: Sharing character formatting data
> #### 2. Game Development: Terrain tiles, particles, and visual effects
> #### 3. Graphics Applications: Sharing texture and material data
> #### 4. Web Browsers: Caching and reusing rendered elements