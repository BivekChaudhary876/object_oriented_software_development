
# 🧱 Tetris — JavaFX Edition

### *Stack it. Clear it. Don't top out.*

[![Java](https://img.shields.io/badge/Java-25+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![JavaFX](https://img.shields.io/badge/JavaFX-UI-0abab5?style=for-the-badge&logo=java&logoColor=white)](https://openjfx.io/)
[![Maven](https://img.shields.io/badge/Build-Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Build Status](https://img.shields.io/badge/Build-Passing-3fb950?style=for-the-badge)]()
[![License](https://img.shields.io/badge/License-Academic-blue?style=for-the-badge)]()

A JavaFX rebuild of the classic Tetris, engineered around a clean layered architecture — input → game mechanics → animation → UI — with real interfaces, abstraction, and encapsulation under the hood.
 
---

## 📚 Table of Contents

- [🎮 Features](#-features)
- [🏗️ Architecture](#️-architecture)
- [🧰 Tech Stack](#-tech-stack)
- [🚀 Getting Started](#-getting-started)
- [📁 Project Structure](#-project-structure)
- [🔀 Git Workflow](#-git-workflow)
- [👥 Team & Roles](#-team--roles)
---

## 🎮 Features

**Application Shell**
- 🎬 Splash screen (group + course info)
- 🏠 Main menu — Play · Config · Scores · Exit
- ⚙️ Live configuration screen (sliders & checkboxes)
- 🏆 Top-10 high score board
  **Gameplay**
- 📐 Classic 10×20 playfield
- 🌊 Smooth, timer-driven gravity — no instant snapping
- ⬅️➡️⬆️⬇️ Full arrow-key move & rotate controls
- 🧹 Multi-row line clear with color integrity
- ⏸️ Pause / resume with `P`
- 🚪 Exit confirmation dialog
---

## 🏗️ Architecture

The game runs on a five-layer pipeline, so input, logic, animation, and rendering never tangle:

```
⌨️  Input Handler  →  🔁  Game Loop  →  🧠  Game Mechanics  →  🎞️  Animation Layer  →  🖼️  UI Layer
                              ↕
                       📦  Data Model (Board, Active Piece, Score)
```

> Keyboard input drives a fixed-timestep game loop, which mutates a shared data model; the animation layer interpolates position changes before the UI layer renders the frame.
 
---

## 🧰 Tech Stack

| Layer | Technology                                   |
|---|----------------------------------------------|
| Language | ☕ Java 25+                                  |
| UI Framework | 🎨 JavaFX                                    |
| Build Tool | 📦 Maven (`org.openjfx:javafx-maven-plugin`) |
| Version Control | 🐙 Git + GitHub (PRs, reviews, tags)         |
 
---

## 🚀 Getting Started

### Prerequisites
- ✅ JDK 25+
- ✅ Maven 3.8+
### Run it

```bash
git clone https://github.com/BivekChaudhary876/object_oriented_software_development.git
cd object_oriented_software_development
mvn clean javafx:run
```

That's it — the splash screen should appear within a few seconds, followed by the main menu. 🎉
 
---
## 📁 Project Structure

<details>
<summary><strong>Click to expand full file tree</strong></summary>

```
tetris_game/
├── src/
│   └── main/
│       ├── java/
│       │   └── au/edu/Griffith/
│       │       ├── AbstractTetromino.java   🧩 Abstract base class for every piece
│       │       ├── Movable.java             🔌 Interface defining movement behaviour
│       │       ├── Configuration.java       ⚙️ Settings screen
│       │       ├── HighScores.java          🏆 High score screen
│       │       ├── Main.java                🚪 Entry point + splash screen
│       │       ├── Tetris.java              🧠 Core game loop & logic
│       │       ├── TetrominoI.java          🟦 I-piece
│       │       ├── TetrominoJ.java          🟧 J-piece
│       │       ├── TetrominoL.java          🟪 L-piece
│       │       ├── TetrominoO.java          🟨 O-piece
│       │       ├── TetrominoS.java          🟩 S-piece
│       │       ├── TetrominoT.java          🟥 T-piece
│       │       └── TetrominoZ.java          🟫 Z-piece
│       └── resources/                       🖼️ Splash image & static assets
├── .gitignore                               🚫 Ignored IDE & build files
├── pom.xml                                  🏷️ Maven project configuration
└── README.md                                📖 Project documentation
```

</details>

---

## 🔀 Git Workflow

```
feature branch → Pull Request → Code Review → Merge into main → Milestone tag
```

1. 🌱 Branch off `main` for each feature (e.g. `feature/high-score-back-button`)
2. 💬 Commit with clear, descriptive messages
3. 🔍 Open a PR — every PR gets reviewed by a teammate before merging
4. ✅ Merge once approved
5. 🏷️ Milestones are marked with annotated tags (e.g. `milestone-1-submission`) on the finalized `main`
---

## 👥 Team & Roles

<div align="center">

| Member | Role |
|---|---|
| 🧑‍💻 **Aries Dave Bantigue** | Programmer |
| ✍️ **Anshumaan Saraf** | Documentation Lead |
| 🗂️ **Bivek Chaudhary** | Project Manager |
| 🏛️ **Dean Busooa** | Architect |
| 🧪 **Liz Mary Tharian** | Tester & Programmer |

</div>

---



## Contributing / Git Workflow

This project follows a feature-branch workflow:

1. Create a feature branch off `main` (e.g. `feature/high-score-back-button`)
2. Commit changes with clear, descriptive messages
3. Open a Pull Request against `main`
4. At least one team member reviews and approves the PR
5. Merge into `main` once approved

Milestone submissions are marked with annotated Git tags (e.g. `milestone-1-submission`) pointing at the finalized `main` branch for that milestone.