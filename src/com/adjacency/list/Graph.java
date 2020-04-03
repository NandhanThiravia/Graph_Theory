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

    @SuppressWarnings("unused")
    private Graph() {
    }

    public Graph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.adjacencyArray = new AdjacencyList[this.numberOfVertices];

        for (int index = 0; index < this.numberOfVertices; ++index) {
            this.adjacencyArray[index] = new AdjacencyList();
        }
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

        System.out.println("\n\nBreadth - First Traversal");
        System.out.println("--------------------------");

        boolean visited[] = new boolean[numberOfVertices];
        Queue<Integer> queue = new LinkedList<Integer>();

        if (!visited[0]) {
            visited[0] = true;
            queue.add(0);
        }

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

    /**
     * Displays the Depth First Traversal of a graph
     */
    public void depthFirstTraversal() {
        if (numberOfVertices == 0) {
            System.out.println("No Vertices found");
            return;
        }

        System.out.println("\n\nDepth - First Traversal");
        System.out.println("--------------------------");

        boolean visited[] = new boolean[numberOfVertices];
        Stack<Integer> stack = new Stack<Integer>();

        if (!visited[0]) {
            visited[0] = true;
            stack.add(0);
        }

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
