package com.adjacency.list;

import java.util.ArrayList;

import com.adjacency.list.Graph2.Algorithm;
import com.adjacency.list.Graph2.Type;
import com.adjacency.list.Graph2.Vertex;

public class Graph2Test {
    private static void analyzeGraph1() {
        Graph2 graph = new Graph2(6, Type.UNDIRECTED);

        // Adjacency List Representation
        // ----------------------------
        // [0] -> 1(1) -> 4(1) -> NULL
        // [1] -> 0(1) -> 4(1) -> 3(1) -> 2(1) -> NULL
        // [2] -> 1(1) -> 3(1) -> NULL
        // [3] -> 1(1) -> 2(1) -> 4(1) -> NULL
        // [4] -> 0(1) -> 1(1) -> 3(1) -> 5(1) -> NULL
        // [5] -> 4(1) -> NULL

        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 4);
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        System.out.println("Graph 1");
        System.out.println("--------");
        graph.display();
        System.out.println();
        graph.breadthFirstTraversal();
        System.out.println();
        graph.depthFirstTraversal();
    }

    private static void analyzeGraph2() {
        Graph2 graph = new Graph2(10, Type.DIRECTED);

        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 1(1) -> 5(1) -> NULL
        // [1] -> 7(1) -> NULL
        // [2] -> NULL
        // [3] -> 2(1) -> 4(1) -> 8(1) -> NULL
        // [4] -> 8(1) -> NULL
        // [5] -> NULL
        // [6] -> 0(1) -> 1(1) -> 2(1) -> NULL
        // [7] -> NULL
        // [8] -> 2(1) -> NULL
        // [9] -> 4(1) -> NULL

        graph.addEdge(0, 1);
        graph.addEdge(0, 5);
        graph.addEdge(1, 7);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);
        graph.addEdge(3, 8);
        graph.addEdge(4, 8);
        graph.addEdge(6, 0);
        graph.addEdge(6, 1);
        graph.addEdge(6, 2);
        graph.addEdge(8, 2);
        graph.addEdge(9, 4);

        graph.display();
        graph.isCyclic(Algorithm.KAHN);
        graph.topologicalSort(Algorithm.DFS);
        graph.topologicalSort(Algorithm.KAHN);
        graph.hasPath(3, 3);
    }

    private static void analyzeGraph3() {
        Graph2 graph = new Graph2(6, Type.DIRECTED);
        // Second Graph
        // Adjacency List Representation
        // ----------------------------
        // [0] -> NULL
        // [1] -> NULL
        // [2] -> 3(1) -> NULL
        // [3] -> 1(1) -> NULL
        // [4] -> 0(1) -> 1(1) -> NULL
        // [5] -> 0(1) -> 2(1) -> NULL

        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(5, 0);
        graph.addEdge(5, 2);

        System.out.println();
        System.out.println("Graph 3");
        System.out.println("--------");
        graph.display();
        System.out.println();
        graph.topologicalSort(Algorithm.DFS);
    }

    private static void analyzeGraph4() {
        Graph2 graph = new Graph2(7, Type.UNDIRECTED);

        // Adjacency List Representation
        // -------------------------------
        // [0] -> 1(1) -> 2(1) -> NULL
        // [1] -> 0(1) -> 3(1) -> NULL
        // [2] -> 0(1) -> 3(1) -> NULL
        // [3] -> 1(1) -> 2(1) -> NULL
        // [4] -> 5(1) -> 6(1) -> NULL
        // [5] -> 4(1) -> 6(1) -> NULL
        // [6] -> 4(1) -> 5(1) -> NULL

        // Has 2 Islands in this graph

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);

        graph.addEdge(1, 3);

        graph.addEdge(2, 3);

        graph.addEdge(4, 5);
        graph.addEdge(4, 6);

        graph.addEdge(5, 6);

        graph.display();

        graph.breadthFirstTraversal();

        graph.depthFirstTraversal();
    }

    private static void analyzeGraph5() {
        Graph2 graph = new Graph2(6, Type.UNDIRECTED);

        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 4(1) -> 2(1) -> 1(1) -> NULL
        // [1] -> 0(1) -> 3(1) -> NULL
        // [2] -> 0(1) -> 4(1) -> 3(1) -> NULL
        // [3] -> 1(1) -> 5(1) -> 2(1) -> NULL
        // [4] -> 0(1) -> 2(1) -> 5(1) -> NULL
        // [5] -> 3(1) -> 4(1) -> NULL

        graph.addEdge(0, 4);
        graph.addEdge(0, 2);
        graph.addEdge(0, 1);

        graph.addEdge(1, 3);

        graph.addEdge(2, 4);

        graph.addEdge(3, 5);
        graph.addEdge(3, 2);

        graph.addEdge(4, 5);

        graph.display();
        graph.shortestDistance(0, Algorithm.BFS);
        graph.shortestDistance(1, Algorithm.BFS);

        graph.isCyclic(Algorithm.DFS);
    }

    private static void analyzeGraph6() {
        Graph2 graph = new Graph2(3, Type.UNDIRECTED);

        // Adjacency List Representation
        // -------------------------------
        // [0] -> 1(1) -> 2(1) -> NULL
        // [1] -> 0(1) -> 2(1) -> NULL
        // [2] -> 1(1) -> 0(1) -> NULL

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        graph.display();
        graph.isCyclic(Algorithm.DFS);
    }

    private static void analyzeGraph7() {
        Graph2 graph = new Graph2(6, Type.DIRECTED);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 5);
        graph.addEdge(3, 4);
        graph.addEdge(5, 1);
        graph.display();

        graph.isCyclic(Algorithm.KAHN);
        graph.shortestDistance(1, Algorithm.BFS);
    }

    private static void analyzeGraph8() {
        Graph2 graph = new Graph2(8, Type.DIRECTED);

        // Adjacency List Representation
        // ----------------------------
        // [0] -> 1(1) -> NULL
        // [1] -> 2(1) -> NULL
        // [2] -> 3(1) -> NULL
        // [3] -> 4(1) -> NULL
        // [4] -> 5(1) -> NULL
        // [5] -> 6(1) -> NULL
        // [6] -> 7(1) -> NULL
        // [7] -> 0(1) -> NULL

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 0);
        graph.display();

        graph.isCyclic(Algorithm.KAHN);
        graph.topologicalSort(Algorithm.DFS);
    }

    private static void analyzeGraph9() {
        Graph2 graph = new Graph2(6, Type.DIRECTED);

        // Adjacency List Representation
        // ----------------------------
        // [0] -> 1(1) -> NULL
        // [1] -> NULL
        // [2] -> 3(1) -> 1(1) -> NULL
        // [3] -> 4(1) -> NULL
        // [4] -> 5(1) -> NULL
        // [5] -> 2(1) -> NULL

        graph.addEdge(0, 1);
        graph.addEdge(2, 3);
        graph.addEdge(2, 1);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 2);

        graph.display();
        graph.isCyclic(Algorithm.KAHN);
        graph.topologicalSort(Algorithm.DFS);
    }

    private static void analyzeGraph10() {
        Graph2 graph = new Graph2(6, Type.DIRECTED);

        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 1(1) -> NULL
        // [1] -> NULL
        // [2] -> 1(1) -> 3(1) -> NULL
        // [3] -> 4(1) -> NULL
        // [4] -> 5(1) -> NULL
        // [5] -> 1(1) -> NULL

        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 1);

        graph.display();
        graph.isCyclic(Algorithm.KAHN);

        graph.topologicalSort(Algorithm.DFS);
        graph.topologicalSort(Algorithm.KAHN);
    }

    private static void analyzeGraph11() {
        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 2(1) -> 3(1) -> NULL
        // [1] -> 3(1) -> 4(1) -> NULL
        // [2] -> NULL
        // [3] -> NULL
        // [4] -> NULL

        Graph2 graph = new Graph2(5, Type.DIRECTED);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);

        graph.display();
        graph.topologicalSort(Algorithm.DFS);
        graph.topologicalSort(Algorithm.KAHN);
    }

    private static void analyzeGraph12() {
        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 1(1) -> 2(1) -> NULL
        // [1] -> 3(1) -> NULL
        // [2] -> 3(1) -> NULL
        // [3] -> 4(1) -> 5(1) -> NULL
        // [4] -> NULL
        // [5] -> NULL

        Graph2 graph = new Graph2(6, Type.DIRECTED);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);

        graph.display();
        graph.topologicalSort(Algorithm.DFS);
        graph.shortestDistance(0, Algorithm.BFS);

        graph.countOfPath(0, 3);
    }

    private static void analyzeGraph13() {
        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 4(1) -> 1(2) -> NULL
        // [1] -> 2(3) -> NULL
        // [2] -> 3(6) -> NULL
        // [3] -> NULL
        // [4] -> 5(4) -> 2(2) -> NULL
        // [5] -> 3(1) -> NULL

        Graph2 graph = new Graph2(6, Type.DIRECTED);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 4, 1);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 6);
        graph.addEdge(4, 2, 2);
        graph.addEdge(4, 5, 4);
        graph.addEdge(5, 3, 1);

        graph.display();
        graph.topologicalSort(Algorithm.DFS);
        graph.shortestDistance(0, Algorithm.BFS);
        graph.shortestDistance(1, Algorithm.BFS);
    }

    private static void analyzeGraph14() {
        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 4(1) -> 1(2) -> NULL
        // [1] -> 2(3) -> NULL
        // [2] -> 3(6) -> NULL
        // [3] -> NULL
        // [4] -> 5(4) -> 2(2) -> NULL
        // [5] -> 3(1) -> NULL

        Graph2 graph = new Graph2(7, Type.DIRECTED);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 5, 1);
        graph.addEdge(1, 2, 20);
        graph.addEdge(2, 3, 15);
        graph.addEdge(3, 4, 10);
        graph.addEdge(5, 6, 1);
        graph.addEdge(6, 1, 1);

        graph.display();
        graph.topologicalSort(Algorithm.DFS);
        graph.shortestDistance(0, Algorithm.BFS);
    }

    private static void analyzeGraph15() {
        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 1(1) -> 2(1) -> NULL
        // [1] -> 0(1) -> 3(1) -> 4(1) -> 5(1) -> NULL
        // [2] -> 0(1) -> 6(1) -> NULL
        // [3] -> 1(1) -> NULL
        // [4] -> 1(1) -> NULL
        // [5] -> 1(1) -> NULL
        // [6] -> 2(1) -> 7(1) -> NULL
        // [7] -> 6(1) -> NULL
        Graph2 graph = new Graph2(8, Type.UNDIRECTED);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 5);
        graph.addEdge(2, 6);
        graph.addEdge(6, 7);

        graph.display();

        graph.displayLevel(0, 7);
    }

    private static void analyzeGraph16() {
        // Adjacency List Representation
        // -------------------------------
        // [0] -> 1(2) -> 2(5) -> NULL
        // [1] -> 0(2) -> 3(1) -> 4(9) -> NULL
        // [2] -> 0(5) -> 5(3) -> 3(7) -> NULL
        // [3] -> 1(1) -> 2(7) -> 4(4) -> 6(8) -> NULL
        // [4] -> 1(9) -> 3(4) -> NULL
        // [5] -> 2(3) -> 6(6) -> NULL
        // [6] -> 3(8) -> 5(6) -> NULL

        int numberOfVertices = 7;
        Graph2 graph = new Graph2(numberOfVertices, Type.UNDIRECTED);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 5);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 9);
        graph.addEdge(2, 5, 3);
        graph.addEdge(2, 3, 7);
        graph.addEdge(3, 4, 4);
        graph.addEdge(3, 6, 8);
        graph.addEdge(5, 6, 6);

        graph.display();

        ArrayList<ArrayList<Vertex>> minSpanningTree = graph.minSpanningTree(0);
        System.out.println("Minimum Spanning Tree Representation");
        System.out.println("--------------------------------------");
        for (int index = 0; index < numberOfVertices; index++) {
            System.out.print("[" + index + "] -> ");
            ArrayList<Vertex> innerList = minSpanningTree.get(index);
            for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
                System.out.print(innerList.get(innerIndex).value + "(" + innerList.get(innerIndex).distance + ")" + " -> ");
            }
            System.out.println("NULL");
        }
        System.out.println();
    }

    private static void analyzeGraph17() {
        // Adjacency List Representation
        // -------------------------------
        // [0] -> 1(4) -> 2(8) -> NULL
        // [1] -> 0(4) -> 3(8) -> 2(11) -> NULL
        // [2] -> 0(8) -> 1(11) -> 4(7) -> 5(1) -> NULL
        // [3] -> 1(8) -> 4(2) -> 7(4) -> 6(7) -> NULL
        // [4] -> 2(7) -> 3(2) -> 5(6) -> NULL
        // [5] -> 2(1) -> 4(6) -> 7(2) -> NULL
        // [6] -> 3(7) -> 7(14) -> 8(9) -> NULL
        // [7] -> 3(4) -> 5(2) -> 6(14) -> 8(10) -> NULL
        // [8] -> 6(9) -> 7(10) -> NULL

        int numberOfVertices = 9;
        Graph2 graph = new Graph2(numberOfVertices, Type.UNDIRECTED);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 8);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 2, 11);
        graph.addEdge(2, 4, 7);
        graph.addEdge(2, 5, 1);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 7, 4);
        graph.addEdge(3, 6, 7);
        graph.addEdge(4, 5, 6);
        graph.addEdge(5, 7, 2);
        graph.addEdge(6, 7, 14);
        graph.addEdge(6, 8, 9);
        graph.addEdge(7, 8, 10);

        graph.display();
        graph.shortestDistance(0, Algorithm.DIJKSTRA);
    }

    private static void analyzeGraph18() {
        // Adjacency List Representation
        // -------------------------------
        // [0] -> 1(1) -> 2(4) -> NULL
        // [1] -> 3(2) -> 2(-3) -> NULL
        // [2] -> 3(3) -> NULL
        // [3] -> NULL

        int numberOfVertixes = 4;
        Graph2 graph = new Graph2(numberOfVertixes, Type.DIRECTED);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 2, -3);
        graph.addEdge(2, 3, 3);

        graph.display();

        graph.shortestDistance(0, Algorithm.BELLMAN_FORD);
        graph.shortestDistance(1, Algorithm.BELLMAN_FORD);
        graph.shortestDistance(2, Algorithm.BELLMAN_FORD);
        graph.shortestDistance(3, Algorithm.BELLMAN_FORD);
    }

    private static void analyzeGraph19() {
        // Adjacency List Representation
        // -------------------------------
        // [0] -> 1(1) -> NULL
        // [1] -> 2(1) -> 3(1) -> NULL
        // [2] -> 0(1) -> NULL
        // [3] -> 4(1) -> NULL
        // [4] -> NULL

        int numberOfVertices = 5;
        Graph2 graph = new Graph2(numberOfVertices, Type.DIRECTED);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);

        graph.display();
        graph.getConnectedComponents(Algorithm.KOSARAJU);
    }

    private static void analyzeGraph20() {
        // Adjacency List Representation
        // -------------------------------
        // [0] -> 1(1) -> NULL
        // [1] -> 2(1) -> NULL
        // [2] -> 3(1) -> NULL
        // [3] -> 0(1) -> 4(1) -> NULL
        // [4] -> 5(1) -> NULL
        // [5] -> 4(1) -> NULL

        int numberOfVertices = 6;
        Graph2 graph = new Graph2(numberOfVertices, Type.DIRECTED);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 4);

        graph.display();
        graph.getConnectedComponents(Algorithm.KOSARAJU);
    }

    private static void analyzeGraph21() {
        // Adjacency List Representation
        // -------------------------------
        // [0] -> 1(1) -> NULL
        // [1] -> 3(1) -> 2(1) -> NULL
        // [2] -> NULL
        // [3] -> 0(1) -> NULL

        int numberOfVertices = 4;
        Graph2 graph = new Graph2(numberOfVertices, Type.DIRECTED);
        graph.addEdge(0, 1);
        graph.addEdge(1, 3);
        graph.addEdge(3, 0);
        graph.addEdge(1, 2);

        graph.display();
        graph.getConnectedComponents(Algorithm.KOSARAJU);
    }

    public static void main(String[] args) {
        // analyzeGraph1();
        // analyzeGraph2();
        // analyzeGraph3();
        // analyzeGraph4();
        // analyzeGraph5();
        // analyzeGraph6();
        // analyzeGraph7();
        // analyzeGraph8();
        // analyzeGraph9();
        // analyzeGraph10();
        // analyzeGraph11();
        // analyzeGraph12();
        // analyzeGraph13();
        // analyzeGraph14();
        // analyzeGraph15();
        // analyzeGraph16();
        // analyzeGraph17();
        // analyzeGraph18();
        // analyzeGraph19();
        // analyzeGraph20();
        analyzeGraph21();
    }
}
