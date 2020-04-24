package com.adjacency.list;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {
    public int data;
    public Node link;

    @SuppressWarnings("unused")
    private Node() {
    }

    public Node(int data) {
        this.data = data;
        this.link = null;
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

    public void addEdge(int fromVertex, int toVertex) {
        Node node = new Node(toVertex);

        if (adjacencyArray[fromVertex].head == null) {
            adjacencyArray[fromVertex].head = node;
        } else {
            node.link = adjacencyArray[fromVertex].head;
            adjacencyArray[fromVertex].head = node;
        }
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
                System.out.print(traversal.data + " -> ");
                traversal = traversal.link;
            }
            System.out.println("NULL");
        }
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
    }
}
