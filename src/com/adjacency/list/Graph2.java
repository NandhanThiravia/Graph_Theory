package com.adjacency.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph2 {
    enum Type {
        UNDIRECTED, DIRECTED
    }

    enum Algorithm {
        KAHN, DFS, BFS
    }

    class Vertex {
        public int value;
        public int distance;

        public Vertex(int value, int distance) {
            this.value = value;
            this.distance = distance;
        }
    }

    private ArrayList<ArrayList<Vertex>> mAdjacencyList;
    private int mTotalVertex;
    private Type mType;

    public Graph2(int numberOfVertices, Type type) {
        mType = type;
        mTotalVertex = numberOfVertices;
        mAdjacencyList = new ArrayList<ArrayList<Vertex>>();
        mAdjacencyList.clear();
        for (int index = 0; index < numberOfVertices; ++index) {
            mAdjacencyList.add(new ArrayList<Vertex>());
        }
    }

    public int getVertexCount() {
        return mTotalVertex;
    }

    public void addEdge(int from, int to, int distance) {
        Vertex node = null;

        node = new Vertex(to, distance);
        mAdjacencyList.get(from).add(node);

        if (mType == Type.UNDIRECTED) {
            node = new Vertex(from, distance);
            mAdjacencyList.get(to).add(node);
        }
    }

    public void addEdge(int from, int to) {
        addEdge(from, to, 1);
    }

    /**
     * Displays the Adjacency List Representation of a Graph
     */
    public void display() {
        System.out.println("Adjacency List Representation");
        System.out.println("-------------------------------");
        for (int index = 0; index < mTotalVertex; index++) {
            System.out.print("[" + index + "] -> ");
            ArrayList<Vertex> innerList = mAdjacencyList.get(index);
            for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
                System.out.print(innerList.get(innerIndex).value + "(" + innerList.get(innerIndex).distance + ")" + " -> ");
            }
            System.out.println("NULL");
        }
        System.out.println();
    }

    /**
     * Displays the Breadth First Traversal of a Graph
     */
    public void breadthFirstTraversal() {
        if (mTotalVertex == 0) {
            System.out.println("No Vertices found");
            return;
        }

        System.out.println();
        System.out.println("Breadth - First Traversal");
        System.out.println("--------------------------");

        boolean isVisited[] = new boolean[mTotalVertex];
        Queue<Integer> queue = new LinkedList<Integer>();

        // Below variable tracks the number of islands in graphs in the input
        int islands = 0;
        for (int index = 0; index < mTotalVertex; ++index) {
            if (!isVisited[index]) {
                isVisited[index] = true;
                queue.add(index);
                ++islands;

                System.out.println();
                System.out.println("Island: " + islands);

                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    System.out.println(node);

                    ArrayList<Vertex> innerList = mAdjacencyList.get(node);
                    for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
                        if (!isVisited[innerList.get(innerIndex).value]) {
                            isVisited[innerList.get(innerIndex).value] = true;
                            queue.add(innerList.get(innerIndex).value);
                        }
                    }
                }
            }

        }

        System.out.println();
        System.out.println("Islands in this Graph: " + islands);
        System.out.println();
    }

    /**
     * Displays the Depth First Traversal of a Graph
     */
    public void depthFirstTraversal() {
        if (mTotalVertex == 0) {
            System.out.println("No Vertices found");
            return;
        }

        System.out.println("Depth - First Traversal");
        System.out.println("--------------------------");

        boolean isVisited[] = new boolean[mTotalVertex];
        Stack<Integer> stack = new Stack<Integer>();

        int islands = 0;
        for (int index = 0; index < mTotalVertex; ++index) {
            if (!isVisited[index]) {
                isVisited[index] = true;
                stack.add(index);
                ++islands;
                System.out.println();
                System.out.println("Island: " + islands);

                while (!stack.isEmpty()) {
                    int vertex = stack.pop();
                    System.out.println(vertex);

                    ArrayList<Vertex> innerList = mAdjacencyList.get(vertex);
                    for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
                        if (!isVisited[innerList.get(innerIndex).value]) {
                            isVisited[innerList.get(innerIndex).value] = true;
                            stack.add(innerList.get(innerIndex).value);
                        }
                    }
                }
            }
        }

        System.out.println();
        System.out.println("Islands: " + islands);
        System.out.println();
    }

    private boolean hasNonVisitedNeighbour(int vertex, boolean[] isVisited) {
        ArrayList<Vertex> innerList = mAdjacencyList.get(vertex);
        for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
            if (!isVisited[innerList.get(innerIndex).value]) {
                return true;
            }
        }
        return false;
    }

    private int nextNonVisitedNeighbour(int vertex, boolean[] isVisited) {
        ArrayList<Vertex> innerList = mAdjacencyList.get(vertex);
        for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
            if (!isVisited[innerList.get(innerIndex).value]) {
                return innerList.get(innerIndex).value;
            }
        }
        return -1;
    }

    /**
     * Displays the Topological Sort of a Directed Graph
     */
    public void topologicalSort() {
        boolean isVisited[] = new boolean[mTotalVertex];
        Stack<Integer> output = new Stack<Integer>();
        Stack<Integer> stack = new Stack<Integer>();

        for (int index = 0; index < mTotalVertex; ++index) {
            if (!isVisited[index]) {
                isVisited[index] = true;
                stack.push(index);

                while (!stack.isEmpty()) {
                    int vertex = stack.pop();

                    while (hasNonVisitedNeighbour(vertex, isVisited)) {
                        stack.push(vertex);
                        vertex = nextNonVisitedNeighbour(vertex, isVisited);
                        isVisited[vertex] = true;
                    }
                    output.push(vertex);
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
    public void shortestDistance(int sourceVertex) {
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] isVisited = new boolean[mTotalVertex];
        int[] distance = new int[mTotalVertex];

        for (int index = 0; index < mTotalVertex; ++index) {
            distance[index] = Integer.MAX_VALUE;
        }

        isVisited[sourceVertex] = true;
        distance[sourceVertex] = 0;
        queue.add(sourceVertex);

        while (!queue.isEmpty()) {
            int parentVertex = queue.poll();

            ArrayList<Vertex> innerList = mAdjacencyList.get(parentVertex);
            for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
                int childVertex = innerList.get(innerIndex).value;
                int childVertexDistance = innerList.get(innerIndex).distance;
                if (!isVisited[childVertex]) {
                    queue.add(childVertex);
                    isVisited[childVertex] = true;
                    if (distance[childVertex] > distance[parentVertex] + childVertexDistance) {
                        distance[childVertex] = distance[parentVertex] + childVertexDistance;
                    }
                }
            }
        }

        System.out.println();
        System.out.println("Shortest Distance from " + sourceVertex + " node");
        System.out.println("------------------------------");
        for (int index = 0; index < mTotalVertex; ++index) {
            if (distance[index] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(distance[index]);
            }
        }
        return;
    }

    private boolean isDFSUndirectedCyclic() {
        boolean isCyclic = false;
        if (mTotalVertex == 0) {
            System.out.println("No Vertices found");
            return isCyclic;
        }

        boolean[] visited = new boolean[mTotalVertex];
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> parentStack = new Stack<Integer>();

        for (int index = 0; index < mTotalVertex; ++index) {
            if (!visited[index]) {
                visited[index] = true;
                stack.add(index);
                parentStack.add(-1);

                while (!stack.isEmpty()) {
                    Integer vertex = stack.pop();
                    Integer parentVertex = parentStack.pop();

                    ArrayList<Vertex> innerList = mAdjacencyList.get(vertex);
                    int childVertex = -1;
                    for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
                        childVertex = innerList.get(innerIndex).value;
                        if (!visited[childVertex]) {
                            visited[childVertex] = true;
                            stack.add(childVertex);
                            parentStack.add(vertex);
                        } else if (childVertex != parentVertex) {
                            return true;
                        }
                    }
                }
            }
        }
        return isCyclic;
    }

    private boolean isKahnDirectedCyclic() {
        boolean isVisited[] = new boolean[mTotalVertex];
        int inDegree[] = new int[mTotalVertex];

        for (int index = 0; index < mTotalVertex; ++index) {
            ArrayList<Vertex> innerList = mAdjacencyList.get(index);
            for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
                ++inDegree[innerList.get(innerIndex).value];
            }
        }

        boolean isCyclic = true;
        int visitedCount = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        while (true) {
            boolean isFound = false;
            for (int index = 0; index < mTotalVertex; ++index) {
                if (!isVisited[index] && inDegree[index] == 0) {
                    isFound = true;
                    queue.add(index);
                    isVisited[index] = true;
                    ++visitedCount;
                }
            }

            if (!isFound) {
                break;
            }

            while (!queue.isEmpty()) {
                int node = queue.poll();
                ArrayList<Vertex> innerList = mAdjacencyList.get(node);
                for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
                    --inDegree[innerList.get(innerIndex).value];
                }
            }
        }

        if (visitedCount == mTotalVertex) {
            isCyclic = false;
        }
        return isCyclic;
    }

    /**
     * it let's know if a Graph is Cyclic or Acyclic.
     * 
     * For Undirected Graphs, DFS / BFS Algorithm can be used, either Stack of Queue.
     * For Directed Graphs, KAHN's Algorithm is used.
     * @param mAlgorithm
     * @return
     */
    public boolean isCyclic(Algorithm mAlgorithm) {
        boolean isCyclic = false;
        if (Type.UNDIRECTED == mType) {
            if (Algorithm.DFS == mAlgorithm) {
                isCyclic = isDFSUndirectedCyclic();
            }
        } else if (Type.DIRECTED == mType) {
            if (Algorithm.KAHN == mAlgorithm) {
                isCyclic = isKahnDirectedCyclic();
            }
        }
        String status = "";
        status = (isCyclic ? "Cyclic" : "Acyclic");
        System.out.println();
        System.out.println("The Graph is " + status);
        return isCyclic;
    }
}
