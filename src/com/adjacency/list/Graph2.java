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
        KAHN, DFS, BFS, TOPOLOGICAL, DIJKSTRA, BELLMAN_FORD, KOSARAJU
    }

    class Vertex {
        public int value;
        public int distance;

        public Vertex(int value, int distance) {
            this.value = value;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Vertex))
                return false;

            Vertex vertex = (Vertex) obj;
            if (vertex.value == this.value) {
                return true;
            }
            return false;
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

        // System.out.println("Destination Vertex: " + destinationVertex);
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            int level = levelQueue.poll();

            // System.out.println("Vertex: " + vertex + " Level: " + level);
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

    private int[] topologicalSortKosaraju() {
        return topologicalSortDFS();
    }

    /**
     * Displays the Topological Sort of a Directed Graph But it fails to work, if there is a Cycle in the Graph
     */
    private int[] topologicalSortDFS() {
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
        int[] outputList = new int[mTotalVertex];

        int index = 0;
        while (!output.isEmpty()) {
            int vertex = output.pop();
            System.out.println(vertex);
            outputList[index] = vertex;
            ++index;
        }
        System.out.println();
        return outputList;
    }

    private int[] topologicalSortKahn() {
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
            return null;
        }

        System.out.println("Topological Sort");
        System.out.println("-----------------");
        for (int index = 0; index < output.length; ++index) {
            System.out.println(output[index]);
        }
        return output;
    }

    /**
     * Topological Sort using: 1) Kahn's Algorithm 2) Depth-First Search Algorithm
     * 
     * @param mAlgorithm
     */
    public int[] topologicalSort(Algorithm mAlgorithm) {
        int[] output = null;
        if (Type.DIRECTED == mType) {
            if (Algorithm.DFS == mAlgorithm) {
                if (!isCyclic(Algorithm.KAHN)) {
                    output = topologicalSortDFS();
                } else {
                    System.out.println("Topological Sort cannot be performed, because the Graph is Cyclic");
                }
            } else if (Algorithm.KAHN == mAlgorithm) {
                output = topologicalSortKahn();
            } else if (Algorithm.KOSARAJU == mAlgorithm) {
                output = topologicalSortKosaraju();
            }
        } else {
            System.err.println("Topological Sort is only for Directed Graphs");
        }
        return output;
    }

    /**
     * To find the Shortest Distance from a source vertex to other nodes, Breadth First Search is the best way. Breadth First Search will put nearby neighbours first to queue thereby marking the
     * distance as 1 and its neighbours as distance + 1 The same would happen to their first neighbours.
     *
     * Shortest Distance Algorithm would not work with DFS Algorithm, because of the way it operates and the way isVisited boolean array is utilized.
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
        System.out.println();
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

    private int minVertexDistance(int[] distance, boolean[] isFinalized) {
        int minIndex = -1;
        int min = Integer.MAX_VALUE;

        for (int index = 0; index < mTotalVertex; ++index) {
            if (!isFinalized[index]) {
                if (min > distance[index]) {
                    min = distance[index];
                    minIndex = index;
                }
            }
        }
        return minIndex;
    }

    private void shortestDistanceDijkstra(int sourceVertex) {
        boolean[] isFinalized = new boolean[mTotalVertex];
        int[] distance = new int[mTotalVertex];
        ArrayList<Integer> vertexList = new ArrayList<Integer>();
        for (int index = 0; index < mTotalVertex; ++index) {
            distance[index] = Integer.MAX_VALUE;
            vertexList.add(index);
        }

        // Assigning 0 as distance to itself
        distance[sourceVertex] = 0;

        int parentVertex = 0;
        while (!vertexList.isEmpty()) {
            parentVertex = minVertexDistance(distance, isFinalized);
            vertexList.remove(new Integer(parentVertex));
            isFinalized[parentVertex] = true;

            Vertex neighbourVertex = null;
            ArrayList<Vertex> neighbourList = mAdjacencyList.get(parentVertex);
            for (int neighbourIndex = 0; neighbourIndex < neighbourList.size(); ++neighbourIndex) {
                neighbourVertex = neighbourList.get(neighbourIndex);
                if (!isFinalized[neighbourVertex.value]) {
                    if (distance[neighbourVertex.value] > distance[parentVertex] + neighbourVertex.distance) {
                        distance[neighbourVertex.value] = distance[parentVertex] + neighbourVertex.distance;
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
    }

    private void shortestDistanceDirectedBellmanFord(int sourceVertex) {
        int[] distance = new int[mTotalVertex];
        for (int index = 0; index < mTotalVertex; ++index) {
            distance[index] = Integer.MAX_VALUE;
        }
        distance[sourceVertex] = 0;

        for (int counter = 0; counter < (mTotalVertex - 1); ++counter) {
            // Traverse through all the edges
            for (int vertex = 0; vertex < mTotalVertex; ++vertex) {
                ArrayList<Vertex> neighbourList = mAdjacencyList.get(vertex);
                for (int neighbourIndex = 0; neighbourIndex < neighbourList.size(); ++neighbourIndex) {
                    Vertex neighbourVertex = neighbourList.get(neighbourIndex);

                    // Addition of any value with INFINITY would be INFINITY, so skipping,
                    // safeguarding the addition in next if condition
                    if (distance[vertex] == Integer.MAX_VALUE)
                        continue;

                    if (distance[neighbourVertex.value] > distance[vertex] + neighbourVertex.distance) {
                        distance[neighbourVertex.value] = distance[vertex] + neighbourVertex.distance;
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
    }

    public void shortestDistance(int sourceVertex, Algorithm algorithm) {
        if (Type.UNDIRECTED == mType) {
            if (Algorithm.BFS == algorithm) {
                shortestDistanceUndirectedBFS(sourceVertex);
            } else if (Algorithm.DIJKSTRA == algorithm) {
                shortestDistanceDijkstra(sourceVertex);
            }
        } else if (Type.DIRECTED == mType) {
            if (Algorithm.BFS == algorithm) {
                shortestDistanceDirectedBFS(sourceVertex);
            } else if (Algorithm.DIJKSTRA == algorithm) {
                shortestDistanceDijkstra(sourceVertex);
            } else if (Algorithm.BELLMAN_FORD == algorithm) {
                shortestDistanceDirectedBellmanFord(sourceVertex);
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

    public int countOfPath(int source, int destination) {
        int countOfPath = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(source);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            ArrayList<Vertex> neighbourList = mAdjacencyList.get(vertex);
            for (int neighbourIndex = 0; neighbourIndex < neighbourList.size(); ++neighbourIndex) {
                int neighbour = neighbourList.get(neighbourIndex).value;
                if (neighbour != destination) {
                    queue.add(neighbour);
                } else {
                    countOfPath += 1;
                }
            }
        }

        System.out.println("Number of Path from " + source + " to " + destination + " are " + countOfPath);
        return countOfPath;
    }

    public ArrayList<ArrayList<Vertex>> minSpanningTree(int sourceVertex) {
        ArrayList<ArrayList<Vertex>> minSpanningTree = new ArrayList<ArrayList<Vertex>>();
        ArrayList<Integer> inMSTList = new ArrayList<Integer>();
        ArrayList<Integer> outMSTList = new ArrayList<Integer>();

        boolean isVisited[] = new boolean[mTotalVertex];
        inMSTList.add(sourceVertex);
        for (int vertex = 0; vertex < mTotalVertex; ++vertex) {
            if (vertex != sourceVertex) {
                outMSTList.add(vertex);
            }
            minSpanningTree.add(new ArrayList<Vertex>());
        }
        isVisited[sourceVertex] = true;

        while (!outMSTList.isEmpty()) {
            int fromVertex = -1;
            int nearestNeighbour = -1;
            int nearesetNeighbourDistance = Integer.MAX_VALUE;
            int inMSTVertex = -1;
            for (int vertexIndex = 0; vertexIndex < inMSTList.size(); ++vertexIndex) {
                inMSTVertex = inMSTList.get(vertexIndex);
                ArrayList<Vertex> neighbourList = mAdjacencyList.get(inMSTVertex);
                for (int neighbourIndex = 0; neighbourIndex < neighbourList.size(); ++neighbourIndex) {
                    Vertex neighbourVertex = neighbourList.get(neighbourIndex);
                    if (!isVisited[neighbourVertex.value] && neighbourVertex.distance < nearesetNeighbourDistance) {
                        nearesetNeighbourDistance = neighbourVertex.distance;
                        nearestNeighbour = neighbourVertex.value;
                        fromVertex = inMSTVertex;
                    }
                }
            }
            minSpanningTree.get(fromVertex).add(new Vertex(nearestNeighbour, nearesetNeighbourDistance));
            minSpanningTree.get(nearestNeighbour).add(new Vertex(fromVertex, nearesetNeighbourDistance));
            isVisited[nearestNeighbour] = true;
            inMSTList.add(nearestNeighbour);
            outMSTList.remove(new Integer(nearestNeighbour)); // Because removing an element taken Object as argument
        }

        return minSpanningTree;
    }

    public ArrayList<ArrayList<Integer>> getConnectedComponents(Algorithm algorithm) {
        if (algorithm == Algorithm.KOSARAJU) {
            return getConnectedComponentsKosaraju();
        }
        return null;
    }

    private ArrayList<ArrayList<Integer>> getConnectedComponentsKosaraju() {
        // Step 1
        // Topological Sorting
        int[] output = topologicalSort(Algorithm.KOSARAJU);

        // Step 2
        // Transpose of the Graph
        int numberOfVertices = mTotalVertex;
        Graph2 graph = new Graph2(numberOfVertices, Type.DIRECTED);
        for (int index = 0; index < mTotalVertex; index++) {
            ArrayList<Vertex> neighbourList = mAdjacencyList.get(index);
            for (int neighbourIndex = 0; neighbourIndex < neighbourList.size(); ++neighbourIndex) {
                graph.addEdge(neighbourList.get(neighbourIndex).value, index);
            }
        }
        graph.display();

        // Step 3
        // Depth or Breadth First Traversal
        boolean isVisited[] = new boolean[mTotalVertex];
        Stack<Integer> stack = new Stack<Integer>();
        ArrayList<ArrayList<Integer>> connectedComponents = new ArrayList<ArrayList<Integer>>();
        for (int index = 0; index < mTotalVertex; ++index) {
            if (!isVisited[index]) {
                ArrayList<Integer> subComponents = new ArrayList<Integer>();
                isVisited[index] = true;
                stack.add(index);

                while (!stack.isEmpty()) {
                    int vertex = stack.pop();
                    subComponents.add(vertex);

                    ArrayList<Vertex> innerList = graph.mAdjacencyList.get(vertex);
                    for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
                        if (!isVisited[innerList.get(innerIndex).value]) {
                            isVisited[innerList.get(innerIndex).value] = true;
                            stack.add(innerList.get(innerIndex).value);
                        }
                    }
                }
                connectedComponents.add(subComponents);
            }
        }

        System.out.println("Number of Components: " + connectedComponents.size());
        System.out.println();
        for (int index = 0; index < connectedComponents.size(); ++index) {
            ArrayList<Integer> innerList = connectedComponents.get(index);
            System.out.println("Component " + (index + 1) + ":");
            for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
                System.out.println(innerList.get(innerIndex));
            }
            System.out.println();
        }
        return connectedComponents;
    }

}
