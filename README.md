# 🧱 Tetris — JavaFX Edition

### *Stack it. Clear it. Don't top out.*

[![Java](https://img.shields.io/badge/Java-25+-ED8B00?style=for-the-badge\&logo=openjdk\&logoColor=white)](https://openjdk.org/)
[![JavaFX](https://img.shields.io/badge/JavaFX-UI-0abab5?style=for-the-badge\&logo=java\&logoColor=white)](https://openjfx.io/)
[![Maven](https://img.shields.io/badge/Build-Maven-C71A36?style=for-the-badge\&logo=apachemaven\&logoColor=white)](https://maven.apache.org/)
[![Build Status](https://img.shields.io/badge/Build-Passing-3fb950?style=for-the-badge)]()
[![License](https://img.shields.io/badge/License-Academic-blue?style=for-the-badge)]()

A JavaFX implementation of the classic Tetris game, developed using object-oriented design principles. The project demonstrates abstraction, encapsulation, interfaces, inheritance, Java records, and modular game functionality.

---

## 📚 Table of Contents

* [🎮 Features](#-features)
* [🏗️ Architecture](#️-architecture)
* [🧰 Tech Stack](#-tech-stack)
* [🚀 Getting Started](#-getting-started)
* [📁 Project Structure](#-project-structure)
* [🔀 Git Workflow](#-git-workflow)
* [🏷️ Versioning and Tags](#️-versioning-and-tags)
* [👥 Team & Roles](#-team--roles)

---

## 🎮 Features

### Application

* 🎬 Splash screen with group and course information
* 🏠 Main menu with Play, Configuration, High Scores, and Exit options
* ⚙️ Configuration screen for game settings
* 🏆 High-score screen for viewing recorded scores
* 🚪 Exit confirmation dialog

### Gameplay

* 📐 Classic Tetris playfield
* 🧩 Seven Tetromino types: I, J, L, O, S, T, and Z
* ⬅️➡️⬇️ Piece movement controls
* 🔄 Piece rotation
* 🌊 Timer-driven automatic piece movement
* 🧹 Line clearing
* 🎯 Score tracking
* ⏸️ Pause and resume functionality using `P`
* 🎮 Game-over handling
* 🔁 Replay functionality

### Object-Oriented Design

* 🧩 `AbstractTetromino` provides a common abstraction for Tetromino pieces
* 🔌 `Movable` defines movement behaviour
* 📦 Java `record` types are used for score-entry data
* 🔒 Encapsulation is used to protect game state
* 🏛️ Inheritance is used for the different Tetromino implementations

---

# 🧱 Tetris — JavaFX Edition

### *Stack it. Clear it. Don't top out.*

[![Java](https://img.shields.io/badge/Java-25+-ED8B00?style=for-the-badge\&logo=openjdk\&logoColor=white)](https://openjdk.org/)
[![JavaFX](https://img.shields.io/badge/JavaFX-UI-0abab5?style=for-the-badge\&logo=java\&logoColor=white)](https://openjfx.io/)
[![Maven](https://img.shields.io/badge/Build-Maven-C71A36?style=for-the-badge\&logo=apachemaven\&logoColor=white)](https://maven.apache.org/)
[![Build Status](https://img.shields.io/badge/Build-Passing-3fb950?style=for-the-badge)]()
[![License](https://img.shields.io/badge/License-Academic-blue?style=for-the-badge)]()

A JavaFX implementation of the classic Tetris game, developed using object-oriented design principles. The project demonstrates abstraction, encapsulation, interfaces, inheritance, Java records, and modular game functionality.

---

## 📚 Table of Contents

* [🎮 Features](#-features)
* [🏗️ Architecture](#️-architecture)
* [🧰 Tech Stack](#-tech-stack)
* [🚀 Getting Started](#-getting-started)
* [📁 Project Structure](#-project-structure)
* [🔀 Git Workflow](#-git-workflow)
* [🏷️ Versioning and Tags](#️-versioning-and-tags)
* [👥 Team & Roles](#-team--roles)

---

## 🎮 Features

### Application

* 🎬 Splash screen with group and course information
* 🏠 Main menu with Play, Configuration, High Scores, and Exit options
* ⚙️ Configuration screen for game settings
* 🏆 High-score screen for viewing recorded scores
* 🚪 Exit confirmation dialog

### Gameplay

* 📐 Classic Tetris playfield
* 🧩 Seven Tetromino types: I, J, L, O, S, T, and Z
* ⬅️➡️⬇️ Piece movement controls
* 🔄 Piece rotation
* 🌊 Timer-driven automatic piece movement
* 🧹 Line clearing
* 🎯 Score tracking
* ⏸️ Pause and resume functionality using `P`
* 🎮 Game-over handling
* 🔁 Replay functionality

### Object-Oriented Design

* 🧩 `AbstractTetromino` provides a common abstraction for Tetromino pieces
* 🔌 `Movable` defines movement behaviour
* 📦 Java `record` types are used for score-entry data
* 🔒 Encapsulation is used to protect game state
* 🏛️ Inheritance is used for the different Tetromino implementations

---



## 🧰 Tech Stack

| Component               | Technology                               |
| ----------------------- | ---------------------------------------- |
| Programming Language    | ☕ Java 25+                               |
| User Interface          | 🎨 JavaFX                                |
| Build Tool              | 📦 Maven                                 |
| Version Control         | 🐙 Git                                   |
| Repository Hosting      | 🐙 GitHub                                |
| Development Environment | 💻 IntelliJ IDEA                         |
| Testing/Review          | 🔍 GitHub Pull Requests and Code Reviews |

---

## 🚀 Getting Started

### Prerequisites

Make sure the following are installed:

* ☕ JDK 25 or later
* 📦 Maven 3.8 or later
* 💻 IntelliJ IDEA or another Java IDE
* 🐙 Git

### Clone the Repository

```bash
git clone https://github.com/BivekChaudhary876/tetris_game.git
```

Navigate into the project:

```bash
cd tetris_game
```

### Run the Application

Using Maven:

```bash
mvn clean javafx:run
```

The application should start with the splash screen and then display the main menu.

### Run from IntelliJ IDEA

1. Open the `tetris_game` folder in IntelliJ IDEA.
2. Allow IntelliJ to import the Maven project.
3. Allow Maven dependencies to finish downloading.
4. Locate `Main.java`.
5. Run the application using the IntelliJ Run configuration.

---

## 📁 Project Structure

The repository contains the Java source code, resources, Maven configuration, documentation, and Git configuration.

<details>
<summary><strong>Click to expand full file tree</strong></summary>

```text
tetris_game/
├── src/
│   └── main/
│       ├── java/
│       │   └── au/edu/Griffith/
│       │       ├── AbstractTetromino.java   🧩 Abstract base class for Tetromino pieces
│       │       ├── Movable.java             🔌 Interface defining movement behaviour
│       │       ├── Configuration.java       ⚙️ Configuration screen
│       │       ├── HighScores.java          🏆 High-score screen
│       │       ├── Main.java                🚪 Application entry point and splash screen
│       │       ├── Tetris.java              🧠 Core game logic and game loop
│       │       ├── TetrominoI.java          🟦 I-piece
│       │       ├── TetrominoJ.java          🟧 J-piece
│       │       ├── TetrominoL.java          🟪 L-piece
│       │       ├── TetrominoO.java          🟨 O-piece
│       │       ├── TetrominoS.java          🟩 S-piece
│       │       ├── TetrominoT.java          🟥 T-piece
│       │       └── TetrominoZ.java          🟫 Z-piece
│       └── resources/                       🖼️ Images and static resources
├── .gitignore                               🚫 Ignored IDE and build files
├── pom.xml                                  📦 Maven project configuration
└── README.md                                📖 Project documentation
```

</details>

### Ignored Files

The following generated or IDE-specific files are intentionally excluded from the repository:

```text
.idea/
target/
*.iml
*.class
```

These files are generated locally by IntelliJ IDEA or Maven and are not required to build the project from source.

---

## 🔀 Git Workflow

The project follows a feature-branch and Pull Request workflow.

```text
Create Feature Branch
        │
        ▼
   Develop Feature
        │
        ▼
      Commit
        │
        ▼
    Push Branch
        │
        ▼
  Create Pull Request
        │
        ▼
   Code Review
        │
        ▼
   PR Approval
        │
        ▼
   Merge into main
```

### Development Workflow

1. Update the local `main` branch.

```bash
git checkout main
git pull origin main
```

2. Create a feature branch.

```bash
git checkout -b feature/feature-name
```

3. Make and test the required changes.

4. Commit the changes using a clear commit message.

```bash
git add .
git commit -m "Add feature description"
```

5. Push the feature branch.

```bash
git push -u origin feature/feature-name
```

6. Create a Pull Request from the feature branch into `main`.

7. A team member reviews the Pull Request.

8. Address any requested changes.

9. Merge the approved Pull Request into `main`.

---

## 🏷️ Versioning and Tags

Git tags are used to identify important project milestones and finalized versions.

The project can use annotated tags such as:

```text
milestone-1-submission
milestone-2-submission
final-submission
```

Tags should be created on the finalized `main` branch after the relevant milestone has been completed and reviewed.

Example:

```bash
git checkout main
git pull origin main
git tag -a milestone-1-submission -m "Milestone 1 submission"
git push origin milestone-1-submission
```

Tags provide a stable reference to the exact version of the project submitted for each milestone.

---

## 👥 Team & Roles

| Team Member                   | Role                |
| ----------------------------- | ------------------- |
| 🧑‍💻 **Aries Dave Bantigue** | Programmer          |
| ✍️ **Anshumaan Saraf**        | Documentation Lead  |
| 🗂️ **Bivek Chaudhary**       | Project Manager     |
| 🏛️ **Dean Busooa**           | Architect           |
| 🧪 **Liz Mary Tharian**       | Tester & Programmer |

---

## 📌 Project Information

**Project:** Tetris — JavaFX Edition
**Repository:** `tetris_game`
**Language:** Java
**Framework:** JavaFX
**Build System:** Maven
**Version Control:** Git + GitHub

---

## 📖 Academic Project

This project was developed as part of the **Object Oriented Software Development** course and demonstrates the application of object-oriented programming concepts and collaborative software development practices.

---


## 🧰 Tech Stack

| Component               | Technology                               |
| ----------------------- | ---------------------------------------- |
| Programming Language    | ☕ Java 25+                               |
| User Interface          | 🎨 JavaFX                                |
| Build Tool              | 📦 Maven                                 |
| Version Control         | 🐙 Git                                   |
| Repository Hosting      | 🐙 GitHub                                |
| Development Environment | 💻 IntelliJ IDEA                         |
| Testing/Review          | 🔍 GitHub Pull Requests and Code Reviews |

---

## 🚀 Getting Started

### Prerequisites

Make sure the following are installed:

* ☕ JDK 25 or later
* 📦 Maven 3.8 or later
* 💻 IntelliJ IDEA or another Java IDE
* 🐙 Git

### Clone the Repository

```bash
git clone https://github.com/BivekChaudhary876/tetris_game.git
```

Navigate into the project:

```bash
cd tetris_game
```

### Run the Application

Using Maven:

```bash
mvn clean javafx:run
```

The application should start with the splash screen and then display the main menu.

### Run from IntelliJ IDEA

1. Open the `tetris_game` folder in IntelliJ IDEA.
2. Allow IntelliJ to import the Maven project.
3. Allow Maven dependencies to finish downloading.
4. Locate `Main.java`.
5. Run the application using the IntelliJ Run configuration.

---

## 📁 Project Structure

The repository contains the Java source code, resources, Maven configuration, documentation, and Git configuration.

<details>
<summary><strong>Click to expand full file tree</strong></summary>

```text
tetris_game/
├── src/
│   └── main/
│       ├── java/
│       │   └── au/edu/Griffith/
│       │       ├── AbstractTetromino.java   🧩 Abstract base class for Tetromino pieces
│       │       ├── Movable.java             🔌 Interface defining movement behaviour
│       │       ├── Configuration.java       ⚙️ Configuration screen
│       │       ├── HighScores.java          🏆 High-score screen
│       │       ├── Main.java                🚪 Application entry point and splash screen
│       │       ├── Tetris.java              🧠 Core game logic and game loop
│       │       ├── TetrominoI.java          🟦 I-piece
│       │       ├── TetrominoJ.java          🟧 J-piece
│       │       ├── TetrominoL.java          🟪 L-piece
│       │       ├── TetrominoO.java          🟨 O-piece
│       │       ├── TetrominoS.java          🟩 S-piece
│       │       ├── TetrominoT.java          🟥 T-piece
│       │       └── TetrominoZ.java          🟫 Z-piece
│       └── resources/                       🖼️ Images and static resources
├── .gitignore                               🚫 Ignored IDE and build files
├── pom.xml                                  📦 Maven project configuration
└── README.md                                📖 Project documentation
```

</details>

### Ignored Files

The following generated or IDE-specific files are intentionally excluded from the repository:

```text
.idea/
target/
*.iml
*.class
```

These files are generated locally by IntelliJ IDEA or Maven and are not required to build the project from source.

---

## 🔀 Git Workflow

The project follows a feature-branch and Pull Request workflow.

```text
Create Feature Branch
        │
        ▼
   Develop Feature
        │
        ▼
      Commit
        │
        ▼
    Push Branch
        │
        ▼
  Create Pull Request
        │
        ▼
   Code Review
        │
        ▼
   PR Approval
        │
        ▼
   Merge into main
```

### Development Workflow

1. Update the local `main` branch.

```bash
git checkout main
git pull origin main
```

2. Create a feature branch.

```bash
git checkout -b feature/feature-name
```

3. Make and test the required changes.

4. Commit the changes using a clear commit message.

```bash
git add .
git commit -m "Add feature description"
```

5. Push the feature branch.

```bash
git push -u origin feature/feature-name
```

6. Create a Pull Request from the feature branch into `main`.

7. A team member reviews the Pull Request.

8. Address any requested changes.

9. Merge the approved Pull Request into `main`.

---

## 🏷️ Versioning and Tags

Git tags are used to identify important project milestones and finalized versions.

The project can use annotated tags such as:

```text
milestone-1-submission
milestone-2-submission
final-submission
```

Tags should be created on the finalized `main` branch after the relevant milestone has been completed and reviewed.

Example:

```bash
git checkout main
git pull origin main
git tag -a milestone-1-submission -m "Milestone 1 submission"
git push origin milestone-1-submission
```

Tags provide a stable reference to the exact version of the project submitted for each milestone.

---

## 👥 Team & Roles

| Team Member                   | Role                |
| ----------------------------- | ------------------- |
| 🧑‍💻 **Aries Dave Bantigue** | Programmer          |
| ✍️ **Anshumaan Saraf**        | Documentation Lead  |
| 🗂️ **Bivek Chaudhary**       | Project Manager     |
| 🏛️ **Dean Busooa**           | Architect           |
| 🧪 **Liz Mary Tharian**       | Tester & Programmer |

---

## 📌 Project Information

**Project:** Tetris — JavaFX Edition
**Repository:** `tetris_game`
**Language:** Java
**Framework:** JavaFX
**Build System:** Maven
**Version Control:** Git + GitHub

---

## 📖 Academic Project

This project was developed as part of the **Object Oriented Software Development** course and demonstrates the application of object-oriented programming concepts and collaborative software development practices.

---
