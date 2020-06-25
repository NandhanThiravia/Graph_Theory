package com.adjacency.list;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph2 {
    enum Type {
        UNDIRECTED, DIRECTED
    }

    enum Algorithm {
        KAHN, DFS, BFS, TOPOLOGICAL
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

    public void displayLevel(int startVertex, int destinationVertex) {
        int levelOfDestination = -1;

        Queue<Integer> levelQueue = new LinkedList<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean isVisited[] = new boolean[mTotalVertex];

        queue.add(startVertex);
        levelQueue.add(startVertex);
        isVisited[startVertex] = true;

        //System.out.println("Destination Vertex: " + destinationVertex);
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            int level = levelQueue.poll();

            //System.out.println("Vertex: " + vertex + " Level: " + level);
            if (vertex == destinationVertex) {
                levelOfDestination = level;
                break;
            }

            ArrayList<Vertex> neighbourList = mAdjacencyList.get(vertex);
            for (int index = 0; index < neighbourList.size(); ++index) {
                int neighbour = neighbourList.get(index).value;
                if (!isVisited[neighbour]) {
                    isVisited[neighbour] = true;
                    queue.add(neighbour);
                    levelQueue.add(level + 1);
                }
            }
        }
        System.out.println(levelOfDestination);
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
     * Displays the Topological Sort of a Directed Graph But it fails to work, if
     * there is a Cycle in the Graph
     */
    private void topologicalSortDFS() {
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

    private void topologicalSortKahn() {
        boolean isVisited[] = new boolean[mTotalVertex];
        int inDegree[] = new int[mTotalVertex];

        for (int index = 0; index < mTotalVertex; ++index) {
            ArrayList<Vertex> innerList = mAdjacencyList.get(index);
            for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
                ++inDegree[innerList.get(innerIndex).value];
            }
        }

        int[] output = new int[mTotalVertex];
        int visitedCount = 0;
        boolean isCyclic = true;
        Queue<Integer> queue = new LinkedList<Integer>();
        while (true) {
            boolean isFound = false;
            for (int index = 0; index < mTotalVertex; ++index) {
                if (!isVisited[index] && inDegree[index] == 0) {
                    isFound = true;
                    queue.add(index);
                    isVisited[index] = true;
                    output[visitedCount] = index;
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

        if (isCyclic) {
            System.err.println("The Graph is Cyclic, so sort cannot be performed");
            return;
        }

        System.out.println("Topological Sort");
        System.out.println("-----------------");
        for (int index = 0; index < output.length; ++index) {
            System.out.println(output[index]);
        }
    }

    /**
     * Topological Sort using: 1) Kahn's Algorithm 2) Depth-First Search Algorithm
     * 
     * @param mAlgorithm
     */
    public void topologicalSort(Algorithm mAlgorithm) {
        if (Type.DIRECTED == mType) {
            if (Algorithm.DFS == mAlgorithm) {
                if (!isCyclic(Algorithm.KAHN)) {
                    topologicalSortDFS();
                } else {
                    System.out.println("Topological Sort cannot be performed, because the Graph is Cyclic");
                }
            } else if (Algorithm.KAHN == mAlgorithm) {
                topologicalSortKahn();
            }
        } else {
            System.err.println("Topological Sort is only for Directed Graphs");
        }
    }

    /**
     * To find the Shortest Distance from a source vertex to other nodes, Breadth
     * First Search is the best way. Breadth First Search will put nearby neighbours
     * first to queue thereby marking the distance as 1 and its neighbours as
     * distance + 1 The same would happen to their first neighbours.
     *
     * Shortest Distance Algorithm would not work with DFS Algorithm, because of the
     * way it operates and the way isVisited boolean array is utilized.
     *
     * @param sourceVertex
     */
    private void shortestDistanceDirectedBFS(int sourceVertex) {
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] distance = new int[mTotalVertex];

        for (int index = 0; index < mTotalVertex; ++index) {
            distance[index] = Integer.MAX_VALUE;
        }

        distance[sourceVertex] = 0;
        queue.add(sourceVertex);

        while (!queue.isEmpty()) {
            int parentVertex = queue.poll();

            ArrayList<Vertex> innerList = mAdjacencyList.get(parentVertex);
            for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
                int childVertex = innerList.get(innerIndex).value;
                int childVertexDistance = innerList.get(innerIndex).distance;
                queue.add(childVertex);
                if (distance[childVertex] > distance[parentVertex] + childVertexDistance) {
                    distance[childVertex] = distance[parentVertex] + childVertexDistance;
                }
            }
        }

        System.out.println();
        System.out.println("Shortest Distance from " + sourceVertex + " node");
        System.out.println("------------------------------");
        for (int index = 0; index < mTotalVertex; ++index) {
            if (distance[index] == Integer.MAX_VALUE) {
                System.out.println(index + ": INF");
            } else {
                System.out.println(index + ": " + distance[index]);
            }
        }
        return;
    }

    private void shortestDistanceUndirectedBFS(int sourceVertex) {
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
                    isVisited[childVertex] = true;
                    queue.add(childVertex);
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
                System.out.println(index + ": INF");
            } else {
                System.out.println(index + ": " + distance[index]);
            }
        }
        return;
    }

    public void shortestDistance(int sourceVertex, Algorithm mAlgorithm) {
        if (Type.UNDIRECTED == mType) {
            shortestDistanceUndirectedBFS(sourceVertex);
        } else if (Type.DIRECTED == mType) {
            if (Algorithm.BFS == mAlgorithm) {
                shortestDistanceDirectedBFS(sourceVertex);
            } else if (Algorithm.TOPOLOGICAL == mAlgorithm) {
                // TBD
            }
        }
    }

    private boolean isCyclicDFSUndirected() {
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

    private boolean isCyclicKahnDirected() {
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
     * For Undirected Graphs: 1) Depth-First Search Algorithm
     * 
     * For Directed Graphs: 1) KAHN's Algorithm 2) Topological Algorithm
     *
     * @param mAlgorithm
     * @return
     */
    public boolean isCyclic(Algorithm mAlgorithm) {
        boolean isCyclic = false;
        if (Type.UNDIRECTED == mType) {
            if (Algorithm.DFS == mAlgorithm) {
                isCyclic = isCyclicDFSUndirected();
            }
        } else if (Type.DIRECTED == mType) {
            if (Algorithm.KAHN == mAlgorithm) {
                isCyclic = isCyclicKahnDirected();
            } else if (Algorithm.TOPOLOGICAL == mAlgorithm) {
                // TBD
            }
        }
        String status = "";
        status = (isCyclic ? "Cyclic" : "Acyclic");
        System.out.println();
        System.out.println("The Graph is " + status);
        return isCyclic;
    }

    public boolean hasPath(int source, int destination) {
        boolean hasPath = false;

        boolean[] isVisited = new boolean[mTotalVertex];
        Stack<Integer> stack = new Stack<Integer>();
        int childVertex = -1;

        isVisited[source] = true;
        stack.push(source);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();

            if (vertex == destination) {
                hasPath = true;
                break;
            }

            ArrayList<Vertex> innerList = mAdjacencyList.get(vertex);
            for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
                childVertex = innerList.get(innerIndex).value;
                if (!isVisited[childVertex]) {
                    isVisited[childVertex] = true;
                    stack.push(childVertex);
                }
            }
        }

        String status = (hasPath ? "A" : "NO");
        System.out.println();
        System.out.println("There is " + status + " path from " + source + " to " + destination);
        return hasPath;
    }
}
