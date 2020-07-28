package com.rough.note;
// { Driver Code Starts

// Initial Template for Java

// { Driver Code Starts
//Initial Template for Java
import java.util.ArrayList;
import java.util.Scanner;

class DriverClass {
    static void printSolution(int dist[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(dist[i] + " ");
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.next());

        while (t > 0) {
            int V = Integer.parseInt(sc.next());
            ;
            ArrayList<ArrayList<Integer>> list = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                ArrayList<Integer> temp = new ArrayList<>(V);
                list.add(i, temp);
            }

            for (int i = 0; i < V; i++) {
                ArrayList<Integer> temp = list.get(i);
                for (int j = 0; j < V; j++) {
                    temp.add(Integer.parseInt(sc.next()));
                }
                list.add(temp);
            }
            int s = Integer.parseInt(sc.next());
            ;
            Solution T = new Solution();
            int[] res = T.dijkstra(list, s, V);
            printSolution(res, V);
            System.out.println();
            t--;
        }
    }
}// } Driver Code Ends

//User function Template for Java

class Graph {
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

    public Graph(int numberOfVertices) {
        mTotalVertex = numberOfVertices;
        mAdjacencyList = new ArrayList<ArrayList<Vertex>>();
        mAdjacencyList.clear();
        for (int index = 0; index < numberOfVertices; ++index) {
            mAdjacencyList.add(new ArrayList<Vertex>());
        }
    }

    public void addEdge(int from, int to, int distance) {
        Vertex node = null;
        node = new Vertex(to, distance);
        mAdjacencyList.get(from).add(node);
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

    public int[] shortestDistanceDijkstra(int sourceVertex) {
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
        return distance;
    }
}

/*
 * g: vector of vectors which represents the graph src: source vertex to start traversing graph with V: number of vertices
 */
class Solution {
    static int[] dijkstra(ArrayList<ArrayList<Integer>> g, int src, int V) {
        Graph graph = new Graph(V);
        for (int row = 0; row < V; ++row) {
            for (int column = 0; column < V; ++column) {
                if (g.get(row).get(column) != 0) {
                    graph.addEdge(row, column, g.get(row).get(column));
                }
            }
        }

        return graph.shortestDistanceDijkstra(src);
    }
}
