# Bridge Design Pattern
> #### ➡ It separates abstraction (Shape) from its implementation (Rendering Method) so that the two can evolve independently.

#
### How the Bridge Pattern Works 🔧
> #### 1. Abstraction: Define the high-level interface for shapes.
> #### 2. Implementor: Define the interface for rendering methods.
> #### 3. Concrete Implementor: Provide specific implementations of the rendering methods (e.g., Raster, Vector).
> #### 4. Refined Abstraction: Implement shapes by using rendering methods through composition.
#
### Real-life Use Cases of the Bridge Pattern 🌎
> #### 1.  Graphics Libraries: Libraries like OpenGL use a bridge-like pattern to separate shapes from rendering logic.
> #### 2. UI Frameworks: UI frameworks often decouple widgets from their look-and-feel using the Bridge Pattern.
