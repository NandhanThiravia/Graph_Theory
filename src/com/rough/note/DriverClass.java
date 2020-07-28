package com.rough.note;
// { Driver Code Starts

// Initial Template for Java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DriverClass {

    public static void main(String args[]) throws IOException {

        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String str[] = read.readLine().trim().split(" ");
            int V = Integer.parseInt(str[0]);
            int E = Integer.parseInt(str[1]);

            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int j = 0; j < V; j++)
                    temp.add(Integer.MAX_VALUE);
                graph.add(temp);
            }
            str = read.readLine().trim().split(" ");
            int k = 0;
            int i = 0;
            while (i++ < E) {
                int u = Integer.parseInt(str[k++]);
                int v = Integer.parseInt(str[k++]);
                int w = Integer.parseInt(str[k++]);
                u--;
                v--;
                graph.get(u).set(v, w);
                graph.get(v).set(u, w);
            }

            System.out.println(new MST().spanningTree(V, E, graph));
        }
    }
}
// } Driver Code Ends

// User function Template for Java

class Graph {
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

    public Graph(int numberOfVertices) {
        mTotalVertex = numberOfVertices;
        mAdjacencyList = new ArrayList<ArrayList<Vertex>>();
        mAdjacencyList.clear();
        for (int index = 0; index < numberOfVertices; ++index) {
            mAdjacencyList.add(new ArrayList<Vertex>());
        }
    }

    public void addEdge(int from, int to, int distance) {
        Vertex node = new Vertex(to, distance);
        mAdjacencyList.get(from).add(node);
    }

    public int minSpanningTree(int sourceVertex) {
        int totalWeight = 0;
        // ArrayList<ArrayList<Vertex>> minSpanningTree = new ArrayList<ArrayList<Vertex>>();
        ArrayList<Integer> inMSTList = new ArrayList<Integer>();
        ArrayList<Integer> outMSTList = new ArrayList<Integer>();

        boolean isVisited[] = new boolean[mTotalVertex];
        inMSTList.add(sourceVertex);
        for (int vertex = 0; vertex < mTotalVertex; ++vertex) {
            if (vertex != sourceVertex) {
                outMSTList.add(vertex);
            }
            // minSpanningTree.add(new ArrayList<Vertex>());
        }
        isVisited[sourceVertex] = true;

        while (!outMSTList.isEmpty()) {
            // int fromVertex = -1;
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
                        // fromVertex = inMSTVertex;
                    }
                }
            }
            totalWeight += nearesetNeighbourDistance;
            // minSpanningTree.get(fromVertex).add(new Vertex(nearestNeighbour, nearesetNeighbourDistance));
            // minSpanningTree.get(nearestNeighbour).add(new Vertex(fromVertex, nearesetNeighbourDistance));
            isVisited[nearestNeighbour] = true;
            inMSTList.add(nearestNeighbour);
            outMSTList.remove(new Integer(nearestNeighbour)); // Because removing an element taken Object as argument
        }

        return totalWeight;
    }
}

class MST {
    int spanningTree(int V, int E, ArrayList<ArrayList<Integer>> graph) {
        Graph localGraph = new Graph(V);
        for (int i = 0; i < V; i++) {
            ArrayList<Integer> innerList = graph.get(i);
            for (int j = 0; j < V; j++) {
                int distance = innerList.get(j);
                if (distance != Integer.MAX_VALUE) {
                    localGraph.addEdge(i, j, distance);
                }
            }
        }
        int totalWeight = localGraph.minSpanningTree(0);
        return totalWeight;
    }
}
