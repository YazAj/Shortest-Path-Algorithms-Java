# Shortest Path Algorithms — Dijkstra vs A*

A Java project that implements and compares two shortest-path search algorithms: **Dijkstra's Algorithm** and **A\***.

The program builds a small weighted directed graph, searches for the shortest path from node **A** to node **D**, and compares both algorithms using:

- Shortest distance
- Number of visited nodes
- Execution time

## Algorithms

### Dijkstra's Algorithm

Dijkstra's algorithm finds the shortest path in a weighted graph with non-negative edge weights. It expands nodes according to the currently known shortest distance from the starting node.

### A* Search

A* combines the path cost from the start node with a heuristic estimate of the remaining distance to the goal:

```text
f(n) = g(n) + h(n)
```

In this project, the heuristic is the Euclidean distance between node coordinates.

## Graph Used

The program defines four nodes:

| Node | Coordinates |
| --- | --- |
| A | (0, 0) |
| B | (10, 5) |
| C | (5, 15) |
| D | (20, 20) |

Directed weighted edges:

```text
A -> B  weight 10
A -> C  weight 15
B -> D  weight 12
C -> D  weight 5
```

The true shortest route from **A** to **D** is:

```text
A -> C -> D
```

with a total cost of:

```text
20
```

Dijkstra finds this optimal distance. With the current Euclidean heuristic, the A* implementation returns a distance of `22` through `A -> B -> D`. This happens because the heuristic can overestimate the remaining graph cost, so it is not admissible for the edge weights used in this example.

## Repository Structure

```text
Shortest-Path-Algorithms-Java/
├── Main.java
├── README.md
├── .gitignore
└── docs/
    ├── Algorithm Design & Implementation - Phase 1.pptx
    ├── Algorithm Design & Implementation - Phase 2.pptx
    ├── Algorithm Phase 3 Analysis & Evaluation.pptx
    └── Progress Report.docx
```

## Requirements

- Java Development Kit (JDK) 8 or newer

Check your installation:

```bash
java -version
javac -version
```

## Compile and Run

Open a terminal in the project directory and run:

```bash
javac Main.java
java Main
```

The program prints results for both Dijkstra and A*, including the reported distance, visited-node count, and execution time.

A typical run of the current implementation reports:

```text
--- Dijkstra Algorithm Results ---
Shortest distance: 20
Number of visited nodes: 4

--- A* Algorithm Results ---
Shortest distance: 22
Number of visited nodes: 3
```

Execution time varies between runs and machines.

## Implementation Details

The project includes:

- `Node` class for node IDs, names, and coordinates
- `Edge` class for graph connections and weights
- `PriorityQueue` for efficient node selection
- Euclidean-distance heuristic for A*
- Runtime measurement using `System.nanoTime()`

## Technologies Used

- Java
- Java Collections Framework
- Priority Queue
- Graph Algorithms
- Heuristic Search

## Learning Objectives

This project demonstrates:

- Representing a weighted graph in Java
- Implementing Dijkstra's shortest-path algorithm
- Implementing A* search
- Designing and applying a heuristic function
- Comparing algorithms by explored nodes and execution time
- Understanding why A* requires an admissible/consistent heuristic for optimality
- Using priority queues in graph-search problems

## Documentation

Project presentations and the progress report are available in the [`docs`](docs/) directory.

---

Built as an algorithm design and analysis project comparing Dijkstra and A* shortest-path search.
