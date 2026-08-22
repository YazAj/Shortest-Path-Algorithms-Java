import java.util.*;

class Node {
    public int id;
    public String name;
    public int x;
    public int y;
    public Node(int id, String name, int x, int y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }
}

class Edge {
    public Node target;
    public int weight;
    public Edge(Node target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}

public class Main {
    
    public static int calculateHeuristic(Node node, Node goal) {
        return (int) Math.sqrt(Math.pow(node.x - goal.x, 2) + Math.pow(node.y - goal.y, 2));
    }

    public static void runDijkstra(Map<Node, List<Edge>> graph, Node start, Node goal, int numNodes) {
        int[] dist = new int[numNodes];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start.id] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(dist[a.id], dist[b.id]));
        boolean[] visited = new boolean[numNodes];
        
        int visitedNodesCount = 0;
        long startTime = System.nanoTime();

        pq.add(start);

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visited[current.id]) continue;
            visited[current.id] = true;
            visitedNodesCount++;

            if (current.id == goal.id) break;

            for (Edge e : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited[e.target.id]) {
                    int newDist = dist[current.id] + e.weight;
                    if (newDist < dist[e.target.id]) {
                        dist[e.target.id] = newDist;
                        pq.add(e.target);
                    }
                }
            }
        }
        long endTime = System.nanoTime();
        
        System.out.println("--- Dijkstra Algorithm Results ---");
        System.out.println("Shortest distance: " + dist[goal.id]);
        System.out.println("Number of visited nodes: " + visitedNodesCount);
        System.out.println("Execution time: " + (endTime - startTime) / 1000000.0 + " ms\n");
    }

    public static void runAStar(Map<Node, List<Edge>> graph, Node start, Node goal, int numNodes) {
        int[] gCost = new int[numNodes];
        int[] fCost = new int[numNodes];
        Arrays.fill(gCost, Integer.MAX_VALUE);
        Arrays.fill(fCost, Integer.MAX_VALUE);

        gCost[start.id] = 0;
        fCost[start.id] = calculateHeuristic(start, goal);

        PriorityQueue<Node> openSet = new PriorityQueue<>((a, b) -> Integer.compare(fCost[a.id], fCost[b.id]));
        boolean[] visited = new boolean[numNodes];
        
        int visitedNodesCount = 0;
        long startTime = System.nanoTime();

        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (visited[current.id]) continue;
            visited[current.id] = true;
            visitedNodesCount++;

            if (current.id == goal.id) break;

            for (Edge e : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited[e.target.id]) {
                    int tentative_gCost = gCost[current.id] + e.weight;

                    if (tentative_gCost < gCost[e.target.id]) {
                        gCost[e.target.id] = tentative_gCost;
                        fCost[e.target.id] = gCost[e.target.id] + calculateHeuristic(e.target, goal);
                        openSet.add(e.target);
                    }
                }
            }
        }
        long endTime = System.nanoTime();

        System.out.println("--- A* Algorithm Results ---");
        System.out.println("Shortest distance: " + gCost[goal.id]);
        System.out.println("Number of visited nodes: " + visitedNodesCount);
        System.out.println("Execution time: " + (endTime - startTime) / 1000000.0 + " ms\n");
    }

    public static void main(String[] args) {
        // Create map nodes with coordinates (ID, Name, X, Y)
        Node nodeA = new Node(0, "A", 0, 0);
        Node nodeB = new Node(1, "B", 10, 5);
        Node nodeC = new Node(2, "C", 5, 15);
        Node nodeD = new Node(3, "D", 20, 20);
        
        Map<Node, List<Edge>> graph = new HashMap<>();
        
        // Build graph edges with weights
        graph.put(nodeA, Arrays.asList(new Edge(nodeB, 10), new Edge(nodeC, 15)));
        graph.put(nodeB, Arrays.asList(new Edge(nodeD, 12)));
        graph.put(nodeC, Arrays.asList(new Edge(nodeD, 5)));
        
        // Run comparison from node A to node D
        int totalNodes = 4;
        runDijkstra(graph, nodeA, nodeD, totalNodes);
        runAStar(graph, nodeA, nodeD, totalNodes);
    }
}