package com.adjacency.list;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class CycleNode {
    public int currentNode;
    public int parentNode;

    public CycleNode(int currentNode, int parentNode) {
        this.currentNode = currentNode;
        this.parentNode = parentNode;
    }
}

class Node {
    public int data;
    public int distance;
    public Node link;

    @SuppressWarnings("unused")
    private Node() {
    }

    public Node(int data) {
        this.data = data;
        this.link = null;
    }

    public Node(int data, int distance) {
        this.data = data;
        this.distance = distance;
    }
}

class AdjacencyList {
    public Node head;

    public AdjacencyList() {
        head = null;
    }
}

public class Graph {
    private AdjacencyList[] adjacencyArray;
    private int numberOfVertices;

    boolean visited[];

    @SuppressWarnings("unused")
    private Graph() {
    }

    public Graph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.adjacencyArray = new AdjacencyList[this.numberOfVertices];

        for (int index = 0; index < this.numberOfVertices; ++index) {
            this.adjacencyArray[index] = new AdjacencyList();
        }

        visited = new boolean[this.numberOfVertices];
    }

    public void addEdge(int fromVertex, int toVertex, int distance) {
        Node node = new Node(toVertex, distance);

        if (adjacencyArray[fromVertex].head == null) {
            adjacencyArray[fromVertex].head = node;
        } else {
            node.link = adjacencyArray[fromVertex].head;
            adjacencyArray[fromVertex].head = node;
        }
    }

    public void addEdge(int fromVertex, int toVertex) {
        addEdge(fromVertex, toVertex, 1);
    }

    /**
     * Displays the Adjacency List Representation of a Graph
     */
    public void display() {
        System.out.println("Adjacency List Presentation");
        System.out.println("----------------------------");
        for (int index = 0; index < numberOfVertices; index++) {
            System.out.print("[" + index + "] -> ");
            Node traversal = adjacencyArray[index].head;
            while (traversal != null) {
                System.out.print(traversal.data + "(" + traversal.distance + ")" + " -> ");
                traversal = traversal.link;
            }
            System.out.println("NULL");
        }
        System.out.println();
    }

    /**
     * Displays the Breadth First Traversal of a Graph
     */
    public void breadthFirstTraversal() {
        if (numberOfVertices == 0) {
            System.out.println("No Vertices found");
            return;
        }

        System.out.println();
        System.out.println("Breadth - First Traversal");
        System.out.println("--------------------------");

        for (int index = 0; index < numberOfVertices; ++index) {
            visited[index] = false;
        }

        Queue<Integer> queue = new LinkedList<Integer>();

        // Below variable tracks the number of islands in graphs in the input
        int islands = 0;
        for (int index = 0; index < numberOfVertices; ++index) {
            if (!visited[index]) {
                visited[index] = true;
                queue.add(index);
                ++islands;
                System.out.println();
                System.out.println("Island: " + islands);

                while (!queue.isEmpty()) {
                    int data = queue.poll();
                    System.out.println(data);

                    Node iterator = adjacencyArray[data].head;
                    while (iterator != null) {
                        if (!visited[iterator.data]) {
                            visited[iterator.data] = true;
                            queue.add(iterator.data);
                        }
                        iterator = iterator.link;
                    }
                }
            }

        }

        System.out.println();
        System.out.println("Islands in this Graph: " + islands);
        System.out.println();
    }

    /**
     * Displays the Depth First Traversal of a graph
     */
    public void depthFirstTraversal() {
        if (numberOfVertices == 0) {
            System.out.println("No Vertices found");
            return;
        }

        System.out.println("Depth - First Traversal");
        System.out.println("--------------------------");

        for (int index = 0; index < numberOfVertices; ++index) {
            visited[index] = false;
        }

        Stack<Integer> stack = new Stack<Integer>();

        int islands = 0;
        for (int index = 0; index < numberOfVertices; ++index) {
            if (!visited[index]) {
                visited[index] = true;
                stack.add(index);
                ++islands;
                System.out.println();
                System.out.println("Island: " + islands);

                while (!stack.isEmpty()) {
                    int data = stack.pop();
                    System.out.println(data);

                    Node iterator = adjacencyArray[data].head;
                    while (iterator != null) {
                        if (!visited[iterator.data]) {
                            visited[iterator.data] = true;
                            stack.add(iterator.data);
                        }
                        iterator = iterator.link;
                    }
                }
            }
        }

        System.out.println();
        System.out.println("Islands: " + islands);
        System.out.println();
    }

    private boolean hasNonVisitedNeighbour(int data) {
        Node iterator = adjacencyArray[data].head;
        while (iterator != null) {
            if (!visited[iterator.data]) {
                // System.out.println("Non visited node present");
                return true;
            }
            iterator = iterator.link;
        }
        return false;
    }

    private int nextNonVisitedNeighbour(int data) {
        Node iterator = adjacencyArray[data].head;
        while (iterator != null) {
            if (!visited[iterator.data]) {
                return iterator.data;
            }
            iterator = iterator.link;
        }
        return -1;
    }

    public void topologicalSort() {
        for (int index = 0; index < numberOfVertices; ++index) {
            visited[index] = false;
        }

        Stack<Integer> output = new Stack<Integer>();
        Stack<Integer> stack = new Stack<Integer>();

        for (int nodeInSequence = 0; nodeInSequence < numberOfVertices; ++nodeInSequence) {
            if (!visited[nodeInSequence]) {
                visited[nodeInSequence] = true;
                stack.push(nodeInSequence);

                while (!stack.isEmpty()) {
                    int node = stack.pop();

                    while (hasNonVisitedNeighbour(node)) {
                        stack.push(node);
                        node = nextNonVisitedNeighbour(node);
                        visited[node] = true;
                    }
                    output.push(node);
                }
            }
        }

        System.out.println("Topological Sort");
        System.out.println("-----------------");
        while (!output.isEmpty()) {
            System.out.println(output.pop());
        }
        System.out.println();
    }

    /**
     * To find the Shortest Distance from a source vertex to other nodes, Breadth
     * First Search is the best way. Breadth First Search will put nearby neighbours
     * first to queue thereby marking the distance as 1 and its neighbours as
     * distance + 1 The same would happen to their first neighbours.
     * 
     * @param sourceVertex
     */
    public void unitShortestDistance(int sourceVertex) {
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[numberOfVertices];
        int[] distance = new int[numberOfVertices];

        for (int index = 0; index < numberOfVertices; ++index) {
            distance[index] = Integer.MAX_VALUE;
        }

        visited[sourceVertex] = true;
        distance[sourceVertex] = 0;
        queue.add(sourceVertex);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            Node nodeIterator = adjacencyArray[vertex].head;
            while (nodeIterator != null) {
                if (!visited[nodeIterator.data]) {
                    queue.add(nodeIterator.data);
                    visited[nodeIterator.data] = true;
                    distance[nodeIterator.data] = distance[vertex] + 1;
                }
                nodeIterator = nodeIterator.link;
            }
        }

        System.out.println();
        System.out.println("Shortest Distance from " + sourceVertex + " node");
        System.out.println("------------------------------");
        for (int index = 0; index < numberOfVertices; ++index) {
            System.out.println(distance[index]);
        }
        return;
    }

    public void nonUnitShortestDistance(int sourceVertex) {
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] distance = new int[numberOfVertices];

        for (int index = 0; index < numberOfVertices; ++index) {
            distance[index] = Integer.MAX_VALUE;
        }

        distance[sourceVertex] = 0;
        queue.add(sourceVertex);

        int tempDistance = 0;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            Node nodeIterator = adjacencyArray[vertex].head;
            while (nodeIterator != null) {
                queue.add(nodeIterator.data);
                tempDistance = distance[vertex] + nodeIterator.distance;
                if (distance[nodeIterator.data] > tempDistance) {
                    distance[nodeIterator.data] = tempDistance;
                }
                nodeIterator = nodeIterator.link;
            }
        }

        System.out.println();
        System.out.println("Shortest Distance from " + sourceVertex + " node");
        System.out.println("------------------------------");
        for (int index = 0; index < numberOfVertices; ++index) {
            System.out.println(distance[index]);
        }
        System.out.println();
        return;
    }

    public void topoShortestDistance(int sourceVertex) {
        for (int index = 0; index < numberOfVertices; ++index) {
            visited[index] = false;
        }

        Stack<Integer> output = new Stack<Integer>();
        Stack<Integer> stack = new Stack<Integer>();

        for (int nodeInSequence = 0; nodeInSequence < numberOfVertices; ++nodeInSequence) {
            if (!visited[nodeInSequence]) {
                visited[nodeInSequence] = true;
                stack.push(nodeInSequence);

                while (!stack.isEmpty()) {
                    int node = stack.pop();

                    while (hasNonVisitedNeighbour(node)) {
                        stack.push(node);
                        node = nextNonVisitedNeighbour(node);
                        visited[node] = true;
                    }
                    output.push(node);
                }
            }
        }

        int[] distance = new int[numberOfVertices];
        for (int index = 0; index < numberOfVertices; ++index) {
            distance[index] = 500;
        }

        distance[sourceVertex] = 0;
        while (!output.isEmpty()) {
            int data = output.pop();

            Node node = adjacencyArray[data].head;
            while (node != null) {
                if (distance[node.data] > distance[data] + node.distance) {
                    distance[node.data] = distance[data] + node.distance;
                }
                node = node.link;
            }
        }

        System.out.println("Shortest Distance");
        System.out.println("-----------------");
        for (int index = 0; index < numberOfVertices; ++index) {
            System.out.println(distance[index]);
        }
        System.out.println();

    }

    public boolean isCycleDetectedUndirected() {
        boolean isCycleDetected = false;
        if (numberOfVertices == 0) {
            System.out.println("No Vertices found");
            return isCycleDetected;
        }

        boolean[] visited = new boolean[numberOfVertices];
        Stack<CycleNode> stack = new Stack<CycleNode>();

        for (int index = 0; index < numberOfVertices; ++index) {
            if (!visited[index]) {
                visited[index] = true;
                stack.add(new CycleNode(index, -1));

                while (!stack.isEmpty()) {
                    CycleNode cycleNode = stack.pop();
                    int currentNode = cycleNode.currentNode;
                    int parentNode = cycleNode.parentNode;

                    Node iterator = adjacencyArray[currentNode].head;
                    while (iterator != null) {
                        if (!visited[iterator.data]) {
                            visited[iterator.data] = true;
                            stack.add(new CycleNode(iterator.data, currentNode));
                        } else if (iterator.data != parentNode) {
                            return true;
                        }
                        iterator = iterator.link;
                    }
                }
            }
        }
        return isCycleDetected;
    }

    public CycleNode isCycleDetectedDirected() {
        if (numberOfVertices == 0) {
            System.out.println("No Vertices found");
            return null;
        }

        boolean[] isVisitedGobally = new boolean[numberOfVertices];
        boolean[] isVisitedLocally = new boolean[numberOfVertices];
        Stack<Integer> stack = new Stack<Integer>();

        for (int index = 0; index < numberOfVertices; ++index) {
            if (!isVisitedGobally[index]) {
                reset(isVisitedLocally);
                // isVisitedLocally[index] = true;

                stack.add(index);

                while (!stack.isEmpty()) {
                    int currentNode = stack.pop();
                    isVisitedLocally[currentNode] = true;

                    Node iterator = adjacencyArray[currentNode].head;
                    if (iterator == null) {
                        // The idea behind this if condition is as follows:
                        // When we try to go down the line in a graph to find for any cycle and if we
                        // come to an end, then we merge local visited array to global visited array
                        // and reset the local array and search again
                        merge(isVisitedGobally, isVisitedLocally);
                        reset(isVisitedLocally);
                        continue;
                    }
                    while (iterator != null) {
                        if (!isVisitedLocally[iterator.data]) {
                            // isVisitedLocally[iterator.data] = true;
                            stack.add(iterator.data);
                        } else if (isVisitedLocally[iterator.data]) {
                            return new CycleNode(iterator.data, currentNode);
                        }
                        iterator = iterator.link;
                    }
                }

                // Bring in the changes of Local Visited Array into Global Visited Array
                merge(isVisitedGobally, isVisitedLocally);
            }
        }
        return null;
    }

    private void merge(boolean[] isVisitedGobally, boolean[] isVisitedLocally) {
        for (int index = 0; index < isVisitedGobally.length; ++index) {
            isVisitedGobally[index] = isVisitedGobally[index] | isVisitedLocally[index];
        }
    }

    private void reset(boolean[] array) {
        for (int index = 0; index < array.length; ++index) {
            array[index] = false;
        }
    }
}
