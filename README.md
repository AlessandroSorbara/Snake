# 🐍 Snake Game (Java Swing - MVC)

This is a classic **Snake Game** implemented in **Java** using the **Swing** library and designed with the **MVC (Model-View-Controller)** architectural pattern for better code organization and maintainability.

### 🎮 Features

- Classic snake movement and growth logic;
- Keyboard input support (arrow keys or WASD);
- Game-over detection on collision;
- Scoring system;
- Clean GUI with Java Swing;
- MVC separation for easier scalability and debugging;
- **NEW: Italian brainrot update: added 20 sounds!**

### 🧱 Project Structure (MVC)

- `model/`  
  Contains game logic and data, such as the snake's position, direction, food generation, and collision handling.

- `view/`  
  Handles everything related to the GUI (game board, rendering the snake, food, and score display).

- `controller/`  
  Manages user input (key presses) and updates the model accordingly.

- `SnakeGame.java`  
  Entry point that initializes and connects the model, view, and controller.

### 🚀 How to Run

1. Clone or download this repository;
2. Open the project in your preferred IDE (e.g., IntelliJ, Eclipse, VS Code);
3. Make sure Java JDK 8 or higher is installed;
4. Mark the resources directory as **Sources Root**;
5. Run `SnakeGame.java`.

#### ⚡ Option 2: Run the JAR File (No IDE Needed)

1. Make sure you have Java installed (JDK 8+)
2. Download the latest `Snake.jar` from the [releases](#) section
3. Double-click the JAR file **or** run it from terminal:
   ```bash
   java -jar Snake.jar